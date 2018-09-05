package com.airlenet.data.domain;

/**
 * 可锁定的，如为true，则不可删除
 * 
 * @author airlenet
 * @since 3.0.0
 */
public interface Lockedable {
	public boolean isLocked();

	public void setLocked(boolean locked);

	/**
	 * 标识为已锁定
	 */
	public void markLocked();
}
