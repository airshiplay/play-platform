package com.airshiplay.play.main.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import com.airshiplay.play.main.entity.UserEntity;
import com.airshiplay.play.main.service.UserEntityService;
import com.airshiplay.play.security.CustomUserDetails;

@Component
public class AuthenticationSuccessListener implements ApplicationListener<AuthenticationSuccessEvent> {

	@Autowired
	private UserEntityService userEntityService;

	@Override
	public void onApplicationEvent(AuthenticationSuccessEvent event) {
		Authentication authentication = event.getAuthentication();
		Object principal = authentication.getPrincipal();
		if (principal instanceof CustomUserDetails) {
			@SuppressWarnings("unchecked")
			UserEntity userEntity = ((CustomUserDetails<?, UserEntity>) principal).getCustomUser();
			userEntity.setLastLoginDate(new Date());

			WebAuthenticationDetails details = (WebAuthenticationDetails) authentication.getDetails();
			userEntity.setLastLoginIp(details.getRemoteAddress());
			userEntityService.save(userEntity);
		}

	}

}
