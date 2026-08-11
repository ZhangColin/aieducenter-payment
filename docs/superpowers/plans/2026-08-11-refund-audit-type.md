# RefundOrder 引入 auditType（AUTO/MANUAL）实现计划

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** RefundOrder 新增 `auditType` 枚举（`AUTO`|`MANUAL`）+ 列 `audit_type` + Flyway 迁移 + 历史回填（`auditor_id` 非空→`MANUAL`，否则 `AUTO`）；`createRefund` 免审分支置 `AUTO`（`auditorId` 保持空），`auditRefund` 置 `MANUAL`（`auditorId` 取自 command）；`RefundOrderResponse` 暴露 `auditType`。

**Architecture:** DDD 六边形，最小改动。聚合层把「审核类型」从隐式哨兵（`auditorName="SYSTEM"`）提升为显式枚举字段：拆分 `audit(...)`（人工，内置置 `MANUAL`）与新增 `autoAudit()`（免审，置 `AUTO` 且 `auditorId/Name` 为空）两个意图清晰的方法，彻底移除 `SYSTEM` 哨兵。两条测试缝：聚合单元测试（两路径取值，沿用 `RefundOrderTest` 模式）+ AppService Mockito 缝（沿用 `RefundAppServiceTest`）。

**Tech Stack:** Java 21, Spring Boot, JPA/Hibernate, cartisan-data-jpa（`BaseEnum`/`BaseEnumConverter`/`AuditableSoftDeletable`）, JUnit 5 + Mockito + AssertJ, Flyway（PostgreSQL）, pitest。

## 关联文档

- 父 spec：Issue #8（实现规约，story 27「退款审核区分 AUTO/MANUAL，不被哨兵值误导」）。
- 术语：`CONTEXT.md`（`auditType` 词条、`_Avoid_: 用 auditorName="SYSTEM" 哨兵判断`）。
- 编码规范：`docs/guide/限界上下文代码编写规范.md`。
- 先例：`2026-04-14-refund-need-audit.md`（免审 `needAudit` 通道，本 issue 在其上把哨兵字段化）。

## Global Constraints

- **`audit()` 签名不变**：保留 `audit(Long auditorId, String auditorName, Boolean agreed, String remark)`，仅在方法体内置 `this.auditType = AuditType.MANUAL`。所有既有调用点（AppService `auditRefund`、测试辅助方法）零改动。
- **唯一调用点改动**：`RefundAppService.createRefund` 免审分支由 `saved.audit(null, "SYSTEM", true, "免审自动通过")` 改为 `saved.autoAudit()`。
- **移除哨兵**：AUTO 路径 `auditorId=null` 且 `auditorName=null`（不再写 `"SYSTEM"`），`auditRemark=null`（`auditType=AUTO` 即信号，不留哨兵式备注，呼应 CONTEXT.md `_Avoid_`）。
- **BaseEnum**：`AuditType` 放 `domain/enums/`，实现 `BaseEnum<AuditType>` + 内部 `JpaConverter`（`@Converter(autoApply=true)`），DB 存 Integer code。
- **银行无关**：全程无 ICBC / 具体银行硬编码（CONTEXT.md 不变式 5）。
- **导入顺序**：Java 标准库 → hutool → Jakarta/Spring → cartisan → 项目内部。
- **DTO 只用 Record**；响应只暴露 DTO，不暴露聚合；枚举以 Integer code + String name 暴露（沿用 `RefundOrderResponse` 的 `status/statusName` 约定）。
- **测试命名**：`given_X_when_Y_then_Z`，AssertJ 断言。

## 关键设计决策（锁定）

