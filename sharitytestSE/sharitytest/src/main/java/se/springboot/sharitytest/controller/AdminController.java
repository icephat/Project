package se.springboot.sharitytest.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import se.springboot.sharitytest.model.User;
import se.springboot.sharitytest.service.UserService;

@Controller
public class AdminController {
	
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String adminIndex(Model model) {
		
		ArrayList<User> admins = (ArrayList<User>) userService.findByRole("admin");
		model.addAttribute("admins", admins);
		System.out.println(admins.size());
		
		return "admin/admin";
	}
	
	@RequestMapping(value = "/addadmin", method = RequestMethod.GET)
	public String addAdmin(Model model) {
		
		
		
		return "admin/manageAdmin";
	}
	
	@RequestMapping(value = "/infoadmin", method = RequestMethod.GET)
	public String adminInfo(@RequestParam(value = "adminId") int adminId, Model model) {

		User user = userService.findById(adminId);


		model.addAttribute("user", user);

		return "admin/infoadmin";

	}
	
	@RequestMapping(value="/regadmin", method = RequestMethod.POST)
	public String save(@ModelAttribute("user") User user,Model model) {
		
		user.setStatus("active");
		user.setRole("admin");
		userService.save(user);
		
		return "redirect:/admin";

	}
	
	@RequestMapping(value="/updateadmin", method = RequestMethod.POST)
	public String updateAdmin(@ModelAttribute("user") User user,Model model) {
		
		System.out.println(user.getUsername() + user.getUserId());
		User user2 = userService.findById(user.getUserId());
		
		user2.setFirstName(user.getFirstName());
		user2.setLastName(user.getLastName());
		user2.setAddress(user.getAddress());
		user2.setSkills(user.getSkills());
		
		
		userService.save(user2);
		
	
		return "redirect:/admin";

	}
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteInfo(@RequestParam(value = "userId") int userId, Model model) {

		
		userService.delete(userId);

		return "redirect:/admin";

	}
	
	
	
}
