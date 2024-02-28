package org.cassava.model;
// Generated Nov 30, 2022, 10:33:28 PM by Hibernate Tools 5.5.9.Final

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Role generated by hbm2java
 */
public class Role implements java.io.Serializable {

	private Integer roleId;
	private String nameEng;
	private String nameTh;
	private String userDefine;
	@JsonIgnore
	private List<User> users = new ArrayList<User>();

	public Role() {
	}

	public Role(String nameEng, String nameTh, String userDefine) {
		this.nameEng = nameEng;
		this.nameTh = nameTh;
		this.userDefine = userDefine;
	}

	public Role(String nameEng, String nameTh, String userDefine, List users) {
		this.nameEng = nameEng;
		this.nameTh = nameTh;
		this.userDefine = userDefine;
		this.users = users;
	}

	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getNameEng() {
		return this.nameEng;
	}

	public void setNameEng(String nameEng) {
		this.nameEng = nameEng;
	}

	public String getNameTh() {
		return this.nameTh;
	}

	public void setNameTh(String nameTh) {
		this.nameTh = nameTh;
	}

	public String getUserDefine() {
		return this.userDefine;
	}

	public void setUserDefine(String userDefine) {
		this.userDefine = userDefine;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List users) {
		this.users = users;
	}

}