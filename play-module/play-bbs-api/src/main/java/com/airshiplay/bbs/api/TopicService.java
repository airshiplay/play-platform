package com.airshiplay.bbs.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.airshiplay.bbs.domain.Topic;

public interface TopicService {

	Topic findOne(Long id);

	Page<Topic> findAll(Pageable pageable);
	
	Page<Topic> findGood(Pageable pageable);
	
	Page<Topic> findByTab(String tabCode, Pageable pageable);
	
	void save(Topic topic);

}
