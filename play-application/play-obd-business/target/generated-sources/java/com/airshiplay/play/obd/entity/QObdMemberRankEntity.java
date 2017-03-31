package com.airshiplay.play.obd.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QObdMemberRankEntity is a Querydsl query type for ObdMemberRankEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QObdMemberRankEntity extends EntityPathBase<ObdMemberRankEntity> {

    private static final long serialVersionUID = -642768285L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QObdMemberRankEntity obdMemberRankEntity = new QObdMemberRankEntity("obdMemberRankEntity");

    public final com.airshiplay.play.repo.jpa.QDataEntity _super = new com.airshiplay.play.repo.jpa.QDataEntity(this);

    public final StringPath code = createString("code");

    public final com.airshiplay.play.main.entity.QAdminUserEntity createdBy;

    //inherited
    public final DateTimePath<java.util.Date> createdDate = _super.createdDate;

    public final StringPath description = createString("description");

    public final StringPath icon = createString("icon");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.airshiplay.play.main.entity.QAdminUserEntity lastModifiedBy;

    //inherited
    public final DateTimePath<java.util.Date> lastModifiedDate = _super.lastModifiedDate;

    public final StringPath name = createString("name");

    public final SetPath<ObdUserEntity, QObdUserEntity> obdusers = this.<ObdUserEntity, QObdUserEntity>createSet("obdusers", ObdUserEntity.class, QObdUserEntity.class, PathInits.DIRECT2);

    public QObdMemberRankEntity(String variable) {
        this(ObdMemberRankEntity.class, forVariable(variable), INITS);
    }

    public QObdMemberRankEntity(Path<? extends ObdMemberRankEntity> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QObdMemberRankEntity(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QObdMemberRankEntity(PathMetadata metadata, PathInits inits) {
        this(ObdMemberRankEntity.class, metadata, inits);
    }

    public QObdMemberRankEntity(Class<? extends ObdMemberRankEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.createdBy = inits.isInitialized("createdBy") ? new com.airshiplay.play.main.entity.QAdminUserEntity(forProperty("createdBy"), inits.get("createdBy")) : null;
        this.lastModifiedBy = inits.isInitialized("lastModifiedBy") ? new com.airshiplay.play.main.entity.QAdminUserEntity(forProperty("lastModifiedBy"), inits.get("lastModifiedBy")) : null;
    }

}

