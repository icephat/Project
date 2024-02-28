package org.cassava.web.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.cassava.api.util.model.Response;
import org.cassava.model.District;
import org.cassava.model.Farmer;
import org.cassava.model.Organization;
import org.cassava.model.Province;
import org.cassava.model.Role;
import org.cassava.model.User;
import org.cassava.services.DistrictService;
import org.cassava.services.FarmerService;
import org.cassava.services.OrganizationService;
import org.cassava.services.ProvinceService;
import org.cassava.services.RoleService;
import org.cassava.services.SubdistrictService;
import org.cassava.services.UserService;
import org.cassava.util.MvcHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Controller
public class FarmerOrganizationController {

	@Autowired
	private OrganizationService organizationService;

	@Autowired
	private ProvinceService provinceService;

	@Autowired
	private SubdistrictService subdistrictService;

	@Autowired
	private UserService userService;

	@Autowired
	private DistrictService districtService;

	@Autowired
	private FarmerService farmerService;

	@Autowired
	private RoleService roleService;

	private static String gmail = "@gmail.com";

	@RequestMapping(value = { "/farmerorganization/index", "/farmerorganization/",
			"/farmerorganization" }, method = RequestMethod.GET)
	public String orgFarmerIndex(Model model, Authentication authentication) {
		User u = userService.findByUsername(MvcHelper.getUsername(authentication));
		model.addAttribute("farmers", farmerService.findByOrganizationAndStatusValid(u.getStaff().getOrganization()));
		return "farmerorganization/index";
	}

	@RequestMapping(value = "/farmerorganization/addfarmer", method = RequestMethod.GET)
	public String orgFarmerAdd(Model model, Authentication authentication) {
		String username = MvcHelper.getUsername(authentication);
		Organization organization = organizationService.findByStaffUsername(username);

		model.addAttribute("farmers", farmerService.findFarmerOrganizationNotIn(organization));
		return "farmerorganization/addfarmer";
	}

	@RequestMapping(value = "/farmerorganization/addorganization/{id}", method = RequestMethod.POST)
	public String orgFarmerAddFarmer(@PathVariable("id") int id, Model m, Authentication authentication) {
		// Ice
		// change to method post//
		String username = MvcHelper.getUsername(authentication);
		Organization organization = organizationService.findByStaffUsername(username);
		Farmer farmer = farmerService.findById(id);

		if (farmer == null) {
			return "redirect:/farmerorganization/";
		}

		List<Organization> org = farmer.getOrganizations();
		org.add(organization);
		List<Farmer> fr = organization.getFarmers();
		fr.add(farmer);

		organizationService.save(organization);
		farmerService.save(farmer);

		return "redirect:/farmerorganization/";
	}

	@RequestMapping(value = "/farmerorganization/approve/{id}", method = RequestMethod.POST)
	public String orgFarmerApproveAddFarmer(@PathVariable("id") int id, Model m, Authentication authentication) {
		// Ice
		// change to method post
		// remove old farmer organization //
		// add new organization to farmer //
		// change status active //
		// remove all user role
		// add user role farmer

		String username = MvcHelper.getUsername(authentication);
		Organization organization = organizationService.findByStaffUsername(username);
		Farmer farmer = farmerService.findById(id);
		if (farmer == null) {
			return "redirect:/farmerorganization/";
		}
		User user = farmer.getUser();
		Role role = roleService.findByNameEng("farmer");

		if (farmer.getUser().getUserStatus().equals("waiting")) {

			if (farmer.getOrganizations() != null) {

				for (Organization org : farmer.getOrganizations()) {

					farmerService.deleteFarmerOrganizationByFarmerIdAndOrganizationId(farmer.getFarmerId(),
							org.getOrganizationId());

				}
				user.setUserStatus("active");
				userService.save(user);

			}
			if (farmer.getUser().getRoles() != null) {
				for (Role r : farmer.getUser().getRoles()) {
					userService.deleteUserRoleByUserIdandRoleId(farmer.getUser().getUserId(), r.getRoleId());
				}

			}

			List<Role> r = farmer.getUser().getRoles();
			r.add(role);
			List<User> u = role.getUsers();
			u.add(farmer.getUser());
			roleService.save(role);
			userService.save(farmer.getUser());

			List<Organization> org = farmer.getOrganizations();
			org.add(organization);
			List<Farmer> fr = organization.getFarmers();
			fr.add(farmer);

			organizationService.save(organization);
			farmerService.save(farmer);
		} else if (farmer.getUser().getUserStatus().equals("active")) {
			List<Organization> org = farmer.getOrganizations();
			org.add(organization);
			List<Farmer> fr = organization.getFarmers();
			fr.add(farmer);

			organizationService.save(organization);
			farmerService.save(farmer);

		}

		return "redirect:/farmerorganization/";
	}

