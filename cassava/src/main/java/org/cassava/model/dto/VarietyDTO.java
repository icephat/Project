package org.cassava.model.dto;

import org.cassava.model.Variety;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class VarietyDTO {

	private Variety variety;
		
	private CommonsMultipartFile[] files;

	public Variety getVariety() {
		return variety;
	}

	public void setVariety(Variety variety) {
		this.variety = variety;
	}

	public CommonsMultipartFile[] getFiles() {
		return files;
	}

	public void setFiles(CommonsMultipartFile[] files) {
		this.files = files;
	}
}
