package com.airshiplay.play.main.security;

import java.io.Serializable;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//package com.airshiplay.play.main.security;
//
//import java.util.Collection;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
import com.airshiplay.play.main.entity.UserEntity;
import com.airshiplay.play.main.repo.UserEntityRepository;
import com.airshiplay.play.security.CustomUserDetails;
import com.airshiplay.play.security.shiro.PlayShiroUserDetailsService;
//import com.airshiplay.play.security.CustomUserDetails;
//
import com.google.common.collect.Sets;

@Service()
public class ShiroUserDetailsService implements PlayShiroUserDetailsService {

	@Override
	public EntityUserDetails findByUsername(String username) {
		UserEntity userEntity = userEntityRepository.findByUsername(username);
		if (userEntity == null) {
			return null;
		}
		return new EntityUserDetails(userEntity.getId(),
				userEntity.getUsername(),userEntity.getUsername(), userEntity.getPassword(),
				userEntity.getSalt(), userEntity.isEnabled(),
				!userEntity.isAccountExpired(),
				!userEntity.isCredentialsExpired(), !userEntity.isLocked());
	}

	//
	@Autowired
	private UserEntityRepository userEntityRepository;

	public class EntityUserDetails extends CustomUserDetails<Long, UserEntity> {

		public EntityUserDetails(Long id, String username,String realname, String password,
				String credentialsSalt, boolean enabled,
				boolean accountNonExpired, boolean credentialsNonExpired,
				boolean accountNonLocked) {
			super(id, username,realname, password, credentialsSalt, enabled,
					accountNonExpired, credentialsNonExpired, accountNonLocked);
		}

		private static final long serialVersionUID = 8220061317304759492L;

		@Override
		public UserEntity getCustomUser() {
			return userEntityRepository.findOne(getId());
		}

	}

	@Override
	public Set<String> findRoles(String username, Serializable uid) {
		return Sets.newHashSet("admin");
	}

	@Override
	public Set<String> findPermissions(String username, Serializable uid) {
		return Sets.newHashSet("*:*:*");// 资源标识符:操作:对象实例ID
	}
}
