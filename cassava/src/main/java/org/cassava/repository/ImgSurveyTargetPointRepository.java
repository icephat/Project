package org.cassava.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.cassava.model.Disease;
import org.cassava.model.ImgSurveyTargetPoint;
import org.cassava.model.Survey;
import org.cassava.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ImgSurveyTargetPointRepository extends CrudRepository<ImgSurveyTargetPoint, Integer> {

	@Query("from ImgSurveyTargetPoint i where i.approveStatus = 'Waiting'")
	public List<ImgSurveyTargetPoint> findImgSurveyTargetPointWaiting();

	@Query("from ImgSurveyTargetPoint i where i.approveStatus = 'Approved'")
	public List<ImgSurveyTargetPoint> findImgSurveyTargetPointApproved();

	@Query("from ImgSurveyTargetPoint i where i.approveStatus = 'Reject'")
	public List<ImgSurveyTargetPoint> findImgSurveyTargetPointReject();

	@Query("from ImgSurveyTargetPoint im inner join im.surveytargetpoint sp where sp.surveyTargetPointId = :id and im.approveStatus in :listStatus")
	public List<ImgSurveyTargetPoint> findImgBySurveyTargetPointIdAndListStatus(@Param("id")int id,@Param("listStatus") ArrayList<String> listStatus);
	
	// @Query("from ImgSurveyTargetPoint i where i.approveStatus = 'Approved'")
	// public List<ImgSurveyTargetPoint> findImgSurveyTargetPointApproved(Pageable
	// pageable);

	@Query("from ImgSurveyTargetPoint i where (i.uploadDate between :datestart and :dateEnd ) and i.approveStatus = 'Waiting'")
	public List<ImgSurveyTargetPoint> findImgSurveyTargetPointWaitingBetweenDate(@Param("datestart") Date datestart,
			@Param("dateEnd") Date dateEnd);

	@Query("select im from ImgSurveyTargetPoint as im inner join im.surveytargetpoint as sp inner join sp.surveytarget as st where st.targetofsurvey.targetOfSurveyId = :targetOfSurveyId and im.approveStatus = 'Approved'")
	public List<ImgSurveyTargetPoint> findImgSurveyTargetPointApprovedByTargetOfSurveyId(
			@Param("targetOfSurveyId") int targetOfSurveyId, Pageable pageable);

	@Query("select im from ImgSurveyTargetPoint as im inner join im.surveytargetpoint as sp inner join sp.surveytarget as st where st.targetofsurvey.targetOfSurveyId = :targetOfSurveyId and im.approveStatus = 'Waiting'")
	public List<ImgSurveyTargetPoint> findImgSurveyTargetPointWaitingByTargetOfSurveyId(
			@Param("targetOfSurveyId") int targetOfSurveyId, Pageable pageable);

	@Query("select im from ImgSurveyTargetPoint as im inner join im.surveytargetpoint as sp inner join sp.surveytarget as st where st.targetofsurvey.targetOfSurveyId = :targetOfSurveyId and im.approveStatus = 'Reject'")
	public List<ImgSurveyTargetPoint> findImgSurveyTargetPointRejectByTargetOfSurveyId(
			@Param("targetOfSurveyId") int targetOfSurveyId, Pageable pageable);

	@Query(value = "SELECT filePath,imgSurveyTargetPointID from imgsurveytargetpoint imgstp join surveytargetpoint stp on imgstp.surveyTargetPointID= stp.surveyTargetPointID join surveytarget st on stp.surveyTargetID=st.surveyTargetID join targetofsurvey tos on st.targetOfSurveyID=tos.targetOfSurveyID join disease di on tos.targetOfSurveyID=di.diseaseID where di.diseaseID = :d AND Year(imgstp.approveDate) = :y AND Month(imgstp.approveDate) = :m AND imgstp.approveStatus = 'Approved'", nativeQuery = true)
	public List<Object> findImgSurveyTargetPointApprovedByYearAndMonthAndDiseaseId(@Param("d") int d, @Param("y") int y,
			@Param("m") int m);

	@Query(value = "SELECT filePath,imgSurveyTargetPointID from imgsurveytargetpoint imgstp join surveytargetpoint stp on imgstp.surveyTargetPointID= stp.surveyTargetPointID join surveytarget st on stp.surveyTargetID=st.surveyTargetID join targetofsurvey tos on st.targetOfSurveyID=tos.targetOfSurveyID join disease di on tos.targetOfSurveyID=di.diseaseID where di.diseaseID = :d AND Year(imgstp.approveDate) = :y  AND imgstp.approveStatus = 'Approved'", nativeQuery = true)
	public List<Object> findImgSurveyTargetPointApprovedByYearAndDiseaseId(@Param("d") int d, @Param("y") int y);

	@Query("select count(Year(imgstp.approveDate)),Year(imgstp.approveDate) from ImgSurveyTargetPoint imgstp join imgstp.surveytargetpoint stp join stp.surveytarget st join st.targetofsurvey tos join tos.disease d where imgstp.approveStatus = 'Approved' GROUP BY Year(imgstp.approveDate) ORDER BY Year(imgstp.approveDate) DESC")
	public List<Object> findAmountOnUploadYear();

	@Query(value = "SELECT count(Month(imgstp.approveDate)),Month(imgstp.approveDate) from imgsurveytargetpoint imgstp join surveytargetpoint stp on imgstp.surveyTargetPointID= stp.surveyTargetPointID join surveytarget st on stp.surveyTargetID=st.surveyTargetID join targetofsurvey tos on st.targetOfSurveyID=tos.targetOfSurveyID join disease di on tos.targetOfSurveyID=di.diseaseID where di.diseaseID = :id AND Year(imgstp.approveDate) = :y and imgstp.approveStatus = 'Approved' GROUP BY Month(imgstp.approveDate)", nativeQuery = true)
	public List<Object> findAmountImgSurveyTargetPointEachMonthByDiseaseIdAndYear(@Param("id") int id,
			@Param("y") int y);

	@Query(value = "SELECT count(imgstp.surveyTargetPointID) from imgsurveytargetpoint imgstp join surveytargetpoint stp on imgstp.surveyTargetPointID= stp.surveyTargetPointID where  imgstp.surveyTargetPointID= :id", nativeQuery = true)
	public int findAmountImgSurveyTargetPointBySurveyTargetPointId(@Param("id") int id);

	@Query(value = "SELECT count(imgstp.surveyTargetPointID) from imgsurveytargetpoint imgstp join surveytargetpoint stp on imgstp.surveyTargetPointID= stp.surveyTargetPointID where stp.surveyTargetID= :id and stp.pointNumber = :point", nativeQuery = true)
	public int findAmountImgSurveyTargetPointBySurveyTargetIdAndPointNumber(@Param("id") int id,
			@Param("point") int point);

	@Query(value = "SELECT count(imgstp.surveyTargetPointID) from imgsurveytargetpoint imgstp join surveytargetpoint stp on imgstp.surveyTargetPointID= stp.surveyTargetPointID where imgstp.approveStatus in :listStatus and stp.surveyTargetID= :id and stp.pointNumber = :point", nativeQuery = true)
	public int findAmountImgSurveyTargetPointBySurveyTargetIdAndPointNumberAndListStatus(@Param("id") int id,
			@Param("point") int point,@Param("listStatus")List<String> status);
	
	@Query(value = "SELECT count(Month(imgstp.approveDate)) from imgsurveytargetpoint imgstp join surveytargetpoint stp on imgstp.surveyTargetPointID= stp.surveyTargetPointID join surveytarget st on stp.surveyTargetID=st.surveyTargetID join targetofsurvey tos on st.targetOfSurveyID=tos.targetOfSurveyID join disease di on tos.targetOfSurveyID=di.diseaseID where di.diseaseID = :id AND Year(imgstp.approveDate) = :y AND Month(imgstp.approveDate) = :m and imgstp.approveStatus = 'Approved' GROUP BY Month(imgstp.approveDate)", nativeQuery = true)
	public Object findAmountImgSurveyTargetPointByDiseaseIdAndYearAndMonth(@Param("id") int id, @Param("y") int y,
			@Param("m") int m);

	@Query(value = "SELECT filePath from imgsurveytargetpoint imgstp join surveytargetpoint stp on imgstp.surveyTargetPointID= stp.surveyTargetPointID join surveytarget st on stp.surveyTargetID=st.surveyTargetID join targetofsurvey tos on st.targetOfSurveyID=tos.targetOfSurveyID join disease di on tos.targetOfSurveyID=di.diseaseID where di.diseaseID = :id AND Year(imgstp.approveDate) = :y AND Month(imgstp.approveDate) = :m GROUP BY Month(imgstp.approveDate)", nativeQuery = true)
	public List<String> findFilePathByDiseaseIdAndYearAndMonth(@Param("id") int id, @Param("y") int y,
			@Param("m") int m);

	@Query(value = "SELECT count(Month(imgstp.approveDate)) from imgsurveytargetpoint imgstp join surveytargetpoint stp on imgstp.surveyTargetPointID= stp.surveyTargetPointID join surveytarget st on stp.surveyTargetID=st.surveyTargetID join targetofsurvey tos on st.targetOfSurveyID=tos.targetOfSurveyID join disease di on tos.targetOfSurveyID=di.diseaseID where di.diseaseID = :id AND Year(imgstp.approveDate) = :y and imgstp.approveStatus = 'Approved'", nativeQuery = true)
	public int findAmountImgSurveyTargetPointByDiseaseIdAndYear(@Param("id") int id, @Param("y") int y);

	@Query("SELECT img FROM Disease d INNER JOIN d.targetofsurvey t INNER JOIN t.surveytargets s  INNER JOIN s.surveytargetpoints sp INNER JOIN sp.imgsurveytargetpoints img WHERE d = :disease and img.approveStatus =:status")
	public List<ImgSurveyTargetPoint> findByDiseaseAndStatus(@Param("disease") Disease disease,
			@Param("status") String status);

	@Query("SELECT img FROM Disease d INNER JOIN d.targetofsurvey t INNER JOIN t.surveytargets s  INNER JOIN s.surveytargetpoints sp INNER JOIN sp.imgsurveytargetpoints img WHERE d = :disease and img.approveStatus =:status")
	public List<ImgSurveyTargetPoint> findByDiseaseAndStatus(@Param("disease") Disease disease,
			@Param("status") String status, Pageable pageable);

	@Query("SELECT img FROM Disease d INNER JOIN d.targetofsurvey t INNER JOIN t.surveytargets s  INNER JOIN s.surveytargetpoints sp INNER JOIN sp.imgsurveytargetpoints img WHERE d = :disease and img.approveStatus = :status and  (img.uploadDate between :datestart and :dateEnd )")
	public List<ImgSurveyTargetPoint> findByDiseaseAndStatusBetweenDate(@Param("disease") Disease disease,
			@Param("status") String status, @Param("datestart") Date datestart, @Param("dateEnd") Date dateEnd,
			Pageable pageable);

	@Query("SELECT img FROM Disease d INNER JOIN d.targetofsurvey t INNER JOIN t.surveytargets s  INNER JOIN s.surveytargetpoints sp INNER JOIN sp.imgsurveytargetpoints img WHERE d = :disease and img.approveStatus = :status and  (img.uploadDate between :datestart and :dateEnd )")
	public List<ImgSurveyTargetPoint> findByDiseaseAndStatusBetweenDate(@Param("disease") Disease disease,
			@Param("status") String status, @Param("datestart") Date datestart, @Param("dateEnd") Date dateEnd);

	@Query("select s.staffId, sum(case when imgstp.approveStatus = :astatus then 1 else 0 end),sum(case when imgstp.approveStatus in :rstatus then 1 else 0 end) from ImgSurveyTargetPoint imgstp inner join imgstp.userByApproveBy u inner join u.staff s where month(imgstp.approveDate) in :months and year(imgstp.approveDate) = :year group by s.staffId")
	public List<Object> findImgSurveyTargetPointCountStatusByListMonthAndYear(@Param("months") List<Integer> m,
			@Param("year") int y, @Param("astatus") String astatus, @Param("rstatus") List<String> rstatus);

	@Query("select tos.name, sum(case when imgstp.approveStatus = :astatus then 1 else 0 end),sum(case when imgstp.approveStatus in :rstatus then 1 else 0 end) from ImgSurveyTargetPoint imgstp inner join imgstp.surveytargetpoint stp inner join stp.surveytarget st inner join st.targetofsurvey tos inner join tos.disease d group by tos.name")
	public List<Object> findAllDiseaseNameAndCountStatus(@Param("astatus") String astatus,
			@Param("rstatus") List<String> rstatus);

	@Query(value = "SELECT k.dayincalendar, IFNULL(p.a,0), IFNULL(p.rd,0) " + "FROM "
			+ "(SELECT day(imgstp.approveDate) dayindb, sum(case when imgstp.approveStatus = :astatus then 1 else 0 end) a, sum(case when imgstp.approveStatus in :rstatus then 1 else 0 end) rd "
			+ " from imgsurveytargetpoint imgstp "
			+ " where month(imgstp.approveDate) = :month and year(imgstp.approveDate) = :year "
			+ " group by day(imgstp.approveDate)) p " + " RIGHT JOIN " + " (SELECT day(date_field) dayincalendar "
			+ "  FROM ( "
			+ "      SELECT MAKEDATE(:year,1) + INTERVAL (:month-1) MONTH + INTERVAL daynum DAY date_field "
			+ "      FROM ( " + "          SELECT t*10+u daynum "
			+ "          FROM (SELECT 0 t UNION SELECT 1 UNION SELECT 2 UNION SELECT 3) A, "
			+ "          (SELECT 0 u UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) B "
			+ "          ORDER BY daynum ) AA " + "  ) AAA " + "  WHERE MONTH(date_field) = :month "
			+ "  ORDER BY date_field)k " + "  ON p.dayindb = k.dayincalendar  "
			+ "ORDER BY k.dayincalendar ASC", nativeQuery = true)
	public List<Object> findMonthAndCountStatusByStatusAndMonthAndYear(@Param("astatus") String astatus,
			@Param("rstatus") List<String> rstatus, @Param("month") int month, @Param("year") int year);

	@Query("select tos.name, sum(case when imgstp.approveStatus = :astatus then 1 else 0 end),sum(case when imgstp.approveStatus in :rstatus then 1 else 0 end) from ImgSurveyTargetPoint imgstp inner join imgstp.surveytargetpoint stp inner join stp.surveytarget st inner join st.targetofsurvey tos inner join tos.disease d inner join imgstp.userByApproveBy apb where apb.userId = :userId group by tos.name")
	public List<Object> findAllDiseaseNameCountStatusByStatusAndUserId(@Param("astatus") String astatus,
			@Param("rstatus") List<String> rstatus, @Param("userId") int userId);

	@Query("select month(imgstp.approveDate), sum(case when imgstp.approveStatus = :astatus then 1 else 0 end),sum(case when imgstp.approveStatus in :rstatus then 1 else 0 end) from ImgSurveyTargetPoint imgstp where year(imgstp.approveDate) = :year group by month(imgstp.approveDate)")
	public List<Object> findMonthAndCountStatusByStatusAndMonthAndYear(@Param("astatus") String astatus,
			@Param("rstatus") List<String> rstatus, @Param("year") int year);

	@Query("select year(imgstp.approveDate), sum(case when imgstp.approveStatus = :astatus then 1 else 0 end),sum(case when imgstp.approveStatus in :rstatus then 1 else 0 end) from ImgSurveyTargetPoint imgstp where (year(imgstp.approveDate) between :startyear and :endyear) group by month(imgstp.approveDate),year(imgstp.approveDate)")
	public List<Object> findYearAndCountStatusByStatusAndBetweenYear(@Param("astatus") String astatus,
			@Param("rstatus") List<String> rstatus, @Param("startyear") int startyear, @Param("endyear") int endyear);

	@Query("select stp.pointNumber,stp.itemNumber,count(stp.surveyTargetPointId) from ImgSurveyTargetPoint imgstp inner join imgstp.surveytargetpoint stp group by stp.pointNumber,stp.itemNumber")
	public List<Object> findMonthAndCountStatusByStatusAndMonthAndYear();
	
	@Query("select count(stp.surveyTargetPointId) from ImgSurveyTargetPoint imgstp inner join imgstp.surveytargetpoint stp inner join stp.surveytarget st inner join st.survey s inner join s.planting p inner join p.field f inner join f.userinfields uif where stp.pointNumber = :pointNumber and stp.itemNumber = :itemNumber and uif.user = :user and s = :survey")
	public Integer findCountImgSurveyTargetPointByPointNumberAndItemNumberAndSurvey(@Param("pointNumber")int pointNumber,@Param("itemNumber")int itemNumber,@Param("survey")Survey survey,@Param("user")User user);
	
	@Query("select coalesce(min(year(imgstp.approveDate)), year(current_date())) from ImgSurveyTargetPoint imgstp")
    public Integer findMinYearInImgSurveyTargetPoint();

	@Query("select s.staffId, sum(case when imgstp.approveStatus = :astatus then 1 else 0 end),sum(case when imgstp.approveStatus in :rstatus then 1 else 0 end),sum(case when imgstp.approveStatus = :bstatus then 1 else 0 end),sum(case when imgstp.approveStatus = :cstatus then 1 else 0 end) from ImgSurveyTargetPoint imgstp inner join imgstp.userByApproveBy u inner join u.staff s where month(imgstp.approveDate) in :months and year(imgstp.approveDate) = :year group by s.staffId")
    public List<Object> findImgSurveyTargetPointCountAllStatusByListMonthAndYear(@Param("months") List<Integer> m,
            @Param("year") int y, @Param("astatus") String astatus, @Param("rstatus") List<String> rstatus, @Param("bstatus") String bstatus, @Param("cstatus") String cstatus);
	// @Query("select img from ImgSurveyTargetPoint img where
	// img.surveytargetpoint.surveyTargetPointId = :id")
	// public List<ImgSurveyTargetPoint> findImageBySurveyTargetPointId(@Param("id")
	// int id);
	
	@Query("select imgstp from ImgSurveyTargetPoint imgstp inner join imgstp.surveytargetpoint stp inner join stp.surveytarget st inner join st.targetofsurvey tos inner join st.survey s where s.surveyId = :surveyId and stp.pointNumber = :pointNumber and stp.itemNumber = :itemNumber and imgstp.approveStatus = 'Approved'")
	public List<ImgSurveyTargetPoint> findBySurveyIdAndPointNumberAndItemNumberAndApprovedStatus (@Param("surveyId")int surveyId, @Param("pointNumber")int pointNumber, @Param("itemNumber")int itemNumber);
	
	@Query("select count(imgstp.imgSurveyTargetPointId) from ImgSurveyTargetPoint imgstp inner join imgstp.surveytargetpoint stp inner join stp.surveytarget st inner join st.targetofsurvey tos inner join st.survey s where s.surveyId = :surveyId and stp.pointNumber = :pointNumber and stp.itemNumber = :itemNumber")
	public Integer countImgBySurveyIdAndPointNumberAndItemNumber (@Param("surveyId")int surveyId, @Param("pointNumber")int pointNumber, @Param("itemNumber")int itemNumber);
	
}
