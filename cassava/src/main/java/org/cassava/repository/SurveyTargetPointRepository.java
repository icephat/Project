package org.cassava.repository;

import java.util.List;

import org.cassava.model.SurveyTargetPoint;
import org.cassava.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface SurveyTargetPointRepository extends CrudRepository<SurveyTargetPoint, Integer>{


	@Query("select stp from SurveyTargetPoint stp inner join stp.surveytarget st where st.surveyTargetId = :id and stp.pointNumber = :pointNumber and stp.itemNumber = :itemNumber")
	public SurveyTargetPoint findBySurveyTargetIdAndPointNumberAndItemNumber(@Param("id")int id,@Param("pointNumber") int pointNumber,@Param("itemNumber") int itember);
	
	@Query("select stp from SurveyTargetPoint stp inner join stp.surveytarget st where st.surveyTargetId = :id and stp.pointNumber = :pointNumber")
	public List<SurveyTargetPoint> findBySurveyTargetIdAndPointNumber(@Param("id")int id, @Param("pointNumber") int pointNumber);
	
	@Query("select stp from SurveyTargetPoint stp inner join stp.surveytarget st inner join st.survey s inner join s.planting p inner join p.field f inner join f.userinfields uif where stp.surveyTargetPointId = :surveyTargetPointId and uif.user = :user")
	public SurveyTargetPoint findByUserAndSurveyTargetPointId(@Param("surveyTargetPointId")int surveyTargetPointId,@Param("user")User user);

//	@Query("From SurveyTargetPoint where surveyTargetId=:id and pointNumber=:pointNumber")
//	public List<SurveyTargetPoint> findBySurveyTargetIdAndPointNumber(@Param("id")int id,@Param("pointNumber")int pointNumber);
	
	@Query("select avg(stp.value) from SurveyTargetPoint stp where stp.surveytarget.surveyTargetId = :surveyTargetId")
	public float calculationAverageDamage(@Param("surveyTargetId")int surveyTargetId);
	
	@Query("select ((sum((case when stp.value > 0 then 1 else 0 end))*100)/count(stp)) from SurveyTargetPoint stp where stp.surveytarget.surveyTargetId = :surveyTargetId")
	public float calculationPercentDamage(@Param("surveyTargetId")int surveyTargetId);
	
	
//	@Query("select stp from SurveyTargetPoint stp where stp.surveytarget.surveyTargetId = :surveyTargetId and stp.pointNumber = :pointNumber and stp.itemNumber = :itemNumber")
//	public SurveyTargetPoint getSurveyTargetpointByPosition(@Param("surveyTargetId") int surveyTargetId,@Param("pointNumber") int pointNumber,@Param("itemNumber") int itemNumber);
	
}
