package org.cassava.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import java.util.ArrayList;
import java.util.Arrays;

import org.cassava.model.District;
import org.cassava.model.Farmer;
import org.cassava.model.Field;
import org.cassava.model.Organization;
import org.cassava.model.Province;
import org.cassava.model.Subdistrict;
import org.cassava.model.User;
import org.cassava.repository.FarmerRepository;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service("FarmerService")
public class FarmerService {

	@Autowired
	private FarmerRepository farmerRepository;

	@Autowired
	private OrganizationService organizationService;

	@Autowired
	private FieldService fieldService;

	@Autowired
	private ProvinceService provinceService;

	@Autowired
	private DistrictService districtService;

	@Autowired
	private SubdistrictService subdistrictService;
	@Autowired
	private final EntityManager entityManager;
	
	@Autowired
	public FarmerService (EntityManager entityManager) {
		this.entityManager = entityManager.getEntityManagerFactory().createEntityManager();
	}

	public List<Farmer> findAll() {
		return (List<Farmer>) farmerRepository.findAll();
	}

	public Farmer findByIDcard(String Card) {
		Farmer card = farmerRepository.findByIDcard(Card);
		return card;
	}

	public List<Farmer> findFarmerWithoutOrganization(int id) {
		return (List<Farmer>) farmerRepository.findFarmerWithoutOrganization(id);
	}

	public Farmer findById(int id) {
		Farmer farmer = farmerRepository.findById(id).orElse(null);
		return farmer;
	}

	public Farmer save(Farmer p) {
		return farmerRepository.save(p);
	}

	public void deletebyid(int i) {
		farmerRepository.deleteById(i);
	}

	public List<Farmer> findByOrganization(Integer organizationId) {
		return farmerRepository.findByOrganization(organizationId);
	}

	public List<Farmer> findFarmerByOrganizationAndFarmerOwnerNotIn(Organization organization, Field field) {
		return farmerRepository.findFarmerByOrganizationAndFarmerOwnerNotIn(organization, field);
	}

	public List<Farmer> findFarmerByOrganizationAndFarmerOwnerNotInAndKey(Organization organization, Field field,
			String key) {
		return farmerRepository.findFarmerByOrganizationAndFarmerOwnerNotInAndKey(organization, field, key);
	}

	public List<Farmer> findFarmerByOrganizationAndFarmerOwnerNotInAndStatus(Organization organization, Field field, int page,
			int value,List<String> status) {
		Pageable pageable = PageRequest.of(page - 1, value);
		return farmerRepository.findFarmerByOrganizationAndFarmerOwnerNotInAndStatus(organization, field, pageable,status);
	}

	public List<Farmer> findFarmerByOrganizationAndFarmerOwnerNotInAndKeyAndStatus(Organization organization, Field field,
			String key, int page, int value,List<String> status) {
		Pageable pageable = PageRequest.of(page - 1, value);
		return farmerRepository.findFarmerByOrganizationAndFarmerOwnerNotInAndKeyAndStatus(organization, field, key, pageable,status);
	}

	public List<Farmer> findFarmerOrganizationNotIn(int v) {
		Organization a = organizationService.findById(v);
		return (List<Farmer>) farmerRepository.findFarmerOrganizationNotIn(a);
	}

	public List<Farmer> findFarmerOrganizationNotIn(Organization v) {
		return (List<Farmer>) farmerRepository.findFarmerOrganizationNotIn(v);
	}

	public List<Farmer> findByOrganizationfieldNotIn(int organizationId, int fieldId) {
		Organization organization = organizationService.findById(organizationId);
		Field field = fieldService.findById(fieldId);
		return farmerRepository.findByOrganizationFieldNotIn(organization, field);
	}

	public List<Farmer> findByStatus(String status) {
		return (List<Farmer>) farmerRepository.findByStatus(status);
	}

	public List<Farmer> findByListStatus(List<String> status) {
		return (List<Farmer>) farmerRepository.findByListStatus(status);
	}

	public List<Farmer> findByStatusValid() {
		ArrayList<String> validStatus = new ArrayList<>(Arrays.asList("active", "inactive"));
		return (List<Farmer>) farmerRepository.findByListStatus(validStatus);
	}

	public List<Farmer> findByStatusValid(int page, int value) {
		Pageable pageable = PageRequest.of(page - 1, value);
		ArrayList<String> validStatus = new ArrayList<>(Arrays.asList("active", "inactive"));
		return (List<Farmer>) farmerRepository.findByListStatus(validStatus, pageable);
	}

	public List<Farmer> findByStatusValidAndKey(String key, int page, int value) {
		Pageable pageable = PageRequest.of(page - 1, value);
		ArrayList<String> validStatus = new ArrayList<>(Arrays.asList("active", "inactive"));
		return (List<Farmer>) farmerRepository.findByListStatusAndKey(key, validStatus, pageable);
	}

	public List<Farmer> findByStatusValidAndKey(String key) {
		ArrayList<String> validStatus = new ArrayList<>(Arrays.asList("active", "inactive"));
		return (List<Farmer>) farmerRepository.findByListStatusAndKey(key, validStatus);
	}

	public List<Farmer> findByOrganizationAndStatus(Organization organization, String status) {
		return (List<Farmer>) farmerRepository.findByOrganizationAndStatus(organization, status);
	}