	@RequestMapping(value = "/farmerorganization/farmerinvalid", method = RequestMethod.GET)
	public String orgFarmerFarmerInvalid(Model model) {
		List<Integer> hasFK = new ArrayList<Integer>();
		for (Farmer farmer : farmerService.findByStatus("invalid")) {
			hasFK.add(farmerService.checkFkByFarmerId(farmer.getFarmerId()));
		}
		model.addAttribute("farmers", farmerService.findByStatus("invalid"));
		model.addAttribute("hasFK", hasFK);
		// model.addAttribute("farmers",
		// farmerService.findByOrganizationAndStatus(organization, "invalid"));

		return "farmerorganization/farmerinvalid";
	}

	@RequestMapping(value = "/farmerorganization/updatefarmerinvalid", method = RequestMethod.POST)
	public ResponseEntity<Response<ObjectNode>> orgFarmerUpdatFarmerInvalid(@ModelAttribute("farmer") Farmer farmer,
			Model model) {
		farmer.setAddress(farmer.getAddress().trim());
		//farmer.getUser().setUsername(farmer.getUser().getUsername().trim());
		farmer.getUser().setTitle(farmer.getUser().getTitle().trim());
		farmer.getUser().setFirstName(farmer.getUser().getFirstName().trim());
		farmer.getUser().setLastName(farmer.getUser().getLastName().trim());
		farmer.getUser().setPhoneNo(farmer.getUser().getPhoneNo().trim());
		ResponseEntity<Response<ObjectNode>> responseEntity = validate(farmer);
		if (responseEntity.getStatusCode() == HttpStatus.OK) {
			Farmer f = farmerService.findById(farmer.getFarmerId());

			f.getUser().setUsername(null);
			f.getUser().setUserStatus("invalid");
			f.setFarmerId(farmer.getFarmerId());
			f.setIdcard(null);
			f.setAddress(farmer.getAddress());
			f.getUser().setFirstName(farmer.getUser().getFirstName());
			f.getUser().setLastName(farmer.getUser().getLastName());
			f.getUser().setPhoneNo(farmer.getUser().getPhoneNo());
			f.getUser().setTitle(farmer.getUser().getTitle());
			f.setSubdistrict(farmer.getSubdistrict());
			f.getSubdistrict().setDistrict(farmer.getSubdistrict().getDistrict());
			f.getSubdistrict().getDistrict().setProvince(farmer.getSubdistrict().getDistrict().getProvince());
			userService.save(f.getUser());
			farmerService.save(f);
		}
		return responseEntity;
	}

