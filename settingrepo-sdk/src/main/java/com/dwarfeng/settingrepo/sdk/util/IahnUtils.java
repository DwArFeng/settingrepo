package com.dwarfeng.settingrepo.sdk.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * 国际化工具类。
 *
 * @author DwArFeng
 * @since 2.1.0
 */
public final class IahnUtils {

    /**
     * 预处理 RFC 4546 字段。
     *
     * <p>
     *
     * <pre>
     * IahnUtils.preProcessRfc4546Field(null)  = ""
     * IahnUtils.preProcessRfc4546Field("")    = ""
     * IahnUtils.preProcessRfc4546Field("aBc") = "abc"
     * IahnUtils.preProcessRfc4546Field("  aBc  ") = "abc"
     * IahnUtils.preProcessRfc4546Field("abc") = "abc"
     * </pre>
     *
     * @param value 待预处理的 RFC 4546 字段。
     * @return 处理后的 RFC 4546 字段。
     */
    // 为了代码的可扩展性，此处不做简化。
    @SuppressWarnings("JavaExistingMethodCanBeUsed")
    public static String preProcessRfc4546Field(String value) {
        if (Objects.isNull(value)) {
            return StringUtils.EMPTY;
        }
        value = StringUtils.trim(value);
        value = StringUtils.lowerCase(value);
        return value;
    }

    /**
     * 预处理 Mek ID。
     *
     * <p>
     *
     * <pre>
     * IahnUtils.preProcessMekId(null)  = null
     * IahnUtils.preProcessMekId("")    = ""
     * IahnUtils.preProcessMekId("aBc") = "abc"
     * IahnUtils.preProcessMekId("  aBc  ") = "abc"
     * IahnUtils.preProcessMekId("abc") = "abc"
     * </pre>
     *
     * @param value 待预处理的 Mek ID。
     * @return 处理后的 Mek ID。
     */
    public static String preProcessMekId(String value) {
        value = StringUtils.trim(value);
        value = StringUtils.lowerCase(value);
        return value;
    }

    private IahnUtils() {
        throw new IllegalStateException("禁止实例化");
    }
}
