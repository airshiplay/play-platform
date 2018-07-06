package com.airlenet.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Persistable;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

/**
 * {@linkplain Page}的序列化器。
 * 
 * @作者 airlenet
 * @创建时间 2015年10月5日 下午10:25:30
 * @param
 * 			<P>
 * @param <T>
 * @param <I>
 */
public class PageInfoSerializer<P extends PageInfo<T>, T extends Persistable<I>, I extends Serializable>
		extends JsonSerializer<P> {

	public static final String DEFAULT_TOTAL_FIELD = "total";
	public static final String DEFAULT_CURRENT_FIELD = "current";
	public static final String DEFAULT_ROWCOUNT_FIELD = "rowCount";
	public static final String DEFAULT_ROWS_FIELD = "rows";

	@Override
	public void serialize(P value, JsonGenerator gen, SerializerProvider serializers)
			throws IOException, JsonProcessingException {
		gen.writeStartObject();
		gen.writeNumberField(DEFAULT_CURRENT_FIELD, value.getPageNum());
		gen.writeNumberField(DEFAULT_ROWCOUNT_FIELD, value.getPageSize());
		gen.writeFieldName(DEFAULT_ROWS_FIELD);
		serializers.findValueSerializer(List.class, null).serialize(value.getList(), gen, serializers);
		gen.writeNumberField(DEFAULT_TOTAL_FIELD, value.getTotal());
		gen.writeEndObject();
	}

}
