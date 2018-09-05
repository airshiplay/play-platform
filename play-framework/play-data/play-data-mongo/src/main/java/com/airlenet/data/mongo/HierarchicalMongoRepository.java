package com.airlenet.data.mongo;

import com.airlenet.data.domain.Node;
import com.airlenet.data.domain.Tree;
import com.querydsl.core.types.Predicate;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface HierarchicalMongoRepository<T extends HierarchicalDoc<?, T>> extends BaseMongoRepository<T> {

	List<T> findRoots();

	List<T> findAllChildren(T root);

	Tree<T> findByRoot(T root);

	Tree<T> findTree(Predicate predicate);

	Tree<T> findTree(Predicate predicate, Node<T> singleRoot);
}
