# 订单生命周期读模型（OrderLifecycleAppService）实现计划

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** 新增 `OrderLifecycleAppService.getLifecycle(orderNo)`：按 `createdAt` 升序合并某支付订单的 PaymentLog（既有按单查询）与 OperationLog（T1 的按 targetNo 查询），返回统一事件序列（只读、不写库、不改状态，见 ADR-0002）。端点 `GET /api/v1/orders/{orderNo}/lifecycle`。

**Architecture:** DDD 六边形**读模型**（ADR-0002：不合表、合视图）。复用既有两个仓储查询方法——`PaymentLogRepository.findByPaymentOrderNoOrderByCreatedAtDesc(orderNo)` 与 `OperationLogRepository.findByTargetNoOrderByCreatedAtDesc(orderNo)`——**不新建表、不新建聚合、不新增仓储方法、不写库**。应用层把两份日志各自映射为统一事件 DTO（标注来源 `GATEWAY`/`OPERATION`），合并后按 `createdAt` 升序、`id` 升序稳定排序返回。一条测试缝：AppService Mockito 缝（mock 仓储 + Mapper，验证合并 + 排序逻辑，变异友好）。

**Tech Stack:** Java 21，Spring Boot，cartisan-data-jpa（`BaseRepository` / `AuditableSoftDeletable` / TSID），cartisan-openapi（`@RequireSignature`），JUnit 5 + Mockito + AssertJ，pitest。

## 关联文档

- 父 spec：Issue #8（实现规约，User Story 10「订单完整生命周期」）。
- 本 issue：#14（T6 · 订单生命周期读模型）。
- 依赖：#9（T1 · OperationLog 能力）——**代码层已落地**（聚合 / 仓储 `findByTargetNoOrderByCreatedAtDesc` / 枚举 / `V6__create_operation_logs.sql` 均在库），本计划直接消费。
- 术语：`CONTEXT.md`（订单生命周期 = 两表合并的只读视图；PaymentLog / OperationLog 职责分离）。
- 决策：`docs/adr/0002-operation-log-separation.md`（不合表、合视图）、`docs/adr/0001-no-admin-state-mutation.md`（运营不施加状态，本端点只读）。
- 编码规范：`docs/guide/限界上下文代码编写规范.md`。

## Global Constraints

- **只读读模型**：`@Transactional(readOnly = true)`，全程不调用任何 `save` / 状态迁移方法，不改订单状态（ADR-0001、ADR-0002）。
- **银行无关**（CONTEXT.md 不变式 5）：统一事件 DTO 不出现 ICBC / 具体银行硬编码；网关事件只透出 `bankCode` / `bankInterface` 通用列。
- **复用既有查询**：不新增仓储方法、不新增迁移；`orderNo` 语义 = **支付订单号**（PaymentLog 按 `paymentOrderNo` 查，OperationLog 按 `targetNo` 查——支付/退款单号命名空间不同，`targetNo` 维度足够）。
- **排序约定**：合并后按 `createdAt` **升序**（"从创建到终态"的时间线，CONTEXT.md · 订单生命周期）；相同 `createdAt` 时按 `id` 升序稳定排序（TSID 全局唯一且含时间序，跨表可比，保证确定性）。
- **DTO 只用 Record**；响应只暴露 DTO，不暴露聚合。
- **Mapper 为手写 `@Component` 类**（非 MapStruct）：生命周期是**两源 → 一目标**的合并读模型，不适用 MapStruct `DomainMapper` 的单源 `convert` 模式；故 `application/mapper/OrderLifecycleMapper` 用普通类提供 `fromPaymentLog` / `fromOperationLog` 两法，由 AppService 注入。Mapper 不进 pitest 评分（属映射层，与 MapStruct 生成实现同等对待，见 pom 注释）。
- **构造函数注入**（`@RequiredArgsConstructor`），禁止字段注入。
- **导入顺序**：Java 标准库 → Jakarta/Spring → cartisan → 项目内部（本计划无 hutool 依赖）。
- **测试命名**：`given_X_when_Y_then_Z`，AssertJ 断言；Mockito 严格存根（`MockitoExtension` 默认 STRICT_STUBS），仅存根实际调用的方法。
- **签名校验**：端点 `@RequireSignature`；GET 请求体为空 → bodyDigest = SHA-256(空)，path 变量参与签名串（框架已支持）。

## 关键设计决策（锁定）

