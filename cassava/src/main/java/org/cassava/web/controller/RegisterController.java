package org.cassava.web.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.cassava.api.util.model.Response;
import org.cassava.model.Farmer;
import org.cassava.model.Organization;
import org.cassava.model.Province;
import org.cassava.model.RegisterCode;
import org.cassava.model.Role;
import org.cassava.model.Staff;
import org.cassava.model.User;
import org.cassava.model.dto.FarmerDTO;
import org.cassava.model.dto.StaffDTO;
import org.cassava.services.FarmerService;
import org.cassava.services.OrganizationService;
import org.cassava.services.ProvinceService;
import org.cassava.services.RegisterCodeService;
import org.cassava.services.RoleService;
import org.cassava.services.StaffService;
import org.cassava.services.UserService;
import org.cassava.util.MvcHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Controller
public class RegisterController {

	@Autowired
	private RegisterCodeService registcodeService;

	@Autowired
	private OrganizationService organizationService;

	@Autowired
	private FarmerService farmerService;

	@Autowired
	private UserService userService;

	@Autowired
	private StaffService staffService;

	@Autowired
	private ProvinceService provinceService;

	@Autowired
	private RoleService roleService;

	@RequestMapping(value = "/register/email", method = RequestMethod.GET)
	public String homeIndex(Model m) {
		return "register/registEmail";
	}

	@RequestMapping(value = "/register/farmer/confirm", method = RequestMethod.GET)
	public String farmerConfirmRegister(Model m, Authentication authentication) {
		String userName = MvcHelper.getUsername(authentication);
		int check = 1;
		m.addAttribute("check", check);
		m.addAttribute("userName", userName);
		return "register/registerFarmerConfirm";
	}

	@RequestMapping(value = "/register/farmer/confirm", method = RequestMethod.POST)
	public String farmerConfirmCheck(String check, String check2, Model m, Authentication authentication) {
		// System.out.println("xxx - go to register email page");
		String userName = MvcHelper.getUsername(authentication);
		int checkbox = 1;
		m.addAttribute("provinces", provinceService.findAll());
		m.addAttribute("organizations", organizationService.findAll());
		m.addAttribute("userName", userName);

		if (check != null && check2 != null) {
			m.addAttribute("check", checkbox);
			return "register/registInfoFarmer";
		} else {
			checkbox = 0;
			m.addAttribute("check", checkbox);
			return "register/registerFarmerConfirm";
		}

	}

	@RequestMapping(value = "/register/staff/confirm", method = RequestMethod.GET)
	public String staffConfirmRegister(Model m, Authentication authentication) {
		String userName = MvcHelper.getUsername(authentication);
		int check = 1;
		m.addAttribute("check", check);
		m.addAttribute("userName", userName);
		return "register/registerStaffConfirm";
	}

	@RequestMapping(value = "/register/staff/confirm", method = RequestMethod.POST)
	public String staffConfirmCheck(String check, String check2, Model m, Authentication authentication) {
		String userName = MvcHelper.getUsername(authentication);
		int checkbox = 1;
		m.addAttribute("organizations", organizationService.findAll());
		if (check != null && check2 != null) {
			m.addAttribute("check", checkbox);
			m.addAttribute("userName", userName);
			return "register/registInfoStaff";
		} else {
			checkbox = 0;
			m.addAttribute("check", checkbox);
			m.addAttribute("userName", userName);
			return "register/registerStaffConfirm";
		}

	}

	@RequestMapping(value = "/register/confirm", method = RequestMethod.GET)
	public String confirmRegister(@RequestParam("code") String code, Model m, Authentication authentication) {
		String userName = MvcHelper.getUsername(authentication);
		int check = 1;
		m.addAttribute("check", check);
		m.addAttribute("code", code);
		m.addAttribute("userName", userName);
		return "register/registerConfirm";
	}

	@RequestMapping(value = "/register/confirm", method = RequestMethod.POST)
	public String confirmCheck(@RequestParam("code") String code, String check, String check2, Model m,
			Authentication authentication) {
		String userName = MvcHelper.getUsername(authentication);
		int checkbox = 1;
		if (check != null && check2 != null) {
			m.addAttribute("check", checkbox);
			m.addAttribute("code", code);
			return "redirect:/register/check";
		} else {
			checkbox = 0;
			m.addAttribute("check", checkbox);
			m.addAttribute("userName", userName);
			m.addAttribute("code", code);
			return "register/registerConfirm";
		}

	}

