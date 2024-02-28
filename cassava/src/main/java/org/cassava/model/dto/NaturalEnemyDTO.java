package org.cassava.model.dto;

import org.cassava.model.NaturalEnemy;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class NaturalEnemyDTO {
	
	private NaturalEnemy naturalEnemy ;
	
	private CommonsMultipartFile[] files ;

	public NaturalEnemy getNaturalEnemy() {
		return naturalEnemy;
	}

	public void setNaturalEnemy(NaturalEnemy naturalEnemy) {
		this.naturalEnemy = naturalEnemy;
	}

	public CommonsMultipartFile[] getFiles() {
		return files;
	}

	public void setFiles(CommonsMultipartFile[] files) {
		this.files = files;
	}

	
}
