package com.airshiplay.play.main.security;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.airshiplay.play.main.entity.AuthorityEntity;
import com.airshiplay.play.main.entity.QAuthorityEntity;
import com.airshiplay.play.main.entity.QRoleEntity;
import com.airshiplay.play.main.entity.RoleEntity;
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
import com.airshiplay.play.main.repo.AuthorityEntityRespository;
import com.airshiplay.play.main.repo.RoleEntityRepository;
import com.airshiplay.play.main.repo.UserEntityRepository;
import com.airshiplay.play.security.shiro.PlayShiroUserDetailsService;
//import com.airshiplay.play.security.CustomUserDetails;
//
import com.google.common.collect.Sets;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Service
public class ShiroUserDetailsService implements PlayShiroUserDetailsService {

	@Autowired
	private UserEntityRepository userEntityRepository;
	@Autowired
	private RoleEntityRepository roleEntityRepository;
	@Autowired
	private AuthorityEntityRespository authorityEntityRespository;
	@Autowired
	EntityManager entityManager;

	@Override
	@Transactional(readOnly = true)
	public AdminUserDetails findByUsername(String username) {
		UserEntity userEntity = userEntityRepository.findByUsername(username);
		if (userEntity == null) {
			return null;
		}
		return new AdminUserDetails(userEntity.getId(),
				userEntity.getUsername(), userEntity.getUsername(),
				userEntity.getPassword(), userEntity.getSalt(),
				userEntity.isEnabled(), !userEntity.isAccountExpired(),
				!userEntity.isCredentialsExpired(), !userEntity.isLocked());
	}

	//

	@Transactional(readOnly = true)
	@Override
	public Set<String> findRoles(String username, Serializable uid) {

		Iterator<RoleEntity> result = roleEntityRepository.findAll(
				QRoleEntity.roleEntity.users.any().id.eq((Long) uid))
				.iterator();
		Set<String> sets = new HashSet<String>();
		while (result.hasNext()) {
			RoleEntity roleEntity = (RoleEntity) result.next();
			sets.add(roleEntity.getCode());
		}
		return Sets.newHashSet("admin");
	}

	@Transactional(readOnly = true)
	@Override
	public Set<String> findPermissions(String username, Serializable uid) {

		Iterator<AuthorityEntity> result =	authorityEntityRespository
				.findAll(QAuthorityEntity.authorityEntity.roles.any().users
						.any().id.eq((Long) uid)).iterator();

//		JPAQueryFactory query = new JPAQueryFactory(entityManager);
//
//		List<String> results = query
//				.select(QAuthorityEntity.authorityEntity.permission)
//				.from(QAuthorityEntity.authorityEntity)
//				.where(QAuthorityEntity.authorityEntity.roles.any().users.any().id
//						.eq((Long) uid)).fetch();

		Set<String> sets = new HashSet<String>();
		while (result.hasNext()) {
			AuthorityEntity roleEntity = (AuthorityEntity) result.next();
			sets.add(roleEntity.getPermission());
		}
		return sets;// Sets.newHashSet("*:*:*:*");//
										// 资源标识符:操作:对象实例ID
	}
}
