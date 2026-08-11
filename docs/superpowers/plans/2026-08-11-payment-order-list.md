# 支付订单列表（多条件分页）实现计划

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** 新增支付订单多条件分页查询能力：`PaymentOrderQuery`（订单号 / 业务订单号 / 业务系统 / 多状态 / 支付方式 / 接入类型 / 通道 / 金额区间 / 创建时间区间 / 付款时间区间）+ `PaymentOrderQueryAppService.list(query, Pageable) → PageResponse` + 端点 `GET /api/v1/payments`。详情沿用既有 `GET /api/v1/payments/{no}`（`PaymentAppService.getPayment`），本票不动。

**Architecture:** DDD 六边形。**严格复用 OperationLog 已确立的本仓列表查询范式**（query record + `@Condition` + `ConditionSpecifications.fromAnnotation(query)` + `BaseRepository.findAll(Specification, Pageable)` + `PageResponse` + `@RequireSignature` GET 端点）。新增**独立的查询应用服务** `PaymentOrderQueryAppService`（spec「按职责拆分应用服务」——读列表与既有写侧 `PaymentAppService` 分离，避免带入网关/notifier 等写依赖）。列表行复用既有 `PaymentOrderResponse` 与 `PaymentOrderMapper.convert`（唯一转换真源）。一条测试缝：AppService Mockito 缝（核心路径，变异友好）。不引入 `@DataJpaTest`（已与用户确认，与现有代码库一致）。

**Tech Stack:** Java 21, Spring Boot, JPA/Hibernate, cartisan-data-jpa（`@Condition`/`ConditionType`/`ConditionSpecifications`/`BaseRepository`）, cartisan-openapi（`@RequireSignature`）, cartisan-web（`PageResponse`/`ApiResponse`）, JUnit 5 + Mockito + AssertJ, Flyway, pitest。

## 关联文档

- 父 spec：Issue #8（实现规约）；本票：Issue #10（T2 · 支付订单列表）。
- 范式参照：`OperationLogAppService` / `OperationLogQuery` / `OperationLogApiV1Controller`（最近实现，本仓列表查询范式）。
- 术语：`CONTEXT.md`（不变式 5「对外接口与领域逻辑与具体银行解耦」）。
- 编码规范：`docs/guide/限界上下文代码编写规范.md`；框架手册：`docs/guide/cartisan-boot-使用手册.md`（@Condition 11 种条件类型、枚举增强、Pageable）。

## Global Constraints

- **银行无关**：查询全程不出现 ICBC / 具体银行硬编码（CONTEXT.md 不变式 5）。`PaymentChannel` 是抽象通道枚举，`paymentChannel` 筛选是银行无关维度。
- **不动既有写侧**：不修改 `PaymentAppService`、`PaymentOrder` 聚合、既有枚举、`PaymentOrderResponse`、`PaymentOrderMapper.convert` 静态方法、`GET /api/v1/payments/{no}`。新增只做「加法」。
- **DTO 只用 Record**；响应只暴露 DTO，不暴露聚合。
- **构造函数注入**（`@RequiredArgsConstructor`），禁止字段注入。
- **导入顺序**：Java 标准库 → Jakarta/Spring → cartisan → 项目内部。
- **测试命名**：`given_X_when_Y_then_Z`，AssertJ 断言；Mockito 缝 mock 仓储与 Mapper，用 `any(Specification.class)` + `any/eq(Pageable.class)`。
- **签名校验**：端点 `@RequireSignature`；GET 无 body → bodyDigest = SHA-256(空)，query 参与签名串（框架已支持，`SignatureVerificationIntegrationTest` 兜底）。
- **强制分页**：端点强制 `Pageable` 参数（`@PageableDefault(size = 20)`），无不分页列表方法。
- **变异测试**：`mvn org.pitest:pitest-maven:mutationCoverage` 须达 `mutationThreshold=70`。

## 关键设计决策（锁定）

