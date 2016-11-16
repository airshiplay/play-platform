package com.airshiplay.play.oauth2.shiro;

import org.springframework.beans.factory.annotation.Autowired;

import com.airshiplay.play.main.entity.UserEntity;
import com.airshiplay.play.main.service.UserEntityService;
import com.airshiplay.play.security.CustomUserDetails;

public class Oauth2UserDetails extends CustomUserDetails<Long, UserEntity> {
	@Autowired
	private UserEntityService userEntityService;

	public Oauth2UserDetails(Long id, UserEntity user) {
		super(id, user.getUsername(), user.getRealname(), user.getPassword(),
				user.getSalt(), true, true, true, true);
	}

	private static final long serialVersionUID = 8220061317304759492L;

	@Override
	public UserEntity getCustomUser() {
		return userEntityService.findOne(getId());
	}
}
