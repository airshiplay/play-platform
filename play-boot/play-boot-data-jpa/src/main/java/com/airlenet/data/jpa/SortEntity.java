package com.airlenet.data.jpa;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

import com.airlenet.data.domain.Sortable;
import com.airlenet.data.domain.Userable;
import org.apache.commons.lang3.builder.CompareToBuilder;

@MappedSuperclass
public class SortEntity<U extends Userable, I extends Serializable> extends DataEntity<U, I>
		implements Sortable, Comparable<SortEntity<U, I>> {

	private static final long serialVersionUID = -512099056914573545L;

	private Integer sortNo;

	@Override
	public Integer getSortNo() {
		return sortNo;
	}

	@Override
	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}

	@Override
	public int compareTo(SortEntity<U, I> o) {
		return new CompareToBuilder().append(getSortNo(), o.getSortNo()).append(getId(), o.getId()).toComparison();
	}

}
