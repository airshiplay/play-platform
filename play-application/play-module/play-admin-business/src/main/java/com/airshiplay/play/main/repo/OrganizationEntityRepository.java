package com.airshiplay.play.main.repo;

import com.airshiplay.play.main.entity.OrganizationEntity;
import com.airshiplay.play.repo.jpa.HierarchicalJpaRepository;

public interface OrganizationEntityRepository extends HierarchicalJpaRepository<OrganizationEntity, Long> {

}
