package com.dwarfeng.settingrepo.impl.handler.formatter;

import com.dwarfeng.dutil.basic.impl.io.StringOutputStream;
import com.dwarfeng.dutil.basic.sdk.io.IOUtil;
import com.dwarfeng.settingrepo.impl.internal.i18n.ImplMessageKey;
import com.dwarfeng.settingrepo.impl.internal.i18n.ImplMessages;
import com.dwarfeng.settingrepo.sdk.handler.formatter.AbstractFormatterRegistry;
import com.dwarfeng.settingrepo.stack.bean.entity.SettingCategory;
import com.dwarfeng.settingrepo.stack.exception.FormatterException;
import com.dwarfeng.settingrepo.stack.exception.FormatterExecutionException;
import com.dwarfeng.settingrepo.stack.exception.FormatterMakeException;
import com.dwarfeng.settingrepo.stack.handler.Formatter;
import com.dwarfeng.subgrade.basic.stack.bean.key.StringIdKey;
import groovy.lang.GroovyClassLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * Groovy 格式化器注册。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
@Component
public class GroovyFormatterRegistry extends AbstractFormatterRegistry {

    public static final String FORMATTER_TYPE = "groovy_formatter";

    private static final String EXAMPLE_PROCESSOR_RESOURCE_PATH =
            "/com/dwarfeng/settingrepo/impl/groovy/ExampleFormatterProcessor.groovy";

    private static final Logger LOGGER = LoggerFactory.getLogger(GroovyFormatterRegistry.class);

    private final ApplicationContext ctx;

    public GroovyFormatterRegistry(ApplicationContext ctx) {
        super(FORMATTER_TYPE);
        this.ctx = ctx;
    }

    @Override
    public String provideLabel() {
        return ImplMessages.message(ImplMessageKey.SPI_FORMATTER_GROOVY_FORMATTER_LABEL);
    }

    @Override
    public String provideDescription() {
        return ImplMessages.message(ImplMessageKey.SPI_FORMATTER_GROOVY_FORMATTER_DESCRIPTION);
    }

    @Override
    public String provideExampleContent() {
        try {
            String example;
            try (
                    InputStream sin = openExampleProcessorResource();
                    StringOutputStream sout = new StringOutputStream(StandardCharsets.UTF_8, true)
            ) {
                IOUtil.trans(sin, sout, 4096);
                sout.flush();
                example = sout.toString();
            }
            return example;
        } catch (Exception e) {
            LOGGER.warn(ImplMessages.message(ImplMessageKey.LOG_GROOVY_EXAMPLE_PROCESSOR_READ_FAILED), e);
            return "";
        }
    }

    private InputStream openExampleProcessorResource() throws IOException {
        InputStream inputStream = GroovyFormatterRegistry.class.getResourceAsStream(EXAMPLE_PROCESSOR_RESOURCE_PATH);
        if (inputStream == null) {
            throw new IOException("Resource not found: " + EXAMPLE_PROCESSOR_RESOURCE_PATH);
        }
        return inputStream;
    }

    @Override
    public Formatter makeFormatter(SettingCategory settingCategory) throws FormatterException {
        try (GroovyClassLoader classLoader = new GroovyClassLoader()) {
            // 通过 Groovy 脚本生成处理器。
            Class<?> aClass = classLoader.parseClass(settingCategory.getFormatterParam());
            Processor processor = (Processor) aClass.getDeclaredConstructor().newInstance();
            ctx.getAutowireCapableBeanFactory().autowireBean(processor);
            // 构建过滤器对象。
            return ctx.getBean(GroovyFormatter.class, settingCategory, processor);
        } catch (Exception e) {
            throw new FormatterMakeException(e);
        }
    }

    @Override
    public String toString() {
        return "GroovyFormatterRegistry{" +
                "ctx=" + ctx +
                ", formatterType='" + formatterType + '\'' +
                '}';
    }

    @Component
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public static class GroovyFormatter implements Formatter {

        private final SettingCategory settingCategory;
        private final Processor processor;

        public GroovyFormatter(SettingCategory settingCategory, Processor processor) {
            this.settingCategory = settingCategory;
            this.processor = processor;
        }

        @Override
        public StringIdKey format(String[] args) throws FormatterExecutionException {
            try {
                return processor.format(settingCategory, args);
            } catch (Exception e) {
                throw new FormatterExecutionException(e);
            }
        }

        @Override
        public String toString() {
            return "GroovyFormatter{" +
                    "settingCategory=" + settingCategory +
                    ", processor=" + processor +
                    '}';
        }
    }

    /**
     * Groovy 处理器。
     *
     * @author DwArFeng
     * @since 1.0.0
     */
    public interface Processor {

        /**
         * 执行格式化。
         *
         * @param settingCategory 格式化器的设置类别上下文。
         * @param args            格式化所需的参数组成的数组。
         * @return 参数格式化后形成的配置节点主键。
         * @throws Exception 格式化过程中出现的任何异常。
         */
        StringIdKey format(SettingCategory settingCategory, String[] args) throws Exception;
    }
}
