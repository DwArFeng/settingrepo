package com.dwarfeng.settingrepo.stack.handler;

import com.dwarfeng.settingrepo.stack.exception.FormatterExecutionException;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.handler.Handler;

/**
 * 格式化器。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface Formatter extends Handler {

    /**
     * 执行格式化。
     *
     * @param args 格式化所需的参数组成的数组。
     * @return 参数格式化后形成的配置节点主键。
     * @throws FormatterExecutionException 格式化过程中出现异常。
     */
    StringIdKey format(Object[] args) throws FormatterExecutionException;
}
