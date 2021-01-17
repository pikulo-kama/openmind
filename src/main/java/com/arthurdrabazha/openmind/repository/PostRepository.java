package com.arthurdrabazha.openmind.repository;

import com.arthurdrabazha.openmind.model.Category;
import com.arthurdrabazha.openmind.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findPostsByAuthorId(Long authorId);

    List<Post> findAllByCategoriesContains(Category category);

    List<Post> findAllByTopicContains(String topic);

    List<Post> findAllByIdNotNullOrderByLikesAsc();

    List<Post> findAllByIdNotNullOrderByLikesDesc();

}
