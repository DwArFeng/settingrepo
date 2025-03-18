# ChangeLog

### Release_2.1.0_20250213_build_A

#### 功能构建

- 优化配置文件。
  - 优化 `application-context-database.xml`，使得更多属性可以在配置文件中配置。

- 修复部分功能性实体集合类型的字段在映射时有可能产生空指针异常的问题。
  - com.dwarfeng.settingrepo.sdk.bean.dto.FastJsonImageListNodeInspectResult。

- 优化部分类中部分方法的行为分析行为。
  - com.dwarfeng.settingrepo.impl.service.FormatterSupportMaintainServiceImpl。
  - com.dwarfeng.settingrepo.impl.service.ImageListNodeItemMaintainServiceImpl。
  - com.dwarfeng.settingrepo.impl.service.ImageListNodeMaintainServiceImpl。
  - com.dwarfeng.settingrepo.impl.service.ImageNodeMaintainServiceImpl。
  - com.dwarfeng.settingrepo.impl.service.SettingCategoryMaintainServiceImpl。
  - com.dwarfeng.settingrepo.impl.service.SettingNodeMaintainServiceImpl。
  - com.dwarfeng.settingrepo.impl.service.TextNodeMaintainServiceImpl。

- 新增 telqos 指令。
  - com.dwarfeng.settingrepo.impl.service.telqos.IahnNodeCommand。

- 新增实体操作服务。
  - com.dwarfeng.settingrepo.stack.service.IahnNodeOperateService。

- 建立实体以及维护服务，并通过单元测试。
  - com.dwarfeng.settingrepo.stack.bean.entity.IahnNode。
  - com.dwarfeng.settingrepo.stack.bean.entity.IahnNodeLocale。
  - com.dwarfeng.settingrepo.stack.bean.entity.IahnNodeMek。
  - com.dwarfeng.settingrepo.stack.bean.entity.IahnNodeMessage。

#### Bug修复

- 修正配置类中的错误。
  - ExceptionCodeOffsetConfiguration。

#### 功能移除

- (无)

---

### Release_2.0.0_20240802_build_B

#### 功能构建

- 依赖升级。
  - 升级 `subgrade` 依赖版本为 `1.5.5.a` 并解决兼容性问题，以规避漏洞。
  - 升级 `spring` 依赖版本为 `5.3.37` 以规避漏洞。
  - 升级 `spring-kafka` 依赖版本为 `2.9.11` 以规避漏洞。
  - 升级 `jetty` 依赖版本为 `9.4.55.v20240627` 以规避漏洞。
  - 升级 `netty` 依赖版本为 `4.1.108.Final` 以规避漏洞。
  - 升级 `snowflake` 依赖版本为 `1.5.2.a` 以规避漏洞。
  - 升级 `spring-terminator` 依赖版本为 `1.0.13.a` 以规避漏洞。
  - 升级 `spring-telqos` 依赖版本为 `1.1.10.a` 以规避漏洞。
  - 升级 `dwarfeng-ftp` 依赖版本为 `1.2.2.a` 以规避漏洞。

#### Bug修复

- (无)

#### 功能移除

- (无)

---

### Release_2.0.0_20240802_build_A

#### 功能构建

- 启动器优化。
  - 将入口方法中完整独立的功能封装在子方法中，使入口方法代码结构更加清晰。

- 优化启停脚本的目录结构。

- 优化部分维护服务实现中的部分方法的性能。
  - com.dwarfeng.settingrepo.impl.service.FormatterSupportMaintainServiceImpl。
  - com.dwarfeng.settingrepo.impl.service.ImageListNodeItemMaintainServiceImpl。
  - com.dwarfeng.settingrepo.impl.service.ImageListNodeMaintainServiceImpl。
  - com.dwarfeng.settingrepo.impl.service.ImageNodeMaintainServiceImpl。
  - com.dwarfeng.settingrepo.impl.service.SettingCategoryMaintainServiceImpl。
  - com.dwarfeng.settingrepo.impl.service.SettingNodeMaintainServiceImpl。
  - com.dwarfeng.settingrepo.impl.service.TextNodeMaintainServiceImpl。

- 更新 README.md。

- Wiki 编写。
  - 构建 wiki 目录结构。
  - docs/wiki/en_US/Contents.md。
  - docs/wiki/en_US/Introduction.md。
  - docs/wiki/zh_CN/Contents.md。
  - docs/wiki/zh_CN/Introduction.md。

