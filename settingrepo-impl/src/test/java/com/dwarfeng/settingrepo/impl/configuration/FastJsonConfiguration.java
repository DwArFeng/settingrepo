package com.dwarfeng.settingrepo.impl.configuration;

import com.alibaba.fastjson2.JSONFactory;
import com.dwarfeng.settingrepo.sdk.bean.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FastJsonConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(FastJsonConfiguration.class);

    public FastJsonConfiguration() {
        LOGGER.info("正在配置 FastJson autotype 白名单");
        JSONFactory.getDefaultObjectReaderProvider().addAutoTypeAccept(FastJsonFormatterSupport.class.getCanonicalName());
        JSONFactory.getDefaultObjectReaderProvider().addAutoTypeAccept(FastJsonSettingCategory.class.getCanonicalName());
        JSONFactory.getDefaultObjectReaderProvider().addAutoTypeAccept(FastJsonSettingNode.class.getCanonicalName());
        JSONFactory.getDefaultObjectReaderProvider().addAutoTypeAccept(FastJsonTextNode.class.getCanonicalName());
        JSONFactory.getDefaultObjectReaderProvider().addAutoTypeAccept(FastJsonImageNode.class.getCanonicalName());
        JSONFactory.getDefaultObjectReaderProvider().addAutoTypeAccept(FastJsonImageListNode.class.getCanonicalName());
        JSONFactory.getDefaultObjectReaderProvider().addAutoTypeAccept(FastJsonImageListNodeItem.class.getCanonicalName());
        JSONFactory.getDefaultObjectReaderProvider().addAutoTypeAccept(FastJsonIahnNode.class.getCanonicalName());
        JSONFactory.getDefaultObjectReaderProvider().addAutoTypeAccept(FastJsonIahnNodeLocale.class.getCanonicalName());
        JSONFactory.getDefaultObjectReaderProvider().addAutoTypeAccept(FastJsonIahnNodeMek.class.getCanonicalName());
        JSONFactory.getDefaultObjectReaderProvider().addAutoTypeAccept(FastJsonIahnNodeMessage.class.getCanonicalName());
        JSONFactory.getDefaultObjectReaderProvider().addAutoTypeAccept(FastJsonLongTextNode.class.getCanonicalName());
        JSONFactory.getDefaultObjectReaderProvider().addAutoTypeAccept(FastJsonFileNode.class.getCanonicalName());
        JSONFactory.getDefaultObjectReaderProvider().addAutoTypeAccept(FastJsonFileListNode.class.getCanonicalName());
        JSONFactory.getDefaultObjectReaderProvider().addAutoTypeAccept(FastJsonFileListNodeItem.class.getCanonicalName());
        JSONFactory.getDefaultObjectReaderProvider().addAutoTypeAccept(FastJsonNavigationNode.class.getCanonicalName());
        JSONFactory.getDefaultObjectReaderProvider().addAutoTypeAccept(FastJsonNavigationNodeItem.class.getCanonicalName());
        JSONFactory.getDefaultObjectReaderProvider().addAutoTypeAccept(FastJsonKvNode.class.getCanonicalName());
        JSONFactory.getDefaultObjectReaderProvider().addAutoTypeAccept(FastJsonKvNodeItem.class.getCanonicalName());
        LOGGER.debug("FastJson autotype 白名单配置完毕");
    }
}
