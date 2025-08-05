package com.dwarfeng.settingrepo.stack.service;

import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;

/**
 * 支持 QoS 服务。
 *
 * @author DwArFeng
 * @since 2.4.0
 */
public interface SupportQosService extends Service {

    /**
     * 重置格式化器。
     *
     * @throws ServiceException 服务异常。
     */
    void resetFormatter() throws ServiceException;
}
