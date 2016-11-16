package com.airshiplay.play.bbs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airshiplay.play.bbs.entity.TabEntity;
import com.airshiplay.play.bbs.repo.TabEntityRepository;
import com.airshiplay.play.main.service.EntityService;

@Service
public class TabEntityService extends EntityService<TabEntity, Long> {

	@Autowired
	private TabEntityRepository tabEntityRepository;
	
	public TabEntity findByCode(String code) {
		return tabEntityRepository.findByCode(code);
	}
	
}
