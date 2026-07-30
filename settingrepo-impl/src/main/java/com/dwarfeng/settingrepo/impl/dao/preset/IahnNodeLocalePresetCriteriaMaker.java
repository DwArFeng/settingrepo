package com.dwarfeng.settingrepo.impl.dao.preset;

import com.dwarfeng.settingrepo.impl.bean.entity.HibernateIahnNodeLocale;
import com.dwarfeng.settingrepo.stack.service.IahnNodeLocaleMaintainService;
import com.dwarfeng.subgrade.basic.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.data.sdk.hibernate.criteria.PresetCriteriaMaker;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;

@Component
public class IahnNodeLocalePresetCriteriaMaker implements PresetCriteriaMaker<HibernateIahnNodeLocale> {

    @Override
    public void makeCriteria(
            CriteriaBuilder criteriaBuilder, CriteriaQuery<?> criteriaQuery, Root<HibernateIahnNodeLocale> root,
            String s, Object[] objects
    ) {
        switch (s) {
            case IahnNodeLocaleMaintainService.CHILD_FOR_NODE:
                childForNode(criteriaBuilder, criteriaQuery, root, objects);
                break;
            case IahnNodeLocaleMaintainService.CHILD_FOR_NODE_ORDERED:
                childForNodeOrdered(criteriaBuilder, criteriaQuery, root, objects);
                break;
            default:
                throw new IllegalArgumentException("无法识别的预设: " + s);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void childForNode(
            CriteriaBuilder criteriaBuilder, CriteriaQuery<?> criteriaQuery, Root<HibernateIahnNodeLocale> root,
            Object[] objects
    ) {
        try {
            if (Objects.isNull(objects[0])) {
                CriteriaQueryHelper.addRestriction(
                        criteriaBuilder, criteriaQuery, criteriaBuilder.isNull(root.get("nodeStringId"))
                );
            } else {
                StringIdKey nodeKey = (StringIdKey) objects[0];
                CriteriaQueryHelper.addRestriction(
                        criteriaBuilder,
                        criteriaQuery,
                        CriteriaQueryHelper.equalOrIsNull(
                                criteriaBuilder, root.get("nodeStringId"), nodeKey.getStringId()
                        )
                );
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    private void childForNodeOrdered(
            CriteriaBuilder criteriaBuilder, CriteriaQuery<?> criteriaQuery, Root<HibernateIahnNodeLocale> root,
            Object[] objects
    ) {
        try {
            if (Objects.isNull(objects[0])) {
                CriteriaQueryHelper.addRestriction(
                        criteriaBuilder, criteriaQuery, criteriaBuilder.isNull(root.get("nodeStringId"))
                );
            } else {
                StringIdKey nodeKey = (StringIdKey) objects[0];
                CriteriaQueryHelper.addRestriction(
                        criteriaBuilder,
                        criteriaQuery,
                        CriteriaQueryHelper.equalOrIsNull(
                                criteriaBuilder, root.get("nodeStringId"), nodeKey.getStringId()
                        )
                );
            }
            CriteriaQueryHelper.addOrder(criteriaQuery, criteriaBuilder.asc(root.get("language")));
            CriteriaQueryHelper.addOrder(criteriaQuery, criteriaBuilder.asc(root.get("country")));
            CriteriaQueryHelper.addOrder(criteriaQuery, criteriaBuilder.asc(root.get("variant")));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }
}
