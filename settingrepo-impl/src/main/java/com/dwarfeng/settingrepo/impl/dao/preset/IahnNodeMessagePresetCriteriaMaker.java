package com.dwarfeng.settingrepo.impl.dao.preset;

import com.dwarfeng.settingrepo.impl.bean.entity.HibernateIahnNodeMessage;
import com.dwarfeng.settingrepo.sdk.util.IahnUtils;
import com.dwarfeng.settingrepo.stack.bean.key.IahnNodeLocaleKey;
import com.dwarfeng.settingrepo.stack.bean.key.IahnNodeMekKey;
import com.dwarfeng.settingrepo.stack.service.IahnNodeMessageMaintainService;
import com.dwarfeng.subgrade.basic.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.data.sdk.hibernate.criteria.PresetCriteriaMaker;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;

@Component
public class IahnNodeMessagePresetCriteriaMaker implements PresetCriteriaMaker<HibernateIahnNodeMessage> {

    @Override
    public void makeCriteria(
            CriteriaBuilder criteriaBuilder, CriteriaQuery<?> criteriaQuery, Root<HibernateIahnNodeMessage> root,
            String s, Object[] objects
    ) {
        switch (s) {
            case IahnNodeMessageMaintainService.CHILD_FOR_NODE:
                childForNode(criteriaBuilder, criteriaQuery, root, objects);
                break;
            case IahnNodeMessageMaintainService.CHILD_FOR_LOCALE:
                childForLocale(criteriaBuilder, criteriaQuery, root, objects);
                break;
            case IahnNodeMessageMaintainService.CHILD_FOR_MEK:
                childForMek(criteriaBuilder, criteriaQuery, root, objects);
                break;
            default:
                throw new IllegalArgumentException("无法识别的预设: " + s);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void childForNode(
            CriteriaBuilder criteriaBuilder, CriteriaQuery<?> criteriaQuery, Root<HibernateIahnNodeMessage> root,
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

    private void childForLocale(
            CriteriaBuilder criteriaBuilder, CriteriaQuery<?> criteriaQuery, Root<HibernateIahnNodeMessage> root,
            Object[] objects
    ) {
        try {
            if (Objects.isNull(objects[0])) {
                CriteriaQueryHelper.addRestriction(
                        criteriaBuilder, criteriaQuery, criteriaBuilder.isNull(root.get("nodeStringId"))
                );
            } else {
                IahnNodeLocaleKey localeKey = (IahnNodeLocaleKey) objects[0];
                CriteriaQueryHelper.addRestriction(
                        criteriaBuilder,
                        criteriaQuery,
                        CriteriaQueryHelper.equalOrIsNull(
                                criteriaBuilder, root.get("nodeStringId"), localeKey.getNodeStringId()
                        )
                );
                CriteriaQueryHelper.addRestriction(
                        criteriaBuilder,
                        criteriaQuery,
                        CriteriaQueryHelper.equalOrIsNull(
                                criteriaBuilder,
                                root.get("language"),
                                IahnUtils.preProcessRfc4546Field(localeKey.getLanguage())
                        )
                );
                CriteriaQueryHelper.addRestriction(
                        criteriaBuilder,
                        criteriaQuery,
                        CriteriaQueryHelper.equalOrIsNull(
                                criteriaBuilder,
                                root.get("country"),
                                IahnUtils.preProcessRfc4546Field(localeKey.getCountry())
                        )
                );
                CriteriaQueryHelper.addRestriction(
                        criteriaBuilder,
                        criteriaQuery,
                        CriteriaQueryHelper.equalOrIsNull(
                                criteriaBuilder,
                                root.get("variant"),
                                IahnUtils.preProcessRfc4546Field(localeKey.getVariant())
                        )
                );
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    private void childForMek(
            CriteriaBuilder criteriaBuilder, CriteriaQuery<?> criteriaQuery, Root<HibernateIahnNodeMessage> root,
            Object[] objects
    ) {
        try {
            if (Objects.isNull(objects[0])) {
                CriteriaQueryHelper.addRestriction(
                        criteriaBuilder, criteriaQuery, criteriaBuilder.isNull(root.get("nodeStringId"))
                );
            } else {
                IahnNodeMekKey mekKey = (IahnNodeMekKey) objects[0];
                CriteriaQueryHelper.addRestriction(
                        criteriaBuilder,
                        criteriaQuery,
                        CriteriaQueryHelper.equalOrIsNull(
                                criteriaBuilder, root.get("nodeStringId"), mekKey.getNodeStringId()
                        )
                );
                CriteriaQueryHelper.addRestriction(
                        criteriaBuilder,
                        criteriaQuery,
                        CriteriaQueryHelper.equalOrIsNull(
                                criteriaBuilder, root.get("mekId"), IahnUtils.preProcessMekId(mekKey.getMekId())
                        )
                );
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }
}
