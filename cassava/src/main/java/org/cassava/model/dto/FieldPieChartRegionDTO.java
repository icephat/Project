package org.cassava.model.dto;

import java.util.ArrayList;
import java.util.List;

public class FieldPieChartRegionDTO {
	
	private ArrayList<String> regions = new ArrayList<String>();
	private ArrayList<Double> amountFields = new ArrayList<Double>();
	
	public ArrayList<String> getRegions() {
		return regions;
	}
	public void setRegions(ArrayList<String> regions) {
		this.regions = regions;
	}
	public ArrayList<Double> getAmountFields() {
		return amountFields;
	}
	public void setAmountFields(ArrayList<Double> amountFields) {
		this.amountFields = amountFields;
	}
	
	

	
	
	

}
