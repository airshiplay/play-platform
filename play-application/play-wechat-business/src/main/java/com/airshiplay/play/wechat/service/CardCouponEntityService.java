package com.airshiplay.play.wechat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airshiplay.play.main.service.EntityService;
import com.airshiplay.play.wechat.entity.CardCouponEntity;
import com.airshiplay.play.wechat.repo.CardCouponEntityRepository;
/**
 * 卡券管理
 *
 * @author  lig
 * @version 
 */
@Service
public class CardCouponEntityService extends EntityService<CardCouponEntity, Long> {
	
	@Autowired
	private CardCouponEntityRepository cardCouponEntityRepository;
}
