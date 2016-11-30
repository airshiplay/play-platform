package com.airshiplay.play.obd.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QVehicleEntity is a Querydsl query type for VehicleEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QVehicleEntity extends EntityPathBase<VehicleEntity> {

    private static final long serialVersionUID = -1258509608L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QVehicleEntity vehicleEntity = new QVehicleEntity("vehicleEntity");

    public final com.airshiplay.play.repo.jpa.QDataEntity _super = new com.airshiplay.play.repo.jpa.QDataEntity(this);

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

    public QVehicleEntity(String variable) {
        this(VehicleEntity.class, forVariable(variable), INITS);
    }

    public QVehicleEntity(Path<? extends VehicleEntity> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QVehicleEntity(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QVehicleEntity(PathMetadata metadata, PathInits inits) {
        this(VehicleEntity.class, metadata, inits);
    }

    public QVehicleEntity(Class<? extends VehicleEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.createdBy = inits.isInitialized("createdBy") ? new com.airshiplay.play.main.entity.QAdminUserEntity(forProperty("createdBy"), inits.get("createdBy")) : null;
        this.lastModifiedBy = inits.isInitialized("lastModifiedBy") ? new com.airshiplay.play.main.entity.QAdminUserEntity(forProperty("lastModifiedBy"), inits.get("lastModifiedBy")) : null;
        this.obdDevice = inits.isInitialized("obdDevice") ? new QObdDeviceEntity(forProperty("obdDevice"), inits.get("obdDevice")) : null;
        this.user = inits.isInitialized("user") ? new QObdUserEntity(forProperty("user"), inits.get("user")) : null;
    }

}

