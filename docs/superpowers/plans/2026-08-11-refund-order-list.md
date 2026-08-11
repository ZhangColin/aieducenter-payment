# 退款订单列表（多条件分页，含 auditType）实现计划

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** 新增退款订单多条件分页查询能力：`RefundOrderQuery`（退款订单号 / 支付订单号 / 业务订单号 / 业务系统 / 多状态 / 审核类型 / 审核者 / 退款金额区间 / 创建时间区间）+ `RefundOrderQueryAppService.list(query, Pageable) → PageResponse` + 端点 `GET /api/v1/refunds`。详情沿用既有 `GET /api/v1/refunds/{no}`（`RefundAppService.queryRefund`），本票不动。

**Architecture:** DDD 六边形。**严格复用 T2（支付订单列表）已确立的本仓列表查询范式**——query record + `@Condition` + `ConditionSpecifications.fromAnnotation(query)` + `BaseRepository.findAll(Specification, Pageable)` + `PageResponse` + 独立查询应用服务 + `@RequireSignature` GET 端点。新增**独立的查询应用服务** `RefundOrderQueryAppService`（spec「按职责拆分应用服务」——读列表与既有写侧 `RefundAppService` 分离，避免带入网关/notifier/事务模板等写依赖）。**唯一非平凡改动**：退款侧此前没有 `RefundOrderMapper`（转换逻辑是 `RefundAppService` 的私有 `toResponse`），本票把它提取为 `RefundOrderMapper.convert` 静态方法（全仓退款转换唯一真源），并新增 `convertList` default 方法委托之——保证列表行与 `queryRefund` 详情输出逐字段一致、杜绝双转换路径漂移（与 T2 `PaymentOrderMapper` 决策一致）。一条测试缝：AppService Mockito 缝（核心路径，变异友好）。不引入 `@DataJpaTest`（与现有代码库一致）。

**Tech Stack:** Java 21, Spring Boot, JPA/Hibernate, cartisan-data-jpa（`@Condition`/`ConditionType`/`ConditionSpecifications`/`BaseRepository`）, cartisan-openapi（`@RequireSignature`）, cartisan-web（`PageResponse`/`ApiResponse`）, MapStruct（`@Mapper(componentModel="spring")`），JUnit 5 + Mockito + AssertJ，Flyway（PostgreSQL），pitest。

## 关联文档

- 父 spec：Issue #8（实现规约）；本票：Issue #12（T4 · 退款订单列表）。
- 范式参照（直接沿用）：`docs/superpowers/plans/2026-08-11-payment-order-list.md`（T2，本仓列表查询范式）；`PaymentOrderQueryAppService` / `PaymentOrderQuery` / `PaymentOrderMapper` / `PaymentApiV1Controller`。
- 前置票：Issue #11（T3 · RefundOrder 引入 auditType）——本票筛选维度 `auditType` 即 T3 落地的字段。
- 术语：`CONTEXT.md`（不变式 5「对外接口与领域逻辑与具体银行解耦」；`auditType` 词条）。
- 编码规范：`docs/guide/限界上下文代码编写规范.md`；框架手册：`docs/guide/cartisan-boot-使用手册.md`（@Condition 11 种条件类型、Pageable）。

## Global Constraints

- **银行无关**：查询全程不出现 ICBC / 具体银行硬编码（CONTEXT.md 不变式 5）。退款无通道枚举筛选（退款不区分通道），无需引入银行维度。
- **不动既有写侧逻辑**：不修改 `RefundOrder` 聚合、既有枚举（`RefundStatus`/`AuditType`）、`RefundOrderResponse`、`GET /api/v1/refunds/{refundOrderNo}`、`RefundAppService` 的业务方法。唯一允许的写侧改动是**行为保持的重构**——把 `RefundAppService` 私有 `toResponse` 方法体搬到 `RefundOrderMapper.convert` 静态方法、调用点改为静态调用（无新增字段、无构造函数变化、输出逐字段不变）。
- **DTO 只用 Record**；响应只暴露 DTO，不暴露聚合；枚举以 Integer code + String name 暴露（沿用 `RefundOrderResponse` 的 `status/statusName`、`auditType/auditTypeName` 约定）。
- **构造函数注入**（`@RequiredArgsConstructor`），禁止字段注入。
- **导入顺序**：Java 标准库 → Jakarta/Spring → cartisan → 项目内部。
- **测试命名**：`given_X_when_Y_then_Z`，AssertJ 断言；Mockito 缝 mock 仓储与 Mapper，用 `any(Specification.class)` + `any(Pageable.class)`。
- **签名校验**：端点 `@RequireSignature`；GET 无 body → bodyDigest = SHA-256(空)，query 参与签名串（框架已支持，`SignatureVerificationIntegrationTest` 兜底）。
- **强制分页**：端点强制 `Pageable` 参数（`@PageableDefault(size = 20)`），无不分页列表方法。
- **变异测试**：`mvn org.pitest:pitest-maven:mutationCoverage` 须达 `mutationThreshold=70`。

