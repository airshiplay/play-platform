package com.airlenet.quartz.service;

import org.springframework.stereotype.Service;

import com.airlenet.quartz.entity.TriggerEntity;
import com.airlenet.repo.jpa.EntityService;

@Service
public class TriggerService extends EntityService<TriggerEntity, String> {

}
