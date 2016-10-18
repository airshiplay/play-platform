package com.airshiplay.play.main.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserCredentialsEntity is a Querydsl query type for UserCredentialsEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserCredentialsEntity extends EntityPathBase<UserCredentialsEntity> {

    private static final long serialVersionUID = 783581383L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserCredentialsEntity userCredentialsEntity = new QUserCredentialsEntity("userCredentialsEntity");

    public final com.airshiplay.play.repo.jpa.QBaseEntity _super = new com.airshiplay.play.repo.jpa.QBaseEntity(this);

    public final StringPath account = createString("account");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath secret = createString("secret");

    public final StringPath type = createString("type");

    public final QUserEntity user;

    public QUserCredentialsEntity(String variable) {
        this(UserCredentialsEntity.class, forVariable(variable), INITS);
    }

    public QUserCredentialsEntity(Path<? extends UserCredentialsEntity> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QUserCredentialsEntity(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QUserCredentialsEntity(PathMetadata metadata, PathInits inits) {
        this(UserCredentialsEntity.class, metadata, inits);
    }

    public QUserCredentialsEntity(Class<? extends UserCredentialsEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUserEntity(forProperty("user"), inits.get("user")) : null;
    }

}

