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
import org.cassava.model.PestManagement;
import org.cassava.services.PestManagementService;
import org.cassava.services.PestService;
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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Controller
public class PestCideController {
	@Value("${external.resoures.path}")
	private String externalPath;
	
	@Autowired
	private PestManagementService PestmanagementService;
	
	@Autowired
	private PestService pestservice;

	@RequestMapping(value = "/pestcide/create", method = RequestMethod.GET)
	public String pestCideCreate(Model model) {
		
		return "pestcide/pestCideCreate";
	}
	
	@RequestMapping(value = "/pestcide/save", method = RequestMethod.POST)
	public String pestCideSave(@ModelAttribute("pestcide")PestManagement pestCide ,Model model) {
		PestmanagementService.save(pestCide);
		return "redirect:/pest/create";
	}
	
	@RequestMapping(value = "/pestcide/edit/{id}", method = RequestMethod.GET)
	public String pestCideEdit(@PathVariable("id") int id ,Model model) {
		List<String> natList = new ArrayList<String>();
		natList.add("ฉีดพ่นเฉพาะจุด");
		natList.add("แช่ท่อนพันธุ์");
		model.addAttribute("pestcides", natList);
		model.addAttribute("pestcide",PestmanagementService.findById(id));
		return "pestcide/pestCideEdit";
	}
	
	@RequestMapping(value = "/pestcide/pestmanagement/edit/{id}", method = RequestMethod.GET)
	public String pestCideEditNew(@PathVariable("id") int id ,Model model) {
		List<String> natList = new ArrayList<String>();
		natList.add("ฉีดพ่นเฉพาะจุด");
		natList.add("แช่ท่อนพันธุ์");
		model.addAttribute("pestcides", natList);
		model.addAttribute("pestcide",PestmanagementService.findById(id));
		return "pestcide/pestCideEditNew";
	}
	
	@RequestMapping(value = "/pestcide/update", method = RequestMethod.POST)
	public ResponseEntity<Response<ObjectNode>> pestCideUpdate(@ModelAttribute("pestcide")PestManagement pestcide ,Model model) {
		pestcide.setPesticideName(pestcide.getPesticideName().trim());
		pestcide.setPesticideNameEng(pestcide.getPesticideNameEng().trim());
		pestcide.setPercentApi(pestcide.getPercentApi().trim());
		pestcide.setPesticideMechanism(pestcide.getPesticideMechanism().trim());
		pestcide.setToxicityLevel(pestcide.getToxicityLevel().trim());
		pestcide.setInstruction(pestcide.getInstruction().trim());
		pestcide.setInstructionDetail(pestcide.getInstructionDetail().trim());
		ResponseEntity<Response<ObjectNode>> responseEntity = validate(pestcide);	
		if(responseEntity.getStatusCode() == HttpStatus.OK) {
		PestmanagementService.save(pestcide);
		}
		return responseEntity;
		//PestmanagementService.save(pestCide);
		//return "redirect:/pest/edit/"+pestCide.getPest().getPestId();
	}
	
	@RequestMapping(value = "/pestcide/delete/{id}", method = RequestMethod.GET)
	public String pestCideDelete(@PathVariable("id") int id,Model model) {
		int idPest = PestmanagementService.findById(id).getPest().getPestId();
		PestmanagementService.deleteById(id);
		return "redirect:/pest/edit/"+idPest;
	}
	
	@RequestMapping(value = "/pestcide/pestmanagement/delete/{id}", method = RequestMethod.GET)
	public String pestCideDeleteNew(@PathVariable("id") int id,Model model) {
		int idPest = PestmanagementService.findById(id).getPest().getPestId();
		PestmanagementService.deleteById(id);
		return "redirect:/pestcide/pestmanagement/create/"+idPest;
	}
	
	@RequestMapping(value = "/pestcide/pestmanagement/create/{id}", method = RequestMethod.GET)
	public String pestCideCreateNew(@PathVariable("id") int id, Model model) {
		System.out.println(id);
		model.addAttribute("pest", pestservice.findById(id));
		model.addAttribute("pestcides", PestmanagementService.findAllById(id));
		return "pestcide/pestCideCreateNew";
	}
	
	@RequestMapping(value = "/pestcide/pestmanagement/update", method = RequestMethod.POST)
	public ResponseEntity<Response<ObjectNode>> pestCideUpdateNew(@ModelAttribute("pestcide")PestManagement pestcide ,Model model, Authentication authentication) {
//		System.out.println(pestCide.getPest());
//		System.out.println(pestcide.getPesticideName());
		pestcide.setPesticideName(pestcide.getPesticideName().trim());
		pestcide.setPesticideNameEng(pestcide.getPesticideNameEng().trim());
		pestcide.setPercentApi(pestcide.getPercentApi().trim());
		pestcide.setPesticideMechanism(pestcide.getPesticideMechanism().trim());
		pestcide.setToxicityLevel(pestcide.getToxicityLevel().trim());
		pestcide.setInstruction(pestcide.getInstruction().trim());
		pestcide.setInstructionDetail(pestcide.getInstructionDetail().trim());
		ResponseEntity<Response<ObjectNode>> responseEntity = validate(pestcide);	
		if(responseEntity.getStatusCode() == HttpStatus.OK) {
		PestmanagementService.save(pestcide);
		}
		return responseEntity;
	}
	private	ResponseEntity<Response<ObjectNode>> validate(PestManagement pestManagement){
		ObjectNode responObject = new ObjectMapper().createObjectNode();		
		if(!pestManagement.getPesticideName().isEmpty()) {
		PestManagement pestManagementIn = PestmanagementService.findByName(pestManagement.getPesticideName());
		
		if (pestManagementIn != null && pestManagementIn.getPestManagementId() != pestManagement.getPestManagementId()) {
			responObject.put("pesticideName", "ชื่อนี้ถูกใช้แล้ว");		
		}
		}
		System.out.println("------");
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();		
		Set<ConstraintViolation<PestManagement>> violations = validator.validate(pestManagement);		
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
		System.out.println(responObject.size());
		return new ResponseEntity<Response<ObjectNode>>(res, res.getHttpStatus());
	}
	@RequestMapping(value = "/pestcide/add/{id}", method = RequestMethod.GET)
		public String pestCideAdd(@PathVariable("id") int id, Model model) {
		model.addAttribute("pest", pestservice.findById(id));
		model.addAttribute("pestcides", PestmanagementService.findAllById(id));
		return "pestcide/pestCideAdd";
	}
	@RequestMapping(value = "/pestcide/add/edit/{id}", method = RequestMethod.GET)
	public String pestCideAddInEdit(@PathVariable("id") int id, Model model) {
	model.addAttribute("pest", pestservice.findById(id));
	model.addAttribute("pestcides", PestmanagementService.findAllById(id));
	return "pestcide/pestCideAddInEdit";
}
	@RequestMapping(value = "/pestcide/pestmanagement/save", method = RequestMethod.POST)
	public String pestCideSaveNew(@ModelAttribute("pestcide")PestManagement pestCide ,Model model) {
		PestmanagementService.save(pestCide);
		return "pestcide/pestCideCreateNew";
	}
	
}
