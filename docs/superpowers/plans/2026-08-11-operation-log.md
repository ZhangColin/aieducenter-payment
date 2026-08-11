# OperationLog 能力（聚合 + 记录 + 查询）实现计划

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** 新增 OperationLog 聚合（表 `pay_operation_logs`），记录行为者对订单发起的写操作（审核/通知重发），并提供多条件分页查询端点 `GET /api/v1/operation-logs`。

**Architecture:** DDD 六边形，沿用现有 PaymentLog/RefundOrder 模式。OperationLog 是**追加只写**聚合（ADR-0002）：仅记录、不改订单状态。多条件查询用 cartisan-data-jpa 的 `@Condition` + `ConditionSpecifications.fromAnnotation(query)` + `BaseRepository` 继承的 `findAll(Specification, Pageable)`。两条测试缝：聚合单元测试（领域不变式）+ AppService Mockito 缝（核心路径，变异友好）。不引入 `@DataJpaTest`（已与用户确认）。

**Tech Stack:** Java 21, Spring Boot, JPA/Hibernate, MapStruct, cartisan-data-jpa（`@Condition`/`BaseRepository`/`AuditableSoftDeletable`/TSID）, cartisan-openapi（`@RequireSignature`）, JUnit 5 + Mockito + AssertJ, Flyway, pitest。

## 关联文档

- 父 spec：Issue #8（实现规约）。
- 术语：`CONTEXT.md`（OperationLog / PaymentLog 职责分离、不变式 5「银行无关」）。
- 决策：`docs/adr/0002-operation-log-separation.md`（不合表、合视图；追加只写）。
- 编码规范：`docs/guide/限界上下文代码编写规范.md`。

## Global Constraints

- **银行无关**：OperationLog 全程不出现 ICBC / 具体银行硬编码（CONTEXT.md 不变式 5）。
- **追加只写**：聚合无状态迁移方法，只有构造（ADR-0002）。
- **TSID 主键**：`@PrePersist` 内 `TsidGenerator.newInstance().generate()`，与 PaymentLog/RefundOrder 一致。
- **继承 `AuditableSoftDeletable`**：自动获得 `createdAt/updatedAt/createdBy/updatedBy/deleted`，无需自建审计字段。
- **BaseEnum**：枚举放 `domain/enums/`，实现 `BaseEnum<T>` + 内部 `JpaConverter`（`@Converter(autoApply=true)`），DB 存 Integer code。
- **DTO 只用 Record**；响应只暴露 DTO，不暴露聚合；Mapper 沿用 MapStruct `@Mapper(componentModel="spring")` + `DomainMapper` 模式。
- **构造函数注入**（`@RequiredArgsConstructor`），禁止字段注入。
- **导入顺序**：Java 标准库 → hutool → Jakarta/Spring → cartisan → 项目内部。
- **错误码**：`PaymentMessage` 实现 `CodeMessage`，新错误码用 `PAY_05x` 段。
- **测试命名**：`given_X_when_Y_then_Z`，AssertJ 断言；Mockito 用 `ArgumentCaptor` 验证传给协作者的参数（变异友好）。
- **签名校验**：端点 `@RequireSignature`；GET 请求体为空 → bodyDigest = SHA-256(空)，query 参与签名串（框架已支持）。

## 关键设计决策（锁定）

| 决策点 | 取值 | 理由 |
|--------|------|------|
| `result` 字段类型 | `String`（length 32，NOT NULL） | spec 未枚举 result 取值；用稳定 token（`SUCCESS`/`FAILED`），过滤友好、不 over-engineer 枚举（YAGNI）。语义＝「本次记录的操作是否完成成功」，非业务结果好坏（AUDIT_REJECT 仍记 SUCCESS＝拒绝动作已成功落库）。 |
| `operatorId/Name/System` 可空 | 均可空 | CONTEXT.md：系统发起的动作 operatorId/Name 可空；operatorSystem 在无签名上下文（如调度）时也可空。 |
| `record(...)` 方法名 | 保留 `record` | spec 明示；`record` 是 Java 受限标识符，**可作为方法名**（不可作为 record 类型名），javac 合法。 |
| 列表返回类型 | `ApiResponse<PageResponse<OperationLogResponse>>` | 沿用本仓 controller 统一 `ApiResponse.ok(...)` 包装；PageResponse 为分页结构体。 |
| 时间范围筛选 | 双字段 `createdAtStart`(GREATER_EQUAL) + `createdAtEnd`(LESS_EQUAL)，`propName="createdAt"` | 比单字段 BETWEEN 更显式可读，`createdAt` 继承自 `AuditableSoftDeletable`（`@MappedSuperclass`），Specification `root.get("createdAt")` 可用。 |
| `findByTargetNoOrderByCreatedAtDesc` | 仅按 targetNo | spec 明示；支付/退款单号命名空间不同，单号维度足够。供后续生命周期读模型（Issue #10）消费。 |

## File Structure

