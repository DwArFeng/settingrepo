package com.dwarfeng.settingrepo.sdk.bean.key.formatter;

import com.dwarfeng.settingrepo.stack.bean.key.IahnNodeLocaleKey;
import com.dwarfeng.subgrade.sdk.common.Constants;
import com.dwarfeng.subgrade.sdk.redis.formatter.StringKeyFormatter;

import java.util.Objects;

/**
 * IahnNodeLocaleKey 的文本格式化转换器。
 *
 * @author mooyuan
 * @since 2.1.0
 */
public class IahnNodeLocaleStringKeyFormatter implements StringKeyFormatter<IahnNodeLocaleKey> {

    private String prefix;

    public IahnNodeLocaleStringKeyFormatter(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String format(IahnNodeLocaleKey key) {
        Objects.requireNonNull(key);
        return prefix + key.getNodeStringId() + "_" + key.getLanguage() + "_" + key.getCountry() + "_" +
                key.getVariant();
    }

    @Override
    public String generalFormat() {
        return prefix + Constants.REDIS_KEY_WILDCARD_CHARACTER;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String toString() {
        return "IahnNodeLocaleStringKeyFormatter{" +
                "prefix='" + prefix + '\'' +
                '}';
    }
}
