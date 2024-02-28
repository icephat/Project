package org.cassava.repository;

import java.util.List;

import org.cassava.model.NaturalEnemy;
import org.cassava.model.Pest;
import org.cassava.model.PestPhase;
import org.cassava.model.PestPhaseSurvey;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PestPhaseSurveyRepository extends CrudRepository<PestPhaseSurvey,Integer>{
	
	
	@Query("from PestPhaseSurvey ps where ps.pest.pestId = :pestId")
	public List<PestPhaseSurvey> findByPestId(@Param("pestId") int pestId);
	
	@Query("select ps from PestPhaseSurvey ps inner join ps.targetofsurvey tos inner join tos.surveytargets st inner join st.survey s inner join s.planting p inner join p.field f inner join f.userinfields uif inner join uif.user u where s.surveyId =:id and u.username =:name")
	public List<PestPhaseSurvey> findBySurveyIDAndUserName(@Param("id") int id,@Param("name") String name);
	
	@Query("select d from PestPhaseSurvey d inner join d.targetofsurvey tos where tos.name like %:name%")
    public PestPhaseSurvey findByName(@Param("name")String name);
	
	
}
