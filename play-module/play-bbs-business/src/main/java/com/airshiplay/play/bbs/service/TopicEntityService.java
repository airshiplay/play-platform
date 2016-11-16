package com.airshiplay.play.bbs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.airshiplay.play.bbs.entity.TabEntity;
import com.airshiplay.play.bbs.entity.TopicEntity;
import com.airshiplay.play.bbs.repo.TopicEntityRepository;
import com.airshiplay.play.main.service.EntityService;

@Service
public class TopicEntityService extends EntityService<TopicEntity, Long> {

	@Autowired
	private TopicEntityRepository topicEntityRepository;
	
	public Page<TopicEntity> findBy(TabEntity tab, Pageable pageable) {
		return topicEntityRepository.findByTab(tab, pageable);
	}
	
	public Page<TopicEntity> findGood(Pageable pageable) {
		return topicEntityRepository.findByGoodTrue(pageable);
	}
}