- 增加设置节点相关逻辑。
  - 增加可以被访问属性，以及设置节点对应的节点分类和参数。
  - 通过可以被访问属性，可以判断设置节点是否可以被访问，并通过预设查询查询到可以被访问的设置节点。
  - 通过对应的节点分类和参数，可以反查到设置节点的分类和参数，以便于其它调用方使用。

- 增加预设查询。
  - com.dwarfeng.settingrepo.stack.service.SettingNodeMaintainService.REACHABLE。
  - com.dwarfeng.settingrepo.stack.service.SettingNodeMaintainService.ID_LIKE_REACHABLE。
  - com.dwarfeng.settingrepo.stack.service.SettingNodeMaintainService.CATEGORY_EQUALS。
  - com.dwarfeng.settingrepo.stack.service.SettingNodeMaintainService.UNREACHABLE。

- 增加实体字段。
  - com.dwarfeng.settingrepo.stack.bean.entity.SettingNode.reachable。
  - com.dwarfeng.settingrepo.stack.bean.entity.SettingNode.category。
  - com.dwarfeng.settingrepo.stack.bean.entity.SettingNode.args。

- 优化约束类中的常量值。
  - 增加 `Constraints.LENGTH_SETTING_CATEGORY_ID`，以约束设置类型 ID 的长度。
  - 增加 `Constraints.LENGTH_SETTING_NODE_ID`，以约束设置节点 ID 的长度。

- 新增依赖。
  - 增加依赖 `snowflake` 以应用其功能，版本为 `1.5.1.a`。
  - 增加依赖 `hessian` 以应用其功能，版本为 `4.0.38`。
  - 增加依赖 `javax.servlet-api` 以应用其功能，版本为 `4.0.1`。
  - 增加依赖 `jetty` 以应用其功能，版本为 `9.4.51.v20230217`。

- 新增实体操作服务。
  - com.dwarfeng.settingrepo.stack.service.ImageNodeOperateService。
  - com.dwarfeng.settingrepo.stack.service.ImageListNodeOperateService。

- 建立实体以及维护服务，并通过单元测试。
  - com.dwarfeng.settingrepo.stack.bean.entity.ImageNode。
  - com.dwarfeng.settingrepo.stack.bean.entity.ImageListNode。
  - com.dwarfeng.settingrepo.stack.bean.entity.ImageListNodeItem。
  - com.dwarfeng.settingrepo.stack.bean.entity.SettingNodeMetadata。

- 新增 telqos 指令。
  - com.dwarfeng.settingrepo.impl.service.telqos.SettingNodeCommand。
  - com.dwarfeng.settingrepo.impl.service.telqos.ImageNodeCommand。
  - com.dwarfeng.settingrepo.impl.service.telqos.ImageListNodeCommand。

- 增加预设的运维指令。
  - com.dwarfeng.springtelqos.api.integration.log4j2.Log4j2Command。

- 日志功能优化。
  - 优化默认日志配置，默认配置仅向控制台输出 `INFO` 级别的日志。
  - 优化日志配置结构，提供 `conf/logging/settings.xml` 配置文件及其不同平台的参考配置文件，以供用户自定义日志配置。
  - 优化日志配置结构，提供 `confext/logging-settings.xml` 配置文件，以供外部功能自定义日志配置。
  - 优化启动脚本，使服务支持新的日志配置结构。
  - 优化 `assembly.xml`，使项目打包时输出新的日志配置结构。
  - 优化 `confext/README.md`，添加新的日志配置结构的相关说明。

- 依赖升级。
  - 升级 `slf4j` 依赖版本为 `1.7.36` 以规避漏洞。
  - 升级 `subgrade` 依赖版本为 `1.5.3.a` 以规避漏洞。
  - 升级 `spring-telqos` 依赖版本为 `1.1.9.a` 以规避漏洞。

- 重构设置节点机制。
  - 增加设置节点的类型，使其除了能够存储文本外，还可以存储其它类型的数据。
  - 优化接口方法，使其更加易用。

#### Bug修复

- 修正 `telqos/my-banner.txt` 中不正确的文本内容。

- 补全 .gitignore 中缺失的配置项。

- 修复 telqos 工具类中部分注解不正确的 bug。

#### 功能移除

- (无)
