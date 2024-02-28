package org.cassava.security.filter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;

import org.cassava.model.User;
import org.cassava.services.UserService;
import org.cassava.util.TokenProvider;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@PropertySource(value = "classpath:/application.properties")
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Value("${jwt.header.string}")
	private String HEADER_STRING;

	@Value("${jwt.token.prefix}")
	public String TOKEN_PREFIX;

	@Autowired
	private UserService userService;

	@Autowired
	private TokenProvider jwtTokenUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
			throws IOException, ServletException {

		String header = req.getHeader(HEADER_STRING);

		boolean hasError = false;

		String username = null;

		String authToken = null;

		if (header != null && header.startsWith(TOKEN_PREFIX)) {

			authToken = header.replace(TOKEN_PREFIX, "");
			try {
				username = jwtTokenUtil.getUsernameFromToken(authToken);
			} catch (IllegalArgumentException e) {
				hasError = true;
				logger.error("An error occurred while fetching Username from Token", e);

				resp.setContentType(MediaType.APPLICATION_JSON_VALUE);
				resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				resp.getWriter().write(new JSONObject().put("timestamp", LocalDate.now())
						.put("message", "An error occurred while fetching Username from Token").toString());
			} catch (ExpiredJwtException e) {
				hasError = true;
				resp.setContentType(MediaType.APPLICATION_JSON_VALUE);
				resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				resp.getWriter().write(
						new JSONObject().put("timestamp", LocalDate.now()).put("message", e.getMessage()).toString());
				logger.warn("The token has expired", e);

			} catch (SignatureException e) {
				hasError = true;
				resp.setContentType(MediaType.APPLICATION_JSON_VALUE);
				resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				resp.getWriter().write(new JSONObject().put("timestamp", LocalDate.now())
						.put("message", "Authentication Failed. Username or Password not valid.").toString());
				// logger.warn("The token has expired", e);
				logger.error("Authentication Failed. Username or Password not valid.");
			}
		} else {
			// logger.warn("Couldn't find bearer string, header will be ignored");
		}

		if (!hasError && username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			User user = userService.findByUsername(username);

			if (user == null && jwtTokenUtil.validateToken(authToken, null)) {

				UsernamePasswordAuthenticationToken authentication = jwtTokenUtil.getAuthenticationToken(authToken,
						SecurityContextHolder.getContext().getAuthentication(), null);
				SecurityContextHolder.getContext().setAuthentication(authentication);

			} else if (jwtTokenUtil.validateToken(authToken, user)) {

				UsernamePasswordAuthenticationToken authentication = jwtTokenUtil.getAuthenticationToken(authToken,
						SecurityContextHolder.getContext().getAuthentication(), user);
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));

				// logger.info("authenticated user " + username + ", setting security context");
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}

		chain.doFilter(req, resp);
	}
}