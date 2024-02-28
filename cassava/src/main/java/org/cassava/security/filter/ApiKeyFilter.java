package org.cassava.security.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ResponseStatusException;

@PropertySource("classpath:/application-oauth2.properties")
public class ApiKeyFilter extends OncePerRequestFilter {
	
	@Value("${flutter.api.key}")
	private String key ;

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws ServletException, IOException {

		String apiKeyHeader = req.getHeader("API_KEY");
		
		//System.out.println(key);

		if (apiKeyHeader == null || apiKeyHeader.isBlank() || !apiKeyHeader.equals(key))
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
					"You cannot consume this service. Please check your api key.");
		chain.doFilter(req, res);
		

	}

}
