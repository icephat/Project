package org.cassava.api.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;

import org.cassava.api.util.model.Response;
import org.cassava.model.Field;
import org.cassava.model.Herbicide;
import org.cassava.model.Planting;
import org.cassava.model.PlantingCassavaVariety;
import org.cassava.model.PlantingCassavaVarietyId;
import org.cassava.model.Subdistrict;
import org.cassava.model.Survey;
import org.cassava.model.User;
import org.cassava.model.Variety;
import org.cassava.model.dto.PlantingSearchDTO;
import org.cassava.services.FieldService;
import org.cassava.services.HerbicideService;
import org.cassava.services.PlantingCassavaVarietyService;
import org.cassava.services.PlantingService;
import org.cassava.services.SurveyService;
import org.cassava.services.UserService;
import org.cassava.services.VarietyService;
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
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@RequestMapping(value = "/api/planting")
public class PlantingRestController {
	@Autowired
	private PlantingService plantingService;

	@Autowired
	private SurveyService surveyService;

	@Autowired
	private FieldService fieldService;

	@Autowired
	private UserService userService;

	@Autowired
	private VarietyService varietyService;

	@Autowired
	private HerbicideService herbicideService;

	@Autowired
	private PlantingCassavaVarietyService plantingCassavaVarietyService;

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

	@PostMapping(value = "/")
	public ResponseEntity<Response<?>> createPlanting(@RequestBody String rawPlaning) {

		Response<ObjectNode> res = new Response<>();

		ObjectMapper mapper = new ObjectMapper();

		try {
			String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());

			User user = userService.findByUsername(userName);
			JsonNode jsonNode = mapper.readTree(rawPlaning);
			int fieldid = jsonNode.get("field").asInt();
			JsonNode varieties = jsonNode.get("plantingcassavavarieties");

			JsonNode h1Node = jsonNode.get("herbicideByWeedingChemical1");
			JsonNode h2Node = jsonNode.get("herbicideByWeedingChemical2");
			JsonNode h3Node = jsonNode.get("herbicideByWeedingChemical3");

			// System.out.println(fieldid+" "+h1);
			Field field = fieldService.findById(fieldid, user);

			// System.out.println(planting);

			Planting planting = mapper.readValue(rawPlaning, Planting.class);

			planting.setField(field);
			planting.setUserByLastUpdateBy(user);
			planting.setLastUpdateDate(new Date());
			planting.setCreateDate(new Date());
			// System.out.println(planting2.getName());
			// System.out.println(planting2.getHerbicideByWeedingChemical2());
			if (h1Node != null) {
				Herbicide herbicide1 = herbicideService.findById(h1Node.asInt());
				planting.setHerbicideByWeedingChemical1(herbicide1);
			}

			if (h2Node != null) {
				Herbicide herbicide2 = herbicideService.findById(h2Node.asInt());
				planting.setHerbicideByWeedingChemical2(herbicide2);
			}

			if (h3Node != null) {
				Herbicide herbicide3 = herbicideService.findById(h3Node.asInt());
				planting.setHerbicideByWeedingChemical3(herbicide3);
			}
			planting.setUserByCreateBy(user);
			planting.setUserByLastUpdateBy(user);
			planting = plantingService.save(planting);
			// res.setBody(planting);
			for (int i = 0; i < varieties.size(); i++) {
				int varietyId = varieties.get(i).asInt();
				Variety variety = varietyService.findById(varietyId);
				PlantingCassavaVarietyId pvi = new PlantingCassavaVarietyId();
				pvi.setPlantingId(planting.getPlantingId());
				pvi.setVarietyId(variety.getVarietyId());

				PlantingCassavaVariety pv = new PlantingCassavaVariety();
				pv.setId(pvi);
				pv.setPlanting(planting);
				pv.setVariety(variety);
				plantingCassavaVarietyService.save(pv);
			}

			String plantObjStr = mapper.writeValueAsString(planting);

			JsonNode jNode = mapper.readTree(plantObjStr);

			ObjectNode responObject = mapper.createObjectNode();
			Iterator<String> iterator = jsonNode.fieldNames();
			responObject.put("plantingId", planting.getPlantingId());
			iterator.forEachRemaining(e -> {
				if (jNode.get(e) != null)
					responObject.put(e.toString(), jNode.get(e));

			});
			Herbicide h1 = planting.getHerbicideByWeedingChemical1();
			Herbicide h2 = planting.getHerbicideByWeedingChemical2();
			Herbicide h3 = planting.getHerbicideByWeedingChemical3();
			if (h1 != null) {
				responObject.put("herbicideByWeedingChemical1", h1.getHerbicideId());
			}
			if (h2 != null) {
				responObject.put("herbicideByWeedingChemical2", h2.getHerbicideId());
			}
			if (h3 != null) {
				responObject.put("herbicideByWeedingChemical3", h3.getHerbicideId());
			}

			res.setBody(responObject);

			res.setHttpStatus(HttpStatus.OK);

			res.setMessage("Planting created");

		} catch (Exception e) {
			res.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			res.setMessage(e.getMessage());
		}

