package com.airlenet.admin.repo;

import com.airlenet.admin.entity.QMemberUserEntity;
import com.airlenet.data.jpa.EntityRepository;
import com.airlenet.admin.entity.MemberUserEntity;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

public interface MemberUserEntityRepository extends EntityRepository<MemberUserEntity, Long>, QuerydslBinderCustomizer<QMemberUserEntity> {

    MemberUserEntity findByUsername(String username);

    MemberUserEntity findByEmail(String email);

    MemberUserEntity findByMobile(String mobile);

    @Override
    default void customize(QuerydslBindings bindings, QMemberUserEntity root) {
        bindings.bind(root.username).first((path, value) -> path.contains(value));
        bindings.bind(root.nickname).first((path, value) -> path.contains(value));
        bindings.bind(root.email).first((path, value) -> path.contains(value));
        bindings.bind(root.mobile).first((path, value) -> path.contains(value));
        bindings.excluding(root.password);
    }
}
