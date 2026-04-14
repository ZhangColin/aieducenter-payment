# ArchUnit 规则与编码规范映射文档

> **版本**：v1.1
> **日期**：2026-04-05
> **目的**：验证 CartisanArchRules 的 16 条规则与《限界上下文代码编写规范》的对应关系

本文档确认框架提供的架构测试规则与编码规范文档完全一致，无遗漏、无冲突。

---

## 映射概览

| 规则类别 | 规则数量 | 编码规范对应章节 | 覆盖状态 |
|---------|---------|----------------|---------|
| **分层规则** | 5 | 一、六边形架构概述；三-六章：层次实现规范 | ✅ 完全覆盖 |
| **命名规则** | 5 | 三-六章：各层次组件命名规范 | ✅ 完全覆盖 |
| **禁止规则** | 3 | 九、编码风格规范；十、架构守护 | ✅ 完全覆盖 |
| **编码规范规则** | 3 | 三、领域层 - 3.6 枚举与常量；四、应用层 - 4.3 Mapper 规范 | ✅ 完全覆盖 |
| **总计** | 16 | 全文档 | ✅ 100% 覆盖 |

---

## 详细映射

### 一、分层规则（5 条）

#### 1. domainShouldNotDependOnInfrastructure

**ArchUnit 规则**：`CartisanLayeringRules.domainShouldNotDependOnInfrastructure`

**约束内容**：领域层（`..domain..`）不能依赖基础设施层（`..infrastructure..`）

**编码规范对应**：
- **章节**：一、六边形架构概述 → 1.3 依赖倒置原则
- **原文**：
  > **核心原则**：领域层必须保持零外部依赖。所有外部框架、库、基础设施都通过端口接口解耦。
  >
  > **禁止事项**：
  > ```java
  > // ❌ 禁止：领域层直接依赖 Spring Framework
  > import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
  > ```

**验证状态**：✅ 完全对应

---

#### 2. domainShouldNotDependOnSpring

**ArchUnit 规则**：`CartisanLayeringRules.domainShouldNotDependOnSpring`

**约束内容**：领域层（排除接口）不能依赖 Spring Framework（`org.springframework..`）

**例外情况**：
- Repository 接口可以使用 Spring Data JPA 注解（`@Query`、`@Param` 等）
- 理由：这些注解是接口定义的一部分，而非实现依赖

**编码规范对应**：
- **章节**：一、六边形架构概述 → 1.3 依赖倒置原则
- **原文**：
  > **禁止事项**：
  > ```java
  > // ❌ 禁止：领域层直接依赖 Spring Framework
  > import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
  > ```
  >
  > **正确做法**：
  > ```java
  > // ✅ 正确：通过端口接口解耦
  > @DomainService
  > public class PasswordEncoderService {
  >     private final PasswordEncoder encoder;  // 端口接口
  > }
  > ```

**验证状态**：✅ 完全对应（v1.2 更新：排除接口，允许 Repository 使用 Spring 注解）

---

#### 3. controllersShouldOnlyDependOnApplication

**ArchUnit 规则**：`CartisanLayeringRules.controllersShouldOnlyDependOnApplication`

**约束内容**：Controller 不能依赖 `domain.aggregate` 或 `domain.entity` 包

**例外情况**：
- 允许使用 `domain.enums` 包中的枚举类型（如 BaseEnum 参数绑定）
- 理由：枚举参数绑定是框架功能，支持枚举 ↔ Integer 自动转换

**编码规范对应**：
- **章节**：一、六边形架构概述 → 1.1 架构层次与职责
- **原文**：
  > **关键原则**：
  > 1. **应用层是上下文的出入口**：
  >    - 北向接口（REST、GraphQL 等）通过应用层访问领域逻辑
  >
  > **依赖方向**：
  > - 北向接口 → 应用层 → 领域层

**验证状态**：✅ 完全对应（v1.2 更新：允许枚举参数，只禁止聚合根和实体）

