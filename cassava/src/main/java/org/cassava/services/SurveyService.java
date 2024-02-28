package org.cassava.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.cassava.model.District;
import org.cassava.model.Field;
import org.cassava.model.Organization;
import org.cassava.model.Province;
import org.cassava.model.Subdistrict;
import org.cassava.model.Survey;
import org.cassava.model.TargetOfSurvey;
import org.cassava.model.User;
import org.cassava.model.dto.SurveySearchDTO;
import org.cassava.repository.SurveyRepository;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service("SurveyService")
public class SurveyService {

	@Autowired
	private SurveyRepository surveyRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private FieldService fieldService;
	@Autowired
	private SubdistrictService subdistrictService;
	@Autowired
	private DistrictService districtService;
	@Autowired
	private OrganizationService organizationService;
	@Autowired
	private ProvinceService provinceService;
	@Autowired
	private TargetOfSurveyService targetOfSurveyService;

	@Autowired
	private final EntityManager entityManager;

	@Autowired
	public SurveyService(EntityManager entityManager) {
		this.entityManager = entityManager.getEntityManagerFactory().createEntityManager();
	}

	public List<Survey> findAll() {
		List<Survey> Surveys = (List<Survey>) surveyRepository.findAll();
		return Surveys;
	}

	public List<Survey> findByUserInField(User user) {
		List<Survey> Surveys = (List<Survey>) surveyRepository.findByUserInField(user);
		return Surveys;
	}

	public List<Survey> findByUserInField(User user, int page, int value) {
		Pageable pageable = PageRequest.of(page - 1, value);
		List<Survey> Surveys = (List<Survey>) surveyRepository.findByUserInField(user, pageable);
		return Surveys;
	}

	public List<Survey> findByUserInFieldAndKeyAndDate(User user, String key, Date stDate, Date edDate) {
		List<Survey> Surveys = (List<Survey>) surveyRepository.findByUserInFieldAndKeyAndDate(user, key, stDate,
				edDate);
		return Surveys;
	}

	public List<Survey> findByUserInFieldAndKeyAndDate(User user, String key, Date stDate, Date edDate, int page,
			int value) {
		Pageable pageable = PageRequest.of(page - 1, value);
		List<Survey> Surveys = (List<Survey>) surveyRepository.findByUserInFieldAndKeyAndDate(user, key, stDate, edDate,
				pageable);
		return Surveys;
	}

	public List<Survey> findByUserInFieldAndPlanting(User user, int id) {
		List<Survey> Surveys = (List<Survey>) surveyRepository.findByUserInFieldAndPlanting(user, id);
		return Surveys;
	}

	public List<Survey> findByUserInFieldAndPlanting(User user, int id, int page, int value) {
		Pageable pageable = PageRequest.of(page - 1, value);
		List<Survey> Surveys = (List<Survey>) surveyRepository.findByUserInFieldAndPlanting(user, id, pageable);
		return Surveys;
	}

	public List<Survey> findAll(int page, int value) {
		page -= 1;
		Pageable pageable = PageRequest.of(page, value);
		List<Survey> fields = surveyRepository.findByPagination(pageable);
		return fields;
	}

	public List<Survey> findByDate(Date d1, Date d2) {
		List<Survey> Surveydates = (List<Survey>) surveyRepository.findByDate(d1, d2);
		return Surveydates;
	}

	public List<Survey> findByRainType(String r) {
		List<Survey> Rain = (List<Survey>) surveyRepository.findByRainType(r);
		return Rain;
	}

	public List<Survey> findBySunlightType(String s) {
		List<Survey> Sunlight = (List<Survey>) surveyRepository.findBySunlightType(s);
		return Sunlight;
	}

	public List<Survey> findByDewType(String d) {
		List<Survey> Dew = (List<Survey>) surveyRepository.findByDewType(d);
		return Dew;
	}

	public List<Survey> findBySoilType(String sl) {
		List<Survey> Soil = (List<Survey>) surveyRepository.findBySoilType(sl);
		return Soil;
	}

	public List<Survey> findByPercentDamageFromHerbicide(String p) {
		List<Survey> PercentDamage = (List<Survey>) surveyRepository.findByPercentDamageFromHerbicide(p);
		return PercentDamage;
	}

	public Survey findById(int id) {
		Survey survey = surveyRepository.findById(id).orElse(null);
		return survey;
	}

