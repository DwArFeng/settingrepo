package com.dwarfeng.settingrepo.stack.exception;

import com.dwarfeng.settingrepo.stack.internal.i18n.StackMessageKey;
import com.dwarfeng.settingrepo.stack.internal.i18n.StackMessages;
import com.dwarfeng.subgrade.basic.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.basic.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.basic.stack.exception.HandlerException;

import java.io.Serial;

/**
 * 导航节点条目索引冲突异常。
 *
 * @author DwArFeng
 * @since 2.4.2
 */
public class NavigationNodeItemIndexConflictException extends HandlerException {

    @Serial
    private static final long serialVersionUID = -6297734274526580729L;

    private final StringIdKey settingNodeKey;
    private final LongIdKey parentNavigationNodeItemKey;
    private final int index;

    public NavigationNodeItemIndexConflictException(
            StringIdKey settingNodeKey, LongIdKey parentNavigationNodeItemKey, int index
    ) {
        this.settingNodeKey = settingNodeKey;
        this.parentNavigationNodeItemKey = parentNavigationNodeItemKey;
        this.index = index;
    }

    public NavigationNodeItemIndexConflictException(
            Throwable cause, StringIdKey settingNodeKey, LongIdKey parentNavigationNodeItemKey, int index
    ) {
        super(cause);
        this.settingNodeKey = settingNodeKey;
        this.parentNavigationNodeItemKey = parentNavigationNodeItemKey;
        this.index = index;
    }

    @Override
    public String getMessage() {
        return StackMessages.message(
                StackMessageKey.NAVIGATION_NODE_ITEM_INDEX_CONFLICT,
                settingNodeKey, parentNavigationNodeItemKey, index
        );
    }
}
