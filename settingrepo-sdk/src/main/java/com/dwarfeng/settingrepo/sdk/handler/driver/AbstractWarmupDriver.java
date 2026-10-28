package com.dwarfeng.settingrepo.sdk.handler.driver;

import com.dwarfeng.settingrepo.stack.handler.WarmupDriver;

/**
 * 预热驱动器的抽象实现。
 *
 * <p>
 * 抽象实现集中保存驱动器上下文，具体策略只需要完成调度注册和取消逻辑。
 *
 * @author DwArFeng
 * @since 3.1.0
 */
public abstract class AbstractWarmupDriver implements WarmupDriver {

    protected Context context;

    @Override
    public void init(Context context) {
        this.context = context;
    }

    @Override
    public String toString() {
        return "AbstractWarmupDriver{" +
                "context=" + context +
                '}';
    }
}
