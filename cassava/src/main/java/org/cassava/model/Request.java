package org.cassava.model;
// Generated Nov 30, 2022, 10:33:28 PM by Hibernate Tools 5.5.9.Final

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Request generated by hbm2java
 */
public class Request implements java.io.Serializable {

	private Integer requestId;
	private Staff staffByApproveBy;
	private Staff staffByApproveByDpphato;
	private Staff staffByStaffId;
	private Staff staffByApproveByDae;
	private Staff staffByApproveByDa;
	private String type;
	private Date dateRequest;
	private String requestNote;
	private String status;
	private Date dateApprove;
	private Date dateExpire;
	private String statusDae;
	private String statusDa;
	private String statusDppatho;
	private List<RequestFile> requestfiles = new ArrayList<RequestFile>();
	private List<RequestDetail> requestdetails = new ArrayList<RequestDetail>();

	public Request() {
	}

	public Request(Staff staffByStaffId, String type, Date dateRequest, String status) {
		this.staffByStaffId = staffByStaffId;
		this.type = type;
		this.dateRequest = dateRequest;
		this.status = status;
	}

	public Request(Staff staffByApproveBy, Staff staffByApproveByDpphato, Staff staffByStaffId,
			Staff staffByApproveByDae, Staff staffByApproveByDa, String type, Date dateRequest, String requestNote,
			String status, Date dateApprove, Date dateExpire, String statusDae, String statusDa, String statusDppatho,
			List requestfiles, List requestdetails) {
		this.staffByApproveBy = staffByApproveBy;
		this.staffByApproveByDpphato = staffByApproveByDpphato;
		this.staffByStaffId = staffByStaffId;
		this.staffByApproveByDae = staffByApproveByDae;
		this.staffByApproveByDa = staffByApproveByDa;
		this.type = type;
		this.dateRequest = dateRequest;
		this.requestNote = requestNote;
		this.status = status;
		this.dateApprove = dateApprove;
		this.dateExpire = dateExpire;
		this.statusDae = statusDae;
		this.statusDa = statusDa;
		this.statusDppatho = statusDppatho;
		this.requestfiles = requestfiles;
		this.requestdetails = requestdetails;
	}

	public Integer getRequestId() {
		return this.requestId;
	}

	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
	}

	public Staff getStaffByApproveBy() {
		return this.staffByApproveBy;
	}

	public void setStaffByApproveBy(Staff staffByApproveBy) {
		this.staffByApproveBy = staffByApproveBy;
	}

	public Staff getStaffByApproveByDpphato() {
		return this.staffByApproveByDpphato;
	}

	public void setStaffByApproveByDpphato(Staff staffByApproveByDpphato) {
		this.staffByApproveByDpphato = staffByApproveByDpphato;
	}

	public Staff getStaffByStaffId() {
		return this.staffByStaffId;
	}

	public void setStaffByStaffId(Staff staffByStaffId) {
		this.staffByStaffId = staffByStaffId;
	}

	public Staff getStaffByApproveByDae() {
		return this.staffByApproveByDae;
	}

	public void setStaffByApproveByDae(Staff staffByApproveByDae) {
		this.staffByApproveByDae = staffByApproveByDae;
	}

	public Staff getStaffByApproveByDa() {
		return this.staffByApproveByDa;
	}

	public void setStaffByApproveByDa(Staff staffByApproveByDa) {
		this.staffByApproveByDa = staffByApproveByDa;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getDateRequest() {
		return this.dateRequest;
	}

	public void setDateRequest(Date dateRequest) {
		this.dateRequest = dateRequest;
	}

	public String getRequestNote() {
		return this.requestNote;
	}

	public void setRequestNote(String requestNote) {
		this.requestNote = requestNote;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDateApprove() {
		return this.dateApprove;
	}

	public void setDateApprove(Date dateApprove) {
		this.dateApprove = dateApprove;
	}

	public Date getDateExpire() {
		return this.dateExpire;
	}

	public void setDateExpire(Date dateExpire) {
		this.dateExpire = dateExpire;
	}

	public String getStatusDae() {
		return this.statusDae;
	}

	public void setStatusDae(String statusDae) {
		this.statusDae = statusDae;
	}

	public String getStatusDa() {
		return this.statusDa;
	}

	public void setStatusDa(String statusDa) {
		this.statusDa = statusDa;
	}

	public String getStatusDppatho() {
		return this.statusDppatho;
	}

	public void setStatusDppatho(String statusDppatho) {
		this.statusDppatho = statusDppatho;
	}

	public List<RequestFile> getRequestfiles() {
		return this.requestfiles;
	}

	public void setRequestfiles(List requestfiles) {
		this.requestfiles = requestfiles;
	}

	public List<RequestDetail> getRequestdetails() {
		return this.requestdetails;
	}

	public void setRequestdetails(List requestdetails) {
		this.requestdetails = requestdetails;
	}

}
