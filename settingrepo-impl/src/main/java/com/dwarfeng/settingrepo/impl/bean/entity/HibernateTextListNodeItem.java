package com.dwarfeng.settingrepo.impl.bean.entity;

import com.dwarfeng.settingrepo.sdk.util.Constraints;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateLongIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateStringIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.persistence.*;
import java.util.Optional;

/**
 * Hibernate 文本列表节点条目。
 *
 * @author lihc
 * @since 3.2.0
 */
@Entity
@IdClass(HibernateLongIdKey.class)
@Table(name = "tbl_text_list_node_item")
public class HibernateTextListNodeItem implements Bean {

    private static final long serialVersionUID = -4406793332443708368L;

    // region 主键

    @Id
    @Column(name = "id", nullable = false, unique = true)
    private Long longId;

    // endregion

    // region 外键

    @Column(name = "node_id", length = Constraints.LENGTH_SETTING_NODE_ID)
    private String nodeStringId;

    // endregion

    // region 主属性字段

    @Column(name = "column_index", nullable = false)
    private int index;

    @Column(name = "null_flag", nullable = false)
    private boolean nullFlag;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    // endregion

    // region 多对一

    @ManyToOne(targetEntity = HibernateTextListNode.class)
    @JoinColumns({ //
            @JoinColumn(name = "node_id", referencedColumnName = "id", insertable = false, updatable = false), //
    })
    private HibernateTextListNode node;

    // endregion

    public HibernateTextListNodeItem() {
    }

    // region 映射用属性区

    public HibernateLongIdKey getKey() {
        return Optional.ofNullable(longId).map(HibernateLongIdKey::new).orElse(null);
    }

    public void setKey(HibernateLongIdKey idKey) {
        this.longId = Optional.ofNullable(idKey).map(HibernateLongIdKey::getLongId).orElse(null);
    }

    public HibernateStringIdKey getNodeKey() {
        return Optional.ofNullable(nodeStringId).map(HibernateStringIdKey::new).orElse(null);
    }

    public void setNodeKey(HibernateStringIdKey idKey) {
        this.nodeStringId = Optional.ofNullable(idKey).map(HibernateStringIdKey::getStringId).orElse(null);
    }

    // endregion

    // region 常规属性区

    public Long getLongId() {
        return longId;
    }

    public void setLongId(Long longId) {
        this.longId = longId;
    }

    public String getNodeStringId() {
        return nodeStringId;
    }

    public void setNodeStringId(String nodeStringId) {
        this.nodeStringId = nodeStringId;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isNullFlag() {
        return nullFlag;
    }

    public void setNullFlag(boolean nullFlag) {
        this.nullFlag = nullFlag;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public HibernateTextListNode getNode() {
        return node;
    }

    public void setNode(HibernateTextListNode node) {
        this.node = node;
    }

    // endregion

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "longId = " + longId + ", " +
                "nodeStringId = " + nodeStringId + ", " +
                "index = " + index + ", " +
                "nullFlag = " + nullFlag + ", " +
                "content = " + content + ", " +
                "node = " + node + ")";
    }
}