## 关键设计决策（锁定）

| 决策点 | 取值 | 理由 |
|--------|------|------|
| 新建查询 AppService vs 扩 `RefundAppService` | **新建 `RefundOrderQueryAppService`** | 与 T2 一致（spec「按职责拆分应用服务」）；既有 `RefundAppService` 含写侧依赖（`PaymentGatewayPort`/`BusinessSystemNotifier`/`TransactionTemplate`），读列表不需要；保持既有 `queryRefund` 不动（本票不动）。读/写分离，Mockito 缝更干净（只 mock 仓储 + Mapper）。 |
| 新建 `RefundOrderMapper` + 重构 `toResponse` | **新建 `RefundOrderMapper`（static `convert` + default `convertList`）；`RefundAppService.toResponse` 私有方法删除，4 处调用点改为 `RefundOrderMapper.convert(order)` 静态调用** | T2 已确立「唯一转换真源」原则。当前 `RefundAppService.toResponse` 是退款转换的唯一真源（被 createRefund/auditRefund/queryRefund 共用）；若新查询服务另起转换会产生双路径漂移（T2 决策表已明确警告「不扩展 DomainMapper 以免引入双转换路径漂移」）。提取为 Mapper 后：列表 `convertList` 委托 static `convert`，列表行与详情逐字段一致；静态调用不新增 `RefundAppService` 字段 → 构造签名不变 → `RefundAppServiceTest` 零改动。 |
| `status(多)` 实现 | `@Condition(propName = "status", type = ConditionType.IN) List<RefundStatus> statuses` | 与 T2 完全一致；**`propName` 必须为 `status`**（字段名 `statuses` 与实体属性 `status` 不同）；框架 `IN` 走 `path.in(coll)`。 |
| `auditType` 筛选 | `@Condition(type = ConditionType.EQUAL) AuditType auditType` | `AuditType` 是 `BaseEnum`，框架 EQUAL 直接 `path.equals(value)`；`audit_type` 列可空（PENDING 态为 null），`fromAnnotation` 对 null 自动跳过——只对显式传入的 auditType 过滤。 |
| `auditorId` 筛选 | `@Condition(type = ConditionType.EQUAL) Long auditorId` | `auditor_id` 列可空，null 跳过；EQUAL 精确匹配审核者。 |
| `refundAmount` 区间 | `propName = "refundAmount"`，`refundAmountMin`(GREATER_EQUAL) / `refundAmountMax`(LESS_EQUAL) | 字段名 `refundAmountMin/Max` 与实体属性 `refundAmount` 不同，必须显式 `propName`（同 T2 `amount`）。 |
| 仓储多条件查询 | **复用 `BaseRepository.findAll(Specification, Pageable)`，不改 `RefundOrderRepository` 接口** | 与 T2/OperationLog 一致；无需派生方法或 `@Query`，缝最少。既有 `findByStatusIn` 不动（保留，不破坏调用方）。 |
| 新增索引 | V9 给 `audit_type` / `auditor_id` / `refund_amount` / `created_at` 建部分索引 | V1 已覆盖 `payment_order_no` / `business_order_no` / `business_system_name` / `status`（+ `refund_order_no` UNIQUE）；本票新增筛选维度 `audit_type`/`auditor_id`/`refund_amount` 需补索引；`created_at` 区间筛选高频但 V1 漏建（支付侧 V1 有 `idx_payment_orders_created_at`，退款侧 V1 无），一并补齐。acceptance criteria「为筛选用字段建索引」。 |
| 端点位置 | **在既有 `RefundApiV1Controller` 加 `@GetMapping list`** | `/api/v1/refunds` 已是控制器根路径；详情 `GET /{refundOrderNo}` 不动；list 与 detail 同控制器，Spring 路由无冲突（`GET /` vs `GET /{no}`）。 |
| pitest 范围 | `targetClasses` 增 `com.aieducenter.payment.application.RefundOrderQueryAppService` | 与 T2/OperationLog 一致；query record / DTO / 控制器 / 枚举 / Mapper 不在变异评分范围（pom 既有约定）。 |

## File Structure

```
src/main/resources/db/migration/
└── V9__add_refund_order_query_indexes.sql                   [新] 为 audit_type/auditor_id/refund_amount/created_at 建部分索引

src/main/java/com/aieducenter/payment/
├── application/
│   ├── dto/query/
│   │   └── RefundOrderQuery.java                            [新] @Condition 多条件 record
│   ├── mapper/
│   │   └── RefundOrderMapper.java                           [新] static convert（真源）+ default convertList
│   ├── RefundOrderQueryAppService.java                      [新] list(query, pageable) → PageResponse
│   └── RefundAppService.java                                [改] toResponse 私有方法删除，调用点改 RefundOrderMapper.convert（行为保持）
└── endpoints/api/v1/
    └── RefundApiV1Controller.java                           [改] +GET /api/v1/refunds（list，薄委托）

src/test/java/com/aieducenter/payment/
└── application/
    └── RefundOrderQueryAppServiceTest.java                  [新] Mockito 缝（list 参数传递 + 响应映射）

pom.xml                                                      [改] pitest targetClasses +RefundOrderQueryAppService
```

