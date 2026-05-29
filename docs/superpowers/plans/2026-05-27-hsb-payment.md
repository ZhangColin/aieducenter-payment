# 建行惠市宝支付接入 实现计划

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** 实现建行惠市宝（对公专业结算综合服务平台）支付接入，支持多方分账场景下的支付、退款、分账确认功能。

**Architecture:** 新建独立的 `hsb` 限界上下文（bounded context），遵循现有 DDD 六边形架构：domain（聚合根、枚举、仓储、端口）→ application（DTO、应用服务）→ infrastructure（签名、HTTP、网关适配器）→ endpoints（控制器）。与现有工行代码完全隔离。

**Tech Stack:** Java 21, Spring Boot 3, JPA/Hibernate, PostgreSQL, Flyway, mktpaysign.jar (建行签名), fastjson2, MapStruct, Lombok, JUnit 5 + Mockito

---

## 文件结构

### Domain 层 (`hsb/domain/`)
| 文件 | 职责 |
|------|------|
| `enums/HsbPaymentStatus.java` | 支付状态枚举: PENDING/PAID/FAILED/EXPIRED |
| `enums/HsbRefundStatus.java` | 退款状态枚举: PENDING/REFUNDING/SUCCESS/FAILED |
| `enums/HsbSettlementStatus.java` | 分账状态枚举: PENDING/CONFIRMED/FAILED |
| `error/HsbMessage.java` | 错误消息枚举 |
| `aggregate/HsbSubOrder.java` | 子订单（@OneToMany 嵌入聚合根） |
| `aggregate/HsbPaymentOrder.java` | 主支付订单聚合根 |
| `aggregate/HsbRefundSubOrder.java` | 退款子订单 |
| `aggregate/HsbRefundOrder.java` | 退款订单聚合根 |
| `aggregate/HsbSettlementConfirm.java` | 分账确认聚合根 |
| `aggregate/HsbPaymentLog.java` | 操作日志 |
| `repository/HsbPaymentOrderRepository.java` | 支付订单仓储 |
| `repository/HsbRefundOrderRepository.java` | 退款订单仓储 |
| `repository/HsbSettlementConfirmRepository.java` | 分账确认仓储 |
| `repository/HsbPaymentLogRepository.java` | 日志仓储 |
| `port/HsbPaymentGatewayPort.java` | 南向网关端口接口 |
| `port/response/CreateHsbPaymentResponse.java` | 创建支付响应 |
| `port/response/QueryHsbPaymentResponse.java` | 查询支付响应 |
| `port/response/CreateHsbRefundResponse.java` | 创建退款响应 |
| `port/response/QueryHsbRefundResponse.java` | 查询退款响应 |
| `port/response/ConfirmSettlementResponse.java` | 确认分账响应 |

### Application 层 (`hsb/application/`)
| 文件 | 职责 |
|------|------|
| `dto/command/CreateHsbPaymentCommand.java` | 创建支付命令（含子订单列表） |
| `dto/command/CreateHsbRefundCommand.java` | 创建退款命令（含退款子订单列表） |
| `dto/command/ConfirmHsbSettlementCommand.java` | 确认分账命令 |
| `dto/callback/HsbPaymentCallbackParam.java` | 支付回调参数 |
| `dto/callback/HsbRefundCallbackParam.java` | 退款回调参数 |
| `dto/response/HsbPaymentOrderResponse.java` | 支付订单响应（含子订单） |
| `dto/response/HsbRefundOrderResponse.java` | 退款订单响应（含子订单） |
| `mapper/HsbPaymentOrderMapper.java` | MapStruct 转换器 |
| `HsbPaymentAppService.java` | 支付应用服务 |
| `HsbCallbackAppService.java` | 回调处理服务 |
| `HsbRefundAppService.java` | 退款应用服务 |
| `HsbSettlementAppService.java` | 分账确认应用服务 |
| `HsbPaymentQueryScheduler.java` | 支付状态定时查询 |
| `HsbRefundQueryScheduler.java` | 退款状态定时查询 |

### Infrastructure 层 (`hsb/infrastructure/`)
| 文件 | 职责 |
|------|------|
| `HsbConfig.java` | 配置属性 |
| `HsbSplicingUtil.java` | 签名字符串拼接 |
| `HsbSignUtil.java` | RSA 签名/验签封装 |
| `HsbHttpClient.java` | HTTP 客户端 |
| `HsbPaymentGatewayAdapter.java` | 建行网关适配器 |

### Endpoints 层 (`hsb/endpoints/api/v1/`)
| 文件 | 职责 |
|------|------|
| `HsbPaymentController.java` | 支付 API |
| `HsbRefundController.java` | 退款 API |
| `HsbSettlementController.java` | 分账确认 API |
| `HsbCallbackController.java` | 建行回调 API |

### 其他
| 文件 | 职责 |
|------|------|
| `pom.xml` | 添加 mktpaysign.jar 依赖 |
| `lib/ccb/mktpaysign.jar` | 建行签名工具包 |
| `db/migration/V2__create_hsb_tables.sql` | 6张HSB表 |

---


## Task 1: 安装 mktpaysign.jar + 更新 pom.xml

**Files:**
- Create: `lib/ccb/mktpaysign.jar`（从建行测试代码目录复制）
- Modify: `pom.xml`

- [ ] **Step 1: 复制 mktpaysign.jar 到 lib/ccb 目录**

```bash
mkdir -p lib/ccb
cp "docs/建行/TestPay/WebContent/WEB-INF/lib/mktpaysign.jar" lib/ccb/mktpaysign.jar
```

- [ ] **Step 2: 在 pom.xml 中添加 mktpaysign.jar 依赖**

在 `pom.xml` 的 `<!-- ICBC SDK dependencies -->` 注释块之后添加：

```xml
        <!-- CCB HSB SDK dependencies -->
        <dependency>
            <groupId>com.ccb</groupId>
            <artifactId>mktpaysign</artifactId>
            <version>1.0</version>
            <scope>system</scope>
            <systemPath>${project.basedir}/lib/ccb/mktpaysign.jar</systemPath>
        </dependency>
```

- [ ] **Step 3: 编译验证**

Run: `mvn compile -q`
Expected: BUILD SUCCESS

- [ ] **Step 4: Commit**

```bash
git add lib/ccb/mktpaysign.jar pom.xml
git commit -m "feat: add CCB mktpaysign.jar dependency for HSB payment"
```

---


## Task 2: Flyway 迁移 — 创建 HSB 表

**Files:**
- Create: `src/main/resources/db/migration/V2__create_hsb_tables.sql`

- [ ] **Step 1: 创建迁移文件**

```sql
-- ========================================================================
-- HSB (惠市宝) Context: Payment Orders
-- ========================================================================

CREATE TABLE hsb_payment_orders (
    id BIGINT PRIMARY KEY,
    payment_order_no VARCHAR(64) NOT NULL UNIQUE,
    business_main_order_no VARCHAR(64) NOT NULL,
    business_system_name VARCHAR(128) NOT NULL,
    business_name VARCHAR(128),
    status INTEGER NOT NULL,
    mkt_id VARCHAR(32) NOT NULL,
    payment_method VARCHAR(8) NOT NULL,
    order_type VARCHAR(8) NOT NULL,
    currency VARCHAR(8) DEFAULT '156',
    total_amount BIGINT NOT NULL,
    txn_total_amount BIGINT NOT NULL,
    fee_bearer_id VARCHAR(32),
    expired_seconds BIGINT DEFAULT 3600,
    notify_url VARCHAR(512),
    attach TEXT,
    pay_url VARCHAR(512),
    pay_qr_code VARCHAR(512),
    prim_order_no VARCHAR(64),
    py_trn_no VARCHAR(64),
    actual_amount BIGINT,
    paid_at TIMESTAMP,
    failed_at TIMESTAMP,
    expired_at TIMESTAMP,

    -- Audit fields
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by BIGINT,
    updated_by BIGINT,
    deleted BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE INDEX idx_hsb_payment_orders_business_no ON hsb_payment_orders(business_main_order_no) WHERE deleted = FALSE;
CREATE INDEX idx_hsb_payment_orders_system ON hsb_payment_orders(business_system_name) WHERE deleted = FALSE;
CREATE INDEX idx_hsb_payment_orders_status ON hsb_payment_orders(status) WHERE deleted = FALSE;
CREATE INDEX idx_hsb_payment_orders_created_at ON hsb_payment_orders(created_at) WHERE deleted = FALSE;

-- ========================================================================
-- HSB Context: Payment Sub Orders
-- ========================================================================

CREATE TABLE hsb_payment_sub_orders (
    id BIGINT PRIMARY KEY,
    payment_order_id BIGINT NOT NULL REFERENCES hsb_payment_orders(id),
    payment_order_no VARCHAR(64),
    business_main_order_no VARCHAR(64) NOT NULL,
    business_sub_order_no VARCHAR(64) NOT NULL,
    mkt_mrch_id VARCHAR(32) NOT NULL,
    order_amount BIGINT NOT NULL,
    txn_amount BIGINT NOT NULL,
    sub_order_id VARCHAR(64),
    confirmed BOOLEAN DEFAULT FALSE,
    confirmed_at TIMESTAMP,

    -- Audit fields
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by BIGINT,
    updated_by BIGINT,
    deleted BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE INDEX idx_hsb_sub_orders_payment_id ON hsb_payment_sub_orders(payment_order_id) WHERE deleted = FALSE;
CREATE INDEX idx_hsb_sub_orders_biz_sub_no ON hsb_payment_sub_orders(business_sub_order_no) WHERE deleted = FALSE;
CREATE INDEX idx_hsb_sub_orders_mkt_mrch_id ON hsb_payment_sub_orders(mkt_mrch_id) WHERE deleted = FALSE;

-- ========================================================================
-- HSB Context: Refund Orders
-- ========================================================================

CREATE TABLE hsb_refund_orders (
    id BIGINT PRIMARY KEY,
    refund_order_no VARCHAR(64) NOT NULL UNIQUE,
    payment_order_id BIGINT NOT NULL REFERENCES hsb_payment_orders(id),
    payment_order_no VARCHAR(64) NOT NULL,
    business_main_order_no VARCHAR(64) NOT NULL,
    business_system_name VARCHAR(128) NOT NULL,
    business_name VARCHAR(128),
    refund_type VARCHAR(16) DEFAULT 'ASYNC',
    status INTEGER NOT NULL,
    refund_amount BIGINT NOT NULL,
    reason VARCHAR(512),
    notify_url VARCHAR(512),
    attach TEXT,
    super_refund_no VARCHAR(64),
    refunded_at TIMESTAMP,
    failed_at TIMESTAMP,

    -- Audit fields
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by BIGINT,
    updated_by BIGINT,
    deleted BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE INDEX idx_hsb_refund_orders_payment_id ON hsb_refund_orders(payment_order_id) WHERE deleted = FALSE;
CREATE INDEX idx_hsb_refund_orders_business_no ON hsb_refund_orders(business_main_order_no) WHERE deleted = FALSE;
CREATE INDEX idx_hsb_refund_orders_status ON hsb_refund_orders(status) WHERE deleted = FALSE;

-- ========================================================================
-- HSB Context: Refund Sub Orders
-- ========================================================================

CREATE TABLE hsb_refund_sub_orders (
    id BIGINT PRIMARY KEY,
    refund_order_id BIGINT NOT NULL REFERENCES hsb_refund_orders(id),
    refund_order_no VARCHAR(64),
    business_main_order_no VARCHAR(64) NOT NULL,
    business_sub_order_no VARCHAR(64) NOT NULL,
    sub_order_id VARCHAR(64),
    refund_amount BIGINT NOT NULL,

    -- Audit fields
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by BIGINT,
    updated_by BIGINT,
    deleted BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE INDEX idx_hsb_refund_sub_orders_refund_id ON hsb_refund_sub_orders(refund_order_id) WHERE deleted = FALSE;
CREATE INDEX idx_hsb_refund_sub_orders_biz_sub_no ON hsb_refund_sub_orders(business_sub_order_no) WHERE deleted = FALSE;

-- ========================================================================
-- HSB Context: Settlement Confirms
-- ========================================================================

CREATE TABLE hsb_settlement_confirms (
    id BIGINT PRIMARY KEY,
    payment_order_id BIGINT NOT NULL REFERENCES hsb_payment_orders(id),
    payment_order_no VARCHAR(64) NOT NULL,
    business_main_order_no VARCHAR(64) NOT NULL,
    status INTEGER NOT NULL,
    business_sub_order_nos JSON,
    sub_order_ids JSON,
    confirmed_at TIMESTAMP,

    -- Audit fields
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by BIGINT,
    updated_by BIGINT,
    deleted BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE INDEX idx_hsb_settlement_confirms_payment_id ON hsb_settlement_confirms(payment_order_id) WHERE deleted = FALSE;
CREATE INDEX idx_hsb_settlement_confirms_status ON hsb_settlement_confirms(status) WHERE deleted = FALSE;

-- ========================================================================
-- HSB Context: Payment Logs
-- ========================================================================

CREATE TABLE hsb_payment_logs (
    id BIGINT PRIMARY KEY,
    payment_order_no VARCHAR(64),
    refund_order_no VARCHAR(64),
    log_type VARCHAR(32),
    bank_interface VARCHAR(64),
    request_url VARCHAR(512),
    request_params TEXT,
    response_params TEXT,
    http_status INTEGER,
    return_code VARCHAR(16),
    return_msg TEXT,
    execution_time BIGINT,
    success BOOLEAN,
    error_message TEXT,

    -- Audit fields
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by BIGINT,
    updated_by BIGINT,
    deleted BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE INDEX idx_hsb_payment_logs_payment_no ON hsb_payment_logs(payment_order_no) WHERE deleted = FALSE;
CREATE INDEX idx_hsb_payment_logs_refund_no ON hsb_payment_logs(refund_order_no) WHERE deleted = FALSE;
```

- [ ] **Step 2: Commit**

```bash
git add src/main/resources/db/migration/V2__create_hsb_tables.sql
git commit -m "feat: add Flyway migration for HSB payment tables"
```

---


## Task 3: Domain 枚举 + 错误消息

**Files:**
- Create: `src/main/java/com/aieducenter/payment/hsb/domain/enums/HsbPaymentStatus.java`
- Create: `src/main/java/com/aieducenter/payment/hsb/domain/enums/HsbRefundStatus.java`
- Create: `src/main/java/com/aieducenter/payment/hsb/domain/enums/HsbSettlementStatus.java`
- Create: `src/main/java/com/aieducenter/payment/hsb/domain/error/HsbMessage.java`

- [ ] **Step 1: 创建 HsbPaymentStatus 枚举**

```java
package com.aieducenter.payment.hsb.domain.enums;

import com.cartisan.core.domain.BaseEnum;
import com.cartisan.data.jpa.converter.BaseEnumConverter;
import jakarta.persistence.Converter;

public enum HsbPaymentStatus implements BaseEnum<HsbPaymentStatus> {
    PENDING(1, "待支付"),
    PAID(2, "已支付"),
    FAILED(3, "支付失败"),
    EXPIRED(4, "已过期");

    private final Integer code;
    private final String name;

    HsbPaymentStatus(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public Integer getCode() { return code; }

    @Override
    public String getName() { return name; }

    public boolean isTerminal() {
        return this == PAID || this == FAILED || this == EXPIRED;
    }

    @Converter(autoApply = true)
    public static class JpaConverter extends BaseEnumConverter<HsbPaymentStatus> {
        public JpaConverter() { super(HsbPaymentStatus.class); }
    }
}
```

- [ ] **Step 2: 创建 HsbRefundStatus 枚举**

```java
package com.aieducenter.payment.hsb.domain.enums;

import com.cartisan.core.domain.BaseEnum;
import com.cartisan.data.jpa.converter.BaseEnumConverter;
import jakarta.persistence.Converter;

public enum HsbRefundStatus implements BaseEnum<HsbRefundStatus> {
    PENDING(1, "待退款"),
    REFUNDING(2, "退款中"),
    SUCCESS(3, "退款成功"),
    FAILED(4, "退款失败");

    private final Integer code;
    private final String name;

    HsbRefundStatus(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public Integer getCode() { return code; }

    @Override
    public String getName() { return name; }

    public boolean isTerminal() {
        return this == SUCCESS || this == FAILED;
    }

    @Converter(autoApply = true)
    public static class JpaConverter extends BaseEnumConverter<HsbRefundStatus> {
        public JpaConverter() { super(HsbRefundStatus.class); }
    }
}
```

- [ ] **Step 3: 创建 HsbSettlementStatus 枚举**

