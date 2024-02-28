package org.cassava.api.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.cassava.api.util.model.Response;
import org.cassava.model.ImgSurveyTargetPoint;
import org.cassava.model.Survey;
import org.cassava.model.SurveyTarget;
import org.cassava.model.SurveyTargetPoint;
import org.cassava.model.TargetOfSurvey;
import org.cassava.model.User;
import org.cassava.model.dto.SurveyTargetAndPointDTO;
import org.cassava.model.dto.SurveyTargetPointDTOs;
import org.cassava.model.dto.SurveyTargetPointUpdateDTO;
import org.cassava.repository.ImgSurveyTargetPointRepository;
import org.cassava.services.DiseaseService;
import org.cassava.services.ImgSurveyTargetPointService;
import org.cassava.services.NaturalEnemyService;
import org.cassava.services.PestPhaseService;
import org.cassava.services.PestPhaseSurveyService;
import org.cassava.services.SurveyService;
import org.cassava.services.SurveyTargetPointService;
import org.cassava.services.SurveyTargetService;
import org.cassava.services.TargetOfSurveyService;
import org.cassava.services.UserService;
import org.cassava.util.ImageBase64Helper;
import org.cassava.util.MvcHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@RequestMapping(value = "/api/surveytargetpoint")
public class SurveyTargetPointRestController {

	@Value("${external.resoures.path}")
	private String externalPath;

	@Autowired
	private SurveyTargetPointService surveyTargetPointService;

	@Autowired
	private TargetOfSurveyService targetOfSurveyService;

	@Autowired
	private ImgSurveyTargetPointService imgSurveyTargetPointService;

	@Autowired
	private SurveyTargetService surveyTargetService;

	@Autowired
	private SurveyService surveyService;

	@Autowired
	private DiseaseService diseaseService;

	@Autowired
	private PestPhaseSurveyService pestPhaseSurveyService;

	@Autowired
	private NaturalEnemyService enemyService;

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

	@GetMapping(value = "/surveyId/{id}/disease/{pointnumber}/item/{itemnumber}")
	public ResponseEntity<Response<List<SurveyTargetAndPointDTO>>> getPointDisease(@PathVariable int id,
			@PathVariable int pointnumber, @PathVariable int itemnumber) {
		Response<List<SurveyTargetAndPointDTO>> res = new Response<>();
		String username = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
		try {
			List<SurveyTarget> surveyTargets = surveyTargetService.findByDiseaseandSurvey(id);
			List<SurveyTargetAndPointDTO> surveyTargetAndPointDTOs = new ArrayList<>();
			for (int i = 0; i < surveyTargets.size(); i++) {
				SurveyTargetPoint surveyTargetPoints = surveyTargetPointService
						.findBySurveyTargetIdAndPointNumberAndItemNumber(surveyTargets.get(i).getSurveyTargetId(),
								pointnumber, itemnumber);
				SurveyTargetAndPointDTO surveyTargetAndPointDTO = new SurveyTargetAndPointDTO();
				surveyTargetAndPointDTO.setSurveyTargetid(surveyTargets.get(i).getSurveyTargetId());
				surveyTargetAndPointDTO.setTargetofSurveyName(surveyTargets.get(i).getTargetofsurvey().getName());
				surveyTargetAndPointDTO.setSurveyTargetPoints(surveyTargetPoints);
				surveyTargetAndPointDTOs.add(surveyTargetAndPointDTO);
			}
			res.setBody(surveyTargetAndPointDTOs);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<List<SurveyTargetAndPointDTO>>>(res, res.getHttpStatus());

		} catch (Exception ex) {
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<List<SurveyTargetAndPointDTO>>>(res, res.getHttpStatus());
		}
	}

