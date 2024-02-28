package org.cassava.model.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.cassava.model.Survey;
import org.cassava.model.SurveyTarget;

public class SurveyDTO {

	private Survey survey;

	private String surveyDate;
	
	private String temperature;
	
	private String humidity;

	private List<SelectedTargetOfSurveyDTO> surveytargetDiseases;
	
	private List<SelectedTargetOfSurveyDTO> surveytargetNaturalEnemies;

	private List<SelectedTargetOfSurveyDTO> surveytargetPestPhases;
	
	private List<SelectedImageSurveyDTO> selectedImageSurveyDTOs;

	public List<SelectedTargetOfSurveyDTO> getSurveytargetDiseases() {
		return surveytargetDiseases;
	}

	public void setSurveytargetDiseases(List<SelectedTargetOfSurveyDTO> surveytargetDiseases) {
		this.surveytargetDiseases = surveytargetDiseases;
	}


	public List<SelectedTargetOfSurveyDTO> getSurveytargetNaturalEnemies() {
		return surveytargetNaturalEnemies;
	}

	public void setSurveytargetNaturalEnemies(List<SelectedTargetOfSurveyDTO> surveytargetNaturalEnemies) {
		this.surveytargetNaturalEnemies = surveytargetNaturalEnemies;
	}

	public List<SelectedTargetOfSurveyDTO> getSurveytargetPestPhases() {
		return surveytargetPestPhases;
	}

	public void setSurveytargetPestPhases(List<SelectedTargetOfSurveyDTO> surveytargetPestPhases) {
		this.surveytargetPestPhases = surveytargetPestPhases;
	}

	public Survey getSurvey() {
		return survey;
	}

	public void setSurvey(Survey survey) {
		this.survey = survey;
	}

	public String getSurveyDate() {
		return surveyDate;
	}

	public void setSurveyDate(String surveyDate) {
		this.surveyDate = surveyDate;
	}

	public List<SelectedImageSurveyDTO> getSelectedImageSurveyDTOs() {
		return selectedImageSurveyDTOs;
	}

	public void setSelectedImageSurveyDTOs(List<SelectedImageSurveyDTO> selectedImageSurveyDTOs) {
		this.selectedImageSurveyDTOs = selectedImageSurveyDTOs;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getHumidity() {
		return humidity;
	}

	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}



}