| 决策点 | 取值 | 理由 |
|--------|------|------|
| 聚合 API | 拆 `audit(...)`（MANUAL）+ 新增 `autoAudit()`（AUTO） | 意图清晰、编码不变式（AUTO 恒无 auditor 且通过；MANUAL 恒有 auditor），彻底移除 `SYSTEM` 哨兵；`audit()` 签名不变→既有调用点零改动。 |
| `audit_type` 列类型 | `INTEGER`（BaseEnum code：`AUTO=1`/`MANUAL=2`） | 与 `RefundStatus`/`OperationType` 一致；DB 存 code，响应暴露 code+name。 |
| AUTO 时 `auditRemark` | `null` | `auditType=AUTO` 即信号，不留哨兵式备注（CONTEXT.md `_Avoid_` 呼应）。 |
| AUTO 时 `auditorId/Name` | 均 `null` | spec 明示「auditorId 保持空」；不再写 `"SYSTEM"`。 |
| 响应字段 | `auditType`(Integer) + `auditTypeName`(String)，紧随 `auditRemark` | 与 `RefundOrderResponse` 的 `status/statusName`、`OperationLogResponse` 一致。 |
| 回填规则 | `auditor_id IS NOT NULL → 2(MANUAL)`，否则 `1(AUTO)`；回填后 `NOT NULL` | spec 明示。历史免审单 `auditor_id` 本就为空→`AUTO`；人工审核单有 `auditor_id`→`MANUAL`。 |
| pitest | 追加 `com.aieducenter.payment.domain.aggregate.RefundOrder` 到 `<targetClasses>` | issue 要求「pitest 过」；与 operation-log 先例（新聚合入 pitest + 单独 chore commit）一致。 |

## File Structure

```
src/main/resources/db/migration/
└── V8__add_refund_audit_type.sql                         [新] 加列 + 回填 + NOT NULL

src/main/java/com/aieducenter/payment/
├── domain/
│   ├── enums/
│   │   └── AuditType.java                                [新] 枚举 AUTO(1)/MANUAL(2)
│   └── aggregate/
│       └── RefundOrder.java                              [改] +auditType 字段；audit() 置 MANUAL；新增 autoAudit()
├── application/
│   ├── dto/response/
│   │   └── RefundOrderResponse.java                      [改] +auditType +auditTypeName
│   └── RefundAppService.java                             [改] createRefund 免审分支 audit→autoAudit；toResponse 透传 auditType
└── pom.xml                                               [改] pitest targetClasses +RefundOrder

src/test/java/com/aieducenter/payment/
├── domain/aggregate/
│   └── RefundOrderTest.java                              [改] 扩展：AUTO(autoAudit) / MANUAL(audit) 两路径取值
└── application/
    └── RefundAppServiceTest.java                         [改] createRefund 免审分支断言 auditType=AUTO
```

---

### Task 1: Flyway 迁移——加列 `audit_type` + 历史回填

**Files:**
- Create: `src/main/resources/db/migration/V8__add_refund_audit_type.sql`

**Interfaces:**
- Produces: `pay_refund_orders.audit_type INTEGER NOT NULL`，历史单按 `auditor_id` 是否非空回填 `MANUAL(2)`/`AUTO(1)`。

- [ ] **Step 1: 写迁移脚本**

创建 `src/main/resources/db/migration/V8__add_refund_audit_type.sql`：

```sql
-- ========================================================================
-- Payment Context: RefundOrder 引入 auditType（AUTO/MANUAL）
-- 把「审核类型」从隐式哨兵（auditor_name='SYSTEM'）提升为显式枚举列。
-- 免审（创建即自动通过）→ AUTO(1)；人工审核（操作者放行/拒绝）→ MANUAL(2)。
-- 历史回填：auditor_id 非空 → MANUAL，否则 → AUTO（见 CONTEXT.md auditType 词条）。
-- ========================================================================

ALTER TABLE pay_refund_orders ADD COLUMN audit_type INTEGER;

UPDATE pay_refund_orders
SET audit_type = CASE WHEN auditor_id IS NOT NULL THEN 2 ELSE 1 END;

ALTER TABLE pay_refund_orders ALTER COLUMN audit_type SET NOT NULL;
```

- [ ] **Step 2: 编译确认（迁移在 `mvn test` 启动期由 Flyway 执行）**

Run: `mvn compile`
Expected: BUILD SUCCESS。

- [ ] **Step 3: Commit**

```bash
git add src/main/resources/db/migration/V8__add_refund_audit_type.sql
git commit -m "feat(payment): V8 迁移——RefundOrder.auditType 列 + 历史回填（AUTO/MANUAL）"
```

---

### Task 2: 领域层——AuditType 枚举 + RefundOrder 字段/方法（TDD）

**Files:**
- Create: `src/main/java/com/aieducenter/payment/domain/enums/AuditType.java`
- Modify: `src/main/java/com/aieducenter/payment/domain/aggregate/RefundOrder.java`
- Test: `src/test/java/com/aieducenter/payment/domain/aggregate/RefundOrderTest.java`