	public Survey save(Survey k) {
		return surveyRepository.save(k);
	}

	public void deleteById(int id) {
		surveyRepository.deleteById(id);
	}

	public List<Survey> findByUserInField(int id) {
		User user = userService.findById(id);
		return surveyRepository.findByUserInField(user);
	}

	public List<Survey> findByUserInField(String name) {
		User user = userService.findByUsername(name);
		return surveyRepository.findByUserInField(user);
	}

	public List<Survey> findBySubdistrictAndDate(String name, int subdistrictId, Date date, int page, int value) {
		Pageable pageable = PageRequest.of(page - 1, value);
		User user = userService.findByUsername(name);
		Subdistrict subdistrict = subdistrictService.findById(subdistrictId);
		return surveyRepository.findBySubdistrictAndDate(user, subdistrict, date, pageable);
	}

	public List<Survey> findByDistrictAndDate(String name, int districtId, Date date, int page, int value) {
		Pageable pageable = PageRequest.of(page - 1, value);
		User user = userService.findByUsername(name);
		District district = districtService.findById(districtId);
		return surveyRepository.findByDistrictAndDate(user, district, date, pageable);
	}

	public List<Survey> findByOwnerAndDate(String name, int userId, Date date, int page, int value) {
		User userOwner = userService.findById(userId);
		Pageable pageable = PageRequest.of(page - 1, value);
		User user = userService.findByUsername(name);
		return surveyRepository.findByOwnerAndDate(user, userOwner, date, pageable);
	}

	public List<Survey> findByFieldAndDate(String name, int fieldId, Date date, int page, int value) {
		Pageable pageable = PageRequest.of(page - 1, value);
		User user = userService.findByUsername(name);
		Field field = fieldService.findById(fieldId);
		return surveyRepository.findByFieldAndDate(user, field, date, pageable);
	}

	public Survey findByUserAndSurveyId(User user, int surveyId) {
		return surveyRepository.findByUserAndSurveyId(user, surveyId);
	}

	public List<Survey> findAllByUserNameAndDate(String userName, Date date, int page, int value) {
		Pageable pageable = PageRequest.of(page - 1, value);
		User user = userService.findByUsername(userName);
		return surveyRepository.findAllByUserNameAndDate(user, date, pageable);
	}

	public List<Survey> findByDateAndOrganizationAndUser(String date, int organizationId, int userId) {
		Organization organization = organizationService.findById(userId);
		User user = userService.findById(userId);
		return surveyRepository.findByDateAndOrganizationAndUser(date, organization, user);
	}

	public int countByUserInfield(User user) {
		return surveyRepository.countByUserInfield(user);
	}

