package se.springboot.sharitytest.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import se.springboot.sharitytest.model.UserReport;
import se.springboot.sharitytest.service.UserReportService;
import se.springboot.sharitytest.service.UserService;

@Controller
public class UserReportController {

	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserReportService userReportService;
	
	@RequestMapping(value = "/reportaccount",  method = RequestMethod.GET)
	public String reportAccountIndex(Model model) {
		ArrayList<UserReport> reportAccounts = (ArrayList<UserReport>) userReportService.findAll();
		model.addAttribute("reportAccounts", reportAccounts);
		return "reportaccount/index";
		
	}
	
	@RequestMapping(value = "/suspend", method = RequestMethod.GET)
	public String deleteInfo(@RequestParam(value = "reportId") int reportId, Model model) {

		UserReport userReport = userReportService.findById(reportId);
		userReport.getUserByUserIdInReport().setStatus("nonactivie");
		
		userReportService.delete(reportId);

		return "redirect:/reportaccount";

	}
	
	
	
}
