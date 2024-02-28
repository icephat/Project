package se.springboot.sharitytest.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import se.springboot.sharitytest.model.User;
import se.springboot.sharitytest.model.JoinActivity;

@Repository
public interface ActivityHistoryRepository extends CrudRepository<JoinActivity, Integer> {
	
	@Query("from JoinActivity j inner join j.user u inner join j.activity a where u.userId = :userId and j.status='apporve' order by a.activityId ASC")
	public List<JoinActivity> findByUsername(@Param("userId")Integer userId);						//add param
	
	@Query("SELECT ja.activity.activityId, ja.user, ja.score,ja.comment FROM JoinActivity ja")
	List<Object[]> findActivityUsersWithScore();

	default List<List<Object>> getActivityUserListWithScore() {
	    List<Object[]> results = findActivityUsersWithScore();
	    Map<Integer, List<Object>> activityUserMap = new HashMap<>();
	    for (Object[] result : results) {
	        Integer activityId = (Integer) result[0];
	        User user = (User) result[1];
	        Integer score = (Integer) result[2];
	        String comment= (String) result[3];
	        if (!activityUserMap.containsKey(activityId)) {
	            activityUserMap.put(activityId, new ArrayList<>());
	        }
	        Map<String, Object> userWithScore = new HashMap<>();
	        userWithScore.put("userinfo", user);
	        userWithScore.put("score", score);
	        userWithScore.put("comment", comment);
	        activityUserMap.get(activityId).add(userWithScore);
	    }
	    List<List<Object>> activityUserList = new ArrayList<>();
	    for (Map.Entry<Integer, List<Object>> entry : activityUserMap.entrySet()) {
	        List<Object> activityUser = new ArrayList<>();
	        activityUser.add(entry.getKey()); // activity ID
	        activityUser.add(entry.getValue()); // list of users with score
	        activityUserList.add(activityUser);
	    }
	    return activityUserList;
	}
}
