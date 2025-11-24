package com.dwarfeng.settingrepo.stack.service;

import com.dwarfeng.settingrepo.stack.bean.dto.*;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;

/**
 * 导航节点 QOS 服务。
 *
 * @author DwArFeng
 * @since 2.4.2
 */
public interface NavigationNodeQosService extends Service {

    /**
     * 查看导航节点的大小。
     *
     * @param info 导航节点大小信息。
     * @return 导航节点大小结果。
     * @throws ServiceException 服务异常。
     */
    NavigationNodeSizeResult size(NavigationNodeSizeInfo info) throws ServiceException;

    /**
     * 查看导航节点。
     *
     * <p>
     * 返回树形嵌套结构，同级节点按照 index 升序返回。
     *
     * @param info 导航节点查看信息。
     * @return 导航节点查看结果。
     * @throws ServiceException 服务异常。
     */
    NavigationNodeInspectResult inspect(NavigationNodeInspectInfo info) throws ServiceException;

    /**
     * 插入导航节点条目。
     *
     * <p>
     * 插入导航节点条目时，会对导航节点做插入操作。<br>
     * 如果插入信息中的索引与兄弟节点（相同 parentKey）的索引冲突，会抛出异常。<br>
     *
     * @param info 导航节点条目插入信息。
     * @return 导航节点条目插入结果。
     * @throws ServiceException 服务异常。
     */
    NavigationNodeItemInsertResult insertItem(NavigationNodeItemInsertInfo info) throws ServiceException;

    /**
     * 更新导航节点条目。
     *
     * <p>
     * 更新导航节点条目时，会对导航节点做更新操作。<br>
     * 如果更新信息中的索引与兄弟节点（相同 parentKey）的索引冲突，会抛出异常。<br>
     *
     * @param info 导航节点条目更新信息。
     * @throws ServiceException 服务异常。
     */
    void updateItem(NavigationNodeItemUpdateInfo info) throws ServiceException;

    /**
     * 移除导航节点条目。
     *
     * <p>
     * 级联删除逻辑由 MaintainService 处理，本方法无需关心。
     *
     * @param info 导航节点条目移除信息。
     * @throws ServiceException 服务异常。
     */
    void removeItem(NavigationNodeItemRemoveInfo info) throws ServiceException;

    /**
     * 格式化导航节点索引。
     *
     * <p>
     * 格式化导航节点索引时，会对指定父节点下的所有直接子节点（一级）进行索引重新分配。<br>
     * 索引格式化的起始值和步长从配置文件 <code>navigation.properties</code> 中读取。<br>
     * 格式化后的索引将保持原有顺序，但索引值会按照配置的起始值和步长重新分配。<br>
     *
     * @param info 导航节点索引格式化信息。
     * @throws ServiceException 服务异常。
     */
    void formatIndex(NavigationNodeFormatIndexInfo info) throws ServiceException;
}

