package com.arthurdrabazha.openmind.service;

import com.arthurdrabazha.openmind.dto.CreateUserDto;
import com.arthurdrabazha.openmind.exception.UserNotFoundException;
import com.arthurdrabazha.openmind.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findById(Long id) throws UserNotFoundException;

    User create(CreateUserDto userDto);

    User findByUsername(String username) throws UserNotFoundException;

    void deleteById(Long id);

    void updatePassword(User user, Object passwordDto);

    Boolean isPasswordWasUsed(String passwordDigest);

    Boolean isUsernameTaken(CreateUserDto createUserDto);

    void activate(Long userId);

    void deactivate(Long userId);
}
