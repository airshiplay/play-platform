package com.airshiplay.play.obd.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QObdDeviceEntity is a Querydsl query type for ObdDeviceEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QObdDeviceEntity extends EntityPathBase<ObdDeviceEntity> {

    private static final long serialVersionUID = 2101496787L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QObdDeviceEntity obdDeviceEntity = new QObdDeviceEntity("obdDeviceEntity");

    public final com.airshiplay.play.repo.jpa.QDataEntity _super = new com.airshiplay.play.repo.jpa.QDataEntity(this);

    public final SetPath<VehicleAlarmEntity, QVehicleAlarmEntity> alarm = this.<VehicleAlarmEntity, QVehicleAlarmEntity>createSet("alarm", VehicleAlarmEntity.class, QVehicleAlarmEntity.class, PathInits.DIRECT2);

    public final QVehicleEntity car;

    public final com.airshiplay.play.main.entity.QAdminUserEntity createdBy;

    //inherited
    public final DateTimePath<java.util.Date> createdDate = _super.createdDate;

    public final StringPath description = createString("description");

    public final StringPath deviveNo = createString("deviveNo");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.airshiplay.play.main.entity.QAdminUserEntity lastModifiedBy;

    //inherited
    public final DateTimePath<java.util.Date> lastModifiedDate = _super.lastModifiedDate;

    public final StringPath type = createString("type");

    public QObdDeviceEntity(String variable) {
        this(ObdDeviceEntity.class, forVariable(variable), INITS);
    }

    public QObdDeviceEntity(Path<? extends ObdDeviceEntity> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QObdDeviceEntity(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QObdDeviceEntity(PathMetadata metadata, PathInits inits) {
        this(ObdDeviceEntity.class, metadata, inits);
    }

    public QObdDeviceEntity(Class<? extends ObdDeviceEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.car = inits.isInitialized("car") ? new QVehicleEntity(forProperty("car"), inits.get("car")) : null;
        this.createdBy = inits.isInitialized("createdBy") ? new com.airshiplay.play.main.entity.QAdminUserEntity(forProperty("createdBy"), inits.get("createdBy")) : null;
        this.lastModifiedBy = inits.isInitialized("lastModifiedBy") ? new com.airshiplay.play.main.entity.QAdminUserEntity(forProperty("lastModifiedBy"), inits.get("lastModifiedBy")) : null;
    }

}

