package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.Arrays;

/**
 * 国际化节点消息表查询信息。
 *
 * @author DwArFeng
 * @since 2.3.3
 */
public class IahnNodeMessageTableInspectInfo implements Dto {

    private static final long serialVersionUID = 6354732983305528413L;

    private String category;
    private String[] args;

    public IahnNodeMessageTableInspectInfo() {
    }

    public IahnNodeMessageTableInspectInfo(String category, String[] args) {
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
        return "IahnNodeMessageTableInspectInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}
