package org.cassava.model.dto;

public class FieldSearchDTO {
	protected String fieldName;
	protected String ownerName;
	protected String address;
	
	public FieldSearchDTO() {
		
	}

	public FieldSearchDTO(String fieldName, String ownerName, String address) {
		super();
		this.fieldName = fieldName;
		this.ownerName = ownerName;
		this.address = address;
	}
	
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	
}
