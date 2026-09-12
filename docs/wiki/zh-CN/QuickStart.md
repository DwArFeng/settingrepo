# Quick Start - 快速开始

## 确认系统需求

- CPU：2 核以上。
- 内存：4G 以上。
- 硬盘：100G 以上。
- CentOS 7。
- JDK 25。
- MySQL 8.0.19。
- Redis 5.0.7。
- Zookeeper 3.5.5。
- FTP 服务器。
- snowflake-distributed-service 1.6.4.a。

## 获取软件包

从 Github 上获取软件包，软件包可以从 Github 的 Release 页面下载。

## 解压软件包

软件包的名称格式为 `settingrepo-all-he-${version}-release.tar.gz`，其中 `${version}` 为软件包的版本号。

使用工具软件，将软件包上传至服务器 `/usr/local` 目录下，解压软件包。

```shell
cd /usr/local
tar -zxvf settingrepo-all-he-${version}-release.tar.gz
mv settingrepo-all-he-${version}-release/settingrepo-all-he-${version} settingrepo
```

## 数据库初始化

连接到 MySQL 数据库，执行如下 SQL 语句：

```sql
SET NAMES utf8mb4;

CREATE DATABASE IF NOT EXISTS `settingrepo`
    DEFAULT CHARACTER SET utf8mb4
    COLLATE utf8mb4_0900_bin;

USE `settingrepo`;

CREATE TABLE IF NOT EXISTS `tbl_formatter_support` (
  `id` varchar(50) COLLATE utf8mb4_0900_bin NOT NULL,
  `description` text COLLATE utf8mb4_0900_bin,
  `example_param` text COLLATE utf8mb4_0900_bin,
  `label` varchar(255) COLLATE utf8mb4_0900_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_bin;

CREATE TABLE IF NOT EXISTS `tbl_setting_category` (
  `id` varchar(100) COLLATE utf8mb4_0900_bin NOT NULL,
  `created_datamark` varchar(100) COLLATE utf8mb4_0900_bin DEFAULT NULL,
  `formatter_param` text COLLATE utf8mb4_0900_bin,
  `formatter_type` varchar(50) COLLATE utf8mb4_0900_bin DEFAULT NULL,
  `modified_datamark` varchar(100) COLLATE utf8mb4_0900_bin DEFAULT NULL,
  `remark` varchar(100) COLLATE utf8mb4_0900_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_bin;

CREATE TABLE IF NOT EXISTS `tbl_setting_node` (
  `id` varchar(250) COLLATE utf8mb4_0900_bin NOT NULL,
  `args` text COLLATE utf8mb4_0900_bin,
  `category` varchar(100) COLLATE utf8mb4_0900_bin DEFAULT NULL,
  `created_datamark` varchar(100) COLLATE utf8mb4_0900_bin DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `modified_datamark` varchar(100) COLLATE utf8mb4_0900_bin DEFAULT NULL,
  `reachable` bit(1) DEFAULT NULL,
  `remark` varchar(100) COLLATE utf8mb4_0900_bin DEFAULT NULL,
  `type` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_bin;

CREATE TABLE IF NOT EXISTS `tbl_text_node` (
  `id` varchar(250) COLLATE utf8mb4_0900_bin NOT NULL,
  `created_datamark` varchar(100) COLLATE utf8mb4_0900_bin DEFAULT NULL,
  `modified_datamark` varchar(100) COLLATE utf8mb4_0900_bin DEFAULT NULL,
  `value` mediumtext COLLATE utf8mb4_0900_bin,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_bin;

CREATE TABLE IF NOT EXISTS `tbl_long_text_node` (
  `id` varchar(250) COLLATE utf8mb4_0900_bin NOT NULL,
  `created_datamark` varchar(100) COLLATE utf8mb4_0900_bin DEFAULT NULL,
  `length` bigint DEFAULT NULL,
  `modified_datamark` varchar(100) COLLATE utf8mb4_0900_bin DEFAULT NULL,
  `null_flag` bit(1) DEFAULT NULL,
  `preview` varchar(1000) COLLATE utf8mb4_0900_bin DEFAULT NULL,
  `store_name` varchar(100) COLLATE utf8mb4_0900_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_bin;

CREATE TABLE IF NOT EXISTS `tbl_image_node` (
  `id` varchar(250) COLLATE utf8mb4_0900_bin NOT NULL,
  `created_datamark` varchar(100) COLLATE utf8mb4_0900_bin DEFAULT NULL,
  `length` bigint DEFAULT NULL,
  `modified_datamark` varchar(100) COLLATE utf8mb4_0900_bin DEFAULT NULL,
  `origin_name` varchar(100) COLLATE utf8mb4_0900_bin NOT NULL,
  `store_name` varchar(100) COLLATE utf8mb4_0900_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_bin;

CREATE TABLE IF NOT EXISTS `tbl_image_list_node` (
  `id` varchar(250) COLLATE utf8mb4_0900_bin NOT NULL,
  `created_datamark` varchar(100) COLLATE utf8mb4_0900_bin DEFAULT NULL,
  `modified_datamark` varchar(100) COLLATE utf8mb4_0900_bin DEFAULT NULL,
  `size` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_bin;

CREATE TABLE IF NOT EXISTS `tbl_image_list_node_item` (
  `id` bigint NOT NULL,
  `column_index` int NOT NULL,
  `length` bigint DEFAULT NULL,
  `node_id` varchar(250) COLLATE utf8mb4_0900_bin DEFAULT NULL,
  `null_flag` bit(1) NOT NULL,
  `origin_name` varchar(100) COLLATE utf8mb4_0900_bin DEFAULT NULL,
  `store_name` varchar(100) COLLATE utf8mb4_0900_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKo585o0wl8q1rwn8qaffay06k8` (`node_id`),
  CONSTRAINT `FKo585o0wl8q1rwn8qaffay06k8` FOREIGN KEY (`node_id`) REFERENCES `tbl_image_list_node` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_bin;

