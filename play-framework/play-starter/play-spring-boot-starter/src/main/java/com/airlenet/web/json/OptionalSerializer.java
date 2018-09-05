package com.airlenet.web.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class OptionalSerializer extends JsonSerializer<Optional> {
    @Override
    public void serialize(Optional optional, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if(optional.isPresent()){
            gen.writeObject(optional.get());
        }else{
            gen.writeObject(null);
        }

    }
}
