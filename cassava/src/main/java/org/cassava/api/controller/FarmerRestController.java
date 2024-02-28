package org.cassava.api.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.cassava.api.util.model.Response;
import org.cassava.model.Farmer;
import org.cassava.model.Organization;
import org.cassava.model.Role;
import org.cassava.model.Subdistrict;
import org.cassava.model.User;
import org.cassava.services.FarmerService;
import org.cassava.services.OrganizationService;
import org.cassava.services.RoleService;
import org.cassava.services.SubdistrictService;
import org.cassava.services.UserService;
import org.cassava.util.MvcHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@RequestMapping("/api/farmer")
public class FarmerRestController {
	
	@Autowired
	private FarmerService farmerService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private OrganizationService organizationService;
	
	@Autowired
	private SubdistrictService subdistrictService;
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Response<ObjectNode>> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Response<ObjectNode> res = new Response<>();
		res.setHttpStatus(HttpStatus.BAD_REQUEST);

		ObjectMapper mapper = new ObjectMapper();

		ObjectNode responObject = mapper.createObjectNode();

		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldname = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			responObject.put(fieldname, errorMessage);
		});
		res.setMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
		res.setBody(responObject);
		return new ResponseEntity<Response<ObjectNode>>(res, res.getHttpStatus());
	}
	
	@GetMapping("/organization/page/{page}/value/{value}")
	public ResponseEntity<Response<List<User>>> findByOrganization(@PathVariable("page")int page, @PathVariable("value")int value) {
		Response<List<User>> res = new Response<>();
		try {
			String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
			User user = userService.findByUsername(userName);
			List<User> farmers = farmerService.findUserByOrganization(user.getStaff().getOrganization().getOrganizationId(), page, value);

			res.setBody(farmers);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<List<User>>>(res, res.getHttpStatus());

		} catch (Exception ex) {
			res.setMessage(ex.getMessage());
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<List<User>>>(res, res.getHttpStatus());
		}
	}

	@PostMapping("/farmerorganization")
	public ResponseEntity<Response<Farmer>> createFarmerOrganization(@RequestBody String rawFarmer) {

		Response<Farmer> res = new Response<>();
		ObjectMapper mapper = new ObjectMapper();
		try {
			String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
			Organization organization = organizationService.findByStaffUsername(userName);
			JsonNode jsonNode = mapper.readTree(rawFarmer);
			int subDistrictId = jsonNode.get("subdistrict").asInt();
			Subdistrict subdistrict = subdistrictService.findById(subDistrictId);
			Role role = roleService.findByNameEng("farmer");
			User user = new User();
			
			String firstName = jsonNode.get("firstName").asText();
			String title = jsonNode.get("title").asText();
			String lastName = jsonNode.get("lastName").asText();
			String phoneNo = jsonNode.get("phoneNo").asText();
			String address = jsonNode.get("address").asText();
			
			user.setFirstName(firstName);
			user.setTitle(title);
			user.setLastName(lastName);
			user.setPhoneNo(phoneNo);
			user.setUserStatus("invalid");
			
			Farmer farmer = new Farmer(user,address,"Yes");
			farmer.setIdcard(null);
			farmer.getUser().setLatestLogin(new Date());
			farmer.getUser().setRequestInfoStatus("No");
			farmer.setSubdistrict(subdistrict);
			
			List<Organization> org = farmer.getOrganizations();
			org.add(organization);
			List<Farmer> fr = organization.getFarmers();
			fr.add(farmer);

			List<Role> r = farmer.getUser().getRoles();
			r.add(role);
			List<User> u = role.getUsers();
			u.add(farmer.getUser());
			
			roleService.save(role);
			userService.save(user);
			organizationService.save(organization);
			farmerService.save(farmer);
			
			res.setBody(farmer);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<Farmer>>(res, res.getHttpStatus());

		} catch (Exception ex) {
			res.setMessage(ex.getMessage());
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<Farmer>>(res, res.getHttpStatus());
		}
	}
	
	@PostMapping("/search")
	public ResponseEntity<Response<List<User>>> findByOrganizationAndFirstNameAndLastNameAndProvinceName(@RequestBody String key) {

		Response<List<User>> res = new Response<>();
		try {
			String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
			User user = userService.findByUsername(userName);
			List<User> farmers = farmerService.findUserByOrganizationAndFirstNameAndLastNameAndProvinceName(user.getStaff().getOrganization().getOrganizationId(),key);
			res.setBody(farmers);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<List<User>>>(res, res.getHttpStatus());

		} catch (Exception ex) {
			res.setMessage(ex.getMessage());
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<List<User>>>(res, res.getHttpStatus());
		}
	}
	
}
