package com.airlenet.thymeleaf;

import java.util.List;

import com.airlenet.data.domain.Userable;

public interface ThymeleafViewVariable {

	Userable getCurrentUser();

	public List<? extends ThymeleafMenuItem<?>> getMenuRoots();

}
