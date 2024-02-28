package org.cassava.web.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
import org.cassava.model.Field;
import org.cassava.model.Organization;
import org.cassava.model.Province;
import org.cassava.model.Role;
import org.cassava.model.Staff;
import org.cassava.model.User;
import org.cassava.model.UserInField;
import org.cassava.model.UserInFieldId;
import org.cassava.model.dto.ImageDTO;
import org.cassava.services.DistrictService;
import org.cassava.services.FarmerService;
import org.cassava.services.FieldService;
import org.cassava.services.OrganizationService;
import org.cassava.services.ProvinceService;
import org.cassava.services.RoleService;
import org.cassava.services.StaffService;
import org.cassava.services.SubdistrictService;
import org.cassava.services.UserInFieldService;
import org.cassava.services.UserService;
import org.cassava.util.MvcHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Controller
public class FieldController {

	@Value("${external.resoures.path}")
	private String externalPath;

	@Autowired
	FieldService fieldService;

	@Autowired
	UserService userService;

	@Autowired
	UserInFieldService userInFieldService;

	@Autowired
	ProvinceService provinceService;

	@Autowired
	DistrictService districtService;

	@Autowired
	SubdistrictService subdistrictService;

	@Autowired
	RoleService roleService;

	@Autowired
	FarmerService farmerService;

	@Autowired
	StaffService staffService;

	@Autowired
	OrganizationService organizationService;

	@RequestMapping(value = {"/field/index","/field/","/field"}, method = RequestMethod.GET)
	public String fieldIndex(Model model, Authentication authentication) {
		List<UserInField> ownerInFields = null;
		Staff staff = staffService.findByUserName(MvcHelper.getUsername(authentication));
		List<ImageDTO> dtos = new ArrayList<ImageDTO>();
		int role = 0;
		ArrayList<String> listStatus = new ArrayList<>(List.of("Active", "Inactive", "Invalid"));
		List<Farmer> farmers = farmerService.findByOrganizationAndListStatus(staff.getOrganization(), listStatus);
		if (roleService.findByUserIdAndRoleName(staff.getUser().getUserId(), "fieldRegistrar") != null) {
			role = 1;
			ownerInFields = userInFieldService.findAllByOrganizationAndRoleOwner(staff.getOrganization());
			for (UserInField f : ownerInFields) {
				ImageDTO imageDTO = new ImageDTO();
				imageDTO.setImage(externalPath + File.separator + "Field" + File.separator + f.getField().getImgPath());

				dtos.add(imageDTO);
			}
		} else if (roleService.findByUserIdAndRoleName(staff.getUser().getUserId(), "fieldResponsible") != null) {
			List<Field> fields = fieldService.findByUserInFieldAndRoleInField(staff.getUser().getUserId(),
					"staffResponse");
			ownerInFields = userInFieldService.findAllByFiledsAndRoleOwner(fields);
			for (UserInField f : ownerInFields) {
				ImageDTO imageDTO = new ImageDTO();
				imageDTO.setImage(externalPath + File.separator + "Field" + File.separator + f.getField().getImgPath());

				dtos.add(imageDTO);
			}
		}

		else {

		}
		model.addAttribute("ownerInFields", ownerInFields);
		model.addAttribute("images", dtos);
		model.addAttribute("role", role);
		model.addAttribute("farmer", farmers);

		return "/field/fieldIndex";
	}

	@RequestMapping(value = "/field/fieldcreate", method = RequestMethod.GET)
	public String fieldCreate(@ModelAttribute("field") Field field, Model model, Authentication authentication) {
		Staff staff = staffService.findByUserName(MvcHelper.getUsername(authentication));
		ArrayList<String> listStatus = new ArrayList<>(List.of("Active", "Inactive", "Invalid"));
		List<Farmer> farmers = farmerService.findByOrganizationAndListStatus(staff.getOrganization(), listStatus);

		model.addAttribute("farmer", farmers);
		model.addAttribute("provinces", provinceService.findAll());
		return "/field/fieldCreate";
	}
	