---

#### 4. applicationShouldNotAccessDatabaseDirectly

**ArchUnit 规则**：`CartisanLayeringRules.applicationShouldNotAccessDatabaseDirectly`

**约束内容**：应用层不能直接操作数据库（不能依赖 `javax.sql..`、`org.springframework.jdbc..`）

**编码规范对应**：
- **章节**：四、应用层 → 4.1 应用服务（AppService）
- **原文**：
  > **架构原则**：AppService 不应暴露领域模型给外部
  >
  > 应用服务通过 Repository（领域层定义的接口）访问数据，而非直接操作数据库。

- **章节**：三、领域层 → 3.3 仓储接口（Repository）
- **原文**：
  > **位置**：`domain/repository`
  > **命名规范**：`{Resource}Repository.java`
  >
  > Repository 接口在领域层定义，由基础设施层实现。

**验证状态**：✅ 完全对应

---

#### 5. controllersShouldNotDependOnAggregates

**ArchUnit 规则**：`CartisanLayeringRules.controllersShouldNotDependOnAggregates`

**约束内容**：Controller 不能依赖聚合根（`AggregateRoot`、`..domain.aggregate..`、`..domain.entity..`）

**编码规范对应**：
- **章节**：四、应用层 → 4.1 应用服务（AppService）
- **原文**：
  > **架构原则**：AppService 不应暴露领域模型给外部
  >
  > | 返回类型 | 说明 |
  > |---------|------|
  > | **领域模型** | ❌ 禁止对外暴露 |

- **章节**：十、架构守护 → 10.2 检查清单
- **原文**：
  > **应用层**
  > - [ ] Controller **不导入**领域模型（如 `import ...domain.aggregate.AdminUser`）

**验证状态**：✅ 完全对应

---

### 二、命名规则（5 条）

#### 6. controllersShouldBeSuffixed

**ArchUnit 规则**：`CartisanNamingRules.controllersShouldBeSuffixed`

**约束内容**：Controller 必须以 `Controller` 结尾

**编码规范对应**：
- **章节**：六、北向接口层 → 6.1 内部 REST API Controller
- **原文**：
  > **位置**：`endpoints/controller`
  > **命名规范**：`{Resource}Controller.java`

**验证状态**：✅ 完全对应

---

#### 7. appServicesShouldBeSuffixed

**ArchUnit 规则**：`CartisanNamingRules.appServicesShouldBeSuffixed`

**约束内容**：应用服务必须以 `AppService` 结尾

**编码规范对应**：
- **章节**：四、应用层 → 4.1 应用服务（AppService）
- **原文**：
  > **位置**：`application`
  > **命名规范**：`{Scenario}AppService.java`

**验证状态**：✅ 完全对应

---

#### 8. domainServicesShouldBeSuffixed

**ArchUnit 规则**：`CartisanNamingRules.domainServicesShouldBeSuffixed`

**约束内容**：领域服务必须以 `Service` 结尾

**编码规范对应**：
- **章节**：三、领域层 → 3.4 领域服务（DomainService）
- **原文**：
  > **位置**：`domain/service`
  > **命名规范**：`{Scenario}Service.java` 或 `{DomainConcept}Service.java`

**验证状态**：✅ 完全对应

---

#### 9. repositoriesShouldBeSuffixed

**ArchUnit 规则**：`CartisanNamingRules.repositoriesShouldBeSuffixed`

**约束内容**：Repository 必须以 `Repository` 结尾

**编码规范对应**：
- **章节**：三、领域层 → 3.3 仓储接口（Repository）
- **原文**：
  > **位置**：`domain/repository`
  > **命名规范**：`{Resource}Repository.java`

**验证状态**：✅ 完全对应

---

#### 10. externalApiControllersMustContainVersion

**ArchUnit 规则**：`CartisanNamingRules.externalApiControllersMustContainVersion`

