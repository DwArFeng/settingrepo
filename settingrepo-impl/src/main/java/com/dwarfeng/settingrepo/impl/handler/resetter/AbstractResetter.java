package com.dwarfeng.settingrepo.impl.handler.resetter;

import com.dwarfeng.settingrepo.stack.handler.Resetter;

/**
 * 重置器的抽象实现。
 *
 * @author DwArFeng
 * @since 1.1.1
 */
public abstract class AbstractResetter implements Resetter {

    protected Context context;

    @Override
    public void init(Context context) {
        this.context = context;
    }

    @Override
    public String toString() {
        return "AbstractResetter{" +
                "context=" + context +
                '}';
    }
}
