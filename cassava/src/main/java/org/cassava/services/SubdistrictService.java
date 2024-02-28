package org.cassava.services;

import org.cassava.repository.SubdistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import org.cassava.model.Subdistrict;
import org.cassava.model.User;
@Service("SubdistrictService")
public class SubdistrictService {
	@Autowired
	private SubdistrictRepository subdistrictRepository;
	
	@Autowired
	private UserService userService;
	
	public List<Subdistrict> findAll(){
		return (List<Subdistrict>) subdistrictRepository.findAll();
	}
	
	/*public List<Subdistrict> findAllByUserinField(String username){
		User user = userService.findByUsername(username);
		return (List<Subdistrict>) subdistrictRepository.findAllByUserinField(user);
	}*/
	
	public List<Subdistrict> findAllByUserinField(String username, int page, int value){
		int start = page - 1;
		Pageable pageable = PageRequest.of(start, value);
		User user = userService.findByUsername(username);
		return (List<Subdistrict>) subdistrictRepository.findAllByUserinField(user,pageable);
	}
	
	public List<Subdistrict> findByDistrictId(int districtId) {
        return subdistrictRepository.findByDistrictId(districtId);
    }
	public Subdistrict findById(int id) {
		return subdistrictRepository.findById(id).get();
	}

	public List<Subdistrict> findByUserinField(int userId, String username, int page, int value ){
		int start = page - 1;
		Pageable pageable = PageRequest.of(start, value);
		User user = userService.findByUsername(username);
		return subdistrictRepository.findByUserInField(userId,user,pageable);
	}
	
//	public List<Subdistrict> findByUserinField(int userId,String username){
//		User user = userService.findByUsername(username);
//		return subdistrictRepository.getSubdistrictByUserInField(userId,user);
//	}
	
}
