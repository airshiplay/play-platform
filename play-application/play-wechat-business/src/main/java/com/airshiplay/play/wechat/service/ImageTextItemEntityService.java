package com.airshiplay.play.wechat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airshiplay.play.main.service.EntityService;
import com.airshiplay.play.wechat.entity.ImageTextItemEntity;
import com.airshiplay.play.wechat.repo.ImageTextItemEntityRepository;

/**
 * 图文子集
 * 
 * @author lig
 * @version
 */
@Service
public class ImageTextItemEntityService extends EntityService<ImageTextItemEntity, Long> {

	@Autowired
	private ImageTextItemEntityRepository imageTextItemEntityRepository;
}
