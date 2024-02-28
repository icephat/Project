package org.cassava.model.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.cassava.model.Disease;
import org.cassava.model.District;
import org.cassava.model.Province;
import org.cassava.model.Subdistrict;

public class MessageDTO {

	private Province province;

	private District district;
	
	private Integer districtId;
	private Integer provinceId;
	
	private List<Integer> subdistrict;
	
	private List<Integer> diseases;

	private String level;
	
	private String title;
	private String text;

	

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	

	public List<Integer> getDiseases() {
		return diseases;
	}

	public void setDiseases(List<Integer> diseases) {
		this.diseases = diseases;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	public List<Integer> getSubdistrict() {
		return subdistrict;
	}

	public void setSubdistrict(List<Integer> subdistrict) {
		this.subdistrict = subdistrict;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}

	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	
	
	

}
