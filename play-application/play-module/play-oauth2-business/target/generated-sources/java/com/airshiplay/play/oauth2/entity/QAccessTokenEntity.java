package com.airshiplay.play.oauth2.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAccessTokenEntity is a Querydsl query type for AccessTokenEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAccessTokenEntity extends EntityPathBase<AccessTokenEntity> {

    private static final long serialVersionUID = 1500047689L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAccessTokenEntity accessTokenEntity = new QAccessTokenEntity("accessTokenEntity");

    public final com.airshiplay.play.repo.jpa.QDataEntity _super = new com.airshiplay.play.repo.jpa.QDataEntity(this);

    public final StringPath authenticationId = createString("authenticationId");

    public final StringPath clientId = createString("clientId");

    public final com.airshiplay.play.main.entity.QUserEntity createdBy;

    //inherited
    public final DateTimePath<java.util.Date> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.airshiplay.play.main.entity.QUserEntity lastModifiedBy;

    //inherited
    public final DateTimePath<java.util.Date> lastModifiedDate = _super.lastModifiedDate;

    public final StringPath refreshToken = createString("refreshToken");

    public final NumberPath<Integer> refreshTokenExpiredSeconds = createNumber("refreshTokenExpiredSeconds", Integer.class);

    public final NumberPath<Integer> tokenExpiredSeconds = createNumber("tokenExpiredSeconds", Integer.class);

    public final StringPath tokenId = createString("tokenId");

    public final StringPath tokenType = createString("tokenType");

    public final StringPath username = createString("username");

    public QAccessTokenEntity(String variable) {
        this(AccessTokenEntity.class, forVariable(variable), INITS);
    }

    public QAccessTokenEntity(Path<? extends AccessTokenEntity> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QAccessTokenEntity(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QAccessTokenEntity(PathMetadata metadata, PathInits inits) {
        this(AccessTokenEntity.class, metadata, inits);
    }

    public QAccessTokenEntity(Class<? extends AccessTokenEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.createdBy = inits.isInitialized("createdBy") ? new com.airshiplay.play.main.entity.QUserEntity(forProperty("createdBy"), inits.get("createdBy")) : null;
        this.lastModifiedBy = inits.isInitialized("lastModifiedBy") ? new com.airshiplay.play.main.entity.QUserEntity(forProperty("lastModifiedBy"), inits.get("lastModifiedBy")) : null;
    }

}