```
src/main/resources/db/migration/
└── V6__create_operation_logs.sql                      [新] 建表 + 索引

src/main/java/com/aieducenter/payment/
├── domain/
│   ├── enums/
│   │   ├── OperationLogTargetType.java                [新] 枚举 PAYMENT/REFUND
│   │   └── OperationType.java                         [新] 枚举 AUDIT_APPROVE/AUDIT_REJECT/NOTIFY_RESEND
│   ├── aggregate/
│   │   └── OperationLog.java                          [新] 聚合根（追加只写）
│   ├── repository/
│   │   └── OperationLogRepository.java                [新] 仓储接口（findByTargetNo + 继承 Specification 分页）
│   └── error/
│       └── PaymentMessage.java                        [改] +4 条校验错误码
├── application/
│   ├── dto/
│   │   ├── query/
│   │   │   └── OperationLogQuery.java                 [新] @Condition 多条件
│   │   └── response/
│   │       └── OperationLogResponse.java              [新] 响应 DTO
│   ├── mapper/
│   │   └── OperationLogMapper.java                    [新] MapStruct + DomainMapper
│   └── OperationLogAppService.java                    [新] record(...) + list(query, pageable)
└── endpoints/api/v1/
    └── OperationLogApiV1Controller.java               [新] GET /api/v1/operation-logs

src/test/java/com/aieducenter/payment/
├── domain/aggregate/
│   └── OperationLogTest.java                          [新] 聚合不变式（领域缝）
└── application/
    └── OperationLogAppServiceTest.java                [新] Mockito 缝（AppService 缝）
```

---

### Task 1: Flyway 迁移建表 `pay_operation_logs`

**Files:**
- Create: `src/main/resources/db/migration/V6__create_operation_logs.sql`

**Interfaces:**
- Produces: 表 `pay_operation_logs`，列与 OperationLog 聚合一一对应（target_type INTEGER / target_no VARCHAR(64) / operation INTEGER / operator_id BIGINT / operator_name VARCHAR(64) / operator_system VARCHAR(128) / result VARCHAR(32) / remark TEXT + AuditableSoftDeletable 标准列）。

- [ ] **Step 1: 写迁移脚本**

创建 `src/main/resources/db/migration/V6__create_operation_logs.sql`：

```sql
-- ========================================================================
-- Payment Context: Operation Logs
-- 行为者对订单发起的写操作留痕（审核通过/拒绝、通知重发等）。
-- 与 pay_payment_logs（网关交互日志）职责分离（见 ADR-0002）。追加只写。
-- ========================================================================

CREATE TABLE pay_operation_logs (
    id BIGINT PRIMARY KEY,
    target_type INTEGER NOT NULL,
    target_no VARCHAR(64) NOT NULL,
    operation INTEGER NOT NULL,
    operator_id BIGINT,
    operator_name VARCHAR(64),
    operator_system VARCHAR(128),
    result VARCHAR(32) NOT NULL,
    remark TEXT,

    -- Audit fields（AuditableSoftDeletable）
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by BIGINT,
    updated_by BIGINT,
    deleted BOOLEAN NOT NULL DEFAULT FALSE
);

-- 索引覆盖 target_no / created_at / operator_id（acceptance criteria）
CREATE INDEX idx_operation_logs_target_no ON pay_operation_logs(target_no) WHERE deleted = FALSE;
CREATE INDEX idx_operation_logs_created_at ON pay_operation_logs(created_at) WHERE deleted = FALSE;
CREATE INDEX idx_operation_logs_operator_id ON pay_operation_logs(operator_id) WHERE deleted = FALSE;
```

- [ ] **Step 2: 验证迁移可被 Flyway 解析（编译期 + 启动期）**

Run: `mvn compile`
Expected: BUILD SUCCESS（迁移脚本在 `mvn test` 启动时由 Flyway 执行；此处先确保语法不影响构建）。

- [ ] **Step 3: Commit**

```bash
git add src/main/resources/db/migration/V6__create_operation_logs.sql
git commit -m "feat(payment): Flyway 迁移建表 pay_operation_logs（OperationLog）"
```

---

### Task 2: 领域层 — 枚举 + 错误码 + OperationLog 聚合 + 仓储 + 聚合测试

**Files:**
- Create: `src/main/java/com/aieducenter/payment/domain/enums/OperationLogTargetType.java`
- Create: `src/main/java/com/aieducenter/payment/domain/enums/OperationType.java`
- Create: `src/main/java/com/aieducenter/payment/domain/aggregate/OperationLog.java`
- Create: `src/main/java/com/aieducenter/payment/domain/repository/OperationLogRepository.java`
- Modify: `src/main/java/com/aieducenter/payment/domain/error/PaymentMessage.java`
- Test: `src/test/java/com/aieducenter/payment/domain/aggregate/OperationLogTest.java`

**Interfaces:**
- Produces（供后续 Task 消费）：
  - `enum OperationLogTargetType implements BaseEnum<OperationLogTargetType>` — `PAYMENT(1,"支付订单")`, `REFUND(2,"退款订单")`
  - `enum OperationType implements BaseEnum<OperationType>` — `AUDIT_APPROVE(1,"审核通过")`, `AUDIT_REJECT(2,"审核拒绝")`, `NOTIFY_RESEND(3,"通知重发")`
  - `class OperationLog extends AuditableSoftDeletable implements AggregateRoot<OperationLog, Long>`，构造签名：
    `new OperationLog(OperationLogTargetType targetType, String targetNo, OperationType operation, Long operatorId, String operatorName, String operatorSystem, String result, String remark)`
  - `interface OperationLogRepository extends BaseRepository<OperationLog, Long>`，方法 `List<OperationLog> findByTargetNoOrderByCreatedAtDesc(String targetNo)`
  - `PaymentMessage.OPERATION_LOG_TARGET_TYPE_REQUIRED` / `_TARGET_NO_REQUIRED` / `_OPERATION_REQUIRED` / `_RESULT_REQUIRED`

