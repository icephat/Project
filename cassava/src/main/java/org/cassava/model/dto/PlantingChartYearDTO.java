package org.cassava.model.dto;

import java.util.ArrayList;

public class PlantingChartYearDTO {

	private ArrayList<Integer> years = new ArrayList<Integer>();
	private ArrayList<Double> amounts = new ArrayList<Double>();
	
	public ArrayList<Integer> getYears() {
		return years;
	}
	public void setYears(ArrayList<Integer> years) {
		this.years = years;
	}
	public ArrayList<Double> getAmounts() {
		return amounts;
	}
	public void setAmounts(ArrayList<Double> amounts) {
		this.amounts = amounts;
	}

}
