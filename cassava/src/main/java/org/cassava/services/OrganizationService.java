package org.cassava.services;

import java.util.List;

import org.apache.catalina.User;
import org.cassava.model.Organization;
import org.cassava.model.SurveyTarget;
import org.cassava.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service("Organization")
public class OrganizationService {
	@Autowired
	private OrganizationRepository organizationRepository;
	
	public List<Organization> findAll(){
		return (List<Organization>) organizationRepository.findAll();
	}
	
	public List<Organization> findAll(int page,int value){
		Pageable pageable = PageRequest.of(page-1, value);
		return (List<Organization>) organizationRepository.findAll(pageable);
	}
	
	
	public List<Organization> findByFarmerUsername(String username) {
		return organizationRepository.findByFarmerUsername(username);
	}
	
	public Organization findByStaffUsername(String username) {
		return organizationRepository.findByStaffUsername(username);
	}
	
	/*
	public List<Object> staffNonRole(int organizationId, String role){
		return organizationRepository.staffNonRole(organizationId,role);
	}		
	*/
	public Organization findById(int id) {
		return organizationRepository.findById(id).orElse(null);
	}		
	
	public Integer checkFkByOrganizationId(int organizationId){
		return organizationRepository.checkFkByOrganizationId(organizationId);
	}

	public int countStaffInOrganizationRole(int organizationId, String role){
		return organizationRepository.countStaffInOrganizationRole(organizationId,role);
	}
	
	public List<Object> countStaffInRole(String role){
		return organizationRepository.countStaffInRole(role);
	}
	
	public void deleteById (int id) {
		organizationRepository.deleteById(id);
	}
	
	public Organization save (Organization o) {
		return organizationRepository.save(o);
	}
	
	public List<Organization> findByName (String n){
		List<Organization> name = (List<Organization>) organizationRepository.findByName(n);
		return name;
	}
	
	public Organization findByPhone (String p){
		Organization phone = organizationRepository.findByPhone(p);
		return phone;
	}
	
	public Organization findByCode (String c){
		Organization code = organizationRepository.findByCode(c);
		return code;
	}
	public List<Organization> findBySurveyTarget(List<SurveyTarget> serveyTarget) {
		return organizationRepository.findBySurveyTarget(serveyTarget);
	}
	

}
