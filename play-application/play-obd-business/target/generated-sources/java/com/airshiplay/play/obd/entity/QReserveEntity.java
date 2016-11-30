package com.airshiplay.play.obd.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QReserveEntity is a Querydsl query type for ReserveEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QReserveEntity extends EntityPathBase<ReserveEntity> {

    private static final long serialVersionUID = 1315748968L;

    public static final QReserveEntity reserveEntity = new QReserveEntity("reserveEntity");

    public final com.airshiplay.play.repo.jpa.QBaseEntity _super = new com.airshiplay.play.repo.jpa.QBaseEntity(this);

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.util.Date> lastModifiedDate = createDateTime("lastModifiedDate", java.util.Date.class);

    public QReserveEntity(String variable) {
        super(ReserveEntity.class, forVariable(variable));
    }

    public QReserveEntity(Path<? extends ReserveEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QReserveEntity(PathMetadata metadata) {
        super(ReserveEntity.class, metadata);
    }

}

