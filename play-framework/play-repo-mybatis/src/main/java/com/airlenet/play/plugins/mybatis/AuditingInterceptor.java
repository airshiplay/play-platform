package com.airlenet.play.plugins.mybatis;

import com.airlenet.play.plugins.mybatis.annotation.CreatedBy;
import com.airlenet.play.plugins.mybatis.annotation.CreatedDate;
import com.airlenet.play.plugins.mybatis.annotation.LastModifiedBy;
import com.airlenet.play.plugins.mybatis.annotation.LastModifiedDate;
import com.airlenet.play.security.CustomUserDetails;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.shiro.SecurityUtils;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Properties;

/**
 * 审计
 * 创建用户，创建日期，更新用户，更新日期
 * Created by lig on 17/2/5.
 */
@Intercepts({@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})})
public class AuditingInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
        Object parameter = invocation.getArgs()[1];
        Field[] fields = parameter.getClass().getDeclaredFields();
        Date currentDate = new Date();
        Object user = SecurityUtils.getSubject().getPrincipal();
        CustomUserDetails<?, ?> customUserDetails = null;
        if (user instanceof CustomUserDetails<?, ?>) {
            customUserDetails = (CustomUserDetails<?, ?>) user;
        }
        if (SqlCommandType.UPDATE == sqlCommandType) {
            for (Field field : fields) {
                if (customUserDetails != null && AnnotationUtils.getAnnotation(field, LastModifiedBy.class) != null) {
                    field.setAccessible(true);
                    field.set(parameter, customUserDetails.getId());
                    field.setAccessible(false);
                }
                if (AnnotationUtils.getAnnotation(field, LastModifiedDate.class) != null) {
                    field.setAccessible(true);
                    field.set(parameter, currentDate);
                    field.setAccessible(false);
                }
            }
        } else if (SqlCommandType.INSERT == sqlCommandType) {
            for (Field field : fields) {
                if (customUserDetails != null && AnnotationUtils.getAnnotation(field, CreatedBy.class) != null) {
                    field.setAccessible(true);
                    field.set(parameter, customUserDetails.getId());
                    field.setAccessible(false);
                }
                if (AnnotationUtils.getAnnotation(field, CreatedDate.class) != null) {
                    field.setAccessible(true);
                    field.set(parameter, currentDate);
                    field.setAccessible(false);
                }
            }
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof Executor) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }

    @Override
    public void setProperties(Properties properties) {
    }
}
