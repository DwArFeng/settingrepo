package com.dwarfeng.settingrepo.stack.service;

import com.dwarfeng.settingrepo.stack.bean.entity.FormatterSupport;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 格式化器支持器维护服务。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface FormatterSupportMaintainService extends BatchCrudService<StringIdKey, FormatterSupport>,
        EntireLookupService<FormatterSupport>, PresetLookupService<FormatterSupport> {

    String ID_LIKE = "id_like";
    String LABEL_LIKE = "label_like";

    /**
     * 重置格式化器支持。
     *
     * @throws ServiceException 服务异常。
     */
    void reset() throws ServiceException;
}
