package com.dwarfeng.settingrepo.stack.exception;

import com.dwarfeng.settingrepo.stack.bean.key.IahnNodeMekKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 国际化节点 Mek 不存在异常。
 *
 * @author DwArFeng
 * @since 2.1.0
 */
public class IahnNodeMekNotExistsException extends HandlerException {

    private static final long serialVersionUID = 8898498586070860781L;

    private final IahnNodeMekKey iahnNodeMekKey;

    public IahnNodeMekNotExistsException(IahnNodeMekKey iahnNodeMekKey) {
        this.iahnNodeMekKey = iahnNodeMekKey;
    }

    public IahnNodeMekNotExistsException(Throwable cause, IahnNodeMekKey iahnNodeMekKey) {
        super(cause);
        this.iahnNodeMekKey = iahnNodeMekKey;
    }

    @Override
    public String getMessage() {
        return "国际化节点 Mek " + iahnNodeMekKey + " 不存在";
    }
}