```java
package com.aieducenter.payment.hsb.domain.enums;

import com.cartisan.core.domain.BaseEnum;
import com.cartisan.data.jpa.converter.BaseEnumConverter;
import jakarta.persistence.Converter;

public enum HsbSettlementStatus implements BaseEnum<HsbSettlementStatus> {
    PENDING(1, "待确认"),
    CONFIRMED(2, "已确认"),
    FAILED(3, "确认失败");

    private final Integer code;
    private final String name;

    HsbSettlementStatus(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public Integer getCode() { return code; }

    @Override
    public String getName() { return name; }

    public boolean isTerminal() {
        return this == CONFIRMED || this == FAILED;
    }

    @Converter(autoApply = true)
    public static class JpaConverter extends BaseEnumConverter<HsbSettlementStatus> {
        public JpaConverter() { super(HsbSettlementStatus.class); }
    }
}
```

- [ ] **Step 4: 创建 HsbMessage 错误枚举**

```java
package com.aieducenter.payment.hsb.domain.error;

import com.cartisan.core.exception.CodeMessage;

public enum HsbMessage implements CodeMessage {
    // ========== 业务规则错误 (400) ==========
    HSB_PAYMENT_ORDER_NOT_PENDING(400, "HSB_010", "惠市宝支付订单不是待支付状态"),
    HSB_REFUND_ORDER_NOT_PENDING(400, "HSB_011", "惠市宝退款订单不是待退款状态"),
    HSB_REFUND_AMOUNT_EXCEEDS(400, "HSB_012", "退款金额超过可退款金额"),
    HSB_SETTLEMENT_NOT_PAID(400, "HSB_013", "支付订单未支付，无法确认分账"),
    HSB_SUB_ORDER_NOT_FOUND(400, "HSB_014", "子订单不存在"),
    HSB_SUB_ORDER_ALREADY_CONFIRMED(400, "HSB_015", "子订单已确认分账"),

    // ========== 资源不存在 (404) ==========
    HSB_PAYMENT_ORDER_NOT_FOUND(404, "HSB_030", "惠市宝支付订单不存在"),
    HSB_REFUND_ORDER_NOT_FOUND(404, "HSB_031", "惠市宝退款订单不存在"),

    // ========== 签名错误 (400) ==========
    HSB_SIGN_VERIFY_FAILED(400, "HSB_040", "建行回调验签失败"),
    HSB_SIGN_FAILED(500, "HSB_041", "建行签名失败");

    private final int httpStatus;
    private final String code;
    private final String message;

    HsbMessage(int httpStatus, String code, String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }

    @Override
    public int httpStatus() { return httpStatus; }

    @Override
    public String code() { return code; }

    @Override
    public String message() { return message; }
}
```

- [ ] **Step 5: 编译验证**

Run: `mvn compile -q`
Expected: BUILD SUCCESS

- [ ] **Step 6: Commit**

```bash
git add src/main/java/com/aieducenter/payment/hsb/domain/enums/ src/main/java/com/aieducenter/payment/hsb/domain/error/
git commit -m "feat: add HSB domain enums and error messages"
```

---


## Task 4: HsbPaymentOrder + HsbSubOrder 聚合根

**Files:**
- Create: `src/main/java/com/aieducenter/payment/hsb/domain/aggregate/HsbSubOrder.java`
- Create: `src/main/java/com/aieducenter/payment/hsb/domain/aggregate/HsbPaymentOrder.java`

- [ ] **Step 1: 创建 HsbSubOrder 实体**

```java
package com.aieducenter.payment.hsb.domain.aggregate;

import com.cartisan.data.jpa.domain.AuditableSoftDeletable;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Table(name = "hsb_payment_sub_orders")
public class HsbSubOrder extends AuditableSoftDeletable {

    @Getter
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Getter
    @Column(name = "payment_order_id", nullable = false)
    private Long paymentOrderId;

    @Getter
    @Column(name = "payment_order_no", length = 64)
    private String paymentOrderNo;

    @Getter
    @Column(name = "business_main_order_no", nullable = false, length = 64)
    private String businessMainOrderNo;

    @Getter
    @Column(name = "business_sub_order_no", nullable = false, length = 64)
    private String businessSubOrderNo;

    @Getter
    @Column(name = "mkt_mrch_id", nullable = false, length = 32)
    private String mktMrchId;

    @Getter
    @Column(name = "order_amount", nullable = false)
    private Long orderAmount;

    @Getter
    @Column(name = "txn_amount", nullable = false)
    private Long txnAmount;

    @Getter
    @Column(name = "sub_order_id", length = 64)
    private String subOrderId;

    @Getter
    @Column(name = "confirmed")
    private Boolean confirmed = false;

    @Getter
    @Column(name = "confirmed_at")
    private LocalDateTime confirmedAt;

    @PrePersist
    void prePersist() {
        if (id == null) {
            this.id = com.cartisan.data.jpa.id.TsidGenerator.newInstance().generate();
        }
    }

    protected HsbSubOrder() {}

    public HsbSubOrder(
            String businessMainOrderNo,
            String businessSubOrderNo,
            String mktMrchId,
            Long orderAmount,
            Long txnAmount
    ) {
        this.businessMainOrderNo = businessMainOrderNo;
        this.businessSubOrderNo = businessSubOrderNo;
        this.mktMrchId = mktMrchId;
        this.orderAmount = orderAmount;
        this.txnAmount = txnAmount;
        this.confirmed = false;
    }

    void setPaymentOrderInfo(Long paymentOrderId, String paymentOrderNo) {
        this.paymentOrderId = paymentOrderId;
        this.paymentOrderNo = paymentOrderNo;
    }

    public void setSubOrderId(String subOrderId) {
        this.subOrderId = subOrderId;
    }

    public void markAsConfirmed() {
        this.confirmed = true;
        this.confirmedAt = LocalDateTime.now();
    }
}
```

- [ ] **Step 2: 创建 HsbPaymentOrder 聚合根**

```java
package com.aieducenter.payment.hsb.domain.aggregate;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.aieducenter.payment.hsb.domain.enums.HsbPaymentStatus;
import com.aieducenter.payment.hsb.domain.error.HsbMessage;
import com.cartisan.core.domain.AggregateRoot;
import com.cartisan.core.stereotype.Aggregate;
import com.cartisan.core.util.Assertions;
import com.cartisan.data.jpa.domain.AuditableSoftDeletable;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "hsb_payment_orders")
@Aggregate
public class HsbPaymentOrder extends AuditableSoftDeletable implements AggregateRoot<HsbPaymentOrder, Long> {

    private static final String PAYMENT_ORDER_NO_PREFIX = "HSB";
    private static final DateTimeFormatter TIMESTAMP_FORMAT = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    @Getter
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Getter
    @Column(name = "payment_order_no", nullable = false, unique = true, length = 64)
    private String paymentOrderNo;

    @Getter
    @Column(name = "business_main_order_no", nullable = false, length = 64)
    private String businessMainOrderNo;

    @Getter
    @Column(name = "business_system_name", nullable = false, length = 128)
    private String businessSystemName;

    @Getter
    @Column(name = "business_name", length = 128)
    private String businessName;

    @Getter
    @Column(name = "status", nullable = false)
    private HsbPaymentStatus status;

    @Getter
    @Column(name = "mkt_id", nullable = false, length = 32)
    private String mktId;

    @Getter
    @Column(name = "payment_method", nullable = false, length = 8)
    private String paymentMethod;

    @Getter
    @Column(name = "order_type", nullable = false, length = 8)
    private String orderType;

    @Getter
    @Column(name = "currency", length = 8)
    private String currency;

    @Getter
    @Column(name = "total_amount", nullable = false)
    private Long totalAmount;

    @Getter
    @Column(name = "txn_total_amount", nullable = false)
    private Long txnTotalAmount;

    @Getter
    @Column(name = "fee_bearer_id", length = 32)
    private String feeBearerId;

    @Getter
    @Column(name = "expired_seconds")
    private Long expiredSeconds;

    @Getter
    @Column(name = "notify_url", length = 512)
    private String notifyUrl;

    @Getter
    @Column(name = "attach", columnDefinition = "TEXT")
    private String attach;

    @Getter
    @Column(name = "pay_url", length = 512)
    private String payUrl;

    @Getter
    @Column(name = "pay_qr_code", length = 512)
    private String payQrCode;

    @Getter
    @Column(name = "prim_order_no", length = 64)
    private String primOrderNo;

    @Getter
    @Column(name = "py_trn_no", length = 64)
    private String pyTrnNo;

    @Getter
    @Column(name = "actual_amount")
    private Long actualAmount;

    @Getter
    @Column(name = "paid_at")
    private LocalDateTime paidAt;

    @Getter
    @Column(name = "failed_at")
    private LocalDateTime failedAt;

    @Getter
    @Column(name = "expired_at")
    private LocalDateTime expiredAt;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_order_id")
    private List<HsbSubOrder> subOrders = new ArrayList<>();

    public List<HsbSubOrder> getSubOrders() {
        return List.copyOf(subOrders);
    }

    @PrePersist
    void prePersist() {
        if (id == null) {
            this.id = com.cartisan.data.jpa.id.TsidGenerator.newInstance().generate();
            this.paymentOrderNo = generatePaymentOrderNo();
            this.status = HsbPaymentStatus.PENDING;
            this.currency = this.currency != null ? this.currency : "156";
            this.expiredAt = calculateExpiredAt();
        }
    }

    protected HsbPaymentOrder() {}

    public HsbPaymentOrder(
            String businessMainOrderNo,
            String businessSystemName,
            String businessName,
            String mktId,
            String paymentMethod,
            String orderType,
            String currency,
            Long totalAmount,
            Long txnTotalAmount,
            String feeBearerId,
            Long expiredSeconds,
            String notifyUrl,
            String attach,
            List<HsbSubOrder> subOrders
    ) {
        Assertions.require(StrUtil.isNotBlank(businessMainOrderNo), HsbMessage.HSB_PAYMENT_ORDER_NOT_FOUND);
        Assertions.require(totalAmount != null && totalAmount > 0, HsbMessage.HSB_PAYMENT_ORDER_NOT_FOUND);
        Assertions.require(txnTotalAmount != null && txnTotalAmount > 0, HsbMessage.HSB_PAYMENT_ORDER_NOT_FOUND);

        this.businessMainOrderNo = businessMainOrderNo;
        this.businessSystemName = businessSystemName;
        this.businessName = businessName;
        this.mktId = mktId;
        this.paymentMethod = paymentMethod;
        this.orderType = orderType;
        this.currency = currency != null ? currency : "156";
        this.totalAmount = totalAmount;
        this.txnTotalAmount = txnTotalAmount;
        this.feeBearerId = feeBearerId;
        this.expiredSeconds = expiredSeconds != null ? expiredSeconds : 3600L;
        this.notifyUrl = notifyUrl;
        this.attach = attach;
        this.status = HsbPaymentStatus.PENDING;
        this.expiredAt = calculateExpiredAt();

        if (subOrders != null) {
            for (HsbSubOrder subOrder : subOrders) {
                addSubOrder(subOrder);
            }
        }
    }

    private void addSubOrder(HsbSubOrder subOrder) {
        this.subOrders.add(subOrder);
    }

    /**
     * 设置建行返回的支付链接和二维码
     */
    public void setPaymentResult(String payUrl, String payQrCode, String primOrderNo) {
        this.payUrl = payUrl;
        this.payQrCode = payQrCode;
        this.primOrderNo = primOrderNo;
    }

    /**
     * 支付成功
     */
    public void markAsPaid(String pyTrnNo, Long actualAmount) {
        Assertions.require(this.status == HsbPaymentStatus.PENDING, HsbMessage.HSB_PAYMENT_ORDER_NOT_PENDING);
        this.status = HsbPaymentStatus.PAID;
        this.pyTrnNo = pyTrnNo;
        this.actualAmount = actualAmount;
        this.paidAt = LocalDateTime.now();
    }

    /**
     * 支付失败
     */
    public void markAsFailed() {
        Assertions.require(this.status == HsbPaymentStatus.PENDING, HsbMessage.HSB_PAYMENT_ORDER_NOT_PENDING);
        this.status = HsbPaymentStatus.FAILED;
        this.failedAt = LocalDateTime.now();
    }

    /**
     * 标记为过期
     */
    public void markAsExpired() {
        Assertions.require(this.status == HsbPaymentStatus.PENDING, HsbMessage.HSB_PAYMENT_ORDER_NOT_PENDING);
        this.status = HsbPaymentStatus.EXPIRED;
    }

    /**
     * 同步子订单的建行子订单编号（支付回调时建行返回）
     */
    public void updateSubOrderIds(List<HsbSubOrder> updatedSubOrders) {
        for (HsbSubOrder updated : updatedSubOrders) {
            this.subOrders.stream()
                .filter(so -> so.getBusinessSubOrderNo().equals(updated.getBusinessSubOrderNo()))
                .findFirst()
                .ifPresent(so -> so.setSubOrderId(updated.getSubOrderId()));
        }
    }

    private String generatePaymentOrderNo() {
        String timestamp = LocalDateTime.now().format(TIMESTAMP_FORMAT);
        String random = RandomUtil.randomString("0123456789", 6);
        return PAYMENT_ORDER_NO_PREFIX + timestamp + random;
    }

    private LocalDateTime calculateExpiredAt() {
        return LocalDateTime.now().plusSeconds(this.expiredSeconds != null ? this.expiredSeconds : 3600L);
    }
}
```

- [ ] **Step 3: 编译验证**

Run: `mvn compile -q`
Expected: BUILD SUCCESS

- [ ] **Step 4: Commit**

```bash
git add src/main/java/com/aieducenter/payment/hsb/domain/aggregate/HsbSubOrder.java src/main/java/com/aieducenter/payment/hsb/domain/aggregate/HsbPaymentOrder.java
git commit -m "feat: add HsbPaymentOrder and HsbSubOrder aggregate roots"
```

---


## Task 5: HsbRefundOrder + HsbRefundSubOrder 聚合根

**Files:**
- Create: `src/main/java/com/aieducenter/payment/hsb/domain/aggregate/HsbRefundSubOrder.java`
- Create: `src/main/java/com/aieducenter/payment/hsb/domain/aggregate/HsbRefundOrder.java`

- [ ] **Step 1: 创建 HsbRefundSubOrder 实体**

```java
package com.aieducenter.payment.hsb.domain.aggregate;

import com.cartisan.data.jpa.domain.AuditableSoftDeletable;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "hsb_refund_sub_orders")
public class HsbRefundSubOrder extends AuditableSoftDeletable {

    @Getter
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Getter
    @Column(name = "refund_order_id", nullable = false)
    private Long refundOrderId;

    @Getter
    @Column(name = "refund_order_no", length = 64)
    private String refundOrderNo;

    @Getter
    @Column(name = "business_main_order_no", nullable = false, length = 64)
    private String businessMainOrderNo;

    @Getter
    @Column(name = "business_sub_order_no", nullable = false, length = 64)
    private String businessSubOrderNo;

    @Getter
    @Column(name = "sub_order_id", length = 64)
    private String subOrderId;

    @Getter
    @Column(name = "refund_amount", nullable = false)
    private Long refundAmount;

    @PrePersist
    void prePersist() {
        if (id == null) {
            this.id = com.cartisan.data.jpa.id.TsidGenerator.newInstance().generate();
        }
    }

    protected HsbRefundSubOrder() {}

    public HsbRefundSubOrder(
            String businessMainOrderNo,
            String businessSubOrderNo,
            String subOrderId,
            Long refundAmount
    ) {
        this.businessMainOrderNo = businessMainOrderNo;
        this.businessSubOrderNo = businessSubOrderNo;
        this.subOrderId = subOrderId;
        this.refundAmount = refundAmount;
    }

    void setRefundOrderInfo(Long refundOrderId, String refundOrderNo) {
        this.refundOrderId = refundOrderId;
        this.refundOrderNo = refundOrderNo;
    }
}
```

- [ ] **Step 2: 创建 HsbRefundOrder 聚合根**

