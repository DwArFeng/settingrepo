package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.basic.stack.bean.dto.Dto;

import java.io.Serial;

/**
 * 文件节点查看结果。
 *
 * @author DwArFeng
 * @since 2.4.2
 */
public class FileNodeInspectResult implements Dto {

    @Serial
    private static final long serialVersionUID = 3780005671546975141L;

    private String originName;
    private long length;

    public FileNodeInspectResult() {
    }

    public FileNodeInspectResult(String originName, long length) {
        this.originName = originName;
        this.length = length;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "FileNodeInspectResult{" +
                "originName='" + originName + '\'' +
                ", length=" + length +
                '}';
    }
}