	@GetMapping(value = "/surveyId/{id}/pestphase/{pointnumber}/item/{itemnumber}")
	public ResponseEntity<Response<List<SurveyTargetAndPointDTO>>> getPointPestPhase(@PathVariable int id,
			@PathVariable int pointnumber, @PathVariable int itemnumber) {
		Response<List<SurveyTargetAndPointDTO>> res = new Response<>();
		String username = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
		try {
			List<SurveyTarget> surveyTargets = surveyTargetService.findByPestPhaseandSurvey(id);
			List<SurveyTargetAndPointDTO> surveyTargetAndPointDTOs = new ArrayList<>();
			System.out.println(surveyTargets.size());
			for (int i = 0; i < surveyTargets.size(); i++) {
				SurveyTargetPoint surveyTargetPoints = surveyTargetPointService
						.findBySurveyTargetIdAndPointNumberAndItemNumber(surveyTargets.get(i).getSurveyTargetId(),
								pointnumber, itemnumber);
				SurveyTargetAndPointDTO surveyTargetAndPointDTO = new SurveyTargetAndPointDTO();
				surveyTargetAndPointDTO.setSurveyTargetid(surveyTargets.get(i).getSurveyTargetId());
				surveyTargetAndPointDTO.setTargetofSurveyName(surveyTargets.get(i).getTargetofsurvey().getName());
				surveyTargetAndPointDTO.setSurveyTargetPoints(surveyTargetPoints);
				surveyTargetAndPointDTOs.add(surveyTargetAndPointDTO);
			}
			res.setBody(surveyTargetAndPointDTOs);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<List<SurveyTargetAndPointDTO>>>(res, res.getHttpStatus());

		} catch (Exception ex) {
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<List<SurveyTargetAndPointDTO>>>(res, res.getHttpStatus());
		}
	}

	@GetMapping(value = "/surveyId/{id}/naturalenemy/{pointnumber}/item/{itemnumber}")
	public ResponseEntity<Response<List<SurveyTargetAndPointDTO>>> getPointNaturalEnemy(@PathVariable int id,
			@PathVariable int pointnumber, @PathVariable int itemnumber) {
		Response<List<SurveyTargetAndPointDTO>> res = new Response<>();
		String username = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
		try {
			List<SurveyTarget> surveyTargets = surveyTargetService.findByNaturalEnemyandSurvey(id);
			List<SurveyTargetAndPointDTO> surveyTargetAndPointDTOs = new ArrayList<>();
//			System.out.println(surveyTargets.size());
			for (int i = 0; i < surveyTargets.size(); i++) {
				SurveyTargetPoint surveyTargetPoints = surveyTargetPointService
						.findBySurveyTargetIdAndPointNumberAndItemNumber(surveyTargets.get(i).getSurveyTargetId(),
								pointnumber, itemnumber);
				SurveyTargetAndPointDTO surveyTargetAndPointDTO = new SurveyTargetAndPointDTO();
				surveyTargetAndPointDTO.setSurveyTargetid(surveyTargets.get(i).getSurveyTargetId());
				surveyTargetAndPointDTO.setTargetofSurveyName(surveyTargets.get(i).getTargetofsurvey().getName());
				surveyTargetAndPointDTO.setSurveyTargetPoints(surveyTargetPoints);
				surveyTargetAndPointDTOs.add(surveyTargetAndPointDTO);
			}
			res.setBody(surveyTargetAndPointDTOs);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<List<SurveyTargetAndPointDTO>>>(res, res.getHttpStatus());

		} catch (Exception ex) {
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<List<SurveyTargetAndPointDTO>>>(res, res.getHttpStatus());
		}
	}

