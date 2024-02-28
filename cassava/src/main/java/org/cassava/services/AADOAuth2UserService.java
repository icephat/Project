package org.cassava.services;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.cassava.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import org.cassava.model.Role;

@Service
public class AADOAuth2UserService extends OidcUserService {

	@Autowired
	private UserService userAccountService;

	@Override
	public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {

		OidcUser oidcUser = super.loadUser(userRequest);

		String userName = (String) oidcUser.getAttributes().get("email");

		Set<SimpleGrantedAuthority> authorities = new HashSet<SimpleGrantedAuthority>();

		User user = userAccountService.findByUsername(userName);

		if (user == null)
			authorities = getAuthority();
		else
			authorities = getAuthority(user);

		return new DefaultOidcUser(authorities, oidcUser.getIdToken());
	}

	private Set<SimpleGrantedAuthority> getAuthority() {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();

		authorities.add(new SimpleGrantedAuthority(UserService.ROLE_PREFIX + "Anonymous"));
		/*
		 * user.getRoles().g
		 * 
		 * if (user == null) { authorities.add(new
		 * SimpleGrantedAuthority(UserAccountService.ROLE_PREFIX + "Applicant")); } else
		 * if (user.getUserStatus().contentEquals("Active")) {
		 * 
		 * authorities.add(new SimpleGrantedAuthority(UserAccountService.ROLE_PREFIX +
		 * user.getAdminRole()));
		 * 
		 * authorities.add( new SimpleGrantedAuthority(UserAccountService.ROLE_PREFIX +
		 * user.getPosition().getPositionName()));
		 * 
		 * if (user.getCountryadmins().size() > 0) {
		 * 
		 * authorities.add(new SimpleGrantedAuthority(UserAccountService.ROLE_PREFIX +
		 * "CountryAdmin")); }
		 * 
		 * }
		 */

		return authorities;
	}

	private Set<SimpleGrantedAuthority> getAuthority(User user) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();

		List<Role> roles =  user.getRoles() ;
		
		//authorities.add(new SimpleGrantedAuthority(UserService.ROLE_PREFIX + "Admin"));
		


		for(Role r : roles) {
			//System.out.println( r.getNameEng());
			authorities.add(new SimpleGrantedAuthority(UserService.ROLE_PREFIX + r.getNameEng()));
		}
		
		/*
		 * user.getRoles().g
		 * 
		 * if (user == null) { authorities.add(new
		 * SimpleGrantedAuthority(UserAccountService.ROLE_PREFIX + "Applicant")); } else
		 * if (user.getUserStatus().contentEquals("Active")) {
		 * 
		 * authorities.add(new SimpleGrantedAuthority(UserAccountService.ROLE_PREFIX +
		 * user.getAdminRole()));
		 * 
		 * authorities.add( new SimpleGrantedAuthority(UserAccountService.ROLE_PREFIX +
		 * user.getPosition().getPositionName()));
		 * 
		 * if (user.getCountryadmins().size() > 0) {
		 * 
		 * authorities.add(new SimpleGrantedAuthority(UserAccountService.ROLE_PREFIX +
		 * "CountryAdmin")); }
		 * 
		 * }
		 */

		return authorities;
	}

}
