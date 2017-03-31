package com.airshiplay.play.wechat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airshiplay.play.main.service.EntityService;
import com.airshiplay.play.wechat.entity.MaterialEntity;
import com.airshiplay.play.wechat.repo.MaterialEntityRepository;
/**
 * 素材
 * @author  lig
 * @version 
 */
@Service
public class MaterialEntityService extends EntityService<MaterialEntity, Long> {
	
	@Autowired
	private MaterialEntityRepository materialEntityRepository;
}
