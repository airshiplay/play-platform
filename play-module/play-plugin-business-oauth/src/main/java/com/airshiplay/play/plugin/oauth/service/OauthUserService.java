package com.airshiplay.play.plugin.oauth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airshiplay.play.main.service.EntityService;
import com.airshiplay.play.plugin.oauth.model.OauthUserEntity;
import com.airshiplay.play.plugin.oauth.repo.OauthUserRepository;

@Service
public class OauthUserService extends EntityService<OauthUserEntity, Long> {

	@Autowired
	private OauthUserRepository oauthUserRepository;

	public OauthUserEntity findByOauthPluginIdAndUserId(String oauthPluginId, String userId) {
		return oauthUserRepository.findByOauthPluginIdAndUserId(oauthPluginId, userId);
	}
}
