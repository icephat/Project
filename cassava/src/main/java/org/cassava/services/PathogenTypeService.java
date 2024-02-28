package org.cassava.services;

import java.util.List;
import java.util.Optional;

import org.cassava.model.Pathogen;
import org.cassava.model.PathogenType;
import org.cassava.repository.PathogenRepository;
import org.cassava.repository.PathogenTypeRepository;
import org.cassava.repository.VarietyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

@Service
public class PathogenTypeService {
	@Autowired
	private PathogenTypeRepository pathogentypeRepository;
	
	// Insert & Edit
	public PathogenType save(PathogenType p) {
		return pathogentypeRepository.save(p);
	}
	
	public PathogenType findByName(String name) {
		return pathogentypeRepository.findByName(name);
	}
	
	public PathogenType findById(int id) {
		return pathogentypeRepository.findById(id).get();
	}
	
	public List<PathogenType> findAll(){
		return (List<PathogenType>) pathogentypeRepository.findAll();
	}
	
	//Delete
	public void deleteById(int id) {
		pathogentypeRepository.deleteById(id);
	}
	
	public void deleteByObject(PathogenType p) {
		pathogentypeRepository.delete(p);
	}
}
