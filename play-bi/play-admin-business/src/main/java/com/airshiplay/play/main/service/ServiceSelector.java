package com.airshiplay.play.main.service;

import com.google.common.base.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ServiceSelector {

	@Autowired(required = false)
	private List<EntityService<?, ?>> entityServices;

	public EntityService<?, ?> getEntityService(final Class<?> entityClass) {
		if (entityServices != null) {
			for (EntityService<?, ?> entityService : entityServices) {
				if (Objects.equal(entityService.getEntityClass(), entityClass)) {
					return entityService;
				}
			}
		}
		return null;
	}
}