---

### Task 1: Flyway 迁移——为退款筛选用字段补索引

**Files:**
- Create: `src/main/resources/db/migration/V9__add_refund_order_query_indexes.sql`

**Interfaces:**
- Produces: 在 `pay_refund_orders` 上为 `audit_type` / `auditor_id` / `refund_amount` / `created_at` 四个筛选用列建部分索引（`WHERE deleted = FALSE`），与 V1/V7/V8 索引风格一致。

- [ ] **Step 1: 写迁移脚本**

创建 `src/main/resources/db/migration/V9__add_refund_order_query_indexes.sql`：

```sql
-- ========================================================================
-- Payment Context: 退款订单列表查询筛选用字段索引
-- 为后台多条件分页查询 GET /api/v1/refunds 的高频筛选维度补索引（acceptance criteria）。
-- 已有索引（V1）：payment_order_no / business_order_no / business_system_name / status（+ refund_order_no UNIQUE）。
-- 本迁移补齐：audit_type（T3 新增审核类型筛选）/ auditor_id（审核者筛选）/ refund_amount（金额区间）/ created_at（创建时间区间，V1 漏建）。
-- 银行无关：退款无通道筛选，无 ICBC 硬编码。
-- ========================================================================

CREATE INDEX idx_refund_orders_audit_type ON pay_refund_orders(audit_type) WHERE deleted = FALSE;
CREATE INDEX idx_refund_orders_auditor_id ON pay_refund_orders(auditor_id) WHERE deleted = FALSE;
CREATE INDEX idx_refund_orders_refund_amount ON pay_refund_orders(refund_amount) WHERE deleted = FALSE;
CREATE INDEX idx_refund_orders_created_at ON pay_refund_orders(created_at) WHERE deleted = FALSE;
```

- [ ] **Step 2: 验证构建不受影响**

Run: `mvn compile -q`
Expected: BUILD SUCCESS（迁移脚本由 Flyway 在 `mvn test` 启动时执行；此处先确保不破坏构建）。

- [ ] **Step 3: Commit**

```bash
git add src/main/resources/db/migration/V9__add_refund_order_query_indexes.sql
git commit -m "feat(payment): V9 迁移——退款订单列表筛选用字段索引（audit_type/auditor_id/refund_amount/created_at）"
```

---

### Task 2: `RefundOrderQueryAppService` + `RefundOrderQuery` + `RefundOrderMapper` + `RefundAppService` 重构（TDD）

**Files:**
- Create: `src/main/java/com/aieducenter/payment/application/dto/query/RefundOrderQuery.java`
- Create: `src/main/java/com/aieducenter/payment/application/mapper/RefundOrderMapper.java`
- Create: `src/main/java/com/aieducenter/payment/application/RefundOrderQueryAppService.java`
- Modify: `src/main/java/com/aieducenter/payment/application/RefundAppService.java`
- Test: `src/test/java/com/aieducenter/payment/application/RefundOrderQueryAppServiceTest.java`

**Interfaces:**
- Consumes: `RefundOrderRepository`（既有，`extends BaseRepository` → 已有 `findAll(Specification, Pageable)`）；`RefundOrder`（既有，含 `auditType`/`auditorId`/`refundAmount`/`status` 等 getter）；`RefundOrderResponse`（既有 record）。
- Produces:
  - `RefundOrderQuery`（record，11 个筛选字段，带 `@Condition`）
  - `RefundOrderMapper.convert(RefundOrder) → RefundOrderResponse`（static，全仓退款转换唯一真源）
  - `RefundOrderMapper.convertList(List<RefundOrder>) → List<RefundOrderResponse>`（default 方法，委托 static convert）
  - `RefundOrderQueryAppService.list(RefundOrderQuery query, Pageable pageable) → PageResponse<RefundOrderResponse>`
  - 供 Task 3 控制器调用 `list`。

- [ ] **Step 1: 写查询条件 record（测试编译依赖的类型先行）**

创建 `src/main/java/com/aieducenter/payment/application/dto/query/RefundOrderQuery.java`：

