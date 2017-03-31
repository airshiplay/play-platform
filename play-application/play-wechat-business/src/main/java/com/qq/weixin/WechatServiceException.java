package com.qq.weixin;

public class WechatServiceException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int errorCode;

	public WechatServiceException(int errorCode, String errmsg) {
		super(errmsg);
		this.errorCode = errorCode;
	}

	public WechatServiceException(String errmsg, Throwable thr) {
		super(errmsg, thr);
	}
}
