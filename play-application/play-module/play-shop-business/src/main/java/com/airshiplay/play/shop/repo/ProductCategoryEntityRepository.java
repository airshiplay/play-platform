package com.airshiplay.play.shop.repo;

import com.airshiplay.play.repo.jpa.HierarchicalJpaRepository;
import com.airshiplay.play.shop.entity.ProductCategoryEntity;

public interface ProductCategoryEntityRepository extends HierarchicalJpaRepository<ProductCategoryEntity, Long> {

}
