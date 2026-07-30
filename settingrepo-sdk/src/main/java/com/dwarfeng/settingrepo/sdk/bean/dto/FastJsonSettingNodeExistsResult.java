package com.dwarfeng.settingrepo.sdk.bean.dto;

import com.alibaba.fastjson2.annotation.JSONField;
import com.dwarfeng.settingrepo.stack.bean.dto.SettingNodeExistsResult;
import com.dwarfeng.subgrade.basic.stack.bean.dto.Dto;

import java.io.Serial;
import java.util.Objects;

/**
 * FastJson 设置节点存在结果。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class FastJsonSettingNodeExistsResult implements Dto {

    @Serial
    private static final long serialVersionUID = -6720200739353048447L;

    public static FastJsonSettingNodeExistsResult of(SettingNodeExistsResult settingNodeExistsResult) {
        if (Objects.isNull(settingNodeExistsResult)) {
            return null;
        } else {
            return new FastJsonSettingNodeExistsResult(
                    settingNodeExistsResult.isExists()
            );
        }
    }

    @JSONField(name = "exists", ordinal = 1)
    private boolean exists;

    public FastJsonSettingNodeExistsResult() {
    }

    public FastJsonSettingNodeExistsResult(boolean exists) {
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
        return "FastJsonSettingNodeExistsResult{" +
                "exists=" + exists +
                '}';
    }
}
