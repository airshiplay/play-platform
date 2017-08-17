package com.airshiplay.play.main.repo;

import com.airshiplay.play.main.entity.UserCredentialsEntity;
import com.airshiplay.play.repo.jpa.BaseJpaRepository;

public interface UserCredentialsEntityRepository extends BaseJpaRepository<UserCredentialsEntity, Long> {

	UserCredentialsEntity findByAccountAndType(String account, String type);

}