- [ ] **Step 1: 新增 4 条错误码**

在 `PaymentMessage.java` 的「业务规则错误 (400)」段（`REFUND_ALREADY_REFUNDED` 之后或「业务限制」段之前）追加：

```java
    // ========== 操作日志校验错误 (400) ==========
    OPERATION_LOG_TARGET_TYPE_REQUIRED(400, "PAY_050", "操作日志目标类型不能为空"),
    OPERATION_LOG_TARGET_NO_REQUIRED(400, "PAY_051", "操作日志目标单号不能为空"),
    OPERATION_LOG_OPERATION_REQUIRED(400, "PAY_052", "操作日志操作类型不能为空"),
    OPERATION_LOG_RESULT_REQUIRED(400, "PAY_053", "操作日志结果不能为空"),
```

- [ ] **Step 2: 创建枚举 `OperationLogTargetType`**

```java
package com.aieducenter.payment.domain.enums;

import com.cartisan.core.domain.BaseEnum;
import com.cartisan.data.jpa.converter.BaseEnumConverter;
import jakarta.persistence.Converter;

/**
 * 操作日志目标类型。
 */
public enum OperationLogTargetType implements BaseEnum<OperationLogTargetType> {
    PAYMENT(1, "支付订单"),
    REFUND(2, "退款订单");

    private final Integer code;
    private final String name;

    OperationLogTargetType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getName() {
        return name;
    }

    @Converter(autoApply = true)
    public static class JpaConverter extends BaseEnumConverter<OperationLogTargetType> {
        public JpaConverter() {
            super(OperationLogTargetType.class);
        }
    }
}
```

- [ ] **Step 3: 创建枚举 `OperationType`**

```java
package com.aieducenter.payment.domain.enums;

import com.cartisan.core.domain.BaseEnum;
import com.cartisan.data.jpa.converter.BaseEnumConverter;
import jakarta.persistence.Converter;

/**
 * 操作日志的操作类型。
 *
 * <p>行为者对订单发起的写操作。</p>
 */
public enum OperationType implements BaseEnum<OperationType> {
    AUDIT_APPROVE(1, "审核通过"),
    AUDIT_REJECT(2, "审核拒绝"),
    NOTIFY_RESEND(3, "通知重发");

    private final Integer code;
    private final String name;

    OperationType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getName() {
        return name;
    }

    @Converter(autoApply = true)
    public static class JpaConverter extends BaseEnumConverter<OperationType> {
        public JpaConverter() {
            super(OperationType.class);
        }
    }
}
```

- [ ] **Step 4: 写失败测试（领域缝）**

创建 `src/test/java/com/aieducenter/payment/domain/aggregate/OperationLogTest.java`：

```java
package com.aieducenter.payment.domain.aggregate;

import com.aieducenter.payment.domain.enums.OperationLogTargetType;
import com.aieducenter.payment.domain.enums.OperationType;
import com.cartisan.core.exception.DomainException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * OperationLog 聚合根测试（领域缝）。
 *
 * <p>校验构造不变式；OperationLog 为追加只写，无状态迁移方法。</p>
 */
@DisplayName("OperationLog 聚合根测试")
class OperationLogTest {

    @Test
    @DisplayName("给定有效输入，创建操作日志时应该成功并保存全部字段")
    void given_validInput_when_createOperationLog_then_allFieldsStored() {
        OperationLog log = new OperationLog(
            OperationLogTargetType.REFUND, "REF001", OperationType.AUDIT_APPROVE,
            123L, "张三", "admin-bff", "SUCCESS", "同意退款"
        );

        assertThat(log.getTargetType()).isEqualTo(OperationLogTargetType.REFUND);
        assertThat(log.getTargetNo()).isEqualTo("REF001");
        assertThat(log.getOperation()).isEqualTo(OperationType.AUDIT_APPROVE);
        assertThat(log.getOperatorId()).isEqualTo(123L);
        assertThat(log.getOperatorName()).isEqualTo("张三");
        assertThat(log.getOperatorSystem()).isEqualTo("admin-bff");
        assertThat(log.getResult()).isEqualTo("SUCCESS");
        assertThat(log.getRemark()).isEqualTo("同意退款");
    }

    @Test
    @DisplayName("给定系统发起的动作，操作者字段为空时也应该成功")
    void given_systemInitiated_when_operatorFieldsNull_then_success() {
        OperationLog log = new OperationLog(
            OperationLogTargetType.PAYMENT, "PAY001", OperationType.NOTIFY_RESEND,
            null, null, null, "SUCCESS", null
        );

        assertThat(log.getOperatorId()).isNull();
        assertThat(log.getOperatorName()).isNull();
        assertThat(log.getOperatorSystem()).isNull();
        assertThat(log.getRemark()).isNull();
    }

    @Test
    @DisplayName("给定目标类型为空，创建时应该抛出异常")
    void given_nullTargetType_when_create_then_throwsException() {
        assertThatThrownBy(() -> new OperationLog(
            null, "REF001", OperationType.AUDIT_APPROVE,
            1L, "张三", "sys", "SUCCESS", null
        ))
            .isInstanceOf(DomainException.class)
            .hasMessageContaining("操作日志目标类型不能为空");
    }

    @Test
    @DisplayName("给定目标单号为空白，创建时应该抛出异常")
    void given_blankTargetNo_when_create_then_throwsException() {
        assertThatThrownBy(() -> new OperationLog(
            OperationLogTargetType.REFUND, "  ", OperationType.AUDIT_APPROVE,
            1L, "张三", "sys", "SUCCESS", null
        ))
            .isInstanceOf(DomainException.class)
            .hasMessageContaining("操作日志目标单号不能为空");
    }

    @Test
    @DisplayName("给定操作类型为空，创建时应该抛出异常")
    void given_nullOperation_when_create_then_throwsException() {
        assertThatThrownBy(() -> new OperationLog(
            OperationLogTargetType.REFUND, "REF001", null,
            1L, "张三", "sys", "SUCCESS", null
        ))
            .isInstanceOf(DomainException.class)
            .hasMessageContaining("操作日志操作类型不能为空");
    }

    @Test
    @DisplayName("给定结果为空，创建时应该抛出异常")
    void given_blankResult_when_create_then_throwsException() {
        assertThatThrownBy(() -> new OperationLog(
            OperationLogTargetType.REFUND, "REF001", OperationType.AUDIT_APPROVE,
            1L, "张三", "sys", "", null
        ))
            .isInstanceOf(DomainException.class)
            .hasMessageContaining("操作日志结果不能为空");
    }
}
```