| 决策点 | 取值 | 理由 |
|--------|------|------|
| 新建查询 AppService vs 扩 `PaymentAppService` | **新建 `PaymentOrderQueryAppService`** | spec「按职责拆分应用服务，不按消费方」明示「支付订单查询 AppService」独立；既有 `PaymentAppService` 含写侧依赖（`PaymentGatewayPort`/`BusinessSystemNotifier`），读列表不需要；保持既有 `getPayment` 不动（本票不动）。读/写分离，Mockito 缝更干净（只 mock 仓储 + Mapper）。 |
| `status(多)` 实现 | `@Condition(propName = "status", type = ConditionType.IN) List<PaymentStatus> statuses` | 框架 `IN` 接受 `Collection`（`buildPredicate` 走 `path.in(coll)`）；**`propName` 必须为 `status`**（字段名 `statuses` 与实体属性 `status` 不同，不设 propName 会找不到路径）；多状态筛选是 spec 硬需求。 |
| Mapper 列表转换 | **给 `PaymentOrderMapper` 加 `default convertList(List)` 委托既有 static `convert`** | static `convert` 是唯一真源（被 `PaymentAppService`×5、`PaymentCallbackAppService`×1 使用）；既有 MapStruct `toResponse` 为**死代码**且 `paymentChannelName` 自动映射会落 null（与 static convert 的 `getName()`「工商银行」不一致）。委托 static convert 保证列表行与 `getPayment` 详情输出**逐字段一致**、零分歧、可被 Mockito mock。不扩展 `DomainMapper` 以免引入双转换路径漂移。 |
| 仓储多条件查询 | **复用 `BaseRepository.findAll(Specification, Pageable)`，不改 `PaymentOrderRepository` 接口** | 与 OperationLog 一致；`ConditionSpecifications.fromAnnotation(query)` 生成 Specification；无需派生方法或 `@Query`，缝最少。 |
| 新增索引 | V7 给 `paid_at` / `payment_channel` / `amount` 建部分索引 | V1 已覆盖 `business_order_no`/`business_system_name`/`status`/`created_at`；V5 已覆盖 `pay_mode`/`access_type`；`payment_order_no` 唯一索引。acceptance criteria「为筛选用字段建索引」→ 补齐剩余三个筛选用字段。 |
| 端点位置 | **在既有 `PaymentApiV1Controller` 加 `@GetMapping list`** | `/api/v1/payments` 已是控制器根路径；详情 `GET /{paymentOrderNo}` 不动；list 与 detail 同控制器，Spring 路由无冲突（`GET /` vs `GET /{no}`）。 |
| pitest 范围 | `targetClasses` 增 `com.aieducenter.payment.application.PaymentOrderQueryAppService` | 与 OperationLog 一致；query record / DTO / 控制器 / 枚举 / Mapper 不在变异评分范围（pom 既有约定）。 |

## File Structure

```
src/main/resources/db/migration/
└── V7__add_payment_order_query_indexes.sql              [新] 为 paid_at/payment_channel/amount 建部分索引

src/main/java/com/aieducenter/payment/
├── application/
│   ├── dto/query/
│   │   └── PaymentOrderQuery.java                       [新] @Condition 多条件 record
│   ├── mapper/
│   │   └── PaymentOrderMapper.java                      [改] +default convertList(List) 委托 static convert
│   └── PaymentOrderQueryAppService.java                 [新] list(query, pageable) → PageResponse
└── endpoints/api/v1/
    └── PaymentApiV1Controller.java                      [改] +GET /api/v1/payments（list，薄委托）

src/test/java/com/aieducenter/payment/
└── application/
    └── PaymentOrderQueryAppServiceTest.java             [新] Mockito 缝（list 参数传递 + 响应映射）

pom.xml                                                 [改] pitest targetClasses +PaymentOrderQueryAppService
```

---

### Task 1: Flyway 迁移——为支付订单筛选用字段补索引

**Files:**
- Create: `src/main/resources/db/migration/V7__add_payment_order_query_indexes.sql`

**Interfaces:**
- Produces: 在 `pay_payment_orders` 上为 `paid_at` / `payment_channel` / `amount` 三个新增筛选用列建部分索引（`WHERE deleted = FALSE`），与 V1/V5 索引风格一致。

