package com.airshiplay.play.main.repo;

import com.airlenet.repo.jpa.HierarchicalEntityRepository;
import com.airshiplay.play.main.entity.DictEntity;

import java.util.List;

public interface DictEntityRepository extends
		HierarchicalEntityRepository<DictEntity, Long> {
	List<DictEntity> findByType(String type);

	int countByType(String type);

}
