package org.cassava.model.dto;

import java.util.Date;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.cassava.model.Subdistrict;
import org.cassava.model.User;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class RegisterFarmerDTO {


	
	@NotBlank(message = "ห้ามเว้นว่าง")
	@Size(min = 2, max = 10, message = "ชื่อต้องมีความยาวตั้งแต่ 2 - 10 ตัวอักษร")
	private String title;

	@NotBlank(message = "ห้ามเว้นว่าง")
	@Size(min = 2, max = 20, message = "ชื่อต้องมีความยาวตั้งแต่ 2 - 20 ตัวอักษร")
	private String firstName;

	@NotBlank(message = "ห้ามเว้นว่าง")
	@Size(min = 2, max = 30, message = "ชื่อต้องมีความยาวตั้งแต่ 2 - 30 ตัวอักษร")
	private String lastName;

	@Digits(message = "ตัวเลขควรมี 10 หลัก", fraction = 0, integer = 10)
	private String phoneNo;
	
	private String code;
	
	public int getSubDistrictId() {
		return subDistrictId;
	}

	public void setSubDistrictId(int subDistrictId) {
		this.subDistrictId = subDistrictId;
	}

	@NotNull(message = "ห้ามไม่มีค่า")
	private int subDistrictId;
	
	@NotNull(message = "ห้ามไม่มีค่า")
	@NotBlank(message = "ห้ามเว้นว่าง")
	private String address;
	
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNo() {
		return this.phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}


	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	

}
