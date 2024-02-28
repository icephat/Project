package org.cassava.security;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.cassava.model.User;
import org.cassava.services.UserService;
import org.cassava.util.ImageBase64Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class MultiRoleUrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Autowired
	ActiveUserStore activeUserStore;

	@Value("${extern.resoures.path}")
	private String externalPath;

	@Autowired
	UserService userService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		handle(request, response, authentication);
		clearAuthenticationAttributes(request, authentication);
	}

	protected void handle(final HttpServletRequest request, final HttpServletResponse response,
			final Authentication authentication) throws IOException {
		final String targetUrl = determineTargetUrl(authentication);

		if (response.isCommitted()) {
			// logger.debug("Response has already been committed. Unable to redirect to " +
			// targetUrl);
			return;
		}

		redirectStrategy.sendRedirect(request, response, targetUrl);
	}

	protected String determineTargetUrl(final Authentication authentication) {

		Map<String, String> roleTargetUrlMap = new HashMap<>();
		roleTargetUrlMap.put("ROLE_User", "/rapid/index");
		roleTargetUrlMap.put("ROLE_Admin", "/rapid/index");

		final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		for (final GrantedAuthority grantedAuthority : authorities) {

			String authorityName = grantedAuthority.getAuthority();
			if (roleTargetUrlMap.containsKey(authorityName)) {
				return roleTargetUrlMap.get(authorityName);
			}
		}

		throw new IllegalStateException();
	}

	/**
	 * Removes temporary authentication-related data which may have been stored in
	 * the session during the authentication process.
	 */
	protected final void clearAuthenticationAttributes(final HttpServletRequest request,
			Authentication authentication) {
		final HttpSession session = request.getSession(false);

		if (session == null) {
			return;
		}

		User user = userService.findByUsername(authentication.getName());

		LoggedUser loggedUser = new LoggedUser(user.getUserId(), authentication.getName(), user.getFirstName(),
				user.getLastName(), activeUserStore);
		
		session.setAttribute("loggedUser", user);
		/*session.setAttribute("loggedUserPicture",
				ImageBase64Helper.toImageBase64(externalPath + loggedUser.getPicture()));*/
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}

}