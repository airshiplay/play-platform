package com.airlenet.data.domain;

/**
 * 默认根据sortNo大小进行升序排序
 *
 * @author airlenet
 * @since 3.0.0
 */
public interface Sortable {
	Integer getSortNo();

	void setSortNo(Integer sortNo);
}
