package com.dwarfeng.settingrepo.stack.handler;

import com.dwarfeng.settingrepo.stack.struct.WarmupLocalCache;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.handler.LocalCacheHandler;

/**
 * 预热本地缓存处理器。
 *
 * <p>
 * 该处理器实现了 <code>LocalCacheHandler&lt;LongIdKey, WarmupLocalCache&gt;</code> 接口，
 * 用于处理与预热驱动相关的本地缓存。<br>
 * 对于 <code>LocalCacheHandler&lt;K, V&gt;</code> 接口中的泛型参数:
 * <table>
 *     <tr>
 *         <th></th>
 *         <th>类型</th>
 *         <th>含义</th>
 *     </tr>
 *     <tr>
 *         <td>K</td>
 *         <td>LongIdKey</td>
 *         <td>预热设置主键</td>
 *     </tr>
 *     <tr>
 *         <td>V</td>
 *         <td>WarmupLocalCache</td>
 *         <td>预热本地缓存</td>
 *     </tr>
 * </table>
 *
 * @author DwArFeng
 * @see LocalCacheHandler
 * @see WarmupLocalCache
 * @since 3.1.0
 */
public interface WarmupLocalCacheHandler extends LocalCacheHandler<LongIdKey, WarmupLocalCache> {
}
