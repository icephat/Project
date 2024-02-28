package org.cassava.api.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.cassava.api.util.model.Response;
import org.cassava.model.Farmer;
import org.cassava.model.Field;
import org.cassava.model.Organization;
import org.cassava.model.Planting;
import org.cassava.model.Staff;
import org.cassava.model.Role;
import org.cassava.model.Subdistrict;
import org.cassava.model.SurveyPoint;
import org.cassava.model.User;
import org.cassava.model.UserInField;
import org.cassava.model.UserInFieldId;
import org.cassava.model.dto.FieldSearchDTO;
import org.cassava.services.FarmerService;
import org.cassava.services.FieldService;
import org.cassava.services.OrganizationService;
import org.cassava.services.PlantingService;
import org.cassava.services.StaffService;
import org.cassava.services.RoleService;
import org.cassava.services.SubdistrictService;
import org.cassava.services.UserInFieldService;
import org.cassava.services.UserService;
import org.cassava.util.MvcHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@RequestMapping("/api/fields")
public class FieldRestController {
	@Autowired
	private FieldService fieldService;

	@Autowired
	private PlantingService plantingService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private OrganizationService organizationService;
	
	@Autowired
	private SubdistrictService subdistrictService;
	
	@Autowired
	private FarmerService farmerService;
	
	@Autowired
	private UserInFieldService userInFieldService;

	@Autowired
	private StaffService staffService;

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
	
	@PostMapping(value = "/")
	public ResponseEntity<Response<?>> createField(@RequestBody String rawField) {

		Response<ObjectNode> res = new Response<>();

		ObjectMapper mapper = new ObjectMapper();

		try {
			String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());

			User user = userService.findByUsername(userName);
			JsonNode jsonNode = mapper.readTree(rawField);
			int subDistrictId = jsonNode.get("subdistrict").asInt();
			int farmerId =jsonNode.get("userinfields").asInt();
			Organization organization = user.getStaff().getOrganization();
			Subdistrict subdistrict = subdistrictService.findById(subDistrictId);
			Farmer famer = farmerService.findById(farmerId);
			User user2 = famer.getUser();
			
			
			Field field = mapper.readValue(rawField, Field.class);

			field.setOrganization(organization);
			field.setSubdistrict(subdistrict);
			field.setUserByLastUpdateBy(user);
			field.setLastUpdateDate(new Date());
			field.setCreateDate(new Date());
			field.setUserByCreateBy(user);
			field.setStatus("ใช้งาน");

			field = fieldService.save(field);
			
			List<UserInField> userInFields = new ArrayList<UserInField>();
			
			UserInField farmerOwner = new  UserInField();
			UserInFieldId farmerOwnerId = new UserInFieldId();
			farmerOwnerId.setUserId(farmerId);
			farmerOwnerId.setFieldId(field.getFieldId());
			farmerOwnerId.setRole("farmerOwner");
			farmerOwner.setId(farmerOwnerId);
			farmerOwner.setUser(famer.getUser());
			farmerOwner.setField(field);
			farmerOwner = userInFieldService.save(farmerOwner);
			
			if (roleService.findByUserIdAndRoleName(user2.getUserId(), "farmerOwner") == null) {
				List<Role> userrole = user2.getRoles();
				userrole.add(roleService.findByNameEng("farmerOwner"));
				user2.setRoles(userrole);
				userService.save(user2);
			}
			
			UserInField fieldResponsible = new  UserInField();
			UserInFieldId fieldResponsibleId = new UserInFieldId();
			fieldResponsibleId.setUserId(user.getUserId());
			fieldResponsibleId.setFieldId(field.getFieldId());
			fieldResponsibleId.setRole("staffResponse");
			fieldResponsible.setUser(user);
			fieldResponsible.setField(field);
			fieldResponsible.setId(fieldResponsibleId);
			fieldResponsible = userInFieldService.save(fieldResponsible);
			
			if (roleService.findByUserIdAndRoleName(user.getUserId(), "fieldResponsible") == null) {
				List<Role> userrole = user.getRoles();
				userrole.add(roleService.findByNameEng("fieldResponsible"));
				user.setRoles(userrole);
				userService.save(user);
			}
			
			userInFields.add(farmerOwner);
			userInFields.add(fieldResponsible);
			
			field.setUserinfields(userInFields);
			
			field = fieldService.save(field);
			
			String plantObjStr = mapper.writeValueAsString(field);

			JsonNode jNode = mapper.readTree(plantObjStr);

			
			ObjectNode responObject = mapper.createObjectNode();
			Iterator<String> iterator = jsonNode.fieldNames();
			responObject.put("fieldId", field.getFieldId());
			iterator.forEachRemaining(e -> {
				if (jNode.get(e) != null)
					responObject.put(e.toString(), jNode.get(e));

			});
			
			responObject.put("organization", field.getOrganization().getOrganizationId());
			responObject.put("subdistrictID", field.getSubdistrict().getSubdistrictId());

			res.setBody(responObject);

			res.setHttpStatus(HttpStatus.OK);

			res.setMessage("field created");

		} catch (Exception e) {
			res.setMessage(e.getMessage());
			res.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Response<?>>(res, res.getHttpStatus());
		
	}

