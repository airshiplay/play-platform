package com.airlenet.play.repo.jpa;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;

import com.querydsl.core.types.Predicate;
import com.airlenet.play.repo.domain.Node;
import com.airlenet.play.repo.domain.Tree;
import com.airlenet.play.repo.domain.TreeHelper;

public class HierarchicalJpaRepositoryImpl<T extends HierarchicalEntity<?, I, T>, I extends Serializable> extends BaseJpaRepositoryImpl<T, I>
		implements HierarchicalJpaRepository<T, I> {

	public HierarchicalJpaRepositoryImpl(JpaEntityInformation<T, I> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
	}

	@Override
	public List<T> findRoots() {
		return getQuery(new ParentSpecification<>(null, "parent"), (Sort) null).getResultList();
	}

	@Override
	public List<T> findAllChildren(T root) {
		return getQuery(new AllChildrenSpecification<>(root, "treePath"), (Sort) null).getResultList();
	}

	@Override
	public Tree<T> findTree(Predicate predicate) {
		return findTree(predicate, null);
	}

	@Override
	public Tree<T> findByRoot(T root) {
		List<T> allChildren = root == null ? findAll() : findAllChildren(root);
		return TreeHelper.toTree(root, allChildren);
	}

	@Override
	public Tree<T> findTree(Predicate predicate, Node<T> singleRoot) {
		List<T> allChildren = findAll(predicate);
		return TreeHelper.toTree(null, allChildren, singleRoot);
	}

	@Override
	public T sort(T source, T target, String action) {
		switch (action) {
		case "over":
			source.setParent(target);
			return save(source);
		case "before":
		case "after":
			Integer sourceSortNo = source.getSortNo();
			Integer targetSortNo = target.getSortNo();

			T parent = target.getParent();

			source.setSortNo(targetSortNo);
			source.setParent(parent);
			target.setSortNo(sourceSortNo);
			save(target);
			return save(source);
		default:
			break;
		}

		return source;
	}

}
