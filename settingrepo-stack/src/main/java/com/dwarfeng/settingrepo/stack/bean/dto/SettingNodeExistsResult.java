package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.basic.stack.bean.dto.Dto;

import java.io.Serial;

/**
 * 设置节点存在结果。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class SettingNodeExistsResult implements Dto {

    @Serial
    private static final long serialVersionUID = 7903561730316012047L;

    private boolean exists;

    public SettingNodeExistsResult() {
    }

    public SettingNodeExistsResult(boolean exists) {
        this.exists = exists;
    }

    public boolean isExists() {
        return exists;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }

    @Override
    public String toString() {
        return "SettingNodeExistsResult{" +
                "exists=" + exists +
                '}';
    }
}
