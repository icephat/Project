package org.cassava.repository;




import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.cassava.model.Field;
import org.cassava.model.Organization;
import org.cassava.model.Planting;
import org.cassava.model.Province;
import org.cassava.model.Subdistrict;
import org.cassava.model.Survey;
import org.cassava.model.SurveyTarget;
import org.cassava.model.User;

@Repository
public interface FieldRepository extends CrudRepository<Field,Integer> {
	
	@Query("SELECT f FROM Field f ORDER BY f.fieldId")
	public List<Field> findByPagination(Pageable pageable);
	
	@Query("select f from Field as f inner join f.userinfields as uif inner join uif.user as us where us = :user")
	public List<Field> findByPagitation(@Param("user") User user,Pageable pageable);
	
	@Query("select f from Field as f inner join f.userinfields as uif inner join uif.user as us where us = :user and f.createDate < :date order by f.createDate DESC")
	public List<Field> findAllOrderByDate(@Param("user")User user, @Param("date")Date date, Pageable pageable);
	
	@Query("SELECT f FROM Field as f inner join f.userinfields as uif INNER JOIN uif.user as u WHERE u = :user and f.fieldId = :fieldId")
	public Field findById(@Param("fieldId") int fieldId,@Param("user") User user);
	
	@Query("SELECT f FROM Field as f inner join f.userinfields as uif INNER JOIN uif.user as u WHERE u = :user")
	public List<Field> findByUserInField(@Param("user") User user,Pageable pageable);

	@Query("SELECT f FROM Field as f inner join f.plantings as P inner join f.userinfields as uif INNER JOIN uif.user as u WHERE P = :planting and u = :user")
	public Field findByPlantingAndUser(@Param("planting") Planting planting , @Param("user") User user);
	
	@Query("SELECT f FROM Survey as s inner join s.planting as P inner join P.field as f inner join f.userinfields as uif INNER JOIN uif.user as u WHERE s = :survey and u = :user")
	public Field findBySurveyAndUser(@Param("survey") Survey survey, @Param("user") User user);
	
	@Query("FROM Field f WHERE f.subdistrict.subdistrictId = :subdistrictId ORDER BY f.fieldId")
	public List<Field> findBySubdistrictId(@Param("subdistrictId") int subdistrictId,Pageable pageable);
	
	@Query("FROM Field f inner join f.subdistrict as s inner join f.userinfields as uif inner join uif.user as us WHERE s = :subdistrict and us = :user ORDER BY f.fieldId")
	public List<Field> findBySubdistrictIdAndUser(@Param("subdistrict") Subdistrict subdistrict,@Param("user") User user,Pageable pageable);

	// SELECT * FROM field INNER JOIN userinfield on field.fieldID = userinfield.fieldID WHERE userinfield.userID = 2;
	@Query("SELECT f FROM Field as f inner join f.userinfields as uf inner join uf.field where uf.user.userId =:userId")
	public List<Field> findByUserInField(@Param("userId") int userId);
	
	@Query("SELECT f FROM Field f inner join f.subdistrict as s inner join f.userinfields as uif inner join uif.user as us WHERE f in(SELECT f FROM Field as f inner join f.userinfields as uf inner join uf.field where uf.user.userId =:userId)and us = :user")
	public List<Field> findByUserInField(@Param("userId") int userId,@Param("user") User user,Pageable pageable);
	
	@Query("SELECT f FROM Field f inner join f.subdistrict as s inner join f.userinfields as uif inner join uif.user as us WHERE f in(SELECT f FROM Field as f inner join f.userinfields as uf inner join uf.field where uf.user.userId =:userId)and us = :user and f.createDate < :date order by f.createDate DESC")
	public List<Field> findByUserInFieldAndDate(@Param("userId") int userId,@Param("user") User user,@Param("date")Date date,Pageable pageable);
	
	@Query("FROM Field as f inner join f.subdistrict as sub WHERE f in :fields AND sub.subdistrictId =:subid")
	public List<Field> findByFieldsAndSubDistrictId(@Param("fields") List<Field> fields,@Param("subid") int subid);

	@Query("SELECT f FROM Field f inner join f.subdistrict as s inner join f.userinfields as uif inner join uif.user as us WHERE f in(SELECT f FROM Field as f inner join f.userinfields as uif inner join uif.id as uifid where uifid.userId =:uid and f.subdistrict = :subdistrict) and us = :user")
	public List<Field> findBySubdistricIdAndUserId(@Param("subdistrict") Subdistrict subdistrict,@Param("uid") int uid,@Param("user") User user,Pageable pageable);
	
