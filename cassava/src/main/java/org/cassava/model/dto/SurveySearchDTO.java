package org.cassava.model.dto;

import java.util.List;

public class SurveySearchDTO extends PlantingSearchDTO {
	private long surveyStartDate;
	private long surveyEndDate;
	private List<String> status;
	
	public SurveySearchDTO() {
		super();
	}

	public SurveySearchDTO(long surveyStartDate, long surveyEndDate, List<String> status) {
		super();
		this.surveyStartDate = surveyStartDate;
		this.surveyEndDate = surveyEndDate;
		this.status = status;
	}

	public long getSurveyStartDate() {
		return surveyStartDate;
	}

	public void setSurveyStartDate(long surveyStartDate) {
		this.surveyStartDate = surveyStartDate;
	}

	public long getSurveyEndDate() {
		return surveyEndDate;
	}

	public void setSurveyEndDate(long surveyEndDate) {
		this.surveyEndDate = surveyEndDate;
	}

	public List<String> getStatus() {
		return status;
	}

	public void setStatus(List<String> status) {
		this.status = status;
	}
	
	
}
