# Preset Formatter Implements - 预设格式化器实现

## 说明

本文档详细说明了本项目内置的所有格式化器。

内置格式化器注册器的代码位于 `com.dwarfeng.settingrepo.impl.handler.formatter` 包中，
您可以通过查看该包下的注册器类了解本项目内置的格式化器类型和构造方式。

项目中所有的内置格式化器如下：

| 类型               | 注册器                    | 实现              | 说明                                             |
|--------------------|---------------------------|-------------------|--------------------------------------------------|
| `groovy_formatter` | `GroovyFormatterRegistry` | `GroovyFormatter` | 通过自定义 Groovy 脚本根据参数生成设置节点主键。 |

以下实现的示例内容会作为 `FormatterSupport.exampleParam` 写入系统，
实际使用时应结合设置类别主键、节点类型和业务参数调整脚本逻辑。

## GroovyFormatterRegistry

### 介绍

`GroovyFormatterRegistry` 是基于 Groovy 脚本的格式化器注册器，类型为 `groovy_formatter`。
它从 `SettingCategory.formatterParam` 读取 Groovy 源码，使用 `GroovyClassLoader` 动态编译，
并要求脚本实现 `GroovyFormatterRegistry.Processor` 接口。

注册器创建 `GroovyFormatter` 时，会通过 Spring 容器对脚本处理器执行自动装配。
`GroovyFormatter` 使用原型作用域，每次根据设置类别构造独立实例，执行时将设置类别上下文和 `args` 传给脚本处理器。

### 参数

| 字段名           | 类型     | 说明                                                                 |
|------------------|----------|----------------------------------------------------------------------|
| `formatterType`  | `String` | 必须填写 `groovy_formatter`。                                        |
| `formatterParam` | `String` | Groovy 源码，必须定义实现 `GroovyFormatterRegistry.Processor` 的类。 |

脚本处理器需要实现以下方法：

| 方法签名                                                             | 说明                                     |
|----------------------------------------------------------------------|------------------------------------------|
| `StringIdKey format(SettingCategory settingCategory, String[] args)` | 根据设置类别和调用参数生成设置节点主键。 |

脚本可以使用 Spring 自动装配的 Bean，但应自行处理参数校验和脚本执行异常。
异常会由 `GroovyFormatter` 包装为 `FormatterExecutionException`。

### 示例参数

以下示例将所有调用参数按顺序拼接，并将拼接结果作为设置节点主键字符串：

```groovy
package com.dwarfeng.settingrepo.impl.groovy

import com.dwarfeng.settingrepo.impl.handler.formatter.GroovyFormatterRegistry
import com.dwarfeng.settingrepo.stack.bean.entity.SettingCategory
import com.dwarfeng.subgrade.basic.stack.bean.key.StringIdKey

/**
 * 示例格式化器。
 *
 * <p>
 * 将输入的所有参数简单的拼接在一起，形成的字符串作为主键的字符串返回。
 */
@SuppressWarnings("GrPackage")
class ExampleFormatterProcessor implements GroovyFormatterRegistry.Processor {

    @Override
    StringIdKey format(SettingCategory settingCategory, String[] args) throws Exception {
        StringBuilder sb = new StringBuilder()
        for (Object arg : args) {
            sb.append(arg)
        }
        return new StringIdKey(sb.toString())
    }
}
```

示例脚本对应资源文件：
`settingrepo-impl/src/main/resources/com/dwarfeng/settingrepo/impl/groovy/ExampleFormatterProcessor.groovy`。

## 参阅

- [Formatter](./Formatter.md) - 格式化器，详细说明了本项目的格式化器机制。
- [Opt Directory](./OptDirectory.md) - 可选配置目录说明，详细介绍了本项目的可选配置，即 `opt/` 目录下的内容。
