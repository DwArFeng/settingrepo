package com.dwarfeng.settingrepo.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 导航节点条目不匹配异常。
 *
 * @author DwArFeng
 * @since 2.4.2
 */
public class NavigationNodeItemMismatchedException extends HandlerException {

    private static final long serialVersionUID = 2978418863941017214L;

    private final LongIdKey navigationNodeItemKey;
    private final StringIdKey settingNodeKey;

    public NavigationNodeItemMismatchedException(LongIdKey navigationNodeItemKey, StringIdKey settingNodeKey) {
        this.navigationNodeItemKey = navigationNodeItemKey;
        this.settingNodeKey = settingNodeKey;
    }

    public NavigationNodeItemMismatchedException(
            Throwable cause, LongIdKey navigationNodeItemKey, StringIdKey settingNodeKey
    ) {
        super(cause);
        this.navigationNodeItemKey = navigationNodeItemKey;
        this.settingNodeKey = settingNodeKey;
    }

    @Override
    public String getMessage() {
        return "导航节点条目 " + navigationNodeItemKey + " 与设置节点 " + settingNodeKey + " 不匹配";
    }
}
