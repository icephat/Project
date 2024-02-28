package se.springboot.sharitytest.model;
// Generated Feb 28, 2023, 7:05:30 PM by Hibernate Tools 5.6.3.Final

import java.util.HashSet;
import java.util.Set;

/**
 * User generated by hbm2java
 */
public class User implements java.io.Serializable {

	private Integer userId;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String status;
	private String role;
	private String address;
	private String skills;
	private Set activities = new HashSet(0);
	private Set joinActivities = new HashSet(0);
	private Set comments = new HashSet(0);
	private Set userReportsForUserIdReport = new HashSet(0);
	private Set userReportsForUserIdInReport = new HashSet(0);
	private Set reportComments = new HashSet(0);

	public User() {
	}

	public User(String username, String password, String firstName, String lastName, String status, String role,
			String address, String skills) {
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.status = status;
		this.role = role;
		this.address = address;
		this.skills = skills;
	}

	public User(String username, String password, String firstName, String lastName, String status, String role,
			String address, String skills, Set activities, Set joinactivities, Set comments,
			Set userreportsForUserIdReport, Set userreportsForUserIdInReport, Set reportcomments) {
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.status = status;
		this.role = role;
		this.address = address;
		this.skills = skills;
		this.activities = activities;
		this.joinActivities = joinactivities;
		this.comments = comments;
		this.userReportsForUserIdReport = userreportsForUserIdReport;
		this.setUserReportsForUserIdInReport(userreportsForUserIdInReport);
		this.reportComments = reportcomments;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSkills() {
		return this.skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public Set getActivities() {
		return this.activities;
	}

	public void setActivities(Set activities) {
		this.activities = activities;
	}

	public Set getJoinActivities() {
		return this.joinActivities;
	}

	public void setJoinActivities(Set joinactivities) {
		this.joinActivities = joinactivities;
	}

	public Set getComments() {
		return this.comments;
	}

	public void setComments(Set comments) {
		this.comments = comments;
	}

	public Set getUserReportsForUserIdReport() {
		return this.userReportsForUserIdReport;
	}

	public void setUserReportsForUserIdReport(Set userreportsForUserIdReport) {
		this.userReportsForUserIdReport = userreportsForUserIdReport;
	}

	public Set getReportComments() {
		return this.reportComments;
	}

	public void setReportComments(Set reportcomments) {
		this.reportComments = reportcomments;
	}

	public Set getUserReportsForUserIdInReport() {
		return userReportsForUserIdInReport;
	}

	public void setUserReportsForUserIdInReport(Set userReportsForUserIdInReport) {
		this.userReportsForUserIdInReport = userReportsForUserIdInReport;
	}

}
