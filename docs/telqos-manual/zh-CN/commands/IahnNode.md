# Iahn Node - 国际化节点命令

`iahnnode` 用于维护国际化地区、Mek 和消息。该命令包含 13 个 sub command。

## 命令语法

```text
usage: iahnnode -im,--inspect-message [-json json-string] [-jf json-file]
iahnnode -biml,--batch-inspect-message-by-locale [-json json-string] [-jf json-file]
iahnnode -ill,--inspect-locale-list [-json json-string] [-jf json-file]
iahnnode -iml,--inspect-mek-list [-json json-string] [-jf json-file]
iahnnode -imt,--inspect-message-table [-json json-string] [-jf json-file]
iahnnode -pl,--put-locale [-json json-string] [-jf json-file]
iahnnode -rl,--remove-locale [-json json-string] [-jf json-file]
iahnnode -pm,--put-mek [-json json-string] [-jf json-file]
iahnnode -rm,--remove-mek [-json json-string] [-jf json-file]
iahnnode -um,--upsert-message [-json json-string] [-jf json-file]
iahnnode -buml,--batch-upsert-message-by-locale [-json json-string] [-jf json-file]
iahnnode -bumm,--batch-upsert-message-by-mek [-json json-string] [-jf json-file]
iahnnode -cc,--clear-cache [-json json-string] [-jf json-file]
```

## Sub Command

| option | 长选项                            | 用途                      | JSON 参考类                                  | JSON 文件                                                                                          |
|--------|-----------------------------------|---------------------------|----------------------------------------------|----------------------------------------------------------------------------------------------------|
| `im`   | `inspect-message`                 | 查询单条消息              | `WebInputIahnNodeMessageInspectInfo`         | [inspect-message.json](../json-file/iahnnode/inspect-message.json)                                 |
| `biml` | `batch-inspect-message-by-locale` | 按地区批量查询消息        | `WebInputIahnNodeMessageInspectByLocaleInfo` | [batch-inspect-message-by-locale.json](../json-file/iahnnode/batch-inspect-message-by-locale.json) |
| `ill`  | `inspect-locale-list`             | 查询地区列表              | `WebInputIahnNodeLocaleListInspectInfo`      | [inspect-locale-list.json](../json-file/iahnnode/inspect-locale-list.json)                         |
| `iml`  | `inspect-mek-list`                | 查询 Mek 列表             | `WebInputIahnNodeMekListInspectInfo`         | [inspect-mek-list.json](../json-file/iahnnode/inspect-mek-list.json)                               |
| `imt`  | `inspect-message-table`           | 查询消息表                | `WebInputIahnNodeMessageTableInspectInfo`    | [inspect-message-table.json](../json-file/iahnnode/inspect-message-table.json)                     |
| `pl`   | `put-locale`                      | 推入地区                  | `WebInputIahnNodeLocalePutInfo`              | [put-locale.json](../json-file/iahnnode/put-locale.json)                                           |
| `rl`   | `remove-locale`                   | 移除地区                  | `WebInputIahnNodeLocaleRemoveInfo`           | [remove-locale.json](../json-file/iahnnode/remove-locale.json)                                     |
| `pm`   | `put-mek`                         | 推入 Mek                  | `WebInputIahnNodeMekPutInfo`                 | [put-mek.json](../json-file/iahnnode/put-mek.json)                                                 |
| `rm`   | `remove-mek`                      | 移除 Mek                  | `WebInputIahnNodeMekRemoveInfo`              | [remove-mek.json](../json-file/iahnnode/remove-mek.json)                                           |
| `um`   | `upsert-message`                  | 插入或更新单条消息        | `WebInputIahnNodeMessageUpsertInfo`          | [upsert-message.json](../json-file/iahnnode/upsert-message.json)                                   |
| `buml` | `batch-upsert-message-by-locale`  | 按地区批量插入或更新消息  | `WebInputIahnNodeMessageUpsertByLocaleInfo`  | [batch-upsert-message-by-locale.json](../json-file/iahnnode/batch-upsert-message-by-locale.json)   |
| `bumm` | `batch-upsert-message-by-mek`     | 按 Mek 批量插入或更新消息 | `WebInputIahnNodeMessageUpsertByMekInfo`     | [batch-upsert-message-by-mek.json](../json-file/iahnnode/batch-upsert-message-by-mek.json)         |
| `cc`   | `clear-cache`                     | 清除国际化节点缓存        | `WebInputIahnNodeClearCacheInfo`             | [clear-cache.json](../json-file/iahnnode/clear-cache.json)                                         |

## 公共字段

| 字段       | 说明                                                   |
|------------|--------------------------------------------------------|
| `category` | 设置类别                                               |
| `args`     | 节点格式化参数                                         |
| `language` | 语言，例如 `zh`、`en`、`ja`                            |
| `country`  | 国家或地区代码，例如 `CN`、`JP`；无国家限定时为 `null` |
| `variant`  | 地区变体；通常为 `null`                                |
| `mek_id`   | 消息的 Mek 标识                                        |

## 消息字段

| 操作   | 消息字段                                                      |
|--------|---------------------------------------------------------------|
| `im`   | `mek_id`                                                      |
| `um`   | `mek_id`、`message`                                           |
| `buml` | `items`，每项包含 `mek_id`、`message`                         |
| `bumm` | `items`，每项包含 `language`、`country`、`variant`、`message` |

地区批量操作使用 `language`、`country`、`variant` 作为地区限定。Mek 批量操作使用 `mek_id` 作为消息限定。

## 地区和 Mek 字段

| 操作  | 主要字段                                            |
|-------|-----------------------------------------------------|
| `ill` | `category`、`args`                                  |
| `iml` | `category`、`args`                                  |
| `imt` | `category`、`args`                                  |
| `pl`  | `language`、`country`、`variant`、`label`、`remark` |
| `rl`  | `language`、`country`、`variant`                    |
| `pm`  | `mek_id`、`label`、`default_message`、`remark`      |
| `rm`  | `mek_id`                                            |
| `cc`  | `category`、`args`                                  |

通过相对链接查看各操作的全部字段和已填充值：

- [`json-file/iahnnode`](../json-file/iahnnode)

## 风险提示

- `rl`、`rm` 会删除地区或 Mek 数据。
- `buml`、`bumm` 会批量更新消息，执行前应确认 `items` 的数量和内容。
- `cc` 只清除缓存，但后续查询可能重新加载数据库内容，应结合业务状态确认执行时机。
