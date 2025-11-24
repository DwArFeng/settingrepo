package com.dwarfeng.settingrepo.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 导航节点条目索引冲突异常。
 *
 * @author DwArFeng
 * @since 2.4.2
 */
public class NavigationNodeItemIndexConflictException extends HandlerException {

    private static final long serialVersionUID = 8891213341252581513L;

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
        return "导航节点条目索引冲突, 设置节点主键: " + settingNodeKey + ", 父导航节点条目主键: " +
                parentNavigationNodeItemKey + ", 索引: " + index;
    }
}
