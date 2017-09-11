package com.airlenet.security.shiro.realm;

import com.airlenet.security.CustomUserDetails;
import com.airlenet.security.shiro.PlayShiroUserDetailsService;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class PlayRealm extends AuthorizingRealm {
	@Autowired
	private PlayShiroUserDetailsService userService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		CustomUserDetails<?, ?> user = (CustomUserDetails<?, ?>) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		String username = user.getUsername();

		if (user.getType() == CustomUserDetails.Type.AdminThirdPartyOauth) {
			authorizationInfo.setRoles(userService.findAdminRoles(username, user.getId()));
			authorizationInfo.setStringPermissions(userService.findAdminPermissions(username, user.getId()));
		} else if (user.getType() == CustomUserDetails.Type.MemberThirdPartyOauth) {
			authorizationInfo.setRoles(userService.findMemberRoles(username, user.getId()));
			authorizationInfo.setStringPermissions(userService.findMemberPermissions(username, user.getId()));
		} else if (user.getType() == CustomUserDetails.Type.Admin) {
			authorizationInfo.setRoles(userService.findAdminRoles(username, user.getId()));
			authorizationInfo.setStringPermissions(userService.findAdminPermissions(username, user.getId()));
		} else if (user.getType() == CustomUserDetails.Type.Member) {
			authorizationInfo.setRoles(userService.findMemberRoles(username, user.getId()));
			authorizationInfo.setStringPermissions(userService.findMemberPermissions(username, user.getId()));
		} else if (user.getType() == CustomUserDetails.Type.AdminOauth2) {
			authorizationInfo.setRoles(userService.findAdminRoles(username, user.getId()));
			authorizationInfo.setStringPermissions(userService.findAdminPermissions(username, user.getId()));
		} else if (user.getType() == CustomUserDetails.Type.MemberOauth2) {
			authorizationInfo.setRoles(userService.findMemberRoles(username, user.getId()));
			authorizationInfo.setStringPermissions(userService.findMemberPermissions(username, user.getId()));
		} else {
			authorizationInfo.setRoles(userService.findRoles(username, user.getId()));
			authorizationInfo.setStringPermissions(userService.findPermissions(username, user.getId()));
		}

		return authorizationInfo;
	}

}
