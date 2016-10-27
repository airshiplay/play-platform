package com.airshiplay.play.security.shiro.authc;

import org.apache.shiro.authc.RememberMeAuthenticationToken;

import com.airshiplay.play.security.CustomUserDetails;

public class PlayPluginOauthToken<U extends CustomUserDetails<?,?>> implements
		RememberMeAuthenticationToken {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1424880846034478481L;
	private U customUserDetails;

	public PlayPluginOauthToken(U u) {
		this.customUserDetails = u;
	}

	@Override
	public Object getPrincipal() {
		return this.customUserDetails;
	}

	@Override
	public Object getCredentials() {
		return null;
	}

	@Override
	public boolean isRememberMe() {
		return false;
	}

	public U getCustomUserDetails() {
		return this.customUserDetails;
	}
}
