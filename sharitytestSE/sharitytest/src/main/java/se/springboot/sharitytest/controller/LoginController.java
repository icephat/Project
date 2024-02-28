package se.springboot.sharitytest.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import se.springboot.sharitytest.model.Activity;
import se.springboot.sharitytest.model.User;
import se.springboot.sharitytest.model.login.UserLogin;
import se.springboot.sharitytest.service.UserService;

@Controller
public class LoginController {

	@Autowired
	UserService userService;

	String alert = "";

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String index(Model model) {

		model.addAttribute("alert", alert);
		return "login/login";
	}

	@RequestMapping(value="/home", method = RequestMethod.POST)
	public String checkUser(@ModelAttribute("user") User user,Model model ) {
		
		ArrayList<User> users = (ArrayList<User>) userService.findAll();
		boolean check = false;
		for(User use: users) {
			if(use.getUsername().equals(user.getUsername())) {
				check = true;
				break;
			}
		}
		
		
		
		
		
		if(check) {
			String username = user.getUsername();
			String password = user.getPassword();

			User u = userService.findByUsername(username);
			UserLogin.userLogin = u;
			System.out.println(UserLogin.userLogin.getUsername());
			
			if(u.getStatus().equals("active")) {
				if (u.getRole().equals("admin")) {
					if (u.getPassword().equals(password)) {
						System.out.println("pass");
						model.addAttribute("user", u);
						return "admin/home";
					}
				} else {
					if (u != null) {
						System.out.println("have");

						if (u.getPassword().equals(password)) {
							System.out.println("pass");
							// Activity.setUserTemp(user);
							model.addAttribute("user", u);
							return "home/home";
						} else {

							System.out.println("not");

						}

					} else {
						System.out.println("don't have");

					}
				}
			}
		}
		
		
		
		
		alert = "กรุณาตรวจสอบข้อมูลใหม่";
		
		model.addAttribute("alert", alert);
		return "redirect:/login";
		
	}

}
