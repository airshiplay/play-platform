package com.qq.weixin.mp.aes;

import java.io.IOException;

import com.qq.weixin.WexinHttp;
import com.qq.weixin.mp.api.WexinApiMsgKefu;
import com.qq.weixin.mp.model.msg.send.kf.WexinKfMsgReq;

public class WexinKeFuApiTest {

	public static void main(String[] args) throws IOException {
		String appid = "wx2ab7d2c9e9640c8b";
		String appSecret = "15be487a1ac6f3d6aea6c39b88cc35df";
		WexinApiMsgKefu api = WexinHttp.getApi(appid, appSecret, WexinApiMsgKefu.class);

		api.sendMsg(WexinKfMsgReq.getTextMsg("oTl2euLs6iswJU5kt2H4FtLZAiZk", "hello test")).execute().body();

		api.sendMsg(WexinKfMsgReq.getCardMsg("oTl2euLs6iswJU5kt2H4FtLZAiZk", "pTl2euP3X_0hrEnXfaAnAKvLc-z0")).execute().body();
		api.sendMsg(WexinKfMsgReq.getCardMsg("oTl2euLs6iswJU5kt2H4FtLZAiZk", "pTl2euMy7ECf8ot6j4Q4iPfgRwlk")).execute().body();

	}

}
