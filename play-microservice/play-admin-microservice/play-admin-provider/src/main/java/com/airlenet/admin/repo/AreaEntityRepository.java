package com.airlenet.admin.repo;

import com.airlenet.admin.entity.QAreaEntity;
import com.airlenet.data.jpa.HierarchicalEntityRepository;
import com.airlenet.admin.entity.AreaEntity;
import com.airlenet.admin.entity.AreaEntity.AreaType;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

public interface AreaEntityRepository extends HierarchicalEntityRepository<AreaEntity, Long>, QuerydslBinderCustomizer<QAreaEntity> {

    AreaEntity findByFullNameAndType(String fullName, AreaType type);

    @Override
    default public void customize(QuerydslBindings bindings, QAreaEntity root) {
        bindings.bind(root.fullName).first((path, value) -> path.contains(value));
    }
}
