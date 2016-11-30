package com.airshiplay.play.obd.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QActivityEntity is a Querydsl query type for ActivityEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QActivityEntity extends EntityPathBase<ActivityEntity> {

    private static final long serialVersionUID = -703974327L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QActivityEntity activityEntity = new QActivityEntity("activityEntity");

    public final com.airshiplay.play.repo.jpa.QDataEntity _super = new com.airshiplay.play.repo.jpa.QDataEntity(this);

    public final DateTimePath<java.util.Date> beginDate = createDateTime("beginDate", java.util.Date.class);

    public final StringPath content = createString("content");

    public final com.airshiplay.play.main.entity.QAdminUserEntity createdBy;

    //inherited
    public final DateTimePath<java.util.Date> createdDate = _super.createdDate;

    public final DateTimePath<java.util.Date> endDate = createDateTime("endDate", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.airshiplay.play.main.entity.QAdminUserEntity lastModifiedBy;

    //inherited
    public final DateTimePath<java.util.Date> lastModifiedDate = _super.lastModifiedDate;

    public final SetPath<Store4SEntity, QStore4SEntity> store4Ss = this.<Store4SEntity, QStore4SEntity>createSet("store4Ss", Store4SEntity.class, QStore4SEntity.class, PathInits.DIRECT2);

    public QActivityEntity(String variable) {
        this(ActivityEntity.class, forVariable(variable), INITS);
    }

    public QActivityEntity(Path<? extends ActivityEntity> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QActivityEntity(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QActivityEntity(PathMetadata metadata, PathInits inits) {
        this(ActivityEntity.class, metadata, inits);
    }

    public QActivityEntity(Class<? extends ActivityEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.createdBy = inits.isInitialized("createdBy") ? new com.airshiplay.play.main.entity.QAdminUserEntity(forProperty("createdBy"), inits.get("createdBy")) : null;
        this.lastModifiedBy = inits.isInitialized("lastModifiedBy") ? new com.airshiplay.play.main.entity.QAdminUserEntity(forProperty("lastModifiedBy"), inits.get("lastModifiedBy")) : null;
    }

}

