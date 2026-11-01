package com.dwarfeng.settingrepo.impl.handler;

import com.dwarfeng.settingrepo.sdk.handler.WarmupDriverProvider;
import com.dwarfeng.settingrepo.sdk.util.Constants;
import com.dwarfeng.settingrepo.stack.bean.dto.*;
import com.dwarfeng.settingrepo.stack.bean.entity.WarmupSetting;
import com.dwarfeng.settingrepo.stack.exception.UnsupportedWarmupDriverTypeDriverException;
import com.dwarfeng.settingrepo.stack.exception.WarmupDriverException;
import com.dwarfeng.settingrepo.stack.handler.*;
import com.dwarfeng.settingrepo.stack.service.WarmupSettingMaintainService;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class WarmupDriverHandlerImpl implements WarmupDriverHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(WarmupDriverHandlerImpl.class);

    private final WarmupSettingMaintainService warmupSettingMaintainService;

    private final KvNodeOperateHandler kvNodeOperateHandler;
    private final NavigationNodeOperateHandler navigationNodeOperateHandler;
    private final IahnNodeOperateHandler iahnNodeOperateHandler;

    private final List<WarmupDriverProvider> warmupDriverProviders;

    private final InternalWarmupDriverContext warmupDriverContext = new InternalWarmupDriverContext();

    public WarmupDriverHandlerImpl(
            WarmupSettingMaintainService warmupSettingMaintainService,
            KvNodeOperateHandler kvNodeOperateHandler,
            NavigationNodeOperateHandler navigationNodeOperateHandler,
            IahnNodeOperateHandler iahnNodeOperateHandler,
            List<WarmupDriverProvider> warmupDriverProviders
    ) {
        this.warmupSettingMaintainService = warmupSettingMaintainService;
        this.kvNodeOperateHandler = kvNodeOperateHandler;
        this.navigationNodeOperateHandler = navigationNodeOperateHandler;
        this.iahnNodeOperateHandler = iahnNodeOperateHandler;
        this.warmupDriverProviders = Optional.ofNullable(warmupDriverProviders).orElse(Collections.emptyList());
    }

    @PostConstruct
    public void init() {
        LOGGER.info("初始化预热驱动器...");
        warmupDriverProviders.stream().map(WarmupDriverProvider::provide)
                .forEach(warmupDriver -> warmupDriver.init(warmupDriverContext));
    }

    @Override
    public WarmupDriver find(String type) throws WarmupDriverException {
        return warmupDriverProviders.stream()
                .filter(warmupDriverProvider -> warmupDriverProvider.supportType(type))
                .map(WarmupDriverProvider::provide)
                .findAny()
                .orElseThrow(() -> new UnsupportedWarmupDriverTypeDriverException(type));
    }

    /**
     * 内部驱动器上下文。
     *
     * @author DwArFeng
     * @since 3.1.0
     */
    private class InternalWarmupDriverContext implements WarmupDriver.Context {

        @Override
        public void warmup(LongIdKey warmupSettingKey) throws Exception {
            WarmupSetting warmupSetting = warmupSettingMaintainService.get(warmupSettingKey);

            switch (warmupSetting.getType()) {
                case Constants.WARMUP_CACHE_TYPE_KV_NODE_INSPECT_RESULT:
                    warmupKvNodeInspect(warmupSetting);
                    break;
                case Constants.WARMUP_CACHE_TYPE_KV_NODE_INSPECT_ITEM_RESULT:
                    warmupKvNodeInspectItem(warmupSetting);
                    break;
                case Constants.WARMUP_CACHE_TYPE_NAVIGATION_NODE_INSPECT_RESULT:
                    warmupNavigationNodeInspectResult(warmupSetting);
                    break;
                case Constants.WARMUP_CACHE_TYPE_IAHN_NODE_LOCALE_MAP:
                    warmupIahnNodeLocaleMap(warmupSetting);
                    break;
                case Constants.WARMUP_CACHE_TYPE_IAHN_NODE_MESSAGE_MAP:
                    warmupIahnNodeMessageMap(warmupSetting);
                    break;
                case Constants.WARMUP_CACHE_TYPE_IAHN_NODE_LOCALE_LIST:
                    warmupIahnNodeLocaleList(warmupSetting);
                    break;
                case Constants.WARMUP_CACHE_TYPE_IAHN_NODE_MEK_LIST:
                    warmupIahnNodeMekList(warmupSetting);
                    break;
                case Constants.WARMUP_CACHE_TYPE_IAHN_NODE_MESSAGE_TABLE:
                    warmupIahnNodeMessageTable(warmupSetting);
                    break;
                default:
                    throw new IllegalArgumentException("预热设置类型 " + warmupSetting.getType() + " 无效");
            }
        }

        /**
         * 预热键值对节点查看结果缓存。
         *
         * @param warmupSetting 预热设置。
         * @throws Exception 方法执行过程中发生的任何异常。
         */
        private void warmupKvNodeInspect(WarmupSetting warmupSetting) throws Exception {
            kvNodeOperateHandler.inspectWithResetTtl(new KvNodeInspectInfo(
                    warmupSetting.getTargetStringAlpha(), warmupSetting.getTargetStringArrayAlpha()
            ));
        }

        /**
         * 预热键值对节点条目查看结果缓存。
         *
         * @param warmupSetting 预热设置。
         * @throws Exception 方法执行过程中发生的任何异常。
         */
        private void warmupKvNodeInspectItem(WarmupSetting warmupSetting) throws Exception {
            kvNodeOperateHandler.inspectItemWithResetTtl(new KvNodeItemInspectInfo(
                    warmupSetting.getTargetStringAlpha(), warmupSetting.getTargetStringArrayAlpha(),
                    warmupSetting.getTargetStringBravo()
            ));
        }

        /**
         * 预热导航节点查看结果缓存。
         *
         * @param warmupSetting 预热设置。
         * @throws Exception 方法执行过程中发生的任何异常。
         */
        private void warmupNavigationNodeInspectResult(WarmupSetting warmupSetting) throws Exception {
            navigationNodeOperateHandler.inspectWithResetTtl(new NavigationNodeInspectInfo(
                    warmupSetting.getTargetStringAlpha(), warmupSetting.getTargetStringArrayAlpha()
            ));
        }

        /**
         * 预热国际化节点地区映射缓存。
         *
         * @param warmupSetting 预热设置。
         * @throws Exception 方法执行过程中发生的任何异常。
         */
        private void warmupIahnNodeLocaleMap(WarmupSetting warmupSetting) throws Exception {
            iahnNodeOperateHandler.inspectMessageWithResetTtl(new IahnNodeMessageInspectInfo(
                    warmupSetting.getTargetStringAlpha(), warmupSetting.getTargetStringArrayAlpha(),
                    warmupSetting.getTargetStringCharlie(), warmupSetting.getTargetStringDelta(),
                    warmupSetting.getTargetStringEcho(), null
            ));
        }

        /**
         * 预热国际化节点消息映射缓存。
         *
         * @param warmupSetting 预热设置。
         * @throws Exception 方法执行过程中发生的任何异常。
         */
        private void warmupIahnNodeMessageMap(WarmupSetting warmupSetting) throws Exception {
            iahnNodeOperateHandler.batchInspectMessageByLocaleWithResetTtl(
                    new IahnNodeMessageInspectByLocaleInfo(
                            warmupSetting.getTargetStringAlpha(), warmupSetting.getTargetStringArrayAlpha(),
                            warmupSetting.getTargetStringCharlie(), warmupSetting.getTargetStringDelta(),
                            warmupSetting.getTargetStringEcho()
                    )
            );
        }

        /**
         * 预热国际化节点地区列表缓存。
         *
         * @param warmupSetting 预热设置。
         * @throws Exception 方法执行过程中发生的任何异常。
         */
        private void warmupIahnNodeLocaleList(WarmupSetting warmupSetting) throws Exception {
            iahnNodeOperateHandler.inspectLocaleListWithResetTtl(new IahnNodeLocaleListInspectInfo(
                    warmupSetting.getTargetStringAlpha(), warmupSetting.getTargetStringArrayAlpha()
            ));
        }

        /**
         * 预热国际化节点 Mek 列表缓存。
         *
         * @param warmupSetting 预热设置。
         * @throws Exception 方法执行过程中发生的任何异常。
         */
        private void warmupIahnNodeMekList(WarmupSetting warmupSetting) throws Exception {
            iahnNodeOperateHandler.inspectMekListWithResetTtl(new IahnNodeMekListInspectInfo(
                    warmupSetting.getTargetStringAlpha(), warmupSetting.getTargetStringArrayAlpha()
            ));
        }

        /**
         * 预热国际化节点消息表缓存。
         *
         * @param warmupSetting 预热设置。
         * @throws Exception 方法执行过程中发生的任何异常。
         */
        private void warmupIahnNodeMessageTable(WarmupSetting warmupSetting) throws Exception {
            iahnNodeOperateHandler.inspectMessageTableWithResetTtl(new IahnNodeMessageTableInspectInfo(
                    warmupSetting.getTargetStringAlpha(), warmupSetting.getTargetStringArrayAlpha()
            ));
        }
    }
}
