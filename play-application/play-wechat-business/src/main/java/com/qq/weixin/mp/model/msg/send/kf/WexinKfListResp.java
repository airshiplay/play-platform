package com.qq.weixin.mp.model.msg.send.kf;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class WexinKfListResp {
	private List<WexinKefu> kf_list;
}
