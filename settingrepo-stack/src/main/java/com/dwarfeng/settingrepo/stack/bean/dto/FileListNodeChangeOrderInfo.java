package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.Arrays;

/**
 * 文件列表节点变更顺序信息。
 *
 * @author DwArFeng
 * @since 2.4.2
 */
public class FileListNodeChangeOrderInfo implements Dto {

    private static final long serialVersionUID = -6298586500457848124L;

    private String category;
    private String[] args;
    private int oldIndex;
    private int neoIndex;

    public FileListNodeChangeOrderInfo() {
    }

    public FileListNodeChangeOrderInfo(String category, String[] args, int oldIndex, int neoIndex) {
        this.category = category;
        this.args = args;
        this.oldIndex = oldIndex;
        this.neoIndex = neoIndex;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String[] getArgs() {
        return args;
    }

    public void setArgs(String[] args) {
        this.args = args;
    }

    public int getOldIndex() {
        return oldIndex;
    }

    public void setOldIndex(int oldIndex) {
        this.oldIndex = oldIndex;
    }

    public int getNeoIndex() {
        return neoIndex;
    }

    public void setNeoIndex(int neoIndex) {
        this.neoIndex = neoIndex;
    }

    @Override
    public String toString() {
        return "FileListNodeChangeOrderInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                ", oldIndex=" + oldIndex +
                ", neoIndex=" + neoIndex +
                '}';
    }
}
