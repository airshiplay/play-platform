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
import com.airshiplay.play.security.CustomUserDetails.Type;
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
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		CustomUserDetails<?, ?> user = (CustomUserDetails<?, ?>) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		String username = user.getUsername();
		if (user.getType() == Type.AdminThirdPartyOauth) {
			authorizationInfo.setRoles(userService.findAdminRoles(username, user.getId()));
			authorizationInfo.setStringPermissions(userService.findAdminPermissions(username, user.getId()));
		} else if (user.getType() == Type.MemberThirdPartyOauth) {
			authorizationInfo.setRoles(userService.findMemberRoles(username, user.getId()));
			authorizationInfo.setStringPermissions(userService.findMemberPermissions(username, user.getId()));
		} else {
			authorizationInfo.setRoles(userService.findRoles(username, user.getId()));
			authorizationInfo.setStringPermissions(userService.findPermissions(username, user.getId()));
		}

		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		CustomUserDetails<?, ?> userDetails = (CustomUserDetails<?, ?>) token.getPrincipal();
		// new SimpleAuthenticationInfo();
		return new SimpleAuthenticationInfo(userDetails, // 用户名
				userDetails.getPassword(), // 密码
				ByteSource.Util.bytes(userDetails.getCredentialsSalt()),// salt=username+salt
				userDetails.getNickname() // realm name
		);
	}
}
