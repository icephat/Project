package org.cassava.model.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class PlantingMonthAreaDTO {

	private Set<Integer> years = new HashSet<Integer>();
	private ArrayList<String> months  = new ArrayList<String>();
	private ArrayList<Double> nums = new ArrayList<Double>();
	
	
	
	
	
	public ArrayList<Double> getNums() {
		return nums;
	}
	public void setNums(ArrayList<Double> nums) {
		this.nums = nums;
	}
	public Set<Integer> getYears() {
		return years;
	}
	public void setYears(Set<Integer> years) {
		this.years = years;
	}
	public ArrayList<String> getMonths() {
		return months;
	}
	public void setMonths(ArrayList<String> months) {
		this.months = months;
	}
}
