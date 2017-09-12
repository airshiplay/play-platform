package com.airshiplay.play.cms.service;

import com.airlenet.repo.jpa.HierarchicalEntityService;
import org.springframework.stereotype.Service;

import com.airshiplay.play.cms.entity.ArticleCategoryEntity;

@Service
public class ArticleCategoryEntityService extends HierarchicalEntityService<ArticleCategoryEntity, Long> {

}
