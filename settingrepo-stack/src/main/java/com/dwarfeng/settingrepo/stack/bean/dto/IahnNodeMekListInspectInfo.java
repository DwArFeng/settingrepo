package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.basic.stack.bean.dto.Dto;

import java.io.Serial;
import java.util.Arrays;

/**
 * 国际化节点 Mek 列表查询信息。
 *
 * @author DwArFeng
 * @since 2.3.3
 */
public class IahnNodeMekListInspectInfo implements Dto {

    @Serial
    private static final long serialVersionUID = -3055387453193232863L;

    private String category;
    private String[] args;

    public IahnNodeMekListInspectInfo() {
    }

    public IahnNodeMekListInspectInfo(String category, String[] args) {
        this.category = category;
        this.args = args;
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

    @Override
    public String toString() {
        return "IahnNodeMekListInspectInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}
