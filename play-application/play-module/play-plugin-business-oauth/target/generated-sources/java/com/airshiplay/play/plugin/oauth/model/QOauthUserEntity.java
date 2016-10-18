package com.airshiplay.play.plugin.oauth.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOauthUserEntity is a Querydsl query type for OauthUserEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QOauthUserEntity extends EntityPathBase<OauthUserEntity> {

    private static final long serialVersionUID = -110726587L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOauthUserEntity oauthUserEntity = new QOauthUserEntity("oauthUserEntity");

    public final com.airshiplay.play.repo.jpa.QBaseEntity _super = new com.airshiplay.play.repo.jpa.QBaseEntity(this);

    public final StringPath avatarUrl = createString("avatarUrl");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final StringPath oauthPluginId = createString("oauthPluginId");

    public final com.airshiplay.play.main.entity.QUserEntity owner;

    public final StringPath userId = createString("userId");

    public final StringPath username = createString("username");

    public QOauthUserEntity(String variable) {
        this(OauthUserEntity.class, forVariable(variable), INITS);
    }

    public QOauthUserEntity(Path<? extends OauthUserEntity> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QOauthUserEntity(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QOauthUserEntity(PathMetadata metadata, PathInits inits) {
        this(OauthUserEntity.class, metadata, inits);
    }

    public QOauthUserEntity(Class<? extends OauthUserEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.owner = inits.isInitialized("owner") ? new com.airshiplay.play.main.entity.QUserEntity(forProperty("owner"), inits.get("owner")) : null;
    }

}

