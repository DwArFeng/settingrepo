package com.dwarfeng.settingrepo.stack.dao;

import com.dwarfeng.settingrepo.stack.bean.entity.VideoListNodeItem;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 视频列表节点条目数据访问层。
 *
 * @author fucl
 * @since 3.2.0
 */
public interface VideoListNodeItemDao extends BatchBaseDao<LongIdKey, VideoListNodeItem>,
        EntireLookupDao<VideoListNodeItem>, PresetLookupDao<VideoListNodeItem> {
}
