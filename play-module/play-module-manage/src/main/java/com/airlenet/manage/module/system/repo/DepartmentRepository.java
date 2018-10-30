package com.airlenet.manage.module.system.repo;

import com.airlenet.manage.module.system.entity.Department;
import com.airlenet.manage.module.system.entity.QDepartment;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import com.airlenet.data.jpa.HierarchicalEntityRepository;
import com.airlenet.manage.module.system.entity.Department;
import com.airlenet.manage.module.system.entity.QDepartment;

public interface DepartmentRepository
		extends HierarchicalEntityRepository<Department, Long>, QuerydslBinderCustomizer<QDepartment> {

	@Override
	default void customize(QuerydslBindings bindings, QDepartment root) {
		bindings.bind(root.name).first((path, value) -> path.contains(value));
	}
}
