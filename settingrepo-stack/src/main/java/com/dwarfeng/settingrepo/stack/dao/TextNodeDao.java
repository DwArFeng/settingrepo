package com.dwarfeng.settingrepo.stack.dao;

import com.dwarfeng.settingrepo.stack.bean.entity.TextNode;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 文本节点数据访问层。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public interface TextNodeDao extends BatchBaseDao<StringIdKey, TextNode>, EntireLookupDao<TextNode>,
        PresetLookupDao<TextNode> {
}
