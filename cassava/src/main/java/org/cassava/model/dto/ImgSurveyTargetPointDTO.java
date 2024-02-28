package org.cassava.model.dto;

import org.cassava.model.SurveyTargetPoint;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class ImgSurveyTargetPointDTO {
	
	private SurveyTargetPoint surveyTargetPoint;
	
	private CommonsMultipartFile[] files ;
	
	public SurveyTargetPoint getSurveyTargetPoint() {
		return surveyTargetPoint;
	}

	public void setSurveyTargetPoint(SurveyTargetPoint surveyTargetPoint) {
		this.surveyTargetPoint = surveyTargetPoint;
	}

	public CommonsMultipartFile[] getFiles() {
		return files;
	}

	public void setFiles(CommonsMultipartFile[] files) {
		this.files = files;
	}
}
