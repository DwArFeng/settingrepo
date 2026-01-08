package com.dwarfeng.settingrepo.impl.dao.preset;

import com.dwarfeng.settingrepo.stack.service.NavigationNodeItemMaintainService;
import com.dwarfeng.subgrade.sdk.hibernate.criteria.PresetCriteriaMaker;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;

@Component
public class NavigationNodeItemPresetCriteriaMaker implements PresetCriteriaMaker {

    @Override
    public void makeCriteria(DetachedCriteria detachedCriteria, String s, Object[] objects) {
        switch (s) {
            case NavigationNodeItemMaintainService.CHILD_FOR_NODE:
                childForNode(detachedCriteria, objects);
                break;
            case NavigationNodeItemMaintainService.CHILD_FOR_PARENT:
                childForParent(detachedCriteria, objects);
                break;
            case NavigationNodeItemMaintainService.CHILD_FOR_PARENT_INDEX_ASC:
                childForParentIndexAsc(detachedCriteria, objects);
                break;
            case NavigationNodeItemMaintainService.CHILD_FOR_PARENT_INDEX_DESC:
                childForParentIndexDesc(detachedCriteria, objects);
                break;
            case NavigationNodeItemMaintainService.CHILD_FOR_PARENT_INDEX_EQ:
                childForParentIndexEq(detachedCriteria, objects);
                break;
            case NavigationNodeItemMaintainService.CHILD_FOR_NODE_CHILD_FOR_PARENT_INDEX_ASC:
                childForNodeChildForParentIndexAsc(detachedCriteria, objects);
                break;
            case NavigationNodeItemMaintainService.CHILD_FOR_NODE_CHILD_FOR_PARENT_INDEX_DESC:
                childForNodeChildForParentIndexDesc(detachedCriteria, objects);
                break;
            case NavigationNodeItemMaintainService.CHILD_FOR_NODE_CHILD_FOR_PARENT_INDEX_EQ:
                childForNodeChildForParentIndexEq(detachedCriteria, objects);
                break;
            case NavigationNodeItemMaintainService.CHILD_FOR_NODE_NAME_LIKE:
                childForNodeNameLike(detachedCriteria, objects);
                break;
            default:
                throw new IllegalArgumentException("无法识别的预设: " + s);
        }
    }

    @SuppressWarnings("DuplicatedCode")
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

    private void childForParent(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("parentLongId"));
            } else {
                LongIdKey longIdKey = (LongIdKey) objects[0];
                detachedCriteria.add(
                        Restrictions.eqOrIsNull("parentLongId", longIdKey.getLongId())
                );
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void childForParentIndexAsc(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("parentLongId"));
            } else {
                LongIdKey longIdKey = (LongIdKey) objects[0];
                detachedCriteria.add(
                        Restrictions.eqOrIsNull("parentLongId", longIdKey.getLongId())
                );
            }
            detachedCriteria.addOrder(Order.asc("index"));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void childForParentIndexDesc(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("parentLongId"));
            } else {
                LongIdKey longIdKey = (LongIdKey) objects[0];
                detachedCriteria.add(
                        Restrictions.eqOrIsNull("parentLongId", longIdKey.getLongId())
                );
            }
            detachedCriteria.addOrder(Order.desc("index"));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void childForParentIndexEq(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("parentLongId"));
            } else {
                LongIdKey longIdKey = (LongIdKey) objects[0];
                detachedCriteria.add(
                        Restrictions.eqOrIsNull("parentLongId", longIdKey.getLongId())
                );
            }
            Integer index = (Integer) objects[1];
            detachedCriteria.add(
                    Restrictions.eqOrIsNull("index", index)
            );
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void childForNodeChildForParentIndexAsc(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("nodeStringId"));
            } else {
                StringIdKey stringIdKey = (StringIdKey) objects[0];
                detachedCriteria.add(
                        Restrictions.eqOrIsNull("nodeStringId", stringIdKey.getStringId())
                );
            }
            if (Objects.isNull(objects[1])) {
                detachedCriteria.add(Restrictions.isNull("parentLongId"));
            } else {
                LongIdKey longIdKey = (LongIdKey) objects[1];
                detachedCriteria.add(
                        Restrictions.eqOrIsNull("parentLongId", longIdKey.getLongId())
                );
            }
            detachedCriteria.addOrder(Order.asc("index"));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void childForNodeChildForParentIndexDesc(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("nodeStringId"));
            } else {
                StringIdKey stringIdKey = (StringIdKey) objects[0];
                detachedCriteria.add(
                        Restrictions.eqOrIsNull("nodeStringId", stringIdKey.getStringId())
                );
            }
            if (Objects.isNull(objects[1])) {
                detachedCriteria.add(Restrictions.isNull("parentLongId"));
            } else {
                LongIdKey longIdKey = (LongIdKey) objects[1];
                detachedCriteria.add(
                        Restrictions.eqOrIsNull("parentLongId", longIdKey.getLongId())
                );
            }
            detachedCriteria.addOrder(Order.desc("index"));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void childForNodeChildForParentIndexEq(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("nodeStringId"));
            } else {
                StringIdKey stringIdKey = (StringIdKey) objects[0];
                detachedCriteria.add(
                        Restrictions.eqOrIsNull("nodeStringId", stringIdKey.getStringId())
                );
            }
            if (Objects.isNull(objects[1])) {
                detachedCriteria.add(Restrictions.isNull("parentLongId"));
            } else {
                LongIdKey longIdKey = (LongIdKey) objects[1];
                detachedCriteria.add(
                        Restrictions.eqOrIsNull("parentLongId", longIdKey.getLongId())
                );
            }
            Integer index = (Integer) objects[2];
            detachedCriteria.add(
                    Restrictions.eqOrIsNull("index", index)
            );
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void childForNodeNameLike(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("nodeStringId"));
            } else {
                StringIdKey stringIdKey = (StringIdKey) objects[0];
                detachedCriteria.add(
                        Restrictions.eqOrIsNull("nodeStringId", stringIdKey.getStringId())
                );
            }
            String pattern = (String) objects[1];
            detachedCriteria.add(Restrictions.like("name", pattern, MatchMode.ANYWHERE));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }
}
