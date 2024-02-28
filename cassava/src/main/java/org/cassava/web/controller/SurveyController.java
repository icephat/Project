package org.cassava.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.cassava.api.util.model.Response;
import org.cassava.model.ImgSurveyTargetPoint;
import org.cassava.model.Planting;
import org.cassava.model.Survey;
import org.cassava.model.SurveyTarget;
import org.cassava.model.SurveyTargetPoint;
import org.cassava.model.TargetOfSurvey;
import org.cassava.model.User;
import org.cassava.model.dto.ImageDTO;
import org.cassava.model.dto.ImgSurveyTargetPointDTO;
import org.cassava.model.dto.SelectedImageSurveyDTO;
import org.cassava.model.dto.SurveyDTO;
import org.cassava.model.dto.SurveyTargetPointDTO;
import org.cassava.services.ImgSurveyTargetPointService;
import org.cassava.services.PlantingService;
import org.cassava.services.SurveyPointService;
import org.cassava.services.SurveyService;
import org.cassava.services.SurveyTargetPointService;
import org.cassava.services.SurveyTargetService;
import org.cassava.services.TargetOfSurveyService;
import org.cassava.services.UserService;
import org.cassava.util.ImageBase64Helper;
import org.cassava.util.MvcHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
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
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Controller
public class SurveyController {

	@Value("${external.resoures.path}")
	private String externalPath;

	@Autowired
	private UserService userService;

	@Autowired
	private SurveyService surveyService;

	@Autowired
	private ImgSurveyTargetPointService imgSurveyTargetPointService;

	@Autowired
	private SurveyTargetService surveyTargetService;

	@Autowired
	private PlantingService plantingService;

	@Autowired
	private TargetOfSurveyService targetofsurveyService;

	@Autowired
	private SurveyTargetPointService surveyTargetPointService;

	@Autowired
	private SurveyPointService surveyPointService;

	public void setPageIndex(int sizeItem, Model model, int pageCurrent, int pageSize) {
		int firstItem = 0, lastItem = 0;
		if (sizeItem > 0) {
			firstItem = (pageSize * (pageCurrent - 1)) + 1;
			lastItem = firstItem + sizeItem - 1;
		}
		model.addAttribute("firstItem", firstItem);
		model.addAttribute("lastItem", lastItem);
	}

	@RequestMapping(value = { "/survey/index", "/survey/", "/survey" }, method = RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("sizePages", Arrays.asList(10, 25, 50));
		return "/survey/index";
	}

	@RequestMapping(value = { "/survey/index/total" }, method = RequestMethod.GET)
	@ResponseBody
	public int indexTableTotal(@RequestParam(value = "key", required = false) String key,
			@RequestParam(value = "startDate", required = false) Date startDate,
			@RequestParam(value = "endDate", required = false) Date endDate, Authentication authentication,
			Model model) {
		User user = userService.findByUsername(MvcHelper.getUsername(authentication));
		int totalItem;
		if ((key == null || key == "") && (startDate == null || endDate == null)) {
			totalItem = surveyService.findByUserInField(user).size();
		} else {
			totalItem = surveyService.findByUserInFieldAndKeyAndDate(user, key, startDate, endDate).size();
		}

		return totalItem;
	}

	@RequestMapping(value = { "/survey/index/page/{page}/value/{value}" }, method = RequestMethod.GET)
	public String indexTable(@PathVariable("page") int page, @PathVariable("value") int value,
			@RequestParam(value = "key", required = false) String key,
			@RequestParam(value = "startDate", required = false) Date startDate,
			@RequestParam(value = "endDate", required = false) Date endDate, Authentication authentication,
			Model model) {
		User user = userService.findByUsername(MvcHelper.getUsername(authentication));

		List<Survey> surveys;
		if ((key == null || key == "") && (startDate == null || endDate == null)) {
			surveys = surveyService.findByUserInField(user, page, value);
		} else {
			surveys = surveyService.findByUserInFieldAndKeyAndDate(user, key, startDate, endDate, page, value);
		}
		model.addAttribute("surveys", surveys);
		setPageIndex(surveys.size(), model, page, value);
		Format formatter = new SimpleDateFormat("d MMM yyyy", new Locale("th", "TH"));
		model.addAttribute("formatter", formatter);
		return "/survey/indexTable";
	}

	@RequestMapping(value = "/survey/planting", method = RequestMethod.GET)
	public String planting(Model model, Authentication authentication) {
		model.addAttribute("sizePages", Arrays.asList(10, 25, 50));
		return "/survey/planting";
	}

	@RequestMapping(value = "/survey/planting/page/{page}/value/{value}", method = RequestMethod.GET)
	public String selectPlantingPage(@PathVariable("page") int page, @PathVariable("value") int value,
			@RequestParam(value = "key", required = false) String key,
			@RequestParam(value = "startDate", required = false) Date startDate,
			@RequestParam(value = "endDate", required = false) Date endDate, Authentication authentication,
			Model model) {
		User user = userService.findByUsername(MvcHelper.getUsername(authentication));

		List<Planting> plantings;
		if ((key == null || key == "") && (startDate == null || endDate == null)) {
			plantings = plantingService.findByUserInField(user, page, value);
		} else {
			plantings = plantingService.findByUserInFieldAndKeyAndDate(user, key, startDate, endDate, page, value);
		}
		model.addAttribute("plantings", plantings);
		setPageIndex(plantings.size(), model, page, value);
		Format formatter = new SimpleDateFormat("d MMM yyyy", new Locale("th", "TH"));
		model.addAttribute("formatter", formatter);
		return "/survey/plantingTable";
	}

