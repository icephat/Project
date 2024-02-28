package org.cassava.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.cassava.model.Field;
import org.cassava.model.Organization;
import org.cassava.model.Plant;
import org.cassava.model.Planting;
import org.cassava.model.Staff;
import org.cassava.model.User;
import org.cassava.model.dto.PlantingSearchDTO;
import org.cassava.repository.PlantingRepository;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service("PlantingService")
public class PlantingService {
	@Autowired
	private PlantingRepository plantingRepository;

	@Autowired
	private UserService userService;
	@Autowired
	private OrganizationService organizationService;
	@Autowired
	private StaffService staffService;

	@Autowired
	private final EntityManager entityManager;

	@Autowired
	public PlantingService(EntityManager entityManager) {
		this.entityManager = entityManager.getEntityManagerFactory().createEntityManager();
	}

	public Planting save(Planting planting) {
		return plantingRepository.save(planting);
	}

	public List<Planting> findAll() {
		return (List<Planting>) plantingRepository.findAll();
	}
	
	public Planting findByOrganizationAndCode(int organizationId,String code) {
		Organization organization = organizationService.findById(organizationId);
		return plantingRepository.findByOrganizationAndCode(organization,code);
	}	

	public List<Planting> findByUsername(String username, int page, int value) {
		;
		Pageable pageable = PageRequest.of(page, value);
		return plantingRepository.findByUsername(username, pageable);
	}

	public List<Planting> findByUsernameAndDate(String username, int page, int value, Date date) {
		;
		Pageable pageable = PageRequest.of(page, value);
		return plantingRepository.findByUsernameAndDate(username, pageable, date);
	}

	public List<Planting> findAll(int page, int value) {
		;
		Pageable pageable = PageRequest.of(page, value);
		return plantingRepository.findByPagitation(pageable);
	}

	public List<Planting> findByFieldId(int fieldId, int page, int value) {
		Pageable pageable = PageRequest.of(page - 1, value);
		return plantingRepository.findByFieldId(fieldId, pageable);
	}

	public List<Planting> findByFieldIdAndUser(int fieldId, String username, int page, int value) {
		User user = userService.findByUsername(username);
		Pageable pageable = PageRequest.of(page - 1, value);
		return plantingRepository.findByFieldIdAndUser(fieldId, user, pageable);
	}

	public List<Planting> findByFieldIdAndUser(int fieldId, String username) {
		User user = userService.findByUsername(username);
		return plantingRepository.findByFieldIdAndUser(fieldId, user);
	}

	public List<Planting> findByFieldIdAndUserAndDate(int fieldId, String username, int page, int value, Date date) {
		User user = userService.findByUsername(username);
		Pageable pageable = PageRequest.of(page - 1, value);
		return plantingRepository.findByFieldIdAndUserAndDate(fieldId, user, date, pageable);
	}

	public Planting findById(int plantId) {
		return plantingRepository.findById(plantId).orElse(null);
	}

	public Planting findByUsernameAndPlantingId(String username, int plantId) {
		return plantingRepository.findByUsernameAndPlantingId(username, plantId);
	}

	public Planting findByCode(String code) {
		return plantingRepository.findByCode(code);
	}
	
	public List<Planting> findByUsernameAndSubdistrict(String username, int subid, int page, int value) {
		Pageable pageable = PageRequest.of(page - 1, value);
		return plantingRepository.findByUsernameAndSubdistrict(username, subid, pageable);
	}

	public List<Planting> findByUsernameAndSubdistrictAndDate(String username, int subid, int page, int value,
			Date date) {
		Pageable pageable = PageRequest.of(page - 1, value);
		return plantingRepository.findByUsernameAndSubdistrictAndDate(username, subid, date, pageable);
	}

	public List<Planting> findByUsernameAndDistrict(String username, int disid, int page, int value) {
		Pageable pageable = PageRequest.of(page - 1, value);
		return plantingRepository.findByUsernameAndDistrict(username, disid, pageable);
	}

	public List<Planting> findByUsernameAndDistrictAndDate(String username, int disid, int page, int value, Date date) {
		Pageable pageable = PageRequest.of(page - 1, value);
		return plantingRepository.findByUsernameAndDistrictAndDate(username, disid, date, pageable);
	}

