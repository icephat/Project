package se.springboot.sharitytest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import se.springboot.sharitytest.model.User;
import se.springboot.sharitytest.service.UserService;

@Controller
public class RegisterController {

	@Autowired
	UserService userService;
	
	@RequestMapping(value="/register", method = RequestMethod.GET)
	public String index(Model model) {
		/*ArrayList<User> users = (ArrayList<User>) userService.findAll();
		
		model.addAttribute("users", users);*/
		return "register/register";

	}

	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String save(@ModelAttribute("user") User user,Model model) {
		
		
		
		System.out.println(user.getAddress()+" ............... ");
		user.setStatus("active");
		user.setRole("user");
		userService.save(user);
		
	
		return "register/alertcon";

	}
	
}
