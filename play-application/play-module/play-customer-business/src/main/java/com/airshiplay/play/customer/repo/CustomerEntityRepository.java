package com.airshiplay.play.customer.repo;

import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import com.airshiplay.play.customer.entity.CustomerEntity;
import com.airshiplay.play.customer.entity.QCustomerEntity;
import com.airshiplay.play.repo.jpa.BaseJpaRepository;

public interface CustomerEntityRepository  extends BaseJpaRepository<CustomerEntity, Long>, QuerydslBinderCustomizer<QCustomerEntity> {
	@Override
	default void customize(QuerydslBindings bindings, QCustomerEntity root) {
		bindings.bind(root.username).first((path, value) -> path.contains(value));
		bindings.bind(root.name).first((path, value) -> path.contains(value));
		bindings.bind(root.email).first((path, value) -> path.contains(value));
		bindings.bind(root.mobile).first((path, value) -> path.contains(value));
	}
}
