package com.dwarfeng.settingrepo.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.settingrepo.stack.bean.dto.TextListNodeTextFetchResult;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.Objects;

/**
 * FastJson 文本列表节点文本拉取结果。
 *
 * @author DwArFeng
 * @since 3.2.1
 */
public class FastJsonTextListNodeTextFetchResult implements Dto {

    private static final long serialVersionUID = 1889866259447134087L;

    public static FastJsonTextListNodeTextFetchResult of(TextListNodeTextFetchResult textListNodeTextFetchResult) {
        if (Objects.isNull(textListNodeTextFetchResult)) {
            return null;
        } else {
            return new FastJsonTextListNodeTextFetchResult(
                    textListNodeTextFetchResult.getContent()
            );
        }
    }

    @JSONField(name = "content", ordinal = 1)
    private String content;

    public FastJsonTextListNodeTextFetchResult() {
    }

    public FastJsonTextListNodeTextFetchResult(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "FastJsonTextListNodeTextFetchResult{" +
                "content='" + content + '\'' +
                '}';
    }
}
