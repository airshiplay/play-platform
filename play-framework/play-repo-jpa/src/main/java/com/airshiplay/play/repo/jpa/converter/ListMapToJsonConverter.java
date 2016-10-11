package com.airshiplay.play.repo.jpa.converter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.SimpleType;
import com.google.common.base.Strings;
import com.airshiplay.play.core.SpringContext;

@Converter
public class ListMapToJsonConverter implements AttributeConverter<Object, String> {

	private ObjectMapper objectMapper;

	@Override
	public String convertToDatabaseColumn(Object attribute) {
		if (attribute == null) {
			return null;
		}
		try {
			return getObjectMapper().writeValueAsString(attribute);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Object convertToEntityAttribute(String dbData) {
		if (Strings.isNullOrEmpty(dbData)) {
			return null;
		}
		try {
			return getObjectMapper().readValue(dbData,
					CollectionType.construct(ArrayList.class, MapType.construct(HashMap.class, SimpleType.construct(String.class), SimpleType.construct(Object.class))));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public ObjectMapper getObjectMapper() {
		if (objectMapper == null) {
			objectMapper = SpringContext.getBean(ObjectMapper.class);
		}
		return objectMapper;
	}

}
