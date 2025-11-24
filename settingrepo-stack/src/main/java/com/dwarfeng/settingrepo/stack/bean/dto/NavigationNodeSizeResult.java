package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

/**
 * 导航节点大小结果。
 *
 * @author DwArFeng
 * @since 2.4.2
 */
public class NavigationNodeSizeResult implements Dto {

    private static final long serialVersionUID = 5382883051495406537L;

    /**
     * 导航节点的数量。
     */
    private int size;

    public NavigationNodeSizeResult() {
    }

    public NavigationNodeSizeResult(int size) {
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
        return "NavigationNodeSizeResult{" +
                "size=" + size +
                '}';
    }
}
