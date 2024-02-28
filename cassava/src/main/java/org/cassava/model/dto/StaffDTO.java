package org.cassava.model.dto;

import org.cassava.model.RegisterCode;
import org.cassava.model.Staff;

public class StaffDTO {
	
	private Staff staff;
	
	private RegisterCode registercode;

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public RegisterCode getRegistercode() {
		return registercode;
	}

	public void setRegistercode(RegisterCode registercode) {
		this.registercode = registercode;
	}
}