```java
package com.aieducenter.payment.application.dto.query;

import com.aieducenter.payment.domain.enums.AuditType;
import com.aieducenter.payment.domain.enums.RefundStatus;
import com.cartisan.data.jpa.specification.Condition;
import com.cartisan.data.jpa.specification.ConditionType;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 退款订单多条件查询条件。
 *
 * <p>字段配合 {@link Condition} 注解，由 {@code ConditionSpecifications.fromAnnotation(query)}
 * 生成 JPA Specification。null 与空字符串自动跳过；集合仅在非 null 时参与 IN。
 * 沿用 T2（支付订单列表）确立的本仓列表查询筛选约定。</p>
 *
 * <p>筛选维度：退款订单号 / 支付订单号 / 业务订单号 / 业务系统 / 多状态 /
 * 审核类型（auditType，T3 落地）/ 审核者 / 退款金额区间（refundAmountMin..refundAmountMax）/
 * 创建时间区间。</p>
 */
public record RefundOrderQuery(
    @Condition(type = ConditionType.EQUAL) String refundOrderNo,
    @Condition(type = ConditionType.EQUAL) String paymentOrderNo,
    @Condition(type = ConditionType.EQUAL) String businessOrderNo,
    @Condition(type = ConditionType.EQUAL) String businessSystemName,
    @Condition(propName = "status", type = ConditionType.IN) List<RefundStatus> statuses,
    @Condition(type = ConditionType.EQUAL) AuditType auditType,
    @Condition(type = ConditionType.EQUAL) Long auditorId,
    @Condition(propName = "refundAmount", type = ConditionType.GREATER_EQUAL) Long refundAmountMin,
    @Condition(propName = "refundAmount", type = ConditionType.LESS_EQUAL) Long refundAmountMax,
    @Condition(propName = "createdAt", type = ConditionType.GREATER_EQUAL) LocalDateTime createdAtFrom,
    @Condition(propName = "createdAt", type = ConditionType.LESS_EQUAL) LocalDateTime createdAtTo
) {
}
```

> **注**：`statuses` 字段名与实体属性 `status` 不同，必须显式 `propName = "status"`；区间字段同理用 `propName` 指向 `refundAmount`/`createdAt`。`auditType`/`auditorId` 字段名与实体属性同名，无需 `propName`。

- [ ] **Step 2: 写失败测试（Mockito 缝）**

创建 `src/test/java/com/aieducenter/payment/application/RefundOrderQueryAppServiceTest.java`：

```java
package com.aieducenter.payment.application;

import com.aieducenter.payment.application.dto.query.RefundOrderQuery;
import com.aieducenter.payment.application.dto.response.RefundOrderResponse;
import com.aieducenter.payment.application.mapper.RefundOrderMapper;
import com.aieducenter.payment.domain.aggregate.RefundOrder;
import com.aieducenter.payment.domain.enums.AuditType;
import com.aieducenter.payment.domain.enums.RefundStatus;
import com.aieducenter.payment.domain.repository.RefundOrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
 * RefundOrderQueryAppService 测试（AppService Mockito 缝）。
 *
 * <p>沿用 PaymentOrderQueryAppServiceTest 模式：mock 仓储与 Mapper，构造被测服务直接驱动。
 * 覆盖：查询参数正确传递（Specification + Pageable）与 PageResponse 响应映射（变异友好）。</p>
 *
 * <p>说明：仓储 findAll 被 mock，Specification 不真正执行（不引入 @DataJpaTest，
 * 与现有代码库一致）；查询正确性靠 @Condition 注解的编译期保证与显式可读性。</p>
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("退款订单查询应用服务测试")
class RefundOrderQueryAppServiceTest {

    @Mock private RefundOrderRepository refundOrderRepository;
    @Mock private RefundOrderMapper refundOrderMapper;

    private RefundOrderQueryAppService service;

    @BeforeEach
    void setUp() {
        service = new RefundOrderQueryAppService(refundOrderRepository, refundOrderMapper);
    }

    @Test
    @DisplayName("list：多条件查询分页（含 auditType/auditorId），返回 PageResponse 且仅暴露 DTO，page 为 1-based")
    void given_queryAndPageable_when_list_then_returnsPageResponseOfDtos() {
        RefundOrderQuery query = new RefundOrderQuery(
            "REF20260811", "PAY20260811", "BIZ001", "course-system",
            List.of(RefundStatus.APPROVED, RefundStatus.REFUNDING),
            AuditType.MANUAL, 123L,
            100L, 10000L,
            LocalDateTime.of(2026, 8, 1, 0, 0), LocalDateTime.of(2026, 8, 11, 0, 0)
        );
        Pageable pageable = PageRequest.of(0, 20);

        RefundOrder order = new RefundOrder(
            "BIZ001", "PAY20260811", "course-system", "课程购买",
            10000L, 10000L, "用户申请退款", null, null
        );
        Page<RefundOrder> page = new PageImpl<>(List.of(order), pageable, 1);
        when(refundOrderRepository.findAll(any(Specification.class), any(Pageable.class)))
            .thenReturn(page);

        RefundOrderResponse responseDto = new RefundOrderResponse(
            1L, "BIZ001", "REF20260811", "PAY20260811", "course-system", "课程购买",
            RefundStatus.APPROVED.getCode(), RefundStatus.APPROVED.getName(),
            10000L, 10000L, "用户申请退款",
            "张三", true, "同意",
            AuditType.MANUAL.getCode(), AuditType.MANUAL.getName(),
            LocalDateTime.now(), LocalDateTime.now(), null, null,
            "BANK_REFUND_001", null
        );
        when(refundOrderMapper.convertList(List.of(order))).thenReturn(List.of(responseDto));

        var result = service.list(query, pageable);

        assertThat(result.items()).containsExactly(responseDto);
        assertThat(result.total()).isEqualTo(1L);
        assertThat(result.page()).isEqualTo(1);          // 0-based → 1-based（+1 变异点）
        assertThat(result.size()).isEqualTo(20);
        verify(refundOrderRepository).findAll(any(Specification.class), any(Pageable.class));
    }

    @Test
    @DisplayName("list：空查询条件也返回 PageResponse（Specification 不抛错）")
    void given_emptyQuery_when_list_then_returnsPageResponse() {
        RefundOrderQuery query = new RefundOrderQuery(
            null, null, null, null, null, null, null, null, null, null, null
        );
        Pageable pageable = PageRequest.of(2, 10);

        Page<RefundOrder> emptyPage = new PageImpl<>(List.of(), pageable, 0);
        when(refundOrderRepository.findAll(any(Specification.class), any(Pageable.class)))
            .thenReturn(emptyPage);
        when(refundOrderMapper.convertList(List.of())).thenReturn(List.of());

        var result = service.list(query, pageable);

        assertThat(result.items()).isEmpty();
        assertThat(result.total()).isZero();
        assertThat(result.page()).isEqualTo(3);          // PageRequest.of(2,10) → page 3
        assertThat(result.size()).isEqualTo(10);
    }
}
```

