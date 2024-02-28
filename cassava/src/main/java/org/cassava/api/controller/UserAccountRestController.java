package org.cassava.api.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.cassava.api.util.model.Response;
import org.cassava.model.Refreshtoken;
import org.cassava.model.User;
import org.cassava.security.oauth2.model.AuthToken;
import org.cassava.security.oauth2.model.OAuthAuthenUser;
import org.cassava.security.oauth2.model.TokenRefreshRequest;
import org.cassava.services.RefreshTokenService;
import org.cassava.services.UserService;
import org.cassava.util.GoogleTokenVerification;
import org.cassava.util.MvcHelper;
import org.cassava.util.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/oauth")
public class UserAccountRestController {

	@Autowired
	private UserService userService;

	@Autowired
	private TokenProvider jwtTokenUtil;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private RefreshTokenService refreshTokenService;

	@PostMapping("/refreshtoken")
	public ResponseEntity<?> refreshToken(@Valid @RequestBody TokenRefreshRequest request) {

		String requestRefreshToken = request.getRefreshToken();
		Response<AuthToken> resp = new Response();

		resp.setMessage(requestRefreshToken + " Refresh token is not in database!");

		resp.setHttpStatus(HttpStatus.NOT_FOUND);

		return refreshTokenService.findByToken(requestRefreshToken).map(refreshTokenService::verifyExpiration)
				.map(Refreshtoken::getUser).map(user -> {
					Set<SimpleGrantedAuthority> authorities = new HashSet<>();

					final Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(),
							null, authorities);

					SecurityContextHolder.getContext().setAuthentication(authentication);
					User _user = new User();

					_user.setUsername(user.getUsername());

					String token = jwtTokenUtil.generateToken(authentication);

					resp.setBody(new AuthToken(_user, token, requestRefreshToken));

					resp.setMessage("Token refresh");

					resp.setHttpStatus(HttpStatus.OK);

					return new ResponseEntity<Response<AuthToken>>(resp, resp.getHttpStatus());

				}).orElse(new ResponseEntity<Response<AuthToken>>(resp, resp.getHttpStatus()));
	}

	@RequestMapping(value = "/google/access_token", method = RequestMethod.POST)
	public ResponseEntity<Response<AuthToken>> generateTokenWithGoogleAccessToken(@RequestBody OAuthAuthenUser oUser)
			throws AuthenticationException {

		String access_token = oUser.getToken();

		Set<SimpleGrantedAuthority> authorities = new HashSet<>();

		Response<AuthToken> resp = new Response();
		
		resp.setHttpStatus(HttpStatus.BAD_REQUEST);

		if (GoogleTokenVerification.verify(access_token)) {
			User user = userService.findByUserNameAndStatus(oUser.getUsername(), "active");

			if (user == null) {
				authorities.add(new SimpleGrantedAuthority("ROLE_Anonymous"));
				resp.setHttpStatus(HttpStatus.OK);
				final Authentication authentication = new UsernamePasswordAuthenticationToken(oUser.getUsername(), null,
						authorities);

				SecurityContextHolder.getContext().setAuthentication(authentication);
				User _user = new User();

				_user.setUsername(oUser.getUsername());

				final String token = jwtTokenUtil.generateToken(authentication);

				resp.setBody(new AuthToken(_user, token));

				resp.setMessage("Anonymous User");

				return new ResponseEntity<Response<AuthToken>>(resp, resp.getHttpStatus());
			}

			authorities.add(new SimpleGrantedAuthority("ROLE_User"));
			final Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(), null,
					authorities);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			User _user = new User();

			_user.setUserId(user.getUserId());

			_user.setUsername(user.getUsername());

			_user.setFirstName(user.getFirstName());

			_user.setLastName(user.getLastName());

			final String token = jwtTokenUtil.generateToken(authentication);

			final Refreshtoken refreshToken = refreshTokenService.createRefreshToken(user);

			resp.setBody(new AuthToken(_user, token, refreshToken.getToken()));

			resp.setHttpStatus(HttpStatus.OK);

			resp.setMessage("OK");

		}

		return new ResponseEntity<Response<AuthToken>>(resp, resp.getHttpStatus());

	}

	@RequestMapping(value = "/google", method = RequestMethod.POST)
	public ResponseEntity<Response<AuthToken>> generateToken(@RequestBody OAuthAuthenUser oUser)
			throws AuthenticationException {

		String access_token = oUser.getToken();

		Set<SimpleGrantedAuthority> authorities = new HashSet<>();

		Response<AuthToken> resp = new Response();

		// if (GoogleTokenVerification.verify(access_token)) {
		User user = userService.findByUserNameAndStatus(oUser.getUsername(), "active");

		if (user == null) {
			authorities.add(new SimpleGrantedAuthority("ROLE_Anonymous"));
			resp.setHttpStatus(HttpStatus.OK);
			final Authentication authentication = new UsernamePasswordAuthenticationToken(oUser.getUsername(), null,
					authorities);

			SecurityContextHolder.getContext().setAuthentication(authentication);
			User _user = new User();

			_user.setUsername(oUser.getUsername());

			final String token = jwtTokenUtil.generateToken(authentication);

			resp.setBody(new AuthToken(_user, token));

			resp.setMessage("Anonymous User");

			return new ResponseEntity<Response<AuthToken>>(resp, resp.getHttpStatus());
		}

		authorities.add(new SimpleGrantedAuthority("ROLE_User"));
		final Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(), null,
				authorities);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		User _user = new User();

		_user.setUserId(user.getUserId());

		_user.setUsername(user.getUsername());

		_user.setFirstName(user.getFirstName());

		_user.setLastName(user.getLastName());

		final String token = jwtTokenUtil.generateToken(authentication);

		final Refreshtoken refreshToken = refreshTokenService.createRefreshToken(user);

		resp.setBody(new AuthToken(_user, token, refreshToken.getToken()));

		resp.setHttpStatus(HttpStatus.OK);

		resp.setMessage("OK");

		return new ResponseEntity<Response<AuthToken>>(resp, resp.getHttpStatus());

	}

	@PostMapping("/signout")
	public ResponseEntity<?> logoutUser() {
		String userName = MvcHelper.getUsername(SecurityContextHolder.getContext().getAuthentication());
		User userDetails = userService.findByUsername(userName);
		int userId = userDetails.getUserId();
		refreshTokenService.deleteByUserId(userId);
		return ResponseEntity.ok("");
	}

}
