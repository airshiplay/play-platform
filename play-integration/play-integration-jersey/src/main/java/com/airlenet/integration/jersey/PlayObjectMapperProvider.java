package com.airlenet.integration.jersey;

import ch.mfrey.jackson.antpathfilter.AntPathFilterMixin;
import ch.mfrey.jackson.antpathfilter.AntPathPropertyFilter;
import com.airlenet.core.SpringContext;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import javax.ws.rs.core.Context;
import javax.ws.rs.ext.ContextResolver;

/**
 * @author airlenet
 * @version 2017-10-05
 */
public class PlayObjectMapperProvider implements ContextResolver<ObjectMapper> {

    public PlayObjectMapperProvider() {
    }

    @Override
    public ObjectMapper getContext(final Class<?> type) {
        return SpringContext.getBean(ObjectMapper.class);
    }



}