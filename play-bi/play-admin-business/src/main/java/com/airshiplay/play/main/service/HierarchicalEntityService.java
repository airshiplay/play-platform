package com.airshiplay.play.main.service;

import com.airlenet.play.repo.jpa.HierarchicalEntity;
import com.airlenet.play.repo.jpa.HierarchicalJpaRepository;
import com.querydsl.core.types.Predicate;

import java.io.Serializable;
import java.util.List;

public abstract class HierarchicalEntityService<T extends HierarchicalEntity<?, I, T>, I extends Serializable>
		extends EntityService<T, I> {

	public List<T> findRoots() {
		return getHierarchicalJpaRepository().findRoots();
	}

	public List<T> findAllChildren(T root) {
		return getHierarchicalJpaRepository().findAllChildren(root);
	}

	public Tree<T> findByRoot(T root) {
		return getHierarchicalJpaRepository().findByRoot(root);
	}

	public Tree<T> findTree(Predicate predicate) {
		return getHierarchicalJpaRepository().findTree(predicate);
	}

	public Tree<T> toTree(T root, List<T> nodes) {
		return getHierarchicalJpaRepository().toTree(root, nodes);
	}

	protected HierarchicalJpaRepository<T, I> getHierarchicalJpaRepository() {
		return ((HierarchicalJpaRepository<T, I>) baseRepository);
	}

}
