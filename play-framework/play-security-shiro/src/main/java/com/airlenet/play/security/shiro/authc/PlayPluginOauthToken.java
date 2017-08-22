package com.airlenet.play.security.shiro.authc;

import com.airlenet.play.security.CustomUserDetails;
import org.apache.shiro.authc.HostAuthenticationToken;
import org.apache.shiro.authc.RememberMeAuthenticationToken;

public class PlayPluginOauthToken<U extends CustomUserDetails<?,?>> implements HostAuthenticationToken,
		RememberMeAuthenticationToken {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1424880846034478481L;
	private U customUserDetails;
	private final String host;
	public PlayPluginOauthToken(U u,String host) {
		this.customUserDetails = u;
		this.host = host;
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

	@Override
	public String getHost() {
		return this.host;
	}
}
