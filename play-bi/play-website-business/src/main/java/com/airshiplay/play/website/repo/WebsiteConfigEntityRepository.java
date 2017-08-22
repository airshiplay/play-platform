package com.airshiplay.play.website.repo;

import com.airlenet.play.repo.jpa.BaseJpaRepository;
import com.airshiplay.play.website.entity.WebsiteConfigEntity;

public interface WebsiteConfigEntityRepository extends BaseJpaRepository<WebsiteConfigEntity, Long> {

	WebsiteConfigEntity findTopByOrderByCreatedDateDesc();

}
