package org.cassava.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FrontEndController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String loginIndex(Model model) {
		
		return "/frontend/index";
	}
	@RequestMapping(value = "/varietyvue", method = RequestMethod.GET)
	public String varietyvue(Model model) {
		
		return "/frontend/index";
	}
	@RequestMapping(value = "/diseasevue", method = RequestMethod.GET)
	public String diseasevue(Model model) {
		
		return "/frontend/index";
	}
	@RequestMapping(value = "/pestvue", method = RequestMethod.GET)
	public String pestvue(Model model) {
		
		return "/frontend/index";
	}
	@RequestMapping(value = "/naturalenemyvue", method = RequestMethod.GET)
	public String naturalenemyvue(Model model) {
		
		return "/frontend/index";
	}

}