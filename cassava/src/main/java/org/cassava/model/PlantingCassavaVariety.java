package org.cassava.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

// Generated Nov 30, 2022, 10:33:28 PM by Hibernate Tools 5.5.9.Final

/**
 * Plantingcassavavariety generated by hbm2java
 */
public class PlantingCassavaVariety implements java.io.Serializable {

	private PlantingCassavaVarietyId id;
	@JsonIgnore
	private Planting planting;
	@JsonIgnore
	private Variety variety;

	public PlantingCassavaVariety() {
	}
	
	public PlantingCassavaVariety(PlantingCassavaVarietyId id) {
		this.id = id;
	}

	public PlantingCassavaVariety(PlantingCassavaVarietyId id, Planting planting, Variety variety) {
		this.id = id;
		this.planting = planting;
		this.variety = variety;
	}

	public PlantingCassavaVarietyId getId() {
		return this.id;
	}

	public void setId(PlantingCassavaVarietyId id) {
		this.id = id;
	}

	public Planting getPlanting() {
		return this.planting;
	}

	public void setPlanting(Planting planting) {
		this.planting = planting;
	}

	public Variety getVariety() {
		return this.variety;
	}

	public void setVariety(Variety variety) {
		this.variety = variety;
	}

}