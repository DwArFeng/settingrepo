package com.dwarfeng.settingrepo.impl.dao.preset;

import com.dwarfeng.settingrepo.stack.service.WarmupDriverInfoMaintainService;
import com.dwarfeng.subgrade.sdk.hibernate.criteria.PresetCriteriaMaker;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;

/**
 * 预热驱动信息预设查询条件构造器。
 *
 * @author DwArFeng
 * @since 3.1.0
 */
@Component
public class WarmupDriverInfoPresetCriteriaMaker implements PresetCriteriaMaker {

    @Override
    public void makeCriteria(DetachedCriteria criteria, String preset, Object[] objs) {
        switch (preset) {
            case WarmupDriverInfoMaintainService.CHILD_FOR_WARMUP_SETTING:
                childForWarmupSetting(criteria, objs);
                break;
            case WarmupDriverInfoMaintainService.CHILD_FOR_WARMUP_SETTING_ENABLED:
                childForWarmupSettingEnabled(criteria, objs);
                break;
            default:
                throw new IllegalArgumentException("无法识别的预设: " + preset);
        }
    }

    private void childForWarmupSetting(DetachedCriteria criteria, Object[] objs) {
        try {
            if (Objects.isNull(objs[0])) {
                criteria.add(Restrictions.isNull("warmupSettingLongId"));
            } else {
                LongIdKey warmupSettingKey = (LongIdKey) objs[0];
                criteria.add(Restrictions.eqOrIsNull("warmupSettingLongId", warmupSettingKey.getLongId()));
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objs));
        }
    }

    private void childForWarmupSettingEnabled(DetachedCriteria criteria, Object[] objs) {
        try {
            if (Objects.isNull(objs[0])) {
                criteria.add(Restrictions.isNull("warmupSettingLongId"));
            } else {
                LongIdKey warmupSettingKey = (LongIdKey) objs[0];
                criteria.add(Restrictions.eqOrIsNull("warmupSettingLongId", warmupSettingKey.getLongId()));
            }
            criteria.add(Restrictions.eq("enabled", true));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objs));
        }
    }
}
