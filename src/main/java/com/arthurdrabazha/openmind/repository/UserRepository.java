package com.arthurdrabazha.openmind.repository;

import com.arthurdrabazha.openmind.model.Category;
import com.arthurdrabazha.openmind.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByUsername(String username);

    Boolean existsUserByUsername(String username);

    List<User> findUsersByCategoriesContains(Category categories);

    @Query(value = "SELECT old_password_digest " +
            "FROM user_passwords " +
            "WHERE user_id=:id",
            nativeQuery = true)

    List<String> findAllUsedPasswordsForUser(Long id);

    @Query(value = "INSERT INTO user_passwords (user_id, old_password_digest)" +
            "VALUES user_id=:id, old_password_digest=:password",
            nativeQuery = true)

    void storeOldPassword(@Param("password") String passwordDigest, @Param("id") Long userId);
}