	@RequestMapping(value = "/field/image/edit/{fid}", method = RequestMethod.GET)
	public String fieldimageedit(@ModelAttribute("fid") int fid, Model model) {
		Field field =fieldService.findById(fid) ;
		if(field == null) {
			return "redirect:/field/";
		}
		ImageDTO dtos = new ImageDTO();
		dtos.setImage(externalPath + File.separator + "Field" + File.separator + field.getImgPath());
		model.addAttribute("image", dtos);
		model.addAttribute("field", field);
		return "/field/imageedit";
	}
	@RequestMapping(value = "/field/image/delete/{fid}", method = RequestMethod.GET)
	public String fieldimagdelete(@PathVariable("fid") int fid, Model model) {
		Field field =fieldService.findById(fid) ;
		if(field == null) {
			return "redirect:/field/";
		}
		String name = externalPath + File.separator + "Field" + File.separator + field.getImgPath() ;
		File f = new File(name); 
		f.delete() ;
		field.setImgPath(null) ;
		fieldService.save(field) ;
		return "redirect:/field/image/edit/" + fid ;
	}
	@RequestMapping(value = "/field/image/save", method = RequestMethod.POST)
	public  ResponseEntity<String> fieldimagesave(@RequestParam(name = "img", required = false) MultipartFile file ,@RequestParam(name = "fieldid") int fid, Model model) throws IOException {
		Field field = fieldService.findById(fid) ;
		if(file!=null) {
			String name = externalPath + File.separator + "Field" + File.separator + field.getImgPath() ;
			File f = new File(name); 
			f.delete() ;
			String filename = fieldService.writeFile(file, field.getName()) ;
			field.setImgPath(filename);
			fieldService.save(field) ;
		}
		return  ResponseEntity.ok("{\"message\": \"success\"}");
	}
	
