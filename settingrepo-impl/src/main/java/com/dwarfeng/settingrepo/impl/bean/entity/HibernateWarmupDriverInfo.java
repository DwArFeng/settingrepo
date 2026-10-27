package com.dwarfeng.settingrepo.impl.bean.entity;

import com.dwarfeng.datamark.sdk.jpa.DatamarkEntityListener;
import com.dwarfeng.datamark.sdk.jpa.DatamarkField;
import com.dwarfeng.settingrepo.sdk.util.Constraints;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.persistence.*;
import java.util.Optional;

/**
 * 预热驱动信息的 Hibernate 实体。
 *
 * @author DwArFeng
 * @since 3.1.0
 */
@Entity
@IdClass(HibernateLongIdKey.class)
@Table(name = "tbl_warmup_driver_info")
@EntityListeners(DatamarkEntityListener.class)
public class HibernateWarmupDriverInfo implements Bean {

    private static final long serialVersionUID = 546849347652586466L;

    // region 主键

    @Id
    @Column(name = "id", nullable = false, unique = true)
    private Long longId;

    // endregion

    // region 外键

    @Column(name = "warmup_setting_id")
    private Long warmupSettingLongId;

    // endregion

    // region 主属性字段

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @Column(name = "type", length = Constraints.LENGTH_TYPE, nullable = false)
    private String type;

    @Column(name = "param", columnDefinition = "TEXT")
    private String param;

    @Column(name = "remark", length = Constraints.LENGTH_REMARK)
    private String remark;

    // endregion

    // region 多对一

    @ManyToOne(targetEntity = HibernateWarmupSetting.class)
    @JoinColumns({ //
            @JoinColumn(name = "warmup_setting_id", referencedColumnName = "id", insertable = false, updatable = false), //
    })
    private HibernateWarmupSetting warmupSetting;

    // endregion

    // region 审计

    @DatamarkField(handlerName = "warmupDriverInfoDatamarkHandler")
    @Column(
            name = "created_datamark",
            length = com.dwarfeng.datamark.sdk.util.Constraints.LENGTH_DATAMARK_VALUE,
            updatable = false
    )
    private String createdDatamark;

    @DatamarkField(handlerName = "warmupDriverInfoDatamarkHandler")
    @Column(
            name = "modified_datamark",
            length = com.dwarfeng.datamark.sdk.util.Constraints.LENGTH_DATAMARK_VALUE
    )
    private String modifiedDatamark;

    // endregion

    public HibernateWarmupDriverInfo() {
    }

    // region 映射用属性区

    public HibernateLongIdKey getKey() {
        return Optional.ofNullable(longId).map(HibernateLongIdKey::new).orElse(null);
    }

    public void setKey(HibernateLongIdKey key) {
        this.longId = Optional.ofNullable(key).map(HibernateLongIdKey::getLongId).orElse(null);
    }

    public HibernateLongIdKey getWarmupSettingKey() {
        return Optional.ofNullable(warmupSettingLongId).map(HibernateLongIdKey::new).orElse(null);
    }

    public void setWarmupSettingKey(HibernateLongIdKey key) {
        this.warmupSettingLongId = Optional.ofNullable(key).map(HibernateLongIdKey::getLongId).orElse(null);
    }

    // endregion

    // region 常规属性区

    public Long getLongId() {
        return longId;
    }

    public void setLongId(Long longId) {
        this.longId = longId;
    }

    public Long getWarmupSettingLongId() {
        return warmupSettingLongId;
    }

    public void setWarmupSettingLongId(Long warmupSettingLongId) {
        this.warmupSettingLongId = warmupSettingLongId;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public HibernateWarmupSetting getWarmupSetting() {
        return warmupSetting;
    }

    public void setWarmupSetting(HibernateWarmupSetting warmupSetting) {
        this.warmupSetting = warmupSetting;
    }

    public String getCreatedDatamark() {
        return createdDatamark;
    }

    public void setCreatedDatamark(String createdDatamark) {
        this.createdDatamark = createdDatamark;
    }

    public String getModifiedDatamark() {
        return modifiedDatamark;
    }

    public void setModifiedDatamark(String modifiedDatamark) {
        this.modifiedDatamark = modifiedDatamark;
    }

    // endregion

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "longId = " + longId + ", " +
                "warmupSettingLongId = " + warmupSettingLongId + ", " +
                "enabled = " + enabled + ", " +
                "type = " + type + ", " +
                "param = " + param + ", " +
                "remark = " + remark + ", " +
                "warmupSetting = " + warmupSetting + ", " +
                "createdDatamark = " + createdDatamark + ", " +
                "modifiedDatamark = " + modifiedDatamark + ")";
    }
}
