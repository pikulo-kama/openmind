package com.arthurdrabazha.openmind.repository;

import com.arthurdrabazha.openmind.model.Category;
import com.arthurdrabazha.openmind.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUsername(String username);
    List<User> findUsersByCategoriesContains(List<Category> categories);
}
