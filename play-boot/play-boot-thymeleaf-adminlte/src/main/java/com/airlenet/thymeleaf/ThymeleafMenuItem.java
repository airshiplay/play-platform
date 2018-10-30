package com.airlenet.thymeleaf;

import com.airlenet.data.domain.Hierarchical;

public interface ThymeleafMenuItem<T extends ThymeleafMenuItem<T>> extends Hierarchical<T> {

	public String getName();

	public String getCode();

	public String getIconCls();

	public String getHref();

	public String getParameters();
}