- [ ] **Step 1: 写迁移脚本**

创建 `src/main/resources/db/migration/V7__add_payment_order_query_indexes.sql`：

```sql
-- ========================================================================
-- Payment Context: 支付订单列表查询筛选用字段索引
-- 为后台多条件分页查询 GET /api/v1/payments 的高频筛选维度补索引（acceptance criteria）。
-- 已有索引（V1）：business_order_no / business_system_name / status / created_at（+ payment_order_no UNIQUE）。
-- 已有索引（V5）：pay_mode / access_type。
-- 本迁移补齐：paid_at（付款时间区间）/ payment_channel（通道筛选）/ amount（金额区间）。
-- 银行无关：仅索引抽象通道列 payment_channel，无 ICBC 硬编码。
-- ========================================================================

CREATE INDEX idx_payment_orders_paid_at ON pay_payment_orders(paid_at) WHERE deleted = FALSE;
CREATE INDEX idx_payment_orders_payment_channel ON pay_payment_orders(payment_channel) WHERE deleted = FALSE;
CREATE INDEX idx_payment_orders_amount ON pay_payment_orders(amount) WHERE deleted = FALSE;
```

- [ ] **Step 2: 验证构建不受影响**

Run: `mvn compile -q`
Expected: BUILD SUCCESS（迁移脚本由 Flyway 在 `mvn test` 启动时执行；此处先确保不破坏构建）。

- [ ] **Step 3: Commit**

```bash
git add src/main/resources/db/migration/V7__add_payment_order_query_indexes.sql
git commit -m "feat(payment): V7 迁移——支付订单列表筛选用字段索引（paid_at/payment_channel/amount）

Co-Authored-By: Claude <noreply@anthropic.com>"
```

---

### Task 2: `PaymentOrderQueryAppService` + `PaymentOrderQuery` + Mapper `convertList`（TDD）

**Files:**
- Create: `src/main/java/com/aieducenter/payment/application/dto/query/PaymentOrderQuery.java`
- Modify: `src/main/java/com/aieducenter/payment/application/mapper/PaymentOrderMapper.java`
- Create: `src/main/java/com/aieducenter/payment/application/PaymentOrderQueryAppService.java`
- Test: `src/test/java/com/aieducenter/payment/application/PaymentOrderQueryAppServiceTest.java`

**Interfaces:**
- Consumes: `PaymentOrderRepository`（既有，`extends BaseRepository` → 已有 `findAll(Specification, Pageable)`）；`PaymentOrderMapper`（既有 static `convert`）。
- Produces:
  - `PaymentOrderQuery`（record，13 个筛选字段，带 `@Condition`）
  - `PaymentOrderQueryAppService.list(PaymentOrderQuery query, Pageable pageable) → PageResponse<PaymentOrderResponse>`
  - `PaymentOrderMapper.convertList(List<PaymentOrder>) → List<PaymentOrderResponse>`（default 方法）
  - 供 Task 3 控制器调用 `list`。

- [ ] **Step 1: 写查询条件 record（测试编译依赖的类型先行）**

创建 `src/main/java/com/aieducenter/payment/application/dto/query/PaymentOrderQuery.java`：

