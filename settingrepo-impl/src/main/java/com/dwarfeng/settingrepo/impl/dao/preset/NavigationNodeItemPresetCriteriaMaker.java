package com.dwarfeng.settingrepo.impl.dao.preset;

import com.dwarfeng.settingrepo.impl.bean.entity.HibernateNavigationNodeItem;
import com.dwarfeng.settingrepo.stack.service.NavigationNodeItemMaintainService;
import com.dwarfeng.subgrade.basic.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.basic.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.data.sdk.hibernate.criteria.PresetCriteriaMaker;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;

@Component
public class NavigationNodeItemPresetCriteriaMaker implements PresetCriteriaMaker<HibernateNavigationNodeItem> {

    @Override
    public void makeCriteria(
            CriteriaBuilder criteriaBuilder, CriteriaQuery<?> criteriaQuery, Root<HibernateNavigationNodeItem> root,
            String s, Object[] objects
    ) {
        switch (s) {
            case NavigationNodeItemMaintainService.CHILD_FOR_NODE:
                childForNode(criteriaBuilder, criteriaQuery, root, objects);
                break;
            case NavigationNodeItemMaintainService.CHILD_FOR_PARENT:
                childForParent(criteriaBuilder, criteriaQuery, root, objects);
                break;
            case NavigationNodeItemMaintainService.CHILD_FOR_PARENT_INDEX_ASC:
                childForParentIndexAsc(criteriaBuilder, criteriaQuery, root, objects);
                break;
            case NavigationNodeItemMaintainService.CHILD_FOR_PARENT_INDEX_DESC:
                childForParentIndexDesc(criteriaBuilder, criteriaQuery, root, objects);
                break;
            case NavigationNodeItemMaintainService.CHILD_FOR_PARENT_INDEX_EQ:
                childForParentIndexEq(criteriaBuilder, criteriaQuery, root, objects);
                break;
            case NavigationNodeItemMaintainService.CHILD_FOR_NODE_CHILD_FOR_PARENT_INDEX_ASC:
                childForNodeChildForParentIndexAsc(criteriaBuilder, criteriaQuery, root, objects);
                break;
            case NavigationNodeItemMaintainService.CHILD_FOR_NODE_CHILD_FOR_PARENT_INDEX_DESC:
                childForNodeChildForParentIndexDesc(criteriaBuilder, criteriaQuery, root, objects);
                break;
            case NavigationNodeItemMaintainService.CHILD_FOR_NODE_CHILD_FOR_PARENT_INDEX_EQ:
                childForNodeChildForParentIndexEq(criteriaBuilder, criteriaQuery, root, objects);
                break;
            case NavigationNodeItemMaintainService.CHILD_FOR_NODE_NAME_LIKE:
                childForNodeNameLike(criteriaBuilder, criteriaQuery, root, objects);
                break;
            default:
                throw new IllegalArgumentException("无法识别的预设: " + s);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void childForNode(
            CriteriaBuilder criteriaBuilder, CriteriaQuery<?> criteriaQuery, Root<HibernateNavigationNodeItem> root,
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

    private void childForParent(
            CriteriaBuilder criteriaBuilder, CriteriaQuery<?> criteriaQuery, Root<HibernateNavigationNodeItem> root,
            Object[] objects
    ) {
        try {
            if (Objects.isNull(objects[0])) {
                CriteriaQueryHelper.addRestriction(
                        criteriaBuilder, criteriaQuery, criteriaBuilder.isNull(root.get("parentLongId"))
                );
            } else {
                LongIdKey longIdKey = (LongIdKey) objects[0];
                CriteriaQueryHelper.addRestriction(
                        criteriaBuilder,
                        criteriaQuery,
                        CriteriaQueryHelper.equalOrIsNull(
                                criteriaBuilder, root.get("parentLongId"), longIdKey.getLongId()
                        )
                );
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    private void childForParentIndexAsc(
            CriteriaBuilder criteriaBuilder, CriteriaQuery<?> criteriaQuery, Root<HibernateNavigationNodeItem> root,
            Object[] objects
    ) {
        try {
            if (Objects.isNull(objects[0])) {
                CriteriaQueryHelper.addRestriction(
                        criteriaBuilder, criteriaQuery, criteriaBuilder.isNull(root.get("parentLongId"))
                );
            } else {
                LongIdKey longIdKey = (LongIdKey) objects[0];
                CriteriaQueryHelper.addRestriction(
                        criteriaBuilder,
                        criteriaQuery,
                        CriteriaQueryHelper.equalOrIsNull(
                                criteriaBuilder, root.get("parentLongId"), longIdKey.getLongId()
                        )
                );
            }
            CriteriaQueryHelper.addOrder(criteriaQuery, criteriaBuilder.asc(root.get("index")));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    private void childForParentIndexDesc(
            CriteriaBuilder criteriaBuilder, CriteriaQuery<?> criteriaQuery, Root<HibernateNavigationNodeItem> root,
            Object[] objects
    ) {
        try {
            if (Objects.isNull(objects[0])) {
                CriteriaQueryHelper.addRestriction(
                        criteriaBuilder, criteriaQuery, criteriaBuilder.isNull(root.get("parentLongId"))
                );
            } else {
                LongIdKey longIdKey = (LongIdKey) objects[0];
                CriteriaQueryHelper.addRestriction(
                        criteriaBuilder,
                        criteriaQuery,
                        CriteriaQueryHelper.equalOrIsNull(
                                criteriaBuilder, root.get("parentLongId"), longIdKey.getLongId()
                        )
                );
            }
            CriteriaQueryHelper.addOrder(criteriaQuery, criteriaBuilder.desc(root.get("index")));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    private void childForParentIndexEq(
            CriteriaBuilder criteriaBuilder, CriteriaQuery<?> criteriaQuery, Root<HibernateNavigationNodeItem> root,
            Object[] objects
    ) {
        try {
            if (Objects.isNull(objects[0])) {
                CriteriaQueryHelper.addRestriction(
                        criteriaBuilder, criteriaQuery, criteriaBuilder.isNull(root.get("parentLongId"))
                );
            } else {
                LongIdKey longIdKey = (LongIdKey) objects[0];
                CriteriaQueryHelper.addRestriction(
                        criteriaBuilder,
                        criteriaQuery,
                        CriteriaQueryHelper.equalOrIsNull(
                                criteriaBuilder, root.get("parentLongId"), longIdKey.getLongId()
                        )
                );
            }
            Integer index = (Integer) objects[1];
            CriteriaQueryHelper.addRestriction(criteriaBuilder, criteriaQuery,
                    CriteriaQueryHelper.equalOrIsNull(criteriaBuilder, root.get("index"), index)
            );
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void childForNodeChildForParentIndexAsc(
            CriteriaBuilder criteriaBuilder, CriteriaQuery<?> criteriaQuery, Root<HibernateNavigationNodeItem> root,
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
            if (Objects.isNull(objects[1])) {
                CriteriaQueryHelper.addRestriction(
                        criteriaBuilder, criteriaQuery, criteriaBuilder.isNull(root.get("parentLongId"))
                );
            } else {
                LongIdKey longIdKey = (LongIdKey) objects[1];
                CriteriaQueryHelper.addRestriction(
                        criteriaBuilder,
                        criteriaQuery,
                        CriteriaQueryHelper.equalOrIsNull(
                                criteriaBuilder, root.get("parentLongId"), longIdKey.getLongId()
                        )
                );
            }
            CriteriaQueryHelper.addOrder(criteriaQuery, criteriaBuilder.asc(root.get("index")));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void childForNodeChildForParentIndexDesc(
            CriteriaBuilder criteriaBuilder, CriteriaQuery<?> criteriaQuery, Root<HibernateNavigationNodeItem> root,
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
            if (Objects.isNull(objects[1])) {
                CriteriaQueryHelper.addRestriction(
                        criteriaBuilder, criteriaQuery, criteriaBuilder.isNull(root.get("parentLongId"))
                );
            } else {
                LongIdKey longIdKey = (LongIdKey) objects[1];
                CriteriaQueryHelper.addRestriction(
                        criteriaBuilder,
                        criteriaQuery,
                        CriteriaQueryHelper.equalOrIsNull(
                                criteriaBuilder, root.get("parentLongId"), longIdKey.getLongId()
                        )
                );
            }
            CriteriaQueryHelper.addOrder(criteriaQuery, criteriaBuilder.desc(root.get("index")));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void childForNodeChildForParentIndexEq(
            CriteriaBuilder criteriaBuilder, CriteriaQuery<?> criteriaQuery, Root<HibernateNavigationNodeItem> root,
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
            if (Objects.isNull(objects[1])) {
                CriteriaQueryHelper.addRestriction(
                        criteriaBuilder, criteriaQuery, criteriaBuilder.isNull(root.get("parentLongId"))
                );
            } else {
                LongIdKey longIdKey = (LongIdKey) objects[1];
                CriteriaQueryHelper.addRestriction(
                        criteriaBuilder,
                        criteriaQuery,
                        CriteriaQueryHelper.equalOrIsNull(
                                criteriaBuilder, root.get("parentLongId"), longIdKey.getLongId()
                        )
                );
            }
            Integer index = (Integer) objects[2];
            CriteriaQueryHelper.addRestriction(
                    criteriaBuilder,
                    criteriaQuery,
                    CriteriaQueryHelper.equalOrIsNull(criteriaBuilder, root.get("index"), index)
            );
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    private void childForNodeNameLike(
            CriteriaBuilder criteriaBuilder, CriteriaQuery<?> criteriaQuery, Root<HibernateNavigationNodeItem> root,
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
            String pattern = (String) objects[1];
            CriteriaQueryHelper.addRestriction(
                    criteriaBuilder,
                    criteriaQuery,
                    CriteriaQueryHelper.likeAnywhere(criteriaBuilder, root.get("name"), pattern)
            );
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }
}
