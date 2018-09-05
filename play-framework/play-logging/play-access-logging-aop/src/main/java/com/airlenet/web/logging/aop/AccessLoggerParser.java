package com.airlenet.web.logging.aop;


import com.airlenet.web.boost.aop.context.MethodInterceptorHolder;
import com.airlenet.web.logging.LoggerDefine;

import java.lang.reflect.Method;

public interface AccessLoggerParser {
    boolean support(Class clazz, Method method);

    LoggerDefine parse(MethodInterceptorHolder holder);
}
