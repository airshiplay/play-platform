package com.qq.weixin.mp.model.card;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WexinCardListResp {
	private int errcode;
	private String errmsg;
	private List<String> card_id_list;
	private int total_num;
}