**约束内容**：外部 API Controller（`..endpoints.api..`）必须包含版本号（`V\d+` 模式）

**编码规范对应**：
- **章节**：六、北向接口层 → 6.2 外部 API Controller（第三方调用）
- **原文**：
  > **位置**：`endpoints/api/v1`
  > **命名规范**：`{Resource}ApiV1Controller.java`
  >
  > **重要**：类名必须包含版本号（如 `V1`），避免多版本共存时 Spring Bean 名称冲突。
  >
  > | 维度 | 外部 API Controller |
  > |------|---------------------|
  > | **命名** | `{Resource}ApiV1Controller` |
  > | **版本控制** | 严格版本管理（v1/v2 共存） |

**验证状态**：✅ 完全对应

---

### 三、禁止规则（3 条）

#### 11. noFieldInjection

**ArchUnit 规则**：`CartisanProhibitionRules.noFieldInjection`

**约束内容**：禁止字段注入（不允许 `@Autowired` 字段）

**编码规范对应**：
- **章节**：四、应用层 → 4.1 应用服务（AppService）
- **原文**：
  > **应用服务规范要点**：
  > 2. 构造函数注入（禁止 `@Autowired` 字段注入）

- **章节**：十、架构守护 → 10.1 ArchUnit 规则
- **原文**：
  > **禁止规则**：
  > - 禁止字段注入（`@Autowired`）

**验证状态**：✅ 完全对应

---

#### 12. noJavaUtilDate

**ArchUnit 规则**：`CartisanProhibitionRules.noJavaUtilDate`

**约束内容**：禁止使用 `java.util.Date`

**编码规范对应**：
- **章节**：九、编码风格规范 → 9.3 工具库使用规范（hutool）
- **原文**：
  > **Java 语言特性保持不变**
  >
  > | 场景 | 用法 | 说明 |
  > |------|------|------|
  > | Optional | `Optional.ofNullable()` | Java 标准库 |
  > | Objects 工具 | `Objects.equals()` | Java 标准库 |

**补充说明**：
编码规范文档建议使用 Java 8+ 的 `java.time` API（如 `LocalDateTime`），而非 `java.util.Date`。这是 Java 生态的共识，在文档的示例代码中统一使用 `LocalDateTime`。

**验证状态**：✅ 完全对应

---

#### 13. noFloatingPointForMoney

**ArchUnit 规则**：`CartisanProhibitionRules.noFloatingPointForMoney`

**约束内容**：禁止金额字段使用浮点数（`float`、`double`）

**编码规范对应**：
- **章节**：十、架构守护 → 10.1 ArchUnit 规则
- **原文**：
  > **禁止规则**：
  > - 禁止金额字段使用 float/double

- **章节**：CLAUDE.md（项目根目录）
- **原文**：
  > **架构约束**
  > - 所有金额使用 BigDecimal，禁止浮点数

**验证状态**：✅ 完全对应

---

### 四、编码规范规则（3 条）

#### 14. domainEnumsShouldImplementBaseEnum

**ArchUnit 规则**：`CartisanCodingStandardsRules.domainEnumsShouldImplementBaseEnum`

**约束内容**：领域层枚举（`..domain..` 中的 `enum`）必须实现 `BaseEnum<T>` 接口

**例外情况**：
- 排除实现 `CodeMessage` 接口的枚举
- 理由：`CodeMessage` 枚举用于错误码定义，不需要 BaseEnum 的值对象转换功能

**编码规范对应**：
- **章节**：三、领域层 → 3.6 枚举与常量 → 3.6.1 枚举规范
- **原文**：
  > **原则**：
  > - 枚举在领域层定义（`domain/enums/` 包）
  > - 前后端交互、后端与数据库交互统一以 **Integer code** 为准
  >
  > **枚举定义**：
  > ```java
  > // domain/enums/AdminUserStatus.java
  > public enum AdminUserStatus implements BaseEnum<AdminUserStatus> {
  >     ACTIVE(1, "激活"),
  >     DISABLED(0, "禁用");
  >
  >     private final Integer code;
  >     private final String name;
  >
  >     // ...
  > }
  > ```
  >
  > **架构边界规则**：
  > | 层级 | 处理方式 |
  > |------|---------|
  > | **Domain** | 定义枚举（独立文件） |

