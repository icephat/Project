package se.springboot.sharitytest.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import se.springboot.sharitytest.model.Activity;
import se.springboot.sharitytest.repository.ActivityRepository;

@Service
public class ActivityService {

	@Autowired(required=true)
	private ActivityRepository activityRepository;
	
	public List<Activity> findAll() {
		return (List<Activity>) activityRepository.findAll();
	}
	
	public Activity findById(int id) {
		return activityRepository.findById(id).get();
	}
	
	public void save(Activity activity) {
		activityRepository.save(activity);
	}
	
	public void delete(int id) {
		activityRepository.deleteById(id);
	}
	
	public List<Activity> findAllWithuser() {
		return (List<Activity>) activityRepository.findAllWithUser();
	}
	
	public Activity findActivityWithUserById(int activityId) {
		return activityRepository.findActivityWithUserById(activityId);
	}

	public List<Activity> findByIdUser(int userId) {
		return  (List<Activity>) activityRepository.findByIdUser(userId);
	}
	
	public List<Activity> findByStatusANdDate(String status,Date date){
		return activityRepository.findByStatusANdDate(status, date);
	}
	
	public List<Activity> findByStatusAndUserId(String status,int userId){
		return activityRepository.findByStatusAndUserId(status, userId);
	}
}