	@GetMapping(value = "/{id}/imgBase64")
	public ResponseEntity<Response<?>> findImageByID(@PathVariable int id) {
		String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
		Field fin = fieldService.findById(id, userName);
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode responObject = mapper.createObjectNode();
		Response<ObjectNode> res = new Response();
		if (fin != null) {

			responObject.put("fieldId", fin.getFieldId());

			responObject.put("imgBase64", fieldService.getResizeBase64ImageFile(fin.getFieldId()));

			res.setBody(responObject);

			res.setHttpStatus(HttpStatus.OK);

		} else {
			res.setHttpStatus(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Response<?>>(res, res.getHttpStatus());
	}

	@PostMapping(value = "/imgBase64")
	public ResponseEntity<Response<?>> findImageByID(@RequestBody List<Field> fields) {

		Response<List<ObjectNode>> res = new Response();
		String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
		ObjectMapper mapper = new ObjectMapper();
		List<ObjectNode> objNodes = new ArrayList<ObjectNode>();
		for (Field f : fields) {
			ObjectNode responObject = mapper.createObjectNode();

			Field fin = fieldService.findById(f.getFieldId(), userName);

			if (fin != null) {

				responObject.put("fieldId", f.getFieldId());

				responObject.put("imgBase64", fieldService.getResizeBase64ImageFile(f.getFieldId()));

				objNodes.add(responObject);
			}

		}

		res.setBody(objNodes);

		res.setHttpStatus(HttpStatus.OK);

		return new ResponseEntity<Response<?>>(res, res.getHttpStatus());
	}

	// ***
	/*
	 * @PostMapping(value =
	 * "/organization/{organizationId}/subdistrict/{subdistrictId}") public
	 * ResponseEntity<Response<String>> addnewField(@RequestBody Field
	 * newField, @PathVariable int organizationId,
	 * 
	 * @PathVariable int subdistrictId) {
	 * 
	 * Response<String> res = new Response<>(); try { String userName =
	 * MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication())
	 * ; User user = userService.findByUsername(userName);
	 * newField.setUserByCreateBy(user); newField.setUserByLastUpdateBy(user);
	 * Organization organization = organizationService.findById(organizationId);
	 * Subdistrict subdistrict = subdistrictService.findById(subdistrictId);
	 * newField.setOrganization(organization); newField.setSubdistrict(subdistrict);
	 * fieldService.save(newField); res.setMessage("Find  successfully.");
	 * res.setBody("OK"); res.setHttpStatus(HttpStatus.OK);
	 * 
	 * return new ResponseEntity<Response<String>>(res, res.getHttpStatus()); }
	 * catch (Exception ex) { res.setMessage(ex.toString());
	 * res.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR); return new
	 * ResponseEntity<Response<String>>(res, res.getHttpStatus()); } }
	 */

	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<?>> getFieldById(@PathVariable int id) {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode responObject = mapper.createObjectNode();
		Response<ObjectNode> res = new Response<>();
		try {
			String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
			User user = userService.findByUsername(userName);
			int checkFieldRes = userService.checkUserNameAndRoleEng(user, "fieldResponsible");
			int checkFieldRegis = userService.checkUserNameAndRoleEng(user, "fieldRegistrar");
			int checkStaffRes = userInFieldService.checkFieldIdAndUserIdAndRolename(id, user.getUserId(), "staffResponse");
			Boolean bool = ((checkFieldRes == 1) || (checkFieldRegis == 1) || (checkStaffRes == 1) );
			Field field = fieldService.findById(id, userName);
			responObject = mapper.valueToTree(field);
			responObject.put("editable", bool);
//			field.setImgPath("");
//			field.setImgPath(fieldService.getResizeBase64ImageFile(field.getImgPath())) ;
//			field.setPlantings(null);
			res.setBody(responObject);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<?>>(res, res.getHttpStatus());

		} catch (Exception ex) {
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<?>>(res, res.getHttpStatus());
		}
	}

	@GetMapping(value = "/{id}/planting/page/{page}/value/{value}/date/{millisecondDate}")
	public ResponseEntity<Response<List<Planting>>> getPlantingInfield(@PathVariable int id, @PathVariable int page,
			@PathVariable int value, @PathVariable long millisecondDate) {
		Response<List<Planting>> res = new Response<>();
		try {
			String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
			Date date = new Date(millisecondDate);
			List<Planting> plantings = plantingService.findByFieldIdAndUserAndDate(id, userName, page, value, date);
			res.setBody(plantings);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<List<Planting>>>(res, res.getHttpStatus());

		} catch (Exception ex) {
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<List<Planting>>>(res, res.getHttpStatus());
		}
	}

	@PutMapping(value = "/")
	public ResponseEntity<Response<?>> updateField(@RequestBody String rawField) {
		Response<ObjectNode> res = new Response<>();
		ObjectMapper mapper = new ObjectMapper();

		try {
			String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
			User user = userService.findByUsername(userName);
			
			JsonNode jsonNode = mapper.readTree(rawField);
			int subDistrictId = jsonNode.get("subdistrict").asInt();
			int farmerId =jsonNode.get("userinfields").asInt();
			Subdistrict subdistrict = subdistrictService.findById(subDistrictId);
			Farmer famer = farmerService.findById(farmerId);
			
			Field field = mapper.readValue(rawField, Field.class);
			Field f = fieldService.findById(field.getFieldId());

			f.setSubdistrict(subdistrict);
			f.setUserByLastUpdateBy(user);
			f.setLastUpdateDate(new Date());
			f.clone(field);

			f = fieldService.save(f);
			
			List<UserInField> userInFields = f.getUserinfields();
			List<UserInField> userInFields2 = new ArrayList<UserInField>();
			List<User> user2 = new ArrayList<User>();
			
			for(UserInField userInField : userInFields) {

				if(userInField.getId().getRole().equals("farmerOwner")) {
					user2.add(userInField.getUser());
					userInFieldService.delete(userInField);	
				}
				if(!(userInField.getId().getRole().equals("farmerOwner"))) {
					userInFields2.add(userInField);
				}
			}
			for(User u : user2) {
				if (userInFieldService.findAllByUserIdAndRoleName(u.getUserId(), "farmerOwner").isEmpty()) {
					List<Role> userrole = u.getRoles();
					String rname = null;
					rname = "farmerOwner";
					for (Role r : userrole) {
						if (r.getNameEng().equals(rname)) {
							userrole.remove(r);
							userService.save(u);
							break;
						}
					}
				}
			}
			UserInField farmerOwner = new  UserInField();
			UserInFieldId farmerOwnerId = new UserInFieldId();
			farmerOwnerId.setUserId(farmerId);
			farmerOwnerId.setFieldId(field.getFieldId());
			farmerOwnerId.setRole("farmerOwner");
			farmerOwner.setId(farmerOwnerId);
			farmerOwner.setUser(famer.getUser());
			farmerOwner.setField(field);
			farmerOwner = userInFieldService.save(farmerOwner);
			User user3 = famer.getUser();
			userInFields2.add(farmerOwner);
			f.setUserinfields(userInFields2);
			f = fieldService.save(f);
		
			if (roleService.findByUserIdAndRoleName(user3.getUserId(),"farmerOwner") == null) {
				List<Role> userrole = user3.getRoles();
				userrole.add(roleService.findByNameEng("farmerOwner"));
				user3.setRoles(userrole);
				userService.save(user3);
			}
			
			String plantObjStr = mapper.writeValueAsString(field);

			JsonNode jNode = mapper.readTree(plantObjStr);

			ObjectNode responObject = mapper.createObjectNode();
			Iterator<String> iterator = jsonNode.fieldNames();
			responObject.put("fieldId", f.getFieldId());
			iterator.forEachRemaining(e -> {
				if (jNode.get(e) != null)
					responObject.put(e.toString(), jNode.get(e));

			});
			
			responObject.put("organization", f.getOrganization().getOrganizationId());
			responObject.put("subdistrictID", f.getSubdistrict().getSubdistrictId());

			res.setBody(responObject);

			res.setHttpStatus(HttpStatus.OK);

			res.setMessage("field created");

		} catch (Exception e) {
			res.setMessage(e.getMessage());
			res.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Response<?>>(res, res.getHttpStatus());
		
	}

	@GetMapping(value = "/user/{userid}/page/{page}/value/{value}")
	public ResponseEntity<Response<List<Field>>> getFieldByUserInField(@PathVariable int userid, @PathVariable int page,
			@PathVariable int value, long millisecondDate) {
		Response<List<Field>> res = new Response<>();
		try {
			String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
			Date date = new Date(millisecondDate);
			User user = userService.findByUsername(userName);
			List<Field> fields = fieldService.findByUserInFieldAndDate(userid, user, date, page, value);
			res.setBody(fields);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<List<Field>>>(res, res.getHttpStatus());
		} catch (Exception ex) {
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<List<Field>>>(res, res.getHttpStatus());
		}

	}

	@GetMapping(value = "/page/{page}")
	public ResponseEntity<Response<List<Field>>> getAllField(@PathVariable int page) {
		Response<List<Field>> res = new Response<>();
		try {
			String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
			List<Field> fieldList = fieldService.findAll(userName, page);
			res.setBody(fieldList);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<List<Field>>>(res, res.getHttpStatus());

		} catch (Exception ex) {
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<List<Field>>>(res, res.getHttpStatus());
		}
	}

	@GetMapping(value = "/page/{page}/value/{value}/date/{millisecondDate}")
	public ResponseEntity<Response<List<Field>>> getAllField(@PathVariable int page, @PathVariable int value,
			@PathVariable long millisecondDate) {
		Response<List<Field>> res = new Response<>();
		try {
			String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
			Date date = new Date(millisecondDate);
			List<Field> fieldList = fieldService.findAllOrderByDate(userName, page, value, date);
			res.setBody(fieldList);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<List<Field>>>(res, res.getHttpStatus());

		} catch (Exception ex) {
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<List<Field>>>(res, res.getHttpStatus());
		}
	}

	@GetMapping(value = "/page/{page}/value/{value}")
	public ResponseEntity<Response<List<Field>>> getAllFields(@PathVariable int page, @PathVariable int value,
			long millisecondDate) {
		Response<List<Field>> res = new Response<>();
		try {
			String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
			Date date = new Date(millisecondDate);
			List<Field> fieldList = fieldService.findAllOrderByDate(userName, value, page, date);
			res.setBody(fieldList);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<List<Field>>>(res, res.getHttpStatus());

		} catch (Exception ex) {
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<List<Field>>>(res, res.getHttpStatus());
		}
	}

	@GetMapping(value = "/substrictid/{substrictid}/userid/{userid}/page/{page}/value/{value}")
	public ResponseEntity<Response<List<Field>>> getFieldinSubdistrictId(@PathVariable int substrictid,
			@PathVariable int userid, @PathVariable int page, @PathVariable int value, long millisecondDate) {
		Response<List<Field>> res = new Response<>();
		try {
			String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
			Date date = new Date(millisecondDate);
			List<Field> fieldList = fieldService.findBySubdistrictIdAndUserIdAndDate(substrictid, userid, userName,
					page, value, date);
			res.setBody(fieldList);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<List<Field>>>(res, res.getHttpStatus());
		} catch (Exception ex) {
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<List<Field>>>(res, res.getHttpStatus());
		}
	}

	@GetMapping(value = "/planting/{id}")
	public ResponseEntity<Response<Field>> getFieldByPlantingId(@PathVariable int id) {
		Response<Field> res = new Response<>();
		try {
			String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
			User user = userService.findByUsername(userName);
			Field field = fieldService.findByPlantingAndUser(id, user);
			res.setMessage(field.getFieldId().toString());
			res.setBody(field);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<Field>>(res, res.getHttpStatus());
		} catch (Exception ex) {
			res.setMessage("Result not found");
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<Field>>(res, res.getHttpStatus());

		}
	}

	@GetMapping(value = "/survey/{id}")
	public ResponseEntity<Response<Field>> getFieldBySurveyId(@PathVariable int id) {
		Response<Field> res = new Response<>();
		try {
			String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
			User user = userService.findByUsername(userName);
			Field field = fieldService.findBySurveyAndUser(id, user);
			res.setMessage(field.getFieldId().toString());
			res.setBody(field);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<Field>>(res, res.getHttpStatus());
		} catch (Exception ex) {
			res.setMessage("Result not found");
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<Field>>(res, res.getHttpStatus());

		}
	}

	@GetMapping(value = "/count")
	public ResponseEntity<Response<Integer>> findNumberOfAllFieldByUser() {
		Response<Integer> res = new Response<>();
		try {
			String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
			User user = userService.findByUsername(userName);
			int NumberOfAllField = fieldService.findNumberOfAllFieldByUser(user);
			res.setMessage("Result count : " + NumberOfAllField);
			res.setBody(NumberOfAllField);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<Integer>>(res, res.getHttpStatus());
		} catch (Exception ex) {
			res.setMessage("Result not found");
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<Integer>>(res, res.getHttpStatus());

		}

	}

	@PostMapping(value = "/search")
	public ResponseEntity<Response<List<Field>>> searchByKey(@RequestBody FieldSearchDTO searchDTO) {
		Response<List<Field>> res = new Response<>();
		try {
			String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
			User user = userService.findByUsername(userName);
			List<Field> result = fieldService.search(searchDTO, user);
			res.setBody(result);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<List<Field>>>(res, res.getHttpStatus());
		} catch (Exception ex) {
			res.setMessage(ex.getMessage());
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<List<Field>>>(res, res.getHttpStatus());
		}
	}

	@PostMapping(value = "/search/page/{page}/value/{value}/date/{milliseconddate}")
	public ResponseEntity<Response<List<Field>>> searchByKeyPagination(@RequestBody FieldSearchDTO searchDTO,
			@PathVariable("page") int page, @PathVariable("value") int value,
			@PathVariable("milliseconddate") long milliseconddate) {
		Response<List<Field>> res = new Response<>();
		try {
			String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
			User user = userService.findByUsername(userName);
			Date date = new Date(milliseconddate);
			List<Field> result = fieldService.searchPagination(searchDTO, user, page, value, date);
			res.setBody(result);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<List<Field>>>(res, res.getHttpStatus());
		} catch (Exception ex) {
			res.setMessage(ex.getMessage());
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<List<Field>>>(res, res.getHttpStatus());
		}
	}

	@GetMapping(value = "/{id}/address")
	public ResponseEntity<Response<?>> findAddressByField(@PathVariable int id) {
		String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());

		Field fin = fieldService.findById(id, userName);
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode responObject = mapper.createObjectNode();
		Response<ObjectNode> res = new Response();

		if (fin != null) {
			Subdistrict subdistrict = fin.getSubdistrict();
			responObject.put("fieldId", fin.getFieldId());
			responObject.put("substrict", subdistrict.getName());
			responObject.put("district", subdistrict.getDistrict().getName());
			responObject.put("province", subdistrict.getDistrict().getProvince().getName());
			res.setBody(responObject);
			res.setHttpStatus(HttpStatus.OK);
		} else {
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			res.setMessage("field id = " + id + " not found");
		}

		return new ResponseEntity<Response<?>>(res, res.getHttpStatus());
	}

	@GetMapping(value = "/index/page/{page}/value/{value}/date/{millisecondDate}")
	public ResponseEntity<Response<List<?>>> index(@PathVariable("page") int page, @PathVariable("value") int value,
			@PathVariable("millisecondDate") long millisecondDate) {
		String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
		ObjectMapper mapper = new ObjectMapper();
		List<ObjectNode> responseList = new ArrayList<>();
		Response<List<?>> res = new Response();

		try {

			Date date = new Date(millisecondDate);
			List<Field> fieldList = fieldService.findAllOrderByDate(userName, page, value, date);

			for (Field field : fieldList) {
				ObjectNode responObject = mapper.createObjectNode();
				User user = userService.findByFieldIdAndRoleAndUser(field.getFieldId(), "farmerOwner", userName).get(0);
				Subdistrict subdistrict = field.getSubdistrict();
				responObject.put("fieldId", field.getFieldId());
				responObject.put("fieldName", field.getName());
				responObject.put("createDate", field.getCreateDate().toString());
				responObject.put("substrict", subdistrict.getName());
				responObject.put("district", subdistrict.getDistrict().getName());
				responObject.put("province", subdistrict.getDistrict().getProvince().getName());
				responObject.put("title", user.getTitle());
				responObject.put("firstName", user.getFirstName());
				responObject.put("lastName", user.getLastName());
				responseList.add(responObject);
			}
			res.setBody(responseList);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<List<?>>>(res, res.getHttpStatus());
		} catch (Exception ex) {
			res.setMessage(ex.getMessage());
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<List<?>>>(res, res.getHttpStatus());
		}
	}

	@PostMapping(value = "/searchbykey/page/{page}/value/{value}/date/{milliseconddate}")
	public ResponseEntity<Response<List<Field>>> search(@RequestBody String key, @PathVariable("page") int page,
			@PathVariable("value") int value, @PathVariable("milliseconddate") long milliseconddate) {
		Response<List<Field>> res = new Response<>();
		ObjectMapper mapper = new ObjectMapper();
		try {
			String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
			User user = userService.findByUsername(userName);
			Date date = new Date(milliseconddate);
			JsonNode jsonNode = mapper.readTree(key);
			String skey = jsonNode.get("key").asText();
			List<Field> result = fieldService.findByKey(skey, user, page, value, date);
			res.setBody(result);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<List<Field>>>(res, res.getHttpStatus());
		} catch (Exception ex) {
			res.setMessage(ex.getMessage());
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<List<Field>>>(res, res.getHttpStatus());
		}
	}

	@GetMapping(value = "/countfield")
	public ResponseEntity<Response<Integer>> getFieldByUserInField () {
		Response<Integer> res = new Response<>();
		try {
			String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
			User user = userService.findByUsername(userName);
			Integer count = fieldService.countByUserInField(user);
			res.setBody(count);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<Integer>>(res, res.getHttpStatus());
		} catch (Exception ex) {
			res.setBody(null);
			res.setMessage(ex.getMessage());
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<Integer>>(res, res.getHttpStatus());
		}

	}
	
	@PostMapping(value = "/{fieldid}/add/staff/{staffid}")
	public ResponseEntity<Response<String>> addStaffSurvey(@PathVariable("fieldid") int fieldid,@PathVariable("staffid") int staffid) {
		Response<String> res = new Response<>();
		try {
			UserInFieldId userinfieldId = new UserInFieldId();
			Field field = fieldService.findById(fieldid);
			User user = userService.findById(staffid);
			
			if (roleService.findByUserIdAndRoleName(staffid, "fieldSurvey") == null) {
				List<Role> userrole = user.getRoles();
				userrole.add(roleService.findByNameEng("fieldSurvey"));
				user.setRoles(userrole);
				userService.save(user);
			}
			
			userinfieldId.setFieldId(fieldid);
			userinfieldId.setUserId(staffid);
			userinfieldId.setRole("staffSurvey");
			UserInField staffResponsible = new UserInField();
			staffResponsible.setField(field);
			staffResponsible.setUser(user);
			staffResponsible.setId(userinfieldId);
			userInFieldService.save(staffResponsible);
			
			res.setMessage("created Success");
			res.setBody("Created Success");
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<String>>(res, res.getHttpStatus());
		} catch (Exception ex) {
			res.setMessage(ex.getLocalizedMessage());
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<String>>(res, res.getHttpStatus());
		}
	}
	
	@DeleteMapping(value = "/{fieldid}/delete/staff/{staffid}")
	public ResponseEntity<Response<String>> deleteStaffSurvey(@PathVariable("fieldid") int fieldid,@PathVariable("staffid") int staffid) {
		Response<String> res = new Response<>();
		try {
			
			UserInField userinfield = userInFieldService.findByFieldIdAndUserIdAndRolename(fieldid, staffid, "staffSurvey");
			userInFieldService.delete(userinfield);
			
			if (userInFieldService.findAllByUserIdAndRoleName(staffid, "staffSurvey").isEmpty()) {
				User user = userService.findById(staffid);
				List<Role> userrole = user.getRoles();
				String rname = null;
				rname = "fieldSurvey";
				for (Role r : userrole) {
					if (r.getNameEng().equals(rname)) {
						userrole.remove(r);
						userService.save(user);
						break;
					}
				}
			}
			res.setMessage("Delete Success");
			res.setBody("Delete Success");
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<String>>(res, res.getHttpStatus());
		} catch (Exception ex) {
			res.setMessage(ex.getLocalizedMessage());
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<String>>(res, res.getHttpStatus());
		}
	}

	@GetMapping(value = "/{fieldid}/staff/page/{page}/value/{value}")
	public ResponseEntity<Response<List<?>>> findStaffByOrganizationAndFieldNotIn(@PathVariable("fieldid") int fieldid,
			@PathVariable("page") int page, @PathVariable("value") int value) {
		Response<List<?>> res = new Response<>();
		ObjectMapper mapper = new ObjectMapper();
		List<ObjectNode> responseList = new ArrayList<>();
		try {
			String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
			User user = userService.findByUsername(userName);
			Field field = fieldService.findById(fieldid);
			List<Staff> staffs = staffService.findByOrganization(user.getStaff().getOrganization(), page, value);
			List<String> rolename = new ArrayList<String>();
			rolename.add("staffCreator");
			rolename.add("staffResponse");
			rolename.add("staffSurvey");
			rolename.add("farmerOwner");
			rolename.add("farmerSurvey");

			for (Staff s : staffs) {
				User u = s.getUser();
				
				ObjectNode responObject = mapper.createObjectNode();
				if (staffService.checkStaffNotInFieldByOrganizationAndField(user.getStaff().getOrganization(), field,
						s.getStaffId()) > 0) {
					
					responObject.put("staffId", s.getStaffId());
					responObject.put("firstName", s.getUser().getFirstName());
					responObject.put("lastName", s.getUser().getLastName());
					responObject.put("status", "0");
					
				}
				if (staffService.checkStaffInFieldByOrganizationAndField(field, rolename, s.getStaffId()) > 0) {
					
					responObject.put("staffId", s.getStaffId());
					responObject.put("firstName", s.getUser().getFirstName());
					responObject.put("lastName", s.getUser().getLastName());
					responObject.put("status", "1");
					
				}
				
				int check = userService.checkUserNameAndRoleEng(u, "fieldResponsible");
				Boolean bool = (check == 1);
				responObject.put("Respon", bool);
				responseList.add(responObject);
			}

			res.setBody(responseList);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<List<?>>>(res, res.getHttpStatus());
		} catch (Exception ex) {
			res.setMessage(ex.getMessage());
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<List<?>>>(res, res.getHttpStatus());
		}

	}
	
	@GetMapping(value = "/{fieldid}/farmer/page/{page}/value/{value}")
	public ResponseEntity<Response<List<?>>> findfarmerByOrganizationAndFieldNotIn(@PathVariable("fieldid") int fieldid,
			@PathVariable("page") int page, @PathVariable("value") int value) {
		Response<List<?>> res = new Response<>();
		ObjectMapper mapper = new ObjectMapper();
		List<ObjectNode> responseList = new ArrayList<>();
		try {
			String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
			User user = userService.findByUsername(userName);
			Field field = fieldService.findById(fieldid);
			List<Farmer> farmers = farmerService.findByOrganization(user.getStaff().getOrganization().getOrganizationId());
			List<String> rolename = new ArrayList<String>();
			rolename.add("staffCreator");
			rolename.add("staffResponse");
			rolename.add("staffSurvey");
			rolename.add("farmerOwner");
			rolename.add("farmerSurvey");

			for (Farmer f : farmers) {
				ObjectNode responObject = mapper.createObjectNode();
				User u = f.getUser();
				if (farmerService.checkFarmerNotInFieldByOrganizationAndField(user.getStaff().getOrganization(), field, f.getFarmerId()) > 0) {
					
					responObject.put("farmerId", f.getFarmerId());
					responObject.put("firstName", f.getUser().getFirstName());
					responObject.put("lastName", f.getUser().getLastName());
					responObject.put("status", "0");
				}
				if (farmerService.checkFarmerInFieldByOrganizationAndField(field, rolename, f.getFarmerId(), user.getStaff().getOrganization()) > 0) {
					
					responObject.put("farmerId", f.getFarmerId());
					responObject.put("firstName", f.getUser().getFirstName());
					responObject.put("lastName", f.getUser().getLastName());
					responObject.put("status", "1");
					
				}
				int check = userService.checkUserNameAndRoleEng(u, "farmerOwner");
				Boolean bool = (check == 1);
				responObject.put("Respon", bool);
				
				responseList.add(responObject);
			}

			res.setBody(responseList);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<List<?>>>(res, res.getHttpStatus());
		} catch (Exception ex) {
			res.setMessage(ex.getMessage());
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<List<?>>>(res, res.getHttpStatus());
		}

	}
	
	@PostMapping(value = "/{fieldid}/add/farmer/{farmerid}")
	public ResponseEntity<Response<String>> addFarmerSurvey(@PathVariable("fieldid") int fieldid,@PathVariable("farmerid") int farmerid) {
		Response<String> res = new Response<>();
		try {
			UserInFieldId userinfieldId = new UserInFieldId();
			Field field = fieldService.findById(fieldid);
			User user = userService.findById(farmerid);
			
			if (roleService.findByUserIdAndRoleName(farmerid, "farmerSurveyor") == null) {
				List<Role> userrole = user.getRoles();
				userrole.add(roleService.findByNameEng("farmerSurveyor"));
				user.setRoles(userrole);
				userService.save(user);
			}
			
			userinfieldId.setFieldId(fieldid);
			userinfieldId.setUserId(farmerid);
			userinfieldId.setRole("farmerSurvey");
			UserInField farmerResponsible = new UserInField();
			farmerResponsible.setField(field);
			farmerResponsible.setUser(user);
			farmerResponsible.setId(userinfieldId);
			userInFieldService.save(farmerResponsible);
			
			res.setMessage("created Success");
			res.setBody("Created Success");
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<String>>(res, res.getHttpStatus());
		} catch (Exception ex) {
			res.setMessage(ex.getLocalizedMessage());
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<String>>(res, res.getHttpStatus());
		}
	}
	
	@DeleteMapping(value = "/{fieldid}/delete/farmer/{farmerid}")
	public ResponseEntity<Response<String>> deleteFarmerSurvey(@PathVariable("fieldid") int fieldid,@PathVariable("farmerid") int farmerid) {
		Response<String> res = new Response<>();
		try {
			
			UserInField userinfield = userInFieldService.findByFieldIdAndUserIdAndRolename(fieldid, farmerid, "farmerSurvey");
			userInFieldService.delete(userinfield);
			
			if (userInFieldService.findAllByUserIdAndRoleName(farmerid, "farmerSurvey").isEmpty()) {
				User user = userService.findById(farmerid);
				List<Role> userrole = user.getRoles();
				String rname = null;
				rname = "farmerSurveyor";
				for (Role r : userrole) {
					if (r.getNameEng().equals(rname)) {
						userrole.remove(r);
						userService.save(user);
						break;
					}
				}
			}
			res.setMessage("Delete Success");
			res.setBody("Delete Success");
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<String>>(res, res.getHttpStatus());
		} catch (Exception ex) {
			res.setMessage(ex.getLocalizedMessage());
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<String>>(res, res.getHttpStatus());
		}
	}

	@PostMapping(value = "/{fieldid}/searchstaff/page/{page}/value/{value}")
	public ResponseEntity<Response<List<?>>> getStaffInOrganization(@PathVariable("fieldid")int fieldid, @RequestBody String rawSearch,@PathVariable("page") int page,
			@PathVariable("value") int value) {
		Response<List<?>> res = new Response<>();
		ObjectMapper mapper = new ObjectMapper();
		List<ObjectNode> responseList = new ArrayList<>();
		try {
			String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
			JsonNode jsonNode = mapper.readTree(rawSearch);
			String firstname = jsonNode.get("firstname").asText();
			String lastname =jsonNode.get("lastname").asText();
			User user = userService.findByUsername(userName);
			Organization organization = organizationService.findByStaffUsername(userName);
			Field field = fieldService.findById(fieldid);
			List<User> users = userService.findStaffByKey(firstname,lastname, organization, page, value);
			List<String> rolename = new ArrayList<String>();
			rolename.add("staffCreator");
			rolename.add("staffResponse");
			rolename.add("staffSurvey");
			rolename.add("farmerOwner");
			rolename.add("farmerSurvey");
			
			for (User s : users) {
				ObjectNode responObject = mapper.createObjectNode();
				
				if (staffService.checkStaffNotInFieldByOrganizationAndField(user.getStaff().getOrganization(), field,
						s.getStaff().getStaffId()) > 0) {
					
					responObject.put("staffId", s.getStaff().getStaffId());
					responObject.put("firstName", s.getFirstName());
					responObject.put("lastName", s.getLastName());
					responObject.put("status", "0");
				}
				if (staffService.checkStaffInFieldByOrganizationAndField(field, rolename, s.getStaff().getStaffId()) > 0) {

					responObject.put("staffId", s.getStaff().getStaffId());
					responObject.put("firstName", s.getFirstName());
					responObject.put("lastName", s.getLastName());
					responObject.put("status", "1");
				}
				

				int check = userService.checkUserNameAndRoleEng(s, "fieldResponsible");
				Boolean bool = (check == 1);
				responObject.put("Respon", bool);
				
				responseList.add(responObject);
			}
			
			res.setBody(responseList);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<List<?>>>(res, res.getHttpStatus());
		} catch (Exception ex) {
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<List<?>>>(res, res.getHttpStatus());
		}

	}
	
	@PostMapping(value = "/{fieldid}/searchfarmer/page/{page}/value/{value}")
	public ResponseEntity<Response<List<?>>> getFarmerInOrganization(@PathVariable("fieldid")int fieldid
			, @RequestBody String rawSearch,@PathVariable("page") int page,
			@PathVariable("value") int value) {
		Response<List<?>> res = new Response<>();
		ObjectMapper mapper = new ObjectMapper();
		List<ObjectNode> responseList = new ArrayList<>();
		try {
			String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
			JsonNode jsonNode = mapper.readTree(rawSearch);
			String firstname = jsonNode.get("firstname").asText();
			String lastname =jsonNode.get("lastname").asText();
			User user = userService.findByUsername(userName);
			Organization organization = organizationService.findByStaffUsername(userName);
			List<User> users = userService.findFarmerByKey(firstname,lastname, organization, page, value);
			Field field = fieldService.findById(fieldid);
			List<String> rolename = new ArrayList<String>();
			rolename.add("staffCreator");
			rolename.add("staffResponse");
			rolename.add("staffSurvey");
			rolename.add("farmerOwner");
			rolename.add("farmerSurvey");
			
			for (User f : users) {
				ObjectNode responObject = mapper.createObjectNode();
				if (farmerService.checkFarmerNotInFieldByOrganizationAndField(user.getStaff().getOrganization(), field, f.getFarmer().getFarmerId()) > 0) {
					
					responObject.put("farmerId", f.getFarmer().getFarmerId());
					responObject.put("firstName", f.getFirstName());
					responObject.put("lastName", f.getLastName());
					responObject.put("status", "0");

				}
				if (farmerService.checkFarmerInFieldByOrganizationAndField(field, rolename, f.getFarmer().getFarmerId(), user.getStaff().getOrganization()) > 0) {
					
					responObject.put("farmerId", f.getFarmer().getFarmerId());
					responObject.put("firstName", f.getFirstName());
					responObject.put("lastName", f.getLastName());
					responObject.put("status", "1");

					
				}

				int check = userService.checkUserNameAndRoleEng(f, "farmerOwner");
				Boolean bool = (check == 1);
				responObject.put("Respon", bool);
				responseList.add(responObject);
			}
			
			res.setBody(responseList);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<List<?>>>(res, res.getHttpStatus());
		} catch (Exception ex) {
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<List<?>>>(res, res.getHttpStatus());
		}
	}
	
	@GetMapping(value = "checkfieldregistrar")
	public ResponseEntity<Response<Boolean>> checkFieldRegistrar () {
		Response<Boolean> res = new Response<>();
		try {
			String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
			User user = userService.findByUsername(userName);
			int check = userService.checkUserNameAndRoleEng(user, "fieldRegistrar");
			Boolean bool = (check == 1);
			res.setBody(bool);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<Boolean>>(res, res.getHttpStatus());
		} catch (Exception ex) {
			res.setBody(null);
			res.setMessage(ex.getMessage());
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<Boolean>>(res, res.getHttpStatus());
		}

	}
}
