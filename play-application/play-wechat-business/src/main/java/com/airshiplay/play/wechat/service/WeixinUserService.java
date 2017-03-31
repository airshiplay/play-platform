package com.airshiplay.play.wechat.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.airshiplay.play.main.api.LogService;
import com.airshiplay.play.main.api.LogService.LogLevel;
import com.airshiplay.play.main.api.LogService.OperateType;
import com.airshiplay.play.wechat.entity.AttentionUserEntity;
import com.airshiplay.play.wechat.entity.QAttentionUserEntity;
import com.airshiplay.play.wechat.entity.WechatConfigEntity;
import com.qq.weixin.WechatServiceException;
import com.qq.weixin.WexinHttp;
import com.qq.weixin.mp.api.WexinApiUser;
import com.qq.weixin.mp.model.user.WexinUserInfoBatchReq;
import com.qq.weixin.mp.model.user.WexinUserInfoBatchResp;
import com.qq.weixin.mp.model.user.WexinUserInfoResp;
import com.qq.weixin.mp.model.user.WexinUserListResp;

@Service
public class WeixinUserService extends WeixinService {
	@Autowired
	AttentionUserEntityService wechatUserEntityService;
	@Autowired
	WechatConfigEntityService wechatConfigEntityService;

	@Autowired
	LogService logService;

	/**
	 * 
	 * 
	 * @param configId
	 *            公众号ID
	 */
	public void syncUser(Long configId) {
		WechatConfigEntity config = wechatConfigEntityService.findOne(configId);
		WexinApiUser userApi = WexinHttp.getApi(config.getAppId(), config.getAppSecret(), WexinApiUser.class);
		try {
			String nextOpenid = null;
			do {
				WexinUserListResp userList = userApi.getUserList(nextOpenid).execute().body();
				if (userList.getErrcode() != 0) {
					logService.addLog(OperateType.UPDATE, LogLevel.ERROR, "同步微信用户列表错误：" + userList.getErrcode() + userList.getErrmsg());
					throw new WechatServiceException(userList.getErrcode(), userList.getErrmsg());
				}
				if (userList.getData() == null) {
					return;
				}
				List<String> openIdList = userList.getData().getOpenid();
				List<AttentionUserEntity> entities = new ArrayList<AttentionUserEntity>(openIdList.size());
				for (String openId : openIdList) {
					if (wechatUserEntityService.findByAppidAndOpenId(config.getAppId(), openId) == null) {
						AttentionUserEntity wechatUserEntity = new AttentionUserEntity();
						wechatUserEntity.setOpenid(openId);
						wechatUserEntity.setConfig(config);
						entities.add(wechatUserEntity);
					}
				}
				wechatUserEntityService.save(entities);
				nextOpenid = userList.getNext_openid();
			} while (StringUtils.isNotEmpty(nextOpenid));
			logService.addLog(OperateType.UPDATE, LogLevel.INFO, "同步微信用户列表成功");
		} catch (IOException e) {
			logService.addLog(OperateType.UPDATE, LogLevel.ERROR, "同步微信用户列表错误：", e);
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 
	 * @param configId
	 *            公众号ID
	 */
	public void syncUserDetail(Long configId) {
		WechatConfigEntity config = wechatConfigEntityService.findOne(configId);
		int pageNum = 1, index = 0;
		do {
			Page<AttentionUserEntity> page = wechatUserEntityService.findAll(QAttentionUserEntity.attentionUserEntity.config.id.eq(configId), new PageRequest(index, 20));
			if (page.getTotalElements() == 0) {
				throw new RuntimeException("此公众号没有关注用户");
			}

			pageNum = page.getTotalPages();
			List<AttentionUserEntity> content = page.getContent();

			WexinUserInfoBatchReq req = new WexinUserInfoBatchReq();
			for (AttentionUserEntity userEntity : content)
				req.addOpenid(userEntity.getOpenid());

			WexinApiUser userApi = WexinHttp.getApi(config.getAppId(), config.getAppSecret(), WexinApiUser.class);
			try {
				WexinUserInfoBatchResp resp = userApi.getUserInfoBatch(req).execute().body();
				List<WexinUserInfoResp> users = resp.getUser_info_list();
				List<AttentionUserEntity> entities = new ArrayList<AttentionUserEntity>(users.size());
				for (WexinUserInfoResp user : users) {
					AttentionUserEntity wechatUserEntity = wechatUserEntityService.findByAppidAndOpenId(config.getAppId(), user.getOpenid());
					wechatUserEntity.setCity(user.getCity());
					wechatUserEntity.setCountry(user.getCountry());
					wechatUserEntity.setGroupid(user.getGroupid());
					wechatUserEntity.setHeadimgurl(user.getHeadimgurl());
					wechatUserEntity.setLanguage(user.getLanguage());
					wechatUserEntity.setNickname(user.getNickname());
					wechatUserEntity.setOpenid(user.getOpenid());
					wechatUserEntity.setSex(user.getSex());
					wechatUserEntity.setProvince(user.getProvince());
					wechatUserEntity.setUnionid(user.getUnionid());
					entities.add(wechatUserEntity);
				}
				wechatUserEntityService.save(entities);

			} catch (IOException e) {
				logService.addLog(OperateType.UPDATE, LogLevel.ERROR, "同步微信用户详情列表错误：", e);
				e.printStackTrace();
			}
			index++;
		} while (index < pageNum);

	}
}
