package com.airshiplay.play.oauth2.shiro.authc;

import org.apache.shiro.authc.RememberMeAuthenticationToken;

import com.airshiplay.play.security.CustomUserDetails;

public class Oauth2Token implements RememberMeAuthenticationToken {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CustomUserDetails<?, ?> customUserDetails;

	private String accessToken;

	public Oauth2Token(String accessToken) {
		this.accessToken = accessToken;
	}

	@Override
	public Object getPrincipal() {
		return this.getCustomUserDetails();
	}

	@Override
	public Object getCredentials() {
		return null;
	}

	@Override
	public boolean isRememberMe() {
		return false;
	}

	public CustomUserDetails<?, ?> getCustomUserDetails() {
		return this.customUserDetails;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public void setCustomUserDetails(CustomUserDetails<?, ?> customUserDetails) {
		this.customUserDetails = customUserDetails;
	}

}
