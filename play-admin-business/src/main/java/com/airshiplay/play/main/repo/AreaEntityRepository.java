package com.airshiplay.play.main.repo;

import com.airlenet.repo.jpa.HierarchicalEntityRepository;
import com.airshiplay.play.main.entity.AreaEntity;
import com.airshiplay.play.main.entity.AreaEntity.AreaType;
import com.airshiplay.play.main.entity.QAreaEntity;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

public interface AreaEntityRepository extends HierarchicalEntityRepository<AreaEntity, Long>,QuerydslBinderCustomizer<QAreaEntity> {

	AreaEntity findByFullNameAndType(String fullName,AreaType type);

	@Override
	default public void customize(QuerydslBindings bindings, QAreaEntity root){
		bindings.bind(root.fullName).first((path,value) ->path.contains(value));
	}
}
