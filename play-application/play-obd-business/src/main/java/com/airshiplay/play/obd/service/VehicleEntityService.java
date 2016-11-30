package com.airshiplay.play.obd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airshiplay.play.main.service.EntityService;
import com.airshiplay.play.obd.entity.VehicleEntity;
import com.airshiplay.play.obd.repo.VehicleEntityRepository;

@Service
public class VehicleEntityService extends EntityService<VehicleEntity, Long> {
	
	@Autowired
	private VehicleEntityRepository vehicleEntityRepository;
}