**Interfaces:**
- Produces（供 Task 3 消费）：
  - `enum AuditType implements BaseEnum<AuditType>` — `AUTO(1, "免审")`, `MANUAL(2, "人工审核")`
  - `RefundOrder.getAuditType() → AuditType`
  - `RefundOrder.autoAudit() → void`（PENDING→APPROVED，置 `auditType=AUTO`，`auditorId/Name/remark` 保持空）
  - `audit(Long, String, Boolean, String)` 行为不变，额外置 `auditType=MANUAL`

- [ ] **Step 1: 创建枚举 `AuditType`**

创建 `src/main/java/com/aieducenter/payment/domain/enums/AuditType.java`：

```java
package com.aieducenter.payment.domain.enums;

import com.cartisan.core.domain.BaseEnum;
import com.cartisan.data.jpa.converter.BaseEnumConverter;
import jakarta.persistence.Converter;

/**
 * 退款审核类型。
 *
 * <p>{@link #AUTO} 为免审（创建时系统自动通过，{@code auditorId} 为空）；
 * {@link #MANUAL} 为人工审核（操作者放行/拒绝，{@code auditorId} 非空）。</p>
 *
 * <p>判断「是否人工审核」应看本字段，而非 {@code auditorName} 之类哨兵值（见 CONTEXT.md）。</p>
 */
public enum AuditType implements BaseEnum<AuditType> {
    AUTO(1, "免审"),
    MANUAL(2, "人工审核");

    private final Integer code;
    private final String name;

    AuditType(Integer code, String name) {
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
    public static class JpaConverter extends BaseEnumConverter<AuditType> {
        public JpaConverter() {
            super(AuditType.class);
        }
    }
}
```

- [ ] **Step 2: 写失败测试（领域缝）——扩展 `RefundOrderTest`**

在 `src/test/java/com/aieducenter/payment/domain/aggregate/RefundOrderTest.java` 追加（并在既有审核通过/拒绝用例补 `auditType` 断言）：

```java
// 既有 given_pendingRefund_when_auditApproved_then_statusChangesToApproved 的 Then 段补：
assertThat(order.getAuditType()).isEqualTo(AuditType.MANUAL);

// 既有 given_pendingRefund_when_auditRejected_then_statusChangesToRejected 的 Then 段补：
assertThat(order.getAuditType()).isEqualTo(AuditType.MANUAL);
```

新增两个用例：

```java
@Test
@DisplayName("给定待审核退款，免审自动通过时应该置 auditType=AUTO 且无审核者")
void given_pendingRefund_when_autoAudit_then_auditTypeAutoAndNoAuditor() {
    RefundOrder order = new RefundOrder(
        "ORDER001", "PAY001", "TestSystem",
        "课程购买", 10000L, 10000L, "Reason", null, null
    );

    order.autoAudit();

    assertThat(order.getAuditType()).isEqualTo(AuditType.AUTO);
    assertThat(order.getStatus()).isEqualTo(RefundStatus.APPROVED);
    assertThat(order.isApproved()).isTrue();
    assertThat(order.getAuditorId()).isNull();
    assertThat(order.getAuditorName()).isNull();
    assertThat(order.getAuditAgreed()).isTrue();
    assertThat(order.getAuditedAt()).isNotNull();
    assertThat(order.getApprovedAt()).isNotNull();
}

@Test
@DisplayName("给定非待审核退款（已审核），免审自动通过时应该抛出异常")
void given_auditedRefund_when_autoAuditAgain_then_throwsException() {
    RefundOrder order = new RefundOrder(
        "ORDER001", "PAY001", "TestSystem",
        "课程购买", 10000L, 10000L, "Reason", null, null
    );
    order.audit(123L, "张三", true, "同意");

    assertThatThrownBy(() -> order.autoAudit())
        .isInstanceOf(com.cartisan.core.exception.DomainException.class)
        .hasMessageContaining("退款订单不是待审核状态");
}
```

并在测试类顶部补 import：`import com.aieducenter.payment.domain.enums.AuditType;`

- [ ] **Step 3: 运行测试确认失败**

Run: `mvn test -Dtest=RefundOrderTest -Dsurefire.failIfNoSpecifiedTests=false`
Expected: 编译失败（`AuditType` 不存在；`RefundOrder.getAuditType()` / `autoAudit()` 不存在）。

- [ ] **Step 4: 修改 `RefundOrder`——加字段 + `autoAudit()` + `audit()` 置 MANUAL**

在 `src/main/java/com/aieducenter/payment/domain/aggregate/RefundOrder.java`：

(a) import 段加 `import com.aieducenter.payment.domain.enums.AuditType;`

