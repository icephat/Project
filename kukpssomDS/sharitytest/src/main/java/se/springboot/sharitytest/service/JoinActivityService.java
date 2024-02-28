package se.springboot.sharitytest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.springboot.sharitytest.model.JoinActivity;
import se.springboot.sharitytest.repository.JoinActivityRepository;

@Service
public class JoinActivityService {
	
	@Autowired
	JoinActivityRepository joinActivityRepository;
	
	public List<JoinActivity> findAll() {
		return (List<JoinActivity>) joinActivityRepository.findAll();
	}
	
	public JoinActivity findById(int id) {
		return joinActivityRepository.findById(id).get();
	}
	
	
	public void save(JoinActivity joinActivity) {
		joinActivityRepository.save(joinActivity);
	}
	
	public void delete(int id) {
		joinActivityRepository.deleteById(id);
	}
	
	public List<JoinActivity> findJoinActivityByUserId(int userId) {
        return (List<JoinActivity>) joinActivityRepository.findJoinActivityByUserId(userId);
    }
	
	
	public List<JoinActivity> findJoinActivityByActivityId(int activityId,String status) {
		return (List<JoinActivity>) joinActivityRepository.findJoinActivityByActivityId(activityId,status);
	}
	
	public JoinActivity findJoinActivityByUserIdAndActivityId(int userId,int activityId) {
		return joinActivityRepository.findJoinActivityByUserIdAndActivityId(userId,activityId);
	}
	
}
