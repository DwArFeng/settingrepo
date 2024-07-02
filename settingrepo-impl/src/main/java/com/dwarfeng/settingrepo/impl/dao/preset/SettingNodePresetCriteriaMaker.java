package com.dwarfeng.settingrepo.impl.dao.preset;

import com.dwarfeng.settingrepo.stack.service.SettingNodeMaintainService;
import com.dwarfeng.subgrade.sdk.hibernate.criteria.PresetCriteriaMaker;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class SettingNodePresetCriteriaMaker implements PresetCriteriaMaker {

    @Override
    public void makeCriteria(DetachedCriteria detachedCriteria, String s, Object[] objects) {
        switch (s) {
            case SettingNodeMaintainService.ID_LIKE:
                idLike(detachedCriteria, objects);
                break;
            case SettingNodeMaintainService.REACHABLE:
                reachable(detachedCriteria, objects);
                break;
            case SettingNodeMaintainService.ID_LIKE_REACHABLE:
                idLikeReachable(detachedCriteria, objects);
                break;
            case SettingNodeMaintainService.CATEGORY_EQUALS:
                categoryEquals(detachedCriteria, objects);
                break;
            case SettingNodeMaintainService.UNREACHABLE:
                unreachable(detachedCriteria, objects);
                break;
            default:
                throw new IllegalArgumentException("无法识别的预设: " + s);
        }
    }

    private void idLike(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            String pattern = (String) objects[0];
            detachedCriteria.add(Restrictions.like("stringId", pattern, MatchMode.ANYWHERE));
            detachedCriteria.addOrder(Order.asc("stringId"));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    private void reachable(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            detachedCriteria.add(Restrictions.eq("reachable", true));
            detachedCriteria.addOrder(Order.asc("stringId"));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    private void idLikeReachable(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            String pattern = (String) objects[0];
            detachedCriteria.add(Restrictions.like("stringId", pattern, MatchMode.ANYWHERE));
            detachedCriteria.add(Restrictions.eq("reachable", true));
            detachedCriteria.addOrder(Order.asc("stringId"));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    private void categoryEquals(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            String category = (String) objects[0];
            detachedCriteria.add(Restrictions.eq("category", category));
            detachedCriteria.addOrder(Order.asc("stringId"));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    private void unreachable(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            detachedCriteria.add(Restrictions.eq("reachable", false));
            detachedCriteria.addOrder(Order.asc("stringId"));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }
}
