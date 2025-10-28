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
        ParserConfig.getGlobalInstance().addAccept(FastJsonImageListNode.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonImageListNodeItem.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonIahnNode.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonIahnNodeLocale.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonIahnNodeMek.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonIahnNodeMessage.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonLongTextNode.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonFileNode.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonFileListNode.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonFileListNodeItem.class.getCanonicalName());
        LOGGER.debug("FastJson autotype 白名单配置完毕");
    }
}