```java
package com.aieducenter.payment.hsb.domain.aggregate;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.aieducenter.payment.hsb.domain.enums.HsbRefundStatus;
import com.aieducenter.payment.hsb.domain.error.HsbMessage;
import com.cartisan.core.domain.AggregateRoot;
import com.cartisan.core.stereotype.Aggregate;
import com.cartisan.core.util.Assertions;
import com.cartisan.data.jpa.domain.AuditableSoftDeletable;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "hsb_refund_orders")
@Aggregate
public class HsbRefundOrder extends AuditableSoftDeletable implements AggregateRoot<HsbRefundOrder, Long> {

    private static final String REFUND_ORDER_NO_PREFIX = "HSBRF";
    private static final DateTimeFormatter TIMESTAMP_FORMAT = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    @Getter
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Getter
    @Column(name = "refund_order_no", nullable = false, unique = true, length = 64)
    private String refundOrderNo;

    @Getter
    @Column(name = "payment_order_id", nullable = false)
    private Long paymentOrderId;

    @Getter
    @Column(name = "payment_order_no", nullable = false, length = 64)
    private String paymentOrderNo;

    @Getter
    @Column(name = "business_main_order_no", nullable = false, length = 64)
    private String businessMainOrderNo;

    @Getter
    @Column(name = "business_system_name", nullable = false, length = 128)
    private String businessSystemName;

    @Getter
    @Column(name = "business_name", length = 128)
    private String businessName;

    @Getter
    @Column(name = "refund_type", length = 16)
    private String refundType;

    @Getter
    @Column(name = "status", nullable = false)
    private HsbRefundStatus status;

    @Getter
    @Column(name = "refund_amount", nullable = false)
    private Long refundAmount;

    @Getter
    @Column(name = "reason", length = 512)
    private String reason;

    @Getter
    @Column(name = "notify_url", length = 512)
    private String notifyUrl;

    @Getter
    @Column(name = "attach", columnDefinition = "TEXT")
    private String attach;

    @Getter
    @Column(name = "super_refund_no", length = 64)
    private String superRefundNo;

    @Getter
    @Column(name = "refunded_at")
    private LocalDateTime refundedAt;

    @Getter
    @Column(name = "failed_at")
    private LocalDateTime failedAt;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "refund_order_id")
    private List<HsbRefundSubOrder> subOrders = new ArrayList<>();

    public List<HsbRefundSubOrder> getSubOrders() {
        return List.copyOf(subOrders);
    }

    @PrePersist
    void prePersist() {
        if (id == null) {
            this.id = com.cartisan.data.jpa.id.TsidGenerator.newInstance().generate();
            this.refundOrderNo = generateRefundOrderNo();
            this.status = HsbRefundStatus.PENDING;
        }
    }

    protected HsbRefundOrder() {}

    public HsbRefundOrder(
            Long paymentOrderId,
            String paymentOrderNo,
            String businessMainOrderNo,
            String businessSystemName,
            String businessName,
            String refundType,
            Long refundAmount,
            String reason,
            String notifyUrl,
            String attach,
            List<HsbRefundSubOrder> subOrders
    ) {
        Assertions.require(refundAmount != null && refundAmount > 0, HsbMessage.HSB_REFUND_AMOUNT_EXCEEDS);

        this.paymentOrderId = paymentOrderId;
        this.paymentOrderNo = paymentOrderNo;
        this.businessMainOrderNo = businessMainOrderNo;
        this.businessSystemName = businessSystemName;
        this.businessName = businessName;
        this.refundType = refundType != null ? refundType : "ASYNC";
        this.refundAmount = refundAmount;
        this.reason = reason;
        this.notifyUrl = notifyUrl;
        this.attach = attach;
        this.status = HsbRefundStatus.PENDING;

        if (subOrders != null) {
            for (HsbRefundSubOrder subOrder : subOrders) {
                this.subOrders.add(subOrder);
            }
        }
    }

    /**
     * 标记为退款中
     */
    public void markAsRefunding() {
        Assertions.require(this.status == HsbRefundStatus.PENDING || this.status == HsbRefundStatus.REFUNDING,
            HsbMessage.HSB_REFUND_ORDER_NOT_PENDING);
        this.status = HsbRefundStatus.REFUNDING;
    }

    /**
     * 退款成功
     */
    public void markAsSuccess(String superRefundNo) {
        Assertions.require(this.status == HsbRefundStatus.PENDING || this.status == HsbRefundStatus.REFUNDING,
            HsbMessage.HSB_REFUND_ORDER_NOT_PENDING);
        this.status = HsbRefundStatus.SUCCESS;
        this.superRefundNo = superRefundNo;
        this.refundedAt = LocalDateTime.now();
    }

    /**
     * 退款失败
     */
    public void markAsFailed() {
        Assertions.require(this.status == HsbRefundStatus.PENDING || this.status == HsbRefundStatus.REFUNDING,
            HsbMessage.HSB_REFUND_ORDER_NOT_PENDING);
        this.status = HsbRefundStatus.FAILED;
        this.failedAt = LocalDateTime.now();
    }

    private String generateRefundOrderNo() {
        String timestamp = LocalDateTime.now().format(TIMESTAMP_FORMAT);
        String random = RandomUtil.randomString("0123456789", 6);
        return REFUND_ORDER_NO_PREFIX + timestamp + random;
    }
}
```

- [ ] **Step 3: 编译验证**

Run: `mvn compile -q`
Expected: BUILD SUCCESS

- [ ] **Step 4: Commit**

```bash
git add src/main/java/com/aieducenter/payment/hsb/domain/aggregate/HsbRefundSubOrder.java src/main/java/com/aieducenter/payment/hsb/domain/aggregate/HsbRefundOrder.java
git commit -m "feat: add HsbRefundOrder and HsbRefundSubOrder aggregate roots"
```

---


## Task 6: HsbSettlementConfirm + HsbPaymentLog + 仓储 + 端口

**Files:**
- Create: `src/main/java/com/aieducenter/payment/hsb/domain/aggregate/HsbSettlementConfirm.java`
- Create: `src/main/java/com/aieducenter/payment/hsb/domain/aggregate/HsbPaymentLog.java`
- Create: `src/main/java/com/aieducenter/payment/hsb/domain/repository/HsbPaymentOrderRepository.java`
- Create: `src/main/java/com/aieducenter/payment/hsb/domain/repository/HsbRefundOrderRepository.java`
- Create: `src/main/java/com/aieducenter/payment/hsb/domain/repository/HsbSettlementConfirmRepository.java`
- Create: `src/main/java/com/aieducenter/payment/hsb/domain/repository/HsbPaymentLogRepository.java`
- Create: `src/main/java/com/aieducenter/payment/hsb/domain/port/HsbPaymentGatewayPort.java`
- Create: `src/main/java/com/aieducenter/payment/hsb/domain/port/response/CreateHsbPaymentResponse.java`
- Create: `src/main/java/com/aieducenter/payment/hsb/domain/port/response/QueryHsbPaymentResponse.java`
- Create: `src/main/java/com/aieducenter/payment/hsb/domain/port/response/CreateHsbRefundResponse.java`
- Create: `src/main/java/com/aieducenter/payment/hsb/domain/port/response/QueryHsbRefundResponse.java`
- Create: `src/main/java/com/aieducenter/payment/hsb/domain/port/response/ConfirmSettlementResponse.java`

- [ ] **Step 1: 创建 HsbSettlementConfirm 聚合根**

```java
package com.aieducenter.payment.hsb.domain.aggregate;

import com.aieducenter.payment.hsb.domain.enums.HsbSettlementStatus;
import com.cartisan.core.domain.AggregateRoot;
import com.cartisan.core.stereotype.Aggregate;
import com.cartisan.data.jpa.domain.AuditableSoftDeletable;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "hsb_settlement_confirms")
@Aggregate
public class HsbSettlementConfirm extends AuditableSoftDeletable implements AggregateRoot<HsbSettlementConfirm, Long> {

    @Getter
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Getter
    @Column(name = "payment_order_id", nullable = false)
    private Long paymentOrderId;

    @Getter
    @Column(name = "payment_order_no", nullable = false, length = 64)
    private String paymentOrderNo;

    @Getter
    @Column(name = "business_main_order_no", nullable = false, length = 64)
    private String businessMainOrderNo;

    @Getter
    @Column(name = "status", nullable = false)
    private HsbSettlementStatus status;

    @Getter
    @Column(name = "business_sub_order_nos", columnDefinition = "JSON")
    private String businessSubOrderNos;

    @Getter
    @Column(name = "sub_order_ids", columnDefinition = "JSON")
    private String subOrderIds;

    @Getter
    @Column(name = "confirmed_at")
    private LocalDateTime confirmedAt;

    @PrePersist
    void prePersist() {
        if (id == null) {
            this.id = com.cartisan.data.jpa.id.TsidGenerator.newInstance().generate();
        }
    }

    protected HsbSettlementConfirm() {}

    public HsbSettlementConfirm(
            Long paymentOrderId,
            String paymentOrderNo,
            String businessMainOrderNo,
            List<String> businessSubOrderNos,
            List<String> subOrderIds
    ) {
        this.paymentOrderId = paymentOrderId;
        this.paymentOrderNo = paymentOrderNo;
        this.businessMainOrderNo = businessMainOrderNo;
        this.businessSubOrderNos = com.alibaba.fastjson2.JSON.toJSONString(businessSubOrderNos);
        this.subOrderIds = com.alibaba.fastjson2.JSON.toJSONString(subOrderIds);
        this.status = HsbSettlementStatus.PENDING;
    }

    public void markAsConfirmed() {
        this.status = HsbSettlementStatus.CONFIRMED;
        this.confirmedAt = LocalDateTime.now();
    }

    public void markAsFailed() {
        this.status = HsbSettlementStatus.FAILED;
    }
}
```

- [ ] **Step 2: 创建 HsbPaymentLog 实体**

```java
package com.aieducenter.payment.hsb.domain.aggregate;

import com.cartisan.data.jpa.domain.AuditableSoftDeletable;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "hsb_payment_logs")
public class HsbPaymentLog extends AuditableSoftDeletable {

    @Getter
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Getter
    @Column(name = "payment_order_no", length = 64)
    private String paymentOrderNo;

    @Getter
    @Column(name = "refund_order_no", length = 64)
    private String refundOrderNo;

    @Getter
    @Column(name = "log_type", length = 32)
    private String logType;

    @Getter
    @Column(name = "bank_interface", length = 64)
    private String bankInterface;

    @Getter
    @Column(name = "request_url", length = 512)
    private String requestUrl;

    @Getter
    @Column(name = "request_params", columnDefinition = "TEXT")
    private String requestParams;

    @Getter
    @Column(name = "response_params", columnDefinition = "TEXT")
    private String responseParams;

    @Getter
    @Column(name = "http_status")
    private Integer httpStatus;

    @Getter
    @Column(name = "return_code", length = 16)
    private String returnCode;

    @Getter
    @Column(name = "return_msg", columnDefinition = "TEXT")
    private String returnMsg;

    @Getter
    @Column(name = "execution_time")
    private Long executionTime;

    @Getter
    @Column(name = "success")
    private Boolean success;

    @Getter
    @Column(name = "error_message", columnDefinition = "TEXT")
    private String errorMessage;

    @PrePersist
    void prePersist() {
        if (id == null) {
            this.id = com.cartisan.data.jpa.id.TsidGenerator.newInstance().generate();
        }
    }

    protected HsbPaymentLog() {}

    public HsbPaymentLog(
            String paymentOrderNo, String refundOrderNo, String logType,
            String bankInterface, String requestUrl, String requestParams,
            String responseParams, Integer httpStatus, String returnCode,
            String returnMsg, Long executionTime, Boolean success, String errorMessage
    ) {
        this.paymentOrderNo = paymentOrderNo;
        this.refundOrderNo = refundOrderNo;
        this.logType = logType;
        this.bankInterface = bankInterface;
        this.requestUrl = requestUrl;
        this.requestParams = requestParams;
        this.responseParams = responseParams;
        this.httpStatus = httpStatus;
        this.returnCode = returnCode;
        this.returnMsg = returnMsg;
        this.executionTime = executionTime;
        this.success = success;
        this.errorMessage = errorMessage;
    }
}
```

- [ ] **Step 3: 创建 4 个仓储接口**

`HsbPaymentOrderRepository.java`:

```java
package com.aieducenter.payment.hsb.domain.repository;

import com.aieducenter.payment.hsb.domain.aggregate.HsbPaymentOrder;
import com.aieducenter.payment.hsb.domain.enums.HsbPaymentStatus;
import com.cartisan.data.jpa.repository.BaseRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface HsbPaymentOrderRepository extends BaseRepository<HsbPaymentOrder, Long> {
    Optional<HsbPaymentOrder> findByPaymentOrderNo(String paymentOrderNo);
    Optional<HsbPaymentOrder> findByBusinessMainOrderNo(String businessMainOrderNo);
    boolean existsByPaymentOrderNo(String paymentOrderNo);
    List<HsbPaymentOrder> findByStatusAndExpiredAtBefore(HsbPaymentStatus status, LocalDateTime expiredAt);
}
```

`HsbRefundOrderRepository.java`:

```java
package com.aieducenter.payment.hsb.domain.repository;

import com.aieducenter.payment.hsb.domain.aggregate.HsbRefundOrder;
import com.aieducenter.payment.hsb.domain.enums.HsbRefundStatus;
import com.cartisan.data.jpa.repository.BaseRepository;

import java.util.List;
import java.util.Optional;

public interface HsbRefundOrderRepository extends BaseRepository<HsbRefundOrder, Long> {
    Optional<HsbRefundOrder> findByRefundOrderNo(String refundOrderNo);
    List<HsbRefundOrder> findByStatus(HsbRefundStatus status);
    List<HsbRefundOrder> findByPyTrnNo(String pyTrnNo);
}
```

`HsbSettlementConfirmRepository.java`:

```java
package com.aieducenter.payment.hsb.domain.repository;

import com.aieducenter.payment.hsb.domain.aggregate.HsbSettlementConfirm;
import com.cartisan.data.jpa.repository.BaseRepository;

import java.util.List;

public interface HsbSettlementConfirmRepository extends BaseRepository<HsbSettlementConfirm, Long> {
    List<HsbSettlementConfirm> findByPaymentOrderNo(String paymentOrderNo);
}
```

`HsbPaymentLogRepository.java`:

```java
package com.aieducenter.payment.hsb.domain.repository;

import com.aieducenter.payment.hsb.domain.aggregate.HsbPaymentLog;
import com.cartisan.data.jpa.repository.BaseRepository;

public interface HsbPaymentLogRepository extends BaseRepository<HsbPaymentLog, Long> {
}
```

- [ ] **Step 4: 创建 HsbPaymentGatewayPort 端口接口**

```java
package com.aieducenter.payment.hsb.domain.port;

import com.aieducenter.payment.hsb.domain.aggregate.HsbPaymentOrder;
import com.aieducenter.payment.hsb.domain.aggregate.HsbRefundOrder;
import com.aieducenter.payment.hsb.domain.aggregate.HsbRefundSubOrder;
import com.aieducenter.payment.hsb.domain.aggregate.HsbSubOrder;
import com.aieducenter.payment.hsb.domain.port.response.*;
import com.cartisan.core.stereotype.Port;
import com.cartisan.core.stereotype.PortType;

import java.util.List;

@Port(PortType.CLIENT)
public interface HsbPaymentGatewayPort {
    CreateHsbPaymentResponse createPayment(HsbPaymentOrder order, List<HsbSubOrder> subOrders);
    QueryHsbPaymentResponse queryPayment(String mktId, String mainOrderNo, String pyTrnNo);
    CreateHsbRefundResponse createRefund(HsbRefundOrder order, String pyTrnNo, List<HsbRefundSubOrder> subOrders);
    QueryHsbRefundResponse queryRefund(String mktId, String custRfndTrcno, String rfndTrcno);
    ConfirmSettlementResponse confirmSettlement(String mktId, String primOrderNo, String subOrderId);
}
```

- [ ] **Step 5: 创建 5 个端口响应 record**

`CreateHsbPaymentResponse.java`:

```java
package com.aieducenter.payment.hsb.domain.port.response;

public record CreateHsbPaymentResponse(
    boolean success,
    String returnCode,
    String returnMsg,
    String payUrl,
    String payQrCode,
    String primOrderNo,
    long executionTime,
    String requestParams,
    String responseParams
) {}
```

`QueryHsbPaymentResponse.java`:

```java
package com.aieducenter.payment.hsb.domain.port.response;

import com.aieducenter.payment.hsb.domain.enums.HsbPaymentStatus;

public record QueryHsbPaymentResponse(
    boolean success,
    String returnCode,
    String returnMsg,
    HsbPaymentStatus paymentStatus,
    String pyTrnNo,
    Long actualAmount,
    long executionTime,
    String responseParams
) {}
```

`CreateHsbRefundResponse.java`:

```java
package com.aieducenter.payment.hsb.domain.port.response;

import com.aieducenter.payment.hsb.domain.enums.HsbRefundStatus;

public record CreateHsbRefundResponse(
    boolean success,
    String returnCode,
    String returnMsg,
    HsbRefundStatus refundStatus,
    String superRefundNo,
    long executionTime,
    String requestParams,
    String responseParams
) {}
```

`QueryHsbRefundResponse.java`:

```java
package com.aieducenter.payment.hsb.domain.port.response;

import com.aieducenter.payment.hsb.domain.enums.HsbRefundStatus;

public record QueryHsbRefundResponse(
    boolean success,
    String returnCode,
    String returnMsg,
    HsbRefundStatus refundStatus,
    String superRefundNo,
    long executionTime,
    String responseParams
) {}
```

`ConfirmSettlementResponse.java`:

