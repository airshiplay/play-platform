package com.airshiplay.play.main.service;

import com.airshiplay.play.main.entity.DictEntity;
import com.airshiplay.play.main.repo.DictEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DictEntityService extends EntityService<DictEntity, Long> {
	@Autowired
	private DictEntityRepository dictEntityRepository;

	@Transactional(readOnly = true)
	public List<DictEntity> findByType(String type) {
		return dictEntityRepository.findByType(type);
	}

	@Transactional(readOnly = true)
	public int countByType(String type) {
		return dictEntityRepository.countByType(type);
	}

	public <T extends Enum<T>> List<DictEntity> save(Class<T> enumCls, String description) {

		ArrayList<DictEntity> itr = new ArrayList<DictEntity>();

		T ts[] = enumCls.getEnumConstants();
		for (T t : ts) {
			DictEntity entity = new DictEntity();
			entity.setType(enumCls.getSimpleName());
			entity.setValue(t.ordinal());
			entity.setDescription(description);
			entity.setName(t.name());
			try {
				String label = null;
				for (Field f : t.getClass().getDeclaredFields()) {
					if (f.getName().equals("ENUM$VALUES")) {
						continue;
					}
					boolean exsit = false;
					for (Field f1 : t.getDeclaringClass().getFields()) {
						if (f1.getName().equals(f.getName())) {
							exsit = true;
							break;
						}
					}
					if (exsit) {
						continue;
					}
					label = f.getName();
					break;
				}
				if (label != null)
					entity.setLabel(org.apache.commons.beanutils.BeanUtils.getProperty(t, label));
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
			itr.add(entity);

		}

		return dictEntityRepository.save(itr);
	}
}
