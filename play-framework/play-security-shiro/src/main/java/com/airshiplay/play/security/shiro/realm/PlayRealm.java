package com.airshiplay.play.security.shiro.realm;

import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.airshiplay.play.security.CustomUserDetails;
import com.airshiplay.play.security.CustomUserDetails.Type;
import com.airshiplay.play.security.shiro.PlayShiroUserDetailsService;

public abstract class PlayRealm extends AuthorizingRealm {
	@Autowired
	private PlayShiroUserDetailsService userService;

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
		} else if (user.getType() == Type.Admin) {
			authorizationInfo.setRoles(userService.findAdminRoles(username, user.getId()));
			authorizationInfo.setStringPermissions(userService.findAdminPermissions(username, user.getId()));
		} else if (user.getType() == Type.Member) {
			authorizationInfo.setRoles(userService.findMemberRoles(username, user.getId()));
			authorizationInfo.setStringPermissions(userService.findMemberPermissions(username, user.getId()));
		} else if (user.getType() == Type.AdminOauth2) {
			authorizationInfo.setRoles(userService.findAdminRoles(username, user.getId()));
			authorizationInfo.setStringPermissions(userService.findAdminPermissions(username, user.getId()));
		} else if (user.getType() == Type.MemberOauth2) {
			authorizationInfo.setRoles(userService.findMemberRoles(username, user.getId()));
			authorizationInfo.setStringPermissions(userService.findMemberPermissions(username, user.getId()));
		} else {
			authorizationInfo.setRoles(userService.findRoles(username, user.getId()));
			authorizationInfo.setStringPermissions(userService.findPermissions(username, user.getId()));
		}

		return authorizationInfo;
	}

}
