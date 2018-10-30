package com.airlenet.data.domain;

public interface Disabledable {

	boolean isDisabled();

	void setDisabled(boolean disabled);

	/**
	 * 标识为已禁用
	 */
	void markDisabled();

}
