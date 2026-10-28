package com.dwarfeng.settingrepo.stack.handler;

import com.dwarfeng.settingrepo.stack.exception.WarmupDriverException;
import com.dwarfeng.subgrade.stack.handler.Handler;

/**
 * 预热驱动器查找处理器。
 *
 * @author DwArFeng
 * @since 3.1.0
 */
public interface WarmupDriverHandler extends Handler {

    /**
     * 寻找指定类型的驱动器。
     *
     * @param type 驱动器类型。
     * @return 符合类型的驱动器。
     * @throws WarmupDriverException 处理器异常。
     */
    WarmupDriver find(String type) throws WarmupDriverException;
}
