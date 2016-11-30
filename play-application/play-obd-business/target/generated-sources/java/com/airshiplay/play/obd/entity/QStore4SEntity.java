package com.airshiplay.play.obd.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStore4SEntity is a Querydsl query type for Store4SEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QStore4SEntity extends EntityPathBase<Store4SEntity> {

    private static final long serialVersionUID = -1127675924L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStore4SEntity store4SEntity = new QStore4SEntity("store4SEntity");

    public final com.airshiplay.play.repo.jpa.QDataEntity _super = new com.airshiplay.play.repo.jpa.QDataEntity(this);

    public final StringPath address = createString("address");

    public final com.airshiplay.play.main.entity.QAreaEntity area;

    public final com.airshiplay.play.main.entity.QAdminUserEntity createdBy;

    //inherited
    public final DateTimePath<java.util.Date> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.airshiplay.play.main.entity.QAdminUserEntity lastModifiedBy;

    //inherited
    public final DateTimePath<java.util.Date> lastModifiedDate = _super.lastModifiedDate;

    public final StringPath lat = createString("lat");

    public final StringPath lng = createString("lng");

    public final StringPath name = createString("name");

    public final StringPath phone = createString("phone");

    public final com.airshiplay.play.main.entity.QAdminUserEntity primaryLeader;

    public QStore4SEntity(String variable) {
        this(Store4SEntity.class, forVariable(variable), INITS);
    }

    public QStore4SEntity(Path<? extends Store4SEntity> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QStore4SEntity(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QStore4SEntity(PathMetadata metadata, PathInits inits) {
        this(Store4SEntity.class, metadata, inits);
    }

    public QStore4SEntity(Class<? extends Store4SEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.area = inits.isInitialized("area") ? new com.airshiplay.play.main.entity.QAreaEntity(forProperty("area")) : null;
        this.createdBy = inits.isInitialized("createdBy") ? new com.airshiplay.play.main.entity.QAdminUserEntity(forProperty("createdBy"), inits.get("createdBy")) : null;
        this.lastModifiedBy = inits.isInitialized("lastModifiedBy") ? new com.airshiplay.play.main.entity.QAdminUserEntity(forProperty("lastModifiedBy"), inits.get("lastModifiedBy")) : null;
        this.primaryLeader = inits.isInitialized("primaryLeader") ? new com.airshiplay.play.main.entity.QAdminUserEntity(forProperty("primaryLeader"), inits.get("primaryLeader")) : null;
    }

}

