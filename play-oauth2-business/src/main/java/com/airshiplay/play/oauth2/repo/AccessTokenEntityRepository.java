package com.airshiplay.play.oauth2.repo;

import com.airshiplay.play.oauth2.entity.AccessTokenEntity;
import com.airshiplay.play.repo.jpa.BaseJpaRepository;

public interface AccessTokenEntityRepository extends
		BaseJpaRepository<AccessTokenEntity, Long> {
	public AccessTokenEntity findOneByTokenId(String tokenId);
}