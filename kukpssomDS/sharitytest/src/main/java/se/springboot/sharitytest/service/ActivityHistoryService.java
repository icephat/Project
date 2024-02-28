package se.springboot.sharitytest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.springboot.sharitytest.model.JoinActivity;

import se.springboot.sharitytest.repository.ActivityHistoryRepository;

@Service
public class ActivityHistoryService {
	
	@Autowired
	private ActivityHistoryRepository activityHistoryRepository;
	
	public List<JoinActivity> findByUsername(Integer userId){											//add param
		return (List<JoinActivity>) activityHistoryRepository.findByUsername(userId);			//add param
	}
	public List<List<Object>> getActivityUserList() {
        return activityHistoryRepository.getActivityUserListWithScore();
    }
}
