package com.airshiplay.play.main.repo;

import com.airlenet.repo.jpa.EntityRepository;
import com.airshiplay.play.main.entity.AdminUserEntity;
import com.airshiplay.play.main.entity.QAdminUserEntity;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;


public interface AdminUserEntityRepository extends EntityRepository<AdminUserEntity, Long>, QuerydslBinderCustomizer<QAdminUserEntity> {

	AdminUserEntity findByUsername(String username);

	AdminUserEntity findByEmail(String email);

	AdminUserEntity findByMobile(String mobile);

	@Override
	default void customize(QuerydslBindings bindings, QAdminUserEntity root) {
		bindings.bind(root.username).first((path, value) -> path.contains(value));
		bindings.bind(root.nickname).first((path, value) -> path.contains(value));
		bindings.bind(root.email).first((path, value) -> path.contains(value));
		bindings.bind(root.mobile).first((path, value) -> path.contains(value));
	}
}
