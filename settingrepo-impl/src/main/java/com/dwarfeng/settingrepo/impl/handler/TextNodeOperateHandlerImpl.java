package com.dwarfeng.settingrepo.impl.handler;

import com.dwarfeng.settingrepo.sdk.util.Constants;
import com.dwarfeng.settingrepo.stack.bean.dto.TextNodeInspectInfo;
import com.dwarfeng.settingrepo.stack.bean.dto.TextNodeInspectResult;
import com.dwarfeng.settingrepo.stack.bean.dto.TextNodePutInfo;
import com.dwarfeng.settingrepo.stack.bean.entity.SettingNode;
import com.dwarfeng.settingrepo.stack.bean.entity.TextNode;
import com.dwarfeng.settingrepo.stack.handler.FormatLocalCacheHandler;
import com.dwarfeng.settingrepo.stack.handler.TextNodeOperateHandler;
import com.dwarfeng.settingrepo.stack.service.SettingNodeMaintainService;
import com.dwarfeng.settingrepo.stack.service.TextNodeMaintainService;
import com.dwarfeng.subgrade.sdk.exception.HandlerExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

/**
 * 文本节点操作处理器。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
@Component
public class TextNodeOperateHandlerImpl implements TextNodeOperateHandler {

    private final SettingNodeMaintainService settingNodeMaintainService;
    private final TextNodeMaintainService textNodeMaintainService;

    private final FormatLocalCacheHandler formatLocalCacheHandler;

    private final HandlerValidator handlerValidator;

    public TextNodeOperateHandlerImpl(
            SettingNodeMaintainService settingNodeMaintainService,
            TextNodeMaintainService textNodeMaintainService,
            FormatLocalCacheHandler formatLocalCacheHandler,
            HandlerValidator handlerValidator
    ) {
        this.settingNodeMaintainService = settingNodeMaintainService;
        this.textNodeMaintainService = textNodeMaintainService;
        this.formatLocalCacheHandler = formatLocalCacheHandler;
        this.handlerValidator = handlerValidator;
    }

    @Override
    public TextNodeInspectResult inspect(TextNodeInspectInfo info) throws HandlerException {
        try {
            // 展开参数。
            String category = info.getCategory();
            String[] args = info.getArgs();

            // 确认设置类别存在。
            StringIdKey settingCategoryKey = new StringIdKey(category);
            handlerValidator.makeSureSettingCategoryExists(settingCategoryKey);

            // 根据 category 以及 args 获取对应的设置节点主键。
            StringIdKey settingNodeKey = formatLocalCacheHandler.get(settingCategoryKey).format(args);

            // 获取设置节点实体。
            SettingNode settingNode = settingNodeMaintainService.getIfExists(settingNodeKey);

            // 如果节点不适配，则直接返回 null。
            if (!settingNodeMatching(settingNode)) {
                return null;
            }

            // 获取文本节点。
            TextNode textNode = textNodeMaintainService.getIfExists(settingNodeKey);

            // 如果文本节点不存在，则初始化，并插入或更新实体。
            if (Objects.isNull(textNode)) {
                textNode = new TextNode(settingNodeKey, null);
                textNodeMaintainService.insertOrUpdate(textNode);
            }

            // 构造结果并返回。
            return new TextNodeInspectResult(textNode.getValue());
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @Override
    public void put(TextNodePutInfo info) throws HandlerException {
        try {
            // 展开参数。
            String category = info.getCategory();
            String[] args = info.getArgs();
            String value = info.getValue();

            // 确认设置类别存在。
            StringIdKey settingCategoryKey = new StringIdKey(category);
            handlerValidator.makeSureSettingCategoryExists(settingCategoryKey);

            // 根据 category 以及 args 获取对应的设置节点主键。
            StringIdKey settingNodeKey = formatLocalCacheHandler.get(settingCategoryKey).format(args);

            // 获取设置节点实体。
            SettingNode settingNode = settingNodeMaintainService.getIfExists(settingNodeKey);

            // 如果节点设置不存在，则初始化节点设置实体。
            if (Objects.isNull(settingNode)) {
                String remark = "由 settingrepo 自动生成的文本节点";
                settingNode = new SettingNode(
                        settingNodeKey, Constants.SETTING_NODE_TYPE_TEXT, new Date(), remark,
                        true, category, args
                );
            }
            // 否则更新属性。
            else {
                settingNode.setType(Constants.SETTING_NODE_TYPE_TEXT);
                settingNode.setLastModifiedDate(new Date());
            }

            // 构造文本节点实体。
            TextNode textNode = new TextNode(settingNodeKey, value);

            // 插入或更新文本节点。
            textNodeMaintainService.insertOrUpdate(textNode);

            // 更新设置节点属性。
            settingNode.setLastModifiedDate(new Date());

            // 插入或更新设置节点。
            settingNodeMaintainService.insertOrUpdate(settingNode);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    private boolean settingNodeMatching(SettingNode settingNode) {
        if (Objects.isNull(settingNode)) {
            return false;
        }
        return Objects.equals(settingNode.getType(), Constants.SETTING_NODE_TYPE_TEXT);
    }
}
