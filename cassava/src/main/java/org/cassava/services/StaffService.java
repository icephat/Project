package org.cassava.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.cassava.model.Farmer;
import org.cassava.model.Field;
import org.cassava.model.Organization;
import org.cassava.model.Plant;
import org.cassava.model.Staff;
import org.cassava.model.User;
import org.cassava.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service("StaffService")
public class StaffService {

	@Autowired
	private StaffRepository staffRepository;

	@Autowired
	private OrganizationService organizationService;

	public List<Staff> findAll() {
		return (List<Staff>) staffRepository.findAll();
	}

	public List<Staff> findByKey(String p, String d) {
		List<Staff> Staffs = (List<Staff>) staffRepository.findByKey(p, d);
		return Staffs;
	}

	public Staff findById(int id) {
		Staff staff = staffRepository.findById(id).orElse(null);
		return staff;
	}

	public Staff save(Staff p) {
		return staffRepository.save(p);
	}

	public void deleteById(int i) {
		staffRepository.deleteById(i);
	}

	
	public List<Object> findStaffByFieldIdAndOrganizationIdAndRoleId(int fieldId, int organizationId, String roleId) {
		return staffRepository.findStaffByFieldIdAndOrganizationIdAndRoleId(fieldId, organizationId, roleId);
	}
	/*
	public List<Staff> findStaffByOrganizationAndRolenameNotIn(Organization organization, Field field,
			String roleName) {
		return staffRepository.findStaffByOrganizationAndRolenameNotIn(organization, field, roleName);
	}
	*/
	public List<Staff> findByOrganizationAndFieldNotIn(Organization organization, Field field) {
		return staffRepository.findByOrganizationAndFieldNotIn(organization, field);
	}
	
	public List<Staff> findByOrganizationAndFieldNotInAndKey(Organization organization, Field field,String key) {
		return staffRepository.findByOrganizationAndFieldNotInAndKey(organization, field, key);
	}
	
	public List<Staff> findByOrganizationAndFieldNotInAndStatus(Organization organization, Field field, int page, int value,List<String> status) {
		Pageable pageable = PageRequest.of(page-1, value);
		return staffRepository.findByOrganizationAndFieldNotInAndStatus(organization, field, pageable,status);
	}
	
	public List<Staff> findByOrganizationAndFieldNotInAndKeyAndStatus(Organization organization, Field field, String key, int page, int value,List<String> status) {
		Pageable pageable = PageRequest.of(page-1, value);
		return staffRepository.findByOrganizationAndFieldNotInAndKeyAndStatus(organization, field, key, pageable,status);
	}

	public List<Staff> findAdminStaff() {
		return (List<Staff>) staffRepository.findAdminStaff();
	}

	public List<Staff> findByUserDefine(String userDefine) {
		return staffRepository.findByUserDefine(userDefine);
	}

	public List<Staff> findRegularStaff() {
		return (List<Staff>) staffRepository.findRegularStaff();
	}
	
	public List<Staff> findRegularStaffByListStatus(List<String> status) {
		return (List<Staff>) staffRepository.findRegularStaffByListStatus(status);
	}
	
	public List<Staff> findRegularStaffByValidStatus() {
		ArrayList<String> validStatus = new ArrayList<>(Arrays.asList("active", "inactive"));
		return (List<Staff>) staffRepository.findRegularStaffByListStatus(validStatus);
	}
	
	public List<Staff> findRegularStaffbyOrganization(int id) {
		Organization o = organizationService.findById(id);
		return (List<Staff>) staffRepository.findRegularStaffbyOrganization(o);
	}

	public List<Staff> findRegularStaffbyOrganization(Organization og) {
		return (List<Staff>) staffRepository.findRegularStaffbyOrganization(og);
	}

	public Staff findByUserName(String username) {
		return staffRepository.findByUserName(username);
	}

	public List<Object> findByUserRole(int id) {
		return staffRepository.findByUserRole(id);
	}

	
	public List<Staff> findByStatus(String status) {
		return (List<Staff>) staffRepository.findByStatus(status);
	}

