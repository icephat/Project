package org.cassava.web.controller;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.cassava.api.util.model.Response;
import org.cassava.model.Organization;
import org.cassava.model.Role;
import org.cassava.model.Staff;
import org.cassava.model.User;
import org.cassava.services.OrganizationService;
import org.cassava.services.RoleService;
import org.cassava.services.StaffService;
import org.cassava.services.UserService;
import org.cassava.util.MvcHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Controller
public class StaffController {

	@Autowired
	private StaffService staffService;

	@Autowired
	private UserService userService;

	@Autowired
	private OrganizationService organizationService;

	@Autowired
	private RoleService roleService;

	public void setPageIndex(int sizeItem, Model model, int pageCurrent, int pageSize) {
		int firstItem = 0, lastItem = 0;
		if (sizeItem > 0) {
			firstItem = (pageSize * (pageCurrent - 1)) + 1;
			lastItem = firstItem + sizeItem - 1;
		}
		model.addAttribute("firstItem", firstItem);
		model.addAttribute("lastItem", lastItem);
	}

	@RequestMapping(value = { "/staff/index", "/staff/", "/staff" }, method = RequestMethod.GET)
	public String index(Model model, Authentication authentication) {
		model.addAttribute("sizePages", Arrays.asList(10, 25, 50));
		return "/staff/index";
	}

	@RequestMapping(value = "/staff/page/{page}/value/{value}", method = RequestMethod.GET)
	public String staffPage(@PathVariable("page") int page, @PathVariable("value") int value,
			@RequestParam(value = "key", required = false) String key, Authentication authentication, Model model) {
		User user = userService.findByUsername(MvcHelper.getUsername(authentication));

		List<Staff> staffs;
		if ((key == null || key == "")) {
			if (roleService.findByUserIdAndRoleName(user.getUserId(), "systemAdmin") != null) {
				staffs = staffService.findByStatusValid(page, value);
			} else {
				Organization organization = user.getStaff().getOrganization();
				staffs = staffService.findByOrganizationAndStatusValid(organization, page, value);
			}
		} else {
			if (roleService.findByUserIdAndRoleName(user.getUserId(), "systemAdmin") != null) {
				staffs = staffService.findByStatusValidAndKey(key, page, value);
			} else {
				Organization organization = user.getStaff().getOrganization();
				staffs = staffService.findByOrganizationAndStatusValidAndKey(organization, key, page, value);
			}
		}
		model.addAttribute("staffs", staffs);
		setPageIndex(staffs.size(), model, page, value);
		Format formatter = new SimpleDateFormat("d MMM yyyy", new Locale("th", "TH"));
		model.addAttribute("formatter", formatter);
		model.addAttribute("active", "active");
		model.addAttribute("inactive", "inactive");
		return "/staff/staffTable";
	}

	@RequestMapping(value = { "/staff/total" }, method = RequestMethod.GET)
	@ResponseBody
	public int selectStaffTableTotal(@RequestParam(value = "key", required = false) String key,
			Authentication authentication, Model model) {
		int totalItem;
		User user = userService.findByUsername(MvcHelper.getUsername(authentication));

		if ((key == null || key == "")) {
			if (roleService.findByUserIdAndRoleName(user.getUserId(), "systemAdmin") != null) {
				totalItem = staffService.findByStatusValid().size();
			} else {
				Organization organization = user.getStaff().getOrganization();
				totalItem = staffService.findByOrganizationAndStatusValid(organization).size();
			}
		} else {
			if (roleService.findByUserIdAndRoleName(user.getUserId(), "systemAdmin") != null) {
				totalItem = staffService.findByStatusValidAndKey(key).size();
			} else {
				Organization organization = user.getStaff().getOrganization();
				totalItem = staffService.findByOrganizationAndStatusValidAndKey(organization, key).size();
			}
		}
		return totalItem;
	}

	@RequestMapping(value = "/staff/delete/{id}", method = RequestMethod.GET)
	public String deleteFarmer(@PathVariable("id") int id, Model model) {
		if (staffService.findById(id) != null && userService.findById(id) != null) {
			staffService.deleteById(id);
			userService.deleteById(id);
		}

		return "redirect:/staff/";
	}

	// @RequestMapping(value = "/staff/approve/update", method = RequestMethod.POST)
	// public String staffDetailUpdate(@ModelAttribute("staff") Staff staff, String
	// status, Model model) {

	// User user = userService.findById(staff.getStaffId());
	// Staff s = staffService.findById(staff.getStaffId());

	// Organization org =
	// organizationService.findById(s.getOrganization().getOrganizationId());

