package org.cassava.web.controller;

import java.io.File;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.cassava.api.util.model.Response;
import org.cassava.model.Disease;
import org.cassava.model.NaturalEnemy;
import org.cassava.model.Permission;
import org.cassava.model.PermissionFile;
import org.cassava.model.PermissionOrganization;
import org.cassava.model.PermissionProvince;
import org.cassava.model.PermissionTargetOfSurvey;
import org.cassava.model.Pest;
import org.cassava.model.PestPhaseSurvey;
import org.cassava.model.Planting;
import org.cassava.model.Province;
import org.cassava.model.Staff;
import org.cassava.model.TargetOfSurvey;
import org.cassava.model.User;
import org.cassava.model.Variety;
import org.cassava.model.dto.PermissionAndCountSurveyTargetDTO;
import org.cassava.model.dto.PermissionDTO;
import org.cassava.model.dto.PlantingDTO;
import org.cassava.model.dto.TargetOfSurveyAndCountServeyTargetDTO;
import org.cassava.services.DiseaseService;
import org.cassava.services.NaturalEnemyService;
import org.cassava.services.PermissionFileService;
import org.cassava.services.PermissionOrganizationService;
import org.cassava.services.PermissionProvinceService;
import org.cassava.services.PermissionService;
import org.cassava.services.PermissionTargetOfSurveyService;
import org.cassava.services.PestPhaseSurveyService;
import org.cassava.services.PestService;
import org.cassava.services.ProvinceService;
import org.cassava.services.StaffService;
import org.cassava.services.TargetOfSurveyService;
import org.cassava.services.UserService;
import org.cassava.util.MvcHelper;
import org.cassava.util.PermissionExporter;
import org.cassava.web.controller.binder.DiseaseBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.nimbusds.oauth2.sdk.http.HTTPResponse;

@Controller
public class PermissionController {

	@Value("${external.resoures.path}")
	private String externalPath;

	@Autowired
	private ProvinceService provinceService;

	@Autowired
	private TargetOfSurveyService targetofsurveyService;

	@Autowired
	private DiseaseService diseaseService;

	@Autowired
	private StaffService staffService;

	@Autowired
	private PermissionService permissionService;

	@Autowired
	private PermissionProvinceService permissionProvinceService;

	@Autowired
	private PermissionTargetOfSurveyService permissionTargetOfSurveyService;

	@Autowired
	private DiseaseBinder diseaseBinder;

	@Autowired
	private PermissionFileService permissionFileService;

	@Autowired
	private PestService pestService;

	@Autowired
	private NaturalEnemyService naturalEnemyService;

	@Autowired
	private PestPhaseSurveyService pestPhaseSurveyService;

	@Autowired
	private UserService userService;

