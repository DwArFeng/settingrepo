package com.dwarfeng.settingrepo.impl.handler.formatter;

/**
 * 抽象格式化器注册。
 *
 * @author DwArFeng
 * @see com.dwarfeng.settingrepo.sdk.handler.formatter.AbstractFormatterRegistry
 * @since 1.0.0
 * @deprecated 该对象已经被废弃，请使用 sdk 模块下的对应对象代替。
 */
@Deprecated
public abstract class AbstractFormatterRegistry extends
        com.dwarfeng.settingrepo.sdk.handler.formatter.AbstractFormatterRegistry {

    public AbstractFormatterRegistry() {
    }

    public AbstractFormatterRegistry(String formatterType) {
        super(formatterType);
    }
}
