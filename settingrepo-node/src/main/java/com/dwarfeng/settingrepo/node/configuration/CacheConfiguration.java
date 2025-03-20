package com.dwarfeng.settingrepo.node.configuration;

import com.dwarfeng.settingrepo.sdk.bean.FastJsonMapper;
import com.dwarfeng.settingrepo.sdk.bean.entity.*;
import com.dwarfeng.settingrepo.sdk.bean.key.formatter.IahnNodeLocaleStringKeyFormatter;
import com.dwarfeng.settingrepo.sdk.bean.key.formatter.IahnNodeMekStringKeyFormatter;
import com.dwarfeng.settingrepo.sdk.bean.key.formatter.IahnNodeMessageStringKeyFormatter;
import com.dwarfeng.settingrepo.stack.bean.entity.*;
import com.dwarfeng.settingrepo.stack.bean.key.IahnNodeLocaleKey;
import com.dwarfeng.settingrepo.stack.bean.key.IahnNodeMekKey;
import com.dwarfeng.settingrepo.stack.bean.key.IahnNodeMessageKey;
import com.dwarfeng.subgrade.impl.bean.MapStructBeanTransformer;
import com.dwarfeng.subgrade.impl.cache.RedisBatchBaseCache;
import com.dwarfeng.subgrade.sdk.redis.formatter.LongIdStringKeyFormatter;
import com.dwarfeng.subgrade.sdk.redis.formatter.StringIdStringKeyFormatter;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class CacheConfiguration {

    private final RedisTemplate<String, ?> template;

    @Value("${cache.prefix.entity.formatter_support}")
    private String formatterSupportPrefix;
    @Value("${cache.prefix.entity.setting_category}")
    private String settingCategoryPrefix;
    @Value("${cache.prefix.entity.setting_node}")
    private String settingNodePrefix;
    @Value("${cache.prefix.entity.text_node}")
    private String textNodePrefix;
    @Value("${cache.prefix.entity.image_node}")
    private String imageNodePrefix;
    @Value("${cache.prefix.entity.image_list_node}")
    private String imageListNodePrefix;
    @Value("${cache.prefix.entity.image_list_node_item}")
    private String imageListNodeItemPrefix;
    @Value("${cache.prefix.entity.iahn_node}")
    private String iahnNodePrefix;
    @Value("${cache.prefix.entity.iahn_node_locale}")
    private String iahnNodeLocalePrefix;
    @Value("${cache.prefix.entity.iahn_node_mek}")
    private String iahnNodeMekPrefix;
    @Value("${cache.prefix.entity.iahn_node_message}")
    private String iahnNodeMessagePrefix;
    @Value("${cache.prefix.entity.long_text_node}")
    private String longTextNodePrefix;

    public CacheConfiguration(RedisTemplate<String, ?> template) {
        this.template = template;
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<StringIdKey, FormatterSupport, FastJsonFormatterSupport>
    formatterSupportRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonFormatterSupport>) template,
                new StringIdStringKeyFormatter(formatterSupportPrefix),
                new MapStructBeanTransformer<>(
                        FormatterSupport.class, FastJsonFormatterSupport.class, FastJsonMapper.class
                )
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<StringIdKey, SettingCategory, FastJsonSettingCategory>
    settingCategoryRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonSettingCategory>) template,
                new StringIdStringKeyFormatter(settingCategoryPrefix),
                new MapStructBeanTransformer<>(
                        SettingCategory.class, FastJsonSettingCategory.class, FastJsonMapper.class
                )
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<StringIdKey, SettingNode, FastJsonSettingNode> settingNodeRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonSettingNode>) template,
                new StringIdStringKeyFormatter(settingNodePrefix),
                new MapStructBeanTransformer<>(
                        SettingNode.class, FastJsonSettingNode.class, FastJsonMapper.class
                )
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<StringIdKey, TextNode, FastJsonTextNode> textNodeRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonTextNode>) template,
                new StringIdStringKeyFormatter(textNodePrefix),
                new MapStructBeanTransformer<>(
                        TextNode.class, FastJsonTextNode.class, FastJsonMapper.class
                )
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<StringIdKey, ImageNode, FastJsonImageNode> imageNodeRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonImageNode>) template,
                new StringIdStringKeyFormatter(imageNodePrefix),
                new MapStructBeanTransformer<>(
                        ImageNode.class, FastJsonImageNode.class, FastJsonMapper.class
                )
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<StringIdKey, ImageListNode, FastJsonImageListNode> imageListNodeRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonImageListNode>) template,
                new StringIdStringKeyFormatter(imageListNodePrefix),
                new MapStructBeanTransformer<>(
                        ImageListNode.class, FastJsonImageListNode.class, FastJsonMapper.class
                )
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<LongIdKey, ImageListNodeItem, FastJsonImageListNodeItem>
    imageListNodeItemRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonImageListNodeItem>) template,
                new LongIdStringKeyFormatter(imageListNodeItemPrefix),
                new MapStructBeanTransformer<>(
                        ImageListNodeItem.class, FastJsonImageListNodeItem.class, FastJsonMapper.class
                )
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<StringIdKey, IahnNode, FastJsonIahnNode> iahnNodeRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonIahnNode>) template,
                new StringIdStringKeyFormatter(iahnNodePrefix),
                new MapStructBeanTransformer<>(
                        IahnNode.class, FastJsonIahnNode.class, FastJsonMapper.class
                )
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<IahnNodeLocaleKey, IahnNodeLocale, FastJsonIahnNodeLocale>
    iahnNodeLocaleRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonIahnNodeLocale>) template,
                new IahnNodeLocaleStringKeyFormatter(iahnNodeLocalePrefix),
                new MapStructBeanTransformer<>(
                        IahnNodeLocale.class, FastJsonIahnNodeLocale.class, FastJsonMapper.class
                )
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<IahnNodeMekKey, IahnNodeMek, FastJsonIahnNodeMek>
    iahnNodeMekRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonIahnNodeMek>) template,
                new IahnNodeMekStringKeyFormatter(iahnNodeMekPrefix),
                new MapStructBeanTransformer<>(
                        IahnNodeMek.class, FastJsonIahnNodeMek.class, FastJsonMapper.class
                )
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<IahnNodeMessageKey, IahnNodeMessage, FastJsonIahnNodeMessage>
    iahnNodeMessageRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonIahnNodeMessage>) template,
                new IahnNodeMessageStringKeyFormatter(iahnNodeMessagePrefix),
                new MapStructBeanTransformer<>(
                        IahnNodeMessage.class, FastJsonIahnNodeMessage.class, FastJsonMapper.class
                )
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<StringIdKey, LongTextNode, FastJsonLongTextNode> longTextNodeRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonLongTextNode>) template,
                new StringIdStringKeyFormatter(longTextNodePrefix),
                new MapStructBeanTransformer<>(
                        LongTextNode.class, FastJsonLongTextNode.class, FastJsonMapper.class
                )
        );
    }
}
