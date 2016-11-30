package com.airshiplay.play.obd.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QObdUserEntity is a Querydsl query type for ObdUserEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QObdUserEntity extends EntityPathBase<ObdUserEntity> {

    private static final long serialVersionUID = -875387896L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QObdUserEntity obdUserEntity = new QObdUserEntity("obdUserEntity");

    public final com.airshiplay.play.main.entity.QMemberUserEntity _super;

    //inherited
    public final BooleanPath accountExpired;

    //inherited
    public final NumberPath<Integer> age;

    // inherited
    public final com.airshiplay.play.main.entity.QAreaEntity area;

    //inherited
    public final StringPath areaName;

    //inherited
    public final DateTimePath<java.util.Date> birthday;

    public final SetPath<VehicleEntity, QVehicleEntity> cars = this.<VehicleEntity, QVehicleEntity>createSet("cars", VehicleEntity.class, QVehicleEntity.class, PathInits.DIRECT2);

    // inherited
    public final com.airshiplay.play.main.entity.QAdminUserEntity createdBy;

    //inherited
    public final DateTimePath<java.util.Date> createdDate;

    //inherited
    public final BooleanPath credentialsExpired;

    //inherited
    public final StringPath email;

    //inherited
    public final BooleanPath enabled;

    //inherited
    public final NumberPath<Long> id;

    //inherited
    public final DateTimePath<java.util.Date> lastLoginDate;

    //inherited
    public final StringPath lastLoginIp;

    // inherited
    public final com.airshiplay.play.main.entity.QAdminUserEntity lastModifiedBy;

    //inherited
    public final DateTimePath<java.util.Date> lastModifiedDate;

    //inherited
    public final BooleanPath locked;

    //inherited
    public final StringPath mobile;

    //inherited
    public final StringPath nickname;

    public final QObdMemberRankEntity obdrank;

    public final SetPath<OrderEntity, QOrderEntity> orders = this.<OrderEntity, QOrderEntity>createSet("orders", OrderEntity.class, QOrderEntity.class, PathInits.DIRECT2);

    //inherited
    public final StringPath password;

    //inherited
    public final StringPath photo;

    // inherited
    public final com.airshiplay.play.main.entity.QMemberRankEntity rank;

    //inherited
    public final StringPath realname;

    //inherited
    public final StringPath salt;

    //inherited
    public final StringPath sex;

    //inherited
    public final StringPath username;

    public QObdUserEntity(String variable) {
        this(ObdUserEntity.class, forVariable(variable), INITS);
    }

    public QObdUserEntity(Path<? extends ObdUserEntity> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QObdUserEntity(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QObdUserEntity(PathMetadata metadata, PathInits inits) {
        this(ObdUserEntity.class, metadata, inits);
    }

    public QObdUserEntity(Class<? extends ObdUserEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new com.airshiplay.play.main.entity.QMemberUserEntity(type, metadata, inits);
        this.accountExpired = _super.accountExpired;
        this.age = _super.age;
        this.area = _super.area;
        this.areaName = _super.areaName;
        this.birthday = _super.birthday;
        this.createdBy = _super.createdBy;
        this.createdDate = _super.createdDate;
        this.credentialsExpired = _super.credentialsExpired;
        this.email = _super.email;
        this.enabled = _super.enabled;
        this.id = _super.id;
        this.lastLoginDate = _super.lastLoginDate;
        this.lastLoginIp = _super.lastLoginIp;
        this.lastModifiedBy = _super.lastModifiedBy;
        this.lastModifiedDate = _super.lastModifiedDate;
        this.locked = _super.locked;
        this.mobile = _super.mobile;
        this.nickname = _super.nickname;
        this.obdrank = inits.isInitialized("obdrank") ? new QObdMemberRankEntity(forProperty("obdrank"), inits.get("obdrank")) : null;
        this.password = _super.password;
        this.photo = _super.photo;
        this.rank = _super.rank;
        this.realname = _super.realname;
        this.salt = _super.salt;
        this.sex = _super.sex;
        this.username = _super.username;
    }

}

