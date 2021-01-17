package com.arthurdrabazha.openmind.repository;

import com.arthurdrabazha.openmind.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Short> {

    @Query(value = "SELECT category_id FROM users_categories WHERE user_id=:userId", nativeQuery = true)
    List<Category> findAllByUsersContains(Long userId);

    @Query(value = "SELECT category_id FROM posts_categories WHERE user_id=:postId", nativeQuery = true)
    List<Category> getAllByPostsContains(Long postId);

}
