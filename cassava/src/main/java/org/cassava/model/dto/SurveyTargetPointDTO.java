package org.cassava.model.dto;

import java.util.List;

import org.cassava.model.SurveyTargetPoint;

public class SurveyTargetPointDTO {
	private int[][] surveyValue;
	
	private int[][] surveyTargetPointIdList;
	
	private int surveyTargetId;
	
	private String status;

	public int[][] getSurveyValue() {
		return surveyValue;
	}

	public void setSurveyValue(int[][] surveyValue) {
		this.surveyValue = surveyValue;
	}

	public int[][] getSurveyTargetPointIdList() {
		return surveyTargetPointIdList;
	}

	public void setSurveyTargetPointIdList(int[][] surveyTargetPointIdList) {
		this.surveyTargetPointIdList = surveyTargetPointIdList;
	}

	public int getSurveyTargetId() {
		return surveyTargetId;
	}

	public void setSurveyTargetId(int surveyTargetId) {
		this.surveyTargetId = surveyTargetId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}






}
