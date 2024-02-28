package org.cassava.api.controller;

import java.util.List;

import org.cassava.api.util.model.Response;
import org.cassava.model.PlantingCassavaVariety;
import org.cassava.model.Variety;
import org.cassava.services.PlantingCassavaVarietyService;
import org.cassava.services.VarietyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@RequestMapping(value="/api/plantingcassavavariety")
public class PlantingCassavaVarietyRestController {
	@Autowired
	private PlantingCassavaVarietyService cassavaVarietyService;
	@Autowired
	private VarietyService varietyService;
	
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
	
	@GetMapping(value = "/planting/{plantingId}")
	public ResponseEntity<Response<List<Variety>>> findVarietyByPlantingId(@PathVariable("plantingId")int plantingId){
		Response<List<Variety>> res = new Response<>();
		try {
			List<Variety> varieties = varietyService.findByPlantingId(plantingId);
			res.setBody(varieties);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<List<Variety>>>(res,res.getHttpStatus());
		} catch(Exception ex){
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<List<Variety>>>(res,res.getHttpStatus());
		}
	}
	
	@DeleteMapping(value = "/{plantingId}")
	public ResponseEntity<Response<String>> deletePlantingById(@PathVariable("plantingId")int plantingId,@RequestBody Variety[] varietyId){
		Response<String> res = new Response<>();
		try {
			List<PlantingCassavaVariety> cassavaVarieties = cassavaVarietyService.findByPlantingId(plantingId);
			for(Variety id:varietyId) {
				Variety variety = varietyService.findById(id.getVarietyId());
				for(PlantingCassavaVariety cassavaVariety:cassavaVarieties) {
					if(cassavaVariety.getVariety().getVarietyId() == variety.getVarietyId()) {
						cassavaVarietyService.deleteByPlantingCassavaVariety(cassavaVariety);
					}
				}
			}
			res.setMessage("Delete successfully.");
			res.setBody("");
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<String>>(res,res.getHttpStatus());
		}catch(Exception ex) {
			res.setMessage("Result not found");
			res.setBody(ex.toString());
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<String>>(res,res.getHttpStatus());
			
		}
	}
	
}