	@Query("SELECT f FROM Field f inner join f.subdistrict as s inner join f.userinfields as uif inner join uif.user as us WHERE f in(SELECT f FROM Field as f inner join f.userinfields as uif inner join uif.id as uifid where uifid.userId =:uid and f.subdistrict = :subdistrict) and us = :user and f.createDate < :date order by f.createDate DESC")
	public List<Field> findBySubdistrictIdAndUserIdAndDate(@Param("subdistrict") Subdistrict subdistrict,@Param("uid") int uid,@Param("user")User user, @Param("date")Date date, Pageable pageable);

	@Query("FROM Field as f inner join f.userinfields as uif inner join uif.id as uifid where uifid.userId =:uid and f.subdistrict.subdistrictId = :subid")
	public List<Field> findBySubdistricIdAndUserId(@Param("subid") int subid,@Param("uid") int uid,Pageable pageable);
	// @Query("from Field as f inner join f.userinfields as uf inner join uf.field where uf.user.userId =:userId and f.subdistrict.subdistrictId =:subdistrictId")
	// public List<Field> getByUserInFieldWithSubdistrict(@Param("userId") int userId,@Param("subdistrictId") int subdistrictId);
	
	@Query("select count(f) from Field f inner join f.userinfields uif where uif.user = :user")
	public int findNumberOfAllFieldByUser(@Param("user")User user);
	
	@Query("select r.name,sum(f.size) from Field f inner join f.subdistrict s inner join s.district d inner join d.province p inner join p.region r group by r.name")
	public List<Object> findNumberOfAllFieldInRegion();
	
	@Query("from Field f where f.name = :name")
	public Field findByName(@Param("name")String name);
	
	@Query("select f from Field f inner join f.userinfields uif inner join uif.user u inner join u.roles r where r.nameEng = :nameEng and f.organization= :organization")
	public List<Field> findByUserRoleEngAndOrganization(@Param("nameEng")String nameEng,@Param("organization")Organization organization);
	
	@Query("select f from Field f inner join f.userinfields uif inner join uif.user u where u = :user")
	public List<Field> findByUserInField(@Param("user")User user);
	
	@Query("select f from Field f inner join f.userinfields uif inner join uif.user u inner join uif.id as uifid where u =:user and uifid.role=:roleInField")
	public List<Field> findByUserInFieldAndRoleInField(@Param("user")User user, @Param("roleInField")String roleInField);
	
	@Query("select f from Field f inner join f.organization o where o = :organization")
	public List<Field> findByOrganization(@Param("organization")Organization organization);
	
	@Query("select f from Field f inner join f.organization o where o = :organization and f.code = :code")
	public Field findByOrganizationAndCode(@Param("organization")Organization organization,@Param("code")String code);
	
	@Query("select f.latitude, f.longitude, case when st.percentDamage != 0 then 1 else 0 end as detected"
				+ " from Field f inner join f.plantings p inner join p.surveys s inner join s.surveytargets st inner join st.targetofsurvey tos"
				+ " where s.createDate >= :startDate and s.createDate <= :endDate and  tos.targetOfSurveyId = :tosId and f.latitude is not null and f.longitude is not null")
	public List<Object> findLocationByStartDateAndEndDateAndTargetOfSurveyId(@Param("startDate")Date startDate, @Param("endDate")Date endDate, @Param("tosId")int tosId);

	@Query("select distinct f from SurveyTarget st inner join st.survey s inner join s.planting p inner join p.field f where st in :sts")
	public List<Field> findBySurveyTarget(@Param("sts") List<SurveyTarget> sts);
	
	@Query("select count(f) from Field f inner join f.plantings as p inner join p.surveys s inner join s.surveytargets st inner join st.targetofsurvey ts inner join ts.disease d where Year(p.createDate) in :year and Month(p.createDate) in :month and s.status = 'Complete'")
	public Integer findNumberOfFieldHasDiseaseByMonthsAndYears(@Param("month")List<Integer> month ,@Param("year")List<Integer> year);
	
	@Query("select count(f) from Field f inner join f.plantings as p inner join p.surveys s inner join s.surveytargets st inner join st.targetofsurvey ts inner join ts.naturalenemy n where Year(p.createDate) in :year and Month(p.createDate) in :month and s.status = 'Complete'")
	public Integer findNumberOfFieldHasNaturalEnemyByMonthsAndYears(@Param("month")List<Integer> month ,@Param("year")List<Integer> year);
	
