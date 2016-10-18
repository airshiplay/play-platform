package com.airshiplay.play.cms.repo;

import com.airshiplay.play.cms.entity.ArticleCategoryEntity;
import com.airshiplay.play.repo.jpa.HierarchicalJpaRepository;

public interface ArticleCategoryEntityRepository extends HierarchicalJpaRepository<ArticleCategoryEntity, Long> {

}
