package com.airshiplay.play.main.security;

import com.airshiplay.play.core.SpringContext;
import com.airshiplay.play.main.entity.UserEntity;
import com.airshiplay.play.main.repo.UserEntityRepository;
import com.airshiplay.play.security.CustomUserDetails;

public class AadminUserDetails extends CustomUserDetails<Long, UserEntity> {
	private static final long serialVersionUID = 8220061317304759492L;

	public AadminUserDetails(Long id, String username,String realname, String password,
			String credentialsSalt, boolean enabled,
			boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked) {
		super(id, username,realname, password, credentialsSalt, enabled,
				accountNonExpired, credentialsNonExpired, accountNonLocked);
	}


	@Override
	public UserEntity getCustomUser() {
		UserEntityRepository userEntityRepository =	SpringContext.getBean(UserEntityRepository.class);
		return userEntityRepository.findOne(getId());
	}

}