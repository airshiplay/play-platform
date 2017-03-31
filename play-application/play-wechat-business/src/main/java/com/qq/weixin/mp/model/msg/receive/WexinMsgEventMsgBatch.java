package com.qq.weixin.mp.model.msg.receive;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * 群发消息结果事件推送
 * <p>
 * 
 * <pre>
 * <xml>
 * <ToUserName><![CDATA[gh_3e8adccde292]]></ToUserName>
 * <FromUserName><![CDATA[oR5Gjjl_eiZoUpGozMo7dbBJ362A]]></FromUserName>
 * <CreateTime>1394524295</CreateTime>
 * <MsgType><![CDATA[event]]></MsgType>
 * <Event><![CDATA[MASSSENDJOBFINISH]]></Event>
 * <MsgID>1988</MsgID>
 * <Status><![CDATA[sendsuccess]]></Status>
 * <TotalCount>100</TotalCount>
 * <FilterCount>80</FilterCount>
 * <SentCount>75</SentCount>
 * <ErrorCount>5</ErrorCount>
 * </xml>
 * </pre>
 * 
 * @author lig
 *
 */
@Root(name = "xml")
public class WexinMsgEventMsgBatch extends WexinMsgFactory.WechatMsgParent {

	@Element(name = "MsgID")
	private String msgID;
	@Element(name = "Status", data = true)
	private String status;
	@Element(name = "TotalCount")
	private Long totalCount;
	@Element(name = "FilterCount")
	private Long filterCount;
	@Element(name = "SentCount")
	private Long sentCount;
	@Element(name = "ErrorCount")
	private Long errorCount;

	public String getMsgID() {
		return msgID;
	}

	public void setMsgID(String msgID) {
		this.msgID = msgID;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	public Long getFilterCount() {
		return filterCount;
	}

	public void setFilterCount(Long filterCount) {
		this.filterCount = filterCount;
	}

	public Long getSentCount() {
		return sentCount;
	}

	public void setSentCount(Long sentCount) {
		this.sentCount = sentCount;
	}

	public Long getErrorCount() {
		return errorCount;
	}

	public void setErrorCount(Long errorCount) {
		this.errorCount = errorCount;
	}

}
