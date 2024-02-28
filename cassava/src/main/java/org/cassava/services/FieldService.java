package org.cassava.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.cassava.model.Field;
import org.cassava.model.Organization;
import org.cassava.model.Planting;
import org.cassava.model.Staff;
import org.cassava.model.Subdistrict;
import org.cassava.model.Survey;
import org.cassava.model.SurveyTarget;
import org.cassava.model.User;
import org.cassava.model.dto.FieldSearchDTO;
import org.cassava.repository.FieldRepository;
import org.cassava.util.ImageBase64Helper;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service("FieldService")
public class FieldService {
	@Autowired
	private FieldRepository fieldRepository;
	@Autowired
	private PlantingService plantingService;
	@Autowired
	private UserService userService;
	@Autowired
	private SurveyService surveyService;
	@Autowired
	private SubdistrictService subdistrictService;
	@Autowired
	private OrganizationService organizationService;
	@Autowired
	private final EntityManager entityManager;
	@Autowired
	private StaffService staffService;

	@Autowired
	public FieldService(EntityManager entityManager) {
		this.entityManager = entityManager.getEntityManagerFactory().createEntityManager();
	}

	@Value("${external.resoures.path}")
	private String externalPath;

	public Field save(Field field) {
		return fieldRepository.save(field);
	}

	public List<Field> findAll() {
		List<Field> field = (List<Field>) fieldRepository.findAll();
		return field;
	}

	public List<Field> findAll(String username, int page) {
		int start = page - 1;
		Pageable pageable = PageRequest.of(start, 19);
		User user = userService.findByUsername(username);
		return fieldRepository.findByPagitation(user, pageable);
	}

	public List<Field> findAll(String username, int page, int value) {
		int start = page - 1;
		Pageable pageable = PageRequest.of(start, value);
		User user = userService.findByUsername(username);
		return fieldRepository.findByPagitation(user, pageable);
	}

	public List<Field> findAll(int page) {
		int start = page - 1;
		Pageable pageable = PageRequest.of(start, 19);
		List<Field> fields = fieldRepository.findByPagination(pageable);
		return fields;
	}

	public List<Field> findAll(int page, int value) {
		int start = page - 1;
		Pageable pageable = PageRequest.of(start, value);
		List<Field> fields = fieldRepository.findByPagination(pageable);
		return fields;
	}

	public List<Field> findAll(int page, int value, Date date) {
		int start = page - 1;
		Pageable pageable = PageRequest.of(start, value);
		List<Field> fields = fieldRepository.findByPagination(pageable);
		return fields;
	}

	public List<Field> findByUserInField(String authentication, int page, int value) {
		int start = page - 1;
		Pageable pageable = PageRequest.of(start, value);
		User user = userService.findByUsername(authentication);
		List<Field> fields = fieldRepository.findByUserInField(user, pageable);
		return fields;
	}

	public List<Field> findByUserInField(User user, int page, int value) {
		int start = page - 1;
		Pageable pageable = PageRequest.of(start, value);
		List<Field> fields = fieldRepository.findByUserInField(user, pageable);
		return fields;
	}

	public Field findByPlantingAndUser(int id, String authentication) {
		User user = userService.findByUsername(authentication);
		Planting planting = plantingService.findById(id);
		return fieldRepository.findByPlantingAndUser(planting, user);
	}

	public Field findByPlantingAndUser(Planting planting, String authentication) {
		User user = userService.findByUsername(authentication);
		return fieldRepository.findByPlantingAndUser(planting, user);
	}

	public Field findByPlantingAndUser(int id, User user) {
		Planting planting = plantingService.findById(id);
		return fieldRepository.findByPlantingAndUser(planting, user);
	}

	public Field findByPlantingAndUser(Planting planting, User user) {
		return fieldRepository.findByPlantingAndUser(planting, user);
	}

	public Field findBySurveyAndUser(Survey survey, String authentication) {
		User user = userService.findByUsername(authentication);
		return fieldRepository.findBySurveyAndUser(survey, user);
	}

	public Field findBySurveyAndUser(int id, User user) {
		Survey survey = surveyService.findById(id);
		return fieldRepository.findBySurveyAndUser(survey, user);
	}

