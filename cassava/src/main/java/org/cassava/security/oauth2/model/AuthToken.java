package org.cassava.security.oauth2.model;

import org.cassava.model.User;

public class AuthToken {

	private String accessToken;

	private String refreshToken;

	private User user;

	public AuthToken(User user, String accessToken) {
		super();
		this.accessToken = accessToken;
		this.user = user;
	}

	public AuthToken(User user, String accessToken, String refreshToken) {
		super();
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getToken() {
		return accessToken;
	}

	public void setToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String toString() {
		return accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
}