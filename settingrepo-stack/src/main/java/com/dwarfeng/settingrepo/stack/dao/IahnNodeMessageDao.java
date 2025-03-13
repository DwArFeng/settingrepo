package com.dwarfeng.settingrepo.stack.dao;

import com.dwarfeng.settingrepo.stack.bean.entity.IahnNodeMessage;
import com.dwarfeng.settingrepo.stack.bean.key.IahnNodeMessageKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 国际化节点消息数据访问层。
 *
 * @author mooyuan
 * @author DwArFeng
 * @since 2.1.0
 */
public interface IahnNodeMessageDao extends BatchBaseDao<IahnNodeMessageKey, IahnNodeMessage>,
        EntireLookupDao<IahnNodeMessage>, PresetLookupDao<IahnNodeMessage> {
}
