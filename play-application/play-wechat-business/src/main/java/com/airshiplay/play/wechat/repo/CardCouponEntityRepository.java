package com.airshiplay.play.wechat.repo;

import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import com.airshiplay.play.repo.jpa.BaseJpaRepository;
import com.airshiplay.play.wechat.entity.CardCouponEntity;
import com.airshiplay.play.wechat.entity.QCardCouponEntity;

public interface CardCouponEntityRepository extends BaseJpaRepository<CardCouponEntity, Long>, QuerydslBinderCustomizer<QCardCouponEntity> {
	@Override
	default void customize(QuerydslBindings bindings, QCardCouponEntity root) {
		bindings.bind(root.title).first((path, value) -> path.contains(value));
	}
}