	public List<Planting> findByUsernameAndOwnerId(String username, int ownerid, int page, int value) {
		Pageable pageable = PageRequest.of(page - 1, value);
		return plantingRepository.findByUsernameAndOwnerId(username, ownerid, pageable);
	}

	public List<Planting> findByUsernameAndOwnerIdAndDate(String username, int ownerid, int page, int value,
			Date date) {
		Pageable pageable = PageRequest.of(page - 1, value);
		return plantingRepository.findByUsernameAndOwnerIdAndDate(username, ownerid, pageable, date);
	}

	public void deleteById(int plantId) {
		plantingRepository.deleteById(plantId);
	}

	public int findNumberOfAllPlantingByUser(User user) {
		return plantingRepository.findNumberOfAllPlantingByUser(user);
	}

	public Planting findBySurveyIdAndUsername(int surveyId, String username) {
		return plantingRepository.findBySurveyIdAndUsername(surveyId, username);
	}

	public List<Planting> findPlantingDiseaseBySurveyTargetIdAndUsername(int surveyTargetId, String username, int page,
			int value) {
		Pageable pageable = PageRequest.of(page - 1, value);
		return plantingRepository.findPlantingDiseaseBySurveyTargetIdAndUsername(surveyTargetId, username, pageable);
	}

	public List<Planting> findPlantingDiseaseBySurveyTargetIdAndUsernameAndDate(int surveyTargetId, String username,
			int page, int value, Date date) {
		Pageable pageable = PageRequest.of(page - 1, value);
		return plantingRepository.findPlantingDiseaseBySurveyTargetIdAndUsernameAndDate(surveyTargetId, username,
				pageable, date);
	}

	public List<Planting> findByUserInField(User user) {
		return plantingRepository.findByUserInField(user);
	}

	public List<Planting> findByUserInField(User user, int page, int value) {
		Pageable pageable = PageRequest.of(page - 1, value);
		return plantingRepository.findByUserInField(user, pageable);
	}

	public List<Planting>  findByUserInFieldAndKeyAndDate(User user,String key,Date stDate,Date edDate) {		
		return plantingRepository.findByUserInFieldAndKeyAndDate(user,key,stDate,edDate);
	}
	
	public List<Planting> findByUserInFieldAndKeyAndDate(User user,String key,Date stDate,Date edDate,int page,int value) {		
		Pageable pageable = PageRequest.of(page-1, value);
		return plantingRepository.findByUserInFieldAndKeyAndDate(user,key,stDate,edDate,pageable);
	}

	public int findMaxYearlastUpdateDate() {
		return plantingRepository.findMaxYearlastUpdateDate();
	}

	public List<Object> findNumberOfAllDiseaseInFiveYear(String status) {
		int year = plantingRepository.findMaxYearlastUpdateDate() - 5;
		return plantingRepository.findNumberOfAllDiseaseInFiveYear(year, status);
	}

	public List<Object> findNumberOfAllNaturalEnemyInFiveYear(String status) {
		int year = plantingRepository.findMaxYearlastUpdateDate() - 5;
		return plantingRepository.findNumberOfAllNaturalEnemyInFiveYear(year, status);
	}

	public List<Object> findNumberOfAllPestInFiveYear(String status) {
		int year = plantingRepository.findMaxYearlastUpdateDate() - 5;
		return plantingRepository.findNumberOfAllPestInFiveYear(year, status);
	}

	public List<Object> findCountDiseaseInFiveYear(String status) {
		int year = plantingRepository.findMaxYearlastUpdateDate() - 5;
		return plantingRepository.findCountDiseaseInFiveYear(year, status);
	}

	public List<Object> findCountNaturalEnemyInFiveYear(String status) {
		int year = plantingRepository.findMaxYearlastUpdateDate() - 5;
		return plantingRepository.findCountNaturalEnemyInFiveYear(year, status);
	}

	public List<Object> findCountPestInFiveYear(String status) {
		int year = plantingRepository.findMaxYearlastUpdateDate() - 5;
		return plantingRepository.findCountPestInFiveYear(year, status);
	}

	public List<Object> findNumberOfAllPlantingInFiveYear() {
		int year = plantingRepository.findMaxYearlastUpdateDate() - 5;
		return plantingRepository.findNumberOfAllPlantingInFiveYear(year);
	}

	public Integer findCountPlantingByMonthAndYear(List<Integer> month, List<Integer> year) {
		return plantingRepository.findCountPlantingByMonthAndYear(month, year);
	}

