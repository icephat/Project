package org.cassava.web.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;


@ControllerAdvice
public class ResourceErrorController implements ErrorController {


	@ExceptionHandler(NoHandlerFoundException.class)
	public String handleError(Exception ex,Model model) {
	   // Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
	    
	    
	            return "error/404";
	    
	}
	
}
