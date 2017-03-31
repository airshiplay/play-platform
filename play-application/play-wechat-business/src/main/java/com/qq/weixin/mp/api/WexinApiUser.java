package com.qq.weixin.mp.api;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

import com.qq.weixin.mp.model.user.WexinTagUserReq;
import com.qq.weixin.mp.model.user.WexinTagUserResp;
import com.qq.weixin.mp.model.user.WexinUserInfoBatchReq;
import com.qq.weixin.mp.model.user.WexinUserInfoBatchResp;
import com.qq.weixin.mp.model.user.WexinUserInfoResp;
import com.qq.weixin.mp.model.user.WexinUserListResp;
import com.qq.weixin.mp.model.user.WexinUserRemark;
import com.qq.weixin.mp.model.user.WexinUserTag;

public interface WexinApiUser  {
	public static final String baseUrl = "https://api.weixin.qq.com/cgi-bin/";

	/**
	 * 创建标签
	 * 
	 * @param wapper
	 * @return
	 */
	@POST("tags/create")
	@Headers("access_token: 0")
	public Call<WexinUserTag> createTags(@Body WexinUserTag wapper);

	/**
	 * 编辑标签
	 * 
	 * @param wapper
	 * @return
	 */
	@POST("tags/update")
	@Headers("access_token: 0")
	public Call<WexinUserTag> updateTags(@Body WexinUserTag wapper);

	/**
	 * 删除标签
	 * 
	 * @param wapper
	 * @return
	 */
	@POST("tags/delete")
	@Headers("access_token: 0")
	public Call<WexinUserTag> deleteTags(@Body WexinUserTag wapper);

	/**
	 * 获取标签下粉丝列表
	 * 
	 * @param req
	 * @return
	 */
	@POST("user/tag/get")
	@Headers("access_token: 0")
	public Call<WexinTagUserResp> getTagUser(WexinTagUserReq req);

	/**
	 * 获取用户列表
	 * 
	 * @param nextOpenid
	 * @return
	 */
	@GET("user/get")
	@Headers("access_token: 0")
	public Call<WexinUserListResp> getUserList(@Query("next_openid") String nextOpenid);

	/**
	 * 获取用户基本信息（包括UnionID机制）
	 * 
	 * @param openid
	 * @param lang
	 * @return
	 */
	@GET("user/info")
	@Headers("access_token: 0")
	public Call<WexinUserInfoResp> getUserInfo(@Query("openid") String openid, @Query("lang") String lang);

	/**
	 * 批量获取用户基本信息,最多100条
	 * 
	 * @param req
	 * @return
	 */
	@POST("user/info/batchget")
	@Headers("access_token: 0")
	public Call<WexinUserInfoBatchResp> getUserInfoBatch(@Body WexinUserInfoBatchReq req);

	@POST("user/info/updateremark")
	@Headers("access_token: 0")
	public Call<String> updateUserRemark(@Body WexinUserRemark remark);

	/**
	 * 获取公众号的黑名单列表
	 * 
	 * @param map
	 *            { "begin_openid":"OPENID1" } 当 begin_openid 为空时，默认从开头拉取。
	 * @return
	 */
	@POST("tags/members/getblacklist")
	@Headers("access_token: 0")
	public Call<String> getBlackList(@Body HashMap<String, String> map);

	/**
	 * 拉黑用户
	 * 
	 * @param map
	 *            { "opened_list":["OPENID1"," OPENID2"] }
	 * @return
	 */
	@POST("tags/members/batchblacklist")
	@Headers("access_token: 0")
	public Call<String> batchblacklist(@Body HashMap<String, String> map);

	/**
	 * 取消拉黑用户
	 * 
	 * @param map
	 *            { "opened_list":["OPENID1","OPENID2"] }
	 * @return
	 */
	@POST("tags/members/batchunblacklist")
	@Headers("access_token: 0")
	public Call<String> batchunblacklist(@Body HashMap<String, String> map);
 
}
