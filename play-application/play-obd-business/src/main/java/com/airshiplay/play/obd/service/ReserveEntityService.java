package com.airshiplay.play.obd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airshiplay.play.main.service.EntityService;
import com.airshiplay.play.obd.entity.ReserveEntity;
import com.airshiplay.play.obd.repo.ReserveEntityRepository;

@Service
public class ReserveEntityService extends EntityService<ReserveEntity, Long> {
	@Autowired
	ReserveEntityRepository reserveEntityRepository;
}
