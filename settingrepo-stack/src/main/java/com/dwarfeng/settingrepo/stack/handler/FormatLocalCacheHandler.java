package com.dwarfeng.settingrepo.stack.handler;

import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

/**
 * 格式化本地缓存处理器。
 *
 * <p>处理器在本地保存数据，缓存中的数据可以保证与数据源保持同步。</p>
 * <p>数据存放在本地，必要时才与数据访问层通信，这有助于程序效率的提升。</p>
 * <p>该处理器线程安全。</p>
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface FormatLocalCacheHandler extends Handler {

    /**
     * 获取指定的设置类别对应的格式化器。
     *
     * @param settingCategoryKey 指定的设置类别。
     * @return 指定的设置类别对应的格式化器。
     * @throws HandlerException 处理器异常。
     */
    Formatter getFormatter(StringIdKey settingCategoryKey) throws HandlerException;

    /**
     * 清除本地缓存。
     *
     * @throws HandlerException 处理器异常。
     */
    void clear() throws HandlerException;
}
