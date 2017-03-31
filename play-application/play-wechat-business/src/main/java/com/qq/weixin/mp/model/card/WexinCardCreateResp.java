package com.qq.weixin.mp.model.card;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * @author lig
 *
 */
@Getter
@Setter
@ToString
public class WexinCardCreateResp {

	private int errcode;
	private String errmsg;
	private String card_id;
}
