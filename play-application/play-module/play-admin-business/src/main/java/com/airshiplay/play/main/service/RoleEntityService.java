package com.airshiplay.play.main.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.airshiplay.play.main.entity.QRoleEntity;
import com.airshiplay.play.main.entity.QUserEntity;
import com.airshiplay.play.main.entity.RoleEntity;
import com.airshiplay.play.main.entity.UserEntity;
import com.airshiplay.play.main.repo.RoleEntityRepository;
import com.airshiplay.play.main.repo.UserEntityRepository;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Service
public class RoleEntityService extends EntityService<RoleEntity, Long> {

	@Autowired
	private RoleEntityRepository roleEntityRepository;

	@Autowired
	EntityManager entityManager;
	@Autowired
	private UserEntityRepository userEntityRepository;

	@Transactional(readOnly = true)
	public Page<UserEntity> findUserPageByRoleId(Long roleId, Pageable pageable) {
		return userEntityRepository.findAll(
				QUserEntity.userEntity.roles.any().id.eq(roleId), pageable);
	}

	@Transactional(readOnly = true)
	public Page<UserEntity> findUnExistUserPageByRoleId(Long roleId,
			Predicate predicate, Pageable pageable) {
		Page<UserEntity> result = userEntityRepository.findAll(
				QUserEntity.userEntity.roles.any().id.ne(roleId).or(
						QUserEntity.userEntity.roles.isEmpty()), pageable);
		return result;
	}

	// public void deleteAuthoritiesByRoleIdAndMenuId(Long roleid,Long menuId){
	// // roleEntityRepository.deleteByEgId(roleid, menuId);
	// JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
	// new PathBuilderFactory().create(RoleEntity.class);
	// jpaQueryFactory.delete(QRoleEntity.roleEntity.authorities.any()).where(QRoleEntity.roleEntity.id.eq(20L)).execute();
	// }
}
