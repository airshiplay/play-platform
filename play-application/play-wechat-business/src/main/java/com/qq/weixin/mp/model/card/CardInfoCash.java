package com.qq.weixin.mp.model.card;

import lombok.Getter;
import lombok.Setter;

/**
 * 代金券类型。
 * 
 * @author lig
 *
 */
@Getter
@Setter
public class CardInfoCash {
	private CardBaseInfo base_info;
	private CardAdvancedInfo advanced_info;
	private int least_cost;
	private int reduce_cost;
}
