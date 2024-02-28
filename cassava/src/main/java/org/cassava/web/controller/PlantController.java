package org.cassava.web.controller;

import org.cassava.services.PlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PlantController {
	
	@Autowired
	private PlantService plantService;
	
	//@GetMapping
	@RequestMapping(value = "/plant/Index", method = RequestMethod.GET)
	public String index(Model model) {
		return "plant/index";
	}

}