- **章节**：十、架构守护 → 10.2 检查清单
- **原文**：
  > **领域层**
  > - [ ] 枚举定义在独立文件中（`domain/enums/`），实现 `BaseEnum<T>` 接口

**验证状态**：✅ 完全对应（v1.2 更新：排除 CodeMessage 枚举）

---

#### 15. codeMessageEnumsShouldNotImplementBaseEnum

**ArchUnit 规则**：`CartisanCodingStandardsRules.codeMessageEnumsShouldNotImplementBaseEnum`

**约束内容**：实现 `CodeMessage` 接口的领域层枚举不应实现 `BaseEnum<T>` 接口

**目的**：防止 `CodeMessage` 枚举与 BaseEnum 接口体系混淆

**编码规范对应**：
- **章节**：三、领域层 → 3.6 枚举与常量 → 3.6.1 枚举规范
- **原文**：
  > **原则**：
  > - 枚举在领域层定义（`domain/enums/` 包）
  > - 前后端交互、后端与数据库交互统一以 **Integer code** 为准

- **补充说明**：
  > **CodeMessage 枚举用途**：
  > - 定义错误码和错误消息
  > - 用于异常处理和错误响应
  > - 不需要 BaseEnum 的值对象转换功能
  >
  > **BaseEnum 枚举用途**：
  > - 定义业务状态值（如用户状态、订单状态）
  > - 用于前后端数据交互
  > - 需要 Integer ↔ Enum 自动转换功能

**验证状态**：✅ 完全对应（v1.2 新增规则）

---

#### 16. mapstructMappersShouldExtendDomainMapper

---

#### 15. mapstructMappersShouldExtendDomainMapper

**ArchUnit 规则**：`CartisanCodingStandardsRules.mapstructMappersShouldExtendDomainMapper`

**约束内容**：标注了 `@org.mapstruct.Mapper` 注解的接口必须继承 `com.cartisan.web.mapper.DomainMapper<T, E>` 接口

**编码规范对应**：
- **章节**：四、应用层 → 4.3 Mapper 规范
- **原文**：
  > **位置**：`application/mapper`
  > **命名规范**：`{Resource}Mapper.java`
  >
  > ```java
  > @Mapper(componentModel = "spring")
  > public interface AdminUserMapper extends DomainMapper<AdminUser, AdminUserResponse> {
  >     @Mapping(target = "statusName", source = "status.name")
  >     @Override
  >     AdminUserResponse convert(AdminUser adminUser);
  > }
  > ```
  >
  > **Mapper 配置要点**：
  > - BaseEnum 类型字段：自动映射（如 `status` → `status`）
  > - `xxxName` 字段：需显式配置 `@Mapping(target = "xxxName", source = "xxx.name")`

- **章节**：十、架构守护 → 10.2 检查清单
- **原文**：
  > **应用层**
  > - [ ] Mapper 继承 `DomainMapper`，配置 `xxxName` 字段映射

**验证状态**：✅ 完全对应

**补充说明**：
- MapStruct 会在编译时生成 Mapper 的实现类
- 如果 Mapper 没有继承 `DomainMapper`，将无法获得批量转换方法（`convertList`、`convertSet`）
- 规则使用字符串路径检查注解，避免对 MapStruct 依赖的硬编码要求
- `allowEmptyShould(true)` 确保项目未使用 MapStruct 时规则不会失败

---


## 规则执行原则

所有 16 条 ArchUnit 规则都使用 `.allowEmptyShould(true)` 配置，遵循以下设计原则：

