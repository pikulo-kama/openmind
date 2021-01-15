package com.arthurdrabazha.openmind.repository;

import com.arthurdrabazha.openmind.model.Category;
import com.arthurdrabazha.openmind.model.Post;
import com.arthurdrabazha.openmind.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByAuthor(User author);

    List<Post> findAllByCategoriesContains(Category category);

    List<Post> findAllByTopicContains(String topic);

    List<Post> findPostsByDislikesNotNullOrderByLikesAsc();

    List<Post> findPostsByDislikesNotNullOrderByLikesDesc();

}
