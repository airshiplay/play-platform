package com.airlenet.web.json;

import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.util.List;

//import org.apache.commons.configuration.Configuration;
import com.airlenet.data.domain.Hierarchical;
import com.airlenet.data.domain.Node;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.data.domain.Persistable;
import org.springframework.util.ClassUtils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class NodeSerializer<N extends Node<T>, T extends Hierarchical<T>> extends JsonSerializer<N> {

	private String textPropertyName;
	private String checkedPropertyName;
	private String childrenPropertyName;

	public NodeSerializer(String text_property,String checked_property,String children_property ) {
//		Configuration configuration = StaticConfigSupplier.getConfiguration();
		textPropertyName = text_property==null?"text":text_property;//configuration.getString("tree.node.text_property", "text");
		checkedPropertyName = checked_property==null?"checked":checked_property;//configuration.getString("tree.node.checked_property", "checked");
		childrenPropertyName = children_property==null?"children":children_property;//configuration.getString("tree.node.children_property", "children");
	}

	@Override
	public void serialize(N value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
		if (value == null) {
			gen.writeNull();
			return;
		}

		gen.writeStartObject();

		if (value.getText() != null) {
			gen.writeStringField(textPropertyName, value.getText());
		}

		if (value.getIconCls() != null) {
			gen.writeStringField("iconCls", value.getIconCls());
		}

		if (value.getChildren() != null && value.getChildren().size() > 0) {
			gen.writeFieldName(childrenPropertyName);
			serializers.findValueSerializer(List.class, null).serialize(value.getChildren(), gen, serializers);
		}

		gen.writeBooleanField("leaf", value.getLeaf());

		T data = value.getData();

		if (value.getChecked() != null) {
			if (data instanceof Persistable) {
				if (((Persistable<?>) data).getId() != null) {
					gen.writeBooleanField(checkedPropertyName, value.getChecked());
				}
			} else {
				gen.writeBooleanField(checkedPropertyName, value.getChecked());
			}
		}

		if (value.getExpanded() != null) {
			gen.writeBooleanField("expanded", value.getExpanded());
		}

		if (data != null) {
			BeanWrapperImpl beanWrapperImpl = new BeanWrapperImpl(data);

			for (PropertyDescriptor propertyDescriptor : beanWrapperImpl.getPropertyDescriptors()) {
				String propertyName = propertyDescriptor.getName();
				Class<?> propertyType = propertyDescriptor.getPropertyType();
				if (!ClassUtils.isAssignable(Iterable.class, propertyType)
						&& !ArrayUtils.contains(new String[] { "class", "new", "children", "parent", "leaf", "checked", "iconCls", "expanded",
								"createdBy", "createdDate", "lastModifiedBy", "lastModifiedDate" }, propertyName)) {
					Object propertyValue = beanWrapperImpl.getPropertyValue(propertyName);

					if (propertyValue != null) {
						gen.writeFieldName(propertyName);
						serializers.findValueSerializer(propertyValue.getClass(), null).serialize(propertyValue, gen, serializers);
					}

				}
			}

		}

		gen.writeEndObject();
	}

}