- [ ] **Step 5: 运行测试确认失败（聚合尚未创建）**

Run: `mvn test -Dtest=OperationLogTest -Dsurefire.failIfNoSpecifiedTests=false`
Expected: 编译失败（`OperationLog` 类不存在）。

- [ ] **Step 6: 实现 `OperationLog` 聚合**

创建 `src/main/java/com/aieducenter/payment/domain/aggregate/OperationLog.java`：

```java
package com.aieducenter.payment.domain.aggregate;

import cn.hutool.core.util.StrUtil;
import com.aieducenter.payment.domain.enums.OperationLogTargetType;
import com.aieducenter.payment.domain.enums.OperationType;
import com.aieducenter.payment.domain.error.PaymentMessage;
import com.cartisan.core.domain.AggregateRoot;
import com.cartisan.core.stereotype.Aggregate;
import com.cartisan.core.util.Assertions;
import com.cartisan.data.jpa.domain.AuditableSoftDeletable;
import jakarta.persistence.*;
import lombok.Getter;

/**
 * 操作日志聚合根。
 *
 * <p>记录行为者对订单发起的写操作（审核通过/拒绝、通知重发等），用于合规追溯与运营审计。
 * 与 {@link PaymentLog}（网关交互日志）职责分离（见 ADR-0002）。</p>
 *
 * <p><b>追加只写</b>：仅记录，不改订单状态；无状态迁移方法。</p>
 */
@Entity
@Table(name = "pay_operation_logs")
@Aggregate
public class OperationLog extends AuditableSoftDeletable implements AggregateRoot<OperationLog, Long> {

    @Getter
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    /** 操作目标类型（PAYMENT / REFUND） */
    @Getter
    @Column(name = "target_type", nullable = false)
    private OperationLogTargetType targetType;

    /** 操作目标单号（支付订单号 / 退款订单号） */
    @Getter
    @Column(name = "target_no", nullable = false, length = 64)
    private String targetNo;

    /** 操作类型（AUDIT_APPROVE / AUDIT_REJECT / NOTIFY_RESEND） */
    @Getter
    @Column(name = "operation", nullable = false)
    private OperationType operation;

    /** 操作者 ID（系统发起的动作可空） */
    @Getter
    @Column(name = "operator_id")
    private Long operatorId;

    /** 操作者名称（系统发起的动作可空） */
    @Getter
    @Column(name = "operator_name", length = 64)
    private String operatorName;

    /** 来源系统（调用方 appName，取自签名上下文；可空） */
    @Getter
    @Column(name = "operator_system", length = 128)
    private String operatorSystem;

    /** 操作结果（稳定 token，如 SUCCESS / FAILED） */
    @Getter
    @Column(name = "result", nullable = false, length = 32)
    private String result;

    /** 备注（决策依据等，可空） */
    @Getter
    @Column(name = "remark", columnDefinition = "TEXT")
    private String remark;

    @PrePersist
    void prePersist() {
        if (id == null) {
            this.id = com.cartisan.data.jpa.id.TsidGenerator.newInstance().generate();
        }
    }

    /** JPA 要求的无参构造函数 */
    protected OperationLog() {
        // JPA only
    }

    /**
     * 记录一条操作日志。
     *
     * @param targetType     目标类型（必填）
     * @param targetNo       目标单号（必填，非空白）
     * @param operation      操作类型（必填）
     * @param operatorId     操作者 ID（可空，系统发起时为 null）
     * @param operatorName   操作者名称（可空）
     * @param operatorSystem 来源系统（可空）
     * @param result         操作结果（必填，非空白）
     * @param remark         备注（可空）
     */
    public OperationLog(
            OperationLogTargetType targetType,
            String targetNo,
            OperationType operation,
            Long operatorId,
            String operatorName,
            String operatorSystem,
            String result,
            String remark
    ) {
        Assertions.require(targetType != null, PaymentMessage.OPERATION_LOG_TARGET_TYPE_REQUIRED);
        Assertions.require(StrUtil.isNotBlank(targetNo), PaymentMessage.OPERATION_LOG_TARGET_NO_REQUIRED);
        Assertions.require(operation != null, PaymentMessage.OPERATION_LOG_OPERATION_REQUIRED);
        Assertions.require(StrUtil.isNotBlank(result), PaymentMessage.OPERATION_LOG_RESULT_REQUIRED);

        this.targetType = targetType;
        this.targetNo = targetNo;
        this.operation = operation;
        this.operatorId = operatorId;
        this.operatorName = operatorName;
        this.operatorSystem = operatorSystem;
        this.result = result;
        this.remark = remark;
    }
}
```

