package org.cassava.api.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.cassava.api.util.model.Response;
import org.cassava.model.Field;
import org.cassava.model.Planting;
import org.cassava.model.Subdistrict;
import org.cassava.model.Survey;
import org.cassava.model.User;
import org.cassava.model.UserInField;
import org.cassava.services.PlantingService;
import org.cassava.services.SurveyService;
import org.cassava.services.UserInFieldService;
import org.cassava.services.UserService;
import org.cassava.util.MvcHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@RequestMapping(value = "/api/calendar")
public class CalendarRestController {

	@Autowired
	private PlantingService plantingService;
	@Autowired
	private SurveyService surveyService;
	@Autowired
	private UserInFieldService userInFieldService;
	@Autowired
	private UserService userService;

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

	@GetMapping(value = "/date/{milliseconddate}")
	public ResponseEntity<Response<List<Integer>>> countPlantingAndSurvey(
			@PathVariable("milliseconddate") long milliseconddate) {
		String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
		Response<List<Integer>> res = new Response<>();
		User user = userService.findByUsername(userName);
		try {
			Date date = new Date(milliseconddate);
			List<Integer> countPlanting = plantingService.countByCreateDateInCalendar(date, user.getUserId());
			List<Integer> countSurvey = surveyService.countByCreateDateInCalendar(date, user.getUserId());
			List<Integer> result = new ArrayList<Integer>();
			int j = 0;
			for (Integer i : countPlanting) {
				Integer r = i + countSurvey.get(j);
				result.add(r);
				j++;
			}
			res.setBody(result);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<List<Integer>>>(res, res.getHttpStatus());
		} catch (Exception ex) {
			res.setMessage(ex.getLocalizedMessage());
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<List<Integer>>>(res, res.getHttpStatus());
		}
	}

	@GetMapping(value = "/plantingandsurvey/date/{milliseconddate}")
	public ResponseEntity<Response<List<?>>> findPlantingAndSurvey(
			@PathVariable("milliseconddate") long milliseconddate) {
		ObjectMapper mapper = new ObjectMapper();
		List<ObjectNode> responseList = new ArrayList<>();
		Response<List<?>> res = new Response();
		String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
		User user = userService.findByUsername(userName);
		try {
			Date date = new Date(milliseconddate);
			List<Planting> plantings = plantingService.findByMonthAndYearCreateDate(date, user.getUserId());
			List<Survey> surveys = surveyService.findByMonthAndYearCreateDate(date, user.getUserId());

			for (Planting planting : plantings) {
				ObjectNode responObject = mapper.createObjectNode();
				Field field = planting.getField();
				Subdistrict subdistrict = field.getSubdistrict();
				UserInField uif = userInFieldService.findByFieldIdAndRoleName(field.getFieldId());
				responObject.put("type", "planting");
				responObject.put("id", planting.getPlantingId());
				responObject.put("fieldName", field.getName());
				responObject.put("name", planting.getName());
				responObject.put("substrict", subdistrict.getName());
				responObject.put("district", subdistrict.getDistrict().getName());
				responObject.put("province", subdistrict.getDistrict().getProvince().getName());
				responObject.put("title", uif.getUser().getTitle());
				responObject.put("firstName", uif.getUser().getFirstName());
				responObject.put("lastName", uif.getUser().getLastName());
				responseList.add(responObject);
			}
			
			for (Survey survey : surveys) {
				ObjectNode responObject = mapper.createObjectNode();
				Planting planting = survey.getPlanting();
				Field field = planting.getField();
				Subdistrict subdistrict = field.getSubdistrict();
				UserInField uif = userInFieldService.findByFieldIdAndRoleName(field.getFieldId());
				responObject.put("type", "survey");
				responObject.put("id", survey.getSurveyId());
				responObject.put("plantingName", planting.getName());
				responObject.put("plantingCode", planting.getCode());
				responObject.put("name", survey.getCreateDate().toString());
				responObject.put("substrict", subdistrict.getName());
				responObject.put("district", subdistrict.getDistrict().getName());
				responObject.put("province", subdistrict.getDistrict().getProvince().getName());
				responObject.put("title", uif.getUser().getTitle());
				responObject.put("firstName", uif.getUser().getFirstName());
				responObject.put("lastName", uif.getUser().getLastName());
				responseList.add(responObject);
			}
			
			res.setBody(responseList);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<List<?>>>(res, res.getHttpStatus());
		} catch (Exception ex) {
			res.setMessage(ex.getLocalizedMessage());
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<List<?>>>(res, res.getHttpStatus());
		}
	}
	
	@GetMapping(value = "/planting/createdate/{millisecondDate}")
	public ResponseEntity<Response<List<Planting>>> findByCreateDateAndUser(
			@PathVariable("millisecondDate") long millisecondDate) {
		Response<List<Planting>> res = new Response();
		String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
		User user = userService.findByUsername(userName);
		try {
			Date date = new Date(millisecondDate);
			List<Planting> plantings = plantingService.findByCreateDateAndUser(date, user);
			res.setBody(plantings);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<List<Planting>>>(res, res.getHttpStatus());
		} catch (Exception ex) {
			res.setMessage(ex.getMessage());
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<List<Planting>>>(res, res.getHttpStatus());
		}
	}
	
	@GetMapping(value = "/survey/createdate/{millisecondDate}")
	public ResponseEntity<Response<List<Survey>>> findByCreateDate(@PathVariable("millisecondDate") long millisecondDate) {
		Response<List<Survey>> res = new Response();
		String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
		try {
			Date date = new Date(millisecondDate);
			List<Survey> surveys = surveyService.findByCreateDateAndUser(userName, date);
			res.setBody(surveys);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<List<Survey>>>(res, res.getHttpStatus());

		} catch (Exception ex) {
			res.setMessage(ex.getMessage());
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<List<Survey>>>(res, res.getHttpStatus());
		}
	} 

}
