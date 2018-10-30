package com.airlenet.scheduler.module.service;

import com.airlenet.scheduler.module.entity.JobPrimaryKey;
import org.springframework.stereotype.Service;

import com.airlenet.data.jpa.EntityService;
import com.airlenet.scheduler.module.entity.JobEntity;
import com.airlenet.scheduler.module.entity.JobPrimaryKey;

@Service
public class JobService extends EntityService<JobEntity, JobPrimaryKey> {

}
