package com.dwarfeng.settingrepo.impl.configuration;

import com.alibaba.fastjson.parser.ParserConfig;
import com.dwarfeng.settingrepo.sdk.bean.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FastJsonConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(FastJsonConfiguration.class);

    public FastJsonConfiguration() {
        LOGGER.info("正在配置 FastJson autotype 白名单");
        ParserConfig.getGlobalInstance().addAccept(FastJsonFormatterSupport.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonSettingCategory.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonSettingNode.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonTextNode.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonImageNode.class.getCanonicalName());
        LOGGER.debug("FastJson autotype 白名单配置完毕");
    }
}
