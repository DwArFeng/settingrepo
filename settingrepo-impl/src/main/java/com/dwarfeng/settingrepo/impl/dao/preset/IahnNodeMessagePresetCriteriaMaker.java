package com.dwarfeng.settingrepo.impl.dao.preset;

import com.dwarfeng.settingrepo.sdk.util.IahnUtils;
import com.dwarfeng.settingrepo.stack.bean.key.IahnNodeLocaleKey;
import com.dwarfeng.settingrepo.stack.bean.key.IahnNodeMekKey;
import com.dwarfeng.settingrepo.stack.service.IahnNodeMessageMaintainService;
import com.dwarfeng.subgrade.sdk.hibernate.criteria.PresetCriteriaMaker;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;

@Component
public class IahnNodeMessagePresetCriteriaMaker implements PresetCriteriaMaker {

    @Override
    public void makeCriteria(DetachedCriteria detachedCriteria, String s, Object[] objects) {
        switch (s) {
            case IahnNodeMessageMaintainService.CHILD_FOR_NODE:
                childForNode(detachedCriteria, objects);
                break;
            case IahnNodeMessageMaintainService.CHILD_FOR_LOCALE:
                childForLocale(detachedCriteria, objects);
                break;
            case IahnNodeMessageMaintainService.CHILD_FOR_MEK:
                childForMek(detachedCriteria, objects);
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
                StringIdKey nodeKey = (StringIdKey) objects[0];
                detachedCriteria.add(Restrictions.eqOrIsNull("nodeStringId", nodeKey.getStringId()));
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    private void childForLocale(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("nodeStringId"));
            } else {
                IahnNodeLocaleKey localeKey = (IahnNodeLocaleKey) objects[0];
                detachedCriteria.add(Restrictions.eqOrIsNull("nodeStringId", localeKey.getNodeStringId()));
                detachedCriteria.add(Restrictions.eqOrIsNull(
                        "language", IahnUtils.preProcessRfc4546Field(localeKey.getLanguage())
                ));
                detachedCriteria.add(Restrictions.eqOrIsNull(
                        "country", IahnUtils.preProcessRfc4546Field(localeKey.getCountry())
                ));
                detachedCriteria.add(Restrictions.eqOrIsNull(
                        "variant", IahnUtils.preProcessRfc4546Field(localeKey.getVariant())
                ));
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    private void childForMek(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("nodeStringId"));
            } else {
                IahnNodeMekKey mekKey = (IahnNodeMekKey) objects[0];
                detachedCriteria.add(Restrictions.eqOrIsNull("nodeStringId", mekKey.getNodeStringId()));
                detachedCriteria.add(Restrictions.eqOrIsNull("mekId", IahnUtils.preProcessMekId(mekKey.getMekId())));
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }
}