```java
package com.aieducenter.payment.application.dto.query;

import com.aieducenter.payment.domain.enums.AccessType;
import com.aieducenter.payment.domain.enums.PayMode;
import com.aieducenter.payment.domain.enums.PaymentChannel;
import com.aieducenter.payment.domain.enums.PaymentStatus;
import com.cartisan.data.jpa.specification.Condition;
import com.cartisan.data.jpa.specification.ConditionType;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 支付订单多条件查询条件。
 *
 * <p>字段配合 {@link Condition} 注解，由 {@code ConditionSpecifications.fromAnnotation(query)}
 * 生成 JPA Specification。null 与空字符串自动跳过；集合仅在非 null 时参与 IN。
 * 确立本仓列表查询筛选约定，后续列表票（退款 / 网关日志）沿用。</p>
 *
 * <p>筛选维度：订单号 / 业务订单号 / 业务系统 / 多状态 / 支付方式 / 接入类型 / 通道 /
 * 金额区间（amountMin..amountMax）/ 创建时间区间 / 付款时间区间。</p>
 */
public record PaymentOrderQuery(
    @Condition(type = ConditionType.EQUAL) String paymentOrderNo,
    @Condition(type = ConditionType.EQUAL) String businessOrderNo,
    @Condition(type = ConditionType.EQUAL) String businessSystemName,
    @Condition(propName = "status", type = ConditionType.IN) List<PaymentStatus> statuses,
    @Condition(type = ConditionType.EQUAL) PayMode payMode,
    @Condition(type = ConditionType.EQUAL) AccessType accessType,
    @Condition(type = ConditionType.EQUAL) PaymentChannel paymentChannel,
    @Condition(propName = "amount", type = ConditionType.GREATER_EQUAL) Long amountMin,
    @Condition(propName = "amount", type = ConditionType.LESS_EQUAL) Long amountMax,
    @Condition(propName = "createdAt", type = ConditionType.GREATER_EQUAL) LocalDateTime createdAtFrom,
    @Condition(propName = "createdAt", type = ConditionType.LESS_EQUAL) LocalDateTime createdAtTo,
    @Condition(propName = "paidAt", type = ConditionType.GREATER_EQUAL) LocalDateTime paidAtFrom,
    @Condition(propName = "paidAt", type = ConditionType.LESS_EQUAL) LocalDateTime paidAtTo
) {
}
```

> **注**：`statuses` 字段名与实体属性 `status` 不同，必须显式 `propName = "status"`；区间字段同理用 `propName` 指向 `amount`/`createdAt`/`paidAt`。

- [ ] **Step 2: 写失败测试（Mockito 缝）**

创建 `src/test/java/com/aieducenter/payment/application/PaymentOrderQueryAppServiceTest.java`：

```java
package com.aieducenter.payment.application;

import com.aieducenter.payment.application.dto.query.PaymentOrderQuery;
import com.aieducenter.payment.application.dto.response.PaymentOrderResponse;
import com.aieducenter.payment.application.mapper.PaymentOrderMapper;
import com.aieducenter.payment.domain.aggregate.PaymentOrder;
import com.aieducenter.payment.domain.enums.PaymentStatus;
import com.aieducenter.payment.domain.repository.PaymentOrderRepository;
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
 * PaymentOrderQueryAppService 测试（AppService Mockito 缝）。
 *
 * <p>沿用 OperationLogAppServiceTest 模式：mock 仓储与 Mapper，构造被测服务直接驱动。
 * 覆盖：查询参数正确传递（Specification + Pageable）与 PageResponse 响应映射（变异友好）。</p>
 *
 * <p>说明：仓储 findAll 被 mock，Specification 不真正执行（不引入 @DataJpaTest，
 * 与现有代码库一致）；查询正确性靠 @Condition 注解的编译期保证与显式可读性。</p>
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("支付订单查询应用服务测试")
class PaymentOrderQueryAppServiceTest {

    @Mock private PaymentOrderRepository paymentOrderRepository;
    @Mock private PaymentOrderMapper paymentOrderMapper;

    private PaymentOrderQueryAppService service;

    @BeforeEach
    void setUp() {
        service = new PaymentOrderQueryAppService(paymentOrderRepository, paymentOrderMapper);
    }

    @Test
    @DisplayName("list：多条件查询分页，返回 PageResponse 且仅暴露 DTO，page 为 1-based")
    void given_queryAndPageable_when_list_then_returnsPageResponseOfDtos() {
        PaymentOrderQuery query = new PaymentOrderQuery(
            "PAY20260811", "BIZ001", "course-system",
            List.of(PaymentStatus.PAID, PaymentStatus.PENDING),
            null, null, null,
            100L, 10000L,
            LocalDateTime.of(2026, 8, 1, 0, 0), LocalDateTime.of(2026, 8, 11, 0, 0),
            null, null
        );
        Pageable pageable = PageRequest.of(0, 20);

        PaymentOrder order = new PaymentOrder(
            "BIZ001", "course-system", "课程购买",
            10000L, "Python 课程", "描述",
            "https://biz.example.com/notify", "attach", 3600L
        );
        Page<PaymentOrder> page = new PageImpl<>(List.of(order), pageable, 1);
        when(paymentOrderRepository.findAll(any(Specification.class), any(Pageable.class)))
            .thenReturn(page);

        PaymentOrderResponse responseDto = new PaymentOrderResponse(
            1L, "BIZ001", "PAY20260811", "course-system", "课程购买",
            PaymentStatus.PAID.getCode(), PaymentStatus.PAID.getName(),
            10000L, "Python 课程", "描述", "工商银行",
            null, "127.0.0.1", LocalDateTime.now(), null, LocalDateTime.now(),
            "ICBC_ORDER_001", "THIRD_001"
        );
        when(paymentOrderMapper.convertList(List.of(order))).thenReturn(List.of(responseDto));

        var result = service.list(query, pageable);

        assertThat(result.items()).containsExactly(responseDto);
        assertThat(result.total()).isEqualTo(1L);
        assertThat(result.page()).isEqualTo(1);          // 0-based → 1-based（+1 变异点）
        assertThat(result.size()).isEqualTo(20);
        verify(paymentOrderRepository).findAll(any(Specification.class), any(Pageable.class));
    }

    @Test
    @DisplayName("list：空查询条件也返回 PageResponse（Specification 不抛错）")
    void given_emptyQuery_when_list_then_returnsPageResponse() {
        PaymentOrderQuery query = new PaymentOrderQuery(
            null, null, null, null, null, null, null,
            null, null, null, null, null, null
        );
        Pageable pageable = PageRequest.of(2, 10);

        Page<PaymentOrder> emptyPage = new PageImpl<>(List.of(), pageable, 0);
        when(paymentOrderRepository.findAll(any(Specification.class), any(Pageable.class)))
            .thenReturn(emptyPage);
        when(paymentOrderMapper.convertList(List.of())).thenReturn(List.of());

        var result = service.list(query, pageable);

        assertThat(result.items()).isEmpty();
        assertThat(result.total()).isZero();
        assertThat(result.page()).isEqualTo(3);          // PageRequest.of(2,10) → page 3
        assertThat(result.size()).isEqualTo(10);
    }
}
```

