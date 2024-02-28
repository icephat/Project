package se.springboot.sharitytest.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import se.springboot.sharitytest.model.Activity;

@Repository
public interface ActivityRepository extends CrudRepository<Activity, Integer> {

	@Query("from Activity a inner join a.user")
	public List<Activity> findAllWithUser();
	
	@Query("from Activity a inner join a.user where a.activityId = :activityId")
	public Activity findActivityWithUserById(@Param("activityId") int activityId);

	@Query("from Activity a join a.user u where u.userId = :userId")
	public List<Activity> findByIdUser(@Param("userId") int userId);
	
	@Query("from Activity a where a.status = :status and a.dateActivity >= :date")
	public List<Activity> findByStatusANdDate(@Param("status")String status,@Param("date")Date date);
	
	@Query("from Activity a where a.status = :status and a.user.userId = :userId")
	public List<Activity> findByStatusAndUserId(@Param("status")String status,@Param("userId")int userId);
}
