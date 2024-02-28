package org.cassava.web.controller;

import java.io.IOException;

import org.cassava.model.ImgNaturalEnemy;
import org.cassava.model.NaturalEnemy;
import org.cassava.services.ImgNaturalEnemyService;
import org.cassava.services.NaturalEnemyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ImgNaturalEnemyController {

	@Autowired
	private ImgNaturalEnemyService imgNaturalEnemyService;

	@Autowired
	private NaturalEnemyService naturalenemyService;

	@RequestMapping(value = "/imgnaturalenemy/", method = RequestMethod.POST)
	@ResponseBody
	public String createImgNaturalEnemy(@RequestParam("targetId") String id, @RequestParam("file") MultipartFile file) {
		System.out.println(id);
		NaturalEnemy naturalenemy = naturalenemyService.findById(Integer.parseInt(id));
		String fileName;

		try {
			
			fileName = naturalenemyService.writeFile(file);
			ImgNaturalEnemy img = new ImgNaturalEnemy(naturalenemy, fileName);
			imgNaturalEnemyService.save(img);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "";
	}

}
