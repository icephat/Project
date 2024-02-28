package org.cassava.web.controller;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
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
import org.cassava.model.Subdistrict;
import org.cassava.model.User;
import org.cassava.services.DistrictService;
import org.cassava.services.FarmerService;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Controller
public class FarmerController {

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

	public void setPageIndex(int sizeItem, Model model, int pageCurrent, int pageSize) {
		int firstItem = 0, lastItem = 0;
		if (sizeItem > 0) {
			firstItem = (pageSize * (pageCurrent - 1)) + 1;
			lastItem = firstItem + sizeItem - 1;
		}
		model.addAttribute("firstItem", firstItem);
		model.addAttribute("lastItem", lastItem);
	}

	@RequestMapping(value = { "/farmer/index", "/farmer/", "/farmer" }, method = RequestMethod.GET)
	public String index(Model model, Authentication authentication) {
		model.addAttribute("sizePages", Arrays.asList(10, 25, 50));
		return "/farmer/index";
	}

	@RequestMapping(value = "/farmer/page/{page}/value/{value}", method = RequestMethod.GET)
	public String staffPage(@PathVariable("page") int page, @PathVariable("value") int value,
			@RequestParam(value = "key", required = false) String key, Authentication authentication, Model model) {
		User user = userService.findByUsername(MvcHelper.getUsername(authentication));

		List<Farmer> farmers;
		if ((key == null || key == "")) {
			if (roleService.findByUserIdAndRoleName(user.getUserId(), "systemAdmin") != null) {
				farmers = farmerService.findByStatusValid(page, value);
			} else {
				Organization organization = user.getStaff().getOrganization();
				farmers = farmerService.findByOrganizationAndStatusValid(organization, page, value);
			}
		} else {
			if (roleService.findByUserIdAndRoleName(user.getUserId(), "systemAdmin") != null) {
				farmers = farmerService.findByStatusValidAndKey(key, page, value);
			} else {
				Organization organization = user.getStaff().getOrganization();
				farmers = farmerService.findByOrganizationAndStatusValidAndKey(organization, key, page, value);
			}
		}
		model.addAttribute("farmers", farmers);
		setPageIndex(farmers.size(), model, page, value);
		Format formatter = new SimpleDateFormat("d MMM yyyy", new Locale("th", "TH"));
		model.addAttribute("formatter", formatter);
		model.addAttribute("active", "active");
		model.addAttribute("inactive", "inactive");
		return "/farmer/farmerTable";
	}

	@RequestMapping(value = { "/farmer/total" }, method = RequestMethod.GET)
	@ResponseBody
	public int selectStaffTableTotal(@RequestParam(value = "key", required = false) String key,
			Authentication authentication, Model model) {
		int totalItem;
		User user = userService.findByUsername(MvcHelper.getUsername(authentication));

		if ((key == null || key == "")) {
			if (roleService.findByUserIdAndRoleName(user.getUserId(), "systemAdmin") != null) {
				totalItem = farmerService.findByStatusValid().size();
			} else {
				Organization organization = user.getStaff().getOrganization();
				totalItem = farmerService.findByOrganizationAndStatusValid(organization).size();
			}
		} else {
			if (roleService.findByUserIdAndRoleName(user.getUserId(), "systemAdmin") != null) {
				totalItem = farmerService.findByStatusValidAndKey(key).size();
			} else {
				Organization organization = user.getStaff().getOrganization();
				totalItem = farmerService.findByOrganizationAndStatusValidAndKey(organization, key).size();
			}
		}
		return totalItem;
	}

	@RequestMapping(value = "/farmer/approve", method = RequestMethod.GET)
	public String farmerApprove(Model model, Authentication authentication) {

		User u = userService.findByUsername(MvcHelper.getUsername(authentication));
		if (roleService.findByUserIdAndRoleName(u.getUserId(), "systemAdmin") != null) {
			model.addAttribute("farmers", farmerService.findByStatus("waiting"));
		} else {
			Organization organization = u.getStaff().getOrganization();
			model.addAttribute("farmers", farmerService.findByOrganizationAndStatus(organization, "waiting"));
		}
		String active = "active";
		String inactive = "inactive";
		model.addAttribute("active", active);
		model.addAttribute("inactive", inactive);

		return "farmer/farmerapprove";
	}

	@RequestMapping(value = "/farmer/{id}/approve/save", method = RequestMethod.GET)
	public String approve(@PathVariable("id") int id, Model m, Authentication authentication) {
			User user = userService.findById(id);
			if(user == null) {
				return "redirect:/farmer/approve";
			}
			user.setUserStatus("active");

			List<Role> roleUser = user.getRoles();

			while (user.getRoles().size() > 0) {
				roleUser.remove(0);
			}

			roleUser.add(roleService.findByNameEng("farmer"));
			userService.save(user);
			return "redirect:/farmer/approve";
	}

	@RequestMapping(value = "/farmer/{id}/detail", method = RequestMethod.GET)
	public String FarmerDetail(@PathVariable("id") int id, Model model) {
			Farmer farmer = farmerService.findById(id);
			if(farmer == null) {
				return "redirect:/farmer/approve";
			}
			model.addAttribute("farmer", farmer);

			Province provinceFarmer = farmer.getSubdistrict().getDistrict().getProvince();
			District districtFarmer = farmer.getSubdistrict().getDistrict();

			model.addAttribute("provinceFarmer", provinceFarmer);
			model.addAttribute("districtFarmer", districtFarmer);
			model.addAttribute("subdistrictFarmer", farmer.getSubdistrict());

			model.addAttribute("district", districtService.findByProvinceId(provinceFarmer.getProvinceId()));
			model.addAttribute("subdistrict", subdistrictService.findByDistrictId(districtFarmer.getDistrictId()));
			model.addAttribute("provinces", provinceService.findAll());

			Format formatter = new SimpleDateFormat("d MMM yyyy", new Locale("th", "TH"));
			model.addAttribute("formatter", formatter);

			return "/farmer/detail";
	}

