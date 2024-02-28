package se.springboot.sharitytest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.springboot.sharitytest.model.TypeActivity;
import se.springboot.sharitytest.model.TypeOfActivity;
import se.springboot.sharitytest.repository.TypeActivityRepository;
import se.springboot.sharitytest.repository.TypeOfActivityRepository;

@Service
public class TypeOfActivityService {
	
	@Autowired
	TypeOfActivityRepository typeOfActivityRepository;
	
	public List<TypeOfActivity> findAll() {
		return (List<TypeOfActivity>)typeOfActivityRepository.findAll();
	}
	
	public TypeOfActivity findById(int id) {
		return (TypeOfActivity) typeOfActivityRepository.findById(id).get();
	}
	
	public void save(TypeOfActivity typeOfActivity) {
		typeOfActivityRepository.save(typeOfActivity);
	}
	
	public void delete(int id) {
		typeOfActivityRepository.deleteById(id);
	}

}
