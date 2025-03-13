package com.dwarfeng.settingrepo.impl.configuration;

import com.dwarfeng.settingrepo.impl.service.operation.*;
import com.dwarfeng.settingrepo.stack.bean.entity.*;
import com.dwarfeng.settingrepo.stack.bean.key.IahnNodeLocaleKey;
import com.dwarfeng.settingrepo.stack.bean.key.IahnNodeMekKey;
import com.dwarfeng.settingrepo.stack.bean.key.IahnNodeMessageKey;
import com.dwarfeng.settingrepo.stack.cache.FormatterSupportCache;
import com.dwarfeng.settingrepo.stack.cache.IahnNodeMessageCache;
import com.dwarfeng.settingrepo.stack.cache.TextNodeCache;
import com.dwarfeng.settingrepo.stack.dao.*;
import com.dwarfeng.subgrade.impl.generation.ExceptionKeyGenerator;
import com.dwarfeng.subgrade.impl.service.CustomBatchCrudService;
import com.dwarfeng.subgrade.impl.service.DaoOnlyEntireLookupService;
import com.dwarfeng.subgrade.impl.service.DaoOnlyPresetLookupService;
import com.dwarfeng.subgrade.impl.service.GeneralBatchCrudService;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {

    private final ServiceExceptionMapperConfiguration serviceExceptionMapperConfiguration;
    private final GenerateConfiguration generateConfiguration;

    private final FormatterSupportDao formatterSupportDao;
    private final FormatterSupportCache formatterSupportCache;
    private final SettingCategoryCrudOperation settingCategoryCrudOperation;
    private final SettingCategoryDao settingCategoryDao;
    private final SettingNodeCrudOperation settingNodeCrudOperation;
    private final SettingNodeDao settingNodeDao;
    private final TextNodeDao textNodeDao;
    private final TextNodeCache textNodeCache;
    private final ImageNodeCrudOperation imageNodeCrudOperation;
    private final ImageNodeDao imageNodeDao;
    private final ImageListNodeCrudOperation imageListNodeCrudOperation;
    private final ImageListNodeDao imageListNodeDao;
    private final ImageListNodeItemCrudOperation imageListNodeItemCrudOperation;
    private final ImageListNodeItemDao imageListNodeItemDao;
    private final IahnNodeCrudOperation iahnNodeCrudOperation;
    private final IahnNodeDao iahnNodeDao;
    private final IahnNodeLocaleCrudOperation iahnNodeLocaleCrudOperation;
    private final IahnNodeLocaleDao iahnNodeLocaleDao;
    private final IahnNodeMekCrudOperation iahnNodeMekCrudOperation;
    private final IahnNodeMekDao iahnNodeMekDao;
    private final IahnNodeMessageDao iahnNodeMessageDao;
    private final IahnNodeMessageCache iahnNodeMessageCache;

    @Value("${cache.timeout.entity.formatter_support}")
    private long formatterSupportTimeout;
    @Value("${cache.timeout.entity.text_node}")
    private long textNodeTimeout;
    @Value("${cache.timeout.entity.iahn_node_message}")
    private long iahnNodeMessageTimeout;

    public ServiceConfiguration(
            ServiceExceptionMapperConfiguration serviceExceptionMapperConfiguration,
            GenerateConfiguration generateConfiguration,
            FormatterSupportDao formatterSupportDao,
            FormatterSupportCache formatterSupportCache,
            SettingCategoryCrudOperation settingCategoryCrudOperation,
            SettingCategoryDao settingCategoryDao,
            SettingNodeCrudOperation settingNodeCrudOperation,
            SettingNodeDao settingNodeDao,
            TextNodeDao textNodeDao,
            TextNodeCache textNodeCache,
            ImageNodeCrudOperation imageNodeCrudOperation,
            ImageNodeDao imageNodeDao,
            ImageListNodeCrudOperation imageListNodeCrudOperation,
            ImageListNodeDao imageListNodeDao,
            ImageListNodeItemCrudOperation imageListNodeItemCrudOperation,
            ImageListNodeItemDao imageListNodeItemDao,
            IahnNodeCrudOperation iahnNodeCrudOperation,
            IahnNodeDao iahnNodeDao,
            IahnNodeLocaleCrudOperation iahnNodeLocaleCrudOperation,
            IahnNodeLocaleDao iahnNodeLocaleDao,
            IahnNodeMekCrudOperation iahnNodeMekCrudOperation,
            IahnNodeMekDao iahnNodeMekDao,
            IahnNodeMessageDao iahnNodeMessageDao,
            IahnNodeMessageCache iahnNodeMessageCache
    ) {
        this.serviceExceptionMapperConfiguration = serviceExceptionMapperConfiguration;
        this.generateConfiguration = generateConfiguration;
        this.formatterSupportDao = formatterSupportDao;
        this.formatterSupportCache = formatterSupportCache;
        this.settingCategoryCrudOperation = settingCategoryCrudOperation;
        this.settingCategoryDao = settingCategoryDao;
        this.settingNodeCrudOperation = settingNodeCrudOperation;
        this.settingNodeDao = settingNodeDao;
        this.textNodeDao = textNodeDao;
        this.textNodeCache = textNodeCache;
        this.imageNodeCrudOperation = imageNodeCrudOperation;
        this.imageNodeDao = imageNodeDao;
        this.imageListNodeCrudOperation = imageListNodeCrudOperation;
        this.imageListNodeDao = imageListNodeDao;
        this.imageListNodeItemCrudOperation = imageListNodeItemCrudOperation;
        this.imageListNodeItemDao = imageListNodeItemDao;
        this.iahnNodeCrudOperation = iahnNodeCrudOperation;
        this.iahnNodeDao = iahnNodeDao;
        this.iahnNodeLocaleCrudOperation = iahnNodeLocaleCrudOperation;
        this.iahnNodeLocaleDao = iahnNodeLocaleDao;
        this.iahnNodeMekCrudOperation = iahnNodeMekCrudOperation;
        this.iahnNodeMekDao = iahnNodeMekDao;
        this.iahnNodeMessageDao = iahnNodeMessageDao;
        this.iahnNodeMessageCache = iahnNodeMessageCache;
    }

    @Bean
    public GeneralBatchCrudService<StringIdKey, FormatterSupport> formatterSupportGeneralBatchCrudService() {
        return new GeneralBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                formatterSupportDao,
                formatterSupportCache,
                new ExceptionKeyGenerator<>(),
                formatterSupportTimeout
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<FormatterSupport> formatterSupportDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                formatterSupportDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<FormatterSupport> formatterSupportDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                formatterSupportDao
        );
    }

    @Bean
    public CustomBatchCrudService<StringIdKey, SettingCategory> settingCategoryCustomBatchCrudService() {
        return new CustomBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                settingCategoryCrudOperation,
                new ExceptionKeyGenerator<>()
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<SettingCategory> settingCategoryDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                settingCategoryDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<SettingCategory> settingCategoryDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                settingCategoryDao
        );
    }

    @Bean
    public CustomBatchCrudService<StringIdKey, SettingNode> settingNodeCustomBatchCrudService() {
        return new CustomBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                settingNodeCrudOperation,
                new ExceptionKeyGenerator<>()
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<SettingNode> settingNodeDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                settingNodeDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<SettingNode> settingNodeDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                settingNodeDao
        );
    }

    @Bean
    public GeneralBatchCrudService<StringIdKey, TextNode> textNodeGeneralBatchCrudService() {
        return new GeneralBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                textNodeDao,
                textNodeCache,
                new ExceptionKeyGenerator<>(),
                textNodeTimeout
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<TextNode> textNodeDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                textNodeDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<TextNode> textNodeDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                textNodeDao
        );
    }

    @Bean
    public CustomBatchCrudService<StringIdKey, ImageNode> imageNodeCustomBatchCrudService() {
        return new CustomBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                imageNodeCrudOperation,
                new ExceptionKeyGenerator<>()
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<ImageNode> imageNodeDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                imageNodeDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<ImageNode> imageNodeDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                imageNodeDao
        );
    }

    @Bean
    public CustomBatchCrudService<StringIdKey, ImageListNode> imageListNodeCustomBatchCrudService() {
        return new CustomBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                imageListNodeCrudOperation,
                new ExceptionKeyGenerator<>()
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<ImageListNode> imageListNodeDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                imageListNodeDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<ImageListNode> imageListNodeDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                imageListNodeDao
        );
    }

    @Bean
    public CustomBatchCrudService<LongIdKey, ImageListNodeItem> imageListNodeItemCustomBatchCrudService() {
        return new CustomBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                imageListNodeItemCrudOperation,
                generateConfiguration.snowflakeLongIdKeyGenerator()
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<ImageListNodeItem> imageListNodeItemDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                imageListNodeItemDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<ImageListNodeItem> imageListNodeItemDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                imageListNodeItemDao
        );
    }

    @Bean
    public CustomBatchCrudService<StringIdKey, IahnNode> iahnNodeCustomBatchCrudService() {
        return new CustomBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                iahnNodeCrudOperation,
                new ExceptionKeyGenerator<>()
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<IahnNode> iahnNodeDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                iahnNodeDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<IahnNode> iahnNodeDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                iahnNodeDao
        );
    }

    @Bean
    public CustomBatchCrudService<IahnNodeLocaleKey, IahnNodeLocale> iahnNodeLocaleCustomBatchCrudService() {
        return new CustomBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                iahnNodeLocaleCrudOperation,
                new ExceptionKeyGenerator<>()
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<IahnNodeLocale> iahnNodeLocaleDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                iahnNodeLocaleDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<IahnNodeLocale> iahnNodeLocaleDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                iahnNodeLocaleDao
        );
    }

    @Bean
    public CustomBatchCrudService<IahnNodeMekKey, IahnNodeMek> iahnNodeMekCustomBatchCrudService() {
        return new CustomBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                iahnNodeMekCrudOperation,
                new ExceptionKeyGenerator<>()
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<IahnNodeMek> iahnNodeMekDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                iahnNodeMekDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<IahnNodeMek> iahnNodeMekDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                iahnNodeMekDao
        );
    }

    @Bean
    public GeneralBatchCrudService<IahnNodeMessageKey, IahnNodeMessage> iahnNodeMessageGeneralBatchCrudService() {
        return new GeneralBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                iahnNodeMessageDao,
                iahnNodeMessageCache,
                new ExceptionKeyGenerator<>(),
                iahnNodeMessageTimeout
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<IahnNodeMessage> iahnNodeMessageDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                iahnNodeMessageDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<IahnNodeMessage> iahnNodeMessageDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                iahnNodeMessageDao
        );
    }
}
