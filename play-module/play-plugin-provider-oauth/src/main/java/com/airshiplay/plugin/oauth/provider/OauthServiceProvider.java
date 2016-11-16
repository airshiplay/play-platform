package com.airshiplay.plugin.oauth.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.airshiplay.play.main.entity.UserEntity;
import com.airshiplay.play.main.service.UserEntityService;
import com.airshiplay.play.plugin.oauth.model.OauthPlugin;
import com.airshiplay.play.plugin.oauth.model.OauthUserEntity;
import com.airshiplay.play.plugin.oauth.service.OauthPluginService;
import com.airshiplay.play.plugin.oauth.service.OauthUserService;
import com.airshiplay.main.domain.User;
import com.airshiplay.plugin.oauth.api.OauthService;

@com.alibaba.dubbo.config.annotation.Service
@Service
public class OauthServiceProvider implements OauthService {

	@Autowired
	private OauthPluginService oauthPluginService;

	@Autowired
	private OauthUserService oauthUserService;

	@Autowired
	private UserEntityService userEntityService;
	
	@Override
	public User auth(String oauthPluginId, String code) {
		OauthPlugin oauthPlugin = oauthPluginService.getOauthPlugin(oauthPluginId);
		String accessToken = oauthPlugin.getAccessToken(code);
		Assert.notNull(accessToken);

		OauthUserEntity oauthUser = oauthPlugin.getOauthUser(accessToken);
		Assert.notNull(oauthUser);

		UserEntity userEntity = null;
		if (oauthUser.isNew() || oauthUser.getOwner() == null) {
			userEntity = userEntityService.newEntity();
			userEntity.setName(oauthUser.getName());
			userEntity.setUsername(oauthPluginId + "," + code);
			userEntity.setPhoto(oauthUser.getAvatarUrl());
			userEntity.setAccountExpired(false);
			userEntity.setEnabled(true);
			userEntity.setCredentialsExpired(false);
			userEntity.setLocked(false);
			userEntityService.save(userEntity);
			oauthUser.setOwner(userEntity);
			oauthUserService.save(oauthUser);
		} else {
			userEntity = oauthUser.getOwner();
		}

		User user = new User();
		if (userEntity != null) {
			user.setId(userEntity.getId());
			user.setUsername(userEntity.getUsername());
			user.setName(userEntity.getName());
			user.setEmail(userEntity.getEmail());
			user.setMobile(userEntity.getMobile());
			user.setLastLoginDate(userEntity.getLastLoginDate());
			user.setLastLoginIp(userEntity.getLastLoginIp());
			user.setSex(userEntity.getSex());
		}

		return user;
	}

	@Override
	public com.airshiplay.plugin.oauth.domain.OauthPlugin getOauthPlugin(String oauthPluginId) {
		OauthPlugin oauthPlugin = oauthPluginService.getOauthPlugin(oauthPluginId);

		com.airshiplay.plugin.oauth.domain.OauthPlugin vo = new com.airshiplay.plugin.oauth.domain.OauthPlugin();
		vo.setAuthorizationParameterMap(oauthPlugin.getAuthorizationParameterMap());
		vo.setAuthorizationUrl(oauthPlugin.getAuthorizationUrl());
		return vo;
	}

}
