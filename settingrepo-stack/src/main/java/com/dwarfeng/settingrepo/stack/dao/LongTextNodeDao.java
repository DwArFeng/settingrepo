package com.dwarfeng.settingrepo.stack.dao;

import com.dwarfeng.settingrepo.stack.bean.entity.LongTextNode;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 长文本节点数据访问层。
 *
 * @author DwArFeng
 * @since 2.2.0
 */
public interface LongTextNodeDao extends BatchBaseDao<StringIdKey, LongTextNode>, EntireLookupDao<LongTextNode>,
        PresetLookupDao<LongTextNode> {
}
