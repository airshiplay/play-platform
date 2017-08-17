package com.airshiplay.play.cms.repo;

import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import com.airshiplay.play.cms.entity.QQuestionEntity;
import com.airshiplay.play.cms.entity.QuestionEntity;
import com.airshiplay.play.repo.jpa.BaseJpaRepository;

public interface QuestionEntityRepository extends BaseJpaRepository<QuestionEntity, Long>, QuerydslBinderCustomizer<QQuestionEntity> {

	@Override
	default void customize(QuerydslBindings bindings, QQuestionEntity root) {
		bindings.bind(root.title).first((path, value) -> path.contains(value));
	}
	
}
