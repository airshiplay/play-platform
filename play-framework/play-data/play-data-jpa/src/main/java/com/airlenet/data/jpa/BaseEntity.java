package com.airlenet.data.jpa;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseEntity<I extends Serializable> extends AbstractPersistable<I>implements Persistable<I> {

	private static final long serialVersionUID = 2909347062854434851L;

}
