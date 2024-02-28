package org.cassava.web.controller;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import org.cassava.model.Role;
import org.cassava.model.User;
import org.cassava.model.dto.RoleDTO;
import org.cassava.model.dto.SelectedRoleDTO;
import org.cassava.services.RoleService;
import org.cassava.services.StaffService;
import org.cassava.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminController {

	@Autowired
	private UserService userService;

	@Autowired
	private StaffService staffService;

	@Autowired
	private RoleService roleService;

	@RequestMapping(value = { "/admin/index", "/admin/", "/admin" }, method = RequestMethod.GET)
	public String index(Model model) {

		List<Role> roles = roleService.findByUserDefine("Y");
		List<User> users = userService.findByUserDefine("Y");

		int[][] tableRole = new int[users.size()][roles.size()];
		for (int i = 0; i < tableRole.length; i++) {
			Role[] r = (Role[]) users.get(i).getRoles().toArray(new Role[0]);
			for (int j = 0; j < tableRole[i].length; j++) {
				for (int k = 0; k < r.length; k++) {
					if (roles.get(j).getNameEng().equals(r[k].getNameEng())) {
						tableRole[i][j] = 1;
					}
				}
			}
		}
		model.addAttribute("tableRole", tableRole);
		model.addAttribute("roles", roles);
		model.addAttribute("users", users);
		return "/admin/index";
	}

	@RequestMapping(value = "/admin/approve", method = RequestMethod.GET)
	public String approve(Model model) {
		model.addAttribute("staffs", staffService.findRegularStaffByValidStatus());
		return "/admin/approve";
	}

	@RequestMapping(value = "/admin/approve/{id}/create", method = RequestMethod.GET)
	public String staffDetail(@PathVariable("id") int id, Model model) {
		RoleDTO roleDTO = new RoleDTO();
		if (userService.findById(id) == null) {
			return "redirect:/admin/";
		}
		roleDTO.setUser(userService.findById(id));
		model.addAttribute("roleDTO1", roleDTO);
		model.addAttribute("roleList", staffService.findByUserRole(id));
		Format formatter = new SimpleDateFormat("d MMM yyyy", new Locale("th", "TH"));
		model.addAttribute("formatter", formatter);
		return "/admin/createapprove";

	}

	@RequestMapping(value = "/admin/update", method = RequestMethod.POST)
	public String update(@ModelAttribute("roleDTO") RoleDTO roleDTO, String status, Model model) throws Exception {

		User ur = userService.findById(roleDTO.getUser().getUserId());
		List<SelectedRoleDTO> roleselect = roleDTO.getRolecheck();
		for (SelectedRoleDTO selected : roleselect) {
			boolean count = true;
			Role role = roleService.findById(selected.getRole().getRoleId());
			List<Role> roleUser = ur.getRoles();
			List<User> userRole = role.getUsers();
			if (selected.isChecked()) {
				for (Role r : roleUser) {
					if (r.getRoleId() == role.getRoleId()) {
						count = false;
					}
				}
				if (count) {
					roleUser.add(role);
					userRole.add(ur);
				}
			} else {
				for (Role r : roleUser) {
					if (r.getRoleId() == selected.getRole().getRoleId()) {
						roleUser.remove(r);
						break;
					}
				}
				for (User uR : userRole) {
					if (uR.getUserId() == ur.getUserId()) {
						userRole.remove(uR);
						break;
					}
				}
			}
			roleService.save(role);
		}
		userService.save(ur);
		return "redirect:/admin/";
	}
}