```java
package com.aieducenter.payment.hsb.domain.port.response;

public record ConfirmSettlementResponse(
    boolean success,
    String returnCode,
    String returnMsg,
    long executionTime,
    String requestParams,
    String responseParams
) {}
```

- [ ] **Step 6: 编译验证**

Run: `mvn compile -q`
Expected: BUILD SUCCESS

- [ ] **Step 7: Commit**

```bash
git add src/main/java/com/aieducenter/payment/hsb/domain/
git commit -m "feat: add HSB domain aggregates, repositories, port and response records"
```

---


## Task 7: Application DTOs + Mapper + HsbConfig

**Files:**
- Create: `src/main/java/com/aieducenter/payment/hsb/application/dto/command/CreateHsbPaymentCommand.java`
- Create: `src/main/java/com/aieducenter/payment/hsb/application/dto/command/CreateHsbRefundCommand.java`
- Create: `src/main/java/com/aieducenter/payment/hsb/application/dto/command/ConfirmHsbSettlementCommand.java`
- Create: `src/main/java/com/aieducenter/payment/hsb/application/dto/callback/HsbPaymentCallbackParam.java`
- Create: `src/main/java/com/aieducenter/payment/hsb/application/dto/callback/HsbRefundCallbackParam.java`
- Create: `src/main/java/com/aieducenter/payment/hsb/application/dto/response/HsbPaymentOrderResponse.java`
- Create: `src/main/java/com/aieducenter/payment/hsb/application/dto/response/HsbRefundOrderResponse.java`
- Create: `src/main/java/com/aieducenter/payment/hsb/application/mapper/HsbPaymentOrderMapper.java`
- Create: `src/main/java/com/aieducenter/payment/hsb/infrastructure/HsbConfig.java`

- [ ] **Step 1: 创建 Command DTOs**

`CreateHsbPaymentCommand.java`:

```java
package com.aieducenter.payment.hsb.application.dto.command;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

import java.util.List;

@Builder
public record CreateHsbPaymentCommand(
    @NotBlank String businessMainOrderNo,
    String businessName,
    @NotBlank @Builder.Default String paymentMethod = "03",
    @NotBlank @Builder.Default String orderType = "04",
    String currency,
    @NotNull @Positive Long totalAmount,
    @NotNull @Positive Long txnTotalAmount,
    String feeBearerId,
    Long expiredSeconds,
    String notifyUrl,
    String attach,
    @NotEmpty @Valid List<HsbSubOrderCommand> subOrders
) {
    @Builder
    public record HsbSubOrderCommand(
        @NotBlank String businessSubOrderNo,
        @NotBlank String mktMrchId,
        @NotNull @Positive Long orderAmount,
        @NotNull @Positive Long txnAmount
    ) {}
}
```

`CreateHsbRefundCommand.java`:

```java
package com.aieducenter.payment.hsb.application.dto.command;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

import java.util.List;

@Builder
public record CreateHsbRefundCommand(
    @NotBlank String paymentOrderNo,
    @NotBlank String businessMainOrderNo,
    String refundType,
    @NotNull @Positive Long refundAmount,
    String reason,
    String notifyUrl,
    String attach,
    @Valid List<HsbRefundSubOrderCommand> subOrders
) {
    @Builder
    public record HsbRefundSubOrderCommand(
        @NotBlank String businessSubOrderNo,
        @NotNull @Positive Long refundAmount
    ) {}
}
```

`ConfirmHsbSettlementCommand.java`:

```java
package com.aieducenter.payment.hsb.application.dto.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

import java.util.List;

@Builder
public record ConfirmHsbSettlementCommand(
    @NotBlank String paymentOrderNo,
    @NotEmpty List<String> businessSubOrderNos
) {}
```

- [ ] **Step 2: 创建 Callback DTOs**

`HsbPaymentCallbackParam.java`:

```java
package com.aieducenter.payment.hsb.application.dto.callback;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

/**
 * 建行支付结果通知参数（建行字段名为 Pascal_Case 下划线风格）
 */
@Data
public class HsbPaymentCallbackParam {
    @JSONField(name = "Main_Ordr_No")
    private String mainOrdrNo;

    @JSONField(name = "Py_Trn_No")
    private String pyTrnNo;

    @JSONField(name = "Ordr_Amt")
    private String ordrAmt;

    @JSONField(name = "Txnamt")
    private String txnamt;

    @JSONField(name = "Pay_Time")
    private String payTime;

    @JSONField(name = "Ordr_Stcd")
    private String ordrStcd;

    @JSONField(name = "Sign_Inf")
    private String signInf;

    @JSONField(name = "Prim_Ordr_No")
    private String primOrdrNo;

    // 子订单信息（建行返回的子订单列表）
    // 具体结构根据实际回调数据解析

    public boolean isPaymentSuccess() {
        return "2".equals(ordrStcd);
    }

    public boolean isPaymentFailed() {
        return "3".equals(ordrStcd);
    }

    public boolean isPaymentExpired() {
        return "4".equals(ordrStcd);
    }
}
```

`HsbRefundCallbackParam.java`:

```java
package com.aieducenter.payment.hsb.application.dto.callback;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

/**
 * 建行退款结果通知参数
 */
@Data
public class HsbRefundCallbackParam {
    @JSONField(name = "Ittparty_Tms")
    private String ittpartyTms;

    @JSONField(name = "Ittparty_Jrnl_No")
    private String ittpartyJrnlNo;

    @JSONField(name = "Py_Trn_No")
    private String pyTrnNo;

    @JSONField(name = "Cust_Rfnd_Trcno")
    private String custRfndTrcno;

    @JSONField(name = "Super_Refund_No")
    private String superRefundNo;

    @JSONField(name = "Rfnd_Amt")
    private String rfndAmt;

    @JSONField(name = "Refund_Rsp_St")
    private String refundRspSt;

    @JSONField(name = "Refund_Rsp_Inf")
    private String refundRspInf;

    @JSONField(name = "Refund_Funds_Source")
    private String refundFundsSource;

    @JSONField(name = "Sign_Inf")
    private String signInf;

    public boolean isRefundSuccess() {
        return "00".equals(refundRspSt);
    }
}
```

- [ ] **Step 3: 创建 Response DTOs**

`HsbPaymentOrderResponse.java`:

```java
package com.aieducenter.payment.hsb.application.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record HsbPaymentOrderResponse(
    String paymentOrderNo,
    String businessMainOrderNo,
    String businessSystemName,
    String businessName,
    String status,
    String mktId,
    String paymentMethod,
    String orderType,
    String currency,
    Long totalAmount,
    Long txnTotalAmount,
    String feeBearerId,
    String payUrl,
    String payQrCode,
    String primOrderNo,
    String pyTrnNo,
    Long actualAmount,
    LocalDateTime paidAt,
    LocalDateTime failedAt,
    LocalDateTime expiredAt,
    String notifyUrl,
    String attach,
    List<HsbSubOrderResponse> subOrders
) {
    @Builder
    public record HsbSubOrderResponse(
        String businessSubOrderNo,
        String mktMrchId,
        Long orderAmount,
        Long txnAmount,
        String subOrderId,
        Boolean confirmed,
        LocalDateTime confirmedAt
    ) {}
}
```

`HsbRefundOrderResponse.java`:

```java
package com.aieducenter.payment.hsb.application.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record HsbRefundOrderResponse(
    String refundOrderNo,
    String paymentOrderNo,
    String businessMainOrderNo,
    String businessSystemName,
    String businessName,
    String refundType,
    String status,
    Long refundAmount,
    String reason,
    String superRefundNo,
    LocalDateTime refundedAt,
    LocalDateTime failedAt,
    String notifyUrl,
    String attach,
    List<HsbRefundSubOrderResponse> subOrders
) {
    @Builder
    public record HsbRefundSubOrderResponse(
        String businessSubOrderNo,
        String subOrderId,
        Long refundAmount
    ) {}
}
```

- [ ] **Step 4: 创建 HsbPaymentOrderMapper**

```java
package com.aieducenter.payment.hsb.application.mapper;

import com.aieducenter.payment.hsb.application.dto.response.HsbPaymentOrderResponse;
import com.aieducenter.payment.hsb.application.dto.response.HsbRefundOrderResponse;
import com.aieducenter.payment.hsb.domain.aggregate.HsbPaymentOrder;
import com.aieducenter.payment.hsb.domain.aggregate.HsbRefundOrder;
import com.aieducenter.payment.hsb.domain.aggregate.HsbRefundSubOrder;
import com.aieducenter.payment.hsb.domain.aggregate.HsbSubOrder;

public class HsbPaymentOrderMapper {

    public static HsbPaymentOrderResponse convert(HsbPaymentOrder order) {
        return HsbPaymentOrderResponse.builder()
            .paymentOrderNo(order.getPaymentOrderNo())
            .businessMainOrderNo(order.getBusinessMainOrderNo())
            .businessSystemName(order.getBusinessSystemName())
            .businessName(order.getBusinessName())
            .status(order.getStatus().getName())
            .mktId(order.getMktId())
            .paymentMethod(order.getPaymentMethod())
            .orderType(order.getOrderType())
            .currency(order.getCurrency())
            .totalAmount(order.getTotalAmount())
            .txnTotalAmount(order.getTxnTotalAmount())
            .feeBearerId(order.getFeeBearerId())
            .payUrl(order.getPayUrl())
            .payQrCode(order.getPayQrCode())
            .primOrderNo(order.getPrimOrderNo())
            .pyTrnNo(order.getPyTrnNo())
            .actualAmount(order.getActualAmount())
            .paidAt(order.getPaidAt())
            .failedAt(order.getFailedAt())
            .expiredAt(order.getExpiredAt())
            .notifyUrl(order.getNotifyUrl())
            .attach(order.getAttach())
            .subOrders(order.getSubOrders().stream()
                .map(HsbPaymentOrderMapper::convertSubOrder)
                .toList())
            .build();
    }

    private static HsbPaymentOrderResponse.HsbSubOrderResponse convertSubOrder(HsbSubOrder sub) {
        return HsbPaymentOrderResponse.HsbSubOrderResponse.builder()
            .businessSubOrderNo(sub.getBusinessSubOrderNo())
            .mktMrchId(sub.getMktMrchId())
            .orderAmount(sub.getOrderAmount())
            .txnAmount(sub.getTxnAmount())
            .subOrderId(sub.getSubOrderId())
            .confirmed(sub.getConfirmed())
            .confirmedAt(sub.getConfirmedAt())
            .build();
    }

    public static HsbRefundOrderResponse convert(HsbRefundOrder order) {
        return HsbRefundOrderResponse.builder()
            .refundOrderNo(order.getRefundOrderNo())
            .paymentOrderNo(order.getPaymentOrderNo())
            .businessMainOrderNo(order.getBusinessMainOrderNo())
            .businessSystemName(order.getBusinessSystemName())
            .businessName(order.getBusinessName())
            .refundType(order.getRefundType())
            .status(order.getStatus().getName())
            .refundAmount(order.getRefundAmount())
            .reason(order.getReason())
            .superRefundNo(order.getSuperRefundNo())
            .refundedAt(order.getRefundedAt())
            .failedAt(order.getFailedAt())
            .notifyUrl(order.getNotifyUrl())
            .attach(order.getAttach())
            .subOrders(order.getSubOrders().stream()
                .map(HsbPaymentOrderMapper::convertRefundSubOrder)
                .toList())
            .build();
    }

    private static HsbRefundOrderResponse.HsbRefundSubOrderResponse convertRefundSubOrder(HsbRefundSubOrder sub) {
        return HsbRefundOrderResponse.HsbRefundSubOrderResponse.builder()
            .businessSubOrderNo(sub.getBusinessSubOrderNo())
            .subOrderId(sub.getSubOrderId())
            .refundAmount(sub.getRefundAmount())
            .build();
    }
}
```

- [ ] **Step 5: 创建 HsbConfig**

```java
package com.aieducenter.payment.hsb.infrastructure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "hsb")
public class HsbConfig {
    private String baseUrl = "http://marketpaypl4.dev.jh:8035/online/direct/";
    private String mktId;
    private String privateKey;
    private String platformPublicKey;
    private String initiatorSystemId = "00000";
    private String initiatorChannelCode = "0000000000000000000000000";
    private Version version = new Version();

    @Data
    public static class Version {
        private String placeOrder = "5";
        private String queryOrder = "5";
        private String refundOrder = "3";
        private String queryRefund = "4";
        private String confirmSettlement = "4";
    }

    public String getPlaceOrderUrl() { return baseUrl + "gatherPlaceorder"; }
    public String getQueryOrderUrl() { return baseUrl + "gatherEnquireOrder"; }
    public String getRefundOrderUrl() { return baseUrl + "refundOrder"; }
    public String getQueryRefundUrl() { return baseUrl + "enquireRefundOrder"; }
    public String getConfirmSettlementUrl() { return baseUrl + "mergeNoticeArrival"; }
}
```

- [ ] **Step 6: 编译验证**

Run: `mvn compile -q`
Expected: BUILD SUCCESS

- [ ] **Step 7: Commit**

```bash
git add src/main/java/com/aieducenter/payment/hsb/application/ src/main/java/com/aieducenter/payment/hsb/infrastructure/HsbConfig.java
git commit -m "feat: add HSB application DTOs, mapper and config"
```

---


## Task 8: 签名工具 + HTTP 客户端

**Files:**
- Create: `src/main/java/com/aieducenter/payment/hsb/infrastructure/HsbSplicingUtil.java`
- Create: `src/main/java/com/aieducenter/payment/hsb/infrastructure/HsbSignUtil.java`
- Create: `src/main/java/com/aieducenter/payment/hsb/infrastructure/HsbHttpClient.java`

- [ ] **Step 1: 创建 HsbSplicingUtil（签名字符串拼接）**

签名字符串拼接规则：TreeMap 按 key 字典排序，排除 SIGN_INF/Svc_Rsp_St/Svc_Rsp_Cd/Rsp_Inf，空值不参与签名。

```java
package com.aieducenter.payment.hsb.infrastructure;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;

import java.util.ArrayList;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 建行惠市宝签名字符串拼接工具
 *
 * <p>规则：TreeMap 按 key 字典排序，排除 Sign_Inf/Svc_Rsp_St/Svc_Rsp_Cd/Rsp_Inf，空值不参与签名</p>
 * <p>通知接口验签规则：仅排除 Sign_Inf，空值不参与签名</p>
 */
public class HsbSplicingUtil {

    /**
     * 创建签名字符串（API 调用：排除 Sign_Inf/Svc_Rsp_St/Svc_Rsp_Cd/Rsp_Inf）
     */
    public static String createSign(String json) {
        return createSign(json, false);
    }

    /**
     * 创建签名字符串
     * @param json JSON 字符串
     * @param isNotification 是否为通知接口（通知接口仅排除 Sign_Inf）
     */
    public static String createSign(String json, boolean isNotification) {
        String signStr = splicingSign(json, isNotification);
        if (signStr.endsWith("&")) {
            signStr = signStr.substring(0, signStr.length() - 1);
        }
        return signStr;
    }

    private static String splicingSign(String json, boolean isNotification) {
        JSONObject jsonObject = JSONObject.parseObject(json);
        SortedMap<String, Object> sortedMap = new TreeMap<>();

        for (String key : jsonObject.keySet()) {
            Object value = jsonObject.get(key);

            if (shouldExclude(key, isNotification)) {
                continue;
            }

            if (value instanceof JSONArray jsonArray) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < jsonArray.size(); i++) {
                    sb.append(splicingSign(jsonArray.getJSONObject(i).toJSONString(), isNotification));
                }
                sortedMap.put(key, sb.toString());
            } else if (value instanceof JSONObject jsonObj) {
                String nestedSign = splicingSign(jsonObj.toJSONString(), isNotification);
                sortedMap.put(key, nestedSign);
            } else {
                String strValue = value == null ? "" : value.toString();
                if (!strValue.isBlank()) {
                    sortedMap.put(key, strValue);
                }
            }
        }

        StringBuilder result = new StringBuilder();
        for (SortedMap.Entry<String, Object> entry : sortedMap.entrySet()) {
            Object value = entry.getValue();
            if (value instanceof ArrayList<?> list) {
                for (Object v : list) {
                    result.append(v);
                }
            } else {
                result.append(entry.getKey()).append("=").append(value).append("&");
            }
        }
        return result.toString();
    }

    private static boolean shouldExclude(String key, boolean isNotification) {
        String upperKey = key.toUpperCase();
        if ("SIGN_INF".equals(upperKey)) return true;
        if (!isNotification) {
            return "SVC_RSP_ST".equals(upperKey) || "SVC_RSP_CD".equals(upperKey) || "RSP_INF".equals(upperKey);
        }
        return false;
    }
}
```

- [ ] **Step 2: 创建 HsbSignUtil（RSA 签名/验签封装）**

