package org.cassava.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;

import org.cassava.api.util.model.Response;
import org.cassava.model.ImgNaturalEnemy;
import org.cassava.model.NaturalEnemy;
import org.cassava.model.TargetOfSurvey;
import org.cassava.model.dto.ImageDTO;
import org.cassava.model.dto.NaturalEnemyDTO;
import org.cassava.model.dto.NoImage;
import org.cassava.services.ImgNaturalEnemyService;
import org.cassava.services.NaturalEnemyService;
import org.cassava.services.TargetOfSurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Controller

public class NaturalEnemyController {

	@Value("${external.resoures.path}")
	private String externalPath;

	@Autowired
	private NaturalEnemyService naturalenemyService;

	@Autowired
	private ImgNaturalEnemyService imgNaturalEnemyService;

	@Autowired
	private TargetOfSurveyService targetofsurveyService;

	@RequestMapping(value = {"/naturalenemy/index","/naturalenemy/","/naturalenemy"}, method = RequestMethod.GET)
	public String naturalenemyIndex(Model model) {
		
		List<NaturalEnemy> naturalenemies = naturalenemyService.findAll();
		List<Integer> hasFK = new ArrayList<Integer>();
		List<ImageDTO> dtos = new ArrayList<ImageDTO>();
		Random r = new Random();
		for (NaturalEnemy naturalenemy : naturalenemies) {
			ImageDTO imageDTO = new ImageDTO();
			int size = naturalenemy.getImgnaturalenemies().size();
			if (size > 0) {
				String path = externalPath + File.separator + "Naturalenemy" + File.separator
						+ naturalenemy.getImgnaturalenemies().get(r.nextInt(size)).getFilePath();
				boolean checkFileExist = new File(path).exists();
				if (!checkFileExist) {
					imageDTO.setImageBase64(NoImage.noImageBase64);
				} else {
					imageDTO.setImage(path);
				}
			} else {
				imageDTO.setImageBase64(NoImage.noImageBase64);
			}
			hasFK.add(naturalenemyService.checkFkByNaturalEnemyId(naturalenemy.getNaturalEnemyId()));
			dtos.add(imageDTO);
		}
		model.addAttribute("hasFK", hasFK);
		model.addAttribute("naturalenemys", naturalenemies);
		model.addAttribute("images", dtos);
		return "/naturalenemy/naturalenemyIndex";
	}

	@RequestMapping(value = "/naturalenemy/search", method = RequestMethod.GET)
	public String naturalenemySearch(String key) {
		return "redirect:/naturalenemy/";
	}

	@RequestMapping(value = "/naturalenemy/create", method = RequestMethod.GET)
	public String naturalenemyCreate(Model model) {
		
		List<String> natList = new ArrayList<String>();
		natList.add("ตัวห้ำ");
		natList.add("ตัวเบียน");
		
		model.addAttribute("naturalEnemiesType", natList);
		
		model.addAttribute("targetOfSurvey", new TargetOfSurvey());
		return "/naturalenemy/naturalenemyCreate";
	}

	@RequestMapping(value = "/naturalenemy/edit/{id}", method = RequestMethod.GET)
	public String naturalenemyEdit(@PathVariable("id") int id, Model model) {
		NaturalEnemy naturalenemy = naturalenemyService.findById(id);
		if(naturalenemy == null) {
			return "redirect:/naturalenemy/";
		}
		List<ImageDTO> dtos = new ArrayList<ImageDTO>();

		for (ImgNaturalEnemy imgnaturalenemy : naturalenemy.getImgnaturalenemies()) {
			ImageDTO imageDTO = new ImageDTO();
			imageDTO.setImage(
					externalPath + File.separator + "Naturalenemy" + File.separator + imgnaturalenemy.getFilePath());
			dtos.add(imageDTO);
		}
		List<String> natList = new ArrayList<String>();
		natList.add("ตัวห้ำ");
		natList.add("ตัวเบียน");
		List<ImgNaturalEnemy> imgNaturalenemy = naturalenemy.getImgnaturalenemies();
		model.addAttribute("targetofsurvey", naturalenemy.getTargetofsurvey());
		model.addAttribute("naturalEnemies", natList);
		model.addAttribute("imgNaturalenemy", imgNaturalenemy);
		model.addAttribute("dtoList", dtos);
		return "/naturalenemy/naturalenemyEdit";
	}

