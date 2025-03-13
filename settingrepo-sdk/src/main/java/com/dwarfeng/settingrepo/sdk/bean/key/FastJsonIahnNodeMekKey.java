package com.dwarfeng.settingrepo.sdk.bean.key;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.settingrepo.stack.bean.key.IahnNodeMekKey;
import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

/**
 * FastJson 国际化节点 Mek 键。
 *
 * @author mooyuan
 * @author DwArFeng
 * @since 2.1.0
 */
public class FastJsonIahnNodeMekKey implements Key {

    private static final long serialVersionUID = -160673159086253323L;

    public static FastJsonIahnNodeMekKey of(IahnNodeMekKey iahnNodeMekKey) {
        if (Objects.isNull(iahnNodeMekKey)) {
            return null;
        } else {
            return new FastJsonIahnNodeMekKey(
                    iahnNodeMekKey.getNodeStringId(),
                    iahnNodeMekKey.getMekId()
            );
        }
    }

    @JSONField(name = "node_string_id", ordinal = 1)
    private String nodeStringId;

    @JSONField(name = "mek_id", ordinal = 2)
    private String mekId;

    public FastJsonIahnNodeMekKey() {
    }

    public FastJsonIahnNodeMekKey(String nodeStringId, String mekId) {
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

        FastJsonIahnNodeMekKey that = (FastJsonIahnNodeMekKey) o;
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
        return "FastJsonIahnNodeMekKey{" +
                "nodeStringId='" + nodeStringId + '\'' +
                ", mekId='" + mekId + '\'' +
                '}';
    }
}
