package com.airshiplay.play.cms.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.airshiplay.play.cms.entity.SubscribeEntity;
import com.airshiplay.play.main.service.EntityService;

@Service
public class SubscribeEntityService extends EntityService<SubscribeEntity, Long> {

	@Override
	public SubscribeEntity save(SubscribeEntity entity) {
		if (entity.isNew()) {
			entity.setCreatedDate(new Date());
		}
		return super.save(entity);
	}
}
