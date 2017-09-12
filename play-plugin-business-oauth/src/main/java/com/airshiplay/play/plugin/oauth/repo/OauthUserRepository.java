package com.airshiplay.play.plugin.oauth.repo;

import com.airshiplay.play.plugin.oauth.model.OauthUserEntity;
import com.airshiplay.play.repo.jpa.BaseJpaRepository;

public interface OauthUserRepository extends BaseJpaRepository<OauthUserEntity, Long> {

	OauthUserEntity findByOauthPluginIdAndUserId(String oauthPluginId, String userId);
	
}
