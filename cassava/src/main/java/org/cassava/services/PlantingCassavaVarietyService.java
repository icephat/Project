package org.cassava.services;

import java.util.List;

import org.cassava.model.Pest;
import org.cassava.model.PestPhase;
import org.cassava.model.PlantingCassavaVariety;
import org.cassava.repository.PestPhaseRepository;
import org.cassava.repository.PlantingCassavaVarietyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlantingCassavaVarietyService {
	
	@Autowired
	private PlantingCassavaVarietyRepository plantingCassavaVarietyRepository;
	
	public List<PlantingCassavaVariety> findByPlantingId(int id){
		return plantingCassavaVarietyRepository.findByPlantingId(id);
	}
	
	public PlantingCassavaVariety findById(int id) {
		return plantingCassavaVarietyRepository.findById(id).orElse(null);
	}
	
	public PlantingCassavaVariety findByVarietyIdAndPlantingId(int varietyId,int plantingId) {
		return plantingCassavaVarietyRepository.findByVarietyIdAndPlantingId(varietyId,plantingId);
	}
	
	public List<PlantingCassavaVariety> findAll() {
		return (List<PlantingCassavaVariety>) plantingCassavaVarietyRepository.findAll();
	}
	
	public PlantingCassavaVariety save (PlantingCassavaVariety p) {
		return plantingCassavaVarietyRepository.save(p);
	}
	
	public void deleteByPlantingCassavaVariety(PlantingCassavaVariety p) {
		plantingCassavaVarietyRepository.delete(p);
	}
}
