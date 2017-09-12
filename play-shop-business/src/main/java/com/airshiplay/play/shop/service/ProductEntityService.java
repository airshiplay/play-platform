package com.airshiplay.play.shop.service;

import java.util.List;

import com.airlenet.repo.jpa.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airshiplay.play.shop.entity.ProductEntity;
import com.airshiplay.play.shop.repo.ProductEntityRepository;

@Service
public class ProductEntityService extends EntityService<ProductEntity, Long> {

	@Autowired
	private ProductEntityRepository productEntityRepository;
	
	public List<ProductEntity> relatedProduct(ProductEntity self) {
		return productEntityRepository.findTop3ByProductCategoryAndIdNot(self.getProductCategory(), self.getId());
	}
	
}
