module com.dwarfeng.settingrepo.stack {

    requires com.dwarfeng.settingrepo.base;
    requires com.dwarfeng.subgrade.basic;
    requires com.dwarfeng.subgrade.data;
    requires com.dwarfeng.subgrade.cache;
    requires com.dwarfeng.subgrade.lifecycle;
    requires static org.jetbrains.annotations;

    exports com.dwarfeng.settingrepo.stack.bean.dto;
    exports com.dwarfeng.settingrepo.stack.bean.entity;
    exports com.dwarfeng.settingrepo.stack.bean.key;
    exports com.dwarfeng.settingrepo.stack.cache;
    exports com.dwarfeng.settingrepo.stack.dao;
    exports com.dwarfeng.settingrepo.stack.exception;
    exports com.dwarfeng.settingrepo.stack.handler;
    exports com.dwarfeng.settingrepo.stack.service;

    opens com.dwarfeng.settingrepo.stack.i18n to com.dwarfeng.settingrepo.base;
}
