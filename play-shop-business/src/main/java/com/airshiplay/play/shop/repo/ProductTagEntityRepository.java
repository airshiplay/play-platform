package com.airshiplay.play.shop.repo;

import com.airlenet.repo.jpa.EntityRepository;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import com.airshiplay.play.shop.entity.ProductTagEntity;
import com.airshiplay.play.shop.entity.QProductTagEntity;

public interface ProductTagEntityRepository extends EntityRepository<ProductTagEntity, Long>, QuerydslBinderCustomizer<QProductTagEntity> {

	@Override
	default void customize(QuerydslBindings bindings, QProductTagEntity root) {
		bindings.bind(root.name).first((path, value) -> path.contains(value));
	}
}
