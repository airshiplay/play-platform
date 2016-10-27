package com.airshiplay.play.security.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.airshiplay.play.security.CustomUserDetails;
import com.airshiplay.play.security.shiro.authc.PlayPluginOauthToken;

public class OauthRealm extends AuthorizingRealm {

	public OauthRealm() {
		super();
		setAuthenticationTokenClass(PlayPluginOauthToken.class);
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		CustomUserDetails<?,?> userDetails =  (CustomUserDetails<?,?>) token.getPrincipal();
		new SimpleAuthenticationInfo();
		return new SimpleAuthenticationInfo(
				userDetails, //用户名
				userDetails.getPassword(), //密码
                ByteSource.Util.bytes(userDetails.getCredentialsSalt()),//salt=username+salt
                userDetails.getRealname()  //realm name
        );
	}
}
