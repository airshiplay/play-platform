package com.airlenet.admin.repo;

import com.airlenet.admin.entity.UserEntity;
import com.airlenet.admin.entity.QUserEntity;
import com.airlenet.data.jpa.EntityRepository;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;


public interface UserEntityRepository extends EntityRepository<UserEntity, Long>, QuerydslBinderCustomizer<QUserEntity> {

    UserEntity findByUsername(String username);

    UserEntity findByEmail(String email);

    UserEntity findByMobile(String mobile);

    @Override
    default void customize(QuerydslBindings bindings, QUserEntity root) {
        bindings.bind(root.username).first((path, value) -> path.contains(value));
        bindings.bind(root.nickname).first((path, value) -> path.contains(value));
        bindings.bind(root.email).first((path, value) -> path.contains(value));
        bindings.bind(root.mobile).first((path, value) -> path.contains(value));
    }
}
