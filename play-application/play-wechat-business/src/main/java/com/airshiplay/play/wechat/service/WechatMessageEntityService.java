package com.airshiplay.play.wechat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airshiplay.play.main.service.EntityService;
import com.airshiplay.play.wechat.entity.WechatMessageEntity;
import com.airshiplay.play.wechat.repo.WechatMessageEntityRepository;
/**
 * 消息管理
 *
 * @author  lig
 * @version 
 */
@Service
public class WechatMessageEntityService extends EntityService<WechatMessageEntity, Long> {
	
	@Autowired
	private WechatMessageEntityRepository wechatMessageEntityRepository;
}
