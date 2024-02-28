package org.cassava.model.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.cassava.model.Disease;
import org.cassava.model.NaturalEnemy;
import org.cassava.model.Permission;
import org.cassava.model.PermissionFile;
import org.cassava.model.Pest;
import org.cassava.model.Province;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class PermissionDTO {

	private Date dateInfoStart;

	private Date dateInfoEnd;

	private List<Province> provinces;

	private List<Integer> diseases  = new ArrayList<Integer>();

	private List<Integer> pests = new ArrayList<Integer>();

	private List<Integer> naturalEnemies = new ArrayList<Integer>();

	private CommonsMultipartFile[] files;

	public List<Province> getProvinces() {
		return provinces;
	}

	public void setProvinces(List<Province> provinces) {
		this.provinces = provinces;
	}

	public List<Integer> getDiseases() {
		return diseases;
	}

	public void setDiseases(List<Integer> diseases) {
		this.diseases = diseases;
	}

	public Date getDateInfoStart() {
		return dateInfoStart;
	}

	public void setDateInfoStart(Date dateInfoStart) {
		this.dateInfoStart = dateInfoStart;
	}

	public Date getDateInfoEnd() {
		return dateInfoEnd;
	}

	public void setDateInfoEnd(Date dateInfoEnd) {
		this.dateInfoEnd = dateInfoEnd;
	}

	public CommonsMultipartFile[] getFiles() {
		return files;
	}

	public void setFiles(CommonsMultipartFile[] files) {
		this.files = files;
	}

	public List<Integer> getPests() {
		return pests;
	}

	public void setPests(List<Integer> pests) {
		this.pests = pests;
	}

	public List<Integer> getNaturalEnemies() {
		return naturalEnemies;
	}

	public void setNaturalEnemies(List<Integer> naturalEnemies) {
		this.naturalEnemies = naturalEnemies;
	}

}
