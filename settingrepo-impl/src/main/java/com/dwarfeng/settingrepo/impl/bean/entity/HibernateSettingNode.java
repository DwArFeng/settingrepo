package com.dwarfeng.settingrepo.impl.bean.entity;

import com.alibaba.fastjson.JSONArray;
import com.dwarfeng.settingrepo.sdk.util.Constraints;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateStringIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Entity
@IdClass(HibernateStringIdKey.class)
@Table(name = "tbl_setting_node")
public class HibernateSettingNode implements Bean {

    private static final long serialVersionUID = -7340610859830007022L;

    // -----------------------------------------------------------主键-----------------------------------------------------------
    @Id
    @Column(name = "id", length = Constraints.LENGTH_SETTING_NODE_ID, nullable = false, unique = true)
    private String stringId;

    // -----------------------------------------------------------主属性字段-----------------------------------------------------------
    @Column(name = "type")
    private int type;

    @Column(name = "last_modified_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Column(name = "remark", length = Constraints.LENGTH_REMARK)
    private String remark;

    @Column(name = "reachable")
    private boolean reachable;

    @Column(name = "category", length = Constraints.LENGTH_SETTING_CATEGORY_ID)
    private String category;

    @Column(name = "args", columnDefinition = "TEXT")
    @Convert(converter = StringArrayStringConverter.class)
    private String[] args;

    public HibernateSettingNode() {
    }

    // -----------------------------------------------------------映射用属性区-----------------------------------------------------------
    public HibernateStringIdKey getKey() {
        return Optional.ofNullable(stringId).map(HibernateStringIdKey::new).orElse(null);
    }

    public void setKey(HibernateStringIdKey idKey) {
        this.stringId = Optional.ofNullable(idKey).map(HibernateStringIdKey::getStringId).orElse(null);
    }

    // -----------------------------------------------------------常规属性区-----------------------------------------------------------
    public String getStringId() {
        return stringId;
    }

    public void setStringId(String stringId) {
        this.stringId = stringId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public boolean isReachable() {
        return reachable;
    }

    public void setReachable(boolean reachable) {
        this.reachable = reachable;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String[] getArgs() {
        return args;
    }

    public void setArgs(String[] args) {
        this.args = args;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "stringId = " + stringId + ", " +
                "type = " + type + ", " +
                "lastModifiedDate = " + lastModifiedDate + ", " +
                "remark = " + remark + ", " +
                "reachable = " + reachable + ", " +
                "category = " + category + ", " +
                "args = " + Arrays.toString(args) + ")";
    }

    /**
     * 字符串数组与字符串的转换器。
     *
     * @author DwArFeng
     * @since 2.0.0
     */
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