- [ ] **Step 3: 运行测试，确认失败（类不存在）**

Run: `mvn test -Dtest=RefundOrderQueryAppServiceTest -Dsurefire.failIfNoSpecifiedTests=false`
Expected: 编译失败——`RefundOrderQueryAppService` 与 `RefundOrderMapper.convertList` 不存在。

- [ ] **Step 4: 写 `RefundOrderMapper`（提取自 `RefundAppService.toResponse`，逐字段一致）**

创建 `src/main/java/com/aieducenter/payment/application/mapper/RefundOrderMapper.java`：

```java
package com.aieducenter.payment.application.mapper;

import com.aieducenter.payment.application.dto.response.RefundOrderResponse;
import com.aieducenter.payment.domain.aggregate.RefundOrder;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * 退款订单映射器
 *
 * <p>使用 MapStruct 进行领域对象与 DTO 之间的转换。static {@link #convert} 是全仓退款转换唯一真源
 * （被 {@code RefundAppService} 创建/审核/查询详情、{@code RefundOrderQueryAppService} 列表共用）；
 * {@link #convertList} 委托 static convert，保证列表行与详情 {@code queryRefund} 输出逐字段一致。</p>
 *
 * <p>注：{@code auditType} 列可空（PENDING 态尚未发生审核动作），{@link #convert} 对
 * {@code getAuditType() == null} 空安全处理（code/name 落 null），与详情输出一致。</p>
 */
@Mapper(componentModel = "spring")
public interface RefundOrderMapper {

    /**
     * 静态便捷方法（全仓退款转换唯一真源）。
     *
     * @param refundOrder 退款订单聚合根
     * @return 响应 DTO
     */
    static RefundOrderResponse convert(RefundOrder refundOrder) {
        return new RefundOrderResponse(
            refundOrder.getId(),
            refundOrder.getBusinessOrderNo(),
            refundOrder.getRefundOrderNo(),
            refundOrder.getPaymentOrderNo(),
            refundOrder.getBusinessSystemName(),
            refundOrder.getBusinessName(),
            refundOrder.getStatus().getCode(),
            refundOrder.getStatus().getName(),
            refundOrder.getRefundAmount(),
            refundOrder.getRefundableAmount(),
            refundOrder.getReason(),
            refundOrder.getAuditorName(),
            refundOrder.getAuditAgreed(),
            refundOrder.getAuditRemark(),
            refundOrder.getAuditType() != null ? refundOrder.getAuditType().getCode() : null,
            refundOrder.getAuditType() != null ? refundOrder.getAuditType().getName() : null,
            refundOrder.getCreatedAt(),
            refundOrder.getApprovedAt(),
            refundOrder.getRefundedAt(),
            refundOrder.getFailedAt(),
            refundOrder.getBankRefundNo(),
            refundOrder.getNotifyUrl()
        );
    }

    /**
     * 批量转换为响应 DTO（委托 static {@link #convert}，保证与详情输出一致）。
     *
     * @param refundOrders 退款订单聚合根列表
     * @return 响应 DTO 列表
     */
    default List<RefundOrderResponse> convertList(List<RefundOrder> refundOrders) {
        return refundOrders.stream().map(RefundOrderMapper::convert).toList();
    }
}
```

