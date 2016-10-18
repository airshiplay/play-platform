package com.airshiplay.play.main.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSettingEntity is a Querydsl query type for SettingEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSettingEntity extends EntityPathBase<SettingEntity> {

    private static final long serialVersionUID = 1470405798L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSettingEntity settingEntity = new QSettingEntity("settingEntity");

    public final com.airshiplay.play.repo.jpa.QDataEntity _super = new com.airshiplay.play.repo.jpa.QDataEntity(this);

    public final StringPath address = createString("address");

    public final StringPath adminLoginBanner = createString("adminLoginBanner");

    public final StringPath certtext = createString("certtext");

    public final QUserEntity createdBy;

    //inherited
    public final DateTimePath<java.util.Date> createdDate = _super.createdDate;

    public final StringPath email = createString("email");

    public final StringPath hotSearch = createString("hotSearch");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath introduction = createString("introduction");

    public final BooleanPath isSiteEnabled = createBoolean("isSiteEnabled");

    public final QUserEntity lastModifiedBy;

    //inherited
    public final DateTimePath<java.util.Date> lastModifiedDate = _super.lastModifiedDate;

    public final StringPath logo = createString("logo");

    public final StringPath phone1 = createString("phone1");

    public final StringPath phone2 = createString("phone2");

    public final StringPath shortSiteName = createString("shortSiteName");

    public final StringPath siteCloseMessage = createString("siteCloseMessage");

    public final StringPath siteName = createString("siteName");

    public final StringPath zipCode = createString("zipCode");

    public QSettingEntity(String variable) {
        this(SettingEntity.class, forVariable(variable), INITS);
    }

    public QSettingEntity(Path<? extends SettingEntity> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSettingEntity(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSettingEntity(PathMetadata metadata, PathInits inits) {
        this(SettingEntity.class, metadata, inits);
    }

    public QSettingEntity(Class<? extends SettingEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.createdBy = inits.isInitialized("createdBy") ? new QUserEntity(forProperty("createdBy"), inits.get("createdBy")) : null;
        this.lastModifiedBy = inits.isInitialized("lastModifiedBy") ? new QUserEntity(forProperty("lastModifiedBy"), inits.get("lastModifiedBy")) : null;
    }

}

