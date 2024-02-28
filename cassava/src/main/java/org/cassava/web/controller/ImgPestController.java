package org.cassava.web.controller;

import java.io.IOException;

import org.cassava.model.ImgPest;
import org.cassava.model.Pest;
import org.cassava.services.ImgPestService;
import org.cassava.services.PestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ImgPestController {

	@Autowired
	private ImgPestService imgPestService;

	@Autowired
	private PestService pestService;

	@RequestMapping(value = "/imgpest/", method = RequestMethod.POST)
	@ResponseBody
	public String createImgPest(@RequestParam("targetId") String id, @RequestParam("file") MultipartFile file) {

		Pest pest = pestService.findById(Integer.parseInt(id));
		String fileName;

		try {
			
			fileName = pestService.writeFile(file);
			ImgPest img = new ImgPest(pest, fileName);
			imgPestService.save(img);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "";
	}

}