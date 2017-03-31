package com.airshiplay.play.obd.repo;

import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import com.airshiplay.play.obd.entity.CarEntity;
import com.airshiplay.play.obd.entity.QCarEntity;
import com.airshiplay.play.repo.jpa.BaseJpaRepository;

public interface CarEntityRepository extends BaseJpaRepository<CarEntity, Long>, QuerydslBinderCustomizer<QCarEntity> {

	@Override
	default void customize(QuerydslBindings bindings, QCarEntity root) {
		//bindings.bind(root.title).first((path, value) -> path.contains(value));
	}
}
