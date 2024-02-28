package org.cassava.security.oauth2.model;

import javax.validation.constraints.NotBlank;

public class TokenRefreshRequest {
	@NotBlank
  private String refreshToken;

  public String getRefreshToken() {
    return refreshToken;
  }

  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }
}