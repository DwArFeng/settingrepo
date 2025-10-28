package com.dwarfeng.settingrepo.node.configuration;

import com.dwarfeng.settingrepo.impl.bean.BeanMapper;
import com.dwarfeng.settingrepo.impl.bean.entity.*;
import com.dwarfeng.settingrepo.impl.bean.key.HibernateIahnNodeLocaleKey;
import com.dwarfeng.settingrepo.impl.bean.key.HibernateIahnNodeMekKey;
import com.dwarfeng.settingrepo.impl.bean.key.HibernateIahnNodeMessageKey;
import com.dwarfeng.settingrepo.impl.dao.preset.*;
import com.dwarfeng.settingrepo.stack.bean.entity.*;
import com.dwarfeng.settingrepo.stack.bean.key.IahnNodeLocaleKey;
import com.dwarfeng.settingrepo.stack.bean.key.IahnNodeMekKey;
import com.dwarfeng.settingrepo.stack.bean.key.IahnNodeMessageKey;
import com.dwarfeng.subgrade.impl.bean.MapStructBeanTransformer;
import com.dwarfeng.subgrade.impl.dao.HibernateBatchBaseDao;
import com.dwarfeng.subgrade.impl.dao.HibernateEntireLookupDao;
import com.dwarfeng.subgrade.impl.dao.HibernatePresetLookupDao;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateLongIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateStringIdKey;
import com.dwarfeng.subgrade.sdk.hibernate.modification.DefaultDeletionMod;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
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
    private final ImageNodePresetCriteriaMaker imageNodePresetCriteriaMaker;
    private final ImageListNodePresetCriteriaMaker imageListNodePresetCriteriaMaker;
    private final ImageListNodeItemPresetCriteriaMaker imageListNodeItemPresetCriteriaMaker;
    private final IahnNodePresetCriteriaMaker iahnNodePresetCriteriaMaker;
    private final IahnNodeLocalePresetCriteriaMaker iahnNodeLocalePresetCriteriaMaker;
    private final IahnNodeMekPresetCriteriaMaker iahnNodeMekPresetCriteriaMaker;
    private final IahnNodeMessagePresetCriteriaMaker iahnNodeMessagePresetCriteriaMaker;
    private final LongTextNodePresetCriteriaMaker longTextNodePresetCriteriaMaker;
    private final FileNodePresetCriteriaMaker fileNodePresetCriteriaMaker;

    @Value("${hibernate.jdbc.batch_size}")
    private int batchSize;

    public DaoConfiguration(
            HibernateTemplate template,
            FormatterSupportPresetCriteriaMaker formatterSupportPresetCriteriaMaker,
            SettingCategoryPresetCriteriaMaker settingCategoryPresetCriteriaMaker,
            SettingNodePresetCriteriaMaker settingNodePresetCriteriaMaker,
            TextNodePresetCriteriaMaker textNodePresetCriteriaMaker,
            ImageNodePresetCriteriaMaker imageNodePresetCriteriaMaker,
            ImageListNodePresetCriteriaMaker imageListNodePresetCriteriaMaker,
            ImageListNodeItemPresetCriteriaMaker imageListNodeItemPresetCriteriaMaker,
            IahnNodePresetCriteriaMaker iahnNodePresetCriteriaMaker,
            IahnNodeLocalePresetCriteriaMaker iahnNodeLocalePresetCriteriaMaker,
            IahnNodeMekPresetCriteriaMaker iahnNodeMekPresetCriteriaMaker,
            IahnNodeMessagePresetCriteriaMaker iahnNodeMessagePresetCriteriaMaker,
            LongTextNodePresetCriteriaMaker longTextNodePresetCriteriaMaker,
            FileNodePresetCriteriaMaker fileNodePresetCriteriaMaker
    ) {
        this.template = template;
        this.formatterSupportPresetCriteriaMaker = formatterSupportPresetCriteriaMaker;
        this.settingCategoryPresetCriteriaMaker = settingCategoryPresetCriteriaMaker;
        this.settingNodePresetCriteriaMaker = settingNodePresetCriteriaMaker;
        this.textNodePresetCriteriaMaker = textNodePresetCriteriaMaker;
        this.imageNodePresetCriteriaMaker = imageNodePresetCriteriaMaker;
        this.imageListNodePresetCriteriaMaker = imageListNodePresetCriteriaMaker;
        this.imageListNodeItemPresetCriteriaMaker = imageListNodeItemPresetCriteriaMaker;
        this.iahnNodePresetCriteriaMaker = iahnNodePresetCriteriaMaker;
        this.iahnNodeLocalePresetCriteriaMaker = iahnNodeLocalePresetCriteriaMaker;
        this.iahnNodeMekPresetCriteriaMaker = iahnNodeMekPresetCriteriaMaker;
        this.iahnNodeMessagePresetCriteriaMaker = iahnNodeMessagePresetCriteriaMaker;
        this.longTextNodePresetCriteriaMaker = longTextNodePresetCriteriaMaker;
        this.fileNodePresetCriteriaMaker = fileNodePresetCriteriaMaker;
    }

    @Bean
    public HibernateBatchBaseDao<StringIdKey, HibernateStringIdKey, FormatterSupport, HibernateFormatterSupport>
    formatterSupportHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new MapStructBeanTransformer<>(StringIdKey.class, HibernateStringIdKey.class, BeanMapper.class),
                new MapStructBeanTransformer<>(
                        FormatterSupport.class, HibernateFormatterSupport.class, BeanMapper.class
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
                        FormatterSupport.class, HibernateFormatterSupport.class, BeanMapper.class
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
                        FormatterSupport.class, HibernateFormatterSupport.class, BeanMapper.class
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
                new MapStructBeanTransformer<>(StringIdKey.class, HibernateStringIdKey.class, BeanMapper.class),
                new MapStructBeanTransformer<>(
                        SettingCategory.class, HibernateSettingCategory.class, BeanMapper.class
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
                        SettingCategory.class, HibernateSettingCategory.class, BeanMapper.class
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
                        SettingCategory.class, HibernateSettingCategory.class, BeanMapper.class
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
                new MapStructBeanTransformer<>(StringIdKey.class, HibernateStringIdKey.class, BeanMapper.class),
                new MapStructBeanTransformer<>(SettingNode.class, HibernateSettingNode.class, BeanMapper.class),
                HibernateSettingNode.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<SettingNode, HibernateSettingNode> settingNodeHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                template,
                new MapStructBeanTransformer<>(SettingNode.class, HibernateSettingNode.class, BeanMapper.class),
                HibernateSettingNode.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<SettingNode, HibernateSettingNode> settingNodeHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                template,
                new MapStructBeanTransformer<>(SettingNode.class, HibernateSettingNode.class, BeanMapper.class),
                HibernateSettingNode.class,
                settingNodePresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<StringIdKey, HibernateStringIdKey, TextNode, HibernateTextNode>
    textNodeHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new MapStructBeanTransformer<>(StringIdKey.class, HibernateStringIdKey.class, BeanMapper.class),
                new MapStructBeanTransformer<>(TextNode.class, HibernateTextNode.class, BeanMapper.class),
                HibernateTextNode.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<TextNode, HibernateTextNode> textNodeHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                template,
                new MapStructBeanTransformer<>(TextNode.class, HibernateTextNode.class, BeanMapper.class),
                HibernateTextNode.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<TextNode, HibernateTextNode> textNodeHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                template,
                new MapStructBeanTransformer<>(TextNode.class, HibernateTextNode.class, BeanMapper.class),
                HibernateTextNode.class,
                textNodePresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<StringIdKey, HibernateStringIdKey, ImageNode, HibernateImageNode>
    imageNodeHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new MapStructBeanTransformer<>(StringIdKey.class, HibernateStringIdKey.class, BeanMapper.class),
                new MapStructBeanTransformer<>(ImageNode.class, HibernateImageNode.class, BeanMapper.class),
                HibernateImageNode.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<ImageNode, HibernateImageNode> imageNodeHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                template,
                new MapStructBeanTransformer<>(ImageNode.class, HibernateImageNode.class, BeanMapper.class),
                HibernateImageNode.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<ImageNode, HibernateImageNode> imageNodeHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                template,
                new MapStructBeanTransformer<>(ImageNode.class, HibernateImageNode.class, BeanMapper.class),
                HibernateImageNode.class,
                imageNodePresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<StringIdKey, HibernateStringIdKey, ImageListNode, HibernateImageListNode>
    imageListNodeHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new MapStructBeanTransformer<>(StringIdKey.class, HibernateStringIdKey.class, BeanMapper.class),
                new MapStructBeanTransformer<>(
                        ImageListNode.class, HibernateImageListNode.class, BeanMapper.class
                ),
                HibernateImageListNode.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<ImageListNode, HibernateImageListNode> imageListNodeHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                template,
                new MapStructBeanTransformer<>(
                        ImageListNode.class, HibernateImageListNode.class, BeanMapper.class
                ),
                HibernateImageListNode.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<ImageListNode, HibernateImageListNode> imageListNodeHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                template,
                new MapStructBeanTransformer<>(
                        ImageListNode.class, HibernateImageListNode.class, BeanMapper.class
                ),
                HibernateImageListNode.class,
                imageListNodePresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<LongIdKey, HibernateLongIdKey, ImageListNodeItem, HibernateImageListNodeItem>
    imageListNodeItemHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new MapStructBeanTransformer<>(LongIdKey.class, HibernateLongIdKey.class, BeanMapper.class),
                new MapStructBeanTransformer<>(
                        ImageListNodeItem.class, HibernateImageListNodeItem.class, BeanMapper.class
                ),
                HibernateImageListNodeItem.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<ImageListNodeItem, HibernateImageListNodeItem>
    imageListNodeItemHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                template,
                new MapStructBeanTransformer<>(
                        ImageListNodeItem.class, HibernateImageListNodeItem.class, BeanMapper.class
                ),
                HibernateImageListNodeItem.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<ImageListNodeItem, HibernateImageListNodeItem>
    imageListNodeItemHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                template,
                new MapStructBeanTransformer<>(
                        ImageListNodeItem.class, HibernateImageListNodeItem.class, BeanMapper.class
                ),
                HibernateImageListNodeItem.class,
                imageListNodeItemPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<StringIdKey, HibernateStringIdKey, IahnNode, HibernateIahnNode>
    iahnNodeHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new MapStructBeanTransformer<>(StringIdKey.class, HibernateStringIdKey.class, BeanMapper.class),
                new MapStructBeanTransformer<>(IahnNode.class, HibernateIahnNode.class, BeanMapper.class),
                HibernateIahnNode.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<IahnNode, HibernateIahnNode> iahnNodeHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                template,
                new MapStructBeanTransformer<>(IahnNode.class, HibernateIahnNode.class, BeanMapper.class),
                HibernateIahnNode.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<IahnNode, HibernateIahnNode> iahnNodeHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                template,
                new MapStructBeanTransformer<>(IahnNode.class, HibernateIahnNode.class, BeanMapper.class),
                HibernateIahnNode.class,
                iahnNodePresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<IahnNodeLocaleKey, HibernateIahnNodeLocaleKey, IahnNodeLocale, HibernateIahnNodeLocale>
    iahnNodeLocaleHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new MapStructBeanTransformer<>(
                        IahnNodeLocaleKey.class, HibernateIahnNodeLocaleKey.class, BeanMapper.class
                ),
                new MapStructBeanTransformer<>(
                        IahnNodeLocale.class, HibernateIahnNodeLocale.class, BeanMapper.class
                ),
                HibernateIahnNodeLocale.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<IahnNodeLocale, HibernateIahnNodeLocale>
    iahnNodeLocaleHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                template,
                new MapStructBeanTransformer<>(
                        IahnNodeLocale.class, HibernateIahnNodeLocale.class, BeanMapper.class
                ),
                HibernateIahnNodeLocale.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<IahnNodeLocale, HibernateIahnNodeLocale>
    iahnNodeLocaleHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                template,
                new MapStructBeanTransformer<>(
                        IahnNodeLocale.class, HibernateIahnNodeLocale.class, BeanMapper.class
                ),
                HibernateIahnNodeLocale.class,
                iahnNodeLocalePresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<IahnNodeMekKey, HibernateIahnNodeMekKey, IahnNodeMek, HibernateIahnNodeMek>
    iahnNodeMekHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new MapStructBeanTransformer<>(
                        IahnNodeMekKey.class, HibernateIahnNodeMekKey.class, BeanMapper.class
                ),
                new MapStructBeanTransformer<>(
                        IahnNodeMek.class, HibernateIahnNodeMek.class, BeanMapper.class
                ),
                HibernateIahnNodeMek.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<IahnNodeMek, HibernateIahnNodeMek>
    iahnNodeMekHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                template,
                new MapStructBeanTransformer<>(
                        IahnNodeMek.class, HibernateIahnNodeMek.class, BeanMapper.class
                ),
                HibernateIahnNodeMek.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<IahnNodeMek, HibernateIahnNodeMek>
    iahnNodeMekHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                template,
                new MapStructBeanTransformer<>(
                        IahnNodeMek.class, HibernateIahnNodeMek.class, BeanMapper.class
                ),
                HibernateIahnNodeMek.class,
                iahnNodeMekPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<IahnNodeMessageKey, HibernateIahnNodeMessageKey, IahnNodeMessage,
            HibernateIahnNodeMessage> iahnNodeMessageHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new MapStructBeanTransformer<>(
                        IahnNodeMessageKey.class, HibernateIahnNodeMessageKey.class, BeanMapper.class
                ),
                new MapStructBeanTransformer<>(
                        IahnNodeMessage.class, HibernateIahnNodeMessage.class, BeanMapper.class
                ),
                HibernateIahnNodeMessage.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<IahnNodeMessage, HibernateIahnNodeMessage>
    iahnNodeMessageHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                template,
                new MapStructBeanTransformer<>(
                        IahnNodeMessage.class, HibernateIahnNodeMessage.class, BeanMapper.class
                ),
                HibernateIahnNodeMessage.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<IahnNodeMessage, HibernateIahnNodeMessage>
    iahnNodeMessageHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                template,
                new MapStructBeanTransformer<>(
                        IahnNodeMessage.class, HibernateIahnNodeMessage.class, BeanMapper.class
                ),
                HibernateIahnNodeMessage.class,
                iahnNodeMessagePresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<StringIdKey, HibernateStringIdKey, LongTextNode, HibernateLongTextNode>
    longTextNodeHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new MapStructBeanTransformer<>(StringIdKey.class, HibernateStringIdKey.class, BeanMapper.class),
                new MapStructBeanTransformer<>(LongTextNode.class, HibernateLongTextNode.class, BeanMapper.class),
                HibernateLongTextNode.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<LongTextNode, HibernateLongTextNode> longTextNodeHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                template,
                new MapStructBeanTransformer<>(LongTextNode.class, HibernateLongTextNode.class, BeanMapper.class),
                HibernateLongTextNode.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<LongTextNode, HibernateLongTextNode> longTextNodeHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                template,
                new MapStructBeanTransformer<>(LongTextNode.class, HibernateLongTextNode.class, BeanMapper.class),
                HibernateLongTextNode.class,
                longTextNodePresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<StringIdKey, HibernateStringIdKey, FileNode, HibernateFileNode>
    fileNodeHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new MapStructBeanTransformer<>(StringIdKey.class, HibernateStringIdKey.class, BeanMapper.class),
                new MapStructBeanTransformer<>(FileNode.class, HibernateFileNode.class, BeanMapper.class),
                HibernateFileNode.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<FileNode, HibernateFileNode> fileNodeHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                template,
                new MapStructBeanTransformer<>(FileNode.class, HibernateFileNode.class, BeanMapper.class),
                HibernateFileNode.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<FileNode, HibernateFileNode> fileNodeHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                template,
                new MapStructBeanTransformer<>(FileNode.class, HibernateFileNode.class, BeanMapper.class),
                HibernateFileNode.class,
                fileNodePresetCriteriaMaker
        );
    }
}
