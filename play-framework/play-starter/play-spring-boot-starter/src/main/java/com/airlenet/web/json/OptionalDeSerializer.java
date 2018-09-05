package com.airlenet.web.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Optional;

public class OptionalDeSerializer extends JsonDeserializer<Optional> {
//    @Override
//    public void serialize(Optional optional, JsonGenerator gen, SerializerProvider serializers) throws IOException {
//        if(optional.isPresent()){
//            gen.writeObject(optional.get());
//        }else{
//            gen.writeObject(null);
//        }
//
//    }

    @Override
    public Optional deserialize(JsonParser gen, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return Optional.empty();
    }
}
