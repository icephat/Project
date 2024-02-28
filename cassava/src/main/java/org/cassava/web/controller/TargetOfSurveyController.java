package org.cassava.web.controller;

import org.cassava.api.util.model.Response;
import org.cassava.model.Disease;
import org.cassava.model.Plant;
import org.cassava.model.TargetOfSurvey;
import org.cassava.services.PlantService;
import org.cassava.services.TargetOfSurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TargetOfSurveyController {

	@Autowired
	private TargetOfSurveyService service;

	@Autowired
	private PlantService plantService;

	@RequestMapping(value = "/targetofsurvey/ajax", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Response<String>> createTargetOfSurvey(
			@ModelAttribute("targetOfSurvey") TargetOfSurvey target, Model model) {

		TargetOfSurvey dbTarget = service.findByName(target.getName());

		Response<String> resp = new Response<String>();
		resp.setHttpStatus(HttpStatus.OK);

		if (dbTarget != null) {
			resp.setMessage("Duplicated Name");
			resp.setHttpStatus(HttpStatus.NOT_ACCEPTABLE);
			return new ResponseEntity<Response<String>>(resp, resp.getHttpStatus());
		}
		if (target.getNaturalenemy() != null) {
			target.setName(target.getName().trim());
			target.getNaturalenemy().setCommonName(target.getNaturalenemy().getCommonName().trim());
			target.getNaturalenemy().setScientificName(target.getNaturalenemy().getScientificName().trim());
			target.getNaturalenemy().setType(target.getNaturalenemy().getType().trim());
			target.getNaturalenemy().setControlMethod(target.getNaturalenemy().getControlMethod().trim());
			target.getNaturalenemy().setReleaseRate(target.getNaturalenemy().getReleaseRate().trim());
			target.getNaturalenemy().setSource(target.getNaturalenemy().getSource().trim());
			target.getNaturalenemy().setTargetofsurvey(target);
			//System.out.println("check tar");
		}
		else if (target.getDisease() != null) {
			
			target.getDisease().setCode(target.getDisease().getCode().trim());
			target.getDisease().setSymptom(target.getDisease().getSymptom().trim());
			target.getDisease().setControlDisease(target.getDisease().getControlDisease().trim());
			target.getDisease().setControlPest(target.getDisease().getControlPest().trim());
			target.getDisease().setSource(target.getDisease().getSource().trim());
			
			target.getDisease().setTargetofsurvey(target);
			Plant p = plantService.findById(1);
			Disease d = target.getDisease();
			d.setPlant(p);
		}

		else if (target.getPestphasesurvey() != null)
			target.getPestphasesurvey().setTargetofsurvey(target);
		target.setName(target.getName().trim());
		TargetOfSurvey dbTs = service.save(target);
		int id = dbTs.getTargetOfSurveyId();
		resp.setBody(id + "");

		return new ResponseEntity<Response<String>>(resp, resp.getHttpStatus());

		// ret
	}
}
