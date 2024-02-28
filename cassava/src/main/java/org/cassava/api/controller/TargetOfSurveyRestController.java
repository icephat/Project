package org.cassava.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.cassava.api.util.model.Response;
import org.cassava.model.Disease;
import org.cassava.model.NaturalEnemy;
import org.cassava.model.PestPhaseSurvey;
import org.cassava.model.Planting;
import org.cassava.model.TargetOfSurvey;
import org.cassava.model.dto.SelectedTargetOfSurveyDTO;
import org.cassava.services.DiseaseService;
import org.cassava.services.NaturalEnemyService;
import org.cassava.services.PestPhaseService;
import org.cassava.services.PestPhaseSurveyService;
import org.cassava.services.TargetOfSurveyService;
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
@RequestMapping("/api/targetofsurvey")
public class TargetOfSurveyRestController {
	@Autowired
	private TargetOfSurveyService targetrofSurveyService;
	@Autowired
	private DiseaseService diseaseService;
	@Autowired
	private NaturalEnemyService naturalEnemyService;
	@Autowired
	private PestPhaseSurveyService pestPhaseSurveyService;
	
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
	
	
	
	
	@GetMapping(value="/getAllTarget/page/{page}/value/{value}")
	public ResponseEntity<Response<List<SelectedTargetOfSurveyDTO>>> findAll(@PathVariable int page,@PathVariable int value){
		Response<List<SelectedTargetOfSurveyDTO>> res = new Response<>();
		try {
			String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
			List<SelectedTargetOfSurveyDTO> selectedTargetOfSurveyDTOs  = targetrofSurveyService.findAll(page, value);
			res.setBody(selectedTargetOfSurveyDTOs);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<List<SelectedTargetOfSurveyDTO>>>(res,res.getHttpStatus());
		}catch(Exception ex) {
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<List<SelectedTargetOfSurveyDTO>>>(res,res.getHttpStatus());
		}
	}
	
	
	
	
	
	@GetMapping(value="/{id}/getdisease/page/{page}/value/{value}")
	public ResponseEntity<Response<List<Disease>>> findDiseaseByUserNameAndTargetOfSurveyId(@PathVariable int id,@PathVariable int page,
			@PathVariable int value){
		Response<List<Disease>> res = new Response<>();
		try {
			String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
			

			List<Disease> diseases = targetrofSurveyService.findDiseaseByUserNameAndTargetOfSurveyId(userName, id, page, value);
			res.setBody(diseases);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<List<Disease>>>(res,res.getHttpStatus());
		}
		catch(Exception ex) {
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<List<Disease>>>(res,res.getHttpStatus());
		}
	}

	@GetMapping(value="/{id}/getpestphasesurvey/page/{page}/value/{value}")
	public ResponseEntity<Response<List<PestPhaseSurvey>>> findPestPhaseSurveyByUserNameAndTargetOfSurveyId(@PathVariable int id,
			@PathVariable int page,@PathVariable int value){
		Response<List<PestPhaseSurvey>> res = new Response<>();
		try {
			String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
			List<PestPhaseSurvey> pestPhaseSurveys = targetrofSurveyService.findPestPhaseSurveyByUserNameAndTargetOfSurveyId(userName, id, page, value);
			res.setBody(pestPhaseSurveys);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<List<PestPhaseSurvey>>>(res,res.getHttpStatus());
		}
		catch(Exception ex) {
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<List<PestPhaseSurvey>>>(res,res.getHttpStatus());
		}
	}
	
	
	@GetMapping(value="/{id}/getnaturalEnemy/page/{page}/value/{value}")
	public ResponseEntity<Response<List<NaturalEnemy>>> findNaturalEnemyByUserNameAndTargetOfSurveyId(@PathVariable int id,
			@PathVariable int page,@PathVariable int value){
		Response<List<NaturalEnemy>> res = new Response<>();
		try {
			String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
			List<NaturalEnemy> naturalEnemies = targetrofSurveyService.findNaturalEnemyByUserNameAndTargetOfSurveyId(userName, id, page, value);
			res.setBody(naturalEnemies);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<List<NaturalEnemy>>>(res,res.getHttpStatus());
		}
		catch(Exception ex) {
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<List<NaturalEnemy>>>(res,res.getHttpStatus());
		}
	}
	
