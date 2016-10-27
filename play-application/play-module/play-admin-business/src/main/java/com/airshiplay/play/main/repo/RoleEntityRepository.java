package com.airshiplay.play.main.repo;

import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import com.airshiplay.play.main.entity.QRoleEntity;
import com.airshiplay.play.main.entity.RoleEntity;
import com.airshiplay.play.repo.jpa.BaseJpaRepository;

public interface RoleEntityRepository extends BaseJpaRepository<RoleEntity, Long>, QuerydslBinderCustomizer<QRoleEntity> {
	@Override
	default void customize(QuerydslBindings bindings, QRoleEntity root) {
		bindings.bind(root.name).first((path, value) -> path.contains(value));
		bindings.bind(root.code).first((path, value) -> path.contains(value));
	}
}