	@Autowired
	private PermissionOrganizationService permissionOrganizationService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setAutoGrowCollectionLimit(1024);
		binder.registerCustomEditor(Disease.class, this.diseaseBinder);

	}

	@RequestMapping(value = {"/permission/index","/permission/","/permission"}, method = RequestMethod.GET)
	public String requesterIndex(Model model, Authentication authentication) {

		String username = MvcHelper.getUsername(authentication);
		Staff staff = staffService.findByUserName(username);

		List<Permission> permissionList = (List<Permission>) permissionService.findByStaffAndType(staff, "inOrganize");

		// for (PermissionFile permissionFile : )

		List<List<Province>> provinces = new ArrayList<List<Province>>();
		List<List<Object>> targetOfSurveys = new ArrayList<List<Object>>();

		for (Permission permission : permissionList) {
			// test new query
			List<Integer> proviceIds = permissionProvinceService.findProvinceIdByPermission(permission);
			provinces.add(permissionProvinceService.findProvinceByPermission(permission));
			List<Integer> tosIds = permissionTargetOfSurveyService.findtargetofsurveyIdByPermission(permission);

			List<Object> obj2 = targetofsurveyService
					.findCountSurveyAndTargetOfSurveyByTargetOfSurveyIdAndDateAndProvinceAndStatus(tosIds,
							permission.getDateInfoStart(), permission.getDateInfoEnd(), proviceIds, "Complete");
			targetOfSurveys.add(obj2);

		}

		List<Disease> diseases = diseaseService.findAll();
		List<NaturalEnemy> naturalEnemies = naturalEnemyService.findAll();
		List<PestPhaseSurvey> pestPhaseSurveys = pestPhaseSurveyService.findAll();
		Format formatter = new SimpleDateFormat("d MMM yyyy", new Locale("th", "TH"));

		model.addAttribute("permissionList", permissionList);
		model.addAttribute("provinceList", provinces);
		model.addAttribute("targetOfSurveys", targetOfSurveys);

		model.addAttribute("naturalEnemies", naturalEnemies);
		model.addAttribute("diseases", diseases);
		model.addAttribute("provinces", provinceService.findAll());
		model.addAttribute("pestPhaseSurveys", pestPhaseSurveys);

		model.addAttribute("formatter", formatter);
		return "permission/index";
	}

	@RequestMapping(value = "/permission/create", method = RequestMethod.POST)
	public ResponseEntity<Response<ObjectNode>> addPermission(
			@ModelAttribute("permissionDTO") PermissionDTO permissionDTO, Model model, Authentication authentication) {
		

		deletePermissionWithExpireDate();

		String username = MvcHelper.getUsername(authentication);
		Staff staff = staffService.findByUserName(username);
		Permission permission = new Permission();

		permission.setType("inOrganize");
		permission.setStaffByStaffId(staff);
		permission.setDateRequest(new Date());
		permission.setStatus("Waiting");

		permission.setDateInfoStart(permissionDTO.getDateInfoStart());
		permission.setDateInfoEnd(permissionDTO.getDateInfoEnd());

		permissionService.save(permission);

		List<Province> provinces = permissionDTO.getProvinces();
		List<Integer> diseases = permissionDTO.getDiseases();
		CommonsMultipartFile[] files = permissionDTO.getFiles();

		for (CommonsMultipartFile file : files) {

			try {
				String fileName = permissionService.writeFile(file);

				PermissionFile permissionFile = new PermissionFile(permission, fileName);
				permissionFileService.save(permissionFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		for (Province province : provinces) {
			if (!province.getName().equals("selectAll")) {
				PermissionProvince permissionProvince = new PermissionProvince(permission,
						provinceService.findByName(province.getName()));
				permissionProvinceService.save(permissionProvince);
			}

		}

		for (int disease : diseases) {
			if (disease!= 0) {
				TargetOfSurvey targetOfSurvey = targetofsurveyService.findById(disease);
				PermissionTargetOfSurvey permissionTargetOfSurvey = new PermissionTargetOfSurvey(permission,
						targetOfSurvey, "dis");
				permissionTargetOfSurveyService.save(permissionTargetOfSurvey);
			}
		}

		for (int id : permissionDTO.getPests()) {
			if (id != 0) {
				TargetOfSurvey targetOfSurvey = targetofsurveyService.findById(id);
				PermissionTargetOfSurvey permissionTargetOfSurvey = new PermissionTargetOfSurvey(permission,
						targetOfSurvey, "pes");
				permissionTargetOfSurveyService.save(permissionTargetOfSurvey);
			}

		}

		for (int id : permissionDTO.getNaturalEnemies()) {
			if (id != 0) {
				TargetOfSurvey targetOfSurvey = targetofsurveyService.findById(id);

				PermissionTargetOfSurvey permissionTargetOfSurvey = new PermissionTargetOfSurvey(permission,
						targetOfSurvey, "nat");
				permissionTargetOfSurveyService.save(permissionTargetOfSurvey);
			}
		}
		PermissionOrganization permissionOrganization = new PermissionOrganization(staff.getOrganization(), permission);
		permissionOrganizationService.save(permissionOrganization);
//	}
		// return "redirect:/permission/index";
		System.out.println("check fin");
		ObjectNode responObject = new ObjectMapper().createObjectNode();
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
		// return responseEntity;
	}

//	private	ResponseEntity<Response<ObjectNode>> validate(PermissionDTO permissionDTO){
//		ObjectNode responObject = new ObjectMapper().createObjectNode();		
//
////		Planting plantingIn = plantingService.findByCode(plantingDTO.getPlanting().getCode());
//		
//		CommonsMultipartFile[] files = permissionDTO.getFiles();
//		
//		for (CommonsMultipartFile file : files) {
//
//			try {
//				String fileName = permissionService.writeFile(file);
//				if(file.getSize()==0) {
//					responObject.put("File", "กรุณาเลือกไฟล์");	
//				}
//				//System.out.println(file.getSize());
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
////		System.out.println("check"+listv);
////		if(listv==null) {
////			System.out.println("varieties = null");
////			responObject.put("varieties", "กรุณาเลือกชนิดพันธุ์หลัก");		
////		}else{
////			System.out.println("varieties --"+ listv.get(0).getName());
////		}
//		System.out.println("------");
//		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();		
//	//	Set<ConstraintViolation<Planting>> violations = validator.validate(plantingDTO.getPlanting());		
//		HashMap<String, List<String>> errorMessages = new HashMap<String, List<String>>();
////		if (violations.size() > 0) {				
////			violations.stream().forEach((ConstraintViolation<?> error) -> {
////				String key = error.getPropertyPath().toString();
////				String message = error.getMessage();				
////				List<String> list = null;				
////				if (errorMessages.containsKey(key)) {
////					list = errorMessages.get(key);
////				} else {
////					list = new ArrayList<String>();
////				}
////				list.add(message);
////				System.out.println(key);
////				errorMessages.put(key, list);
////			});
////			for (String key : errorMessages.keySet()) {
////				List<String> sortedList = errorMessages.get(key);
////				Collections.sort(sortedList);
////				Iterator<String> itorError = sortedList.iterator();				
////				StringBuilder sb = new StringBuilder(itorError.next());
////				while (itorError.hasNext()) {
////					sb.append(", ").append(itorError.next());
////				}
////				responObject.put(key, sb.toString());
////			}
////		}	
//		Response<ObjectNode> res = new Response<>();		
//		if (responObject.size()==0) 
//			res.setHttpStatus(HttpStatus.OK);	
//		else {
//			res.setHttpStatus(HttpStatus.BAD_REQUEST);			
//			res.setMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
//			res.setBody(responObject);
//		}	
//		System.out.println(responObject.size());
//		return new ResponseEntity<Response<ObjectNode>>(res, res.getHttpStatus());
//	}
	@RequestMapping(value = "/permission/request", method = RequestMethod.GET)
	public String requesterApproveIndex(Model model, Authentication authentication) {

		String username = MvcHelper.getUsername(authentication);
		Format formatter = new SimpleDateFormat("d MMM yyyy", new Locale("th", "TH"));
		Staff staff = staffService.findByUserName(username);

		ArrayList<String> status = new ArrayList<String>();
		status.add("Waiting");

		List<Permission> permissions = permissionService.findByListStatusAndTypeAndOrganization(status, "inOrganize",
				staff.getOrganization());
		List<List<Object>> targetOfSurveyAndCounts = new ArrayList<List<Object>>();
		List<List<Province>> provinces = new ArrayList<List<Province>>();

		for (Permission permission : permissions) {

			List<Integer> tosIds = permissionTargetOfSurveyService.findtargetofsurveyIdByPermission(permission);
			List<Integer> provinceIds = permissionProvinceService.findProvinceIdByPermission(permission);

			provinces.add(permissionProvinceService.findProvinceByPermission(permission));

			targetOfSurveyAndCounts.add(targetofsurveyService
					.findCountSurveyAndTargetOfSurveyByTargetOfSurveyIdAndDateAndProvinceAndStatus(tosIds,
							permission.getDateInfoStart(), permission.getDateInfoEnd(), provinceIds, "Complete"));
		}

		model.addAttribute("permissions", permissions);
		model.addAttribute("targetOfSurveyAndCounts", targetOfSurveyAndCounts);
		model.addAttribute("provinces", provinces);

		model.addAttribute("formatter", formatter);
		return "permission/requestIndex";

	}

	@RequestMapping(value = "/permission/request/approved", method = RequestMethod.GET)
	public String requesterApproved(Model model, Authentication authentication) {

		String username = MvcHelper.getUsername(authentication);
		Staff staff = staffService.findByUserName(username);

		Format formatter = new SimpleDateFormat("d MMM yyyy", new Locale("th", "TH"));
		ArrayList<String> status = new ArrayList<String>();
		status.add("Approve");
		status.add("Reject");
		List<Permission> permissions = permissionService.findByListStatusAndTypeAndOrganization(status, "inOrganize",
				staff.getOrganization());

		List<List<Object>> targetOfSurveyAndCounts = new ArrayList<List<Object>>();
		List<List<Province>> provinces = new ArrayList<List<Province>>();

		for (Permission permission : permissions) {

			List<Integer> tosIds = permissionTargetOfSurveyService.findtargetofsurveyIdByPermission(permission);
			List<Integer> provinceIds = permissionProvinceService.findProvinceIdByPermission(permission);

			provinces.add(permissionProvinceService.findProvinceByPermission(permission));

			targetOfSurveyAndCounts.add(targetofsurveyService
					.findCountSurveyAndTargetOfSurveyByTargetOfSurveyIdAndDateAndProvinceAndStatus(tosIds,
							permission.getDateInfoStart(), permission.getDateInfoEnd(), provinceIds, "Complete"));
		}

		model.addAttribute("now", new Date());
		model.addAttribute("permissions", permissions);
		model.addAttribute("targetOfSurveyAndCounts", targetOfSurveyAndCounts);
		model.addAttribute("provinces", provinces);
		model.addAttribute("formatter", formatter);
		return "permission/requestApproved";

	}

	@RequestMapping(value = "/permission/approve/{permisisonId}", method = RequestMethod.GET)
	public String approvePermission(Model model, @PathVariable("permisisonId") int permissionId,
			Authentication authentication) {

		deletePermissionWithExpireDate();

		Permission permission = permissionService.findById(permissionId);
		if (permission != null) {
			User u = userService.findByUsername(MvcHelper.getUsername(authentication));
			Staff staff = staffService.findById(permission.getStaffByStaffId().getStaffId());
			if (staff.getOrganization().getOrganizationId() == u.getStaff().getOrganization().getOrganizationId()) {
				Date date = new Date();
				permission.setDateApprove(date);
				permission.setStaffByApproveBy(staff);
				permission.setStatus("Approve");
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				cal.add(Calendar.YEAR, 1);
				Date expireDate = cal.getTime();
				permission.setDateExpire(expireDate);

				permissionService.save(permission);
			}
		}

		return "redirect:/permission/request";
	}

	@RequestMapping(value = "/permission/reject/{permisisonId}", method = RequestMethod.GET)
	public String rejectPermission(Model model, @PathVariable("permisisonId") int permissionId,
			Authentication authentication) {

		deletePermissionWithExpireDate();

		Permission permission = permissionService.findById(permissionId);

		if (permission != null) {
			User u = userService.findByUsername(MvcHelper.getUsername(authentication));
			Staff staff = staffService.findById(permission.getStaffByStaffId().getStaffId());
			if (staff.getOrganization().getOrganizationId() == u.getStaff().getOrganization().getOrganizationId()) {
				permission.setStaffByApproveBy(staff);
				permission.setStatus("Reject");
				Date date = new Date();
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				cal.add(Calendar.MONTH, 1);
				Date expireDate = cal.getTime();

				permission.setDateApprove(date);
				permission.setDateExpire(expireDate);

				permissionService.save(permission);
			}
		}

		return "redirect:/permission/request";

	}

	@RequestMapping(value = "/permission/request/download/{permisisonfileId}", method = RequestMethod.GET)
	public void fileRequesterExport(Model model, @PathVariable("permisisonfileId") int permissionfileId,
			HttpServletResponse response, Authentication authentication) throws IOException {
		PermissionFile permissionFile = permissionFileService.findById(permissionfileId);

		if (permissionFile.getPermission() != null) {
			User u = userService.findByUsername(MvcHelper.getUsername(authentication));
			Staff staff = staffService.findById(permissionFile.getPermission().getStaffByStaffId().getStaffId());
			if (staff.getOrganization().getOrganizationId() == u.getStaff().getOrganization().getOrganizationId()) {
				PermissionExporter perExport = new PermissionExporter();
				perExport.export(response,
						externalPath + File.separator + "Permission" + File.separator + permissionFile.getFilePath());
			}
		}

	}

	@RequestMapping(value = { "/permission/request/delete/{permisisonId}",
			"/permission/request/delete/{permisisonId}/page/{toPage}" }, method = RequestMethod.GET)
	public String deleteRequester(Model model, @PathVariable("permisisonId") int permissionId,
			@PathVariable(value = "toPage", required = false) String toPage, Authentication authentication) {

		Permission permission = permissionService.findById(permissionId);

		if (permission != null) {
			User u = userService.findByUsername(MvcHelper.getUsername(authentication));
			Staff staff = staffService.findById(permission.getStaffByStaffId().getStaffId());
			if (staff.getOrganization().getOrganizationId() == u.getStaff().getOrganization().getOrganizationId()) {
				List<PermissionFile> permissionFiles = permission.getPermissionfiles();
				for (int i = 0; i < permissionFiles.size(); i++) {
					File file = new File(externalPath + File.separator + "Permission" + File.separator
							+ permissionFiles.get(i).getFilePath());

					if (file.delete()) {
						System.out.println("Deleted the file: " + file.getName());
					} else {
						System.out.println("Failed to delete the file.");
					}
				}
				permissionService.deleteById(permissionId);

			}
		}

		// model.addAttribute(permission);
		if (toPage == null)
			return "redirect:/permission/index";
		if (toPage.equals("approved"))
			return "redirect:/permission/request/approved";
		else
			return "redirect:/permission/index";

	}

	private void deletePermissionWithExpireDate() {

		ArrayList<String> status = new ArrayList<String>();
		status.add("Approve");
		status.add("Reject");

		ArrayList<Permission> permissions = (ArrayList<Permission>) permissionService
				.findByListStatusAndTypeAndLessThanDateExpire(status, "inOrganize", new Date());

		for (Permission permission : permissions) {
			permissionService.deleteById(permission.getPermisisonId());
		}
	}

}