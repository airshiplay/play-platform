package com.airshiplay.play.main.security;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import com.airshiplay.play.main.entity.AdminUserEntity;
import com.airshiplay.play.security.CustomUserDetails;

@Component
public class AuthenticationAuditorAware implements AuditorAware<AdminUserEntity> {

	@Override
	public AdminUserEntity getCurrentAuditor() {
		Subject authentication = SecurityUtils.getSubject();
		if (authentication == null) {
			return null;
		}
		Object principal = authentication.getPrincipal();
		if(principal==null)
			return null;
		@SuppressWarnings("unchecked")
		CustomUserDetails<?, AdminUserEntity> user = (CustomUserDetails<?, AdminUserEntity>) principal;
		return user.getCustomUser();
	}

}
