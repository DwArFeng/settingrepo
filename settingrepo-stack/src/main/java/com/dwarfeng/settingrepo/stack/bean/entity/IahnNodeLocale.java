package com.dwarfeng.settingrepo.stack.bean.entity;

import com.dwarfeng.settingrepo.stack.bean.key.IahnNodeLocaleKey;
import com.dwarfeng.subgrade.basic.stack.bean.entity.Entity;

import java.io.Serial;

/**
 * 国际化节点地区。
 *
 * @author mooyuan
 * @author DwArFeng
 * @since 2.1.0
 */
public class IahnNodeLocale implements Entity<IahnNodeLocaleKey> {

    @Serial
    private static final long serialVersionUID = 5752180661799323870L;

    private IahnNodeLocaleKey key;
    private String label;
    private String remark;

    public IahnNodeLocale() {
    }

    public IahnNodeLocale(IahnNodeLocaleKey key, String label, String remark) {
        this.key = key;
        this.label = label;
        this.remark = remark;
    }

    @Override
    public IahnNodeLocaleKey getKey() {
        return key;
    }

    @Override
    public void setKey(IahnNodeLocaleKey key) {
        this.key = key;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "IahnNodeLocale{" +
                "key=" + key +
                ", label='" + label + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
