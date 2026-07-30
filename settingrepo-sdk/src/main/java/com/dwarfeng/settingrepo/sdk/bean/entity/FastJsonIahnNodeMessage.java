package com.dwarfeng.settingrepo.sdk.bean.entity;

import com.alibaba.fastjson2.annotation.JSONField;
import com.dwarfeng.settingrepo.sdk.bean.key.FastJsonIahnNodeMessageKey;
import com.dwarfeng.settingrepo.stack.bean.entity.IahnNodeMessage;
import com.dwarfeng.subgrade.basic.stack.bean.Bean;

import java.io.Serial;
import java.util.Objects;

/**
 * FastJson 国际化节点消息。
 *
 * @author mooyuan
 * @author DwArFeng
 * @since 2.1.0
 */
public class FastJsonIahnNodeMessage implements Bean {

    @Serial
    private static final long serialVersionUID = -5469302910355810113L;

    public static FastJsonIahnNodeMessage of(IahnNodeMessage iahnNodeMessage) {
        if (Objects.isNull(iahnNodeMessage)) {
            return null;
        } else {
            return new FastJsonIahnNodeMessage(
                    FastJsonIahnNodeMessageKey.of(iahnNodeMessage.getKey()),
                    iahnNodeMessage.getMessage()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonIahnNodeMessageKey key;

    @JSONField(name = "message", ordinal = 2)
    private String message;

    public FastJsonIahnNodeMessage() {
    }

    public FastJsonIahnNodeMessage(FastJsonIahnNodeMessageKey key, String message) {
        this.key = key;
        this.message = message;
    }

    public FastJsonIahnNodeMessageKey getKey() {
        return key;
    }

    public void setKey(FastJsonIahnNodeMessageKey key) {
        this.key = key;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "FastJsonIahnNodeMessage{" +
                "key=" + key +
                ", message='" + message + '\'' +
                '}';
    }
}
