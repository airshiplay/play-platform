package com.airshiplay.play.obd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airshiplay.play.main.service.EntityService;
import com.airshiplay.play.obd.entity.ObdMemberRankEntity;
import com.airshiplay.play.obd.repo.ObdMemberRankEntityRepository;

@Service
public class ObdMemberRankEntityService extends EntityService<ObdMemberRankEntity, Long> {
	
	@Autowired
	private ObdMemberRankEntityRepository memberRankEntityRepository;
}
