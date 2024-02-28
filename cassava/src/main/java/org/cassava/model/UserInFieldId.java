package org.cassava.model;
// Generated Nov 30, 2022, 10:33:28 PM by Hibernate Tools 5.5.9.Final

/**
 * UserinfieldId generated by hbm2java
 */
public class UserInFieldId implements java.io.Serializable {

	private int userId;
	private int fieldId;
	private String role;

	public UserInFieldId() {
	}

	public UserInFieldId(int userId, int fieldId, String role) {
		this.userId = userId;
		this.fieldId = fieldId;
		this.role = role;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getFieldId() {
		return this.fieldId;
	}

	public void setFieldId(int fieldId) {
		this.fieldId = fieldId;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof UserInFieldId))
			return false;
		UserInFieldId castOther = (UserInFieldId) other;

		return (this.getUserId() == castOther.getUserId()) && (this.getFieldId() == castOther.getFieldId())
				&& ((this.getRole() == castOther.getRole()) || (this.getRole() != null && castOther.getRole() != null
						&& this.getRole().equals(castOther.getRole())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getUserId();
		result = 37 * result + this.getFieldId();
		result = 37 * result + (getRole() == null ? 0 : this.getRole().hashCode());
		return result;
	}

}