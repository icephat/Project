package org.cassava.repository;

import java.util.List;

import org.cassava.model.Pathogen;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PathogenRepository extends CrudRepository<Pathogen,Integer>{
	@Query("from Pathogen p where p.scientificName = :name")
	public Pathogen findByName(@Param("name") String name);
	
	@Query("FROM Pathogen p where p.pathogenId not in (select p.pathogenId from Pathogen p join p.diseases dp where dp.diseaseId = :diseaseId)")
    public List<Pathogen> findByDiseaseNotIn(@Param("diseaseId")int diseaseId);
	
	@Query("select count(*) from Pathogen as p inner join p.diseases as d where p.pathogenId = :pathogenId")
	public Integer checkFkBypathogenId(@Param("pathogenId")int pathogenId);
}
