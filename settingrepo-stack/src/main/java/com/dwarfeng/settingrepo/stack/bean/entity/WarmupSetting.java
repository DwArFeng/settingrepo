package com.dwarfeng.settingrepo.stack.bean.entity;

import com.dwarfeng.subgrade.stack.bean.entity.Entity;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

import java.util.Arrays;

/**
 * 预热设置。
 *
 * @author DwArFeng
 * @since 3.1.0
 */
public class WarmupSetting implements Entity<LongIdKey> {

    private static final long serialVersionUID = -7031836310783544393L;

    private LongIdKey key;

    /**
     * 预热目标的类型。
     *
     * <p>
     * int 枚举，可能的值为：
     * <ul>
     *     <li>键值对节点查看结果缓存</li>
     *     <li>键值对节点条目查看结果缓存</li>
     *     <li>导航节点查看结果缓存</li>
     *     <li>国际化节点地区映射缓存</li>
     *     <li>国际化节点消息映射缓存</li>
     *     <li>国际化节点地区列表缓存</li>
     *     <li>国际化节点 Mek 列表缓存</li>
     *     <li>国际化节点消息表缓存</li>
     * </ul>
     * 这些值与设置节点类型互相独立，不得互相转换。
     */
    private int type;

    /**
     * 预热字符串 1#。
     *
     * <p>
     * 此处的值的含义由 {@link #type} 决定，其对应关系如下：
     * <table>
     *     <tr>
     *         <th>目标类型</th>
     *         <th>是否需要</th>
     *         <th>字段含义</th>
     *     </tr>
     *     <tr>
     *         <td>键值对节点查看结果缓存</td>
     *         <td>是</td>
     *         <td>设置类别的主键字符串</td>
     *     </tr>
     *     <tr>
     *         <td>键值对节点条目查看结果缓存</td>
     *         <td>是</td>
     *         <td>设置类别的主键字符串</td>
     *     </tr>
     *     <tr>
     *         <td>导航节点查看结果缓存</td>
     *         <td>是</td>
     *         <td>设置类别的主键字符串</td>
     *     </tr>
     *     <tr>
     *         <td>国际化节点地区映射缓存</td>
     *         <td>是</td>
     *         <td>设置类别的主键字符串</td>
     *     </tr>
     *     <tr>
     *         <td>国际化节点消息映射缓存</td>
     *         <td>是</td>
     *         <td>设置类别的主键字符串</td>
     *     </tr>
     *     <tr>
     *         <td>国际化节点地区列表缓存</td>
     *         <td>是</td>
     *         <td>设置类别的主键字符串</td>
     *     </tr>
     *     <tr>
     *         <td>国际化节点 Mek 列表缓存</td>
     *         <td>是</td>
     *         <td>设置类别的主键字符串</td>
     *     </tr>
     *     <tr>
     *         <td>国际化节点消息表缓存</td>
     *         <td>是</td>
     *         <td>设置类别的主键字符串</td>
     *     </tr>
     * </table>
     */
    private String targetStringAlpha;

    /**
     * 预热字符串 2#。
     *
     * <p>
     * 此处的值的含义由 {@link #type} 决定，其对应关系如下：
     * <table>
     *     <tr>
     *         <th>目标类型</th>
     *         <th>是否需要</th>
     *         <th>字段含义</th>
     *     </tr>
     *     <tr>
     *         <td>键值对节点查看结果缓存</td>
     *         <td>否</td>
     *         <td>无</td>
     *     </tr>
     *     <tr>
     *         <td>键值对节点条目查看结果缓存</td>
     *         <td>是</td>
     *         <td>条目的字符串主键</td>
     *     </tr>
     *     <tr>
     *         <td>导航节点查看结果缓存</td>
     *         <td>否</td>
     *         <td>无</td>
     *     </tr>
     *     <tr>
     *         <td>国际化节点地区映射缓存</td>
     *         <td>否</td>
     *         <td>无</td>
     *     </tr>
     *     <tr>
     *         <td>国际化节点消息映射缓存</td>
     *         <td>否</td>
     *         <td>无</td>
     *     </tr>
     *     <tr>
     *         <td>国际化节点地区列表缓存</td>
     *         <td>否</td>
     *         <td>无</td>
     *     </tr>
     *     <tr>
     *         <td>国际化节点 Mek 列表缓存</td>
     *         <td>否</td>
     *         <td>无</td>
     *     </tr>
     *     <tr>
     *         <td>国际化节点消息表缓存</td>
     *         <td>否</td>
     *         <td>无</td>
     *     </tr>
     * </table>
     */
    private String targetStringBravo;