		return new ResponseEntity<Response<?>>(res, res.getHttpStatus());
		/*
		 * Response<Planting> res = new Response<>(); try {
		 * 
		 * String userName =
		 * MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication())
		 * ; User user = userService.findByUsername(userName); Field field =
		 * fieldService.findById(fieldid, user); if (herbicideid1 != 0) { Herbicide
		 * herbicide1 = herbicideService.findById(herbicideid1);
		 * planting.setHerbicideByWeedingChemical1(herbicide1); }
		 * 
		 * if (herbicideid2 != 0) { Herbicide herbicide2 =
		 * herbicideService.findById(herbicideid2);
		 * planting.setHerbicideByWeedingChemical2(herbicide2); }
		 * 
		 * if (herbicideid3 != 0) { Herbicide herbicide3 =
		 * herbicideService.findById(herbicideid3);
		 * planting.setHerbicideByWeedingChemical3(herbicide3); }
		 * 
		 * planting.setUserByCreateBy(user); planting.setUserByLastUpdateBy(user);
		 * 
		 * planting.setField(field); plantingService.save(planting);
		 * res.setBody(planting); res.setHttpStatus(HttpStatus.OK); return new
		 * ResponseEntity<Response<Planting>>(res, res.getHttpStatus());
		 * 
		 * } catch (Exception ex) { res.setMessage(ex.getMessage()); res.setBody(null);
		 * res.setHttpStatus(HttpStatus.NOT_FOUND); return new
		 * ResponseEntity<Response<Planting>>(res, res.getHttpStatus()); }
		 */
	}

	/*
	 * @PostMapping(value = "/") public ResponseEntity<Response<String>>
	 * addnewPlanting(@RequestBody Planting planting) { Response<String> res = new
	 * Response<>(); try { String userName =
	 * MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication())
	 * ; User user = userService.findByUsername(userName);
	 * planting.setUserByCreateBy(user); planting.setUserByLastUpdateBy(user);
	 * plantingService.save(planting); res.setMessage("Add planting successfully");
	 * res.setBody("OK"); res.setHttpStatus(HttpStatus.OK); return new
	 * ResponseEntity<Response<String>>(res, res.getHttpStatus()); } catch
	 * (Exception ex) { res.setMessage(ex.toString());
	 * res.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR); return new
	 * ResponseEntity<Response<String>>(res, res.getHttpStatus()); } }
	 */

	@GetMapping(value = "/subdistric/{subid}/page/{page}/value/{value}")
	public ResponseEntity<Response<List<Planting>>> findBySubdistrict(@PathVariable int subid, @PathVariable int page,
			@PathVariable int value, long millisecondDate) {
		Response<List<Planting>> res = new Response<>();
		try {
			Date date = new Date(millisecondDate);
			String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
			List<Planting> plantings = plantingService.findByUsernameAndSubdistrictAndDate(userName, subid, page, value,
					date);
			res.setBody(plantings);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<List<Planting>>>(res, res.getHttpStatus());
		} catch (Exception ex) {
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<List<Planting>>>(res, res.getHttpStatus());
		}
	}

	@GetMapping(value = "/surveytarget/{id}/page/{page}/value/{value}")
	public ResponseEntity<Response<List<Planting>>> getPlantingDisease(@PathVariable int id, @PathVariable int page,
			@PathVariable int value, long millisecondDate) {
		Response<List<Planting>> res = new Response<>();
		try {
			Date date = new Date(millisecondDate);
			String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
			List<Planting> plantings = plantingService.findPlantingDiseaseBySurveyTargetIdAndUsernameAndDate(id,
					userName, page, value, date);
			res.setBody(plantings);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<List<Planting>>>(res, res.getHttpStatus());
		} catch (Exception ex) {
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<List<Planting>>>(res, res.getHttpStatus());
		}
	}

	@GetMapping(value = "/distric/{disid}/page/{page}/value/{value}")
	public ResponseEntity<Response<List<Planting>>> findByDistrict(@PathVariable int disid, @PathVariable int page,
			@PathVariable int value, long millisecondDate) {
		Response<List<Planting>> res = new Response<>();
		try {
			String username = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
			Date date = new Date(millisecondDate);
			List<Planting> plantings = plantingService.findByUsernameAndDistrictAndDate(username, disid, page, value,
					date);
			res.setBody(plantings);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<List<Planting>>>(res, res.getHttpStatus());
		} catch (Exception ex) {
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<List<Planting>>>(res, res.getHttpStatus());
		}
	}

	@GetMapping(value = "/fieldowner/{ownerid}/page/{page}/value/{value}")
	public ResponseEntity<Response<List<Planting>>> findByFieldOwner(@PathVariable int ownerid, @PathVariable int page,
			@PathVariable int value, long millisecondDate) {
		Response<List<Planting>> res = new Response<>();
		try {
			String username = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
			Date date = new Date(millisecondDate);
			List<Planting> plantings = plantingService.findByUsernameAndOwnerIdAndDate(username, ownerid, page, value,
					date);
			res.setBody(plantings);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<List<Planting>>>(res, res.getHttpStatus());
		} catch (Exception ex) {
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<List<Planting>>>(res, res.getHttpStatus());
		}
	}

	@GetMapping(value = "/survey/{surveyId}")
	public ResponseEntity<Response<Planting>> findBySurveyId(@PathVariable("surveyId") int surveyId) {
		Response<Planting> res = new Response<>();
		try {
			String username = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
			Planting planting = plantingService.findBySurveyIdAndUsername(surveyId, username);
			res.setBody(planting);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<Planting>>(res, res.getHttpStatus());
		} catch (Exception ex) {
			res.setMessage(ex.getMessage());
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<Planting>>(res, res.getHttpStatus());
		}
	}

	@GetMapping(value = "/page/{page}")
	public ResponseEntity<Response<List<Planting>>> getAllPlanting(@PathVariable int page) {
		Response<List<Planting>> res = new Response<>();
		try {
			page -= 1;
			String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
//			User user = userService.findByUsername(userName);
			List<Planting> plantings = plantingService.findByUsername(userName, page, 20);
			res.setBody(plantings);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<List<Planting>>>(res, res.getHttpStatus());
		} catch (Exception ex) {
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<List<Planting>>>(res, res.getHttpStatus());
		}
	}

