package com.airshiplay.play.security.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.airshiplay.play.security.CustomUserDetails;
import com.airshiplay.play.security.shiro.PlayShiroUserDetailsService;
import com.airshiplay.play.security.shiro.authc.PlayPluginOauthToken;

public class OauthRealm extends AuthorizingRealm {
	@Autowired
	private PlayShiroUserDetailsService userService;

	public OauthRealm() {
		super();
		setAuthenticationTokenClass(PlayPluginOauthToken.class);
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		CustomUserDetails<?, ?> user = (CustomUserDetails<?, ?>) principals
				.getPrimaryPrincipal();
		String username = user.getUsername().toString();
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		authorizationInfo
				.setRoles(userService.findRoles(username, user.getId()));
		authorizationInfo.setStringPermissions(userService.findPermissions(
				username, user.getId()));
		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		CustomUserDetails<?, ?> userDetails = (CustomUserDetails<?, ?>) token
				.getPrincipal();
		// new SimpleAuthenticationInfo();
		return new SimpleAuthenticationInfo(userDetails, // 用户名
				userDetails.getPassword(), // 密码
				ByteSource.Util.bytes(userDetails.getCredentialsSalt()),// salt=username+salt
				userDetails.getRealname() // realm name
		);
	}
}
