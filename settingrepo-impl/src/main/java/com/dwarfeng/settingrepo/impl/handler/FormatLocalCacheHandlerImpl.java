package com.dwarfeng.settingrepo.impl.handler;

import com.dwarfeng.settingrepo.stack.bean.entity.SettingCategory;
import com.dwarfeng.settingrepo.stack.handler.FormatLocalCacheHandler;
import com.dwarfeng.settingrepo.stack.handler.Formatter;
import com.dwarfeng.settingrepo.stack.handler.FormatterHandler;
import com.dwarfeng.settingrepo.stack.service.SettingCategoryMaintainService;
import com.dwarfeng.subgrade.impl.handler.Fetcher;
import com.dwarfeng.subgrade.impl.handler.GeneralLocalCacheHandler;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class FormatLocalCacheHandlerImpl implements FormatLocalCacheHandler {

    private final GeneralLocalCacheHandler<StringIdKey, Formatter> handler;

    public FormatLocalCacheHandlerImpl(FormatterFetcher formatterFetcher) {
        this.handler = new GeneralLocalCacheHandler<>(formatterFetcher);
    }

    @BehaviorAnalyse
    @Override
    public boolean exists(StringIdKey key) throws HandlerException {
        return handler.exists(key);
    }

    @BehaviorAnalyse
    @Override
    public Formatter get(StringIdKey key) throws HandlerException {
        return handler.get(key);
    }

    @BehaviorAnalyse
    @Override
    public boolean remove(StringIdKey key) {
        return handler.remove(key);
    }

    @BehaviorAnalyse
    @Override
    public void clear() {
        handler.clear();
    }

    @Component
    public static class FormatterFetcher implements Fetcher<StringIdKey, Formatter> {

        private final SettingCategoryMaintainService settingCategoryMaintainService;

        private final FormatterHandler formatterHandler;

        public FormatterFetcher(
                SettingCategoryMaintainService settingCategoryMaintainService,
                FormatterHandler formatterHandler
        ) {
            this.settingCategoryMaintainService = settingCategoryMaintainService;
            this.formatterHandler = formatterHandler;
        }

        @Override
        @BehaviorAnalyse
        @Transactional(
                transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class
        )
        public boolean exists(StringIdKey key) throws Exception {
            return settingCategoryMaintainService.exists(key);
        }

        @Override
        @BehaviorAnalyse
        @Transactional(
                transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class
        )
        public Formatter fetch(StringIdKey key) throws Exception {
            SettingCategory settingCategory = settingCategoryMaintainService.get(key);
            return formatterHandler.make(settingCategory);
        }
    }
}
