package com.airshiplay.play.cms.repo;

import com.airlenet.repo.jpa.EntityRepository;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import com.airshiplay.play.cms.entity.AdPositionEntity;
import com.airshiplay.play.cms.entity.QAdPositionEntity;

public interface AdPositionEntityRepository extends
		EntityRepository<AdPositionEntity, Long>,
		QuerydslBinderCustomizer<QAdPositionEntity> {

	@Override
	default public void customize(QuerydslBindings bindings,
			QAdPositionEntity root) {
		bindings.bind(root.name).first((path, value) -> path.contains(value));
	}
}
