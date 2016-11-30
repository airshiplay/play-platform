package com.airshiplay.play.obd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airshiplay.play.main.service.EntityService;
import com.airshiplay.play.obd.entity.OrderEntity;
import com.airshiplay.play.obd.repo.OrderEntityRepository;

@Service
public class OrderEntityService extends EntityService<OrderEntity, Long> {
	
	@Autowired
	private OrderEntityRepository orderEntityRepository;
}