- [ ] **Step 3: 运行测试，确认失败（类不存在）**

Run: `mvn test -Dtest=PaymentOrderQueryAppServiceTest -q`
Expected: 编译失败——`PaymentOrderQueryAppService` 与 `PaymentOrderMapper.convertList` 不存在。

- [ ] **Step 4: 给 Mapper 加 `convertList` default 方法（委托既有 static `convert`）**

修改 `src/main/java/com/aieducenter/payment/application/mapper/PaymentOrderMapper.java`，在 `convert` 静态方法后追加 `convertList` default 方法，并在 import 区加 `java.util.List`：

```java
package com.aieducenter.payment.application.mapper;

import com.aieducenter.payment.application.dto.response.PaymentOrderResponse;
import com.aieducenter.payment.domain.aggregate.PaymentOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * 支付订单映射器
 *
 * <p>使用 MapStruct 进行领域对象与 DTO 之间的转换。static {@link #convert} 是全仓唯一转换真源
 * （被 PaymentAppService / PaymentCallbackAppService 使用）；{@link #convertList} 委托 static convert，
 * 保证列表行与详情 {@code getPayment} 输出逐字段一致。</p>
 */
@Mapper(componentModel = "spring")
public interface PaymentOrderMapper {

    /**
     * 转换为响应 DTO
     *
     * @param paymentOrder 支付订单聚合根
     * @return 响应 DTO
     */
    @Mapping(target = "status", source = "status.code")
    @Mapping(target = "statusName", source = "status.name")
    PaymentOrderResponse toResponse(PaymentOrder paymentOrder);

    /**
     * 静态便捷方法（全仓唯一转换真源）
     *
     * @param paymentOrder 支付订单聚合根
     * @return 响应 DTO
     */
    static PaymentOrderResponse convert(PaymentOrder paymentOrder) {
        return new PaymentOrderResponse(
            paymentOrder.getId(),
            paymentOrder.getBusinessOrderNo(),
            paymentOrder.getPaymentOrderNo(),
            paymentOrder.getBusinessSystemName(),
            paymentOrder.getBusinessName(),
            paymentOrder.getStatus().getCode(),
            paymentOrder.getStatus().getName(),
            paymentOrder.getAmount(),
            paymentOrder.getSubject(),
            paymentOrder.getBody(),
            paymentOrder.getPaymentChannel() != null ? paymentOrder.getPaymentChannel().getName() : null,
            paymentOrder.getQrCodeUrl(),
            paymentOrder.getClientIp(),
            paymentOrder.getCreatedAt(),
            paymentOrder.getExpiredAt(),
            paymentOrder.getPaidAt(),
            paymentOrder.getBankOrderNo(),
            paymentOrder.getThirdPartyOrderNo()
        );
    }

    /**
     * 批量转换为响应 DTO（委托 static {@link #convert}，保证与详情输出一致）。
     *
     * @param paymentOrders 支付订单聚合根列表
     * @return 响应 DTO 列表
     */
    default List<PaymentOrderResponse> convertList(List<PaymentOrder> paymentOrders) {
        return paymentOrders.stream().map(PaymentOrderMapper::convert).toList();
    }
}
```

