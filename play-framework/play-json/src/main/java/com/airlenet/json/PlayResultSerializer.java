package com.airlenet.json;

import com.airlenet.repo.domain.PlayResult;
import com.airlenet.repo.domain.Result;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.google.common.base.Joiner;
import com.google.common.base.Objects;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import org.springframework.validation.ObjectError;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author airlenet
 * @version 2017-10-07
 */
public class PlayResultSerializer extends JsonSerializer<PlayResult> {

    public static final String FIELD_result = "result";
    public static final String FIELD_MESSAGE = "message";

    public PlayResultSerializer() {
    }

    @Override
    public void serialize(PlayResult value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value == null) {
            gen.writeNull();
            return;
        }

        gen.writeStartObject();

        gen.writeStringField(FIELD_result, value.getResult());
        if (!Strings.isNullOrEmpty(value.getMessage())) {
            gen.writeStringField(FIELD_MESSAGE, value.getMessage());
        }
        if (value.getPages() != null) {
            gen.writeNumberField("pages", value.getPages());
        }
        if (value.getTotal() != null) {
            gen.writeNumberField("total", value.getTotal());
        }
        if (value.content != null) {
            gen.writeObjectField("content", value.getContent());
        }

        if (value.getExtraProperties() != null) {
            for (Map.Entry<String, Object> entry : value.getExtraProperties().entrySet()) {
                gen.writeObjectField(entry.getKey(), entry.getValue());
            }
        }
        gen.writeEndObject();

    }

}
