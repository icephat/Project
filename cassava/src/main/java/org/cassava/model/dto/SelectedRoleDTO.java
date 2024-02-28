package org.cassava.model.dto;

import org.cassava.model.Role;

public class SelectedRoleDTO {
	
	private boolean checked;
	
	private Role role;

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	

}
