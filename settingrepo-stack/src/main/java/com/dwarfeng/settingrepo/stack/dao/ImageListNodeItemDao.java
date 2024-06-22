package com.dwarfeng.settingrepo.stack.dao;

import com.dwarfeng.settingrepo.stack.bean.entity.ImageListNodeItem;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 图片列表节点条目节点数据访问层。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public interface ImageListNodeItemDao extends BatchBaseDao<LongIdKey, ImageListNodeItem>,
        EntireLookupDao<ImageListNodeItem>, PresetLookupDao<ImageListNodeItem> {
}
