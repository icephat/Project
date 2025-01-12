package org.cassava.model;
// Generated Nov 30, 2022, 10:33:28 PM by Hibernate Tools 5.5.9.Final

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Field generated by hbm2java
 */
public class Field implements java.io.Serializable {

	private Integer fieldId;
	@JsonIgnore
	private Organization organization;
	@JsonIgnore
	private Subdistrict subdistrict;

	@JsonIgnore
	private User userByLastUpdateBy;   // NULL
	@JsonIgnore
	private User userByCreateBy;
	@Size(min = 2, max = 50, message = "ต้องมีความยาวตั้งแต่ 2 - 50 ตัวอักษร")
	private String code;
	@Size(min = 2, max = 100, message = "ต้องมีความยาวตั้งแต่ 2 - 100 ตัวอักษร")
	private String name;
	@NotNull (message = "ห้ามไม่มีค่า")
	@NotBlank(message = "ห้ามเว้นว่าง")
	@Size(min = 2, max = 100, message = "ต้องมีความยาวตั้งแต่ 2 - 100 ตัวอักษร")
	private String address;
	@Size(max = 30, message = "ต้องมีความยาวไม่เกิน 30 ตัวอักษร")
	private String road;
	@Size(max = 10, message = "ต้องมีความยาวไม่เกิน 10 ตัวอักษร")
	private String moo;
	private String landmark;
	@Digits(integer=12, fraction=7)
	private Float latitude;
	@Digits(integer=12, fraction=7)
	private Float longitude;
	private Float metresAboveSeaLv;
	@NotNull (message = "ห้ามไม่มีค่า")
	@Digits(integer=12, fraction=6)
	private float size;
	private String imgPath;
	private String status;
	private Date createDate;
	private Date lastUpdateDate;

	/* Not about model it many in relation */

	@JsonIgnore
	private List<UserInField> userinfields = new ArrayList<UserInField>();
	@JsonIgnore
	private List<Planting> plantings = new ArrayList<Planting>();

	public void clone(Field otherField) {
		this.code = otherField.getCode();
		this.name = otherField.getName();
		this.address = otherField.getAddress();
		this.road = otherField.getRoad();
		this.moo = otherField.getMoo();
		this.landmark = otherField.getLandmark();
		this.latitude = otherField.getLatitude();
		this.longitude = otherField.getLongitude();
		this.metresAboveSeaLv = otherField.getMetresAboveSeaLv();
		this.size = otherField.getSize();
		this.imgPath = otherField.getImgPath();
	}
	
	public Field() {
	}

	public Field(Organization organization, Subdistrict subdistrict, User userByCreateBy, String address,
			Float latitude, Float longitude, float size, String status, Date createDate) {
		this.organization = organization;
		this.subdistrict = subdistrict;
		this.userByCreateBy = userByCreateBy;
		this.address = address;
		this.latitude = latitude;
		this.longitude = longitude;
		this.size = size;
		this.status = status;
		this.createDate = createDate;
	}

	public Field(Organization organization, Subdistrict subdistrict, User userByLastUpdateBy, User userByCreateBy,
			String code, String name, String address, String road, String moo, String landmark, Float latitude,
			Float longitude, Float metresAboveSeaLv, float size, String imgPath, String status, Date createDate,
			Date lastUpdateDate, List userinfields, List plantings) {
		this.organization = organization;
		this.subdistrict = subdistrict;
		this.userByLastUpdateBy = userByLastUpdateBy;
		this.userByCreateBy = userByCreateBy;
		this.code = code;
		this.name = name;
		this.address = address;
		this.road = road;
		this.moo = moo;
		this.landmark = landmark;
		this.latitude = latitude;
		this.longitude = longitude;
		this.metresAboveSeaLv = metresAboveSeaLv;
		this.size = size;
		this.imgPath = imgPath;
		this.status = status;
		this.createDate = createDate;
		this.lastUpdateDate = lastUpdateDate;
		this.userinfields = userinfields;
		this.plantings = plantings;
	}

	public Integer getFieldId() {
		return this.fieldId;
	}

	public void setFieldId(Integer fieldId) {
		this.fieldId = fieldId;
	}

	public Organization getOrganization() {
		return this.organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Subdistrict getSubdistrict() {
		return this.subdistrict;
	}

	public void setSubdistrict(Subdistrict subdistrict) {
		this.subdistrict = subdistrict;
	}

	public User getUserByLastUpdateBy() {
		return this.userByLastUpdateBy;
	}

	public void setUserByLastUpdateBy(User userByLastUpdateBy) {
		this.userByLastUpdateBy = userByLastUpdateBy;
	}

	public User getUserByCreateBy() {
		return this.userByCreateBy;
	}

	public void setUserByCreateBy(User userByCreateBy) {
		this.userByCreateBy = userByCreateBy;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRoad() {
		return this.road;
	}

	public void setRoad(String road) {
		this.road = road;
	}

	public String getMoo() {
		return this.moo;
	}

	public void setMoo(String moo) {
		this.moo = moo;
	}

	public String getLandmark() {
		return this.landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public Float getLatitude() {
		return this.latitude;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	public Float getLongitude() {
		return this.longitude;
	}

	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}

	public Float getMetresAboveSeaLv() {
		return this.metresAboveSeaLv;
	}

	public void setMetresAboveSeaLv(Float metresAboveSeaLv) {
		this.metresAboveSeaLv = metresAboveSeaLv;
	}

	public float getSize() {
		return this.size;
	}

	public void setSize(float size) {
		this.size = size;
	}

	public String getImgPath() {
		return this.imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getLastUpdateDate() {
		return this.lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public List<UserInField> getUserinfields() {
		return this.userinfields;
	}

	public void setUserinfields(List userinfields) {
		this.userinfields = userinfields;
	}

	public List<Planting> getPlantings() {
		return this.plantings;
	}

	public void setPlantings(List plantings) {
		this.plantings = plantings;
	}

}
