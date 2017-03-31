package com.qq.weixin.mp.model.card;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WexinCardMemberUpdateReq {
	private String card_id;
	private CardInfoMember member_card;

}