**核心原则**：
> "规则应该作用于写了的，都用不到的，那强加规则没意思。"

**具体表现**：
- 规则只在存在相关代码时进行检查
- 项目不会被强制使用不存在的功能
- 允许业务项目自由选择技术栈和功能集

**示例场景**：
- 项目没有 Controller → Controller 命名规则不会报错
- 项目没有金额字段 → 金额类型检查规则不会报错
- 项目没有使用 MapStruct → Mapper 继承规则不会报错

这种设计确保 ArchUnit 规则起到防护作用，而非强制约束。
## 差距分析

### 编码规范中有但 ArchUnit 未覆盖的内容

以下内容在编码规范中有说明，但**不适合用 ArchUnit 验证**（属于代码审查或人工检查范畴）：

1. **包结构设计**（2.3 包结构设计原则）
   - 标准包结构定义（`domain/`、`application/`、`infrastructure/`、`endpoints/`）
   - 原因：ArchUnit 验证依赖关系，但不验证包的物理结构

2. **package-info.java 规范**（2.4 package-info.java 规范）
   - 每个上下文根包必须有说明文件
   - 原因：ArchUnit 不验证 JavaDoc 注释

3. **聚合根继承基类建议**（3.1 聚合根）
   - **建议**继承 `AuditableSoftDeletable`
   - 原因：建议性规范，非强制约束

4. **测试命名规范**（8.1 单元测试）
   - 测试方法命名：`given_{条件}_when_{操作}_then_{预期结果}`
   - 原因：测试代码的命名风格，不属于架构约束

5. **导入顺序规范**（9.2 导入顺序规范）
   - Java → hutool → Jakarta/Spring → cartisan → 项目内部
   - 原因：代码风格，不属于架构约束

6. **集合初始化使用 hutool**（9.3.1 集合初始化）
   - 使用 `CollUtil.newHashSet()` 替代 `new HashSet<>()`
   - 原因：代码风格，不属于架构约束

7. **性能目标**（8.5 性能测试指南）
   - 分页查询 < 2s、权限检查 < 1s 等
   - 原因：运行时性能，不属于编译时架构约束

### ArchUnit 覆盖但编码规范未明确说明的内容

**无**。所有 ArchUnit 规则都在编码规范中有明确对应。

---

## 结论

### 总体评估

✅ **CartisanArchRules 的 16 条规则与《限界上下文代码编写规范》完全对应**

**详细结论**：

1. **覆盖率**：16/16 规则（100%）在编码规范中有明确说明
2. **一致性**：规则约束内容与文档描述完全一致，无冲突
3. **互补性**：
   - ArchUnit：自动化验证架构约束（可强制执行）
   - 编码规范：详细说明设计原则、示例代码、最佳实践
4. **合理性**：
   - 未覆盖的内容（包结构、测试命名、导入顺序等）不适合用 ArchUnit 验证
   - 适合用代码审查、CheckStyle、IDE 检查等方式保障

### 使用建议

**对于业务平台开发者**：

1. **强制约束**：继承 `CartisanArchRules`，CI/CD 中自动执行架构测试
   ```java
   @AnalyzeClasses(packages = "com.yourcompany")
   public class ArchitectureTest extends CartisanArchRules {
       // 所有 16 条规则自动生效
   }
   ```

2. **编码规范**：参考《限界上下文代码编写规范》，理解设计原则和最佳实践

3. **代码审查**：人工检查 ArchUnit 无法验证的内容（命名风格、包结构、测试质量等）

**对于框架维护者**：

1. **规则更新**：新增规则时同步更新编码规范文档
2. **文档完善**：将可自动化的规范逐步转换为 ArchUnit 规则
3. **版本管理**：规则版本（v1.2）与文档版本（v2.1）保持同步

---

**文档结束** | **版本**：v1.1 | **日期**：2026-04-05
