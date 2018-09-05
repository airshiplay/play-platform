package com.airlenet.web.logging.aop;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.airlenet.web.boost.aop.context.MethodInterceptorHolder;
import com.airlenet.web.logging.LoggerDefine;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

public class SwaggerAccessLoggerParser implements AccessLoggerParser {
    @Override
    public boolean support(Class clazz, Method method) {

        Api api = AnnotationUtils.findAnnotation(clazz, Api.class);
        ApiOperation operation = AnnotationUtils.findAnnotation(method, ApiOperation.class);

        return api != null || operation != null;
    }

    @Override
    public LoggerDefine parse(MethodInterceptorHolder holder) {
        Api api = holder.findAnnotation(Api.class);
        ApiOperation operation = holder.findAnnotation(ApiOperation.class);
        String action = "";
        if (api != null) {
            action = action.concat(api.value());
        }
        if (null != operation) {
            action = StringUtils.isEmpty(action) ? operation.value() : action + "-" + operation.value();
        }
        return new LoggerDefine(action, "");
    }
}
