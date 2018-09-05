package com.airlenet.data.mongo;

import com.airlenet.data.domain.Hierarchical;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

public class HierarchicalDoc<U, T> extends SortDoc<U> implements Hierarchical<T> {

	private static final long serialVersionUID = 5476261171221766078L;

	@JsonIgnore
	@DBRef(lazy = true)
	private T parent;

	@JsonIgnore
	@DBRef(lazy = true)
	private List<T> children;

	@JsonIgnore
	private String treePath;

	@Override
	public T getParent() {
		return parent;
	}

	@Override
	public void setParent(T parent) {
		this.parent = parent;
	}

	@Override
	public List<T> getChildren() {
		return children;
	}

	@Override
	public void setChildren(List<T> children) {
		this.children = children;
	}

	public String getTreePath() {
		return treePath;
	}

	public void setTreePath(String treePath) {
		this.treePath = treePath;
	}

	public boolean isLeaf() {
		return children == null || children.size() == 0;
	}

	public boolean isRoot() {
		return parent == null;
	}
}