	@RequestMapping(value = "/farmerorganization/editfarmerinvalid/{id}", method = RequestMethod.GET)
	public String orgFarmerEditFarmerInvalid(@PathVariable("id") int id, Model model) {

		Farmer farmer = farmerService.findById(id);
		if (farmer == null) {
			return "redirect:/farmerorganization/";
		}
		/*
		 * List<String> statusList = new ArrayList<String>();
		 * statusList.add("เปิดการใช้งาน"); statusList.add("ปิดการใช้งาน");
		 * model.addAttribute("statusList", statusList);
		 * 
		 * String status;
		 * 
		 * 
		 * 
		 * if (farmer.getUser().getUserStatus().equals("active")) { status =
		 * "เปิดการใช้งาน";
		 * 
		 * } else {
		 * 
		 * status = "ปิดการใช้งาน";
		 * 
		 * } model.addAttribute("status", status);
		 */

		Province provinceFarmer = farmer.getSubdistrict().getDistrict().getProvince();
		District districtFarmer = farmer.getSubdistrict().getDistrict();
		model.addAttribute("provinceFarmer", provinceFarmer);
		model.addAttribute("districtFarmer", districtFarmer);
		model.addAttribute("subdistrictFarmer", farmer.getSubdistrict());
		model.addAttribute("district", districtService.findByProvinceId(provinceFarmer.getProvinceId()));
		model.addAttribute("subdistrict", subdistrictService.findByDistrictId(districtFarmer.getDistrictId()));

		List<Province> provinces = provinceService.findAll();
		model.addAttribute("provinces", provinces);
		model.addAttribute("farmer", farmer);
		return "/farmerorganization/editfarmerinvalid";
	}

	@RequestMapping(value = "/farmerorganization/edit/{id}", method = RequestMethod.GET)
	public String orgFarmerEdit(@PathVariable("id") int id, Model model) {
		Farmer farmer = farmerService.findById(id);
		if (farmer == null) {
			return "redirect:/farmerorganization/";
		}
		List<String> statusList = new ArrayList<String>();
		statusList.add("เปิดการใช้งาน");
		statusList.add("ปิดการใช้งาน");
		model.addAttribute("statusList", statusList);

		String status;

		if (farmer.getUser().getUserStatus().equals("active")) {
			status = "เปิดการใช้งาน";

		} else {

			status = "ปิดการใช้งาน";

		}
		model.addAttribute("status", status);

		Province provinceFarmer = farmer.getSubdistrict().getDistrict().getProvince();
		District districtFarmer = farmer.getSubdistrict().getDistrict();
		model.addAttribute("provinceFarmer", provinceFarmer);
		model.addAttribute("districtFarmer", districtFarmer);
		model.addAttribute("subdistrictFarmer", farmer.getSubdistrict());
		model.addAttribute("provinces", provinceService.findAll());
		model.addAttribute("district", districtService.findByProvinceId(provinceFarmer.getProvinceId()));
		model.addAttribute("subdistrict", subdistrictService.findByDistrictId(districtFarmer.getDistrictId()));

		model.addAttribute("farmer", farmer);
		return "/farmerorganization/edit";
	}

	@RequestMapping(value = "/farmerorganization/search", method = RequestMethod.GET)
	public String orgFarmerSearch(Model model) {
		return "farmerorganization/search";
	}

	@RequestMapping(value = "/farmerorganization/find", method = RequestMethod.POST)
	public String orgFarmerFind(@ModelAttribute("farmer") Farmer farmer, Model model, Authentication authentication) {

		String username = MvcHelper.getUsername(authentication);
		Organization organization = organizationService.findByStaffUsername(username);

		int check = 0;
		String message = "", status = "";

		Farmer fInOrg = farmerService.findByOrgannizationAndUsername(organization, farmer.getUser().getUsername());
		if (fInOrg != null) {
			if (fInOrg.getUser().getUserStatus().equals("waiting")) {
				check = 2;
				message = "เกษตรอยู่ในหน่วยงานแล้ว สถานะรออนุมัติ";
				status = "รออนุมัติ";
			} else {
				check = 1;
				message = "เกษตรอยู่ในหน่วยงานแล้ว";
				if (fInOrg.getUser().getUserStatus().equals("active")) {
					status = "เปิดการใช้งาน";
				} else if (fInOrg.getUser().getUserStatus().equals("inactive")) {
					status = "ปิดการใช้งาน";
				}
			}
			model.addAttribute("farmer", fInOrg);
		} else {
			Farmer fNotInOrg = farmerService.findfarmerNotinOrgannizationbyusername(organization,
					farmer.getUser().getUsername());
			if (fNotInOrg != null) {

				if (fNotInOrg.getUser().getUserStatus().equals("active")) {
					check = 3;
					message = "พบเกษตรจากหน่วยงานอื่น";
					status = "เปิดการใช้งาน";
				} else if (fNotInOrg.getUser().getUserStatus().equals("inactive")) {
					check = 3;
					message = "พบเกษตรกรจากหน่วยงานอื่น";
					status = "ปิดการใช้งาน";
				} else if (fNotInOrg.getUser().getUserStatus().equals("waiting")) {
					check = 4;
					message = "พบเกษตรจากหน่วยงานอื่น - สถานะรออนุมัติ";
					status = "รออนุมัติ";
				}
				model.addAttribute("farmer", fNotInOrg);
			} else {
				check = 0;
				message = "ไม่พบบัญชีผู้ใช้ของเกษตรกรตามที่ระบุ";
			}
		}
		model.addAttribute("status", status);
		model.addAttribute("check", check);
		model.addAttribute("message", message);
		return "farmerorganization/search";
	}

