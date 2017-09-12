package com.airshiplay.play.main.service;

import com.airlenet.repo.jpa.EntityService;
import com.airshiplay.play.main.entity.AuthorityEntity;
import com.airshiplay.play.main.entity.AuthorityEntity.PermissionType;
import com.airshiplay.play.main.entity.QAuthorityEntity;
import com.airshiplay.play.main.repo.AuthorityEntityRespository;
import com.airshiplay.play.main.repo.RoleEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorityEntityService extends
		EntityService<AuthorityEntity, Long> {

	@Autowired
	private AuthorityEntityRespository authorityEntityRespository;

	@Autowired
	private RoleEntityRepository roleEntityRespository;

	public Iterable<AuthorityEntity> findAuthoritiesByMenuId(Long menuId) {
		return authorityEntityRespository
				.findAll(QAuthorityEntity.authorityEntity.menu.id.eq(menuId));
	}

	public Iterable<AuthorityEntity> findAuthoritiesByMenuIdAndType(
			Long menuId, PermissionType type) {
		return authorityEntityRespository
				.findAll(QAuthorityEntity.authorityEntity.menu.id.eq(menuId)
						.and(QAuthorityEntity.authorityEntity.type.eq(type)));
	}

	public List<AuthorityEntity> findAuthoritiesByRoleId(Long roleId) {
		return roleEntityRespository.findOne(roleId).getAuthorities();
	}

}
