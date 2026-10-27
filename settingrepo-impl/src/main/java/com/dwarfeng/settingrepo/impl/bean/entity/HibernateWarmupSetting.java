package com.dwarfeng.settingrepo.impl.bean.entity;

import com.alibaba.fastjson.JSONArray;
import com.dwarfeng.datamark.sdk.jpa.DatamarkEntityListener;
import com.dwarfeng.datamark.sdk.jpa.DatamarkField;
import com.dwarfeng.settingrepo.sdk.util.Constraints;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.persistence.*;
import java.util.*;

/**
 * 预热设置的 Hibernate 实体。
 *
 * @author DwArFeng
 * @since 3.1.0
 */
@Entity
@IdClass(HibernateLongIdKey.class)
@Table(name = "tbl_warmup_setting")
@EntityListeners(DatamarkEntityListener.class)
public class HibernateWarmupSetting implements Bean {

    private static final long serialVersionUID = 5385333500529357761L;

    // region 主键

    @Id
    @Column(name = "id", nullable = false, unique = true)
    private Long longId;

    // endregion

    // region 主属性字段

    @Column(name = "type", nullable = false)
    private int type;

    @Column(name = "target_string_alpha", columnDefinition = "TEXT")
    private String targetStringAlpha;

    @Column(name = "target_string_bravo", columnDefinition = "TEXT")
    private String targetStringBravo;

    @Column(name = "target_string_charlie", columnDefinition = "TEXT")
    private String targetStringCharlie;

    @Column(name = "target_string_delta", columnDefinition = "TEXT")
    private String targetStringDelta;

    @Column(name = "target_string_echo", columnDefinition = "TEXT")
    private String targetStringEcho;

    @Column(name = "target_string_array_alpha", columnDefinition = "TEXT")
    @Convert(converter = StringArrayStringConverter.class)
    private String[] targetStringArrayAlpha;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @Column(name = "remark", length = Constraints.LENGTH_REMARK)
    private String remark;

    // endregion

    // region 一对多

    @OneToMany(
            cascade = CascadeType.MERGE, targetEntity = HibernateWarmupDriverInfo.class,
            mappedBy = "warmupSetting"
    )
    private Set<HibernateWarmupDriverInfo> driverInfos = new HashSet<>();

    // endregion

    // region 审计

    @DatamarkField(handlerName = "warmupSettingDatamarkHandler")
    @Column(
            name = "created_datamark",
            length = com.dwarfeng.datamark.sdk.util.Constraints.LENGTH_DATAMARK_VALUE,
            updatable = false
    )
    private String createdDatamark;

    @DatamarkField(handlerName = "warmupSettingDatamarkHandler")
    @Column(
            name = "modified_datamark",
            length = com.dwarfeng.datamark.sdk.util.Constraints.LENGTH_DATAMARK_VALUE
    )
    private String modifiedDatamark;

    // endregion

    public HibernateWarmupSetting() {
    }

    // region 映射用属性区

    public HibernateLongIdKey getKey() {
        return Optional.ofNullable(longId).map(HibernateLongIdKey::new).orElse(null);
    }

    public void setKey(HibernateLongIdKey key) {
        this.longId = Optional.ofNullable(key).map(HibernateLongIdKey::getLongId).orElse(null);
    }

    // endregion

    // region 常规属性区

    public Long getLongId() {
        return longId;
    }

    public void setLongId(Long longId) {
        this.longId = longId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTargetStringAlpha() {
        return targetStringAlpha;
    }

    public void setTargetStringAlpha(String targetStringAlpha) {
        this.targetStringAlpha = targetStringAlpha;
    }

    public String getTargetStringBravo() {
        return targetStringBravo;
    }

    public void setTargetStringBravo(String targetStringBravo) {
        this.targetStringBravo = targetStringBravo;
    }

    public String getTargetStringCharlie() {
        return targetStringCharlie;
    }

    public void setTargetStringCharlie(String targetStringCharlie) {
        this.targetStringCharlie = targetStringCharlie;
    }

    public String getTargetStringDelta() {
        return targetStringDelta;
    }

    public void setTargetStringDelta(String targetStringDelta) {
        this.targetStringDelta = targetStringDelta;
    }

    public String getTargetStringEcho() {
        return targetStringEcho;
    }

    public void setTargetStringEcho(String targetStringEcho) {
        this.targetStringEcho = targetStringEcho;
    }

    public String[] getTargetStringArrayAlpha() {
        return targetStringArrayAlpha;
    }

    public void setTargetStringArrayAlpha(String[] targetStringArrayAlpha) {
        this.targetStringArrayAlpha = targetStringArrayAlpha;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Set<HibernateWarmupDriverInfo> getDriverInfos() {
        return driverInfos;
    }

    public void setDriverInfos(Set<HibernateWarmupDriverInfo> driverInfos) {
        this.driverInfos = driverInfos;
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
                "type = " + type + ", " +
                "targetStringAlpha = " + targetStringAlpha + ", " +
                "targetStringBravo = " + targetStringBravo + ", " +
                "targetStringCharlie = " + targetStringCharlie + ", " +
                "targetStringDelta = " + targetStringDelta + ", " +
                "targetStringEcho = " + targetStringEcho + ", " +
                "targetStringArrayAlpha = " + Arrays.toString(targetStringArrayAlpha) + ", " +
                "enabled = " + enabled + ", " +
                "remark = " + remark + ", " +
                "createdDatamark = " + createdDatamark + ", " +
                "modifiedDatamark = " + modifiedDatamark + ")";
    }

    @Converter
    public static class StringArrayStringConverter implements AttributeConverter<String[], String> {

        @Override
        public String convertToDatabaseColumn(String[] attribute) {
            if (Objects.isNull(attribute)) {
                return null;
            }
            return JSONArray.toJSONString(attribute);
        }

        @Override
        public String[] convertToEntityAttribute(String dbData) {
            if (Objects.isNull(dbData)) {
                return null;
            }
            return JSONArray.parseArray(dbData, String.class).toArray(new String[0]);
        }
    }
}
