package com.dwarfeng.settingrepo.impl.dao.preset;

import com.dwarfeng.settingrepo.impl.bean.entity.HibernateSettingCategory;
import com.dwarfeng.settingrepo.stack.service.SettingCategoryMaintainService;
import com.dwarfeng.subgrade.data.sdk.hibernate.criteria.PresetCriteriaMaker;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class SettingCategoryPresetCriteriaMaker implements PresetCriteriaMaker<HibernateSettingCategory> {

    @SuppressWarnings("SwitchStatementWithTooFewBranches")
    @Override
    public void makeCriteria(
            CriteriaBuilder criteriaBuilder, CriteriaQuery<?> criteriaQuery, Root<HibernateSettingCategory> root,
            String s, Object[] objects
    ) {
        switch (s) {
            case SettingCategoryMaintainService.ID_LIKE:
                idLike(criteriaBuilder, criteriaQuery, root, objects);
                break;
            default:
                throw new IllegalArgumentException("无法识别的预设: " + s);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void idLike(
            CriteriaBuilder criteriaBuilder, CriteriaQuery<?> criteriaQuery, Root<HibernateSettingCategory> root,
            Object[] objects
    ) {
        try {
            String pattern = (String) objects[0];
            CriteriaQueryHelper.addRestriction(
                    criteriaBuilder,
                    criteriaQuery,
                    CriteriaQueryHelper.likeAnywhere(criteriaBuilder, root.get("stringId"), pattern)
            );
            CriteriaQueryHelper.addOrder(criteriaQuery, criteriaBuilder.asc(root.get("stringId")));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }
}
