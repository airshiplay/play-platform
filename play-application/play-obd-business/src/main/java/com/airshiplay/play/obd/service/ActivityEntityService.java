package com.airshiplay.play.obd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airshiplay.play.main.service.EntityService;
import com.airshiplay.play.obd.entity.ActivityEntity;
import com.airshiplay.play.obd.repo.ActivityEntityRepository;

@Service
public class ActivityEntityService extends EntityService<ActivityEntity, Long> {
	
	@Autowired
	private ActivityEntityRepository activityEntityRepository;
}
