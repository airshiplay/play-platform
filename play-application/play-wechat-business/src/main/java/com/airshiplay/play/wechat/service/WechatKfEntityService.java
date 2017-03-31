package com.airshiplay.play.wechat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airshiplay.play.main.service.EntityService;
import com.airshiplay.play.wechat.entity.WechatKfEntity;
import com.airshiplay.play.wechat.repo.WechatKfEntityRepository;
/**
 * 微信客服
 *
 * @author  lig
 * @version 
 */
@Service
public class WechatKfEntityService extends EntityService<WechatKfEntity, Long> {
	
	@Autowired
	private WechatKfEntityRepository wechatKfEntityRepository;
}
