package org.cassava.model.dto;

import java.util.ArrayList;

import org.cassava.model.Permission;

public class PermissionAndCountSurveyTargetDTO {

	private Permission permission;
	private ArrayList<TargetOfSurveyAndCountServeyTargetDTO> targetOfSurveyAndCountServeyTargets = new ArrayList<TargetOfSurveyAndCountServeyTargetDTO>();

	public Permission getPermission() {
		return permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}

	public ArrayList<TargetOfSurveyAndCountServeyTargetDTO> getTargetOfSurveyAndCountServeyTargets() {
		return targetOfSurveyAndCountServeyTargets;
	}

	public void setTargetOfSurveyAndCountServeyTargets(
			ArrayList<TargetOfSurveyAndCountServeyTargetDTO> targetOfSurveyAndCountServeyTargets) {
		this.targetOfSurveyAndCountServeyTargets = targetOfSurveyAndCountServeyTargets;
	}

}
