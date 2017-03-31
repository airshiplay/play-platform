package com.airshiplay.play.obd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airshiplay.play.main.service.EntityService;
import com.airshiplay.play.obd.entity.CarAlarmEntity;
import com.airshiplay.play.obd.repo.CarAlarmEntityRepository;
/**
 * 车辆告警
 *
 * @author  lig
 * @version 
 */
@Service
public class CarAlarmEntityService extends EntityService<CarAlarmEntity, Long> {
	
	@Autowired
	private CarAlarmEntityRepository carAlarmEntityRepository;
}
