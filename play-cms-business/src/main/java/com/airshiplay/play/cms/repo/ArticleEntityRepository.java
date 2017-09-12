package com.airshiplay.play.cms.repo;

import java.util.List;

import com.airlenet.repo.jpa.EntityRepository;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import com.airshiplay.play.cms.entity.ArticleEntity;
import com.airshiplay.play.cms.entity.QArticleEntity;

public interface ArticleEntityRepository extends EntityRepository<ArticleEntity, Long>, QuerydslBinderCustomizer<QArticleEntity> {

	List<ArticleEntity> findTop2ByOrderByCreatedDateDesc();
	
	@Override
	default void customize(QuerydslBindings bindings, QArticleEntity root) {
		bindings.bind(root.title).first((path, value) -> path.contains(value));
	}

}
