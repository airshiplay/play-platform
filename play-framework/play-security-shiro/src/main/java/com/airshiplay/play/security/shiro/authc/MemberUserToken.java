package com.airshiplay.play.security.shiro.authc;

import org.apache.shiro.authc.UsernamePasswordToken;

public class MemberUserToken extends UsernamePasswordToken {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5290517389724399897L;
	private String mobile;
	private String email;

	public MemberUserToken(String username, String mobile, String email, String password, boolean rememberMe, String host) {
		super(username, password, rememberMe, host);
		this.mobile = mobile;
		this.email = (email);
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
