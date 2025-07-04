package com.dwarfeng.settingrepo.impl.handler;

import com.dwarfeng.settingrepo.stack.bean.dto.*;
import com.dwarfeng.settingrepo.stack.bean.entity.SettingNode;
import com.dwarfeng.settingrepo.stack.handler.FormatLocalCacheHandler;
import com.dwarfeng.settingrepo.stack.handler.SettingNodeOperateHandler;
import com.dwarfeng.settingrepo.stack.service.SettingNodeMaintainService;
import com.dwarfeng.subgrade.sdk.exception.HandlerExceptionHelper;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class SettingNodeOperateHandlerImpl implements SettingNodeOperateHandler {

    private final SettingNodeMaintainService settingNodeMaintainService;

    private final FormatLocalCacheHandler formatLocalCacheHandler;

    private final HandlerValidator handlerValidator;

    public SettingNodeOperateHandlerImpl(
            SettingNodeMaintainService settingNodeMaintainService,
            FormatLocalCacheHandler formatLocalCacheHandler,
            HandlerValidator handlerValidator
    ) {
        this.settingNodeMaintainService = settingNodeMaintainService;
        this.formatLocalCacheHandler = formatLocalCacheHandler;
        this.handlerValidator = handlerValidator;
    }

    @BehaviorAnalyse
    @Override
    public SettingNodeExistsResult exists(SettingNodeExistsInfo info) throws HandlerException {
        try {
            // 展开参数。
            String category = info.getCategory();
            String[] args = info.getArgs();

            // 确认设置类别存在。
            StringIdKey settingCategoryKey = new StringIdKey(category);
            handlerValidator.makeSureSettingCategoryExists(settingCategoryKey);

            // 根据 category 以及 args 获取对应的设置节点主键。
            StringIdKey settingNodeKey = formatLocalCacheHandler.get(settingCategoryKey).format(args);

            // 确认设置节点存在。
            boolean exists = settingNodeMaintainService.exists(settingNodeKey);

            // 构造返回值并返回。
            return new SettingNodeExistsResult(exists);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @BehaviorAnalyse
    @Override
    public SettingNodeInspectResult inspect(SettingNodeInspectInfo info) throws HandlerException {
        try {
            // 展开参数。
            String category = info.getCategory();
            String[] args = info.getArgs();

            // 确认设置类别存在。
            StringIdKey settingCategoryKey = new StringIdKey(category);
            handlerValidator.makeSureSettingCategoryExists(settingCategoryKey);

            // 根据 category 以及 args 获取对应的设置节点主键。
            StringIdKey settingNodeKey = formatLocalCacheHandler.get(settingCategoryKey).format(args);

            // 获取设置节点。
            SettingNode settingNode = settingNodeMaintainService.getIfExists(settingNodeKey);

            // 如果设置节点不存在，则返回 null。
            if (settingNode == null) {
                return null;
            }

            // 构造返回值并返回。
            return new SettingNodeInspectResult(
                    settingNode.getType(), settingNode.getLastModifiedDate(), settingNode.getRemark()
            );
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @BehaviorAnalyse
    @Override
    public void init(SettingNodeInitInfo info) throws HandlerException {
        try {
            // 展开参数。
            String category = info.getCategory();
            String[] args = info.getArgs();
            int type = info.getType();
            String remark = info.getRemark();

            // 确认设置类别存在。
            StringIdKey settingCategoryKey = new StringIdKey(category);
            handlerValidator.makeSureSettingCategoryExists(settingCategoryKey);

            // 根据 category 以及 args 获取对应的设置节点主键。
            StringIdKey settingNodeKey = formatLocalCacheHandler.get(settingCategoryKey).format(args);

            // 确认设置节点不存在。
            handlerValidator.makeSureSettingNodeNotExists(settingNodeKey);

            // 构造设置节点。
            SettingNode settingNode = new SettingNode(settingNodeKey, type, new Date(), remark, true, category, args);

            // 调用维护服务插入实体。
            settingNodeMaintainService.insert(settingNode);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @BehaviorAnalyse
    @Override
    public void remove(SettingNodeRemoveInfo info) throws HandlerException {
        try {
            // 展开参数。
            String category = info.getCategory();
            String[] args = info.getArgs();

            // 确认设置类别存在。
            StringIdKey settingCategoryKey = new StringIdKey(category);
            handlerValidator.makeSureSettingCategoryExists(settingCategoryKey);

            // 根据 category 以及 args 获取对应的设置节点主键。
            StringIdKey settingNodeKey = formatLocalCacheHandler.get(settingCategoryKey).format(args);

            // 确认设置节点存在。
            handlerValidator.makeSureSettingNodeExists(settingNodeKey);

            // 调用维护服务删除实体。
            settingNodeMaintainService.delete(settingNodeKey);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }
}
