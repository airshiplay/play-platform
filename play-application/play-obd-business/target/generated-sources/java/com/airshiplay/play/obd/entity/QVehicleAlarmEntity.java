package com.airshiplay.play.obd.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QVehicleAlarmEntity is a Querydsl query type for VehicleAlarmEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QVehicleAlarmEntity extends EntityPathBase<VehicleAlarmEntity> {

    private static final long serialVersionUID = 903992255L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QVehicleAlarmEntity vehicleAlarmEntity = new QVehicleAlarmEntity("vehicleAlarmEntity");

    public final com.airshiplay.play.repo.jpa.QDataEntity _super = new com.airshiplay.play.repo.jpa.QDataEntity(this);

    public final com.airshiplay.play.main.entity.QAdminUserEntity createdBy;

    //inherited
    public final DateTimePath<java.util.Date> createdDate = _super.createdDate;

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.airshiplay.play.main.entity.QAdminUserEntity lastModifiedBy;

    //inherited
    public final DateTimePath<java.util.Date> lastModifiedDate = _super.lastModifiedDate;

    public final QObdDeviceEntity obdDevice;

    public final EnumPath<VehicleAlarmEntity.Status> status = createEnum("status", VehicleAlarmEntity.Status.class);

    public final EnumPath<VehicleAlarmEntity.AlarmType> type = createEnum("type", VehicleAlarmEntity.AlarmType.class);

    public QVehicleAlarmEntity(String variable) {
        this(VehicleAlarmEntity.class, forVariable(variable), INITS);
    }

    public QVehicleAlarmEntity(Path<? extends VehicleAlarmEntity> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QVehicleAlarmEntity(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QVehicleAlarmEntity(PathMetadata metadata, PathInits inits) {
        this(VehicleAlarmEntity.class, metadata, inits);
    }

    public QVehicleAlarmEntity(Class<? extends VehicleAlarmEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.createdBy = inits.isInitialized("createdBy") ? new com.airshiplay.play.main.entity.QAdminUserEntity(forProperty("createdBy"), inits.get("createdBy")) : null;
        this.lastModifiedBy = inits.isInitialized("lastModifiedBy") ? new com.airshiplay.play.main.entity.QAdminUserEntity(forProperty("lastModifiedBy"), inits.get("lastModifiedBy")) : null;
        this.obdDevice = inits.isInitialized("obdDevice") ? new QObdDeviceEntity(forProperty("obdDevice"), inits.get("obdDevice")) : null;
    }

}

