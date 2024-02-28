package org.cassava.services;

import java.util.List;

import org.cassava.model.Permission;
import org.cassava.model.PermissionProvince;
import org.cassava.model.Province;
import org.cassava.repository.PermissionProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("PermissionProvinceService")
public class PermissionProvinceService {

	@Autowired
	private PermissionProvinceRepository permissionProvinceRepository;
	
	public PermissionProvince save(PermissionProvince permissionprovince) {
		return permissionProvinceRepository.save(permissionprovince);
	}
	
	public List<PermissionProvince> findAll(){
		List<PermissionProvince> permissionprovince = (List<PermissionProvince>) permissionProvinceRepository.findAll();
		return permissionprovince;
	}
	
	public PermissionProvince findById(int permissionprovinceId) {
		return permissionProvinceRepository.findById(permissionprovinceId).get();
	}
	
	public void deleteById(int permissionprovinceId) {
		permissionProvinceRepository.deleteById(permissionprovinceId);
	}
	
	public List<Province> findProvinceByPermission(Permission permission){
		return (List<Province>) permissionProvinceRepository.findProvinceByPermission(permission) ;
	}
	
	public List<Integer> findProvinceIdByPermission(Permission permission){
		return (List<Integer>) permissionProvinceRepository.findProvinceIdByPermission(permission) ;
	}
}
