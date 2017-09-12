package com.airshiplay.play.shop.service;

import com.airlenet.repo.jpa.HierarchicalEntityService;
import org.springframework.stereotype.Service;

import com.airshiplay.play.shop.entity.ProductCategoryEntity;

@Service
public class ProductCategoryEntityService extends HierarchicalEntityService<ProductCategoryEntity, Long> {

}
