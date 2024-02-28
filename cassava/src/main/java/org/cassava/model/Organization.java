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
 * Organization generated by hbm2java
 */
public class Organization implements java.io.Serializable {

	private Integer organizationId;
	
	@NotNull(message = "ห้ามไม่มีค่า")
	@NotBlank(message = "ห้ามเว้นว่าง")
	@Size(min = 2, max = 32, message = "ชื่อต้องมีความยาวตั้งแต่ 2 - 32 ตัวอักษร")
	private String name;
	
	@NotNull(message = "ห้ามไม่มีค่า")
	@NotEmpty(message = "ห้ามเว้นว่าง")
	@Digits(message="ต้องเป็นตัวเลขห้ามมีทศนิยม", fraction = 0, integer = 10)
	@Size(min = 9, max = 10, message = "ความยาว 9-10 ตัว")
	private String phone;
	
	@NotNull(message = "ห้ามไม่มีค่า")
	@NotBlank(message = "ห้ามเว้นว่าง")
	@Size(min = 2, max = 10, message = "ต้องมีความยาวตั้งแต่ 2 - 10 ตัวอักษร")
	private String code;
	
	private String accessInfoLevel;
	@JsonIgnore
	private List<Staff> staffs = new ArrayList<Staff>();
	@JsonIgnore
	private List<Farmer> farmers = new ArrayList<Farmer>();
	@JsonIgnore
	private List<Field> fields = new ArrayList<Field>();
	@JsonIgnore
	private List<RegisterCode> registercodes = new ArrayList<RegisterCode>();
	@JsonIgnore
	private List<PermissionOrganization> permissionOrganizations = new ArrayList<PermissionOrganization>();
	
	public Organization() {
	}

	public Organization(String name, String code, String accessInfoLevel) {
		this.name = name;
		this.code = code;
		this.accessInfoLevel = accessInfoLevel;
	}

	public Organization(String name, String phone, String code, String accessInfoLevel, List staffs, List farmers,
			List fields, List registercodes) {
		this.name = name;
		this.phone = phone;
		this.code = code;
		this.accessInfoLevel = accessInfoLevel;
		this.staffs = staffs;
		this.farmers = farmers;
		this.fields = fields;
		this.registercodes = registercodes;
	}

	public Integer getOrganizationId() {
		return this.organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAccessInfoLevel() {
		return this.accessInfoLevel;
	}

	public void setAccessInfoLevel(String accessInfoLevel) {
		this.accessInfoLevel = accessInfoLevel;
	}

	public List<Staff> getStaffs() {
		return this.staffs;
	}

	public void setStaffs(List staffs) {
		this.staffs = staffs;
	}

	public List<Farmer> getFarmers() {
		return this.farmers;
	}

	public void setFarmers(List farmers) {
		this.farmers = farmers;
	}

	public List<Field> getFields() {
		return this.fields;
	}

	public void setFields(List fields) {
		this.fields = fields;
	}

	public List<RegisterCode> getRegistercodes() {
		return this.registercodes;
	}

	public void setRegistercodes(List registercodes) {
		this.registercodes = registercodes;
	}

	public List<PermissionOrganization> getPermissionOrganizations() {
		return permissionOrganizations;
	}

	public void setPermissionOrganizations(List<PermissionOrganization> permissionOrganizations) {
		this.permissionOrganizations = permissionOrganizations;
	}
	
	

}