	@RequestMapping(value = "/field/fieldsave/{size}", method = RequestMethod.POST)
	public ResponseEntity<Response<ObjectNode>> fieldSave(@PathVariable("size") String size,@ModelAttribute("field") Field field, @RequestParam(name = "framownerid") int framownerid,
			Authentication authentication,@RequestParam(name = "img", required = false) MultipartFile file) throws IOException {
		System.out.println("check");
		
		field.setCode(field.getCode().trim());
		field.setName(field.getName().trim());
		field.setRoad(field.getRoad().trim());
		field.setMoo(field.getMoo().trim());
		field.setLandmark(field.getLandmark().trim());
		field.setAddress(field.getAddress().trim());
		
		
		ResponseEntity<Response<ObjectNode>> responseEntity = validate(authentication,field,size,framownerid);	
		
		if(responseEntity.getStatusCode() == HttpStatus.OK) {
		field.setSize(Float.parseFloat(size));
		
//		field.setLongitude(Float.parseFloat(longitude));
//		
//		field.setLatitude(Float.parseFloat(latitude));
		
		User userfarm = userService.findById(framownerid);
		
		
		if (roleService.findByUserIdAndRoleName(framownerid, "farmerOwner") == null) {
			List<Role> userrole = userfarm.getRoles();
			userrole.add(roleService.findByNameEng("farmerOwner"));
			userfarm.setRoles(userrole);
			userService.save(userfarm);
		}
		String usercreate = MvcHelper.getUsername(authentication);
		Organization or = userService.findByUsername(usercreate).getStaff().getOrganization();
		field.setOrganization(or);
		field.setUserByCreateBy(userService.findByUsername(usercreate));
		Date date = new Date();
		field.setCreateDate(date);
		if(file!=null) {
			String filename = fieldService.writeFile(file, field.getName()) ;
			field.setImgPath(filename);
		}
		field.setStatus("ใช้งาน");
		fieldService.save(field);
		UserInFieldId userinfieldId = new UserInFieldId();
		int fieldid = field.getFieldId();
		Field fieldset = fieldService.findById(fieldid);
		userinfieldId.setFieldId(fieldid);
		userinfieldId.setUserId(userfarm.getUserId());
		userinfieldId.setRole("farmerOwner");
		UserInField farmerOwner = new UserInField();
		farmerOwner.setField(fieldset);
		farmerOwner.setUser(userfarm);
		farmerOwner.setId(userinfieldId);
		userInFieldService.save(farmerOwner);
		}
		return responseEntity;
	}
	private	ResponseEntity<Response<ObjectNode>> validate(Authentication authentication,Field field,String size,int framownerid){
		ObjectNode responObject = new ObjectMapper().createObjectNode();		

		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();	
		if(!(size.equals("null"))&&!(Float.parseFloat(size)<0)) {
			
		}else {
			responObject.put("size", "กรุณากรอกข้อมูล");
		}
//		if(longitude.equals("null")){
//			responObject.put("longitude", "กรุณากรอกลองติจูด");
//		}
		if(framownerid==0){
			responObject.put("framownersize", "กรุณาเพิ่มเกษตรกรก่อน");
		}
		if(field.getSubdistrict().getSubdistrictId()==null) {
			responObject.put("provinces", "กรุณาเลือกจังหวัด");
		}
		String usercreate = MvcHelper.getUsername(authentication);
		Organization or = userService.findByUsername(usercreate).getStaff().getOrganization();
		field.setOrganization(or);
//		Field fieldIn = fieldService.findByOrganizationAndCode(or.getOrganizationId(),field.getCode());
//		if (fieldIn != null && fieldIn.getFieldId() != field.getFieldId()) {
//			responObject.put("code", "ชื่อนี้ถูกใช้แล้ว");		
//		}
		if (field.getFieldId()==null&&fieldService.findcheckByOrganizationAndCode(field.getOrganization().getOrganizationId(),
				field.getCode())>0) {
			responObject.put("code", "ชื่อนี้ถูกใช้แล้ว");
		}
		if (field.getFieldId()!=null&&fieldService.findcheckByOrganizationAndCodeAndFieldId(field.getOrganization().getOrganizationId(),
				field.getCode(),field.getFieldId())>0) {
			responObject.put("code", "ชื่อนี้ถูกใช้แล้ว");
		}
		Set<ConstraintViolation<Field>> violations = validator.validate(field);	
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
		if (responObject.size()==0) 
			res.setHttpStatus(HttpStatus.OK);	
		else {
			res.setHttpStatus(HttpStatus.BAD_REQUEST);			
			res.setMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
			res.setBody(responObject);
		}	
		return new ResponseEntity<Response<ObjectNode>>(res, res.getHttpStatus());
	}
	@RequestMapping(value = "/field/update/{size}", method = RequestMethod.POST)
	public ResponseEntity<Response<ObjectNode>> fieldUpdate(@PathVariable("size") String size,@ModelAttribute("field") Field field, @RequestParam(name = "framownerid") int framownerid,
			Authentication authentication) throws IOException {
		field.setCode(field.getCode().trim());
		field.setName(field.getName().trim());
		field.setRoad(field.getRoad().trim());
		field.setMoo(field.getMoo().trim());
		field.setLandmark(field.getLandmark().trim());
		field.setAddress(field.getAddress().trim());
		
	
		
		ResponseEntity<Response<ObjectNode>> responseEntity = validate(authentication,field,size,framownerid);		
		if(responseEntity.getStatusCode() == HttpStatus.OK) {
		field.setSize(Float.parseFloat(size));
		
		Field f = fieldService.findById(field.getFieldId());
		String usercreate = MvcHelper.getUsername(authentication);
		f.setUserByLastUpdateBy(userService.findByUsername(usercreate));
		UserInField farmerOwner = userInFieldService.findByFieldIdAndRoleName(field.getFieldId());
		if (farmerOwner.getId()
				.getUserId() != framownerid) {
			userInFieldService.delete(farmerOwner);
			if (userInFieldService.findAllByUserIdAndRoleName(farmerOwner.getId().getUserId(), "farmerOwner")
					.isEmpty()) {
				User user = userService.findById(farmerOwner.getId().getUserId());
				List<Role> userrole = user.getRoles();
				for (Role role : userrole) {
					if (role.getNameEng().equals("farmerOwner")) {
						userrole.remove(role);
						userService.save(user);
						break;
					}
				}
			}

			UserInFieldId userinfieldId = farmerOwner
					.getId();
			userinfieldId.setUserId(framownerid);
			farmerOwner.setId(userinfieldId);
			userInFieldService.save(farmerOwner);
			User userfarm = userService.findById(framownerid);
			if (roleService.findByUserIdAndRoleName(framownerid,
					"farmerOwner") == null) {
				List<Role> userrole = userfarm.getRoles();
				userrole.add(roleService.findByNameEng("farmerOwner"));
				userfarm.setRoles(userrole);
				userService.save(userfarm);
			}
		}
		Date date = new Date();
		f.setAddress(field.getAddress());
		f.setCode(field.getCode());
		f.setLandmark(field.getLandmark());
		f.setLastUpdateDate(date);
		f.setLatitude(field.getLatitude());
		f.setLongitude(field.getLongitude());
		f.setMetresAboveSeaLv(field.getMetresAboveSeaLv());
		f.setMoo(field.getMoo());
		f.setName(field.getName());
		f.setRoad(field.getRoad());
		f.setSize(field.getSize());
		f.setStatus(field.getStatus());
		f.setSubdistrict(field.getSubdistrict());
		f.setStatus("ใช้งาน");

		fieldService.save(f);
		}
		return responseEntity;
	}

	
	

