package com.dwarfeng.settingrepo.impl.service;

import com.dwarfeng.settingrepo.stack.bean.dto.*;
import com.dwarfeng.settingrepo.stack.handler.IahnNodeOperateHandler;
import com.dwarfeng.settingrepo.stack.service.IahnNodeOperateService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.stereotype.Service;

@Service
public class IahnNodeOperateServiceImpl implements IahnNodeOperateService {

    private final IahnNodeOperateHandler iahnNodeOperateHandler;

    private final ServiceExceptionMapper sem;

    public IahnNodeOperateServiceImpl(IahnNodeOperateHandler iahnNodeOperateHandler, ServiceExceptionMapper sem) {
        this.iahnNodeOperateHandler = iahnNodeOperateHandler;
        this.sem = sem;
    }

    @Override
    public IahnNodeMessageInspectResult inspectMessage(IahnNodeMessageInspectInfo info) throws ServiceException {
        try {
            return iahnNodeOperateHandler.inspectMessage(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("查询国际化消息时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public IahnNodeMessageInspectByLocaleResult batchInspectMessageByLocale(IahnNodeMessageInspectByLocaleInfo info)
            throws ServiceException {
        try {
            return iahnNodeOperateHandler.batchInspectMessageByLocale(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("基于国际化地区批量查询国际化消息时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public IahnNodeLocaleListInspectResult inspectLocaleList(IahnNodeLocaleListInspectInfo info)
            throws ServiceException {
        try {
            return iahnNodeOperateHandler.inspectLocaleList(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("查询国际化地区列表时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public IahnNodeMekListInspectResult inspectMekList(IahnNodeMekListInspectInfo info) throws ServiceException {
        try {
            return iahnNodeOperateHandler.inspectMekList(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("查询国际化 Mek 列表时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public IahnNodeMessageTableInspectResult inspectMessageTable(IahnNodeMessageTableInspectInfo info)
            throws ServiceException {
        try {
            return iahnNodeOperateHandler.inspectMessageTable(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("查询国际化消息表时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void putLocale(IahnNodeLocalePutInfo info) throws ServiceException {
        try {
            iahnNodeOperateHandler.putLocale(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("推入国际化地区时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void removeLocale(IahnNodeLocaleRemoveInfo info) throws ServiceException {
        try {
            iahnNodeOperateHandler.removeLocale(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("移除国际化地区时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void putMek(IahnNodeMekPutInfo info) throws ServiceException {
        try {
            iahnNodeOperateHandler.putMek(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("推入国际化 Mek 时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void removeMek(IahnNodeMekRemoveInfo info) throws ServiceException {
        try {
            iahnNodeOperateHandler.removeMek(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("移除国际化 Mek 时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void upsertMessage(IahnNodeMessageUpsertInfo info) throws ServiceException {
        try {
            iahnNodeOperateHandler.upsertMessage(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("插入或更新国际化消息时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void batchUpsertMessageByLocale(IahnNodeMessageUpsertByLocaleInfo info) throws ServiceException {
        try {
            iahnNodeOperateHandler.batchUpsertMessageByLocale(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("基于国际化地区批量插入或更新国际化消息时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void batchUpsertMessageByMek(IahnNodeMessageUpsertByMekInfo info) throws ServiceException {
        try {
            iahnNodeOperateHandler.batchUpsertMessageByMek(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse(
                    "基于国际化 Mek 批量插入或更新国际化消息时发生异常", LogLevel.WARN, e, sem
            );
        }
    }
}
