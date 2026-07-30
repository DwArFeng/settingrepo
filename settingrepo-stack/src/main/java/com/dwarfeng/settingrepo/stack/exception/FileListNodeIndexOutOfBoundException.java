package com.dwarfeng.settingrepo.stack.exception;

import com.dwarfeng.settingrepo.stack.internal.i18n.StackMessageKey;
import com.dwarfeng.settingrepo.stack.internal.i18n.StackMessages;
import com.dwarfeng.subgrade.basic.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.basic.stack.exception.HandlerException;

import java.io.Serial;

/**
 * 文件列表节点索引越界异常。
 *
 * @author DwArFeng
 * @since 2.4.2
 */
public class FileListNodeIndexOutOfBoundException extends HandlerException {

    @Serial
    private static final long serialVersionUID = 1081061041901363267L;

    private final StringIdKey settingNodeKey;
    private final int expectedMinIndex;
    private final int expectedMaxIndex;
    private final int actualIndex;

    public FileListNodeIndexOutOfBoundException(
            StringIdKey settingNodeKey, int expectedMinIndex, int expectedMaxIndex, int actualIndex
    ) {
        this.settingNodeKey = settingNodeKey;
        this.expectedMinIndex = expectedMinIndex;
        this.expectedMaxIndex = expectedMaxIndex;
        this.actualIndex = actualIndex;
    }

    public FileListNodeIndexOutOfBoundException(
            Throwable cause, StringIdKey settingNodeKey, int expectedMinIndex, int expectedMaxIndex, int actualIndex
    ) {
        super(cause);
        this.settingNodeKey = settingNodeKey;
        this.expectedMinIndex = expectedMinIndex;
        this.expectedMaxIndex = expectedMaxIndex;
        this.actualIndex = actualIndex;
    }

    @Override
    public String getMessage() {
        String expectedIndexRange;
        if (expectedMinIndex <= expectedMaxIndex) {
            expectedIndexRange = "[" + expectedMinIndex + ", " + expectedMaxIndex + "]";
        } else {
            expectedIndexRange = StackMessages.message(StackMessageKey.INDEX_RANGE_EMPTY);
        }
        return StackMessages.message(
                StackMessageKey.FILE_LIST_NODE_INDEX_OUT_OF_BOUND, settingNodeKey, expectedIndexRange, actualIndex
        );
    }
}
