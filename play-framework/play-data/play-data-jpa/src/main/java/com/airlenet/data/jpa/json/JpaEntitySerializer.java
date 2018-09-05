package com.airlenet.data.jpa.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;

import java.io.IOException;
import java.io.Serializable;

/**
 * jpa实体序列化器
 * 
 * @作者 airlenet
 * @创建时间 2015年10月5日 下午10:24:20
 * @param <T>
 * @param <I>
 */
public class JpaEntitySerializer<T, I> extends JsonSerializer<T> {
	private JpaEntityInformation<T, I> entityInformation;
	private BeanProperty beanProperty;

	public JpaEntitySerializer(JpaEntityInformation<T, I> entityInformation, BeanProperty beanProperty) {
		this.entityInformation = entityInformation;
		this.beanProperty = beanProperty;
	}

	@Override
	public void serialize(T value, JsonGenerator gen, SerializerProvider serializers)
			throws IOException, JsonProcessingException {
		if (value == null) {
			serializers.defaultSerializeNull(gen);
		} else {
			serializers.findValueSerializer(entityInformation.getJavaType(), beanProperty).serialize(value, gen,
					serializers);
		}
	}

}