	@RequestMapping(value = "/register/code", method = RequestMethod.GET)
	public String registCodeIndex(Model m, Authentication authentication, HttpServletRequest request,
			HttpServletResponse response) {

		String userName = MvcHelper.getUsername(authentication);
		User user = userService.findByUsername(userName);
		if (user == null) {
			m.addAttribute("userName", userName);
			return "register/registCode";
		}

		m.addAttribute("message", userName + "<br> บัญชีผู้ใช้อยู่ระหว่างรออนุมัติ");
		m.addAttribute("subMessage", "กรุณาติดต่อเจ้าหน้าที่");
		clearSession(authentication, request, response);
		return "register/registerStatus";
	}

	private void clearSession(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		Cookie[] cookies = request.getCookies();

		if (cookies != null)
			for (Cookie cookie : cookies) {

				cookie.setValue("");
				cookie.setPath("/");
				cookie.setMaxAge(0);
				response.addCookie(cookie);

			}

		request.getSession();
	}

	@RequestMapping(value = "/register/status", method = RequestMethod.GET)
	public String regisSuccess(Model m, HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) {
		String userName = MvcHelper.getUsername(authentication);
		String message = userName + " ลงทะเบียนสำเร็จ";

		m.addAttribute("message", message);
		clearSession(authentication, request, response);

		return "register/registerStatus";
	}

	@RequestMapping(value = "/register/check", method = RequestMethod.GET)
	public String checkCode(@RequestParam("code") String code, Model m, Authentication authentication) {

		String userName = MvcHelper.getUsername(authentication);
		RegisterCode registercode = registcodeService.findByCode(code);
		String message;

		if (registercode != null) { // found
									// RegisterCode r = registcodeService.findValidCodeByCode(code);
			String checkStatus = registcodeService.checkStatusByCode(registercode);
			if (checkStatus.equals("Valid")) {
				// correct
				m.addAttribute("id", registercode.getRegisterCodeId());
				m.addAttribute("userName", userName);
				m.addAttribute("organization", registercode.getOrganization().getName());

				if (registercode.getUserType().equals("Farmer")) {
					List<Province> provinces = provinceService.findAll();
					m.addAttribute("provinces", provinces);
					m.addAttribute("nameDistrict", "farmer.subdistrict.district.districtId");
					m.addAttribute("nameSubdistrict", "farmer.subdistrict.subdistrictId");
					return "register/registInfoFarmer";
				} else {
					return "register/registInfoStaff";
				}
			} else {
				if (checkStatus.equals("Expire"))
					message = "รหัสหมดอายุ";
				else if (checkStatus.equals("NotDue"))
					message = "รหัสยังไม่เริ่มใช้งาน";
				else if (checkStatus.equals("Exceed"))
					message = "รหัสถูกใช้ครบจำนวนแล้ว";
				else
					message = "รหัสไม่ถูกต้อง";
			}
		} else { // not found
			message = "ไม่พบรหัสตามที่ระบุ";
		}
		m.addAttribute("errMsg", message);
		m.addAttribute("userName", userName);

		return "register/registCode";
	}

	@RequestMapping(value = "/register/farmer", method = RequestMethod.GET)
	public String registInfoFarmer(Model m, Authentication authentication) {
		String userName = MvcHelper.getUsername(authentication);

		m.addAttribute("userName", userName);
		m.addAttribute("provinces", provinceService.findAll());
		m.addAttribute("organizations", organizationService.findAll());
		return "register/registInfoFarmer";
	}

	@RequestMapping(value = "/register/staff", method = RequestMethod.GET)
	public String registInfoStaff(Model m, Authentication authentication) {
		String userName = MvcHelper.getUsername(authentication);

		m.addAttribute("userName", userName);
		m.addAttribute("organizations", organizationService.findAll());
		return "register/registInfoStaff";
	}

