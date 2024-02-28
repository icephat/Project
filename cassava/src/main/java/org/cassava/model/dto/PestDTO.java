package org.cassava.model.dto;

import org.cassava.model.Pest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class PestDTO {
	
	private Pest pest;
	
	private CommonsMultipartFile[] files;

	public Pest getPest() {
		return pest;
	}

	public void setPest(Pest pest) {
		this.pest = pest;
	}

	public CommonsMultipartFile[] getFiles() {
		return files;
	}

	public void setFiles(CommonsMultipartFile[] files) {
		this.files = files;
	}
	
	
}
