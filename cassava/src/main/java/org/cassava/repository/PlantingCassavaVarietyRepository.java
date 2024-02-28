package org.cassava.repository;

import java.util.List;

import org.cassava.model.PlantingCassavaVariety;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PlantingCassavaVarietyRepository extends CrudRepository<PlantingCassavaVariety, Integer>{
	
	@Query("FROM PlantingCassavaVariety p WHERE p.planting.plantingId=:plantingId")
	public List<PlantingCassavaVariety> findByPlantingId(@Param("plantingId")int plantingId);
	
	@Query("FROM PlantingCassavaVariety p WHERE p.variety.varietyId=:varietyId and p.planting.plantingId=:plantingId")
	public PlantingCassavaVariety findByVarietyIdAndPlantingId(@Param("varietyId")int varietyId,@Param("plantingId")int plantingId);
	
}
