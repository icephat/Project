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
import org.cassava.model.Disease;
import org.cassava.model.ImgDisease;
import org.cassava.model.Pathogen;
import org.cassava.model.PathogenType;
import org.cassava.model.Pest;
import org.cassava.model.Plant;
import org.cassava.model.TargetOfSurvey;
import org.cassava.model.dto.DiseaseDTO;
import org.cassava.model.dto.ImageDTO;
import org.cassava.model.dto.NoImage;
import org.cassava.services.DiseaseService;
import org.cassava.services.ImgDiseaseService;
import org.cassava.services.PathogenService;
import org.cassava.services.PathogenTypeService;
import org.cassava.services.PestService;
import org.cassava.services.PlantService;
import org.cassava.services.TargetOfSurveyService;
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
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Controller
public class DiseaseController {

	@Value("${external.resoures.path}")
	private String externalPath;

	@Autowired
	private DiseaseService diseaseService;

	@Autowired
	private TargetOfSurveyService targetofsurveyService;

	@Autowired
	private PestService pestService;

	@Autowired
	private PlantService plantService;

	@Autowired
	private PathogenTypeService pathogentypeService;

	@Autowired
	private ImgDiseaseService imgdiseaseService;

	@Autowired
	private PathogenService pathogenService;

	@RequestMapping(value = { "/disease/index", "/disease/", "/disease" }, method = RequestMethod.GET)
	public String diseaseIndex(Model model) {

		List<Disease> diseases = diseaseService.findAll();
		List<ImageDTO> dtos = new ArrayList<ImageDTO>();
		List<Integer> hasFK = new ArrayList<Integer>();
		Random r = new Random();
		for (Disease disease : diseases) {
			ImageDTO imageDTO = new ImageDTO();

			int size = disease.getImgdiseases().size();
			if (size > 0) {

				String path = externalPath + File.separator + "Disease" + File.separator
						+ disease.getImgdiseases().get(r.nextInt(size)).getFilePath();
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
			hasFK.add(diseaseService.checkFkBydiseaseId(disease.getDiseaseId()));
		}
		model.addAttribute("hasFK", hasFK);
		model.addAttribute("diseases", diseaseService.findAll());
		model.addAttribute("images", dtos);
		return "/disease/diseaseIndex";
	}

	@RequestMapping(value = "/disease/create/pestpathogen/{id}", method = RequestMethod.GET)
	public String diseaseMax(@PathVariable("id") int id, Model model) {
		Disease disease = diseaseService.findById(id);
		if (disease == null) {
			return "redirect:/disease/";
		}
		model.addAttribute("Disease", disease);
		model.addAttribute("Pests", disease.getPests());
		model.addAttribute("Pathogens", disease.getPathogens());
		model.addAttribute("nameT", disease.getTargetofsurvey());
		return "/disease/diseaseCreatePestPathogen";
	}

	@RequestMapping(value = "/disease/create", method = RequestMethod.GET)
	public String diseaseCreate(Model model) {
		List<Pest> pests = pestService.findAll();
		List<Pathogen> pathogens = pathogenService.findAll();

		model.addAttribute("targetOfSurvey", new TargetOfSurvey());
		model.addAttribute("Pests", pests);
		model.addAttribute("Pathogens", pathogens);
		return "/disease/diseaseCreate";
	}

	@RequestMapping(value = "/disease/edit/pestpathogen/{id}", method = RequestMethod.GET)
	public String diseaseCreatePest(@PathVariable("id") int id, Model model) {
		Disease disease = diseaseService.findById(id);
		if (disease == null) {
			return "redirect:/disease/";
		}
		model.addAttribute("Disease", disease);
		model.addAttribute("Pests", disease.getPests());
		model.addAttribute("Pathogens", disease.getPathogens());
		
		model.addAttribute("nameT", disease.getTargetofsurvey());
		return "/disease/diseaseEditPestPathogen";
	}

	@RequestMapping(value = "/disease/createpest/{id}", method = RequestMethod.GET)
	public String diseaseCreatePestnew(@PathVariable("id") int id, Model model) {
		Disease disease = diseaseService.findById(id);
		if (disease == null) {
			return "redirect:/disease/";
		}
		List<Pest> Pests = pestService.findAll();
		model.addAttribute("Disease", disease);
		model.addAttribute("Pests", Pests);

		List<Pest> pestSum = pestService.findByDiseaseNotIn(id);

		model.addAttribute("nameT", disease.getTargetofsurvey());
		model.addAttribute("Pestssum", pestSum);

		model.addAttribute("Pestt", disease.getPests());
		return "/disease/diseaseCreatePest";
	}

	@RequestMapping(value = "/disease/createpathogen/{id}", method = RequestMethod.GET)
	public String diseaseCreatePathogennew(@PathVariable("id") int id, Model model) {

		Disease disease = diseaseService.findById(id);
		if (disease == null) {
			return "redirect:/disease/";
		}
		List<Pathogen> pathogens = pathogenService.findAll();
		List<PathogenType> pathogenTypes = pathogentypeService.findAll();

		model.addAttribute("Disease", disease);
		model.addAttribute("pathogens", pathogens);
		model.addAttribute("pathogentype", pathogenTypes);
		model.addAttribute("pathogenn", disease.getPathogens());

		List<Pathogen> pathogenSum = pathogenService.findByDiseaseNotIn(id);

		model.addAttribute("nameT", disease.getTargetofsurvey());
		model.addAttribute("pathogensum", pathogenSum);
		return "/disease/diseaseCreatePathogen";
	}

	@RequestMapping(value = "/disease/addpest/{id}/{ids}", method = RequestMethod.GET)
	public String diseaseCreatePestnewAdd(@PathVariable("id") int id, @PathVariable("ids") int ids, Model model) {
		Disease disease = diseaseService.findById(id);
		Pest pest = pestService.findById(ids);
		if (disease == null || pest == null) {
			return "redirect:/disease/";
		}
		List<Pest> pestss = disease.getPests();
		pestss.add(pest);
		diseaseService.save(disease);

		return "redirect:/disease/createpest/{id}";
	}

	@RequestMapping(value = "/disease/addpathogen/{id}/{ids}", method = RequestMethod.GET)
	public String diseaseCreatePathogennewAdd(@PathVariable("id") int id, @PathVariable("ids") int ids, Model model) {
		Disease disease = diseaseService.findById(id);
		Pathogen pathogen = pathogenService.findById(ids);
		if (disease == null || pathogen == null) {
			return "redirect:/disease/";
		}
		List<Pathogen> pathogens = disease.getPathogens();
		pathogens.add(pathogen);
		diseaseService.save(disease);

		return "redirect:/disease/createpathogen/{id}";
	}

	@RequestMapping(value = "/disease/deletepest/{id}/{ids}", method = RequestMethod.GET)
	public String diseaseCreatePestnewDel(@PathVariable("id") int id, @PathVariable("ids") int ids, Model model) {

		Disease disease = diseaseService.findById(id);
		Pest pest = pestService.findById(ids);
		if (disease == null || pest == null) {
			return "redirect:/disease/";
		}
		List<Pest> pestss = disease.getPests();
		Pest newPest = new Pest();
		for (Pest idp : pestss) {
			if (idp.getPestId() == pest.getPestId())
				newPest = idp;
		}
		pestss.remove(newPest);
		diseaseService.save(disease);

		return "redirect:/disease/edit/pestpathogen/{id}";
	}

	@RequestMapping(value = "/disease/deletepathogen/{id}/{ids}", method = RequestMethod.GET)
	public String diseaseCreatePathogennewDel(@PathVariable("id") int id, @PathVariable("ids") int ids, Model model) {

		Disease disease = diseaseService.findById(id);
		Pathogen pathogen = pathogenService.findById(ids);
		if (disease == null || pathogen == null) {
			return "redirect:/disease/";
		}
		List<Pathogen> pathogenss = disease.getPathogens();
		Pathogen newPathogen = new Pathogen();

		for (Pathogen idp : pathogenss) {
			if (idp.getPathogenId() == pathogen.getPathogenId())
				newPathogen = idp;
		}
		pathogenss.remove(newPathogen);
		diseaseService.save(disease);

		return "redirect:/disease/edit/pestpathogen/{id}";
	}

	@RequestMapping(value = "/disease/edit/{id}", method = RequestMethod.GET)
	public String diseaseEdit(@PathVariable("id") int id, Model model) {

		Disease disease = diseaseService.findById(id);
		if (disease == null) {
			return "redirect:/disease/";
		}
		model.addAttribute("Pests", disease.getPests());
		model.addAttribute("Pathogens", disease.getPathogens());
		List<ImageDTO> dtos = new ArrayList<ImageDTO>();

		for (ImgDisease imgdisease : disease.getImgdiseases()) {
			ImageDTO imageDTO = new ImageDTO();
			imageDTO.setImage(externalPath + File.separator + "Disease" + File.separator + imgdisease.getFilePath());
			dtos.add(imageDTO);
		}
		List<ImgDisease> imgdiseases = disease.getImgdiseases();
		model.addAttribute("targetofsurvey", disease.getTargetofsurvey());
		model.addAttribute("imgDisease", imgdiseases);
		model.addAttribute("dtoList", dtos);
		
		return "/disease/diseaseEdit";
	}

	@RequestMapping(value = "/disease/update", method = RequestMethod.POST)
	public ResponseEntity<Response<ObjectNode>> diseaseUpdate(
			@ModelAttribute("targetofsurvey") TargetOfSurvey targetOfSurvey, Model model, BindingResult bindingResult) {
		List<Pathogen> pathogenss = diseaseService.findById(targetOfSurvey.getTargetOfSurveyId()).getPathogens();
		List<Pest> pests = diseaseService.findById(targetOfSurvey.getTargetOfSurveyId()).getPests();
		targetOfSurvey.setName(targetOfSurvey.getName().trim());
		targetOfSurvey.getDisease().setCode(targetOfSurvey.getDisease().getCode().trim());
		targetOfSurvey.getDisease().setSymptom(targetOfSurvey.getDisease().getSymptom().trim());
		targetOfSurvey.getDisease().setControlDisease(targetOfSurvey.getDisease().getControlDisease().trim());
		targetOfSurvey.getDisease().setControlPest(targetOfSurvey.getDisease().getControlPest().trim());
		targetOfSurvey.getDisease().setSource(targetOfSurvey.getDisease().getSource().trim());
		ResponseEntity<Response<ObjectNode>> responseEntity = validate(targetOfSurvey);
		if (responseEntity.getStatusCode() == HttpStatus.OK) {
			System.out.println(targetOfSurvey.getDisease().getDiseaseId());
			Disease diseaseTOS = diseaseService.findById(targetOfSurvey.getDisease().getDiseaseId());
			
			TargetOfSurvey ts = diseaseTOS.getTargetofsurvey();
			Plant p = plantService.findById(1);
			targetOfSurvey.getDisease().setPlant(p);
			ts.setName(targetOfSurvey.getName());
			targetofsurveyService.save(ts);
			
			diseaseTOS.setPests(pests);
			diseaseTOS.setPathogens(pathogenss);
			diseaseTOS.setCode(targetOfSurvey.getDisease().getCode());
			diseaseTOS.setControlDisease(targetOfSurvey.getDisease().getControlDisease());
			diseaseTOS.setControlPest(targetOfSurvey.getDisease().getControlPest());
			diseaseTOS.setSource(targetOfSurvey.getDisease().getSource());
			diseaseTOS.setSymptom(targetOfSurvey.getDisease().getSymptom());
			diseaseService.save(diseaseTOS);
			//diseaseService.save(targetOfSurvey.getDisease());

		}
		return responseEntity;

	}

	@RequestMapping(value = "/disease/delete/{diseaseId}", method = RequestMethod.GET)
	public String diseaseDelete(@PathVariable("diseaseId") int Id, Model model) {
		if (diseaseService.checkFkBydiseaseId(Id) == 0 && diseaseService.findById(Id) != null
				&& targetofsurveyService.findById(Id) != null) {
			diseaseService.deleteById(Id);
			targetofsurveyService.deleteById(Id);
		}
		return "redirect:/disease/index";
	}

	@RequestMapping(value = "/disease/image/edit/{id}", method = RequestMethod.GET)
	public String diseaseImageEdit(@PathVariable("id") int id, Model model) {
		Disease disease = diseaseService.findById(id);
		if (disease == null) {
			return "redirect:/disease/";
		}
		List<ImgDisease> imgdiseases = disease.getImgdiseases();
		List<ImageDTO> dtos = new ArrayList<ImageDTO>();

		for (ImgDisease imgdisease : disease.getImgdiseases()) {
			ImageDTO imageDTO = new ImageDTO();
			imageDTO.setImage(externalPath + File.separator + "Disease" + File.separator + imgdisease.getFilePath());
			dtos.add(imageDTO);
		}
		model.addAttribute("disease", disease);
		model.addAttribute("imgdisease", imgdiseases);
		model.addAttribute("dtoList", dtos);

		return "disease/diseaseImageEdit";
	}

	@RequestMapping(value = "/disease/image/delete/{id}", method = RequestMethod.GET)
	public String diseaseImageDelete(@PathVariable("id") int id, Model model) {

		ImgDisease imgdisease = imgdiseaseService.findById(id);
		if (imgdisease == null) {
			return "redirect:/disease/";
		}

		imgdiseaseService.deleteById(id);

		int diseaseID = imgdisease.getDisease().getDiseaseId();
		return "redirect:/disease/image/edit/" + diseaseID;
	}

	@RequestMapping(value = "/disease/image/save", method = RequestMethod.POST)
	public String diseaseImageSave(@ModelAttribute("DiseaseDTO") DiseaseDTO dto, Model model) {
		int diseaseID = dto.getDisease().getDiseaseId();
		Disease disease = diseaseService.findById(dto.getDisease().getDiseaseId());
		for (CommonsMultipartFile c : dto.getFiles()) {
			if (c.getSize() > 0) {
				try {
					String fileName = diseaseService.writeFile(c);
					ImgDisease img = new ImgDisease(disease, fileName);
					disease.getImgdiseases().add(img);
					imgdiseaseService.save(img);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return "redirect:/disease/image/edit/" + diseaseID;
	}

	@RequestMapping(value = "/disease/check", method = RequestMethod.POST)
	public ResponseEntity<Response<ObjectNode>> duplicateUsername(
			@ModelAttribute("targetOfSurvey") TargetOfSurvey target, BindingResult bindingResult, Model model) {
		//target.getName().trim();
		target.setName(target.getName().trim());
		target.getDisease().setCode(target.getDisease().getCode().trim());
		target.getDisease().setSymptom(target.getDisease().getSymptom().trim());
		target.getDisease().setControlDisease(target.getDisease().getControlDisease().trim());
		target.getDisease().setControlPest(target.getDisease().getControlPest().trim());
		target.getDisease().setSource(target.getDisease().getSource().trim());
		System.out.println(target.getName());
		ResponseEntity<Response<ObjectNode>> responseEntity = validate(target);
		return responseEntity;
	}

	private ResponseEntity<Response<ObjectNode>> validate(TargetOfSurvey targetSurvey) {
		ObjectNode responObject = new ObjectMapper().createObjectNode();

		TargetOfSurvey targetSurveyIn = targetofsurveyService.findByName(targetSurvey.getName());
		if (targetSurveyIn != null && targetSurveyIn.getTargetOfSurveyId() != targetSurvey.getTargetOfSurveyId()) {
			responObject.put("name", "ชื่อนี้ถูกใช้แล้ว");
		}
		
		
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<TargetOfSurvey>> violations = validator.validate(targetSurvey);
		Set<ConstraintViolation<Disease>> violationdisease = validator.validate(targetSurvey.getDisease());
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
		if (violationdisease.size() > 0) {
			violationdisease.stream().forEach((ConstraintViolation<?> error) -> {
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
		if (responObject.size() == 0)
			res.setHttpStatus(HttpStatus.OK);
		else {
			res.setHttpStatus(HttpStatus.BAD_REQUEST);
			res.setMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
			res.setBody(responObject);
		}
		return new ResponseEntity<Response<ObjectNode>>(res, res.getHttpStatus());
	}

	@RequestMapping(value = "/disease/savediseaseT", method = RequestMethod.POST)
	public ResponseEntity<Response<ObjectNode>> registInfoFarmerSaveT(
			@ModelAttribute("targetOfSurvey") @Valid TargetOfSurvey target, BindingResult bindingResult, Model m) {
		Response<ObjectNode> res = new Response<>();

		res.setHttpStatus(HttpStatus.CREATED);

		boolean hasError = false;

		ObjectMapper mapper = new ObjectMapper();

		ObjectNode responObject = mapper.createObjectNode();

		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

		Set<ConstraintViolation<TargetOfSurvey>> violations = validator.validate(target);
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

}