> **说明**：`convert` 方法体逐字段复制自 `RefundAppService.toResponse`（含 `auditType != null` 空安全），是行为保持的提取。不定义 MapStruct `toResponse` 抽象方法（退款转换走 static `convert`，避免 MapStruct 自动映射对可空 `auditType` 产生与手写不一致的 null 处理——同 T2 `PaymentOrderMapper` 既有的「static convert 为真源」决策）。

- [ ] **Step 5: 写 `RefundOrderQueryAppService`（最小实现使测试通过）**

创建 `src/main/java/com/aieducenter/payment/application/RefundOrderQueryAppService.java`：

```java
package com.aieducenter.payment.application;

import com.aieducenter.payment.application.dto.query.RefundOrderQuery;
import com.aieducenter.payment.application.dto.response.RefundOrderResponse;
import com.aieducenter.payment.application.mapper.RefundOrderMapper;
import com.aieducenter.payment.domain.aggregate.RefundOrder;
import com.aieducenter.payment.domain.repository.RefundOrderRepository;
import com.cartisan.data.jpa.specification.ConditionSpecifications;
import com.cartisan.web.response.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 退款订单查询应用服务（读侧）。
 *
 * <p>spec「按职责拆分应用服务」：与既有写侧 {@code RefundAppService}（创建 / 审核 / 查状态同步）分离，
 * 仅承担多条件分页读列表。详情沿用既有 {@code RefundAppService.queryRefund}。查询银行无关
 * （CONTEXT.md 不变式 5）。沿用 T2（支付订单列表）确立的本仓列表查询范式。</p>
 */
@Service
@RequiredArgsConstructor
public class RefundOrderQueryAppService {

    private final RefundOrderRepository refundOrderRepository;
    private final RefundOrderMapper refundOrderMapper;

    /**
     * 多条件分页查询退款订单。
     *
     * @param query    查询条件（null / 空字符串字段自动跳过）
     * @param pageable 分页参数
     * @return 分页响应（仅暴露 DTO）
     */
    @Transactional(readOnly = true)
    public PageResponse<RefundOrderResponse> list(RefundOrderQuery query, Pageable pageable) {
        Page<RefundOrder> page = refundOrderRepository.findAll(
            ConditionSpecifications.fromAnnotation(query), pageable
        );
        return new PageResponse<>(
            refundOrderMapper.convertList(page.getContent()),
            page.getTotalElements(),
            pageable.getPageNumber() + 1,
            pageable.getPageSize()
        );
    }
}
```

- [ ] **Step 6: 重构 `RefundAppService`——删除私有 `toResponse`，调用点改静态 `RefundOrderMapper.convert`**

修改 `src/main/java/com/aieducenter/payment/application/RefundAppService.java`：

(a) import 区加 `import com.aieducenter.payment.application.mapper.RefundOrderMapper;`

(b) 把 4 处 `toResponse(...)` 调用改为 `RefundOrderMapper.convert(...)`：
   - `createRefund` 末尾 `return toResponse(saved);` → `return RefundOrderMapper.convert(saved);`
   - `auditRefund` 末尾 `return toResponse(saved);` → `return RefundOrderMapper.convert(saved);`
   - `queryRefund` 末尾 `return toResponse(refundOrder);` → `return RefundOrderMapper.convert(refundOrder);`

   （`executeRefundAfterApproval` 等私有方法无 `toResponse` 调用，不动。）

(c) 删除类末尾的私有方法 `private RefundOrderResponse toResponse(RefundOrder order) { ... }`（整段，约 26 行）——其方法体已逐字段搬到 `RefundOrderMapper.convert`。

> **行为保持**：`RefundAppService` 不新增字段（静态调用）、构造签名不变 → `RefundAppServiceTest`（6 依赖构造、断言响应字段）零改动、仍绿。输出与重构前逐字段一致（含 `auditType` 空安全）。

- [ ] **Step 7: 运行新测试，确认通过**

Run: `mvn test -Dtest=RefundOrderQueryAppServiceTest -Dsurefire.failIfNoSpecifiedTests=false`
Expected: BUILD SUCCESS——2 个测试全部通过。

- [ ] **Step 8: 运行既有退款测试，确认重构无回归**

Run: `mvn test -Dtest=RefundAppServiceTest,RefundOrderTest -Dsurefire.failIfNoSpecifiedTests=false`
Expected: BUILD SUCCESS——重构行为保持，既有测试全绿。

- [ ] **Step 9: Commit**

```bash
git add src/main/java/com/aieducenter/payment/application/dto/query/RefundOrderQuery.java \
        src/main/java/com/aieducenter/payment/application/mapper/RefundOrderMapper.java \
        src/main/java/com/aieducenter/payment/application/RefundOrderQueryAppService.java \
        src/main/java/com/aieducenter/payment/application/RefundAppService.java \
        src/test/java/com/aieducenter/payment/application/RefundOrderQueryAppServiceTest.java
git commit -m "feat(payment): RefundOrderQueryAppService（list 多条件分页，含 auditType/auditorId）+ Query + Mapper"
```

