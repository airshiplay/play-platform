package com.airlenet.integration.swagger;

import io.swagger.oas.annotations.Operation;
import io.swagger.oas.integration.api.OpenAPIConfiguration;
import io.swagger.oas.integration.api.OpenApiReader;
import io.swagger.oas.models.OpenAPI;
import io.swagger.oas.models.PathItem;
import io.swagger.oas.models.parameters.Parameter;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author airlenet
 * @version 2017-11-27
 */
public class PlayOpenApiReader implements OpenApiReader {
    @Override
    public void setConfiguration(OpenAPIConfiguration openAPIConfiguration) {

    }

    @Override
    public OpenAPI read(Set<Class<?>> set, Map<String, Object> map) {
        OpenAPI openAPI = new OpenAPI();
        Iterator<Class<?>> iterator = set.iterator();
        while (iterator.hasNext()){
            Class<?> aClass = iterator.next();
            Operation annotation = aClass.getMethods()[0].getAnnotation(Operation.class);
            openAPI.path("",new PathItem().get(new io.swagger.oas.models.Operation().addParametersItem(new Parameter().name("11"))));
        }
        return openAPI;
    }
}
