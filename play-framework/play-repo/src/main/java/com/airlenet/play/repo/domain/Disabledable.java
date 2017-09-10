package com.airlenet.play.repo.domain;

public interface Disabledable {

	boolean isDisabled();

	void setDisabled(boolean disabled);

	/**
	 * 标识为已禁用
	 */
	void markDisabled();

}
