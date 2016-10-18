package com.airshiplay.play.main.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDictEntity is a Querydsl query type for DictEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDictEntity extends EntityPathBase<DictEntity> {

    private static final long serialVersionUID = -471049786L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDictEntity dictEntity = new QDictEntity("dictEntity");

    public final com.airshiplay.play.repo.jpa.QHierarchicalEntity _super = new com.airshiplay.play.repo.jpa.QHierarchicalEntity(this);

    public final ListPath<DictEntity, QDictEntity> children = this.<DictEntity, QDictEntity>createList("children", DictEntity.class, QDictEntity.class, PathInits.DIRECT2);

    public final QUserEntity createdBy;

    //inherited
    public final DateTimePath<java.util.Date> createdDate = _super.createdDate;

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath label = createString("label");

    public final QUserEntity lastModifiedBy;

    //inherited
    public final DateTimePath<java.util.Date> lastModifiedDate = _super.lastModifiedDate;

    public final StringPath name = createString("name");

    public final QDictEntity parent;

    //inherited
    public final NumberPath<Integer> sortNo = _super.sortNo;

    //inherited
    public final StringPath treePath = _super.treePath;

    public final StringPath type = createString("type");

    public final NumberPath<Integer> value = createNumber("value", Integer.class);

    public QDictEntity(String variable) {
        this(DictEntity.class, forVariable(variable), INITS);
    }

    public QDictEntity(Path<? extends DictEntity> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QDictEntity(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QDictEntity(PathMetadata metadata, PathInits inits) {
        this(DictEntity.class, metadata, inits);
    }

    public QDictEntity(Class<? extends DictEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.createdBy = inits.isInitialized("createdBy") ? new QUserEntity(forProperty("createdBy"), inits.get("createdBy")) : null;
        this.lastModifiedBy = inits.isInitialized("lastModifiedBy") ? new QUserEntity(forProperty("lastModifiedBy"), inits.get("lastModifiedBy")) : null;
        this.parent = inits.isInitialized("parent") ? new QDictEntity(forProperty("parent"), inits.get("parent")) : null;
    }

}