| 决策点 | 取值 | 理由 |
|--------|------|------|
| `orderNo` 语义 | 支付订单号 | issue #14 明示「该订单的 PaymentLog（既有按单查询）」——既有按单查询即 `findByPaymentOrderNoOrderByCreatedAtDesc`。OperationLog 以 `targetNo=支付订单号` 关联（PAYMENT 类操作）。退款单号命名空间独立，无碰撞。 |
| 排序方向 | `createdAt` **升序** | CONTEXT.md「从创建到终态的完整事件序列」——时间线按时间正序阅读最自然。仓储方法返回 DESC（列表场景约定），AppService 合并后**显式重排为升序**，不透传仓储顺序。 |
| 同时间稳定键 | `id` 升序 | 跨两表的 TSID 全局唯一、含时间序，可比；保证相同 `createdAt` 下输出确定（变异友好：可写同时间跨来源用例杀死「移除 thenComparing」变异）。 |
| 是否分页 | 不分页，返回 `List` | 单订单事件序列有界（数十至数百条），生命周期是一次性"看全"视图，非海量分页列表（区别于 PaymentLog/OperationLog 的多条件分页端点）。 |
| 来源标注 `source` 类型 | `String`（`"GATEWAY"`/`"OPERATION"`） | AC 明示 `GATEWAY`/`OPERATION` 字面值；用 String 避免枚举 Jackson 序列化歧义，与 PaymentLog.logType 的 String 约定一致。 |
| Mapper 形态 | 手写 `@Component` 类（非 MapStruct 接口） | 两源 → 一目标不适用 MapStruct `DomainMapper<单源,单目标>`；手写两法 `fromPaymentLog` / `fromOperationLog` 最直接，且可被 Mockito mock，让 AppService 缝聚焦"合并排序"。 |
| `outcome`（结果 token）统一 | GATEWAY：`success ? "SUCCESS" : "FAILED"`；OPERATION：取 `result` | 把两类来源的结果归一为 `SUCCESS`/`FAILED` 稳定 token，前端可统一渲染成败（银行无关，不暴露 returnCode 语义）。 |
| 订单不存在是否 404 | **否**，返回空列表 | 读模型只合并两表记录；无事件则空序列是合法生命周期。避免引入 `PaymentOrderRepository` 依赖与「订单存在性」耦合（AC 未要求 404）。 |
| pitest 范围 | 追加 `com.aieducenter.payment.application.OrderLifecycleAppService` | 沿用 pom 既有策略：仅精确覆盖有 Mockito 缝的查询应用服务；Mapper（映射层）与 Controller（薄委托）不进评分。 |

## File Structure

```
src/main/java/com/aieducenter/payment/
├── application/
│   ├── dto/
│   │   └── response/
│   │       └── OrderLifecycleResponse.java          [新] 统一事件 DTO（来源标签 + 语义字段）
│   ├── mapper/
│   │   └── OrderLifecycleMapper.java                [新] 手写 @Component：fromPaymentLog / fromOperationLog
│   └── OrderLifecycleAppService.java                [新] getLifecycle(orderNo)：合并两源 + 升序排序
└── endpoints/api/v1/
    └── OrderLifecycleApiV1Controller.java            [新] GET /api/v1/orders/{orderNo}/lifecycle

src/test/java/com/aieducenter/payment/
└── application/
    └── OrderLifecycleAppServiceTest.java             [新] Mockito 缝：合并排序逻辑

pom.xml                                               [改] pitest targetClasses 追加 OrderLifecycleAppService
```

**不新增**：迁移（无新表/列）、聚合、仓储方法、枚举、错误码。全部复用 #9（OperationLog）与既有（PaymentLog）成果。

---

### Task 1: 应用层读模型 — TDD（Response DTO + Mapper + AppService + 缝测试）

**Files:**
- Create: `src/main/java/com/aieducenter/payment/application/dto/response/OrderLifecycleResponse.java`
- Create: `src/main/java/com/aieducenter/payment/application/mapper/OrderLifecycleMapper.java`
- Create: `src/main/java/com/aieducenter/payment/application/OrderLifecycleAppService.java`
- Test: `src/test/java/com/aieducenter/payment/application/OrderLifecycleAppServiceTest.java`

**Interfaces:**
- Consumes（既有，#9 与 PaymentLog）：
  - `PaymentLogRepository.findByPaymentOrderNoOrderByCreatedAtDesc(String paymentOrderNo) → List<PaymentLog>`
  - `OperationLogRepository.findByTargetNoOrderByCreatedAtDesc(String targetNo) → List<OperationLog>`
  - `PaymentLog`（聚合，14 参构造；`getLogType/getBankCode/getBankInterface/getSuccess/getReturnMsg/getErrorMessage/getId/getCreatedAt`）
  - `OperationLog`（聚合，8 参构造；`getOperation → OperationType`、`getOperatorName/getOperatorSystem/getResult/getRemark/getId/getCreatedAt`）
