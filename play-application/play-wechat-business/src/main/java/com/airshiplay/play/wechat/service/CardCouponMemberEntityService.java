package com.airshiplay.play.wechat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airshiplay.play.main.service.EntityService;
import com.airshiplay.play.wechat.entity.CardCouponMemberEntity;
import com.airshiplay.play.wechat.repo.CardCouponMemberEntityRepository;
/**
 * 会员卡券
 *
 * @author  lig
 * @version 
 */
@Service
public class CardCouponMemberEntityService extends EntityService<CardCouponMemberEntity, Long> {
	
	@Autowired
	private CardCouponMemberEntityRepository cardCouponMemberEntityRepository;
}