	public Field findBySurveyAndUser(Survey survey, User user) {
		return fieldRepository.findBySurveyAndUser(survey, user);
	}

	public Field findBySurveyAndUser(int id, String authentication) {
		Survey survey = surveyService.findById(id);
		User user = userService.findByUsername(authentication);
		return fieldRepository.findBySurveyAndUser(survey, user);
	}

	public Field findById(int fieldId) {
		return fieldRepository.findById(fieldId).orElse(null);
	}

	public Field findById(int fieldId, String username) {
		User user = userService.findByUsername(username);
		return fieldRepository.findById(fieldId, user);
	}

	public Field findById(int fieldId, User user) {
		return fieldRepository.findById(fieldId, user);
	}

	public List<Field> findBySubdistricIdAndUserId(int subdistrictId, int userid, String username, int page,
			int value) {
		int start = page - 1;
		Pageable pageable = PageRequest.of(start, value);
		User user = userService.findByUsername(username);
		Subdistrict subdistrict = subdistrictService.findById(subdistrictId);
		return fieldRepository.findBySubdistricIdAndUserId(subdistrict, userid, user, pageable);
	}

	public List<Field> findBySubdistrictIdAndUserIdAndDate(int subdistrictId, int userid, String username, int page,
			int value, Date date) {
		int start = page - 1;
		Pageable pageable = PageRequest.of(start, value);
		User user = userService.findByUsername(username);
		Subdistrict subdistrict = subdistrictService.findById(subdistrictId);
		return fieldRepository.findBySubdistrictIdAndUserIdAndDate(subdistrict, userid, user, date, pageable);
	}

	public void deleteById(int fieldId) {
		fieldRepository.deleteById(fieldId);
	}

	public List<Field> findBySubdistricIdAndUserId(int subdistrictId, int userid, int page, int value) {
		int start = page - 1;
		Pageable pageable = PageRequest.of(start, value);
		return fieldRepository.findBySubdistricIdAndUserId(subdistrictId, userid, pageable);
	}

	public List<Field> findByUserInField(int userid) {
		return fieldRepository.findByUserInField(userid);
	}

	public List<Field> findByUserInField(int userid, User user, int page, int value) {
		int start = page - 1;
		Pageable pageable = PageRequest.of(start, value);
		return fieldRepository.findByUserInField(userid, user, pageable);
	}

	public List<Field> findByFieldsAndSubDistrictId(List<Field> fields, int subid) {
		return fieldRepository.findByFieldsAndSubDistrictId(fields, subid);
	}

	public int findNumberOfAllFieldByUser(User user) {
		return fieldRepository.findNumberOfAllFieldByUser(user);
	}

	public Field findByName(String name) {
		return fieldRepository.findByName(name);
	}

	/*
	 * public List<Field> findByUserRoleEngAndOrganization(int organizationId){
	 * String role = "fieldRegistrar"; Organization org =
	 * organizationService.findById(organizationId); return
	 * fieldRepository.findByUserRoleEngAndOrganization(role, org); }
	 */
	public List<Field> findByUserInField(User user) {
		return fieldRepository.findByUserInField(user);
	}

	public List<Field> findByOrganization(Organization organization) {
		return fieldRepository.findByOrganization(organization);
	}

	public Field findByOrganizationAndCode(int organizationId, String code) {
		Organization organization = organizationService.findById(organizationId);
		return fieldRepository.findByOrganizationAndCode(organization, code);
	}

	public List<Field> findByUserInFieldAndRoleInField(int userId, String roleInField) {
		User user = userService.findById(userId);
		return fieldRepository.findByUserInFieldAndRoleInField(user, roleInField);
	}

	public List<Field> findByUserInFieldAndDate(int userid, User user, Date date, int page, int value) {
		int start = page - 1;
		Pageable pageable = PageRequest.of(start, value);
		return fieldRepository.findByUserInFieldAndDate(userid, user, date, pageable);
	}

	public List<Field> findAllOrderByDate(String username, int page, int value, Date date) {
		int start = page - 1;
		Pageable pageable = PageRequest.of(start, value);
		User user = userService.findByUsername(username);
		List<Field> fields = fieldRepository.findAllOrderByDate(user, date, pageable);
		return fields;
	}

