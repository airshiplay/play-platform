package com.airshiplay.play.obd.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCarEntity is a Querydsl query type for CarEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCarEntity extends EntityPathBase<CarEntity> {

    private static final long serialVersionUID = 1643133440L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCarEntity carEntity = new QCarEntity("carEntity");

    public final com.airshiplay.play.repo.jpa.QDataEntity _super = new com.airshiplay.play.repo.jpa.QDataEntity(this);

    public final SetPath<CarAlarmEntity, QCarAlarmEntity> alarms = this.<CarAlarmEntity, QCarAlarmEntity>createSet("alarms", CarAlarmEntity.class, QCarAlarmEntity.class, PathInits.DIRECT2);

    public final StringPath brand = createString("brand");

    public final com.airshiplay.play.main.entity.QAdminUserEntity createdBy;

    //inherited
    public final DateTimePath<java.util.Date> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.airshiplay.play.main.entity.QAdminUserEntity lastModifiedBy;

    //inherited
    public final DateTimePath<java.util.Date> lastModifiedDate = _super.lastModifiedDate;

    public final StringPath licenseNo = createString("licenseNo");

    public final StringPath model = createString("model");

    public final QObdDeviceEntity obdDevice;

    public final QObdUserEntity user;

    public QCarEntity(String variable) {
        this(CarEntity.class, forVariable(variable), INITS);
    }

    public QCarEntity(Path<? extends CarEntity> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCarEntity(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCarEntity(PathMetadata metadata, PathInits inits) {
        this(CarEntity.class, metadata, inits);
    }

    public QCarEntity(Class<? extends CarEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.createdBy = inits.isInitialized("createdBy") ? new com.airshiplay.play.main.entity.QAdminUserEntity(forProperty("createdBy"), inits.get("createdBy")) : null;
        this.lastModifiedBy = inits.isInitialized("lastModifiedBy") ? new com.airshiplay.play.main.entity.QAdminUserEntity(forProperty("lastModifiedBy"), inits.get("lastModifiedBy")) : null;
        this.obdDevice = inits.isInitialized("obdDevice") ? new QObdDeviceEntity(forProperty("obdDevice"), inits.get("obdDevice")) : null;
        this.user = inits.isInitialized("user") ? new QObdUserEntity(forProperty("user"), inits.get("user")) : null;
    }

}

