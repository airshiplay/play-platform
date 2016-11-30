package com.airshiplay.play.obd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airshiplay.play.main.service.EntityService;
import com.airshiplay.play.obd.entity.ObdDeviceEntity;
import com.airshiplay.play.obd.repo.ObdDeviceEntityRepository;

@Service
public class ObdDeviceEntityService extends EntityService<ObdDeviceEntity, Long> {
	
	@Autowired
	private ObdDeviceEntityRepository obdDeviceEntityRepository;
}
