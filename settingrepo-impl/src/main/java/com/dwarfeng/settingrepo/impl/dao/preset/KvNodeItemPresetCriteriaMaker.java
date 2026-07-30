package com.dwarfeng.settingrepo.impl.dao.preset;

import com.dwarfeng.settingrepo.impl.bean.entity.HibernateKvNodeItem;
import com.dwarfeng.settingrepo.stack.service.KvNodeItemMaintainService;
import com.dwarfeng.subgrade.basic.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.data.sdk.hibernate.criteria.PresetCriteriaMaker;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;

@Component
public class KvNodeItemPresetCriteriaMaker implements PresetCriteriaMaker<HibernateKvNodeItem> {

    @Override
    public void makeCriteria(
            CriteriaBuilder criteriaBuilder, CriteriaQuery<?> criteriaQuery, Root<HibernateKvNodeItem> root,
            String s, Object[] objects
    ) {
        switch (s) {
            case KvNodeItemMaintainService.CHILD_FOR_NODE:
                childForNode(criteriaBuilder, criteriaQuery, root, objects);
                break;
            case KvNodeItemMaintainService.STRING_ID_ASC:
                stringIdAsc(criteriaBuilder, criteriaQuery, root, objects);
                break;
            case KvNodeItemMaintainService.CHILD_FOR_NODE_ITEM_STRING_ID_ASC:
                childForNodeItemStringIdAsc(criteriaBuilder, criteriaQuery, root, objects);
                break;
            default:
                throw new IllegalArgumentException("无法识别的预设: " + s);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void childForNode(
            CriteriaBuilder criteriaBuilder, CriteriaQuery<?> criteriaQuery, Root<HibernateKvNodeItem> root,
            Object[] objects
    ) {
        try {
            if (Objects.isNull(objects[0])) {
                CriteriaQueryHelper.addRestriction(
                        criteriaBuilder, criteriaQuery, criteriaBuilder.isNull(root.get("nodeStringId"))
                );
            } else {
                StringIdKey stringIdKey = (StringIdKey) objects[0];
                CriteriaQueryHelper.addRestriction(
                        criteriaBuilder,
                        criteriaQuery,
                        CriteriaQueryHelper.equalOrIsNull(
                                criteriaBuilder, root.get("nodeStringId"), stringIdKey.getStringId()
                        )
                );
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    // 为了保持代码的一致性，此处不做简化。
    @SuppressWarnings("unused")
    private void stringIdAsc(
            CriteriaBuilder criteriaBuilder, CriteriaQuery<?> criteriaQuery, Root<HibernateKvNodeItem> root,
            Object[] objects
    ) {
        CriteriaQueryHelper.addOrder(criteriaQuery, criteriaBuilder.asc(root.get("stringId")));
    }

    private void childForNodeItemStringIdAsc(
            CriteriaBuilder criteriaBuilder, CriteriaQuery<?> criteriaQuery, Root<HibernateKvNodeItem> root,
            Object[] objects
    ) {
        try {
            if (Objects.isNull(objects[0])) {
                CriteriaQueryHelper.addRestriction(
                        criteriaBuilder, criteriaQuery, criteriaBuilder.isNull(root.get("nodeStringId"))
                );
            } else {
                StringIdKey stringIdKey = (StringIdKey) objects[0];
                CriteriaQueryHelper.addRestriction(
                        criteriaBuilder,
                        criteriaQuery,
                        CriteriaQueryHelper.equalOrIsNull(
                                criteriaBuilder, root.get("nodeStringId"), stringIdKey.getStringId()
                        )
                );
            }
            CriteriaQueryHelper.addOrder(criteriaQuery, criteriaBuilder.asc(root.get("itemStringId")));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }
}
