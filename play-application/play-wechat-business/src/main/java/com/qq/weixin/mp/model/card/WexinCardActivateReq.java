package com.qq.weixin.mp.model.card;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author lig
 *
 */

@Getter
@Setter
public class WexinCardActivateReq {

	private int init_bonus;
	private int init_balance;
	private String membership_number;
	private String code;
	private String card_id;
	private String background_pic_url;
	private String init_custom_field_value1;
}
