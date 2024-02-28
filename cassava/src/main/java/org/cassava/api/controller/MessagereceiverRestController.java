package org.cassava.api.controller;

import java.util.List;

import org.cassava.api.util.model.Response;
import org.cassava.model.Field;
import org.cassava.model.Message;
import org.cassava.model.Messagereceiver;
import org.cassava.model.MessagereceiverId;
import org.cassava.model.User;
import org.cassava.model.dto.MessageAndReceiverDTO;
import org.cassava.model.dto.MessageReceiverDTO;
import org.cassava.services.MessageService;
import org.cassava.services.MessagereceiverService;
import org.cassava.services.PlantingCassavaVarietyService;
import org.cassava.services.UserService;
import org.cassava.services.VarietyService;
import org.cassava.util.MvcHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@RequestMapping(value = "/api/messagereceiver")
public class MessagereceiverRestController {

	@Autowired
	private MessagereceiverService messagereceiverService;

	@Autowired
	private UserService userService;

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
	public ResponseEntity<Response<List<MessageAndReceiverDTO>>> findMessageAndReceiverDTOByUserId() {

		Response<List<MessageAndReceiverDTO>> res = new Response<>();
		try {
			String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
			List<MessageAndReceiverDTO> messageReceiverDTOs = messagereceiverService
					.findMessageAndReceiverDTOByUserId(userName);
			res.setBody(messageReceiverDTOs);
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<List<MessageAndReceiverDTO>>>(res, res.getHttpStatus());

		} catch (Exception ex) {
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<List<MessageAndReceiverDTO>>>(res, res.getHttpStatus());
		}
	}

	@PostMapping(value = "/messages/{id}")
	public ResponseEntity<Response<String>> markReadMessage(@PathVariable int id) {

		Response<String> res = new Response<>();

		try {
			String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());

			User user = userService.findByUsername(userName);

			Messagereceiver messagereceiver = messagereceiverService.findByUserIdAndMessageId(user.getUserId(), id);

			messagereceiver.setReadStatus("Y");

			messagereceiverService.save(messagereceiver);

			res.setMessage("marked message id = " + id);

			res.setHttpStatus(HttpStatus.OK);

			return new ResponseEntity<Response<String>>(res, res.getHttpStatus());

		} catch (Exception ex) {
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<String>>(res, res.getHttpStatus());
		}
	}

	@DeleteMapping("/")
	public ResponseEntity<Response<String>> deleteMessage(@RequestBody MessagereceiverId messagereceiverId) {
		Response<String> res = new Response<>();
		try {
			String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());

			User user = userService.findByUsername(userName);

			Messagereceiver messagereceiver = messagereceiverService.findByUserIdAndMessageId(user.getUserId(),
					messagereceiverId.getMessageId());

			messagereceiverService.delete(messagereceiver);

			res.setBody("message id: " + messagereceiver.getId().getMessageId() + " deleted");

			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<String>>(res, res.getHttpStatus());

		} catch (Exception ex) {
			res.setBody(ex.getMessage());
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<String>>(res, res.getHttpStatus());
		}
	}

	@GetMapping(value = "/countmessage")
	public ResponseEntity<Response<Integer>> countMessage() {

		Response<Integer> res = new Response<>();
		try {
			String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
			User user = userService.findByUsername(userName);
			List<Messagereceiver> messageReceiverDTOs = messagereceiverService
					.findByUserIdAndReadStatus(user.getUserId(), "N");
			res.setBody(messageReceiverDTOs.size());
			res.setHttpStatus(HttpStatus.OK);
			return new ResponseEntity<Response<Integer>>(res, res.getHttpStatus());

		} catch (Exception ex) {
			res.setMessage(ex.getMessage());
			res.setBody(null);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Response<Integer>>(res, res.getHttpStatus());
		}
	}
}
