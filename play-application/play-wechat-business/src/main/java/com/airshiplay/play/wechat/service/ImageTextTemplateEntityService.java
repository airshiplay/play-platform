package com.airshiplay.play.wechat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airshiplay.play.main.service.EntityService;
import com.airshiplay.play.wechat.entity.ImageTextTemplateEntity;
import com.airshiplay.play.wechat.repo.ImageTextTemplateEntityRepository;
/**
 * 图文消息
 * @author  lig
 * @version 
 */
@Service
public class ImageTextTemplateEntityService extends EntityService<ImageTextTemplateEntity, Long> {
	
	@Autowired
	private ImageTextTemplateEntityRepository imageTextTemplateEntityRepository;
}
