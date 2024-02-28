package org.cassava.api.controller;

import java.util.List;

import org.cassava.api.util.model.Response;
import org.cassava.model.District;
import org.cassava.model.Province;
import org.cassava.model.Subdistrict;
import org.cassava.services.DiseaseService;
import org.cassava.services.DistrictService;
import org.cassava.services.SubdistrictService;
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
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@RequestMapping("/api/districts")
public class DistrictRestController {
	
	
	@Autowired
	private SubdistrictService subDistrictService;
	@Autowired
	private DistrictService districtService;
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response<ObjectNode>> handleValidationExceptions(MethodArgumentNotValidException ex){
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
        return new ResponseEntity<Response<ObjectNode>>(res,res.getHttpStatus());
    }
	
	@GetMapping("/{id}/subdistricts")
	public ResponseEntity<Response<List<Subdistrict>>> findByDistrictId(@PathVariable int id) {
		Response<List<Subdistrict>> res = new Response();
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getName();
			List<Subdistrict> subdistricts = subDistrictService.findByDistrictId(id);
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
	public ResponseEntity<Response<List<District>>> findAll() {
		Response<List<District>> res = new Response();
		try {
			//String userName = SecurityContextHolder.getContext().getAuthentication().getName();
			List<District> districts = districtService.findAll();
			res.setBody(districts);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<List<District>>>(res, res.getHttpStatus());
		} catch (Exception ex) {
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<List<District>>>(res, res.getHttpStatus());
		}
	}
}
