package com.airlenet.data.jpa;

import java.io.Serializable;

import com.airlenet.data.DomainLifecycle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface EntityRepository<T, I extends Serializable>
		extends JpaRepository<T, I>, QuerydslPredicateExecutor<T>, DomainLifecycle<T> {

}