	@RequestMapping(value = "/register/farmer/save", method = RequestMethod.POST)
	public ResponseEntity<Response<String>> registInfoFarmerSave(@ModelAttribute("farmerDTO") FarmerDTO farmerDTO,
			Model m, Authentication authentication, HttpServletRequest request,
			HttpServletResponse response) {
		Farmer farmer = farmerDTO.getFarmer();
		farmer.setAddress(farmer.getAddress().trim());
		farmer.getUser().setUsername(farmer.getUser().getUsername().trim());
		farmer.getUser().setTitle(farmer.getUser().getTitle().trim());
		farmer.getUser().setFirstName(farmer.getUser().getFirstName().trim());
		farmer.getUser().setLastName(farmer.getUser().getLastName().trim());
		farmer.getUser().setPhoneNo(farmer.getUser().getPhoneNo().trim());
		User user = userService.findByUsername(farmer.getUser().getUsername());
		RegisterCode registercode = farmerDTO.getRegistercode();
		Response<String> resp = new Response<String>();
		resp.setHttpStatus(HttpStatus.OK);
		if (user != null) {
			m.addAttribute("message", "พบบัญชีผู้ใช้ลงทะเบียนอยู่ในระบบแล้ว");
			m.addAttribute("subMessage", "กรุณาติดต่อเจ้าหน้าที่");
			clearSession(authentication, request, response);
			String string = new String("register/registerStatus");
			resp.setMessage("registerregisterStatus");
			resp.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<String>>(resp, resp.getHttpStatus());
		}

		if (registercode.getRegisterCodeId() != null) {
			RegisterCode r = registcodeService.findById(registercode.getRegisterCodeId());
			r.setNumUseRegist(r.getNumUseRegist() + 1);
			registcodeService.save(r);

			farmer.getUser().setUserStatus("active");
			farmer.getUser().setRequestInfoStatus("No");
			Date date = new Date();
			farmer.getUser().setLatestLogin(date);
			// farmer.setPdpastatus("No");
			farmerService.save(farmer);

			user = userService.findByUsername(farmer.getUser().getUsername());
			Role role = roleService.findByNameEng("farmer");

			List<Role> roleUser = user.getRoles();
			List<User> userRole = role.getUsers();
			roleUser.add(role);
			userRole.add(user);
			userService.save(user);
			roleService.save(role);

			Organization organization = r.getOrganization();
			List<Organization> orgs = farmer.getOrganizations();
			List<Farmer> frs = organization.getFarmers();
			orgs.add(organization);
			frs.add(farmer);
			organizationService.save(organization);
			farmerService.save(farmer);
		} else {
			farmer.getUser().setUserStatus("waiting");
			farmer.getUser().setRequestInfoStatus("No");
			Date date = new Date();
			farmer.getUser().setLatestLogin(date);
			// farmer.setPdpastatus("No");
			farmerService.save(farmer);

			user = userService.findByUsername(farmer.getUser().getUsername());
			Role role = roleService.findByNameEng("waitingApprove");

			List<Role> roleUser = user.getRoles();
			List<User> userRole = role.getUsers();
			roleUser.add(role);
			userRole.add(user);
			userService.save(user);
			roleService.save(role);

			Organization organization = organizationService.findById(farmerDTO.getOrganization().getOrganizationId());
			List<Organization> orgs = farmer.getOrganizations();
			List<Farmer> frs = organization.getFarmers();
			orgs.add(organization);
			frs.add(farmer);
			organizationService.save(organization);
			farmerService.save(farmer);
		}
		resp.setMessage("register/status");
		resp.setHttpStatus(HttpStatus.NOT_ACCEPTABLE);
		return new ResponseEntity<Response<String>>(resp, resp.getHttpStatus());
	}

	@RequestMapping(value = "/register/staff/save", method = RequestMethod.POST)
	public ResponseEntity<Response<String>> registInfoStaffSave(@ModelAttribute("staffDTO") StaffDTO staffDTO, Model m,
			Authentication authentication, HttpServletRequest request,
			HttpServletResponse response) {
		Staff staff = staffDTO.getStaff();
		staff.getUser().setUsername(staff.getUser().getUsername().trim());
		staff.getUser().setTitle(staff.getUser().getTitle().trim());
		staff.getUser().setFirstName(staff.getUser().getFirstName().trim());
		staff.getUser().setLastName(staff.getUser().getLastName().trim());
		staff.getUser().setPhoneNo(staff.getUser().getPhoneNo().trim());
		// Staff staff = staffDTO.getStaff();
		User user = userService.findByUsername(staff.getUser().getUsername());
		RegisterCode registercode = staffDTO.getRegistercode();
		Response<String> resp = new Response<String>();
		resp.setHttpStatus(HttpStatus.OK);
		if (user != null) {
			m.addAttribute("message", "พบบัญชีผู้ใช้ลงทะเบียนอยู่ในระบบแล้ว");
			m.addAttribute("subMessage", "กรุณาติดต่อเจ้าหน้าที่");
			clearSession(authentication, request, response);
			resp.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<String>>(resp, resp.getHttpStatus());
		}
		if (registercode.getRegisterCodeId() != null) {
			RegisterCode r = registcodeService.findById(registercode.getRegisterCodeId());
			r.setNumUseRegist(r.getNumUseRegist() + 1);
			registcodeService.save(r);

			staff.getUser().setUserStatus("active");
			staff.getUser().setRequestInfoStatus("No");
			staff.setOrganization(r.getOrganization());
			Date date = new Date();
			staff.getUser().setLatestLogin(date);
			staffService.save(staff);

			user = userService.findByUsername(staff.getUser().getUsername());
			Role role = roleService.findByNameEng("staff");
			;
			List<Role> roleUser = user.getRoles();
			List<User> userRole = role.getUsers();
			roleUser.add(role);
			userRole.add(user);
			userService.save(user);
			roleService.save(role);
		} else {
			staff.getUser().setUserStatus("waiting");
			staff.getUser().setRequestInfoStatus("No");
			Date date = new Date();
			staff.getUser().setLatestLogin(date);
			staffService.save(staff);

			user = userService.findByUsername(staff.getUser().getUsername());
			Role role = roleService.findByNameEng("waitingApprove");

			List<Role> roleUser = user.getRoles();
			List<User> userRole = role.getUsers();
			roleUser.add(role);
			userRole.add(user);
			userService.save(user);
			roleService.save(role);
		}
		resp.setHttpStatus(HttpStatus.NOT_ACCEPTABLE);
		return new ResponseEntity<Response<String>>(resp, resp.getHttpStatus());
	}

