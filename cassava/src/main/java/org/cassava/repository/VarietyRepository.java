package org.cassava.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.cassava.model.Variety;
import java.util.List;
@Repository
public interface  VarietyRepository extends CrudRepository<Variety,Integer>{
	
	@Query("from Variety v where v.name = :name")
	public Variety findByName(@Param("name") String name);
	
	@Query("from Variety v where v.mainCharacter = :mainCharacter")
	public List<Variety> findByCharacter(@Param("mainCharacter") String mainCharacter);
	
	@Query("from Variety v where v.apicalLeavesColor like '%:apicalLeavesColor%' or v.youngLeavesColor like '%:youngLeavesColor%'  or v.petioleColor like '%:petioleColor%'  or v.stemColor like '%:stemColor%'  or v.mainCharacter like '%:mainCharacter%' ")
	public List<Variety> findByKey(@Param("apicalLeavesColor") String apicalLeavesColor,
			@Param("youngLeavesColor") String youngLeavesColor, @Param("petioleColor") String petioleColor,@Param("stemColor") String stemColor,
			@Param("mainCharacter") String mainCharacter);
	
//	@Query("from Variety v where v.source = :source")
//	public List<Variety> findBySource(@Param("source") String source);
	
//	@Query("from Variety v where v.plantID = :plantID")
//	public List<Variety> findByPlantID(@Param("plantID") String plantID);
	
	@Query("select v from PlantingCassavaVariety pcv inner join pcv.variety v inner join pcv.planting p where p.plantingId = :plantingId")
	public List<Variety> findByPlantingId(@Param("plantingId")int plantingId);	
	
	@Query("select count(*) from Variety as v inner join v.plantingcassavavarieties as pcv where v.varietyId = :varietyId")
	public Integer checkFkByVarietyId(@Param("varietyId")int varietyId);
	
}
