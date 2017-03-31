package com.qq.weixin.mp.model.card;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WexinCardListReq {
	private int offset;
	private int count;
	private List<String> status_list;

	public WexinCardListReq() {

	}

	public WexinCardListReq(int offset, int count) {
		this.offset = offset;
		this.count = count;
	}
}
