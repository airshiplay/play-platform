package com.airshiplay.play.obd.repo;

import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import com.airshiplay.play.obd.entity.ObdOrderEntity;
import com.airshiplay.play.obd.entity.QObdOrderEntity;
import com.airshiplay.play.repo.jpa.BaseJpaRepository;

public interface ObdOrderEntityRepository extends BaseJpaRepository<ObdOrderEntity, Long>, QuerydslBinderCustomizer<QObdOrderEntity> {

	@Override
	default void customize(QuerydslBindings bindings, QObdOrderEntity root) {
		//bindings.bind(root.title).first((path, value) -> path.contains(value));
	}
}
