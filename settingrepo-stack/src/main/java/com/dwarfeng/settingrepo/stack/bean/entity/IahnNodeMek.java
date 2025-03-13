package com.dwarfeng.settingrepo.stack.bean.entity;

import com.dwarfeng.settingrepo.stack.bean.key.IahnNodeMekKey;
import com.dwarfeng.subgrade.stack.bean.entity.Entity;

/**
 * 国际化节点 Mek。
 *
 * @author mooyuan
 * @author DwArFeng
 * @since 2.1.0
 */
public class IahnNodeMek implements Entity<IahnNodeMekKey> {

    private static final long serialVersionUID = -235604661513146828L;

    private IahnNodeMekKey key;
    private String label;
    private String defaultMessage;
    private String remark;

    public IahnNodeMek() {
    }

    public IahnNodeMek(IahnNodeMekKey key, String label, String defaultMessage, String remark) {
        this.key = key;
        this.label = label;
        this.defaultMessage = defaultMessage;
        this.remark = remark;
    }

    @Override
    public IahnNodeMekKey getKey() {
        return key;
    }

    @Override
    public void setKey(IahnNodeMekKey key) {
        this.key = key;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }

    public void setDefaultMessage(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "IahnNodeMek{" +
                "key=" + key +
                ", label='" + label + '\'' +
                ", defaultMessage='" + defaultMessage + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
