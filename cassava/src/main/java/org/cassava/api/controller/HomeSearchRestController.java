package org.cassava.api.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.cassava.api.util.model.Response;
import org.cassava.model.Disease;
import org.cassava.model.ImgDisease;
import org.cassava.model.ImgNaturalEnemy;
import org.cassava.model.ImgPest;
import org.cassava.model.ImgVariety;
import org.cassava.model.NaturalEnemy;
import org.cassava.model.Pathogen;
import org.cassava.model.Pest;
import org.cassava.model.Variety;
import org.cassava.model.dto.HomePageDTO;
import org.cassava.model.dto.HomePageDiseaseDTO;
import org.cassava.model.dto.HomePageNaturalEnemyDTO;
import org.cassava.model.dto.HomePagePestDTO;
import org.cassava.model.dto.HomePageVarietyDTO;
import org.cassava.model.dto.NameTargetOfSurveyDTO;
import org.cassava.services.DiseaseService;
import org.cassava.services.FieldService;
import org.cassava.services.ImgDiseaseService;
import org.cassava.services.ImgNaturalEnemyService;
import org.cassava.services.ImgPestService;
import org.cassava.services.ImgVarietyService;
import org.cassava.services.NaturalEnemyService;
import org.cassava.services.OrganizationService;
import org.cassava.services.PestService;
import org.cassava.services.UserService;
import org.cassava.services.VarietyService;
import org.cassava.util.ImageBase64Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@RequestMapping("/v3/api/")
@CrossOrigin("http://localhost:8081")
public class HomeSearchRestController {
	@Value("${external.resoures.path}")
	private String externalPath;

	@Autowired
	private VarietyService varietyService;
	@Autowired
	private OrganizationService organizationService;
	@Autowired
	private DiseaseService diseaseService;
	@Autowired
	private PestService pestService;
	@Autowired
	private NaturalEnemyService naturalEnemyService;
	@Autowired
	private UserService userService;
	@Autowired
	private ImgVarietyService imgVarietyService;
	@Autowired
	private ImgDiseaseService imgDiseaseService;
	@Autowired
	private ImgNaturalEnemyService imgNaturalEnemyService;
	@Autowired
	private ImgPestService imgPestService;
	@Autowired
	private FieldService fieldService;

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

	@GetMapping("/month/{month}/year/{year}")
	public ResponseEntity<Response<List<?>>> findNameAndNumAndAvgPercentAndDamageLeveLHasDiseaseByMonthsAndYears(@PathVariable int month,@PathVariable int year) {
		Response<List<?>> res = new Response();
		try {
			List<Object>homePages = fieldService.findNameAndNumAndAvgPercentAndDamageLeveLHasDiseaseByMonthsAndYears(month, year);
			List<NameTargetOfSurveyDTO> nameTargetOfSurveyDTOs = new ArrayList<NameTargetOfSurveyDTO>();
			for(Object homepage : homePages) {
				Object[] objects = (Object []) homepage;
				NameTargetOfSurveyDTO nameTargetOfSurveyDTO = new NameTargetOfSurveyDTO();
				nameTargetOfSurveyDTO.setName(objects[0].toString());
				nameTargetOfSurveyDTO.setNum(objects[1].toString());
				nameTargetOfSurveyDTO.setPercent(objects[2].toString());
				nameTargetOfSurveyDTO.setAvgDamageLevel(objects[3].toString());
				nameTargetOfSurveyDTOs.add(nameTargetOfSurveyDTO);
			}
			res.setBody(nameTargetOfSurveyDTOs);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<List<?>>>(res, res.getHttpStatus());
		} catch (Exception ex) {
			res.setMessage(ex.getMessage());
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<List<?>>>(res, res.getHttpStatus());
		}
	}

}
