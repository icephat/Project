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
import org.cassava.model.ImgPest;
import org.cassava.model.Pest;
import org.cassava.model.PestPhase;
import org.cassava.model.PestPhaseSurvey;
import org.cassava.model.TargetOfSurvey;
import org.cassava.model.dto.ImageDTO;
import org.cassava.model.dto.NoImage;
import org.cassava.model.dto.PestDTO;
import org.cassava.services.ImgPestService;
import org.cassava.services.PestManagementService;
import org.cassava.services.PestPhaseService;
import org.cassava.services.PestPhaseSurveyService;
import org.cassava.services.PestService;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Controller
public class PestController {

	@Value("${external.resoures.path}")
	private String externalPath;

	@Autowired
	private PestService pestService;

	@Autowired
	private PestManagementService pestmanagementService;

	@Autowired
	private ImgPestService imgpestService;

	@Autowired
	private TargetOfSurveyService targetofsurveyService;

	@Autowired
	private PestPhaseSurveyService pestphaseSurveyService;

	@Autowired
	private PestPhaseService pestphaseService;

	@RequestMapping(value = {"/pest/index","/pest/","/pest"}, method = RequestMethod.GET)
	public String pestIndex(Model model) {
		List<Pest> pests = pestService.findAll();
		List<ImageDTO> dtos = new ArrayList<ImageDTO>();
		List<Integer> hasFK = new ArrayList<Integer>();
		Random r = new Random();

		for (Pest pest : pests) {
			ImageDTO imageDTO = new ImageDTO();
			int size = pest.getImgpests().size();
			if (size > 0) {
				String path = externalPath + File.separator + "Pest" + File.separator
						+ pest.getImgpests().get(r.nextInt(size)).getFilePath();
				boolean checkFileExist = new File(path).exists();
				if (!checkFileExist) {
					imageDTO.setImageBase64(NoImage.noImageBase64);
				} else {
					imageDTO.setImage(path);
				}
			} else {
				imageDTO.setImageBase64(NoImage.noImageBase64);
			}
			dtos.add(imageDTO);
			hasFK.add(pestService.checkFkByPestId(pest.getPestId()));
		}
		model.addAttribute("hasFK", hasFK);
		model.addAttribute("pests", pestService.findAll());
		model.addAttribute("images", dtos);
		return "pest/pestIndex";
	}

	@RequestMapping(value = "/pest/search", method = RequestMethod.GET)
	public String pestSearch(String key) {
		return "redirect:/pest/";
	}

	@RequestMapping(value = "/pest/create", method = RequestMethod.GET)
	public String pestCreate(Model model) {
		return "pest/pestCreate";
	}

	@RequestMapping(value = "/pest/save", method = RequestMethod.POST)
	@ResponseBody
	public String pestSave(@ModelAttribute("pest") Pest pest, Model model) {
		pest.setName(pest.getName().trim());
		pest.setSource(pest.getSource().trim());
		pest.setScientificName(pest.getScientificName().trim());
		Pest pests = null;
		pests = pestService.findByName(pest.getName());
		if (pests != null) {
			return "duplicate name";
		}
		Pest p = pestService.save(pest);
		int id = p.getPestId();
		model.addAttribute("id", id);
		List<PestPhase> pp = pestphaseService.findAll();
		for (PestPhase i : pp) {
			TargetOfSurvey ts = new TargetOfSurvey();
			PestPhaseSurvey ps = new PestPhaseSurvey();
			ts.setName(p.getName()+"("+i.getName()+")");
			ps.setTargetofsurvey(ts);
			ps.setPest(p);
			ps.setPestphase(i);
			ps = pestphaseSurveyService.save(ps);
			ts.setPestphasesurvey(ps);
			targetofsurveyService.save(ts);
		}
		return "<input type=\"hidden\" class=\"form-control\" id=\"targetId\" value=\"" + id + "\" >";
	}

	@RequestMapping(value = "/pest/edit/{id}", method = RequestMethod.GET)
	public String pestEdit(@ModelAttribute("id") int id, Model model) {
		Pest pest = pestService.findById(id);
		if(pest == null) {
			return "redirect:/pest/";
		}
		List<ImgPest> imgPest = pest.getImgpests();
		List<ImageDTO> dtos = new ArrayList<ImageDTO>();

		for (ImgPest imgpest : pest.getImgpests()) {
			ImageDTO imageDTO = new ImageDTO();
			imageDTO.setImage(externalPath + File.separator + "Pest" + File.separator + imgpest.getFilePath());
			dtos.add(imageDTO);
		}
		model.addAttribute("dtoList", dtos);
		model.addAttribute("imgPest", imgPest);
		model.addAttribute("pest", pestService.findById(id));
		model.addAttribute("pestcides", pestmanagementService.findAllById(pest.getPestId()));
		return "pest/pestEdit";
	}

