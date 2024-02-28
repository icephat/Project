package org.cassava.api.controller;

import java.util.List;

import org.cassava.api.util.model.Response;
import org.cassava.model.Field;
import org.cassava.model.Role;
import org.cassava.model.Survey;
import org.cassava.model.User;
import org.cassava.services.FieldService;
import org.cassava.services.SurveyService;
import org.cassava.services.UserService;
import org.cassava.util.MvcHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

	@Autowired
	private UserService userService;

	@Autowired
	private FieldService fieldService;

	@Autowired
	private SurveyService surveyService;

	@GetMapping("/{userid}")
	public ResponseEntity<Response<User>> findUserById(@PathVariable int userid) {
		Response<User> res = new Response<>();
		try {
			User user = userService.findById(userid);
			// List<Field> fields = fieldService.findByUserInField(userid);
			// fields = fieldService.findByFieldsAndSubDistrictId(fields, subid);
			// List<User> userInFields = userService.findByFieldsAndRole(fields,
			// "farmerOwner");
			// userInFieldService.
			// List<Subdistrict> subdistricts =
			// subdistrictService.findByUserinField(userid);
			res.setBody(user);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<User>>(res, res.getHttpStatus());
		} catch (Exception ex) {
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<User>>(res, res.getHttpStatus());
		}

	}

	@GetMapping("/{userid}/subdistricts/{subid}")
	public ResponseEntity<Response<List<User>>> findOwnerBySubDistrictId(@PathVariable int userid,
			@PathVariable int subid) {
		Response<List<User>> res = new Response<>();
		try {
			List<Field> fields = fieldService.findByUserInField(userid);
			fields = fieldService.findByFieldsAndSubDistrictId(fields, subid);
			List<User> userInFields = userService.findByFieldsAndRole(fields, "farmerOwner");
			// userInFieldService.
			// List<Subdistrict> subdistricts =
			// subdistrictService.findByUserinField(userid);
			res.setBody(userInFields);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<List<User>>>(res, res.getHttpStatus());
		} catch (Exception ex) {
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<List<User>>>(res, res.getHttpStatus());
		}

	}

	@GetMapping("/{userId}/survey")
	public ResponseEntity<Response<List<Survey>>> findSurveyByUserId(@PathVariable int userId) {
		Response<List<Survey>> res = new Response<>();
		try {
			List<Survey> surveys = surveyService.findByUserInField(userId);
			res.setBody(surveys);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<List<Survey>>>(res, res.getHttpStatus());
		} catch (Exception ex) {
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<List<Survey>>>(res, res.getHttpStatus());
		}
	}

	@GetMapping(value = "/owner/field/{fieldId}")
	public ResponseEntity<Response<User>> findOwnerByfieldIdAndRole(@PathVariable int fieldId) {
		Response<User> res = new Response<>();
		try {
			String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());

			User user = userService.findByFieldIdAndRoleAndUser(fieldId, "farmerOwner", userName).get(0);

			res.setBody(user);
			
			res.setHttpStatus(HttpStatus.OK);
			
			return new ResponseEntity<Response<User>>(res, res.getHttpStatus());
		} catch (Exception ex) {

			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<User>>(res, res.getHttpStatus());
		}

	}
	
	@GetMapping(value = "/role")
	public ResponseEntity<Response<?>> findRoleByUser() {
		Response<ObjectNode> res = new Response<>();
		ObjectMapper mapper = new ObjectMapper();
		try {
			String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
			User user = userService.findByUsername(userName);
			List<Role> roles = user.getRoles();
			ArrayNode array = mapper.valueToTree(roles);
			ObjectNode responObject = mapper.createObjectNode();
			responObject.put("userId", user.getUserId());
			responObject.putArray("role").addAll(array);
			
			res.setBody(responObject);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<?>>(res, res.getHttpStatus());
		} catch (Exception ex) {
			res.setMessage(ex.getLocalizedMessage());
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<?>>(res, res.getHttpStatus());
		}

	}
}
