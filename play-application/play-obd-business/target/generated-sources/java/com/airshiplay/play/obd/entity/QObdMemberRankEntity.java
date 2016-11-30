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

    public final com.airshiplay.play.main.entity.QMemberRankEntity _super;

    //inherited
    public final StringPath code;

    // inherited
    public final com.airshiplay.play.main.entity.QAdminUserEntity createdBy;

    //inherited
    public final DateTimePath<java.util.Date> createdDate;

    public final StringPath description = createString("description");

    //inherited
    public final StringPath icon;

    //inherited
    public final NumberPath<Long> id;

    // inherited
    public final com.airshiplay.play.main.entity.QAdminUserEntity lastModifiedBy;

    //inherited
    public final DateTimePath<java.util.Date> lastModifiedDate;

    //inherited
    public final StringPath name;

    public final SetPath<ObdUserEntity, QObdUserEntity> obdusers = this.<ObdUserEntity, QObdUserEntity>createSet("obdusers", ObdUserEntity.class, QObdUserEntity.class, PathInits.DIRECT2);

    //inherited
    public final SetPath<com.airshiplay.play.main.entity.MemberUserEntity, com.airshiplay.play.main.entity.QMemberUserEntity> users;

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
        this._super = new com.airshiplay.play.main.entity.QMemberRankEntity(type, metadata, inits);
        this.code = _super.code;
        this.createdBy = _super.createdBy;
        this.createdDate = _super.createdDate;
        this.icon = _super.icon;
        this.id = _super.id;
        this.lastModifiedBy = _super.lastModifiedBy;
        this.lastModifiedDate = _super.lastModifiedDate;
        this.name = _super.name;
        this.users = _super.users;
    }

}