	@GetMapping(value = "/surveyId/{id}/disease/{pointnumber}")
	public ResponseEntity<Response<List<SurveyTargetPointDTOs>>> getPointDisease(@PathVariable("id") int id,
			@PathVariable("pointnumber") int pointnumber) {
		Response<List<SurveyTargetPointDTOs>> res = new Response<>();
		String username = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
		try {
			List<SurveyTarget> surveyTargets = surveyTargetService.findByDiseaseandSurvey(id);
			List<SurveyTargetPointDTOs> surveyTargetAndPointDTOs = new ArrayList<>();
			for (int i = 0; i < surveyTargets.size(); i++) {
				List<SurveyTargetPoint> surveyTargetPoints = surveyTargetPointService
						.findBySurveyTargetIdAndPointNumber(surveyTargets.get(i).getSurveyTargetId(), pointnumber);
				SurveyTargetPointDTOs surveyTargetAndPointDTO = new SurveyTargetPointDTOs();
				surveyTargetAndPointDTO.setSurveyTargetid(surveyTargets.get(i).getSurveyTargetId());
				surveyTargetAndPointDTO.setTargetofSurveyName(surveyTargets.get(i).getTargetofsurvey().getName());
				surveyTargetAndPointDTO.setSurveyTargetPoints(surveyTargetPoints);
				surveyTargetAndPointDTOs.add(surveyTargetAndPointDTO);
			}
			res.setBody(surveyTargetAndPointDTOs);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<List<SurveyTargetPointDTOs>>>(res, res.getHttpStatus());

		} catch (Exception ex) {
			res.setMessage(ex.getLocalizedMessage());
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<List<SurveyTargetPointDTOs>>>(res, res.getHttpStatus());
		}
	}

	@GetMapping(value = "/surveyId/{id}/pestphase/{pointnumber}")
	public ResponseEntity<Response<List<SurveyTargetPointDTOs>>> getPointPestPhase(@PathVariable("id") int id,
			@PathVariable("pointnumber") int pointnumber) {
		Response<List<SurveyTargetPointDTOs>> res = new Response<>();
		String username = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
		try {
			List<SurveyTarget> surveyTargets = surveyTargetService.findByPestPhaseandSurvey(id);
			List<SurveyTargetPointDTOs> surveyTargetAndPointDTOs = new ArrayList<>();
			System.out.println(surveyTargets.size());
			for (int i = 0; i < surveyTargets.size(); i++) {
				List<SurveyTargetPoint> surveyTargetPoints = surveyTargetPointService
						.findBySurveyTargetIdAndPointNumber(surveyTargets.get(i).getSurveyTargetId(), pointnumber);
				SurveyTargetPointDTOs surveyTargetAndPointDTO = new SurveyTargetPointDTOs();
				surveyTargetAndPointDTO.setSurveyTargetid(surveyTargets.get(i).getSurveyTargetId());
				surveyTargetAndPointDTO.setTargetofSurveyName(surveyTargets.get(i).getTargetofsurvey().getName());
				surveyTargetAndPointDTO.setSurveyTargetPoints(surveyTargetPoints);
				surveyTargetAndPointDTOs.add(surveyTargetAndPointDTO);
			}
			res.setBody(surveyTargetAndPointDTOs);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<List<SurveyTargetPointDTOs>>>(res, res.getHttpStatus());

		} catch (Exception ex) {
			res.setMessage(ex.getLocalizedMessage());
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<List<SurveyTargetPointDTOs>>>(res, res.getHttpStatus());
		}
	}

