package org.cassava.model;
// Generated Nov 30, 2022, 10:33:28 PM by Hibernate Tools 5.5.9.Final

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Staff generated by hbm2java
 */
public class Staff implements java.io.Serializable {

	private int staffId;
	@JsonIgnore
	private Organization organization;
	private User user;
	private String position;
	private String division;
	@JsonIgnore
	private List<Request> requestsForApproveBy = new ArrayList<Request>();
	@JsonIgnore
	private List<Request> requestsForApproveByDpphato = new ArrayList<Request>();
	@JsonIgnore
	private List<ApiStaff> apistaffs = new ArrayList<ApiStaff>();
	@JsonIgnore
	private List<Request> requestsForStaffId = new ArrayList<Request>();
	@JsonIgnore
	private List<Request> requestsForApproveByDae = new ArrayList<Request>();
	@JsonIgnore
	private List<Request> requestsForApproveByDa = new ArrayList<Request>();
	@JsonIgnore
	private List<Permission> permissionsForApproveBy = new ArrayList<Permission>();
	@JsonIgnore
	private List<Permission> permissionsForStaffId = new ArrayList<Permission>();
	@JsonIgnore
	private List<Token> tokens = new ArrayList<Token>();

	public Staff() {
	}

	public Staff(User user, String position, String division) {
		this.user = user;
		this.position = position;
		this.division = division;
	}
	
	public Staff(Organization organization,User user, String position, String division) {
		this.organization =organization;
		this.user = user;
		this.position = position;
		this.division = division;
	}

	public Staff(Organization organization, User user, String position, String division, List requestsForApproveBy,
			List requestsForApproveByDpphato, List apistaffs, List requestsForStaffId, List requestsForApproveByDae,
			List requestsForApproveByDa, List permissionsForApproveBy, List permissionsForStaffId, List tokens) {
		this.organization = organization;
		this.user = user;
		this.position = position;
		this.division = division;
		this.requestsForApproveBy = requestsForApproveBy;
		this.requestsForApproveByDpphato = requestsForApproveByDpphato;
		this.apistaffs = apistaffs;
		this.requestsForStaffId = requestsForStaffId;
		this.requestsForApproveByDae = requestsForApproveByDae;
		this.requestsForApproveByDa = requestsForApproveByDa;
		this.permissionsForApproveBy = permissionsForApproveBy;
		this.permissionsForStaffId = permissionsForStaffId;
		this.tokens = tokens;
	}

	public int getStaffId() {
		return this.staffId;
	}

	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}

	public Organization getOrganization() {
		return this.organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getDivision() {
		return this.division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public List<Request> getRequestsForApproveBy() {
		return this.requestsForApproveBy;
	}

	public void setRequestsForApproveBy(List requestsForApproveBy) {
		this.requestsForApproveBy = requestsForApproveBy;
	}

	public List<Request> getRequestsForApproveByDpphato() {
		return this.requestsForApproveByDpphato;
	}

	public void setRequestsForApproveByDpphato(List requestsForApproveByDpphato) {
		this.requestsForApproveByDpphato = requestsForApproveByDpphato;
	}

	public List<ApiStaff> getApistaffs() {
		return this.apistaffs;
	}

	public void setApistaffs(List apistaffs) {
		this.apistaffs = apistaffs;
	}

	public List<Request> getRequestsForStaffId() {
		return this.requestsForStaffId;
	}

	public void setRequestsForStaffId(List requestsForStaffId) {
		this.requestsForStaffId = requestsForStaffId;
	}

	public List<Request> getRequestsForApproveByDae() {
		return this.requestsForApproveByDae;
	}

	public void setRequestsForApproveByDae(List requestsForApproveByDae) {
		this.requestsForApproveByDae = requestsForApproveByDae;
	}

	public List<Request> getRequestsForApproveByDa() {
		return this.requestsForApproveByDa;
	}

	public void setRequestsForApproveByDa(List requestsForApproveByDa) {
		this.requestsForApproveByDa = requestsForApproveByDa;
	}

	public List<Permission> getPermissionsForApproveBy() {
		return this.permissionsForApproveBy;
	}

	public void setPermissionsForApproveBy(List permissionsForApproveBy) {
		this.permissionsForApproveBy = permissionsForApproveBy;
	}

	public List<Permission> getPermissionsForStaffId() {
		return this.permissionsForStaffId;
	}

	public void setPermissionsForStaffId(List permissionsForStaffId) {
		this.permissionsForStaffId = permissionsForStaffId;
	}

	public List<Token> getTokens() {
		return this.tokens;
	}

	public void setTokens(List tokens) {
		this.tokens = tokens;
	}

}
