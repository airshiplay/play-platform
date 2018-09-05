package com.airlenet.data.domain;

/**
 * 可默认的，如为true，则为默认对象
 * 
 * @author airlenet
 * @since 3.0.0
 */
public interface Defaultable {
	
	public static final String PROPERTY_NAME = "defaulted";

	public boolean isDefaulted();

	public void setDefaulted(boolean defaulted);

	/**
	 * 标识为默认
	 */
	public void markDefaulted();

}
