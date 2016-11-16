package com.airshiplay.bbs.domain;

import com.airshiplay.main.domain.Domain;

public class Tab extends Domain<Long> {

	private static final long serialVersionUID = -7354837617761723902L;

	private String name;
	private String code;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
