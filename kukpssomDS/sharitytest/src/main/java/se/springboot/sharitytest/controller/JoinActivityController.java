package se.springboot.sharitytest.controller;

import java.sql.Date;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import se.springboot.sharitytest.model.Activity;
import se.springboot.sharitytest.model.Comment;
import se.springboot.sharitytest.model.ConditionActivity;
import se.springboot.sharitytest.model.JoinActivity;
import se.springboot.sharitytest.model.dto.JoinActivityDTO;
import se.springboot.sharitytest.model.login.UserLogin;
import se.springboot.sharitytest.service.ActivityService;
import se.springboot.sharitytest.service.CommentService;
import se.springboot.sharitytest.service.ConditionActivityService;
import se.springboot.sharitytest.service.JoinActivityService;
import se.springboot.sharitytest.model.dto.CommentDTO;

@Controller
public class JoinActivityController {

	@Autowired
	private ActivityService activityService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private ConditionActivityService conditionActivityService;
	@Autowired
	private JoinActivityService joinActivityService;

	/*@RequestMapping(value = "/activity", method = RequestMethod.GET)
	public String activityIndex(Model model) {

		ArrayList<Activity> activities = (ArrayList<Activity>) activityService.findAll();

		model.addAttribute("activities", activities);
		return "activity/activityindex";

	}*/
	
	@RequestMapping(value="/activitydetail",method = RequestMethod.GET)
	public String activityDetail(@RequestParam("activityId")int activityId,Model model) {
		
		Activity activity = activityService.findById(activityId);
		ArrayList<Comment> comments = (ArrayList<Comment>) commentService.getCommentsByActivityId(activityId);
		ArrayList<JoinActivity> joinActivities = (ArrayList<JoinActivity>) joinActivityService.findJoinActivityByActivityId(activityId, "apporve");
		int joinAmount = joinActivities.size();
		boolean join = false;
		for(JoinActivity joinActivity : joinActivities) {
			if(joinActivity.getUser().equals(UserLogin.userLogin)) {
				join = true;
				break;
			}
		}
		
		
		
		System.out.println(activity.getConditionOfActivities().size());
		
		model.addAttribute("join", join);
		model.addAttribute("joinAmount", joinAmount);
		model.addAttribute("activity", activity);
		model.addAttribute("user",UserLogin.userLogin);
		model.addAttribute("comments", comments);
		
		return "activity/detail";
	}
	
	@RequestMapping(value="/postcomment", method = RequestMethod.POST)
	public String postComment(@ModelAttribute("commentDTO") CommentDTO commentDTO, Model model) {
	    Comment comment = new Comment();
	    
	    Activity activity = activityService.findById(commentDTO.getActivity());
	    
	    comment.setActivity(activity);
	    comment.setUser(UserLogin.userLogin);
	    comment.setDetail(commentDTO.getDetail());
	    commentService.save(comment);
	    
	    return "redirect:/activitydetail"+"?activityId=" + commentDTO.getActivity();
	}

	@RequestMapping(value="/joinactivity",method = RequestMethod.POST)
	public String joinActivity(@ModelAttribute("joinActivityDTO")JoinActivityDTO joinActivityDTO,Model model) {
		JoinActivity joinActivity = new JoinActivity();
		
		Activity activity = activityService.findById(joinActivityDTO.getActivity());
		ConditionActivity conditionActivity = conditionActivityService.findById(joinActivityDTO.getConditionActivity());
		joinActivity.setActivity(activity);
		joinActivity.setUser(UserLogin.userLogin);
		joinActivity.setConditionActivity(conditionActivity);
		joinActivity.setStatus("waiting");
		joinActivity.setDetailRequest(joinActivityDTO.getDetailRequest());
		long millis = System.currentTimeMillis();
		joinActivity.setDateRequest(new Date(millis));
		
		joinActivityService.save(joinActivity);
		
	
		
		return "redirect:/activitydetail" + "?activityId=" + joinActivityDTO.getActivity();
	}
}
