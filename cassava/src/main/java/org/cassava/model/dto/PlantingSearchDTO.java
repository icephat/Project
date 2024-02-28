package org.cassava.model.dto;

public class PlantingSearchDTO extends FieldSearchDTO {
	protected String plantingName;
	protected long startDate;
	protected long endDate;
	
	
	public PlantingSearchDTO() {
		super();
	}
	
	public String getPlantingName() {
		return plantingName;
	}
	public void setPlantingName(String plantingName) {
		this.plantingName = plantingName;
	}

	public long getStartDate() {
		return startDate;
	}

	public void setStartDate(long startDate) {
		this.startDate = startDate;
	}

	public long getEndDate() {
		return endDate;
	}

	public void setEndDate(long endDate) {
		this.endDate = endDate;
	}
	

}
