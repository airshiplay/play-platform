package com.qq.weixin.mp.api;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

import com.qq.weixin.mp.model.card.WexinCardActivateReq;
import com.qq.weixin.mp.model.card.WexinCardActivateResp;
import com.qq.weixin.mp.model.card.WexinCardCreateReq;
import com.qq.weixin.mp.model.card.WexinCardCreateResp;
import com.qq.weixin.mp.model.card.WexinCardListReq;
import com.qq.weixin.mp.model.card.WexinCardListResp;
import com.qq.weixin.mp.model.card.WexinCardMemberUpdateReq;
import com.qq.weixin.mp.model.card.WexinCardMemberUpdateResp;
import com.qq.weixin.mp.model.card.WexinCardQrReq;
import com.qq.weixin.mp.model.card.WexinCardQrResp;
import com.qq.weixin.mp.model.card.WexinCardReq;
import com.qq.weixin.mp.model.card.WexinCardResp;
import com.qq.weixin.mp.model.card.WexinCardUpdateUserReq;
import com.qq.weixin.mp.model.card.WexinCardUpdateUserResp;
import com.qq.weixin.mp.model.card.WexinCardUserReq;
import com.qq.weixin.mp.model.card.WexinCardUserResp;
import com.qq.weixin.mp.model.card.WexinMediaResp;

public interface WexinApiCard {
	public static final String baseUrl = "https://api.weixin.qq.com/card/";

	/**
	 * 创建会员卡
	 * 
	 * @param req
	 * @return
	 */
	@POST("create")
	@Headers("access_token: 0")
	Call<WexinCardCreateResp> createCard(@Body WexinCardCreateReq req);

	/**
	 * 激活会员卡
	 * 
	 * @param req
	 * @return
	 */
	@POST("membercard/activate")
	@Headers("access_token: 0")
	Call<WexinCardActivateResp> activateCard(@Body WexinCardActivateReq req);

	/**
	 * 更新会员信息
	 * 
	 * @param req
	 * @return
	 */
	@POST("membercard/updateuser")
	@Headers("access_token: 0")
	Call<WexinCardUpdateUserResp> updateCardUser(@Body WexinCardUpdateUserReq req);

	/**
	 * 获取会员信息
	 * 
	 * @param req
	 * @return
	 */
	@POST("membercard/userinfo/get")
	@Headers("access_token: 0")
	Call<WexinCardUserResp> getCardUser(@Body WexinCardUserReq req);

	/**
	 * 卡券媒体接口
	 * 
	 * @param multipartBody
	 * @return
	 */

	@POST("https://api.weixin.qq.com/cgi-bin/media/uploadimg")
	// @POST("http://172.16.1.52:80/11")
	@Headers("access_token: 0")
	// @Multipart
	Call<WexinMediaResp> uploadImage(@Body RequestBody body);

	// https://api.weixin.qq.com/card/
	/**
	 * 领取会员卡二维码
	 * 
	 * @param req
	 * @return
	 */
	@POST("qrcode/create")
	@Headers("access_token: 0")
	Call<WexinCardQrResp> createCardQR(@Body WexinCardQrReq req);

	/**
	 * 卡券信息获取
	 * 
	 * @param req
	 * @return
	 */
	@POST("get")
	@Headers("access_token: 0")
	Call<WexinCardResp> getCard(@Body WexinCardReq req);

	@POST("update")
	@Headers("access_token: 0")
	Call<WexinCardMemberUpdateResp> updateCard(@Body WexinCardMemberUpdateReq req);

	@POST("batchget")
	@Headers("access_token: 0")
	Call<WexinCardListResp> batchget(@Body WexinCardListReq req);
}
