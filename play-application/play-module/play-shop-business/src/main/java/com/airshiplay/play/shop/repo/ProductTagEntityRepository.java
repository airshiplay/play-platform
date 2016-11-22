package com.airshiplay.play.shop.repo;

import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import com.airshiplay.play.repo.jpa.BaseJpaRepository;
import com.airshiplay.play.shop.entity.ProductTagEntity;
import com.airshiplay.play.shop.entity.QProductTagEntity;

public interface ProductTagEntityRepository extends BaseJpaRepository<ProductTagEntity, Long>, QuerydslBinderCustomizer<QProductTagEntity> {

	@Override
	default void customize(QuerydslBindings bindings, QProductTagEntity root) {
		bindings.bind(root.name).first((path, value) -> path.contains(value));
	}
}