	public String getResizeBase64ImageFile(int fieldId) {

		Field f = findById(fieldId);

		Path path = Paths.get(externalPath + File.separator + "Field" + File.separator + f.getImgPath());

		if (f.getImgPath() == null)
			return "";

		String base64File = "";

		FileInputStream fIn;
		try {

			fIn = new FileInputStream(path.toString());

			base64File = ImageBase64Helper.toImageBase64(ImageBase64Helper.resizeImage(fIn));

			// return base64File;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return base64File;
	}

	public String writeFile(MultipartFile file, String field) throws IOException {

		File folder = new File(externalPath + File.separator + "Field" + File.separator);
		if (!folder.exists()) {
			folder.mkdirs();
		}
		SecureRandom rand = new SecureRandom();
		// Setting the upper bound to generate
		// the random numbers between the specific range
		int upperbound = 1000;
		// Generating random values from 0 - 999
		// using nextInt()
		int int_random1 = rand.nextInt(upperbound);

		String filename = field;

		SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
		Date date = new Date();

		String now = formatter.format(date);

		String type = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);

		filename = "f_" + filename + "_" + now + "_" + int_random1 + "." + type;
		String path = externalPath + File.separator + "Field" + File.separator + filename;
		OutputStream outputStream = new FileOutputStream(path);
		outputStream.write(file.getBytes());
		outputStream.close();

		return filename;
	}

	public List<Object> findLocationByStartDateAndEndDateAndTargetOfSurveyId(Date startDate, Date endDate, int tosId) {
		return fieldRepository.findLocationByStartDateAndEndDateAndTargetOfSurveyId(startDate, endDate, tosId);
	}

	public List<Object> findNumberOfAllFieldInRegion() {
		return fieldRepository.findNumberOfAllFieldInRegion();
	}

	public List<Field> search(FieldSearchDTO fieldSearchDTO, User user) throws JsonProcessingException, ParseException {
		ObjectMapper obj = new ObjectMapper();
		JSONParser parser = new JSONParser();
		org.json.simple.JSONObject jobj = (org.json.simple.JSONObject) parser
				.parse(obj.writeValueAsString(fieldSearchDTO));

		StringBuilder sb = new StringBuilder();

		sb.append("select * from field where fieldID in " + "(SELECT distinct f.fieldID FROM field f "
				+ "INNER JOIN subdistrict s ON f.subdistrictID = s.subdistrictID "
				+ "INNER JOIN district d on d.districtID = s.districtID "
				+ "INNER join province pv on pv.provinceID = d.provinceID "
				+ "INNER JOIN userinfield uif on f.fieldID = uif.fieldID "
				+ "INNER JOIN user u on uif.userID = u.userID where f.fieldID in " + "(SELECT f.fieldid "
				+ "FROM `field` f inner join userinfield uif on f.fieldID = uif.fieldID " + "and uif.userID = "
				+ user.getUserId() + ") ");

		if (jobj.get("fieldName") != null) {
			sb.append("and  (f.code like \"%" + fieldSearchDTO.getFieldName() + "%\" or f.name like \"%"
					+ fieldSearchDTO.getFieldName() + "%\") ");
		}

		if (jobj.get("ownerName") != null) {
			sb.append("and ((u.firstName like \"%" + fieldSearchDTO.getOwnerName() + "%\" or u.lastName like \"%"
					+ fieldSearchDTO.getOwnerName() + "%\") and( uif.role = \"farmerOwner\")) ");

		}

		if (jobj.get("address") != null) {
			sb.append("and (s.name LIKE \"%" + fieldSearchDTO.getAddress() + "%\" or d.name like \"%"
					+ fieldSearchDTO.getAddress() + "%\" or pv.name like \"%" + fieldSearchDTO.getAddress() + "%\") ");

		}

		sb.append(")");
		Query q = entityManager.createNativeQuery(sb.toString(), Field.class);

		return q.getResultList();

	}

