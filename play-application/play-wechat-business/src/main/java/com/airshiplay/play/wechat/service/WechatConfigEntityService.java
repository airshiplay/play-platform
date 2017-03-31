package com.airshiplay.play.wechat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airshiplay.play.main.service.EntityService;
import com.airshiplay.play.wechat.entity.QWechatConfigEntity;
import com.airshiplay.play.wechat.entity.WechatConfigEntity;
import com.airshiplay.play.wechat.repo.WechatConfigEntityRepository;

@Service
public class WechatConfigEntityService extends EntityService<WechatConfigEntity, Long> {

	@Autowired
	private WechatConfigEntityRepository wechatConfigEntityRepository;

	public WechatConfigEntity findConfigByAppid(String appid) {
		return wechatConfigEntityRepository.findOne(QWechatConfigEntity.wechatConfigEntity.appId.eq(appid));
	}
}
