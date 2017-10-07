package com.airlenet.security.shiro;

import javax.inject.Singleton;

import org.glassfish.hk2.api.DynamicConfiguration;
import org.glassfish.hk2.api.Factory;
import org.glassfish.hk2.utilities.Binder;
import org.glassfish.hk2.utilities.binding.BindingBuilderFactory;
import org.glassfish.jersey.internal.inject.Injections;
import org.glassfish.jersey.server.ContainerRequest;
import org.glassfish.jersey.server.model.Parameter;
//import org.glassfish.jersey.server.spi.internal.ValueFactoryProvider;
import org.glassfish.jersey.server.spi.internal.ValueParamProvider;

import java.util.function.Function;


/**
 * @author airlenet
 * @version 2017-10-04
 */
public abstract class TypeFactory<T> implements Factory<T>, ValueParamProvider, Binder {
    public final Class<T> type;

    public TypeFactory(Class<T> type) {
        this.type = type;
    }

    // org.glassfish.hk2.api.Factory<T>

    @Override
    public void dispose(T instance) {
    }

    /**
     * Get an injected value provider for the parameter. May return {@code null}
     * in case the parameter is not supported by the value provider.
     *
     * @param parameter parameter requesting the value provider instance.
     * @return injected parameter value provider. Returns {@code null} if parameter is not supported.
     */
    @Override
    public Function<ContainerRequest, ?> getValueProvider(Parameter parameter) {
//        if (type.equals(parameter.getRawType())) {
//            return this;
//        }
        return null;
    }
// org.glassfish.jersey.server.spi.internal.ValueFactoryProvider

//    @Override
//    public Factory<?> getValueFactory(Parameter parameter) {
//        if (type.equals(parameter.getRawType())) {
//            return this;
//        }
//        return null;
//    }

    @Override
    public PriorityType getPriority() {
        return Priority.NORMAL;
    }

    // org.glassfish.hk2.utilities.Binder
    @Override
    public void bind(DynamicConfiguration config) {
        BindingBuilderFactory.addBinding(
                BindingBuilderFactory.newFactoryBinder(this).to(type).in(Singleton.class),
                config);
        BindingBuilderFactory.addBinding(
                BindingBuilderFactory.newFactoryBinder(this).to(ValueParamProvider.class),
                config);
    }
}
