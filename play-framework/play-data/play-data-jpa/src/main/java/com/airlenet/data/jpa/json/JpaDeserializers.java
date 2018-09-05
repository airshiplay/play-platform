package com.airlenet.data.jpa.json;

import com.airlenet.data.jpa.BaseEntity;
import com.fasterxml.jackson.databind.*;
import org.apache.commons.lang3.ClassUtils;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * jpa反序列化器
 * 
 * @作者 airlenet
 * @创建时间 2015年10月5日 下午10:23:55
 */
public class JpaDeserializers extends com.fasterxml.jackson.databind.deser.Deserializers.Base {

	private EntityManager entityManager;
	private Set<Class<?>> excluded = new HashSet<>();

	public JpaDeserializers(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public JpaDeserializers exclude(Class<?> clazz) {
		assert clazz != null;
		excluded.add(clazz);
		return this;
	}

	@Override
	public JsonDeserializer<?> findBeanDeserializer(JavaType type, DeserializationConfig config,
			BeanDescription beanDesc) throws JsonMappingException {
		if (!excluded.contains(type.getRawClass()) && ClassUtils.isAssignable(type.getRawClass(), BaseEntity.class)) {
			JpaEntityInformation<?, ? > entityInformation = JpaEntityInformationSupport
					.getEntityInformation(type.getRawClass(), entityManager);
			if (entityInformation != null) {
				return new JpaEntityDeserializer<>(entityInformation, entityManager);
			}

		}
		return super.findBeanDeserializer(type, config, beanDesc);
	}
}