	@GetMapping(value="/getalldisease")
	public ResponseEntity<Response<List<TargetOfSurvey>>> findAllDisease(){
		String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
		Response<List<TargetOfSurvey>> res = new Response<>();
		try {
			List<TargetOfSurvey> diseases = targetrofSurveyService.findAllDisease();
			res.setBody(diseases);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<List<TargetOfSurvey>>>(res,res.getHttpStatus());
		}
		catch(Exception ex) {
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<List<TargetOfSurvey>>>(res,res.getHttpStatus());
		}
	}
	
	
	@GetMapping("/getdisease/survey/{id}")
	public ResponseEntity<Response<List<SelectedTargetOfSurveyDTO>>> findDiseaseBySurveyId(@PathVariable int id){
		Response<List<SelectedTargetOfSurveyDTO>> res = new Response<>();
		try {
			String username = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
			List<SelectedTargetOfSurveyDTO> selectedTargetOfSurveyDTOs = new ArrayList<>();
			List<TargetOfSurvey> targetOfSurveysDiseaseInsurvey = targetrofSurveyService.findAllDiseaseBySurveyId(id);
			List<TargetOfSurvey> targetOfSurveysDisease = targetrofSurveyService.findAllDisease();
			for(TargetOfSurvey tos:targetOfSurveysDiseaseInsurvey) {
				SelectedTargetOfSurveyDTO selectedTargetOfSurveyDTO = new SelectedTargetOfSurveyDTO();
				selectedTargetOfSurveyDTO.setChecked(true);
				selectedTargetOfSurveyDTO.setTargetofsurvey(tos);
				selectedTargetOfSurveyDTOs.add(selectedTargetOfSurveyDTO);
			}
			for(TargetOfSurvey tos:targetOfSurveysDisease) {
				boolean isIn = false;
				for(int i=0;i<selectedTargetOfSurveyDTOs.size();i++) {
					if(selectedTargetOfSurveyDTOs.get(i).getTargetofsurvey().getTargetOfSurveyId() == tos.getTargetOfSurveyId()) {	
						isIn = true;
						break;
					}
				}
				if(!isIn) {
					SelectedTargetOfSurveyDTO selectedTargetOfSurveyDTO = new SelectedTargetOfSurveyDTO();
					selectedTargetOfSurveyDTO.setChecked(false);
					selectedTargetOfSurveyDTO.setTargetofsurvey(tos);
					selectedTargetOfSurveyDTOs.add(selectedTargetOfSurveyDTO);
				}
			}
			res.setBody(selectedTargetOfSurveyDTOs);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<List<SelectedTargetOfSurveyDTO>>>(res,res.getHttpStatus());
		}catch(Exception ex) {
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<List<SelectedTargetOfSurveyDTO>>>(res,res.getHttpStatus());
		}
	}
	
	
	
	

	@GetMapping(value="/getallpestphasesurvey")
	public ResponseEntity<Response<List<TargetOfSurvey>>> findAllPestPhaseSurvey(){
		String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
		Response<List<TargetOfSurvey>> res = new Response<>();
		try {
			List<TargetOfSurvey> pestPhaseSurveys = targetrofSurveyService.findAllPestPhaseSurvey();
			res.setBody(pestPhaseSurveys);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<List<TargetOfSurvey>>>(res,res.getHttpStatus());
		}
		catch(Exception ex) {
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<List<TargetOfSurvey>>>(res,res.getHttpStatus());
		}
	}
	
