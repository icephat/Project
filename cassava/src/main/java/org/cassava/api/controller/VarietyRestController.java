package org.cassava.api.controller;


import java.util.List;

import org.cassava.api.util.model.Response;
import org.cassava.model.Variety;
import org.cassava.services.VarietyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@RequestMapping(value = "/api/variety")
public class VarietyRestController {
	@Autowired
	private VarietyService varietyService;
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Response<ObjectNode>> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Response<ObjectNode> res = new Response<>();
		res.setHttpStatus(HttpStatus.BAD_REQUEST);

		ObjectMapper mapper = new ObjectMapper();

		ObjectNode responObject = mapper.createObjectNode();

		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldname = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			responObject.put(fieldname, errorMessage);
		});
		res.setMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
		res.setBody(responObject);
		return new ResponseEntity<Response<ObjectNode>>(res, res.getHttpStatus());
	}
	
	@GetMapping(value = "/")
	public ResponseEntity<Response<List<Variety>>> findAll () {
		Response<List<Variety>> res = new Response<>();
		try {
			List<Variety> varieties = varietyService.findAll();
			res.setBody(varieties);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<List<Variety>>>(res, res.getHttpStatus());
		} catch (Exception ex) {
			res.setMessage(ex.getLocalizedMessage());
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<List<Variety>>>(res, res.getHttpStatus());
		}
	}

}
