# Setting Node - 设置节点命令

`settingnode` 用于判断、查看、初始化和移除设置节点。设置节点类型由整数 `type` 表示。

## 命令语法

```text
usage: settingnode -exists [-json json-string] [-jf json-file]
settingnode -inspect [-json json-string] [-jf json-file]
settingnode -init [-json json-string] [-jf json-file]
settingnode -remove [-json json-string] [-jf json-file]
```

## Sub Command

| option    | 长选项 | 用途                 | JSON 参考类                      | JSON 文件                                             |
|-----------|--------|----------------------|----------------------------------|-------------------------------------------------------|
| `exists`  | -      | 判断设置节点是否存在 | `WebInputSettingNodeExistsInfo`  | [exists.json](../json-file/settingnode/exists.json)   |
| `inspect` | -      | 查看设置节点         | `WebInputSettingNodeInspectInfo` | [inspect.json](../json-file/settingnode/inspect.json) |
| `init`    | -      | 初始化设置节点       | `WebInputSettingNodeInitInfo`    | [init.json](../json-file/settingnode/init.json)       |
| `remove`  | -      | 移除设置节点         | `WebInputSettingNodeRemoveInfo`  | [remove.json](../json-file/settingnode/remove.json)   |

## 字段说明

| 字段       | 适用操作 | 说明           |
|------------|----------|----------------|
| `category` | 全部     | 设置类别       |
| `args`     | 全部     | 节点格式化参数 |
| `type`     | init     | 节点类型整数   |
| `remark`   | init     | 节点备注       |

## 节点类型

|  值 | 类型         |
|----:|--------------|
| `0` | 文本节点     |
| `1` | 长文本节点   |
| `2` | 图片节点     |
| `3` | 图片列表节点 |
| `4` | 国际化节点   |
| `5` | 文件节点     |
| `6` | 文件列表节点 |
| `7` | 导航节点     |
| `8` | 键值对节点   |

## 风险提示

- `init` 会创建并初始化业务节点，节点类型必须与后续使用的操作命令一致。
- `remove` 会移除设置节点，执行前应确认节点是否仍被业务引用。
- `inspect` 和 `exists` 是只读操作，可用于写操作前确认节点状态。