	@RequestMapping(value = { "/survey/planting/total" }, method = RequestMethod.GET)
	@ResponseBody
	public int selectPlantingTableTotal(@RequestParam(value = "key", required = false) String key,
			@RequestParam(value = "startDate", required = false) Date startDate,
			@RequestParam(value = "endDate", required = false) Date endDate, Authentication authentication,
			Model model) {
		User user = userService.findByUsername(MvcHelper.getUsername(authentication));
		int totalItem;

		if ((key == null || key == "") && (startDate == null || endDate == null)) {
			totalItem = plantingService.findByUserInField(user).size();
		} else {
			totalItem = plantingService.findByUserInFieldAndKeyAndDate(user, key, startDate, endDate).size();
		}
		return totalItem;
	}

	@RequestMapping(value = "/survey/plantingId/{id}", method = RequestMethod.GET)
	public String plantingId(@PathVariable("id") int id, Model model, Authentication authentication) {
		Planting planting = plantingService.findById(id);
		if (planting == null) {
			return "redirect:/survey/";
		}
		model.addAttribute("sizePages", Arrays.asList(10, 25, 50));
		model.addAttribute("planting", planting);
		Format formatter = new SimpleDateFormat("d MMM yyyy", new Locale("th", "TH"));
		model.addAttribute("formatter", formatter);
		return "/survey/plantingId";
	}

	@RequestMapping(value = "/survey/plantingId/{id}/page/{page}/value/{value}", method = RequestMethod.GET)
	public String plantingIdTable(@PathVariable("page") int page, @PathVariable("value") int value,
			@RequestParam(value = "startDate", required = false) Date startDate,
			@RequestParam(value = "endDate", required = false) Date endDate, Authentication authentication,
			@PathVariable("id") int id, Model model) {
		User user = userService.findByUsername(MvcHelper.getUsername(authentication));
		List<Survey> surveys = surveyService.findByUserInFieldAndPlanting(user, id, page, value);
		model.addAttribute("surveys", surveys);
		setPageIndex(surveys.size(), model, page, value);
		Format formatter = new SimpleDateFormat("d MMM yyyy", new Locale("th", "TH"));
		model.addAttribute("formatter", formatter);
		return "/survey/plantingIdTable";
	}

	@RequestMapping(value = { "/survey/plantingId/{id}/total" }, method = RequestMethod.GET)
	@ResponseBody
	public int selectPlantingIdTableTotal(@PathVariable("id") int id,
			@RequestParam(value = "key", required = false) String key,
			@RequestParam(value = "startDate", required = false) Date startDate,
			@RequestParam(value = "endDate", required = false) Date endDate, Authentication authentication,
			Model model) {
		User user = userService.findByUsername(MvcHelper.getUsername(authentication));
		int totalItem;

		totalItem = surveyService.findByUserInFieldAndPlanting(user, id).size();
		return totalItem;
	}

	@RequestMapping(value = "/survey/create/{id}", method = RequestMethod.GET)
	public String create(@PathVariable("id") int id, Model model) {
		Planting p = plantingService.findById(id);
		if (p == null) {
			return "redirect:/survey/";
		}

		model.addAttribute("rainType", Arrays.asList("ไม่มีฝน", "ฝนทิ้งช่วง", "ฝนปรอย", "ฝนตกชุก"));
		model.addAttribute("sunlightType", Arrays.asList("แดดจัด", "แดดน้อยฟ้าครึ้ม"));
		model.addAttribute("dewType", Arrays.asList("ไม่มีน้ำค้าง", "น้ำค้างเล็กน้อย", "น้ำค้างแรง"));
		model.addAttribute("percentDamageFromHerbicide",
				Arrays.asList("0", "10", "20", "30", "40", "50", "60", "70", "80", "90", "100"));

		/*
		 * // note List<Object> obj = targetofsurveyService.findNaturalEnemy();
		 * List<Integer> naturalEnemyIdList = new ArrayList<Integer>(); List<String>
		 * naturalEnemyNameList = new ArrayList<String>(); for (Object o : obj) {
		 * Object[] ob = (Object[]) o; naturalEnemyIdList.add((Integer) ob[0]);
		 * naturalEnemyNameList.add((ob[1]).toString()); }
		 * model.addAttribute("naturalEnemyIdList", naturalEnemyIdList);
		 * model.addAttribute("naturalEnemyListSize", naturalEnemyIdList.size());
		 * model.addAttribute("naturalEnemyNameList", naturalEnemyNameList);
		 */
		model.addAttribute("naturalEnemyList", targetofsurveyService.findNaturalEnemy());

		List<Object> obj1 = targetofsurveyService.findDisease();
		List<Integer> diseaseIdList = new ArrayList<Integer>();
		List<String> diseaseNameList = new ArrayList<String>();
		for (Object o : obj1) {
			Object[] ob = (Object[]) o;
			diseaseIdList.add((Integer) ob[0]);
			diseaseNameList.add((ob[1]).toString());
		}

		List<Object> obj2 = targetofsurveyService.findPest();
		List<Integer> pestIdList = new ArrayList<Integer>();
		List<String> pestNameList = new ArrayList<String>();
		for (Object o : obj2) {
			Object[] ob = (Object[]) o;
			pestIdList.add((Integer) ob[0]);
			pestNameList.add((ob[1]).toString());
		}

		model.addAttribute("diseaseIdList", diseaseIdList);
		model.addAttribute("diseaseListSize", diseaseIdList.size());
		model.addAttribute("diseaseNameList", diseaseNameList);
		model.addAttribute("pestIdList", pestIdList);
		model.addAttribute("pestListSize", pestIdList.size());
		model.addAttribute("pestNameList", pestNameList);

		model.addAttribute("plantingId", id);
		model.addAttribute("plantingName", p.getName());
		model.addAttribute("plantingCode", p.getCode());

		model.addAttribute("userInFields", p.getField().getUserinfields());
		model.addAttribute("plantingDate", p.getPrimaryPlantPlantingDate());
		return "/survey/create";
	}

