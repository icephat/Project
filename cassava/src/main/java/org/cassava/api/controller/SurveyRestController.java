package org.cassava.api.controller;

import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.io.File;
import java.io.FileInputStream;

import javax.validation.Valid;

import org.cassava.api.util.model.Response;
import org.cassava.model.Disease;
import org.cassava.model.Field;
import org.cassava.model.ImgSurveyTargetPoint;
import org.cassava.model.NaturalEnemy;
import org.cassava.model.PestPhaseSurvey;
import org.cassava.model.Planting;
import org.cassava.model.Subdistrict;
import org.cassava.model.Survey;
import org.cassava.model.SurveyPoint;
import org.cassava.model.SurveyTarget;
import org.cassava.model.SurveyTargetPoint;
import org.cassava.model.TargetOfSurvey;
import org.cassava.model.User;
import org.cassava.model.dto.ImgSurveyPointDTO;
import org.cassava.model.dto.PestPhaeDTO;
import org.cassava.model.dto.SurveySearchDTO;
import org.cassava.services.DiseaseService;
import org.cassava.services.ImgSurveyTargetPointService;
import org.cassava.services.NaturalEnemyService;
import org.cassava.services.PestPhaseSurveyService;
import org.cassava.services.SurveyPointService;
import org.cassava.services.SurveyService;
import org.cassava.services.SurveyTargetPointService;
import org.cassava.services.SurveyTargetService;
import org.cassava.services.TargetOfSurveyService;
import org.cassava.services.UserInFieldService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@RequestMapping(value = "/api/survey")
public class SurveyRestController {

	@Value("${external.resoures.path}")
	private String externalPath;

	@Autowired
	private SurveyService surveyService;
	@Autowired
	private UserInFieldService userInFieldService;
	@Autowired
	private UserService userService;
	@Autowired
	private SurveyTargetService surveyTargetService;
	@Autowired
	private SurveyTargetPointService surveyTargetPointService;
	@Autowired
	private TargetOfSurveyService targetOfSurveyService;
	@Autowired
	private NaturalEnemyService naturalEnemyService;
	@Autowired
	private DiseaseService diseaseService;
	@Autowired
	private PestPhaseSurveyService pestPhaseSurveyService;
	@Autowired
	private SurveyPointService surveyPointService;
	@Autowired
	private ImgSurveyTargetPointService imgSurveyTargetPointService;

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

	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<Survey>> findById(@PathVariable int id) {
		Response<Survey> res = new Response<>();
		String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
		try {

			User user = userService.findByUsername(userName);
			Survey survey = surveyService.findByUserAndSurveyId(user, id);
			res.setBody(survey);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<Survey>>(res, res.getHttpStatus());

		} catch (Exception ex) {

			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<Survey>>(res, res.getHttpStatus());
		}
	}

	@GetMapping(value = "/{id}/diseases")
	public ResponseEntity<Response<List<Disease>>> findDiseasesBySurveyId(@PathVariable int id) {
		Response<List<Disease>> res = new Response<>();
		String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
		try {

			List<Disease> diseases = diseaseService.findBySurveyIDAndUserName(id, userName);
			res.setBody(diseases);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<List<Disease>>>(res, res.getHttpStatus());

		} catch (Exception ex) {

			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<List<Disease>>>(res, res.getHttpStatus());
		}
	}

	@GetMapping(value = "/{id}/naturalenemies")
	public ResponseEntity<Response<List<NaturalEnemy>>> findNeturalEnemiesBySurveyId(@PathVariable int id) {
		Response<List<NaturalEnemy>> res = new Response<>();
		String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
		try {

			List<NaturalEnemy> enemies = naturalEnemyService.findBySurveyIDAndUserName(id, userName);
			res.setBody(enemies);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<List<NaturalEnemy>>>(res, res.getHttpStatus());

		} catch (Exception ex) {

			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<List<NaturalEnemy>>>(res, res.getHttpStatus());
		}
	}

