package com.qq.weixin.mp.model.msg.send.batch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import com.google.common.collect.Lists;
import com.qq.weixin.mp.model.msg.send.WexinSendMsg;

/**
 * 群发消息格式，不完善
 * 
 * @author lig
 *
 */
@Getter
@Setter
@ToString
public class WexinSendMsgAllReq {
	private Filter filter;
	private List<String> touser;
	private String towxname;
	private String msgtype;
	private WexinSendMsg voice;
	private WexinSendMsg wxcard;

	@Getter
	@Setter
	@ToString
	public static class Filter {
		private boolean is_to_all;
		private String tag_id;

		public Filter(boolean is_to_all, String tag_id) {
			this.is_to_all = is_to_all;
			this.tag_id = tag_id;
		}

	}

	/**
	 * 根据标签进行群发【订阅号与服务号认证后均可用】
	 * 
	 * @param is_to_all
	 * @param tag_id
	 * @return
	 */
	public WexinSendMsgAllReq setFilter(boolean is_to_all, String tag_id) {
		this.filter = new Filter(is_to_all, tag_id);
		return this;
	}

	/**
	 * 根据OpenID列表群发【订阅号不可用，服务号认证后可用】
	 * 
	 * @param openids
	 * @return
	 */
	public WexinSendMsgAllReq addToUser(String... openids) {
		if (this.touser == null) {
			this.touser = new ArrayList<String>();
		}
		this.touser.addAll(Lists.newArrayList(openids));
		return this;
	}

	/**
	 * 根据OpenID列表群发【订阅号不可用，服务号认证后可用】
	 * 
	 * @param openids
	 * @return
	 */
	public WexinSendMsgAllReq addToUser(List<String> openids) {
		if (this.touser == null) {
			this.touser = new ArrayList<String>();
		}
		this.touser.addAll(openids);
		return this;
	}

	public static WexinSendMsgAllReq getCardMsg(String card_id) {
		WexinSendMsgAllReq req = new WexinSendMsgAllReq();
		req.msgtype = "wxcard";
		req.wxcard = new WexinSendMsg();
		req.wxcard.setCard_id(card_id);
		return req;
	}
}
