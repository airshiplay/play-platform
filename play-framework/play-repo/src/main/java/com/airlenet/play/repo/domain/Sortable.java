package com.airlenet.play.repo.domain;

/**
 * 默认根据sortNo大小进行升序排序
 * 
 * @作者 
 * @博客 http://ken.whenling.com
 * @创建时间 2016年2月3日 下午12:59:31
 */
public interface Sortable {
	Integer getSortNo();

	void setSortNo(Integer sortNo);
}
