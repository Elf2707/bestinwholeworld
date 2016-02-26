package ua.lorien.bestinwholeworld.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.lorien.bestinwholeworld.model.Comment;

public interface CommentCrudRepository extends JpaRepository<Comment, Long> {
}