	@GetMapping(value = "/{id}/pestphasesurveys")
	public ResponseEntity<Response<List<PestPhaeDTO>>> findPestPhaseSurveyBySurveyId(@PathVariable int id) {
		Response<List<PestPhaeDTO>> res = new Response<>();
		String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
		try {

			List<PestPhaseSurvey> phaseSurveys = pestPhaseSurveyService.findBySurveyIDAndUserName(id, userName);
			List<PestPhaeDTO> pestPhaeDTOs = new ArrayList<>();
			for (PestPhaseSurvey phaseSurvey : phaseSurveys) {
				PestPhaeDTO pestPhaeDTO = new PestPhaeDTO();
				pestPhaeDTO.setPestPhaseSurveyId(phaseSurvey.getPestPhaseSurveyId());
				pestPhaeDTO.setPestId(phaseSurvey.getPest().getPestId());
				pestPhaeDTO.setPestName(phaseSurvey.getPest().getName());
				pestPhaeDTO.setPestPhaseId(phaseSurvey.getPestphase().getPestPhaseId());
				pestPhaeDTO.setPestPhaseName(phaseSurvey.getPestphase().getName());
				pestPhaeDTOs.add(pestPhaeDTO);
			}

			res.setBody(pestPhaeDTOs);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<List<PestPhaeDTO>>>(res, res.getHttpStatus());

		} catch (Exception ex) {

			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<List<PestPhaeDTO>>>(res, res.getHttpStatus());
		}
	}

	@PostMapping(value = "/{id}/targetofsurvey/{targetofsurveyId}/surveytarget")
	public ResponseEntity<Response<SurveyTarget>> createSurveyTargetBySurvey(@PathVariable int id,
			@PathVariable int targetofsurveyId, @Valid @RequestBody SurveyTarget surveyTarget) {
		Response<SurveyTarget> res = new Response<>();
		String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
		User user = userService.findByUsername(userName);
		TargetOfSurvey targetOfSurvey = targetOfSurveyService.findById(targetofsurveyId);
		Survey survey = surveyService.findByUserAndSurveyId(user, id);
		try {

			surveyTarget.setSurvey(survey);
			surveyTarget.setTargetofsurvey(targetOfSurvey);
			surveyTargetService.save(surveyTarget);
			res.setMessage("8888");
			res.setBody(surveyTarget);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<SurveyTarget>>(res, res.getHttpStatus());

		} catch (Exception ex) {
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<SurveyTarget>>(res, res.getHttpStatus());
		}
	}

//	@PostMapping(value="/{id}/surveytargetpoint")
//	 public ResponseEntity<Response<SurveyTarget>> createSurveyTargetPointBySurveyTarget(@PathVariable int id,@RequestBody SurveyTargetPoint surveyTargetPoint){
//		Response<SurveyTarget> res = new Response<>();
//	 	try {
//	 		SurveyTarget surveyTarget = surveyTargetService.findById(id);
//	 		surveyTargetPoint.setSurveytarget(surveyTarget);
//	 		surveyTargetPointService.save(surveyTargetPoint);
//			res.setBody(surveyTarget);
//	 		res.setHttpStatus(HttpStatus.OK);
//	 		return new ResponseEntity<Response<SurveyTarget>>(res,res.getHttpStatus());
//			
//	 	}catch(Exception ex) {
//	 		res.setBody(null);
//	 		res.setHttpStatus(HttpStatus.NOT_FOUND);
//	 		return new ResponseEntity<Response<SurveyTarget>>(res,res.getHttpStatus());
//	 	}
//	}

	@GetMapping("/{id}/user")
	public ResponseEntity<Response<User>> findFarmerOwnerInFieldBySurvey(@PathVariable int id) {
		Response<User> res = new Response<>();
		String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
		try {
			User user = userInFieldService.findByUserIdAndSurveyIdAndRoleName(userName, id).getUser();
			User u = new User(user.getFirstName(), user.getLastName(), user.getPhoneNo(), user.getUserStatus(),
					user.getRequestInfoStatus());
			res.setBody(u);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<User>>(res, res.getHttpStatus());
		} catch (Exception ex) {
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<User>>(res, res.getHttpStatus());
		}
	}

//	@GetMapping(value = "/page/{page}")
//	public ResponseEntity<Response<List<Survey>>> getAllSurvey(@PathVariable int page) {
//
//		Response<List<Survey>> res = new Response<>();
//		try {
//			String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
//			List<Survey> surveys = surveyService.findAll(userName,page,20);
//			res.setBody(surveys);
//			res.setHttpStatus(HttpStatus.OK);
//			return new ResponseEntity<Response<List<Survey>>>(res, res.getHttpStatus());
//
//		} catch (Exception ex) {
//			res.setBody(null);
//			res.setHttpStatus(HttpStatus.NOT_FOUND);
//			return new ResponseEntity<Response<List<Survey>>>(res, res.getHttpStatus());
//		}
//	}

