package com.dwarfeng.settingrepo.impl.handler;

import com.dwarfeng.settingrepo.stack.exception.SettingCategoryNotExistsException;
import com.dwarfeng.settingrepo.stack.exception.SettingNodeExistsException;
import com.dwarfeng.settingrepo.stack.exception.SettingNodeNotExistsException;
import com.dwarfeng.settingrepo.stack.service.SettingCategoryMaintainService;
import com.dwarfeng.settingrepo.stack.service.SettingNodeMaintainService;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 操作处理器验证器。
 *
 * <p>
 * 为操作处理器提供公共的验证方法。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
@Component
public class HandlerValidator {

    private final SettingCategoryMaintainService settingCategoryMaintainService;
    private final SettingNodeMaintainService settingNodeMaintainService;

    public HandlerValidator(
            SettingCategoryMaintainService settingCategoryMaintainService,
            SettingNodeMaintainService settingNodeMaintainService
    ) {
        this.settingCategoryMaintainService = settingCategoryMaintainService;
        this.settingNodeMaintainService = settingNodeMaintainService;
    }

    public void makeSureSettingCategoryExists(StringIdKey settingCategoryKey) throws HandlerException {
        try {
            if (Objects.isNull(settingCategoryKey) || !settingCategoryMaintainService.exists(settingCategoryKey)) {
                throw new SettingCategoryNotExistsException(settingCategoryKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    public void makeSureSettingNodeExists(StringIdKey settingNodeKey) throws HandlerException {
        try {
            if (Objects.isNull(settingNodeKey) || !settingNodeMaintainService.exists(settingNodeKey)) {
                throw new SettingNodeNotExistsException(settingNodeKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    public void makeSureSettingNodeNotExists(StringIdKey settingNodeKey) throws HandlerException {
        try {
            if (Objects.isNull(settingNodeKey)) {
                return;
            }
            if (settingNodeMaintainService.exists(settingNodeKey)) {
                throw new SettingNodeExistsException(settingNodeKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }
}
