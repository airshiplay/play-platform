package com.airshiplay.play.app.service;

import com.airlenet.repo.jpa.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airshiplay.play.app.entity.AppEntity;
import com.airshiplay.play.app.entity.AppEntity.Platform;
import com.airshiplay.play.app.entity.QAppEntity;
import com.airshiplay.play.app.repo.AppEntityRepository;
import com.airshiplay.play.main.entity.QAdminUserEntity;

@Service
public class AppEntityService extends EntityService<AppEntity, Long> {
	@Autowired
	private AppEntityRepository appEntityRepository;

	public void test() {
		QAppEntity appEntity = QAppEntity.appEntity;
		QAdminUserEntity.adminUserEntity.area.name.eq("");

		appEntityRepository.findAll(appEntity.platform.eq(Platform.android));

	}

	@Override
	public AppEntity save(AppEntity entity) {
		return super.save(entity);
	}
}
