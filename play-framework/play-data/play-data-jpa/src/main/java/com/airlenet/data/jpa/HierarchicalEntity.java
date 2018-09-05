package com.airlenet.data.jpa;

import com.airlenet.data.domain.Hierarchical;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@MappedSuperclass
@EntityListeners(value = { HierarchicalEntityListener.class })
public class HierarchicalEntity<U, I extends Serializable, T> extends SortEntity<U, I> implements Hierarchical<T> {

	private static final long serialVersionUID = 4795899175741576611L;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	private T parent;

	@JsonIgnore
	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = { CascadeType.REMOVE })
	@OrderBy("sortNo asc")
	private List<T> children;

	@JsonIgnore
	@Column(length = 500)
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
