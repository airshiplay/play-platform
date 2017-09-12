package com.airshiplay.play.main.repo;

import com.airlenet.repo.jpa.EntityRepository;
import com.airshiplay.play.main.entity.UserCredentialsEntity;

public interface UserCredentialsEntityRepository extends EntityRepository<UserCredentialsEntity, Long> {

	UserCredentialsEntity findByAccountAndType(String account, String type);

}