	public List<Field> searchPagination(FieldSearchDTO fieldSearchDTO, User user, int page, int value, Date date)
			throws JsonProcessingException, ParseException {
		ObjectMapper obj = new ObjectMapper();
		JSONParser parser = new JSONParser();
		org.json.simple.JSONObject jobj = (org.json.simple.JSONObject) parser
				.parse(obj.writeValueAsString(fieldSearchDTO));

		StringBuilder sb = new StringBuilder();

		sb.append("select * from field f where f.fieldID in " + "(SELECT distinct f.fieldID FROM field f "
				+ "INNER JOIN subdistrict s ON f.subdistrictID = s.subdistrictID "
				+ "INNER JOIN district d on d.districtID = s.districtID "
				+ "INNER join province pv on pv.provinceID = d.provinceID "
				+ "INNER JOIN userinfield uif on f.fieldID = uif.fieldID "
				+ "INNER JOIN user u on uif.userID = u.userID where f.fieldID in " + "(SELECT f.fieldid "
				+ "FROM `field` f inner join userinfield uif on f.fieldID = uif.fieldID " + "and uif.userID = "
				+ user.getUserId() + ") " + "and (f.lastUpdateDate < \"" + date.toInstant() + "\") ");

		if (jobj.get("fieldName") != null) {
			sb.append("and  (f.code like \"%" + fieldSearchDTO.getFieldName() + "%\" or f.name like \"%"
					+ fieldSearchDTO.getFieldName() + "%\") ");
		}

		if (jobj.get("ownerName") != null) {
			sb.append("and ((u.firstName like \"%" + fieldSearchDTO.getOwnerName() + "%\" or u.lastName like \"%"
					+ fieldSearchDTO.getOwnerName() + "%\") and( uif.role = \"farmerOwner\")) ");

		}

		if (jobj.get("address") != null) {
			sb.append("and (s.name LIKE \"%" + fieldSearchDTO.getAddress() + "%\" or d.name like \"%"
					+ fieldSearchDTO.getAddress() + "%\" or pv.name like \"%" + fieldSearchDTO.getAddress() + "%\") ");

		}

		sb.append(") order by f.lastUpdateDate DESC ");
		Query q = entityManager.createNativeQuery(sb.toString(), Field.class).setFirstResult((page - 1) * value)
				.setMaxResults(value);
		return q.getResultList();

	}

	public List<Field> findBySurveyTarget(List<SurveyTarget> sts) {
		return fieldRepository.findBySurveyTarget(sts);
	}

	public Integer findNumberOfFieldHasDiseaseByMonthsAndYears(List<Integer> month, List<Integer> year) {
		return fieldRepository.findNumberOfFieldHasDiseaseByMonthsAndYears(month, year);
	}

	public Integer findNumberOfFieldHasNaturalEnemyByMonthsAndYears(List<Integer> month, List<Integer> year) {
		return fieldRepository.findNumberOfFieldHasNaturalEnemyByMonthsAndYears(month, year);
	}

	public Integer findNumberOfFieldHasPestByMonthsAndYears(List<Integer> month, List<Integer> year) {
		return fieldRepository.findNumberOfFieldHasPestByMonthsAndYears(month, year);
	}

	public List<Object> findAvgPercentAndDamageLeveLHasDiseaseByMonthsAndYears(List<Integer> month,
			List<Integer> year) {
		return fieldRepository.findAvgPercentAndDamageLeveLHasDiseaseByMonthsAndYears(month, year);
	}

	public List<Object> findAvgPercentAndDamageLeveLHasNaturalEnemyByMonthsAndYears(List<Integer> month,
			List<Integer> year) {
		return fieldRepository.findAvgPercentAndDamageLeveLHasNaturalEnemyByMonthsAndYears(month, year);
	}

	public List<Object> findAvgPercentAndDamageLeveLHasPestByMonthsAndYears(List<Integer> month, List<Integer> year) {
		return fieldRepository.findAvgPercentAndDamageLeveLHasPestByMonthsAndYears(month, year);
	}

	public List<Object> findNumberOfAllFieldInRegionByOrganization(String username) {
		Staff staff = staffService.findByUserName(username);
		Organization organization = organizationService.findByStaffUsername(staff.getUser().getUsername());
		return fieldRepository.findNumberOfAllFieldInRegionByOrganization(organization);
	}