	public Double findNumberOfAllPlantingByMonthAndYear(List<Integer> month, List<Integer> year) {
		return plantingRepository.findNumberOfAllPlantingByMonthAndYear(month, year);
	}

	public List<Object> findNumberOfAllPlantingByMonthAndYearInRegion(List<Integer> month, List<Integer> year) {
		return plantingRepository.findNumberOfAllPlantingByMonthAndYearInRegion(month, year);
	}

	public List<Object> findNumberOfAllPlantingByMonthAndYearInVariety(List<Integer> month, List<Integer> year) {
		return plantingRepository.findNumberOfAllPlantingByMonthAndYearInVariety(month, year);
	}

	public List<Object> findNumberOfPlantingAllMonthInThreeYear(int year) {
		int startyear = year;
		return plantingRepository.findNumberOfPlantingAllMonthInThreeYear(startyear, year);
	}

	public List<Integer> findAllYearInLastUpdateDate() {
		return plantingRepository.findAllYearInLastUpdateDate();
	}

	public List<Integer> findAllMonthInLastUpdateDate() {
		return plantingRepository.findAllMonthInLastUpdateDate();
	}

	public Double findNumberOfAllPlantingByMonthAndYearandOrganization(List<Integer> month, List<Integer> year,Staff staff) {
		Organization organization = organizationService.findByStaffUsername(staff.getUser().getUsername());
		return plantingRepository.findNumberOfAllPlantingByMonthAndYearandOrganization(month, year,organization.getOrganizationId());
	}

	public Integer findCountPlantingByMonthAndYearandOrganization(List<Integer> month, List<Integer> year,Staff staff) {
		Organization organization = organizationService.findByStaffUsername(staff.getUser().getUsername());
		return plantingRepository.findCountPlantingByMonthAndYearandOrganization(month, year,organization.getOrganizationId());
	}

	public List<Object> findNumberOfAllPlantingByMonthAndYearandOrganizationInRegion(List<Integer> month, List<Integer> year,Staff staff) {
		Organization organization = organizationService.findByStaffUsername(staff.getUser().getUsername());
		return plantingRepository.findNumberOfAllPlantingByMonthAndYearandOrganizationInRegion(month, year,organization.getOrganizationId());
	}

	public List<Object> findNumberOfAllPlantingByMonthAndYearandOrganizationInVariety(List<Integer> month, List<Integer> year,Staff staff) {
		Organization organization = organizationService.findByStaffUsername(staff.getUser().getUsername());
		return plantingRepository.findNumberOfAllPlantingByMonthAndYearandOrganizationInVariety(month, year,organization.getOrganizationId());
	}

	public List<Object> findNumberOfPlantingAllMonthInThreeYearByOrganization(int year,Staff staff) {
		Organization organization = organizationService.findByStaffUsername(staff.getUser().getUsername());
		int startyear = year;
		return plantingRepository.findNumberOfPlantingAllMonthInThreeYearByOrganization(startyear, year,organization.getOrganizationId());
	}

	public List<Integer> findAllYearInLastUpdateDateByOrganization(Staff staff) {
		Organization organization = organizationService.findByStaffUsername(staff.getUser().getUsername());
		return plantingRepository.findAllYearInLastUpdateDateByOrganization(organization.getOrganizationId());
	}

	public List<Integer> findAllMonthInLastUpdateDateByOrganization(Staff staff) {
		Organization organization = organizationService.findByStaffUsername(staff.getUser().getUsername());
		return plantingRepository.findAllMonthInLastUpdateDateByOrganization(organization.getOrganizationId());
	}

	public Double findNumberOfAllPlantingByMonthAndYearAndUserInfield(List<Integer> month, List<Integer> year,String username) {
		User user = userService.findByUsername(username);
		return plantingRepository.findNumberOfAllPlantingByMonthAndYearAndUserInfield(month, year,user.getUserId());
	}

	public Integer findCountPlantingByMonthAndYearAndUserInfield(List<Integer> month, List<Integer> year,String username) {
		User user = userService.findByUsername(username);
		return plantingRepository.findCountPlantingByMonthAndYearAndUserInfield(month, year,user.getUserId());
	}

