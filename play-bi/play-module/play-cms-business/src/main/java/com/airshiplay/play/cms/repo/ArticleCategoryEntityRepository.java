package com.airshiplay.play.cms.repo;

import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import com.airshiplay.play.cms.entity.ArticleCategoryEntity;
import com.airshiplay.play.cms.entity.QArticleCategoryEntity;
import com.airshiplay.play.repo.jpa.HierarchicalJpaRepository;

public interface ArticleCategoryEntityRepository extends HierarchicalJpaRepository<ArticleCategoryEntity, Long>, QuerydslBinderCustomizer<QArticleCategoryEntity> {

	@Override
	default public void customize(QuerydslBindings bindings,
			QArticleCategoryEntity root) {
		bindings.bind(root.name).first((path, value) -> path.containsIgnoreCase(value));
		bindings.bind(root.seoTitle).first((path, value) -> path.containsIgnoreCase(value));
		bindings.bind(root.seoKeywords).first((path, value) -> path.containsIgnoreCase(value));
		bindings.bind(root.seoDescription).first((path, value) -> path.containsIgnoreCase(value));
	}
	
}
