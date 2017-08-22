package com.airlenet.play.json;

import java.io.IOException;
import java.util.Map.Entry;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.google.common.base.Objects;
import com.google.common.base.Strings;
import com.airlenet.play.repo.domain.Result;
import com.airlenet.play.repo.domain.Result.ResultCode;

public class ResultSerializer extends JsonSerializer<Result> {

	public static final String FIELD_SUCCESS = "success";
	public static final String FIELD_MESSAGE = "msg";
	public static final String FIELD_CODE = "code";

	@Override
	public void serialize(Result value, JsonGenerator gen, SerializerProvider serializers)
			throws IOException, JsonProcessingException {
		if (value == null) {
			gen.writeNull();
			return;
		}

		gen.writeStartObject();

		gen.writeBooleanField(FIELD_SUCCESS, Objects.equal(value.getCode(), ResultCode.success));
		gen.writeStringField(FIELD_CODE, value.getCode().name());
		if (!Strings.isNullOrEmpty(value.getMessage())) {
			gen.writeStringField(FIELD_MESSAGE, value.getMessage());
		}
		if (value.getExtraProperties() != null) {
			for (Entry<String, Object> entry : value.getExtraProperties().entrySet()) {
				gen.writeObjectField(entry.getKey(), entry.getValue());
			}
		}

		gen.writeEndObject();

	}

}
