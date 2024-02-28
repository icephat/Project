package org.cassava.model.dto;

import java.util.ArrayList;
import java.util.List;

import org.cassava.model.SurveyTargetPoint;

public class SurveyTargetPointDTOs {
	
	private String surveyTargetName;
	private int surveyTargetid;
	private List<SurveyTargetPoint> surveyTargetPoints = new ArrayList<SurveyTargetPoint>();

	public String getSurveyTargetName() {
		return this.surveyTargetName;
	}

	public void setTargetofSurveyName(String surveyTargetName) {
		this.surveyTargetName = surveyTargetName;
	}

	public int getSurveyTargetId() {
		return this.surveyTargetid;
	}

	public void setSurveyTargetid(int surveyTargetid) {
		this.surveyTargetid = surveyTargetid;
	}

	public List<SurveyTargetPoint> getSurveyTargetPoints() {
		return surveyTargetPoints;
	}

	public void setSurveyTargetPoints(List<SurveyTargetPoint> surveyTargetPoints) {
		this.surveyTargetPoints = surveyTargetPoints;
	}

}