- Produces（供 Task 2 消费）：
  - `record OrderLifecycleResponse(Long id, String source, LocalDateTime createdAt, String action, String actionName, String outcome, String performer, String performerSystem, String detail)`
  - `OrderLifecycleMapper.fromPaymentLog(PaymentLog) → OrderLifecycleResponse`（`@Component`）
  - `OrderLifecycleMapper.fromOperationLog(OperationLog) → OrderLifecycleResponse`（`@Component`）
  - `OrderLifecycleAppService.getLifecycle(String orderNo) → List<OrderLifecycleResponse>`（`@Transactional(readOnly = true)`）

- [ ] **Step 1: 写失败测试（AppService Mockito 缝）**

创建 `src/test/java/com/aieducenter/payment/application/OrderLifecycleAppServiceTest.java`：

```java
package com.aieducenter.payment.application;

import com.aieducenter.payment.application.dto.response.OrderLifecycleResponse;
import com.aieducenter.payment.application.mapper.OrderLifecycleMapper;
import com.aieducenter.payment.domain.aggregate.OperationLog;
import com.aieducenter.payment.domain.aggregate.PaymentLog;
import com.aieducenter.payment.domain.enums.OperationLogTargetType;
import com.aieducenter.payment.domain.enums.OperationType;
import com.aieducenter.payment.domain.repository.OperationLogRepository;
import com.aieducenter.payment.domain.repository.PaymentLogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * OrderLifecycleAppService 测试（AppService Mockito 缝）。
 *
 * <p>沿用 PaymentLogQueryAppServiceTest / OperationLogAppServiceTest 模式：mock 仓储与 Mapper，
 * 构造被测服务直接驱动。Mapper 被 mock → 聚合的 id/createdAt（持久化时由 JPA/TSID 注入）不影响测试；
 * 排序键由 Mapper 返回的 canned DTO 的 createdAt/id 携带，精准验证「合并 + 升序排序」逻辑（变异友好）。</p>
 *
 * <p>覆盖（AC：合并排序逻辑）：① 两源合并、按 createdAt 升序（仓储返回倒序，验证显式升序重排）；
 * ② 相同 createdAt 跨来源按 id 升序稳定； ③ 两源均空返回空列表。</p>
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("订单生命周期应用服务测试")
class OrderLifecycleAppServiceTest {

    @Mock private PaymentLogRepository paymentLogRepository;
    @Mock private OperationLogRepository operationLogRepository;
    @Mock private OrderLifecycleMapper orderLifecycleMapper;

    private OrderLifecycleAppService service;

    @BeforeEach
    void setUp() {
        service = new OrderLifecycleAppService(
            paymentLogRepository, operationLogRepository, orderLifecycleMapper
        );
    }

    @Test
    @DisplayName("getLifecycle：合并 GATEWAY 与 OPERATION 事件，按 createdAt 升序返回（仓储倒序输入）")
    void given_interleavedEvents_when_getLifecycle_then_mergedAndSortedByCreatedAtAsc() {
        String orderNo = "PAY20260811001";
        LocalDateTime t1 = LocalDateTime.of(2026, 8, 11, 9, 0, 0);
        LocalDateTime t2 = LocalDateTime.of(2026, 8, 11, 9, 5, 0);
        LocalDateTime t3 = LocalDateTime.of(2026, 8, 11, 9, 10, 0);
        LocalDateTime t4 = LocalDateTime.of(2026, 8, 11, 9, 15, 0);

        // 仓储按 createdAt DESC 返回（既有方法约定）；AppService 须显式重排为 ASC。
        PaymentLog gatewayEarly = gatewayLog(orderNo, "PAYMENT_REQUEST");
        PaymentLog gatewayLate = gatewayLog(orderNo, "PAYMENT_QUERY");
        when(paymentLogRepository.findByPaymentOrderNoOrderByCreatedAtDesc(orderNo))
            .thenReturn(List.of(gatewayLate, gatewayEarly));   // DESC：晚的在前

        OperationLog opMid = operationLog(orderNo, OperationType.AUDIT_APPROVE);
        OperationLog opLast = operationLog(orderNo, OperationType.NOTIFY_RESEND);
        when(operationLogRepository.findByTargetNoOrderByCreatedAtDesc(orderNo))
            .thenReturn(List.of(opLast, opMid));               // DESC：晚的在前

        // Mapper canned DTO：携带排序键（createdAt / id），来源标签 + action 区分。
        OrderLifecycleResponse r1 = event(100L, "GATEWAY", t1, "PAYMENT_REQUEST");
        OrderLifecycleResponse r2 = event(200L, "OPERATION", t2, "AUDIT_APPROVE");
        OrderLifecycleResponse r3 = event(101L, "GATEWAY", t3, "PAYMENT_QUERY");
        OrderLifecycleResponse r4 = event(201L, "OPERATION", t4, "NOTIFY_RESEND");
        when(orderLifecycleMapper.fromPaymentLog(gatewayEarly)).thenReturn(r1);
        when(orderLifecycleMapper.fromPaymentLog(gatewayLate)).thenReturn(r3);
        when(orderLifecycleMapper.fromOperationLog(opMid)).thenReturn(r2);
        when(orderLifecycleMapper.fromOperationLog(opLast)).thenReturn(r4);

        List<OrderLifecycleResponse> result = service.getLifecycle(orderNo);

        assertThat(result).containsExactly(r1, r2, r3, r4);    // 升序：t1<t2<t3<t4（跨来源交错）
        assertThat(result).extracting(OrderLifecycleResponse::source)
            .containsExactly("GATEWAY", "OPERATION", "GATEWAY", "OPERATION");
    }

    @Test
    @DisplayName("getLifecycle：相同 createdAt 跨来源时按 id 升序稳定排序")
    void given_sameCreatedAtAcrossSources_when_getLifecycle_then_sortedByIdAsc() {
        String orderNo = "PAY20260811002";
        LocalDateTime sameTime = LocalDateTime.of(2026, 8, 11, 10, 0, 0);

        // 插入顺序：先 GATEWAY(id=200) 后 OPERATION(id=100)；同 createdAt。
        // 有 thenComparing(id) → [100, 200]；无（仅稳定排序）→ 维持插入序 [200, 100]（变异被杀死）。
        PaymentLog gateway = gatewayLog(orderNo, "PAYMENT_REQUEST");
        when(paymentLogRepository.findByPaymentOrderNoOrderByCreatedAtDesc(orderNo))
            .thenReturn(List.of(gateway));
        OperationLog operation = operationLog(orderNo, OperationType.NOTIFY_RESEND);
        when(operationLogRepository.findByTargetNoOrderByCreatedAtDesc(orderNo))
            .thenReturn(List.of(operation));

        OrderLifecycleResponse gatewayEvent = event(200L, "GATEWAY", sameTime, "PAYMENT_REQUEST");
        OrderLifecycleResponse operationEvent = event(100L, "OPERATION", sameTime, "NOTIFY_RESEND");
        when(orderLifecycleMapper.fromPaymentLog(gateway)).thenReturn(gatewayEvent);
        when(orderLifecycleMapper.fromOperationLog(operation)).thenReturn(operationEvent);

        List<OrderLifecycleResponse> result = service.getLifecycle(orderNo);

        assertThat(result).containsExactly(operationEvent, gatewayEvent);  // id 升序：100 < 200
    }

    @Test
    @DisplayName("getLifecycle：两源均无记录时返回空列表")
    void given_noEvents_when_getLifecycle_then_returnsEmptyList() {
        String orderNo = "PAY20260811003";

        when(paymentLogRepository.findByPaymentOrderNoOrderByCreatedAtDesc(orderNo))
            .thenReturn(List.of());
        when(operationLogRepository.findByTargetNoOrderByCreatedAtDesc(orderNo))
            .thenReturn(List.of());

        List<OrderLifecycleResponse> result = service.getLifecycle(orderNo);

        assertThat(result).isEmpty();
    }

    // ---- 构造器：仅满足 Mapper 调用所需，id/createdAt 由持久化注入、本测试不依赖 ----

    private static PaymentLog gatewayLog(String orderNo, String logType) {
        return new PaymentLog(
            orderNo, null, logType, "ICBC", "qrcode/consumption",
            "https://gw/qrcode", "{}", "{}",
            200, "0", "成功", 300L, Boolean.TRUE, null
        );
    }

    private static OperationLog operationLog(String orderNo, OperationType operation) {
        return new OperationLog(
            OperationLogTargetType.PAYMENT, orderNo, operation,
            123L, "张三", "admin-bff", "SUCCESS", null
        );
    }

    private static OrderLifecycleResponse event(long id, String source, LocalDateTime createdAt, String action) {
        return new OrderLifecycleResponse(
            id, source, createdAt, action, action,
            "SUCCESS", "performer", "performer-system", "detail"
        );
    }
}
```