	@Query("select count(f) from Field f inner join f.plantings as p inner join p.surveys s inner join s.surveytargets st inner join st.targetofsurvey ts inner join ts.pestphasesurvey ps inner join ps.pest pe where Year(p.createDate) in :year and Month(p.createDate) in :month and s.status = 'Complete'")
	public Integer findNumberOfFieldHasPestByMonthsAndYears(@Param("month")List<Integer> month ,@Param("year")List<Integer> year);
	
	@Query("select ts.name as name, SUM(st.percentDamage)/count(st.percentDamage) as percent , SUM(st.avgDamageLevel)/count(st.percentDamage) as avgDamageLevel from Field f inner join f.plantings as p inner join p.surveys s inner join s.surveytargets st inner join st.targetofsurvey ts inner join ts.disease d where Year(p.createDate) in :year and Month(p.createDate) in :month and s.status = 'Complete' GROUP BY ts.name")
	public List<Object> findAvgPercentAndDamageLeveLHasDiseaseByMonthsAndYears(@Param("month")List<Integer> month ,@Param("year")List<Integer> year);
	
	@Query("select ts.name as name, SUM(st.percentDamage)/count(st.percentDamage) as percent , SUM(st.avgDamageLevel)/count(st.percentDamage) as avgDamageLevel from Field f inner join f.plantings as p inner join p.surveys s inner join s.surveytargets st inner join st.targetofsurvey ts inner join ts.naturalenemy n where  Year(p.createDate) in :year and Month(p.createDate) in :month and s.status = 'Complete' GROUP BY ts.name")
	public List<Object> findAvgPercentAndDamageLeveLHasNaturalEnemyByMonthsAndYears(@Param("month")List<Integer> month ,@Param("year")List<Integer> year);
	
	@Query("select ts.name as name, SUM(st.percentDamage)/count(st.percentDamage) as percent , SUM(st.avgDamageLevel)/count(st.percentDamage) as avgDamageLevel from Field f inner join f.plantings as p inner join p.surveys s inner join s.surveytargets st inner join st.targetofsurvey ts inner join ts.pestphasesurvey ps inner join ps.pest pe where Year(p.createDate) in :year and Month(p.createDate) in :month and s.status = 'Complete' GROUP BY ts.name")
	public List<Object> findAvgPercentAndDamageLeveLHasPestByMonthsAndYears(@Param("month")List<Integer> month ,@Param("year")List<Integer> year);

	@Query("select r.name,sum(f.size) from Field f inner join f.subdistrict s inner join s.district d inner join d.province p inner join p.region r inner join f.organization o where o = :organization group by r.name")
	public List<Object> findNumberOfAllFieldInRegionByOrganization(@Param("organization")Organization organization);
	
	@Query("select r.name,sum(f.size) from Field f inner join f.subdistrict s inner join s.district d inner join d.province p inner join p.region r inner join f.userinfields uif inner join uif.user u where u = :user group by r.name")
	public List<Object> findNumberOfAllFieldInRegionByUser(@Param("user")User user);
	
	@Query("select count(f) from Field f inner join f.organization o inner join f.plantings as p inner join p.surveys s inner join s.surveytargets st inner join st.targetofsurvey ts inner join ts.disease d where Year(p.createDate) in :year and Month(p.createDate) in :month and o = :organization and s.status = 'Complete'")
	public Integer findNumberOfFieldHasDiseaseByMonthsAndYearsByOrganization(@Param("month")List<Integer> month ,@Param("year")List<Integer> year,@Param("organization")Organization organization);
	
	@Query("select count(f) from Field f inner join f.organization o inner join f.plantings as p inner join p.surveys s inner join s.surveytargets st inner join st.targetofsurvey ts inner join ts.naturalenemy n where Year(p.createDate) in :year and Month(p.createDate) in :month and o = :organization and s.status = 'Complete'")
	public Integer findNumberOfFieldHasNaturalEnemyByMonthsAndYearsByOrganization(@Param("month")List<Integer> month ,@Param("year")List<Integer> year,@Param("organization")Organization organization);
	
	@Query("select count(f) from Field f inner join f.organization o inner join f.plantings as p inner join p.surveys s inner join s.surveytargets st inner join st.targetofsurvey ts inner join ts.pestphasesurvey ps inner join ps.pest pe where Year(p.createDate) in :year and Month(p.createDate) in :month and o = :organization and s.status = 'Complete'")
	public Integer findNumberOfFieldHasPestByMonthsAndYearsByOrganization(@Param("month")List<Integer> month ,@Param("year")List<Integer> year,@Param("organization")Organization organization);
	