	public List<Object> findNumberOfAllFieldInRegionByUserInField(String username) {
		User user = userService.findByUsername(username);
		return fieldRepository.findNumberOfAllFieldInRegionByUser(user);
	}

	public Integer findNumberOfFieldHasDiseaseByMonthsAndYearsByOrganization(List<Integer> month, List<Integer> year,
			String username) {
		Staff staff = staffService.findByUserName(username);
		Organization organization = organizationService.findByStaffUsername(staff.getUser().getUsername());
		return fieldRepository.findNumberOfFieldHasDiseaseByMonthsAndYearsByOrganization(month, year, organization);
	}

	public Integer findNumberOfFieldHasNaturalEnemyByMonthsAndYearsByOrganization(List<Integer> month,
			List<Integer> year, String username) {
		Staff staff = staffService.findByUserName(username);
		Organization organization = organizationService.findByStaffUsername(staff.getUser().getUsername());
		return fieldRepository.findNumberOfFieldHasNaturalEnemyByMonthsAndYearsByOrganization(month, year,
				organization);
	}

	public Integer findNumberOfFieldHasPestByMonthsAndYearsByOrganization(List<Integer> month, List<Integer> year,
			String username) {
		Staff staff = staffService.findByUserName(username);
		Organization organization = organizationService.findByStaffUsername(staff.getUser().getUsername());
		return fieldRepository.findNumberOfFieldHasPestByMonthsAndYearsByOrganization(month, year, organization);
	}

	public List<Object> findAvgPercentAndDamageLeveLHasDiseaseByMonthsAndYearsByOrganization(List<Integer> month,
			List<Integer> year, String username) {
		Staff staff = staffService.findByUserName(username);
		Organization organization = organizationService.findByStaffUsername(staff.getUser().getUsername());
		return fieldRepository.findAvgPercentAndDamageLeveLHasDiseaseByMonthsAndYearsByOrganization(month, year,
				organization);
	}

	public List<Object> findAvgPercentAndDamageLeveLHasNaturalEnemyByMonthsAndYearsByOrganization(List<Integer> month,
			List<Integer> year, String username) {
		Staff staff = staffService.findByUserName(username);
		Organization organization = organizationService.findByStaffUsername(staff.getUser().getUsername());
		return fieldRepository.findAvgPercentAndDamageLeveLHasNaturalEnemyByMonthsAndYearsByOrganization(month, year,
				organization);
	}

	public List<Object> findAvgPercentAndDamageLeveLHasPestByMonthsAndYearsByOrganization(List<Integer> month,
			List<Integer> year, String username) {
		Staff staff = staffService.findByUserName(username);
		Organization organization = organizationService.findByStaffUsername(staff.getUser().getUsername());
		return fieldRepository.findAvgPercentAndDamageLeveLHasPestByMonthsAndYearsByOrganization(month, year,
				organization);
	}

	public Integer findNumberOfFieldHasDiseaseByMonthsAndYearsByUserInField(List<Integer> month, List<Integer> year,
			String username) {
		User user = userService.findByUsername(username);
		return fieldRepository.findNumberOfFieldHasDiseaseByMonthsAndYearsByUser(month, year, user);
	}

	public Integer findNumberOfFieldHasNaturalEnemyByMonthsAndYearsByUserInField(List<Integer> month,
			List<Integer> year, String username) {
		User user = userService.findByUsername(username);
		return fieldRepository.findNumberOfFieldHasNaturalEnemyByMonthsAndYearsByUser(month, year, user);
	}

	public Integer findNumberOfFieldHasPestByMonthsAndYearsByUserInField(List<Integer> month, List<Integer> year,
			String username) {
		User user = userService.findByUsername(username);
		return fieldRepository.findNumberOfFieldHasPestByMonthsAndYearsByUser(month, year, user);
	}

	public List<Object> findAvgPercentAndDamageLeveLHasDiseaseByMonthsAndYearsByUserInField(List<Integer> month,
			List<Integer> year, String username) {
		User user = userService.findByUsername(username);
		return fieldRepository.findAvgPercentAndDamageLeveLHasDiseaseByMonthsAndYearsByUser(month, year, user);
	}

