package org.cassava.services;

import java.util.List;

import org.cassava.model.Permission;
import org.cassava.model.PermissionFile;
import org.cassava.model.Staff;
import org.cassava.repository.PermissionFileRepository;
import org.cassava.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("PermissionFileService")
public class PermissionFileService {
	

	@Autowired
	private PermissionService permissionService;
	@Autowired
	private PermissionFileRepository permissionFileRepository;
	
	public PermissionFile save(PermissionFile permissionFile) {
		return permissionFileRepository.save(permissionFile);
	}
	
	public List<PermissionFile> findAll(){
		List<PermissionFile> permissionFile = (List<PermissionFile>) permissionFileRepository.findAll();
		return permissionFile;
	}
	
	public PermissionFile findById(int permissionFileId) {
		return permissionFileRepository.findById(permissionFileId).get();
	}
	
	public void deleteById(int permissionFileId) {
		permissionFileRepository.deleteById(permissionFileId);
	}

}
