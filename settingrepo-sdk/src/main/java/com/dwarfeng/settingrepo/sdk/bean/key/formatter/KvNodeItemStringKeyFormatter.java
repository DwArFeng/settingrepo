package com.dwarfeng.settingrepo.sdk.bean.key.formatter;

import com.dwarfeng.settingrepo.stack.bean.key.KvNodeItemKey;
import com.dwarfeng.subgrade.data.sdk.redis.formatter.StringKeyFormatter;

import java.util.Objects;

/**
 * KvNodeItemKey 的文本格式化转换器。
 *
 * @author wangxy
 * @since 2.5.0
 */
public class KvNodeItemStringKeyFormatter implements StringKeyFormatter<KvNodeItemKey> {

    private String prefix;

    public KvNodeItemStringKeyFormatter(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String format(KvNodeItemKey key) {
        Objects.requireNonNull(key);
        return prefix + key.getNodeStringId() + "_" + key.getItemStringId();
    }

    @Override
    public String generalFormat() {
        return prefix + StringKeyFormatter.REDIS_KEY_WILDCARD_CHARACTER;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String toString() {
        return "KvNodeItemStringKeyFormatter{" +
                "prefix='" + prefix + '\'' +
                '}';
    }
}
