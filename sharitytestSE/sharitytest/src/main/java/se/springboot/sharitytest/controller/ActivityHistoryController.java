package se.springboot.sharitytest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import se.springboot.sharitytest.model.ConditionActivity;
import se.springboot.sharitytest.model.JoinActivity;
import se.springboot.sharitytest.model.TypeActivity;
import se.springboot.sharitytest.model.User;
import se.springboot.sharitytest.model.UserReport;
import se.springboot.sharitytest.model.dto.JoinActivityDTO;
import se.springboot.sharitytest.model.dto.ReportAccountDTO;
import se.springboot.sharitytest.model.login.UserLogin;
import se.springboot.sharitytest.service.ActivityHistoryService;
import se.springboot.sharitytest.service.JoinActivityService;
import se.springboot.sharitytest.service.UserReportService;
import se.springboot.sharitytest.service.UserService;

@Controller
public class ActivityHistoryController {

	@Autowired
	ActivityHistoryService activityHistoryService;

	@Autowired
	UserService userService;

	@Autowired
	UserReportService userReportService;
	
	@Autowired
	JoinActivityService joinActivityService;

	String alert = "";
	@RequestMapping(value = "/activityhistory", method = RequestMethod.GET)
	public String activityhistory(Model model) {
		alert = "";
		System.out.println(UserLogin.userLogin.getUserId());
		ArrayList<JoinActivity> activityhistorys = (ArrayList<JoinActivity>) activityHistoryService
				.findByUsername(UserLogin.userLogin.getUserId());
		List<List<Object>> listofuser = activityHistoryService.getActivityUserList();
		
		model.addAttribute("userLog", UserLogin.userLogin);
		model.addAttribute("listofuser", listofuser);
		model.addAttribute("activityhistorys", activityhistorys);
		return "activityhistory/activityhistory";

	}

	@RequestMapping(value = "/reportAccount", method = RequestMethod.GET)
	public String reportAccount(@RequestParam(value = "userId") int userId, Model model) {

		User user = userService.findById(userId);
		model.addAttribute("alert", alert);
		model.addAttribute("user", user);

		return "activityhistory/reportaccount";

	}

	@RequestMapping(value = "/report", method = RequestMethod.POST)
	public String report(@ModelAttribute("reportAccountDTO") ReportAccountDTO reportAccountDTO, Model model) {

		UserReport userReport = new UserReport();
		User user = userService.findById(reportAccountDTO.getUserId());
		if (reportAccountDTO.getDetail().equals("")) {
			alert = "โปรดระบุเหตุผลของการรายงาน";
			model.addAttribute("alert", alert);
			model.addAttribute("user", user);
			return "activityhistory/reportaccount";
		}
		
		userReport.setDetail(reportAccountDTO.getDetail());
		userReport.setUserByUserIdReport(UserLogin.userLogin);
		userReport.setUserByUserIdInReport(user);
		
		userReportService.save(userReport);
		return "redirect:/activityhistory";

	}
	
	@RequestMapping(value = "/estimate", method = RequestMethod.GET)
	public String callestimate(@RequestParam("activityId")Integer activityId,Model model) {
		System.out.println(UserLogin.userLogin.getUsername());
		model.addAttribute("activityId", activityId);
		model.addAttribute("user", UserLogin.userLogin);
		return "activityhistory/estimate";
	}
	
	@RequestMapping(value = "/estimateConfirm", method = RequestMethod.POST)
	public String estimate(@ModelAttribute("JoinActivityDTO") JoinActivityDTO joinActivityDTO, Model model) {
		
		System.out.println(UserLogin.userLogin.getUsername());
		System.out.println(joinActivityDTO.getScore());
		System.out.println(joinActivityDTO.getComment());
		System.out.println(joinActivityDTO.getActivity());
		JoinActivity joinActivity = joinActivityService.findJoinActivityByUserIdAndActivityId(UserLogin.userLogin.getUserId(), joinActivityDTO.getActivity());
		joinActivity.setScore(joinActivityDTO.getScore());
		joinActivity.setComment(joinActivityDTO.getComment());
		joinActivityService.save(joinActivity);
		
		return "redirect:/activityhistory";

	}

}
