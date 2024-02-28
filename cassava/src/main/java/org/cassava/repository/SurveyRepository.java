package org.cassava.repository;

import java.util.Date;
import java.util.List;

import org.cassava.model.Field;
import org.cassava.model.Organization;
import org.cassava.model.Province;
import org.cassava.model.Subdistrict;
import org.cassava.model.Survey;
import org.cassava.model.TargetOfSurvey;
import org.cassava.model.User;
import org.cassava.model.District;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyRepository extends CrudRepository<Survey, Integer> {

	@Query("from Survey s where s.date between beforedate and afterddate")
	public Survey findByDate(@Param("date") Date beforedate, @Param("date") Date afterddate);

	@Query("from Survey s where s.rainType=:rainType")
	public Survey findByRainType(@Param("rainType") String rainType);

	@Query("from Survey s where s.sunlightType=:sunlightType")
	public Survey findBySunlightType(@Param("sunlightType") String sunlightType);

	@Query("from Survey s where s.dewType=:dewType")
	public Survey findByDewType(@Param("dewType") String dewType);

	@Query("from Survey s where s.soilType like '%:soilType%'")
	public Survey findBySoilType(@Param("soilType") String soilType);

	@Query("from Survey s where s.percentDamageFromHerbicide=:percentDamageFromHerbicide")
	public Survey findByPercentDamageFromHerbicide(
			@Param("percentDamageFromHerbicide") String percentDamageFromHerbicide);

	@Query("FROM Survey s ORDER BY s.surveyId")
	public List<Survey> findByPagination(Pageable pageable);

	@Query("SELECT s FROM Survey s INNER JOIN s.planting p INNER JOIN p.field f INNER JOIN f.userinfields uif WHERE uif.user = :user and f.subdistrict = :subdistrict and s.lastUpdateDate < :date order by s.lastUpdateDate DESC")
	public List<Survey> findBySubdistrictAndDate(@Param("user") User user,
			@Param("subdistrict") Subdistrict subdistrict, @Param("date") Date date, Pageable pageable);

	@Query("SELECT s FROM Survey s INNER JOIN s.planting p INNER JOIN p.field f INNER JOIN f.userinfields uif inner join f.subdistrict sd inner join sd.district d WHERE uif.user = :user and d = :district and s.lastUpdateDate < :date order by s.lastUpdateDate DESC")
	public List<Survey> findByDistrictAndDate(@Param("user") User user, @Param("district") District district,
			@Param("date") Date date, Pageable pageable);

	@Query("SELECT s from Survey s inner join s.planting p inner join p.field f inner join f.userinfields uif where uif.user = :user and f = :field and s.lastUpdateDate < :date order by s.lastUpdateDate DESC")
	public List<Survey> findByFieldAndDate(@Param("user") User user, @Param("field") Field field,
			@Param("date") Date date, Pageable pageable);

	@Query("select s from Survey s inner join s.planting p inner join p.field as f inner join f.userinfields as uif inner join uif.id as uifid where s in (select s from Survey s inner join s.planting p inner join p.field as f inner join f.userinfields as uif inner join uif.id as uifid where uifid.role = 'farmerOwner' and uif.user = :userOwner) and uif.user = :user and s.lastUpdateDate < :date order by s.lastUpdateDate DESC")
	public List<Survey> findByOwnerAndDate(@Param("user") User user, @Param("userOwner") User userOwner,
			@Param("date") Date date, Pageable pageable);

	@Query("SELECT s from Survey s inner join s.planting p inner join p.field f inner join f.userinfields uif where s.surveyId = :surveyId and uif.user = :user")
	public Survey findByUserAndSurveyId(@Param("user") User user, @Param("surveyId") int surveyId);

	@Query("select s from Survey s inner join s.planting p inner join p.field f inner join f.userinfields uif where uif.user = :user")
	public List<Survey> findAll(@Param("user") User user, Pageable pageable);

	@Query("select s from Survey s inner join s.planting p inner join p.field f inner join f.userinfields uif inner join uif.id uifid where s.date like %:date% AND f.organization = :organization AND uifid.role = 'farmerOwner' AND uif.user = :user")
	public List<Survey> findByDateAndOrganizationAndUser(@Param("date") String date,
			@Param("organization") Organization organization, @Param("user") User user);

	@Query("select count(s) from Survey s inner join s.planting p inner join p.field f inner join f.userinfields uif where uif.user = :user")
	public int countByUserInfield(@Param("user") User user);

	@Query("select s from Survey s inner join s.surveytargets st inner join st.targetofsurvey tos inner join s.planting p inner join p.field f inner join f.subdistrict sd inner join sd.district d inner join d.province pv where (s.date between :startDate and :endDate) and f = :field and pv = :province and tos = :targetOfSurvey")
	public List<Survey> findByDateAndFieldAndTargetOfSurveyAndProvince(@Param("startDate") Date startDate,
			@Param("endDate") Date endDate, @Param("field") Field field, @Param("province") Province province,
			@Param("targetOfSurvey") TargetOfSurvey targetOfSurvey);

	@Query("select s from Survey s inner join s.planting p inner join p.field f inner join f.userinfields uif inner join uif.user u where u =:user")
	public List<Survey> findByUserInField(@Param("user") User user, Pageable pageable);

	@Query("select s from Survey s inner join s.planting p inner join p.field f inner join f.userinfields uif inner join uif.user u where u =:user")
	public List<Survey> findByUserInField(@Param("user") User user);

	@Query("select s from Survey s inner join s.planting p inner join p.field f inner join f.userinfields uif inner join uif.user u where u =:user and ((p.code like %:key% or p.name like %:key% or f.name like %:key% or f.code like %:key%) and ( s.date between :stDate and :edDate))")
	public List<Survey> findByUserInFieldAndKeyAndDate(@Param("user") User user, @Param("key") String key,
			@Param("stDate") Date stdate, @Param("edDate") Date edDate, Pageable pageable);

	@Query("select s from Survey s inner join s.planting p inner join p.field f inner join f.userinfields uif inner join uif.user u where u =:user and ((p.code like %:key% or p.name like %:key% or f.name like %:key% or f.code like %:key%) and ( s.date between :stDate and :edDate))")
	public List<Survey> findByUserInFieldAndKeyAndDate(@Param("user") User user, @Param("key") String key,
			@Param("stDate") Date stdate, @Param("edDate") Date edDate);

	@Query("select s from Survey s inner join s.planting p inner join p.field f inner join f.userinfields uif inner join uif.user u where u = :user and p.plantingId = :id")
	public List<Survey> findByUserInFieldAndPlanting(@Param("user") User user, @Param("id") int id);

	@Query("select s from Survey s inner join s.planting p inner join p.field f inner join f.userinfields uif inner join uif.user u where u = :user and p.plantingId = :id")
	public List<Survey> findByUserInFieldAndPlanting(@Param("user") User user, @Param("id") int id, Pageable pageable);

	@Query("select s from Survey s inner join s.planting p inner join p.field f where f.organization = :organization")
	public List<Survey> findByOrganizationId(@Param("organization") Organization organization);

	@Query("select s from Survey s inner join s.planting p inner join p.field f inner join f.userinfields uif where uif.user = :user and s.lastUpdateDate < :date order by s.lastUpdateDate DESC")
	public List<Survey> findAllByUserNameAndDate(@Param("user") User user, @Param("date") Date date, Pageable pageable);

	@Query("select s from Survey s INNER JOIN s.planting p inner join p.field as f inner join f.userinfields as uif INNER JOIN uif.user as u WHERE u = :user and p.plantingId = :plantingId and s.date < :date order by s.date DESC")
	public List<Survey> findByPlantingIdAndUserAndDate(@Param("plantingId") int plantingId, @Param("user") User user,
			@Param("date") Date date, Pageable pageable);

	@Query("select s from Survey s inner join s.planting p inner join p.field f inner join f.userinfields uif inner join uif.user u where date(s.createDate) = :date and u.username = :username")
	public List<Survey> findByCreateDateAndUser(@Param("username") String username, @Param("date") Date date);

	@Query(value = "select IFNULL(s.c,0) co FROM "
			+ "(select day(s.createDate) d, count(s.surveyID) c "
			+ "from survey s inner join planting p on s.plantingID = p.plantingID "
			+ "inner join field f on p.fieldID = f.fieldID "
			+ "INNER JOIN userinfield uif on f.fieldID = uif.fieldID "
			+ "where (Year(s.createDate) = year(:date)) and (Month(s.createDate) = month(:date)) and uif.userID = :userId "
			+ "group by day(s.createDate)) s "
			+ "RIGHT JOIN  (SELECT day(date_field) dayincalendar "
			+ "FROM ( SELECT MAKEDATE(year(:date),1) + INTERVAL (month(:date)-1) MONTH + INTERVAL daynum DAY date_field "
			+ "FROM (	SELECT t*10+u daynum FROM (SELECT 0 t UNION SELECT 1 UNION SELECT 2 UNION SELECT 3) A, "
			+ "(SELECT 0 u UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) B "
			+ "ORDER BY daynum ) AA   ) AAA   WHERE MONTH(date_field) = month(:date) "
			+ "ORDER BY date_field)k   ON s.d = k.dayincalendar "
			+ "ORDER BY k.dayincalendar ASC ", nativeQuery = true)
	public List<Integer> countByCreateDateInCalendar (@Param("date")Date date, @Param("userId")int userId);
	
	@Query("select s from Survey s inner join s.planting p inner join p.field f inner join f.userinfields uif inner join uif.user u where day(s.createDate) = day(:date) and month(s.createDate) = month(:date) and year(s.createDate) = year(:date) and u.userId = :userId")
	public List<Survey> findByMonthAndYearCreateDate (@Param("date")Date date, @Param("userId")int userId);
	
	@Query("from Survey s where date(s.createDate) = :date")
	public List<Survey> findByCreateDate(@Param("date") Date date);
	
	@Query("select count(s) from Survey s inner join s.planting p inner join p.field f inner join f.userinfields uif where uif.user = :user and p.plantingId = :plantingId")
	public Integer countByUserInFieldAndPlantingId(@Param("user")User user, @Param("plantingId")int plantingId);

}
