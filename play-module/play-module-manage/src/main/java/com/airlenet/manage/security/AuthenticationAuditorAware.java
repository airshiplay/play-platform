package com.airlenet.manage.security;

import com.airlenet.manage.module.system.entity.User;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthenticationAuditorAware implements AuditorAware<User> {

	@Override
	public Optional<User> getCurrentAuditor() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			return Optional.empty();
		}
		Object principal = authentication.getPrincipal();
		if (principal instanceof DefaultDetailsService.CurrentUserDetails) {
			return Optional.ofNullable(((DefaultDetailsService.CurrentUserDetails) principal).getCustomUser());
		}
		return Optional.empty();
	}

}