(b) 在「Audit information」字段块（`auditorId` 之前）加：

```java
    @Getter
    @Column(name = "audit_type", nullable = false)
    private AuditType auditType;
```

(c) `audit(...)` 方法体内（在 `Assertions.require(...)` 之后、赋值段）加 `this.auditType = AuditType.MANUAL;`

(d) 在 `audit(...)` 方法之后新增 `autoAudit()`：

```java
    /**
     * 免审自动通过（创建退款时 needAudit=false 触发）。
     *
     * <p>置 {@code auditType=AUTO}，不记录审核者（{@code auditorId/Name} 保持空），
     * 直接进入 APPROVED。判断「是否免审」应看 {@link #getAuditType()}，而非审核者哨兵值。</p>
     */
    public void autoAudit() {
        Assertions.require(this.status == RefundStatus.PENDING,
            PaymentMessage.REFUND_ORDER_NOT_PENDING);

        this.auditType = AuditType.AUTO;
        this.auditAgreed = true;
        this.auditedAt = LocalDateTime.now();
        this.status = RefundStatus.APPROVED;
        this.approvedAt = LocalDateTime.now();
    }
```

- [ ] **Step 5: 运行测试确认通过**

Run: `mvn test -Dtest=RefundOrderTest -Dsurefire.failIfNoSpecifiedTests=false`
Expected: 全部 PASS（含新增 2 个用例 + 既有用例补的断言）。

- [ ] **Step 6: Commit**

```bash
git add src/main/java/com/aieducenter/payment/domain/enums/AuditType.java \
        src/main/java/com/aieducenter/payment/domain/aggregate/RefundOrder.java \
        src/test/java/com/aieducenter/payment/domain/aggregate/RefundOrderTest.java
git commit -m "feat(payment): RefundOrder 引入 auditType（AUTO 免审 / MANUAL 人工）+ autoAudit()"
```

---

### Task 3: 应用层——Response 暴露 auditType + AppService 免审分支改 autoAudit

**Files:**
- Modify: `src/main/java/com/aieducenter/payment/application/dto/response/RefundOrderResponse.java`
- Modify: `src/main/java/com/aieducenter/payment/application/RefundAppService.java`
- Test: `src/test/java/com/aieducenter/payment/application/RefundAppServiceTest.java`

**Interfaces:**
- Consumes: Task 2 的 `RefundOrder.getAuditType()`、`autoAudit()`、`AuditType`。
- Produces: `RefundOrderResponse` 新增 `auditType`(Integer) + `auditTypeName`(String)；`createRefund` 免审分支调 `autoAudit()`。

- [ ] **Step 1: 写失败测试——免审创建返回 auditType=AUTO**

在 `RefundAppServiceTest.createRefund_needAuditFalse_autoApproveAndRefund` 的断言段补：

```java
// 验证免审路径 auditType=AUTO（1）
assertThat(response.auditType()).isEqualTo(1);
assertThat(response.auditTypeName()).isEqualTo("免审");
```

并在 `auditRefund_approved_refundSuccess`（人工审核通过路径）补：

```java
assertThat(response.auditType()).isEqualTo(2); // MANUAL
```

- [ ] **Step 2: 运行测试确认失败**

Run: `mvn test -Dtest=RefundAppServiceTest -Dsurefire.failIfNoSpecifiedTests=false`
Expected: 编译失败（`RefundOrderResponse` 无 `auditType()`/`auditTypeName()` 访问器）。

- [ ] **Step 3: 修改 `RefundOrderResponse`——加两字段**

在 `auditRemark` 之后加 `auditType` + `auditTypeName`（保持 record 字段顺序）：

```java
public record RefundOrderResponse(
    Long id,
    String businessOrderNo,
    String refundOrderNo,
    String paymentOrderNo,
    String businessSystemName,
    String businessName,
    Integer status,
    String statusName,
    Long refundAmount,
    Long refundableAmount,
    String reason,
    String auditorName,
    Boolean auditAgreed,
    String auditRemark,
    Integer auditType,
    String auditTypeName,
    LocalDateTime createdAt,
    LocalDateTime approvedAt,
    LocalDateTime refundedAt,
    LocalDateTime failedAt,
    String bankRefundNo,
    String notifyUrl
) {
}
```

- [ ] **Step 4: 修改 `RefundAppService`——免审分支改 `autoAudit()` + `toResponse` 透传**

(a) `createRefund` 免审分支（约第 76–80 行）：

