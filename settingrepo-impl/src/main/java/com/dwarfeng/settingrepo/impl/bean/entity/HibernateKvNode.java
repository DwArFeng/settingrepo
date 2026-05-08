package com.dwarfeng.settingrepo.impl.bean.entity;

import com.dwarfeng.datamark.sdk.jpa.DatamarkEntityListener;
import com.dwarfeng.datamark.sdk.jpa.DatamarkField;
import com.dwarfeng.settingrepo.sdk.util.Constraints;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateStringIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Entity
@IdClass(HibernateStringIdKey.class)
@Table(name = "tbl_kv_node")
@EntityListeners(DatamarkEntityListener.class)
public class HibernateKvNode implements Bean {

    private static final long serialVersionUID = 6575589791162797130L;

    // region 主键

    @Id
    @Column(name = "id", length = Constraints.LENGTH_SETTING_NODE_ID, nullable = false, unique = true)
    private String stringId;

    // endregion

    // region 主属性字段

    @Column(name = "count")
    private int count;

    // endregion

    // region 一对多

    @OneToMany(cascade = CascadeType.MERGE, targetEntity = HibernateKvNodeItem.class, mappedBy = "node")
    private Set<HibernateKvNodeItem> items = new HashSet<>();

    // endregion

    // region 审计

    @DatamarkField(handlerName = "settingNodeDatamarkHandler")
    @Column(
            name = "created_datamark",
            length = com.dwarfeng.datamark.sdk.util.Constraints.LENGTH_DATAMARK_VALUE,
            updatable = false
    )
    private String createdDatamark;

    @DatamarkField(handlerName = "settingNodeDatamarkHandler")
    @Column(
            name = "modified_datamark",
            length = com.dwarfeng.datamark.sdk.util.Constraints.LENGTH_DATAMARK_VALUE
    )
    private String modifiedDatamark;

    // endregion

    public HibernateKvNode() {
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Set<HibernateKvNodeItem> getItems() {
        return items;
    }

    public void setItems(Set<HibernateKvNodeItem> items) {
        this.items = items;
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
                "stringId = " + stringId + ", " +
                "count = " + count + ", " +
                "createdDatamark = " + createdDatamark + ", " +
                "modifiedDatamark = " + modifiedDatamark + ")";
    }
}
