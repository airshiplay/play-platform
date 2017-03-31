package com.airshiplay.play.obd.repo;

import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import com.airshiplay.play.obd.entity.ObdCfgCarEntity;
import com.airshiplay.play.obd.entity.QObdCfgCarEntity;
import com.airshiplay.play.repo.jpa.BaseJpaRepository;

public interface ObdCfgCarEntityRepository extends BaseJpaRepository<ObdCfgCarEntity, Long>, QuerydslBinderCustomizer<QObdCfgCarEntity> {

	@Override
	default void customize(QuerydslBindings bindings, QObdCfgCarEntity root) {
		//bindings.bind(root.title).first((path, value) -> path.contains(value));
	}
}
