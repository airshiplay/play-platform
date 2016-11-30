package com.airshiplay.play.main.repo;

import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import com.airshiplay.play.main.entity.MemberUserEntity;
import com.airshiplay.play.main.entity.QMemberUserEntity;
import com.airshiplay.play.repo.jpa.BaseJpaRepository;

public interface MemberUserEntityRepository extends BaseJpaRepository<MemberUserEntity, Long>, QuerydslBinderCustomizer<QMemberUserEntity> {
	
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
