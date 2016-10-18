package com.airshiplay.play.plugin.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPluginConfigEntity is a Querydsl query type for PluginConfigEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPluginConfigEntity extends EntityPathBase<PluginConfigEntity> {

    private static final long serialVersionUID = -1624748705L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPluginConfigEntity pluginConfigEntity = new QPluginConfigEntity("pluginConfigEntity");

    public final com.airshiplay.play.repo.jpa.QSortEntity _super = new com.airshiplay.play.repo.jpa.QSortEntity(this);

    public final MapPath<String, String, StringPath> attributes = this.<String, String, StringPath>createMap("attributes", String.class, String.class, StringPath.class);

    public final com.airshiplay.play.main.entity.QUserEntity createdBy;

    //inherited
    public final DateTimePath<java.util.Date> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isEnabled = createBoolean("isEnabled");

    public final com.airshiplay.play.main.entity.QUserEntity lastModifiedBy;

    //inherited
    public final DateTimePath<java.util.Date> lastModifiedDate = _super.lastModifiedDate;

    public final StringPath pluginId = createString("pluginId");

    //inherited
    public final NumberPath<Integer> sortNo = _super.sortNo;

    public QPluginConfigEntity(String variable) {
        this(PluginConfigEntity.class, forVariable(variable), INITS);
    }

    public QPluginConfigEntity(Path<? extends PluginConfigEntity> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QPluginConfigEntity(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QPluginConfigEntity(PathMetadata metadata, PathInits inits) {
        this(PluginConfigEntity.class, metadata, inits);
    }

    public QPluginConfigEntity(Class<? extends PluginConfigEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.createdBy = inits.isInitialized("createdBy") ? new com.airshiplay.play.main.entity.QUserEntity(forProperty("createdBy"), inits.get("createdBy")) : null;
        this.lastModifiedBy = inits.isInitialized("lastModifiedBy") ? new com.airshiplay.play.main.entity.QUserEntity(forProperty("lastModifiedBy"), inits.get("lastModifiedBy")) : null;
    }

}