- [ ] **Step 7: 运行测试确认通过**

Run: `mvn test -Dtest=OperationLogTest -Dsurefire.failIfNoSpecifiedTests=false`
Expected: 6 个测试全部 PASS。

- [ ] **Step 8: 创建仓储接口**

创建 `src/main/java/com/aieducenter/payment/domain/repository/OperationLogRepository.java`：

```java
package com.aieducenter.payment.domain.repository;

import com.aieducenter.payment.domain.aggregate.OperationLog;
import com.cartisan.data.jpa.repository.BaseRepository;

import java.util.List;

/**
 * 操作日志仓储接口。
 *
 * <p>领域层定义，基础设施层由 Spring Data 自动生成实现。多条件分页查询复用
 * {@code BaseRepository} 继承的 {@code findAll(Specification, Pageable)}。</p>
 */
public interface OperationLogRepository extends BaseRepository<OperationLog, Long> {

    /**
     * 按目标单号查询操作日志，按创建时间倒序（供订单生命周期读模型合并用）。
     *
     * @param targetNo 目标单号
     * @return 操作日志列表
     */
    List<OperationLog> findByTargetNoOrderByCreatedAtDesc(String targetNo);
}
```

- [ ] **Step 9: 编译确认整体无误**

Run: `mvn compile`
Expected: BUILD SUCCESS。

- [ ] **Step 10: Commit**

```bash
git add src/main/java/com/aieducenter/payment/domain/ src/test/java/com/aieducenter/payment/domain/aggregate/OperationLogTest.java
git commit -m "feat(payment): OperationLog 聚合 + 枚举 + 仓储 + 不变式测试"
```

---

### Task 3: 应用层 — Query/Response DTO + Mapper + AppService + AppService 测试

**Files:**
- Create: `src/main/java/com/aieducenter/payment/application/dto/query/OperationLogQuery.java`
- Create: `src/main/java/com/aieducenter/payment/application/dto/response/OperationLogResponse.java`
- Create: `src/main/java/com/aieducenter/payment/application/mapper/OperationLogMapper.java`
- Create: `src/main/java/com/aieducenter/payment/application/OperationLogAppService.java`
- Test: `src/test/java/com/aieducenter/payment/application/OperationLogAppServiceTest.java`

**Interfaces:**
- Consumes: Task 2 的 `OperationLog`、`OperationLogRepository`、两个枚举。
- Produces:
  - `record OperationLogQuery(OperationLogTargetType targetType, String targetNo, OperationType operation, Long operatorId, String operatorSystem, String result, LocalDateTime createdAtStart, LocalDateTime createdAtEnd)`（字段均 `@Condition`）
  - `record OperationLogResponse(Long id, Integer targetType, String targetTypeName, String targetNo, Integer operation, String operationName, Long operatorId, String operatorName, String operatorSystem, String result, String remark, LocalDateTime createdAt)`
  - `OperationLogAppService.record(OperationLogTargetType, String, OperationType, Long, String, String, String, String) → void`
  - `OperationLogAppService.list(OperationLogQuery, Pageable) → PageResponse<OperationLogResponse>`

- [ ] **Step 1: 写失败测试（AppService 缝）**

创建 `src/test/java/com/aieducenter/payment/application/OperationLogAppServiceTest.java`：

