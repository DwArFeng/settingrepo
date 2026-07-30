package com.dwarfeng.settingrepo.impl.dao.preset;

import com.dwarfeng.settingrepo.impl.bean.entity.HibernateFileListNode;
import com.dwarfeng.subgrade.data.sdk.hibernate.criteria.PresetCriteriaMaker;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Component;

@Component
public class FileListNodePresetCriteriaMaker implements PresetCriteriaMaker<HibernateFileListNode> {

    @SuppressWarnings("SwitchStatementWithTooFewBranches")
    @Override
    public void makeCriteria(
            CriteriaBuilder criteriaBuilder, CriteriaQuery<?> criteriaQuery, Root<HibernateFileListNode> root,
            String preset, Object[] objs
    ) {
        switch (preset) {
            default:
                throw new IllegalArgumentException("无法识别的预设: " + preset);
        }
    }
}
