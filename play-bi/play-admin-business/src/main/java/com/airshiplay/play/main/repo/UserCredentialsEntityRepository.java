package com.airshiplay.play.main.repo;

import com.airlenet.play.repo.jpa.BaseJpaRepository;
import com.airshiplay.play.main.entity.UserCredentialsEntity;

public interface UserCredentialsEntityRepository extends BaseJpaRepository<UserCredentialsEntity, Long> {

	UserCredentialsEntity findByAccountAndType(String account, String type);

}