```java
package com.aieducenter.payment.application;

import com.aieducenter.payment.application.dto.query.OperationLogQuery;
import com.aieducenter.payment.application.dto.response.OperationLogResponse;
import com.aieducenter.payment.application.mapper.OperationLogMapper;
import com.aieducenter.payment.domain.aggregate.OperationLog;
import com.aieducenter.payment.domain.enums.OperationLogTargetType;
import com.aieducenter.payment.domain.enums.OperationType;
import com.aieducenter.payment.domain.repository.OperationLogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * OperationLogAppService 测试（AppService Mockito 缝）。
 *
 * <p>沿用 RefundAppServiceTest 模式：mock 仓储与 Mapper，构造被测服务直接驱动。
 * 用 ArgumentCaptor 验证传给仓储的聚合字段（变异友好）。</p>
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("操作日志应用服务测试")
class OperationLogAppServiceTest {

    @Mock private OperationLogRepository operationLogRepository;
    @Mock private OperationLogMapper operationLogMapper;

    private OperationLogAppService service;

    @BeforeEach
    void setUp() {
        service = new OperationLogAppService(operationLogRepository, operationLogMapper);
    }

    @Test
    @DisplayName("record：落库一条操作日志，全部字段正确传递")
    void given_validOperation_when_record_then_saveOperationLogWithAllFields() {
        service.record(
            OperationLogTargetType.REFUND, "REF001", OperationType.AUDIT_APPROVE,
            123L, "张三", "admin-bff", "SUCCESS", "同意退款"
        );

        ArgumentCaptor<OperationLog> captor = ArgumentCaptor.forClass(OperationLog.class);
        verify(operationLogRepository).save(captor.capture());
        OperationLog saved = captor.getValue();
        assertThat(saved.getTargetType()).isEqualTo(OperationLogTargetType.REFUND);
        assertThat(saved.getTargetNo()).isEqualTo("REF001");
        assertThat(saved.getOperation()).isEqualTo(OperationType.AUDIT_APPROVE);
        assertThat(saved.getOperatorId()).isEqualTo(123L);
        assertThat(saved.getOperatorName()).isEqualTo("张三");
        assertThat(saved.getOperatorSystem()).isEqualTo("admin-bff");
        assertThat(saved.getResult()).isEqualTo("SUCCESS");
        assertThat(saved.getRemark()).isEqualTo("同意退款");
    }

    @Test
    @DisplayName("list：按查询条件分页，返回 PageResponse 且仅暴露 DTO")
    void given_queryAndPageable_when_list_then_returnsPageResponseOfDtos() {
        OperationLogQuery query = new OperationLogQuery(
            OperationLogTargetType.REFUND, "REF001", OperationType.AUDIT_APPROVE,
            123L, "admin-bff", "SUCCESS", null, null
        );
        Pageable pageable = PageRequest.of(0, 20);

        OperationLog log = new OperationLog(
            OperationLogTargetType.REFUND, "REF001", OperationType.AUDIT_APPROVE,
            123L, "张三", "admin-bff", "SUCCESS", "同意退款"
        );
        Page<OperationLog> page = new PageImpl<>(List.of(log), pageable, 1);
        when(operationLogRepository.findAll(any(Specification.class), any(Pageable.class)))
            .thenReturn(page);

        OperationLogResponse responseDto = new OperationLogResponse(
            1L, 2, "退款订单", "REF001", 1, "审核通过",
            123L, "张三", "admin-bff", "SUCCESS", "同意退款", LocalDateTime.now()
        );
        when(operationLogMapper.convertList(List.of(log))).thenReturn(List.of(responseDto));

        var result = service.list(query, pageable);

        assertThat(result.items()).containsExactly(responseDto);
        assertThat(result.total()).isEqualTo(1L);
        assertThat(result.page()).isEqualTo(1);
        assertThat(result.size()).isEqualTo(20);
    }

    @Test
    @DisplayName("list：空查询条件也返回 PageResponse（Specification 不抛错）")
    void given_emptyQuery_when_list_then_returnsPageResponse() {
        OperationLogQuery query = new OperationLogQuery(
            null, null, null, null, null, null, null, null
        );
        Pageable pageable = PageRequest.of(0, 10);

        Page<OperationLog> emptyPage = new PageImpl<>(List.of(), pageable, 0);
        when(operationLogRepository.findAll(any(Specification.class), any(Pageable.class)))
            .thenReturn(emptyPage);
        when(operationLogMapper.convertList(List.of())).thenReturn(List.of());

        var result = service.list(query, pageable);

        assertThat(result.items()).isEmpty();
        assertThat(result.total()).isZero();
    }
}
```

- [ ] **Step 2: 运行测试确认失败（AppService 尚未创建）**

Run: `mvn test -Dtest=OperationLogAppServiceTest -Dsurefire.failIfNoSpecifiedTests=false`
Expected: 编译失败（`OperationLogAppService` / DTO / Mapper 不存在）。

- [ ] **Step 3: 创建 `OperationLogQuery`**

创建 `src/main/java/com/aieducenter/payment/application/dto/query/OperationLogQuery.java`：

```java
package com.aieducenter.payment.application.dto.query;

import com.aieducenter.payment.domain.enums.OperationLogTargetType;
import com.aieducenter.payment.domain.enums.OperationType;
import com.cartisan.data.jpa.specification.Condition;
import com.cartisan.data.jpa.specification.ConditionType;

import java.time.LocalDateTime;

/**
 * 操作日志查询条件。
 *
 * <p>字段配合 {@link Condition} 注解，由 {@code ConditionSpecifications.fromAnnotation(query)}
 * 生成 JPA Specification。null 与空字符串自动跳过。</p>
 */
public record OperationLogQuery(
    @Condition(type = ConditionType.EQUAL) OperationLogTargetType targetType,
    @Condition(type = ConditionType.EQUAL) String targetNo,
    @Condition(type = ConditionType.EQUAL) OperationType operation,
    @Condition(type = ConditionType.EQUAL) Long operatorId,
    @Condition(type = ConditionType.EQUAL) String operatorSystem,
    @Condition(type = ConditionType.EQUAL) String result,
    @Condition(propName = "createdAt", type = ConditionType.GREATER_EQUAL) LocalDateTime createdAtStart,
    @Condition(propName = "createdAt", type = ConditionType.LESS_EQUAL) LocalDateTime createdAtEnd
) {
}
```

