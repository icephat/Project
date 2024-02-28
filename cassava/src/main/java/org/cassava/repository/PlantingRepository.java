package org.cassava.repository;

import java.util.Date;
//import java.util.Collection;
import java.util.List;

import org.cassava.model.Organization;
import org.cassava.model.Planting;
import org.cassava.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PlantingRepository extends CrudRepository<Planting, Integer> {
	
	
	@Query("from Planting p where p.field.fieldId = :fieldId")
	public List<Planting> findByFieldId(@Param("fieldId") int fieldId,Pageable pageable);
	
	@Query("select p from Planting p inner join p.field f inner join f.organization o where o = :organization and p.code = :code")
	public Planting findByOrganizationAndCode(@Param("organization")Organization organization,@Param("code")String code);
	
	@Query("select p from Planting as p inner join p.field as f inner join f.userinfields as uif INNER JOIN uif.user as u WHERE u = :user and f.fieldId = :fieldId")
	public List<Planting> findByFieldIdAndUser(@Param("fieldId") int fieldId,@Param("user") User user,Pageable pageable);
	
	@Query("select p from Planting as p inner join p.field as f inner join f.userinfields as uif INNER JOIN uif.user as u WHERE u = :user and f.fieldId = :fieldId")
	public List<Planting> findByFieldIdAndUser(@Param("fieldId") int fieldId,@Param("user") User user);
	
	@Query("from Planting p order by p.plantingId")
	public List<Planting> findByPagitation(Pageable pageable);

	@Query("select p from Planting p inner join p.field as f inner join f.userinfields as uif inner join uif.user as us where us.username = :username and p.plantingId = :plantingId")
	public Planting findByUsernameAndPlantingId(@Param("username") String username,@Param("plantingId") int plantingId);
	
	@Query("select p from Planting p inner join p.field as f inner join f.userinfields as uif inner join uif.user as us where us.username = :username")
	public List<Planting> findByUsername(@Param("username") String username,Pageable pageable);
	
	@Query("select p from Planting p inner join p.field as f inner join f.userinfields as uif inner join uif.user as us where p.code = :code")
	public Planting findByCode(@Param("code") String code);
	
	@Query("select p from Planting p inner join p.field as f inner join f.userinfields as uif inner join uif.user as us where us.username = :username and p.lastUpdateDate < :date order by p.lastUpdateDate DESC")
	public List<Planting> findByUsernameAndDate(@Param("username") String username,Pageable pageable,@Param("date")Date date);
	
	@Query("select p from Planting p inner join p.field as f inner join f.userinfields as uif inner join uif.user as us where us.username = :username and f.subdistrict.subdistrictId = :subid")
	public List<Planting> findByUsernameAndSubdistrict(@Param("username") String username,@Param("subid") int subid,Pageable pageable);
	
	@Query("select p from Planting p inner join p.field as f inner join f.userinfields as uif inner join uif.user as us where us.username = :username and f.subdistrict.subdistrictId = :subid and p.lastUpdateDate < :date order by p.lastUpdateDate DESC")
	public List<Planting> findByUsernameAndSubdistrictAndDate(@Param("username") String username,@Param("subid") int subid, @Param("date")Date date, Pageable pageable);
	
	@Query("select p from Planting p inner join p.field as f inner join f.userinfields as uif inner join uif.user as us inner join f.subdistrict as subid inner join subid.district as disid where us.username = :username and disid.districtId = :disid")
	public List<Planting> findByUsernameAndDistrict(@Param("username") String username,@Param("disid") int disid,Pageable pageable);
	
	@Query("select p from Planting p inner join p.field as f inner join f.userinfields as uif inner join uif.user as us inner join f.subdistrict as sd inner join sd.district as d where us.username = :username and d.districtId = :disid and p.lastUpdateDate < :date order by p.lastUpdateDate DESC")
	public List<Planting> findByUsernameAndDistrictAndDate(@Param("username") String username,@Param("disid") int disid,@Param("date")Date date,Pageable pageable);
	
	@Query("select p from Planting p inner join p.field as f inner join f.userinfields as uif inner join uif.id where p in (select p from Planting p inner join p.field as f inner join f.userinfields as uif inner join uif.id as uifid where uifid.role = 'farmerOwner' and uifid.userId = :ownerid) and uif.user.username = :username")
	public List<Planting> findByUsernameAndOwnerId(@Param("username") String username,@Param("ownerid") int ownerid,Pageable pageable);
	
	@Query("select p from Planting p inner join p.field as f inner join f.userinfields as uif inner join uif.id where p in (select p from Planting p inner join p.field as f inner join f.userinfields as uif inner join uif.id as uifid where uifid.role = 'farmerOwner' and uifid.userId = :ownerid) and uif.user.username = :username and p.lastUpdateDate < :date order by p.lastUpdateDate DESC")
	public List<Planting> findByUsernameAndOwnerIdAndDate(@Param("username") String username,@Param("ownerid") int ownerid,Pageable pageable,@Param("date")Date date);
	
	@Query("select count(p) from Planting p inner join p.field f inner join f.userinfields uif where uif.user = :user")
	public int findNumberOfAllPlantingByUser(@Param("user")User user);
	
	@Query("select p from Planting p inner join p.surveys as s inner join p.field f inner join f.userinfields uif where s.surveyId = :surveyId and uif.user.username = :username")
	public Planting findBySurveyIdAndUsername(@Param("surveyId")int surveyId,@Param("username")String username);
	
	@Query("select distinct p from Planting p inner join p.surveys as s inner join s.surveytargets as st inner join st.surveytargetpoints as stp where stp.surveytarget.surveyTargetId = :surveyTargetId and stp.value != 0 and p in(select pp from Planting pp inner join pp.field as f inner join f.userinfields as uif inner join uif.user as u where u.username = :username)")
	public List<Planting> findPlantingDiseaseBySurveyTargetIdAndUsername(@Param("surveyTargetId") int surveyTargetId,@Param("username") String username,Pageable pageable);
	
	@Query("select distinct p from Planting p inner join p.surveys as s inner join s.surveytargets as st inner join st.surveytargetpoints as stp where stp.surveytarget.surveyTargetId = :surveyTargetId and stp.value != 0 and p in(select pp from Planting pp inner join pp.field as f inner join f.userinfields as uif inner join uif.user as u where u.username = :username) and p.lastUpdateDate < :date order by p.lastUpdateDate DESC")
	public List<Planting> findPlantingDiseaseBySurveyTargetIdAndUsernameAndDate(@Param("surveyTargetId") int surveyTargetId,@Param("username") String username,Pageable pageable,@Param("date")Date date);
		
	@Query("select p from Planting p inner join p.field f inner join f.userinfields uif inner join uif.user u where u =:user")
	public List<Planting> findByUserInField(@Param("user")User user);
	
	@Query("select p from Planting p inner join p.field f inner join f.userinfields uif inner join uif.user u where u =:user")
	public List<Planting> findByUserInField(@Param("user")User user,Pageable pageable);
	
	@Query("select p from Planting p inner join p.field f inner join f.userinfields uif inner join uif.user u where u =:user and ((p.code like %:key% or p.name like %:key% or f.name like %:key% or f.code like %:key%) and ( p.primaryPlantPlantingDate between :stDate and :edDate))")
	public List<Planting> findByUserInFieldAndKeyAndDate(@Param("user")User user,@Param("key")String key,@Param("stDate")Date stdate,@Param("edDate")Date edDate,Pageable pageable);
	
	@Query("select p from Planting p inner join p.field f inner join f.userinfields uif inner join uif.user u where u =:user and ((p.code like %:key% or p.name like %:key% or f.name like %:key% or f.code like %:key%) and ( p.primaryPlantPlantingDate between :stDate and :edDate))")
	public List<Planting> findByUserInFieldAndKeyAndDate(@Param("user")User user,@Param("key")String key,@Param("stDate")Date stdate,@Param("edDate")Date edDate);
	
	@Query("select p from Planting as p inner join p.field as f inner join f.userinfields as uif INNER JOIN uif.user as u WHERE u = :user and f.fieldId = :fieldId and p.lastUpdateDate < :date order by p.lastUpdateDate DESC")
	public List<Planting> findByFieldIdAndUserAndDate(@Param("fieldId") int fieldId,@Param("user") User user,@Param("date")Date date,Pageable pageable);
	
	@Query("select case when max(Year(p.lastUpdateDate)) != null then max(Year(p.lastUpdateDate)) else 0 end as year from Planting p ")
	public int findMaxYearlastUpdateDate();
	
	@Query("select Year(p.lastUpdateDate), sum(p.size) from Planting p where Year(p.lastUpdateDate) > :year group by Year(p.lastUpdateDate)order by Year(p.lastUpdateDate) ASC")
	public List<Object> findNumberOfAllPlantingInFiveYear(@Param("year")int year);
	
	@Query("select Year(p.lastUpdateDate), sum(p.size) from Planting p inner join p.surveys s inner join s.surveytargets st inner join st.targetofsurvey ts inner join ts.disease d where Year(p.lastUpdateDate) > :year and s.status = :status group by Year(p.lastUpdateDate)order by Year(p.lastUpdateDate) ASC")
	public List<Object> findNumberOfAllDiseaseInFiveYear(@Param("year")int year,@Param("status")String status);
	
	@Query("select Year(p.lastUpdateDate), sum(p.size) from Planting p inner join p.surveys s inner join s.surveytargets st inner join st.targetofsurvey ts inner join ts.naturalenemy n where Year(p.lastUpdateDate) > :year and s.status = :status group by Year(p.lastUpdateDate)order by Year(p.lastUpdateDate) ASC")
	public List<Object> findNumberOfAllNaturalEnemyInFiveYear(@Param("year")int year,@Param("status")String status);
	
	@Query("select Year(p.lastUpdateDate), sum(p.size) from Planting p inner join p.surveys s inner join s.surveytargets st inner join st.targetofsurvey ts inner join ts.pestphasesurvey ps inner join ps.pest pe where Year(p.lastUpdateDate) > :year and s.status = :status group by Year(p.lastUpdateDate)order by Year(p.lastUpdateDate) ASC")
	public List<Object> findNumberOfAllPestInFiveYear(@Param("year")int year,@Param("status")String status);
	
	@Query("select Year(p.lastUpdateDate), count(p.lastUpdateDate) from Planting p inner join p.surveys s inner join s.surveytargets st inner join st.targetofsurvey ts inner join ts.disease d where Year(p.lastUpdateDate) > :year and s.status = :status group by Year(p.lastUpdateDate)order by Year(p.lastUpdateDate) ASC")
	public List<Object> findCountDiseaseInFiveYear(@Param("year")int year,@Param("status")String status);
	
	@Query("select Year(p.lastUpdateDate), count(p.lastUpdateDate) from Planting p inner join p.surveys s inner join s.surveytargets st inner join st.targetofsurvey ts inner join ts.naturalenemy n where Year(p.lastUpdateDate) > :year and s.status = :status group by Year(p.lastUpdateDate)order by Year(p.lastUpdateDate) ASC")
	public List<Object> findCountNaturalEnemyInFiveYear(@Param("year")int year,@Param("status")String status);
	
	@Query("select Year(p.lastUpdateDate), count(p.lastUpdateDate) from Planting p inner join p.surveys s inner join s.surveytargets st inner join st.targetofsurvey ts inner join ts.pestphasesurvey ps inner join ps.pest pe where Year(p.lastUpdateDate) > :year and s.status = :status group by Year(p.lastUpdateDate)order by Year(p.lastUpdateDate) ASC")
	public List<Object> findCountPestInFiveYear(@Param("year")int year,@Param("status")String status);
	
	@Query("select sum(p.size) from Planting p where Year(p.lastUpdateDate) in :year and Month(p.lastUpdateDate) in :month")
	public Double findNumberOfAllPlantingByMonthAndYear(@Param("month")List<Integer> month ,@Param("year")List<Integer> year);
	
	@Query("select count(p) from Planting p where Year(p.lastUpdateDate) in :year and Month(p.lastUpdateDate) in :month")
	public Integer findCountPlantingByMonthAndYear(@Param("month")List<Integer> month ,@Param("year")List<Integer> year);
	
	@Query("select r.name ,sum(p.size) from Planting p inner join p.field f inner join f.subdistrict s inner join s.district d inner join d.province pr inner join pr.region r where Year(p.lastUpdateDate) in :year and Month(p.lastUpdateDate) in :month group by r.name")
	public List<Object> findNumberOfAllPlantingByMonthAndYearInRegion(@Param("month")List<Integer> month ,@Param("year")List<Integer> year);
	
	@Query(value = "select v.name, SUM(p.size/a.num) as size from planting p inner join plantingcassavavariety pv ON p.plantingID = pv.plantingID INNER JOIN (select p.plantingID , COUNT(p.plantingID) AS num from planting p inner join plantingcassavavariety pv ON p.plantingID = pv.plantingID GROUP BY  p.plantingID)as a ON a.plantingID = pv.plantingID inner join variety v ON v.varietyID = pv.varietyID where Year(p.lastUpdateDate) in :year and Month(p.lastUpdateDate) in :month group by v.name;", nativeQuery = true)
	public List<Object> findNumberOfAllPlantingByMonthAndYearInVariety(@Param("month")List<Integer> month ,@Param("year")List<Integer> year);
	
	@Query(value = "select k.y as year, k.m as month, IFNULL(p.s,0) as num FROM "
			+ "(select Year(p.primaryPlantPlantingDate) y, Month(p.primaryPlantPlantingDate) m, sum(p.size) s "
			+ "from planting p "
			+ "where "
			+ "(Year(p.primaryPlantPlantingDate) between :startyear and :endyear) "
			+ "group by Year(p.primaryPlantPlantingDate),Month(p.primaryPlantPlantingDate) )p "
			+ "RIGHT JOIN "
			+ "(SELECT b.y , a.m FROM(SELECT 1 as m UNION SELECT 2 UNION SELECT 3 "
			+ "            UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 "
			+ "            UNION SELECT 8 UNION SELECT 9  UNION SELECT 10  UNION SELECT 11  UNION SELECT 12 ) a "
			+ "				INNER join (select Year(p.primaryPlantPlantingDate) y "
			+ "					from planting p "
			+ "						where  (Year(p.primaryPlantPlantingDate) between :startyear and :endyear) "
			+ "group by Year(p.primaryPlantPlantingDate)) b)k  ON p.m = k.m AND p.y = k.y;", nativeQuery = true)
	public List<Object> findNumberOfPlantingAllMonthInThreeYear(@Param("startyear")int startyear,@Param("endyear")int endyear);
	
	@Query("select Year(p.lastUpdateDate) from Planting p group by Year(p.lastUpdateDate)")
	public List<Integer> findAllYearInLastUpdateDate();
	
	@Query("select Month(p.lastUpdateDate) from Planting p group by Month(p.lastUpdateDate)")
	public List<Integer> findAllMonthInLastUpdateDate();
	
	@Query("select Year(p.lastUpdateDate), sum(p.size) from Planting p inner join p.field f inner join f.organization o where Year(p.lastUpdateDate) > :year and o.organizationId = :organizationId group by Year(p.lastUpdateDate)order by Year(p.lastUpdateDate) ASC")
	public List<Object> findNumberOfAllPlantingInFiveYearByOrganization(@Param("year")int year, @Param("organizationId")int organizationId);
	
	@Query("select Year(p.lastUpdateDate), sum(p.size) from Planting p inner join p.field f inner join f.userinfields uif inner join uif.user u where Year(p.lastUpdateDate) > :year and u.userId = :userId group by Year(p.lastUpdateDate)order by Year(p.lastUpdateDate) ASC")
	public List<Object> findNumberOfAllPlantingInFiveYearByUser(@Param("year")int year, @Param("userId")int userId);
	
	@Query("select Year(p.lastUpdateDate), sum(p.size) from Planting p inner join p.surveys s inner join s.surveytargets st inner join st.targetofsurvey ts inner join ts.disease d inner join p.field f inner join f.organization o where Year(p.lastUpdateDate) > :year and s.status = :status and o.organizationId = :organizationId group by Year(p.lastUpdateDate)order by Year(p.lastUpdateDate) ASC")
	public List<Object> findNumberOfAllDiseaseInFiveYearByOrganization(@Param("year")int year, @Param("status")String status, @Param("organizationId")int organizationId);
	
	@Query("select Year(p.lastUpdateDate), sum(p.size) from Planting p inner join p.surveys s inner join s.surveytargets st inner join st.targetofsurvey ts inner join ts.disease d inner join p.field f inner join f.userinfields uif inner join uif.user u where Year(p.lastUpdateDate) > :year and s.status = :status and u.userId = :userId group by Year(p.lastUpdateDate)order by Year(p.lastUpdateDate) ASC")
	public List<Object> findNumberOfAllDiseaseInFiveYearByUser(@Param("year")int year, @Param("status")String status, @Param("userId")int userId);
	
	@Query("select Year(p.lastUpdateDate), sum(p.size) from Planting p inner join p.surveys s inner join s.surveytargets st inner join st.targetofsurvey ts inner join ts.naturalenemy n inner join p.field f inner join f.organization o where Year(p.lastUpdateDate) > :year and s.status = :status and o.organizationId = :organizationId group by Year(p.lastUpdateDate)order by Year(p.lastUpdateDate) ASC")
	public List<Object> findNumberOfAllNaturalEnemyInFiveYearByOrganization(@Param("year")int year, @Param("status")String status, @Param("organizationId")int organizationId);
	
	@Query("select Year(p.lastUpdateDate), sum(p.size) from Planting p inner join p.surveys s inner join s.surveytargets st inner join st.targetofsurvey ts inner join ts.naturalenemy n inner join p.field f inner join f.userinfields uif inner join uif.user u where Year(p.lastUpdateDate) > :year and s.status = :status and u.userId = :userId group by Year(p.lastUpdateDate)order by Year(p.lastUpdateDate) ASC")
	public List<Object> findNumberOfAllNaturalEnemyInFiveYearByUser(@Param("year")int year, @Param("status")String status, @Param("userId")int userId);

	@Query("select sum(p.size) from Planting p inner join p.field f inner join f.organization o where Year(p.lastUpdateDate) in :year and Month(p.lastUpdateDate) in :month and o.organizationId = :organizationId")
	public Double findNumberOfAllPlantingByMonthAndYearandOrganization(@Param("month")List<Integer> month ,@Param("year")List<Integer> year,@Param("organizationId") int organizationId);
	
	@Query("select count(p) from Planting p inner join p.field f inner join f.organization o where Year(p.lastUpdateDate) in :year and Month(p.lastUpdateDate) in :month and o.organizationId = :organizationId ")
	public Integer findCountPlantingByMonthAndYearandOrganization(@Param("month")List<Integer> month ,@Param("year")List<Integer> year,@Param("organizationId") int organizationId);

	@Query("select r.name ,sum(p.size) from Planting p inner join p.field f inner join f.organization o inner join p.field f inner join f.subdistrict s inner join s.district d inner join d.province pr inner join pr.region r where Year(p.lastUpdateDate) in :year and Month(p.lastUpdateDate) in :month and o.organizationId = :organizationId group by r.name")
	public List<Object> findNumberOfAllPlantingByMonthAndYearandOrganizationInRegion(@Param("month")List<Integer> month ,@Param("year")List<Integer> year,@Param("organizationId") int organizationId);
	
	@Query(value = "select v.name, SUM(p.size/a.num) as size from planting p inner join field f ON f.fieldID = p.fieldID inner join organization o ON o.organizationID = f.organizationID inner join plantingcassavavariety pv ON p.plantingID = pv.plantingID INNER JOIN (select p.plantingID , COUNT(p.plantingID) AS num from planting p inner join plantingcassavavariety pv ON p.plantingID = pv.plantingID GROUP BY  p.plantingID)as a ON a.plantingID = pv.plantingID inner join variety v ON v.varietyID = pv.varietyID where Year(p.lastUpdateDate) in :year and Month(p.lastUpdateDate) in :month and o.organizationId = :organizationId group by v.name;", nativeQuery = true)
	public List<Object> findNumberOfAllPlantingByMonthAndYearandOrganizationInVariety(@Param("month")List<Integer> month ,@Param("year")List<Integer> year,@Param("organizationId") int organizationId);

	@Query(value = "select k.y as year, k.m as month, IFNULL(p.s,0) as num FROM "
			+ "(select Year(p.primaryPlantPlantingDate) y, Month(p.primaryPlantPlantingDate) m, sum(p.size) s "
			+ "from planting p INNER JOIN field f ON f.fieldID = p.fieldID INNER JOIN organization o ON o.organizationID = f.organizationID "
			+ "where "
			+ "(Year(p.primaryPlantPlantingDate) between :startyear and :endyear) and o.organizationID = :organizationId "
			+ " group by Year(p.primaryPlantPlantingDate),Month(p.primaryPlantPlantingDate) )p "
			+ "RIGHT JOIN "
			+ "(SELECT b.y , a.m FROM(SELECT 1 as m UNION SELECT 2 UNION SELECT 3 "
			+ "            UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 "
			+ "            UNION SELECT 8 UNION SELECT 9  UNION SELECT 10  UNION SELECT 11  UNION SELECT 12 ) a "
			+ "				INNER join (select Year(p.primaryPlantPlantingDate) y "
			+ "					from planting p INNER JOIN field f ON f.fieldID = p.fieldID INNER JOIN organization o ON o.organizationID = f.organizationID "
			+ "						where  (Year(p.primaryPlantPlantingDate) between :startyear and :endyear) and o.organizationID = :organizationId "
			+ " group by Year(p.primaryPlantPlantingDate)) b)k  ON p.m = k.m AND p.y = k.y;", nativeQuery = true)
	public List<Object> findNumberOfPlantingAllMonthInThreeYearByOrganization(@Param("startyear")int startyear,@Param("endyear")int endyear,@Param("organizationId") int organizationId);
	
	@Query("select Year(p.lastUpdateDate) from Planting p inner join p.field f inner join f.organization o where o.organizationId = :organizationId group by Year(p.lastUpdateDate)")
	public List<Integer> findAllYearInLastUpdateDateByOrganization(@Param("organizationId") int organizationId);
	
	@Query("select Month(p.lastUpdateDate) from Planting p inner join p.field f inner join f.organization o where o.organizationId = :organizationId group by Month(p.lastUpdateDate)")
	public List<Integer> findAllMonthInLastUpdateDateByOrganization(@Param("organizationId") int organizationId);

	@Query("select sum(p.size) from Planting p inner join p.field f inner join f.userinfields uif inner join uif.user u where Year(p.lastUpdateDate) in :year and Month(p.lastUpdateDate) in :month and u.userId = :userId")
	public Double findNumberOfAllPlantingByMonthAndYearAndUserInfield(@Param("month")List<Integer> month ,@Param("year")List<Integer> year,@Param("userId")int userId);
	
	@Query("select count(p) from Planting p inner join p.field f inner join f.userinfields uif inner join uif.user u where Year(p.lastUpdateDate) in :year and Month(p.lastUpdateDate) in :month and u.userId = :userId")
	public Integer findCountPlantingByMonthAndYearAndUserInfield(@Param("month")List<Integer> month ,@Param("year")List<Integer> year,@Param("userId")int userId);

	@Query("select r.name ,sum(p.size) from Planting p inner join p.field f inner join f.userinfields uif inner join uif.user u inner join f.subdistrict s inner join s.district d inner join d.province pr inner join pr.region r where Year(p.lastUpdateDate) in :year and Month(p.lastUpdateDate) in :month and u.userId = :userId group by r.name")
	public List<Object> findNumberOfAllPlantingByMonthAndYearAndUserInfieldInRegion(@Param("month")List<Integer> month ,@Param("year")List<Integer> year,@Param("userId")int userId);
	
	@Query(value = "select v.name, SUM(p.size/a.num) as size from planting p inner join field f ON f.fieldID = p.fieldID inner join userinfield uif ON uif.fieldID = f.fieldID inner join user u ON u.userID = uif.userID inner join plantingcassavavariety pv ON p.plantingID = pv.plantingID INNER JOIN (select p.plantingID , COUNT(p.plantingID) AS num from planting p inner join plantingcassavavariety pv ON p.plantingID = pv.plantingID GROUP BY p.plantingID)as a ON a.plantingID = pv.plantingID inner join variety v ON v.varietyID = pv.varietyID where Year(p.lastUpdateDate) in :year and Month(p.lastUpdateDate) in :month and u.userID = :userId group by v.name;", nativeQuery = true)
	public List<Object> findNumberOfAllPlantingByMonthAndYearAndUserInfieldInVariety(@Param("month")List<Integer> month ,@Param("year")List<Integer> year,@Param("userId")int userId);

	@Query(value = "select k.y as year, k.m as month, IFNULL(p.s,0) as num FROM "
			+ "(select Year(p.primaryPlantPlantingDate) y, Month(p.primaryPlantPlantingDate) m, sum(p.size) s "
			+ "from planting p INNER JOIN field f ON f.fieldID = p.fieldID INNER JOIN userinfield uif ON uif.fieldID = f.fieldID INNER JOIN user u ON u.userID = uif.userID "
			+ "where "
			+ "(Year(p.primaryPlantPlantingDate) between :startyear and :endyear) and u.userID = :userId "
			+ " group by Year(p.primaryPlantPlantingDate),Month(p.primaryPlantPlantingDate) )p "
			+ "RIGHT JOIN "
			+ "(SELECT b.y , a.m FROM(SELECT 1 as m UNION SELECT 2 UNION SELECT 3 "
			+ "            UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 "
			+ "            UNION SELECT 8 UNION SELECT 9  UNION SELECT 10  UNION SELECT 11  UNION SELECT 12 ) a "
			+ "				INNER join (select Year(p.primaryPlantPlantingDate) y "
			+ "					from planting p INNER JOIN field f ON f.fieldID = p.fieldID INNER JOIN userinfield uif ON uif.fieldID = f.fieldID INNER JOIN user u ON u.userID = uif.userID "
			+ "						where  (Year(p.primaryPlantPlantingDate) between :startyear and :endyear) and u.userID = :userId "
			+ " group by Year(p.primaryPlantPlantingDate)) b)k  ON p.m = k.m AND p.y = k.y;", nativeQuery = true)
	public List<Object> findNumberOfPlantingAllMonthInThreeYearByUserInfield(@Param("startyear")int startyear,@Param("endyear")int endyear,@Param("userId")int userId);
	
	@Query("select Year(p.lastUpdateDate) from Planting p inner join p.field f inner join f.userinfields uif inner join uif.user u where u.userId = :userId group by Year(p.lastUpdateDate)")
	public List<Integer> findAllYearInLastUpdateDateByUserInfield(@Param("userId")int userId);
	
	@Query("select Month(p.lastUpdateDate) from Planting p inner join p.field f inner join f.userinfields uif inner join uif.user u where u.userId = :userId group by Month(p.lastUpdateDate)")
	public List<Integer> findAllMonthInLastUpdateDateByUserInfield(@Param("userId")int userId);

	@Query("select Year(p.lastUpdateDate), sum(p.size) from Planting p inner join p.surveys s inner join s.surveytargets st inner join st.targetofsurvey ts inner join ts.pestphasesurvey ps inner join ps.pest pe inner join p.field f inner join f.organization o where Year(p.lastUpdateDate) > :year and s.status = :status and o.organizationId = :organizationId group by Year(p.lastUpdateDate)order by Year(p.lastUpdateDate) ASC")
	public List<Object> findNumberOfAllPestInFiveYearByOrganization(@Param("year")int year,@Param("status")String status, @Param("organizationId")int organizationId);
	
	@Query("select Year(p.lastUpdateDate), sum(p.size) from Planting p inner join p.surveys s inner join s.surveytargets st inner join st.targetofsurvey ts inner join ts.pestphasesurvey ps inner join ps.pest pe inner join p.field f inner join f.userinfields uif inner join uif.user u where Year(p.lastUpdateDate) > :year and s.status = :status and u.userId = :userId group by Year(p.lastUpdateDate)order by Year(p.lastUpdateDate) ASC")
	public List<Object> findNumberOfAllPestInFiveYearByUser(@Param("year")int year, @Param("status")String status, @Param("userId")int userId);

	@Query("select Year(p.lastUpdateDate), count(p.lastUpdateDate) from Planting p inner join p.surveys s inner join s.surveytargets st inner join st.targetofsurvey ts inner join ts.disease d inner join p.field f inner join f.organization o where Year(p.lastUpdateDate) > :year and s.status = :status and o.organizationId = :organizationId group by Year(p.lastUpdateDate)order by Year(p.lastUpdateDate) ASC")
	public List<Object> findCountDiseaseInFiveYearByOrganization(@Param("year")int year, @Param("status")String status, @Param("organizationId")int organizationId);
	
	@Query("select Year(p.lastUpdateDate), count(p.lastUpdateDate) from Planting p inner join p.surveys s inner join s.surveytargets st inner join st.targetofsurvey ts inner join ts.naturalenemy n inner join p.field f inner join f.organization o where Year(p.lastUpdateDate) > :year and s.status = :status and o.organizationId = :organizationId group by Year(p.lastUpdateDate)order by Year(p.lastUpdateDate) ASC")
	public List<Object> findCountNaturalEnemyInFiveYearByOrganization(@Param("year")int year, @Param("status")String status, @Param("organizationId")int organizationId);
	
	@Query("select Year(p.lastUpdateDate), count(p.lastUpdateDate) from Planting p inner join p.surveys s inner join s.surveytargets st inner join st.targetofsurvey ts inner join ts.pestphasesurvey ps inner join ps.pest pe inner join p.field f inner join f.organization o where Year(p.lastUpdateDate) > :year and s.status = :status and o.organizationId = :organizationId group by Year(p.lastUpdateDate)order by Year(p.lastUpdateDate) ASC")
	public List<Object> findCountPestInFiveYearByOrganization(@Param("year")int year, @Param("status")String status, @Param("organizationId")int organizationId);
	
	@Query("select Year(p.lastUpdateDate), count(p.lastUpdateDate) from Planting p inner join p.surveys s inner join s.surveytargets st inner join st.targetofsurvey ts inner join ts.disease d inner join p.field f inner join f.userinfields uif inner join uif.user u where Year(p.lastUpdateDate) > :year and s.status = :status and u.userId = :userId group by Year(p.lastUpdateDate)order by Year(p.lastUpdateDate) ASC")
	public List<Object> findCountDiseaseInFiveYearByUser(@Param("year")int year,@Param("status")String status, @Param("userId")int userId);
	
	@Query("select Year(p.lastUpdateDate), count(p.lastUpdateDate) from Planting p inner join p.surveys s inner join s.surveytargets st inner join st.targetofsurvey ts inner join ts.naturalenemy n inner join p.field f inner join f.userinfields uif inner join uif.user u where Year(p.lastUpdateDate) > :year and s.status = :status and u.userId = :userId group by Year(p.lastUpdateDate)order by Year(p.lastUpdateDate) ASC")
	public List<Object> findCountNaturalEnemyInFiveYearByUser(@Param("year")int year,@Param("status")String status, @Param("userId")int userId);
	
	@Query("select Year(p.lastUpdateDate), count(p.lastUpdateDate) from Planting p inner join p.surveys s inner join s.surveytargets st inner join st.targetofsurvey ts inner join ts.pestphasesurvey ps inner join ps.pest pe inner join p.field f inner join f.userinfields uif inner join uif.user u where Year(p.lastUpdateDate) > :year and s.status = :status and u.userId = :userId group by Year(p.lastUpdateDate)order by Year(p.lastUpdateDate) ASC")
	public List<Object> findCountPestInFiveYearByUser(@Param("year")int year,@Param("status")String status, @Param("userId")int userId);

	@Query("select p from Planting p inner join p.field f inner join f.userinfields uif inner join uif.user u where date(p.createDate) = :createDate and u = :user")
	public List<Planting> findByCreateDateAndUser(@Param("createDate")Date createDate,@Param("user")User user);
	
	@Query(value = "select IFNULL(p.c,0) co FROM "
			+ "(	select day(p.createDate) d,  count(p.plantingID) c "
			+ "from planting p INNER join field f on p.fieldID = f.fieldID "
			+ "INNER JOIN userinfield uif on f.fieldID = uif.fieldID "
			+ "where (Year(p.createDate) = year(:date)) and (Month(p.createDate) = month(:date)) and uif.userID = :userId "
			+ "group by day(p.createDate) )p "
			+ "RIGHT JOIN "
			+ "(SELECT day(date_field) dayincalendar "
			+ "FROM ( SELECT MAKEDATE(year(:date),1) + INTERVAL (month(:date)-1) MONTH + INTERVAL daynum DAY date_field "
			+ "FROM (	SELECT t*10+u daynum FROM (SELECT 0 t UNION SELECT 1 UNION SELECT 2 UNION SELECT 3) A,"
			+ "(SELECT 0 u UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) B "
			+ "ORDER BY daynum ) AA   ) AAA   WHERE MONTH(date_field) = month(:date) "
			+ "ORDER BY date_field)k   ON p.d = k.dayincalendar "
			+ "ORDER BY k.dayincalendar ASC ", nativeQuery = true)
	public List<Integer> countByCreateDateInCalendar (@Param("date")Date date, @Param("userId")int userId);
	
	@Query("select p from Planting p inner join p.field f inner join f.userinfields uif inner join uif.user u where day(p.createDate) = day(:date) and month(p.createDate) = month(:date) and year(p.createDate) = year(:date) and u.userId = :userId")
	public List<Planting> findByMonthAndYearCreateDate (@Param("date")Date date, @Param("userId")int userId);
	
	@Query("select count(p) from Planting p inner join p.field f inner join f.userinfields uif inner join uif.user u where u = :user")
	public Integer countByUserInField (@Param("user")User user);
	
	@Query("from Planting p where date(p.createDate) = :createDate")
	public List<Planting> findByCreateDate(@Param("createDate")Date createDate);
	
	@Query("select count(p) from Planting p inner join p.field f inner join f.userinfields uif inner join uif.user u where u = :user and f.fieldId = :fieldId")
	public Integer countByUserInFieldAndFieldId (@Param("user")User user, @Param("fieldId")int fieldId);

	@Query("select count(p) from Planting p inner join p.field f inner join f.organization o where o = :organization and p.code = :code and p.plantingId != :plantingId")
	public Integer findByOrganizationAndCodeAndPlantingId(@Param("organization")Organization organization,@Param("code")String code,@Param("plantingId")int plantingId);

}
