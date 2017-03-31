package com.airshiplay.play.wechat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airshiplay.play.main.service.EntityService;
import com.airshiplay.play.wechat.entity.TextTemplateEntity;
import com.airshiplay.play.wechat.repo.TextTemplateEntityRepository;
/**
 * 文本消息
 * @author  lig
 * @version 
 */
@Service
public class TextTemplateEntityService extends EntityService<TextTemplateEntity, Long> {
	
	@Autowired
	private TextTemplateEntityRepository textTemplateEntityRepository;
}