- [ ] **Step 4: 创建 `OperationLogResponse`**

创建 `src/main/java/com/aieducenter/payment/application/dto/response/OperationLogResponse.java`：

```java
package com.aieducenter.payment.application.dto.response;

import java.time.LocalDateTime;

/**
 * 操作日志响应。
 *
 * <p>枚举以 Integer code + String name 暴露（沿用 RefundOrderResponse 约定）。</p>
 */
public record OperationLogResponse(
    Long id,
    Integer targetType,
    String targetTypeName,
    String targetNo,
    Integer operation,
    String operationName,
    Long operatorId,
    String operatorName,
    String operatorSystem,
    String result,
    String remark,
    LocalDateTime createdAt
) {
}
```

- [ ] **Step 5: 创建 `OperationLogMapper`**

创建 `src/main/java/com/aieducenter/payment/application/mapper/OperationLogMapper.java`：

```java
package com.aieducenter.payment.application.mapper;

import com.aieducenter.payment.application.dto.response.OperationLogResponse;
import com.aieducenter.payment.domain.aggregate.OperationLog;
import com.cartisan.web.mapper.DomainMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * 操作日志映射器。
 *
 * <p>继承 {@link DomainMapper} 获得 {@code convertList}；枚举 → Integer code / String name 显式映射。</p>
 */
@Mapper(componentModel = "spring")
public interface OperationLogMapper extends DomainMapper<OperationLog, OperationLogResponse> {

    @Mapping(target = "targetType", source = "targetType.code")
    @Mapping(target = "targetTypeName", source = "targetType.name")
    @Mapping(target = "operation", source = "operation.code")
    @Mapping(target = "operationName", source = "operation.name")
    @Override
    OperationLogResponse convert(OperationLog operationLog);
}
```

- [ ] **Step 6: 实现 `OperationLogAppService`**

创建 `src/main/java/com/aieducenter/payment/application/OperationLogAppService.java`：

```java
package com.aieducenter.payment.application;

import com.aieducenter.payment.application.dto.query.OperationLogQuery;
import com.aieducenter.payment.application.dto.response.OperationLogResponse;
import com.aieducenter.payment.application.mapper.OperationLogMapper;
import com.aieducenter.payment.domain.aggregate.OperationLog;
import com.aieducenter.payment.domain.enums.OperationLogTargetType;
import com.aieducenter.payment.domain.enums.OperationType;
import com.aieducenter.payment.domain.repository.OperationLogRepository;
import com.cartisan.data.jpa.specification.ConditionSpecifications;
import com.cartisan.web.response.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 操作日志应用服务。
 *
 * <p>供各写操作（审核、通知重发等）调用 {@link #record} 落库；提供 {@link #list} 多条件分页查询。</p>
 */
@Service
@RequiredArgsConstructor
public class OperationLogAppService {

    private final OperationLogRepository operationLogRepository;
    private final OperationLogMapper operationLogMapper;

    /**
     * 记录一条操作日志。
     *
     * @param targetType     目标类型
     * @param targetNo       目标单号
     * @param operation      操作类型
     * @param operatorId     操作者 ID（可空）
     * @param operatorName   操作者名称（可空）
     * @param operatorSystem 来源系统（可空）
     * @param result         操作结果（稳定 token）
     * @param remark         备注（可空）
     */
    @Transactional
    public void record(
            OperationLogTargetType targetType,
            String targetNo,
            OperationType operation,
            Long operatorId,
            String operatorName,
            String operatorSystem,
            String result,
            String remark
    ) {
        OperationLog logEntry = new OperationLog(
            targetType, targetNo, operation,
            operatorId, operatorName, operatorSystem,
            result, remark
        );
        operationLogRepository.save(logEntry);
    }

    /**
     * 多条件分页查询操作日志。
     */
    @Transactional(readOnly = true)
    public PageResponse<OperationLogResponse> list(OperationLogQuery query, Pageable pageable) {
        Page<OperationLog> page = operationLogRepository.findAll(
            ConditionSpecifications.fromAnnotation(query), pageable
        );
        return new PageResponse<>(
            operationLogMapper.convertList(page.getContent()),
            page.getTotalElements(),
            pageable.getPageNumber() + 1,
            pageable.getPageSize()
        );
    }
}
```

- [ ] **Step 7: 运行测试确认通过**

Run: `mvn test -Dtest=OperationLogAppServiceTest -Dsurefire.failIfNoSpecifiedTests=false`
Expected: 3 个测试全部 PASS。

- [ ] **Step 8: Commit**

```bash
git add src/main/java/com/aieducenter/payment/application/ src/test/java/com/aieducenter/payment/application/OperationLogAppServiceTest.java
git commit -m "feat(payment): OperationLogAppService（record + list）+ DTO + Mapper + 缝测试"
```

---

### Task 4: 端点 — `GET /api/v1/operation-logs`

**Files:**
- Create: `src/main/java/com/aieducenter/payment/endpoints/api/v1/OperationLogApiV1Controller.java`

**Interfaces:**
- Consumes: Task 3 的 `OperationLogAppService.list`、`OperationLogQuery`。
- Produces: `GET /api/v1/operation-logs`（`@RequireSignature`，返回 `ApiResponse<PageResponse<OperationLogResponse>>`）。

