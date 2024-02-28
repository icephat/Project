package se.springboot.sharitytest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.springboot.sharitytest.model.ConditionOfActivity;
import se.springboot.sharitytest.repository.ConditionOfActivityRepository;

@Service
public class ConditionOfActivityService {
	
	@Autowired
	private ConditionOfActivityRepository conditionOfActivityRepository;
	
	public List<ConditionOfActivity> findAll() {
		return (List<ConditionOfActivity>) conditionOfActivityRepository.findAll();
	}
	
	public ConditionOfActivity findById(int id) {
		return conditionOfActivityRepository.findById(id).get();
	}
	
	public void save(ConditionOfActivity conditionOfActivity) {
		conditionOfActivityRepository.save(conditionOfActivity);
	}
	
	public void delete(int id) {
		conditionOfActivityRepository.deleteById(id);
	}

}
