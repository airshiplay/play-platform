package com.airlenet.manage.module.system.repo;

import com.airlenet.manage.module.system.entity.QRole;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import com.airlenet.data.jpa.EntityRepository;
import com.airlenet.manage.module.system.entity.QRole;
import com.airlenet.manage.module.system.entity.Role;

public interface RoleRepository extends EntityRepository<Role, Long>, QuerydslBinderCustomizer<QRole> {

	@Override
	default void customize(QuerydslBindings bindings, QRole root) {
		bindings.bind(root.name).first((path, value) -> path.contains(value));
	}
}
