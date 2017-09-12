package com.airshiplay.play.main.security;

import com.airlenet.core.SpringContext;
import com.airlenet.security.CustomUserDetails;
import com.airshiplay.play.main.entity.MemberUserEntity;
import com.airshiplay.play.main.repo.MemberUserEntityRepository;

public class MemberUserDetails extends CustomUserDetails<Long, MemberUserEntity> {
	private static final long serialVersionUID = 8220061317304759492L;

	public MemberUserDetails(Long id, String username,String realname, String password,
			String credentialsSalt, boolean enabled,
			boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked) {
		super(id,Type.Member, username,realname, password, credentialsSalt, enabled,
				accountNonExpired, credentialsNonExpired, accountNonLocked);
	}


	@Override
	public MemberUserEntity getCustomUser() {
		MemberUserEntityRepository userEntityRepository =	SpringContext.getBean(MemberUserEntityRepository.class);
		return userEntityRepository.findOne(getId());
	}

}