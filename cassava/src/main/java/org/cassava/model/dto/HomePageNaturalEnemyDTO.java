package org.cassava.model.dto;

import org.cassava.model.NaturalEnemy;

public class HomePageNaturalEnemyDTO extends HomePageDataDTO {
	private NaturalEnemy naturalEnemy;

	public HomePageNaturalEnemyDTO() {
		super();
	}

	public HomePageNaturalEnemyDTO(NaturalEnemy naturalEnemy) {
		super();
		this.naturalEnemy = naturalEnemy;
	}

	public NaturalEnemy getNaturalEnemy() {
		return naturalEnemy;
	}

	public void setNaturalEnemy(NaturalEnemy naturalEnemy) {
		this.naturalEnemy = naturalEnemy;
	}
	
	
}
