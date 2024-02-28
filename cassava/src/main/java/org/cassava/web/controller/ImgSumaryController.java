package org.cassava.web.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.cassava.model.Staff;
import org.cassava.model.User;
import org.cassava.model.dto.ImgSumaryDTO;
import org.cassava.services.ImgSurveyTargetPointService;
import org.cassava.services.StaffService;
import org.cassava.services.UserService;
import org.cassava.util.MvcHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ImgSumaryController {

	@Autowired
	private ImgSurveyTargetPointService imgSurveyTargetPointService;
	@Autowired
	private StaffService staffService;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/export/imagesumary", method = RequestMethod.GET)
	public String sumaryIndex(Model model) {

		LocalDate currentdate = LocalDate.now();

		ArrayList<Integer> years = new ArrayList<Integer>();

		for (int i = LocalDate.now().getYear(); i >= imgSurveyTargetPointService
				.findMinYearInImgSurveyTargetPoint(); i--) {
			years.add(i);
		}

		model.addAttribute("years", years);
		model.addAttribute("month", currentdate.getMonth().getValue());
		model.addAttribute("year", currentdate.getYear());
		model.addAttribute("maxSize", 100);
		return "export/sumary";

	}

	@RequestMapping(value = "/export/imagesumary/workstatistics", method = RequestMethod.POST)
	public String sumaryWorking(Model model, @RequestParam("months") List<Integer> months,
			@RequestParam("year") int year) {

		ArrayList<Object> objs = new ArrayList<Object>();

		ArrayList<Integer> years = new ArrayList<Integer>();

		for (int i = LocalDate.now().getYear(); i >= imgSurveyTargetPointService
				.findMinYearInImgSurveyTargetPoint(); i--) {
			years.add(i);
		}

		ArrayList<ImgSumaryDTO> imgSumaryDTOs = new ArrayList<ImgSumaryDTO>();
		objs = (ArrayList<Object>) imgSurveyTargetPointService
				.findImgSurveyTargetPointCountAllStatusByListMonthAndYear(months, year);

		for (Object object : objs) {
			ImgSumaryDTO imgSumaryDTO = new ImgSumaryDTO();
			Object[] ob = (Object[]) object;

			Staff staff = staffService.findById(Integer.parseInt(ob[0].toString()));
			imgSumaryDTO.setStaff(staff);
			imgSumaryDTO.setApproved(Integer.parseInt(ob[1].toString()));
			imgSumaryDTO.setRejected(Integer.parseInt(ob[2].toString()));
			imgSumaryDTO.setReject(Integer.parseInt(ob[3].toString()));
			imgSumaryDTO.setDelete(Integer.parseInt(ob[4].toString()));

			imgSumaryDTOs.add(imgSumaryDTO);

		}

		model.addAttribute("imgSumaryDTOs", imgSumaryDTOs);
		model.addAttribute("years", years);
		return "export/sumarywork";

	}

	@RequestMapping(value = "/export/imagesumary/daily", method = RequestMethod.POST)
	public String dailySumary(Model model, @RequestParam("month") int month, @RequestParam("year") int year) {

		String m = null;

		if (month == 1) {
			m = "ม.ค.";
		} else if (month == 2) {
			m = "ก.พ";
		} else if (month == 3) {
			m = "มี.ค.";
		} else if (month == 4) {
			m = "เม.ย.";
		} else if (month == 5) {
			m = "พ.ค.";
		} else if (month == 6) {
			m = "มิ.ย.";
		} else if (month == 7) {
			m = "ก.ค.";
		} else if (month == 8) {
			m = "ส.ค.";
		} else if (month == 9) {
			m = "ก.ย.";
		} else if (month == 10) {
			m = "ต.ค.";
		} else if (month == 11) {
			m = "พ.ย.";
		} else if (month == 12) {
			m = "ธ.ค.";
		}

		String title = "สถิติการตรวจสอบรายวันของ เดือน " + m + " ปี " + Integer.toString(year + 543);

		/*
		 * // ส่วนของการเพิ่ม DB เข้า
		 * 
		 * Random random = new Random(); int min = 1; int max = 10;
		 * 
		 * for (int day = 1; day <= 30; day++) { Date date = new Date(year - 1900, month
		 * - 1, day); System.out.println(date); for (int a = 0; a < random.nextInt(max -
		 * min) + min; a++) { ImgSurveyTargetPoint img = new ImgSurveyTargetPoint();
		 * img.setApproveDate(date); img.setUploadDate(new Date());
		 * img.setFilePath("a"); img.setFilePathS("b");
		 * img.setSurveytargetpoint(surveyTargetPointService.findById(random.nextInt(
		 * 5500 - 5001) + 5001));
		 * 
		 * img.setApproveStatus("Approved");
		 * img.setUserByApproveBy(userService.findById(random.nextInt(5 - 1) + 1));
		 * imgSurveyTargetPointService.save(img); } for (int r = 0; r <
		 * random.nextInt(max + min) + min; r++) { ImgSurveyTargetPoint img = new
		 * ImgSurveyTargetPoint(); img.setApproveDate(date); img.setUploadDate(new
		 * Date()); img.setFilePath("a"); img.setFilePathS("b");
		 * img.setSurveytargetpoint(surveyTargetPointService.findById(random.nextInt(
		 * 5500 - 5001) + 5001));
		 * img.setUserByApproveBy(userService.findById(random.nextInt(5 - 1) + 1));
		 * img.setApproveStatus("Reject"); imgSurveyTargetPointService.save(img);
		 * 
		 * }
		 * 
		 * for (int r = 0; r < random.nextInt(max + min) + min; r++) {
		 * ImgSurveyTargetPoint img = new ImgSurveyTargetPoint();
		 * img.setApproveDate(date); img.setUploadDate(new Date());
		 * img.setFilePath("a"); img.setFilePathS("b");
		 * img.setSurveytargetpoint(surveyTargetPointService.findById(random.nextInt(
		 * 5500 - 5001) + 5001));
		 * img.setUserByApproveBy(userService.findById(random.nextInt(5 - 1) + 1));
		 * img.setApproveStatus("Delete"); imgSurveyTargetPointService.save(img);
		 * 
		 * }
		 * 
		 * }
		 */

		ArrayList<String> labels = new ArrayList<String>();
		ArrayList<Integer> approves = new ArrayList<Integer>();
		ArrayList<Integer> rejects = new ArrayList<Integer>();

		ArrayList<Object> objects = (ArrayList<Object>) imgSurveyTargetPointService
				.findAllAndCountStatusByStatusAndMonthAndYear(month, year);
		for (Object object : objects) {
			Object[] ob = (Object[]) object;
			labels.add(ob[0].toString() + " " + m + Integer.toString(year + 543));
			approves.add(Integer.parseInt(ob[1].toString()));
			rejects.add(Integer.parseInt(ob[2].toString()));
		}

		int maxSize = findMax(approves, rejects);

		model.addAttribute("maxSize", maxSize);
		model.addAttribute("title", title);
		model.addAttribute("labels", labels);
		model.addAttribute("approves", approves);
		model.addAttribute("rejects", rejects);

		LocalDate currentdate = LocalDate.now();

		model.addAttribute("month", currentdate.getMonth().getValue());
		model.addAttribute("year", currentdate.getYear());

		ArrayList<Integer> years = new ArrayList<Integer>();

		for (int i = LocalDate.now().getYear(); i >= imgSurveyTargetPointService
				.findMinYearInImgSurveyTargetPoint(); i--) {
			years.add(i);
		}

		model.addAttribute("years", years);

		return "export/sumary";
	}

	@RequestMapping(value = "/export/imagesumary/monthly", method = RequestMethod.POST)
	public String monthlySumary(Model model, @RequestParam("year") int year) {
		System.out.println(year);

		ArrayList<String> labels = new ArrayList<String>();
		ArrayList<Integer> approves = new ArrayList<Integer>();
		ArrayList<Integer> rejects = new ArrayList<Integer>();

		for (int i = 0; i < 12; i++) {
			approves.add(0);
			rejects.add(0);
		}

		labels.add("ม.ค." + Integer.toString(year + 543));
		labels.add("ก.พ." + Integer.toString(year + 543));
		labels.add("มี.ค." + Integer.toString(year + 543));
		labels.add("เม.ย." + Integer.toString(year + 543));
		labels.add("พ.ค." + Integer.toString(year + 543));
		labels.add("มิ.ย." + Integer.toString(year + 543));
		labels.add("ก.ค." + Integer.toString(year + 543));
		labels.add("ส.ค." + Integer.toString(year + 543));
		labels.add("ก.ย." + Integer.toString(year + 543));
		labels.add("ต.ค." + Integer.toString(year + 543));
		labels.add("พ.ย." + Integer.toString(year + 543));
		labels.add("ธ.ค." + Integer.toString(year + 543));

		ArrayList<Object> objects = (ArrayList<Object>) imgSurveyTargetPointService
				.findMonthAndCountStatusByStatusAndMonthAndYear(year);
		for (Object object : objects) {
			Object[] ob = (Object[]) object;

			String m = ob[0].toString();

			int app = Integer.parseInt(ob[1].toString());
			int rej = Integer.parseInt(ob[2].toString());

			if (m.equals("1")) {
				approves.set(0, app);
				rejects.set(0, rej);
			} else if (m.equals("2")) {
				approves.set(1, app);
				rejects.set(1, rej);
			} else if (m.equals("3")) {
				approves.set(2, app);
				rejects.set(2, rej);
			} else if (m.equals("4")) {
				approves.set(3, app);
				rejects.set(3, rej);
			} else if (m.equals("5")) {
				approves.set(4, app);
				rejects.set(4, rej);
			} else if (m.equals("6")) {
				approves.set(5, app);
				rejects.set(5, rej);
			} else if (m.equals("7")) {
				approves.set(6, app);
				rejects.set(6, rej);
			} else if (m.equals("8")) {
				approves.set(7, app);
				rejects.set(7, rej);
			} else if (m.equals("9")) {
				approves.set(8, app);
				rejects.set(8, rej);
			} else if (m.equals("10")) {
				approves.set(9, app);
				rejects.set(9, rej);
			} else if (m.equals("11")) {
				approves.set(10, app);
				rejects.set(10, rej);
			} else if (m.equals("12")) {
				approves.set(11, app);
				rejects.set(11, rej);
			}
		}

		int maxSize = findMax(approves, rejects);

		String title = "สถิติการตรวจสอบรายเดือนของ ปี " + Integer.toString(year + 543);

		model.addAttribute("maxSize", maxSize);
		model.addAttribute("title", title);
		model.addAttribute("labels", labels);
		model.addAttribute("approves", approves);
		model.addAttribute("rejects", rejects);

		LocalDate currentdate = LocalDate.now();

		model.addAttribute("month", currentdate.getMonth().getValue());
		model.addAttribute("year", currentdate.getYear());

		ArrayList<Integer> years = new ArrayList<Integer>();

		for (int i = LocalDate.now().getYear(); i >= imgSurveyTargetPointService
				.findMinYearInImgSurveyTargetPoint(); i--) {
			years.add(i);
		}

		model.addAttribute("years", years);

		return "export/sumary";
	}

	@RequestMapping(value = "/export/imagesumary/yearly", method = RequestMethod.POST)
	public String yearlySumary(Model model, @RequestParam("year") int year) {

		ArrayList<String> labels = new ArrayList<String>();
		ArrayList<Integer> approves = new ArrayList<Integer>();
		ArrayList<Integer> rejects = new ArrayList<Integer>();

		for (int i = 0; i < 10; i++) {
			approves.add(0);
			rejects.add(0);
		}

		for (int i = 0; i < 10; i++) {
			labels.add("ปี" + Integer.toString(year + 543 + i));
		}

		ArrayList<Object> objects = (ArrayList<Object>) imgSurveyTargetPointService
				.findYearAndCountStatusByStatusAndBetweenYear(year, year + 9);
		for (Object object : objects) {
			Object[] ob = (Object[]) object;

			String y = ob[0].toString();
			int yearly = Integer.parseInt(y) - year;

			int app = Integer.parseInt(ob[1].toString());
			int rej = Integer.parseInt(ob[2].toString());
			approves.set(yearly, app);
			rejects.set(yearly, rej);

		}

		int maxSize = findMax(approves, rejects);

		String title = "สถิติการตรวจสอบรายปี ตั้งเเต่ปี " + Integer.toString(year + 543) + " ถึง "
				+ Integer.toString(year + 543 + 9);

		model.addAttribute("maxSize", maxSize);
		model.addAttribute("title", title);
		model.addAttribute("labels", labels);
		model.addAttribute("approves", approves);
		model.addAttribute("rejects", rejects);

		LocalDate currentdate = LocalDate.now();

		model.addAttribute("month", currentdate.getMonth().getValue());
		model.addAttribute("year", currentdate.getYear());

		ArrayList<Integer> years = new ArrayList<Integer>();

		for (int i = LocalDate.now().getYear(); i >= imgSurveyTargetPointService
				.findMinYearInImgSurveyTargetPoint(); i--) {
			years.add(i);
		}

		model.addAttribute("years", years);

		return "export/sumary";
	}

	@RequestMapping(value = "/export/imagesumary/all", method = RequestMethod.GET)
	public String allSumaryImg(Model model) {

		ArrayList<String> labels = new ArrayList<String>();
		ArrayList<Integer> approves = new ArrayList<Integer>();
		ArrayList<Integer> rejects = new ArrayList<Integer>();

		ArrayList<Object> objects = (ArrayList<Object>) imgSurveyTargetPointService.findAllDiseaseNameAndCountStatus();
		for (Object object : objects) {
			Object[] ob = (Object[]) object;

			labels.add(ob[0].toString());
			approves.add(Integer.parseInt(ob[1].toString()));
			rejects.add(Integer.parseInt(ob[2].toString()));
		}

		int maxSize = findMax(approves, rejects);

		if (maxSize == 0) {
			maxSize = 100;
		}

		model.addAttribute("maxSize", maxSize);

		model.addAttribute("labels", labels);
		model.addAttribute("approves", approves);
		model.addAttribute("rejects", rejects);

		return "export/sumaryImgAll";
	}

	@RequestMapping(value = "/export/imagesumaryperson", method = RequestMethod.GET)
	public String personSumaryImg(Model model, Authentication authentication) {

		String username = MvcHelper.getUsername(authentication);
		User user = userService.findByUsername(username);

		ArrayList<String> labels = new ArrayList<String>();
		ArrayList<Integer> approves = new ArrayList<Integer>();
		ArrayList<Integer> rejects = new ArrayList<Integer>();

		ArrayList<Object> objects = (ArrayList<Object>) imgSurveyTargetPointService
				.findAllDiseaseNameCountStatusByStatusAndUserId(user.getUserId());
		for (Object object : objects) {
			Object[] ob = (Object[]) object;

			labels.add(ob[0].toString());
			approves.add(Integer.parseInt(ob[1].toString()));
			rejects.add(Integer.parseInt(ob[2].toString()));
		}

		int max_size = 0;

		if (approves.size() > 0 && rejects.size() > 0) {
			int a_max = approves.get(0);
			int r_max = rejects.get(0);

			for (int i = 1; i < approves.size(); i++) {
				if (a_max < approves.get(i)) {
					a_max = approves.get(i);
				}
			}
			for (int i = 1; i < rejects.size(); i++) {
				if (r_max < rejects.get(i)) {
					r_max = rejects.get(i);
				}
			}

			if (a_max > r_max) {
				max_size = a_max;
			} else {
				max_size = r_max;
			}

			while (max_size % 100 != 0) {
				max_size += 1;
			}

		}
		int maxSize = max_size;

		if (maxSize == 0) {
			maxSize = 100;
		}

		model.addAttribute("maxSize", maxSize);

		model.addAttribute("labels", labels);
		model.addAttribute("approves", approves);
		model.addAttribute("rejects", rejects);

		return "export/sumaryImgPerson";
	}

	private int findMax(List<Integer> approves, List<Integer> rejects) {
		int max_size = 0;

		if (approves.size() > 0 && rejects.size() > 0) {
			int a_max = approves.get(0);
			int r_max = rejects.get(0);

			for (int i = 1; i < approves.size(); i++) {
				if (a_max < approves.get(i)) {
					a_max = approves.get(i);
				}
			}
			for (int i = 1; i < rejects.size(); i++) {
				if (r_max < rejects.get(i)) {
					r_max = rejects.get(i);
				}
			}

			if (a_max > r_max) {
				max_size = a_max;
			} else {
				max_size = r_max;
			}

			while (max_size % 100 != 0) {
				max_size += 1;
			}

		}

		if (max_size <= 100) {
			max_size = 100;
		}
		return max_size;
	}
}
