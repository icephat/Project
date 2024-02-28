package org.cassava.security;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class CustomInvalidSessiondStrategy implements InvalidSessionStrategy {

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	private String expiredUrl;

	@Override
	public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		Cookie[] cookies = request.getCookies();

		if (cookies != null)
			for (Cookie cookie : cookies) {

				cookie.setValue("");
				cookie.setPath("/");
				cookie.setMaxAge(0);
				response.addCookie(cookie);

			}

		request.getSession();

		String uri = request.getRequestURL().toString();

		redirectStrategy.sendRedirect(request, response,
				"https://www.google.com/accounts/Logout?continue=https://appengine.google.com/_ah/logout?continue="
						+ uri);

	}

}