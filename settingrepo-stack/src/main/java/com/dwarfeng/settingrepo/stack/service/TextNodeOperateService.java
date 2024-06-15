package com.dwarfeng.settingrepo.stack.service;

import com.dwarfeng.settingrepo.stack.bean.dto.TextNodeInspectInfo;
import com.dwarfeng.settingrepo.stack.bean.dto.TextNodeInspectResult;
import com.dwarfeng.settingrepo.stack.bean.dto.TextNodePutInfo;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;

/**
 * 文本节点操作服务。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public interface TextNodeOperateService extends Service {

    /**
     * 查看指定的文本节点。
     *
     * @param info 查看信息。
     * @return 指定的文本节点查看结果。
     * @throws ServiceException 服务异常。
     */
    TextNodeInspectResult inspect(TextNodeInspectInfo info) throws ServiceException;

    /**
     * 向指定的文本节点中放入指定的信息。
     *
     * @param info 放入信息。
     * @throws ServiceException 服务异常。
     */
    void put(TextNodePutInfo info) throws ServiceException;
}
