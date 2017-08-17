package com.airshiplay.play.cms.repo;

import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import com.airshiplay.play.cms.entity.ArticleTagEntity;
import com.airshiplay.play.cms.entity.QArticleTagEntity;
import com.airshiplay.play.repo.jpa.BaseJpaRepository;

public interface ArticleTagEntityRepository extends BaseJpaRepository<ArticleTagEntity, Long>, QuerydslBinderCustomizer<QArticleTagEntity> {

	@Override
	default void customize(QuerydslBindings bindings, QArticleTagEntity root) {
		bindings.bind(root.name).first((path, value) -> path.contains(value));
	}
}
