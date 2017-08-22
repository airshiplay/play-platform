package com.airlenet.play.repo.jpa;

import java.io.Serializable;
import java.util.List;

import com.airlenet.play.repo.domain.Tree;
import org.springframework.data.repository.NoRepositoryBean;

import com.querydsl.core.types.Predicate;

@NoRepositoryBean
public interface HierarchicalJpaRepository<T extends HierarchicalEntity<?, I, T>, I extends Serializable>
		extends BaseJpaRepository<T, I> {

	List<T> findRoots();

	List<T> findAllChildren(T root);

	Tree<T> findByRoot(T root);

	Tree<T> findTree(Predicate predicate);

	Tree<T> toTree(T current, List<T> nodes);
}
