package se.springboot.sharitytest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import se.springboot.sharitytest.model.Activity;
import se.springboot.sharitytest.model.User;
import se.springboot.sharitytest.model.login.UserLogin;
import se.springboot.sharitytest.service.ActivityService;
import se.springboot.sharitytest.service.UserService;

@Controller
public class UserController {
	@Autowired
	UserService userService;
	@Autowired
	ActivityService activityService;
	@RequestMapping(value="/openedActivity", method = RequestMethod.GET)
	public String openedActivity(Model model) {
		List<Activity> activities = activityService.findByIdUser(UserLogin.userLogin.getUserId());
		model.addAttribute("user", UserLogin.userLogin);
		model.addAttribute("activities", activities);
		return "user/historyopenactivity";
	}
}
