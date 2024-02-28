package org.cassava.api.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.cassava.api.util.model.Response;
import org.cassava.model.Farmer;
import org.cassava.model.Organization;
import org.cassava.model.RegisterCode;
import org.cassava.model.Role;
import org.cassava.model.Staff;
import org.cassava.model.Subdistrict;
import org.cassava.model.User;
import org.cassava.model.dto.RegisterFarmerDTO;
import org.cassava.model.dto.RegisterStaffDTO;
import org.cassava.services.FarmerService;
import org.cassava.services.OrganizationService;
import org.cassava.services.RegisterCodeService;
import org.cassava.services.RoleService;
import org.cassava.services.StaffService;
import org.cassava.services.SubdistrictService;
import org.cassava.services.UserService;
import org.cassava.util.MvcHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@RequestMapping(value = "/api/register")
public class RegisterRestController {

	@Autowired
	private RegisterCodeService registCodeService;

	@Autowired
	private OrganizationService organizationService;

	@Autowired
	private UserService userService;

	@Autowired
	private StaffService staffService;

	@Autowired
	private FarmerService farmerService;

	@Autowired
	private SubdistrictService subdistrictService;

	@Autowired
	private RegisterCodeService registerCodeService;

	@Autowired
	private RoleService roleService;

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

	private String checkCode(String code) {

		RegisterCode registercode = registerCodeService.findByCode(code);
		String message;
		if (registercode != null) { // found
									// RegisterCode r = registcodeService.findValidCodeByCode(code);
			String checkStatus = registerCodeService.checkStatusByCode(registercode);
			if (checkStatus.equals("Valid")) {
				message = "ทำงาน";
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
		return message;
	}

	@GetMapping(value = "/{code}")
	@PreAuthorize("hasAuthority('ROLE_Anonymous')")
	public ResponseEntity<Response<ObjectNode>> findById(@PathVariable("code") String code) {
		// Response<ObjectNode>
		Response<ObjectNode> res = new Response<>();
		// String userName =
		// SecurityContextHolder.getContext().getAuthentication().getName();
		ObjectMapper mapper = new ObjectMapper();

		ObjectNode responObject = mapper.createObjectNode();
		try {
			RegisterCode registercode = registerCodeService.findByCode(code);
			String message;
			if (registercode != null) {
				String checkStatus = registerCodeService.checkStatusByCode(registercode);
				if (checkStatus.equals("Valid")) {

					String userType = registCodeService.findUserTypeByCode(code);
					responObject.put("userType", userType);
					responObject.put("organizationName", registercode.getOrganization().getName());
					res.setBody(responObject);
					res.setHttpStatus(HttpStatus.OK);
					return new ResponseEntity<Response<ObjectNode>>(res, res.getHttpStatus());
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
				res.setHttpStatus(HttpStatus.BAD_REQUEST);
			} else { // not found
				message = "รหัสไม่ถูกต้อง";
				res.setHttpStatus(HttpStatus.BAD_REQUEST);
			}
			res.setMessage(message);
			res.setBody(responObject);
			
			return new ResponseEntity<Response<ObjectNode>>(res, res.getHttpStatus());
		} catch (Exception ex) {
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<ObjectNode>>(res, res.getHttpStatus());
		}
	}

	@PostMapping(value = "/staff")
	@PreAuthorize("hasAuthority('ROLE_Anonymous')")
	public ResponseEntity<Response<Staff>> createStaffByRegisterStaffDTO(
			@Valid @RequestBody RegisterStaffDTO registerStaffDTO) {
		Response<Staff> res = new Response<>();
		try {

			// String userName =
			// MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
			// System.out.println(userName);

			String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());// registerStaffDTO.getUserName();

			User userCheck = userService.findByUsername(userName);

			if (userCheck != null) {
				res.setMessage("มีการใช้งาน E-mail ตัวนี้แล้ว");
				res.setHttpStatus(HttpStatus.OK);
				return new ResponseEntity<Response<Staff>>(res, res.getHttpStatus());
			}
			RegisterCode registerCode = registCodeService.findByCode(registerStaffDTO.getCode());
			if (registerCode != null) {
				registerCode.setNumUseRegist(registerCode.getNumUseRegist() + 1);
				registerCodeService.save(registerCode);

				Organization organization = registerCode.getOrganization();
				Role role = roleService.findByNameEng("staff");

				Date date = new Date();

				User user = new User(userName, registerStaffDTO.getTitle(), registerStaffDTO.getFirstName(),
						registerStaffDTO.getLastName(), registerStaffDTO.getPhoneNo(), "active", date, "No");
				List<Role> roleUser = user.getRoles();
				List<User> userRole = role.getUsers();
				roleUser.add(role);
				userRole.add(user);
				userService.save(user);
				roleService.save(role);

				Staff staff = new Staff(organization, user, registerStaffDTO.getPosition(),
						registerStaffDTO.getDivision());

				staffService.save(staff);

				res.setMessage("Success");
				res.setBody(staff);
				res.setHttpStatus(HttpStatus.OK);
				return new ResponseEntity<Response<Staff>>(res, res.getHttpStatus());

			} else {
				res.setMessage("หา code ตัวนี้ไม่เจอ");
				res.setHttpStatus(HttpStatus.OK);
				return new ResponseEntity<Response<Staff>>(res, res.getHttpStatus());
			}

		} catch (Exception ex) {
			res.setMessage("Error");
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<Staff>>(res, res.getHttpStatus());
		}
	}

	@PostMapping(value = "/farmer")
	@PreAuthorize("hasAuthority('ROLE_Anonymous')")
	public ResponseEntity<Response<Farmer>> createFarmerByRegisterFarmerDTO(
			@Valid @RequestBody RegisterFarmerDTO registerFarmerDTO) {
		Response<Farmer> res = new Response<>();
		try {
			String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());// registerFarmerDTO.getUserName();

			User userCheck = userService.findByUsername(userName);

			if (userCheck != null) {

				res.setMessage("มีการใช้งาน E-mail นี้แล้ว");
				res.setHttpStatus(HttpStatus.OK);
				return new ResponseEntity<Response<Farmer>>(res, res.getHttpStatus());
			}

			RegisterCode registerCode = registCodeService.findByCode(registerFarmerDTO.getCode());
			if (registerCode != null) {

				registerCode.setNumUseRegist(registerCode.getNumUseRegist() + 1);

				registerCodeService.save(registerCode);

				Role role = roleService.findByNameEng("farmer");

				Date date = new Date();

				Subdistrict subdistrict = subdistrictService.findById(registerFarmerDTO.getSubDistrictId());

				User user = new User(userName, registerFarmerDTO.getTitle(), registerFarmerDTO.getFirstName(),
						registerFarmerDTO.getLastName(), registerFarmerDTO.getPhoneNo(), "active", date, "No");

				List<Role> roleUser = user.getRoles();

				List<User> userRole = role.getUsers();

				roleUser.add(role);

				userRole.add(user);

				userService.save(user);

				roleService.save(role);

				Farmer farmer = new Farmer(user, registerFarmerDTO.getAddress(), "No");

				farmer.setSubdistrict(subdistrict);

				farmerService.save(farmer);

				res.setMessage("Success");

				res.setBody(farmer);

				res.setHttpStatus(HttpStatus.OK);

				return new ResponseEntity<Response<Farmer>>(res, res.getHttpStatus());

			} else {
				res.setMessage("หา code ตัวนี้ไม่เจอ");
				res.setBody(null);
				res.setHttpStatus(HttpStatus.NOT_FOUND);
				return new ResponseEntity<Response<Farmer>>(res, res.getHttpStatus());
			}

		} catch (Exception ex) {
			res.setMessage("Error");

			res.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);

			return new ResponseEntity<Response<Farmer>>(res, res.getHttpStatus());
		}
	}
}
