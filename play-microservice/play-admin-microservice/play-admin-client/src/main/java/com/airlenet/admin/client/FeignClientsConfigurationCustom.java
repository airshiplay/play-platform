package com.airlenet.admin.client;

//import com.glodon.cloud.feign.SpringMvcContractCustom;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
//import org.springframework.cloud.netflix.feign.AnnotatedParameterProcessor;
//import org.springframework.cloud.netflix.feign.DefaultFeignLoggerFactory;
//import org.springframework.cloud.netflix.feign.FeignFormatterRegistrar;
//import org.springframework.cloud.netflix.feign.FeignLoggerFactory;
//import org.springframework.cloud.netflix.feign.support.ResponseEntityDecoder;
//import org.springframework.cloud.netflix.feign.support.SpringDecoder;
//import org.springframework.cloud.netflix.feign.support.SpringEncoder;
//import org.springframework.cloud.netflix.feign.support.SpringMvcContract;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.AnnotatedParameterProcessor;
import org.springframework.cloud.openfeign.DefaultFeignLoggerFactory;
import org.springframework.cloud.openfeign.FeignFormatterRegistrar;
import org.springframework.cloud.openfeign.FeignLoggerFactory;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.convert.ConversionService;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;

import com.netflix.hystrix.HystrixCommand;

import feign.Contract;
import feign.Feign;
import feign.Logger;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.hystrix.HystrixFeign;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dave Syer
 * @author Venil Noronha
 */
@Configuration
public class FeignClientsConfigurationCustom {

    @Autowired
    private ObjectFactory<HttpMessageConverters> messageConverters;

    @Autowired(required = false)
    private List<AnnotatedParameterProcessor> parameterProcessors = new ArrayList<>();

    @Autowired(required = false)
    private List<FeignFormatterRegistrar> feignFormatterRegistrars = new ArrayList<>();

    @Autowired(required = false)
    private Logger logger;

    @Bean
    @ConditionalOnMissingBean
    public Decoder feignDecoder() {
        return new ResponseEntityDecoder(new SpringDecoder(this.messageConverters));
    }

    @Bean
    @ConditionalOnMissingBean
    public Encoder feignEncoder() {
        return new SpringEncoder(this.messageConverters);
    }

    @Bean
    @ConditionalOnMissingBean
    public Contract feignContract(ConversionService feignConversionService) {
        return new SpringMvcContractCustom(this.parameterProcessors, feignConversionService);
    }

    @Bean
    public FormattingConversionService feignConversionService() {
        FormattingConversionService conversionService = new DefaultFormattingConversionService();
        for (FeignFormatterRegistrar feignFormatterRegistrar : feignFormatterRegistrars) {
            feignFormatterRegistrar.registerFormatters(conversionService);
        }
        return conversionService;
    }

    @Configuration
    @ConditionalOnClass({ HystrixCommand.class, HystrixFeign.class })
    protected static class HystrixFeignConfiguration {
        @Bean
        @Scope("prototype")
        @ConditionalOnMissingBean
        @ConditionalOnProperty(name = "feign.hystrix.enabled", matchIfMissing = true)
        public Feign.Builder feignHystrixBuilder() {
            return HystrixFeign.builder();
        }
    }

    @Bean
    @Scope("prototype")
    @ConditionalOnMissingBean
    public Feign.Builder feignBuilder() {
        return Feign.builder();
    }

    @Bean
    @ConditionalOnMissingBean(FeignLoggerFactory.class)
    public FeignLoggerFactory feignLoggerFactory() {
        return new DefaultFeignLoggerFactory(logger);
    }

}
