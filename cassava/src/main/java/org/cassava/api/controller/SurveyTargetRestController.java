package org.cassava.api.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.cassava.api.util.model.Response;
import org.cassava.model.Survey;
import org.cassava.model.SurveyPoint;
import org.cassava.model.SurveyTarget;
import org.cassava.model.SurveyTargetPoint;
import org.cassava.model.TargetOfSurvey;
import org.cassava.model.User;
import org.cassava.model.dto.SelectedTargetOfSurveyDTO;
import org.cassava.model.dto.SurveyDTO;
import org.cassava.services.SurveyPointService;
import org.cassava.services.SurveyService;
import org.cassava.services.SurveyTargetPointService;
import org.cassava.services.SurveyTargetService;
import org.cassava.services.TargetOfSurveyService;
import org.cassava.services.UserService;
import org.cassava.util.MvcHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@RequestMapping(value = "/api/surveytarget")
public class SurveyTargetRestController {
	@Autowired
	private SurveyTargetService surveyTargetService;
	@Autowired
	private SurveyTargetPointService surveyTargetPointService;
	@Autowired
	private TargetOfSurveyService targetOfSurveyService;
	@Autowired
	private UserService userService;
	
	@Autowired
	private SurveyPointService surveyPointService;
	
	@Autowired
	private SurveyService surveyService;
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response<ObjectNode>> handleValidationExceptions(MethodArgumentNotValidException ex){
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
        return new ResponseEntity<Response<ObjectNode>>(res,res.getHttpStatus());
    }
	
	
	@GetMapping(value="{id}/plantingarea")
	public ResponseEntity<Response<Float>> getPlantingArea(@PathVariable int id){
		Response<Float> res = new Response<>();
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		try {
			Float plantingArea =  surveyTargetService.getPlantingArea(id,userName);
			res.setBody(plantingArea);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<Float>>(res,res.getHttpStatus());
		}catch (Exception e) {
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<Float>>(res,res.getHttpStatus());
		}
	}
	
	
//	@PostMapping(value="/")
//	public 
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<SurveyTarget>> getSurveyTargetById(@PathVariable int id) {
		Response<SurveyTarget> res = new Response<>();
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userService.findByUsername(userName);
		try {
			SurveyTarget surveyTarget = surveyTargetService.findById(id,user);
			res.setBody(surveyTarget);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<SurveyTarget>>(res, res.getHttpStatus());

		} catch (Exception ex) {
			System.out.println(ex.toString());
			res.setBody(null);
			res.setMessage(ex.toString());
			res.setHttpStatus(HttpStatus.NOT_FOUND);
//			res.setHttpStatus(ex.toString());
			
			return new ResponseEntity<Response<SurveyTarget>>(res, res.getHttpStatus());
		}
	}
	
	@PostMapping(value="/{id}/surveytargetpoint")
	 public ResponseEntity<Response<SurveyTargetPoint>> createSurveyTargetPointBySurveyTarget(@PathVariable int id,@Valid@RequestBody SurveyTargetPoint surveyTargetPoint){
		Response<SurveyTargetPoint> res = new Response<>();
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userService.findByUsername(userName);
		SurveyTarget surveyTarget = surveyTargetService.findById(id,user);
	 	try {
	 		
	 		surveyTargetPoint.setSurveytarget(surveyTarget);
	 		surveyTargetPointService.save(surveyTargetPoint);
	 		res.setMessage("8888");
			res.setBody(surveyTargetPoint);
	 		res.setHttpStatus(HttpStatus.OK);
	 		return new ResponseEntity<Response<SurveyTargetPoint>>(res,res.getHttpStatus());
			
	 	}catch(Exception ex) {
	 		res.setBody(null);
	 		res.setHttpStatus(HttpStatus.NOT_FOUND);
	 		return new ResponseEntity<Response<SurveyTargetPoint>>(res,res.getHttpStatus());
	 	}
	}
	
	/*==========================================Create update delete surveytarget=============================================*/
	

	
	
	
	
	
	
	
	
	
//	//Create with survey
	@PostMapping(value = "/create/survey/{id}")
	public ResponseEntity<Response<List<SurveyTarget>>> createSurveyTargetWithGeneratSurveyTargetPoint(@PathVariable int id,@Valid @RequestBody List<SelectedTargetOfSurveyDTO> selectedTargetOfSurveyDTOs){
		Response<List<SurveyTarget>> res = new Response<>();
		try {
			Date date = new Date();
			String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
			User user = userService.findByUsername(userName);
			Survey survey = surveyService.findById(id);
			survey.setUserByLastUpdateBy(user);
			survey.setLastUpdateDate(date);
			survey = surveyService.save(survey);
			
			surveyPointService.saveSurveyPoint(survey);
			List<SurveyTarget> surveyTargets2 = surveyTargetService.saveSurveyTarget(selectedTargetOfSurveyDTOs, survey);
			res.setBody(surveyTargets2);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<List<SurveyTarget>>>(res,res.getHttpStatus());
		}catch(Exception ex) {
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<List<SurveyTarget>>>(res,res.getHttpStatus());
		}
	}
	
	
	
	
	@PutMapping(value="/update/survey/{id}")
	public ResponseEntity<Response<List<SurveyTarget>>> updateSurveyTargetWithGeneratSurveyTargetPoint(@PathVariable int id,@Valid @RequestBody List<SelectedTargetOfSurveyDTO> selectedTargetOfSurveyDTOs){
		Response<List<SurveyTarget>> res = new Response<>();
		try {
			Date date = new Date();
			String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
			User user = userService.findByUsername(userName);
			Survey survey = surveyService.findById(id);
			survey.setUserByLastUpdateBy(user);
			survey.setLastUpdateDate(date);
			surveyService.save(survey);
			List<SurveyTarget> surveyTargets2 = surveyTargetService.updateSurveyTarget(selectedTargetOfSurveyDTOs, survey);
			res.setBody(surveyTargets2);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<List<SurveyTarget>>>(res,res.getHttpStatus());
		}catch(Exception ex) {
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<List<SurveyTarget>>>(res,res.getHttpStatus());
		}
	}

	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Response<String>> deleteSurveyTarget(@PathVariable int id){
		Response<String> res = new Response<>();
		try {
			surveyTargetService.deleteById(id);
			res.setBody("");
			res.setMessage("Delete "+id+" successfully.");
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<String>>(res,res.getHttpStatus());
		}catch(Exception ex) {
			res.setMessage("Result not found");
			res.setBody(ex.toString());
			res.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			return new ResponseEntity<Response<String>>(res,res.getHttpStatus());
			
		}
	}
	
	
	
}
