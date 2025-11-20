package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

/**
 * 文件节点查看结果。
 *
 * @author DwArFeng
 * @since 2.4.2
 */
public class FileNodeInspectResult implements Dto {

    private static final long serialVersionUID = -4779845435107896465L;

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
