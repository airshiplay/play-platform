package com.airshiplay.play.main.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airshiplay.play.main.entity.DictEntity;
import com.airshiplay.play.main.repo.DictEntityRepository;

@Service
public class DictEntityService extends EntityService<DictEntity, Long> {
	@Autowired
	private DictEntityRepository dictEntityRepository;

	public List<DictEntity> findByType(String type){
		return dictEntityRepository.findByType(type);
	}
	
	public int countByType(String type){
		return dictEntityRepository.countByType(type);
	}
	public <T extends Enum<T>> List<DictEntity> save(Class<T> enumCls,
			String description) {
		
		ArrayList<DictEntity> itr = new ArrayList<DictEntity>();

		
		T ts[] = enumCls.getEnumConstants();
		for (T t : ts) {
			
			DictEntity entity = new DictEntity();
			entity.setType(enumCls.getSimpleName());
			entity.setValue(t.ordinal());
			entity.setDescription(description);
			try {
				entity.setLabel(org.apache.commons.beanutils.BeanUtils
						.getProperty(t, "label"));
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
			
			entity.setName(t.name());
			itr.add(entity);
			
		}
		
		return dictEntityRepository.save(itr);
	}
}
