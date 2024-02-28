package se.springboot.sharitytest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.springboot.sharitytest.model.Comment;
import se.springboot.sharitytest.model.User;
import se.springboot.sharitytest.repository.CommentRepository;

@Service
public class CommentService {

	@Autowired
	CommentRepository commentRepository;

	public List<Comment> findAll() {
		return (List<Comment>) commentRepository.findAll();
	}
	
	public Comment findById(int commentId) {
		return commentRepository.findById(commentId).get();
	}
	
    public List<Comment> getCommentsByActivityId(int activityId) {
        return commentRepository.getCommentsByActivityId(activityId);
    }
	
	public void save(Comment comment) {
		commentRepository.save(comment);
	}
	
	public void delete(int id) {
		commentRepository.deleteById(id);
	}
	
}
