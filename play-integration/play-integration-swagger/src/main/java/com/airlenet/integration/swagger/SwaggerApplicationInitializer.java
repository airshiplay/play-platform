package com.airlenet.integration.swagger;

import com.airlenet.core.SpringContext;
import com.airlenet.integration.core.ApplicationInitializer;
import com.airlenet.web.WebSpringContext;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author airlenet
 * @version 2017-11-13
 */
@Component
public class SwaggerApplicationInitializer extends ApplicationInitializer {

    @Override
    public void onServletContextRefreshed() {
        Map<String, Object> beansWithAnnotation = WebSpringContext.getApplicationContext().getBeansWithAnnotation(RestController.class);

        Iterator<Map.Entry<String, Object>> iterator = beansWithAnnotation.entrySet().iterator();
        LocalVariableTableParameterNameDiscoverer parameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();
        while (iterator.hasNext()){
            Map.Entry<String, Object> next = iterator.next();
            Object controller = next.getValue();
            Class<?> controllerCls = controller.getClass();
            RequestMapping clsRequestMapping = controllerCls.getAnnotation(RequestMapping.class);
            String[] clsUrl = clsRequestMapping.value();
            clsRequestMapping.method();

            Method[] methods = controllerCls.getMethods();
            for (Method method:methods){
                RequestMapping methodRequestMapping = method.getAnnotation(RequestMapping.class);
                methodRequestMapping.method();
                String[] methodUrl = methodRequestMapping.value();
                methodRequestMapping.consumes();
                method.getReturnType();
                Annotation[][] annotations = method.getParameterAnnotations();
                String[] parameterNames = parameterNameDiscoverer.getParameterNames(method);
            }
        }

    }

    @Override
    public void onRootContextRefreshed() {

    }
}