	// s.getUser().setUsername(staff.getUser().getUsername());
	// s.setPosition(staff.getPosition());
	// s.setDivision(staff.getDivision());
	// s.setOrganization(org);
	// s.getUser().setLatestLogin(user.getLatestLogin());

	// s.getUser().setTitle(staff.getUser().getTitle());
	// s.getUser().setFirstName(staff.getUser().getFirstName());
	// s.getUser().setLastName(staff.getUser().getLastName());
	// s.getUser().setPhoneNo(staff.getUser().getPhoneNo());
	// s.getUser().setUserStatus(staff.getUser().getUserStatus());

	// user.setStaff(staff);
	// s.getUser().setUserStatus("waiting");

	// organizationService.save(s.getOrganization());
	// userService.save(s.getUser());
	// staffService.save(s);

	// return "redirect:/staff/approve";
	// }
	@RequestMapping(value = "/staff/approve/update", method = RequestMethod.POST)
	public ResponseEntity<Response<ObjectNode>> staffUpdate(@ModelAttribute("staff") Staff staff, Model model,
			Authentication authentication) {
		//staff.setAddress(staff.getAddress().trim());
		staff.getUser().setUsername(staff.getUser().getUsername().trim());
		staff.getUser().setTitle(staff.getUser().getTitle().trim());
		staff.getUser().setFirstName(staff.getUser().getFirstName().trim());
		staff.getUser().setLastName(staff.getUser().getLastName().trim());
		staff.getUser().setPhoneNo(staff.getUser().getPhoneNo().trim());
		staff.getUser().setPhoneNo(staff.getUser().getPhoneNo().trim());
		staff.setPosition(staff.getPosition().trim());
		staff.setDivision(staff.getDivision().trim());
		ResponseEntity<Response<ObjectNode>> responseEntity = validate(staff);
		if (responseEntity.getStatusCode() == HttpStatus.OK) {
			User user = userService.findById(staff.getStaffId());
			Staff s = staffService.findById(staff.getStaffId());

			Organization org = organizationService.findById(s.getOrganization().getOrganizationId());

			s.getUser().setUsername(staff.getUser().getUsername());
			s.setPosition(staff.getPosition());
			s.setDivision(staff.getDivision());
			s.setOrganization(org);
			s.getUser().setLatestLogin(user.getLatestLogin());

			s.getUser().setTitle(staff.getUser().getTitle());
			s.getUser().setFirstName(staff.getUser().getFirstName());
			s.getUser().setLastName(staff.getUser().getLastName());
			s.getUser().setPhoneNo(staff.getUser().getPhoneNo());
			s.getUser().setUserStatus(staff.getUser().getUserStatus());

			user.setStaff(staff);
			user.setUserStatus(s.getUser().getUserStatus());

			organizationService.save(s.getOrganization());
			userService.save(s.getUser());
			staffService.save(s);
		}
		return responseEntity;
	}

	private ResponseEntity<Response<ObjectNode>> validate(Staff staff) {
		ObjectNode responObject = new ObjectMapper().createObjectNode();

		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (staff.getUser().getUserStatus() == null) {
			staff.getUser().setUserStatus("waiting");
		}
		Set<ConstraintViolation<User>> violations = validator.validate(staff.getUser());
		Set<ConstraintViolation<Staff>> violationstaff = validator.validate(staff);
		HashMap<String, List<String>> errorMessages = new HashMap<String, List<String>>();
		if (violations.size() > 0) {
			violations.stream().forEach((ConstraintViolation<?> error) -> {
				String key = error.getPropertyPath().toString();
				String message = error.getMessage();
				List<String> list = null;
				if (errorMessages.containsKey(key)) {
					list = errorMessages.get(key);
				} else {
					list = new ArrayList<String>();
				}
				list.add(message);
				errorMessages.put(key, list);
			});
			for (String key : errorMessages.keySet()) {
				List<String> sortedList = errorMessages.get(key);
				Collections.sort(sortedList);
				Iterator<String> itorError = sortedList.iterator();
				StringBuilder sb = new StringBuilder(itorError.next());
				while (itorError.hasNext()) {
					sb.append(", ").append(itorError.next());
				}
				responObject.put(key, sb.toString());
			}
		}
		if (violationstaff.size() > 0) {
			violationstaff.stream().forEach((ConstraintViolation<?> error) -> {
				String key = error.getPropertyPath().toString();
				String message = error.getMessage();
				List<String> list = null;
				if (errorMessages.containsKey(key)) {
					list = errorMessages.get(key);
				} else {
					list = new ArrayList<String>();
				}
				list.add(message);
				errorMessages.put(key, list);
			});
			for (String key : errorMessages.keySet()) {
				List<String> sortedList = errorMessages.get(key);
				Collections.sort(sortedList);
				Iterator<String> itorError = sortedList.iterator();
				StringBuilder sb = new StringBuilder(itorError.next());
				while (itorError.hasNext()) {
					sb.append(", ").append(itorError.next());
				}
				responObject.put(key, sb.toString());
			}
		}
		Response<ObjectNode> res = new Response<>();
		if (responObject.size() == 0)
			res.setHttpStatus(HttpStatus.OK);
		else {
			res.setHttpStatus(HttpStatus.BAD_REQUEST);
			res.setMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
			res.setBody(responObject);
		}
		return new ResponseEntity<Response<ObjectNode>>(res, res.getHttpStatus());
	}

