package com.airshiplay.play.obd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airshiplay.play.main.service.EntityService;
import com.airshiplay.play.obd.entity.Store4SServiceEntity;
import com.airshiplay.play.obd.repo.Store4SServiceEntityRepository;
/**
 * 4s店服务
 *
 * @author 
 * @version 
 */
@Service
public class Store4SServiceEntityService extends EntityService<Store4SServiceEntity, Long> {
	
	@Autowired
	private Store4SServiceEntityRepository store4SServiceEntityRepository;
}
