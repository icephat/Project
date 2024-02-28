package org.cassava.api.controller;

import java.util.List;

import org.cassava.api.util.model.Response;

import org.cassava.model.Subdistrict;
import org.cassava.services.FieldService;
import org.cassava.services.SubdistrictService;
import org.cassava.util.MvcHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/subistrict")
public class SubdistrictRestController {
	@Autowired
	SubdistrictService subdistrictService;

	@Autowired
	FieldService fieldService;

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

	@GetMapping("/page/{page}/value/{value}")
	public ResponseEntity<Response<List<Subdistrict>>> findAll(@PathVariable int page, @PathVariable int value) {
		Response<List<Subdistrict>> res = new Response();
		try {
			String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
			List<Subdistrict> subdistricts = subdistrictService.findAllByUserinField(userName, page, value);
			res.setBody(subdistricts);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<List<Subdistrict>>>(res, res.getHttpStatus());
		} catch (Exception ex) {
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<List<Subdistrict>>>(res, res.getHttpStatus());
		}
	}

	@GetMapping("/userinfield/{userid}/page/{page}/value/{value}")
	public ResponseEntity<Response<List<Subdistrict>>> findByUserinField(@PathVariable int userid,
			@PathVariable int page, @PathVariable int value) {
		Response<List<Subdistrict>> res = new Response<>();
		try {
			String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
			List<Subdistrict> subdistricts = subdistrictService.findByUserinField(userid, userName, page, value);
			res.setBody(subdistricts);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<List<Subdistrict>>>(res, res.getHttpStatus());
		} catch (Exception ex) {
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<List<Subdistrict>>>(res, res.getHttpStatus());
		}
	}

	@GetMapping("/")
	public ResponseEntity<Response<List<Subdistrict>>> findAll() {
		Response<List<Subdistrict>> res = new Response<>();
		try {
			List<Subdistrict> subdistricts = subdistrictService.findAll();
			res.setBody(subdistricts);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<List<Subdistrict>>>(res, res.getHttpStatus());
		} catch (Exception ex) {
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<List<Subdistrict>>>(res, res.getHttpStatus());
		}
	}
}
