package org.cassava.services;

import java.util.List;

import org.cassava.model.Message;
import org.cassava.model.Permission;
import org.cassava.model.PermissionTargetOfSurvey;
import org.cassava.model.TargetOfSurvey;
import org.cassava.repository.MessageRepository;
import org.cassava.repository.PermissionTargetOfSurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("PermissionTargetOfSurveyService")
public class PermissionTargetOfSurveyService {

	@Autowired
	private PermissionTargetOfSurveyRepository permissionTargetOfSurveyRepository;
	
	public List<PermissionTargetOfSurvey> findAll() {
		return (List<PermissionTargetOfSurvey>) permissionTargetOfSurveyRepository.findAll();
	}

	public PermissionTargetOfSurvey findById(int id) {
		PermissionTargetOfSurvey permissionTargetofsurvey = permissionTargetOfSurveyRepository.findById(id).get();
		return permissionTargetofsurvey;
	}

	public PermissionTargetOfSurvey save(PermissionTargetOfSurvey k) {
		return permissionTargetOfSurveyRepository.save(k);
	}

	public void deleteById(int id) {
		permissionTargetOfSurveyRepository.deleteById(id);
	}
	
	public void deletePermissiontargetofsurveyByTargetOfSurveyID(int SurveyID) {
		permissionTargetOfSurveyRepository.deletePermissiontargetofsurveyByTargetOfSurveyID(SurveyID);
	}

	public List<PermissionTargetOfSurvey> findByPermissionId(int id) {
		return (List<PermissionTargetOfSurvey>) permissionTargetOfSurveyRepository.findByPermissionId(id);
	}
	
	public List<TargetOfSurvey> findtargetofsurveyByPermission(Permission permission) {
		return (List<TargetOfSurvey>) permissionTargetOfSurveyRepository.findtargetofsurveyByPermission(permission);
	}
	
	public List<TargetOfSurvey> findtargetofsurveyByPermissionAndType(Permission permission,String type) {
		return (List<TargetOfSurvey>) permissionTargetOfSurveyRepository.findtargetofsurveyByPermissionAndType(permission, type);
	}	
	
	public List<Integer> findtargetofsurveyIdByPermission(Permission permission) {
		return (List<Integer>) permissionTargetOfSurveyRepository.findtargetofsurveyIdByPermission(permission);
	}
}
