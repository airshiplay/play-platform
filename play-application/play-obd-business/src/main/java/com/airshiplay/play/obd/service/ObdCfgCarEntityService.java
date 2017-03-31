package com.airshiplay.play.obd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airshiplay.play.main.service.EntityService;
import com.airshiplay.play.obd.entity.ObdCfgCarEntity;
import com.airshiplay.play.obd.repo.ObdCfgCarEntityRepository;
/**
 * 预置车型
 *
 * @author  lig
 * @version 
 */
@Service
public class ObdCfgCarEntityService extends EntityService<ObdCfgCarEntity, Long> {
	
	@Autowired
	private ObdCfgCarEntityRepository obdCfgCarEntityRepository;
}
