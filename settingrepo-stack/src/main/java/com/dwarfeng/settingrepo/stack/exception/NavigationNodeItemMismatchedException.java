package com.dwarfeng.settingrepo.stack.exception;

import com.dwarfeng.settingrepo.stack.internal.i18n.StackMessageKey;
import com.dwarfeng.settingrepo.stack.internal.i18n.StackMessages;
import com.dwarfeng.subgrade.basic.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.basic.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.basic.stack.exception.HandlerException;

import java.io.Serial;

/**
 * 导航节点条目不匹配异常。
 *
 * @author DwArFeng
 * @since 2.4.2
 */
public class NavigationNodeItemMismatchedException extends HandlerException {

    @Serial
    private static final long serialVersionUID = -2987395587963407958L;

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
        return StackMessages.message(
                StackMessageKey.NAVIGATION_NODE_ITEM_MISMATCHED, navigationNodeItemKey, settingNodeKey
        );
    }
}
