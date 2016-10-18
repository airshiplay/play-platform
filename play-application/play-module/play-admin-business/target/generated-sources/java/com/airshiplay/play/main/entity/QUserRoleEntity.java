package com.airshiplay.play.main.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserRoleEntity is a Querydsl query type for UserRoleEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserRoleEntity extends EntityPathBase<UserRoleEntity> {

    private static final long serialVersionUID = -835389903L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserRoleEntity userRoleEntity = new QUserRoleEntity("userRoleEntity");

    public final com.airshiplay.play.repo.jpa.QBaseEntity _super = new com.airshiplay.play.repo.jpa.QBaseEntity(this);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QRoleEntity role;

    public final QUserEntity user;

    public QUserRoleEntity(String variable) {
        this(UserRoleEntity.class, forVariable(variable), INITS);
    }

    public QUserRoleEntity(Path<? extends UserRoleEntity> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QUserRoleEntity(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QUserRoleEntity(PathMetadata metadata, PathInits inits) {
        this(UserRoleEntity.class, metadata, inits);
    }

    public QUserRoleEntity(Class<? extends UserRoleEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.role = inits.isInitialized("role") ? new QRoleEntity(forProperty("role"), inits.get("role")) : null;
        this.user = inits.isInitialized("user") ? new QUserEntity(forProperty("user"), inits.get("user")) : null;
    }

}

