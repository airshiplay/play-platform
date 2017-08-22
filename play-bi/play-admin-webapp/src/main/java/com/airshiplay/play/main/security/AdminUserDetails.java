package com.airshiplay.play.main.security;

import com.airlenet.play.core.SpringContext;
import com.airshiplay.play.main.entity.AdminUserEntity;
import com.airshiplay.play.main.repo.AdminUserEntityRepository;
import com.airlenet.play.security.CustomUserDetails;

public class AdminUserDetails extends CustomUserDetails<Long, AdminUserEntity> {
	private static final long serialVersionUID = 8220061317304759492L;

	public AdminUserDetails(Long id, String username,String nickname, String password,
			String credentialsSalt, boolean enabled,
			boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked) {
		super(id,Type.Admin, username,nickname, password, credentialsSalt, enabled,
				accountNonExpired, credentialsNonExpired, accountNonLocked);
	}


	@Override
	public AdminUserEntity getCustomUser() {
		AdminUserEntityRepository userEntityRepository =	SpringContext.getBean(AdminUserEntityRepository.class);
		return userEntityRepository.findOne(getId());
	}

}