	@RequestMapping(value = "/naturalenemy/update", method = RequestMethod.POST)
	public ResponseEntity<Response<ObjectNode>> naturalenemyUpdate(@ModelAttribute("targetofsurvey") TargetOfSurvey targetofsurvey, Model model,BindingResult bindingResult) {
		
		targetofsurvey.setName(targetofsurvey.getName().trim());
		targetofsurvey.getNaturalenemy().setCommonName(targetofsurvey.getNaturalenemy().getCommonName().trim());
		targetofsurvey.getNaturalenemy().setScientificName(targetofsurvey.getNaturalenemy().getScientificName().trim());
		targetofsurvey.getNaturalenemy().setType(targetofsurvey.getNaturalenemy().getType().trim());
		targetofsurvey.getNaturalenemy().setControlMethod(targetofsurvey.getNaturalenemy().getControlMethod().trim());
		targetofsurvey.getNaturalenemy().setReleaseRate(targetofsurvey.getNaturalenemy().getReleaseRate().trim());
		targetofsurvey.getNaturalenemy().setSource(targetofsurvey.getNaturalenemy().getSource().trim());
		
		ResponseEntity<Response<ObjectNode>> responseEntity = validate(targetofsurvey);
		if(responseEntity.getStatusCode() == HttpStatus.OK) {
					targetofsurveyService.save(targetofsurvey);
			
		}
		return responseEntity;
	}

	@RequestMapping(value = "/naturalenemy/delete/{id}", method = RequestMethod.GET)
	public String naturalenemyDelete(@PathVariable("id") int id, Model model) {
		if(naturalenemyService.findById(id) == null || targetofsurveyService.findById(id)== null) {
			return "redirect:/naturalenemy/";
		}
		
		
		if(naturalenemyService.checkFkByNaturalEnemyId(id)==0)
		{
			naturalenemyService.deleteById(id);
			targetofsurveyService.deleteById(id);
		}
		return "redirect:/naturalenemy/";
	}

	@RequestMapping(value = "/naturalenemy/image/edit/{id}", method = RequestMethod.GET)
	public String naturalenemyImageEdit(@PathVariable("id") int id, Model model) {
		NaturalEnemy naturalenemy = naturalenemyService.findById(id);
		if(naturalenemyService.findById(id) == null) {
			return "redirect:/naturalenemy/";
		}
		List<ImgNaturalEnemy> imgNaturalenemy = naturalenemy.getImgnaturalenemies();
		List<ImageDTO> dtos = new ArrayList<ImageDTO>();

		for (ImgNaturalEnemy imgnaturalenemy : naturalenemy.getImgnaturalenemies()) {
			ImageDTO imageDTO = new ImageDTO();
			imageDTO.setImage(
					externalPath + File.separator + "Naturalenemy" + File.separator + imgnaturalenemy.getFilePath());
			dtos.add(imageDTO);
		}
		
		model.addAttribute("targetofsurvey", naturalenemy.getTargetofsurvey());
		model.addAttribute("naturalenemy", naturalenemy);
		model.addAttribute("imgNaturalenemy", imgNaturalenemy);
		model.addAttribute("dtoList", dtos);

		return "naturalenemy/naturalenemyImageEdit";
	}

	@RequestMapping(value = "/naturalenemy/image/delete/{id}", method = RequestMethod.GET)
	public String varietyImageDelete(@PathVariable("id") int id, Model model) {

		ImgNaturalEnemy imgNaturalenemy = imgNaturalEnemyService.findById(id);
		if(imgNaturalEnemyService.findById(id) == null) {
			return "redirect:/naturalenemy/";
		}

		imgNaturalEnemyService.deleteByID(id);

		int naturalenemyID = imgNaturalenemy.getNaturalenemy().getNaturalEnemyId();
		return "redirect:/naturalenemy/image/edit/" + naturalenemyID;
	}

