package com.qq.weixin.mp.model.card;

import lombok.Getter;
import lombok.Setter;

/**
 * 团购券类型
 * 
 * @author lig
 *
 */
@Getter
@Setter
public class CardInfoGroupon {
	private CardBaseInfo base_info;
	private CardAdvancedInfo advanced_info;
	private String deal_detail;
}
