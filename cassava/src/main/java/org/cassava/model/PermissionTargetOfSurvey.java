package org.cassava.model;
// Generated May 23, 2023, 10:56:11 PM by Hibernate Tools 5.6.3.Final

/**
 * Permissiontargetofsurvey generated by hbm2java
 */
public class PermissionTargetOfSurvey implements java.io.Serializable {

	private Integer permissionTargetOfSurveyId;
	private Permission permission;
	private TargetOfSurvey targetofsurvey;
	private String type;

	public PermissionTargetOfSurvey() {
	}

	public PermissionTargetOfSurvey(Permission permission, TargetOfSurvey targetofsurvey, String type) {
		this.permission = permission;
		this.targetofsurvey = targetofsurvey;
		this.type = type;
	}

	public Integer getPermissionTargetOfSurveyId() {
		return this.permissionTargetOfSurveyId;
	}

	public void setPermissionTargetOfSurveyId(Integer permissionTargetOfSurveyId) {
		this.permissionTargetOfSurveyId = permissionTargetOfSurveyId;
	}

	public Permission getPermission() {
		return this.permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}

	public TargetOfSurvey getTargetofsurvey() {
		return this.targetofsurvey;
	}

	public void setTargetofsurvey(TargetOfSurvey targetofsurvey) {
		this.targetofsurvey = targetofsurvey;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}