package ua.lorien.bestinwholeworld.service;

import java.util.List;

import ua.lorien.bestinwholeworld.model.Comment;

public interface CommentService {
	Comment add(Comment comment);

	void delete(Long id);
	
	void deleteAll();

	List<Comment> findAll();

	Comment findById(Long id);
}