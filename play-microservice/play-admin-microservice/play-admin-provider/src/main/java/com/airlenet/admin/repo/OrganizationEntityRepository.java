package com.airlenet.admin.repo;

import com.airlenet.data.jpa.HierarchicalEntityRepository;
import com.airlenet.admin.entity.OrganizationEntity;
import com.airlenet.admin.entity.QOrganizationEntity;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

public interface OrganizationEntityRepository extends HierarchicalEntityRepository<OrganizationEntity, Long>, QuerydslBinderCustomizer<QOrganizationEntity> {
    @Override
    default void customize(QuerydslBindings bindings, QOrganizationEntity root) {
        bindings.bind(root.name).first((path, value) -> path.contains(value));
        bindings.bind(root.code).first((path, value) -> path.contains(value));
    }
}
