package com.dwarfeng.settingrepo.stack.exception;

import com.dwarfeng.settingrepo.stack.internal.i18n.StackMessageKey;
import com.dwarfeng.settingrepo.stack.internal.i18n.StackMessages;
import com.dwarfeng.subgrade.basic.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.basic.stack.exception.HandlerException;

import java.io.Serial;

/**
 * 导航节点条目不存在异常。
 *
 * @author DwArFeng
 * @since 2.4.2
 */
public class NavigationNodeItemNotExistsException extends HandlerException {

    @Serial
    private static final long serialVersionUID = 8054247874452848308L;

    private final LongIdKey navigationNodeItemKey;

    public NavigationNodeItemNotExistsException(LongIdKey navigationNodeItemKey) {
        this.navigationNodeItemKey = navigationNodeItemKey;
    }

    public NavigationNodeItemNotExistsException(Throwable cause, LongIdKey navigationNodeItemKey) {
        super(cause);
        this.navigationNodeItemKey = navigationNodeItemKey;
    }

    @Override
    public String getMessage() {
        return StackMessages.message(StackMessageKey.EXCEPTION_NAVIGATION_NODE_ITEM_NOT_EXISTS, navigationNodeItemKey);
    }
}
