package com.airshiplay.play.website.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QWebsiteConfig is a Querydsl query type for WebsiteConfig
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QWebsiteConfig extends EntityPathBase<WebsiteConfig> {

    private static final long serialVersionUID = 481489788L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QWebsiteConfig websiteConfig = new QWebsiteConfig("websiteConfig");

    public final com.airshiplay.play.repo.jpa.QDataEntity _super = new com.airshiplay.play.repo.jpa.QDataEntity(this);

    public final com.airshiplay.play.main.entity.QAdminUserEntity createdBy;

    //inherited
    public final DateTimePath<java.util.Date> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.airshiplay.play.main.entity.QAdminUserEntity lastModifiedBy;

    //inherited
    public final DateTimePath<java.util.Date> lastModifiedDate = _super.lastModifiedDate;

    public QWebsiteConfig(String variable) {
        this(WebsiteConfig.class, forVariable(variable), INITS);
    }

    public QWebsiteConfig(Path<? extends WebsiteConfig> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QWebsiteConfig(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QWebsiteConfig(PathMetadata metadata, PathInits inits) {
        this(WebsiteConfig.class, metadata, inits);
    }

    public QWebsiteConfig(Class<? extends WebsiteConfig> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.createdBy = inits.isInitialized("createdBy") ? new com.airshiplay.play.main.entity.QAdminUserEntity(forProperty("createdBy"), inits.get("createdBy")) : null;
        this.lastModifiedBy = inits.isInitialized("lastModifiedBy") ? new com.airshiplay.play.main.entity.QAdminUserEntity(forProperty("lastModifiedBy"), inits.get("lastModifiedBy")) : null;
    }

}

