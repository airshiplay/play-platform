package com.airshiplay.play.main.repo;

import com.airshiplay.play.main.entity.MenuEntity;
import com.airshiplay.play.repo.jpa.HierarchicalJpaRepository;

public interface MenuEntityRepository extends HierarchicalJpaRepository<MenuEntity, Long> {

	MenuEntity findByCode(String code);

}
