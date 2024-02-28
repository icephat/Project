package org.cassava.model.dto;

import org.cassava.model.Disease;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class DiseaseDTO {
	
	private Disease disease ;
	
	private CommonsMultipartFile[] files ;

	public Disease getDisease() {
		return disease;
	}

	public void setDisease(Disease disease) {
		this.disease = disease;
	}

	public CommonsMultipartFile[] getFiles() {
		return files;
	}

	public void setFiles(CommonsMultipartFile[] files) {
		this.files = files;
	}
}
