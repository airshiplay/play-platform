package com.qq.weixin.mp.model.report;

/**
 * 结果类--获取消息发送分布数据
 * @author luweichao
 *
 * 2015年1月27日
 */
public class WxDataCubeStreamMsgDistInfo extends WxDataCubeStreamMsgInfo{
 
	private String  count_interval;

	public String getCount_interval() {
		return count_interval;
	}

	public void setCount_interval(String count_interval) {
		this.count_interval = count_interval;
	}
	
}
