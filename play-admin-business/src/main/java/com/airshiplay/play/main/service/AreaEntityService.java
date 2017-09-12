package com.airshiplay.play.main.service;

import com.airlenet.repo.jpa.HierarchicalEntityService;
import com.airshiplay.play.main.entity.AreaEntity;
import com.airshiplay.play.main.entity.AreaEntity.AreaType;
import com.airshiplay.play.main.repo.AreaEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AreaEntityService extends HierarchicalEntityService<AreaEntity, Long> {

	@Autowired
	private AreaEntityRepository areaEntityRepository;

	@Transactional(readOnly = true)
	public AreaEntity findByFullNameAndType(String fullName, AreaType type) {
		return areaEntityRepository.findByFullNameAndType(fullName, type);
	}
}