	@RequestMapping(value = "/farmerorganization/detail/{id}", method = RequestMethod.GET)
	public String farmerShowDetail(@PathVariable("id") int id, Model model) {

		String status;

		Farmer farmer = farmerService.findById(id);

		if (farmer == null) {
			return "redirect:/farmerorganization/";
		}

		if (farmer.getUser().getUserStatus().equals("active")) {
			status = "เปิดการใช้งาน";

		} else {

			status = "ปิดการใช้งาน";

		}
		model.addAttribute("status", status);
		model.addAttribute("farmer", farmer);

		return "/farmerorganization/detail";
	}

	@RequestMapping(value = "/farmerorganization/duplicateusername", method = RequestMethod.POST)
	public ResponseEntity<Response<String>> duplicateUsername(@ModelAttribute("farmer") Farmer farmer, Model model) {

		User userFarmer = userService.findByUsername(farmer.getUser().getUsername() + gmail);
		// System.out.println(userFarmer.getFirstName()+"......");
		Response<String> resp = new Response<String>();
		resp.setHttpStatus(HttpStatus.OK);

		if (userFarmer != null) {
			resp.setMessage("ชื่อผู้ใช้ซ้ำ");
			resp.setHttpStatus(HttpStatus.NOT_ACCEPTABLE);
			return new ResponseEntity<Response<String>>(resp, resp.getHttpStatus());
		}

		return new ResponseEntity<Response<String>>(resp, resp.getHttpStatus());
	}

	@RequestMapping(value = "/farmerorganization/create", method = RequestMethod.GET)
	public String orgFarmerDetail(Model model) {

		List<String> statusList = new ArrayList<String>();
		statusList.add("เปิดการใช้งาน");
		statusList.add("ปิดการใช้งาน");

		model.addAttribute("statusList", statusList);
		List<Province> provinces = provinceService.findAll();
		model.addAttribute("provinces", provinces);

		return "farmerorganization/create";
	}

	@RequestMapping(value = "/farmerorganization/save", method = RequestMethod.POST)
	public ResponseEntity<Response<ObjectNode>> registInfoFarmerSave(@ModelAttribute("farmer") Farmer farmer,
			BindingResult bindingResult, Model m, Authentication authentication) {
		farmer.setAddress(farmer.getAddress().trim());
		farmer.getUser().setTitle(farmer.getUser().getTitle().trim());
		farmer.getUser().setFirstName(farmer.getUser().getFirstName().trim());
		farmer.getUser().setLastName(farmer.getUser().getLastName().trim());
		farmer.getUser().setPhoneNo(farmer.getUser().getPhoneNo().trim());
		ResponseEntity<Response<ObjectNode>> responseEntity = validate(farmer);
		if (responseEntity.getStatusCode() == HttpStatus.OK) {
			String username = MvcHelper.getUsername(authentication);
			Organization organization = organizationService.findByStaffUsername(username);
			Role role = roleService.findByNameEng("farmer");
			System.out.println("save farmerInvail");
			farmer.getUser().setUserStatus("invalid");
			farmer.setIdcard(null);
			farmer.setPdpastatus("No");
			farmer.getUser().setLatestLogin(new Date());
			farmer.getUser().setRequestInfoStatus("No");

			List<Organization> org = farmer.getOrganizations();
			org.add(organization);
			List<Farmer> fr = organization.getFarmers();
			fr.add(farmer);

			List<Role> r = farmer.getUser().getRoles();
			r.add(role);
			List<User> u = role.getUsers();
			u.add(farmer.getUser());

			roleService.save(role);
			userService.save(farmer.getUser());

			organizationService.save(organization);
			farmerService.save(farmer);
		}
		// farmerService.save(farmer);

		return responseEntity;

	}

