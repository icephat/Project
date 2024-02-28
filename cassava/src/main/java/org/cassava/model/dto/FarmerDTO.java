package org.cassava.model.dto;

import org.cassava.model.Farmer;
import org.cassava.model.RegisterCode;
import org.cassava.model.Organization;

public class FarmerDTO {
	
	private Farmer farmer;
	
	private RegisterCode registercode;
	
	private Organization organization;

	public Farmer getFarmer() {
		return farmer;
	}

	public void setFarmer(Farmer farmer) {
		this.farmer = farmer;
	}

	public RegisterCode getRegistercode() {
		return registercode;
	}

	public void setRegistercode(RegisterCode registercode) {
		this.registercode = registercode;
	}
	
	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	
}
