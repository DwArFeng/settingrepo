package com.dwarfeng.settingrepo.impl.dao.preset;

import com.dwarfeng.settingrepo.impl.bean.entity.HibernateKvNode;
import com.dwarfeng.subgrade.data.sdk.hibernate.criteria.PresetCriteriaMaker;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Component;

@Component
public class KvNodePresetCriteriaMaker implements PresetCriteriaMaker<HibernateKvNode> {

    @SuppressWarnings("SwitchStatementWithTooFewBranches")
    @Override
    public void makeCriteria(
            CriteriaBuilder criteriaBuilder, CriteriaQuery<?> criteriaQuery, Root<HibernateKvNode> root,
            String s, Object[] objects
    ) {
        switch (s) {
            default:
                throw new IllegalArgumentException("无法识别的预设: " + s);
        }
    }
}
