package com.arthurdrabazha.openmind.repository;

import com.arthurdrabazha.openmind.model.Category;
import com.arthurdrabazha.openmind.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByUsername(String username);

    List<User> findUsersByCategoriesContains(List<Category> categories);

    @Query(value = "SELECT old_password_digest " +
            "FROM user_passwords " +
            "WHERE user_id=:id",
            nativeQuery = true)
    List<String> findAllUsedPasswordsForUser(@Param("id") Long id);

    @Query(value = "INSERT INTO user_passwords (user_id, old_password_digest)" +
            "VALUES user_id=:id, old_password_digest=:password",
            nativeQuery = true)
    void createUserUsedPassword(@Param("password") String passwordDigest, @Param("id") Long userId);
}
