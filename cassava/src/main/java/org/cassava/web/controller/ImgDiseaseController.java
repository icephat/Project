package org.cassava.web.controller;

import java.io.IOException;

import org.cassava.model.Disease;
import org.cassava.model.ImgDisease;
import org.cassava.services.DiseaseService;
import org.cassava.services.ImgDiseaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ImgDiseaseController {

	@Autowired
	private ImgDiseaseService ImgdiseaseService;

	@Autowired
	private DiseaseService diseaseService;

	@RequestMapping(value = "/imgdisease/", method = RequestMethod.POST)
	@ResponseBody
	public String createImgDisease(@RequestParam("targetId") String id, @RequestParam("file") MultipartFile file) {

		Disease disease = diseaseService.findById(Integer.parseInt(id));
		System.out.println(file.getName());
		
		String fileName;
		
		try {
			
			fileName = diseaseService.writeFile(file);
			ImgDisease img = new ImgDisease(disease, fileName);
			ImgdiseaseService.save(img);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "";
	}

}
