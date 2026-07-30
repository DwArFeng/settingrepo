package com.dwarfeng.settingrepo.stack.bean.entity;

import com.dwarfeng.settingrepo.stack.bean.key.IahnNodeMessageKey;
import com.dwarfeng.subgrade.basic.stack.bean.entity.Entity;

import java.io.Serial;

/**
 * 国际化节点消息。
 *
 * @author mooyuan
 * @author DwArFeng
 * @since 2.1.0
 */
public class IahnNodeMessage implements Entity<IahnNodeMessageKey> {

    @Serial
    private static final long serialVersionUID = -717686620794562443L;

    private IahnNodeMessageKey key;
    private String message;

    public IahnNodeMessage() {
    }

    public IahnNodeMessage(IahnNodeMessageKey key, String message) {
        this.key = key;
        this.message = message;
    }

    @Override
    public IahnNodeMessageKey getKey() {
        return key;
    }

    @Override
    public void setKey(IahnNodeMessageKey key) {
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
        return "IahnNodeMessage{" +
                "key=" + key +
                ", message='" + message + '\'' +
                '}';
    }
}
