package se.springboot.sharitytest.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import se.springboot.sharitytest.model.Activity;
import se.springboot.sharitytest.model.JoinActivity;
import se.springboot.sharitytest.model.login.UserLogin;
import se.springboot.sharitytest.service.JoinActivityService;

@Controller 

public class RequestHistoryController {
	
	@Autowired
	JoinActivityService joinActivityService;
	
	@RequestMapping(value = "/requesthistory", method = RequestMethod.GET)
	public String activityOpenIndex(Model model) {

		int userId = UserLogin.userLogin.getUserId();
		
		ArrayList<JoinActivity> joinActivities = (ArrayList<JoinActivity>) joinActivityService.findJoinActivityByUserId(userId);
		
		model.addAttribute("joinActivities", joinActivities);
		return "requesthistory/index";

	}
	
	@RequestMapping(value = "/deleteJoinActivity", method = RequestMethod.GET)
	public String deleteJoinActivity(@RequestParam("joinActivityId")int joinActivityId ,Model model) {

		joinActivityService.delete(joinActivityId);
		return "redirect:/requesthistory";

	}

}
