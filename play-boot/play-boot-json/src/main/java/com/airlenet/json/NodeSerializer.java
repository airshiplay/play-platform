package com.airlenet.json;

import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Persistable;
import org.springframework.util.ClassUtils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import com.airlenet.data.domain.Hierarchical;
import com.airlenet.data.domain.Node;

public class NodeSerializer<N extends Node<T>, T extends Hierarchical<T>> extends JsonSerializer<N> {

    @Value("${tree.node.text_property}")
    private String textPropertyName;

    @Value("${tree.node.checked_property}")
    private String checkedPropertyName;

    @Value("${tree.node.children_property}")
    private String childrenPropertyName;

    @Override
    public void serialize(N value, JsonGenerator gen, SerializerProvider serializers)
            throws IOException, JsonProcessingException {
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

        if (data != null) {
            if (data instanceof Persistable) {
                if (((Persistable<?>) data).getId() != null) {
                    gen.writeStringField("key", ((Persistable<?>) data).getId().toString());
                }
            }
        }

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
                        && !ArrayUtils.contains(
                        new String[]{"class", "new", "children", "parent", "leaf", "checked", "iconCls",
                                "expanded", "createdBy", "createdDate", "lastModifiedBy", "lastModifiedDate"},
                        propertyName)) {
                    Object propertyValue = beanWrapperImpl.getPropertyValue(propertyName);

                    if (propertyValue != null) {
                        gen.writeFieldName(propertyName);
                        serializers.findValueSerializer(propertyValue.getClass(), null).serialize(propertyValue, gen,
                                serializers);
                    }

                }
            }

        }

        gen.writeEndObject();
    }

}