	public List<Object> findAvgPercentAndDamageLeveLHasNaturalEnemyByMonthsAndYearsByUserInField(List<Integer> month,
			List<Integer> year, String username) {
		User user = userService.findByUsername(username);
		return fieldRepository.findAvgPercentAndDamageLeveLHasNaturalEnemyByMonthsAndYearsByUser(month, year, user);
	}

	public List<Object> findAvgPercentAndDamageLeveLHasPestByMonthsAndYearsByUserInField(List<Integer> month,
			List<Integer> year, String username) {
		User user = userService.findByUsername(username);
		return fieldRepository.findAvgPercentAndDamageLeveLHasPestByMonthsAndYearsByUser(month, year, user);
	}

	public List<Field> findByKey(String key, User user, int page, int value, Date date)
			throws JsonProcessingException, ParseException {

		StringBuilder sb = new StringBuilder();

		sb.append("select * from field f where f.fieldID in " + "(SELECT distinct f.fieldID FROM field f "
				+ "INNER JOIN subdistrict s ON f.subdistrictID = s.subdistrictID "
				+ "INNER JOIN district d on d.districtID = s.districtID "
				+ "INNER join province pv on pv.provinceID = d.provinceID "
				+ "INNER JOIN userinfield uif on f.fieldID = uif.fieldID "
				+ "INNER JOIN user u on uif.userID = u.userID where f.fieldID in " + "(SELECT f.fieldid "
				+ "FROM `field` f inner join userinfield uif on f.fieldID = uif.fieldID " + "and uif.userID = "
				+ user.getUserId() + ") " + "and (f.lastUpdateDate < \"" + date.toInstant() + "\") ");

		sb.append("and  (f.code like \"%" + key + "%\" or f.name like \"%" + key + "%\" ");

		sb.append("or u.firstName like \"%" + key + "%\" or u.lastName like \"%" + key
				+ "%\" and uif.role = \"farmerOwner\" ");

		sb.append("or s.name LIKE \"%" + key + "%\" or d.name like \"%" + key + "%\" or pv.name like \"%" + key
				+ "%\") ");

		sb.append(") order by f.lastUpdateDate DESC ");
		Query q = entityManager.createNativeQuery(sb.toString(), Field.class).setFirstResult((page - 1) * value)
				.setMaxResults(value);
		return q.getResultList();

	}
	
	public Integer countByUserInField (User user) {
		return fieldRepository.countByUserInField(user);
	}
	
	public Integer findcheckByOrganizationAndCode(int organizationId,String code) {
		Organization organization = organizationService.findById(organizationId);
		return fieldRepository.findcheckByOrganizationAndCode(organization,code);
	}
	
	public Integer findcheckByOrganizationAndCodeAndFieldId(int organizationId,String code,int fieldId) {
		Organization organization = organizationService.findById(organizationId);
		return fieldRepository.findcheckByOrganizationAndCodeAndFieldId(organization,code,fieldId);
	}

	public List<Object> countSurveyByStartDateAndEndDateAndTargetOfSurveyIdAndProvinceId(Date startDate, Date endDate, int tosId, int provinceId) {
		return fieldRepository.countSurveyByStartDateAndEndDateAndTargetOfSurveyIdAndProvinceId(startDate, endDate, tosId, provinceId);
	}
	
	public List<Object> countSurveyDetectedByStartDateAndEndDateAndTargetOfSurveyIdAndProvinceId(Date startDate, Date endDate, int tosId, int provinceId) {
		return fieldRepository.countSurveyDetectedByStartDateAndEndDateAndTargetOfSurveyIdAndProvinceId(startDate, endDate, tosId, provinceId);
	}
	
	public List<Object> findNameAndNumAndAvgPercentAndDamageLeveLHasDiseaseByMonthsAndYears(Integer month,Integer year) {
		return fieldRepository.findNameAndNumAndAvgPercentAndDamageLeveLHasDiseaseByMonthsAndYears(month, year);
	}
}
