package org.cassava.web.controller;

import org.cassava.model.District;
import org.cassava.services.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DistrictController {

	@Autowired
	private DistrictService districtService;
	
	@RequestMapping(value = "/district/{id}", method = RequestMethod.GET)	
	public String findByID(Model model, @PathVariable("id") int id) {
		District district = districtService.findById(id);
		model.addAttribute("district", district);	
		return "/district/districtSearch";
	}
	
	@RequestMapping(value = "/district/selectpicker/{id}", method = RequestMethod.GET)	
	public String searchMultipleByID(Model model, @PathVariable("id") int id) {
		District district = districtService.findById(id);	
		model.addAttribute("district", district);	
		return "/district/districtSearchSelectpicker";
	}
	
}