---

### Task 3: 端点 `GET /api/v1/refunds`（薄委托）

**Files:**
- Modify: `src/main/java/com/aieducenter/payment/endpoints/api/v1/RefundApiV1Controller.java`

**Interfaces:**
- Consumes: Task 2 的 `RefundOrderQueryAppService.list(query, pageable)`。
- Produces: `GET /api/v1/refunds` → `ApiResponse<PageResponse<RefundOrderResponse>>`，`@RequireSignature`，与既有 `GET /api/v1/refunds/{refundOrderNo}` 路由无冲突。

- [ ] **Step 1: 在控制器加 list 端点（注入新 AppService + 加 GET 方法）**

修改 `src/main/java/com/aieducenter/payment/endpoints/api/v1/RefundApiV1Controller.java`：

1. import 区追加（按既有导入顺序）：

```java
import com.aieducenter.payment.application.RefundOrderQueryAppService;
import com.aieducenter.payment.application.dto.query.RefundOrderQuery;
import com.cartisan.web.response.PageResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
```

2. 在类内既有 `private final RefundAppService refundAppService;` 之后追加注入字段：

```java
    private final RefundOrderQueryAppService refundOrderQueryAppService;
```

3. 在 `getRefund` 方法之前追加 list 端点：

```java
    @GetMapping
    @RequireSignature
    @Operation(summary = "分页查询退款订单")
    public ApiResponse<PageResponse<RefundOrderResponse>> list(
            RefundOrderQuery query,
            @PageableDefault(size = 20) Pageable pageable
    ) {
        return ApiResponse.ok(refundOrderQueryAppService.list(query, pageable));
    }
```

> **薄委托**：行为由 AppService 缝覆盖；签名校验由 `@RequireSignature` 兜底（`SignatureVerificationIntegrationTest`）；不引入控制器测试缝（与现有代码库一致）。`@RequiredArgsConstructor` 会自动把新字段加入构造函数。list 与既有 `getRefund`（`GET /{refundOrderNo}`）路由无冲突（`GET /` vs `GET /{no}`）。

- [ ] **Step 2: 编译 + 架构守护测试**

Run: `mvn test -Dtest=ArchitectureTest -Dsurefire.failIfNoSpecifiedTests=false`
Expected: BUILD SUCCESS——分层规则通过（Controller 可用领域枚举 / Pageable，框架已许可）。

- [ ] **Step 3: Commit**

```bash
git add src/main/java/com/aieducenter/payment/endpoints/api/v1/RefundApiV1Controller.java
git commit -m "feat(payment): GET /api/v1/refunds 端点（@RequireSignature 多条件分页）"
```

---

### Task 4: pitest 覆盖 + 全量验证

**Files:**
- Modify: `pom.xml`（pitest `<targetClasses>`）

**Interfaces:**
- Consumes: Task 2 的 `RefundOrderQueryAppService`。
- Produces: pitest 变异评分覆盖新 AppService 且 ≥ 70%；全量测试绿。

- [ ] **Step 1: 把新 AppService 纳入 pitest targetClasses**

修改 `pom.xml` 的 `<targetClasses>` 段（约 236–241 行），追加一行 `<param>com.aieducenter.payment.application.RefundOrderQueryAppService</param>`，并在该段上方注释补一行说明（issue #12 追加）：

```xml
                        issue #10 起追加 PaymentOrderQueryAppService（同属可测的查询应用服务缝）。
                        issue #11 起追加 RefundOrder（auditType/autoAudit 状态迁移，领域缝变异）。
                        issue #12 起追加 RefundOrderQueryAppService（退款列表查询应用服务缝）。
                        控制器（薄委托，行为由 AppService 缝 + SignatureVerificationIntegrationTest 覆盖）、
                        枚举（纯值对象）、MapStruct 生成实现不在变异评分范围。
                    -->
                    <targetClasses>
                        <param>com.aieducenter.payment.domain.aggregate.OperationLog</param>
                        <param>com.aieducenter.payment.application.OperationLogAppService</param>
                        <param>com.aieducenter.payment.application.PaymentOrderQueryAppService</param>
                        <param>com.aieducenter.payment.domain.aggregate.RefundOrder</param>
                        <param>com.aieducenter.payment.application.RefundOrderQueryAppService</param>
                    </targetClasses>
```

- [ ] **Step 2: 跑变异测试**

Run: `mvn org.pitest:pitest-maven:mutationCoverage`
Expected: BUILD SUCCESS；`RefundOrderQueryAppService` 变异评分 ≥ 70%（`+1` / `getTotalElements` / `getPageSize` / `getContent` / `convertList` 变异点均被 Task 2 Step 2 的 2 个测试杀灭）。