```java
package com.aieducenter.payment.hsb.infrastructure;

import com.ccb.mktpay.sign.RSASignUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 建行惠市宝签名工具
 *
 * <p>封装 mktpaysign.jar 的 RSASignUtil，提供签名和验签功能</p>
 */
public class HsbSignUtil {

    private static final Logger log = LoggerFactory.getLogger(HsbSignUtil.class);

    /**
     * 用私钥对签名字符串进行签名
     *
     * @param privateKey 私钥
     * @param signStr    签名字符串
     * @return 签名结果
     */
    public static String sign(String privateKey, String signStr) {
        try {
            return RSASignUtil.sign(privateKey, signStr);
        } catch (Exception e) {
            log.error("HSB sign failed: {}", e.getMessage(), e);
            throw new RuntimeException("建行签名失败: " + e.getMessage(), e);
        }
    }

    /**
     * 用平台公钥验证签名
     *
     * @param platformPublicKey 平台公钥
     * @param signStr           签名字符串
     * @param signInf           签名值
     * @return 验签是否通过
     */
    public static boolean verifySign(String platformPublicKey, String signStr, String signInf) {
        try {
            return RSASignUtil.verifySign(platformPublicKey, signStr, signInf);
        } catch (Exception e) {
            log.error("HSB verify sign failed: {}", e.getMessage(), e);
            return false;
        }
    }
}
```

- [ ] **Step 3: 创建 HsbHttpClient（HTTP 客户端）**

```java
package com.aieducenter.payment.hsb.infrastructure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

/**
 * 建行惠市宝 HTTP 客户端
 *
 * <p>JSON POST 调用建行接口</p>
 */
@Component
public class HsbHttpClient {

    private static final Logger log = LoggerFactory.getLogger(HsbHttpClient.class);

    private final HttpClient httpClient;

    public HsbHttpClient() {
        this.httpClient = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(10))
            .build();
    }

    /**
     * 发送 JSON POST 请求
     *
     * @param url  请求地址
     * @param json JSON 字符串
     * @return 响应体 JSON 字符串
     */
    public String postJson(String url, String json) {
        log.info("HSB HTTP POST: url={}", url);
        log.debug("HSB HTTP POST body: {}", json);

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .timeout(Duration.ofSeconds(30))
            .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
            .POST(HttpRequest.BodyPublishers.ofString(json))
            .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            log.info("HSB HTTP response: status={}", response.statusCode());
            log.debug("HSB HTTP response body: {}", response.body());

            if (response.statusCode() != 200) {
                throw new RuntimeException("建行接口返回 HTTP " + response.statusCode() + ": " + response.body());
            }
            return response.body();
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("建行接口调用失败: " + e.getMessage(), e);
        }
    }
}
```

- [ ] **Step 4: 编译验证**

Run: `mvn compile -q`
Expected: BUILD SUCCESS

- [ ] **Step 5: Commit**

```bash
git add src/main/java/com/aieducenter/payment/hsb/infrastructure/HsbSplicingUtil.java src/main/java/com/aieducenter/payment/hsb/infrastructure/HsbSignUtil.java src/main/java/com/aieducenter/payment/hsb/infrastructure/HsbHttpClient.java
git commit -m "feat: add HSB signing utilities and HTTP client"
```

---


## Task 9: HsbPaymentGatewayAdapter（建行网关适配器）

**Files:**
- Create: `src/main/java/com/aieducenter/payment/hsb/infrastructure/HsbPaymentGatewayAdapter.java`

这是最核心的基础设施文件，封装所有建行 API 调用。

- [ ] **Step 1: 创建 HsbPaymentGatewayAdapter**

```java
package com.aieducenter.payment.hsb.infrastructure;

import com.aieducenter.payment.hsb.domain.aggregate.HsbPaymentOrder;
import com.aieducenter.payment.hsb.domain.aggregate.HsbRefundOrder;
import com.aieducenter.payment.hsb.domain.aggregate.HsbRefundSubOrder;
import com.aieducenter.payment.hsb.domain.aggregate.HsbSubOrder;
import com.aieducenter.payment.hsb.domain.enums.HsbPaymentStatus;
import com.aieducenter.payment.hsb.domain.enums.HsbRefundStatus;
import com.aieducenter.payment.hsb.domain.port.HsbPaymentGatewayPort;
import com.aieducenter.payment.hsb.domain.port.response.*;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.cartisan.core.stereotype.Adapter;
import com.cartisan.core.stereotype.PortType;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
@Adapter(PortType.CLIENT)
@RequiredArgsConstructor
public class HsbPaymentGatewayAdapter implements HsbPaymentGatewayPort {

    private static final Logger log = LoggerFactory.getLogger(HsbPaymentGatewayAdapter.class);
    private static final DateTimeFormatter TIMESTAMP_FORMAT = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");

    private final HsbConfig hsbConfig;
    private final HsbHttpClient hsbHttpClient;

    @Override
    public CreateHsbPaymentResponse createPayment(HsbPaymentOrder order, List<HsbSubOrder> subOrders) {
        // 1. 组装请求 JSON
        JSONObject json = new JSONObject(true); // ordered
        json.put("Ittparty_Stm_Id", hsbConfig.getInitiatorSystemId());
        json.put("Py_Chnl_Cd", hsbConfig.getInitiatorChannelCode());
        json.put("Ittparty_Tms", LocalDateTime.now().format(TIMESTAMP_FORMAT));
        json.put("Ittparty_Jrnl_No", order.getPaymentOrderNo());
        json.put("Mkt_Id", hsbConfig.getMktId());
        json.put("Main_Ordr_No", order.getBusinessMainOrderNo());
        json.put("Pymd_Cd", order.getPaymentMethod());
        json.put("Py_Ordr_Tpcd", order.getOrderType());
        json.put("Ccy", order.getCurrency());
        json.put("Ordr_Tamt", fenToYuan(order.getTotalAmount()));
        json.put("Txn_Tamt", fenToYuan(order.getTxnTotalAmount()));
        if (order.getFeeBearerId() != null) {
            json.put("Hdcg_Brs_Id", order.getFeeBearerId());
        }
        json.put("Vno", hsbConfig.getVersion().getPlaceOrder());

        // 子订单列表
        JSONArray orderList = new JSONArray();
        for (HsbSubOrder sub : subOrders) {
            JSONObject subJson = new JSONObject();
            subJson.put("Mkt_Mrch_Id", sub.getMktMrchId());
            subJson.put("Cmdty_Ordr_No", sub.getBusinessSubOrderNo());
            subJson.put("Ordr_Amt", fenToYuan(sub.getOrderAmount()));
            subJson.put("Txnamt", fenToYuan(sub.getTxnAmount()));
            orderList.add(subJson);
        }
        json.put("Orderlist", orderList);

        // 2. 签名
        String requestParams = json.toJSONString();
        String signStr = HsbSplicingUtil.createSign(requestParams);
        String signInf = HsbSignUtil.sign(hsbConfig.getPrivateKey(), signStr);
        json.put("Sign_Inf", signInf);

        // 3. 发送请求
        long startTime = System.currentTimeMillis();
        String responseBody;
        try {
            responseBody = hsbHttpClient.postJson(hsbConfig.getPlaceOrderUrl(), json.toJSONString());
        } catch (Exception e) {
            long executionTime = System.currentTimeMillis() - startTime;
            log.error("HSB createPayment failed", e);
            return new CreateHsbPaymentResponse(false, "SYSTEM_ERROR", e.getMessage(),
                null, null, null, executionTime, requestParams, null);
        }
        long executionTime = System.currentTimeMillis() - startTime;

        // 4. 解析响应
        JSONObject response = JSONObject.parseObject(responseBody);
        String returnCode = response.getString("Svc_Rsp_St");
        String returnMsg = response.getString("Svc_Rsp_Cd");

        boolean success = "00".equals(returnCode);
        String payUrl = success ? response.getString("Cshdk_Url") : null;
        String payQrCode = success ? response.getString("Pay_Qr_Code") : null;
        String primOrderNo = success ? response.getString("Prim_Ordr_No") : null;

        return new CreateHsbPaymentResponse(success, returnCode, returnMsg,
            payUrl, payQrCode, primOrderNo, executionTime, requestParams, responseBody);
    }

    @Override
    public QueryHsbPaymentResponse queryPayment(String mktId, String mainOrderNo, String pyTrnNo) {
        JSONObject json = new JSONObject(true);
        json.put("Ittparty_Stm_Id", hsbConfig.getInitiatorSystemId());
        json.put("Py_Chnl_Cd", hsbConfig.getInitiatorChannelCode());
        json.put("Ittparty_Tms", LocalDateTime.now().format(TIMESTAMP_FORMAT));
        json.put("Ittparty_Jrnl_No", String.valueOf(System.currentTimeMillis()));
        json.put("Mkt_Id", mktId);
        json.put("Main_Ordr_No", mainOrderNo);
        if (pyTrnNo != null) {
            json.put("Py_Trn_No", pyTrnNo);
        }
        json.put("Vno", hsbConfig.getVersion().getQueryOrder());

        String requestParams = json.toJSONString();
        String signStr = HsbSplicingUtil.createSign(requestParams);
        String signInf = HsbSignUtil.sign(hsbConfig.getPrivateKey(), signStr);
        json.put("Sign_Inf", signInf);

        long startTime = System.currentTimeMillis();
        String responseBody;
        try {
            responseBody = hsbHttpClient.postJson(hsbConfig.getQueryOrderUrl(), json.toJSONString());
        } catch (Exception e) {
            long executionTime = System.currentTimeMillis() - startTime;
            return new QueryHsbPaymentResponse(false, "SYSTEM_ERROR", e.getMessage(),
                null, null, null, executionTime, null);
        }
        long executionTime = System.currentTimeMillis() - startTime;

        JSONObject response = JSONObject.parseObject(responseBody);
        String returnCode = response.getString("Svc_Rsp_St");
        boolean success = "00".equals(returnCode);

        HsbPaymentStatus paymentStatus = null;
        String pyTrnNoResult = null;
        Long actualAmount = null;

        if (success) {
            String ordrStcd = response.getString("Ordr_Stcd");
            paymentStatus = mapPaymentStatus(ordrStcd);
            pyTrnNoResult = response.getString("Py_Trn_No");
            String ordrAmt = response.getString("Ordr_Amt");
            if (ordrAmt != null) {
                actualAmount = yuanToFen(ordrAmt);
            }
        }

        return new QueryHsbPaymentResponse(success, returnCode, response.getString("Svc_Rsp_Cd"),
            paymentStatus, pyTrnNoResult, actualAmount, executionTime, responseBody);
    }

    @Override
    public CreateHsbRefundResponse createRefund(HsbRefundOrder order, String pyTrnNo, List<HsbRefundSubOrder> subOrders) {
        JSONObject json = new JSONObject(true);
        json.put("Ittparty_Stm_Id", hsbConfig.getInitiatorSystemId());
        json.put("Py_Chnl_Cd", hsbConfig.getInitiatorChannelCode());
        json.put("Ittparty_Tms", LocalDateTime.now().format(TIMESTAMP_FORMAT));
        json.put("Ittparty_Jrnl_No", order.getRefundOrderNo());
        json.put("Mkt_Id", hsbConfig.getMktId());
        json.put("Py_Trn_No", pyTrnNo);
        json.put("Rfnd_Type", "ASYNC".equals(order.getRefundType()) ? "01" : "00");
        json.put("Rfnd_Amt", fenToYuan(order.getRefundAmount()));
        json.put("Vno", hsbConfig.getVersion().getRefundOrder());

        // 退款子订单
        if (subOrders != null && !subOrders.isEmpty()) {
            JSONArray subList = new JSONArray();
            for (HsbRefundSubOrder sub : subOrders) {
                JSONObject subJson = new JSONObject();
                subJson.put("Sub_Ordr_Id", sub.getSubOrderId());
                subJson.put("Rfnd_Amt", fenToYuan(sub.getRefundAmount()));
                subList.add(subJson);
            }
            json.put("Sub_Ordr_List", subList);
        }

        String requestParams = json.toJSONString();
        String signStr = HsbSplicingUtil.createSign(requestParams);
        String signInf = HsbSignUtil.sign(hsbConfig.getPrivateKey(), signStr);
        json.put("Sign_Inf", signInf);

        long startTime = System.currentTimeMillis();
        String responseBody;
        try {
            responseBody = hsbHttpClient.postJson(hsbConfig.getRefundOrderUrl(), json.toJSONString());
        } catch (Exception e) {
            long executionTime = System.currentTimeMillis() - startTime;
            return new CreateHsbRefundResponse(false, "SYSTEM_ERROR", e.getMessage(),
                null, null, executionTime, requestParams, null);
        }
        long executionTime = System.currentTimeMillis() - startTime;

        JSONObject response = JSONObject.parseObject(responseBody);
        String returnCode = response.getString("Svc_Rsp_St");
        boolean success = "00".equals(returnCode);

        HsbRefundStatus refundStatus = null;
        String superRefundNo = null;
        if (success) {
            String rspSt = response.getString("Refund_Rsp_St");
            refundStatus = mapRefundStatus(rspSt);
            superRefundNo = response.getString("Super_Refund_No");
        }

        return new CreateHsbRefundResponse(success, returnCode, response.getString("Svc_Rsp_Cd"),
            refundStatus, superRefundNo, executionTime, requestParams, responseBody);
    }

    @Override
    public QueryHsbRefundResponse queryRefund(String mktId, String custRfndTrcno, String rfndTrcno) {
        JSONObject json = new JSONObject(true);
        json.put("Ittparty_Stm_Id", hsbConfig.getInitiatorSystemId());
        json.put("Py_Chnl_Cd", hsbConfig.getInitiatorChannelCode());
        json.put("Ittparty_Tms", LocalDateTime.now().format(TIMESTAMP_FORMAT));
        json.put("Ittparty_Jrnl_No", String.valueOf(System.currentTimeMillis()));
        json.put("Mkt_Id", mktId);
        json.put("Cust_Rfnd_Trcno", custRfndTrcno);
        if (rfndTrcno != null) {
            json.put("Rfnd_Trcno", rfndTrcno);
        }
        json.put("Vno", hsbConfig.getVersion().getQueryRefund());

        String requestParams = json.toJSONString();
        String signStr = HsbSplicingUtil.createSign(requestParams);
        String signInf = HsbSignUtil.sign(hsbConfig.getPrivateKey(), signStr);
        json.put("Sign_Inf", signInf);

        long startTime = System.currentTimeMillis();
        String responseBody;
        try {
            responseBody = hsbHttpClient.postJson(hsbConfig.getQueryRefundUrl(), json.toJSONString());
        } catch (Exception e) {
            long executionTime = System.currentTimeMillis() - startTime;
            return new QueryHsbRefundResponse(false, "SYSTEM_ERROR", e.getMessage(),
                null, null, executionTime, null);
        }
        long executionTime = System.currentTimeMillis() - startTime;

        JSONObject response = JSONObject.parseObject(responseBody);
        String returnCode = response.getString("Svc_Rsp_St");
        boolean success = "00".equals(returnCode);

        HsbRefundStatus refundStatus = null;
        String superRefundNo = null;
        if (success) {
            String rspSt = response.getString("Refund_Rsp_St");
            refundStatus = mapRefundStatus(rspSt);
            superRefundNo = response.getString("Super_Refund_No");
        }

        return new QueryHsbRefundResponse(success, returnCode, response.getString("Svc_Rsp_Cd"),
            refundStatus, superRefundNo, executionTime, responseBody);
    }

    @Override
    public ConfirmSettlementResponse confirmSettlement(String mktId, String primOrderNo, String subOrderId) {
        JSONObject json = new JSONObject(true);
        json.put("Ittparty_Stm_Id", hsbConfig.getInitiatorSystemId());
        json.put("Py_Chnl_Cd", hsbConfig.getInitiatorChannelCode());
        json.put("Ittparty_Tms", LocalDateTime.now().format(TIMESTAMP_FORMAT));
        json.put("Ittparty_Jrnl_No", String.valueOf(System.currentTimeMillis()));
        json.put("Mkt_Id", mktId);
        json.put("Prim_Ordr_No", primOrderNo);
        if (subOrderId != null) {
            json.put("Sub_Ordr_Id", subOrderId);
        }
        json.put("Vno", hsbConfig.getVersion().getConfirmSettlement());

        String requestParams = json.toJSONString();
        String signStr = HsbSplicingUtil.createSign(requestParams);
        String signInf = HsbSignUtil.sign(hsbConfig.getPrivateKey(), signStr);
        json.put("Sign_Inf", signInf);

        long startTime = System.currentTimeMillis();
        String responseBody;
        try {
            responseBody = hsbHttpClient.postJson(hsbConfig.getConfirmSettlementUrl(), json.toJSONString());
        } catch (Exception e) {
            long executionTime = System.currentTimeMillis() - startTime;
            return new ConfirmSettlementResponse(false, "SYSTEM_ERROR", e.getMessage(),
                executionTime, requestParams, null);
        }
        long executionTime = System.currentTimeMillis() - startTime;

        JSONObject response = JSONObject.parseObject(responseBody);
        String returnCode = response.getString("Svc_Rsp_St");
        boolean success = "00".equals(returnCode);

        return new ConfirmSettlementResponse(success, returnCode, response.getString("Svc_Rsp_Cd"),
            executionTime, requestParams, responseBody);
    }

    // ========== 金额转换 ==========

    private String fenToYuan(Long fen) {
        if (fen == null) return "0.00";
        return BigDecimal.valueOf(fen).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP).toPlainString();
    }

    private Long yuanToFen(String yuan) {
        if (yuan == null || yuan.isBlank()) return null;
        return new BigDecimal(yuan).multiply(BigDecimal.valueOf(100)).longValue();
    }

    // ========== 状态映射 ==========

    private HsbPaymentStatus mapPaymentStatus(String ordrStcd) {
        if ("2".equals(ordrStcd)) return HsbPaymentStatus.PAID;
        if ("3".equals(ordrStcd)) return HsbPaymentStatus.FAILED;
        if ("4".equals(ordrStcd)) return HsbPaymentStatus.EXPIRED;
        return HsbPaymentStatus.PENDING;
    }

    private HsbRefundStatus mapRefundStatus(String rspSt) {
        if ("00".equals(rspSt)) return HsbRefundStatus.SUCCESS;
        if ("01".equals(rspSt)) return HsbRefundStatus.FAILED;
        return HsbRefundStatus.REFUNDING;
    }
}
```

