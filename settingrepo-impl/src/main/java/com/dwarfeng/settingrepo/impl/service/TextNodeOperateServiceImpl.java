package com.dwarfeng.settingrepo.impl.service;

import com.dwarfeng.settingrepo.stack.bean.dto.TextNodeInspectInfo;
import com.dwarfeng.settingrepo.stack.bean.dto.TextNodeInspectResult;
import com.dwarfeng.settingrepo.stack.bean.dto.TextNodePutInfo;
import com.dwarfeng.settingrepo.stack.handler.TextNodeOperateHandler;
import com.dwarfeng.settingrepo.stack.service.TextNodeOperateService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.stereotype.Service;

@Service
public class TextNodeOperateServiceImpl implements TextNodeOperateService {

    private final TextNodeOperateHandler textNodeOperateHandler;

    private final ServiceExceptionMapper sem;

    public TextNodeOperateServiceImpl(TextNodeOperateHandler textNodeOperateHandler, ServiceExceptionMapper sem) {
        this.textNodeOperateHandler = textNodeOperateHandler;
        this.sem = sem;
    }

    @Override
    public TextNodeInspectResult inspect(TextNodeInspectInfo info) throws ServiceException {
        try {
            return textNodeOperateHandler.inspect(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("查看指定的文本节点时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void put(TextNodePutInfo info) throws ServiceException {
        try {
            textNodeOperateHandler.put(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("向指定的文本节点中放入指定的信息时发生异常", LogLevel.WARN, e, sem);
        }
    }
}
