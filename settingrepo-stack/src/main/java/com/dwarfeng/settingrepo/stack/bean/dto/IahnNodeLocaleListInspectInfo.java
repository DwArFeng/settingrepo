package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.Arrays;

/**
 * 国际化节点地区列表查询信息。
 *
 * @author DwArFeng
 * @since 2.3.3
 */
public class IahnNodeLocaleListInspectInfo implements Dto {

    private static final long serialVersionUID = 7543911911072075020L;

    private String category;
    private String[] args;

    public IahnNodeLocaleListInspectInfo() {
    }

    public IahnNodeLocaleListInspectInfo(String category, String[] args) {
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
        return "IahnNodeLocaleListInspectInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}
