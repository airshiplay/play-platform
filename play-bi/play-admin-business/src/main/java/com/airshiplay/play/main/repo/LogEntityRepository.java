package com.airshiplay.play.main.repo;

import com.airlenet.play.repo.jpa.BaseJpaRepository;
import com.airshiplay.play.main.entity.LogEntity;
import com.airshiplay.play.main.entity.QLogEntity;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

public interface LogEntityRepository extends BaseJpaRepository<LogEntity, Long>, QuerydslBinderCustomizer<QLogEntity> {

	@Override
	default public void customize(QuerydslBindings bindings, QLogEntity root) {
		bindings.bind(root.text).first((path, value) -> path.containsIgnoreCase(value));
	}
}
