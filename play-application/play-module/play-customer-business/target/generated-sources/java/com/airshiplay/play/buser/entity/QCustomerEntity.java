package com.airshiplay.play.buser.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.airshiplay.play.customer.entity.CustomerEntity;
import com.querydsl.core.types.dsl.*;
import com.querydsl.core.types.PathMetadata;

import javax.annotation.Generated;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCustomerEntity is a Querydsl query type for CustomerEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCustomerEntity extends EntityPathBase<CustomerEntity> {

    private static final long serialVersionUID = 288419028L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCustomerEntity customerEntity = new QCustomerEntity("customerEntity");

    public final com.airshiplay.play.repo.jpa.QDataEntity _super = new com.airshiplay.play.repo.jpa.QDataEntity(this);

    public final BooleanPath accountExpired = createBoolean("accountExpired");

    public final NumberPath<Integer> age = createNumber("age", Integer.class);

    public final com.airshiplay.play.main.entity.QAreaEntity area;

    public final StringPath areaName = createString("areaName");

    public final DateTimePath<java.util.Date> birthday = createDateTime("birthday", java.util.Date.class);

    public final QCustomerEntity createdBy;

    //inherited
    public final DateTimePath<java.util.Date> createdDate = _super.createdDate;

    public final BooleanPath credentialsExpired = createBoolean("credentialsExpired");

    public final StringPath email = createString("email");

    public final BooleanPath enabled = createBoolean("enabled");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.util.Date> lastLoginDate = createDateTime("lastLoginDate", java.util.Date.class);

    public final StringPath lastLoginIp = createString("lastLoginIp");

    public final QCustomerEntity lastModifiedBy;

    //inherited
    public final DateTimePath<java.util.Date> lastModifiedDate = _super.lastModifiedDate;

    public final BooleanPath locked = createBoolean("locked");

    public final StringPath mobile = createString("mobile");

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    public final StringPath photo = createString("photo");

    public final StringPath realname = createString("realname");

    public final StringPath salt = createString("salt");

    public final StringPath sex = createString("sex");

    public final StringPath username = createString("username");

    public QCustomerEntity(String variable) {
        this(CustomerEntity.class, forVariable(variable), INITS);
    }

    public QCustomerEntity(Path<? extends CustomerEntity> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCustomerEntity(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCustomerEntity(PathMetadata metadata, PathInits inits) {
        this(CustomerEntity.class, metadata, inits);
    }

    public QCustomerEntity(Class<? extends CustomerEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.area = inits.isInitialized("area") ? new com.airshiplay.play.main.entity.QAreaEntity(forProperty("area")) : null;
        this.createdBy = inits.isInitialized("createdBy") ? new QCustomerEntity(forProperty("createdBy"), inits.get("createdBy")) : null;
        this.lastModifiedBy = inits.isInitialized("lastModifiedBy") ? new QCustomerEntity(forProperty("lastModifiedBy"), inits.get("lastModifiedBy")) : null;
    }

}

