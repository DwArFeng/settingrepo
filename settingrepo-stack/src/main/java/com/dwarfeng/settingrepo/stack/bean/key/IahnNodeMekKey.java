package com.dwarfeng.settingrepo.stack.bean.key;

import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

/**
 * 国际化节点 Mek 键。
 *
 * @author mooyuan
 * @author DwArFeng
 * @since 2.1.0
 */
public class IahnNodeMekKey implements Key {

    private static final long serialVersionUID = -8954168874893920126L;

    private String nodeStringId;
    private String mekId;

    public IahnNodeMekKey() {
    }

    public IahnNodeMekKey(String nodeStringId, String mekId) {
        this.nodeStringId = nodeStringId;
        this.mekId = mekId;
    }

    public String getNodeStringId() {
        return nodeStringId;
    }

    public void setNodeStringId(String nodeStringId) {
        this.nodeStringId = nodeStringId;
    }

    public String getMekId() {
        return mekId;
    }

    public void setMekId(String mekId) {
        this.mekId = mekId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        IahnNodeMekKey that = (IahnNodeMekKey) o;
        return Objects.equals(nodeStringId, that.nodeStringId) && Objects.equals(mekId, that.mekId);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(nodeStringId);
        result = 31 * result + Objects.hashCode(mekId);
        return result;
    }

    @Override
    public String toString() {
        return "IahnNodeMekKey{" +
                "nodeStringId='" + nodeStringId + '\'' +
                ", mekId='" + mekId + '\'' +
                '}';
    }
}
