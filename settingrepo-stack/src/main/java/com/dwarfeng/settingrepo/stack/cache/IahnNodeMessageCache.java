package com.dwarfeng.settingrepo.stack.cache;

import com.dwarfeng.settingrepo.stack.bean.entity.IahnNodeMessage;
import com.dwarfeng.settingrepo.stack.bean.key.IahnNodeMessageKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 国际化节点消息缓存。
 *
 * @author mooyuan
 * @author DwArFeng
 * @since 2.1.0
 */
public interface IahnNodeMessageCache extends BatchBaseCache<IahnNodeMessageKey, IahnNodeMessage> {
}
