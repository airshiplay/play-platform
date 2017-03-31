package com.airshiplay.play.obd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airshiplay.play.main.service.EntityService;
import com.airshiplay.play.obd.entity.ObdOrderEntity;
import com.airshiplay.play.obd.repo.ObdOrderEntityRepository;
/**
 * 订单
 *
 * @author  lig
 * @version 
 */
@Service
public class ObdOrderEntityService extends EntityService<ObdOrderEntity, Long> {
	
	@Autowired
	private ObdOrderEntityRepository obdOrderEntityRepository;
}
