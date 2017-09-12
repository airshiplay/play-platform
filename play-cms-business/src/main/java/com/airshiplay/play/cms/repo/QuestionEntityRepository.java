package com.airshiplay.play.cms.repo;

import com.airlenet.repo.jpa.EntityRepository;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import com.airshiplay.play.cms.entity.QQuestionEntity;
import com.airshiplay.play.cms.entity.QuestionEntity;

public interface QuestionEntityRepository extends EntityRepository<QuestionEntity, Long>, QuerydslBinderCustomizer<QQuestionEntity> {

	@Override
	default void customize(QuerydslBindings bindings, QQuestionEntity root) {
		bindings.bind(root.title).first((path, value) -> path.contains(value));
	}
	
}
