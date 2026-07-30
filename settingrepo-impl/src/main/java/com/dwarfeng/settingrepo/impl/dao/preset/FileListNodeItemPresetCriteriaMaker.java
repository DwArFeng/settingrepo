package com.dwarfeng.settingrepo.impl.dao.preset;

import com.dwarfeng.settingrepo.impl.bean.entity.HibernateFileListNodeItem;
import com.dwarfeng.settingrepo.stack.service.FileListNodeItemMaintainService;
import com.dwarfeng.subgrade.basic.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.data.sdk.hibernate.criteria.PresetCriteriaMaker;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;

@Component
public class FileListNodeItemPresetCriteriaMaker implements PresetCriteriaMaker<HibernateFileListNodeItem> {

    @Override
    public void makeCriteria(
            CriteriaBuilder criteriaBuilder, CriteriaQuery<?> criteriaQuery, Root<HibernateFileListNodeItem> root,
            String s, Object[] objects
    ) {
        switch (s) {
            case FileListNodeItemMaintainService.CHILD_FOR_NODE:
                childForNode(criteriaBuilder, criteriaQuery, root, objects);
                break;
            case FileListNodeItemMaintainService.CHILD_FOR_NODE_INDEX_ASC:
                childForNodeIndexAsc(criteriaBuilder, criteriaQuery, root, objects);
                break;
            case FileListNodeItemMaintainService.CHILD_FOR_NODE_INDEX_EQ:
                childForNodeIndexEq(criteriaBuilder, criteriaQuery, root, objects);
                break;
            case FileListNodeItemMaintainService.CHILD_FOR_NODE_INDEX_GE:
                childForNodeIndexGe(criteriaBuilder, criteriaQuery, root, objects);
                break;
            case FileListNodeItemMaintainService.CHILD_FOR_NODE_INDEX_GT_INDEX_LE:
                childForNodeIndexGtIndexLe(criteriaBuilder, criteriaQuery, root, objects);
                break;
            case FileListNodeItemMaintainService.CHILD_FOR_NODE_INDEX_GE_INDEX_LT:
                childForNodeIndexGeIndexLt(criteriaBuilder, criteriaQuery, root, objects);
                break;
            default:
                throw new IllegalArgumentException("无法识别的预设: " + s);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void childForNode(
            CriteriaBuilder criteriaBuilder, CriteriaQuery<?> criteriaQuery, Root<HibernateFileListNodeItem> root,
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

    @SuppressWarnings("DuplicatedCode")
    private void childForNodeIndexAsc(
            CriteriaBuilder criteriaBuilder, CriteriaQuery<?> criteriaQuery, Root<HibernateFileListNodeItem> root,
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
            CriteriaQueryHelper.addOrder(criteriaQuery, criteriaBuilder.asc(root.get("index")));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void childForNodeIndexEq(
            CriteriaBuilder criteriaBuilder, CriteriaQuery<?> criteriaQuery, Root<HibernateFileListNodeItem> root,
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
            CriteriaQueryHelper.addRestriction(
                    criteriaBuilder,
                    criteriaQuery,
                    criteriaBuilder.equal(root.get("index"), objects[1])
            );
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void childForNodeIndexGe(
            CriteriaBuilder criteriaBuilder, CriteriaQuery<?> criteriaQuery, Root<HibernateFileListNodeItem> root,
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
            CriteriaQueryHelper.addRestriction(
                    criteriaBuilder, criteriaQuery, criteriaBuilder.ge(root.get("index"), (Number) objects[1])
            );
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void childForNodeIndexGtIndexLe(
            CriteriaBuilder criteriaBuilder, CriteriaQuery<?> criteriaQuery, Root<HibernateFileListNodeItem> root,
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
            CriteriaQueryHelper.addRestriction(
                    criteriaBuilder, criteriaQuery, criteriaBuilder.gt(root.get("index"), (Number) objects[1])
            );
            CriteriaQueryHelper.addRestriction(
                    criteriaBuilder, criteriaQuery, criteriaBuilder.le(root.get("index"), (Number) objects[2])
            );
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void childForNodeIndexGeIndexLt(
            CriteriaBuilder criteriaBuilder, CriteriaQuery<?> criteriaQuery, Root<HibernateFileListNodeItem> root,
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
            CriteriaQueryHelper.addRestriction(
                    criteriaBuilder, criteriaQuery, criteriaBuilder.ge(root.get("index"), (Number) objects[1])
            );
            CriteriaQueryHelper.addRestriction(
                    criteriaBuilder, criteriaQuery, criteriaBuilder.lt(root.get("index"), (Number) objects[2])
            );
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }
}
