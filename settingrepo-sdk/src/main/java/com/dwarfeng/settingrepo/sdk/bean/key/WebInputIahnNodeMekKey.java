package com.dwarfeng.settingrepo.sdk.bean.key;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.settingrepo.sdk.util.Constraints;
import com.dwarfeng.settingrepo.stack.bean.key.IahnNodeMekKey;
import com.dwarfeng.subgrade.stack.bean.key.Key;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 国际化节点 Mek 键。
 *
 * @author mooyuan
 * @author DwArFeng
 * @since 2.1.0
 */
public class WebInputIahnNodeMekKey implements Key {

    private static final long serialVersionUID = -1985081364226956888L;

    public static IahnNodeMekKey toStackBean(WebInputIahnNodeMekKey webInputIahnNodeMekKey) {
        if (Objects.isNull(webInputIahnNodeMekKey)) {
            return null;
        } else {
            return new IahnNodeMekKey(
                    webInputIahnNodeMekKey.getNodeStringId(),
                    webInputIahnNodeMekKey.getMekId()
            );
        }
    }

    @JSONField(name = "node_string_id")
    @NotNull
    @NotEmpty
    @Length(max = Constraints.LENGTH_SETTING_NODE_ID)
    private String nodeStringId;

    @JSONField(name = "mek_id")
    @NotNull
    @NotEmpty
    @Length(max = Constraints.LENGTH_IAHN_NODE_MEK_ID)
    private String mekId;

    public WebInputIahnNodeMekKey() {
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

        WebInputIahnNodeMekKey that = (WebInputIahnNodeMekKey) o;
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
        return "WebInputIahnNodeMekKey{" +
                "nodeStringId='" + nodeStringId + '\'' +
                ", mekId='" + mekId + '\'' +
                '}';
    }
}
