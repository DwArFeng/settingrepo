package com.dwarfeng.settingrepo.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 导航节点条目不存在异常。
 *
 * @author DwArFeng
 * @since 2.4.2
 */
public class NavigationNodeItemNotExistsException extends HandlerException {

    private static final long serialVersionUID = -5439770035925326326L;

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
        return "导航节点条目 " + navigationNodeItemKey + " 不存在";
    }
}
