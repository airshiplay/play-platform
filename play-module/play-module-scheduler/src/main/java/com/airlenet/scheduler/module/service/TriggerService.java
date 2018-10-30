package com.airlenet.scheduler.module.service;

import org.springframework.stereotype.Service;

import com.airlenet.data.jpa.EntityService;
import com.airlenet.scheduler.module.entity.TriggerEntity;
import com.airlenet.scheduler.module.entity.TriggerPrimaryKey;

@Service
public class TriggerService extends EntityService<TriggerEntity, TriggerPrimaryKey> {

}
