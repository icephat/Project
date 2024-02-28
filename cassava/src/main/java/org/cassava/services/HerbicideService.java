package org.cassava.services;

import java.util.List;

import org.cassava.model.Herbicide;
import org.cassava.repository.HerbicideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("HerbicideService")
public class HerbicideService {
	
	@Autowired
	private HerbicideRepository herbicideRepository;
	
	public List<Herbicide> findAll(){
		return (List<Herbicide>) herbicideRepository.findAll();
	}
	
	public Herbicide findById(int id){
		return (Herbicide) herbicideRepository.findById(id).get();
	}
//	public List<Herbicide> findAll(){
//		return (List<Herbicide>) herbicideRepository.findAll();
//	}
}
