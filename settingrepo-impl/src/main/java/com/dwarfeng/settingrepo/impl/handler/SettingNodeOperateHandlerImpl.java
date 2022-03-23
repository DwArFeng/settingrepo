package com.dwarfeng.settingrepo.impl.handler;

import com.dwarfeng.settingrepo.stack.bean.dto.SettingNodeInspectInfo;
import com.dwarfeng.settingrepo.stack.bean.dto.SettingNodePutInfo;
import com.dwarfeng.settingrepo.stack.bean.dto.SettingNodeRemoveInfo;
import com.dwarfeng.settingrepo.stack.bean.entity.SettingNode;
import com.dwarfeng.settingrepo.stack.exception.SettingCategoryNotExistsException;
import com.dwarfeng.settingrepo.stack.handler.FormatLocalCacheHandler;
import com.dwarfeng.settingrepo.stack.handler.Formatter;
import com.dwarfeng.settingrepo.stack.handler.SettingNodeOperateHandler;
import com.dwarfeng.settingrepo.stack.service.SettingNodeMaintainService;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class SettingNodeOperateHandlerImpl implements SettingNodeOperateHandler {

    private final SettingNodeMaintainService settingNodeMaintainService;

    private final FormatLocalCacheHandler formatLocalCacheHandler;

    public SettingNodeOperateHandlerImpl(
            SettingNodeMaintainService settingNodeMaintainService, FormatLocalCacheHandler formatLocalCacheHandler
    ) {
        this.settingNodeMaintainService = settingNodeMaintainService;
        this.formatLocalCacheHandler = formatLocalCacheHandler;
    }

    @Override
    public SettingNode inspect(SettingNodeInspectInfo settingNodeInspectInfo) throws HandlerException {
        try {
            StringIdKey settingCategoryKey = new StringIdKey(settingNodeInspectInfo.getCategory());

            // 1. 确认设置类别存在。
            makeSureSettingCategoryExists(settingCategoryKey);

            // 2. 获取格式化器。
            Formatter formatter = formatLocalCacheHandler.getFormatter(settingCategoryKey);

            // 3. 使用格式化器获取 settingNode 的主键。
            StringIdKey settingNodeKey = formatter.format(settingNodeInspectInfo.getArgs());

            // 4. 获取设置节点实体并返回。
            return settingNodeMaintainService.getIfExists(settingNodeKey);
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    @Override
    public void put(SettingNodePutInfo settingNodePutInfo) throws HandlerException {
        try {
            StringIdKey settingCategoryKey = new StringIdKey(settingNodePutInfo.getCategory());

            // 1. 确认设置类别存在。
            makeSureSettingCategoryExists(settingCategoryKey);

            // 2. 获取格式化器。
            Formatter formatter = formatLocalCacheHandler.getFormatter(settingCategoryKey);

            // 3. 使用格式化器获取 settingNode 的主键。
            StringIdKey settingNodeKey = formatter.format(settingNodePutInfo.getArgs());

            // 4. 构造新的设置节点实体。
            SettingNode settingNode = new SettingNode(
                    settingNodeKey,
                    settingNodePutInfo.getValue(),
                    settingNodePutInfo.getRemark()
            );

            // 5. 添加或更新设置节点实体。
            settingNodeMaintainService.insertOrUpdate(settingNode);
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    @Override
    public void remove(SettingNodeRemoveInfo settingNodeRemoveInfo) throws HandlerException {
        try {
            StringIdKey settingCategoryKey = new StringIdKey(settingNodeRemoveInfo.getCategory());

            // 1. 确认设置类别存在。
            makeSureSettingCategoryExists(settingCategoryKey);

            // 2. 获取格式化器。
            Formatter formatter = formatLocalCacheHandler.getFormatter(settingCategoryKey);

            // 3. 使用格式化器获取 settingNode 的主键。
            StringIdKey settingNodeKey = formatter.format(settingNodeRemoveInfo.getArgs());

            // 4. 删除设置节点实体。
            settingNodeMaintainService.deleteIfExists(settingNodeKey);
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    private void makeSureSettingCategoryExists(StringIdKey settingCategoryKey) throws HandlerException {
        Formatter formatter = formatLocalCacheHandler.getFormatter(settingCategoryKey);
        if (Objects.isNull(formatter)) {
            throw new SettingCategoryNotExistsException(settingCategoryKey);
        }
    }
}