	public List<Object> findNumberOfAllPlantingByMonthAndYearAndUserInfieldInRegion(List<Integer> month, List<Integer> year,String username) {
		User user = userService.findByUsername(username);
		return plantingRepository.findNumberOfAllPlantingByMonthAndYearAndUserInfieldInRegion(month, year,user.getUserId());
	}

	public List<Object> findNumberOfAllPlantingByMonthAndYearAndUserInfieldInVariety(List<Integer> month, List<Integer> year,String username) {
		User user = userService.findByUsername(username);
		return plantingRepository.findNumberOfAllPlantingByMonthAndYearAndUserInfieldInVariety(month, year,user.getUserId());
	}

	public List<Object> findNumberOfPlantingAllMonthInThreeYearByUserInfield(int year,String username) {
		User user = userService.findByUsername(username);
		int startyear = year;
		return plantingRepository.findNumberOfPlantingAllMonthInThreeYearByUserInfield(startyear, year,user.getUserId());
	}

	public List<Integer> findAllYearInLastUpdateDateByUserInfield(String username) {
		User user = userService.findByUsername(username);
		return plantingRepository.findAllYearInLastUpdateDateByUserInfield(user.getUserId());
	}

	public List<Integer> findAllMonthInLastUpdateDateByUserInfield(String username) {
		User user = userService.findByUsername(username);
		return plantingRepository.findAllMonthInLastUpdateDateByUserInfield(user.getUserId());
	}

	public List<Planting> search (PlantingSearchDTO searchDTO, User user) throws JsonProcessingException, ParseException {
		ObjectMapper obj = new ObjectMapper();
		JSONParser parser = new JSONParser();
		org.json.simple.JSONObject jobj = (org.json.simple.JSONObject) parser
				.parse(obj.writeValueAsString(searchDTO));

		StringBuilder sb = new StringBuilder();
		sb.append("SELECT distinct p.plantingID,p.code,p.fieldID,concat(p.name,\\\"( \\\",u.firstName,\\\" \\\",u.lastName,\\\" )\\\") as name,p.size,"
				+ "p.previousPlant,p.previousPlantOther,p.besidePlant,p.besidePlantOther,p.primaryVarietyOther,p.primaryPlantPlantingDate,"
				+ "p.primaryPlantHarvestDate,p.secondaryPlantType,p.secondaryPlantVariety,p.secondaryPlantPlantingDate,p.secondaryPlantHarvestDate,"
				+ "p.stemSource,p.soakingStemChemical,p.numTillage,"
				+ "p.soilAmendments,"
				+ "p.soilAmendmentsOther,"
				+ "p.fertilizerDate1,"
				+ "p.fertilizerFormular1,"
				+ "p.fertilizerDate2,"
				+ "p.fertilizerFormular2,"
				+ "p.fertilizerDate3,"
				+ "p.fertilizerFormular3,"
				+ "p.diseaseManagement,"
				+ "p.pestManagement,"
				+ "p.weedingMonth1,"
				+ "p.weedingChemical1,"
				+ "p.weedingChemicalOther1,"
				+ "p.weedingMonth2,"
				+ "p.weedingChemical2,"
				+ "p.weedingChemicalOther2,"
				+ "p.weedingMonth3,"
				+ "p.weedingChemical3,"
				+ "p.weedingChemicalOther3,"
				+ "p.note,"
				+ "p.createBy,"
				+ "p.createDate,"
				+ "p.lastUpdateBy,"
				+ "p.lastUpdateDate "
				+ "FROM planting p INNER JOIN field f on p.fieldID = f.fieldID "
				+ "INNER JOIN subdistrict s ON f.subdistrictID = s.subdistrictID "
				+ "INNER JOIN district d on d.districtID = s.districtID "
				+ "INNER join province pv on pv.provinceID = d.provinceID "
				+ "INNER JOIN userinfield uif on f.fieldID = uif.fieldID "
				+ "INNER JOIN user u on uif.userID = u.userID "
				+ "where "
				+ "p.plantingID in "
				+ "(select p.plantingID from `planting` p "
				+ "inner join field f on p.fieldID = f.fieldID "
				+ "inner join userinfield uif on f.fieldID = uif.fieldID "
				+ "and uif.userID = " + user.getUserId() + ")  and uif.role = \"farmerOwner\" ");

		if (jobj.get("fieldName") != null) {
			sb.append("and  (f.code like \"%" + searchDTO.getFieldName() 
					+ "%\" or f.name like \"%"
					+ searchDTO.getFieldName() 
					+ "%\") ");
		}

		if (jobj.get("ownerName") != null) {
			sb.append("and ((u.firstName like \"%" 
					+ searchDTO.getOwnerName()
					+ "%\" or u.lastName like \"%"
					+ searchDTO.getOwnerName() 
					+ "%\") and ( uif.role = \"farmerOwner\")) ");
		}

		if (jobj.get("address") != null) {
			sb.append("and (s.name LIKE \"%" + searchDTO.getAddress() 
					+ "%\" or d.name like \"%"
					+ searchDTO.getAddress() 
					+ "%\" or pv.name like \"%" 
					+ searchDTO.getAddress() 
					+ "%\") ");
		}

		if (jobj.get("plantingName") != null) {
			sb.append("and (p.code like \"%" 
					+ searchDTO.getPlantingName() 
					+ "%\" or p.name like \"%" 
					+ searchDTO.getPlantingName()
					+ "%\") ");
		}
		
		if ((long)jobj.get("startDate") != 0 && (long)jobj.get("endDate") != 0 ) {
			
			String pattern = "yyyy-MM-dd";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			String startDate = simpleDateFormat.format(new Date(searchDTO.getStartDate()));
			String endDate = simpleDateFormat.format(new Date(searchDTO.getEndDate())) ;
			sb.append("and (p.primaryPlantPlantingDate BETWEEN \""
			+ startDate 
			+"\" and \"" 
			+ endDate + "\") ");
		}

		Query q = entityManager.createNativeQuery(sb.toString(), Planting.class);
		
		return q.getResultList();
	}
	
