package com.airshiplay.play.security.shiro.authc;

import org.apache.shiro.authc.UsernamePasswordToken;

public class UsernameMobilePasswordToken extends UsernamePasswordToken {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4272417052179851393L;
	private String mobile;

	public UsernameMobilePasswordToken(String username, String mobile,
			String password, boolean rememberMe, String host) {
		super(username, password, rememberMe, host);
		this.setMobile(mobile);
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

}
