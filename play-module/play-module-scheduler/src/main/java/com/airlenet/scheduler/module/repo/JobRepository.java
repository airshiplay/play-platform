package com.airlenet.scheduler.module.repo;

import com.airlenet.data.jpa.EntityRepository;
import com.airlenet.scheduler.module.entity.JobPrimaryKey;
import com.airlenet.scheduler.module.entity.JobEntity;
import com.airlenet.scheduler.module.entity.JobPrimaryKey;

public interface JobRepository extends EntityRepository<JobEntity, JobPrimaryKey> {

}
