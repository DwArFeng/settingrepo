package com.dwarfeng.settingrepo.node.configuration;

import com.dwarfeng.settingrepo.impl.bean.entity.HibernateFormatterSupport;
import com.dwarfeng.settingrepo.impl.bean.entity.HibernateSettingCategory;
import com.dwarfeng.settingrepo.impl.bean.entity.HibernateSettingNode;
import com.dwarfeng.settingrepo.impl.dao.preset.FormatterSupportPresetCriteriaMaker;
import com.dwarfeng.settingrepo.impl.dao.preset.SettingCategoryPresetCriteriaMaker;
import com.dwarfeng.settingrepo.impl.dao.preset.SettingNodePresetCriteriaMaker;
import com.dwarfeng.settingrepo.stack.bean.entity.FormatterSupport;
import com.dwarfeng.settingrepo.stack.bean.entity.SettingCategory;
import com.dwarfeng.settingrepo.stack.bean.entity.SettingNode;
import com.dwarfeng.subgrade.impl.bean.DozerBeanTransformer;
import com.dwarfeng.subgrade.impl.dao.HibernateBatchBaseDao;
import com.dwarfeng.subgrade.impl.dao.HibernateEntireLookupDao;
import com.dwarfeng.subgrade.impl.dao.HibernatePresetLookupDao;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateStringIdKey;
import com.dwarfeng.subgrade.sdk.hibernate.modification.DefaultDeletionMod;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTemplate;

@Configuration
public class DaoConfiguration {

    private final HibernateTemplate template;
    private final Mapper mapper;

    private final FormatterSupportPresetCriteriaMaker formatterSupportPresetCriteriaMaker;
    private final SettingCategoryPresetCriteriaMaker settingCategoryPresetCriteriaMaker;
    private final SettingNodePresetCriteriaMaker settingNodePresetCriteriaMaker;

    @Value("${hibernate.jdbc.batch_size}")
    private int batchSize;

    public DaoConfiguration(
            HibernateTemplate template, Mapper mapper,
            FormatterSupportPresetCriteriaMaker formatterSupportPresetCriteriaMaker,
            SettingCategoryPresetCriteriaMaker settingCategoryPresetCriteriaMaker,
            SettingNodePresetCriteriaMaker settingNodePresetCriteriaMaker
    ) {
        this.template = template;
        this.mapper = mapper;
        this.formatterSupportPresetCriteriaMaker = formatterSupportPresetCriteriaMaker;
        this.settingCategoryPresetCriteriaMaker = settingCategoryPresetCriteriaMaker;
        this.settingNodePresetCriteriaMaker = settingNodePresetCriteriaMaker;
    }

    @Bean
    public HibernateBatchBaseDao<StringIdKey, HibernateStringIdKey, FormatterSupport, HibernateFormatterSupport>
    formatterSupportHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new DozerBeanTransformer<>(StringIdKey.class, HibernateStringIdKey.class, mapper),
                new DozerBeanTransformer<>(FormatterSupport.class, HibernateFormatterSupport.class, mapper),
                HibernateFormatterSupport.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<FormatterSupport, HibernateFormatterSupport>
    formatterSupportHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                template,
                new DozerBeanTransformer<>(FormatterSupport.class, HibernateFormatterSupport.class, mapper),
                HibernateFormatterSupport.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<FormatterSupport, HibernateFormatterSupport>
    formatterSupportHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                template,
                new DozerBeanTransformer<>(FormatterSupport.class, HibernateFormatterSupport.class, mapper),
                HibernateFormatterSupport.class,
                formatterSupportPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<StringIdKey, HibernateStringIdKey, SettingCategory, HibernateSettingCategory>
    settingCategoryHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new DozerBeanTransformer<>(StringIdKey.class, HibernateStringIdKey.class, mapper),
                new DozerBeanTransformer<>(SettingCategory.class, HibernateSettingCategory.class, mapper),
                HibernateSettingCategory.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<SettingCategory, HibernateSettingCategory>
    settingCategoryHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                template,
                new DozerBeanTransformer<>(SettingCategory.class, HibernateSettingCategory.class, mapper),
                HibernateSettingCategory.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<SettingCategory, HibernateSettingCategory>
    settingCategoryHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                template,
                new DozerBeanTransformer<>(SettingCategory.class, HibernateSettingCategory.class, mapper),
                HibernateSettingCategory.class,
                settingCategoryPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<StringIdKey, HibernateStringIdKey, SettingNode, HibernateSettingNode>
    settingNodeHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new DozerBeanTransformer<>(StringIdKey.class, HibernateStringIdKey.class, mapper),
                new DozerBeanTransformer<>(SettingNode.class, HibernateSettingNode.class, mapper),
                HibernateSettingNode.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<SettingNode, HibernateSettingNode> settingNodeHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                template,
                new DozerBeanTransformer<>(SettingNode.class, HibernateSettingNode.class, mapper),
                HibernateSettingNode.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<SettingNode, HibernateSettingNode> settingNodeHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                template,
                new DozerBeanTransformer<>(SettingNode.class, HibernateSettingNode.class, mapper),
                HibernateSettingNode.class,
                settingNodePresetCriteriaMaker
        );
    }
}
