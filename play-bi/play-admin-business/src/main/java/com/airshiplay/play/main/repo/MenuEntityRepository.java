package com.airshiplay.play.main.repo;

import com.airlenet.play.repo.jpa.HierarchicalJpaRepository;
import com.airshiplay.play.main.entity.MenuEntity;
public interface MenuEntityRepository extends HierarchicalJpaRepository<MenuEntity, Long> {

	MenuEntity findByCode(String code);

}