	public List<Planting> searchPagination (PlantingSearchDTO searchDTO, User user, int page, int value, Date date) throws JsonProcessingException, ParseException {
		ObjectMapper obj = new ObjectMapper();
		JSONParser parser = new JSONParser();
		org.json.simple.JSONObject jobj = (org.json.simple.JSONObject) parser
				.parse(obj.writeValueAsString(searchDTO));

		StringBuilder sb = new StringBuilder();
		sb.append("SELECT distinct * "
				+ "FROM planting p INNER JOIN field f on p.fieldID = f.fieldID "
				+ "INNER JOIN subdistrict s ON f.subdistrictID = s.subdistrictID "
				+ "INNER JOIN district d on d.districtID = s.districtID "
				+ "INNER join province pv on pv.provinceID = d.provinceID "
				+ "INNER JOIN userinfield uif on f.fieldID = uif.fieldID "
				+ "INNER JOIN user u on uif.userID = u.userID "
				+ "where "
				+ "p.plantingID in "
				+ "(select p.plantingID from `planting` p "
				+ "inner join field f on p.fieldID = f.fieldID "
				+ "inner join userinfield uif on f.fieldID = uif.fieldID "
				+ "and uif.userID = " + user.getUserId() + ")  and uif.role = \"farmerOwner\" ");

		if (jobj.get("fieldName") != null) {
			sb.append("and  (f.code like \"%" + searchDTO.getFieldName() 
					+ "%\" or f.name like \"%"
					+ searchDTO.getFieldName() 
					+ "%\") ");
		}

		if (jobj.get("ownerName") != null) {
			sb.append("and ((u.firstName like \"%" 
					+ searchDTO.getOwnerName()
					+ "%\" or u.lastName like \"%"
					+ searchDTO.getOwnerName() 
					+ "%\") and ( uif.role = \"farmerOwner\")) ");
		}

		if (jobj.get("address") != null) {
			sb.append("and (s.name LIKE \"%" + searchDTO.getAddress() 
					+ "%\" or d.name like \"%"
					+ searchDTO.getAddress() 
					+ "%\" or pv.name like \"%" 
					+ searchDTO.getAddress() 
					+ "%\") ");
		}

		if (jobj.get("plantingName") != null) {
			sb.append("and (p.code like \"%" 
					+ searchDTO.getPlantingName() 
					+ "%\" or p.name like \"%" 
					+ searchDTO.getPlantingName()
					+ "%\") ");
		}
		
		if ((long)jobj.get("startDate") != 0 && (long)jobj.get("endDate") != 0 ) {
			
			String pattern = "yyyy-MM-dd";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			String startDate = simpleDateFormat.format(new Date(searchDTO.getStartDate()));
			String endDate = simpleDateFormat.format(new Date(searchDTO.getEndDate())) ;
			sb.append("and (p.primaryPlantPlantingDate BETWEEN \""
			+ startDate 
			+"\" and \"" 
			+ endDate + "\") ");
		}

		Query q = entityManager.createNativeQuery(sb.toString(), Planting.class).setFirstResult((page-1)*value).setMaxResults(value);
		
		return q.getResultList();
	}
	
