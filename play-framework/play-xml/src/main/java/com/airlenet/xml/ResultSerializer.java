package com.airlenet.xml;

import com.airlenet.repo.domain.Result;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import com.google.common.base.Objects;
import com.google.common.base.Strings;

import java.io.IOException;
import java.util.Map;

/**
 * @author airlenet
 * @version  17/9/8
 */
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

        gen.writeBooleanField(FIELD_SUCCESS, Objects.equal(value.getCode(), Result.ResultCode.success));
        gen.writeStringField(FIELD_CODE, value.getCode().name());
        if (!Strings.isNullOrEmpty(value.getMessage())) {
            gen.writeStringField(FIELD_MESSAGE, value.getMessage());
        }
        if (value.getExtraProperties() != null) {
            for (Map.Entry<String, Object> entry : value.getExtraProperties().entrySet()) {
                gen.writeObjectField(entry.getKey(), entry.getValue());
            }
        }

        gen.writeEndObject();

    }
}
