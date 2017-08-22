package com.airshiplay.play.main.repo;

import com.airlenet.play.repo.jpa.HierarchicalJpaRepository;
import com.airshiplay.play.main.entity.OrganizationEntity;
import com.airshiplay.play.main.entity.QOrganizationEntity;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

public interface OrganizationEntityRepository extends HierarchicalJpaRepository<OrganizationEntity, Long>, QuerydslBinderCustomizer<QOrganizationEntity> {
	@Override
	default void customize(QuerydslBindings bindings, QOrganizationEntity root) {
		bindings.bind(root.name).first((path, value) -> path.contains(value));
		bindings.bind(root.code).first((path, value) -> path.contains(value));
	}
}
