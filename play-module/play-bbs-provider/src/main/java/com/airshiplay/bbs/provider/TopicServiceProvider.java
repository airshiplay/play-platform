package com.airshiplay.bbs.provider;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.google.common.base.Strings;
import com.airshiplay.bbs.api.TopicService;
import com.airshiplay.bbs.domain.Topic;
import com.airshiplay.play.bbs.entity.TabEntity;
import com.airshiplay.play.bbs.entity.TopicEntity;
import com.airshiplay.play.bbs.service.TabEntityService;
import com.airshiplay.play.bbs.service.TopicEntityService;
import com.airshiplay.play.main.entity.UserEntity;
import com.airshiplay.play.main.service.UserEntityService;
import com.airshiplay.main.provider.UserServiceProvider;

@com.alibaba.dubbo.config.annotation.Service
@Service
public class TopicServiceProvider implements TopicService {

	@Autowired
	private TopicEntityService topicEntityService;
	
	@Autowired
	private TabEntityService tabEntityService;
	
	@Autowired
	private UserEntityService userEntityService;
	
	@Autowired
	private TabServiceProvider tabServiceProvider;
	
	@Autowired
	private UserServiceProvider userServiceProvider;

	@Override
	public Topic findOne(Long id) {
		return toDetailDomain(topicEntityService.findOne(id));
	}

	@Override
	public Page<Topic> findAll(Pageable pageable) {
		return topicEntityService.findAll(pageable).map(this::toSimpleDomain);
	}

	public Topic toDetailDomain(TopicEntity entity) {
		Topic topic = toSimpleDomain(entity);
		topic.setContent(entity.getContent());
		return topic;
	}

	public Topic toSimpleDomain(TopicEntity entity) {
		Topic topic = new Topic();
		topic.setId(entity.getId());
		topic.setTitle(entity.getTitle());
		topic.setGood(entity.isGood());
		topic.setPublishedDate(entity.getPublishedDate());

		UserEntity publisher = entity.getPublisher();
		if (publisher != null) {
			topic.setPublisher(userServiceProvider.toDomain(publisher));
		}

		topic.setReplyCount(entity.getReplyCount());
		topic.setStick(entity.isStick());

		TabEntity tab = entity.getTab();
		if (tab != null) {
			topic.setTab(tabServiceProvider.toDomain(tab));
		}

		topic.setViewCount(entity.getViewCount());
		return topic;
	}

	@Override
	public void save(Topic topic) {
		Assert.notNull(topic);
		TopicEntity entity = topic.getId() == null ? topicEntityService.newEntity():topicEntityService.findOne(topic.getId());
		entity.setTitle(topic.getTitle());
		entity.setContent(topic.getContent());
		
		if(entity.isNew()) {
			entity.setPublishedDate(new Date());
			
			Assert.notNull(topic.getPublisher());
			Assert.notNull(topic.getPublisher().getId());
			entity.setPublisher(userEntityService.findOne(topic.getPublisher().getId()));
		}
		
		Long tabId = topic.getTab().getId();
		TabEntity tabEntity = tabEntityService.findOne(tabId);
		
		entity.setTab(tabEntity);
		
		topicEntityService.save(entity);
	}

	@Override
	public Page<Topic> findByTab(String tabCode, Pageable pageable) {
		if(Strings.isNullOrEmpty(tabCode)) {
			return findAll(pageable);
		}
		TabEntity tabEntity = tabEntityService.findByCode(tabCode);
		return topicEntityService.findBy(tabEntity, pageable).map(this::toSimpleDomain);
	}

	@Override
	public Page<Topic> findGood(Pageable pageable) {
		return topicEntityService.findGood(pageable).map(this::toSimpleDomain);
	}

}