- [ ] **Step 2: 编译验证**

Run: `mvn compile -q`
Expected: BUILD SUCCESS

- [ ] **Step 3: Commit**

```bash
git add src/main/java/com/aieducenter/payment/hsb/infrastructure/HsbPaymentGatewayAdapter.java
git commit -m "feat: add HSB payment gateway adapter"
```

---


## Task 10: HsbPaymentAppService（支付应用服务）

**Files:**
- Create: `src/main/java/com/aieducenter/payment/hsb/application/HsbPaymentAppService.java`

- [ ] **Step 1: 创建 HsbPaymentAppService**

```java
package com.aieducenter.payment.hsb.application;

import com.aieducenter.payment.hsb.application.dto.command.CreateHsbPaymentCommand;
import com.aieducenter.payment.hsb.application.dto.response.HsbPaymentOrderResponse;
import com.aieducenter.payment.hsb.application.mapper.HsbPaymentOrderMapper;
import com.aieducenter.payment.hsb.domain.aggregate.HsbPaymentLog;
import com.aieducenter.payment.hsb.domain.aggregate.HsbPaymentOrder;
import com.aieducenter.payment.hsb.domain.aggregate.HsbSubOrder;
import com.aieducenter.payment.hsb.domain.enums.HsbPaymentStatus;
import com.aieducenter.payment.hsb.domain.port.HsbPaymentGatewayPort;
import com.aieducenter.payment.hsb.domain.port.response.CreateHsbPaymentResponse;
import com.aieducenter.payment.hsb.domain.port.response.QueryHsbPaymentResponse;
import com.aieducenter.payment.hsb.domain.repository.HsbPaymentLogRepository;
import com.aieducenter.payment.hsb.domain.repository.HsbPaymentOrderRepository;
import com.aieducenter.payment.hsb.infrastructure.HsbConfig;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HsbPaymentAppService {

    private static final Logger log = LoggerFactory.getLogger(HsbPaymentAppService.class);

    private final HsbPaymentOrderRepository paymentOrderRepository;
    private final HsbPaymentLogRepository paymentLogRepository;
    private final HsbPaymentGatewayPort gatewayPort;
    private final HsbConfig hsbConfig;

    /**
     * 创建支付订单
     */
    @Transactional
    public HsbPaymentOrderResponse createPayment(CreateHsbPaymentCommand command, String businessSystemName) {
        // 1. 创建子订单
        List<HsbSubOrder> subOrders = command.subOrders().stream()
            .map(sub -> new HsbSubOrder(
                command.businessMainOrderNo(),
                sub.businessSubOrderNo(),
                sub.mktMrchId(),
                sub.orderAmount(),
                sub.txnAmount()
            ))
            .toList();

        // 2. 创建主订单
        HsbPaymentOrder order = new HsbPaymentOrder(
            command.businessMainOrderNo(),
            businessSystemName,
            command.businessName(),
            hsbConfig.getMktId(),
            command.paymentMethod(),
            command.orderType(),
            command.currency(),
            command.totalAmount(),
            command.txnTotalAmount(),
            command.feeBearerId(),
            command.expiredSeconds(),
            command.notifyUrl(),
            command.attach(),
            subOrders
        );

        // 3. 保存订单
        HsbPaymentOrder saved = paymentOrderRepository.save(order);

        // 4. 调用建行网关
        CreateHsbPaymentResponse gatewayResponse = gatewayPort.createPayment(order, subOrders);

        // 5. 记录日志
        saveLog(order.getPaymentOrderNo(), null, "PAYMENT_REQUEST", "gatherPlaceorder",
            gatewayResponse.requestParams(), gatewayResponse.responseParams(),
            gatewayResponse.success(), gatewayResponse.returnCode(), gatewayResponse.returnMsg(),
            gatewayResponse.executionTime(), gatewayResponse.success() ? null : "建行网关调用失败");

        // 6. 网关失败则抛异常回滚
        if (!gatewayResponse.success()) {
            throw new RuntimeException("建行网关调用失败: " + gatewayResponse.returnMsg());
        }

        // 7. 保存建行返回的支付链接
        if (gatewayResponse.payUrl() != null || gatewayResponse.primOrderNo() != null) {
            saved.setPaymentResult(gatewayResponse.payUrl(), gatewayResponse.payQrCode(), gatewayResponse.primOrderNo());
            paymentOrderRepository.save(saved);
        }

        return HsbPaymentOrderMapper.convert(saved);
    }

    /**
     * 查询支付订单
     */
    @Transactional(readOnly = true)
    public HsbPaymentOrderResponse getPayment(String paymentOrderNo) {
        HsbPaymentOrder order = paymentOrderRepository.findByPaymentOrderNo(paymentOrderNo)
            .orElseThrow(() -> new IllegalArgumentException("惠市宝支付订单不存在"));

        if (order.getStatus() == HsbPaymentStatus.PENDING && order.getExpiredAt() != null
            && java.time.LocalDateTime.now().isAfter(order.getExpiredAt())) {
            order.markAsExpired();
            paymentOrderRepository.save(order);
        }

        return HsbPaymentOrderMapper.convert(order);
    }

    /**
     * 主动查询支付状态
     */
    @Transactional
    public HsbPaymentOrderResponse queryPaymentStatus(String paymentOrderNo) {
        HsbPaymentOrder order = paymentOrderRepository.findByPaymentOrderNo(paymentOrderNo)
            .orElseThrow(() -> new IllegalArgumentException("惠市宝支付订单不存在"));

        if (order.getStatus() != HsbPaymentStatus.PENDING) {
            return HsbPaymentOrderMapper.convert(order);
        }

        QueryHsbPaymentResponse queryResponse = gatewayPort.queryPayment(
            order.getMktId(), order.getBusinessMainOrderNo(), order.getPyTrnNo());

        saveLog(paymentOrderNo, null, "PAYMENT_QUERY", "gatherEnquireOrder",
            null, queryResponse.responseParams(),
            queryResponse.success(), queryResponse.returnCode(), queryResponse.returnMsg(),
            queryResponse.executionTime(), null);

        if (queryResponse.success() && queryResponse.paymentStatus() != null) {
            if (queryResponse.paymentStatus() == HsbPaymentStatus.PAID) {
                order.markAsPaid(queryResponse.pyTrnNo(), queryResponse.actualAmount());
                paymentOrderRepository.save(order);
            } else if (queryResponse.paymentStatus() == HsbPaymentStatus.FAILED) {
                order.markAsFailed();
                paymentOrderRepository.save(order);
            } else if (queryResponse.paymentStatus() == HsbPaymentStatus.EXPIRED) {
                order.markAsExpired();
                paymentOrderRepository.save(order);
            }
        }

        return HsbPaymentOrderMapper.convert(order);
    }

    private void saveLog(String paymentOrderNo, String refundOrderNo, String logType,
                         String bankInterface, String requestParams, String responseParams,
                         Boolean success, String returnCode, String returnMsg,
                         Long executionTime, String errorMessage) {
        try {
            paymentLogRepository.save(new HsbPaymentLog(
                paymentOrderNo, refundOrderNo, logType, bankInterface,
                null, requestParams, responseParams, 200,
                returnCode, returnMsg, executionTime, success, errorMessage
            ));
        } catch (Exception e) {
            log.warn("Failed to save HSB payment log: {}", e.getMessage());
        }
    }
}
```

- [ ] **Step 2: 编译验证**

Run: `mvn compile -q`
Expected: BUILD SUCCESS

- [ ] **Step 3: Commit**

```bash
git add src/main/java/com/aieducenter/payment/hsb/application/HsbPaymentAppService.java
git commit -m "feat: add HSB payment application service"
```

---


## Task 11: HsbCallbackAppService（回调处理服务）

**Files:**
- Create: `src/main/java/com/aieducenter/payment/hsb/application/HsbCallbackAppService.java`
- Modify: `src/main/java/com/aieducenter/payment/infrastructure/BusinessSystemNotifier.java`（添加 HSB 通知方法）

- [ ] **Step 1: 在 BusinessSystemNotifier 中添加 HSB 通知重载**

在现有 `BusinessSystemNotifier.java` 中添加：

```java
import com.aieducenter.payment.hsb.application.dto.response.HsbPaymentOrderResponse;
import com.aieducenter.payment.hsb.application.dto.response.HsbRefundOrderResponse;

    /**
     * 通知业务系统（HSB 支付结果）
     */
    public void notify(String notifyUrl, HsbPaymentOrderResponse response) {
        if (notifyUrl == null || notifyUrl.isBlank()) {
            log.debug("notifyUrl is empty, skip HSB payment notification. orderId={}", response.paymentOrderNo());
            return;
        }
        sendNotification(notifyUrl, JSON.toJSONString(response), response.paymentOrderNo());
    }

    /**
     * 通知业务系统（HSB 退款结果）
     */
    public void notify(String notifyUrl, HsbRefundOrderResponse response) {
        if (notifyUrl == null || notifyUrl.isBlank()) {
            log.debug("notifyUrl is empty, skip HSB refund notification. refundOrderNo={}", response.refundOrderNo());
            return;
        }
        sendNotification(notifyUrl, JSON.toJSONString(response), response.refundOrderNo());
    }
```

- [ ] **Step 2: 创建 HsbCallbackAppService**

```java
package com.aieducenter.payment.hsb.application;

import com.aieducenter.payment.hsb.application.dto.callback.HsbPaymentCallbackParam;
import com.aieducenter.payment.hsb.application.dto.callback.HsbRefundCallbackParam;
import com.aieducenter.payment.hsb.application.dto.response.HsbPaymentOrderResponse;
import com.aieducenter.payment.hsb.application.dto.response.HsbRefundOrderResponse;
import com.aieducenter.payment.hsb.application.mapper.HsbPaymentOrderMapper;
import com.aieducenter.payment.hsb.domain.aggregate.HsbPaymentLog;
import com.aieducenter.payment.hsb.domain.aggregate.HsbPaymentOrder;
import com.aieducenter.payment.hsb.domain.aggregate.HsbRefundOrder;
import com.aieducenter.payment.hsb.domain.enums.HsbPaymentStatus;
import com.aieducenter.payment.hsb.domain.enums.HsbRefundStatus;
import com.aieducenter.payment.hsb.domain.repository.HsbPaymentLogRepository;
import com.aieducenter.payment.hsb.domain.repository.HsbPaymentOrderRepository;
import com.aieducenter.payment.hsb.domain.repository.HsbRefundOrderRepository;
import com.aieducenter.payment.hsb.infrastructure.HsbConfig;
import com.aieducenter.payment.hsb.infrastructure.HsbSignUtil;
import com.aieducenter.payment.hsb.infrastructure.HsbSplicingUtil;
import com.aieducenter.payment.infrastructure.BusinessSystemNotifier;
import com.alibaba.fastjson2.JSON;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class HsbCallbackAppService {

    private static final Logger log = LoggerFactory.getLogger(HsbCallbackAppService.class);

    private final HsbPaymentOrderRepository paymentOrderRepository;
    private final HsbRefundOrderRepository refundOrderRepository;
    private final HsbPaymentLogRepository paymentLogRepository;
    private final HsbConfig hsbConfig;
    private final BusinessSystemNotifier businessSystemNotifier;

    /**
     * 处理支付结果回调
     *
     * @return 建行应答 JSON
     */
    @Transactional
    public String handlePaymentCallback(HsbPaymentCallbackParam param) {
        log.info("Received HSB payment callback: mainOrderNo={}, ordrStcd={}",
            param.getMainOrdrNo(), param.getOrdrStcd());

        // 1. 验签（通知接口仅排除 Sign_Inf）
        String rawJson = JSON.toJSONString(param);
        String signStr = HsbSplicingUtil.createSign(rawJson, true);
        boolean verified = HsbSignUtil.verifySign(hsbConfig.getPlatformPublicKey(), signStr, param.getSignInf());

        if (!verified) {
            log.warn("HSB payment callback signature verification failed: mainOrderNo={}", param.getMainOrdrNo());
            return buildCallbackResponse();
        }

        // 2. 根据 Main_Ordr_No 找到订单
        HsbPaymentOrder order = paymentOrderRepository
            .findByBusinessMainOrderNo(param.getMainOrdrNo()).orElse(null);

        if (order == null) {
            log.warn("HSB payment callback: order not found, mainOrderNo={}", param.getMainOrdrNo());
            return buildCallbackResponse();
        }

        // 3. 记录日志
        try {
            paymentLogRepository.save(new HsbPaymentLog(
                order.getPaymentOrderNo(), null, "PAYMENT_CALLBACK", "gatherPlaceorder",
                null, null, rawJson, 200, param.getOrdrStcd(), null,
                null, true, null
            ));
        } catch (Exception e) {
            log.warn("Failed to save HSB callback log: {}", e.getMessage());
        }

        // 4. 幂等检查
        if (order.getStatus() != HsbPaymentStatus.PENDING) {
            log.info("HSB payment callback: order already in terminal state, status={}", order.getStatus());
            return buildCallbackResponse();
        }

        // 5. 更新状态
        if (param.isPaymentSuccess()) {
            Long actualAmount = yuanToFen(param.getOrdrAmt());
            order.markAsPaid(param.getPyTrnNo(), actualAmount);
            // 如果有 primOrderNo，也保存
            if (param.getPrimOrdrNo() != null) {
                order.setPaymentResult(order.getPayUrl(), order.getPayQrCode(), param.getPrimOrdrNo());
            }
        } else if (param.isPaymentFailed()) {
            order.markAsFailed();
        } else if (param.isPaymentExpired()) {
            order.markAsExpired();
        }
        paymentOrderRepository.save(order);

        // 6. 事务提交后通知业务系统
        HsbPaymentOrder finalOrder = order;
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
            @Override
            public void afterCommit() {
                HsbPaymentOrderResponse response = HsbPaymentOrderMapper.convert(finalOrder);
                businessSystemNotifier.notify(finalOrder.getNotifyUrl(), response);
            }
        });

        return buildCallbackResponse();
    }

    /**
     * 处理退款结果回调
     *
     * @return 建行应答 JSON
     */
    @Transactional
    public String handleRefundCallback(HsbRefundCallbackParam param) {
        log.info("Received HSB refund callback: custRfndTrcno={}, refundRspSt={}",
            param.getCustRfndTrcno(), param.getRefundRspSt());

        // 1. 验签
        String rawJson = JSON.toJSONString(param);
        String signStr = HsbSplicingUtil.createSign(rawJson, true);
        boolean verified = HsbSignUtil.verifySign(hsbConfig.getPlatformPublicKey(), signStr, param.getSignInf());

        if (!verified) {
            log.warn("HSB refund callback signature verification failed: custRfndTrcno={}", param.getCustRfndTrcno());
            return buildRefundCallbackResponse(param.getIttpartyTms());
        }

        // 2. 根据 Cust_Rfnd_Trcno（即 refund_order_no）找到退款订单
        HsbRefundOrder refundOrder = refundOrderRepository
            .findByRefundOrderNo(param.getCustRfndTrcno()).orElse(null);

        if (refundOrder == null) {
            log.warn("HSB refund callback: order not found, custRfndTrcno={}", param.getCustRfndTrcno());
            return buildRefundCallbackResponse(param.getIttpartyTms());
        }

        // 3. 记录日志
        try {
            paymentLogRepository.save(new HsbPaymentLog(
                refundOrder.getPaymentOrderNo(), refundOrder.getRefundOrderNo(),
                "REFUND_CALLBACK", "refundOrder",
                null, null, rawJson, 200, param.getRefundRspSt(), param.getRefundRspInf(),
                null, true, null
            ));
        } catch (Exception e) {
            log.warn("Failed to save HSB refund callback log: {}", e.getMessage());
        }

        // 4. 幂等检查
        if (refundOrder.getStatus().isTerminal()) {
            log.info("HSB refund callback: order already in terminal state, status={}", refundOrder.getStatus());
            return buildRefundCallbackResponse(param.getIttpartyTms());
        }

        // 5. 更新状态
        if (param.isRefundSuccess()) {
            refundOrder.markAsSuccess(param.getSuperRefundNo());
        } else {
            refundOrder.markAsFailed();
        }
        refundOrderRepository.save(refundOrder);

        // 6. 事务提交后通知业务系统
        HsbRefundOrder finalOrder = refundOrder;
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
            @Override
            public void afterCommit() {
                HsbRefundOrderResponse response = HsbPaymentOrderMapper.convert(finalOrder);
                businessSystemNotifier.notify(finalOrder.getNotifyUrl(), response);
            }
        });

        return buildRefundCallbackResponse(param.getIttpartyTms());
    }

    private String buildCallbackResponse() {
        String rcvTm = java.time.LocalDateTime.now().format(
            java.time.format.DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        return "{\"Svc_Rsp_St\":\"00\",\"Rcv_Tm\":\"" + rcvTm + "\"}";
    }

    private String buildRefundCallbackResponse(String ittpartyTms) {
        String rcvTm = java.time.LocalDateTime.now().format(
            java.time.format.DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        return "{\"Svc_Rsp_St\":\"00\",\"Rcv_Tm\":\"" + rcvTm + "\",\"Ittparty_Tms\":\"" + ittpartyTms + "\"}";
    }

    private Long yuanToFen(String yuan) {
        if (yuan == null || yuan.isBlank()) return null;
        return new BigDecimal(yuan).multiply(BigDecimal.valueOf(100)).longValue();
    }
}
```

