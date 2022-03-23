package com.dwarfeng.settingrepo.stack.service;

import com.dwarfeng.settingrepo.stack.handler.Formatter;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;

/**
 * 判断QoS服务。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface FormatQosService extends Service {

    /**
     * 获取指定的设置类别对应的格式化器。
     *
     * @param settingCategoryKey 指定的设置类别。
     * @return 指定的设置类别对应的格式化器。
     * @throws ServiceException 服务异常。
     */
    Formatter getFormatter(StringIdKey settingCategoryKey) throws ServiceException;

    /**
     * 清除本地缓存。
     *
     * @throws ServiceException 服务异常。
     */
    void clearLocalCache() throws ServiceException;
}