	@RequestMapping(value = "/survey/save", method = RequestMethod.POST)
	public ResponseEntity<Response<String>> save(@ModelAttribute("surveyDTO") SurveyDTO surveyDTO, Model model,
			Authentication authentication) throws Exception {
		surveyDTO.getSurvey().setTemperature(Float.parseFloat(surveyDTO.getTemperature()));
		// }

		surveyDTO.getSurvey().setHumidity(Float.parseFloat(surveyDTO.getHumidity()));

		Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(surveyDTO.getSurveyDate());
		Survey s = surveyDTO.getSurvey();
		s.setDate(date1);
		s.setNumPlantInPoint(20);
		s.setNumPointSurvey(5);
		s.setSurveyPatternDisease("ระดับ 0-5");
		s.setSurveyPatternNaturalEnemy("เปอร์เซ็นต์");
		s.setSurveyPatternPest("เปอร์เซ็นต์");

		////// set create user /////
		User user = userService.findByUsername(MvcHelper.getUsername(authentication));
		s.setUserByCreateBy(user);
		s.setCreateDate(new Date());
		s.setUserByLastUpdateBy(user);
		s.setLastUpdateDate(new Date());

		////// check other //////
		if (s.getUserByImgOwner().getUserId() == 0) {
			s.setUserByImgOwner(null);
		}
		if (s.getUserByImgPhotographer().getUserId() == 0) {
			s.setUserByImgPhotographer(null);
		}
		s.setStatus("Editing");

		Survey sSave = surveyService.save(s);

		surveyPointService.saveSurveyPoint(sSave);
		surveyTargetService.saveSurveyTarget(surveyDTO.getSurveytargetPestPhases(), sSave);
		surveyTargetService.saveSurveyTarget(surveyDTO.getSurveytargetNaturalEnemies(), sSave);
		surveyTargetService.saveSurveyTarget(surveyDTO.getSurveytargetDiseases(), sSave);
		Response<String> resp = new Response<String>();
		resp.setHttpStatus(HttpStatus.OK);
		int id = s.getSurveyId();
		resp.setBody(id + "");

		return new ResponseEntity<Response<String>>(resp, resp.getHttpStatus());

		// return "redirect:/survey/point/" + s.getSurveyId();
		// return responseEntity;
	}

	@RequestMapping(value = "/survey/check", method = RequestMethod.POST)
	public ResponseEntity<Response<ObjectNode>> checkSave(@ModelAttribute("surveyDTO") SurveyDTO surveyDTO, Model model,
			Authentication authentication) throws Exception {

		ResponseEntity<Response<ObjectNode>> responseEntity = validate(surveyDTO);
//		if(responseEntity.getStatusCode() == HttpStatus.OK) {
//		//if(surveyDTO.getTemperature()!=null) {
//		surveyDTO.getSurvey().setTemperature(Float.parseFloat(surveyDTO.getTemperature()));
//		//}
//	
//		surveyDTO.getSurvey().setHumidity(Float.parseFloat(surveyDTO.getHumidity()));
//
//		Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(surveyDTO.getSurveyDate());
//		Survey s = surveyDTO.getSurvey();
//		s.setDate(date1);
//		s.setNumPlantInPoint(20);
//		s.setNumPointSurvey(5);
//		s.setSurveyPatternDisease("ระดับ 0-5");
//		s.setSurveyPatternNaturalEnemy("เปอร์เซ็นต์");
//		s.setSurveyPatternPest("เปอร์เซ็นต์");
//
//		////// set create user /////
//		User user = userService.findByUsername(MvcHelper.getUsername(authentication));
//		s.setUserByCreateBy(user);
//		s.setCreateDate(new Date());
//		s.setUserByLastUpdateBy(user);
//		s.setLastUpdateDate(new Date());
//
//		////// check other //////
//		if (s.getUserByImgOwner().getUserId() == 0) {
//			s.setUserByImgOwner(null);
//		}
//		if (s.getUserByImgPhotographer().getUserId() == 0) {
//			s.setUserByImgPhotographer(null);
//		}
//		s.setStatus("Editing");
//		
//		Survey sSave = surveyService.save(s);
//
//		surveyPointService.saveSurveyPoint(sSave);
//		surveyTargetService.saveSurveyTarget(surveyDTO.getSurveytargetPestPhases(), sSave);
//		surveyTargetService.saveSurveyTarget(surveyDTO.getSurveytargetNaturalEnemies(), sSave);
//		surveyTargetService.saveSurveyTarget(surveyDTO.getSurveytargetDiseases(), sSave);
////		ObjectNode responObject = new ObjectMapper().createObjectNode();	
////		responObject.put("humidity", "กรุณากรอกความชื้นสัมพัทธ์");	
////		responseEntity.
//		}
		// return "redirect:/survey/point/" + s.getSurveyId();
		return responseEntity;
	}

