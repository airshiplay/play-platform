package com.airshiplay.play.main.security;

import com.airshiplay.play.main.entity.AdminUserEntity;
import com.airshiplay.play.security.CustomUserDetails;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

@Component
public class AdminAuditorAware implements AuditorAware<AdminUserEntity> {

	@Override
	public AdminUserEntity getCurrentAuditor() {
		Subject authentication = SecurityUtils.getSubject();
		if (authentication == null) {
			return null;
		}
		Object principal = authentication.getPrincipal();
		if (principal == null)
			return null;
		CustomUserDetails<?, ?> user = (CustomUserDetails<?, ?>) principal;
		if (user.getType() == CustomUserDetails.Type.Admin) {
			return (AdminUserEntity) user.getCustomUser();
		} else {
			return null;
		}

	}

}