CREATE TABLE IF NOT EXISTS `tbl_iahn_node` (
  `id` varchar(250) COLLATE utf8mb4_0900_bin NOT NULL,
  `created_datamark` varchar(100) COLLATE utf8mb4_0900_bin DEFAULT NULL,
  `modified_datamark` varchar(100) COLLATE utf8mb4_0900_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_bin;

CREATE TABLE IF NOT EXISTS `tbl_iahn_node_locale` (
  `country` varchar(3) COLLATE utf8mb4_0900_bin NOT NULL,
  `language` varchar(3) COLLATE utf8mb4_0900_bin NOT NULL,
  `node_string_id` varchar(250) COLLATE utf8mb4_0900_bin NOT NULL,
  `variant` varchar(8) COLLATE utf8mb4_0900_bin NOT NULL,
  `label` varchar(100) COLLATE utf8mb4_0900_bin DEFAULT NULL,
  `remark` varchar(100) COLLATE utf8mb4_0900_bin DEFAULT NULL,
  PRIMARY KEY (`country`,`language`,`node_string_id`,`variant`),
  KEY `FKrw544dw8tnpr6ki63bc6x5ccs` (`node_string_id`),
  CONSTRAINT `FKrw544dw8tnpr6ki63bc6x5ccs` FOREIGN KEY (`node_string_id`) REFERENCES `tbl_iahn_node` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_bin;

CREATE TABLE IF NOT EXISTS `tbl_iahn_node_mek` (
  `mek_id` varchar(100) COLLATE utf8mb4_0900_bin NOT NULL,
  `node_string_id` varchar(250) COLLATE utf8mb4_0900_bin NOT NULL,
  `default_message` varchar(250) COLLATE utf8mb4_0900_bin DEFAULT NULL,
  `label` varchar(255) COLLATE utf8mb4_0900_bin DEFAULT NULL,
  `remark` varchar(100) COLLATE utf8mb4_0900_bin DEFAULT NULL,
  PRIMARY KEY (`mek_id`,`node_string_id`),
  KEY `FK4prndybmln1qopvo0nawj3f1g` (`node_string_id`),
  CONSTRAINT `FK4prndybmln1qopvo0nawj3f1g` FOREIGN KEY (`node_string_id`) REFERENCES `tbl_iahn_node` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_bin;

CREATE TABLE IF NOT EXISTS `tbl_iahn_node_message` (
  `country` varchar(3) COLLATE utf8mb4_0900_bin NOT NULL,
  `language` varchar(3) COLLATE utf8mb4_0900_bin NOT NULL,
  `mek_id` varchar(100) COLLATE utf8mb4_0900_bin NOT NULL,
  `node_string_id` varchar(250) COLLATE utf8mb4_0900_bin NOT NULL,
  `variant` varchar(8) COLLATE utf8mb4_0900_bin NOT NULL,
  `message` varchar(250) COLLATE utf8mb4_0900_bin NOT NULL,
  PRIMARY KEY (`country`,`language`,`mek_id`,`node_string_id`,`variant`),
  KEY `FKoqd3jeo3gub850c03uukhcr19` (`country`,`language`,`node_string_id`,`variant`),
  KEY `FKh4012vh3rkywjfmb4529jjujs` (`mek_id`,`node_string_id`),
  KEY `FKcex4c8qw2nqf0amni4wqcx4td` (`node_string_id`),
  CONSTRAINT `FKcex4c8qw2nqf0amni4wqcx4td` FOREIGN KEY (`node_string_id`) REFERENCES `tbl_iahn_node` (`id`),
  CONSTRAINT `FKh4012vh3rkywjfmb4529jjujs` FOREIGN KEY (`mek_id`, `node_string_id`) REFERENCES `tbl_iahn_node_mek` (`mek_id`, `node_string_id`),
  CONSTRAINT `FKoqd3jeo3gub850c03uukhcr19` FOREIGN KEY (`country`, `language`, `node_string_id`, `variant`) REFERENCES `tbl_iahn_node_locale` (`country`, `language`, `node_string_id`, `variant`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_bin;

CREATE TABLE IF NOT EXISTS `tbl_file_node` (
  `id` varchar(250) COLLATE utf8mb4_0900_bin NOT NULL,
  `created_datamark` varchar(100) COLLATE utf8mb4_0900_bin DEFAULT NULL,
  `length` bigint DEFAULT NULL,
  `modified_datamark` varchar(100) COLLATE utf8mb4_0900_bin DEFAULT NULL,
  `origin_name` varchar(100) COLLATE utf8mb4_0900_bin NOT NULL,
  `store_name` varchar(100) COLLATE utf8mb4_0900_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_bin;

CREATE TABLE IF NOT EXISTS `tbl_file_list_node` (
  `id` varchar(250) COLLATE utf8mb4_0900_bin NOT NULL,
  `created_datamark` varchar(100) COLLATE utf8mb4_0900_bin DEFAULT NULL,
  `modified_datamark` varchar(100) COLLATE utf8mb4_0900_bin DEFAULT NULL,
  `size` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_bin;

CREATE TABLE IF NOT EXISTS `tbl_file_list_node_item` (
  `id` bigint NOT NULL,
  `column_index` int NOT NULL,
  `length` bigint DEFAULT NULL,
  `node_id` varchar(250) COLLATE utf8mb4_0900_bin DEFAULT NULL,
  `null_flag` bit(1) NOT NULL,
  `origin_name` varchar(100) COLLATE utf8mb4_0900_bin DEFAULT NULL,
  `store_name` varchar(100) COLLATE utf8mb4_0900_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKg3en800re15qo2rgryvgov47m` (`node_id`),
  CONSTRAINT `FKg3en800re15qo2rgryvgov47m` FOREIGN KEY (`node_id`) REFERENCES `tbl_file_list_node` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_bin;

CREATE TABLE IF NOT EXISTS `tbl_navigation_node` (
  `id` varchar(250) COLLATE utf8mb4_0900_bin NOT NULL,
  `content` varchar(255) COLLATE utf8mb4_0900_bin DEFAULT NULL,
  `created_datamark` varchar(100) COLLATE utf8mb4_0900_bin DEFAULT NULL,
  `modified_datamark` varchar(100) COLLATE utf8mb4_0900_bin DEFAULT NULL,
  `size` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_bin;

CREATE TABLE IF NOT EXISTS `tbl_navigation_node_item` (
  `id` bigint NOT NULL,
  `content` text COLLATE utf8mb4_0900_bin,
  `column_index` int DEFAULT NULL,
  `name` varchar(50) COLLATE utf8mb4_0900_bin DEFAULT NULL,
  `node_id` varchar(250) COLLATE utf8mb4_0900_bin DEFAULT NULL,
  `parent_id` bigint DEFAULT NULL,
  `remark` varchar(100) COLLATE utf8mb4_0900_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1jj3naaxvf4o6fpfam566lqlp` (`node_id`),
  KEY `FKf3khsqkbf87fsv7i00xjjapy6` (`parent_id`),
  CONSTRAINT `FK1jj3naaxvf4o6fpfam566lqlp` FOREIGN KEY (`node_id`) REFERENCES `tbl_navigation_node` (`id`),
  CONSTRAINT `FKf3khsqkbf87fsv7i00xjjapy6` FOREIGN KEY (`parent_id`) REFERENCES `tbl_navigation_node_item` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_bin;

CREATE TABLE IF NOT EXISTS `tbl_kv_node` (
  `id` varchar(250) COLLATE utf8mb4_0900_bin NOT NULL,
  `count` int DEFAULT NULL,
  `created_datamark` varchar(100) COLLATE utf8mb4_0900_bin DEFAULT NULL,
  `modified_datamark` varchar(100) COLLATE utf8mb4_0900_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_bin;

CREATE TABLE IF NOT EXISTS `tbl_kv_node_item` (
  `id` varchar(100) COLLATE utf8mb4_0900_bin NOT NULL,
  `node_id` varchar(250) COLLATE utf8mb4_0900_bin NOT NULL,
  `value` text COLLATE utf8mb4_0900_bin,
  PRIMARY KEY (`id`,`node_id`),
  KEY `FK4xg43n95akvlges1y5iqewn8r` (`node_id`),
  CONSTRAINT `FK4xg43n95akvlges1y5iqewn8r` FOREIGN KEY (`node_id`) REFERENCES `tbl_kv_node` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_bin;

INSERT INTO `tbl_formatter_support` (`id`, `description`, `example_param`, `label`)
VALUES (
  'groovy_formatter',
  '通过自定义的 Groovy 脚本将设置类别参数格式化为设置节点主键。',
  'import com.dwarfeng.settingrepo.impl.handler.formatter.GroovyFormatterRegistry
import com.dwarfeng.settingrepo.stack.bean.entity.SettingCategory
import com.dwarfeng.subgrade.basic.stack.bean.key.StringIdKey

@SuppressWarnings("GrPackage")
class ExampleFormatterProcessor implements GroovyFormatterRegistry.Processor {
    @Override
    StringIdKey format(SettingCategory settingCategory, String[] args) throws Exception {
        return new StringIdKey(settingCategory.getKey().getStringId() + "." + args[0])
    }
}
',
  'Groovy 格式化器'
)
ON DUPLICATE KEY UPDATE
  `description` = VALUES(`description`),
  `example_param` = VALUES(`example_param`),
  `label` = VALUES(`label`);

-- QuickStart 演示类别：使用单个参数生成 quickstart.demo.<参数> 节点主键。
INSERT INTO `tbl_setting_category` (
  `id`, `created_datamark`, `formatter_param`, `formatter_type`, `modified_datamark`, `remark`
)
VALUES (
  'quickstart.demo',
  'settingrepo-node',
  'import com.dwarfeng.settingrepo.impl.handler.formatter.GroovyFormatterRegistry
import com.dwarfeng.settingrepo.stack.bean.entity.SettingCategory
import com.dwarfeng.subgrade.basic.stack.bean.key.StringIdKey

@SuppressWarnings("GrPackage")
class QuickStartFormatterProcessor implements GroovyFormatterRegistry.Processor {

    @Override
    StringIdKey format(SettingCategory settingCategory, String[] args) throws Exception {
        if (args.length != 1) {
            throw new IllegalArgumentException("QuickStart formatter requires exactly one argument")
        }
        return new StringIdKey(settingCategory.getKey().getStringId() + "." + args[0])
    }
}
',
  'groovy_formatter',
  'settingrepo-node',
  'QuickStart 典型设置类别，覆盖文本、键值、导航和国际化节点'
)
ON DUPLICATE KEY UPDATE
  `created_datamark` = VALUES(`created_datamark`),
  `formatter_param` = VALUES(`formatter_param`),
  `formatter_type` = VALUES(`formatter_type`),
  `modified_datamark` = VALUES(`modified_datamark`),
  `remark` = VALUES(`remark`);

-- 节点类型：0-文本，4-国际化，7-导航，8-键值。
INSERT INTO `tbl_setting_node` (
  `id`, `args`, `category`, `created_datamark`, `last_modified_date`, `modified_datamark`, `reachable`,
  `remark`, `type`
)
VALUES
  (
    'quickstart.demo.text', '["text"]', 'quickstart.demo', 'settingrepo-node',
    '2026-01-01 00:00:00.000000', 'settingrepo-node', b'1', 'QuickStart 应用基础配置', 0
  ),
  (
    'quickstart.demo.iahn', '["iahn"]', 'quickstart.demo', 'settingrepo-node',
    '2026-01-01 00:00:00.000000', 'settingrepo-node', b'1', 'QuickStart 中英文国际化文案', 4
  ),
  (
    'quickstart.demo.long-text', '["long-text"]', 'quickstart.demo', 'settingrepo-node',
    '2026-01-01 00:00:00.000000', 'settingrepo-node', b'1', 'QuickStart 可为空的长文本节点', 1
  ),
  (
    'quickstart.demo.navigation', '["navigation"]', 'quickstart.demo', 'settingrepo-node',
    '2026-01-01 00:00:00.000000', 'settingrepo-node', b'1', 'QuickStart 分层导航菜单', 7
  ),
  (
    'quickstart.demo.kv', '["kv"]', 'quickstart.demo', 'settingrepo-node',
    '2026-01-01 00:00:00.000000', 'settingrepo-node', b'1', 'QuickStart 功能开关与运行参数', 8
  )
ON DUPLICATE KEY UPDATE
  `args` = VALUES(`args`),
  `category` = VALUES(`category`),
  `created_datamark` = VALUES(`created_datamark`),
  `last_modified_date` = VALUES(`last_modified_date`),
  `modified_datamark` = VALUES(`modified_datamark`),
  `reachable` = VALUES(`reachable`),
  `remark` = VALUES(`remark`),
  `type` = VALUES(`type`);

INSERT INTO `tbl_text_node` (`id`, `created_datamark`, `modified_datamark`, `value`)
VALUES (
  'quickstart.demo.text',
  'settingrepo-node',
  'settingrepo-node',
  '{"applicationName":"SettingRepo QuickStart","theme":"light","pageSize":20,"maintenance":false}'
)
ON DUPLICATE KEY UPDATE
  `created_datamark` = VALUES(`created_datamark`),
  `modified_datamark` = VALUES(`modified_datamark`),
  `value` = VALUES(`value`);

-- 空值长文本是合法状态，读取时返回 null，不要求预先准备 FTP 文件。
INSERT INTO `tbl_long_text_node` (
  `id`, `created_datamark`, `length`, `modified_datamark`, `null_flag`, `preview`, `store_name`
)
VALUES (
  'quickstart.demo.long-text', 'settingrepo-node', 0, 'settingrepo-node', b'1', NULL,
  'quickstart.demo.long-text.empty'
)
ON DUPLICATE KEY UPDATE
  `created_datamark` = VALUES(`created_datamark`),
  `length` = VALUES(`length`),
  `modified_datamark` = VALUES(`modified_datamark`),
  `null_flag` = VALUES(`null_flag`),
  `preview` = VALUES(`preview`),
  `store_name` = VALUES(`store_name`);

INSERT INTO `tbl_kv_node` (`id`, `count`, `created_datamark`, `modified_datamark`)
VALUES ('quickstart.demo.kv', 4, 'settingrepo-node', 'settingrepo-node')
ON DUPLICATE KEY UPDATE
  `count` = VALUES(`count`),
  `created_datamark` = VALUES(`created_datamark`),
  `modified_datamark` = VALUES(`modified_datamark`);

INSERT INTO `tbl_kv_node_item` (`id`, `node_id`, `value`)
VALUES
  ('feature.audit.enabled', 'quickstart.demo.kv', 'true'),
  ('feature.preview.enabled', 'quickstart.demo.kv', 'true'),
  ('page.default-size', 'quickstart.demo.kv', '20'),
  ('theme.default', 'quickstart.demo.kv', 'light')
ON DUPLICATE KEY UPDATE
  `value` = VALUES(`value`);

INSERT INTO `tbl_navigation_node` (`id`, `content`, `created_datamark`, `modified_datamark`, `size`)
VALUES (
  'quickstart.demo.navigation',
  '{"defaultNodeKey":"quickstart.dashboard","ezNavEnabled":true,"ezNavMaxActiveItem":10,"ezNavRestoreWhenLogin":"restore-pinned"}',
  'settingrepo-node',
  'settingrepo-node',
  3
)
ON DUPLICATE KEY UPDATE
  `content` = VALUES(`content`),
  `created_datamark` = VALUES(`created_datamark`),
  `modified_datamark` = VALUES(`modified_datamark`),
  `size` = VALUES(`size`);

INSERT INTO `tbl_navigation_node_item` (
  `id`, `content`, `column_index`, `name`, `node_id`, `parent_id`, `remark`
)
VALUES (
  1,
  '{
  "key": "quickstart",
  "display": {"": {"label": "QuickStart"}},
  "menu": {"shown": true},
  "router": {"required": false, "path": "", "component": {"key": ""}},
  "permission": {"required": false}
}',
  0,
  'quickstart',
  'quickstart.demo.navigation',
  NULL,
  'QuickStart 根菜单'
)
ON DUPLICATE KEY UPDATE
  `content` = VALUES(`content`),
  `column_index` = VALUES(`column_index`),
  `name` = VALUES(`name`),
  `node_id` = VALUES(`node_id`),
  `parent_id` = VALUES(`parent_id`),
  `remark` = VALUES(`remark`);

INSERT INTO `tbl_navigation_node_item` (
  `id`, `content`, `column_index`, `name`, `node_id`, `parent_id`, `remark`
)
VALUES
  (
    2,
    '{
  "key": "quickstart.dashboard",
  "display": {"": {"label": "运行概览"}},
  "menu": {"shown": true},
  "router": {"required": true, "path": "quickstart/dashboard", "component": {"key": "quickstart.dashboard"}},
  "permission": {"required": false}
}',
    10,
    'quickstart.dashboard',
    'quickstart.demo.navigation',
    1,
    'QuickStart 概览菜单'
  ),
  (
    3,
    '{
  "key": "quickstart.settings",
  "display": {"": {"label": "参数设置"}},
  "menu": {"shown": true},
  "router": {"required": true, "path": "quickstart/settings", "component": {"key": "quickstart.settings"}},
  "permission": {"required": false}
}',
    20,
    'quickstart.settings',
    'quickstart.demo.navigation',
    1,
    'QuickStart 设置菜单'
  )
ON DUPLICATE KEY UPDATE
  `content` = VALUES(`content`),
  `column_index` = VALUES(`column_index`),
  `name` = VALUES(`name`),
  `node_id` = VALUES(`node_id`),
  `parent_id` = VALUES(`parent_id`),
  `remark` = VALUES(`remark`);

INSERT INTO `tbl_iahn_node` (`id`, `created_datamark`, `modified_datamark`)
VALUES ('quickstart.demo.iahn', 'settingrepo-node', 'settingrepo-node')
ON DUPLICATE KEY UPDATE
  `created_datamark` = VALUES(`created_datamark`),
  `modified_datamark` = VALUES(`modified_datamark`);

INSERT INTO `tbl_iahn_node_locale` (
  `country`, `language`, `node_string_id`, `variant`, `label`, `remark`
)
VALUES
  ('', '', 'quickstart.demo.iahn', '', '默认地区', '未匹配地区时使用消息键的默认文案'),
  ('cn', 'zh', 'quickstart.demo.iahn', '', '简体中文', 'QuickStart 中文文案'),
  ('us', 'en', 'quickstart.demo.iahn', '', 'English (US)', 'QuickStart English messages')
ON DUPLICATE KEY UPDATE
  `label` = VALUES(`label`),
  `remark` = VALUES(`remark`);

INSERT INTO `tbl_iahn_node_mek` (`mek_id`, `node_string_id`, `default_message`, `label`, `remark`)
VALUES
  ('app.title', 'quickstart.demo.iahn', 'SettingRepo QuickStart', '应用标题', 'QuickStart 应用标题'),
  ('action.save.success', 'quickstart.demo.iahn', '保存成功', '保存成功提示', 'QuickStart 操作反馈'),
  ('welcome', 'quickstart.demo.iahn', '欢迎使用 SettingRepo', '欢迎语', 'QuickStart 欢迎文案')
ON DUPLICATE KEY UPDATE
  `default_message` = VALUES(`default_message`),
  `label` = VALUES(`label`),
  `remark` = VALUES(`remark`);

INSERT INTO `tbl_iahn_node_message` (
  `country`, `language`, `mek_id`, `node_string_id`, `variant`, `message`
)
VALUES
  ('cn', 'zh', 'app.title', 'quickstart.demo.iahn', '', 'SettingRepo 快速开始'),
  ('cn', 'zh', 'action.save.success', 'quickstart.demo.iahn', '', '设置已保存'),
  ('cn', 'zh', 'welcome', 'quickstart.demo.iahn', '', '欢迎使用 SettingRepo'),
  ('us', 'en', 'app.title', 'quickstart.demo.iahn', '', 'SettingRepo QuickStart'),
  ('us', 'en', 'action.save.success', 'quickstart.demo.iahn', '', 'Settings saved'),
  ('us', 'en', 'welcome', 'quickstart.demo.iahn', '', 'Welcome to SettingRepo')
ON DUPLICATE KEY UPDATE
  `message` = VALUES(`message`);
```

## 最小化配置

下文列出了启动程序需要改动的最少的配置文件，每个配置文件中仅展示需要改动的配置项。

`conf/database/connection.properties` 文件中配置数据库连接信息。

```properties
com.dwarfeng.settingrepo.jdbc.url=jdbc:mysql://your-host-here:3306/settingrepo?serverTimezone=Asia/Shanghai&autoReconnect=true
com.dwarfeng.settingrepo.jdbc.username=root
com.dwarfeng.settingrepo.jdbc.password=your-password-here
```

`conf/dubbo/connection.properties` 文件中配置 dubbo 连接信息。

```properties
com.dwarfeng.settingrepo.dubbo.registry.zookeeper.address=zookeeper://your-host-here:2181
com.dwarfeng.settingrepo.dubbo.protocol.dubbo.host=your-host-here
```

`conf/ftp/connection.properties` 文件中配置 FTP 连接信息。程序启动时会建立 FTP 连接，因此即使本文不演示文件节点，也需要提供可用的 FTP 服务。

```properties
com.dwarfeng.settingrepo.ftp.host=your-host-here
com.dwarfeng.settingrepo.ftp.port=21
com.dwarfeng.settingrepo.ftp.username=your-username-here
com.dwarfeng.settingrepo.ftp.password=your-password-here
```

`conf/redis/connection.properties` 文件中配置 redis 连接信息。

```properties
com.dwarfeng.settingrepo.redis.hostName=your-host-here
com.dwarfeng.settingrepo.redis.port=6379
com.dwarfeng.settingrepo.redis.password=your-password-here
```

`conf/settingrepo/push.properties` 文件中配置推送器类型。本文不接入外部推送通道，可使用 `drain` 推送器丢弃推送事件。

```properties
com.dwarfeng.settingrepo.pusher.type=drain
```

## 修改可选配置

下文列出了本文演示链路需要启用的 `opt/` 配置。发布包中对应实现默认以注释形式提供，可直接将对应文件调整为本节展示的完整配置。

`opt/opt-formatter.xml` 文件中启用 Groovy 格式化器。本文使用 Groovy 格式化器将设置类别参数格式化为设置节点主键。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!--suppress SpringFacetInspection -->
<beans
        xmlns:context="http://www.springframework.org/schema/context"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xmlns="http://www.springframework.org/schema/beans"
        xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd"
>

    <!--扫描 handler 的实现包。 -->
    <context:component-scan base-package="com.dwarfeng.settingrepo.impl.handler.formatter" use-default-filters="false">
        <!-- 加载 GroovyFormatter -->
        <context:include-filter
                type="assignable" expression="com.dwarfeng.settingrepo.impl.handler.formatter.GroovyFormatterRegistry"
        />
    </context:component-scan>
</beans>
```

`opt/opt-pusher.xml` 文件中启用 `drain` 推送器。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!--suppress SpringFacetInspection -->
<beans
        xmlns:context="http://www.springframework.org/schema/context"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xmlns="http://www.springframework.org/schema/beans"
        xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd"
>

    <!-- 扫描 handler 的实现包。 -->
    <context:component-scan base-package="com.dwarfeng.settingrepo.impl.handler.pusher" use-default-filters="false">
        <!-- 加载 DrainPusher -->
        <context:include-filter
                type="assignable" expression="com.dwarfeng.settingrepo.impl.handler.pusher.DrainPusher"
        />
    </context:component-scan>
</beans>
```

`opt/opt-resetter.xml` 文件中启用 `NeverResetter`。本文不需要定时重置格式化器缓存。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!--suppress SpringFacetInspection -->
<beans
        xmlns:context="http://www.springframework.org/schema/context"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xmlns="http://www.springframework.org/schema/beans"
        xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd"
>

    <!-- 扫描 handler 的实现包。 -->
    <context:component-scan base-package="com.dwarfeng.settingrepo.impl.handler.resetter" use-default-filters="false">
        <!-- 加载 NeverResetter -->
        <context:include-filter
                type="assignable" expression="com.dwarfeng.settingrepo.impl.handler.resetter.NeverResetter"
        />
    </context:component-scan>
</beans>
```

## 启动程序

在 `/usr/local/settingrepo` 目录下执行如下命令：

```shell
sh  bin/settingrepo-start.sh
```

1. 使用 telnet 连接 Telqos 运维平台，默认端口为 `23`。

```shell
telnet 127.0.0.1 23
```

2. 查看演示文本节点。Groovy 格式化器会将类别 `quickstart.demo` 与参数 `text` 格式化为节点主键 `quickstart.demo.text`。

```text
txtnode -inspect -json {"category":"quickstart.demo","args":["text"]}
查看结果:
  值: {"applicationName":"SettingRepo QuickStart","theme":"light","pageSize":20,"maintenance":false}
OK
```

3. 查看演示键值节点中的指定条目。

```text
kvnode -ii -json {"category":"quickstart.demo","args":["kv"],"item_string_id":"theme.default"}
查询结果:
  值: light
OK
```

4. 向演示文本节点写入新的配置值。

```text
txtnode -put -json {"category":"quickstart.demo","args":["text"],"value":"SettingRepo-QuickStart-is-running"}
OK
```

5. 再次查看演示文本节点，确认新值已经写入。

```text
txtnode -inspect -json {"category":"quickstart.demo","args":["text"]}
查看结果:
  值: SettingRepo-QuickStart-is-running
OK
```

6. 观察数据库，程序会在 `tbl_text_node` 表中写入 `id` 为 `quickstart.demo.text` 的文本配置。
7. 观察数据库，程序会在 `tbl_kv_node_item` 表中写入 `node_id` 为 `quickstart.demo.kv` 的功能开关与运行参数。

## 停止程序

在 `/usr/local/settingrepo` 目录下执行如下命令：

```shell
sh  bin/settingrepo-stop.sh
```
