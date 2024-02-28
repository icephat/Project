package org.cassava.services;

import java.util.List;

import org.cassava.model.User;
import org.cassava.model.Field;
import org.cassava.model.UserInField;
import org.cassava.model.Organization;
import org.cassava.repository.UserInFieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userinfield")
public class UserInFieldService {
	@Autowired
	private UserInFieldRepository userinfieldRepository;
	@Autowired
	private UserService userService;
	
	
	public UserInField save(UserInField userinfield) {
		return userinfieldRepository.save(userinfield);
	}
	
	public UserInField findById(int id) {
		return userinfieldRepository.findById(id).get();
	}
	
	public void delete(UserInField uf) {     
		userinfieldRepository.delete(uf);     
	}	
	
	public void deleteById(int id) {
		userinfieldRepository.deleteById(id);
	}
	/*
	public List<UserInField> findByFieldAndRolename(Field field, String roleName) {
        return userinfieldRepository.findByFieldAndRolename(field, roleName) ;
    }*/
	
	public UserInField findByFieldIdAndUserIdAndRolename(int fieldId,int userId,String roleName) {     
		return userinfieldRepository.findByFieldIdAndUserIdAndRolename(fieldId,userId,roleName);     
	}

	public List<UserInField> findAllByRolenameAndFieldId(int fieldId,List<String> roleName) {
        return userinfieldRepository.findAllByRolenameAndFieldId(fieldId,roleName) ;
    }
	
	public List<UserInField> findAllByUserIdAndRoleName(int userId,String roleName) {
        return userinfieldRepository.findAllByUserIdAndRoleName(userId,roleName) ;
    }

	public UserInField findByFieldIdAndRoleName(int fieldId) {
		return userinfieldRepository.findByFieldIdAndRoleName(fieldId,"farmerOwner") ;
	}

//	public UserInField findFarmerOwnerInFieldBySurveyId(int surveyId){
//		return userinfieldRepository.findFarmerOwnerBySurveyId(surveyId);
//	}
	
	public UserInField findByUserIdAndSurveyIdAndRoleName(String userName,int surveyId) {
		String rolename = "farmerOwner";
		User user = userService.findByUsername(userName);
		return userinfieldRepository.findByUserIdAndSurveyIdAndRoleName(user, surveyId,rolename);
	}

	public List<UserInField> findAllByOrganizationAndRolename(Organization organization, String roleName) {		
		return userinfieldRepository.findAllByOrganizationAndRolename(organization,roleName);
	}
	
	public List<UserInField> findAllByOrganizationAndRoleOwner(Organization organization) {		
		return userinfieldRepository.findAllByOrganizationAndRolename(organization, "farmerOwner");
	}

	public List<UserInField> findAllByFieldsAndRolename(List<Field> fields, String roleName) {		
		return userinfieldRepository.findAllByFieldsAndRolename(fields,roleName);
	}
	
	public List<UserInField> findAllByFiledsAndRoleOwner(List<Field> fields) {		
		return userinfieldRepository.findAllByFieldsAndRolename(fields, "farmerOwner");
	}
	
	public int checkFieldIdAndUserIdAndRolename (int fieldId, int userId, String roleName) {
		return userinfieldRepository.checkFieldIdAndUserIdAndRolename(fieldId, userId, roleName);
	}
}