    /**
     * 预热字符串 3#。
     *
     * <p>
     * 此处的值的含义由 {@link #type} 决定，其对应关系如下：
     * <table>
     *     <tr>
     *         <th>目标类型</th>
     *         <th>是否需要</th>
     *         <th>字段含义</th>
     *     </tr>
     *     <tr>
     *         <td>键值对节点查看结果缓存</td>
     *         <td>否</td>
     *         <td>无</td>
     *     </tr>
     *     <tr>
     *         <td>键值对节点条目查看结果缓存</td>
     *         <td>否</td>
     *         <td>无</td>
     *     </tr>
     *     <tr>
     *         <td>导航节点查看结果缓存</td>
     *         <td>否</td>
     *         <td>无</td>
     *     </tr>
     *     <tr>
     *         <td>国际化节点地区映射缓存</td>
     *         <td>是</td>
     *         <td>语言</td>
     *     </tr>
     *     <tr>
     *         <td>国际化节点消息映射缓存</td>
     *         <td>是</td>
     *         <td>语言</td>
     *     </tr>
     *     <tr>
     *         <td>国际化节点地区列表缓存</td>
     *         <td>否</td>
     *         <td>无</td>
     *     </tr>
     *     <tr>
     *         <td>国际化节点 Mek 列表缓存</td>
     *         <td>否</td>
     *         <td>无</td>
     *     </tr>
     *     <tr>
     *         <td>国际化节点消息表缓存</td>
     *         <td>否</td>
     *         <td>无</td>
     *     </tr>
     * </table>
     */
    private String targetStringCharlie;

    /**
     * 预热字符串 4#。
     *
     * <p>
     * 此处的值的含义由 {@link #type} 决定，其对应关系如下：
     * <table>
     *     <tr>
     *         <th>目标类型</th>
     *         <th>是否需要</th>
     *         <th>字段含义</th>
     *     </tr>
     *     <tr>
     *         <td>键值对节点查看结果缓存</td>
     *         <td>否</td>
     *         <td>无</td>
     *     </tr>
     *     <tr>
     *         <td>键值对节点条目查看结果缓存</td>
     *         <td>否</td>
     *         <td>无</td>
     *     </tr>
     *     <tr>
     *         <td>导航节点查看结果缓存</td>
     *         <td>否</td>
     *         <td>无</td>
     *     </tr>
     *     <tr>
     *         <td>国际化节点地区映射缓存</td>
     *         <td>是</td>
     *         <td>国家</td>
     *     </tr>
     *     <tr>
     *         <td>国际化节点消息映射缓存</td>
     *         <td>是</td>
     *         <td>国家</td>
     *     </tr>
     *     <tr>
     *         <td>国际化节点地区列表缓存</td>
     *         <td>否</td>
     *         <td>无</td>
     *     </tr>
     *     <tr>
     *         <td>国际化节点 Mek 列表缓存</td>
     *         <td>否</td>
     *         <td>无</td>
     *     </tr>
     *     <tr>
     *         <td>国际化节点消息表缓存</td>
     *         <td>否</td>
     *         <td>无</td>
     *     </tr>
     * </table>
     */
    private String targetStringDelta;

    /**
     * 预热字符串 5#。
     *
     * <p>
     * 此处的值的含义由 {@link #type} 决定，其对应关系如下：
     * <table>
     *     <tr>
     *         <th>目标类型</th>
     *         <th>是否需要</th>
     *         <th>字段含义</th>
     *     </tr>
     *     <tr>
     *         <td>键值对节点查看结果缓存</td>
     *         <td>否</td>
     *         <td>无</td>
     *     </tr>
     *     <tr>
     *         <td>键值对节点条目查看结果缓存</td>
     *         <td>否</td>
     *         <td>无</td>
     *     </tr>
     *     <tr>
     *         <td>导航节点查看结果缓存</td>
     *         <td>否</td>
     *         <td>无</td>
     *     </tr>
     *     <tr>
     *         <td>国际化节点地区映射缓存</td>
     *         <td>是</td>
     *         <td>变体</td>
     *     </tr>
     *     <tr>
     *         <td>国际化节点消息映射缓存</td>
     *         <td>是</td>
     *         <td>变体</td>
     *     </tr>
     *     <tr>
     *         <td>国际化节点地区列表缓存</td>
     *         <td>否</td>
     *         <td>无</td>
     *     </tr>
     *     <tr>
     *         <td>国际化节点 Mek 列表缓存</td>
     *         <td>否</td>
     *         <td>无</td>
     *     </tr>
     *     <tr>
     *         <td>国际化节点消息表缓存</td>
     *         <td>否</td>
     *         <td>无</td>
     *     </tr>
     * </table>
     */
    private String targetStringEcho;

