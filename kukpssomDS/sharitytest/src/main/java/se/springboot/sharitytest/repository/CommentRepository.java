package se.springboot.sharitytest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import se.springboot.sharitytest.model.Comment;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Integer> {

    @Query("from Comment c where c.activity.activityId = :activityId")
	public List<Comment> getCommentsByActivityId(@Param("activityId") int activityId);
}
