package se.springboot.sharitytest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.springboot.sharitytest.model.ConditionActivity;
import se.springboot.sharitytest.repository.ConditionActivityRepository;

@Service
public class ConditionActivityService {
	
	@Autowired
	ConditionActivityRepository conditionActivityRepository;
	
	public List<ConditionActivity> findAll() {
		return (List<ConditionActivity>) conditionActivityRepository.findAll();
	}
	
	public ConditionActivity findById(int id) {
		return conditionActivityRepository.findById(id).get();
	}
	
	public void save(ConditionActivity conditionActivity) {
		conditionActivityRepository.save(conditionActivity);
	}
	
	public void delete(int id) {
		conditionActivityRepository.deleteById(id);
	}

}