	public List<Object> findNumberOfAllPlantingInFiveYearByOrganization(String username) {
		int year = plantingRepository.findMaxYearlastUpdateDate() - 5;
		Staff staff = staffService.findByUserName(username);
		Organization organization = organizationService.findByStaffUsername(staff.getUser().getUsername());
		return plantingRepository.findNumberOfAllPlantingInFiveYearByOrganization(year,organization.getOrganizationId());
	}
	
	public List<Object> findNumberOfAllPlantingInFiveYearByOrganizationAndUserInField(String username) {
		int year = plantingRepository.findMaxYearlastUpdateDate() - 5;
		User user = userService.findByUsername(username);
		return plantingRepository.findNumberOfAllPlantingInFiveYearByUser(year,user.getUserId());
	} 
	
	public List<Object> findNumberOfAllDiseaseInFiveYearByOrganization(String status,String username) {
		int year = plantingRepository.findMaxYearlastUpdateDate() - 5;
		Staff staff = staffService.findByUserName(username);
		Organization organization = organizationService.findByStaffUsername(staff.getUser().getUsername());
		return plantingRepository.findNumberOfAllDiseaseInFiveYearByOrganization(year, status, organization.getOrganizationId());
	}
	
	public List<Object> findNumberOfAllDiseaseInFiveYearByOrganizationAndUserInField(String status,String username) {
		int year = plantingRepository.findMaxYearlastUpdateDate() - 5;
		User user = userService.findByUsername(username);
		return plantingRepository.findNumberOfAllDiseaseInFiveYearByUser(year, status,user.getUserId());
	}

	public List<Object> findNumberOfAllNaturalEnemyInFiveYearByOrganization(String status, String username) {
		int year = plantingRepository.findMaxYearlastUpdateDate() - 5;
		Staff staff = staffService.findByUserName(username);
		Organization organization = organizationService.findByStaffUsername(staff.getUser().getUsername());
		return plantingRepository.findNumberOfAllNaturalEnemyInFiveYearByOrganization(year, status, organization.getOrganizationId());
	}
	
	public List<Object> findNumberOfAllNaturalEnemyInFiveYearByUser(String status, String username) {
		int year = plantingRepository.findMaxYearlastUpdateDate() - 5;
		User user = userService.findByUsername(username);
		return plantingRepository.findNumberOfAllNaturalEnemyInFiveYearByUser(year, status, user.getUserId());
	}

	public List<Object> findNumberOfAllPestInFiveYearByOrganization(String status, String username) {
		int year = plantingRepository.findMaxYearlastUpdateDate() - 5;
		Staff staff = staffService.findByUserName(username);
		Organization organization = organizationService.findByStaffUsername(staff.getUser().getUsername());
		return plantingRepository.findNumberOfAllPestInFiveYearByOrganization(year, status, organization.getOrganizationId());
	}
	
	public List<Object> findNumberOfAllPestInFiveYearByUser(String status, String username) {
		int year = plantingRepository.findMaxYearlastUpdateDate() - 5;
		User user = userService.findByUsername(username);
		return plantingRepository.findNumberOfAllPestInFiveYearByUser(year, status, user.getUserId());
	}

	public List<Object> findCountDiseaseInFiveYearByOrganization(String status, String username) {
		int year = plantingRepository.findMaxYearlastUpdateDate() - 5;
		Staff staff = staffService.findByUserName(username);
		Organization organization = organizationService.findByStaffUsername(staff.getUser().getUsername());
		return plantingRepository.findCountDiseaseInFiveYearByOrganization(year, status, organization.getOrganizationId());
	}

	public List<Object> findCountNaturalEnemyInFiveYearByOrganization(String status, String username) {
		int year = plantingRepository.findMaxYearlastUpdateDate() - 5;
		Staff staff = staffService.findByUserName(username);
		Organization organization = organizationService.findByStaffUsername(staff.getUser().getUsername());
		return plantingRepository.findCountNaturalEnemyInFiveYearByOrganization(year, status, organization.getOrganizationId());
	}

