package com.dwarfeng.settingrepo.node.configuration;

import com.dwarfeng.settingrepo.sdk.bean.FastJsonMapper;
import com.dwarfeng.settingrepo.sdk.bean.entity.FastJsonFormatterSupport;
import com.dwarfeng.settingrepo.sdk.bean.entity.FastJsonSettingCategory;
import com.dwarfeng.settingrepo.sdk.bean.entity.FastJsonSettingNode;
import com.dwarfeng.settingrepo.sdk.bean.entity.FastJsonTextNode;
import com.dwarfeng.settingrepo.stack.bean.entity.FormatterSupport;
import com.dwarfeng.settingrepo.stack.bean.entity.SettingCategory;
import com.dwarfeng.settingrepo.stack.bean.entity.SettingNode;
import com.dwarfeng.settingrepo.stack.bean.entity.TextNode;
import com.dwarfeng.subgrade.impl.bean.MapStructBeanTransformer;
import com.dwarfeng.subgrade.impl.cache.RedisBatchBaseCache;
import com.dwarfeng.subgrade.sdk.redis.formatter.StringIdStringKeyFormatter;
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
}
