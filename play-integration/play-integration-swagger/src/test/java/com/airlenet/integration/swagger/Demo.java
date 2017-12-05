package com.airlenet.integration.swagger;

import io.swagger.oas.integration.GenericOpenApiContextBuilder;
import io.swagger.oas.integration.GenericOpenApiScanner;
import io.swagger.oas.integration.OpenApiConfigurationException;
import io.swagger.oas.integration.SwaggerConfiguration;
import io.swagger.oas.integration.api.OpenApiContext;
import io.swagger.oas.models.OpenAPI;
import io.swagger.util.Json;

import java.util.HashSet;

/**
 * @author airlenet
 * @version 2017-11-27
 */
public class Demo {
    public static void main(String args[]) throws OpenApiConfigurationException {
        SwaggerConfiguration configuration = new SwaggerConfiguration();
        GenericOpenApiContextBuilder openApiContextBuilder = new GenericOpenApiContextBuilder();
        HashSet<String> hashSet = new HashSet<>();
        hashSet.add(Swagger2Controller.class.getName());
//        configuration.setResourcePackages(hashSet);
        configuration.resourceClasses(hashSet);
        configuration.setReaderClass(PlayOpenApiReader.class.getName());
        openApiContextBuilder.setOpenApiConfiguration(configuration);
        OpenApiContext openApiContext = openApiContextBuilder.buildContext(true);

        OpenAPI openAPI = openApiContext.read();
        System.out.println(Json.pretty( openAPI));
    }
}