> 说明：`PaymentLog` 构造里 `bankCode="ICBC"` 仅为构造一个**合法实例**喂给被 mock 的 Mapper（Mapper 不会真正读它）；DTO 与排序逻辑全程**银行无关**（CONTEXT.md 不变式 5）。生产代码与响应字段无任何 ICBC 硬编码。

- [ ] **Step 2: 运行测试确认失败（DTO / Mapper / AppService 尚不存在）**

Run: `mvn test -Dtest=OrderLifecycleAppServiceTest -Dsurefire.failIfNoSpecifiedTests=false`
Expected: **编译失败**——`OrderLifecycleResponse` / `OrderLifecycleMapper` / `OrderLifecycleAppService` 不存在。

- [ ] **Step 3: 创建统一事件 DTO `OrderLifecycleResponse`**

创建 `src/main/java/com/aieducenter/payment/application/dto/response/OrderLifecycleResponse.java`：

```java
package com.aieducenter.payment.application.dto.response;

import java.time.LocalDateTime;

/**
 * 订单生命周期统一事件（读模型）。
 *
 * <p>把 {@code PaymentLog}（网关交互，来源 GATEWAY）与 {@code OperationLog}（行为者操作，来源 OPERATION）
 * 按 {@code createdAt} 合并后的统一事件序列项。供客诉排查与运营查看「这单经历了什么」
 * （CONTEXT.md · 订单生命周期；ADR-0002 · 不合表、合视图）。只读、不写库。</p>
 *
 * <p>字段为跨来源的语义抽象：来源标签 + 动作 + 结果 + 执行方 + 补充说明，两类来源各自填值。
 * 银行无关（CONTEXT.md 不变式 5）——网关事件只透出 bankCode/bankInterface 通用列。</p>
 *
 * @param id              源记录主键（PaymentLog.id / OperationLog.id；同时间排序的稳定键）
 * @param source          来源标签：{@code "GATEWAY"}（网关交互）/ {@code "OPERATION"}（行为者操作）
 * @param createdAt       事件时间（合并后的主排序键）
 * @param action          动作稳定 token：GATEWAY→{@code logType}（PAYMENT_REQUEST…）；OPERATION→{@code operation} 枚举名（AUDIT_APPROVE…）
 * @param actionName      动作显示名：GATEWAY→{@code logType} 原值；OPERATION→{@code operation.getName()}（审核通过…）
 * @param outcome         结果 token（SUCCESS/FAILED）：GATEWAY 由 success 派生；OPERATION 取 result
 * @param performer       执行方：GATEWAY→{@code bankInterface}；OPERATION→{@code operatorName}
 * @param performerSystem 执行方系统：GATEWAY→{@code bankCode}；OPERATION→{@code operatorSystem}
 * @param detail          补充说明：GATEWAY→success 时 returnMsg / 失败时 errorMessage；OPERATION→remark
 */
public record OrderLifecycleResponse(
    Long id,
    String source,
    LocalDateTime createdAt,
    String action,
    String actionName,
    String outcome,
    String performer,
    String performerSystem,
    String detail
) {
}
```

