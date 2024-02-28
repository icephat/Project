package org.cassava.services;

import java.util.List;

import org.cassava.model.Pathogen;
import org.cassava.repository.PathogenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PathogenService {
	@Autowired
	private PathogenRepository pathogenRepository;
	
	// Insert & Edit
	public Pathogen save(Pathogen p) {
		return pathogenRepository.save(p);
	}
	
	// Read
	public Pathogen findById(int id){
		return pathogenRepository.findById(id).orElse(null);
	}
	
	public Pathogen findByName(String name) {
		return pathogenRepository.findByName(name);
	}
	
	public List<Pathogen> findAll(){
		return (List<Pathogen>) pathogenRepository.findAll();
	}
	
//	public List<Pathogen> findByKey(String pathogenTypeID){
//		return (List<Pathogen>) pathogenRepository.findBypathogenID(pathogenTypeID);
//	}
	
	//Delete
	public void deleteById(int id) {
		pathogenRepository.deleteById(id);
	}
	
	public List<Pathogen> findByDiseaseNotIn(int diseaseId){
		return pathogenRepository.findByDiseaseNotIn(diseaseId);
	}
	
	public Integer checkFkBypathogenId(int pathogenId) {
		return pathogenRepository.checkFkBypathogenId(pathogenId);
	}
}