- [ ] **Step 3: 编译验证**

Run: `mvn compile -q`
Expected: BUILD SUCCESS

- [ ] **Step 4: Commit**

```bash
git add src/main/java/com/aieducenter/payment/hsb/application/HsbCallbackAppService.java src/main/java/com/aieducenter/payment/infrastructure/BusinessSystemNotifier.java
git commit -m "feat: add HSB callback application service"
```

---


## Task 12: HsbRefundAppService（退款应用服务）

**Files:**
- Create: `src/main/java/com/aieducenter/payment/hsb/application/HsbRefundAppService.java`

- [ ] **Step 1: 创建 HsbRefundAppService**

```java
package com.aieducenter.payment.hsb.application;

import com.aieducenter.payment.hsb.application.dto.command.CreateHsbRefundCommand;
import com.aieducenter.payment.hsb.application.dto.response.HsbRefundOrderResponse;
import com.aieducenter.payment.hsb.application.mapper.HsbPaymentOrderMapper;
import com.aieducenter.payment.hsb.domain.aggregate.HsbPaymentLog;
import com.aieducenter.payment.hsb.domain.aggregate.HsbPaymentOrder;
import com.aieducenter.payment.hsb.domain.aggregate.HsbRefundOrder;
import com.aieducenter.payment.hsb.domain.aggregate.HsbRefundSubOrder;
import com.aieducenter.payment.hsb.domain.aggregate.HsbSubOrder;
import com.aieducenter.payment.hsb.domain.enums.HsbPaymentStatus;
import com.aieducenter.payment.hsb.domain.enums.HsbRefundStatus;
import com.aieducenter.payment.hsb.domain.port.HsbPaymentGatewayPort;
import com.aieducenter.payment.hsb.domain.port.response.CreateHsbRefundResponse;
import com.aieducenter.payment.hsb.domain.port.response.QueryHsbRefundResponse;
import com.aieducenter.payment.hsb.domain.repository.HsbPaymentLogRepository;
import com.aieducenter.payment.hsb.domain.repository.HsbPaymentOrderRepository;
import com.aieducenter.payment.hsb.domain.repository.HsbRefundOrderRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HsbRefundAppService {

    private static final Logger log = LoggerFactory.getLogger(HsbRefundAppService.class);

    private final HsbPaymentOrderRepository paymentOrderRepository;
    private final HsbRefundOrderRepository refundOrderRepository;
    private final HsbPaymentLogRepository paymentLogRepository;
    private final HsbPaymentGatewayPort gatewayPort;

    /**
     * 创建退款订单
     */
    @Transactional
    public HsbRefundOrderResponse createRefund(CreateHsbRefundCommand command, String businessSystemName) {
        // 1. 查找原支付订单
        HsbPaymentOrder paymentOrder = paymentOrderRepository.findByPaymentOrderNo(command.paymentOrderNo())
            .orElseThrow(() -> new IllegalArgumentException("惠市宝支付订单不存在"));

        if (paymentOrder.getStatus() != HsbPaymentStatus.PAID) {
            throw new IllegalStateException("原支付订单未支付，无法退款");
        }

        // 2. 查找子订单的 subOrderId 映射
        Map<String, HsbSubOrder> subOrderMap = paymentOrder.getSubOrders().stream()
            .collect(Collectors.toMap(HsbSubOrder::getBusinessSubOrderNo, Function.identity()));

        // 3. 构建退款子订单
        List<HsbRefundSubOrder> refundSubOrders = new ArrayList<>();
        if (command.subOrders() != null) {
            for (var sub : command.subOrders()) {
                HsbSubOrder originalSub = subOrderMap.get(sub.businessSubOrderNo());
                String subOrderId = originalSub != null ? originalSub.getSubOrderId() : null;
                refundSubOrders.add(new HsbRefundSubOrder(
                    paymentOrder.getBusinessMainOrderNo(),
                    sub.businessSubOrderNo(),
                    subOrderId,
                    sub.refundAmount()
                ));
            }
        }

        // 4. 创建退款订单
        HsbRefundOrder refundOrder = new HsbRefundOrder(
            paymentOrder.getId(),
            paymentOrder.getPaymentOrderNo(),
            command.businessMainOrderNo(),
            businessSystemName,
            null,
            command.refundType(),
            command.refundAmount(),
            command.reason(),
            command.notifyUrl(),
            command.attach(),
            refundSubOrders
        );

        HsbRefundOrder saved = refundOrderRepository.save(refundOrder);

        // 5. 调用建行退款
        CreateHsbRefundResponse response = gatewayPort.createRefund(
            saved, paymentOrder.getPyTrnNo(), refundSubOrders);

        // 6. 记录日志
        try {
            paymentLogRepository.save(new HsbPaymentLog(
                paymentOrder.getPaymentOrderNo(), saved.getRefundOrderNo(),
                "REFUND_REQUEST", "refundOrder",
                null, response.requestParams(), response.responseParams(),
                200, response.returnCode(), response.returnMsg(),
                response.executionTime(), response.success(),
                response.success() ? null : "建行退款调用失败"
            ));
        } catch (Exception e) {
            log.warn("Failed to save HSB refund log: {}", e.getMessage());
        }

        // 7. 根据退款结果更新状态
        if (response.success()) {
            if (response.refundStatus() == HsbRefundStatus.SUCCESS) {
                saved.markAsSuccess(response.superRefundNo());
            } else if (response.refundStatus() == HsbRefundStatus.REFUNDING) {
                saved.markAsRefunding();
            }
        } else {
            saved.markAsFailed();
        }
        refundOrderRepository.save(saved);

        return HsbPaymentOrderMapper.convert(saved);
    }

    /**
     * 查询退款订单
     */
    @Transactional(readOnly = true)
    public HsbRefundOrderResponse getRefund(String refundOrderNo) {
        HsbRefundOrder order = refundOrderRepository.findByRefundOrderNo(refundOrderNo)
            .orElseThrow(() -> new IllegalArgumentException("惠市宝退款订单不存在"));
        return HsbPaymentOrderMapper.convert(order);
    }

    /**
     * 主动查询退款状态
     */
    @Transactional
    public HsbRefundOrderResponse queryRefundStatus(String refundOrderNo) {
        HsbRefundOrder order = refundOrderRepository.findByRefundOrderNo(refundOrderNo)
            .orElseThrow(() -> new IllegalArgumentException("惠市宝退款订单不存在"));

        if (order.getStatus().isTerminal()) {
            return HsbPaymentOrderMapper.convert(order);
        }

        // 需要 mktId — 从支付订单获取
        HsbPaymentOrder paymentOrder = paymentOrderRepository.findById(order.getPaymentOrderId())
            .orElseThrow(() -> new IllegalArgumentException("关联的支付订单不存在"));

        QueryHsbRefundResponse response = gatewayPort.queryRefund(
            paymentOrder.getMktId(), order.getRefundOrderNo(), null);

        try {
            paymentLogRepository.save(new HsbPaymentLog(
                order.getPaymentOrderNo(), order.getRefundOrderNo(),
                "REFUND_QUERY", "enquireRefundOrder",
                null, null, response.responseParams(),
                200, response.returnCode(), response.returnMsg(),
                response.executionTime(), response.success(), null
            ));
        } catch (Exception e) {
            log.warn("Failed to save HSB refund query log: {}", e.getMessage());
        }

        if (response.success() && response.refundStatus() != null) {
            if (response.refundStatus() == HsbRefundStatus.SUCCESS) {
                order.markAsSuccess(response.superRefundNo());
            } else if (response.refundStatus() == HsbRefundStatus.FAILED) {
                order.markAsFailed();
            } else {
                order.markAsRefunding();
            }
            refundOrderRepository.save(order);
        }

        return HsbPaymentOrderMapper.convert(order);
    }
}
```

- [ ] **Step 2: 编译验证**

Run: `mvn compile -q`
Expected: BUILD SUCCESS

- [ ] **Step 3: Commit**

```bash
git add src/main/java/com/aieducenter/payment/hsb/application/HsbRefundAppService.java
git commit -m "feat: add HSB refund application service"
```

---


## Task 13: HsbSettlementAppService（分账确认服务）

**Files:**
- Create: `src/main/java/com/aieducenter/payment/hsb/application/HsbSettlementAppService.java`

- [ ] **Step 1: 创建 HsbSettlementAppService**

```java
package com.aieducenter.payment.hsb.application;

import com.aieducenter.payment.hsb.application.dto.command.ConfirmHsbSettlementCommand;
import com.aieducenter.payment.hsb.domain.aggregate.HsbPaymentLog;
import com.aieducenter.payment.hsb.domain.aggregate.HsbPaymentOrder;
import com.aieducenter.payment.hsb.domain.aggregate.HsbSettlementConfirm;
import com.aieducenter.payment.hsb.domain.aggregate.HsbSubOrder;
import com.aieducenter.payment.hsb.domain.enums.HsbPaymentStatus;
import com.aieducenter.payment.hsb.domain.port.HsbPaymentGatewayPort;
import com.aieducenter.payment.hsb.domain.port.response.ConfirmSettlementResponse;
import com.aieducenter.payment.hsb.domain.repository.HsbPaymentLogRepository;
import com.aieducenter.payment.hsb.domain.repository.HsbPaymentOrderRepository;
import com.aieducenter.payment.hsb.domain.repository.HsbSettlementConfirmRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HsbSettlementAppService {

    private static final Logger log = LoggerFactory.getLogger(HsbSettlementAppService.class);

    private final HsbPaymentOrderRepository paymentOrderRepository;
    private final HsbSettlementConfirmRepository settlementConfirmRepository;
    private final HsbPaymentLogRepository paymentLogRepository;
    private final HsbPaymentGatewayPort gatewayPort;

    /**
     * 确认分账
     */
    @Transactional
    public void confirmSettlement(ConfirmHsbSettlementCommand command) {
        // 1. 查找支付订单
        HsbPaymentOrder order = paymentOrderRepository.findByPaymentOrderNo(command.paymentOrderNo())
            .orElseThrow(() -> new IllegalArgumentException("惠市宝支付订单不存在"));

        if (order.getStatus() != HsbPaymentStatus.PAID) {
            throw new IllegalStateException("支付订单未支付，无法确认分账");
        }

        if (order.getPrimOrderNo() == null) {
            throw new IllegalStateException("支付订单缺少建行主订单编号");
        }

        // 2. 根据 businessSubOrderNos 找到对应的 subOrderId
        Map<String, HsbSubOrder> subOrderMap = order.getSubOrders().stream()
            .collect(Collectors.toMap(HsbSubOrder::getBusinessSubOrderNo, Function.identity()));

        List<String> subOrderIds = new ArrayList<>();
        for (String bizSubNo : command.businessSubOrderNos()) {
            HsbSubOrder subOrder = subOrderMap.get(bizSubNo);
            if (subOrder == null) {
                throw new IllegalArgumentException("子订单不存在: " + bizSubNo);
            }
            if (subOrder.getSubOrderId() == null) {
                throw new IllegalStateException("子订单缺少建行子订单编号: " + bizSubNo);
            }
            if (Boolean.TRUE.equals(subOrder.getConfirmed())) {
                throw new IllegalStateException("子订单已确认分账: " + bizSubNo);
            }
            subOrderIds.add(subOrder.getSubOrderId());
        }

        // 3. 多个子订单用逗号分隔
        String subOrderIdParam = String.join(",", subOrderIds);

        // 4. 调用建行确认收货接口
        ConfirmSettlementResponse response = gatewayPort.confirmSettlement(
            order.getMktId(), order.getPrimOrderNo(), subOrderIdParam);

        // 5. 记录日志
        try {
            paymentLogRepository.save(new HsbPaymentLog(
                order.getPaymentOrderNo(), null, "SETTLEMENT_CONFIRM", "mergeNoticeArrival",
                null, response.requestParams(), response.responseParams(),
                200, response.returnCode(), response.returnMsg(),
                response.executionTime(), response.success(),
                response.success() ? null : "建行确认分账调用失败"
            ));
        } catch (Exception e) {
            log.warn("Failed to save HSB settlement log: {}", e.getMessage());
        }

        // 6. 落库分账确认记录
        HsbSettlementConfirm confirm = new HsbSettlementConfirm(
            order.getId(),
            order.getPaymentOrderNo(),
            order.getBusinessMainOrderNo(),
            command.businessSubOrderNos(),
            subOrderIds
        );

        if (response.success()) {
            confirm.markAsConfirmed();
            // 标记子订单为已确认
            for (String bizSubNo : command.businessSubOrderNos()) {
                HsbSubOrder subOrder = subOrderMap.get(bizSubNo);
                subOrder.markAsConfirmed();
            }
        } else {
            confirm.markAsFailed();
            throw new RuntimeException("建行确认分账失败: " + response.returnMsg());
        }

        settlementConfirmRepository.save(confirm);
    }
}
```

- [ ] **Step 2: 编译验证**

Run: `mvn compile -q`
Expected: BUILD SUCCESS

- [ ] **Step 3: Commit**

```bash
git add src/main/java/com/aieducenter/payment/hsb/application/HsbSettlementAppService.java
git commit -m "feat: add HSB settlement confirm application service"
```

---


## Task 14: Controllers（4个控制器）

**Files:**
- Create: `src/main/java/com/aieducenter/payment/hsb/endpoints/api/v1/HsbPaymentController.java`
- Create: `src/main/java/com/aieducenter/payment/hsb/endpoints/api/v1/HsbRefundController.java`
- Create: `src/main/java/com/aieducenter/payment/hsb/endpoints/api/v1/HsbSettlementController.java`
- Create: `src/main/java/com/aieducenter/payment/hsb/endpoints/api/v1/HsbCallbackController.java`

- [ ] **Step 1: 创建 HsbPaymentController**

