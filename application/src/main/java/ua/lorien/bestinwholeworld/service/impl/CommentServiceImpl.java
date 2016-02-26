package ua.lorien.bestinwholeworld.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ua.lorien.bestinwholeworld.model.Comment;
import ua.lorien.bestinwholeworld.repository.CommentCrudRepository;
import ua.lorien.bestinwholeworld.service.CommentService;

public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentCrudRepository commentCrudRepository;
	
	@Override
	public Comment add(Comment comment){
		return commentCrudRepository.save(comment);
	}

	@Override
	public void delete(Long id){
		commentCrudRepository.delete(id);
	}

	@Override
	public List<Comment> findAll(){
		return commentCrudRepository.findAll();
	}

	@Override
	public Comment findById(Long id){
		return commentCrudRepository.findOne(id);
	}

	@Override
	public void deleteAll() {
		commentCrudRepository.deleteAll();
	}
}
