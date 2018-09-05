package com.airlenet.data.jpa;

import com.airlenet.data.domain.Node;
import com.airlenet.data.domain.Tree;
import com.querydsl.core.types.Predicate;

import java.io.Serializable;
import java.util.List;

public abstract class HierarchicalEntityServiceImpl<T extends HierarchicalEntity<?, I, T>, I extends Serializable> extends EntityServiceImpl<T,I>
		implements HierarchicalEntityService<T, I> {

	@Override
	public HierarchicalEntityRepository<T, I> getRepository() {
		return (HierarchicalEntityRepository<T, I>) super.getRepository();
	}

	public List<T> findRoots() {
		return getRepository().findRoots();
	}

	public List<T> findAllChildren(T root) {
		return getRepository().findAllChildren(root);
	}

	public Tree<T> findByRoot(T root) {
		return getRepository().findByRoot(root);
	}

	public Tree<T> findTree(Predicate predicate) {
		return getRepository().findTree(predicate);
	}

	public Tree<T> findTree(Predicate predicate, Node<T> singleRoot) {
		return getRepository().findTree(predicate, singleRoot);
	}

	public T sort(T source, T target, String action) {
		return getRepository().sort(source, target, action);
	}

}
