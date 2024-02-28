package org.cassava.repository;

import java.util.Date;
import java.util.List;

import org.cassava.model.Disease;
import org.cassava.model.NaturalEnemy;
import org.cassava.model.Organization;
import org.cassava.model.Pathogen;
import org.cassava.model.PestPhaseSurvey;
import org.cassava.model.Province;
import org.cassava.model.SurveyTarget;
import org.cassava.model.TargetOfSurvey;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TargetofsurveyReposirtory extends CrudRepository<TargetOfSurvey, Integer> {

	@Query("from TargetOfSurvey t where t.name = :name")
	public TargetOfSurvey findByName(@Param("name") String name);

	@Query("select t.targetOfSurveyId,t.name from TargetOfSurvey t inner join t.naturalenemy nat")
	public List<Object> findNatauralEnemy();

	@Query("select t.targetOfSurveyId,t.name from TargetOfSurvey t inner join t.pestphasesurvey p")
	public List<Object> findPest();

	@Query("select t.targetOfSurveyId,t.name from TargetOfSurvey t inner join t.disease d")
	public List<Object> findDisease();

	@Query("select d from Disease d inner join d.targetofsurvey t inner join t.surveytargets st inner join st.survey s inner join s.planting p inner join p.field f inner join f.userinfields uif where uif.user.username = :username and t.targetOfSurveyId = :tid")
	public List<Disease> findDiseaseByUserNameAndTargetOfSurveyId(@Param("username") String username,
			@Param("tid") int tid, Pageable pageable);

	@Query("select pp from PestPhaseSurvey pp inner join pp.targetofsurvey t inner join t.surveytargets st inner join st.survey s inner join s.planting p inner join p.field f inner join f.userinfields uif where uif.user.username = :username and t.targetOfSurveyId = :tid")
	public List<PestPhaseSurvey> findPestByUserNameAndTargetOfSurveyId(@Param("username") String username,
			@Param("tid") int tid, Pageable pageable);

	@Query("select ne from NaturalEnemy ne inner join ne.targetofsurvey t inner join t.surveytargets st inner join st.survey s inner join s.planting p inner join p.field f inner join f.userinfields uif where uif.user.username = :username and t.targetOfSurveyId = :tid")
	public List<NaturalEnemy> findNatauralEnemyByUserNameAndTargetOfSurveyId(@Param("username") String username,
			@Param("tid") int tid, Pageable pageable);

	@Query("select tos from TargetOfSurvey tos inner join tos.disease d")
	public List<TargetOfSurvey> findAllDisease();

	@Query("select tos from TargetOfSurvey tos inner join tos.pestphasesurvey pps")
	public List<TargetOfSurvey> findAllPestPhaseSurvey();

	@Query("select tos from TargetOfSurvey tos inner join tos.naturalenemy ne")
	public List<TargetOfSurvey> findAllNaturalEnemy();

	@Query("select tos from TargetOfSurvey tos")
	public List<TargetOfSurvey> findAll(Pageable pageable);

	@Query("select tos from TargetOfSurvey tos inner join tos.surveytargets st inner join st.survey where st.survey.surveyId = :surveyId and tos in (select tos from TargetOfSurvey tos inner join tos.disease d)")
	public List<TargetOfSurvey> findAllDiseaseBySurveyId(@Param("surveyId") int surveyId);

	@Query("select tos from TargetOfSurvey tos inner join tos.surveytargets st inner join st.survey where st.survey.surveyId = :surveyId and tos in (select tos from TargetOfSurvey tos inner join tos.pestphasesurvey pps)")
	public List<TargetOfSurvey> findAllPestPhaseBySurveyId(@Param("surveyId") int surveyId);

	@Query("select tos from TargetOfSurvey tos inner join tos.surveytargets st inner join st.survey where st.survey.surveyId = :surveyId and tos in (select tos from TargetOfSurvey tos inner join tos.naturalenemy ne)")
	public List<TargetOfSurvey> findAllNaturalEnemyBySurveyId(@Param("surveyId") int surveyId);

	@Query("select distinct t from TargetOfSurvey t inner join t.surveytargets st inner join st.survey s inner join s.planting p inner join p.field f inner join t.disease where f.organization = :organization")
	public List<TargetOfSurvey> findDiseaseInSurveyAndOrganization(@Param("organization") Organization organization);

	@Query("select distinct t from TargetOfSurvey t inner join t.surveytargets st inner join st.survey s inner join s.planting p inner join p.field f inner join t.pestphasesurvey where f.organization = :organization")
	public List<TargetOfSurvey> findPestInSurveyAndOrganization(@Param("organization") Organization organization);

	@Query("select distinct t from TargetOfSurvey t inner join t.surveytargets st inner join st.survey s inner join s.planting p inner join p.field f inner join t.naturalenemy where f.organization = :organization")
	public List<TargetOfSurvey> findNaturalEnemyInSurveyAndOrganization(
			@Param("organization") Organization organization);

	@Query("select count(s.surveyId) from TargetOfSurvey tos inner join tos.surveytargets st inner join "
			+ "st.survey s inner join s.planting p inner join p.field f inner join f.subdistrict sd inner join sd.district d inner join d.province pv "
			+ "where tos.targetOfSurveyId in :tosId and (s.date between :frmDate and :enDate) and pv.provinceId in :pvId group by tos.targetOfSurveyId")
	public Object findCountSurveyAndTargetOfSurveyIdByTargetOfSurveyIdAndDateAndProvince(@Param("tosId") int tosId,
			@Param("frmDate") Date frmDate, @Param("enDate") Date enDate, @Param("pvId") List<Integer> pvId);

	@Query(value = "SELECT  t.name , IFNULL(s.c,0) FROM(select targetofsurvey.targetOfSurveyID, targetofsurvey.name from targetofsurvey where targetofsurvey.targetOfSurveyID in :tos ) as t left join (SELECT surveytarget.targetOfSurveyID , COUNT(*) as c FROM surveytarget inner join survey ON surveytarget.surveyID = survey.surveyID INNER JOIN planting ON survey.plantingID = planting.plantingID inner join field ON field.fieldID = planting.fieldID inner join subdistrict ON subdistrict.subdistrictID =field.subdistrictID inner join district on subdistrict.districtID = district.districtID inner join province ON district.provinceID = province.provinceID where province.provinceID in :pv and (survey.date between :frmDate and :enDate) and survey.status = :status GROUP BY surveytarget.targetOfSurveyID) as s ON t.targetOfSurveyID = s.targetOfSurveyID;", nativeQuery = true)
	public List<Object> findCountSurveyAndTargetOfSurveyByTargetOfSurveyIdAndDateAndProvinceAndStatus(
			@Param("tos") List<Integer> tos, @Param("frmDate") Date frmDate, @Param("enDate") Date enDate,
			@Param("pv") List<Integer> pv, @Param("status") String status);
	
	@Query("select distinct ts from SurveyTarget st inner join st.targetofsurvey ts inner join ts.disease d where st in :sts")
	public List<TargetOfSurvey> findTypeDiseaseBySurveyTarget(@Param("sts") List<SurveyTarget> sts);
	
	@Query("select distinct ts from SurveyTarget st inner join st.targetofsurvey ts inner join ts.pestphasesurvey ps where st in :sts")
	public List<TargetOfSurvey> findTypePestPhaseBySurveyTarget(@Param("sts") List<SurveyTarget> sts);
	
	@Query("select distinct ts from SurveyTarget st inner join st.targetofsurvey ts inner join ts.naturalenemy ne where st in :sts")
	public List<TargetOfSurvey> findTypeNaturalEnemyBySurveyTarget(@Param("sts") List<SurveyTarget> sts);
	
	@Query("from TargetOfSurvey t where t.targetOfSurveyId in :targetOfSurveyId")
	public List<TargetOfSurvey> findByListTargetOfSurveyId(@Param("targetOfSurveyId") List<Integer> targetOfSurveyId);
	
	@Query(value = "SELECT  t.name , IFNULL(s.c,0) FROM(select targetofsurvey.targetOfSurveyID, targetofsurvey.name from targetofsurvey where targetofsurvey.targetOfSurveyID in :tos ) as t left join (SELECT surveytarget.targetOfSurveyID , COUNT(*) as c FROM surveytarget inner join survey ON surveytarget.surveyID = survey.surveyID INNER JOIN planting ON survey.plantingID = planting.plantingID inner join field ON field.fieldID = planting.fieldID  inner join organization ON field.organizationID = organization.organizationID inner join subdistrict ON subdistrict.subdistrictID =field.subdistrictID inner join district on subdistrict.districtID = district.districtID inner join province ON district.provinceID = province.provinceID where province.provinceID in :pv and (survey.date between :frmDate and :enDate) and survey.status = :status and organization.organizationID in :organization GROUP BY surveytarget.targetOfSurveyID) as s ON t.targetOfSurveyID = s.targetOfSurveyID;", nativeQuery = true)
    public List<Object> findCountSurveyAndTargetOfSurveyByTargetOfSurveyIdAndDateAndProvinceAndStatusByListOrganization(
            @Param("tos") List<Integer> tos, @Param("frmDate") Date frmDate, @Param("enDate") Date enDate,
            @Param("pv") List<Integer> pv, @Param("status") String status, @Param("organization") List<Integer> organization);
//	targetofSurvey => surveyTarget => survey => planting => field => userInField => user

}
