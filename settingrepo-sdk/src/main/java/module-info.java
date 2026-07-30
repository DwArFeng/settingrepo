module com.dwarfeng.settingrepo.sdk {

    requires com.dwarfeng.settingrepo.base;
    requires com.dwarfeng.settingrepo.stack;
    requires com.dwarfeng.subgrade.basic;
    requires com.dwarfeng.subgrade.data;
    requires com.dwarfeng.subgrade.web;
    requires com.alibaba.fastjson2;
    requires jakarta.validation;
    requires org.apache.commons.lang3;
    requires org.hibernate.validator;
    requires org.mapstruct;
    requires org.slf4j;

    exports com.dwarfeng.settingrepo.sdk.bean;
    exports com.dwarfeng.settingrepo.sdk.bean.dto;
    exports com.dwarfeng.settingrepo.sdk.bean.entity;
    exports com.dwarfeng.settingrepo.sdk.bean.key;
    exports com.dwarfeng.settingrepo.sdk.bean.key.formatter;
    exports com.dwarfeng.settingrepo.sdk.exception;
    exports com.dwarfeng.settingrepo.sdk.handler;
    exports com.dwarfeng.settingrepo.sdk.handler.formatter;
    exports com.dwarfeng.settingrepo.sdk.handler.pusher;
    exports com.dwarfeng.settingrepo.sdk.handler.resetter;
    exports com.dwarfeng.settingrepo.sdk.util;

    opens com.dwarfeng.settingrepo.sdk.i18n to com.dwarfeng.settingrepo.base;
}
