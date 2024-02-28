package org.cassava.repository;

import java.util.List;

import org.cassava.model.SurveyPoint;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface SurveyPointRepository extends CrudRepository<SurveyPoint, Integer> {
	
	@Query("select sp from SurveyPoint sp inner join sp.survey s where s.surveyId = :surveyId and sp.pointNo = :pointNumber")
	public SurveyPoint findBySurveyIdAndPointNumber(@Param("surveyId")int surveyId, @Param("pointNumber")int pointNumber);
	
	@Query("select sp from SurveyPoint sp inner join sp.survey s where s.surveyId = :surveyId")
	public List<SurveyPoint> findBySurveyId (@Param("surveyId")int surveyId);
}
