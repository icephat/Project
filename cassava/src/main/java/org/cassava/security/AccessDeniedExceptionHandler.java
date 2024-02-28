package org.cassava.security;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

public class AccessDeniedExceptionHandler implements AccessDeniedHandler {
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException ex)
			throws IOException, ServletException {
		response.setStatus(HttpStatus.SC_FORBIDDEN);
		response.getWriter().write(new JSONObject().put("timestamp", LocalDate.now())
				.put("message", "Access denied").toString());
	}
}