	public List<Staff> findByListStatus(List<String> status) {
		return (List<Staff>) staffRepository.findByListStatus(status);
	}
	
	public List<Staff> findByStatusValid() {
		ArrayList<String> validStatus = new ArrayList<>(Arrays.asList("active", "inactive"));
		return (List<Staff>) staffRepository.findByListStatus(validStatus);
	}
	
	public List<Staff> findByStatusValidAndKey(String key) {
		ArrayList<String> validStatus = new ArrayList<>(Arrays.asList("active", "inactive"));
		return (List<Staff>) staffRepository.findByListStatusAndKey(validStatus,key);
	}

	public List<Staff> findByStatusValid(int page, int value) {
		Pageable pageable = PageRequest.of(page - 1, value);
		ArrayList<String> validStatus = new ArrayList<>(Arrays.asList("active", "inactive"));
		return (List<Staff>) staffRepository.findByListStatus(validStatus,pageable);
	}
	
	public List<Staff> findByStatusValidAndKey(String key,int page, int value) {
		Pageable pageable = PageRequest.of(page - 1, value);
		ArrayList<String> validStatus = new ArrayList<>(Arrays.asList("active", "inactive"));
		return (List<Staff>) staffRepository.findByListStatusAndKey(validStatus,key,pageable);
	}
	
	public List<Staff> findByListStatusAndKey(String key,int page, int value) {
		Pageable pageable = PageRequest.of(page - 1, value);
		ArrayList<String> validStatus = new ArrayList<>(Arrays.asList("active", "inactive"));
		return (List<Staff>) staffRepository.findByListStatusAndKey(validStatus,key,pageable);
	}
	
	public List<Staff> findByOrganizationAndStatus(Organization organization, String status) {
		return (List<Staff>) staffRepository.findByOrganizationAndStatus(organization, status);
	}	
	
	public List<Staff> findByOrganizationAndStatusValid(Organization organization) {
		return (List<Staff>) staffRepository.findByOrganizationAndStatusValid(organization);
	}
	
	public List<Staff> findByOrganizationAndStatusValidAndKey(Organization organization,String key) {
		return (List<Staff>) staffRepository.findByOrganizationAndStatusValidAndKey(organization,key);
	}

	public List<Staff> findByOrganizationAndStatusValid(Organization organization, int page, int value) {
		Pageable pageable = PageRequest.of(page - 1, value);
		return (List<Staff>) staffRepository.findByOrganizationAndStatusValid(organization, pageable);
	}
	
	public List<Staff> findByOrganizationAndStatusValidAndKey(Organization organization,String key, int page, int value) {
		Pageable pageable = PageRequest.of(page - 1, value);
		return (List<Staff>) staffRepository.findByOrganizationAndStatusValidAndKey(organization,key, pageable);
	}
	
	
	public List<Staff> findByOrganizationAndUserrole(int organizationId, String userRole) {
		return (List<Staff>) staffRepository.findByOrganizationAndUserrole(organizationId, userRole);
	}
	
	public List<Staff> findByOrganizationAndNotUserrole(int organizationId, String userRole) {
		return (List<Staff>) staffRepository.findByOrganizationAndNotUserrole(organizationId, userRole);
	}
	
	public List<Staff> findByOrganizationAndStatusValidAndNotUserrole(int organizationId, String userRole) {
		return (List<Staff>) staffRepository.findByOrganizationAndStatusValidAndNotUserrole(organizationId, userRole);
	}
	
	public List<Staff> findByOrganization (Organization organization, int page, int value) {
		Pageable pageable = PageRequest.of(page - 1, value);
		return staffRepository.findByOrganization(organization,pageable);
	}
	
	public int checkStaffInFieldByOrganizationAndField (Field field, List<String> rolename, int staffId) {
		return staffRepository.checkStaffInFieldByOrganizationAndField(field, rolename, staffId);
	}
	
	public int checkStaffNotInFieldByOrganizationAndField (Organization organization, Field field, int staffId) {
		return staffRepository.checkStaffNotInFieldByOrganizationAndField(organization, field, staffId);
	}

}
