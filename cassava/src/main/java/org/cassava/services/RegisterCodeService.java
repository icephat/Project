package org.cassava.services;

import java.util.List;
import java.util.Date;  

import org.cassava.model.RegisterCode;
import org.cassava.repository.RegisterCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterCodeService {
	
	@Autowired
	private RegisterCodeRepository registercodeRepository;
	
	public List<RegisterCode> findAll(){
		return (List<RegisterCode>) registercodeRepository.findAll();
	}
	
	public List<RegisterCode> findAllFarmerOrder(){
		return (List<RegisterCode>) registercodeRepository.findAllFarmerOrder();
	}		
	
	public List<RegisterCode> findAllUserOrder(){
		return (List<RegisterCode>) registercodeRepository.findAllUserOrder();
	}
	
	public String findStatusByCode(String code) {
		return registercodeRepository.findStatusByCode(code);
	}
	
	public RegisterCode findById(int id) {
		return registercodeRepository.findById(id).get();
	}
	
	public RegisterCode findValidCodeByCode(String code) {
		return registercodeRepository.findValidCodeByCode(code);
	}
	
	public RegisterCode save(RegisterCode regist) {
		return registercodeRepository.save(regist);
	}
	
	public void deleteById(int id) {
		registercodeRepository.deleteById(id);
	}
	
	public void deleteByTypeAndExpiredate(String userType, Date dt) {
		List<RegisterCode> rcList = registercodeRepository.findByTypeAndExpiredate(userType,dt);		
		for (RegisterCode r : rcList )
		{
			deleteById(r.getRegisterCodeId());
		}
	}
	
	public String findUserTypeByCode(String code) {
		return registercodeRepository.findUserTypeByCode(code);
	}
	
	public RegisterCode findByCode(String code) {
		return registercodeRepository.findByCode(code);
	}
	
	public String checkStatusByCode(RegisterCode regist) {
		Date dt = new Date(); 		
		String status;
		if(dt.after(regist.getExpireDate()))
			status = "Expire";			
		else if(dt.before(regist.getStartDate()))
			status = "NotDue";			
		else if (regist.getNumUseRegist()>=regist.getNumUserPermit())			
			status = "Exceed";	
		else
			status = "Valid"; 					
		return status;
	}

}
