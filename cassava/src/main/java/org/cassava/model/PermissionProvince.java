package org.cassava.model;
// Generated Nov 30, 2022, 10:33:28 PM by Hibernate Tools 5.5.9.Final

/**
 * Permissionprovince generated by hbm2java
 */
public class PermissionProvince implements java.io.Serializable {

	private Integer permisisonProvinceId;
	private Permission permission;
	private Province province;

	public PermissionProvince() {
	}

	public PermissionProvince(Permission permission, Province province) {
		this.permission = permission;
		this.province = province;
	}

	public Integer getPermisisonProvinceId() {
		return this.permisisonProvinceId;
	}

	public void setPermisisonProvinceId(Integer permisisonProvinceId) {
		this.permisisonProvinceId = permisisonProvinceId;
	}

	public Permission getPermission() {
		return this.permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}

	public Province getProvince() {
		return this.province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

}