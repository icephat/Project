package org.cassava.model.dto;

import java.util.List;

import org.cassava.model.Role;
import org.cassava.model.Staff;
import org.cassava.model.User;

public class RoleDTO {
	private Role role;
	private User user;
	private List<SelectedRoleDTO> rolecheck;

	

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<SelectedRoleDTO> getRolecheck() {
		return rolecheck;
	}

	public void setRolecheck(List<SelectedRoleDTO> rolecheck) {
		this.rolecheck = rolecheck;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	

}
