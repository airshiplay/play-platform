package com.airshiplay.play.obd.entity;

import com.airshiplay.play.main.entity.AdminUserEntity;
import com.airshiplay.play.repo.jpa.HierarchicalEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 预置车型
 *
 * @author lig
 */
@Entity
@Table(name = "obd_cfg_car")
@Getter
@Setter
public class ObdCfgCarEntity extends HierarchicalEntity<AdminUserEntity, Long, ObdCfgCarEntity> {
    /**
     * 编码
     */
    @NotNull
    @Size(min = 1, max = 100)
    @Column(nullable = false, length = 100)
    private String code;
    /**
     * 名称
     */
    @NotNull
    @Size(min = 1, max = 50)
    @Column(nullable = false, length = 100)
    private String name;
    /**
     * OEM
     */
    @Size(min = 1, max = 50)
    @Column(length = 100)
    private String oemName;
    /**
     * 类型
     */
    @NotNull
    @Column(nullable = false, length = 50)
    @Enumerated(value = EnumType.STRING)
    private CfgCarType type;


    public enum CfgCarType {
        /**
         * 车品牌
         */brand, /**
         * 车系列
         */series, /**
         * 型号
         */type, /**
         * 具体型号
         */detail
    }


}