	public List<Object> findCountPestInFiveYearByOrganization(String status, String username) {
		int year = plantingRepository.findMaxYearlastUpdateDate() - 5;
		Staff staff = staffService.findByUserName(username);
		Organization organization = organizationService.findByStaffUsername(staff.getUser().getUsername());
		return plantingRepository.findCountPestInFiveYearByOrganization(year, status, organization.getOrganizationId());
	}
	
	public List<Object> findCountDiseaseInFiveYearByUser(String status, String username) {
		int year = plantingRepository.findMaxYearlastUpdateDate() - 5;
		User user = userService.findByUsername(username);
		return plantingRepository.findCountDiseaseInFiveYearByUser(year, status, user.getUserId());
	}

	public List<Object> findCountNaturalEnemyInFiveYearByUser(String status, String username) {
		int year = plantingRepository.findMaxYearlastUpdateDate() - 5;
		User user = userService.findByUsername(username);
		return plantingRepository.findCountNaturalEnemyInFiveYearByUser(year, status, user.getUserId());
	}

	public List<Object> findCountPestInFiveYearByUser(String status, String username) {
		int year = plantingRepository.findMaxYearlastUpdateDate() - 5;
		User user = userService.findByUsername(username);
		return plantingRepository.findCountPestInFiveYearByUser(year, status, user.getUserId());
	}

	public List<Planting> findByCreateDateAndUser(Date date , User user) {
		return plantingRepository.findByCreateDateAndUser(date,user);
	}
	
	public List<Planting> findByKey (String key, User user, int page, int value, Date date) throws JsonProcessingException, ParseException {

		StringBuilder sb = new StringBuilder();
		sb.append("SELECT distinct * "
				+ "FROM planting p INNER JOIN field f on p.fieldID = f.fieldID "
				+ "INNER JOIN subdistrict s ON f.subdistrictID = s.subdistrictID "
				+ "INNER JOIN district d on d.districtID = s.districtID "
				+ "INNER join province pv on pv.provinceID = d.provinceID "
				+ "INNER JOIN userinfield uif on f.fieldID = uif.fieldID "
				+ "INNER JOIN user u on uif.userID = u.userID "
				+ "where "
				+ "p.plantingID in "
				+ "(select p.plantingID from `planting` p "
				+ "inner join field f on p.fieldID = f.fieldID "
				+ "inner join userinfield uif on f.fieldID = uif.fieldID "
				+ "and uif.userID = " + user.getUserId() + ")  and uif.role = \"farmerOwner\"" + " and (f.lastUpdateDate < \"" + date.toInstant() + "\")  ");

		
			sb.append("and  (f.code like \"%" + key 
					+ "%\" or f.name like \"%"
					+ key 
					+ "%\" ");

			sb.append("or  p.code like \"%" + key
					+ "%\" or p.name like \"%"
					+ key
					+ "%\" ");

			sb.append("or u.firstName like \"%" 
					+ key
					+ "%\" or u.lastName like \"%"
					+ key
					+ "%\" and ( uif.role = \"farmerOwner\"))");

			
		Query q = entityManager.createNativeQuery(sb.toString(), Planting.class).setFirstResult((page-1)*value).setMaxResults(value);
		
		return q.getResultList();
	}

	public List<Integer> countByCreateDateInCalendar (Date date, int userId){
		return plantingRepository.countByCreateDateInCalendar(date, userId);
	}

	public List<Planting> findByMonthAndYearCreateDate (Date date, int userId){
		return plantingRepository.findByMonthAndYearCreateDate(date, userId);
	}
	
	public Integer countByUserInField (User user) {
		return plantingRepository.countByUserInField(user);
	}
	
	public List<Planting> findByCreateDate (Date date) {
		return plantingRepository.findByCreateDate(date);
	}
	
	public Integer countByUserInFieldAndFieldId (User user,int fieldId) {
		return plantingRepository.countByUserInFieldAndFieldId(user, fieldId);
	}

	public Integer findByOrganizationAndCodeAndPlantingId(int organizationId,String code,int plantingId) {
		Organization organization = organizationService.findById(organizationId);
		return plantingRepository.findByOrganizationAndCodeAndPlantingId(organization,code,plantingId);
	}
}
