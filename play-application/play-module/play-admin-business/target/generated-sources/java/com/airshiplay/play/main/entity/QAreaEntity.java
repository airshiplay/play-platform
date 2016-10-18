package com.airshiplay.play.main.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAreaEntity is a Querydsl query type for AreaEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAreaEntity extends EntityPathBase<AreaEntity> {

    private static final long serialVersionUID = 539222365L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAreaEntity areaEntity = new QAreaEntity("areaEntity");

    public final com.airshiplay.play.repo.jpa.QHierarchicalEntity _super = new com.airshiplay.play.repo.jpa.QHierarchicalEntity(this);

    public final ListPath<AreaEntity, QAreaEntity> children = this.<AreaEntity, QAreaEntity>createList("children", AreaEntity.class, QAreaEntity.class, PathInits.DIRECT2);

    public final QUserEntity createdBy;

    //inherited
    public final DateTimePath<java.util.Date> createdDate = _super.createdDate;

    public final StringPath fullName = createString("fullName");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QUserEntity lastModifiedBy;

    //inherited
    public final DateTimePath<java.util.Date> lastModifiedDate = _super.lastModifiedDate;

    public final StringPath name = createString("name");

    public final QAreaEntity parent;

    //inherited
    public final NumberPath<Integer> sortNo = _super.sortNo;

    //inherited
    public final StringPath treePath = _super.treePath;

    public final EnumPath<AreaEntity.AreaType> type = createEnum("type", AreaEntity.AreaType.class);

    public QAreaEntity(String variable) {
        this(AreaEntity.class, forVariable(variable), INITS);
    }

    public QAreaEntity(Path<? extends AreaEntity> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QAreaEntity(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QAreaEntity(PathMetadata metadata, PathInits inits) {
        this(AreaEntity.class, metadata, inits);
    }

    public QAreaEntity(Class<? extends AreaEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.createdBy = inits.isInitialized("createdBy") ? new QUserEntity(forProperty("createdBy"), inits.get("createdBy")) : null;
        this.lastModifiedBy = inits.isInitialized("lastModifiedBy") ? new QUserEntity(forProperty("lastModifiedBy"), inits.get("lastModifiedBy")) : null;
        this.parent = inits.isInitialized("parent") ? new QAreaEntity(forProperty("parent"), inits.get("parent")) : null;
    }

}

