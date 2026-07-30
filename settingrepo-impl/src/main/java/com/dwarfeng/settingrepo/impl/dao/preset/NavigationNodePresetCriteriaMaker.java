package com.dwarfeng.settingrepo.impl.dao.preset;

import com.dwarfeng.settingrepo.impl.bean.entity.HibernateNavigationNode;
import com.dwarfeng.subgrade.data.sdk.hibernate.criteria.PresetCriteriaMaker;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Component;

@Component
public class NavigationNodePresetCriteriaMaker implements PresetCriteriaMaker<HibernateNavigationNode> {

    @SuppressWarnings("SwitchStatementWithTooFewBranches")
    @Override
    public void makeCriteria(
            CriteriaBuilder criteriaBuilder, CriteriaQuery<?> criteriaQuery, Root<HibernateNavigationNode> root,
            String s, Object[] objects
    ) {
        switch (s) {
            default:
                throw new IllegalArgumentException("无法识别的预设: " + s);
        }
    }
}
