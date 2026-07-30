package com.dwarfeng.settingrepo.stack.exception;

import com.dwarfeng.settingrepo.stack.internal.i18n.StackMessageKey;
import com.dwarfeng.settingrepo.stack.internal.i18n.StackMessages;
import com.dwarfeng.subgrade.basic.stack.exception.HandlerException;

import java.io.Serial;

/**
 * 无效的国际化节点 Mek ID 异常。
 *
 * @author DwArFeng
 * @since 2.1.0
 */
public class InvalidIahnNodeMekIdException extends HandlerException {

    @Serial
    private static final long serialVersionUID = 1203099392270017880L;

    private final String mekId;

    public InvalidIahnNodeMekIdException(String mekId) {
        this.mekId = mekId;
    }

    public InvalidIahnNodeMekIdException(Throwable cause, String mekId) {
        super(cause);
        this.mekId = mekId;
    }

    @Override
    public String getMessage() {
        return StackMessages.message(StackMessageKey.INVALID_IAHN_NODE_MEK_ID, mekId);
    }
}
