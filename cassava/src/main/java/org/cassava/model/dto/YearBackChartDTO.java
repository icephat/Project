package org.cassava.model.dto;

import java.util.ArrayList;
import java.util.List;

public class YearBackChartDTO {
	
	private ArrayList<Double> firstYears = new ArrayList<Double>();
	private ArrayList<Double> secondYears = new ArrayList<Double>();
	private ArrayList<Double> thirdYears = new ArrayList<Double>();
	public ArrayList<Double> getFirstYears() {
		return firstYears;
	}
	public void setFirstYears(ArrayList<Double> firstYears) {
		this.firstYears = firstYears;
	}
	public ArrayList<Double> getSecondYears() {
		return secondYears;
	}
	public void setSecondYears(ArrayList<Double> secondYears) {
		this.secondYears = secondYears;
	}
	public ArrayList<Double> getThirdYears() {
		return thirdYears;
	}
	public void setThirdYears(ArrayList<Double> thirdYears) {
		this.thirdYears = thirdYears;
	}

}
