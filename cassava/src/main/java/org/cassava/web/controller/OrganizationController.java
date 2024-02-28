package org.cassava.web.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.cassava.api.util.model.Response;
import org.cassava.model.Organization;
import org.cassava.model.Role;
import org.cassava.model.Staff;
import org.cassava.model.User;
import org.cassava.services.FarmerService;
import org.cassava.services.OrganizationService;
import org.cassava.services.RoleService;
import org.cassava.services.StaffService;
import org.cassava.services.UserService;
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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Controller
public class OrganizationController {

	@Autowired
	private OrganizationService organizationService;

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private StaffService staffService;

	@Autowired
	private FarmerService farmerService;

	@RequestMapping(value = { "/organization/index", "/organization/", "/organization" }, method = RequestMethod.GET)
	public String index(Model model) {
		List<Organization> organizations = organizationService.findAll();
		List<Integer> countStaffFieldRegistrar = new ArrayList<Integer>();
		List<Integer> countStaff = new ArrayList<Integer>();
		List<Integer> countFarmer = new ArrayList<Integer>();
		List<Integer> hasFK = new ArrayList<Integer>();
		for (Organization or : organizations) {
			countStaffFieldRegistrar
					.add(organizationService.countStaffInOrganizationRole(or.getOrganizationId(), "fieldRegistrar"));
			countStaff.add(staffService.findByOrganizationAndStatusValid(or).size());
			countFarmer.add(farmerService.findByOrganizationAndStatusValid(or).size());
			hasFK.add(organizationService.checkFkByOrganizationId(or.getOrganizationId()));
		}
		model.addAttribute("organizations", organizations);
		model.addAttribute("countStaffFieldRegistrar", countStaffFieldRegistrar);
		model.addAttribute("countStaff", countStaff);
		model.addAttribute("countFarmer", countFarmer);
		model.addAttribute("hasFK", hasFK);
		return "organization/index";
	}

	@RequestMapping(value = "/organization/create", method = RequestMethod.GET)
	public String create(Model model) {
		List<String> types = new ArrayList<String>();
		types.add("ภายในหน่วยงาน");
		types.add("ทั้งหมด");
		model.addAttribute("organizationTypes", types);
		return "organization/create";
	}

	@RequestMapping(value = "/organization/save", method = RequestMethod.POST)
	public ResponseEntity<Response<ObjectNode>> registInfoFarmerSave(
			@ModelAttribute("organization") Organization organization, Model m, Authentication authentication) {
		
		organization.setName(organization.getName().trim());
		organization.setPhone(organization.getPhone().trim());
		organization.setCode(organization.getCode().trim());
		//organization.setAccessInfoLevel(organization.getAccessInfoLevel().trim());
		
		ResponseEntity<Response<ObjectNode>> responseEntity = validate(organization);
		if (responseEntity.getStatusCode() == HttpStatus.OK) {
			organization.setAccessInfoLevel("ภายในหน่วยงาน");
			
			organizationService.save(organization);
		}
		return responseEntity;
	}

	@RequestMapping(value = "/organization/edit/{id}", method = RequestMethod.GET)
	public String save(@PathVariable("id") int id, Model model) {
		
		if (organizationService.findById(id) == null) {
			return "redirect:/organization/";
		}
		model.addAttribute("organization", organizationService.findById(id));
		List<String> types = new ArrayList<String>();
		types.add("ภายในหน่วยงาน");
		types.add("ทั้งหมด");
		model.addAttribute("organizationTypes", types);
		return "organization/edit";

	}

	@RequestMapping(value = "/organization/update", method = RequestMethod.POST)
	public ResponseEntity<Response<ObjectNode>> update(@ModelAttribute("organization") Organization organization,
			Model model) {
		organization.setName(organization.getName().trim());
		organization.setPhone(organization.getPhone().trim());
		organization.setCode(organization.getCode().trim());
		ResponseEntity<Response<ObjectNode>> responseEntity = validate(organization);
		if (responseEntity.getStatusCode() == HttpStatus.OK) {
			organization.setAccessInfoLevel("ภายในหน่วยงาน");
			
			organizationService.save(organization);
		}
		return responseEntity;
	}

	@RequestMapping(value = "/organization/delete/{id}", method = RequestMethod.GET)
	public String deleteOrganization(@PathVariable("id") int id, Model model) {
		if (organizationService.checkFkByOrganizationId(id) == 0 && organizationService.findById(id) != null) {
			organizationService.deleteById(id);
		}
		return "redirect:/organization/";

	}

	private ResponseEntity<Response<ObjectNode>> validate(Organization organization) {
		ObjectNode responObject = new ObjectMapper().createObjectNode();

		Organization organizationIn = organizationService.findByCode(organization.getCode());
		if (organizationIn != null && organizationIn.getOrganizationId() != organization.getOrganizationId()) {
			responObject.put("code", "รหัสหน่วยงานนี้ถูกใช้แล้ว");
		}

		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<Organization>> violations = validator.validate(organization);
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

	@RequestMapping(value = "/organization/info/{id}", method = RequestMethod.GET)
	public String info(@PathVariable("id") int id, Model model) {
		if (organizationService.findById(id) == null) {
			return "redirect:/organization/";
		}
		model.addAttribute("organization", organizationService.findById(id));
		List<Staff> staffInRole = staffService.findByOrganizationAndUserrole(id, "fieldRegistrar");
		model.addAttribute("staffs", staffInRole);
		return "organization/info";
	}

	@RequestMapping(value = "/organization/fieldrole/edit/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") int id, Model model) {
		if (organizationService.findById(id) == null
				|| staffService.findByOrganizationAndStatusValidAndNotUserrole(id, "fieldRegistrar").isEmpty()) {
			return "redirect:/organization/";
		}
		model.addAttribute("staffs", staffService.findByOrganizationAndStatusValidAndNotUserrole(id, "fieldRegistrar"));
		model.addAttribute("organization", organizationService.findById(id));
		return "organization/createFieldRole";
	}

	@RequestMapping(value = "/organization/fieldrole/update/{id}", method = RequestMethod.GET)
	public String addFieldRole(@PathVariable("id") int id, Model model) {
		User u = userService.findById(id);
		if (u == null) {
			return "redirect:/organization/";
		}
		List<Role> roles = u.getRoles();
		roles.add(roleService.findByNameEng("fieldRegistrar"));
		userService.save(u);
		return "redirect:/organization/info/" + u.getStaff().getOrganization().getOrganizationId();

	}

	@RequestMapping(value = "/organization/staff/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") int id, Model model) {
		User u = userService.findById(id);
		if (u == null) {
			return "redirect:/organization/";
		}
		List<Role> roles = u.getRoles();
		for (Role r : roles) {
			if (r.getNameEng().equals("fieldRegistrar")) {
				roles.remove(r);
				break;
			}
		}
		userService.save(u);
		return "redirect:/organization/info/" + u.getStaff().getOrganization().getOrganizationId();

	}
}