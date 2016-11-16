package com.airshiplay.play.bbs.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.airshiplay.play.bbs.entity.TabEntity;
import com.airshiplay.play.bbs.entity.TopicEntity;
import com.airshiplay.play.repo.jpa.BaseJpaRepository;

public interface TopicEntityRepository extends BaseJpaRepository<TopicEntity, Long> {

	Page<TopicEntity> findByTab(TabEntity tab, Pageable pageable);
	
	Page<TopicEntity> findByGoodTrue(Pageable pageable);
	
}