> **只新增 import `java.util.List` 与末尾 `convertList` default 方法**；不改动既有 `toResponse` 与 static `convert`（既有调用方零影响）。

- [ ] **Step 5: 写 `PaymentOrderQueryAppService`（最小实现使测试通过）**

创建 `src/main/java/com/aieducenter/payment/application/PaymentOrderQueryAppService.java`：

```java
package com.aieducenter.payment.application;

import com.aieducenter.payment.application.dto.query.PaymentOrderQuery;
import com.aieducenter.payment.application.dto.response.PaymentOrderResponse;
import com.aieducenter.payment.application.mapper.PaymentOrderMapper;
import com.aieducenter.payment.domain.aggregate.PaymentOrder;
import com.aieducenter.payment.domain.repository.PaymentOrderRepository;
import com.cartisan.data.jpa.specification.ConditionSpecifications;
import com.cartisan.web.response.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 支付订单查询应用服务（读侧）。
 *
 * <p>spec「按职责拆分应用服务」：与既有写侧 {@code PaymentAppService}（创建 / 查状态 / 取消）分离，
 * 仅承担多条件分页读列表。详情沿用既有 {@code PaymentAppService.getPayment}。查询银行无关
 * （CONTEXT.md 不变式 5）。</p>
 */
@Service
@RequiredArgsConstructor
public class PaymentOrderQueryAppService {

    private final PaymentOrderRepository paymentOrderRepository;
    private final PaymentOrderMapper paymentOrderMapper;

    /**
     * 多条件分页查询支付订单。
     *
     * @param query    查询条件（null / 空字符串字段自动跳过）
     * @param pageable 分页参数
     * @return 分页响应（仅暴露 DTO）
     */
    @Transactional(readOnly = true)
    public PageResponse<PaymentOrderResponse> list(PaymentOrderQuery query, Pageable pageable) {
        Page<PaymentOrder> page = paymentOrderRepository.findAll(
            ConditionSpecifications.fromAnnotation(query), pageable
        );
        return new PageResponse<>(
            paymentOrderMapper.convertList(page.getContent()),
            page.getTotalElements(),
            pageable.getPageNumber() + 1,
            pageable.getPageSize()
        );
    }
}
```

- [ ] **Step 6: 运行测试，确认通过**

Run: `mvn test -Dtest=PaymentOrderQueryAppServiceTest -q`
Expected: BUILD SUCCESS——2 个测试全部通过。

- [ ] **Step 7: Commit**

```bash
git add src/main/java/com/aieducenter/payment/application/dto/query/PaymentOrderQuery.java \
        src/main/java/com/aieducenter/payment/application/mapper/PaymentOrderMapper.java \
        src/main/java/com/aieducenter/payment/application/PaymentOrderQueryAppService.java \
        src/test/java/com/aieducenter/payment/application/PaymentOrderQueryAppServiceTest.java
git commit -m "feat(payment): PaymentOrderQueryAppService（list 多条件分页）+ Query + Mapper convertList

Co-Authored-By: Claude <noreply@anthropic.com>"
```