	public List<Survey> findByDateAndFieldAndTargetOfSurveyAndProvince(String startDate, String endDate,
			String fieldName, int provinceId, int targetOfSurveyId) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date frmDate = format.parse(startDate);
		Date enDate = format.parse(endDate);
		Field field = fieldService.findByName(fieldName);
		Province province = provinceService.findById(provinceId);
		TargetOfSurvey tos = targetOfSurveyService.findById(targetOfSurveyId);
		return surveyRepository.findByDateAndFieldAndTargetOfSurveyAndProvince(frmDate, enDate, field, province, tos);
	}

	public List<Survey> findByOrganizationId(int organizationId) {
		Organization organization = organizationService.findById(organizationId);
		return surveyRepository.findByOrganizationId(organization);
	}

	public List<Survey> filterPostsBasedOnKeywords(String start, String end, String fieldname, List<Integer> disease,
			List<Integer> pest, List<Integer> naturalenemy, List<Integer> provinceid) {
		List<Integer> tar = new ArrayList<Integer>();
		tar.addAll(disease);
		tar.addAll(pest);
		tar.addAll(naturalenemy);
		StringBuilder sb = new StringBuilder();

		sb.append("select * FROM survey as ss INNER JOIN" + " (select DISTINCT(s.surveyID) from Survey s");

		if (!tar.isEmpty()) {
			sb.append(
					" inner join surveytarget st on s.surveyID= st.surveyID inner join targetofsurvey tos on st.targetOfSurveyID=tos.targetOfSurveyID");
		}
		sb.append(
				" inner join planting p ON p.plantingID=s.plantingID" + " inner join `field` f on f.fieldID=p.fieldID "
						+ " inner join subdistrict sd on f.subdistrictID=sd.subdistrictID"
						+ " inner join district d on sd.districtID= d.districtID"
						+ " inner join province pv on pv.provinceID=d.provinceID where " + "(s.date between '")
				.append(start).append("' and '").append(end).append("') ");
		if (fieldname != null && !fieldname.isEmpty()) {
			sb.append(" and f.name = '").append(fieldname).append("'");
		}
		sb.append(" and ").append("pv.provinceID IN ( ");
		for (int i = 0; i < provinceid.size(); i++) {
			sb.append(provinceid.get(i));
			if (i < provinceid.size() - 1) {
				sb.append(", ");
			}
		}
		sb.append(")");
		if (!tar.isEmpty()) {
			sb.append(" and ").append("tos.targetOfSurveyID IN (");
			for (int i = 0; i < tar.size(); i++) {
				sb.append(tar.get(i));
				if (i < tar.size() - 1) {
					sb.append(", ");
				}
			}
			sb.append(")");

		}
		sb.append(") as su on ss.surveyID=su.surveyID");
		Query q = entityManager.createNativeQuery(sb.toString(), Survey.class);

		return q.getResultList();
	}

	public List<Survey> search(SurveySearchDTO searchDTO, User user)
			throws JsonProcessingException, ParseException, org.json.simple.parser.ParseException {
		ObjectMapper obj = new ObjectMapper();
		JSONParser parser = new JSONParser();
		org.json.simple.JSONObject jobj = (org.json.simple.JSONObject) parser.parse(obj.writeValueAsString(searchDTO));

		StringBuilder sb = new StringBuilder();
		sb.append("SELECT sv.surveyID,sv.date,"
				+ "sv.plantingID,"
				+ "sv.besidePlant,"
				+ "sv.weed,"
				+ "sv.temperature,"
				+ "sv.humidity,"
				+ "sv.rainType,"
				+ "sv.sunlightType"
				+ "sv.dewType,"
				+ "sv.soilType,"
				+ "sv.percentDamageFromHerbicide,"
				+ "sv.surveyPatternDisease,"
				+ "sv.surveyPatternPest,"
				+ "sv.surveyPatternNaturalEnemy,"
				+ "sv.numPointSurvey,"
				+ "sv.numPlantInPoint,"
				+ "sv.imgOwner,"
				+ "sv.imgOwnerOther,"
				+ "sv.imgPhotographer,"
				+ "sv.imgPhotographerOther,"
				+ "concat(\"( \",f.name,\" \",  DATE_FORMAT(DATE_ADD(p.primaryPlantPlantingDate, INTERVAL 543 YEAR),'%Y-%m-%d'),\" )\") as note,"
				+ "sv.createBy,"
				+ "sv.createDate,"
				+ "sv.lastUpdateBy,"
				+ "sv.lastUpdateDate,"
				+ "sv.status " 
				+ "FROM planting p INNER JOIN field f on p.fieldID = f.fieldID "
				+ "INNER JOIN subdistrict s ON f.subdistrictID = s.subdistrictID "
				+ "INNER JOIN district d on d.districtID = s.districtID "
				+ "INNER join province pv on pv.provinceID = d.provinceID "
				+ "INNER JOIN userinfield uif on f.fieldID = uif.fieldID "
				+ "INNER JOIN user u on uif.userID = u.userID "
				+ "INNER JOIN survey sv ON sv.plantingID = p.plantingID " + "where sv.surveyID in "
				+ "(select sv.surveyID from `survey` sv " + "inner join planting p on sv.plantingID = p.plantingID "
				+ "inner join field f on p.fieldID = f.fieldID "
				+ "inner join userinfield uif on f.fieldID = uif.fieldID " + "and uif.userID = " + user.getUserId()
				+ ")  and uif.role = \"farmerOwner\" ");

		if (jobj.get("fieldName") != null) {
			sb.append("and  (f.code like \"%" + searchDTO.getFieldName() + "%\" or f.name like \"%"
					+ searchDTO.getFieldName() + "%\") ");
		}

		if (jobj.get("ownerName") != null) {
			sb.append("and ((u.firstName like \"%" + searchDTO.getOwnerName() + "%\" or u.lastName like \"%"
					+ searchDTO.getOwnerName() + "%\") and ( uif.role = \"farmerOwner\")) ");
		}

		if (jobj.get("address") != null) {
			sb.append("and (s.name LIKE \"%" + searchDTO.getAddress() + "%\" or d.name like \"%"
					+ searchDTO.getAddress() + "%\" or pv.name like \"%" + searchDTO.getAddress() + "%\") ");
		}

		if (jobj.get("plantingName") != null) {
			sb.append("and (p.code like \"%" + searchDTO.getPlantingName() + "%\" or p.name like \"%"
					+ searchDTO.getPlantingName() + "%\") ");
		}

		if ((long) jobj.get("startDate") != 0 && (long) jobj.get("endDate") != 0) {
			String pattern = "yyyy-MM-dd";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			String startDate = simpleDateFormat.format(new Date(searchDTO.getStartDate()));
			String endDate = simpleDateFormat.format(new Date(searchDTO.getEndDate()));
			sb.append("and (p.primaryPlantPlantingDate BETWEEN \"" + startDate + "\" and \"" + endDate + "\") ");
		}

		if ((long) jobj.get("surveyStartDate") != 0 && (long) jobj.get("surveyEndDate") != 0) {
			String pattern = "yyyy-MM-dd";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			String startDate = simpleDateFormat.format(new Date(searchDTO.getSurveyStartDate()));
			String endDate = simpleDateFormat.format(new Date(searchDTO.getSurveyEndDate()));
			sb.append("and (sv.date BETWEEN \"" + startDate + "\" and \"" + endDate + "\") ");
		}

		if (jobj.get("status") != null) {
			sb.append("and (sv.status in (\"");
			List<String> statusList = searchDTO.getStatus();
			int count = 1;
			for (String status : statusList) {
				sb.append(status + "\"");
				if (count < statusList.size()) {
					sb.append(",\"");
				}
				count++;
			}
			sb.append(")) ");
		}

		Query q = entityManager.createNativeQuery(sb.toString(), Survey.class);

		return q.getResultList();
	}

	public List<Survey> searchPagination(SurveySearchDTO searchDTO, User user, int page, int value, Date date)
			throws JsonProcessingException, ParseException, org.json.simple.parser.ParseException {
		ObjectMapper obj = new ObjectMapper();
		JSONParser parser = new JSONParser();
		org.json.simple.JSONObject jobj = (org.json.simple.JSONObject) parser.parse(obj.writeValueAsString(searchDTO));

		StringBuilder sb = new StringBuilder();
		sb.append("SELECT distinct * " + "FROM planting p INNER JOIN field f on p.fieldID = f.fieldID "
				+ "INNER JOIN subdistrict s ON f.subdistrictID = s.subdistrictID "
				+ "INNER JOIN district d on d.districtID = s.districtID "
				+ "INNER join province pv on pv.provinceID = d.provinceID "
				+ "INNER JOIN userinfield uif on f.fieldID = uif.fieldID "
				+ "INNER JOIN user u on uif.userID = u.userID "
				+ "INNER JOIN survey sv ON sv.plantingID = p.plantingID " + "where sv.surveyID in "
				+ "(select sv.surveyID from `survey` sv " + "inner join planting p on sv.plantingID = p.plantingID "
				+ "inner join field f on p.fieldID = f.fieldID "
				+ "inner join userinfield uif on f.fieldID = uif.fieldID " + "and uif.userID = " + user.getUserId()
				+ ")  and uif.role = \"farmerOwner\" ");

		if (jobj.get("fieldName") != null) {
			sb.append("and  (f.code like \"%" + searchDTO.getFieldName() + "%\" or f.name like \"%"
					+ searchDTO.getFieldName() + "%\") ");
		}

		if (jobj.get("ownerName") != null) {
			sb.append("and ((u.firstName like \"%" + searchDTO.getOwnerName() + "%\" or u.lastName like \"%"
					+ searchDTO.getOwnerName() + "%\") and ( uif.role = \"farmerOwner\")) ");
		}

		if (jobj.get("address") != null) {
			sb.append("and (s.name LIKE \"%" + searchDTO.getAddress() + "%\" or d.name like \"%"
					+ searchDTO.getAddress() + "%\" or pv.name like \"%" + searchDTO.getAddress() + "%\") ");
		}

		if (jobj.get("plantingName") != null) {
			sb.append("and (p.code like \"%" + searchDTO.getPlantingName() + "%\" or p.name like \"%"
					+ searchDTO.getPlantingName() + "%\") ");
		}

		if ((long) jobj.get("startDate") != 0 && (long) jobj.get("endDate") != 0) {
			String pattern = "yyyy-MM-dd";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			String startDate = simpleDateFormat.format(new Date(searchDTO.getStartDate()));
			String endDate = simpleDateFormat.format(new Date(searchDTO.getEndDate()));
			sb.append("and (p.primaryPlantPlantingDate BETWEEN \"" + startDate + "\" and \"" + endDate + "\") ");
		}

		if ((long) jobj.get("surveyStartDate") != 0 && (long) jobj.get("surveyEndDate") != 0) {
			String pattern = "yyyy-MM-dd";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			String startDate = simpleDateFormat.format(new Date(searchDTO.getSurveyStartDate()));
			String endDate = simpleDateFormat.format(new Date(searchDTO.getSurveyEndDate()));
			sb.append("and (sv.date BETWEEN \"" + startDate + "\" and \"" + endDate + "\") ");
		}

		if (jobj.get("status") != null) {
			sb.append("and (sv.status in (\"");
			List<String> statusList = searchDTO.getStatus();
			int count = 1;
			for (String status : statusList) {
				sb.append(status + "\"");
				if (count < statusList.size()) {
					sb.append(",\"");
				}
				count++;
			}
			sb.append(")) ");
		}

		Query q = entityManager.createNativeQuery(sb.toString(), Survey.class).setFirstResult((page - 1) * value)
				.setMaxResults(value);

		return q.getResultList();
	}

	public List<Survey> findByFieldIdAndUserAndDate(int PlantingId, String username, int page, int value, Date date) {
		User user = userService.findByUsername(username);
		Pageable pageable = PageRequest.of(page - 1, value);
		return surveyRepository.findByPlantingIdAndUserAndDate(PlantingId, user, date, pageable);
	}

	public List<Survey> findByCreateDateAndUser(String userName, Date date) {
		return surveyRepository.findByCreateDateAndUser(userName, date);
	}

	public List<Survey> findByKey(String key, User user, int page, int value, Date date)
			throws JsonProcessingException, ParseException, org.json.simple.parser.ParseException {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT distinct * " + "FROM planting p INNER JOIN field f on p.fieldID = f.fieldID "
				+ "INNER JOIN subdistrict s ON f.subdistrictID = s.subdistrictID "
				+ "INNER JOIN district d on d.districtID = s.districtID "
				+ "INNER join province pv on pv.provinceID = d.provinceID "
				+ "INNER JOIN userinfield uif on f.fieldID = uif.fieldID "
				+ "INNER JOIN user u on uif.userID = u.userID "
				+ "INNER JOIN survey sv ON sv.plantingID = p.plantingID " + "where sv.surveyID in "
				+ "(select sv.surveyID from `survey` sv " + "inner join planting p on sv.plantingID = p.plantingID "
				+ "inner join field f on p.fieldID = f.fieldID "
				+ "inner join userinfield uif on f.fieldID = uif.fieldID " + "and uif.userID = " + user.getUserId()
				+ ")  and uif.role = \"farmerOwner\"" + " and (f.lastUpdateDate < \"" + date.toInstant() + "\") ");

		sb.append("and  (f.code like \"%" + key + "%\" or f.name like \"%" + key + "%\" ");

		sb.append("or  p.code like \"%" + key + "%\" or p.name like \"%" + key + "%\" ");

		sb.append("or u.firstName like \"%" + key + "%\" or u.lastName like \"%" + key
				+ "%\") and ( uif.role = \"farmerOwner\") ");

		Query q = entityManager.createNativeQuery(sb.toString(), Survey.class).setFirstResult((page - 1) * value)
				.setMaxResults(value);

		return q.getResultList();
	}

	public List<Integer> countByCreateDateInCalendar (Date date, int userId) {
		return surveyRepository.countByCreateDateInCalendar(date,userId);
	}

	public List<Survey> findByMonthAndYearCreateDate (Date date, int userId) {
		return surveyRepository.findByMonthAndYearCreateDate(date, userId);
	}

	public List<Survey> findByCreateDate (Date date) {
		return surveyRepository.findByCreateDate(date);
	}
	
	public Integer countByUserInFieldAndPlantingId (User user, int plantingId) {
		return surveyRepository.countByUserInFieldAndPlantingId(user, plantingId);
	}
}
