package com.airshiplay.play.obd.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QObdCfgCarEntity is a Querydsl query type for ObdCfgCarEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QObdCfgCarEntity extends EntityPathBase<ObdCfgCarEntity> {

    private static final long serialVersionUID = -1746016563L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QObdCfgCarEntity obdCfgCarEntity = new QObdCfgCarEntity("obdCfgCarEntity");

    public final com.airshiplay.play.repo.jpa.QHierarchicalEntity _super = new com.airshiplay.play.repo.jpa.QHierarchicalEntity(this);

    public final ListPath<ObdCfgCarEntity, QObdCfgCarEntity> children = this.<ObdCfgCarEntity, QObdCfgCarEntity>createList("children", ObdCfgCarEntity.class, QObdCfgCarEntity.class, PathInits.DIRECT2);

    public final StringPath code = createString("code");

    public final com.airshiplay.play.main.entity.QAdminUserEntity createdBy;

    //inherited
    public final DateTimePath<java.util.Date> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.airshiplay.play.main.entity.QAdminUserEntity lastModifiedBy;

    //inherited
    public final DateTimePath<java.util.Date> lastModifiedDate = _super.lastModifiedDate;

    public final StringPath name = createString("name");

    public final StringPath oemName = createString("oemName");

    public final QObdCfgCarEntity parent;

    //inherited
    public final NumberPath<Integer> sortNo = _super.sortNo;

    //inherited
    public final StringPath treePath = _super.treePath;

    public final EnumPath<ObdCfgCarEntity.CfgCarType> type = createEnum("type", ObdCfgCarEntity.CfgCarType.class);

    public QObdCfgCarEntity(String variable) {
        this(ObdCfgCarEntity.class, forVariable(variable), INITS);
    }

    public QObdCfgCarEntity(Path<? extends ObdCfgCarEntity> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QObdCfgCarEntity(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QObdCfgCarEntity(PathMetadata metadata, PathInits inits) {
        this(ObdCfgCarEntity.class, metadata, inits);
    }

    public QObdCfgCarEntity(Class<? extends ObdCfgCarEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.createdBy = inits.isInitialized("createdBy") ? new com.airshiplay.play.main.entity.QAdminUserEntity(forProperty("createdBy"), inits.get("createdBy")) : null;
        this.lastModifiedBy = inits.isInitialized("lastModifiedBy") ? new com.airshiplay.play.main.entity.QAdminUserEntity(forProperty("lastModifiedBy"), inits.get("lastModifiedBy")) : null;
        this.parent = inits.isInitialized("parent") ? new QObdCfgCarEntity(forProperty("parent"), inits.get("parent")) : null;
    }

}

