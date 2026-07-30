module com.dwarfeng.settingrepo.node.all.he {

    requires com.dwarfeng.settingrepo.base;
    requires com.dwarfeng.settingrepo.stack;
    requires com.dwarfeng.settingrepo.sdk;
    requires com.dwarfeng.settingrepo.impl;
    requires com.alibaba.fastjson2;
    requires com.fasterxml.jackson.databind;
    requires com.dwarfeng.datamark.core;
    requires com.dwarfeng.datamark.api;
    requires com.dwarfeng.ftp.core;
    requires com.dwarfeng.ftp.api;
    requires com.dwarfeng.sfds.stack;
    requires com.dwarfeng.sfds.api;
    requires com.dwarfeng.springtelqos.core;
    requires com.dwarfeng.springtelqos.api;
    requires com.dwarfeng.springterminator.core;
    requires com.dwarfeng.springterminator.api;
    requires com.dwarfeng.subgrade.basic;
    requires com.dwarfeng.subgrade.data;
    requires jakarta.annotation;
    requires jakarta.el;
    requires org.apache.commons.logging;
    requires org.glassfish.expressly;
    requires org.slf4j;
    requires spring.aop;
    requires spring.beans;
    requires spring.context;
    requires spring.core;
    requires spring.data.redis;

    exports com.dwarfeng.settingrepo.node.all.he.configuration;
    exports com.dwarfeng.settingrepo.node.all.he.handler;
    exports com.dwarfeng.settingrepo.node.all.he.launcher;

    opens com.dwarfeng.settingrepo.node.all.he.configuration to spring.core, spring.beans, spring.context, spring.aop;
    opens com.dwarfeng.settingrepo.node.all.he.handler to spring.core, spring.beans, spring.context, spring.aop;
    opens com.dwarfeng.settingrepo.node.all.he.i18n to com.dwarfeng.settingrepo.base;
}