	@RequestMapping(value = "/staff/update", method = RequestMethod.POST)
	public String staffUpdate(@ModelAttribute("staff") Staff staff, Model model) {

		User user = userService.findById(staff.getStaffId());
		Staff s = staffService.findById(staff.getStaffId());

		Organization org = organizationService.findById(s.getOrganization().getOrganizationId());

		s.getUser().setUsername(staff.getUser().getUsername());
		s.setPosition(staff.getPosition());
		s.setDivision(staff.getDivision());
		s.setOrganization(org);
		s.getUser().setLatestLogin(user.getLatestLogin());

		s.getUser().setTitle(staff.getUser().getTitle());
		s.getUser().setFirstName(staff.getUser().getFirstName());
		s.getUser().setLastName(staff.getUser().getLastName());
		s.getUser().setPhoneNo(staff.getUser().getPhoneNo());
		s.getUser().setUserStatus(staff.getUser().getUserStatus());

		user.setStaff(staff);
		user.setUserStatus(s.getUser().getUserStatus());

		organizationService.save(s.getOrganization());
		userService.save(s.getUser());
		staffService.save(s);

		return "redirect:/staff/";
	}

	@RequestMapping(value = "/staff/detail/{id}", method = RequestMethod.GET)
	public String staffDetail(@PathVariable("id") int id, Model model) {
		Staff staff = staffService.findById(id);
		if(staff == null) {
			return "redirect:/staff/approve";
		}
		model.addAttribute("staff", staff);
		Format formatter = new SimpleDateFormat("d MMM yyyy", new Locale("th", "TH"));
		model.addAttribute("formatter", formatter);
		return "/staff/detail";
	}

	@RequestMapping(value = "/staff/edit/{id}", method = RequestMethod.GET)
	public String FarmerDetail(@PathVariable("id") int id, Model model) {
		LinkedHashMap<String, String> statusMap = new LinkedHashMap<String, String>();
		statusMap.put("active", "เปิดการใช้งาน");
		statusMap.put("inactive", "ปิดการใช้งาน");
		model.addAttribute("statusMap", statusMap);

		Staff staff = staffService.findById(id);
		if(staff == null) {
			return "redirect:/staff/";
		}
		model.addAttribute("staff", staff);

		Format formatter = new SimpleDateFormat("d MMM yyyy", new Locale("th", "TH"));
		model.addAttribute("formatter", formatter);

		return "/staff/edit";
	}

	@RequestMapping(value = "/staff/approve", method = RequestMethod.GET)
	public String userApprove(Model model, Authentication authentication) {

		User u = userService.findByUsername(MvcHelper.getUsername(authentication));
		if (roleService.findByUserIdAndRoleName(u.getUserId(), "systemAdmin") != null) {
			model.addAttribute("staffs", staffService.findByStatus("waiting"));
		} else {
			Organization organization = u.getStaff().getOrganization();
			model.addAttribute("staffs", staffService.findByOrganizationAndStatus(organization, "waiting"));
		}

		String active = "active";
		String inactive = "inactive";
		model.addAttribute("active", active);
		model.addAttribute("inactive", inactive);

		return "staff/staffapprove";
	}

	@RequestMapping(value = "/staff/{id}/approve/save", method = RequestMethod.GET)
	public String approve(@PathVariable("id") int id, Model m, Authentication authentication) {
		User user = userService.findById(id);
		if(user == null) {
			return "redirect:/staff/approve";
		}
		user.setUserStatus("active");

		List<Role> roleUser = user.getRoles();
		while (user.getRoles().size() > 0) {
			roleUser.remove(0);
		}
		Role r = roleService.findByNameEng("staff");
		roleUser.add(r);
		userService.save(user);
		return "redirect:/staff/approve";
	}

}