	private ResponseEntity<Response<ObjectNode>> validate(SurveyDTO surveyDTO) throws ParseException {
		ObjectNode responObject = new ObjectMapper().createObjectNode();

//		surveyDTO.getSurvey().getTemperature();
		if (surveyDTO.getTemperature() == "" || surveyDTO.getTemperature().isEmpty()) {
			// ifelse {
			responObject.put("temperature", "กรุณากรอกอุณหภูมิ");
			// }
		} else if (!(Float.parseFloat(surveyDTO.getTemperature()) >= 0)) {
			responObject.put("temperature", "กรุณากรอกอุณหภูมิที่ถูกต้อง");
		}
		if (surveyDTO.getHumidity() == "" || surveyDTO.getHumidity().isEmpty()) {
			// else {
			responObject.put("humidity", "กรุณากรอกความชื้นสัมพัทธ์");
			// }
		} else if (!(Float.parseFloat(surveyDTO.getHumidity()) >= 0
				&& Float.parseFloat(surveyDTO.getHumidity()) <= 100)) {
			responObject.put("humidity", "กรุณากรอกความชื้นสัมพัทธ์ช่วง 0-100");
		}
		if (surveyDTO.getSurvey().getUserByImgOwner() == null) {
			responObject.put("userByImgOwner", "กรุณาเลือกเจ้าของภาพ");
		}
		if (surveyDTO.getSurvey().getUserByImgPhotographer() == null) {
			responObject.put("userByImgPhotographer", "กรุณาเลือกผู้ถ่ายภาพ");
		}

		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

		Survey s = surveyDTO.getSurvey();
		if (surveyDTO.getSurveyDate().length() != 0) {
			Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(surveyDTO.getSurveyDate());
			// Survey survey = surveyDTO.getSurvey();
			s.setDate(date1);
		}
		Set<ConstraintViolation<Survey>> violations = validator.validate(s);
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
		if (responObject.size() == 0) {
			res.setHttpStatus(HttpStatus.OK);
			ObjectNode responObject2 = new ObjectMapper().createObjectNode();
			responObject2.put("ID", surveyDTO.getSurvey().getSurveyId());
			res.setBody(responObject);
		} else {
			res.setHttpStatus(HttpStatus.BAD_REQUEST);
			res.setMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
			res.setBody(responObject);
		}
		return new ResponseEntity<Response<ObjectNode>>(res, res.getHttpStatus());
	}

