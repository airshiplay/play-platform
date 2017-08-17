package com.airshiplay.play.main.repo;

import java.util.List;

import com.airshiplay.play.main.entity.DictEntity;
import com.airshiplay.play.repo.jpa.HierarchicalJpaRepository;

public interface DictEntityRepository extends
		HierarchicalJpaRepository<DictEntity, Long> {
	List<DictEntity> findByType(String type);

	int countByType(String type);

}
