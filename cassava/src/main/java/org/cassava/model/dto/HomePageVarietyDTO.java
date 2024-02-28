package org.cassava.model.dto;

import org.cassava.model.Variety;

public class HomePageVarietyDTO extends HomePageDataDTO {
	private Variety variety;
	
	public HomePageVarietyDTO() {
		super();
	}

	public HomePageVarietyDTO(Variety variety) {
		super();
		this.variety = variety;
	}

	public Variety getVariety() {
		return variety;
	}

	public void setVariety(Variety variety) {
		this.variety = variety;
	}
	
	
}