	@Query("select ts.name as name, SUM(st.percentDamage)/count(st.percentDamage) as percent , SUM(st.avgDamageLevel)/count(st.percentDamage) as avgDamageLevel from Field f inner join f.organization o inner join f.plantings as p inner join p.surveys s inner join s.surveytargets st inner join st.targetofsurvey ts inner join ts.disease d where Year(p.createDate) in :year and Month(p.createDate) in :month and o = :organization and s.status = 'Complete' GROUP BY ts.name")
	public List<Object> findAvgPercentAndDamageLeveLHasDiseaseByMonthsAndYearsByOrganization(@Param("month")List<Integer> month ,@Param("year")List<Integer> year,@Param("organization")Organization organization);
	
	@Query("select ts.name as name, SUM(st.percentDamage)/count(st.percentDamage) as percent , SUM(st.avgDamageLevel)/count(st.percentDamage) as avgDamageLevel from Field f inner join f.organization o inner join f.plantings as p inner join p.surveys s inner join s.surveytargets st inner join st.targetofsurvey ts inner join ts.naturalenemy n where  Year(p.createDate) in :year and Month(p.createDate) in :month and o = :organization and s.status = 'Complete' GROUP BY ts.name")
	public List<Object> findAvgPercentAndDamageLeveLHasNaturalEnemyByMonthsAndYearsByOrganization(@Param("month")List<Integer> month ,@Param("year")List<Integer> year,@Param("organization")Organization organization);
	
	@Query("select ts.name as name, SUM(st.percentDamage)/count(st.percentDamage) as percent , SUM(st.avgDamageLevel)/count(st.percentDamage) as avgDamageLevel from Field f inner join f.organization o inner join f.plantings as p inner join p.surveys s inner join s.surveytargets st inner join st.targetofsurvey ts inner join ts.pestphasesurvey ps inner join ps.pest pe where Year(p.createDate) in :year and Month(p.createDate) in :month and o = :organization and s.status = 'Complete' GROUP BY ts.name")
	public List<Object> findAvgPercentAndDamageLeveLHasPestByMonthsAndYearsByOrganization(@Param("month")List<Integer> month ,@Param("year")List<Integer> year,@Param("organization")Organization organization);

	@Query("select count(f) from Field f inner join f.userinfields uif inner join uif.user u inner join f.plantings as p inner join p.surveys s inner join s.surveytargets st inner join st.targetofsurvey ts inner join ts.disease d where Year(p.createDate) in :year and Month(p.createDate) in :month and u = :user and s.status = 'Complete'")
	public Integer findNumberOfFieldHasDiseaseByMonthsAndYearsByUser(@Param("month")List<Integer> month ,@Param("year")List<Integer> year,@Param("user")User user);
	
	@Query("select count(f) from Field f inner join f.userinfields uif inner join uif.user u inner join f.plantings as p inner join p.surveys s inner join s.surveytargets st inner join st.targetofsurvey ts inner join ts.naturalenemy n where Year(p.createDate) in :year and Month(p.createDate) in :month and u = :user and s.status = 'Complete'")
	public Integer findNumberOfFieldHasNaturalEnemyByMonthsAndYearsByUser(@Param("month")List<Integer> month ,@Param("year")List<Integer> year,@Param("user")User user);
	
	@Query("select count(f) from Field f inner join f.userinfields uif inner join uif.user u inner join f.plantings as p inner join p.surveys s inner join s.surveytargets st inner join st.targetofsurvey ts inner join ts.pestphasesurvey ps inner join ps.pest pe where Year(p.createDate) in :year and Month(p.createDate) in :month and u = :user and s.status = 'Complete'")
	public Integer findNumberOfFieldHasPestByMonthsAndYearsByUser(@Param("month")List<Integer> month ,@Param("year")List<Integer> year,@Param("user")User user);
	
	@Query("select ts.name as name, SUM(st.percentDamage)/count(st.percentDamage) as percent , SUM(st.avgDamageLevel)/count(st.percentDamage) as avgDamageLevel from Field f inner join f.userinfields uif inner join uif.user u inner join f.plantings as p inner join p.surveys s inner join s.surveytargets st inner join st.targetofsurvey ts inner join ts.disease d where Year(p.createDate) in :year and Month(p.createDate) in :month and u = :user and s.status = 'Complete' GROUP BY ts.name")
	public List<Object> findAvgPercentAndDamageLeveLHasDiseaseByMonthsAndYearsByUser(@Param("month")List<Integer> month ,@Param("year")List<Integer> year,@Param("user")User user);
	