	@GetMapping(value="/getpestphase/survey/{id}")
	public ResponseEntity<Response<List<SelectedTargetOfSurveyDTO>>> findPestPhaseBySurveyId(@PathVariable int id){
		Response<List<SelectedTargetOfSurveyDTO>> res = new Response<>();
		try {
			String username = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
			List<SelectedTargetOfSurveyDTO> selectedTargetOfSurveyDTOs = new ArrayList<>();
			List<TargetOfSurvey> targetOfSurveysPestPhaseInSurvey = targetrofSurveyService.findAllPestPhaseBySurveyId(id);
			List<TargetOfSurvey> targetOfSurveysPhaseSurvey = targetrofSurveyService.findAllPestPhaseSurvey();
			for(TargetOfSurvey tos:targetOfSurveysPestPhaseInSurvey) {
				SelectedTargetOfSurveyDTO selectedTargetOfSurveyDTO = new SelectedTargetOfSurveyDTO();
				selectedTargetOfSurveyDTO.setChecked(true);
				selectedTargetOfSurveyDTO.setTargetofsurvey(tos);
				selectedTargetOfSurveyDTOs.add(selectedTargetOfSurveyDTO);
			}
			for(TargetOfSurvey tos:targetOfSurveysPhaseSurvey) {
				boolean isIn = false;
				for(int i=0;i<selectedTargetOfSurveyDTOs.size();i++) {
					if(selectedTargetOfSurveyDTOs.get(i).getTargetofsurvey().getTargetOfSurveyId() == tos.getTargetOfSurveyId()) {	
						isIn = true;
						break;
					}
				}
				if(!isIn) {
					SelectedTargetOfSurveyDTO selectedTargetOfSurveyDTO = new SelectedTargetOfSurveyDTO();
					selectedTargetOfSurveyDTO.setChecked(false);
					selectedTargetOfSurveyDTO.setTargetofsurvey(tos);
					selectedTargetOfSurveyDTOs.add(selectedTargetOfSurveyDTO);
				}
			}
			res.setBody(selectedTargetOfSurveyDTOs);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<List<SelectedTargetOfSurveyDTO>>>(res,res.getHttpStatus());
		}catch(Exception ex) {
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<List<SelectedTargetOfSurveyDTO>>>(res,res.getHttpStatus());
		}
	}
	
	
	
	@GetMapping(value="/getallnaturalenemy")
	public ResponseEntity<Response<List<TargetOfSurvey>>> findAllNaturalEnemy(){
		String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
		Response<List<TargetOfSurvey>> res = new Response<>();
		try {
			List<TargetOfSurvey> naturalEnemies = targetrofSurveyService.findAllNaturalEnemy();
			res.setBody(naturalEnemies);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<List<TargetOfSurvey>>>(res,res.getHttpStatus());
		}
		catch(Exception ex) {
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<List<TargetOfSurvey>>>(res,res.getHttpStatus());
		}
	}
	
	
	@GetMapping(value = "/getnaturalenemies/survey/{id}")
	public ResponseEntity<Response<List<SelectedTargetOfSurveyDTO>>> findNaturalEnemiesBySurveyId(@PathVariable int id){
		Response<List<SelectedTargetOfSurveyDTO>> res = new Response<>();
		try {
			String username = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
			List<SelectedTargetOfSurveyDTO> selectedTargetOfSurveyDTOs = new ArrayList<>();
			List<TargetOfSurvey> targetOfSurveysNaturalEnemyinSurvey = targetrofSurveyService.findAllNaturalEnemyBySurveyId(id);
			List<TargetOfSurvey> targetOfSurveysNaturalEnemy = targetrofSurveyService.findAllNaturalEnemy();
			for(TargetOfSurvey tos:targetOfSurveysNaturalEnemyinSurvey) {
				SelectedTargetOfSurveyDTO selectedTargetOfSurveyDTO = new SelectedTargetOfSurveyDTO();
				selectedTargetOfSurveyDTO.setChecked(true);
				selectedTargetOfSurveyDTO.setTargetofsurvey(tos);
				selectedTargetOfSurveyDTOs.add(selectedTargetOfSurveyDTO);
			}
			for(TargetOfSurvey tos:targetOfSurveysNaturalEnemy) {
				boolean isIn = false;
				for(int i=0;i<selectedTargetOfSurveyDTOs.size();i++) {
					if(selectedTargetOfSurveyDTOs.get(i).getTargetofsurvey().getTargetOfSurveyId() == tos.getTargetOfSurveyId()) {	
						isIn = true;
						break;
					}
				}
				if(!isIn) {
					SelectedTargetOfSurveyDTO selectedTargetOfSurveyDTO = new SelectedTargetOfSurveyDTO();
					selectedTargetOfSurveyDTO.setChecked(false);
					selectedTargetOfSurveyDTO.setTargetofsurvey(tos);
					selectedTargetOfSurveyDTOs.add(selectedTargetOfSurveyDTO);
				}
			}
			res.setBody(selectedTargetOfSurveyDTOs);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<List<SelectedTargetOfSurveyDTO>>>(res,res.getHttpStatus());
		}catch(Exception ex) {
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<List<SelectedTargetOfSurveyDTO>>>(res,res.getHttpStatus());
		}
	}
	
	
	
	
	
}