	@GetMapping(value = "/surveyId/{id}/naturalenemy/{pointnumber}")
	public ResponseEntity<Response<List<SurveyTargetPointDTOs>>> getPointNaturalEnemy(@PathVariable("id") int id,
			@PathVariable("pointnumber") int pointnumber) {
		Response<List<SurveyTargetPointDTOs>> res = new Response<>();
		String username = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
		try {
			List<SurveyTarget> surveyTargets = surveyTargetService.findByNaturalEnemyandSurvey(id);
			List<SurveyTargetPointDTOs> surveyTargetAndPointDTOs = new ArrayList<>();
			for (int i = 0; i < surveyTargets.size(); i++) {
				List<SurveyTargetPoint> surveyTargetPoints = surveyTargetPointService
						.findBySurveyTargetIdAndPointNumber(surveyTargets.get(i).getSurveyTargetId(), pointnumber);
				SurveyTargetPointDTOs surveyTargetAndPointDTO = new SurveyTargetPointDTOs();
				surveyTargetAndPointDTO.setSurveyTargetid(surveyTargets.get(i).getSurveyTargetId());
				surveyTargetAndPointDTO.setTargetofSurveyName(surveyTargets.get(i).getTargetofsurvey().getName());
				surveyTargetAndPointDTO.setSurveyTargetPoints(surveyTargetPoints);
				surveyTargetAndPointDTOs.add(surveyTargetAndPointDTO);
			}
			res.setBody(surveyTargetAndPointDTOs);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<List<SurveyTargetPointDTOs>>>(res, res.getHttpStatus());

		} catch (Exception ex) {
			res.setMessage(ex.getLocalizedMessage());
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<List<SurveyTargetPointDTOs>>>(res, res.getHttpStatus());
		}
	}

	@PostMapping(value = "/disease/{pointnumber}/item/{itemnumber}")
	public ResponseEntity<Response<List<SurveyTargetPoint>>> updateDiseaseSurveyTargetPoint(
			@PathVariable("pointnumber") int pointnumber, @PathVariable("itemnumber") int itemnumber,
			@RequestBody List<SurveyTargetPointUpdateDTO> surveyTargetPointUpdateDTOs) {
		String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
		User user = userService.findByUsername(userName);
		Response<List<SurveyTargetPoint>> res = new Response<>();
		List<SurveyTargetPoint> surveyTargetPoints = new ArrayList<>();
		try {
			for (SurveyTargetPointUpdateDTO surveyTargetPointUpdateDTO : surveyTargetPointUpdateDTOs) {
				SurveyTargetPoint surveyTargetPoint = surveyTargetPointService
						.findBySurveyTargetIdAndPointNumberAndItemNumber(surveyTargetPointUpdateDTO.getSurveyTargetId(),
								pointnumber, itemnumber);
				// surveyTargetPoints.add(surveyTargetPointService.updateSurveyTargetPoint(surveyTargetPoint,
				// surveyTargetPointUpdateDTO.getValue(),
				// surveyTargetPointUpdateDTO.getSurveyTargetId()));
				surveyTargetPoints.add(surveyTargetPointService.updateSurveyTargetPoint(surveyTargetPoint,
						surveyTargetPointUpdateDTO.getValue()));
			}
			res.setBody(surveyTargetPoints);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<List<SurveyTargetPoint>>>(res, res.getHttpStatus());

		} catch (Exception ex) {
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<List<SurveyTargetPoint>>>(res, res.getHttpStatus());
		}

	}

	@PostMapping(value = "/naturalenemy/{pointnumber}/item/{itemnumber}")
	public ResponseEntity<Response<List<SurveyTargetPoint>>> updateNaturalenemySurveyTargetPoint(
			@PathVariable("pointnumber") int pointnumber, @PathVariable("itemnumber") int itemnumber,
			@RequestBody List<SurveyTargetPointUpdateDTO> surveyTargetPointUpdateDTOs) {
		String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
		User user = userService.findByUsername(userName);
		Response<List<SurveyTargetPoint>> res = new Response<>();
		List<SurveyTargetPoint> surveyTargetPoints = new ArrayList<>();
		try {
			for (SurveyTargetPointUpdateDTO surveyTargetPointUpdateDTO : surveyTargetPointUpdateDTOs) {
				SurveyTargetPoint surveyTargetPoint = surveyTargetPointService
						.findBySurveyTargetIdAndPointNumberAndItemNumber(surveyTargetPointUpdateDTO.getSurveyTargetId(),
								pointnumber, itemnumber);
				// surveyTargetPoints.add(surveyTargetPointService.updateSurveyTargetPoint(surveyTargetPoint,
				// surveyTargetPointUpdateDTO.getValue(),
				// surveyTargetPointUpdateDTO.getSurveyTargetId()));
				surveyTargetPoints.add(surveyTargetPointService.updateSurveyTargetPoint(surveyTargetPoint,
						surveyTargetPointUpdateDTO.getValue()));
			}
			res.setBody(surveyTargetPoints);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<List<SurveyTargetPoint>>>(res, res.getHttpStatus());

		} catch (Exception ex) {
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<List<SurveyTargetPoint>>>(res, res.getHttpStatus());
		}

	}

