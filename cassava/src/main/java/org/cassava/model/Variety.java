package org.cassava.model;
// Generated Nov 30, 2022, 10:33:28 PM by Hibernate Tools 5.5.9.Final

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Variety generated by hbm2java
 */
public class Variety implements java.io.Serializable {

	private Integer varietyId;
	@JsonIgnore
	private Plant plant;
	@NotNull (message = "ห้ามไม่มีค่า")
	@NotBlank(message = "ห้ามเว้นว่าง")
	@Size(min = 2, max = 30, message = "ชื่อต้องมีความยาวตั้งแต่ 2 - 30 ตัวอักษร")
	private String name;
	private String trichome;
	private String apicalLeavesColor;
	private String youngLeavesColor;
	private String petioleColor;
	private String centralLeafletShape;
	private String branchingHabit;
	private String heightToFirstBranching;
	private String plantHeight;
	private String stemColor;
	private String starchContentRainySeason;
	private String starchContentDrySeason;
	private Float rootYield;
	private String mainCharacter;
	private String limitationNote;
	private String source;
	@JsonIgnore
	private List<PlantingCassavaVariety> plantingcassavavarieties = new ArrayList<PlantingCassavaVariety>();
	@JsonIgnore
	private List<ImgVariety> imgvarieties = new ArrayList<ImgVariety>();

	public Variety() {
	}

	public Variety(Plant plant, String name) {
		this.plant = plant;
		this.name = name;
	}

	public Variety(Plant plant, String name, String trichome, String apicalLeavesColor, String youngLeavesColor,
			String petioleColor, String centralLeafletShape, String branchingHabit, String heightToFirstBranching,
			String plantHeight, String stemColor, String starchContentRainySeason, String starchContentDrySeason,
			Float rootYield, String mainCharacter, String limitationNote, String source, List plantingcassavavarieties,
			List imgvarieties) {
		this.plant = plant;
		this.name = name;
		this.trichome = trichome;
		this.apicalLeavesColor = apicalLeavesColor;
		this.youngLeavesColor = youngLeavesColor;
		this.petioleColor = petioleColor;
		this.centralLeafletShape = centralLeafletShape;
		this.branchingHabit = branchingHabit;
		this.heightToFirstBranching = heightToFirstBranching;
		this.plantHeight = plantHeight;
		this.stemColor = stemColor;
		this.starchContentRainySeason = starchContentRainySeason;
		this.starchContentDrySeason = starchContentDrySeason;
		this.rootYield = rootYield;
		this.mainCharacter = mainCharacter;
		this.limitationNote = limitationNote;
		this.source = source;
		this.plantingcassavavarieties = plantingcassavavarieties;
		this.imgvarieties = imgvarieties;
	}

	public Integer getVarietyId() {
		return this.varietyId;
	}

	public void setVarietyId(Integer varietyId) {
		this.varietyId = varietyId;
	}

	public Plant getPlant() {
		return this.plant;
	}

	public void setPlant(Plant plant) {
		this.plant = plant;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTrichome() {
		return this.trichome;
	}

	public void setTrichome(String trichome) {
		this.trichome = trichome;
	}

	public String getApicalLeavesColor() {
		return this.apicalLeavesColor;
	}

	public void setApicalLeavesColor(String apicalLeavesColor) {
		this.apicalLeavesColor = apicalLeavesColor;
	}

	public String getYoungLeavesColor() {
		return this.youngLeavesColor;
	}

	public void setYoungLeavesColor(String youngLeavesColor) {
		this.youngLeavesColor = youngLeavesColor;
	}

	public String getPetioleColor() {
		return this.petioleColor;
	}

	public void setPetioleColor(String petioleColor) {
		this.petioleColor = petioleColor;
	}

	public String getCentralLeafletShape() {
		return this.centralLeafletShape;
	}

	public void setCentralLeafletShape(String centralLeafletShape) {
		this.centralLeafletShape = centralLeafletShape;
	}

	public String getBranchingHabit() {
		return this.branchingHabit;
	}

	public void setBranchingHabit(String branchingHabit) {
		this.branchingHabit = branchingHabit;
	}

	public String getHeightToFirstBranching() {
		return this.heightToFirstBranching;
	}

	public void setHeightToFirstBranching(String heightToFirstBranching) {
		this.heightToFirstBranching = heightToFirstBranching;
	}

	public String getPlantHeight() {
		return this.plantHeight;
	}

	public void setPlantHeight(String plantHeight) {
		this.plantHeight = plantHeight;
	}

	public String getStemColor() {
		return this.stemColor;
	}

	public void setStemColor(String stemColor) {
		this.stemColor = stemColor;
	}

	public String getStarchContentRainySeason() {
		return this.starchContentRainySeason;
	}

	public void setStarchContentRainySeason(String starchContentRainySeason) {
		this.starchContentRainySeason = starchContentRainySeason;
	}

	public String getStarchContentDrySeason() {
		return this.starchContentDrySeason;
	}

	public void setStarchContentDrySeason(String starchContentDrySeason) {
		this.starchContentDrySeason = starchContentDrySeason;
	}

	public Float getRootYield() {
		return this.rootYield;
	}

	public void setRootYield(Float rootYield) {
		this.rootYield = rootYield;
	}

	public String getMainCharacter() {
		return this.mainCharacter;
	}

	public void setMainCharacter(String mainCharacter) {
		this.mainCharacter = mainCharacter;
	}

	public String getLimitationNote() {
		return this.limitationNote;
	}

	public void setLimitationNote(String limitationNote) {
		this.limitationNote = limitationNote;
	}

	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public List<PlantingCassavaVariety> getPlantingcassavavarieties() {
		return this.plantingcassavavarieties;
	}

	public void setPlantingcassavavarieties(List plantingcassavavarieties) {
		this.plantingcassavavarieties = plantingcassavavarieties;
	}

	public List<ImgVariety> getImgvarieties() {
		return this.imgvarieties;
	}

	public void setImgvarieties(List imgvarieties) {
		this.imgvarieties = imgvarieties;
	}

}
