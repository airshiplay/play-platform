package com.airlenet.admin.entity;

import com.airlenet.data.jpa.DataEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "sys_setting")
public class SettingEntity extends DataEntity<UserEntity, Long> {

    private static final long serialVersionUID = -7311556460000491508L;

    @NotNull
    @Size(min = 2, max = 50)
    @Column(length = 200)
    private String code;

    @NotNull
    @Size(min = 2, max = 45)
    @Column(name = "`key`",length = 45,unique = true)
    private String key;

    /**
     * logo
     */
    @Column(length = 200)
    private String value;

    /**
     * adminLoginBanner
     */
    @Column()
    private boolean serialized;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isSerialized() {
        return serialized;
    }

    public void setSerialized(boolean serialized) {
        this.serialized = serialized;
    }
}