- [ ] **Step 1: 实现 Controller（薄委托，无独立控制器测试缝）**

创建 `src/main/java/com/aieducenter/payment/endpoints/api/v1/OperationLogApiV1Controller.java`：

```java
package com.aieducenter.payment.endpoints.api.v1;

import com.aieducenter.payment.application.OperationLogAppService;
import com.aieducenter.payment.application.dto.query.OperationLogQuery;
import com.aieducenter.payment.application.dto.response.OperationLogResponse;
import com.cartisan.openapi.annotation.RequireSignature;
import com.cartisan.web.response.ApiResponse;
import com.cartisan.web.response.PageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/operation-logs")
@RequiredArgsConstructor
@Validated
@Tag(name = "Operation Log API v1", description = "操作日志接口 v1")
public class OperationLogApiV1Controller {

    private final OperationLogAppService operationLogAppService;

    @GetMapping
    @RequireSignature
    @Operation(summary = "分页查询操作日志")
    public ApiResponse<PageResponse<OperationLogResponse>> list(
            OperationLogQuery query,
            @PageableDefault(size = 20) Pageable pageable
    ) {
        return ApiResponse.ok(operationLogAppService.list(query, pageable));
    }
}
```

- [ ] **Step 2: 编译确认**

Run: `mvn compile`
Expected: BUILD SUCCESS。

- [ ] **Step 3: Commit**

```bash
git add src/main/java/com/aieducenter/payment/endpoints/api/v1/OperationLogApiV1Controller.java
git commit -m "feat(payment): GET /api/v1/operation-logs 端点（@RequireSignature 多条件分页）"
```

---

### Task 5: 验证 — 全量测试 + 架构守护 + 变异测试

- [ ] **Step 1: 全量单元测试**

Run: `mvn test`
Expected: BUILD SUCCESS，全部测试通过（含新增 OperationLogTest / OperationLogAppServiceTest 与既有套件）。

- [ ] **Step 2: 架构守护测试**

Run: `mvn test -Dtest=ArchitectureTest -Dsurefire.failIfNoSpecifiedTests=false`
Expected: PASS（新代码遵循分层规则：领域层不依赖基础设施、命名规范、无字段注入等）。

- [ ] **Step 3: 变异测试（pitest）**

Run: `mvn org.pitest:pitest-maven:mutationCoverage`
Expected: BUILD SUCCESS，变异分数 ≥ 70%（pom `<mutationThreshold>70</mutationThreshold>`）。

> **注意（pitest 配置现状）**：pom 当前 `<targetClasses>com.aieducenter.aieducenterpayment.*</targetClasses>` 与实际包 `com.aieducenter.payment.*` 不匹配，pitest 可能变异 0 个类（trivially 满足阈值但不验证任何代码）。
> 若确认 0 变异：把 `<targetClasses>` 改为覆盖本 issue 新增类的精确范围（如追加 `<param>com.aieducenter.payment.application.OperationLogAppService</param>` 与 `<param>com.aieducenter.payment.domain.aggregate.OperationLog</param>`），重跑确认新增代码变异分数达标。**不**扩大到全 `payment.*`（既有类未经变异准备，可能拉低分数、超出本 issue 范围）。

- [ ] **Step 4: 收尾提交（若有 pitest 配置调整）**

```bash
git add pom.xml
git commit -m "chore(build): pitest targetClasses 覆盖 OperationLog 新增类"
```

- [ ] **Step 5: `/code-review` 走查 + 提交到 develop**

执行 `/code-review`，按反馈修订后，确认所有改动已提交到当前 `develop` 分支。

---

## Self-Review（计划自检）

**1. Spec coverage（acceptance criteria）：**
- ✅ Flyway 建表 `pay_operation_logs` + 索引 target_no/created_at/operator_id → Task 1。
- ✅ record(...) 落库各字段 → Task 3（Step 6）。
- ✅ list 按 targetType/operation/operatorId/operatorSystem/result/时间筛选并分页 → Task 3（Query 字段 + AppService.list）。
- ✅ GET /api/v1/operation-logs 经签名校验、返回 PageResponse、仅暴露 DTO → Task 4。
- ✅ OperationLogTest（聚合不变式）+ AppService Mockito 缝测试 → Task 2 Step 4、Task 3 Step 1。
- ✅ pitest 过 → Task 5 Step 3。
- ✅ OperationLogRepository 含按 targetNo 查询 → Task 2 Step 8。
- ✅ 继承 AuditableSoftDeletable、TSID 主键 → Task 2 Step 6。
- ✅ 银行无关 → 全程无 ICBC 硬编码（Global Constraints）。

**2. Placeholder scan：** 无 TBD / "类似 Task N" / 无代码步骤；每步含完整代码或精确命令。

**3. Type consistency：** `record(OperationLogTargetType, String, OperationType, Long, String, String, String, String)` 在 Task 2（聚合构造）、Task 3（AppService.record）、Issue #9 spec 三处一致；`list(OperationLogQuery, Pageable) → PageResponse<OperationLogResponse>` 在 Task 3/Task 4 一致；枚举 code 值（PAYMENT=1/REFUND=2；AUDIT_APPROVE=1/AUDIT_REJECT=2/NOTIFY_RESEND=3）在枚举定义与 Task 3 测试 DTO（`2,"退款订单"`, `1,"审核通过"`）一致。
