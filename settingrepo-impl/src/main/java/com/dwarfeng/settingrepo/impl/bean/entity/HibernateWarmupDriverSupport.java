package com.dwarfeng.settingrepo.impl.bean.entity;

import com.dwarfeng.settingrepo.sdk.util.Constraints;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateStringIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.persistence.*;
import java.util.Optional;

/**
 * 预热驱动器支持的 Hibernate 实体。
 *
 * <p>
 * 持久化内容仅包含驱动器类型的展示信息，不包含具体预热设置或调度参数。
 * 因此该实体可以与 {@code WarmupDriverInfo} 分离维护，避免重建支持信息时影响实际驱动配置。
 *
 * @author DwArFeng
 * @since 3.1.0
 */
@Entity
@IdClass(HibernateStringIdKey.class)
@Table(name = "tbl_warmup_driver_support")
public class HibernateWarmupDriverSupport implements Bean {

    private static final long serialVersionUID = 8808706053341995180L;

    // region 主键

    @Id
    @Column(name = "id", length = Constraints.LENGTH_TYPE, nullable = false, unique = true)
    private String stringId;

    // endregion

    // region 主属性字段

    @Column(name = "label", nullable = false)
    private String label;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "example_param", columnDefinition = "TEXT")
    private String exampleParam;

    // endregion

    public HibernateWarmupDriverSupport() {
    }

    // region 映射用属性区

    public HibernateStringIdKey getKey() {
        return Optional.ofNullable(stringId).map(HibernateStringIdKey::new).orElse(null);
    }

    public void setKey(HibernateStringIdKey idKey) {
        this.stringId = Optional.ofNullable(idKey).map(HibernateStringIdKey::getStringId).orElse(null);
    }

    // endregion

    // region 常规属性区

    public String getStringId() {
        return stringId;
    }

    public void setStringId(String stringId) {
        this.stringId = stringId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExampleParam() {
        return exampleParam;
    }

    public void setExampleParam(String exampleParam) {
        this.exampleParam = exampleParam;
    }

    // endregion

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "stringId = " + stringId + ", " +
                "label = " + label + ", " +
                "description = " + description + ", " +
                "exampleParam = " + exampleParam + ")";
    }
}
