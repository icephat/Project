package org.cassava.model.dto;


import java.util.List;
import org.cassava.model.SurveyTargetPoint;
public class SurveyTargetAndPointDTO {
	private String surveyTargetName;
	private int surveyTargetid;
	private SurveyTargetPoint  surveyTargetPoints;
	
	public SurveyTargetPoint getSurveyTargetPoints() {
		return surveyTargetPoints;
	}


	public void setSurveyTargetPoints(SurveyTargetPoint surveyTargetPoints) {
		this.surveyTargetPoints = surveyTargetPoints;
	}


	public String getSurveyTargetName() {
		return this.surveyTargetName;
	}


	public void setTargetofSurveyName(String  surveyTargetName) {
		this.surveyTargetName = surveyTargetName;
	}


	public int getSurveyTargetId() {
		return this.surveyTargetid;
	}


	public void setSurveyTargetid(int surveyTargetid) {
		this.surveyTargetid = surveyTargetid;
	}



	
	
	
	
	
	
}
