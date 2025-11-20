package com.dwarfeng.settingrepo.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 文件列表节点索引越界异常。
 *
 * @author DwArFeng
 * @since 2.4.2
 */
public class FileListNodeIndexOutOfBoundException extends HandlerException {

    private static final long serialVersionUID = 3724657099968337316L;

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
            expectedIndexRange = "空集合";
        }
        return "文件列表节点索引越界, 节点主键: " + settingNodeKey +
                ", 期望的索引范围: " + expectedIndexRange + ", 实际的索引: " + actualIndex;
    }
}
