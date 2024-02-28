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
import org.cassava.model.ImgVariety;
import org.cassava.model.Variety;
import org.cassava.model.dto.ImageDTO;
import org.cassava.model.dto.NoImage;
import org.cassava.model.dto.VarietyDTO;
import org.cassava.services.ImgVarietyService;
import org.cassava.services.PlantService;
import org.cassava.services.VarietyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class VarietyController {

	@Value("${external.resoures.path}")
	private String externalPath;

	@Autowired
	private VarietyService varietyService;

	@Autowired
	private ImgVarietyService imgVarietyService;

	@Autowired
	private PlantService plantservice;

	@Autowired
	private ImgVarietyService imgvarietyService;

	@RequestMapping(value = {"/variety/index","/variety/","/variety"}, method = RequestMethod.GET)
	public String varietyIndex(Model model) {
		List<Variety> varietys = varietyService.findAll();
		List<ImageDTO> dtos = new ArrayList<ImageDTO>();
		List<Integer> hasFK = new ArrayList<Integer>();
		Random r = new Random();

		for (Variety variety : varietys) {
			ImageDTO imageDTO = new ImageDTO();
			int size = variety.getImgvarieties().size();
			if (size > 0) {
				String path = externalPath + File.separator + "Variety" + File.separator
						+ variety.getImgvarieties().get(r.nextInt(size)).getFilePath();
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
			hasFK.add(varietyService.checkFkByVarietyId(variety.getVarietyId()));
		}

		model.addAttribute("varietys", varietyService.findAll());
		model.addAttribute("images", dtos);
		model.addAttribute("hasFK", hasFK);
		return "variety/index";
	}

	@RequestMapping(value = "/variety/search", method = RequestMethod.GET)
	public String varietySearch(Model model) {
		return "redirect:/variety/index";
	}

	@RequestMapping(value = "/variety/create/", method = RequestMethod.GET)
	public String varietyCreate(Model model) {
		return "variety/create";
	}

	@RequestMapping(value = "/variety/save", method = RequestMethod.POST)
	@ResponseBody
	public String varietySave(@ModelAttribute("variety") Variety variety, Model model) {
		variety.setName(variety.getName().trim());
		variety.setTrichome(variety.getTrichome().trim());
		variety.setApicalLeavesColor(variety.getApicalLeavesColor().trim());
		variety.setYoungLeavesColor(variety.getYoungLeavesColor().trim());
		variety.setPetioleColor(variety.getPetioleColor().trim());
		variety.setCentralLeafletShape(variety.getCentralLeafletShape().trim());
		variety.setBranchingHabit(variety.getBranchingHabit().trim());
		variety.setHeightToFirstBranching(variety.getHeightToFirstBranching().trim());
		variety.setPlantHeight(variety.getPlantHeight().trim());
		variety.setStemColor(variety.getStemColor().trim());
		variety.setStarchContentRainySeason(variety.getStarchContentRainySeason().trim());
		variety.setStarchContentDrySeason(variety.getStarchContentDrySeason().trim());
		variety.setMainCharacter(variety.getMainCharacter().trim());
		variety.setLimitationNote(variety.getLimitationNote().trim());
		variety.setSource(variety.getSource().trim());
		variety.setPlant(plantservice.findById(1));
		Variety v = varietyService.save(variety);
		int id = v.getVarietyId();
		return "<input type=\"hidden\" class=\"form-control\" id=\"targetId\" value=\"" + id + "\" >";
	}	
	
	@RequestMapping(value = "/variety/edit/{id}", method = RequestMethod.GET)
	public String varietyEdit(@PathVariable("id") int id, Model model) {
		Variety variety = varietyService.findById(id);
		if(variety == null) {
			return "redirect:/variety/";
		}
		List<ImageDTO> dtos = new ArrayList<ImageDTO>();

		for (ImgVariety imgvariety : variety.getImgvarieties()) {
			ImageDTO imageDTO = new ImageDTO();
			imageDTO.setImage(externalPath + File.separator + "Variety" + File.separator + imgvariety.getFilePath());
			dtos.add(imageDTO);
		}
		List<ImgVariety> imgVariety = variety.getImgvarieties();
		model.addAttribute("imgVariety", imgVariety);
		model.addAttribute("variety", variety);
		model.addAttribute("dtoList", dtos);

		return "variety/edit";
	}

	@RequestMapping(value = "/variety/delete/{id}", method = RequestMethod.GET)
	public String varietyDelete(@PathVariable("id") int id, Model m) {
		if(varietyService.checkFkByVarietyId(id)==0 && varietyService.findById(id) != null)		
			varietyService.deleteById(id);
		return "redirect:/variety/index";
	}

	@RequestMapping(value = "/variety/image/edit/{id}", method = RequestMethod.GET)
	public String varietyImageEdit(@PathVariable("id") int id, Model model) {
		Variety variety = varietyService.findById(id);
		if(variety == null) {
			return "redirect:/variety/";
		}
		List<ImgVariety> imgVariety = variety.getImgvarieties();
		List<ImageDTO> dtos = new ArrayList<ImageDTO>();

		for (ImgVariety imgvariety : variety.getImgvarieties()) {
			ImageDTO imageDTO = new ImageDTO();
			int size = variety.getImgvarieties().size();
			if (size > 0) {
				imageDTO.setImage(
						externalPath + File.separator + "Variety" + File.separator + imgvariety.getFilePath());
			} else {
				imageDTO.setImage(externalPath + File.separator + "no-image.jpg");
			}

			dtos.add(imageDTO);
		}

		model.addAttribute("variety", variety);
		model.addAttribute("imgVariety", imgVariety);
		model.addAttribute("dtoList", dtos);

		return "variety/imageedit";
	}

	@RequestMapping(value = "/variety/image/delete/{id}", method = RequestMethod.GET)
	public String varietyImageDelete(@PathVariable("id") int id, Model model) {

		ImgVariety imgVariety = imgVarietyService.findById(id);		
		if(imgVariety == null) {
			return "redirect:/variety/";
		}
		int varietyID = imgVariety.getVariety().getVarietyId();
		imgVarietyService.delete(imgVariety);
		
		return "redirect:/variety/image/edit/" + varietyID;
	}

	@RequestMapping(value = "/variety/image/save", method = RequestMethod.POST)
	public String varietyImageSave(@ModelAttribute("varietyDTO") VarietyDTO dto, Model model) {

		Integer varietyID = dto.getVariety().getVarietyId();

		Variety variety = dto.getVariety();
		Variety v = varietyService.findById(variety.getVarietyId());

		for (CommonsMultipartFile c : dto.getFiles()) {
			if(c.getSize()>0) {
				try {
					String fileName = varietyService.writeFile(c);
					ImgVariety img = new ImgVariety(variety, fileName);
					variety.getImgvarieties().add(img);
					imgvarietyService.save(img);

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			

		}
		varietyService.save(v);
		return "redirect:/variety/image/edit/" + varietyID;
	}
	@RequestMapping(value = "/variety/check/create", method = RequestMethod.POST)
	public ResponseEntity<Response<ObjectNode>> duplicateUsername(@ModelAttribute("variety") Variety variety,BindingResult bindingResult, Model model) {

	
		variety.setName(variety.getName().trim());
		variety.setTrichome(variety.getTrichome().trim());
		variety.setApicalLeavesColor(variety.getApicalLeavesColor().trim());
		variety.setYoungLeavesColor(variety.getYoungLeavesColor().trim());
		variety.setPetioleColor(variety.getPetioleColor().trim());
		variety.setCentralLeafletShape(variety.getCentralLeafletShape().trim());
		variety.setBranchingHabit(variety.getBranchingHabit().trim());
		variety.setHeightToFirstBranching(variety.getHeightToFirstBranching().trim());
		variety.setPlantHeight(variety.getPlantHeight().trim());
		variety.setStemColor(variety.getStemColor().trim());
		variety.setStarchContentRainySeason(variety.getStarchContentRainySeason().trim());
		variety.setStarchContentDrySeason(variety.getStarchContentDrySeason().trim());
		variety.setMainCharacter(variety.getMainCharacter().trim());
		variety.setLimitationNote(variety.getLimitationNote().trim());
		variety.setSource(variety.getSource().trim());
		Variety variety2 = varietyService.findByName(variety.getName());
		Response<ObjectNode> res = new Response<>();
		res.setHttpStatus(HttpStatus.CREATED);

		boolean hasError = false;

		ObjectMapper mapper = new ObjectMapper();

		ObjectNode responObject = mapper.createObjectNode();
		res.setHttpStatus(HttpStatus.OK);
		
		if (variety2 != null) {
			hasError = true;
			res.setMessage("ชื่อซ้ำ");
			res.setHttpStatus(HttpStatus.NOT_ACCEPTABLE);
			String fieldname = "name";
			String errorMessage = res.getMessage();
			responObject.put(fieldname, errorMessage);
			res.setBody(responObject);
		}
		

		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

		Set<ConstraintViolation<Variety>> violations = validator.validate(variety);
		
		if (violations.size() > 0) {

			hasError = true;
			violations.stream().forEach((ConstraintViolation<?> error) -> {
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
	@RequestMapping(value = "/variety/check", method = RequestMethod.POST)
	public ResponseEntity<Response<ObjectNode>> registInfoFarmerSave(@ModelAttribute("variety") @Valid Variety variety,
			BindingResult bindingResult, Model m) {
		variety.setName(variety.getName().trim());
		variety.setTrichome(variety.getTrichome().trim());
		variety.setApicalLeavesColor(variety.getApicalLeavesColor().trim());
		variety.setYoungLeavesColor(variety.getYoungLeavesColor().trim());
		variety.setPetioleColor(variety.getPetioleColor().trim());
		variety.setCentralLeafletShape(variety.getCentralLeafletShape().trim());
		variety.setBranchingHabit(variety.getBranchingHabit().trim());
		variety.setHeightToFirstBranching(variety.getHeightToFirstBranching().trim());
		variety.setPlantHeight(variety.getPlantHeight().trim());
		variety.setStemColor(variety.getStemColor().trim());
		variety.setStarchContentRainySeason(variety.getStarchContentRainySeason().trim());
		variety.setStarchContentDrySeason(variety.getStarchContentDrySeason().trim());
		variety.setMainCharacter(variety.getMainCharacter().trim());
		variety.setLimitationNote(variety.getLimitationNote().trim());
		variety.setSource(variety.getSource().trim());
		ResponseEntity<Response<ObjectNode>> responseEntity = validate(variety);


		return responseEntity;
	}
	@RequestMapping(value = "/variety/update", method = RequestMethod.POST)
	public ResponseEntity<Response<ObjectNode>> registInfoFarmerSave(@ModelAttribute("varietyDTO") @Valid VarietyDTO varietyDTO,
			BindingResult bindingResult, Model m) {
		Variety variety = varietyDTO.getVariety();
		variety.setName(variety.getName().trim());
		variety.setTrichome(variety.getTrichome().trim());
		variety.setApicalLeavesColor(variety.getApicalLeavesColor().trim());
		variety.setYoungLeavesColor(variety.getYoungLeavesColor().trim());
		variety.setPetioleColor(variety.getPetioleColor().trim());
		variety.setCentralLeafletShape(variety.getCentralLeafletShape().trim());
		variety.setBranchingHabit(variety.getBranchingHabit().trim());
		variety.setHeightToFirstBranching(variety.getHeightToFirstBranching().trim());
		variety.setPlantHeight(variety.getPlantHeight().trim());
		variety.setStemColor(variety.getStemColor().trim());
		variety.setStarchContentRainySeason(variety.getStarchContentRainySeason().trim());
		variety.setStarchContentDrySeason(variety.getStarchContentDrySeason().trim());
		variety.setMainCharacter(variety.getMainCharacter().trim());
		variety.setLimitationNote(variety.getLimitationNote().trim());
		variety.setSource(variety.getSource().trim());
		ResponseEntity<Response<ObjectNode>> responseEntity = validate(varietyDTO.getVariety());
		
		if(responseEntity.getStatusCode() == HttpStatus.OK) {
		//Variety variety = varietyDTO.getVariety();
		Variety v = varietyService.findById(variety.getVarietyId());

		v.setName(variety.getName());
		v.setTrichome(variety.getTrichome());
		v.setApicalLeavesColor(variety.getApicalLeavesColor());
		v.setBranchingHabit(variety.getBranchingHabit());
		v.setCentralLeafletShape(variety.getCentralLeafletShape());
		v.setHeightToFirstBranching(variety.getHeightToFirstBranching());
		v.setLimitationNote(variety.getLimitationNote());
		v.setPetioleColor(variety.getPetioleColor());
		v.setPlantHeight(variety.getPlantHeight());
		v.setRootYield(variety.getRootYield());
		v.setSource(variety.getSource());
		v.setStarchContentDrySeason(variety.getStarchContentDrySeason());
		v.setStarchContentRainySeason(variety.getStarchContentRainySeason());
		v.setYoungLeavesColor(variety.getYoungLeavesColor());
		v.setStemColor(variety.getStemColor());
		v.setMainCharacter(variety.getMainCharacter());
		varietyService.save(v);
		}
		return responseEntity;
	}
	private	ResponseEntity<Response<ObjectNode>> validate(Variety variety){
		ObjectNode responObject = new ObjectMapper().createObjectNode();		

		Variety varietyIn = varietyService.findByName(variety.getName());
		if (varietyIn != null && varietyIn.getVarietyId() != variety.getVarietyId()) {
			responObject.put("name", "ชื่อนี้ถูกใช้แล้ว");		
		}
		if (variety.getStarchContentDrySeason().length()>0&&!(Float.parseFloat(variety.getStarchContentDrySeason()) >= 0&&Float.parseFloat(variety.getStarchContentDrySeason()) <=100)) {
			responObject.put("starchContentDrySeason", "กรุณากรอกค่าช่วง 0-100");
		}
		if (variety.getStarchContentRainySeason().length()>0&&!(Float.parseFloat(variety.getStarchContentRainySeason()) >= 0&&Float.parseFloat(variety.getStarchContentRainySeason()) <=100)) {
			responObject.put("starchContentRainySeason", "กรุณากรอกค่าช่วง 0-100");
		}
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();		
		Set<ConstraintViolation<Variety>> violations = validator.validate(variety);		
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