//	@GetMapping(value=)

	@PostMapping(value = "/{id}/survey")
	public ResponseEntity<Response<Survey>> createSurveyByPlantingID(@PathVariable int id,
			@Valid @RequestBody Survey survey) {
		Response<Survey> res = new Response<>();
		try {
			String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
			User user = userService.findByUsername(userName);
			Planting planting = plantingService.findById(id);
			survey.setPlanting(planting);
			survey.setUserByCreateBy(user);
			survey = surveyService.save(survey);
			res.setBody(survey);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<Survey>>(res, res.getHttpStatus());

		} catch (Exception ex) {
			res.setMessage(ex.getMessage());
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<Survey>>(res, res.getHttpStatus());
		}
	}

	@GetMapping(value = "/page/{page}/value/{value}/")
	public ResponseEntity<Response<List<Planting>>> getAllPlanting(@PathVariable int page, @PathVariable int value) {
		Response<List<Planting>> res = new Response<>();
		try {
			page -= 1;
			Date date = new Date();
			String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
			List<Planting> plantings = plantingService.findByUsernameAndDate(userName, page, value, date);
			res.setBody(plantings);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<List<Planting>>>(res, res.getHttpStatus());
		} catch (Exception ex) {
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<List<Planting>>>(res, res.getHttpStatus());
		}
	}

	@GetMapping(value = "/page/{page}/value/{value}/date/{millisecondDate}")
	public ResponseEntity<Response<?>> getAllPlantingOnDate(@PathVariable int page, @PathVariable int value,
			@PathVariable long millisecondDate) {
		// Response<List<Planting>> res = new Response<>();
		Response<List<ObjectNode>> res = new Response<>();
		try {
			page -= 1;
			Date date = new Date(millisecondDate);
			String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
			List<Planting> plantings = plantingService.findByUsernameAndDate(userName, page, value, date);
			List<ObjectNode> objPlanList = new ArrayList<ObjectNode>();
			ObjectMapper mapper = new ObjectMapper();
			plantings.forEach(e1 -> {
				try {
					String plantObjStr = mapper.writeValueAsString(e1);
					JsonNode jsonNode = mapper.readTree(plantObjStr);
					ObjectNode responObject = mapper.createObjectNode();
					Iterator<String> iterator = jsonNode.fieldNames();
					iterator.forEachRemaining(e2 -> {
						responObject.put(e2.toString(), jsonNode.get(e2));
					});
					Herbicide h1 = e1.getHerbicideByWeedingChemical1();
					Herbicide h2 = e1.getHerbicideByWeedingChemical2();
					Herbicide h3 = e1.getHerbicideByWeedingChemical3();
					if (h1 != null) {
						responObject.put("herbicideByWeedingChemical1", h1.getHerbicideId());
					}
					if (h2 != null) {
						responObject.put("herbicideByWeedingChemical2", h2.getHerbicideId());
					}
					if (h3 != null) {
						responObject.put("herbicideByWeedingChemical3", h3.getHerbicideId());
					}
					objPlanList.add(responObject);
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			});

			// res.setBody(plantings);
			res.setBody(objPlanList);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<?>>(res, res.getHttpStatus());
		} catch (Exception ex) {
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<?>>(res, res.getHttpStatus());
		}
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Response<String>> updatePlantingById(@PathVariable int id, @RequestBody String rawPlaning) {
		Response<String> res = new Response<>();
		ObjectMapper mapper = new ObjectMapper();
		try {
			String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
			User user = userService.findByUsername(userName);

			JsonNode jsonNode = mapper.readTree(rawPlaning);
			JsonNode varieties = jsonNode.get("plantingcassavavarieties");

			JsonNode h1Node = jsonNode.get("herbicideByWeedingChemical1");
			JsonNode h2Node = jsonNode.get("herbicideByWeedingChemical2");
			JsonNode h3Node = jsonNode.get("herbicideByWeedingChemical3");

			Planting planting = mapper.readValue(rawPlaning, Planting.class);
			Planting p = plantingService.findById(id);

			if (h1Node != null) {
				Herbicide herbicide1 = herbicideService.findById(h1Node.asInt());
				planting.setHerbicideByWeedingChemical1(herbicide1);
			}

			if (h2Node != null) {
				Herbicide herbicide2 = herbicideService.findById(h2Node.asInt());
				planting.setHerbicideByWeedingChemical2(herbicide2);
			}

			if (h3Node != null) {
				Herbicide herbicide3 = herbicideService.findById(h3Node.asInt());
				planting.setHerbicideByWeedingChemical3(herbicide3);
			}

			planting.setUserByLastUpdateBy(user);
			p.clone(planting);
			p.setLastUpdateDate(new Date());
			p = plantingService.save(p);

			if (varieties != null) {

				List<PlantingCassavaVariety> cassavaVarieties = p.getPlantingcassavavarieties();
				List<Integer> list = new ArrayList<Integer>();
				List<Integer> list2 = new ArrayList<Integer>();

				for (PlantingCassavaVariety pcv : cassavaVarieties) {
					list.add(pcv.getVariety().getVarietyId());
				}

				for (int i = 0; i < varieties.size(); i++) {
					list2.add(varieties.get(i).asInt());
				}

				list.removeAll(list2);

				for (Integer varietyId : list) {
					PlantingCassavaVariety cassavaVariety = plantingCassavaVarietyService
							.findByVarietyIdAndPlantingId(varietyId, p.getPlantingId());
					plantingCassavaVarietyService.deleteByPlantingCassavaVariety(cassavaVariety);
				}

				for (int i = 0; i < varieties.size(); i++) {
					Variety v = varietyService.findById(varieties.get(i).asInt());
					PlantingCassavaVarietyId pvi = new PlantingCassavaVarietyId();
					pvi.setPlantingId(p.getPlantingId());
					pvi.setVarietyId(v.getVarietyId());

					PlantingCassavaVariety pv = new PlantingCassavaVariety();
					pv.setId(pvi);
					pv.setPlanting(p);
					pv.setVariety(v);
					plantingCassavaVarietyService.save(pv);
				}
			}

			res.setBody("");
			res.setMessage("Update Planting Id : " + id);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<String>>(res, res.getHttpStatus());
		} catch (Exception ex) {
			res.setBody(null);
			res.setMessage(ex.toString());
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<String>>(res, res.getHttpStatus());
		}
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<?>> getPlantByid(@PathVariable int id) {
		Response<ObjectNode> res = new Response<>();
		try {
			String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
			Planting planting = plantingService.findByUsernameAndPlantingId(userName, id);

			ObjectMapper mapper = new ObjectMapper();
			String plantObjStr = mapper.writeValueAsString(planting);

			JsonNode jsonNode = mapper.readTree(plantObjStr);
			ObjectNode responObject = mapper.createObjectNode();
			Iterator<String> iterator = jsonNode.fieldNames();
			iterator.forEachRemaining(e -> {
				responObject.put(e.toString(), jsonNode.get(e));

			});
			Herbicide h1 = planting.getHerbicideByWeedingChemical1();
			Herbicide h2 = planting.getHerbicideByWeedingChemical2();
			Herbicide h3 = planting.getHerbicideByWeedingChemical3();
			if (h1 != null) {
				responObject.put("herbicideByWeedingChemical1", h1.getHerbicideId());
			}
			if (h2 != null) {
				responObject.put("herbicideByWeedingChemical2", h2.getHerbicideId());
			}
			if (h3 != null) {
				responObject.put("herbicideByWeedingChemical3", h3.getHerbicideId());
			}
			res.setBody(responObject);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<?>>(res, res.getHttpStatus());
		} catch (Exception ex) {
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<?>>(res, res.getHttpStatus());
		}
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<String>> deletePlantingById(@PathVariable int id) {
		Response<String> res = new Response<>();
		try {
			plantingService.deleteById(id);
			res.setMessage("Delete " + id + " successfully.");
			res.setBody("");
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<String>>(res, res.getHttpStatus());
		} catch (Exception ex) {
			res.setMessage("Result not found");
			res.setBody(ex.toString());
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<String>>(res, res.getHttpStatus());

		}
	}

	@PostMapping(value = "/{plantingId}/plantingcassavavariety")
	public ResponseEntity<Response<List<PlantingCassavaVarietyId>>> createPlantingCassavaVarietyByPlantingId(
			@PathVariable("plantingId") int plantingId, @Valid @RequestBody Variety[] varietyId) {
		Response<List<PlantingCassavaVarietyId>> res = new Response<>();
		try {
//	 		String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
//	 		User user = userService.findByUsername(userName);
			List<PlantingCassavaVarietyId> cassavaVarietyIds = new ArrayList<PlantingCassavaVarietyId>();
			Planting planting = plantingService.findById(plantingId);
			for (Variety id : varietyId) {
				Variety variety = varietyService.findById(id.getVarietyId());
				PlantingCassavaVarietyId cassavaVarietyId = new PlantingCassavaVarietyId(plantingId, id.getVarietyId());
				PlantingCassavaVariety cassavaVariety = new PlantingCassavaVariety(cassavaVarietyId, planting, variety);
				plantingCassavaVarietyService.save(cassavaVariety);
				cassavaVarietyIds.add(cassavaVarietyId);
			}
//	 		survey.setPlanting(planting);
//	 		survey.setUserByCreateBy(user);
//	 		surveyService.save(survey);
			res.setMessage("Create Success");
			res.setBody(cassavaVarietyIds);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<List<PlantingCassavaVarietyId>>>(res, res.getHttpStatus());

		} catch (Exception ex) {
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<List<PlantingCassavaVarietyId>>>(res, res.getHttpStatus());
		}
	}

	@PostMapping(value = "/search")
	public ResponseEntity<Response<List<Planting>>> search(@RequestBody PlantingSearchDTO searchDTO) {
		Response<List<Planting>> res = new Response<>();
		try {
			String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
			User user = userService.findByUsername(userName);
			List<Planting> result = plantingService.search(searchDTO, user);
			res.setBody(result);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<List<Planting>>>(res, res.getHttpStatus());
		} catch (Exception ex) {
			res.setMessage(ex.getMessage());
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<List<Planting>>>(res, res.getHttpStatus());
		}
	}

	@GetMapping(value = "/{id}/survey/page/{page}/value/{value}/date/{millisecondDate}")
	public ResponseEntity<Response<List<Survey>>> getSurveyInPlanting(@PathVariable int id, @PathVariable int page,
			@PathVariable int value, @PathVariable long millisecondDate) {
		Response<List<Survey>> res = new Response<>();
		try {
			String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
			Date date = new Date(millisecondDate);
			List<Survey> plantings = surveyService.findByFieldIdAndUserAndDate(id, userName, page, value, date);
			res.setBody(plantings);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<List<Survey>>>(res, res.getHttpStatus());

		} catch (Exception ex) {
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<List<Survey>>>(res, res.getHttpStatus());
		}
	}

	@GetMapping(value = "/{plantingid}/fieldowner")
	public ResponseEntity<Response<Object>> findOwnerByPlantingId(@PathVariable("plantingid") int plantingId) {
		Response<Object> res = new Response<>();
		try {
			String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
			User user = userService.findByUsername(userName);
			Object result = userService.findByPlantingIdAndRole(plantingId, "farmerOwner");
			res.setBody(result);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<Object>>(res, res.getHttpStatus());
		} catch (Exception ex) {
			res.setMessage(ex.getMessage());
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<Object>>(res, res.getHttpStatus());
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

			page -= 1;
			Date date = new Date(millisecondDate);
			List<Planting> plantings = plantingService.findByUsernameAndDate(userName, page, value, date);

			for (Planting planting : plantings) {
				ObjectNode responObject = mapper.createObjectNode();
				Field field = planting.getField();
				Subdistrict subdistrict = field.getSubdistrict();
				User user = userService.findByFieldIdAndRoleAndUser(field.getFieldId(), "farmerOwner", userName).get(0);
				responObject.put("plantingId", planting.getPlantingId());
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

	@GetMapping(value = "/createdate/{millisecondDate}")
	public ResponseEntity<Response<List<Planting>>> findByCreateDateAndUser(
			@PathVariable("millisecondDate") long millisecondDate) {
		Response<List<Planting>> res = new Response<>();
		try {
			Date date = new Date(millisecondDate);
			String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
			User user = userService.findByUsername(userName);
			List<Planting> result = plantingService.findByCreateDateAndUser(date, user);
			res.setBody(result);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<List<Planting>>>(res, res.getHttpStatus());
		} catch (Exception ex) {
			res.setMessage(ex.getMessage());
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<List<Planting>>>(res, res.getHttpStatus());
		}
	}

	@PostMapping(value = "/search/page/{page}/value/{value}/date/{milliseconddate}")
	public ResponseEntity<Response<List<?>>> searchByKeyPagination(@RequestBody PlantingSearchDTO searchDTO,
			@PathVariable("page") int page, @PathVariable("value") int value,
			@PathVariable("milliseconddate") long milliseconddate) {
		String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
		ObjectMapper mapper = new ObjectMapper();
		List<ObjectNode> responseList = new ArrayList<>();
		Response<List<?>> res = new Response();
		try {
			Date date = new Date(milliseconddate);
			User user = userService.findByUsername(userName);
			List<Planting> plantings = plantingService.searchPagination(searchDTO, user, page, value, date);

			for (Planting planting : plantings) {
				ObjectNode responObject = mapper.createObjectNode();
				Field field = planting.getField();
				Subdistrict subdistrict = field.getSubdistrict();
				User user2 = userService.findByFieldIdAndRoleAndUser(field.getFieldId(), "farmerOwner", userName)
						.get(0);
				responObject.put("plantingId", planting.getPlantingId());
				responObject.put("plantingName", planting.getName());
				responObject.put("fieldName", field.getName());
				responObject.put("substrict", subdistrict.getName());
				responObject.put("district", subdistrict.getDistrict().getName());
				responObject.put("province", subdistrict.getDistrict().getProvince().getName());
				responObject.put("title", user2.getTitle());
				responObject.put("firstName", user2.getFirstName());
				responObject.put("lastName", user2.getLastName());
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

	@GetMapping(value = "/check/field/{fieldid}/planting/{plantingid}/code/{code}")
	public ResponseEntity<Response<Integer>> checkCode(@PathVariable("fieldid") int fieldid,
			@PathVariable("plantingid") int plantingid, @PathVariable("code") String code) {
		Response<Integer> res = new Response<>();
		try {
			int check = 0;
			Integer plantingIn = plantingService.findByOrganizationAndCodeAndPlantingId(
					fieldService.findById(fieldid).getOrganization().getOrganizationId(), code,plantingid);
			if (plantingIn != null && plantingIn > 0) {
				check = 1;
			}
			res.setBody(check);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<Integer>>(res, res.getHttpStatus());
		} catch (Exception ex) {
			res.setMessage(ex.getMessage());
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<Integer>>(res, res.getHttpStatus());
		}
	}

	@PostMapping(value = "/searchbykey/page/{page}/value/{value}/date/{milliseconddate}")
	public ResponseEntity<Response<List<?>>> search(@RequestBody String key, @PathVariable("page") int page,
			@PathVariable("value") int value, @PathVariable("milliseconddate") long milliseconddate) {
		String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
		ObjectMapper mapper = new ObjectMapper();
		List<ObjectNode> responseList = new ArrayList<>();
		Response<List<?>> res = new Response();
		try {
			Date date = new Date(milliseconddate);
			User user = userService.findByUsername(userName);
			JsonNode jsonNode = mapper.readTree(key);
			String skey = jsonNode.get("key").asText();
			System.out.println(skey);
			List<Planting> plantings = plantingService.findByKey(skey, user, page, value, date);

			for (Planting planting : plantings) {
				ObjectNode responObject = mapper.createObjectNode();
				Field field = planting.getField();
				Subdistrict subdistrict = field.getSubdistrict();
				User user2 = userService.findByFieldIdAndRoleAndUser(field.getFieldId(), "farmerOwner", userName)
						.get(0);
				responObject.put("plantingId", planting.getPlantingId());
				responObject.put("plantingName", planting.getName());
				responObject.put("fieldName", field.getName());
				responObject.put("substrict", subdistrict.getName());
				responObject.put("district", subdistrict.getDistrict().getName());
				responObject.put("province", subdistrict.getDistrict().getProvince().getName());
				responObject.put("title", user2.getTitle());
				responObject.put("firstName", user2.getFirstName());
				responObject.put("lastName", user2.getLastName());
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

	@GetMapping(value = "/countplanting")
	public ResponseEntity<Response<Integer>> countByUserInField() {
		Response<Integer> res = new Response<>();
		try {
			String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
			User user = userService.findByUsername(userName);
			Integer count = plantingService.countByUserInField(user);
			res.setBody(count);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<Integer>>(res, res.getHttpStatus());
		} catch (Exception ex) {
			res.setMessage(ex.getMessage());
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<Integer>>(res, res.getHttpStatus());
		}

	}

	@GetMapping(value = "/field/{fieldid}/page/{page}/value/{value}")
	public ResponseEntity<Response<List<?>>> findByFieldId(@PathVariable("fieldid") int fieldid,
			@PathVariable("page") int page, @PathVariable("value") int value) {
		ObjectMapper mapper = new ObjectMapper();
		List<ObjectNode> responseList = new ArrayList<>();
		Response<List<?>> res = new Response();
		try {
			String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
			User user = userService.findByUsername(userName);
			List<Planting> plantings = plantingService.findByFieldIdAndUser(fieldid, user.getUsername(), page, value);
			
			for (Planting planting : plantings) {
				ObjectNode responObject = mapper.createObjectNode();
				Field field = planting.getField();
				Subdistrict subdistrict = field.getSubdistrict();
				User userOwner = userService.findByFieldIdAndRoleAndUser(field.getFieldId(), "farmerOwner", userName).get(0);
				responObject.put("plantingId", planting.getPlantingId());
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
	
	@GetMapping(value = "/countplanting/fieldid/{fieldid}")
	public ResponseEntity<Response<Integer>> countByUserInFieldAndFieldId (@PathVariable("fieldid")int fieldid) {
		Response<Integer> res = new Response<>();
		try {
			String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
			User user = userService.findByUsername(userName);
			Integer count = plantingService.countByUserInFieldAndFieldId(user, fieldid);
			res.setBody(count);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<Integer>>(res, res.getHttpStatus());
		} catch (Exception ex) {
			res.setMessage(ex.getMessage());
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<Integer>>(res, res.getHttpStatus());
		}

	}

}