	@Query("select ts.name as name, SUM(st.percentDamage)/count(st.percentDamage) as percent , SUM(st.avgDamageLevel)/count(st.percentDamage) as avgDamageLevel from Field f inner join f.userinfields uif inner join uif.user u inner join f.plantings as p inner join p.surveys s inner join s.surveytargets st inner join st.targetofsurvey ts inner join ts.naturalenemy n where  Year(p.createDate) in :year and Month(p.createDate) in :month and u = :user and s.status = 'Complete' GROUP BY ts.name")
	public List<Object> findAvgPercentAndDamageLeveLHasNaturalEnemyByMonthsAndYearsByUser(@Param("month")List<Integer> month ,@Param("year")List<Integer> year,@Param("user")User user);
	
	@Query("select ts.name as name, SUM(st.percentDamage)/count(st.percentDamage) as percent , SUM(st.avgDamageLevel)/count(st.percentDamage) as avgDamageLevel from Field f inner join f.userinfields uif inner join uif.user u inner join f.plantings as p inner join p.surveys s inner join s.surveytargets st inner join st.targetofsurvey ts inner join ts.pestphasesurvey ps inner join ps.pest pe where Year(p.createDate) in :year and Month(p.createDate) in :month and u = :user and s.status = 'Complete' GROUP BY ts.name")
	public List<Object> findAvgPercentAndDamageLeveLHasPestByMonthsAndYearsByUser(@Param("month")List<Integer> month ,@Param("year")List<Integer> year,@Param("user")User user);

	@Query("select count(f) from Field f inner join f.userinfields uif inner join uif.user u where u = :user")
	public Integer countByUserInField (@Param("user")User user);
	
	@Query("select count(f) from Field f inner join f.organization o where o = :organization and f.code = :code ")
	public Integer findcheckByOrganizationAndCode(@Param("organization")Organization organization,@Param("code")String code);

	@Query("select count(f) from Field f inner join f.organization o where o = :organization and f.code = :code and f.fieldId != :fieldId")
	public Integer findcheckByOrganizationAndCodeAndFieldId(@Param("organization")Organization organization,@Param("code")String code,@Param("fieldId")int fieldId);

	@Query("select count(st),d.name"
			+ " from Field f inner join f.plantings p inner join p.surveys s inner join s.surveytargets st inner join st.targetofsurvey tos inner join f.subdistrict sub inner join sub.district d inner join d.province p"
			+ " where s.createDate >= :startDate and s.createDate <= :endDate and  tos.targetOfSurveyId = :tosId and  p.provinceId = :pId GROUP BY d.name")
	public List<Object> countSurveyByStartDateAndEndDateAndTargetOfSurveyIdAndProvinceId(@Param("startDate")Date startDate, @Param("endDate")Date endDate, @Param("tosId")int tosId, @Param("pId")int provinceId);
	
	@Query("select count(st),d.name"
			+ " from Field f inner join f.plantings p inner join p.surveys s inner join s.surveytargets st inner join st.targetofsurvey tos inner join f.subdistrict sub inner join sub.district d inner join d.province p"
			+ " where s.createDate >= :startDate and s.createDate <= :endDate and  tos.targetOfSurveyId = :tosId and  p.provinceId = :pId and st.percentDamage > 0 GROUP BY d.name")
	public List<Object> countSurveyDetectedByStartDateAndEndDateAndTargetOfSurveyIdAndProvinceId(@Param("startDate")Date startDate, @Param("endDate")Date endDate, @Param("tosId")int tosId, @Param("pId")int provinceId);
	
	@Query("select ts.name as name,count(ts.name) as num, SUM(st.percentDamage)/count(st.percentDamage) as percent , SUM(st.avgDamageLevel)/count(st.percentDamage) as avgDamageLevel from Field f inner join f.plantings as p inner join p.surveys s inner join s.surveytargets st inner join st.targetofsurvey ts where Year(p.createDate) = :year and Month(p.createDate) = :month and s.status = 'Complete' GROUP BY ts.name")
	public List<Object> findNameAndNumAndAvgPercentAndDamageLeveLHasDiseaseByMonthsAndYears(@Param("month")Integer month ,@Param("year")Integer year);
}