	@GetMapping(value = "/page/{page}/value/{value}/date/{millisecondDate}")
	public ResponseEntity<Response<List<Survey>>> findAllOnDate(@PathVariable int page, @PathVariable int value,
			@PathVariable long millisecondDate) {
		Response<List<Survey>> res = new Response<>();
		String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
		try {
			Date date = new Date(millisecondDate);
			List<Survey> surveys = surveyService.findAllByUserNameAndDate(userName, date, page, value);
			res.setBody(surveys);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<List<Survey>>>(res, res.getHttpStatus());

		} catch (Exception ex) {
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<List<Survey>>>(res, res.getHttpStatus());
		}
	}

	@GetMapping(value = "/page/{page}/value/{value}")
	public ResponseEntity<Response<List<Survey>>> findAll(@PathVariable int page, @PathVariable int value) {
		Response<List<Survey>> res = new Response<>();
		String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
		try {
			Date date = new Date();
			List<Survey> surveys = surveyService.findAllByUserNameAndDate(userName, date, page, value);
			res.setBody(surveys);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<List<Survey>>>(res, res.getHttpStatus());

		} catch (Exception ex) {
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<List<Survey>>>(res, res.getHttpStatus());
		}
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<String>> deleteById(@PathVariable int id) {

		Response<String> res = new Response<>();

		try {
			surveyService.deleteById(id);
			res.setMessage("Delete " + id + " successfully.");
			res.setBody("OK");
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<String>>(res, res.getHttpStatus());
		} catch (Exception ex) {
			res.setMessage("Result not found");
			res.setBody(ex.toString());
			res.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			return new ResponseEntity<Response<String>>(res, res.getHttpStatus());
		}
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Response<String>> updateById(@PathVariable int id, @RequestBody Survey survey) {
		Response<String> res = new Response<>();
		String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
		try {
			User user = userService.findByUsername(userName);
			Survey s = surveyService.findByUserAndSurveyId(user, id);
			s.clone(survey);

			surveyService.save(s);
			res.setBody("");
			res.setMessage("Updated id = " + id + " Complete");
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<String>>(res, res.getHttpStatus());
		} catch (Exception ex) {
			res.setMessage(ex.toString());
			res.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			return new ResponseEntity<Response<String>>(res, res.getHttpStatus());
		}
	}

	@GetMapping(value = "/subdistrict/{subdistrictId}/page/{page}/value/{value}")
	public ResponseEntity<Response<List<Survey>>> findBySubdistrict(@PathVariable int subdistrictId,
			@PathVariable int page, @PathVariable int value, long millisecondDate) {
		Response<List<Survey>> res = new Response<>();
		String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
		try {
			Date date = new Date(millisecondDate);
			List<Survey> surveyList = surveyService.findBySubdistrictAndDate(userName, subdistrictId, date, page,
					value);
			res.setBody(surveyList);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<List<Survey>>>(res, res.getHttpStatus());

		} catch (Exception ex) {
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<List<Survey>>>(res, res.getHttpStatus());
		}
	}

	@GetMapping(value = "/district/{districtId}/page/{page}/value/{value}")
	public ResponseEntity<Response<List<Survey>>> findByDistrict(@PathVariable int districtId, @PathVariable int page,
			@PathVariable int value, long millisecondDate) {
		Response<List<Survey>> res = new Response<>();
		String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
		try {
			Date date = new Date(millisecondDate);
			List<Survey> surveyList = surveyService.findByDistrictAndDate(userName, districtId, date, page, value);
			res.setBody(surveyList);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<List<Survey>>>(res, res.getHttpStatus());

		} catch (Exception ex) {
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<List<Survey>>>(res, res.getHttpStatus());
		}
	}

	@GetMapping(value = "/field/{fieldId}/page/{page}/value/{value}")
	public ResponseEntity<Response<List<Survey>>> findByField(@PathVariable int fieldId, @PathVariable int page,
			@PathVariable int value, long millisecondDate) {
		Response<List<Survey>> res = new Response<>();
		String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
		try {
			Date date = new Date(millisecondDate);
			List<Survey> surveyList = surveyService.findByFieldAndDate(userName, fieldId, date, page, value);
			res.setBody(surveyList);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<List<Survey>>>(res, res.getHttpStatus());

		} catch (Exception ex) {
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<List<Survey>>>(res, res.getHttpStatus());
		}
	}

	@GetMapping(value = "/user/{userId}/page/{page}/value/{value}")
	public ResponseEntity<Response<List<Survey>>> findByOwner(@PathVariable int userId, @PathVariable int page,
			@PathVariable int value, long millisecondDate) {
		Response<List<Survey>> res = new Response<>();
		String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
		try {
			Date date = new Date(millisecondDate);
			List<Survey> surveyList = surveyService.findByOwnerAndDate(userName, userId, date, page, value);
			res.setBody(surveyList);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<List<Survey>>>(res, res.getHttpStatus());

		} catch (Exception ex) {
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<List<Survey>>>(res, res.getHttpStatus());
		}
	}

	@GetMapping(value = "/count")
	public ResponseEntity<Response<Integer>> findNumberOfAllSurveyByUser() {
		Response<Integer> res = new Response<>();
		try {
			String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
			User user = userService.findByUsername(userName);
			int NumberOfAllSurvey = surveyService.countByUserInfield(user);
			res.setMessage("Result count : " + NumberOfAllSurvey);
			res.setBody(NumberOfAllSurvey);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<Integer>>(res, res.getHttpStatus());
		} catch (Exception ex) {
			res.setMessage("Result not found");
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<Integer>>(res, res.getHttpStatus());

		}
	}

	@PostMapping(value = "/surveytargetpoint/{id}/images")
	public ResponseEntity<Response<ObjectNode>> uploadImageSurveyTargetPoint(@RequestParam("file") MultipartFile file,
			@PathVariable("id") int id) {
		Response<ObjectNode> res = new Response<>();
		try {
			String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());

			User user = userService.findByUsername(userName);

			SurveyTargetPoint point = surveyTargetPointService.findByUserAndSurveyTargetPointId(user, id);

			TargetOfSurvey targetOfSurvey = point.getSurveytarget().getTargetofsurvey();

			String fileInfo = surveyTargetPointService.writeFile(file, targetOfSurvey.getName());

			String resizename = "s_" + fileInfo;

			Path source = Paths.get(externalPath + File.separator + "SurveyTargetPoint" + File.separator + fileInfo);

			Path target = Paths
					.get(externalPath + File.separator + "s_SurveyTargetPoint" + File.separator + resizename);

			InputStream is = new FileInputStream(source.toFile());

			ImageBase64Helper.resizeImage(is, target);

			ImgSurveyTargetPoint imgSurveyTargetPoint = new ImgSurveyTargetPoint();

			imgSurveyTargetPoint.setSurveytargetpoint(point);
			imgSurveyTargetPoint.setUserByUploadBy(user);
			imgSurveyTargetPoint.setUploadDate(new Date());
			imgSurveyTargetPoint.setFilePath(fileInfo);
			imgSurveyTargetPoint.setFilePathS(resizename);
			imgSurveyTargetPoint.setApproveStatus("Waiting");
			imgSurveyTargetPointService.save(imgSurveyTargetPoint);

			ObjectMapper mapper = new ObjectMapper();

			ObjectNode responObject = mapper.createObjectNode();

			String filePath = externalPath + File.separator + "s_SurveyTargetPoint" + File.separator
					+ imgSurveyTargetPoint.getFilePathS();

			res.setMessage("Uploaded Complete");
			responObject.put("base64Image", ImageBase64Helper.toImageBase64(filePath));
			res.setBody(responObject);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<ObjectNode>>(res, res.getHttpStatus());
		} catch (NullPointerException ex) {

			res.setMessage("Upload Failed");
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<ObjectNode>>(res, res.getHttpStatus());
		} catch (Exception ex) {
			res.setMessage("Upload Failed");
			res.setBody(null);
			res.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			return new ResponseEntity<Response<ObjectNode>>(res, res.getHttpStatus());
		}
	}

	@GetMapping("/surveytargetpoint/{id}/images")
	public ResponseEntity<Response<List<ImgSurveyPointDTO>>> getAllImageSurveyTargetPoint(@PathVariable("id") int id) {
		Response<List<ImgSurveyPointDTO>> res = new Response<>();
		try {
			String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
			User user = userService.findByUsername(userName);

			SurveyTargetPoint point = surveyTargetPointService.findByUserAndSurveyTargetPointId(user, id);

			List<ImgSurveyTargetPoint> imgSurveyTargetPoints = point.getImgsurveytargetpoints();

			List<ImgSurveyPointDTO> imgSurveyPointDTOs = new ArrayList<>();

			for (ImgSurveyTargetPoint imgS : imgSurveyTargetPoints) {
				String path = externalPath + File.separator + "s_SurveyTargetPoint" + File.separator
						+ imgS.getFilePathS();

				ImgSurveyPointDTO dto = new ImgSurveyPointDTO();
				dto.setImageId(imgS.getImgSurveyTargetPointId());
				dto.setImgBase64(ImageBase64Helper.toImageBase64(path));

				imgSurveyPointDTOs.add(dto);

			}

			res.setMessage("Files Loading Complete");
			res.setBody(imgSurveyPointDTOs);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<List<ImgSurveyPointDTO>>>(res, res.getHttpStatus());

		} catch (NullPointerException ex) {

			res.setMessage("Survey Target Point not found");
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<List<ImgSurveyPointDTO>>>(res, res.getHttpStatus());
		}

		catch (Exception ex) {
			res.setMessage("Error");
			res.setBody(null);
			res.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			return new ResponseEntity<Response<List<ImgSurveyPointDTO>>>(res, res.getHttpStatus());
		}
	}

	@DeleteMapping("/surveytargetpoint/{id}/images/{imgID}")
	public ResponseEntity<Response<String>> deleteSurveytargetpointImageById(@PathVariable("id") int id,
			@PathVariable("imgID") int imgID) {
		Response<String> res = new Response<>();
		try {
			String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
			User user = userService.findByUsername(userName);
			SurveyTargetPoint point = surveyTargetPointService.findByUserAndSurveyTargetPointId(user, id);

			if (point == null)
				throw new NullPointerException();

			imgSurveyTargetPointService.deleteById(imgID);

			res.setMessage("Successfully deleted");
			res.setBody("");
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<String>>(res, res.getHttpStatus());
		} catch (Exception ex) {
			res.setMessage("Failed to delete");
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<String>>(res, res.getHttpStatus());
		}
	}

	@PostMapping(value = "/search")
	public ResponseEntity<Response<List<Survey>>> search(@RequestBody SurveySearchDTO searchDTO) {
		Response<List<Survey>> res = new Response<>();
		try {
			String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
			User user = userService.findByUsername(userName);
			List<Survey> result = surveyService.search(searchDTO, user);
			res.setBody(result);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<List<Survey>>>(res, res.getHttpStatus());
		} catch (Exception ex) {
			res.setMessage(ex.getMessage());
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<List<Survey>>>(res, res.getHttpStatus());
		}
	}

	@PostMapping(value = "/search/page/{page}/value/{value}/date/{milliseconddate}")
	public ResponseEntity<Response<List<?>>> searchByKeyPagination(@RequestBody SurveySearchDTO searchDTO,
			@PathVariable("page") int page, @PathVariable("value") int value,
			@PathVariable("milliseconddate") long milliseconddate) {
		ObjectMapper mapper = new ObjectMapper();
		List<ObjectNode> responseList = new ArrayList<>();
		Response<List<?>> res = new Response();
		try {
			String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
			User user = userService.findByUsername(userName);
			Date date = new Date(milliseconddate);
			List<Survey> surveys = surveyService.searchPagination(searchDTO, user, page, value, date);

			for (Survey survey : surveys) {
				ObjectNode responObject = mapper.createObjectNode();
				Planting planting = survey.getPlanting();
				Field field = planting.getField();
				User userowner = userService.findByFieldIdAndRoleAndUser(field.getFieldId(), "farmerOwner", userName)
						.get(0);
				Subdistrict subdistrict = field.getSubdistrict();
				responObject.put("surveyId", survey.getSurveyId());
				responObject.put("plantingName", planting.getName());
				responObject.put("fieldName", field.getName());
				responObject.put("substrict", subdistrict.getName());
				responObject.put("district", subdistrict.getDistrict().getName());
				responObject.put("province", subdistrict.getDistrict().getProvince().getName());
				responObject.put("title", userowner.getTitle());
				responObject.put("firstName", userowner.getFirstName());
				responObject.put("lastName", userowner.getLastName());
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

	@GetMapping(value = "/index/page/{page}/value/{value}/date/{millisecondDate}")
	public ResponseEntity<Response<List<?>>> index(@PathVariable("page") int page, @PathVariable("value") int value,
			@PathVariable("millisecondDate") long millisecondDate) {
		String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
		ObjectMapper mapper = new ObjectMapper();
		List<ObjectNode> responseList = new ArrayList<>();
		Response<List<?>> res = new Response();

		try {
			Date date = new Date(millisecondDate);
			List<Survey> surveys = surveyService.findAllByUserNameAndDate(userName, date, page, value);

			for (Survey survey : surveys) {
				ObjectNode responObject = mapper.createObjectNode();
				Planting planting = survey.getPlanting();
				Field field = planting.getField();
				User user = userService.findByFieldIdAndRoleAndUser(field.getFieldId(), "farmerOwner", userName).get(0);
				Subdistrict subdistrict = field.getSubdistrict();
				responObject.put("surveyId", survey.getSurveyId());
				responObject.put("plantingName", planting.getName());
				responObject.put("fieldName", field.getName());
				responObject.put("substrict", subdistrict.getName());
				responObject.put("district", subdistrict.getDistrict().getName());
				responObject.put("province", subdistrict.getDistrict().getProvince().getName());
				responObject.put("title", user.getTitle());
				responObject.put("firstName", user.getFirstName());
				responObject.put("lastName", user.getLastName());
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

	@GetMapping(value = "/{id}/surveypoints")
	public ResponseEntity<Response<List<SurveyPoint>>> getSurveyPoint(@PathVariable int id) {
		Response<List<SurveyPoint>> res = new Response<>();

		try {
			String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());

			User user = userService.findByUsername(userName);
			Survey survey = surveyService.findByUserAndSurveyId(user, id);

			res.setBody(survey.getSurveyPoints());
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<List<SurveyPoint>>>(res, res.getHttpStatus());
		} catch (Exception ex) {
			res.setMessage(ex.getMessage());
			res.setBody(null);
			res.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			return new ResponseEntity<Response<List<SurveyPoint>>>(res, res.getHttpStatus());

		}

	}

	@PutMapping(value = "/surveypoint")
	public ResponseEntity<Response<SurveyPoint>> editStatus(@RequestBody String surveyPoint) {
		Response<SurveyPoint> res = new Response<>();

		ObjectMapper mapper = new ObjectMapper();
		try {
			String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());

			JsonNode jsonNode = mapper.readTree(surveyPoint);
			int surveyId = jsonNode.get("surveyId").asInt();
			int pointNumber = jsonNode.get("pointNumber").asInt();
			String status = jsonNode.get("status").asText();
			SurveyPoint result = surveyPointService.findBySurveyIdAndPointNumber(surveyId, pointNumber);
			result.setStatus(status);
			surveyPointService.save(result);
			res.setBody(result);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<SurveyPoint>>(res, res.getHttpStatus());
		} catch (Exception ex) {
			res.setMessage(ex.getMessage());
			res.setBody(null);
			res.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			return new ResponseEntity<Response<SurveyPoint>>(res, res.getHttpStatus());

		}

	}

	@GetMapping(value = "/createdate/{millisecondDate}")
	public ResponseEntity<Response<List<Survey>>> findByCreateDate(
			@PathVariable("millisecondDate") long millisecondDate) {
		Response<List<Survey>> res = new Response<>();
		String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
		try {
			Date date = new Date(millisecondDate);
			List<Survey> survey = surveyService.findByCreateDateAndUser(userName, date);
			res.setBody(survey);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<List<Survey>>>(res, res.getHttpStatus());

		} catch (Exception ex) {
			res.setMessage(ex.getMessage());
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<List<Survey>>>(res, res.getHttpStatus());
		}
	}

	@PostMapping(value = "/searchbykey/page/{page}/value/{value}/date/{milliseconddate}")
	public ResponseEntity<Response<List<?>>> search(@RequestBody String key, @PathVariable("page") int page,
			@PathVariable("value") int value, @PathVariable("milliseconddate") long milliseconddate) {
		ObjectMapper mapper = new ObjectMapper();
		List<ObjectNode> responseList = new ArrayList<>();
		Response<List<?>> res = new Response();
		try {
			String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
			User user = userService.findByUsername(userName);
			Date date = new Date(milliseconddate);
			JsonNode jsonNode = mapper.readTree(key);
			String skey = jsonNode.get("key").asText();
			List<Survey> surveys = surveyService.findByKey(skey, user, page, value, date);
			for (Survey survey : surveys) {
				ObjectNode responObject = mapper.createObjectNode();
				Planting planting = survey.getPlanting();
				Field field = planting.getField();
				User userOwner = userService.findByFieldIdAndRoleAndUser(field.getFieldId(), "farmerOwner", userName)
						.get(0);
				Subdistrict subdistrict = field.getSubdistrict();
				responObject.put("surveyId", survey.getSurveyId());
				responObject.put("plantingName", planting.getName());
				responObject.put("fieldName", field.getName());
				responObject.put("substrict", subdistrict.getName());
				responObject.put("district", subdistrict.getDistrict().getName());
				responObject.put("province", subdistrict.getDistrict().getProvince().getName());
				responObject.put("title", userOwner.getTitle());
				responObject.put("firstName", userOwner.getFirstName());
				responObject.put("lastName", userOwner.getLastName());
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

	@GetMapping(value = "/planting/{plantingid}/page/{page}/value/{value}")
	public ResponseEntity<Response<List<?>>> findByUserInFieldAndPlanting(@PathVariable("plantingid") int plantingid,
			@PathVariable("page") int page, @PathVariable("value") int value) {
		ObjectMapper mapper = new ObjectMapper();
		List<ObjectNode> responseList = new ArrayList<>();
		Response<List<?>> res = new Response();
		String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
		User user = userService.findByUsername(userName);
		try {
			List<Survey> surveys = surveyService.findByUserInFieldAndPlanting(user, plantingid, page, value);

			for (Survey survey : surveys) {
				ObjectNode responObject = mapper.createObjectNode();
				Planting planting = survey.getPlanting();
				Field field = planting.getField();
				User userOwner = userService.findByFieldIdAndRoleAndUser(field.getFieldId(), "farmerOwner", userName)
						.get(0);
				Subdistrict subdistrict = field.getSubdistrict();
				responObject.put("surveyId", survey.getSurveyId());
				responObject.put("plantingName", planting.getName());
				responObject.put("fieldName", field.getName());
				responObject.put("substrict", subdistrict.getName());
				responObject.put("district", subdistrict.getDistrict().getName());
				responObject.put("province", subdistrict.getDistrict().getProvince().getName());
				responObject.put("title", userOwner.getTitle());
				responObject.put("firstName", userOwner.getFirstName());
				responObject.put("lastName", userOwner.getLastName());
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

	@GetMapping(value = "/count/plantingid/{plantingid}")
	public ResponseEntity<Response<Integer>> countByPlantingId(@PathVariable("plantingid") int plantingid) {
		Response<Integer> res = new Response<>();
		try {
			String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
			User user = userService.findByUsername(userName);
			int NumberOfAllSurvey = surveyService.countByUserInFieldAndPlantingId(user, plantingid);
			res.setMessage("Result count : " + NumberOfAllSurvey);
			res.setBody(NumberOfAllSurvey);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<Integer>>(res, res.getHttpStatus());
		} catch (Exception ex) {
			res.setMessage("Result not found");
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<Integer>>(res, res.getHttpStatus());

		}
	}

	@GetMapping(value = "/{surveyid}/surveypoint")
	public ResponseEntity<Response<List<SurveyPoint>>> findSurveyPointBySurveyId(
			@PathVariable("surveyid") int surveyid) {
		Response<List<SurveyPoint>> res = new Response<>();
		try {
			String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
			User user = userService.findByUsername(userName);
			List<SurveyPoint> surveyPoints = surveyPointService.findBySurveyId(surveyid);

			res.setBody(surveyPoints);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<List<SurveyPoint>>>(res, res.getHttpStatus());
		} catch (Exception ex) {
			res.setMessage(ex.getLocalizedMessage());
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<List<SurveyPoint>>>(res, res.getHttpStatus());
		}
	}

	@PutMapping(value = "/{surveyid}/")
	public ResponseEntity<Response<SurveyPoint>> updateSurveyPoint(@PathVariable("surveyid") int surveyid,
			@RequestBody SurveyPoint surveyPoint) {
		Response<SurveyPoint> res = new Response<>();
		try {
			SurveyPoint point = surveyPointService.findBySurveyIdAndPointNumber(surveyid, surveyPoint.getPointNo());
			point.setStatus(surveyPoint.getStatus());
			point = surveyPointService.save(point);
			res.setBody(point);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<SurveyPoint>>(res, res.getHttpStatus());
		} catch (Exception ex) {
			res.setMessage(ex.getLocalizedMessage());
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<SurveyPoint>>(res, res.getHttpStatus());
		}
	}

}
