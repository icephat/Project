package org.cassava.web.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;

import org.cassava.api.util.model.Response;
import org.cassava.model.Pathogen;
import org.cassava.services.PathogenService;
import org.cassava.services.PathogenTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;



@Controller
public class PathogenController {
	
	@Autowired
	private PathogenService pathogenService;
	
	@Autowired
	private PathogenTypeService pathogentypeService;
	
	@RequestMapping(value = {"/pathogen/index","/pathogen/","/pathogen"}, method = RequestMethod.GET)
	public String pathogenIndex(Pathogen pathogen, Model model) {
		List<Integer> hasFK = new ArrayList<Integer>();
		List<Pathogen> pathogens = pathogenService.findAll();
		for (Pathogen pathogen2 : pathogens ){			
			hasFK.add(pathogenService.checkFkBypathogenId(pathogen2.getPathogenId()));
		}
		model.addAttribute("hasFK", hasFK);
		model.addAttribute("pathogens", pathogens);		
		return "pathogen/pathogenIndex";
	}
	
	@RequestMapping(value = "/pathogen/create", method = RequestMethod.GET)
	public String pathogenCreate(Model model) {
		model.addAttribute("pathogentype",pathogentypeService.findAll());
		return "pathogen/pathogenCreate";
	}	
	
	@RequestMapping(value = "/pathogen/edit/{id}", method = RequestMethod.GET)
	public String pathogenEdit(@PathVariable("id") int id,Model model) {	
		Pathogen pathogen = pathogenService.findById(id);
		if(pathogen == null) {
			return "redirect:/pathogen/";
		}
		model.addAttribute("pathogen",pathogen);
		model.addAttribute("pType",pathogentypeService.findAll());		
		return "pathogen/pathogenEdit";
	}
	
	@RequestMapping(value = "/pathogen/update", method = RequestMethod.POST)
	public ResponseEntity<Response<ObjectNode>> pathogenUpdate(@ModelAttribute("pathogen") @Valid Pathogen pathogen,BindingResult bindingResult ,Model model) {
		pathogen.setScientificName(pathogen.getScientificName().trim());
		ResponseEntity<Response<ObjectNode>> responseEntity = validate(pathogen);	
		if(responseEntity.getStatusCode() == HttpStatus.OK) {
			pathogenService.save(pathogen);
			}
			return responseEntity;
	}
	
	@RequestMapping(value = "/pathogen/delete/{id}", method = RequestMethod.GET)
	public String pathogenDelete(@PathVariable("id") int id,Model model) {	
		if(pathogenService.checkFkBypathogenId(id)==0 && pathogenService.findById(id) != null)
		{
			pathogenService.deleteById(id);
		}				
		return "redirect:/pathogen/";
	}
		
	@RequestMapping(value = "/pathogen/save", method = RequestMethod.POST)
	public ResponseEntity<Response<ObjectNode>> registInfoFarmerSave(@RequestParam(name="pathogen.pathogentype")String pathogenId,@ModelAttribute("pathogen") Pathogen pathogen,
			BindingResult bindingResult, Model m) {
		pathogen.setScientificName(pathogen.getScientificName().trim());
		
		ResponseEntity<Response<ObjectNode>> responseEntity = validate(pathogen);	
		if(responseEntity.getStatusCode() == HttpStatus.OK) {
		pathogen.setPathogentype(pathogentypeService.findById(Integer.parseInt(pathogenId)));
		pathogenService.save(pathogen);
		}
		return responseEntity;
	}
	private	ResponseEntity<Response<ObjectNode>> validate(Pathogen pathogen){
		ObjectNode responObject = new ObjectMapper().createObjectNode();		

		Pathogen pathogenIn = pathogenService.findByName(pathogen.getScientificName());
		if (pathogenIn != null && pathogenIn.getPathogenId() != pathogen.getPathogenId()) {
			responObject.put("scientificName", "ชื่อวิทยาศาสตร์นี้ถูกใช้แล้ว");		
		}
		
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();		
		Set<ConstraintViolation<Pathogen>> violations = validator.validate(pathogen);		
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
}
