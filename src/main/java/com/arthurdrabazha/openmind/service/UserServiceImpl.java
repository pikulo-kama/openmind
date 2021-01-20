package com.arthurdrabazha.openmind.service;

import com.arthurdrabazha.openmind.dto.CreateUserDto;
import com.arthurdrabazha.openmind.dto.UpdateUserDto;
import com.arthurdrabazha.openmind.dto.UpdateUserPasswordDto;
import com.arthurdrabazha.openmind.exception.DatabaseLookupException;
import com.arthurdrabazha.openmind.exception.ServiceException;
import com.arthurdrabazha.openmind.model.Category;
import com.arthurdrabazha.openmind.model.User;
import com.arthurdrabazha.openmind.model.UserRole;
import com.arthurdrabazha.openmind.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Value("${user.minimal_age}")
    private Integer MINIMUM_USER_AGE;

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) throws DatabaseLookupException {
        return userRepository.findById(id)
                .orElseThrow(() -> new DatabaseLookupException("User with such id doesn't exist"));
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with such username doesn't exist"));
    }

    @Override
    public List<User> findByCategory(Category category) {
        return userRepository.findUsersByCategoriesContains(category);
    }

    @Transactional
    @Override
    public void create(CreateUserDto createUserDto, UserRole role) {

        if (userRepository.existsUserByUsername(createUserDto.getUsername())) {
            throw new ServiceException("User with this username already exists");
        }

        if (!StringUtils.equals(createUserDto.getPassword(), createUserDto.getRepeatPassword())) {
            throw new ServiceException("Password doesn't match");
        }

        if (ageNotValid(createUserDto.getBirthDate())) {
            throw new ServiceException(String.format("You should be at least %d y.o.", MINIMUM_USER_AGE));
        }

        User newUser = User.builder()
                .username(createUserDto.getUsername())
                .role(role)
                .email(createUserDto.getEmail())
                .password(passwordEncoder.encode(createUserDto.getPassword()))
                .birthDate(createUserDto.getBirthDate().toLocalDate())
                .createDate(Timestamp.valueOf(LocalDateTime.now()))
                .updateDate(Timestamp.valueOf(LocalDateTime.now()))
                .likes(0L)
                .dislikes(0L)
                .isEnabled(Boolean.TRUE)
                .nonActiveDaysBeforeDelete(createUserDto.getDeletePeriod().getNumericalValue())
                .categories(createUserDto.getCategories())
                .build();

        userRepository.save(newUser);
    }

    @Transactional
    @Override
    public User update(User user, UpdateUserDto updateUserDto) {

        User updatedUser = user.toBuilder()
                .username(updateUserDto.getUsername())
                .categories(updateUserDto.getCategories())
                .nonActiveDaysBeforeDelete(updateUserDto.getDeletePeriod().getNumericalValue())
                .build();

        userRepository.save(updatedUser);

        return updatedUser;
    }

    @Transactional
    @Override
    public void delete(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ServiceException("User not found"));

        if (!user.isEnabled()) {
            throw new ServiceException("User should be disabled, in order to delete him");
        }

        if (user.getRole() == UserRole.ROLE_ADMIN) {
            throw new ServiceException("Permission denied! Attempt to delete administrator account");
        }

        userRepository.deleteById(user.getId());
    }

    @Transactional
    @Override
    public void updatePassword(User user, UpdateUserPasswordDto updateUserPasswordDto) {

        if (!passwordEncoder.matches(updateUserPasswordDto.getOldPassword(), user.getPassword())) {
            throw new ServiceException("Old password is incorrect");
        }

        if (!StringUtils.equals(updateUserPasswordDto.getPassword(), updateUserPasswordDto.getPasswordRepeat())) {
            throw new ServiceException("Password doesn't match");
        }

        if (passwordWasUsed(user.getId(), updateUserPasswordDto.getPassword())) {
            throw new ServiceException("You recently used this password");
        }

        userRepository.storeOldPassword(updateUserPasswordDto.getOldPassword(), user.getId());

        User updatedUser = user.toBuilder()
                .password(passwordEncoder.encode(updateUserPasswordDto.getPassword()))
                .build();

        userRepository.save(updatedUser);
    }

    @Override
    public void activate(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ServiceException("User not found"));

        if (user.getRole() == UserRole.ROLE_ADMIN) {
            throw new ServiceException("Permission denied! Attempt to deactivate administrator account");
        }

        setAccountStatus(user, Boolean.TRUE);
    }

    @Override
    public void deactivate(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ServiceException("User not found"));

        setAccountStatus(user, Boolean.FALSE);
    }

    private void setAccountStatus(User user, Boolean status) {
        User updatedUser = user.toBuilder()
                .isEnabled(status)
                .build();

        userRepository.save(updatedUser);
    }


    private Boolean passwordWasUsed(Long userId, String password) {
        List<String> passwords = userRepository.findAllUsedPasswordsForUser(userId);

        return passwords.contains(password);
    }

    private boolean ageNotValid(LocalDateTime birthDate) {
        long age = ChronoUnit.YEARS.between(LocalDateTime.now(), birthDate);

        return age < MINIMUM_USER_AGE;
    }

}
