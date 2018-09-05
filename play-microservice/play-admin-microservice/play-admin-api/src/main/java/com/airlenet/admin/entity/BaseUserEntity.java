package com.airlenet.admin.entity;

import com.airlenet.data.jpa.DataEntity;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public class BaseUserEntity<I extends Serializable> extends DataEntity<BaseUserEntity,I> {
}
