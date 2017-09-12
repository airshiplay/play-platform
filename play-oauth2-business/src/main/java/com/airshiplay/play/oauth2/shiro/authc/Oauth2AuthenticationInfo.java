package com.airshiplay.play.oauth2.shiro.authc;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.subject.PrincipalCollection;

public class Oauth2AuthenticationInfo implements AuthenticationInfo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public PrincipalCollection getPrincipals() {
		return null;
	}

	@Override
	public Object getCredentials() {
		return null;
	}

}
