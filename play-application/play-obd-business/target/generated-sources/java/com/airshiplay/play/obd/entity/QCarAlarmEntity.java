package com.airshiplay.play.obd.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCarAlarmEntity is a Querydsl query type for CarAlarmEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCarAlarmEntity extends EntityPathBase<CarAlarmEntity> {

    private static final long serialVersionUID = -233734761L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCarAlarmEntity carAlarmEntity = new QCarAlarmEntity("carAlarmEntity");

    public final com.airshiplay.play.repo.jpa.QDataEntity _super = new com.airshiplay.play.repo.jpa.QDataEntity(this);

    public final QCarEntity car;

    public final com.airshiplay.play.main.entity.QAdminUserEntity createdBy;

    //inherited
    public final DateTimePath<java.util.Date> createdDate = _super.createdDate;

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.airshiplay.play.main.entity.QAdminUserEntity lastModifiedBy;

    //inherited
    public final DateTimePath<java.util.Date> lastModifiedDate = _super.lastModifiedDate;

    public final QObdDeviceEntity obdDevice;

    public final EnumPath<CarAlarmEntity.Status> status = createEnum("status", CarAlarmEntity.Status.class);

    public final EnumPath<CarAlarmEntity.AlarmType> type = createEnum("type", CarAlarmEntity.AlarmType.class);

    public QCarAlarmEntity(String variable) {
        this(CarAlarmEntity.class, forVariable(variable), INITS);
    }

    public QCarAlarmEntity(Path<? extends CarAlarmEntity> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCarAlarmEntity(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCarAlarmEntity(PathMetadata metadata, PathInits inits) {
        this(CarAlarmEntity.class, metadata, inits);
    }

    public QCarAlarmEntity(Class<? extends CarAlarmEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.car = inits.isInitialized("car") ? new QCarEntity(forProperty("car"), inits.get("car")) : null;
        this.createdBy = inits.isInitialized("createdBy") ? new com.airshiplay.play.main.entity.QAdminUserEntity(forProperty("createdBy"), inits.get("createdBy")) : null;
        this.lastModifiedBy = inits.isInitialized("lastModifiedBy") ? new com.airshiplay.play.main.entity.QAdminUserEntity(forProperty("lastModifiedBy"), inits.get("lastModifiedBy")) : null;
        this.obdDevice = inits.isInitialized("obdDevice") ? new QObdDeviceEntity(forProperty("obdDevice"), inits.get("obdDevice")) : null;
    }

}

