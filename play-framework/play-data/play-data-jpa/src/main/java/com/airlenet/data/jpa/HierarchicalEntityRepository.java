package com.airlenet.data.jpa;

import com.airlenet.data.domain.Node;
import com.airlenet.data.domain.Tree;
import com.querydsl.core.types.Predicate;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

@NoRepositoryBean
public interface HierarchicalEntityRepository<T extends HierarchicalEntity<?, I, T>, I extends Serializable> extends EntityRepository<T, I> {

	List<T> findRoots();

	List<T> findAllChildren(T root);

	Tree<T> findByRoot(T root);

	Tree<T> findTree(Predicate predicate);

	Tree<T> findTree(Predicate predicate, Node<T> singleRoot);

	public T sort(T source, T target, String action);
}
