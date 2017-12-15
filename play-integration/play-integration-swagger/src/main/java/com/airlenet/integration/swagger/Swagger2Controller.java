package com.airlenet.integration.swagger;

//import io.swagger.oas.annotations.responses.ApiResponse;
//import io.swagger.oas.models.OpenAPI;
//import io.swagger.oas.models.Operation;
//import io.swagger.oas.models.PathItem;
//import io.swagger.oas.models.Paths;
//import io.swagger.util.Json;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author airlenet
 * @version 2017-11-12
 */
@RestController
public class Swagger2Controller {
    public static final String DEFAULT_URL = "/v2/api";

//    @RequestMapping(DEFAULT_URL)
//    @ApiResponse
//    @io.swagger.oas.annotations.Operation(method = "get")
//    public Object api(){
//        OpenAPI openAPI = new OpenAPI();
//        openAPI.paths(new Paths().addPathItem("",new PathItem().get(new Operation())));
//        return Json.pretty(openAPI);
//    }
}
