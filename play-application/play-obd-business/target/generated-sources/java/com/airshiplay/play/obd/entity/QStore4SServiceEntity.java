package com.airshiplay.play.obd.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStore4SServiceEntity is a Querydsl query type for Store4SServiceEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QStore4SServiceEntity extends EntityPathBase<Store4SServiceEntity> {

    private static final long serialVersionUID = -45028657L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStore4SServiceEntity store4SServiceEntity = new QStore4SServiceEntity("store4SServiceEntity");

    public final com.airshiplay.play.repo.jpa.QDataEntity _super = new com.airshiplay.play.repo.jpa.QDataEntity(this);

    public final com.airshiplay.play.main.entity.QAdminUserEntity createdBy;

    //inherited
    public final DateTimePath<java.util.Date> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.airshiplay.play.main.entity.QAdminUserEntity lastModifiedBy;

    //inherited
    public final DateTimePath<java.util.Date> lastModifiedDate = _super.lastModifiedDate;

    public final QStore4SEntity store4S;

    public QStore4SServiceEntity(String variable) {
        this(Store4SServiceEntity.class, forVariable(variable), INITS);
    }

    public QStore4SServiceEntity(Path<? extends Store4SServiceEntity> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QStore4SServiceEntity(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QStore4SServiceEntity(PathMetadata metadata, PathInits inits) {
        this(Store4SServiceEntity.class, metadata, inits);
    }

    public QStore4SServiceEntity(Class<? extends Store4SServiceEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.createdBy = inits.isInitialized("createdBy") ? new com.airshiplay.play.main.entity.QAdminUserEntity(forProperty("createdBy"), inits.get("createdBy")) : null;
        this.lastModifiedBy = inits.isInitialized("lastModifiedBy") ? new com.airshiplay.play.main.entity.QAdminUserEntity(forProperty("lastModifiedBy"), inits.get("lastModifiedBy")) : null;
        this.store4S = inits.isInitialized("store4S") ? new QStore4SEntity(forProperty("store4S"), inits.get("store4S")) : null;
    }

}