```java
        // 4. 免审：自动审核通过并发起退款
        if (!command.isNeedAudit()) {
            saved.autoAudit();
            saved = refundOrderRepository.save(saved);
            executeRefundAfterApproval(saved);
        }
```

(b) `toResponse(...)` 在 `auditRemark` 与 `createdAt` 之间插入两行：

```java
            order.getAuditRemark(),
            order.getAuditType().getCode(),
            order.getAuditType().getName(),
            order.getCreatedAt(),
```

- [ ] **Step 5: 运行测试确认通过**

Run: `mvn test -Dtest=RefundAppServiceTest -Dsurefire.failIfNoSpecifiedTests=false`
Expected: 全部 PASS。

- [ ] **Step 6: Commit**

```bash
git add src/main/java/com/aieducenter/payment/application/dto/response/RefundOrderResponse.java \
        src/main/java/com/aieducenter/payment/application/RefundAppService.java \
        src/test/java/com/aieducenter/payment/application/RefundAppServiceTest.java
git commit -m "feat(payment): RefundOrderResponse 暴露 auditType；免审分支改 autoAudit() 移除 SYSTEM 哨兵"
```

---

### Task 4: 验证——全量测试 + 架构守护 + 变异测试

- [ ] **Step 1: 全量单元测试**

Run: `mvn test`
Expected: BUILD SUCCESS（含扩展后的 `RefundOrderTest` / `RefundAppServiceTest` 与既有套件）。

- [ ] **Step 2: 架构守护测试**

Run: `mvn test -Dtest=ArchitectureTest -Dsurefire.failIfNoSpecifiedTests=false`
Expected: PASS（`AuditType` 枚举遵循分层规则；`RefundOrder` 仍在领域层、不依赖基础设施）。

- [ ] **Step 3: 追加 `RefundOrder` 到 pitest `<targetClasses>`**

在 `pom.xml` 的 `<targetClasses>` 段（约 235–239 行）追加：

```xml
                        <param>com.aieducenter.payment.domain.aggregate.RefundOrder</param>
```

并在该段上方注释补一行说明（issue #11 追加）。

- [ ] **Step 4: 变异测试（pitest）**

Run: `mvn org.pitest:pitest-maven:mutationCoverage`
Expected: BUILD SUCCESS，变异分数 ≥ 70%。

> 若 RefundOrder 因既有未覆盖分支（如构造校验 `AMOUNT_INVALID` 的 `businessOrderNo` 空白 / `refundAmount<=0` 两条）拉低分数：补 2 条领域缝测试覆盖这两条校验分支，再重跑。**不**扩大到全 `payment.*`（既有类未经变异准备）。

- [ ] **Step 5: 收尾提交**

```bash
git add pom.xml
git commit -m "chore(build): pitest 覆盖 RefundOrder（auditType/autoAudit 变异）"
```

- [ ] **Step 6: `/code-review` 走查**

执行 `/code-review`，按反馈修订后确认所有改动已提交到当前 `develop` 分支。

---

## Self-Review（计划自检）

**1. Spec coverage（acceptance criteria）：**
- ✅ 迁移与回填正确（`auditor_id` 非空→MANUAL，否则 AUTO）→ Task 1。
- ✅ 免审创建→AUTO 且 `auditorId` 空；人工审核→MANUAL 且 `auditorId` 非空 → Task 2（`autoAudit()` / `audit()` 内置）+ Task 3（AppService 免审分支）。
- ✅ 退款详情响应含 auditType；不再依赖 `auditorName="SYSTEM"` 哨兵 → Task 3（Response + 移除哨兵）。
- ✅ 扩展 `RefundOrderTest` 覆盖两路径取值，pitest 过 → Task 2 Step 2 + Task 4。

**2. Placeholder scan：** 无 TBD / "类似 Task N"；每步含完整代码或精确命令。

**3. Type consistency：** `AuditType` code 值（`AUTO=1`/`MANUAL=2`）在枚举定义、Task 1 回填 SQL（`1`/`2`）、Task 3 测试断言（`1`/`2`、"免审"）三处一致；`audit_type` 列名在 V1（待加列的表 `pay_refund_orders`）、V8 迁移、`RefundOrder` `@Column(name="audit_type")` 一致；`audit()` 签名 `(Long, String, Boolean, String)` 不变→既有 8 处调用点零改动，仅 `createRefund` 第 77 行改为 `autoAudit()`。
