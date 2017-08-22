package com.airshiplay.play.main.repo;

import com.airlenet.play.repo.jpa.HierarchicalJpaRepository;
import com.airshiplay.play.main.entity.DictEntity;

import java.util.List;

public interface DictEntityRepository extends
		HierarchicalJpaRepository<DictEntity, Long> {
	List<DictEntity> findByType(String type);

	int countByType(String type);

}
