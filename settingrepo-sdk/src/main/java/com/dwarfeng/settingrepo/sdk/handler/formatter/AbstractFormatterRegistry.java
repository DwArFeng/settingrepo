package com.dwarfeng.settingrepo.sdk.handler.formatter;

import com.dwarfeng.settingrepo.sdk.handler.FormatterMaker;
import com.dwarfeng.settingrepo.sdk.handler.FormatterSupporter;

import java.util.Objects;

/**
 * 抽象格式化器注册。
 *
 * @author DwArFeng
 * @since 2.3.0
 */
public abstract class AbstractFormatterRegistry implements FormatterMaker, FormatterSupporter {

    protected String formatterType;

    public AbstractFormatterRegistry() {
    }

    public AbstractFormatterRegistry(String formatterType) {
        this.formatterType = formatterType;
    }

    @Override
    public boolean supportType(String type) {
        return Objects.equals(formatterType, type);
    }

    @Override
    public String provideType() {
        return formatterType;
    }

    public String getFormatterType() {
        return formatterType;
    }

    public void setFormatterType(String formatterType) {
        this.formatterType = formatterType;
    }

    @Override
    public String toString() {
        return "AbstractFormatterRegistry{" +
                "formatterType='" + formatterType + '\'' +
                '}';
    }
}
