package com.airshiplay.play.plugin.oauth.service;

import java.util.List;

import com.airlenet.repo.jpa.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airshiplay.play.plugin.oauth.model.OauthUserEntity;
import com.airshiplay.play.plugin.oauth.model.QOauthUserEntity;
import com.airshiplay.play.plugin.oauth.repo.OauthUserRepository;
import com.google.common.collect.Lists;

@Service
public class OauthUserService extends EntityService<OauthUserEntity, Long> {

	@Autowired
	private OauthUserRepository oauthUserRepository;

	public OauthUserEntity findByOauthPluginIdAndUserId(String oauthPluginId, String userId) {
		return oauthUserRepository.findByOauthPluginIdAndUserId(oauthPluginId, userId);
	}

	/**
	 * 解除 管理用户 第三方账号绑定
	 * 
	 * @param oauthPluginId
	 * @param userId
	 */
	public void unBindAdminUser(String oauthPluginId, Long userId) {
		OauthUserEntity user = oauthUserRepository.findOne(QOauthUserEntity.oauthUserEntity.adminOwner.id.eq(userId).and(QOauthUserEntity.oauthUserEntity.oauthPluginId.eq(oauthPluginId)));
		if (user != null) {
			if (user.getMemberOwner() != null) {
				user.setAdminOwner(null);
				oauthUserRepository.save(user);
			} else {
				oauthUserRepository.delete(user.getId());
			}
		}
	}

	public List<OauthUserEntity> findByAdminUserId(Long userId) {
		return Lists.newArrayList(oauthUserRepository.findAll(QOauthUserEntity.oauthUserEntity.adminOwner.id.eq(userId)));
	}

	public List<OauthUserEntity> findByMemberUserId(Long userId) {
		return Lists.newArrayList(oauthUserRepository.findAll(QOauthUserEntity.oauthUserEntity.memberOwner.id.eq(userId)));
	}
}
