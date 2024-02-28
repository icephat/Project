package org.cassava.model.dto;

import java.util.ArrayList;

import org.cassava.model.Staff;

public class ImgSumaryDTO {

	private Staff staff;

	private int approved;

	private int rejected;

	private int reject;

	private int delete;

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public int getApproved() {
		return approved;
	}

	public void setApproved(int approved) {
		this.approved = approved;
	}

	public int getRejected() {
		return rejected;
	}

	public void setRejected(int rejected) {
		this.rejected = rejected;
	}

	public int getReject() {
		return reject;
	}

	public void setReject(int reject) {
		this.reject = reject;
	}

	public int getDelete() {
		return delete;
	}

	public void setDelete(int delete) {
		this.delete = delete;
	}

}
