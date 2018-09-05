package com.airlenet.data.mybatis;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseEntity<I extends Serializable> {
    private I id;
}
