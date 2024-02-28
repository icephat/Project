package se.springboot.sharitytest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.springboot.sharitytest.model.TypeActivity;
import se.springboot.sharitytest.repository.TypeActivityRepository;

@Service
public class TypeActivityService {

	@Autowired
	TypeActivityRepository typeActivityRepository;
	
	public List<TypeActivity> findAll() {
		return (List<TypeActivity>) typeActivityRepository.findAll();
	}
	
	public TypeActivity findById(int id) {
		return typeActivityRepository.findById(id).get();
	}
	
	public void save(TypeActivity typeActivity) {
		typeActivityRepository.save(typeActivity);
	}
	
	public void delete(int id) {
		typeActivityRepository.deleteById(id);
	}
}
