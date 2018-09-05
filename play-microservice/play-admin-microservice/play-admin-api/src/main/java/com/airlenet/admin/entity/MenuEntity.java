package com.airlenet.admin.entity;

import com.airlenet.data.jpa.HierarchicalEntity;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "sys_menu")
@Data
@Builder
public class MenuEntity extends HierarchicalEntity<UserEntity, Long, MenuEntity> {

    private static final long serialVersionUID = 2487374581329950331L;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(nullable = false, length = 50)
    private String text;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(nullable = false, length = 50, unique = true)
    private String code;

    @Column(length = 50)
    private String iconCls;

    @Column(length = 100)
    private String view;

    @Column(length = 500)
    private String config;

    @Enumerated(EnumType.STRING)
    private MenuType type;
    @OneToMany(mappedBy = "menu")
    private Set<PermissionEntity> permissions;

    public static enum MenuType{
        menu,button
    }

    public static class Builder  extends MenuEntity.MenuEntityBuilder{
        MenuEntity parent;
        public MenuEntityBuilder parent(MenuEntity parent) {
            this.parent =parent;
            return this;
        }

        @Override
        public MenuEntity build() {
            MenuEntity entity=  super.build();entity.setParent(parent);
            return entity;
        }
    }

    public static Builder builder(){
        return  new Builder();
    }
}