    /**
     * 预热字符串数组 1#。
     *
     * <p>
     * 此处的值的含义由 {@link #type} 决定，其对应关系如下：
     * <table>
     *     <tr>
     *         <th>目标类型</th>
     *         <th>是否需要</th>
     *         <th>字段含义</th>
     *     </tr>
     *     <tr>
     *         <td>键值对节点查看结果缓存</td>
     *         <td>是</td>
     *         <td>设置类别所属节点的参数数组</td>
     *     </tr>
     *     <tr>
     *         <td>键值对节点条目查看结果缓存</td>
     *         <td>是</td>
     *         <td>设置类别所属节点的参数数组</td>
     *     </tr>
     *     <tr>
     *         <td>导航节点查看结果缓存</td>
     *         <td>是</td>
     *         <td>设置类别所属节点的参数数组</td>
     *     </tr>
     *     <tr>
     *         <td>国际化节点地区映射缓存</td>
     *         <td>是</td>
     *         <td>设置类别所属节点的参数数组</td>
     *     </tr>
     *     <tr>
     *         <td>国际化节点消息映射缓存</td>
     *         <td>是</td>
     *         <td>设置类别所属节点的参数数组</td>
     *     </tr>
     *     <tr>
     *         <td>国际化节点地区列表缓存</td>
     *         <td>是</td>
     *         <td>设置类别所属节点的参数数组</td>
     *     </tr>
     *     <tr>
     *         <td>国际化节点 Mek 列表缓存</td>
     *         <td>是</td>
     *         <td>设置类别所属节点的参数数组</td>
     *     </tr>
     *     <tr>
     *         <td>国际化节点消息表缓存</td>
     *         <td>是</td>
     *         <td>设置类别所属节点的参数数组</td>
     *     </tr>
     * </table>
     */
    private String[] targetStringArrayAlpha;

    /**
     * 是否启用。
     */
    private boolean enabled;

    /**
     * 备注。
     */
    private String remark;

    public WarmupSetting() {
    }

    public WarmupSetting(
            LongIdKey key, int type, String targetStringAlpha,
            String targetStringBravo, String targetStringCharlie, String targetStringDelta,
            String targetStringEcho, String[] targetStringArrayAlpha,
            boolean enabled, String remark
    ) {
        this.key = key;
        this.type = type;
        this.targetStringAlpha = targetStringAlpha;
        this.targetStringBravo = targetStringBravo;
        this.targetStringCharlie = targetStringCharlie;
        this.targetStringDelta = targetStringDelta;
        this.targetStringEcho = targetStringEcho;
        this.targetStringArrayAlpha = targetStringArrayAlpha;
        this.enabled = enabled;
        this.remark = remark;
    }

    @Override
    public LongIdKey getKey() {
        return key;
    }

    @Override
    public void setKey(LongIdKey key) {
        this.key = key;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTargetStringAlpha() {
        return targetStringAlpha;
    }

    public void setTargetStringAlpha(String targetStringAlpha) {
        this.targetStringAlpha = targetStringAlpha;
    }

    public String getTargetStringBravo() {
        return targetStringBravo;
    }

    public void setTargetStringBravo(String targetStringBravo) {
        this.targetStringBravo = targetStringBravo;
    }

    public String getTargetStringCharlie() {
        return targetStringCharlie;
    }

    public void setTargetStringCharlie(String targetStringCharlie) {
        this.targetStringCharlie = targetStringCharlie;
    }

    public String getTargetStringDelta() {
        return targetStringDelta;
    }

    public void setTargetStringDelta(String targetStringDelta) {
        this.targetStringDelta = targetStringDelta;
    }

    public String getTargetStringEcho() {
        return targetStringEcho;
    }

    public void setTargetStringEcho(String targetStringEcho) {
        this.targetStringEcho = targetStringEcho;
    }

    public String[] getTargetStringArrayAlpha() {
        return targetStringArrayAlpha;
    }

    public void setTargetStringArrayAlpha(String[] targetStringArrayAlpha) {
        this.targetStringArrayAlpha = targetStringArrayAlpha;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "WarmupSetting{" +
                "key=" + key +
                ", type=" + type +
                ", targetStringAlpha='" + targetStringAlpha + '\'' +
                ", targetStringBravo='" + targetStringBravo + '\'' +
                ", targetStringCharlie='" + targetStringCharlie + '\'' +
                ", targetStringDelta='" + targetStringDelta + '\'' +
                ", targetStringEcho='" + targetStringEcho + '\'' +
                ", targetStringArrayAlpha=" + Arrays.toString(targetStringArrayAlpha) +
                ", enabled=" + enabled +
                ", remark='" + remark + '\'' +
                '}';
    }
}
