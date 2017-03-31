package com.airshiplay.play.wechat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airshiplay.play.main.service.EntityService;
import com.airshiplay.play.wechat.entity.AttentionUserSubscribeEntity;
import com.airshiplay.play.wechat.repo.AttentionUserSubscribeEntityRepository;
/**
 * 关注用户 时间
 *
 * @author  lig
 * @version 
 */
@Service
public class AttentionUserSubscribeEntityService extends EntityService<AttentionUserSubscribeEntity, Long> {
	
	@Autowired
	private AttentionUserSubscribeEntityRepository attentionUserSubscribeEntityRepository;
}