	@RequestMapping(value = "/field/edit/{fid}", method = RequestMethod.GET)
	public String fieldEdit(@PathVariable("fid") int fid, Model model, Authentication authentication)
			throws FileNotFoundException {
		Field field = fieldService.findById(fid);
		if(field == null) {
			return "redirect:/field/";
		}
		String usercreate = MvcHelper.getUsername(authentication);
		Province fieldprovinces = field.getSubdistrict().getDistrict().getProvince();
		District fielddistrict = field.getSubdistrict().getDistrict();
		ArrayList<String> listStatus = new ArrayList<>(List.of("Active", "Inactive", "Invalid"));
		List<Farmer> listfarmer = farmerService.findByOrganizationAndListStatus(
				userService.findByUsername(usercreate).getStaff().getOrganization(), listStatus);
		ImageDTO dtos = new ImageDTO();
		dtos.setImage(externalPath + File.separator + "Field" + File.separator + field.getImgPath());
		model.addAttribute("farmerOwner", userInFieldService.findByFieldIdAndRoleName(fid));
		model.addAttribute("farmer", listfarmer);
		model.addAttribute("field", field);
		model.addAttribute("provinces", provinceService.findAll());
		model.addAttribute("district", districtService.findByProvinceId(fieldprovinces.getProvinceId()));
		model.addAttribute("subdistrict", subdistrictService.findByDistrictId(fielddistrict.getDistrictId()));
		model.addAttribute("fieldprovinces", fieldprovinces);
		model.addAttribute("fielddistrict", fielddistrict);
		model.addAttribute("fieldsubdistrict", field.getSubdistrict());
		model.addAttribute("image", dtos);
		return "/field/fieldEdit";
	}


	@RequestMapping(value = "/field/delete/{id}", method = RequestMethod.GET)
	public String fieldDelete(@PathVariable("id") int id, Model model) {
		Field f = fieldService.findById(id);
		List<UserInField> userinfield = f.getUserinfields();
		for (UserInField userinfieldelete : userinfield) {
			userInFieldService.delete(userinfieldelete);
			if (userInFieldService.findAllByUserIdAndRoleName(userinfieldelete.getUser().getUserId(),
					userinfieldelete.getId().getRole()).isEmpty()) {
				User user = userService.findById(userinfieldelete.getUser().getUserId());
				List<Role> userrole = user.getRoles();
				if (userinfieldelete.getId().getRole().equals("staffResponse")) {
					for (Role role : userrole) {
						if (role.getNameEng().equals("fieldResponsible")) {
							userrole.remove(role);
							break;
						}
					}
				}
				if (userinfieldelete.getId().getRole().equals("staffSurvey")) {
					for (Role role : userrole) {
						if (role.getNameEng().equals("fieldSurvey")) {
							userrole.remove(role);
							break;
						}
					}
				}
				if (userinfieldelete.getId().getRole().equals("farmerOwner")) {
					for (Role role : userrole) {
						if (role.getNameEng().equals("farmerOwner")) {
							userrole.remove(role);
							break;
						}
					}
				}
				if (userinfieldelete.getId().getRole().equals("farmerSurvey")) {
					for (Role role : userrole) {
						if (role.getNameEng().equals("farmerSurveyor")) {
							userrole.remove(role);
							break;
						}
					}
				}
				userService.save(user);
			}
		}
		fieldService.deleteById(id);
		return "redirect:/field/index";
	}

