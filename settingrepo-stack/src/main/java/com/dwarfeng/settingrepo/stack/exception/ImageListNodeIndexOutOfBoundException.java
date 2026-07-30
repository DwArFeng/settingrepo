package com.dwarfeng.settingrepo.stack.exception;

import com.dwarfeng.settingrepo.stack.internal.i18n.StackMessageKey;
import com.dwarfeng.settingrepo.stack.internal.i18n.StackMessages;
import com.dwarfeng.subgrade.basic.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.basic.stack.exception.HandlerException;

import java.io.Serial;

/**
 * 图片列表节点索引越界异常。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class ImageListNodeIndexOutOfBoundException extends HandlerException {

    @Serial
    private static final long serialVersionUID = -2073138251022355866L;

    private final StringIdKey settingNodeKey;
    private final int expectedMinIndex;
    private final int expectedMaxIndex;
    private final int actualIndex;

    public ImageListNodeIndexOutOfBoundException(
            StringIdKey settingNodeKey, int expectedMinIndex, int expectedMaxIndex, int actualIndex
    ) {
        this.settingNodeKey = settingNodeKey;
        this.expectedMinIndex = expectedMinIndex;
        this.expectedMaxIndex = expectedMaxIndex;
        this.actualIndex = actualIndex;
    }

    public ImageListNodeIndexOutOfBoundException(
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
                StackMessageKey.IMAGE_LIST_NODE_INDEX_OUT_OF_BOUND, settingNodeKey, expectedIndexRange, actualIndex
        );
    }
}
