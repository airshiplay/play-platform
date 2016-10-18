package com.airshiplay.play.main.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMenuEntity is a Querydsl query type for MenuEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMenuEntity extends EntityPathBase<MenuEntity> {

    private static final long serialVersionUID = -721239889L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMenuEntity menuEntity = new QMenuEntity("menuEntity");

    public final com.airshiplay.play.repo.jpa.QHierarchicalEntity _super = new com.airshiplay.play.repo.jpa.QHierarchicalEntity(this);

    public final ListPath<MenuEntity, QMenuEntity> children = this.<MenuEntity, QMenuEntity>createList("children", MenuEntity.class, QMenuEntity.class, PathInits.DIRECT2);

    public final StringPath code = createString("code");

    public final StringPath config = createString("config");

    public final QUserEntity createdBy;

    //inherited
    public final DateTimePath<java.util.Date> createdDate = _super.createdDate;

    public final StringPath iconCls = createString("iconCls");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QUserEntity lastModifiedBy;

    //inherited
    public final DateTimePath<java.util.Date> lastModifiedDate = _super.lastModifiedDate;

    public final QMenuEntity parent;

    //inherited
    public final NumberPath<Integer> sortNo = _super.sortNo;

    public final StringPath text = createString("text");

    //inherited
    public final StringPath treePath = _super.treePath;

    public final StringPath view = createString("view");

    public QMenuEntity(String variable) {
        this(MenuEntity.class, forVariable(variable), INITS);
    }

    public QMenuEntity(Path<? extends MenuEntity> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QMenuEntity(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QMenuEntity(PathMetadata metadata, PathInits inits) {
        this(MenuEntity.class, metadata, inits);
    }

    public QMenuEntity(Class<? extends MenuEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.createdBy = inits.isInitialized("createdBy") ? new QUserEntity(forProperty("createdBy"), inits.get("createdBy")) : null;
        this.lastModifiedBy = inits.isInitialized("lastModifiedBy") ? new QUserEntity(forProperty("lastModifiedBy"), inits.get("lastModifiedBy")) : null;
        this.parent = inits.isInitialized("parent") ? new QMenuEntity(forProperty("parent"), inits.get("parent")) : null;
    }

}

