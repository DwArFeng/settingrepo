package com.dwarfeng.settingrepo.impl.dao.preset;

import com.dwarfeng.settingrepo.stack.service.WarmupDriverSupportMaintainService;
import com.dwarfeng.subgrade.sdk.hibernate.criteria.PresetCriteriaMaker;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 预热驱动器支持预设查询条件构造器。
 *
 * @author DwArFeng
 * @since 3.1.0
 */
@Component
public class WarmupDriverSupportPresetCriteriaMaker implements PresetCriteriaMaker {

    @Override
    public void makeCriteria(DetachedCriteria detachedCriteria, String preset, Object[] objs) {
        switch (preset) {
            case WarmupDriverSupportMaintainService.ID_LIKE:
                idLike(detachedCriteria, objs);
                break;
            case WarmupDriverSupportMaintainService.LABEL_LIKE:
                labelLike(detachedCriteria, objs);
                break;
            default:
                throw new IllegalArgumentException("无法识别的预设: " + preset);
        }
    }

    private void idLike(DetachedCriteria detachedCriteria, Object[] objs) {
        try {
            String pattern = (String) objs[0];
            detachedCriteria.add(Restrictions.like("stringId", pattern, MatchMode.ANYWHERE));
            detachedCriteria.addOrder(Order.asc("stringId"));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objs));
        }
    }

    private void labelLike(DetachedCriteria detachedCriteria, Object[] objs) {
        try {
            String pattern = (String) objs[0];
            detachedCriteria.add(Restrictions.like("label", pattern, MatchMode.ANYWHERE));
            detachedCriteria.addOrder(Order.asc("stringId"));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objs));
        }
    }
}