- [ ] **Step 4: 创建手写 Mapper `OrderLifecycleMapper`**

创建 `src/main/java/com/aieducenter/payment/application/mapper/OrderLifecycleMapper.java`：

```java
package com.aieducenter.payment.application.mapper;

import com.aieducenter.payment.application.dto.response.OrderLifecycleResponse;
import com.aieducenter.payment.domain.aggregate.OperationLog;
import com.aieducenter.payment.domain.aggregate.PaymentLog;
import org.springframework.stereotype.Component;

/**
 * 订单生命周期事件映射器（手写，非 MapStruct）。
 *
 * <p>生命周期是<b>两源 → 一目标</b>的合并读模型，不适用 {@code DomainMapper<单源, 单目标>} 的 MapStruct
 * {@code convert} 模式。故以普通 {@code @Component} 类提供两个来源各自的映射法，由
 * {@link com.aieducenter.payment.application.OrderLifecycleAppService} 注入并按来源分别调用。</p>
 *
 * <p>银行无关（CONTEXT.md 不变式 5）：网关事件只读 PaymentLog 的通用列（bankCode/bankInterface/success/returnMsg/errorMessage），
 * 无 ICBC 硬编码。</p>
 */
@Component
public class OrderLifecycleMapper {

    /** 网关交互日志（PaymentLog）→ 统一事件，来源 GATEWAY。 */
    public OrderLifecycleResponse fromPaymentLog(PaymentLog log) {
        boolean success = Boolean.TRUE.equals(log.getSuccess());
        return new OrderLifecycleResponse(
            log.getId(),
            "GATEWAY",
            log.getCreatedAt(),
            log.getLogType(),
            log.getLogType(),
            success ? "SUCCESS" : "FAILED",
            log.getBankInterface(),
            log.getBankCode(),
            success ? log.getReturnMsg() : log.getErrorMessage()
        );
    }

    /** 行为者操作日志（OperationLog）→ 统一事件，来源 OPERATION。 */
    public OrderLifecycleResponse fromOperationLog(OperationLog log) {
        return new OrderLifecycleResponse(
            log.getId(),
            "OPERATION",
            log.getCreatedAt(),
            log.getOperation().name(),
            log.getOperation().getName(),
            log.getResult(),
            log.getOperatorName(),
            log.getOperatorSystem(),
            log.getRemark()
        );
    }
}
```

