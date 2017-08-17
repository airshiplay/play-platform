package com.airshiplay.play.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airshiplay.play.main.service.EntityService;
import com.airshiplay.play.main.entity.MemberRankEntity;
import com.airshiplay.play.main.repo.MemberRankEntityRepository;

@Service
public class MemberRankEntityService extends EntityService<MemberRankEntity, Long> {
	
	@Autowired
	private MemberRankEntityRepository memberRankEntityRepository;
}