	@RequestMapping(value = "/survey/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") int id, Model model) {
		if (surveyService.findById(id) != null) {
			surveyService.deleteById(id);
		}
		return "redirect:/survey/";
	}

	@RequestMapping(value = "/survey/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") int id, Model model) {
		Survey s = surveyService.findById(id);
		if (s == null) {
			return "redirect:/survey";
		}
		model.addAttribute("rainType", Arrays.asList("ไม่มีฝน", "ฝนทิ้งช่วง", "ฝนปรอย", "ฝนตกชุก"));
		model.addAttribute("sunlightType", Arrays.asList("แดดจัด", "แดดน้อยฟ้าครึ้ม"));
		model.addAttribute("dewType", Arrays.asList("ไม่มีน้ำค้าง", "น้ำค้างเล็กน้อย", "น้ำค้างแรง"));
		model.addAttribute("percentDamageFromHerbicide",
				Arrays.asList("0", "10", "20", "30", "40", "50", "60", "70", "80", "90", "100"));

		model.addAttribute("surveyDiseaseList", surveyTargetService.findAllDiseaseBySurveyId(id));
		model.addAttribute("surveyPestPhaseList", surveyTargetService.findAllPestPhaseBySurveyId(id));
		model.addAttribute("surveyNaturalenemyList", surveyTargetService.findAllNaturalEnemyBySurveyId(id));

		model.addAttribute("checkImgPhotographerOther", (s.getUserByImgPhotographer() == null) ? 0 : 1);
		model.addAttribute("checkImgOwnerOther", (s.getUserByImgOwner() == null) ? 0 : 1);
		model.addAttribute("userInFields", s.getPlanting().getField().getUserinfields());
		SurveyDTO surveyDTO = new SurveyDTO();
		surveyDTO.setSurvey(s);
		model.addAttribute("surveyDTO1", surveyDTO);
		model.addAttribute("plantingDate", s.getPlanting().getPrimaryPlantPlantingDate());
		return "survey/edit";
	}

	@RequestMapping(value = "/survey/update", method = RequestMethod.POST)
	public ResponseEntity<Response<ObjectNode>> update(@ModelAttribute("surveyDTO") SurveyDTO surveyDTO, Model model,
			Authentication authentication) throws Exception {
		ResponseEntity<Response<ObjectNode>> responseEntity = validate(surveyDTO);
		if (responseEntity.getStatusCode() == HttpStatus.OK) {
			Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(surveyDTO.getSurveyDate());
			Survey s = surveyDTO.getSurvey();
			Survey ss = surveyService.findById(s.getSurveyId());
			s.setPlanting(ss.getPlanting());
			s.setDate(date1);
			s.setNumPlantInPoint(ss.getNumPlantInPoint());
			s.setNumPointSurvey(ss.getNumPointSurvey());
			s.setSurveyPatternDisease(ss.getSurveyPatternDisease());
			s.setSurveyPatternNaturalEnemy(ss.getSurveyPatternNaturalEnemy());
			s.setSurveyPatternPest(ss.getSurveyPatternPest());
			s.setCreateDate(ss.getCreateDate());
			s.setStatus(ss.getStatus());

			////// check other //////
			if (s.getUserByImgOwner().getUserId() == 0) {
				s.setUserByImgOwner(null);
			} else {
				s.setImgOwnerOther("");
			}
			if (s.getUserByImgPhotographer().getUserId() == 0) {
				s.setUserByImgPhotographer(null);
			} else {
				s.setImgPhotographerOther("");
			}
			User user = userService.findByUsername(MvcHelper.getUsername(authentication));
			s.setUserByLastUpdateBy(user);
			s.setLastUpdateDate(new Date());
			Survey sSave = surveyService.save(s);

			surveyTargetService.updateSurveyTarget(surveyDTO.getSurveytargetPestPhases(), sSave);
			surveyTargetService.updateSurveyTarget(surveyDTO.getSurveytargetNaturalEnemies(), sSave);
			surveyTargetService.updateSurveyTarget(surveyDTO.getSurveytargetDiseases(), sSave);
		}
		// return "redirect:/survey/";
		return responseEntity;
	}

	@RequestMapping(value = "/survey/point/{id}", method = RequestMethod.GET)
	public String surveyPoint(@PathVariable("id") int id, Model model) {
		Survey s = surveyService.findById(id);
		if (s == null) {
			return "redirect:/survey";
		}
		model.addAttribute("survey", s);
		model.addAttribute("plantingName", s.getPlanting().getName());
		model.addAttribute("plantingCode", s.getPlanting().getCode());

		model.addAttribute("surveyDiseaseList", surveyTargetService.findTypeDiseaseBySurveyId(id));
		model.addAttribute("surveyPestPhaseList", surveyTargetService.findTypePestPhaseBySurveyId(id));
		model.addAttribute("surveyNaturalenemyList", surveyTargetService.findTypeNaturalEnemyBySurveyId(id));
		return "/survey/surveyPoint";
	}

	@RequestMapping(value = "/survey/point/update", method = RequestMethod.POST)
	public String surveyPointUpdate(@ModelAttribute("Survey") Survey survey, Model model) {
		Survey s = surveyService.findById(survey.getSurveyId());
		s.setStatus(survey.getStatus());
		surveyService.save(s);
		return "redirect:/survey/";
	}

	@RequestMapping(value = "/survey/point/{id}/info", method = RequestMethod.POST)
	public ResponseEntity<String> surveyPointUpdate(@PathVariable("id") int id, @Param("point") int point,
			@Param("item") int item, @Param("value") int value, Model model) {
		SurveyTargetPoint stp = surveyTargetPointService.findBySurveyTargetIdAndPointNumberAndItemNumber(id, point,
				item);
		if (stp != null) {
			stp.setValue(value);
			stp = surveyTargetPointService.save(stp);
			surveyTargetService.updateDamage(stp.getSurveytarget().getSurveyTargetId());
			return new ResponseEntity<String>(HttpStatus.OK);
		}
		return new ResponseEntity<String>(HttpStatus.valueOf("STP is null"));

	}

	@RequestMapping(value = "/survey/point/{id}/info", method = RequestMethod.GET)
	public String surveyPointInfo(@PathVariable("id") int id, Model model) {
		SurveyTarget st = surveyTargetService.findById(id);

		if (st == null) {
			return "redirect:/survey";
		}
		List<Integer> pointNumber = new ArrayList<Integer>(5);
		for (int i = 0; i < 5; i++) {
			pointNumber.add(i);
		}
		List<Integer> itemNumber = new ArrayList<Integer>(20);
		for (int i = 0; i < 20; i++) {
			itemNumber.add(i);
		}
		model.addAttribute("pointNumber", pointNumber);
		model.addAttribute("itemNumber", itemNumber);

		model.addAttribute("surveyTarget", st);
		model.addAttribute("surveyId", st.getSurvey().getSurveyId());

		List<SurveyTargetPoint> surveyTargetPoint = st.getSurveytargetpoints();
		int[][] surveyTargetPointId = new int[5][20];
		int[][] value = new int[5][20];
		int[][] countImage = new int[5][20];
		for (SurveyTargetPoint stp : surveyTargetPoint) {
			value[stp.getPointNumber()][stp.getItemNumber()] = stp.getValue();
			surveyTargetPointId[stp.getPointNumber()][stp.getItemNumber()] = stp.getSurveyTargetPointId();
			countImage[stp.getPointNumber()][stp.getItemNumber()] = stp.getImgsurveytargetpoints().size();
		}
		SurveyTargetPointDTO surveyTargetPointDTO = new SurveyTargetPointDTO();
		surveyTargetPointDTO.setSurveyValue(value);
		surveyTargetPointDTO.setSurveyTargetPointIdList(surveyTargetPointId);
		model.addAttribute("surveyTargetPointDTO", surveyTargetPointDTO);
		model.addAttribute("countImage", countImage);

		return "/survey/surveyPointInfo";
	}

	@RequestMapping(value = "/survey/point/{id}/infopercent", method = RequestMethod.GET)
	public String surveyPointInfoPercent(@PathVariable("id") int id, Model model) {
		SurveyTarget st = surveyTargetService.findById(id);
		if (st == null) {
			return "redirect:/survey";
		}
		List<Integer> pointNumber = new ArrayList<Integer>(5);
		for (int i = 0; i < 5; i++) {
			pointNumber.add(i);
		}
		List<Integer> itemNumber = new ArrayList<Integer>(20);
		for (int i = 0; i < 20; i++) {
			itemNumber.add(i);
		}
		model.addAttribute("pointNumber", pointNumber);
		model.addAttribute("itemNumber", itemNumber);

		model.addAttribute("surveyTarget", st);
		model.addAttribute("surveyId", st.getSurvey().getSurveyId());

		List<SurveyTargetPoint> surveyTargetPoint = st.getSurveytargetpoints();
		int[][] surveyTargetPointId = new int[5][20];
		int[][] value = new int[5][20];
		for (SurveyTargetPoint stp : surveyTargetPoint) {
			value[stp.getPointNumber()][stp.getItemNumber()] = stp.getValue();
			surveyTargetPointId[stp.getPointNumber()][stp.getItemNumber()] = stp.getSurveyTargetPointId();
		}
		SurveyTargetPointDTO surveyTargetPointDTO = new SurveyTargetPointDTO();
		surveyTargetPointDTO.setSurveyValue(value);
		surveyTargetPointDTO.setSurveyTargetPointIdList(surveyTargetPointId);
		model.addAttribute("surveyTargetPointDTO", surveyTargetPointDTO);

		return "/survey/surveyPointInfoPercent";
	}

	/////////////////////////////////////////////////////////////////

	@RequestMapping(value = "/survey/point/save", method = RequestMethod.POST)
	public String surveyPointSave(@ModelAttribute("SurveyTargetPointDTO") SurveyTargetPointDTO surveyTargetPointDTO,
			Model model) {
		SurveyTarget surveyTarget = surveyTargetService.findById(surveyTargetPointDTO.getSurveyTargetId());
		surveyTarget.setStatus(surveyTargetPointDTO.getStatus());
		surveyTargetService.save(surveyTarget);

		int[][] surveyTargetPointId = surveyTargetPointDTO.getSurveyTargetPointIdList();
		int[][] value = surveyTargetPointDTO.getSurveyValue();
		surveyTargetPointService.updateSurveyTargetPoint(surveyTargetPointId, value, surveyTarget.getSurveyTargetId());
		return "redirect:/survey/point/" + surveyTarget.getSurvey().getSurveyId();
	}

	@RequestMapping(value = "/survey/image/{id}", method = RequestMethod.GET)
	public String createImage(@PathVariable("id") int id, Model model) {
		SurveyTargetPoint surveyTargetPoint = surveyTargetPointService.findById(id);
		if (surveyTargetPoint == null) {
			return "redirect:/survey";
		}

		List<ImgSurveyTargetPoint> imgSurveyTargetPoint = surveyTargetPoint.getImgsurveytargetpoints();
		List<ImageDTO> dtos = new ArrayList<ImageDTO>();

		for (ImgSurveyTargetPoint imgSurveyTargetPoint2 : surveyTargetPoint.getImgsurveytargetpoints()) {
			ImageDTO imageDTO = new ImageDTO();
			imageDTO.setImage(externalPath + File.separator + "SurveyTargetPoint" + File.separator
					+ imgSurveyTargetPoint2.getFilePath());
			dtos.add(imageDTO);
		}
		model.addAttribute("id", surveyTargetPoint.getSurveytarget().getSurveyTargetId());
		model.addAttribute("surveyTargetPoint", surveyTargetPoint);
		model.addAttribute("imgSurveyTargetPoint", imgSurveyTargetPoint);
		model.addAttribute("dtoList", dtos);
		return "/survey/imageSurveyPoint";
	}

	@RequestMapping(value = "/survey/image/delete/{id}", method = RequestMethod.GET)
	public String imageDelete(@PathVariable("id") int id, Model model) {

		ImgSurveyTargetPoint imgSurveyTargetPoint = imgSurveyTargetPointService.findById(id);
		if (imgSurveyTargetPoint == null) {
			return "redirect:/survey";
		}

		///////////////////////////////////
		/////// delete image file
		///////////////////////////////////

		imgSurveyTargetPointService.deleteById(id);

		int surveyTargetPointId = imgSurveyTargetPoint.getSurveytargetpoint().getSurveyTargetPointId();
		return "redirect:/survey/image/" + surveyTargetPointId;
	}

	@RequestMapping(value = "/survey/image/save", method = RequestMethod.POST)
	public String imageSave(@ModelAttribute("ImgSurveyTargetPointDTO") ImgSurveyTargetPointDTO imgSurveyTargetPointDTO,
			Model model, Authentication authentication) throws IOException {
		int surveyTargetPointId = imgSurveyTargetPointDTO.getSurveyTargetPoint().getSurveyTargetPointId();
		SurveyTargetPoint surveyTargetPoint = surveyTargetPointService.findById(surveyTargetPointId);
		TargetOfSurvey targetOfSurvey = surveyTargetPoint.getSurveytarget().getTargetofsurvey();
		for (CommonsMultipartFile c : imgSurveyTargetPointDTO.getFiles()) {
			if (c.getSize() > 0) {
				try {
					String fileName = surveyTargetPointService.writeFile(c, targetOfSurvey.getName());
					String resizename = "s_" + fileName;
					Path source = Paths
							.get(externalPath + File.separator + "SurveyTargetPoint" + File.separator + fileName);
					Path target = Paths
							.get(externalPath + File.separator + "s_SurveyTargetPoint" + File.separator + resizename);
					try (InputStream is = new FileInputStream(source.toFile())) {
						ImageBase64Helper.resizeImage(is, target);
					}
					ImgSurveyTargetPoint img = new ImgSurveyTargetPoint(surveyTargetPoint, fileName, resizename,
							new Date(), "Waiting");
					img.setUserByUploadBy(userService.findByUsername(MvcHelper.getUsername(authentication)));
					surveyTargetPoint.getImgsurveytargetpoints().add(img);
					imgSurveyTargetPointService.save(img);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		return "redirect:/survey/image/" + surveyTargetPointId;
	}

	public int setSurveyTargetPointDTOList(List<SurveyTarget> surveyTargetList,
			List<SurveyTargetPointDTO> surveyTargetPointDTOList, List<SurveyTarget> surveyTargets, int[][] countImage,
			int count, int[] checkedCountImage, Model model) {
		for (SurveyTarget st : surveyTargetList) {

			List<SurveyTargetPoint> surveyTargetPoint = st.getSurveytargetpoints();

			surveyTargets.add(surveyTargetService.findById(st.getSurveyTargetId()));
			int totalCount = 0;
			for (int i = 0; i < 5; i++) {
				countImage[count][i] = imgSurveyTargetPointService
						.findAmountImgSurveyTargetPointBySurveyTargetIdAndPointNumber(st.getSurveyTargetId(), i);
				totalCount += countImage[count][i];
			}
			checkedCountImage[count] = totalCount;
			count++;

			SurveyTargetPointDTO surveyTargetPointDTO = new SurveyTargetPointDTO();
			int[][] surveyTargetPointId = new int[5][20];
			int[][] value = new int[5][20];

			int surveyTargetId = 0;
			for (SurveyTargetPoint stp : surveyTargetPoint) {
				value[stp.getPointNumber()][stp.getItemNumber()] = stp.getValue();
				surveyTargetPointId[stp.getPointNumber()][stp.getItemNumber()] = stp.getSurveyTargetPointId();
				surveyTargetId = stp.getSurveytarget().getSurveyTargetId();
			}
			surveyTargetPointDTO.setSurveyValue(value);
			surveyTargetPointDTO.setSurveyTargetPointIdList(surveyTargetPointId);
			surveyTargetPointDTO.setSurveyTargetId(surveyTargetId);
			surveyTargetPointDTOList.add(surveyTargetPointDTO);
		}
		model.addAttribute("surveyTargetPointDTOList", surveyTargetPointDTOList);
		model.addAttribute("surveyTargetPointDTOListSize", surveyTargetPointDTOList.size());
		model.addAttribute("surveyTarget", surveyTargets);
		return count;
	}

	@RequestMapping(value = "/survey/summary/{id}", method = RequestMethod.GET)
	public String summary(@PathVariable("id") int id, Model model) {
		Survey s = surveyService.findById(id);
		if (s == null) {
			return "redirect:/survey/";
		}
		Date d = s.getCreateDate();
		LocalDateTime ld = d.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		LocalDateTime now = LocalDateTime.now();

		// create ZoneId
		ZoneOffset zone = ZoneOffset.of("Z");
		int date = (int) (Math.round((now.toEpochSecond(zone) - ld.toEpochSecond(zone)) * 0.00001157407));
		model.addAttribute("datePlanting", date);
		List<Integer> pointNumber = new ArrayList<Integer>(5);
		for (int i = 0; i < 5; i++) {
			pointNumber.add(i);
		}
		List<Integer> itemNumber = new ArrayList<Integer>(20);
		for (int i = 0; i < 20; i++) {
			itemNumber.add(i);
		}
		List<SurveyTargetPointDTO> surveyTargetPointDTOList = new ArrayList<SurveyTargetPointDTO>();
		List<SurveyTarget> surveyTargets = new ArrayList<SurveyTarget>();
		List<SurveyTarget> diseases = surveyTargetService.findTypeDiseaseBySurveyId(id);
		List<SurveyTarget> pestPhases = surveyTargetService.findTypePestPhaseBySurveyId(id);
		List<SurveyTarget> naturalEnemies = surveyTargetService.findTypeNaturalEnemyBySurveyId(id);

		int[][] countImage = new int[((int) diseases.size() + (int) pestPhases.size()
				+ (int) naturalEnemies.size())][5];
		int[] checkedCountImage = new int[((int) diseases.size() + (int) pestPhases.size()
				+ (int) naturalEnemies.size())];
		int count = 0;
		count = setSurveyTargetPointDTOList(diseases, surveyTargetPointDTOList, surveyTargets, countImage, count,
				checkedCountImage, model);
		count = setSurveyTargetPointDTOList(pestPhases, surveyTargetPointDTOList, surveyTargets, countImage, count,
				checkedCountImage, model);
		setSurveyTargetPointDTOList(naturalEnemies, surveyTargetPointDTOList, surveyTargets, countImage, count,
				checkedCountImage, model);
		model.addAttribute("pointNumber", pointNumber);
		model.addAttribute("checkedCountImage", checkedCountImage);
		model.addAttribute("itemNumber", itemNumber);
		model.addAttribute("survey", surveyService.findById(id));
		model.addAttribute("sizeDisease", diseases.size());
		model.addAttribute("countImage", countImage);
		Format formatter = new SimpleDateFormat("d MMM yyyy", new Locale("th", "TH"));
		model.addAttribute("formatter", formatter);
		return "/survey/summary";
	}

	@RequestMapping(value = "/survey/summary/image/{id}", method = RequestMethod.POST)
	public String imageSummary(@ModelAttribute("SurveyDTO") SurveyDTO surveyDTO, @PathVariable("id") int id,
			Model model) {
		List<SelectedImageSurveyDTO> imageSurveyDTOs = surveyDTO.getSelectedImageSurveyDTOs();
		List<SurveyTargetPoint> surveyTargetPoints = new ArrayList<SurveyTargetPoint>();
		if (surveyTargetService.findById(id) == null) {
			return "redirect:/survey/";
		}
		for (SelectedImageSurveyDTO sis : imageSurveyDTOs) {
			List<SurveyTargetPoint> targetPoints = new ArrayList<SurveyTargetPoint>();
			if (imageSurveyDTOs.get(5).isChecked()) {
				targetPoints = surveyTargetService.findById(id).getSurveytargetpoints();
				for (SurveyTargetPoint stp : targetPoints) {
					surveyTargetPoints.add(stp);
				}
				break;
			}
			if (sis.isChecked()) {
				targetPoints = surveyTargetPointService.getBySurveyTargetIdAndPointNumber(id,
						Integer.valueOf(sis.getPoint()) - 1);
				for (SurveyTargetPoint stp : targetPoints) {
					surveyTargetPoints.add(stp);
				}

			}
		}
		List<ImageDTO> dtos = new ArrayList<ImageDTO>();
		List<ImgSurveyTargetPoint> imgSurveyTargetPoints = new ArrayList<ImgSurveyTargetPoint>();
		List<String> name = new ArrayList<String>();
		for (SurveyTargetPoint surveyTargetPoint : surveyTargetPoints) {
			for (ImgSurveyTargetPoint imgSurveyTargetPoint2 : surveyTargetPoint.getImgsurveytargetpoints()) {
				imgSurveyTargetPoints.add(imgSurveyTargetPoint2);
				name.add("จุดที่" + (surveyTargetPoint.getPointNumber() + 1) + "ต้นที่"
						+ (surveyTargetPoint.getItemNumber() + 1));
				ImageDTO imageDTO = new ImageDTO();
				imageDTO.setImage(externalPath + File.separator + "s_SurveyTargetPoint" + File.separator
						+ imgSurveyTargetPoint2.getFilePathS());
				dtos.add(imageDTO);
			}
		}
		model.addAttribute("name", name);
		model.addAttribute("id", surveyTargetService.findById(id).getSurvey().getSurveyId());
		model.addAttribute("surveyTargetPoints", surveyTargetPoints);
		model.addAttribute("imgSurveyTargetPoint", imgSurveyTargetPoints);
		model.addAttribute("dtoList", dtos);

		return "/survey/imageSurveyPointSummary";
	}

	@RequestMapping(value = "/survey/status/{id}", method = RequestMethod.GET)
	public ResponseEntity<String> changeSurveyStatus(@PathVariable("id") int id, Model model,
			Authentication authentication) {
		Survey survey = surveyService.findById(id);
		if (survey == null) {
			return new ResponseEntity<String>("error", HttpStatus.NOT_FOUND);
		}
		if (survey.getStatus().equals("Editing")) {
			survey.setStatus("Complete");
		} else {
			survey.setStatus("Editing");
		}
		surveyService.save(survey);
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}

	@RequestMapping(value = "/survey/point/status/{id}", method = RequestMethod.GET)
	public ResponseEntity<String> changeSurveyPointStatus(@PathVariable("id") int id, Model model,
			Authentication authentication) {
		SurveyTarget surveyTarget = surveyTargetService.findById(id);
		if (surveyTarget == null) {
			return new ResponseEntity<String>("error", HttpStatus.NOT_FOUND);
		}
		if (surveyTarget.getStatus().equals("Editing")) {
			surveyTarget.setStatus("Complete");
		} else {
			surveyTarget.setStatus("Editing");
		}
		surveyTargetService.save(surveyTarget);
		return new ResponseEntity<String>(surveyTarget.getStatus(), HttpStatus.OK);
	}

}
