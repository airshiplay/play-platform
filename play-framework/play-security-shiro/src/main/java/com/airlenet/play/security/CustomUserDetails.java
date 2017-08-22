package com.airlenet.play.security;

import java.io.Serializable;

import com.airlenet.play.security.shiro.PlayShiroUserDetails;

public abstract class CustomUserDetails<I extends Serializable, U> implements PlayShiroUserDetails {

	private static final long serialVersionUID = 8063484673226426535L;

	private final I id;
	private final String username;
	private final String nickname;
	private final Type type;
	private String password;
	private final String credentialsSalt;
	private boolean enabled;
	private final boolean accountNonExpired;
	private final boolean accountNonLocked;
	private final boolean credentialsNonExpired;

	public CustomUserDetails(I id, Type type, String username, String nickname, String password, String credentialsSalt, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked) {
		this.type = type;
		this.username = username;
		this.nickname = nickname;
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

	public Type getType() {
		return type;
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

	public String getNickname() {
		return this.nickname;
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

	public static enum Type {
		/** 管理员 */
		Admin, /** 会员 */
		Member, /** oauth管理员 */
		AdminOauth2, /** oauth2会员 */
		MemberOauth2, /** 第三方Oauth */
		AdminThirdPartyOauth,
		MemberThirdPartyOauth
	}
}
