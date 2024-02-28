package org.cassava.web.controller;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import org.cassava.model.Farmer;
import org.cassava.model.Role;
import org.cassava.model.Staff;
import org.cassava.model.User;
import org.cassava.services.FarmerService;
import org.cassava.services.StaffService;
import org.cassava.services.UserService;
import org.cassava.util.MvcHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ProfileController {

	@Autowired
	private UserService userService;

	@Autowired
	private StaffService staffService;

	@Autowired
	private FarmerService farmerService;

	@RequestMapping(value = "/profile/index", method = RequestMethod.GET)
	public String profile(Model model, Authentication authentication) {

		String username = MvcHelper.getUsername(authentication);

		User user = userService.findByUsername(username);

		Farmer farmer = farmerService.findByUsername(username);

		Staff staff = staffService.findByUserName(username);

		if (farmer != null) {

			model.addAttribute("farmer", farmer);
			model.addAttribute("role", "farmer");
		} else if (staff != null) {
			model.addAttribute("staff", staff);
			model.addAttribute("role", "staff");
			model.addAttribute("roles", user.getRoles());
		}

		Format formatter = new SimpleDateFormat("d MMM yyyy", new Locale("th", "TH"));
		model.addAttribute("formatter", formatter);

		model.addAttribute("user", user);

		return "profile/profile";
	}
}
