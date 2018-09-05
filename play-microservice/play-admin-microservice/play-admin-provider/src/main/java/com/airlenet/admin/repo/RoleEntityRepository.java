package com.airlenet.admin.repo;

import com.airlenet.admin.entity.RoleEntity;
import com.airlenet.data.jpa.EntityRepository;
import com.airlenet.admin.entity.QRoleEntity;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

public interface RoleEntityRepository extends EntityRepository<RoleEntity, Long>, QuerydslBinderCustomizer<QRoleEntity> {
    @Override
    default void customize(QuerydslBindings bindings, QRoleEntity root) {
        bindings.bind(root.name).first((path, value) -> path.contains(value));
        bindings.bind(root.code).first((path, value) -> path.contains(value));
    }
//	@Modifying
//	@Query("DELETE role_auth FROM sys_role_authority  AS role_auth LEFT JOIN sys_authority AS auth ON  role_auth.authority_id =auth.id   where role_auth.role_id = ?1 and auth.menu_id= ?2")
//	int deleteByEgId(Long roleId,Long menuId);

}
