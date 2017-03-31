package com.airshiplay.play.obd.repo;

import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import com.airshiplay.play.obd.entity.Store4SServiceEntity;
import com.airshiplay.play.obd.entity.QStore4SServiceEntity;
import com.airshiplay.play.repo.jpa.BaseJpaRepository;

public interface Store4SServiceEntityRepository extends BaseJpaRepository<Store4SServiceEntity, Long>, QuerydslBinderCustomizer<QStore4SServiceEntity> {

	@Override
	default void customize(QuerydslBindings bindings, QStore4SServiceEntity root) {
		//bindings.bind(root.title).first((path, value) -> path.contains(value));
	}
}
