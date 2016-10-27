package com.airshiplay.play.main.repo;

import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import com.airshiplay.play.main.entity.OrganizationEntity;
import com.airshiplay.play.main.entity.QOrganizationEntity;
import com.airshiplay.play.repo.jpa.BaseJpaRepository;

public interface OrganizationEntityRepository extends BaseJpaRepository<OrganizationEntity, Long>, QuerydslBinderCustomizer<QOrganizationEntity> {
	@Override
	default void customize(QuerydslBindings bindings, QOrganizationEntity root) {
		bindings.bind(root.name).first((path, value) -> path.contains(value));
		bindings.bind(root.code).first((path, value) -> path.contains(value));
	}
}
