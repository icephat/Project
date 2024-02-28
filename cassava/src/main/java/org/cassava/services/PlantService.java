package org.cassava.services;

import java.util.List;
import org.cassava.model.Plant;
import org.cassava.repository.PlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("PlantService")
public class PlantService {

	@Autowired
	private PlantRepository plantRepository;
	
	public List<Plant> findAll(){
		List<Plant> plants = (List<Plant>) plantRepository.findAll();
		return plants ; 
	}
	
	public Plant findByName(String name){
		Plant plant = plantRepository.findByName(name);
		return plant ; 
	}
	
	public Plant findById(int id){
		Plant plant = plantRepository.findById(id).get();
		return plant; 
	}
	
	public Plant save(Plant p) {
		return plantRepository.save(p);
	}
	
	public void deleteById(int id) {
		plantRepository.deleteById(id);
	}
}
