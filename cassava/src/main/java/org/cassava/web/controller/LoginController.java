package org.cassava.web.controller;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Controller
public class LoginController {

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutSuccessfulPage(HttpServletRequest request, HttpServletResponse response, Model model,
			Authentication authentication) throws IOException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		final String baseUrl = 
				ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();

		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}

		Cookie[] cookies = request.getCookies();

		if (cookies != null)
			for (Cookie cookie : cookies) {

				cookie.setValue("");
				cookie.setPath("/");
				cookie.setMaxAge(0);
				response.addCookie(cookie);

			}

		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}

		model.addAttribute("title", "Logout");

		//if (authentication instanceof OAuth2AuthenticationToken) {

			//DefaultOidcUser oidcUser = (DefaultOidcUser) authentication.getPrincipal();

			//List<String> clients = (List) (oidcUser.getAttributes().get("aud"));

			//String clientId = clients.get(0);
			request.getSession();
			return "redirect:"
					+ "https://www.google.com/accounts/Logout?continue=https://appengine.google.com/_ah/logout?continue="+baseUrl;

		//}

		//return "redirect:logoutSuccessfulPage";

	}
}
