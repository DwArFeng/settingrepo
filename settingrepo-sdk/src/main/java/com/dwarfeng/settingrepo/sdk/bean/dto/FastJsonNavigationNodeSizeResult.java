package com.dwarfeng.settingrepo.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.settingrepo.stack.bean.dto.NavigationNodeSizeResult;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * FastJson 导航节点大小结果。
 *
 * @author DwArFeng
 * @since 2.4.2
 */
public class FastJsonNavigationNodeSizeResult implements Bean {

    private static final long serialVersionUID = 3455691046721120681L;

    public static FastJsonNavigationNodeSizeResult of(NavigationNodeSizeResult navigationNodeSizeResult) {
        if (Objects.isNull(navigationNodeSizeResult)) {
            return null;
        } else {
            return new FastJsonNavigationNodeSizeResult(
                    navigationNodeSizeResult.getSize()
            );
        }
    }

    @JSONField(name = "size", ordinal = 1)
    private int size;

    public FastJsonNavigationNodeSizeResult() {
    }

    public FastJsonNavigationNodeSizeResult(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "FastJsonNavigationNodeSizeResult{" +
                "size=" + size +
                '}';
    }
}
