package com.qq.weixin.mp.model.card;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardDateInfo {
	/**
	 * DATE_TYPE_PERMANENT 永久有效
	 */
	private String type;
	private Integer fixed_term;
	private Integer fixed_begin_term;
	private Long begin_timestamp;
	private Long end_timestamp;

	public CardDateInfo() {
	}

	public CardDateInfo(String type) {
		this.type = type;
	}
}