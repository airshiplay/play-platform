package com.airshiplay.play.wechat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airshiplay.play.main.service.EntityService;
import com.airshiplay.play.wechat.entity.AttentionUserEntity;
import com.airshiplay.play.wechat.entity.QAttentionUserEntity;
import com.airshiplay.play.wechat.repo.AttentionUserEntityRepository;

@Service
public class AttentionUserEntityService extends EntityService<AttentionUserEntity, Long> {

	@Autowired
	private AttentionUserEntityRepository wechatUserEntityRepository;

	public AttentionUserEntity findByAppidAndOpenId(String appid, String openid) {
		return wechatUserEntityRepository.findOne(QAttentionUserEntity.attentionUserEntity.openid.eq(openid).and(QAttentionUserEntity.attentionUserEntity.config.appId.eq(appid)));
	}
	
	public AttentionUserEntity findByWechatIdAndOpenId(String wechatId, String openid) {
		return wechatUserEntityRepository.findOne(QAttentionUserEntity.attentionUserEntity.openid.eq(openid).and(QAttentionUserEntity.attentionUserEntity.config.wechatId.eq(wechatId)));
	}
}