- [ ] **Step 5: 实现 `OrderLifecycleAppService`**

创建 `src/main/java/com/aieducenter/payment/application/OrderLifecycleAppService.java`：

```java
package com.aieducenter.payment.application;

import com.aieducenter.payment.application.dto.response.OrderLifecycleResponse;
import com.aieducenter.payment.application.mapper.OrderLifecycleMapper;
import com.aieducenter.payment.domain.aggregate.OperationLog;
import com.aieducenter.payment.domain.aggregate.PaymentLog;
import com.aieducenter.payment.domain.repository.OperationLogRepository;
import com.aieducenter.payment.domain.repository.PaymentLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 订单生命周期读模型应用服务（读侧）。
 *
 * <p>按 {@code orderNo}（支付订单号）合并该订单的 PaymentLog（既有按单查询）与 OperationLog（T1 的按 targetNo 查询），
 * 各自映射为统一事件后按 {@code createdAt} 升序、{@code id} 升序稳定排序返回——「这单从创建到终态经历了什么」
 * （CONTEXT.md · 订单生命周期；ADR-0002 · 不合表、合视图）。</p>
 *
 * <p><b>只读</b>：{@code @Transactional(readOnly = true)}，不落库、不改订单状态（ADR-0001）。银行无关（CONTEXT.md 不变式 5）。</p>
 */
@Service
@RequiredArgsConstructor
public class OrderLifecycleAppService {

    private final PaymentLogRepository paymentLogRepository;
    private final OperationLogRepository operationLogRepository;
    private final OrderLifecycleMapper orderLifecycleMapper;

    /**
     * 查询某支付订单的完整生命周期事件序列。
     *
     * @param orderNo 支付订单号
     * @return 按 createdAt 升序合并的事件序列（每条标注来源 GATEWAY/OPERATION）；无记录时返回空列表
     */
    @Transactional(readOnly = true)
    public List<OrderLifecycleResponse> getLifecycle(String orderNo) {
        // 仓储方法按 createdAt DESC 返回（列表场景约定）；此处合并后显式重排为升序（时间线正序）。
        List<PaymentLog> paymentLogs = paymentLogRepository.findByPaymentOrderNoOrderByCreatedAtDesc(orderNo);
        List<OperationLog> operationLogs = operationLogRepository.findByTargetNoOrderByCreatedAtDesc(orderNo);

        List<OrderLifecycleResponse> events = new ArrayList<>(paymentLogs.size() + operationLogs.size());
        for (PaymentLog paymentLog : paymentLogs) {
            events.add(orderLifecycleMapper.fromPaymentLog(paymentLog));
        }
        for (OperationLog operationLog : operationLogs) {
            events.add(orderLifecycleMapper.fromOperationLog(operationLog));
        }

        events.sort(
            Comparator.comparing(OrderLifecycleResponse::createdAt)
                .thenComparing(OrderLifecycleResponse::id)
        );
        return events;
    }
}
```

- [ ] **Step 6: 运行测试确认通过**

Run: `mvn test -Dtest=OrderLifecycleAppServiceTest -Dsurefire.failIfNoSpecifiedTests=false`
Expected: **3 个测试全部 PASS**（合并升序、同时间 id 稳定、空列表）。

- [ ] **Step 7: Commit**

