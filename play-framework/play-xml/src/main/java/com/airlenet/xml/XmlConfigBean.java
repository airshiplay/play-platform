package com.airlenet.xml;

import com.airlenet.repo.domain.Node;
import com.airlenet.repo.domain.Result;
import com.airlenet.repo.domain.Tree;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lig
 * @date 17/9/8
 */
@Configuration
public class XmlConfigBean {


    @Bean
    public XmlMapper objectMapper() {
        XmlMapper xmlMapper  = new XmlMapper();
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
        xmlMapper.registerModule(simpleModule());
//        // objectMapper.setAnnotationIntrospector(ai)
//
//        objectMapper.addMixIn(Persistable.class, AntPathFilterMixin.class);
//        objectMapper.addMixIn(List.class, AntPathFilterMixin.class);
//        objectMapper.setSerializationInclusion(Include.NON_NULL);
//        objectMapper.disable(SerializationFeature.FAIL_ON_SELF_REFERENCES);
//
//        SimpleFilterProvider filterPrvider=	new SimpleFilterProvider().addFilter("antPathFilter", new AntPathPropertyFilter(new String[] { "*","*.*" }));
//        objectMapper.setFilterProvider(filterPrvider);
        return xmlMapper;
    }

    @Bean
    public JacksonXmlModule simpleModule() {
        JacksonXmlModule simpleModule = new JacksonXmlModule();
//        simpleModule.addSerializer(DateTime.class, new JodaDateTimeSerializer());
//        simpleModule.addSerializer(Page.class, new PageSerializer<>());
//        simpleModule.addSerializer(Tree.class, new TreeSerializer<>());
//        simpleModule.addSerializer(Node.class, new NodeSerializer<>());
        simpleModule.addSerializer(Result.class, new ResultSerializer());
        return simpleModule;
    }
}
