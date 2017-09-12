package com.airshiplay.play.cms.service;

import java.util.List;

import com.airlenet.repo.jpa.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.querydsl.QSort;
import org.springframework.stereotype.Service;

import com.airshiplay.play.cms.entity.AdEntity;
import com.airshiplay.play.cms.entity.AdEntity.Type;
import com.airshiplay.play.cms.entity.QAdEntity;
import com.airshiplay.play.cms.repo.AdEntityRepository;
 import com.google.common.collect.Lists;

@Service
public class AdEntityService extends EntityService<AdEntity, Long> {
	@Autowired
	AdEntityRepository adEntityRepository;

	public List<AdEntity> findTop3(String positionCode, Type type) {
		return Lists.newArrayList( adEntityRepository.findAll(
				QAdEntity.adEntity.type.eq(Type.image).and(
						QAdEntity.adEntity.adPosition.code.eq(positionCode)),
				new PageRequest(0, 3, new QSort(QAdEntity.adEntity.sortNo.asc(),QAdEntity.adEntity.createdDate
						.desc()))).getContent());
	}
}
