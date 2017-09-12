package com.airshiplay.play.shop.repo;

 import com.airlenet.repo.jpa.HierarchicalEntityRepository;
 import com.airshiplay.play.shop.entity.ProductCategoryEntity;

public interface ProductCategoryEntityRepository extends HierarchicalEntityRepository<ProductCategoryEntity, Long> {

}
