package org.cassava.util;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;

public class MvcHelper {

	
	public static String getUsername(Authentication authentication) {

		if (authentication instanceof OAuth2AuthenticationToken) {
			
			DefaultOidcUser oidcUser = (DefaultOidcUser) authentication.getPrincipal();

			return oidcUser.getEmail();
			// return new MvcHelper().getInstanceUserName(oidcUser) ;
		}

		return authentication.getName();
	}

}