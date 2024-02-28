package se.springboot.sharitytest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import se.springboot.sharitytest.model.Activity;
import se.springboot.sharitytest.model.JoinActivity;
import se.springboot.sharitytest.model.User;
import se.springboot.sharitytest.model.login.UserLogin;
import se.springboot.sharitytest.service.ActivityService;
import se.springboot.sharitytest.service.JoinActivityService;
import se.springboot.sharitytest.service.UserService;

@Controller
public class ProfileController {

	@Autowired
	UserService userService;
	
	@Autowired
	ActivityService activityService;
	
	@Autowired
	JoinActivityService joinActivityService;
	
	
	@RequestMapping(value="/profile", method = RequestMethod.GET)
	public String profileIndex(Model model) {

		User u = userService.findById(UserLogin.userLogin.getUserId());
		int join = 0;
		int act=0;
		List<JoinActivity> joinActivity = joinActivityService.findJoinActivityByUserId(UserLogin.userLogin.getUserId());
		List<Activity> activities = activityService.findByIdUser(UserLogin.userLogin.getUserId());
		for(JoinActivity j: joinActivity) {
			if(j.getStatus().equals("apporve")) {
				join++;
			}
		}
		
		for(Activity a: activities) {
			
			act++;
			
		}
		
		model.addAttribute("act", act);
		model.addAttribute("join", join);
		
		model.addAttribute("user", u);
		return "profile/index";
	}
	
}
