package com.airshiplay.play.obd.repo;

import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import com.airshiplay.play.obd.entity.CarAlarmEntity;
import com.airshiplay.play.obd.entity.QCarAlarmEntity;
import com.airshiplay.play.repo.jpa.BaseJpaRepository;

public interface CarAlarmEntityRepository extends BaseJpaRepository<CarAlarmEntity, Long>, QuerydslBinderCustomizer<QCarAlarmEntity> {

	@Override
	default void customize(QuerydslBindings bindings, QCarAlarmEntity root) {
		//bindings.bind(root.title).first((path, value) -> path.contains(value));
	}
}
