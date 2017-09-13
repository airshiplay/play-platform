package com.airshiplay.play.plugin.oauth.repo;

import com.airlenet.repo.jpa.EntityRepository;
import com.airshiplay.play.plugin.oauth.model.OauthUserEntity;

public interface OauthUserRepository extends EntityRepository<OauthUserEntity, Long> {

	OauthUserEntity findByOauthPluginIdAndUserId(String oauthPluginId, String userId);
	
}