```bash
git add src/main/java/com/aieducenter/payment/application/dto/response/OrderLifecycleResponse.java \
        src/main/java/com/aieducenter/payment/application/mapper/OrderLifecycleMapper.java \
        src/main/java/com/aieducenter/payment/application/OrderLifecycleAppService.java \
        src/test/java/com/aieducenter/payment/application/OrderLifecycleAppServiceTest.java
git commit -m "feat(payment): OrderLifecycleAppService（订单生命周期读模型：两源合并升序）+ DTO + Mapper + 缝测试"
```

---

### Task 2: 端点 — `GET /api/v1/orders/{orderNo}/lifecycle`

**Files:**
- Create: `src/main/java/com/aieducenter/payment/endpoints/api/v1/OrderLifecycleApiV1Controller.java`

**Interfaces:**
- Consumes: Task 1 的 `OrderLifecycleAppService.getLifecycle(String) → List<OrderLifecycleResponse>`。
- Produces: `GET /api/v1/orders/{orderNo}/lifecycle`（`@RequireSignature`，返回 `ApiResponse<List<OrderLifecycleResponse>>`）。

- [ ] **Step 1: 实现 Controller（薄委托，无独立控制器测试缝）**

创建 `src/main/java/com/aieducenter/payment/endpoints/api/v1/OrderLifecycleApiV1Controller.java`：

```java
package com.aieducenter.payment.endpoints.api.v1;

import com.aieducenter.payment.application.OrderLifecycleAppService;
import com.aieducenter.payment.application.dto.response.OrderLifecycleResponse;
import com.cartisan.openapi.annotation.RequireSignature;
import com.cartisan.web.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Validated
@Tag(name = "Order Lifecycle API v1", description = "订单生命周期接口 v1")
public class OrderLifecycleApiV1Controller {

    private final OrderLifecycleAppService orderLifecycleAppService;

    @GetMapping("/{orderNo}/lifecycle")
    @RequireSignature
    @Operation(summary = "查询订单生命周期事件序列（PaymentLog + OperationLog 按时间合并）")
    public ApiResponse<List<OrderLifecycleResponse>> getLifecycle(
            @PathVariable String orderNo
    ) {
        return ApiResponse.ok(orderLifecycleAppService.getLifecycle(orderNo));
    }
}
```

- [ ] **Step 2: 编译确认**

Run: `mvn compile`
Expected: BUILD SUCCESS。

- [ ] **Step 3: Commit**

```bash
git add src/main/java/com/aieducenter/payment/endpoints/api/v1/OrderLifecycleApiV1Controller.java
git commit -m "feat(payment): GET /api/v1/orders/{orderNo}/lifecycle 端点（@RequireSignature 两源合并事件序列）"
```

---

### Task 3: 验证 — 全量测试 + 架构守护

- [ ] **Step 1: 全量单元测试**

Run: `mvn test`
Expected: BUILD SUCCESS，全部测试通过（含新增 `OrderLifecycleAppServiceTest` 与既有套件；Flyway V1…V10 正常执行）。

- [ ] **Step 2: 架构守护测试**

Run: `mvn test -Dtest=ArchitectureTest -Dsurefire.failIfNoSpecifiedTests=false`
Expected: PASS（新代码遵循分层规则：应用层依赖领域层、Controller 仅依赖 AppService + DTO、构造注入、无字段注入；Mapper 在 `application/mapper` 包内）。

> 若失败：检查 `OrderLifecycleMapper` 是否误放 `domain/` 包（应在 `application/mapper/`），或 Controller 是否直接引用了聚合（应仅引用 AppService + DTO）。

---

### Task 4: 变异测试（pitest）— 注册 OrderLifecycleAppService

**Files:**
- Modify: `pom.xml`（pitest `<targetClasses>` 段）

- [ ] **Step 1: 追加 pitest targetClasses**

在 `pom.xml` 的 pitest `<targetClasses>` 段末尾（`PaymentLogQueryAppService` 之后）追加一行，并在上方注释补 issue #14 说明：

```xml
                    <targetClasses>
                        <param>com.aieducenter.payment.domain.aggregate.OperationLog</param>
                        <param>com.aieducenter.payment.application.OperationLogAppService</param>
                        <param>com.aieducenter.payment.application.PaymentOrderQueryAppService</param>
                        <param>com.aieducenter.payment.domain.aggregate.RefundOrder</param>
                        <param>com.aieducenter.payment.application.RefundOrderQueryAppService</param>
                        <param>com.aieducenter.payment.application.PaymentLogQueryAppService</param>
                        <param>com.aieducenter.payment.application.OrderLifecycleAppService</param>
                    </targetClasses>
```

