package org.cassava.model.dto;

import java.util.List;

import org.cassava.model.Planting;
import org.cassava.model.Variety;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class PlantingDTO {
	private Planting planting;
	
	public List<Variety> getVarieties() {
		return varieties;
	}

	public void setVarieties(List<Variety> varieties) {
		this.varieties = varieties;
	}

	private List<Variety> varieties;
	
	private String plantingDatePrimaryPlantPlanting;
	
	private String plantingDatePrimaryPlantHarvest;
	
	private String plantingDateSecondaryPlantPlanting;
	
	private String plantingDateSecondaryPlantHarvest;
	
	private String 	plantingDateFertilizerDate1;
	
	private String 	plantingDateFertilizerDate2;
	
	private String 	plantingDateFertilizerDate3;
	
	private String 	plantingDateWeedingMonth1;
	
	private String 	plantingDateWeedingMonth2;
	
	private String 	plantingDateWeedingMonth3;
	
	public Planting getPlanting() {
		return planting;
	}

	public void setPlanting(Planting planting) {
		this.planting = planting;
	}
	
	public String getPlantingDatePrimaryPlantPlanting() {
		return plantingDatePrimaryPlantPlanting;
	}

	public void setPlantingDatePrimaryPlantPlanting(String plantingDatePrimaryPlantPlanting) {
		this.plantingDatePrimaryPlantPlanting = plantingDatePrimaryPlantPlanting;
	}

	public String getPlantingDatePrimaryPlantHarvest() {
		return plantingDatePrimaryPlantHarvest;
	}

	public void setPlantingDatePrimaryPlantHarvest(String plantingDatePrimaryPlantHarvest) {
		this.plantingDatePrimaryPlantHarvest = plantingDatePrimaryPlantHarvest;
	}

	public String getPlantingDateSecondaryPlantPlanting() {
		return plantingDateSecondaryPlantPlanting;
	}

	public void setPlantingDateSecondaryPlantPlanting(String plantingDateSecondaryPlantPlanting) {
		this.plantingDateSecondaryPlantPlanting = plantingDateSecondaryPlantPlanting;
	}

	public String getPlantingDateSecondaryPlantHarvest() {
		return plantingDateSecondaryPlantHarvest;
	}

	public void setPlantingDateSecondaryPlantHarvest(String plantingDateSecondaryPlantHarvest) {
		this.plantingDateSecondaryPlantHarvest = plantingDateSecondaryPlantHarvest;
	}

	public String getPlantingDateFertilizerDate1() {
		return plantingDateFertilizerDate1;
	}

	public void setPlantingDateFertilizerDate1(String plantingDateFertilizerDate1) {
		this.plantingDateFertilizerDate1 = plantingDateFertilizerDate1;
	}

	public String getPlantingDateFertilizerDate2() {
		return plantingDateFertilizerDate2;
	}

	public void setPlantingDateFertilizerDate2(String plantingDateFertilizerDate2) {
		this.plantingDateFertilizerDate2 = plantingDateFertilizerDate2;
	}

	public String getPlantingDateFertilizerDate3() {
		return plantingDateFertilizerDate3;
	}

	public void setPlantingDateFertilizerDate3(String plantingDateFertilizerDate3) {
		this.plantingDateFertilizerDate3 = plantingDateFertilizerDate3;
	}

	public String getPlantingDateWeedingMonth1() {
		return plantingDateWeedingMonth1;
	}

	public void setPlantingDateWeedingMonth1(String plantingDateWeedingMonth1) {
		this.plantingDateWeedingMonth1 = plantingDateWeedingMonth1;
	}

	public String getPlantingDateWeedingMonth2() {
		return plantingDateWeedingMonth2;
	}

	public void setPlantingDateWeedingMonth2(String plantingDateWeedingMonth2) {
		this.plantingDateWeedingMonth2 = plantingDateWeedingMonth2;
	}

	public String getPlantingDateWeedingMonth3() {
		return plantingDateWeedingMonth3;
	}

	public void setPlantingDateWeedingMonth3(String plantingDateWeedingMonth3) {
		this.plantingDateWeedingMonth3 = plantingDateWeedingMonth3;
	}

}
