package com.airshiplay.play.wechat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airshiplay.play.main.service.EntityService;
import com.airshiplay.play.wechat.entity.WechatMemberEntity;
import com.airshiplay.play.wechat.repo.WechatMemberEntityRepository;
/**
 * 微信会员
 *
 * @author  lig
 * @version 
 */
@Service
public class WechatMemberEntityService extends EntityService<WechatMemberEntity, Long> {
	
	@Autowired
	private WechatMemberEntityRepository wechatMemberEntityRepository;
}