---

### Task 3: 端点 `GET /api/v1/payments`（薄委托）

**Files:**
- Modify: `src/main/java/com/aieducenter/payment/endpoints/api/v1/PaymentApiV1Controller.java`

**Interfaces:**
- Consumes: Task 2 的 `PaymentOrderQueryAppService.list(query, pageable)`。
- Produces: `GET /api/v1/payments` → `ApiResponse<PageResponse<PaymentOrderResponse>>`，`@RequireSignature`，与既有 `GET /api/v1/payments/{paymentOrderNo}` 路由无冲突。

- [ ] **Step 1: 在控制器加 list 端点（注入新 AppService + 加 GET 方法）**

修改 `src/main/java/com/aieducenter/payment/endpoints/api/v1/PaymentApiV1Controller.java`：

1. import 区追加（按既有导入顺序）：

```java
import com.aieducenter.payment.application.PaymentOrderQueryAppService;
import com.aieducenter.payment.application.dto.query.PaymentOrderQuery;
import com.cartisan.web.response.PageResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
```

2. 在类内既有 `private final PrepayAppService prepayAppService;` 之后追加注入字段：

```java
    private final PaymentOrderQueryAppService paymentOrderQueryAppService;
```

3. 在 `getPayment` 方法之前追加 list 端点：

```java
    @GetMapping
    @RequireSignature
    @Operation(summary = "分页查询支付订单")
    public ApiResponse<PageResponse<PaymentOrderResponse>> list(
            PaymentOrderQuery query,
            @PageableDefault(size = 20) Pageable pageable
    ) {
        return ApiResponse.ok(paymentOrderQueryAppService.list(query, pageable));
    }
```

> **薄委托**：行为由 AppService 缝覆盖；签名校验由 `@RequireSignature` 兜底（`SignatureVerificationIntegrationTest`）；不引入控制器测试缝（与现有代码库一致）。`@RequiredArgsConstructor` 会自动把新字段加入构造函数。

- [ ] **Step 2: 编译 + 架构守护测试**

Run: `mvn test -Dtest=ArchitectureTest -q`
Expected: BUILD SUCCESS——分层规则通过（Controller 可用领域枚举 / Pageable，框架已许可）。

- [ ] **Step 3: Commit**

```bash
git add src/main/java/com/aieducenter/payment/endpoints/api/v1/PaymentApiV1Controller.java
git commit -m "feat(payment): GET /api/v1/payments 端点（@RequireSignature 多条件分页）

Co-Authored-By: Claude <noreply@anthropic.com>"
```

---

### Task 4: pitest 覆盖 + 全量验证

**Files:**
- Modify: `pom.xml`（pitest `<targetClasses>`）

**Interfaces:**
- Consumes: Task 2 的 `PaymentOrderQueryAppService`。
- Produces: pitest 变异评分覆盖新 AppService 且 ≥ 70%；全量测试绿。

- [ ] **Step 1: 把新 AppService 纳入 pitest targetClasses**

修改 `pom.xml` 的 `<targetClasses>` 段，追加一行 `<param>com.aieducenter.payment.application.PaymentOrderQueryAppService</param>`：

```xml
                    <targetClasses>
                        <param>com.aieducenter.payment.domain.aggregate.OperationLog</param>
                        <param>com.aieducenter.payment.application.OperationLogAppService</param>
                        <param>com.aieducenter.payment.application.PaymentOrderQueryAppService</param>
                    </targetClasses>
```

> 同时把该段上方说明注释里的「先精确覆盖新增且可测的 OperationLog 代码」语义顺延为「OperationLog + PaymentOrderQueryAppService」——可选：把注释里的「OperationLog」补为「OperationLog / 支付订单查询」。注释修订不强制，仅 `<param>` 是必须的。

- [ ] **Step 2: 跑变异测试**

