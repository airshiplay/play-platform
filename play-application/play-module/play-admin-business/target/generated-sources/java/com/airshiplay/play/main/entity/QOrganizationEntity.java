package com.airshiplay.play.main.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrganizationEntity is a Querydsl query type for OrganizationEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QOrganizationEntity extends EntityPathBase<OrganizationEntity> {

    private static final long serialVersionUID = -621572989L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrganizationEntity organizationEntity = new QOrganizationEntity("organizationEntity");

    public final com.airshiplay.play.repo.jpa.QHierarchicalEntity _super = new com.airshiplay.play.repo.jpa.QHierarchicalEntity(this);

    public final StringPath address = createString("address");

    public final ListPath<OrganizationEntity, QOrganizationEntity> children = this.<OrganizationEntity, QOrganizationEntity>createList("children", OrganizationEntity.class, QOrganizationEntity.class, PathInits.DIRECT2);

    public final StringPath code = createString("code");

    public final QUserEntity createdBy;

    //inherited
    public final DateTimePath<java.util.Date> createdDate = _super.createdDate;

    public final StringPath email = createString("email");

    public final StringPath fax = createString("fax");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QUserEntity lastModifiedBy;

    //inherited
    public final DateTimePath<java.util.Date> lastModifiedDate = _super.lastModifiedDate;

    public final StringPath master = createString("master");

    public final StringPath name = createString("name");

    public final QOrganizationEntity parent;

    public final StringPath phone = createString("phone");

    public final QUserEntity primaryLeader;

    public final QUserEntity secondaryLeader;

    //inherited
    public final NumberPath<Integer> sortNo = _super.sortNo;

    //inherited
    public final StringPath treePath = _super.treePath;

    public final EnumPath<OrganizationEntity.OrgType> type = createEnum("type", OrganizationEntity.OrgType.class);

    public final StringPath zipCode = createString("zipCode");

    public QOrganizationEntity(String variable) {
        this(OrganizationEntity.class, forVariable(variable), INITS);
    }

    public QOrganizationEntity(Path<? extends OrganizationEntity> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QOrganizationEntity(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QOrganizationEntity(PathMetadata metadata, PathInits inits) {
        this(OrganizationEntity.class, metadata, inits);
    }

    public QOrganizationEntity(Class<? extends OrganizationEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.createdBy = inits.isInitialized("createdBy") ? new QUserEntity(forProperty("createdBy"), inits.get("createdBy")) : null;
        this.lastModifiedBy = inits.isInitialized("lastModifiedBy") ? new QUserEntity(forProperty("lastModifiedBy"), inits.get("lastModifiedBy")) : null;
        this.parent = inits.isInitialized("parent") ? new QOrganizationEntity(forProperty("parent"), inits.get("parent")) : null;
        this.primaryLeader = inits.isInitialized("primaryLeader") ? new QUserEntity(forProperty("primaryLeader"), inits.get("primaryLeader")) : null;
        this.secondaryLeader = inits.isInitialized("secondaryLeader") ? new QUserEntity(forProperty("secondaryLeader"), inits.get("secondaryLeader")) : null;
    }

}

