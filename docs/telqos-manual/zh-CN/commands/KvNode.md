# Kv Node - 键值对节点命令

`kvnode` 用于维护键值对节点中的条目。所有条目通过 `item_string_id` 唯一标识。

## 命令语法

```text
usage: kvnode -cnt,--count [-json json-string] [-jf json-file]
kvnode -i,--inspect [-json json-string] [-jf json-file]
kvnode -ii,--inspect-item [-json json-string] [-jf json-file]
kvnode -pi,--put-item [-json json-string] [-jf json-file]
kvnode -ri,--remove-item [-json json-string] [-jf json-file]
kvnode -clr,--clear [-json json-string] [-jf json-file]
```

## Sub Command

| option | 长选项         | 用途           | JSON 参考类                     | JSON 文件                                                  |
|--------|----------------|----------------|---------------------------------|------------------------------------------------------------|
| `cnt`  | `count`        | 查询条目数量   | `WebInputKvNodeCountInfo`       | [count.json](../json-file/kvnode/count.json)               |
| `i`    | `inspect`      | 查看键值对节点 | `WebInputKvNodeInspectInfo`     | [inspect.json](../json-file/kvnode/inspect.json)           |
| `ii`   | `inspect-item` | 查看指定条目   | `WebInputKvNodeItemInspectInfo` | [inspect-item.json](../json-file/kvnode/inspect-item.json) |
| `pi`   | `put-item`     | 写入指定条目   | `WebInputKvNodeItemPutInfo`     | [put-item.json](../json-file/kvnode/put-item.json)         |
| `ri`   | `remove-item`  | 移除指定条目   | `WebInputKvNodeItemRemoveInfo`  | [remove-item.json](../json-file/kvnode/remove-item.json)   |
| `clr`  | `clear`        | 清空节点       | `WebInputKvNodeClearInfo`       | [clear.json](../json-file/kvnode/clear.json)               |

## 字段说明

| 字段             | 适用操作   | 说明           |
|------------------|------------|----------------|
| `category`       | 全部       | 设置类别       |
| `args`           | 全部       | 节点格式化参数 |
| `item_string_id` | ii、pi、ri | 条目字符串 ID  |
| `value`          | pi         | 条目值         |

## 风险提示

- `pi` 会覆盖已有条目值。
- `ri` 只删除指定条目，`clr` 会清空整个键值对节点。
- 执行 `ri` 或 `clr` 前应确认节点定位和条目范围。