Run: `mvn org.pitest:pitest-maven:mutationCoverage -q`
Expected: BUILD SUCCESS；`PaymentOrderQueryAppService` 变异评分 ≥ 70%（`+1` / `getTotalElements` / `getPageSize` / `getContent` / `convertList` 变异点均被 Step 2 的 2 个测试杀灭）。

- [ ] **Step 3: 跑全量测试**

Run: `mvn test -q`
Expected: BUILD SUCCESS——全部既有测试 + 新增 `PaymentOrderQueryAppServiceTest` + `ArchitectureTest` 绿。

- [ ] **Step 4: （可选）打包验证**

Run: `mvn package -DskipTests -q`
Expected: BUILD SUCCESS。

- [ ] **Step 5: Commit**

```bash
git add pom.xml
git commit -m "chore(build): pitest 覆盖 PaymentOrderQueryAppService

Co-Authored-By: Claude <noreply@anthropic.com>"
```

---

## Self-Review

**1. Spec / Issue #10 覆盖：**
- *PaymentOrderQuery（全部 13 字段）* → Task 2 Step 1，字段逐一对应 issue 列举的筛选维度（paymentOrderNo / businessOrderNo / businessSystemName / status(多) / payMode / accessType / paymentChannel / amountMin..Max / createdAtFrom..To / paidAtFrom..To）。✓
- *PaymentOrderRepository 多条件分页查询* → Task 2 Step 5，复用 `findAll(Specification, Pageable)`（spec 三选一里的 Specification 路径，与 OperationLog 一致）。✓
- *应用服务 list(query, Pageable) → PageResponse* → Task 2 Step 5。✓
- *端点 GET /api/v1/payments* → Task 3。✓
- *确立列表查询范式* → 严格复用 OperationLog 范式（query record + PageResponse + @Condition 筛选约定）。✓
- *详情沿用既有 getPayment，本票不动* → 不修改 `PaymentAppService.getPayment`，列表走新 `PaymentOrderQueryAppService`。✓
- AC1「经签名、按筛选过滤、分页返回 PageResponse」→ `@RequireSignature`（Task 3）+ `@Condition` 过滤（Task 2）+ `PageResponse`（Task 2）。✓
- AC2「查询银行无关、无 ICBC 硬编码；为筛选用字段建索引」→ 全程无 ICBC 硬编码（`PaymentChannel` 为抽象枚举）；V7 索引（Task 1）。✓
- AC3「AppService Mockito 缝测试覆盖筛选参数传递与响应映射，pitest 过」→ Task 2 测试 + Task 4 pitest。✓

**2. 占位符扫描：** 无 TBD / TODO / 「类似 Task N」/ 无代码步骤。所有代码块完整。✓

**3. 类型一致性：**
- `PaymentOrderQuery` 字段名（`statuses` / `amountMin` / `amountMax` / `createdAtFrom` / `createdAtTo` / `paidAtFrom` / `paidAtTo`）在测试构造、`propName`、控制器绑定中一致。✓
- `PaymentOrderQueryAppService.list(query, pageable)` 签名在测试（Task 2 Step 2）、实现（Step 5）、控制器（Task 3）一致。✓
- `PaymentOrderMapper.convertList(List<PaymentOrder>) → List<PaymentOrderResponse>` 在 Mapper（Step 4）、AppService（Step 5）、测试 mock（Step 2）一致。✓
- 构造函数顺序 `(paymentOrderRepository, paymentOrderMapper)` 在 AppService 与测试 `setUp` 一致。✓

**4. 风险确认：**
- `statuses` 空 list：框架 `fromAnnotation` 只跳过 null 与空字符串，空 `Collection` 会进 `path.in(emptyColl)` → Hibernate 渲染为恒假（返回 0 行）。但 Spring MVC 对未出现的 `statuses` 参数绑定为 null（跳过 → 全状态）；仅当调用方显式传空集合才触发，属可接受的框架已知行为（与 OperationLog 同构），非本票回归。已在决策表与 Global Constraints 注明。
- Mapper 双路径：明确不扩展 `DomainMapper`、`convertList` 委托 static `convert`，杜绝列表行与详情输出漂移。✓
