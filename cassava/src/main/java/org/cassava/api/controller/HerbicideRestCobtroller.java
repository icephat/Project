package org.cassava.api.controller;

import org.apache.catalina.User;
import org.cassava.api.util.model.Response;
import org.cassava.model.Field;
import org.cassava.model.Herbicide;
import org.cassava.services.HerbicideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/herbicide")
public class HerbicideRestCobtroller {
	@Autowired
	HerbicideService herbicideService;
	
	@GetMapping("/{id}")
	public ResponseEntity<Response<Herbicide>> findHerbicideById(@PathVariable int id){
		Response<Herbicide> res = new Response<>();
		try {
			Herbicide herbicide = herbicideService.findById(id);
			res.setBody(herbicide);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<Herbicide>>(res, res.getHttpStatus());
		}catch (Exception e) {
			res.setBody(null);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<Herbicide>>(res, res.getHttpStatus());
		}
		
		
	}
}
