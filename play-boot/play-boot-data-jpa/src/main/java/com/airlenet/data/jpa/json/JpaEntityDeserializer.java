package com.airlenet.data.jpa.json;

import java.io.IOException;
import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * jpa实体反序列化器
 *
 * @param <T>
 * @param <I>
 */
public class JpaEntityDeserializer<T, I> extends JsonDeserializer<T> {

	private JpaEntityInformation<T, I> entityInformation;
	private EntityManager entityManager;

	public JpaEntityDeserializer(JpaEntityInformation<T, I> entityInformation, EntityManager entityManager) {
		this.entityInformation = entityInformation;
		this.entityManager = entityManager;
	}

	@Override
	public T deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		I identifier = p.readValueAs(entityInformation.getIdType());
		return identifier == null ? null : entityManager.getReference(entityInformation.getJavaType(), identifier);
	}

}
