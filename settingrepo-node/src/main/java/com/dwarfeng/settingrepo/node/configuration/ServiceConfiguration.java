package com.dwarfeng.settingrepo.node.configuration;

import com.dwarfeng.settingrepo.impl.service.operation.ImageNodeCrudOperation;
import com.dwarfeng.settingrepo.impl.service.operation.SettingNodeCrudOperation;
import com.dwarfeng.settingrepo.stack.bean.entity.*;
import com.dwarfeng.settingrepo.stack.cache.FormatterSupportCache;
import com.dwarfeng.settingrepo.stack.cache.SettingCategoryCache;
import com.dwarfeng.settingrepo.stack.cache.TextNodeCache;
import com.dwarfeng.settingrepo.stack.dao.*;
import com.dwarfeng.subgrade.impl.generation.ExceptionKeyGenerator;
import com.dwarfeng.subgrade.impl.service.CustomBatchCrudService;
import com.dwarfeng.subgrade.impl.service.DaoOnlyEntireLookupService;
import com.dwarfeng.subgrade.impl.service.DaoOnlyPresetLookupService;
import com.dwarfeng.subgrade.impl.service.GeneralBatchCrudService;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {

    private final ServiceExceptionMapperConfiguration serviceExceptionMapperConfiguration;

    private final FormatterSupportDao formatterSupportDao;
    private final FormatterSupportCache formatterSupportCache;
    private final SettingCategoryDao settingCategoryDao;
    private final SettingCategoryCache settingCategoryCache;
    private final SettingNodeCrudOperation settingNodeCrudOperation;
    private final SettingNodeDao settingNodeDao;
    private final TextNodeDao textNodeDao;
    private final TextNodeCache textNodeCache;
    private final ImageNodeCrudOperation imageNodeCrudOperation;
    private final ImageNodeDao imageNodeDao;

    @Value("${cache.timeout.entity.formatter_support}")
    private long formatterSupportTimeout;
    @Value("${cache.timeout.entity.setting_category}")
    private long settingCategoryTimeout;
    @Value("${cache.timeout.entity.text_node}")
    private long textNodeTimeout;

    public ServiceConfiguration(
            ServiceExceptionMapperConfiguration serviceExceptionMapperConfiguration,
            FormatterSupportDao formatterSupportDao,
            FormatterSupportCache formatterSupportCache,
            SettingCategoryDao settingCategoryDao,
            SettingCategoryCache settingCategoryCache,
            SettingNodeCrudOperation settingNodeCrudOperation,
            SettingNodeDao settingNodeDao,
            TextNodeDao textNodeDao,
            TextNodeCache textNodeCache,
            ImageNodeCrudOperation imageNodeCrudOperation,
            ImageNodeDao imageNodeDao
    ) {
        this.serviceExceptionMapperConfiguration = serviceExceptionMapperConfiguration;
        this.formatterSupportDao = formatterSupportDao;
        this.formatterSupportCache = formatterSupportCache;
        this.settingCategoryDao = settingCategoryDao;
        this.settingCategoryCache = settingCategoryCache;
        this.settingNodeCrudOperation = settingNodeCrudOperation;
        this.settingNodeDao = settingNodeDao;
        this.textNodeDao = textNodeDao;
        this.textNodeCache = textNodeCache;
        this.imageNodeCrudOperation = imageNodeCrudOperation;
        this.imageNodeDao = imageNodeDao;
    }

    @Bean
    public GeneralBatchCrudService<StringIdKey, FormatterSupport> formatterSupportGeneralBatchCrudService() {
        return new GeneralBatchCrudService<>(
                formatterSupportDao,
                formatterSupportCache,
                new ExceptionKeyGenerator<>(),
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                formatterSupportTimeout
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<FormatterSupport> formatterSupportDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                formatterSupportDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<FormatterSupport> formatterSupportDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                formatterSupportDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public GeneralBatchCrudService<StringIdKey, SettingCategory> settingCategoryGeneralBatchCrudService() {
        return new GeneralBatchCrudService<>(
                settingCategoryDao,
                settingCategoryCache,
                new ExceptionKeyGenerator<>(),
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                settingCategoryTimeout
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<SettingCategory> settingCategoryDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                settingCategoryDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<SettingCategory> settingCategoryDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                settingCategoryDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public CustomBatchCrudService<StringIdKey, SettingNode> settingNodeCustomBatchCrudService() {
        return new CustomBatchCrudService<>(
                settingNodeCrudOperation,
                new ExceptionKeyGenerator<>(),
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<SettingNode> settingNodeDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                settingNodeDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<SettingNode> settingNodeDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                settingNodeDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public GeneralBatchCrudService<StringIdKey, TextNode> textNodeGeneralBatchCrudService() {
        return new GeneralBatchCrudService<>(
                textNodeDao,
                textNodeCache,
                new ExceptionKeyGenerator<>(),
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                textNodeTimeout
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<TextNode> textNodeDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                textNodeDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<TextNode> textNodeDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                textNodeDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public CustomBatchCrudService<StringIdKey, ImageNode> imageNodeCustomBatchCrudService() {
        return new CustomBatchCrudService<>(
                imageNodeCrudOperation,
                new ExceptionKeyGenerator<>(),
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<ImageNode> imageNodeDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                imageNodeDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<ImageNode> imageNodeDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                imageNodeDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }
}