	@RequestMapping(value = "/naturalenemy/image/save", method = RequestMethod.POST)
	public String naturalenemyImageSave(@ModelAttribute("naturalEnemyDTO") NaturalEnemyDTO dto, Model model) {
		int naturalenemyID = dto.getNaturalEnemy().getNaturalEnemyId();
		NaturalEnemy naturalenemy = naturalenemyService.findById(dto.getNaturalEnemy().getNaturalEnemyId());
		for (CommonsMultipartFile c : dto.getFiles()) {
			if (c.getSize() > 0) {
				try {
					String fileName = naturalenemyService.writeFile(c);
					ImgNaturalEnemy img = new ImgNaturalEnemy(naturalenemy, fileName);
					naturalenemy.getImgnaturalenemies().add(img);
					imgNaturalEnemyService.save(img);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		return "redirect:/naturalenemy/image/edit/" + naturalenemyID;
	}
	@RequestMapping(value = "/naturalenemy/check", method = RequestMethod.POST)
	public ResponseEntity<Response<ObjectNode>> naturalenemyCheck(@ModelAttribute("targetOfSurvey") TargetOfSurvey targetOfSurvey, Model model, Authentication authentication) {
		
		targetOfSurvey.setName(targetOfSurvey.getName().trim());
		targetOfSurvey.getNaturalenemy().setCommonName(targetOfSurvey.getNaturalenemy().getCommonName().trim());
		targetOfSurvey.getNaturalenemy().setScientificName(targetOfSurvey.getNaturalenemy().getScientificName().trim());
		targetOfSurvey.getNaturalenemy().setType(targetOfSurvey.getNaturalenemy().getType().trim());
		targetOfSurvey.getNaturalenemy().setControlMethod(targetOfSurvey.getNaturalenemy().getControlMethod().trim());
		targetOfSurvey.getNaturalenemy().setReleaseRate(targetOfSurvey.getNaturalenemy().getReleaseRate().trim());
		targetOfSurvey.getNaturalenemy().setSource(targetOfSurvey.getNaturalenemy().getSource().trim());		
		
		ResponseEntity<Response<ObjectNode>> responseEntity = validate(targetOfSurvey);	
		//System.out.println("check natural");
		return responseEntity;
	}
	private	ResponseEntity<Response<ObjectNode>> validate(TargetOfSurvey targetOfSurvey){
		ObjectNode responObject = new ObjectMapper().createObjectNode();		

		TargetOfSurvey targetOfSurveyIn = targetofsurveyService.findByName(targetOfSurvey.getName());
		if (targetOfSurveyIn != null && targetOfSurveyIn.getTargetOfSurveyId() != targetOfSurvey.getTargetOfSurveyId()) {
			responObject.put("name", "ชื่อนี้ถูกใช้แล้ว");		
		}
		NaturalEnemy naturalEnemyIn = naturalenemyService.findByScientificName(targetOfSurvey.getNaturalenemy().getScientificName());
		if (naturalEnemyIn != null && naturalEnemyIn.getTargetofsurvey().getTargetOfSurveyId() != targetOfSurvey.getTargetOfSurveyId()) {
			responObject.put("scientificName", "ชื่อวิทยาศาสตร์นี้ถูกใช้แล้ว");		
		}
		
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();		
		Set<ConstraintViolation<NaturalEnemy>> violationsnaturalenemy = validator.validate(targetOfSurvey.getNaturalenemy());		
		Set<ConstraintViolation<TargetOfSurvey>> violations = validator.validate(targetOfSurvey);		
		
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
		if (violationsnaturalenemy.size() > 0) {				
			violationsnaturalenemy.stream().forEach((ConstraintViolation<?> error) -> {
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
	@RequestMapping(value = "/naturalenemy/save", method = RequestMethod.POST)
	public ResponseEntity<Response<ObjectNode>> registInfoFarmerSave(@ModelAttribute("targetOfSurvey") @Valid TargetOfSurvey target,
			BindingResult bindingResult, Model m) {
		Response<ObjectNode> res = new Response<>();

		res.setHttpStatus(HttpStatus.CREATED);

		boolean hasError = false;

		ObjectMapper mapper = new ObjectMapper();

		ObjectNode responObject = mapper.createObjectNode();

		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

		Set<ConstraintViolation<NaturalEnemy>> violations = validator.validate(target.getNaturalenemy());
		Set<ConstraintViolation<TargetOfSurvey>> violations2 = validator.validate(target);
		if (violations.size() > 0) {

			hasError = true;
			violations.stream().forEach((ConstraintViolation<?> error) -> {
				responObject.put(error.getPropertyPath().toString(), error.getMessage());

			});
		}
		if (violations2.size() > 0) {

			hasError = true;
			violations2.stream().forEach((ConstraintViolation<?> error) -> {
				responObject.put(error.getPropertyPath().toString(), error.getMessage());

			});
		}

		if (bindingResult.hasErrors()) {
			hasError = true;
			bindingResult.getAllErrors().forEach((error) -> {
				String fieldname = ((FieldError) error).getField();
				String errorMessage = error.getDefaultMessage();
				responObject.put(fieldname, errorMessage);
			});

		}

		if (hasError) {
			res.setHttpStatus(HttpStatus.BAD_REQUEST);
			res.setMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
			res.setBody(responObject);

			return new ResponseEntity<Response<ObjectNode>>(res, res.getHttpStatus());
		}

		return new ResponseEntity<Response<ObjectNode>>(res, res.getHttpStatus());
	}

}
