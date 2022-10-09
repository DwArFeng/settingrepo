# ChangeLog

### Release_1.0.3_20221009_build_A

#### 功能构建

- 依赖升级。
  - 升级 `junit` 依赖版本为 `4.13.2` 以规避漏洞。
  - 升级 `spring` 依赖版本为 `5.3.20` 以规避漏洞。
  - 升级 `mysql` 依赖版本为 `8.0.28` 以规避漏洞。
  - 升级 `fastjson` 依赖版本为 `1.2.83` 以规避漏洞。
  - 升级 `dubbo` 依赖版本为 `2.7.15` 以规避漏洞。
  - 升级 `netty` 依赖版本为 `4.1.77.Final` 以规避漏洞。
  - 升级 `hibernate` 依赖版本为 `5.3.20.Final` 以规避漏洞。
  - 升级 `hibernate-validator` 依赖版本为 `6.0.21.Final` 以规避漏洞。
  - 升级 `log4j2` 依赖版本为 `2.17.2` 以规避漏洞。
  - 升级 `groovy` 依赖版本为 `3.0.7` 以规避漏洞。

#### Bug修复

- (无)

#### 功能移除

- 删除不需要的依赖。
  - 删除 `joda-time` 依赖。
  - 删除 `commons-lang3` 依赖。
  - 删除 `commons-io` 依赖。
  - 删除 `httpcomponents` 依赖。

---

### Release_1.0.2_20220912_build_A

#### 功能构建

- 插件升级。
  - 升级 `maven-deploy-plugin` 插件版本为 `2.8.2`。

- 依赖升级。
  - 升级 `spring-terminator` 依赖版本为 `1.0.9.a` 以规避漏洞。
  - 升级 `dutil` 依赖版本为 `beta-0.3.1.a` 以规避漏洞。
  - 升级 `subgrade` 依赖版本为 `1.2.10.a` 以规避漏洞。
  - 升级 `spring-telqos` 依赖版本为 `1.1.4.a` 以规避漏洞。

#### Bug修复

- (无)

#### 功能移除

- (无)

---

### Release_1.0.1_20220412_build_A

#### 功能构建

- (无)

#### Bug修复

- 修正项目中不合理的配置项名称。

#### 功能移除

- (无)

---

### Release_1.0.0_20220323_build_A

#### 功能构建

- 程序结构建立，清理测试通过。

- 实体建立，单元测试通过。
  - com.dwarfeng.settingrepo.stack.bean.entity.FormatterSupport。
  - com.dwarfeng.settingrepo.stack.bean.entity.SettingCategory。
  - com.dwarfeng.settingrepo.stack.bean.entity.SettingNode。

- 实现设置节点主键的格式化机制。

- 实现操作服务。
  - com.dwarfeng.settingrepo.stack.service.SettingNodeOperateService。

- 程序开发完成，运行测试通过，打包测试通过。

#### Bug修复

- (无)

#### 功能移除

- (无)
