package com.airshiplay.play.wechat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airshiplay.play.main.service.EntityService;
import com.airshiplay.play.wechat.entity.AttentionReplyEntity;
import com.airshiplay.play.wechat.entity.QAttentionReplyEntity;
import com.airshiplay.play.wechat.repo.AttentionReplyEntityRepository;

/**
 * 关注回复
 *
 * @author lig
 * @version
 */
@Service
public class AttentionReplyEntityService extends EntityService<AttentionReplyEntity, Long> {

	@Autowired
	private AttentionReplyEntityRepository attentionReplyEntityRepository;

	public AttentionReplyEntity findOneByWechatId(String wechatId) {
		return attentionReplyEntityRepository.findOne(QAttentionReplyEntity.attentionReplyEntity.config.wechatId.eq(wechatId));
	}
}
