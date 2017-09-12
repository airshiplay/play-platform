package com.airshiplay.play.cms.service;

import java.util.Date;

import com.airlenet.repo.jpa.EntityService;
import org.springframework.stereotype.Service;

import com.airshiplay.play.cms.entity.FeedbackEntity;


@Service
public class FeedbackEntityService extends EntityService<FeedbackEntity, Long> {

	@Override
	public FeedbackEntity save(FeedbackEntity entity) {
		if (entity.isNew()) {
			entity.setCreatedDate(new Date());
		}
		return super.save(entity);
	}
}
