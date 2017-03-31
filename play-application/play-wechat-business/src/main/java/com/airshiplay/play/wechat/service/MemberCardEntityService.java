package com.airshiplay.play.wechat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airshiplay.play.main.service.EntityService;
import com.airshiplay.play.wechat.entity.MemberCardEntity;
import com.airshiplay.play.wechat.repo.MemberCardEntityRepository;
/**
 * 会员卡
 *
 * @author  lig
 * @version 
 */
@Service
public class MemberCardEntityService extends EntityService<MemberCardEntity, Long> {
	
	@Autowired
	private MemberCardEntityRepository memberCardEntityRepository;
}
