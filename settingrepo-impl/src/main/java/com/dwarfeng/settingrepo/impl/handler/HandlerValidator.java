package com.dwarfeng.settingrepo.impl.handler;

import com.dwarfeng.settingrepo.sdk.util.IahnUtils;
import com.dwarfeng.settingrepo.stack.bean.entity.NavigationNodeItem;
import com.dwarfeng.settingrepo.stack.bean.key.IahnNodeLocaleKey;
import com.dwarfeng.settingrepo.stack.bean.key.IahnNodeMekKey;
import com.dwarfeng.settingrepo.stack.exception.*;
import com.dwarfeng.settingrepo.stack.service.*;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.apache.commons.lang3.StringUtils;
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
    private final IahnNodeLocaleMaintainService iahnNodeLocaleMaintainService;
    private final IahnNodeMekMaintainService iahnNodeMekMaintainService;
    private final NavigationNodeItemMaintainService navigationNodeItemMaintainService;

    public HandlerValidator(
            SettingCategoryMaintainService settingCategoryMaintainService,
            SettingNodeMaintainService settingNodeMaintainService,
            IahnNodeLocaleMaintainService iahnNodeLocaleMaintainService,
            IahnNodeMekMaintainService iahnNodeMekMaintainService,
            NavigationNodeItemMaintainService navigationNodeItemMaintainService
    ) {
        this.settingCategoryMaintainService = settingCategoryMaintainService;
        this.settingNodeMaintainService = settingNodeMaintainService;
        this.iahnNodeLocaleMaintainService = iahnNodeLocaleMaintainService;
        this.iahnNodeMekMaintainService = iahnNodeMekMaintainService;
        this.navigationNodeItemMaintainService = navigationNodeItemMaintainService;
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

    @SuppressWarnings("DuplicatedCode")
    public void makeSureIahnNodeInspectLocaleValid(String language, String country, String variant)
            throws HandlerException {
        if (Objects.isNull(language) || Objects.isNull(country) || Objects.isNull(variant)) {
            throw new InvalidIahnNodeLocaleException(language, country, variant);
        }
        language = IahnUtils.preProcessRfc4546Field(language);
        country = IahnUtils.preProcessRfc4546Field(country);
        variant = IahnUtils.preProcessRfc4546Field(variant);
        if (StringUtils.isNotEmpty(variant)) {
            if (StringUtils.isEmpty(country)) {
                throw new InvalidIahnNodeLocaleException(language, country, variant);
            }
            if (StringUtils.isEmpty(language)) {
                throw new InvalidIahnNodeLocaleException(language, country, variant);
            }
        }
        if (StringUtils.isNotEmpty(country)) {
            if (StringUtils.isEmpty(language)) {
                throw new InvalidIahnNodeLocaleException(language, country, variant);
            }
        }
    }

    @SuppressWarnings("DuplicatedCode")
    public void makeSureIahnNodeModifyLocaleValid(String language, String country, String variant)
            throws HandlerException {
        if (Objects.isNull(language) || Objects.isNull(country) || Objects.isNull(variant)) {
            throw new InvalidIahnNodeLocaleException(language, country, variant);
        }
        language = IahnUtils.preProcessRfc4546Field(language);
        country = IahnUtils.preProcessRfc4546Field(country);
        variant = IahnUtils.preProcessRfc4546Field(variant);
        if (StringUtils.isEmpty(language)) {
            throw new InvalidIahnNodeLocaleException(language, country, variant);
        }
        // 在 language 一定不为空的情况下，只需要保证 variant 不为空时 country 一定不为空即可。
        if (StringUtils.isNotEmpty(variant)) {
            if (StringUtils.isEmpty(country)) {
                throw new InvalidIahnNodeLocaleException(language, country, variant);
            }
        }
    }

    public void makeSureIahnNodeMekIdValid(String mekId) throws HandlerException {
        if (StringUtils.isBlank(mekId)) {
            throw new InvalidIahnNodeMekIdException(mekId);
        }
    }

    public void makeSureIahnNodeLocaleExists(IahnNodeLocaleKey iahnNodeLocaleKey) throws HandlerException {
        try {
            if (Objects.isNull(iahnNodeLocaleKey) || !iahnNodeLocaleMaintainService.exists(iahnNodeLocaleKey)) {
                throw new IahnNodeLocaleNotExistsException(iahnNodeLocaleKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    public void makeSureIahnNodeMekExists(IahnNodeMekKey iahnNodeMekKey) throws HandlerException {
        try {
            if (Objects.isNull(iahnNodeMekKey) || !iahnNodeMekMaintainService.exists(iahnNodeMekKey)) {
                throw new IahnNodeMekNotExistsException(iahnNodeMekKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    public void makeSureNavigationNodeItemExists(LongIdKey navigationNodeItemKey) throws HandlerException {
        try {
            if (Objects.isNull(navigationNodeItemKey) ||
                    !navigationNodeItemMaintainService.exists(navigationNodeItemKey)) {
                throw new NavigationNodeItemNotExistsException(navigationNodeItemKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    public void makeSureNavigationNodeItemIndexNotConflict(
            StringIdKey settingNodeKey, LongIdKey parentNavigationNodeItemKey, int index
    ) throws HandlerException {
        try {
            int count = navigationNodeItemMaintainService.lookupCount(
                    NavigationNodeItemMaintainService.CHILD_FOR_NODE_CHILD_FOR_PARENT_INDEX_EQ,
                    new Object[]{settingNodeKey, parentNavigationNodeItemKey, index}
            );
            if (count > 0) {
                throw new NavigationNodeItemIndexConflictException(settingNodeKey, parentNavigationNodeItemKey, index);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    public void makeSureNavigationNodeItemMatched(LongIdKey navigationNodeItemKey, StringIdKey settingNodeKey)
            throws HandlerException {
        try {
            NavigationNodeItem navigationNodeItem = navigationNodeItemMaintainService.getIfExists(
                    navigationNodeItemKey
            );
            if (Objects.isNull(navigationNodeItem)) {
                throw new NavigationNodeItemNotExistsException(navigationNodeItemKey);
            }
            if (!Objects.equals(navigationNodeItem.getNodeKey(), settingNodeKey)) {
                throw new NavigationNodeItemMismatchedException(navigationNodeItemKey, settingNodeKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }
}