	private ResponseEntity<Response<ObjectNode>> validate(Farmer farmer) {
		ObjectNode responObject = new ObjectMapper().createObjectNode();

//		Farmer farmerIn = farmerService.findByUsername(farmer.getUser().getUsername());
//		if (farmerIn != null && farmerIn.getFarmerId() != farmer.getFarmerId()) {
//			responObject.put("code", "รหัสหน่วยงานนี้ถูกใช้แล้ว");
//		}
//		System.out.println("------");
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (farmer.getUser().getUserStatus() == null) {
			farmer.getUser().setUserStatus("waiting");
		}
		if (farmer.getSubdistrict().getSubdistrictId() == null) {
			responObject.put("provinces", "กรุณาเลือกจังหวัด");
		}
		if (farmer.getUser().getPhoneNo().length()!=10) {
			responObject.put("phoneNo", "ตัวเลขควรมี 10 หลัก");
		}
//		System.out.println(farmer.getUser().getFirstName());
//		System.out.println(farmer.getUser().getLastName());
//		System.out.println(farmer.getUser().getTitle());
//		System.out.println(farmer.getUser().getPhoneNo());
//		System.out.println(farmer.getUser().getUserStatus());
		Set<ConstraintViolation<User>> violations = validator.validate(farmer.getUser());
		Set<ConstraintViolation<Farmer>> violationsfarmer = validator.validate(farmer);
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
				// System.out.println(list);
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
		if (violationsfarmer.size() > 0) {
			violationsfarmer.stream().forEach((ConstraintViolation<?> error) -> {
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
				// System.out.println(list);
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

	@RequestMapping(value = "/farmerorganization/deletefarmerinvalid/{id}", method = RequestMethod.GET)
	public String deleteFarmerInvalid(@PathVariable("id") int id, Model m, Authentication authentication) {
		if (farmerService.findById(id) != null && userService.findById(id) != null) {
			farmerService.deletebyid(id);

			userService.deleteById(id);
		}
		return "redirect:/farmerorganization/farmerinvalid";
	}

	@RequestMapping(value = "/farmerorganization/delete/{id}", method = RequestMethod.GET)
	public String orgFarmerDelete(@PathVariable("id") int id, Model m, Authentication authentication) {

		String username = MvcHelper.getUsername(authentication);
		Organization organization = organizationService.findByStaffUsername(username);
		Farmer farmer = farmerService.findById(id);
		if(farmer == null) {
			return "redirect:/farmerorganization/";
		}
		/*
		 * List<Organization> org = farmer.getOrganizations();
		 * 
		 * Organization organi = new Organization();
		 * 
		 * for (Organization or : org) { if (or.getOrganizationId() ==
		 * organization.getOrganizationId()) { organi = or; } }
		 * 
		 * org.remove(org.indexOf(organi));
		 * 
		 * 
		 * farmerService.save(farmer);
		 */

		farmerService.deleteFarmerOrganizationByFarmerIdAndOrganizationId(farmer.getFarmerId(),
				organization.getOrganizationId());

		return "redirect:/farmerorganization/index";
	}

}
