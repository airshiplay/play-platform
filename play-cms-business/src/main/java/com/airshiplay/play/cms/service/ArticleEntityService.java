package com.airshiplay.play.cms.service;

import java.util.List;

import com.airlenet.repo.jpa.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airshiplay.play.cms.entity.ArticleEntity;
import com.airshiplay.play.cms.repo.ArticleEntityRepository;

@Service
public class ArticleEntityService extends EntityService<ArticleEntity, Long> {

	@Autowired
	private ArticleEntityRepository articleEntityRepository;
	
	public List<ArticleEntity> findTop2() {
		return articleEntityRepository.findTop2ByOrderByCreatedDateDesc();
	}
	
}
