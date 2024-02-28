package org.cassava.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.cassava.model.Field;
import org.cassava.model.Province;
import org.cassava.model.SurveyTarget;
import org.cassava.model.TargetOfSurvey;
import org.cassava.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyTargetRepository extends CrudRepository<SurveyTarget, Integer> {
	
	
	@Query("from SurveyTarget st inner join st.targetofsurvey ts inner join ts.disease d inner join st.survey s where s.surveyId=:id")
	public List<SurveyTarget> findTypeDiseaseBySurveyId(@Param("id")int id);
	
	@Query("from SurveyTarget st inner join st.targetofsurvey ts inner join ts.pestphasesurvey p inner join st.survey s where s.surveyId=:id")
	public List<SurveyTarget> findTypePestPhaseBySurveyId(@Param("id")int id);
	
	@Query("from SurveyTarget st inner join st.targetofsurvey ts inner join ts.naturalenemy nat inner join st.survey s where s.surveyId=:id")
	public List<SurveyTarget> findTypeNaturalEnemyBySurveyId(@Param("id")int id);

	@Query("select t.targetOfSurveyId from TargetOfSurvey t inner join t.disease d1 left join SurveyTarget st inner join st.targetofsurvey ts  inner join ts.disease d inner join st.survey s  where s.surveyId=:id")
	public List<Object> findCheckListDiseaseBySurveyId(@Param("id")int id);
	
	@Query(value = "SELECT y.targetofsurveyid,y.name,IFNULL(t.c,0),IFNULL(t.status,'null'),t.surveytargetid from (select targetofsurvey.targetofsurveyid, name from targetofsurvey where targetofsurvey.targetofsurveyid IN(select disease.diseaseid from disease))as y left join ( SELECT surveytarget.targetofsurveyid,surveytarget.status,surveytarget.surveytargetid,count(*) as c FROM surveytarget where surveytarget.surveyid =:id group by targetofSurveyid)as t on t.targetofSurveyId=y.targetofsurveyid", nativeQuery = true)
	public List<Object> findAllDiseaseBySurveyId(@Param("id")int id);
	
	@Query(value = "SELECT y.targetofsurveyid,y.name,IFNULL(t.c,0),IFNULL(t.status,'null'),t.surveytargetId from (select targetofsurvey.targetofsurveyid, name from targetofsurvey where targetofsurvey.targetofsurveyid IN(select pestphasesurvey.pestphasesurveyid from pestphasesurvey))as y left join ( SELECT surveytarget.targetofsurveyid,surveytarget.status,surveytarget.surveytargetid,count(*) as c FROM surveytarget where surveytarget.surveyid =:id group by targetofsurveyid)as t on t.targetofsurveyid=y.targetofsurveyid", nativeQuery = true)
	public List<Object> findAllPestPhaseBySurveyId(@Param("id")int id);
	
	@Query(value = "SELECT y.targetofsurveyid,y.name,IFNULL(t.c,0),IFNULL(t.status,'null'),t.surveytargetid from (select targetofsurvey.targetofsurveyid, name from targetofsurvey where targetofsurvey.targetofsurveyid IN(select naturalenemy.naturalenemyId from naturalenemy))as y left join ( SELECT surveytarget.targetofsurveyid,surveytarget.status,surveytarget.surveytargetid,count(*) as c FROM surveytarget where surveytarget.surveyid =:id group by targetofsurveyid)as t on t.targetofsurveyid=y.targetofsurveyid", nativeQuery = true)
	public List<Object> findAllNaturalEnemyBySurveyId(@Param("id")int id);
	
	@Query("from SurveyTarget st inner join st.survey s where s.surveyId=:id")
	public List<SurveyTarget> findBySurveyId(@Param("id")int id);
	
	@Query("select st from SurveyTarget as st inner join st.survey as s where st in (select st from SurveyTarget st inner join st.targetofsurvey tos inner join tos.disease) and s.surveyId = :id ")
	public List<SurveyTarget> findByDiseaseandSurveyId(@Param("id")int id);
	
	
	@Query("select st from SurveyTarget as st inner join st.survey as s where st in (select st from SurveyTarget st inner join st.targetofsurvey tos inner join tos.pestphasesurvey) and s.surveyId = :id ")
	public List<SurveyTarget> findByPestPhaseandSurveyId(@Param("id")int id);
	
	@Query("select st from SurveyTarget as st inner join st.survey as s where st in (select st from SurveyTarget st inner join st.targetofsurvey tos inner join tos.naturalenemy) and s.surveyId = :id ")
	public List<SurveyTarget> findByNaturalEnemyandSurveyId(@Param("id")int id);
	
	
	@Query("from SurveyTarget st inner join st.targetofsurvey ts inner join st.survey s where ts.targetOfSurveyId=:id and s.surveyId=:surveyId")
	public SurveyTarget findByTargetOfSurveyIdAndSurveyId(@Param("id")int id,@Param("surveyId")int surveyId);
	
	@Query("select st from SurveyTarget st inner join st.survey s inner join s.planting p inner join p.field f inner join f.userinfields uif where st.surveyTargetId = :surveyTargetId and uif.user = :user")
	public SurveyTarget findById(@Param("surveyTargetId")int surveyTargetId,@Param("user")User user);
	
//	"select sum(p.size) from Planting p inner join p.surveys as s inner join s.surveytargets as st inner join st.surveytargetpoints as stp where stp.value != 0 and st.surveyTargetId = :surveyTargetId"
	@Query("select sum(p.size) from Planting p where p in (select distinct p from Planting p inner join p.surveys as s inner join s.surveytargets as st inner join st.surveytargetpoints as stp where stp.surveytarget.surveyTargetId = :surveyTargetId and stp.value != 0 and p in(select pp from Planting pp inner join pp.field as f inner join f.userinfields as uif inner join uif.user as u where u.username = :username))")
	public float findPlantingArea(@Param("surveyTargetId") int surveyTargetId,@Param("username") String username);
	
	
	@Query("select st from TargetOfSurvey tos inner join tos.surveytargets st where tos.targetOfSurveyId = :id")
	public SurveyTarget findByTargetOfSurvey(@Param("id") int id);

	@Query("select DISTINCT(st),ptos from SurveyTarget st inner join st.survey s inner join st.targetofsurvey tos inner join tos.permissiontargetofsurveys ptos inner join ptos.permission p where (s.date between :stDate and :edDate) and tos.targetOfSurveyId = :tosId")
	public List<Object> findSurveyTargetAndCountByDateAndTargetOfSurveyId(@Param("stDate")Date stDate, @Param("edDate")Date edDate, @Param("tosId")int tosId);
	
	@Query("select st from SurveyTarget st inner join st.targetofsurvey tos inner join st.survey s inner join s.planting p inner join p.field f inner join f.subdistrict sd "
			+ "inner join sd.district d inner join d.province p where p in :pv and tos in :toss and (s.date between :frmDate and :endDate) and s.status = :status")
	public List<SurveyTarget> findByTargetOfSurveysAndBetweenDateAndProvincesAndStatus(@Param("toss") List<TargetOfSurvey> toss, @Param("frmDate") Date frmDate, @Param("endDate") Date endDate,@Param("pv") List<Province> pv, @Param("status") String status);
	
	@Query("select st from SurveyTarget st inner join st.targetofsurvey tos inner join st.survey s inner join s.planting p inner join p.field f inner join f.subdistrict sd "
			+ "inner join sd.district d inner join d.province p where p in :pv and tos in :toss and (s.date between :frmDate and :endDate) and s.status = :status and f.name like %:fieldname%")
	public List<SurveyTarget> findByTargetOfSurveysAndBetweenDateAndProvincesAndStatusAndFieldName(@Param("toss") List<TargetOfSurvey> toss, @Param("frmDate") Date frmDate, @Param("endDate") Date endDate,@Param("pv") List<Province> pv, @Param("status") String status,@Param("fieldname")String fieldname);		
	
	@Query("select avg(stp.value) from SurveyTargetPoint stp where stp.surveytarget.surveyTargetId = :surveyTargetId")
	public float calAvgDamage(@Param("surveyTargetId")int surveyTargetId);
	
	@Query("select ((sum(case when stp.value > 0 then 1 else 0 end)*100)/count(stp)) from SurveyTargetPoint stp where stp.surveytarget.surveyTargetId = :surveyTargetId")
	public float calPercentDamage(@Param("surveyTargetId")int surveyTargetId);		
	
	@Query("select avg(st.avgDamageLevel) from SurveyTarget st where st in (:surveyTargets)")	
	public float calAvgDamage(@Param("surveyTargets")List<SurveyTarget> surveyTargets);
	
	@Query("select avg(st.percentDamage) from SurveyTarget st where st in (:surveyTargets)")	
	public float calPercentDamage(@Param("surveyTargets")List<SurveyTarget> surveyTargets);
	
	@Query("select sum(st.survey.planting.size) from SurveyTarget st where st in (:surveyTargets)")	
	public float calPlantingArea(@Param("surveyTargets")List<SurveyTarget> surveyTargets);
	
	@Query("select count(*) from SurveyTargetPoint stp where stp.surveytarget in (:surveyTargets)")
	public int calNumSurvey(@Param("surveyTargets") List<SurveyTarget> surveyTargets);

	@Query("select count(distinct f) from SurveyTarget st inner join st.survey s inner join s.planting p inner join p.field f where st in (:surveyTargets) ")
	public int findNumberOfFieldBySurveyTargets(@Param("surveyTargets") List<SurveyTarget> surveyTargets);

	@Query("select  SUM(p.size) from SurveyTarget st inner join st.survey s inner join s.planting p inner join p.field f where st in (:surveyTargets)")
	public Float findSizePlantingBySurveyTargets(@Param("surveyTargets") List<SurveyTarget> surveyTargets);

	@Query("select count(distinct f) from SurveyTarget st inner join st.survey s inner join s.planting p inner join p.field f where st in (:surveyTargets) and st.percentDamage > 0 ")
	public int findNumberOfFieldDiseaseBySurveyTargets(@Param("surveyTargets") List<SurveyTarget> surveyTargets);

	@Query("select COALESCE(SUM(p.size) , 0) from SurveyTarget st inner join st.survey s inner join s.planting p inner join p.field f where st in (:surveyTargets) and st.percentDamage > 0")
	public Float findSizePlantingDiseaseBySurveyTargets(@Param("surveyTargets") List<SurveyTarget> surveyTargets);

	@Query("select sum(st.avgDamageLevel)  from SurveyTarget st inner join st.survey s inner join s.planting p inner join p.field f where st in (:surveyTargets) ")
	public Float findSumAvgDamageLevelDiseaseBySurveyTargets(@Param("surveyTargets") List<SurveyTarget> surveyTargets);

	@Query(value = "SELECT percent/num as AVG FROM(SELECT t.name as name, SUM(st.percentDamage) as percent FROM surveytarget as st INNER JOIN targetofsurvey as t ON t.targetOfSurveyID = st.targetOfSurveyID "
			+ "	INNER JOIN survey as s ON s.surveyID = st.surveyID INNER JOIN planting as p ON p.plantingID = s.plantingID "
			+ "    	INNER JOIN field as f ON f.fieldID = p.fieldID where st.surveyTargetID  in (:surveyTargets)"
			+ "    		GROUP BY t.name) as a NATURAL JOIN(SELECT COUNT(DISTINCT f.fieldID) as num FROM surveytarget as st "
			+ "	INNER JOIN survey as s ON s.surveyID = st.surveyID INNER JOIN planting as p ON p.plantingID = s.plantingID "
			+ "    	INNER JOIN field as f ON f.fieldID = p.fieldID where st.surveyTargetID  in (:surveyTargets))AS b;", nativeQuery = true)
	public Float findAvgPercentDamageInAllFieldBySurveyTargets(
			@Param("surveyTargets") List<SurveyTarget> surveyTargets);

	@Query(value = "SELECT    percent/num as AVG FROM(SELECT t.name as name, COALESCE(SUM(st.percentDamage), 0) as percent FROM surveytarget as st INNER JOIN targetofsurvey as t ON t.targetOfSurveyID = st.targetOfSurveyID "
			+ "	INNER JOIN survey as s ON s.surveyID = st.surveyID INNER JOIN planting as p ON p.plantingID = s.plantingID "
			+ "    	INNER JOIN field as f ON f.fieldID = p.fieldID where st.surveyTargetID  in (:surveyTargets) and st.percentDamage > 0 "
			+ "    		GROUP BY t.name) as a NATURAL JOIN(SELECT COALESCE(COUNT(DISTINCT f.fieldID), 0) as num FROM surveytarget as st "
			+ "	INNER JOIN survey as s ON s.surveyID = st.surveyID INNER JOIN planting as p ON p.plantingID = s.plantingID "
			+ "    	INNER JOIN field as f ON f.fieldID = p.fieldID where st.surveyTargetID  in (:surveyTargets) and st.percentDamage > 0  )AS b;", nativeQuery = true)
	public Float findAvgPercentDamageInAllDiseaseFieldBySurveyTargets(
			@Param("surveyTargets") List<Integer> surveyTargets);

	@Query("select st from RequestDetail as rede inner join rede.surveyTarget st inner join st.targetofsurvey tos where rede.request.requestId= :id and tos =:tos ")
	public List<SurveyTarget> findByRequestIdAndTargetOfSurvey(@Param("id") int id, @Param("tos") TargetOfSurvey tos);
	
	@Query("select count(d) from SurveyTarget st inner join st.targetofsurvey tos inner join tos.disease d inner join st.survey s where s.surveyId = :surveyId")
	public  Integer countDiseaseBySurveyId (@Param("surveyId")int surveyId);
	
	@Query("select count(ne) from SurveyTarget st inner join st.targetofsurvey tos inner join tos.naturalenemy ne inner join st.survey s where s.surveyId = :surveyId")
	public  Integer countNaturalEnemyBySurveyId (@Param("surveyId")int surveyId);
	
	@Query("select count(p) from SurveyTarget st inner join st.targetofsurvey tos inner join tos.pestphasesurvey p inner join st.survey s where s.surveyId = :surveyId")
	public  Integer countPestPhaseSurveyBySurveyId (@Param("surveyId")int surveyId);
	
	@Query("select count(stp.value), sum(case when stp.value > 0 then 1 else 0 end) from SurveyTargetPoint stp inner join stp.surveytarget st inner join st.targetofsurvey tos inner join st.survey s where s.surveyId = :surveyId and stp.pointNumber = :pointNumber and stp.itemNumber = :itemNumber")
	public Object countTotalAndFoundBySurveyIdAndPointNumberAndItemNumber (@Param("surveyId")int surveyId, @Param("pointNumber")int pointNumber, @Param("itemNumber")int itemNumber);

	@Query("select st from SurveyTarget st inner join st.targetofsurvey tos inner join st.survey s inner join s.planting p inner join p.field f inner join f.subdistrict sd "
			+ "inner join sd.district d inner join d.province p where p in :pv and tos in :toss and (s.date between :frmDate and :endDate) and s.status = :status and f.name like %:fieldname% and f.organization.organizationId in :organizationid")
	public List<SurveyTarget> findByTargetOfSurveysAndBetweenDateAndProvincesAndStatusAndFieldNameAndOrganization(@Param("toss") List<TargetOfSurvey> toss, @Param("frmDate") Date frmDate, @Param("endDate") Date endDate,@Param("pv") List<Province> pv, @Param("status") String status,@Param("fieldname")String fieldname,@Param("organizationid")List<Integer> organizationid);		
	
	@Query("select tos from SurveyTarget st inner join st.targetofsurvey tos inner join tos.pestphasesurvey p inner join st.survey s where s.surveyId = :surveyId")
	public List<TargetOfSurvey> findPestPhaseSurveyBySurveyId (@Param("surveyId")int surveyId);
	
	@Query("select tos from SurveyTarget st inner join st.targetofsurvey tos inner join tos.disease d inner join st.survey s where s.surveyId = :surveyId")
	public List<TargetOfSurvey> findDiseaseBySurveyId (@Param("surveyId")int surveyId);
	
	@Query("select tos from SurveyTarget st inner join st.targetofsurvey tos inner join tos.naturalenemy ne inner join st.survey s where s.surveyId = :surveyId")
	public List<TargetOfSurvey> findNaturalEnemyBySurveyId (@Param("surveyId")int surveyId);
	
	@Modifying
	@Transactional
	@Query(value = "Delete st from surveytarget st where st.targetOfSurveyID = :targetOfSurveyID", nativeQuery = true)
	public void deleteSurveyTargetByTargetOfSurveyID(@Param("targetOfSurveyID") int targetOfSurveyID);
}