- [ ] **Step 3: 跑全量测试**

Run: `mvn test`
Expected: BUILD SUCCESS——全部既有测试 + 新增 `RefundOrderQueryAppServiceTest` + `ArchitectureTest` + `SignatureVerificationIntegrationTest` 绿。

- [ ] **Step 4: （可选）打包验证**

Run: `mvn package -DskipTests`
Expected: BUILD SUCCESS。

- [ ] **Step 5: Commit**

```bash
git add pom.xml
git commit -m "chore(build): pitest 覆盖 RefundOrderQueryAppService"
```

- [ ] **Step 6: `/code-review` 走查**

执行 `/code-review`，按反馈修订后确认所有改动已提交到当前 `develop` 分支。

---

## Self-Review

**1. Spec / Issue #12 覆盖：**
- *RefundOrderQuery（全部字段）* → Task 2 Step 1，字段逐一对应 issue 列举的筛选维度（refundOrderNo / paymentOrderNo / businessOrderNo / businessSystemName / status(多) / auditType / auditorId / refundAmountMin..Max / createdAtFrom..To）。✓
- *RefundOrderRepository 多条件分页* → Task 2 Step 5，复用 `findAll(Specification, Pageable)`（与 T2/OperationLog 一致）。✓
- *应用服务 list(query, Pageable) → PageResponse* → Task 2 Step 5。✓
- *端点 GET /api/v1/refunds* → Task 3。✓
- *沿用 T2 列表范式* → 严格复用（query record + PageResponse + @Condition + 独立查询 AppService + Mapper 唯一真源 + @RequireSignature GET 端点）。✓
- *详情沿用既有 queryRefund，本票不动* → 不修改 `RefundAppService.queryRefund` 业务逻辑；仅做行为保持的 toResponse→Mapper.convert 提取（输出逐字段不变）。✓
- AC1「GET /api/v1/refunds 经签名、按含 auditType/auditorId 在内的条件过滤、分页返回」→ `@RequireSignature`（Task 3）+ `@Condition` 过滤含 auditType/auditorId（Task 2 Step 1）+ `PageResponse`（Task 2 Step 5）。✓
- AC2「查询银行无关；为筛选用字段建索引」→ 全程无 ICBC 硬编码（退款无通道筛选）；V9 索引（Task 1）覆盖 audit_type/auditor_id/refund_amount/created_at。✓
- AC3「AppService Mockito 缝测试覆盖，pitest 过」→ Task 2 Step 2 测试（含 auditType/auditorId 断言场景）+ Task 4 pitest。✓

**2. 占位符扫描：** 无 TBD / TODO / 「类似 Task N」/ 无代码步骤。所有代码块完整。✓

**3. 类型一致性：**
- `RefundOrderQuery` 字段名（`statuses` / `refundAmountMin` / `refundAmountMax` / `createdAtFrom` / `createdAtTo`）在测试构造、`propName`、控制器绑定中一致；`auditType`/`auditorId` 字段名与实体属性同名（无需 propName）。✓
- `RefundOrderQueryAppService.list(query, pageable)` 签名在测试（Task 2 Step 2）、实现（Step 5）、控制器（Task 3）一致。✓
- `RefundOrderMapper.convertList(List<RefundOrder>) → List<RefundOrderResponse>` 在 Mapper（Step 4）、AppService（Step 5）、测试 mock（Step 2）一致。✓
- 构造函数顺序 `(refundOrderRepository, refundOrderMapper)` 在 AppService 与测试 `setUp` 一致。✓
- `RefundOrderMapper.convert` 输出与重构前 `RefundAppService.toResponse` 逐字段一致（含 `auditType != null` 空安全）→ `RefundAppServiceTest` 既有断言零影响。✓

**4. 风险确认：**
- `statuses` 空 list：框架 `fromAnnotation` 只跳过 null 与空字符串，空 `Collection` 会进 `path.in(emptyColl)` → Hibernate 渲染为恒假（返回 0 行）。但 Spring MVC 对未出现的 `statuses` 参数绑定为 null（跳过 → 全状态）；仅当调用方显式传空集合才触发，属可接受的框架已知行为（与 T2/OperationLog 同构），非本票回归。已在决策表与 Global Constraints 注明。
- Mapper 重构回归：`RefundAppService.toResponse` → `RefundOrderMapper.convert` 是行为保持提取（方法体逐字段搬迁，无新增字段），`RefundAppServiceTest`（6 依赖构造、断言响应字段）不 mock Mapper、不断言转换路径，仅断言 DTO 字段值 → 重构后仍绿（Task 2 Step 8 显式验证）。
- 索引列可空：`audit_type`/`auditor_id` 可空（PENDING 态），部分索引 `WHERE deleted = FALSE` 仍包含 NULL 行（Postgres 部分索引仅过滤 deleted 维度），筛选时 NULL 行被 `path.equals` 自然排除，行为正确。
