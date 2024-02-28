package org.cassava.model.dto;

public class PlantingYearDTO {
	
	private String yearTh;
	private int yearEn;
	
	public PlantingYearDTO(String yearTh,int yearEn) {
		this.yearEn = yearEn;
		this.yearTh = yearTh;
	}
	
	
	public String getYearTh() {
		return yearTh;
	}
	public void setYearTh(String yearTh) {
		this.yearTh = yearTh;
	}
	public int getYearEn() {
		return yearEn;
	}
	public void setYearEn(int yearEn) {
		this.yearEn = yearEn;
	}

}
