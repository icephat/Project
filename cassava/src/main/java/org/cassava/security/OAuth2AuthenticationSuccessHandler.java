package org.cassava.security;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.cassava.services.UserService;
import org.cassava.util.ImageBase64Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistration.ProviderDetails;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@PropertySource(value = "classpath:/application-oauth2.properties")
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	// @Value("#{${clientsMap}}")
	// private Map<String, String> oauthClients;

	// @Value("#{${clientsLogoutMap}}")
	// private Map<String, String> clientsLogoutMap;

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Autowired
	private UserService userAccountService;

	private String userName;

	@Autowired
	private ActiveUserStore activeUserStore;

	// @Resource(name = "userService")
	// private UserDetailsService userDetailsService;

	@Autowired
	private ClientRegistrationRepository clientRegistrationRepository;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		/*
		 * Iterator<String> itor =
		 * request.getSession().getAttributeNames().asIterator(); while (itor.hasNext())
		 * { System.out.println("----------------------------"+request.getSession().
		 * getAttribute(itor.next()));
		 * 
		 * }
		 */
		if (response.isCommitted()) {
			return;
		}
		DefaultOidcUser oidcUser = (DefaultOidcUser) authentication.getPrincipal();
		Map attributes = oidcUser.getAttributes();

		handle(request, response, authentication);
		clearAuthenticationAttributes(request, authentication);
	}

	protected String determineTargetUrl(final Authentication authentication, DefaultSavedRequest defaultSavedRequest) {

		Map<String, String> roleTargetUrlMap = new HashMap<>();
		roleTargetUrlMap.put("ROLE_Unknown", "/user/register");
		// roleTargetUrlMap.put("ROLE_Admin", "/admin/index");

		DefaultOidcUser oidcUser = (DefaultOidcUser) authentication.getPrincipal();

		userName = (String) oidcUser.getAttributes().get("email");

		org.cassava.model.User user = userAccountService.findByUsername(userName);

		// check user

		if (user == null)
			// return
			// "https://www.google.com/accounts/Logout?continue=https://appengine.google.com/_ah/logout?continue=http://localhost:8080/cassava/";
			return "/register/code";
		if (user.getUserStatus().equalsIgnoreCase("invalid")) {
			return "/";
		} else if (user.getUserStatus().equalsIgnoreCase("Waiting")) {

			return "/register/code";

		} else if (user.getUserStatus().equalsIgnoreCase("Inactive")) {

			return "/inactive";

		}
		
		user.setLatestLogin(new Date());
		user = userAccountService.save(user);

		if (defaultSavedRequest == null) {
			return "/home";
		}
		return defaultSavedRequest.getRequestURL();

	}

	protected void handle(final HttpServletRequest request, final HttpServletResponse response,
			final Authentication authentication) throws IOException {

		DefaultSavedRequest defaultSavedRequest = (DefaultSavedRequest) request.getSession()
				.getAttribute("SPRING_SECURITY_SAVED_REQUEST");
		final String targetUrl = determineTargetUrl(authentication, defaultSavedRequest);

		if (response.isCommitted()) {
			logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
			return;
		}

		redirectStrategy.sendRedirect(request, response, targetUrl);
	}

	protected final void clearAuthenticationAttributes(final HttpServletRequest request,
			Authentication authentication) {
		final HttpSession session = request.getSession(false);

		if (session == null) {

			return;
		}
		DefaultOidcUser oidcUser = (DefaultOidcUser) authentication.getPrincipal();

		org.cassava.model.User userAccount = userAccountService.findByUsername(userName);

		String targetUrl = null;

		if (userAccount == null) {
			// session.invalidate();

			return;
		}

		LoggedUser user = new LoggedUser(userAccount.getUserId(), userName, userAccount.getFirstName(),
				userAccount.getLastName(), activeUserStore);
		session.setAttribute("loggedUser", user);
		session.setAttribute("loggedUserPicture", oidcUser.getPicture());
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}

}
