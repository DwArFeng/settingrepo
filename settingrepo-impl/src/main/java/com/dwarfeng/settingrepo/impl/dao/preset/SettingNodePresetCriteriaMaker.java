package com.dwarfeng.settingrepo.impl.dao.preset;

import com.dwarfeng.settingrepo.impl.bean.entity.HibernateSettingNode;
import com.dwarfeng.settingrepo.stack.service.SettingNodeMaintainService;
import com.dwarfeng.subgrade.data.sdk.hibernate.criteria.PresetCriteriaMaker;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class SettingNodePresetCriteriaMaker implements PresetCriteriaMaker<HibernateSettingNode> {

    @Override
    public void makeCriteria(
            CriteriaBuilder criteriaBuilder, CriteriaQuery<?> criteriaQuery, Root<HibernateSettingNode> root,
            String s, Object[] objects
    ) {
        switch (s) {
            case SettingNodeMaintainService.ID_LIKE:
                idLike(criteriaBuilder, criteriaQuery, root, objects);
                break;
            case SettingNodeMaintainService.REACHABLE:
                reachable(criteriaBuilder, criteriaQuery, root, objects);
                break;
            case SettingNodeMaintainService.ID_LIKE_REACHABLE:
                idLikeReachable(criteriaBuilder, criteriaQuery, root, objects);
                break;
            case SettingNodeMaintainService.CATEGORY_EQUALS:
                categoryEquals(criteriaBuilder, criteriaQuery, root, objects);
                break;
            case SettingNodeMaintainService.UNREACHABLE:
                unreachable(criteriaBuilder, criteriaQuery, root, objects);
                break;
            default:
                throw new IllegalArgumentException("无法识别的预设: " + s);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void idLike(
            CriteriaBuilder criteriaBuilder, CriteriaQuery<?> criteriaQuery, Root<HibernateSettingNode> root,
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

    private void reachable(
            CriteriaBuilder criteriaBuilder, CriteriaQuery<?> criteriaQuery, Root<HibernateSettingNode> root,
            Object[] objects
    ) {
        try {
            CriteriaQueryHelper.addRestriction(
                    criteriaBuilder, criteriaQuery, criteriaBuilder.equal(root.get("reachable"), true)
            );
            CriteriaQueryHelper.addOrder(criteriaQuery, criteriaBuilder.asc(root.get("stringId")));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    private void idLikeReachable(
            CriteriaBuilder criteriaBuilder, CriteriaQuery<?> criteriaQuery, Root<HibernateSettingNode> root,
            Object[] objects
    ) {
        try {
            String pattern = (String) objects[0];
            CriteriaQueryHelper.addRestriction(
                    criteriaBuilder,
                    criteriaQuery,
                    CriteriaQueryHelper.likeAnywhere(criteriaBuilder, root.get("stringId"), pattern)
            );
            CriteriaQueryHelper.addRestriction(
                    criteriaBuilder, criteriaQuery, criteriaBuilder.equal(root.get("reachable"), true)
            );
            CriteriaQueryHelper.addOrder(criteriaQuery, criteriaBuilder.asc(root.get("stringId")));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    private void categoryEquals(
            CriteriaBuilder criteriaBuilder, CriteriaQuery<?> criteriaQuery, Root<HibernateSettingNode> root,
            Object[] objects
    ) {
        try {
            String category = (String) objects[0];
            CriteriaQueryHelper.addRestriction(
                    criteriaBuilder, criteriaQuery, criteriaBuilder.equal(root.get("category"), category)
            );
            CriteriaQueryHelper.addOrder(criteriaQuery, criteriaBuilder.asc(root.get("stringId")));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    private void unreachable(
            CriteriaBuilder criteriaBuilder, CriteriaQuery<?> criteriaQuery, Root<HibernateSettingNode> root,
            Object[] objects
    ) {
        try {
            CriteriaQueryHelper.addRestriction(
                    criteriaBuilder, criteriaQuery, criteriaBuilder.equal(root.get("reachable"), false)
            );
            CriteriaQueryHelper.addOrder(criteriaQuery, criteriaBuilder.asc(root.get("stringId")));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }
}
