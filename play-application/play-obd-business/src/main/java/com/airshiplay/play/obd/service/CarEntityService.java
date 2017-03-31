package com.airshiplay.play.obd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airshiplay.play.main.service.EntityService;
import com.airshiplay.play.obd.entity.CarEntity;
import com.airshiplay.play.obd.repo.CarEntityRepository;
/**
 * 车辆
 *
 * @author  lig
 * @version 
 */
@Service
public class CarEntityService extends EntityService<CarEntity, Long> {
	
	@Autowired
	private CarEntityRepository carEntityRepository;
}
