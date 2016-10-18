package com.airshiplay.play.main.repo;

import com.airshiplay.play.main.entity.AreaEntity;
import com.airshiplay.play.main.entity.AreaEntity.AreaType;
import com.airshiplay.play.repo.jpa.HierarchicalJpaRepository;

public interface AreaEntityRepository extends HierarchicalJpaRepository<AreaEntity, Long> {

	AreaEntity findByFullNameAndType(String fullName,AreaType type);

}
