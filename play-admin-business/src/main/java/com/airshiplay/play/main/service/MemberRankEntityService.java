package com.airshiplay.play.main.service;

import com.airlenet.repo.jpa.EntityService;
import com.airshiplay.play.main.entity.MemberRankEntity;
import com.airshiplay.play.main.repo.MemberRankEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberRankEntityService extends EntityService<MemberRankEntity, Long> {
	
	@Autowired
	private MemberRankEntityRepository memberRankEntityRepository;
}