	@PostMapping(value = "/pestphase/{pointnumber}/item/{itemnumber}")
	public ResponseEntity<Response<List<SurveyTargetPoint>>> updatePestphaseSurveyTargetPoint(
			@PathVariable("pointnumber") int pointnumber, @PathVariable("itemnumber") int itemnumber,
			@RequestBody List<SurveyTargetPointUpdateDTO> surveyTargetPointUpdateDTOs) {
		String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
		User user = userService.findByUsername(userName);
		Response<List<SurveyTargetPoint>> res = new Response<>();
		List<SurveyTargetPoint> surveyTargetPoints = new ArrayList<>();
		try {
			for (SurveyTargetPointUpdateDTO surveyTargetPointUpdateDTO : surveyTargetPointUpdateDTOs) {
				SurveyTargetPoint surveyTargetPoint = surveyTargetPointService
						.findBySurveyTargetIdAndPointNumberAndItemNumber(surveyTargetPointUpdateDTO.getSurveyTargetId(),
								pointnumber, itemnumber);

				surveyTargetPoints.add(surveyTargetPointService.updateSurveyTargetPoint(surveyTargetPoint,
						surveyTargetPointUpdateDTO.getValue()));
			}
			res.setBody(surveyTargetPoints);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<List<SurveyTargetPoint>>>(res, res.getHttpStatus());

		} catch (Exception ex) {
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<List<SurveyTargetPoint>>>(res, res.getHttpStatus());
		}

	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<SurveyTargetPoint>> getSurveyTargetPointByUserAndSurveyTargetPointId(
			@PathVariable int id) {
		Response<SurveyTargetPoint> res = new Response<>();
		String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
		User user = userService.findByUsername(userName);
		try {

			SurveyTargetPoint surveyTargetPoint = surveyTargetPointService.findByUserAndSurveyTargetPointId(user, id);
			res.setBody(surveyTargetPoint);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<SurveyTargetPoint>>(res, res.getHttpStatus());

		} catch (Exception ex) {

			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<SurveyTargetPoint>>(res, res.getHttpStatus());
		}
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Response<String>> updateSurveyTarget(@PathVariable int id,
			@RequestBody SurveyTargetPoint surveyTargetPoint) {
		Response<String> res = new Response<>();
		String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
		User user = userService.findByUsername(userName);
		try {
			SurveyTargetPoint stp = surveyTargetPointService.findByUserAndSurveyTargetPointId(user, id);
			stp.clone(surveyTargetPoint);
//			surveyTargetPoint.setSurveytarget(stp.getSurveytarget());
			surveyTargetPointService.save(surveyTargetPoint);
			res.setBody("");
			res.setMessage("Updated Id " + surveyTargetPoint.getSurveyTargetPointId());
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<String>>(res, res.getHttpStatus());
		} catch (Exception ex) {
			res.setMessage(ex.toString());
			res.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			return new ResponseEntity<Response<String>>(res, res.getHttpStatus());
		}
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<String>> deleteSurveyTarget(@PathVariable int id) {
		Response<String> res = new Response<>();
		try {
			surveyTargetPointService.deleteById(id);
			res.setBody("");
			res.setMessage("Delete " + id + " successfully.");
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<String>>(res, res.getHttpStatus());
		} catch (Exception ex) {
			res.setMessage("Result not found");
			res.setBody(ex.toString());
			res.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			return new ResponseEntity<Response<String>>(res, res.getHttpStatus());

		}
	}

	@GetMapping(value = "/pointnumber/{pointnumber}/item/{itemnumber}/surveyId/{surveyId}/countimage")
	public ResponseEntity<Response<Integer>> getCountImgSurveyTargetPointByPointNumberAndMonthAndItemNumberAndSurveyId(
			@PathVariable int pointnumber, @PathVariable int itemnumber, @PathVariable int surveyId) {
		Response<Integer> res = new Response<>();
		String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
		User user = userService.findByUsername(userName);
		try {
			Survey survey = surveyService.findById(surveyId);
			Integer number = imgSurveyTargetPointService
					.findCountImgSurveyTargetPointByPointNumberAndItemNumberAndSurvey(pointnumber, itemnumber, survey,
							user);
			res.setBody(number);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<Integer>>(res, res.getHttpStatus());

		} catch (Exception ex) {

			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<Integer>>(res, res.getHttpStatus());
		}
	}

	@GetMapping(value = "/surveyid/{surveyid}/countdisease")
	public ResponseEntity<Response<?>> countDiseaseBySurveyId(@PathVariable("surveyid") int surveyid) {
		Response<ObjectNode> res = new Response<>();
		ObjectMapper mapper = new ObjectMapper();
		String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
		User user = userService.findByUsername(userName);
		try {
			Integer count = surveyTargetService.countDiseaseBySurveyId(surveyid);
			List<TargetOfSurvey> disease = surveyTargetService.findDiseaseBySurveyId(surveyid);
			ArrayNode array = mapper.valueToTree(disease);
			ObjectNode responObject = mapper.createObjectNode();
			responObject.put("count", count);
			responObject.putArray("disease").addAll(array);
			res.setBody(responObject);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<?>>(res, res.getHttpStatus());

		} catch (Exception ex) {
			res.setMessage(ex.getMessage());
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<?>>(res, res.getHttpStatus());
		}
	}

	@GetMapping(value = "/surveyid/{surveyid}/countnaturalenemy")
	public ResponseEntity<Response<?>> countNaturalEnemyBySurveyId(@PathVariable("surveyid") int surveyid) {
		Response<ObjectNode> res = new Response<>();
		ObjectMapper mapper = new ObjectMapper();
		String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
		User user = userService.findByUsername(userName);
		try {
			Integer count = surveyTargetService.countNaturalEnemyBySurveyId(surveyid);
			List<TargetOfSurvey> naturalEnemy = surveyTargetService.findNaturalEnemyBySurveyId(surveyid);
			ArrayNode array = mapper.valueToTree(naturalEnemy);
			ObjectNode responObject = mapper.createObjectNode();
			responObject.put("count", count);
			responObject.putArray("naturalEnemy").addAll(array);
			res.setBody(responObject);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<?>>(res, res.getHttpStatus());

		} catch (Exception ex) {
			res.setMessage(ex.getMessage());
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<?>>(res, res.getHttpStatus());
		}
	}

	@GetMapping(value = "/surveyid/{surveyid}/countpestphasesurvey")
	public ResponseEntity<Response<?>> countPestPhaseSurveyBySurveyId(@PathVariable("surveyid") int surveyid) {
		Response<ObjectNode> res = new Response<>();
		ObjectMapper mapper = new ObjectMapper();
		String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
		User user = userService.findByUsername(userName);
		try {
			Integer count = surveyTargetService.countPestPhaseSurveyBySurveyId(surveyid);
			List<TargetOfSurvey> pestphasesurvey = surveyTargetService.findPestPhaseSurveyBySurveyId(surveyid);
			ArrayNode array = mapper.valueToTree(pestphasesurvey);
			ObjectNode responObject = mapper.createObjectNode();
			responObject.put("count", count);
			responObject.putArray("pestphasesurvey").addAll(array);
			res.setBody(responObject);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<?>>(res, res.getHttpStatus());

		} catch (Exception ex) {
			res.setMessage(ex.getMessage());
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<?>>(res, res.getHttpStatus());
		}
	}
	
	@GetMapping(value = "/surveyid/{surveyid}/checksurveytarget")
	public ResponseEntity<Response<Boolean>> checkSurveyTargetBySurveyId(@PathVariable("surveyid") int surveyid) {
		Response<Boolean> res = new Response<>();
		try {
			Integer countPestPhase = surveyTargetService.countPestPhaseSurveyBySurveyId(surveyid);
			Integer countNaturalEnemy = surveyTargetService.countNaturalEnemyBySurveyId(surveyid);
			Integer countDisease = surveyTargetService.countDiseaseBySurveyId(surveyid);
			Boolean check = false;
			if(countPestPhase != 0 || countNaturalEnemy != 0 || countDisease != 0) {
				check = true;
			}
			res.setBody(check);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<Boolean>>(res, res.getHttpStatus());

		} catch (Exception ex) {

			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<Boolean>>(res, res.getHttpStatus());
		}
	}

	@GetMapping(value = "/surveyid/{surveyid}/pointnumber/{pointnumber}/itemnumber/{itemnumber}/images")
	public ResponseEntity<Response<List<?>>> findImageBySurveyIdAndPointNumberAndItemNumberAndApprovedStatus(
			@PathVariable("surveyid") int surveyid, @PathVariable("pointnumber") int pointnumber,
			@PathVariable("itemnumber") int itemnumber) {
		Response<List<?>> res = new Response();
		ObjectMapper mapper = new ObjectMapper();
		List<ObjectNode> responseList = new ArrayList<>();
		try {
			List<ImgSurveyTargetPoint> imgs = imgSurveyTargetPointService
					.findBySurveyIdAndPointNumberAndItemNumberAndApprovedStatus(surveyid, pointnumber, itemnumber);
			for (ImgSurveyTargetPoint img : imgs) {
				TargetOfSurvey targetOfSurvey = img.getSurveytargetpoint().getSurveytarget().getTargetofsurvey();
				ObjectNode responObject = mapper.createObjectNode();
				responObject.put("imgSurveyTargetPointId", img.getImgSurveyTargetPointId());
				responObject.put("filePath", ImageBase64Helper.toImageBase64(
						externalPath + File.separator + "SurveyTargetPoint" + File.separator + img.getFilePath()));
				responObject.put("name", targetOfSurvey.getName());
				responseList.add(responObject);
			}
			res.setBody(responseList);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<List<?>>>(res, res.getHttpStatus());

		} catch (Exception ex) {
			res.setMessage(ex.getMessage());
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<List<?>>>(res, res.getHttpStatus());
		}
	}

	@GetMapping(value = "/surveyid/{surveyid}/pointnumber/{pointnumber}/itemnumber/{itemnumber}")
	public ResponseEntity<Response<List<?>>> findBySurveyIdAndPointNumberAndItemNumberAndApprovedImgStatus(
			@PathVariable("surveyid") int surveyid, @PathVariable("pointnumber") int pointnumber,
			@PathVariable("itemnumber") int itemnumber) {
		Response<List<?>> res = new Response();
		ObjectMapper mapper = new ObjectMapper();
		List<ObjectNode> responseList = new ArrayList<>();
		try {
			Integer amountOfImage = imgSurveyTargetPointService
					.countImgBySurveyIdAndPointNumberAndItemNumber(surveyid, pointnumber, itemnumber);
			Object[] count = (Object[]) surveyTargetService
					.countTotalAndFoundBySurveyIdAndPointNumberAndItemNumber(surveyid, pointnumber, itemnumber);

			ObjectNode responObject = mapper.createObjectNode();
			responObject.put("total", count[0].toString());
			responObject.put("found", count[1].toString());
			responObject.put("amountOfImage", amountOfImage);
			responseList.add(responObject);

			res.setBody(responseList);
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
