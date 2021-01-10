package com.arthurdrabazha.openmind.service;

import com.arthurdrabazha.openmind.dto.ChangePasswordDto;
import com.arthurdrabazha.openmind.dto.CreateUserDto;
import com.arthurdrabazha.openmind.dto.NonActivePeriod;
import com.arthurdrabazha.openmind.exception.UserNotFoundException;
import com.arthurdrabazha.openmind.model.User;
import com.arthurdrabazha.openmind.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) throws UserNotFoundException {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User by such id does not exist"));
    }

    @Override
    public User create(CreateUserDto userDto) {

        User user = User.builder()
                .username(userDto.getUsername())
                .email(userDto.getUsername())
                .password(bCryptPasswordEncoder.encode(userDto.getPassword()))
                .birthDate(userDto.getBirthDate())
                .categories(userDto.getCategories())
                .createDate(Timestamp.valueOf(LocalDateTime.now()))
                .updateDate(Timestamp.valueOf(LocalDateTime.now()))
                .nonActiveDaysBeforeDelete(userDto.getDaysBeforeDelete())
                .build();

        return userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) throws UserNotFoundException {
        return Optional.ofNullable(userRepository.findUserByUsername(username))
                .orElseThrow(() -> new UserNotFoundException("User with such username does not exist."));
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void updatePassword(User user, ChangePasswordDto passwordDto) throws Exception {

        if (!bCryptPasswordEncoder.matches(passwordDto.getOldPassword(), user.getPassword())) {
            throw new Exception();
        }

        if (!StringUtils.equals(passwordDto.getNewPassword(), passwordDto.getNewPasswordRepeat())) {
            throw new Exception();
        }

        if (isPasswordWasUsed(user, passwordDto)) {
            throw new Exception();
        }

        User updatedUser = user.toBuilder()
                .password(bCryptPasswordEncoder.encode(passwordDto.getNewPassword()))
                .build();

        userRepository.save(updatedUser);
    }

    @Override
    public Boolean isPasswordWasUsed(User user, ChangePasswordDto changePasswordDto) {
        return userRepository.findAllUsedPasswordsForUser( user.getId() )
                .contains( bCryptPasswordEncoder.encode(changePasswordDto.getNewPassword()) );
    }

    @Override
    public Boolean isUsernameTaken(CreateUserDto createUserDto) {
        return Objects.nonNull( userRepository.findUserByUsername( createUserDto.getUsername() ) );
    }

    @Override
    public void activate(Long userId) {

    }

    @Override
    public void deactivate(Long userId) {

    }
}
