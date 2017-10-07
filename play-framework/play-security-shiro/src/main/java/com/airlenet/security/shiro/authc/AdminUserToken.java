package com.airlenet.security.shiro.authc;

import org.apache.shiro.authc.UsernamePasswordToken;

public class AdminUserToken extends UsernamePasswordToken {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4272417052179851393L;
	private String mobile;
	private String email;

	public AdminUserToken(String username, String password) {
		super(username, password);
	}
	public AdminUserToken(String username, String password, boolean rememberMe, String host) {
		super(username, password, rememberMe, host);
	}

	public AdminUserToken(String username, String mobile, String email, String password, boolean rememberMe, String host) {
		super(username, password, rememberMe, host);
		this.mobile = mobile;
		this.email = email;
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
