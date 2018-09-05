package com.airlenet.web.starter;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import com.airlenet.data.domain.Node;
import com.airlenet.data.domain.RestResult;
import com.airlenet.data.domain.Tree;
import com.airlenet.web.json.*;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Deserializers;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Serializers;
import com.fasterxml.jackson.datatype.jdk8.Jdk8TypeModifier;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.joda.time.DateTime;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Persistable;
import org.springframework.validation.ObjectError;

import ch.mfrey.jackson.antpathfilter.AntPathFilterMixin;
import ch.mfrey.jackson.antpathfilter.AntPathPropertyFilter;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@Configuration
public class JsonConfigBean {
	private String dateTimeFormat ="yyyy-MM-dd HH:mm:ss";
	private String dateFormat ="yyyy-MM-dd";
	private String timeFormat ="HH:mm:ss";
	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();

		objectMapper.registerModule(simpleModule());
		objectMapper.registerModules(new Jdk8Module(),new JavaTimeModule());
		// objectMapper.setAnnotationIntrospector(ai)
		objectMapper.setDateFormat(new SimpleDateFormat(dateTimeFormat));
		objectMapper.addMixIn(Persistable.class, AntPathFilterMixin.class);
//		objectMapper.addMixIn(List.class, AntPathFilterMixin.class);
		objectMapper.setSerializationInclusion(Include.NON_NULL);
		objectMapper.disable(SerializationFeature.FAIL_ON_SELF_REFERENCES);

		SimpleFilterProvider filterPrvider=	new SimpleFilterProvider().addFilter("antPathFilter", new AntPathPropertyFilter(new String[] { "*","*.*" }));
		objectMapper.setFilterProvider(filterPrvider);
		return objectMapper;
	}

	@Bean
	public SimpleModule simpleModule() {
		SimpleModule simpleModule = new SimpleModule();
		simpleModule.addSerializer(DateTime.class, new JodaDateTimeSerializer());
		simpleModule.addSerializer(Page.class, new PageSerializer<>());
		simpleModule.addSerializer(Tree.class, new TreeSerializer<>());
		simpleModule.addSerializer(Node.class, new NodeSerializer<>(null,null,null));
		simpleModule.addSerializer(RestResult.class, new ResultSerializer());
		simpleModule.addSerializer(ObjectError.class, new ObjectErrorSerializer());
//		simpleModule.addSerializer(Optional.class, new OptionalSerializer());
//		simpleModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(dateTimeFormat)));
//		simpleModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern(dateFormat)));
//		simpleModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern(timeFormat)));


		return simpleModule;
	}

}
