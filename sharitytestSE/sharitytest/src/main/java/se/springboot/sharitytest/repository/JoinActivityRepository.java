package se.springboot.sharitytest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import se.springboot.sharitytest.model.JoinActivity;

@Repository
public interface JoinActivityRepository  extends CrudRepository<JoinActivity, Integer> {

	@Query("from JoinActivity j inner join j.user u where j.activity.activityId = :activityId and j.status = :status")
	 public List<JoinActivity> findJoinActivityByActivityId(@Param("activityId")int activityId,@Param("status")String status);
	
	@Query("from JoinActivity j where j.user.userId = :userId")
    public List<JoinActivity> findJoinActivityByUserId(@Param("userId")int userId);
	
	@Query("from JoinActivity j inner join j.user u inner join j.activity a where u.userId = :userId and a.activityId = :activityId")
	public JoinActivity findJoinActivityByUserIdAndActivityId(@Param("userId")Integer userId,@Param("activityId")Integer activityId);

}