	@RequestMapping(value = "/farmer/delete/{id}", method = RequestMethod.GET)
	public String deleteFarmer(@PathVariable("id") int id, Model model, Authentication authentication) {
		if (farmerService.findById(id) == null) {
			return "redirect:/farmer/";
		}
		User u = userService.findByUsername(MvcHelper.getUsername(authentication));
		if (roleService.findByUserIdAndRoleName(u.getUserId(), "systemAdmin") != null) {
			farmerService.deletebyid(id);
			userService.deleteById(id);
			return "redirect:/farmer/";
		} else if (farmerService.findById(id).getOrganizations().contains(u.getStaff().getOrganization())) {
			farmerService.deletebyid(id);
			userService.deleteById(id);
			return "redirect:/farmer/";
		}
		return "redirect:/farmer/";
	}

	@RequestMapping(value = "/farmer/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") int id, Model model) {
		LinkedHashMap<String, String> statusMap = new LinkedHashMap<String, String>();
		statusMap.put("active", "เปิดการใช้งาน");
		statusMap.put("inactive", "ปิดการใช้งาน");
		model.addAttribute("statusMap", statusMap);

		Farmer farmer = farmerService.findById(id);
		if(farmer == null) {
			return "redirect:/farmer/";
		}
		model.addAttribute("farmer", farmer);

		Province provinceFarmer = farmer.getSubdistrict().getDistrict().getProvince();
		District districtFarmer = farmer.getSubdistrict().getDistrict();
		model.addAttribute("provinceFarmer", provinceFarmer);
		model.addAttribute("districtFarmer", districtFarmer);
		model.addAttribute("subdistrictFarmer", farmer.getSubdistrict());

		model.addAttribute("district", districtService.findByProvinceId(provinceFarmer.getProvinceId()));
		model.addAttribute("subdistrict", subdistrictService.findByDistrictId(districtFarmer.getDistrictId()));
		model.addAttribute("provinces", provinceService.findAll());

		return "farmer/edit";
	}

	@RequestMapping(value = "/farmer/approve/update", method = RequestMethod.POST)
	public ResponseEntity<Response<ObjectNode>> farmerDetailUpdate(@ModelAttribute("farmer") Farmer farmer, Model model,
			Authentication authentication) {
		
		farmer.setAddress(farmer.getAddress().trim());
		farmer.getUser().setUsername(farmer.getUser().getUsername().trim());
		farmer.getUser().setTitle(farmer.getUser().getTitle().trim());
		farmer.getUser().setFirstName(farmer.getUser().getFirstName().trim());
		farmer.getUser().setLastName(farmer.getUser().getLastName().trim());
		farmer.getUser().setPhoneNo(farmer.getUser().getPhoneNo().trim());

		ResponseEntity<Response<ObjectNode>> responseEntity = validate(farmer);
		if (responseEntity.getStatusCode() == HttpStatus.OK) {
			User u = userService.findById(farmer.getFarmerId());
			u.getFarmer().setIdcard(null);
			u.getFarmer().setAddress(farmer.getAddress());
			u.getFarmer().setSubdistrict(farmer.getSubdistrict());

			u.setTitle(farmer.getUser().getTitle());
			u.setFirstName(farmer.getUser().getFirstName());
			u.setLastName(farmer.getUser().getLastName());
			u.setPhoneNo(farmer.getUser().getPhoneNo());
			u.setUserStatus(farmer.getUser().getUserStatus());
			userService.save(u);
		}

		return responseEntity;
	}

	@RequestMapping(value = "/farmer/update", method = RequestMethod.POST)
	public ResponseEntity<Response<ObjectNode>> orgFarmerUpdate(@ModelAttribute("farmer") Farmer farmer, Model model,
			Authentication authentication) {
		farmer.setAddress(farmer.getAddress().trim());
		farmer.getUser().setUsername(farmer.getUser().getUsername().trim());
		farmer.getUser().setTitle(farmer.getUser().getTitle().trim());
		farmer.getUser().setFirstName(farmer.getUser().getFirstName().trim());
		farmer.getUser().setLastName(farmer.getUser().getLastName().trim());
		farmer.getUser().setPhoneNo(farmer.getUser().getPhoneNo().trim());
		ResponseEntity<Response<ObjectNode>> responseEntity = validate(farmer);
		if (responseEntity.getStatusCode() == HttpStatus.OK) {
			User u = userService.findById(farmer.getFarmerId());
			Farmer f = u.getFarmer();

			f.setIdcard(null);
			f.setAddress(farmer.getAddress());
			Subdistrict subdistrict = subdistrictService.findById(farmer.getSubdistrict().getSubdistrictId());
			f.setSubdistrict(subdistrict);

			u.setTitle(farmer.getUser().getTitle());
			u.setFirstName(farmer.getUser().getFirstName());
			u.setLastName(farmer.getUser().getLastName());
			u.setPhoneNo(farmer.getUser().getPhoneNo());
			u.setUserStatus(farmer.getUser().getUserStatus());

			farmerService.save(f);
			userService.save(u);
		}
		return responseEntity;
	}

	private ResponseEntity<Response<ObjectNode>> validate(Farmer farmer) {
		ObjectNode responObject = new ObjectMapper().createObjectNode();

		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<Farmer>> violations = validator.validate(farmer);
		if (violations.size() > 0) {
			violations.stream().forEach((ConstraintViolation<?> error) -> {
				responObject.put(error.getPropertyPath().toString(), error.getMessage());
			});
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

}
