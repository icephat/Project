package org.cassava.services;

import java.util.List;
import org.cassava.model.Role;
import org.cassava.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
	
	@Autowired
	RoleRepository  roleRepository ;

	public Role findById(int id){
		return roleRepository.findById(id).get();
	}
	
	public Role findByNameEng(String nameEng) {
		return roleRepository.findByNameEng(nameEng);
	}
	public Role findByUserIdAndRoleName(int userId,String roleName){
		return  roleRepository.findByUserIdAndRoleName(userId,roleName);
	}
	
	public Role save(Role r) {
		return roleRepository.save(r);
	}

	public List<Role> findByUserDefine(String userDefine){
		return roleRepository.findByUserDefine(userDefine);
	}
	
	public List<Role> findByUserDefine(String userDefine,int page,int value){
		Pageable pageable = PageRequest.of(page-1,value);
		return roleRepository.findByUserDefine(userDefine,pageable);
	}
	
}
