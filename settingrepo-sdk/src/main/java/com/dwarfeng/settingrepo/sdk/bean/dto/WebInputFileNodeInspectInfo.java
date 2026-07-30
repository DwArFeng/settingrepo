package com.dwarfeng.settingrepo.sdk.bean.dto;

import com.alibaba.fastjson2.annotation.JSONField;
import com.dwarfeng.settingrepo.sdk.util.Constraints;
import com.dwarfeng.settingrepo.stack.bean.dto.FileNodeInspectInfo;
import com.dwarfeng.subgrade.basic.stack.bean.dto.Dto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.io.Serial;
import java.util.Arrays;
import java.util.Objects;

/**
 * WebInput 文件节点查看信息。
 *
 * @author DwArFeng
 * @since 2.4.2
 */
public class WebInputFileNodeInspectInfo implements Dto {

    @Serial
    private static final long serialVersionUID = -6937940672144114104L;

    public static FileNodeInspectInfo toStackBean(WebInputFileNodeInspectInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new FileNodeInspectInfo(
                    webInput.getCategory(),
                    webInput.getArgs()
            );
        }
    }

    @JSONField(name = "category")
    @NotNull
    @NotEmpty
    @Length(max = Constraints.LENGTH_NAME)
    private String category;

    @JSONField(name = "args")
    @NotNull
    private String[] args;

    public WebInputFileNodeInspectInfo() {
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
        return "WebInputFileNodeInspectInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}
