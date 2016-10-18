package com.airshiplay.play.main.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRoleEntity is a Querydsl query type for RoleEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QRoleEntity extends EntityPathBase<RoleEntity> {

    private static final long serialVersionUID = 1190674054L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRoleEntity roleEntity = new QRoleEntity("roleEntity");

    public final com.airshiplay.play.repo.jpa.QDataEntity _super = new com.airshiplay.play.repo.jpa.QDataEntity(this);

    public final ListPath<String, StringPath> authorities = this.<String, StringPath>createList("authorities", String.class, StringPath.class, PathInits.DIRECT2);

    public final StringPath code = createString("code");

    public final QUserEntity createdBy;

    //inherited
    public final DateTimePath<java.util.Date> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QUserEntity lastModifiedBy;

    //inherited
    public final DateTimePath<java.util.Date> lastModifiedDate = _super.lastModifiedDate;

    public final BooleanPath locked = createBoolean("locked");

    public final SetPath<MenuEntity, QMenuEntity> menus = this.<MenuEntity, QMenuEntity>createSet("menus", MenuEntity.class, QMenuEntity.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public final SetPath<UserRoleEntity, QUserRoleEntity> userRoles = this.<UserRoleEntity, QUserRoleEntity>createSet("userRoles", UserRoleEntity.class, QUserRoleEntity.class, PathInits.DIRECT2);

    public QRoleEntity(String variable) {
        this(RoleEntity.class, forVariable(variable), INITS);
    }

    public QRoleEntity(Path<? extends RoleEntity> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRoleEntity(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRoleEntity(PathMetadata metadata, PathInits inits) {
        this(RoleEntity.class, metadata, inits);
    }

    public QRoleEntity(Class<? extends RoleEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.createdBy = inits.isInitialized("createdBy") ? new QUserEntity(forProperty("createdBy"), inits.get("createdBy")) : null;
        this.lastModifiedBy = inits.isInitialized("lastModifiedBy") ? new QUserEntity(forProperty("lastModifiedBy"), inits.get("lastModifiedBy")) : null;
    }

}

