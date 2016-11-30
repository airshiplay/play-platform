package com.airshiplay.play.obd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airshiplay.play.main.service.EntityService;
import com.airshiplay.play.obd.entity.VehicleAlarmEntity;
import com.airshiplay.play.obd.repo.VehicleAlarmEntityRepository;

@Service
public class VehicleAlarmEntityService extends EntityService<VehicleAlarmEntity, Long> {
	
	@Autowired
	private VehicleAlarmEntityRepository vehicleAlarmEntityRepository;
}
