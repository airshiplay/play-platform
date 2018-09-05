package com.airlenet.data.jpa;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class PropertyBooleanSpecification<T> implements Specification<T> {

	private boolean booleanValue;

	private String property;

	public PropertyBooleanSpecification(String property, Boolean booleanValue) {
		this.property = property;
		this.booleanValue = booleanValue;
	}

	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		return booleanValue ? cb.isTrue(root.get(property)) : cb.isFalse(root.get(property));
	}

}
