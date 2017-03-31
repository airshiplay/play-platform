package com.airshiplay.play.obd.entity;

import com.airshiplay.play.main.entity.AdminUserEntity;
import com.airshiplay.play.repo.jpa.DataEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 4s店服务
 * Created by lig on 17/1/23.
 */
@Entity
@Table(name = "obd_store4s_service")
public class Store4SServiceEntity extends DataEntity<AdminUserEntity, Long> {

    @ManyToOne
    @JoinColumn(name = "store4s_id")
    private Store4SEntity store4S;
}
