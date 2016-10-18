package com.airshiplay.play.app.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAppEntity is a Querydsl query type for AppEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAppEntity extends EntityPathBase<AppEntity> {

    private static final long serialVersionUID = -1323647139L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAppEntity appEntity = new QAppEntity("appEntity");

    public final com.airshiplay.play.repo.jpa.QDataEntity _super = new com.airshiplay.play.repo.jpa.QDataEntity(this);

    public final com.airshiplay.play.main.entity.QUserEntity createdBy;

    //inherited
    public final DateTimePath<java.util.Date> createdDate = _super.createdDate;

    public final StringPath description = createString("description");

    public final NumberPath<Long> fileSizeBytes = createNumber("fileSizeBytes", Long.class);

    public final StringPath iconPath = createString("iconPath");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath identifier = createString("identifier");

    public final com.airshiplay.play.main.entity.QUserEntity lastModifiedBy;

    //inherited
    public final DateTimePath<java.util.Date> lastModifiedDate = _super.lastModifiedDate;

    public final StringPath name = createString("name");

    public final ListPath<String, StringPath> photos = this.<String, StringPath>createList("photos", String.class, StringPath.class, PathInits.DIRECT2);

    public final EnumPath<AppEntity.Platform> platform = createEnum("platform", AppEntity.Platform.class);

    public final EnumPath<AppEntity.AppSource> source = createEnum("source", AppEntity.AppSource.class);

    public final StringPath trackId = createString("trackId");

    public final StringPath version = createString("version");

    public QAppEntity(String variable) {
        this(AppEntity.class, forVariable(variable), INITS);
    }

    public QAppEntity(Path<? extends AppEntity> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QAppEntity(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QAppEntity(PathMetadata metadata, PathInits inits) {
        this(AppEntity.class, metadata, inits);
    }

    public QAppEntity(Class<? extends AppEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.createdBy = inits.isInitialized("createdBy") ? new com.airshiplay.play.main.entity.QUserEntity(forProperty("createdBy"), inits.get("createdBy")) : null;
        this.lastModifiedBy = inits.isInitialized("lastModifiedBy") ? new com.airshiplay.play.main.entity.QUserEntity(forProperty("lastModifiedBy"), inits.get("lastModifiedBy")) : null;
    }

}