```java
package com.aieducenter.payment.hsb.endpoints.api.v1;

import com.aieducenter.payment.hsb.application.HsbPaymentAppService;
import com.aieducenter.payment.hsb.application.dto.command.CreateHsbPaymentCommand;
import com.aieducenter.payment.hsb.application.dto.response.HsbPaymentOrderResponse;
import com.cartisan.core.context.RequestContext;
import com.cartisan.openapi.annotation.RequireSignature;
import com.cartisan.web.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/hsb/payments")
@RequiredArgsConstructor
@Validated
@Tag(name = "HSB Payment API v1", description = "惠市宝支付接口")
public class HsbPaymentController {

    private final HsbPaymentAppService paymentAppService;

    @PostMapping
    @RequireSignature
    @Operation(summary = "创建惠市宝支付订单")
    public ApiResponse<HsbPaymentOrderResponse> createPayment(
            @Valid @RequestBody CreateHsbPaymentCommand command
    ) {
        String businessSystemName = RequestContext.getCallerAppName();
        HsbPaymentOrderResponse response = paymentAppService.createPayment(command, businessSystemName);
        return ApiResponse.ok(response);
    }

    @GetMapping("/{paymentOrderNo}")
    @RequireSignature
    @Operation(summary = "查询惠市宝支付订单")
    public ApiResponse<HsbPaymentOrderResponse> getPayment(
            @PathVariable String paymentOrderNo
    ) {
        HsbPaymentOrderResponse response = paymentAppService.getPayment(paymentOrderNo);
        return ApiResponse.ok(response);
    }

    @PostMapping("/{paymentOrderNo}/query")
    @RequireSignature
    @Operation(summary = "主动查询惠市宝支付状态")
    public ApiResponse<HsbPaymentOrderResponse> queryPaymentStatus(
            @PathVariable String paymentOrderNo
    ) {
        HsbPaymentOrderResponse response = paymentAppService.queryPaymentStatus(paymentOrderNo);
        return ApiResponse.ok(response);
    }
}
```

- [ ] **Step 2: 创建 HsbRefundController**

```java
package com.aieducenter.payment.hsb.endpoints.api.v1;

import com.aieducenter.payment.hsb.application.HsbRefundAppService;
import com.aieducenter.payment.hsb.application.dto.command.CreateHsbRefundCommand;
import com.aieducenter.payment.hsb.application.dto.response.HsbRefundOrderResponse;
import com.cartisan.core.context.RequestContext;
import com.cartisan.openapi.annotation.RequireSignature;
import com.cartisan.web.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/hsb/refunds")
@RequiredArgsConstructor
@Validated
@Tag(name = "HSB Refund API v1", description = "惠市宝退款接口")
public class HsbRefundController {

    private final HsbRefundAppService refundAppService;

    @PostMapping
    @RequireSignature
    @Operation(summary = "创建惠市宝退款订单")
    public ApiResponse<HsbRefundOrderResponse> createRefund(
            @Valid @RequestBody CreateHsbRefundCommand command
    ) {
        String businessSystemName = RequestContext.getCallerAppName();
        HsbRefundOrderResponse response = refundAppService.createRefund(command, businessSystemName);
        return ApiResponse.ok(response);
    }

    @GetMapping("/{refundOrderNo}")
    @RequireSignature
    @Operation(summary = "查询惠市宝退款订单")
    public ApiResponse<HsbRefundOrderResponse> getRefund(
            @PathVariable String refundOrderNo
    ) {
        HsbRefundOrderResponse response = refundAppService.getRefund(refundOrderNo);
        return ApiResponse.ok(response);
    }

    @PostMapping("/{refundOrderNo}/query")
    @RequireSignature
    @Operation(summary = "主动查询惠市宝退款状态")
    public ApiResponse<HsbRefundOrderResponse> queryRefundStatus(
            @PathVariable String refundOrderNo
    ) {
        HsbRefundOrderResponse response = refundAppService.queryRefundStatus(refundOrderNo);
        return ApiResponse.ok(response);
    }
}
```

- [ ] **Step 3: 创建 HsbSettlementController**

```java
package com.aieducenter.payment.hsb.endpoints.api.v1;

import com.aieducenter.payment.hsb.application.HsbSettlementAppService;
import com.aieducenter.payment.hsb.application.dto.command.ConfirmHsbSettlementCommand;
import com.cartisan.openapi.annotation.RequireSignature;
import com.cartisan.web.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/hsb/settlements")
@RequiredArgsConstructor
@Validated
@Tag(name = "HSB Settlement API v1", description = "惠市宝分账确认接口")
public class HsbSettlementController {

    private final HsbSettlementAppService settlementAppService;

    @PostMapping
    @RequireSignature
    @Operation(summary = "确认惠市宝分账")
    public ApiResponse<Void> confirmSettlement(
            @Valid @RequestBody ConfirmHsbSettlementCommand command
    ) {
        settlementAppService.confirmSettlement(command);
        return ApiResponse.ok(null);
    }
}
```

- [ ] **Step 4: 创建 HsbCallbackController**

注意：回调接口不加 `@RequireSignature`（建行回调不使用我们的签名体系），验签在 AppService 中处理。

```java
package com.aieducenter.payment.hsb.endpoints.api.v1;

import com.aieducenter.payment.hsb.application.HsbCallbackAppService;
import com.aieducenter.payment.hsb.application.dto.callback.HsbPaymentCallbackParam;
import com.aieducenter.payment.hsb.application.dto.callback.HsbRefundCallbackParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/hsb/callback")
@RequiredArgsConstructor
@Tag(name = "HSB Callback API", description = "惠市宝回调接口")
public class HsbCallbackController {

    private static final Logger log = LoggerFactory.getLogger(HsbCallbackController.class);

    private final HsbCallbackAppService callbackAppService;

    @PostMapping(value = "/payment", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "接收建行惠市宝支付结果回调")
    public ResponseEntity<String> handlePaymentCallback(
            @RequestBody HsbPaymentCallbackParam param
    ) {
        log.info("Received HSB payment callback: mainOrderNo={}", param.getMainOrdrNo());
        String response = callbackAppService.handlePaymentCallback(param);
        return ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(response);
    }

    @PostMapping(value = "/refund", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "接收建行惠市宝退款结果回调")
    public ResponseEntity<String> handleRefundCallback(
            @RequestBody HsbRefundCallbackParam param
    ) {
        log.info("Received HSB refund callback: custRfndTrcno={}", param.getCustRfndTrcno());
        String response = callbackAppService.handleRefundCallback(param);
        return ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(response);
    }
}
```

- [ ] **Step 5: 编译验证**

Run: `mvn compile -q`
Expected: BUILD SUCCESS

- [ ] **Step 6: Commit**

```bash
git add src/main/java/com/aieducenter/payment/hsb/endpoints/
git commit -m "feat: add HSB controllers (payment, refund, settlement, callback)"
```

---


## Task 15: 定时查询调度器

**Files:**
- Create: `src/main/java/com/aieducenter/payment/hsb/application/HsbPaymentQueryScheduler.java`
- Create: `src/main/java/com/aieducenter/payment/hsb/application/HsbRefundQueryScheduler.java`

- [ ] **Step 1: 创建 HsbPaymentQueryScheduler**

```java
package com.aieducenter.payment.hsb.application;

import com.aieducenter.payment.hsb.domain.enums.HsbPaymentStatus;
import com.aieducenter.payment.hsb.domain.repository.HsbPaymentOrderRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HsbPaymentQueryScheduler {

    private static final Logger log = LoggerFactory.getLogger(HsbPaymentQueryScheduler.class);

    private final HsbPaymentOrderRepository paymentOrderRepository;
    private final HsbPaymentAppService paymentAppService;

    @Scheduled(cron = "${scheduler.hsb-payment-query:0 */5 * * * *}")
    public void queryPendingOrders() {
        log.info("HSB payment query scheduler started");

        var pendingOrders = paymentOrderRepository
            .findByStatusAndExpiredAtBefore(HsbPaymentStatus.PENDING, java.time.LocalDateTime.now());

        for (var order : pendingOrders) {
            try {
                paymentAppService.queryPaymentStatus(order.getPaymentOrderNo());
            } catch (Exception e) {
                log.error("HSB payment query failed for order {}: {}",
                    order.getPaymentOrderNo(), e.getMessage());
            }
        }

        log.info("HSB payment query scheduler completed, processed {} orders", pendingOrders.size());
    }
}
```

- [ ] **Step 2: 创建 HsbRefundQueryScheduler**

```java
package com.aieducenter.payment.hsb.application;

import com.aieducenter.payment.hsb.domain.enums.HsbRefundStatus;
import com.aieducenter.payment.hsb.domain.repository.HsbRefundOrderRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HsbRefundQueryScheduler {

    private static final Logger log = LoggerFactory.getLogger(HsbRefundQueryScheduler.class);

    private final HsbRefundOrderRepository refundOrderRepository;
    private final HsbRefundAppService refundAppService;

    @Scheduled(cron = "${scheduler.hsb-refund-query:0 */5 * * * *}")
    public void queryRefundingOrders() {
        log.info("HSB refund query scheduler started");

        var refundingOrders = refundOrderRepository.findByStatus(HsbRefundStatus.REFUNDING);

        for (var order : refundingOrders) {
            try {
                refundAppService.queryRefundStatus(order.getRefundOrderNo());
            } catch (Exception e) {
                log.error("HSB refund query failed for order {}: {}",
                    order.getRefundOrderNo(), e.getMessage());
            }
        }

        log.info("HSB refund query scheduler completed, processed {} orders", refundingOrders.size());
    }
}
```

- [ ] **Step 3: 编译验证**

Run: `mvn compile -q`
Expected: BUILD SUCCESS

- [ ] **Step 4: Commit**

```bash
git add src/main/java/com/aieducenter/payment/hsb/application/HsbPaymentQueryScheduler.java src/main/java/com/aieducenter/payment/hsb/application/HsbRefundQueryScheduler.java
git commit -m "feat: add HSB payment and refund query schedulers"
```

---


## Task 16: 配置 + 全量编译验证

**Files:**
- Modify: `src/main/resources/application-local.yml`（添加 HSB 配置）

- [ ] **Step 1: 在 application-local.yml 中添加 HSB 配置**

在 `application-local.yml` 文件末尾追加：

```yaml
# 建行惠市宝支付配置
hsb:
  base-url: ${HSB_BASE_URL:http://marketpaypl4.dev.jh:8035/online/direct/}
  mkt-id: ${HSB_MKT_ID:41060860811052}
  private-key: ${HSB_PRIVATE_KEY:MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDS9se1wbq51SXw49bZgzIdj/ShiV618b/vNDztFhK/iW1nD1zMxo72YD9yHRX8jVWnrByDwL18107HK2nYbHu8t3Bcxd8k3/tpZgosbDOoXExDuJG02cwSx4jbCK6x5WWvucGO+tjoMaB1pPEurK6R8AVykcEzB6eo0bGSjx6ZUDh3pUESMXTKkXoQO/fJofq1BibWUq5KpAdeNL+yjFUUZg05PH2K7XRFp8nwe9tDQ3HrvUx1HhyNP/IFxU/XSR/D7uZsYuYOehiBX76mQzYD8PocnS0ehjpcyho7R/jmGuD4O6BGdWxCthrNrrRd6g030/TvfRictiuEJAMSQqf/AgMBAAECggEAHa61OMCSSjVQSk10XFRWR8yKafQPDGCAVeKus9kIOETYzMhfkTxavxWZt6+Z+VfVdmsD9BG5V4hfwCw+j0HsQwg4WgVJOUH+eLzvr4Jl3klmPZ0Jez2ttfK3McJN+h/Bp/Dl5/0paboZzpOvj5aiVUxFJ/KUEV8BWwJuDqXuczmRsG/JwXCDnLsrIEMmyBXDgvGSEiu/L6mjfIMNpwBPGkTiiJGRlBSWIAWdQr/jNw/po0zb+jlCVGWoPcivWGAafXJQX66aAk4JMiNCuLjdkH+xen/xSWU/QEQ9nWwLRJh6l9shvPWY3bfqQKPYkDGyyLdIUzxpIQBpkn+SZp5TSQKBgQD7KOtBC0bcVPpOVu333BC/pxMzqA2HPMyflQuUr7Di6HXZulxb2qEuXfafRMgVo7puLu/TDozZa83L7W1nEf5nbjClVEgVMbhT1uNgkIRuJ8CJu50wnDWUBsmMB4+Dn6kUG7YD/Psp4M1xzhWQSXvyxFXzAf6+DzrISK0FymJeVQKBgQDXB462cxKLbot8la1rQIjw2lX6p/WMBASuqOnPFlkDjIZuQbYfXchWxJRXVLCUikPzBLjWvJHbjrhmBTeIyxTyib+JThqaMzyMf0Y77/GiUJdUBx7PFHFyhDaj+jSmOxi7ceSZF37RNn539D2S6E7Qusj3OYPlaQrSV1WmLxBZAwKBgQDh9lSBdnXQMRvpc0gxoOnoo5Yg+WcCbu7h/CQpJ1ALNX0h4ArMEQzGPH9vl2A0J9PI4a2eww5xZg4HFJtDCetKftaBSCx59PuTYle7PwoGWPlecU7gtwl1Hg4iT4MMto5VqwC84dPOP5RWeUTpRVOgfIefVAIuWGFYZBpWhViu6QKBgQDLU3gdCX6VnbgD3DyZV/KlXK9ETyGefgY3ab18djNBadWL2FLwIevYMBXc5lX6fyt1Vhe55aE+LRwsS+6RSQbLuHkGynXZLW2ppIezEVY5F1+gswLs6PXFRUOtll/Gd8cRJ8bzBAaEqbS4lJjMmyI7uQNi0l3nxYXYE4EHnSUmJQKBgHqcrvr0P2fl01Yma4CxU/CVppAWpN2xPFrIaOgIhMRwAIB/VJxMIcA35hjf5IDMFeevGFtqUCMHtxvFeDWsQlFw4WVR3aaATnQypNC3lukH4kgko7v6IsVj0NoaVOJd2kpNmivlcQGNGX1fg+wfkuF2vksKWfsJVZjM8GUoqMiu}
  platform-public-key: ${HSB_PLATFORM_PUBLIC_KEY:MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA0vbHtcG6udUl8OPW2YMyHY/0oYletfG/7zQ87RYSv4ltZw9czMaO9mA/ch0V/I1Vp6wcg8C9fNdOxytp2Gx7vLdwXMXfJN/7aWYKLGwzqFxMQ7iRtNnMEseI2wiuseVlr7nBjvrY6DGgdaTxLqyukfAFcpHBMwenqNGxko8emVA4d6VBEjF0ypF6EDv3yaH6tQYm1lKuSqQHXjS/soxVFGYNOTx9iu10RafJ8HvbQ0Nx671MdR4cjT/yBcVP10kfw+7mbGLmDnoYgV++pkM2A/D6HJ0tHoY6XMoaO0f45hrg+DugRnVsQrYaza60XeoNN9P0730YnLYrhCQDEkKn/wIDAQAB}
  version:
    place-order: "5"
    query-order: "5"
    refund-order: "3"
    query-refund: "4"
    confirm-settlement: "4"
  initiator-system-id: "00000"
  initiator-channel-code: "0000000000000000000000000"

# 惠市宝定时任务
scheduler:
  hsb-payment-query: "0 */5 * * * *"
  hsb-refund-query: "0 */5 * * * *"
```

- [ ] **Step 2: 全量编译验证**

Run: `mvn compile`
Expected: BUILD SUCCESS

如果编译失败，根据错误信息修复。常见问题：
- 缺少 import 语句
- 方法签名不匹配
- 缺少 JPA 关联配置

- [ ] **Step 3: 运行单元测试确认未破坏现有功能**

Run: `mvn test`
Expected: 所有现有测试通过

- [ ] **Step 4: Commit**

```bash
git add src/main/resources/application-local.yml
git commit -m "feat: add HSB configuration for local environment"
```

---

## 自查清单

### 规格覆盖
- [x] 创建支付订单 → Task 10 (HsbPaymentAppService) + Task 14 (HsbPaymentController)
- [x] 查询支付结果 → Task 10
- [x] 主动查询支付状态 → Task 10
- [x] 创建退款订单 → Task 12 (HsbRefundAppService) + Task 14 (HsbRefundController)
- [x] 查询退款结果 → Task 12
- [x] 确认分账 → Task 13 (HsbSettlementAppService) + Task 14 (HsbSettlementController)
- [x] 支付结果回调 → Task 11 (HsbCallbackAppService) + Task 14 (HsbCallbackController)
- [x] 退款结果回调 → Task 11 + Task 14
- [x] 签名/验签 → Task 8 (HsbSplicingUtil + HsbSignUtil)
- [x] 金额转换（分/元）→ Task 9 (HsbPaymentGatewayAdapter)
- [x] 定时查询补偿 → Task 15 (Schedulers)
- [x] 数据库6张表 → Task 2 (Flyway)
- [x] HsbConfig 配置 → Task 7 + Task 16

### 类型一致性
- CreateHsbPaymentCommand 的子订单类型 HsbSubOrderCommand 与 HsbPaymentAppService 中构建 HsbSubOrder 的参数匹配
- HsbPaymentGatewayPort 方法签名与 HsbPaymentGatewayAdapter 实现匹配
- HsbCallbackAppService 使用的验签方法 HsbSplicingUtil.createSign(json, true) 与 Task 8 定义匹配
- 所有枚举的 code/name 字段和 JpaConverter 正确定义

### Placeholder 扫描
- 无 TBD/TODO/实现后续等占位符
- 所有代码步骤包含完整代码