	@RequestMapping(value = "/field/{fid}/user/index", method = RequestMethod.GET)
	public String userInField(@PathVariable("fid") int fid, Model model) {
		Field field = fieldService.findById(fid);
		if(field == null) {
			return "redirect:/field/";
		}
		List<String> role = new ArrayList<String>() ;
		role.add("staffResponse") ;
		List<UserInField> listresponsibleinfield = userInFieldService.findAllByRolenameAndFieldId(fid, role);
		role.clear() ;
		role.add("staffSurvey") ;
		List<UserInField> listsurveyinfield = userInFieldService.findAllByRolenameAndFieldId(fid, role);
		role.clear() ;
		role.add("farmerOwner") ;
		List<UserInField> listownerinfield = userInFieldService.findAllByRolenameAndFieldId(fid, role);
		role.clear() ;
		role.add("farmerSurvey") ;
		List<UserInField> listsurveyorinfield = userInFieldService.findAllByRolenameAndFieldId(fid, role);
		model.addAttribute("listresponsibleinfield", listresponsibleinfield);
		model.addAttribute("listsurveyinfield", listsurveyinfield);
		model.addAttribute("listownerinfield", listownerinfield);
		model.addAttribute("listsurveyorinfield", listsurveyorinfield);
		model.addAttribute("field", field);

		return "/field/userInField";

	}

	@RequestMapping(value = { "/field/{id}/userinfield/{toPage}" }, method = RequestMethod.GET)
	public String UserInField(@PathVariable("id") int id, @PathVariable("toPage") String toPage, Model model) {
		Field field = fieldService.findById(id);
		if(field == null) {
			return "redirect:/field/";
		}
		List<String> role = new ArrayList<String>() ;
		role.add(toPage) ;
		List<UserInField> listuserinfield = userInFieldService.findAllByRolenameAndFieldId(id, role);
		model.addAttribute("sizePages", Arrays.asList(10, 25, 50));
		model.addAttribute("listuserinfield", listuserinfield);
		model.addAttribute("field", field);
		model.addAttribute("toPage", toPage);
		return "/field/roleuserinfield";
	}
	
	public void setPageIndex(int sizeItem, Model model, int pageCurrent, int pageSize) {
		int firstItem = 0, lastItem = 0;
		if (sizeItem > 0) {
			firstItem = (pageSize * (pageCurrent - 1)) + 1;
			lastItem = firstItem + sizeItem - 1;
		}
		model.addAttribute("firstItem", firstItem);
		model.addAttribute("lastItem", lastItem);
	}
	
	@RequestMapping(value = { "/field/{id}/userinfield/{toPage}/total" }, method = RequestMethod.GET)
	@ResponseBody
	public int indexTableTotal(@PathVariable("id") int id, @PathVariable("toPage") String toPage, @RequestParam(value = "key", required = false) String key,
			Authentication authentication, Model model) {
		Field field = fieldService.findById(id);
		Organization organizazion = field.getOrganization();
		int totalItem;
		if ((key == null || key == "")) {
			if (toPage.equals("staffResponse") || toPage.equals("staffSurvey")) {
				totalItem = staffService.findByOrganizationAndFieldNotIn(organizazion, field).size();
			} else{
				totalItem = farmerService.findFarmerByOrganizationAndFarmerOwnerNotIn(organizazion, field).size();
			}
		} else {
			if (toPage.equals("staffResponse") || toPage.equals("staffSurvey")) {
				totalItem = staffService.findByOrganizationAndFieldNotInAndKey(organizazion, field, key).size();
			} else{
				totalItem = farmerService.findFarmerByOrganizationAndFarmerOwnerNotInAndKey(organizazion, field, key).size();
			}
		}

		return totalItem;
	}
	