	@RequestMapping(value = "/pest/update", method = RequestMethod.POST)
	public ResponseEntity<Response<ObjectNode>> pestUpdate(@ModelAttribute("pest") Pest pest,BindingResult bindingResult, Model model) {
		pest.setName(pest.getName().trim());
		pest.setSource(pest.getSource().trim());
		pest.setScientificName(pest.getScientificName().trim());
		ResponseEntity<Response<ObjectNode>> responseEntity = validate(pest);
		if(responseEntity.getStatusCode() == HttpStatus.OK) {
			pestService.save(pest);
		}
		return responseEntity;
	}

	@RequestMapping(value = "/pest/delete/{id}", method = RequestMethod.GET)
	public String pestDelete(@ModelAttribute("id") int id, Model model) {
		if(pestService.checkFkByPestId(id)==0 && pestService.findById(id) != null)
			pestService.deleteById(id);
		return "redirect:/pest/";
	}

	@RequestMapping(value = "/pest/image/edit/{id}", method = RequestMethod.GET)
	public String pestImageEdit(@PathVariable("id") int id, Model model) {
		Pest pest = pestService.findById(id);
		if(pest == null) {
			return "redirect:/pest/";
		}
		List<ImgPest> imgPest = pest.getImgpests();
		List<ImageDTO> dtos = new ArrayList<ImageDTO>();

		for (ImgPest imgpest : pest.getImgpests()) {			
			ImageDTO imageDTO = new ImageDTO();
			imageDTO.setImage(externalPath + File.separator + "Pest" + File.separator + imgpest.getFilePath());
			dtos.add(imageDTO);
		}

		model.addAttribute("pest", pest);
		model.addAttribute("imgPest", imgPest);
		model.addAttribute("dtoList", dtos);

		return "pest/pestImageEdit";
	}

	@RequestMapping(value = "/pest/image/delete/{id}", method = RequestMethod.GET)
	public String pestImageDelete(@PathVariable("id") int id, Model model) {

		ImgPest imgPest = imgpestService.findById(id);
		if(imgPest == null) {
			return "redirect:/pest/";
		}
		///////////////////////////////////
		/////// delete image file
		///////////////////////////////////

		imgpestService.deleteByID(id);

		int pestID = imgPest.getPest().getPestId();
		return "redirect:/pest/image/edit/" + pestID;
	}

	@RequestMapping(value = "/pest/image/save", method = RequestMethod.POST)
	public String pestImageSave(@ModelAttribute("pestDTO") PestDTO dto, Model model) {
		int pestID = dto.getPest().getPestId();
		Pest pest = pestService.findById(dto.getPest().getPestId());
		for (CommonsMultipartFile c : dto.getFiles()) {
			if (c.getSize() > 0) {
				try {
					String fileName = pestService.writeFile(c);
					ImgPest img = new ImgPest(pest, fileName);
					pest.getImgpests().add(img);
					imgpestService.save(img);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return "redirect:/pest/image/edit/" + pestID;
	}

	
	@RequestMapping(value = "/pest/check", method = RequestMethod.POST)
	public ResponseEntity<Response<ObjectNode>> pestCheck(@ModelAttribute("pest") Pest pest, Model model, Authentication authentication) {
		pest.setName(pest.getName().trim());
		pest.setSource(pest.getSource().trim());
		pest.setScientificName(pest.getScientificName().trim());
		ResponseEntity<Response<ObjectNode>> responseEntity = validate(pest);	
		
		return responseEntity;
	}
	private	ResponseEntity<Response<ObjectNode>> validate(Pest pest){
		ObjectNode responObject = new ObjectMapper().createObjectNode();		

		Pest pestIn = pestService.findByName(pest.getName());
		Pest pestInsci = pestService.findBySciName(pest.getScientificName());
		if (pestIn != null && pestIn.getPestId() != pest.getPestId()) {
			responObject.put("name", "ชื่อนี้ถูกใช้แล้ว");		
		}
		if (pestInsci != null && pestInsci.getPestId() != pest.getPestId()) {
			responObject.put("scientificName", "ชื่อวิทยาศาสตร์นี้ถูกใช้แล้ว");		
		}
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();		
		Set<ConstraintViolation<Pest>> violations = validator.validate(pest);		
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