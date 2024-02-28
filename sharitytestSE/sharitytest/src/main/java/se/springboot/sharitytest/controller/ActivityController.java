package se.springboot.sharitytest.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import se.springboot.sharitytest.model.Activity;
import se.springboot.sharitytest.model.ConditionActivity;
import se.springboot.sharitytest.model.ConditionOfActivity;
import se.springboot.sharitytest.model.ImageActivity;
import se.springboot.sharitytest.model.TypeActivity;
import se.springboot.sharitytest.model.TypeOfActivity;
import se.springboot.sharitytest.model.dto.ActivityDTO;
import se.springboot.sharitytest.model.login.UserLogin;
import se.springboot.sharitytest.service.ActivityService;
import se.springboot.sharitytest.service.ConditionActivityService;
import se.springboot.sharitytest.service.ConditionOfActivityService;
import se.springboot.sharitytest.service.TypeActivityService;
import se.springboot.sharitytest.service.TypeOfActivityService;
import se.springboot.sharitytest.service.UserService;
import se.springboot.sharitytest.service.ImageActivityService;

@Controller
public class ActivityController {

	@Autowired
	ActivityService activityService;
	@Autowired
	UserService userService;
	@Autowired
	ConditionActivityService conditionActivityService;
	@Autowired
	TypeActivityService typeActivityService;
	@Autowired
	TypeOfActivityService typeOfActivityService;
	@Autowired
	ConditionOfActivityService conditionOfActivityService;
	@Autowired
	ImageActivityService imageActivityService;

	@RequestMapping(value = "/activity", method = RequestMethod.GET)
	public String activityIndex(Model model) {
		
		long millis = System.currentTimeMillis();
		ArrayList<Activity> activities = (ArrayList<Activity>) activityService.findByStatusANdDate("open", new Date(millis));

		model.addAttribute("activities", activities);
		return "activity/activityindex";

	}

	@RequestMapping(value = "/openactivity", method = RequestMethod.GET)
	public String activityOpen(Model model) {
		System.out.println(UserLogin.userLogin.getUsername());
		ArrayList<ConditionActivity> conditionActivities = (ArrayList<ConditionActivity>) conditionActivityService
				.findAll();
		ArrayList<TypeActivity> typeActivities = (ArrayList<TypeActivity>) typeActivityService.findAll();

		model.addAttribute("typeActivities", typeActivities);
		model.addAttribute("conditionActivities", conditionActivities);
		return "activity/openactivity";
	}

	@RequestMapping(value = "/confirm", method = RequestMethod.POST)
	public String save(@ModelAttribute("activityDTO") ActivityDTO activityDTO, Model model,
			@RequestParam("imageActivities") Set<MultipartFile> imageActivities)
			throws ParseException, IllegalStateException, IOException {
		// User u = userService.findByUsername(Activity.getUserTemp().getUsername());

		System.out.println(activityDTO.getNameActivity());

		Activity activity = new Activity();
		activity.setImageActivities(imageActivities);

		// System.out.println(activityDTO.getUser().getUserId());
		activity.setUser(UserLogin.userLogin);
		System.out.println(activity.getUser());
		long millis = System.currentTimeMillis();
		activity.setStatus("open");
		activity.setDateOpen(new java.sql.Date(millis));
		activity.setDateActivity(activityDTO.getDateActivity());
		activity.setDetail(activityDTO.getDetail());
		activity.setNameActivity(activityDTO.getNameActivity());
		activity.setPlace(activityDTO.getPlace());
		activity.setNumberPeople(activityDTO.getNumberPeople());
		activityService.save(activity);

		ArrayList<TypeActivity> typeActivities = (ArrayList<TypeActivity>) activityDTO.getTypeActivities();
		ArrayList<ConditionActivity> conditionActivities = (ArrayList<ConditionActivity>) activityDTO
				.getConditionActivities();

		for (TypeActivity typeActivity : typeActivities) {
			typeOfActivityService.save(new TypeOfActivity(activity, typeActivity));
		}

		for (ConditionActivity conditionActivity : conditionActivities) {
			conditionOfActivityService.save(new ConditionOfActivity(activity, conditionActivity, 0));
		}
		for (MultipartFile file : imageActivities) {
			ImageActivity imageActivity = new ImageActivity();
			/*
			 * String fileName = file.getOriginalFilename(); System.out.println(fileName);
			 * String filePath = fileName; file.transferTo(new File(
			 * "D:\\CPEKU\\SoftwareEng\\sharitytest\\src\\main\\resources\\static\\images\\uploads"+
			 * filePath));
			 */

			File folder = new File("D:\\CPEKU\\SoftwareEng\\sharitytest\\src\\main\\resources\\static\\images\\uploads");
			if (!folder.exists()) {
				folder.mkdirs();
			}

			UUID uuid = UUID.randomUUID();

			String filename = uuid.toString();

			String type = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);

			filename = filename + "." + type;

			String path = "D:\\CPEKU\\SoftwareEng\\sharitytest\\src\\main\\resources\\static\\images\\uploads\\" + filename;
			OutputStream outputStream = new FileOutputStream(path);
			outputStream.write(file.getBytes());
			outputStream.close();

			imageActivity.setImagePath(filename);
			imageActivity.setActivity(activity);
			imageActivityService.save(imageActivity);
		}

		return "activity/confirm";

	}

	@RequestMapping(value = "/change", method = RequestMethod.GET)
	public String change(@RequestParam("activityId") Integer activityId, Model model) {
		System.out.println(activityId);
		Activity a = activityService.findById(activityId);
		a.setStatus("close");
		activityService.save(a);
		model.addAttribute("user", UserLogin.userLogin);
		model.addAttribute("activity", a);
		return "user/confirmChange";
	}
	
	
}
