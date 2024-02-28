package org.cassava.model.dto;

public class SurveyTargetPointUpdateDTO {
	private int surveyTargetId;
	private String targetOfSurveyName;
	private int value;
	
	
	public int getSurveyTargetId() {
		return surveyTargetId;
	}
	public void setSurveyTargetId(int surveyTargetId) {
		this.surveyTargetId = surveyTargetId;
	}
	public String getTargetOfSurveyName() {
		return targetOfSurveyName;
	}
	public void setTargetOfSurveyName(String targetOfSurveyName) {
		this.targetOfSurveyName = targetOfSurveyName;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	
	
}