	@RequestMapping(value = { "/field/{id}/userinfield/{toPage}/page/{page}/value/{value}" }, method = RequestMethod.GET)
	public String indexTable(@PathVariable("id") int id, @PathVariable("toPage") String toPage,@PathVariable("page") int page, @PathVariable("value") int value,
			@RequestParam(value = "key", required = false) String key,
			Authentication authentication, Model model) {
		Field field = fieldService.findById(id);
		if(field == null) {
			return "redirect:/field/";
		}
		Organization organizazion = field.getOrganization();
		List<?> userList;
		List<String> statusstaff = new ArrayList<String>() ;
		statusstaff.add("active") ;
		statusstaff.add("inactive") ;
		List<String> statusfarmer = new ArrayList<String>() ;
		statusfarmer.add("active") ;
		statusfarmer.add("inactive") ;
		statusfarmer.add("invalid") ;
		if ((key == null || key == "")) {
			if (toPage.equals("staffResponse") || toPage.equals("staffSurvey")) {
				userList = staffService.findByOrganizationAndFieldNotInAndStatus(organizazion, field,page,value,statusstaff);
			} else{
				userList = farmerService.findFarmerByOrganizationAndFarmerOwnerNotInAndStatus(organizazion, field, page, value,statusfarmer);
			}
		}else {
			if (toPage.equals("staffResponse") || toPage.equals("staffSurvey")) {
				userList = staffService.findByOrganizationAndFieldNotInAndKeyAndStatus(organizazion, field,key,page,value,statusstaff);
			} else{
				userList = farmerService.findFarmerByOrganizationAndFarmerOwnerNotInAndKeyAndStatus(organizazion, field,key,page,value,statusfarmer);
			}
		}
		model.addAttribute("toPage", toPage);
		model.addAttribute("userList", userList);
		model.addAttribute("field", field);
		setPageIndex(userList.size(), model, page, value);
		return "/field/roleuserinfieldTable";
	}

	@RequestMapping(value = "/field/{fid}/{role}/save/{uid}", method = RequestMethod.GET)
	public String addStaffInField(@PathVariable("fid") int fid, @PathVariable("uid") int uid,
			@PathVariable("role") String role, Model model) {
		Field fieldset = fieldService.findById(fid);
		User user = userService.findById(uid);
		if(fieldset == null || user == null) {
			return "redirect:/field/";
		}
		String rname = null;
		if (role.equals("staffResponse")) {
			rname = "fieldResponsible";
		} else if (role.equals("staffSurvey")) {
			rname = "fieldSurvey";
		} else if (role.equals("farmerSurvey")) {
			rname = "farmerSurveyor";
		}
		if (roleService.findByUserIdAndRoleName(uid, rname) == null) {
			List<Role> userrole = user.getRoles();
			userrole.add(roleService.findByNameEng(rname));
			user.setRoles(userrole);
			userService.save(user);
		}
		UserInFieldId userinfieldId = new UserInFieldId();
		
		userinfieldId.setFieldId(fid);
		userinfieldId.setUserId(uid);
		userinfieldId.setRole(role);
		UserInField staffResponsible = new UserInField();
		staffResponsible.setField(fieldset);
		staffResponsible.setUser(user);
		staffResponsible.setId(userinfieldId);
		userInFieldService.save(staffResponsible);
		return "redirect:/field/"+fid+"/userinfield/"+role;
	}

	@RequestMapping(value = "/field/{fid}/{role}/delete/{uid}", method = RequestMethod.GET)
	public ResponseEntity<String> deleteUserInField(@PathVariable("fid") int fid, @PathVariable("role") String role,
			@PathVariable("uid") int uid, Model model) {
		UserInField userinfield = userInFieldService.findByFieldIdAndUserIdAndRolename(fid, uid, role);
		userInFieldService.delete(userinfield);
		if (userInFieldService.findAllByUserIdAndRoleName(uid, role).isEmpty()) {
			User user = userService.findById(uid);
			List<Role> userrole = user.getRoles();
			String rname = null;
			if (role.equals("staffResponse")) {
				rname = "fieldResponsible";
			} else if (role.equals("staffSurvey")) {
				rname = "fieldSurvey";
			} else if (role.equals("farmerSurvey")) {
				rname = "farmerSurveyor";
			}
			for (Role r : userrole) {
				if (r.getNameEng().equals(rname)) {
					userrole.remove(r);
					userService.save(user);
					break;
				}
			}
		}
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}

}