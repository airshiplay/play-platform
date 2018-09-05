package com.airlenet.data.domain;

public interface Enabledable {

	boolean isEnabled();

	void setEnabled(boolean enabled);

	/**
	 * 标识为已禁用
	 */
	void markDisabled();

}
