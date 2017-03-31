package com.airshiplay.play.obd.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QObdOrderEntity is a Querydsl query type for ObdOrderEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QObdOrderEntity extends EntityPathBase<ObdOrderEntity> {

    private static final long serialVersionUID = 1750763415L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QObdOrderEntity obdOrderEntity = new QObdOrderEntity("obdOrderEntity");

    public final com.airshiplay.play.repo.jpa.QDataEntity _super = new com.airshiplay.play.repo.jpa.QDataEntity(this);

    public final com.airshiplay.play.main.entity.QAdminUserEntity createdBy;

    //inherited
    public final DateTimePath<java.util.Date> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.airshiplay.play.main.entity.QAdminUserEntity lastModifiedBy;

    //inherited
    public final DateTimePath<java.util.Date> lastModifiedDate = _super.lastModifiedDate;

    public final SetPath<ObdUserEntity, QObdUserEntity> users = this.<ObdUserEntity, QObdUserEntity>createSet("users", ObdUserEntity.class, QObdUserEntity.class, PathInits.DIRECT2);

    public QObdOrderEntity(String variable) {
        this(ObdOrderEntity.class, forVariable(variable), INITS);
    }

    public QObdOrderEntity(Path<? extends ObdOrderEntity> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QObdOrderEntity(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QObdOrderEntity(PathMetadata metadata, PathInits inits) {
        this(ObdOrderEntity.class, metadata, inits);
    }

    public QObdOrderEntity(Class<? extends ObdOrderEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.createdBy = inits.isInitialized("createdBy") ? new com.airshiplay.play.main.entity.QAdminUserEntity(forProperty("createdBy"), inits.get("createdBy")) : null;
        this.lastModifiedBy = inits.isInitialized("lastModifiedBy") ? new com.airshiplay.play.main.entity.QAdminUserEntity(forProperty("lastModifiedBy"), inits.get("lastModifiedBy")) : null;
    }

}

