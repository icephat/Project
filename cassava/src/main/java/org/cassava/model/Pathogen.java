package org.cassava.model;
// Generated Nov 30, 2022, 10:33:28 PM by Hibernate Tools 5.5.9.Final

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Pathogen generated by hbm2java
 */
public class Pathogen implements java.io.Serializable {

	private Integer pathogenId;
	@JsonIgnore
	private PathogenType pathogentype;
	@NotBlank(message = "ห้ามเว้นว่าง")
	@Size(min = 2, max = 70, message = "ชื่อต้องมีความยาวตั้งแต่ 2 - 70 ตัวอักษร")
	private String scientificName;
	@JsonIgnore
	private List<Disease> diseases = new ArrayList<Disease>();

	public Pathogen() {
	}

	public Pathogen(PathogenType pathogentype, String scientificName) {
		this.pathogentype = pathogentype;
		this.scientificName = scientificName;
	}

	public Pathogen(PathogenType pathogentype, String scientificName, List diseases) {
		this.pathogentype = pathogentype;
		this.scientificName = scientificName;
		this.diseases = diseases;
	}

	public Integer getPathogenId() {
		return this.pathogenId;
	}

	public void setPathogenId(Integer pathogenId) {
		this.pathogenId = pathogenId;
	}

	public PathogenType getPathogentype() {
		return this.pathogentype;
	}

	public void setPathogentype(PathogenType pathogentype) {
		this.pathogentype = pathogentype;
	}

	public String getScientificName() {
		return this.scientificName;
	}

	public void setScientificName(String scientificName) {
		this.scientificName = scientificName;
	}

	public List<Disease> getDiseases() {
		return this.diseases;
	}

	public void setDiseases(List diseases) {
		this.diseases = diseases;
	}

}