package org.cassava.repository;

import java.util.List;

import org.cassava.model.Pest;
import org.cassava.model.Disease;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PestRepository extends CrudRepository<Pest,Integer>{

	@Query("from Pest p where p.name=:name")
	public Pest findByName(@Param("name")String name);
	
	@Query("from Pest p where p.scientificName=:scientificName")
	public Pest findBySciName(@Param("scientificName")String scientificName);
	
	@Query("FROM Pest p where p.pestId not in (select p.pestId from Pest p join p.diseases dp where dp.diseaseId = :diseaseId)")
    public List<Pest> findByDiseaseNotIn(@Param("diseaseId")int diseaseId);
	
	@Query("select count(*) from Pest as p where p.pestId = :pestId and "
			+ "(p in (select p.pestId from Pest as p inner join p.diseases as d where p.pestId = :pestId)"
			+ "or p in (select p.pestId from Pest as p inner join p.pestphasesurveys pps inner join pps.targetofsurvey as t inner join t.permissiontargetofsurveys pts where p.pestId = :pestId)"
			+ "or p in (select p.pestId from Pest as p inner join p.pestphasesurveys pps inner join pps.targetofsurvey as t inner join t.surveytargets s where p.pestId = :pestId)"
			+ ")"
			)
	public Integer checkFkByPestId(@Param("pestId")int pestId);
}
