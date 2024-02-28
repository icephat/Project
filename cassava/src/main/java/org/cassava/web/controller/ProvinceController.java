package org.cassava.web.controller;

import org.cassava.model.Province;
import org.cassava.services.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class ProvinceController {

	@Autowired
	private ProvinceService provinceService;

	@RequestMapping(value = "/province/{id}", method = RequestMethod.GET)
	
	public String findByID(Model model, @PathVariable("id") int id) {
		Province province = provinceService.findById(id);		
		model.addAttribute("province", province);	
		return "/province/provinceSearch";
	}

	@RequestMapping(value = "/province/code/{code}", method = RequestMethod.GET)
	public String findBycode(Model model, @PathVariable("code") String code) {
		Province province = provinceService.findByCode(code);
		model.addAttribute("province", province);
		return "/province/provinceSearch";
	}

}
