package com.arthurdrabazha.openmind.service;

import com.arthurdrabazha.openmind.dto.CreateUserDto;
import com.arthurdrabazha.openmind.dto.UpdateUserDto;
import com.arthurdrabazha.openmind.dto.UpdateUserPasswordDto;
import com.arthurdrabazha.openmind.model.Category;
import com.arthurdrabazha.openmind.model.User;
import com.arthurdrabazha.openmind.model.UserRole;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findById(Long id);

    User findByUsername(String username);

    List<User> findByCategory(Category category);

    void create(CreateUserDto createUserDto, UserRole userRole);

    User update(User user, UpdateUserDto updateUserDto);

    void delete(Long userId);

    void updatePassword(User user, UpdateUserPasswordDto updateUserPasswordDto);

    void activate(Long userId);

    void deactivate(Long userId);

}
