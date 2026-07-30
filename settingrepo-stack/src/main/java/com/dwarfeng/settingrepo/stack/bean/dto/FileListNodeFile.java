package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.basic.stack.bean.dto.Dto;

import java.io.Serial;
import java.util.Arrays;

/**
 * 文件列表节点文件。
 *
 * @author DwArFeng
 * @since 2.4.2
 */
public class FileListNodeFile implements Dto {

    @Serial
    private static final long serialVersionUID = 2433499105811529565L;

    private String originName;
    private byte[] content;

    public FileListNodeFile() {
    }

    public FileListNodeFile(String originName, byte[] content) {
        this.originName = originName;
        this.content = content;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "FileListNodeFile{" +
                "originName='" + originName + '\'' +
                ", content=" + Arrays.toString(content) +
                '}';
    }
}
