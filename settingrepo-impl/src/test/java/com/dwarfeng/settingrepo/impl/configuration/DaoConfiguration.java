package com.dwarfeng.settingrepo.impl.configuration;

import com.dwarfeng.settingrepo.impl.bean.HibernateMapper;
import com.dwarfeng.settingrepo.impl.bean.entity.HibernateFormatterSupport;
import com.dwarfeng.settingrepo.impl.bean.entity.HibernateSettingCategory;
import com.dwarfeng.settingrepo.impl.bean.entity.HibernateSettingNode;
import com.dwarfeng.settingrepo.impl.bean.entity.HibernateTextNode;
import com.dwarfeng.settingrepo.impl.dao.preset.FormatterSupportPresetCriteriaMaker;
import com.dwarfeng.settingrepo.impl.dao.preset.SettingCategoryPresetCriteriaMaker;
import com.dwarfeng.settingrepo.impl.dao.preset.SettingNodePresetCriteriaMaker;
import com.dwarfeng.settingrepo.impl.dao.preset.TextNodePresetCriteriaMaker;
import com.dwarfeng.settingrepo.stack.bean.entity.FormatterSupport;
import com.dwarfeng.settingrepo.stack.bean.entity.SettingCategory;
import com.dwarfeng.settingrepo.stack.bean.entity.SettingNode;
import com.dwarfeng.settingrepo.stack.bean.entity.TextNode;
import com.dwarfeng.subgrade.impl.bean.MapStructBeanTransformer;
import com.dwarfeng.subgrade.impl.dao.HibernateBatchBaseDao;
import com.dwarfeng.subgrade.impl.dao.HibernateEntireLookupDao;
import com.dwarfeng.subgrade.impl.dao.HibernatePresetLookupDao;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateStringIdKey;
import com.dwarfeng.subgrade.sdk.hibernate.modification.DefaultDeletionMod;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTemplate;

@Configuration
public class DaoConfiguration {

    private final HibernateTemplate template;

    private final FormatterSupportPresetCriteriaMaker formatterSupportPresetCriteriaMaker;
    private final SettingCategoryPresetCriteriaMaker settingCategoryPresetCriteriaMaker;
    private final SettingNodePresetCriteriaMaker settingNodePresetCriteriaMaker;
    private final TextNodePresetCriteriaMaker textNodePresetCriteriaMaker;

    @Value("${hibernate.jdbc.batch_size}")
    private int batchSize;

    public DaoConfiguration(
            HibernateTemplate template,
            FormatterSupportPresetCriteriaMaker formatterSupportPresetCriteriaMaker,
            SettingCategoryPresetCriteriaMaker settingCategoryPresetCriteriaMaker,
            SettingNodePresetCriteriaMaker settingNodePresetCriteriaMaker,
            TextNodePresetCriteriaMaker textNodePresetCriteriaMaker
    ) {
        this.template = template;
        this.formatterSupportPresetCriteriaMaker = formatterSupportPresetCriteriaMaker;
        this.settingCategoryPresetCriteriaMaker = settingCategoryPresetCriteriaMaker;
        this.settingNodePresetCriteriaMaker = settingNodePresetCriteriaMaker;
        this.textNodePresetCriteriaMaker = textNodePresetCriteriaMaker;
    }

    @Bean
    public HibernateBatchBaseDao<StringIdKey, HibernateStringIdKey, FormatterSupport, HibernateFormatterSupport>
    formatterSupportHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new MapStructBeanTransformer<>(StringIdKey.class, HibernateStringIdKey.class, HibernateMapper.class),
                new MapStructBeanTransformer<>(
                        FormatterSupport.class, HibernateFormatterSupport.class, HibernateMapper.class
                ),
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
                new MapStructBeanTransformer<>(
                        FormatterSupport.class, HibernateFormatterSupport.class, HibernateMapper.class
                ),
                HibernateFormatterSupport.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<FormatterSupport, HibernateFormatterSupport>
    formatterSupportHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                template,
                new MapStructBeanTransformer<>(
                        FormatterSupport.class, HibernateFormatterSupport.class, HibernateMapper.class
                ),
                HibernateFormatterSupport.class,
                formatterSupportPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<StringIdKey, HibernateStringIdKey, SettingCategory, HibernateSettingCategory>
    settingCategoryHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new MapStructBeanTransformer<>(StringIdKey.class, HibernateStringIdKey.class, HibernateMapper.class),
                new MapStructBeanTransformer<>(
                        SettingCategory.class, HibernateSettingCategory.class, HibernateMapper.class
                ),
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
                new MapStructBeanTransformer<>(
                        SettingCategory.class, HibernateSettingCategory.class, HibernateMapper.class
                ),
                HibernateSettingCategory.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<SettingCategory, HibernateSettingCategory>
    settingCategoryHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                template,
                new MapStructBeanTransformer<>(
                        SettingCategory.class, HibernateSettingCategory.class, HibernateMapper.class
                ),
                HibernateSettingCategory.class,
                settingCategoryPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<StringIdKey, HibernateStringIdKey, SettingNode, HibernateSettingNode>
    settingNodeHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new MapStructBeanTransformer<>(StringIdKey.class, HibernateStringIdKey.class, HibernateMapper.class),
                new MapStructBeanTransformer<>(SettingNode.class, HibernateSettingNode.class, HibernateMapper.class),
                HibernateSettingNode.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<SettingNode, HibernateSettingNode> settingNodeHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                template,
                new MapStructBeanTransformer<>(SettingNode.class, HibernateSettingNode.class, HibernateMapper.class),
                HibernateSettingNode.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<SettingNode, HibernateSettingNode> settingNodeHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                template,
                new MapStructBeanTransformer<>(SettingNode.class, HibernateSettingNode.class, HibernateMapper.class),
                HibernateSettingNode.class,
                settingNodePresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<StringIdKey, HibernateStringIdKey, TextNode, HibernateTextNode>
    textNodeHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new MapStructBeanTransformer<>(StringIdKey.class, HibernateStringIdKey.class, HibernateMapper.class),
                new MapStructBeanTransformer<>(TextNode.class, HibernateTextNode.class, HibernateMapper.class),
                HibernateTextNode.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<TextNode, HibernateTextNode> textNodeHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                template,
                new MapStructBeanTransformer<>(TextNode.class, HibernateTextNode.class, HibernateMapper.class),
                HibernateTextNode.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<TextNode, HibernateTextNode> textNodeHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                template,
                new MapStructBeanTransformer<>(TextNode.class, HibernateTextNode.class, HibernateMapper.class),
                HibernateTextNode.class,
                textNodePresetCriteriaMaker
        );
    }
}
