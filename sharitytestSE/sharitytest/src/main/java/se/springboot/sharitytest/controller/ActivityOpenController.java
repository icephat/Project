package se.springboot.sharitytest.controller;

import java.util.ArrayList;
import java.util.Date;

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
public class ActivityOpenController {

	@Autowired
	private ActivityService activityService;

	@Autowired
	private JoinActivityService joinActivityService;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/activityopen", method = RequestMethod.GET)
	public String activityOpenIndex(Model model) {

		ArrayList<Activity> activities = (ArrayList<Activity>) activityService.findByStatusAndUserId("open",
				UserLogin.userLogin.getUserId());
		model.addAttribute("activities", activities);
		return "activityopen/index";

	}

	@RequestMapping(value = "/deleteactiviy", method = RequestMethod.GET)
	public String deleteActivity(@RequestParam("activityId") Integer activityId, Model model) {
		System.out.println(activityId);
		activityService.delete(activityId);
		return "redirect:/activityopen";
	}

	@RequestMapping(value = "/request", method = RequestMethod.GET)
	public String activityOpenRequest(@RequestParam(value = "activityId") int activityId, Model model) {

		Activity activity = activityService.findById(activityId);

		ArrayList<JoinActivity> joinActivities = (ArrayList<JoinActivity>) joinActivityService
				.findJoinActivityByActivityId(activity.getActivityId(), "waiting");

		for (JoinActivity j : joinActivities) {

			System.out.println(j.getUser().getUsername() + "   name    ");
		}

		model.addAttribute("joinActivities", joinActivities);

		return "activityopen/request";

	}

	@RequestMapping(value = "/approve", method = RequestMethod.GET)
	public String requestApprove(@RequestParam(value = "joinActivityId") int joinActivityId, Model model) {

		JoinActivity joinActivity = joinActivityService.findById(joinActivityId);

		ArrayList<JoinActivity> joinActivities = (ArrayList<JoinActivity>) joinActivityService
				.findJoinActivityByActivityId(joinActivity.getActivity().getActivityId(), "apporve");

		ArrayList<User> users = new ArrayList<User>();
		for (JoinActivity j : joinActivities) {

			System.out.println(j.getUser().getUsername() + "   name    ");
			users.add(j.getUser());
		}

		System.out.println(users.size() + " size");

		if (users.size() < joinActivity.getActivity().getNumberPeople()) {
			joinActivity.setStatus("apporve");
		}

		joinActivity.setDateApprove(new Date());

		joinActivityService.save(joinActivity);

		int act = joinActivity.getActivity().getActivityId();

		return "redirect:/request?activityId=" + act;

	}

	@RequestMapping(value = "/remove", method = RequestMethod.GET)
	public String requestRemove(@RequestParam(value = "joinActivityId") int joinActivityId, Model model) {

		JoinActivity joinActivity = joinActivityService.findById(joinActivityId);

		joinActivity.setStatus("reject");
		joinActivity.setDateApprove(null);
		joinActivityService.save(joinActivity);
		int act = joinActivity.getActivity().getActivityId();

		return "redirect:/request?activityId=" + act;

	}

	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public String requestInfo(@RequestParam(value = "joinActivityId") int joinActivityId, Model model) {

		JoinActivity joinActivity = joinActivityService.findById(joinActivityId);
		System.out.println(joinActivity.getUser().getFirstName() + " name ++++++ ");
		System.out.println();

		User user = userService.findById(joinActivity.getUser().getUserId());

		model.addAttribute("joinActivity", joinActivity);
		model.addAttribute("user", user);
		System.out.println(user.getFirstName());

		return "activityopen/info";
	}

	@RequestMapping(value = "/userinactivity", method = RequestMethod.GET)
	public String userInActivity(@RequestParam(value = "activityId") int activityId, Model model) {

		Activity activity = activityService.findById(activityId);

		ArrayList<JoinActivity> joinActivities = (ArrayList<JoinActivity>) joinActivityService
				.findJoinActivityByActivityId(activity.getActivityId(), "apporve");

		ArrayList<User> users = new ArrayList<User>();

		for (JoinActivity j : joinActivities) {

			System.out.println(j.getUser().getUsername() + "   name    ");
			users.add(j.getUser());
		}

		model.addAttribute("users", users);
		model.addAttribute("joinActivities", joinActivities);

		return "activityopen/userinactivity";
	}

	@RequestMapping(value = "/infouserinactivity", method = RequestMethod.GET)
	public String infoUserInActivity(@RequestParam(value = "joinActivityId") int joinActivityId, Model model) {

		JoinActivity joinActivity = joinActivityService.findById(joinActivityId);
		System.out.println(joinActivity.getUser().getFirstName() + " name ++++++ ");
		System.out.println();

		User user = userService.findById(joinActivity.getUser().getUserId());

		model.addAttribute("joinActivity", joinActivity);
		model.addAttribute("user", user);
		System.out.println(user.getFirstName());

		return "activityopen/infouserinactivity";
	}

	@RequestMapping(value = "/removeuserinactivity", method = RequestMethod.GET)
	public String requestRemoveUserInActivity(@RequestParam(value = "joinActivityId") int joinActivityId, Model model) {

		JoinActivity joinActivity = joinActivityService.findById(joinActivityId);

		joinActivity.setStatus("reject");
		joinActivity.setDateApprove(null);

		joinActivityService.save(joinActivity);
		int act = joinActivity.getActivity().getActivityId();

		return "redirect:/userinactivity?activityId=" + act;

	}

}
