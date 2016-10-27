package com.airshiplay.play.security;

import java.io.Serializable;

import com.airshiplay.play.security.shiro.PlayShiroUserDetails;

public abstract class CustomUserDetails<I extends Serializable, U> implements
		PlayShiroUserDetails {

	private static final long serialVersionUID = 8063484673226426535L;

	private final I id;
	private String password;
	private final String username;
	private final String realname;
	private final String credentialsSalt;
	private boolean enabled;
	private final boolean accountNonExpired;
	private final boolean accountNonLocked;
	private final boolean credentialsNonExpired;

	public CustomUserDetails(I id, String username,String realname, String password,String credentialsSalt,
			boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked) {
		this.username = username;
		this.realname =realname;
		this.password = password;
		this.credentialsSalt = credentialsSalt;
		this.enabled = enabled;
		this.accountNonExpired = accountNonExpired;
		this.accountNonLocked = accountNonLocked;
		this.credentialsNonExpired = credentialsNonExpired;
		this.id = id;
	}

	public I getId() {
		return id;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public String getCredentialsSalt() {
		return this.credentialsSalt;
	}

	@Override
	public boolean getLocked() {
		return !this.enabled;
	}

	@Override
	public String getPassword() {
		return this.password;
	}
	public String getRealname() {
		return this.realname;
	}
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public void eraseCredentials() {
		password = null;
	}

	public abstract U getCustomUser();


}