并在 `<targetClasses>` 上方注释块末尾追加一行（紧跟 `issue #13 起追加 PaymentLogQueryAppService…` 之后）：

```
                        issue #14 起追加 OrderLifecycleAppService（订单生命周期读模型：两源合并排序应用服务缝）。
```

> Mapper（`OrderLifecycleMapper`，手写映射层）与 Controller（薄委托，行为由 AppService 缝 + SignatureVerificationIntegrationTest 覆盖）不进变异评分，与既有 MapStruct Mapper / Controller 同等对待。

- [ ] **Step 2: 运行变异测试**

Run: `mvn org.pitest:pitest-maven:mutationCoverage`
Expected: BUILD SUCCESS，整体变异分数 ≥ 70%（pom `<mutationThreshold>70</mutationThreshold>`）；`OrderLifecycleAppService` 的合并/排序变异被 Task 1 三个测试杀死（移除排序、降序、移除 thenComparing、丢弃某一来源列表、交换 Mapper 方法等）。

> 若 `OrderLifecycleAppService` 变异分偏低：检查 Task 1 测试是否覆盖「交错时间升序」「同时间 id 稳定」「两源非空且都出现」三类场景——这三个测试已覆盖；不要放宽到全 `payment.*`。

- [ ] **Step 3: Commit**

```bash
git add pom.xml
git commit -m "chore(build): pitest 覆盖 OrderLifecycleAppService"
```

---

### Task 5: `/code-review` 走查 + 确认提交到 develop

- [ ] **Step 1: 执行 `/code-review`**

执行 `/code-review`，按反馈修订（重点：只读契约、银行无关、排序稳定性、DTO 字段语义、Mapper 手写而非 MapStruct 的理由是否充分注释）。

- [ ] **Step 2: 确认提交状态**

Run: `git log --oneline -6`
Expected: 看到本 issue 的 4 个提交（plan + AppService + 端点 + pitest）已落在当前 `develop` 分支；`git status` 干净。

---

## Self-Review（计划自检）

**1. Spec coverage（issue #14 acceptance criteria）：**
- ✅ 端点返回按时间排序的合并事件，每条标注来源（GATEWAY/OPERATION）→ Task 1（DTO `source` 字段）+ Task 2（端点）+ Task 1 测试断言 `source` 序列。
- ✅ 只读、不写库、不改订单状态 → Task 1（`@Transactional(readOnly = true)`，无 `save`/状态迁移，Global Constraints）。
- ✅ AppService Mockito 缝测试覆盖合并排序逻辑 → Task 1 Step 1（三测试：合并升序 / 同时间 id 稳定 / 空）。
- ✅ pitest 过 → Task 4（注册 `OrderLifecycleAppService`，变异分 ≥ 70%）。
- ✅ 合并 PaymentLog（既有按单查询）+ OperationLog（T1 按 targetNo 查询）→ Task 1 Step 5（两个既有仓储方法）。
- ✅ 依赖 #9 已满足（OperationLog 聚合/仓储/枚举/迁移均已在库，本计划验证）。

**2. Placeholder scan：** 无 TBD / "类似 Task N" / "add error handling" 等；每步含完整代码或精确命令。Task 4 Step 1 的注释文本为字面值（非占位）。

**3. Type consistency：**
- `OrderLifecycleResponse` 9 字段在 Task 1 Step 1（测试构造 `event(...)`）、Step 3（DTO 定义）、Step 4（Mapper 两法 return）三处顺序与类型一致：`(Long id, String source, LocalDateTime createdAt, String action, String actionName, String outcome, String performer, String performerSystem, String detail)`。
- `OrderLifecycleMapper.fromPaymentLog(PaymentLog) → OrderLifecycleResponse` / `fromOperationLog(OperationLog) → OrderLifecycleResponse` 在 Step 1（mock）、Step 4（定义）、Step 5（注入调用）一致。
- `OrderLifecycleAppService.getLifecycle(String) → List<OrderLifecycleResponse>` 在 Step 1（驱动）、Step 5（定义）、Task 2（Controller 调用）一致。
- 既有签名复用核对：`PaymentLogRepository.findByPaymentOrderNoOrderByCreatedAtDesc(String)`、`OperationLogRepository.findByTargetNoOrderByCreatedAtDesc(String)`（与 #9 落地代码一致）；`PaymentLog` 14 参构造、`OperationLog` 8 参构造（与聚合定义一致）；`OperationType.name()`/`getName()`、`OperationLogTargetType.PAYMENT`（与枚举一致）。
