package com.airshiplay.play.cms.repo;

import com.airlenet.repo.jpa.EntityRepository;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import com.airshiplay.play.cms.entity.ArticleTagEntity;
import com.airshiplay.play.cms.entity.QArticleTagEntity;

public interface ArticleTagEntityRepository extends EntityRepository<ArticleTagEntity, Long>, QuerydslBinderCustomizer<QArticleTagEntity> {

	@Override
	default void customize(QuerydslBindings bindings, QArticleTagEntity root) {
		bindings.bind(root.name).first((path, value) -> path.contains(value));
	}
}
