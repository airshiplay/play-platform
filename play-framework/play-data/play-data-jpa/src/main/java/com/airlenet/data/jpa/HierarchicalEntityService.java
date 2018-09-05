package com.airlenet.data.jpa;

import com.airlenet.data.domain.Node;
import com.airlenet.data.domain.Tree;
import com.querydsl.core.types.Predicate;

import java.io.Serializable;
import java.util.List;

public interface HierarchicalEntityService<T extends HierarchicalEntity<?, I, T>, I extends Serializable>
		extends EntityService<T, I> {

	@Override
	public HierarchicalEntityRepository<T, I> getRepository() ;

	public List<T> findRoots();

	public List<T> findAllChildren(T root);

	public Tree<T> findByRoot(T root);

	public Tree<T> findTree(Predicate predicate);

	public Tree<T> findTree(Predicate predicate, Node<T> singleRoot);

	public T sort(T source, T target, String action);

}
