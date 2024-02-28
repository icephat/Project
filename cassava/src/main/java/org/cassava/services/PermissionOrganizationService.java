package org.cassava.services;

import java.util.List;

import org.cassava.model.Permission;
import org.cassava.model.PermissionOrganization;
import org.cassava.repository.PermissionOrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionOrganizationService {
	@Autowired
	private PermissionOrganizationRepository permissionOrganizationRepository;
	
	public List<PermissionOrganization> findAll () {
		return (List<PermissionOrganization>) permissionOrganizationRepository.findAll();
	}
	
	public PermissionOrganization save (PermissionOrganization permissionOrganization) {
		return permissionOrganizationRepository.save(permissionOrganization);
	}
	
	public void deleteById (int id) {
		permissionOrganizationRepository.deleteById(id);
	}
	
	public PermissionOrganization findById (int id) {
		return permissionOrganizationRepository.findById(id).get();
	}
	
	public List<Integer> findOrganizationIdByPermission(Permission permission){
		return (List<Integer>) permissionOrganizationRepository.findOrganizationIdByPermission(permission) ;
	}
}
