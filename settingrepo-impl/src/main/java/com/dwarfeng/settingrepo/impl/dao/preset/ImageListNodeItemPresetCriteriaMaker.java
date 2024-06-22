package com.dwarfeng.settingrepo.impl.dao.preset;

import com.dwarfeng.settingrepo.stack.service.ImageListNodeItemMaintainService;
import com.dwarfeng.subgrade.sdk.hibernate.criteria.PresetCriteriaMaker;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;

@Component
public class ImageListNodeItemPresetCriteriaMaker implements PresetCriteriaMaker {

    @Override
    public void makeCriteria(DetachedCriteria detachedCriteria, String s, Object[] objects) {
        switch (s) {
            case ImageListNodeItemMaintainService.CHILD_FOR_NODE:
                childForNode(detachedCriteria, objects);
                break;
            case ImageListNodeItemMaintainService.CHILD_FOR_NODE_INDEX_ASC:
                childForNodeIndexAsc(detachedCriteria, objects);
                break;
            case ImageListNodeItemMaintainService.CHILD_FOR_NODE_INDEX_EQ:
                childForNodeIndexEq(detachedCriteria, objects);
                break;
            case ImageListNodeItemMaintainService.CHILD_FOR_NODE_INDEX_GE:
                childForNodeIndexGe(detachedCriteria, objects);
                break;
            case ImageListNodeItemMaintainService.CHILD_FOR_NODE_INDEX_GT_INDEX_LE:
                childForNodeIndexGtIndexLe(detachedCriteria, objects);
                break;
            case ImageListNodeItemMaintainService.CHILD_FOR_NODE_INDEX_GE_INDEX_LT:
                childForNodeIndexGeIndexLt(detachedCriteria, objects);
                break;
            default:
                throw new IllegalArgumentException("无法识别的预设: " + s);
        }
    }

    private void childForNode(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("nodeStringId"));
            } else {
                StringIdKey stringIdKey = (StringIdKey) objects[0];
                detachedCriteria.add(
                        Restrictions.eqOrIsNull("nodeStringId", stringIdKey.getStringId())
                );
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    private void childForNodeIndexAsc(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("nodeStringId"));
            } else {
                StringIdKey stringIdKey = (StringIdKey) objects[0];
                detachedCriteria.add(
                        Restrictions.eqOrIsNull("nodeStringId", stringIdKey.getStringId())
                );
            }
            detachedCriteria.addOrder(org.hibernate.criterion.Order.asc("index"));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    private void childForNodeIndexEq(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("nodeStringId"));
            } else {
                StringIdKey stringIdKey = (StringIdKey) objects[0];
                detachedCriteria.add(
                        Restrictions.eqOrIsNull("nodeStringId", stringIdKey.getStringId())
                );
            }
            detachedCriteria.add(Restrictions.eq("index", objects[1]));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void childForNodeIndexGe(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("nodeStringId"));
            } else {
                StringIdKey stringIdKey = (StringIdKey) objects[0];
                detachedCriteria.add(
                        Restrictions.eqOrIsNull("nodeStringId", stringIdKey.getStringId())
                );
            }
            detachedCriteria.add(Restrictions.ge("index", objects[1]));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    private void childForNodeIndexGtIndexLe(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("nodeStringId"));
            } else {
                StringIdKey stringIdKey = (StringIdKey) objects[0];
                detachedCriteria.add(
                        Restrictions.eqOrIsNull("nodeStringId", stringIdKey.getStringId())
                );
            }
            detachedCriteria.add(Restrictions.gt("index", objects[1]));
            detachedCriteria.add(Restrictions.le("index", objects[2]));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void childForNodeIndexGeIndexLt(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("nodeStringId"));
            } else {
                StringIdKey stringIdKey = (StringIdKey) objects[0];
                detachedCriteria.add(
                        Restrictions.eqOrIsNull("nodeStringId", stringIdKey.getStringId())
                );
            }
            detachedCriteria.add(Restrictions.ge("index", objects[1]));
            detachedCriteria.add(Restrictions.lt("index", objects[2]));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }
}
