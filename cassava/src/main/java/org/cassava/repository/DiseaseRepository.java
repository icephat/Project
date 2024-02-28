package org.cassava.repository;

import java.util.List;
import org.cassava.model.Disease;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface DiseaseRepository extends CrudRepository<Disease, Integer> {

	@Query("from Disease d where d.symptom like '%:symptom%'")
	public Disease findBySymptom(@Param("symptom")String symptom);
	
	@Query("from Disease d where d.symptom like '%:symptom%' or d.controlDisease like '%:controlDisease%' or d.controlPest like '%:controlPest%'") 
	public List<Disease> findByKey(@Param("symptom")String name,@Param("controlDisease")String controlDisease,@Param("controlPest")String controlPest);
	
	@Query("select d from Disease d inner join d.targetofsurvey tos where tos.name like %:name%")
    public List<Disease> findByTargetofsurveyName(@Param("name")String name);
	
	@Query("select d from Disease d inner join d.targetofsurvey tos inner join tos.surveytargets st inner join st.survey s inner join s.planting p inner join p.field f inner join f.userinfields uif inner join uif.user u where s.surveyId =:id and u.username =:name")
    public List<Disease> findBySurveyIDAndUserName(@Param("id")int id,@Param("name")String userName);
	
	@Query("select d from Disease d inner join d.targetofsurvey tos where tos.name like %:name%")
	public Disease findByName(@Param("name")String name);
	
	@Query("select count(*) from Disease as d where d.diseaseId = :diseaseId and "
			+ "(d in (select d.diseaseId from Disease as d inner join d.targetofsurvey as t inner join t.permissiontargetofsurveys pts where d.diseaseId = :diseaseId)"
			+ "or d in (select d.diseaseId from Disease as d inner join d.targetofsurvey as t inner join t.surveytargets s where d.diseaseId = :diseaseId)"
			+ ")"
			)
	public Integer checkFkBydiseaseId(@Param("diseaseId")int diseaseId);
}