	@RequestMapping(value = "/register/farmer/check", method = RequestMethod.POST)
	public ResponseEntity<Response<ObjectNode>> orgFarmerUpdate(@ModelAttribute("farmerDTO") FarmerDTO farmerDTO,
			BindingResult bindingResult, Authentication authentication, Model model) {
		Farmer farmer = farmerDTO.getFarmer();
		farmer.setAddress(farmer.getAddress().trim());
		farmer.getUser().setUsername(farmer.getUser().getUsername().trim());
		farmer.getUser().setTitle(farmer.getUser().getTitle().trim());
		farmer.getUser().setFirstName(farmer.getUser().getFirstName().trim());
		farmer.getUser().setLastName(farmer.getUser().getLastName().trim());
		farmer.getUser().setPhoneNo(farmer.getUser().getPhoneNo().trim());
		ResponseEntity<Response<ObjectNode>> responseEntity = validate(farmerDTO.getFarmer());
		return responseEntity;
	}

	private ResponseEntity<Response<ObjectNode>> validate(Farmer farmer) {
		ObjectNode responObject = new ObjectMapper().createObjectNode();

		System.out.println("------");
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		farmer.getUser().setUserStatus("waiting");
		System.out.println("subb" + farmer.getSubdistrict().getSubdistrictId());
		if (farmer.getSubdistrict().getSubdistrictId() == null) {
			responObject.put("provinces", "กรุณาเลือกจังหวัด");
		}
		if (!(farmer.getUser().getPhoneNo().length() == 10)) {
			responObject.put("phoneNo", "ตัวเลขควรมี 10 หลัก");
		}
		Set<ConstraintViolation<User>> violations = validator.validate(farmer.getUser());
		Set<ConstraintViolation<Farmer>> violationfarmer = validator.validate(farmer);
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
				System.out.println(key);
				errorMessages.put(key, list);
			});
			for (String key : errorMessages.keySet()) {
				System.out.println(key);
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
		if (violationfarmer.size() > 0) {
			violationfarmer.stream().forEach((ConstraintViolation<?> error) -> {
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
		System.out.println(responObject.size());
		return new ResponseEntity<Response<ObjectNode>>(res, res.getHttpStatus());
	}

	@RequestMapping(value = "/register/staff/check", method = RequestMethod.POST)
	public ResponseEntity<Response<ObjectNode>> orgStaffUpdate(@ModelAttribute("staffDTO") StaffDTO staffDTO,
			BindingResult bindingResult, Authentication authentication, Model model) {
		Staff staff = staffDTO.getStaff();
		staff.getUser().setUsername(staff.getUser().getUsername().trim());
		staff.getUser().setTitle(staff.getUser().getTitle().trim());
		staff.getUser().setFirstName(staff.getUser().getFirstName().trim());
		staff.getUser().setLastName(staff.getUser().getLastName().trim());
		staff.getUser().setPhoneNo(staff.getUser().getPhoneNo().trim());
		ResponseEntity<Response<ObjectNode>> responseEntity = validate(staffDTO.getStaff());
		return responseEntity;
	}

	private ResponseEntity<Response<ObjectNode>> validate(Staff staff) {
		ObjectNode responObject = new ObjectMapper().createObjectNode();

		System.out.println("------");
		staff.getUser().setUserStatus("waiting");
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (!(staff.getUser().getPhoneNo().length() == 10)) {
			responObject.put("phoneNo", "ตัวเลขควรมี 10 หลัก");
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
		System.out.println(responObject.size());
		return new ResponseEntity<Response<ObjectNode>>(res, res.getHttpStatus());
	}
}
