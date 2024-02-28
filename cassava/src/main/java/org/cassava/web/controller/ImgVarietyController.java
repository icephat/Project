package org.cassava.web.controller;

import java.io.IOException;

import org.cassava.model.ImgVariety;
import org.cassava.model.Variety;
import org.cassava.services.ImgVarietyService;
import org.cassava.services.VarietyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ImgVarietyController {

	@Autowired
	private ImgVarietyService imgVarietyService;

	@Autowired
	private VarietyService varietyService;

	@RequestMapping(value = "/imgvariety/", method = RequestMethod.POST)
	@ResponseBody
	public String createImgVariety(@RequestParam("targetId") String id, @RequestParam("file") MultipartFile file) {

		Variety variety = varietyService.findById(Integer.parseInt(id));
		String fileName;
		System.out.println(variety);
		try {
			
			fileName = varietyService.writeFile(file);
			ImgVariety img = new ImgVariety(variety, fileName);
			imgVarietyService.save(img);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "";
	}

}