	public List<Farmer> findByOrganizationAndListStatus(Organization organization, ArrayList<String> listStatus) {
		return (List<Farmer>) farmerRepository.findByOrganizationAndListStatus(organization, listStatus);
	}

	public List<Farmer> findByOrganizationAndStatusValid(Organization organization) {
		ArrayList<String> validStatus = new ArrayList<>(Arrays.asList("active", "inactive"));
		return (List<Farmer>) farmerRepository.findByOrganizationAndListStatus(organization, validStatus);
	}

	public List<Farmer> findByOrganizationAndStatusValid(Organization organization, int page, int value) {
		Pageable pageable = PageRequest.of(page - 1, value);
		ArrayList<String> validStatus = new ArrayList<>(Arrays.asList("active", "inactive"));
		return (List<Farmer>) farmerRepository.findByOrganizationAndListStatus(organization, validStatus, pageable);
	}

	public List<Farmer> findByOrganizationAndStatusValidAndKey(Organization organization, String key) {
		ArrayList<String> validStatus = new ArrayList<>(Arrays.asList("active", "inactive"));
		return (List<Farmer>) farmerRepository.findByOrganizationAndListStatusAndKey(organization, key, validStatus);
	}

	public List<Farmer> findByOrganizationAndStatusValidAndKey(Organization organization, String key, int page,
			int value) {
		Pageable pageable = PageRequest.of(page - 1, value);
		ArrayList<String> validStatus = new ArrayList<>(Arrays.asList("active", "inactive"));
		return (List<Farmer>) farmerRepository.findByOrganizationAndListStatusAndKey(organization, key, validStatus,
				pageable);
	}

	public void deleteFarmerOrganizationByFarmerIdAndOrganizationId(int farmerId, int organizationId) {
		farmerRepository.deleteFarmerOrganizationByFarmerIdAndOrganizationId(farmerId, organizationId);
	}

	public Farmer findByOrgannizationAndUsername(Organization organization, String username) {
		return farmerRepository.findByOrgannizationAndUsername(organization, username);
	}

	public Farmer findfarmerNotinOrgannizationbyusername(Organization organization, String username) {
		return farmerRepository.findfarmerNotinOrgannizationbyusername(organization, username);
	}

	public Farmer findByUsername(String username) {
		return farmerRepository.findByUsername(username);
	}

	public Integer checkFkByFarmerId(int farmerId) {
		return farmerRepository.checkFkByFarmerId(farmerId);
	}

	public Integer checkByProvinceAndDistrictAndSubdistrict(int provinceId, int districtId, int subdistrictId) {
		Province province = provinceService.findById(provinceId);
		Subdistrict subdistrict = subdistrictService.findById(subdistrictId);
		District district = districtService.findById(districtId);
		return farmerRepository.checkByProvinceAndDistrictAndSubdistrict(province, district, subdistrict);
	}

	public List<Farmer> findByProvinceAndDistrictAndSubdistrict(int provinceId, int districtId, int subdistrictId) {
		Province province = provinceService.findById(provinceId);
		Subdistrict subdistrict = subdistrictService.findById(subdistrictId);
		District district = districtService.findById(districtId);
		return farmerRepository.findByProvinceAndDistrictAndSubdistrict(province, district, subdistrict);
	}

	public List<User> findUserByOrganizationAndFirstNameAndLastNameAndProvinceName(int organizationId, String key) throws JsonMappingException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode = mapper.readTree(key);

		StringBuilder sb = new StringBuilder();
		sb.append("select * "
				+ "from farmer f inner join subdistrict sd on f.subdistrictID = sd.subdistrictID "
				+ "inner join district d on sd.districtID = d.districtID "
				+ "inner join province p on d.provinceID = p.provinceID "
				+ "inner join farmerorganization fo on f.farmerID = fo.farmerID "
				+ "inner join user u on f.farmerID = u.userID where fo.organizationID = " + organizationId + " ");
		
		if(jsonNode.get("firstName") != null) {
			sb.append("and (u.firstName like \"%" + jsonNode.get("firstName").asText() + "%\")");
		}
		
		if(jsonNode.get("lastName") != null) {
			sb.append("and (u.lastName like \"%" + jsonNode.get("lastName").asText() + "%\")");
		}
		
		if(jsonNode.get("provinceAddress") != null) {
			sb.append("and (p.name like \"%" + jsonNode.get("provinceAddress").asText() + "%\" "
					+ "or p.nameEng like\"%"+ jsonNode.get("provinceAddress").asText() +"%\")");
		}
		
		Query q = entityManager.createNativeQuery(sb.toString(), User.class);
		System.out.println(sb);
		return q.getResultList();
	}

	public List<User> findUserByOrganization(Integer organizationId, int page, int value) {
		Pageable pageable = PageRequest.of(page - 1, value);
		return farmerRepository.findUserByOrganization(organizationId, pageable);
	}
	
	public int checkFarmerInFieldByOrganizationAndField (Field field, List<String> roleName, int farmerId, Organization organization) {
		return farmerRepository.checkFarmerInFieldByOrganizationAndField(field, roleName, farmerId, organization);
	}
	
	public int checkFarmerNotInFieldByOrganizationAndField (Organization organization, Field field, int farmerId) {
		return farmerRepository.checkFarmerNotInFieldByOrganizationAndField(organization, field, farmerId);
	}
}
