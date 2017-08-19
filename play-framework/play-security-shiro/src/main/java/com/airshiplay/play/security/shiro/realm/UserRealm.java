package com.airshiplay.play.security.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.airshiplay.play.security.CustomUserDetails;
import com.airshiplay.play.security.shiro.PlayShiroUserDetailsService;
import com.airshiplay.play.security.shiro.authc.AdminUserToken;
import com.airshiplay.play.security.shiro.authc.MemberUserToken;

public class UserRealm extends PlayRealm {
	@Autowired
	private PlayShiroUserDetailsService userService;

	 

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		CustomUserDetails<?, ?> user;
		if (token instanceof AdminUserToken) {
			AdminUserToken adminToken = (AdminUserToken) token;
			user = userService.findAdminUserByUsername(adminToken.getUsername());
		} else if (token instanceof MemberUserToken) {
			MemberUserToken memberToken = (MemberUserToken) token;
			user = userService.findMemberUserByUsername(memberToken.getUsername());
		} else {
			String username = (String) token.getPrincipal();
			user = userService.findByUsername(username);
		}

		if (user == null) {
			throw new UnknownAccountException();// 没找到帐号
		}
		if (Boolean.TRUE.equals(user.getLocked())) {
			throw new LockedAccountException(); // 帐号锁定
		}
		// 交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, // 用户名
				user.getPassword(), // 密码
				ByteSource.Util.bytes(user.getCredentialsSalt()),// salt=username+salt
				user.getNickname() // realm name
		);

		return authenticationInfo;
	}

	@Override
	public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
		super.clearCachedAuthorizationInfo(principals);
	}

	@Override
	public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
		super.clearCachedAuthenticationInfo(principals);
	}

	@Override
	public void clearCache(PrincipalCollection principals) {
		super.clearCache(principals);
	}

	public void clearAllCachedAuthorizationInfo() {
		getAuthorizationCache().clear();
	}

	public void clearAllCachedAuthenticationInfo() {
		getAuthenticationCache().clear();
	}

	public void clearAllCache() {
		clearAllCachedAuthenticationInfo();
		clearAllCachedAuthorizationInfo();
	}

}
