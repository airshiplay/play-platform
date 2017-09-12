package com.airshiplay.play.main.repo;

import com.airlenet.repo.jpa.HierarchicalEntityRepository;
import com.airshiplay.play.main.entity.MenuEntity;

public interface MenuEntityRepository extends HierarchicalEntityRepository<MenuEntity, Long> {

	MenuEntity findByCode(String code);

}
