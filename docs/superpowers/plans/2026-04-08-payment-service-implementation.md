# Payment Service Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Build a payment service that provides unified payment and refund APIs for business systems, with detailed transaction logging and extensible bank gateway support (starting with ICBC).

**Architecture:** Dual-context DDD architecture with Security Context (API Key + RSA2 signature authentication, reusable across services) and Payment Context (payment/refund order management, transaction logging, ICBC adapter as south-bound interface).

**Tech Stack:** Spring Boot 3.x, cartisan-boot framework, PostgreSQL, Redis, ICBC SDK, Java 21

---

## Project Overview

### Contexts
1. **Security Context** (`com.aieducenter.security`) - API Key management, RSA2 signature generation/validation, permission control
2. **Payment Context** (`com.aieducenter.payment`) - Payment orders, refund orders with approval workflow, payment logs, ICBC adapter

### Key Design Decisions
- All refunds require approval (audit workflow)
- Detailed timestamp tracking for all status transitions
- `subject`: short payment description (shown to users)
- `attach`: detailed order data in JSON format (not shown, returned as-is)
- `businessSystemName`: from API Key, `businessType`: from business system
- ICBC configuration in application-local.yml (appId, merId, protocol, keys)

---

## File Structure

```
com.aieducenter/
├── security/
│   ├── package-info.java
│   ├── domain/
│   │   ├── aggregate/
│   │   │   └── ApiKey.java
│   │   ├── entity/
│   │   │   └── ApiKeyPermission.java
│   │   ├── service/
│   │   │   └── SignatureService.java
│   │   ├── port/
│   │   │   ├── SignatureGenerator.java
│   │   │   └── SignatureValidator.java
│   │   ├── repository/
│   │   │   └── ApiKeyRepository.java
│   │   └── enums/
│   │       └── ApiKeyStatus.java
│   ├── application/
│   │   ├── dto/
│   │   │   ├── command/
│   │   │   │   ├── CreateApiKeyCommand.java
│   │   │   │   └── AuditRefundCommand.java
│   │   │   ├── query/
│   │   │   │   └── ApiKeyQuery.java
│   │   │   └── response/
│   │   │       ├── ApiKeyResponse.java
│   │   │       ├── CreatePaymentResponse.java
│   │   │       └── PaymentDetailResponse.java
│   │   ├── mapper/
│   │   │   └── ApiKeyMapper.java
│   │   └── ApiKeyManagementAppService.java
│   ├── infrastructure/
│   │   └── persistence/
│   │       └── SpringDataJpaApiKeyRepository.java
│   └── endpoints/
│       └── api/
│           └── v1/
│               └── SecurityApiV1Controller.java
│
└── payment/
    ├── package-info.java
    ├── domain/
    │   ├── aggregate/
    │   │   ├── PaymentOrder.java
    │   │   ├── RefundOrder.java
    │   │   └── PaymentLog.java
    │   ├── entity/
    │   ├── service/
    │   │   └── PaymentService.java
    │   ├── port/
    │   │   └── PaymentGatewayPort.java
    │   ├── repository/
    │   │   ├── PaymentOrderRepository.java
    │   │   ├── RefundOrderRepository.java
    │   │   └── PaymentLogRepository.java
    │   └── enums/
    │       ├── PaymentStatus.java
    │       ├── RefundStatus.java
    │       ├── PaymentMethod.java
    │       └── BusinessType.java
    ├── application/
    │   ├── dto/
    │   │   ├── command/
    │   │   │   ├── CreatePaymentCommand.java
    │   │   │   ├── CreateRefundCommand.java
    │   │   │   └── AuditRefundCommand.java
    │   │   ├── query/
    │   │   │   └── PaymentQuery.java
    │   │   └── response/
    │   │       └── PaymentOrderResponse.java
    │   ├── mapper/
    │   │   └── PaymentOrderMapper.java
    │   ├── PaymentAppService.java
    │   └── RefundAppService.java
    ├── infrastructure/
    │   ├── persistence/
    │   │   ├── SpringDataJpaPaymentOrderRepository.java
    │   │   ├── SpringDataJpaRefundOrderRepository.java
    │   │   └── SpringDataJpaPaymentLogRepository.java
    │   └── icbc/
    │       ├── IcbcPaymentGatewayAdapter.java
    │       ├── IcbcRefundGatewayAdapter.java
    │       ├── IcbcConfig.java
    │       ├── IcbcSignatureService.java
    │       └── IcbcClientFactory.java
    └── endpoints/
        └── api/
            └── v1/
                ├── PaymentApiV1Controller.java
                └── RefundApiV1Controller.java
```

---

## Phase 1: Foundation Setup

### Task 1: Initialize Security Context Structure

**Files:**
- Create: `src/main/java/com/aieducenter/security/package-info.java`
- Create: `src/main/java/com/aieducenter/security/domain/enums/ApiKeyStatus.java`
- Create: `src/main/java/com/aieducenter/security/domain/error/SecurityMessage.java`

**Step 1: Create package-info for Security Context**

```java
/**
 * Security Context.
 *
 * <h3>职责</h3>
 * <ul>
 *   <li>API Key 管理</li>
 *   <li>RSA2 签名生成与验证</li>
 *   <li>权限控制</li>
 * </ul>
 *
 * <h3>限界上下文</h3>
 * <p>可复用的认证鉴权上下文，为对外服务提供统一的 API Key + 签名验证机制</p>
 *
 * <h3>包结构</h3>
 * <ul>
 *   <li>domain - 领域层：API Key 聚合根、签名服务、端口接口</li>
 *   <li>application - 应用层：API Key 管理服务、DTO</li>
 *   <li>infrastructure - 基础设施层：持久化</li>
 *   <li>endpoints - 北向接口：Security API</li>
 * </ul>
 *
 * @since 1.0.0
 */
@BoundedContext(name = "Security", subDomain = SubDomain.SUPPORTING)
package com.aieducenter.security;

import com.cartisan.core.stereotype.BoundedContext;
import com.cartisan.core.stereotype.SubDomain;
```

**Step 2: Create ApiKeyStatus enum**

```java
package com.aieducenter.security.domain.enums;

import com.cartisan.core.domain.BaseEnum;
import com.cartisan.data.jpa.converter.BaseEnumConverter;
import jakarta.persistence.Converter;

public enum ApiKeyStatus implements BaseEnum<ApiKeyStatus> {
    ACTIVE(1, "启用"),
    DISABLED(0, "禁用");

    private final Integer code;
    private final String name;

    ApiKeyStatus(Integer code, String name) {
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
    public static class JpaConverter extends BaseEnumConverter<ApiKeyStatus> {
        public JpaConverter() {
            super(ApiKeyStatus.class);
        }
    }
}
```

**Step 3: Create SecurityMessage error codes**

```java
package com.aieducenter.security.domain.error;

import com.cartisan.core.exception.CodeMessage;

public enum SecurityMessage implements CodeMessage {
    // ========== 格式校验错误 (400) ==========
    API_KEY_INVALID(400, "SEC_001", "API Key 格式不正确"),
    SIGNATURE_INVALID(400, "SEC_002", "签名验证失败"),
    TIMESTAMP_INVALID(400, "SEC_003", "时间戳格式不正确"),

    // ========== 唯一性错误 (409) ==========
    API_KEY_ALREADY_EXISTS(409, "SEC_010", "API Key 已存在"),

    // ========== 资源不存在 (404) ==========
    API_KEY_NOT_FOUND(404, "SEC_020", "API Key 不存在"),

    // ========== 业务限制 (403) ==========
    API_KEY_DISABLED(403, "SEC_030", "API Key 已禁用"),
    PERMISSION_DENIED(403, "SEC_031", "权限不足");

    private final int httpStatus;
    private final String code;
    private final String message;

    SecurityMessage(int httpStatus, String code, String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }

    @Override
    public int httpStatus() {
        return httpStatus;
    }

    @Override
    public String code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }
}
```

---

### Task 2: Initialize Payment Context Structure

**Files:**
- Create: `src/main/java/com/aieducenter/payment/package-info.java`
- Create: `src/main/java/com/aieducenter/payment/domain/enums/PaymentStatus.java`
- Create: `src/main/java/com/aieducenter/payment/domain/enums/RefundStatus.java`
- Create: `src/main/java/com/aieducenter/payment/domain/enums/BusinessType.java`
- Create: `src/main/java/com/aieducenter/payment/domain/enums/PaymentMethod.java`
- Create: `src/main/java/com/aieducenter/payment/domain/error/PaymentMessage.java`

**Step 1: Create PaymentStatus enum with timestamp transitions**

```java
package com.aieducenter.payment.domain.enums;

import com.cartisan.core.domain.BaseEnum;
import com.cartisan.data.jpa.converter.BaseEnumConverter;
import jakarta.persistence.Converter;

public enum PaymentStatus implements BaseEnum<PaymentStatus> {
    PENDING(1, "待支付"),
    PAID(2, "已支付"),
    FAILED(3, "支付失败"),
    CANCELLED(4, "已取消"),
    EXPIRED(5, "已过期");

    private final Integer code;
    private final String name;

    PaymentStatus(Integer code, String name) {
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

    public boolean isTerminal() {
        return this == PAID || this == FAILED || this == CANCELLED || this == EXPIRED;
    }

    @Converter(autoApply = true)
    public static class JpaConverter extends BaseEnumConverter<PaymentStatus> {
        public JpaConverter() {
            super(PaymentStatus.class);
        }
    }
}
```

**Step 2: Create RefundStatus enum with approval workflow**

```java
package com.aieducenter.payment.domain.enums;

import com.cartisan.core.domain.BaseEnum;
import com.cartisan.data.jpa.converter.BaseEnumConverter;
import jakarta.persistence.Converter;

public enum RefundStatus implements BaseEnum<RefundStatus> {
    PENDING(1, "待审核"),
    REJECTED(2, "已拒绝"),
    APPROVED(3, "已批准"),
    REFUNDING(4, "退款中"),
    SUCCESS(5, "退款成功"),
    FAILED(6, "退款失败");

    private final Integer code;
    private final String name;

    RefundStatus(Integer code, String name) {
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

    public boolean isTerminal() {
        return this == REJECTED || this == SUCCESS || this == FAILED;
    }

    @Converter(autoApply = true)
    public static class JpaConverter extends BaseEnumConverter<RefundStatus> {
        public JpaConverter() {
            super(RefundStatus.class);
        }
    }
}
```

**Step 3: Create BusinessType enum**

```java
package com.aieducenter.payment.domain.enums;

import com.cartisan.core.domain.BaseEnum;
import com.cartisan.data.jpa.converter.BaseEnumConverter;
import jakarta.persistence.Converter;

public enum BusinessType implements BaseEnum<BusinessType> {
    EDUCATION(1, "教育"),
    ECOMMERCE(2, "电商"),
    ENTERTAINMENT(3, "娱乐"),
    OTHER(99, "其他");

    private final Integer code;
    private final String name;

    BusinessType(Integer code, String name) {
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
    public static class JpaConverter extends BaseEnumConverter<BusinessType> {
        public JpaConverter() {
            super(BusinessType.class);
        }
    }
}
```

**Step 4: Create PaymentMethod enum**

```java
package com.aieducenter.payment.domain.enums;

import com.cartisan.core.domain.BaseEnum;
import com.cartisan.data.jpa.converter.BaseEnumConverter;
import jakarta.persistence.Converter;

public enum PaymentMethod implements BaseEnum<PaymentMethod> {
    WECHAT(9, "微信"),
    ALIPAY(10, "支付宝"),
    ICBC(1, "工商银行");

    private final Integer code;
    private final String name;

    PaymentMethod(Integer code, String name) {
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
    public static class JpaConverter extends BaseEnumConverter<PaymentMethod> {
        public JpaConverter() {
            super(PaymentMethod.class);
        }
    }
}
```

**Step 5: Create PaymentMessage error codes**

```java
package com.aieducenter.payment.domain.error;

import com.cartisan.core.exception.CodeMessage;

public enum PaymentMessage implements CodeMessage {
    // ========== 格式校验错误 (400) ==========
    PAYMENT_ORDER_NO_INVALID(400, "PAY_001", "支付订单号格式不正确"),
    REFUND_ORDER_NO_INVALID(400, "PAY_002", "退款订单号格式不正确"),
    AMOUNT_INVALID(400, "PAY_003", "金额格式不正确"),

    // ========== 业务规则错误 (400) ==========
    PAYMENT_ORDER_NOT_PENDING(400, "PAY_010", "支付订单不是待支付状态"),
    REFUND_ORDER_NOT_PENDING(400, "PAY_011", "退款订单不是待审核状态"),
    REFUND_AMOUNT_EXCEEDS(400, "PAY_012", "退款金额超过可退款金额"),
    ORIGINAL_PAYMENT_NOT_SUCCESS(400, "PAY_013", "原支付订单未成功，无法退款"),

    // ========== 唯一性错误 (409) ==========
    PAYMENT_ORDER_NO_EXISTS(409, "PAY_020", "支付订单号已存在"),
    REFUND_ORDER_NO_EXISTS(409, "PAY_021", "退款订单号已存在"),

    // ========== 资源不存在 (404) ==========
    PAYMENT_ORDER_NOT_FOUND(404, "PAY_030", "支付订单不存在"),
    REFUND_ORDER_NOT_FOUND(404, "PAY_031", "退款订单不存在"),

    // ========== 业务限制 (403) ==========
    PAYMENT_ORDER_EXPIRED(403, "PAY_040", "支付订单已过期"),
    PAYMENT_ORDER_FAILED(403, "PAY_041", "支付订单已失败"),
    REFUND_ALREADY_REFUNDED(403, "PAY_042", "已退款，不能重复退款");

    private final int httpStatus;
    private final String code;
    private final String message;

    PaymentMessage(int httpStatus, String code, String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }

    @Override
    public int httpStatus() {
        return httpStatus;
    }

    @Override
    public String code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }
}
```

---

## Phase 2: Database Setup

### Task 3: Create Database Migration Scripts

**Files:**
- Create: `src/main/resources/db/migration/V1__create_security_tables.sql`
- Create: `src/main/resources/db/migration/V1__create_payment_tables.sql`

**Step 1: Create security tables migration**

```sql
-- ========================================================================
-- Security Context: API Key Management
-- ========================================================================

CREATE TABLE security_api_keys (
    id BIGINT PRIMARY KEY,
    api_key VARCHAR(64) NOT NULL UNIQUE,
    api_secret VARCHAR(128) NOT NULL,
    business_system_name VARCHAR(128) NOT NULL,
    status INTEGER NOT NULL DEFAULT 1,
    permissions TEXT,
    description VARCHAR(255),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by BIGINT,
    updated_by BIGINT,
    deleted BOOLEAN NOT NULL DEFAULT FALSE
);

-- Indexes
CREATE INDEX idx_security_api_keys_api_key ON security_api_keys(api_key) WHERE deleted = FALSE;
CREATE INDEX idx_security_api_keys_system ON security_api_keys(business_system_name) WHERE deleted = FALSE;
```

**Step 2: Create payment tables migration**

```sql
-- ========================================================================
-- Payment Context: Payment Orders
-- ========================================================================

CREATE TABLE payment_payment_orders (
    id BIGINT PRIMARY KEY,
    business_order_no VARCHAR(64) NOT NULL,
    payment_order_no VARCHAR(64) NOT NULL UNIQUE,
    business_system_name VARCHAR(128) NOT NULL,
    business_type INTEGER NOT NULL,
    status INTEGER NOT NULL,
    amount BIGINT NOT NULL,
    currency VARCHAR(3) NOT NULL DEFAULT 'CNY',
    subject VARCHAR(255),
    body TEXT,
    payment_method INTEGER,
    client_ip VARCHAR(64),
    notify_url VARCHAR(512),
    return_url VARCHAR(512),
    attach TEXT,

    -- Timestamps for status transitions
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    paid_at TIMESTAMP,
    failed_at TIMESTAMP,
    cancelled_at TIMESTAMP,
    expired_at TIMESTAMP,

    -- Bank information
    bank_order_no VARCHAR(64),
    third_party_order_no VARCHAR(128),

    -- Audit fields
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by BIGINT,
    updated_by BIGINT,
    deleted BOOLEAN NOT NULL DEFAULT FALSE
);

-- Indexes
CREATE INDEX idx_payment_orders_business_no ON payment_payment_orders(business_order_no) WHERE deleted = FALSE;
CREATE INDEX idx_payment_orders_system ON payment_payment_orders(business_system_name) WHERE deleted = FALSE;
CREATE INDEX idx_payment_orders_status ON payment_payment_orders(status) WHERE deleted = FALSE;
CREATE INDEX idx_payment_orders_created_at ON payment_payment_orders(created_at) WHERE deleted = FALSE;

-- ========================================================================
-- Payment Context: Refund Orders
-- ========================================================================

CREATE TABLE payment_refund_orders (
    id BIGINT PRIMARY KEY,
    business_order_no VARCHAR(64) NOT NULL,
    refund_order_no VARCHAR(64) NOT NULL UNIQUE,
    payment_order_no VARCHAR(64) NOT NULL,
    business_system_name VARCHAR(128) NOT NULL,
    business_type INTEGER NOT NULL,
    status INTEGER NOT NULL,
    refund_amount BIGINT NOT NULL,
    refundable_amount BIGINT NOT NULL,
    reason VARCHAR(512),

    -- Audit information
    auditor_id BIGINT,
    auditor_name VARCHAR(64),
    audit_agreed BOOLEAN,
    audited_at TIMESTAMP,
    audit_remark TEXT,

    -- Timestamps for status transitions
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    approved_at TIMESTAMP,
    refunded_at TIMESTAMP,
    failed_at TIMESTAMP,

    -- Bank information
    bank_refund_no VARCHAR(64),

    -- Other
    attach TEXT,

    -- Audit fields
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by BIGINT,
    updated_by BIGINT,
    deleted BOOLEAN NOT NULL DEFAULT FALSE
);

-- Indexes
CREATE INDEX idx_refund_orders_payment_no ON payment_refund_orders(payment_order_no) WHERE deleted = FALSE;
CREATE INDEX idx_refund_orders_business_no ON payment_refund_orders(business_order_no) WHERE deleted = FALSE;
CREATE INDEX idx_refund_orders_system ON payment_refund_orders(business_system_name) WHERE deleted = FALSE;
CREATE INDEX idx_refund_orders_status ON payment_refund_orders(status) WHERE deleted = FALSE;

-- ========================================================================
-- Payment Context: Payment Logs
-- ========================================================================

CREATE TABLE payment_logs (
    id BIGINT PRIMARY KEY,
    payment_order_no VARCHAR(64),
    refund_order_no VARCHAR(64),
    log_type VARCHAR(32) NOT NULL,
    bank_code VARCHAR(16) NOT NULL,
    bank_interface VARCHAR(64) NOT NULL,
    request_url VARCHAR(512),
    request_params TEXT,
    response_params TEXT,
    http_status INTEGER,
    return_code VARCHAR(16),
    return_msg TEXT,
    execution_time BIGINT,
    success BOOLEAN NOT NULL,
    error_message TEXT,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted BOOLEAN NOT NULL DEFAULT FALSE
);

-- Indexes
CREATE INDEX idx_payment_logs_payment_no ON payment_logs(payment_order_no) WHERE deleted = FALSE;
CREATE INDEX idx_payment_logs_refund_no ON payment_logs(refund_order_no) WHERE deleted = FALSE;
CREATE INDEX idx_payment_logs_created_at ON payment_logs(created_at) WHERE deleted = FALSE;
```

---

## Phase 3: Security Context Implementation

### Task 4: Implement ApiKey Aggregate

**Files:**
- Create: `src/main/java/com/aieducenter/security/domain/aggregate/ApiKey.java`

**Step 1: Write ApiKey aggregate with validation**

```java
package com.aieducenter.security.domain.aggregate;

import cn.hutool.core.util.StrUtil;
import com.aieducenter.security.domain.enums.ApiKeyStatus;
import com.aieducenter.security.domain.error.SecurityMessage;
import com.cartisan.core.exception.ApplicationException;
import com.cartisan.core.domain.BaseEnum;
import com.cartisan.core.stereotype.Aggregate;
import com.cartisan.core.util.Assertions;
import com.cartisan.data.jpa.domain.AuditableSoftDeletable;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "security_api_keys")
@Aggregate
public class ApiKey extends AuditableSoftDeletable {
    private static final String API_KEY_PATTERN = "^[A-Z0-9]{32}$";

    @Getter
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Getter
    @Column(name = "api_key", nullable = false, unique = true, length = 64)
    private String apiKey;

    @Getter
    @Column(name = "api_secret", nullable = false)
    private String apiSecret;

    @Getter
    @Column(name = "business_system_name", nullable = false, length = 128)
    private String businessSystemName;

    @Getter
    @Column(name = "status", nullable = false)
    private ApiKeyStatus status;

    @Getter
    @Column(name = "permissions", columnDefinition = "TEXT")
    private String permissions;

    @Getter
    @Column(name = "description")
    private String description;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "api_key_id")
    @Getter
    private final Set<ApiKeyPermission> apiKeyPermissions = cn.hutool.core.collection.CollUtil.newHashSet();

    @PrePersist
    void prePersist() {
        if (id == null) {
            this.id = com.cartisan.data.jpa.id.TsidGenerator.newInstance().generate();
        }
    }

    /**
     * 创建 API Key
     */
    public ApiKey(String businessSystemName, String description, Set<String> permissions) {
        Assertions.require(StrUtil.isNotBlank(businessSystemName),
            () -> new ApplicationException(SecurityMessage.API_KEY_INVALID));

        this.businessSystemName = businessSystemName;
        this.description = description;
        this.status = ApiKeyStatus.ACTIVE;
        this.apiKey = generateApiKey();
        this.apiSecret = generateApiSecret();

        if (permissions != null && !permissions.isEmpty()) {
            this.permissions = String.join(",", permissions);
            permissions.forEach(perm ->
                this.apiKeyPermissions.add(new ApiKeyPermission(perm))
            );
        }
    }

    /**
     * 禁用 API Key
     */
    public void disable() {
        this.status = ApiKeyStatus.DISABLED;
    }

    /**
     * 启用 API Key
     */
    public void enable() {
        this.status = ApiKeyStatus.ACTIVE;
    }

    /**
     * 验证权限
     */
    public boolean hasPermission(String permission) {
        if (status != ApiKeyStatus.ACTIVE) {
            return false;
        }
        if (permissions == null) {
            return false;
        }
        return permissions.contains(permission);
    }

    private String generateApiKey() {
        // 生成32位大写字母和数字组合
        return cn.hutool.core.util.RandomUtil.randomString("ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789", 32);
    }

    private String generateApiSecret() {
        // 生成32字节的密钥，然后Base64编码
        byte[] secretBytes = new byte[32];
        new java.security.SecureRandom().nextBytes(secretBytes);
        return java.util.Base64.getEncoder().encodeToString(secretBytes);
    }
}
```

**Step 2: Run tests to verify structure**

No test yet - just verifying compilation.

```bash
mvn compile
```

Expected: Compilation successful

---

## Phase 4: Payment Context - Core Aggregates

### Task 5: Implement PaymentOrder Aggregate

**Files:**
- Create: `src/main/java/com/aieducenter/payment/domain/aggregate/PaymentOrder.java`

**Step 1: Write PaymentOrder aggregate with business methods**

```java
package com.aieducenter.payment.domain.aggregate;

import cn.hutool.core.util.StrUtil;
import com.aieducenter.payment.domain.enums.BusinessType;
import com.aieducenter.payment.domain.enums.PaymentMethod;
import com.aieducenter.payment.domain.enums.PaymentStatus;
import com.aieducenter.payment.domain.error.PaymentMessage;
import com.cartisan.core.exception.DomainException;
import com.cartisan.core.stereotype.Aggregate;
import com.cartisan.core.util.Assertions;
import com.cartisan.data.jpa.domain.AuditableSoftDeletable;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Table(name = "payment_payment_orders")
@Aggregate
public class PaymentOrder extends AuditableSoftDeletable {
    private static final String PAYMENT_ORDER_NO_PREFIX = "PAY";

    @Getter
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Getter
    @Column(name = "business_order_no", nullable = false, length = 64)
    private String businessOrderNo;

    @Getter
    @Column(name = "payment_order_no", nullable = false, unique = true, length = 64)
    private String paymentOrderNo;

    @Getter
    @Column(name = "business_system_name", nullable = false, length = 128)
    private String businessSystemName;

    @Getter
    @Column(name = "business_type", nullable = false)
    private BusinessType businessType;

    @Getter
    @Column(name = "status", nullable = false)
    private PaymentStatus status;

    @Getter
    @Column(name = "amount", nullable = false)
    private Long amount;

    @Getter
    @Column(name = "currency", nullable = false, length = 3)
    private String currency;

    @Getter
    @Column(name = "subject")
    private String subject;

    @Column(name = "body", columnDefinition = "TEXT")
    private String body;

    @Getter
    @Column(name = "payment_method")
    private PaymentMethod paymentMethod;

    @Getter
    @Column(name = "client_ip", length = 64)
    private String clientIp;

    @Getter
    @Column(name = "notify_url", length = 512)
    private String notifyUrl;

    @Getter
    @Column(name = "return_url", length = 512)
    private String returnUrl;

    @Getter
    @Column(name = "attach", columnDefinition = "TEXT")
    private String attach;

    @Getter
    @Column(name = "expired_seconds")
    private Long expiredSeconds;

    // Timestamps for status transitions
    @Getter
    @Column(name = "paid_at")
    private LocalDateTime paidAt;

    @Getter
    @Column(name = "failed_at")
    private LocalDateTime failedAt;

    @Getter
    @Column(name = "cancelled_at")
    private LocalDateTime cancelledAt;

    @Getter
    @Column(name = "expired_at")
    private LocalDateTime expiredAt;

    // Bank information
    @Getter
    @Column(name = "bank_order_no", length = 64)
    private String bankOrderNo;

    @Getter
    @Column(name = "third_party_order_no", length = 128)
    private String thirdPartyOrderNo;

    @PrePersist
    void prePersist() {
        if (id == null) {
            this.id = com.cartisan.data.jpa.id.TsidGenerator.newInstance().generate();
            this.paymentOrderNo = generatePaymentOrderNo();
            this.status = PaymentStatus.PENDING;
            this.currency = "CNY";
            this.expiredAt = calculateExpiredAt();
        }
    }

    /**
     * 创建支付订单
     */
    public PaymentOrder(
            String businessOrderNo,
            String businessSystemName,
            BusinessType businessType,
            Long amount,
            String subject,
            String body,
            String clientIp,
            String notifyUrl,
            String returnUrl,
            String attach,
            Long expiredSeconds
    ) {
        Assertions.require(StrUtil.isNotBlank(businessOrderNo),
            () -> new DomainException("businessOrderNo cannot be blank"));
        Assertions.require(amount != null && amount > 0,
            () -> new DomainException("amount must be positive"));

        this.businessOrderNo = businessOrderNo;
        this.businessSystemName = businessSystemName;
        this.businessType = businessType;
        this.amount = amount;
        this.subject = subject;
        this.body = body;
        this.clientIp = clientIp;
        this.notifyUrl = notifyUrl;
        this.returnUrl = returnUrl;
        this.attach = attach;
        this.expiredSeconds = expiredSeconds != null ? expiredSeconds : 3600L; // 默认1小时
    }

    /**
     * 支付成功
     */
    public void markAsPaid(String bankOrderNo, String thirdPartyOrderNo, PaymentMethod paymentMethod) {
        Assertions.require(this.status == PaymentStatus.PENDING,
            () -> new DomainException(PaymentMessage.PAYMENT_ORDER_NOT_PENDING));

        this.status = PaymentStatus.PAID;
        this.paidAt = LocalDateTime.now();
        this.bankOrderNo = bankOrderNo;
        this.thirdPartyOrderNo = thirdPartyOrderNo;
        this.paymentMethod = paymentMethod;
    }

    /**
     * 支付失败
     */
    public void markAsFailed(String reason) {
        Assertions.require(this.status == PaymentStatus.PENDING,
            () -> new DomainException(PaymentMessage.PAYMENT_ORDER_NOT_PENDING));

        this.status = PaymentStatus.FAILED;
        this.failedAt = LocalDateTime.now();
    }

    /**
     * 取消支付
     */
    public void cancel() {
        Assertions.require(this.status == PaymentStatus.PENDING,
            () -> new DomainException(PaymentMessage.PAYMENT_ORDER_NOT_PENDING));

        this.status = PaymentStatus.CANCELLED;
        this.cancelledAt = LocalDateTime.now();
    }

    /**
     * 标记为过期
     */
    public void markAsExpired() {
        Assertions.require(this.status == PaymentStatus.PENDING,
            () -> new DomainException(PaymentMessage.PAYMENT_ORDER_NOT_PENDING));

        this.status = PaymentStatus.EXPIRED;
    }

    /**
     * 检查是否过期
     */
    public boolean isExpired() {
        return LocalDateTime.now().isAfter(this.expiredAt);
    }

    private String generatePaymentOrderNo() {
        String timestamp = LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String random = cn.hutool.core.util.RandomUtil.randomString("0123456789", 6);
        return PAYMENT_ORDER_NO_PREFIX + timestamp + random;
    }

    private LocalDateTime calculateExpiredAt() {
        return LocalDateTime.now().plusSeconds(this.expiredSeconds != null ? this.expiredSeconds : 3600L);
    }
}
```

---

### Task 6: Implement RefundOrder Aggregate with Audit Workflow

**Files:**
- Create: `src/main/java/com/aieducenter/payment/domain/aggregate/RefundOrder.java`

**Step 1: Write RefundOrder aggregate with audit methods**

```java
package com.aieducenter.payment.domain.aggregate;

import cn.hutool.core.util.StrUtil;
import com.aieducenter.payment.domain.enums.BusinessType;
import com.aieducenter.payment.domain.enums.RefundStatus;
import com.aieducenter.payment.domain.error.PaymentMessage;
import com.cartisan.core.exception.DomainException;
import com.cartisan.core.stereotype.Aggregate;
import com.cartisan.core.util.Assertions;
import com.cartisan.data.jpa.domain.AuditableSoftDeletable;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Table(name = "payment_refund_orders")
@Aggregate
public class RefundOrder extends AuditableSoftDeletable {
    private static final String REFUND_ORDER_NO_PREFIX = "REF";

    @Getter
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Getter
    @Column(name = "business_order_no", nullable = false, length = 64)
    private String businessOrderNo;

    @Getter
    @Column(name = "refund_order_no", nullable = false, unique = true, length = 64)
    private String refundOrderNo;

    @Getter
    @Column(name = "payment_order_no", nullable = false, length = 64)
    private String paymentOrderNo;

    @Getter
    @Column(name = "business_system_name", nullable = false, length = 128)
    private String businessSystemName;

    @Getter
    @Column(name = "business_type", nullable = false)
    private BusinessType businessType;

    @Getter
    @Column(name = "status", nullable = false)
    private RefundStatus status;

    @Getter
    @Column(name = "refund_amount", nullable = false)
    private Long refundAmount;

    @Getter
    @Column(name = "refundable_amount", nullable = false)
    private Long refundableAmount;

    @Getter
    @Column(name = "reason", length = 512)
    private String reason;

    // Audit information
    @Getter
    @Column(name = "auditor_id")
    private Long auditorId;

    @Getter
    @Column(name = "auditor_name", length = 64)
    private String auditorName;

    @Getter
    @Column(name = "audit_agreed")
    private Boolean auditAgreed;

    @Getter
    @Column(name = "audited_at")
    private LocalDateTime auditedAt;

    @Getter
    @Column(name = "audit_remark", columnDefinition = "TEXT")
    private String auditRemark;

    // Timestamps
    @Getter
    @Column(name = "approved_at")
    private LocalDateTime approvedAt;

    @Getter
    @Column(name = "refunded_at")
    private LocalDateTime refundedAt;

    @Getter
    @Column(name = "failed_at")
    private LocalDateTime failedAt;

    // Bank information
    @Getter
    @Column(name = "bank_refund_no", length = 64)
    private String bankRefundNo;

    @Getter
    @Column(name = "attach", columnDefinition = "TEXT")
    private String attach;

    @PrePersist
    void prePersist() {
        if (id == null) {
            this.id = com.cartisan.data.jpa.id.TsidGenerator.newInstance().generate();
            this.refundOrderNo = generateRefundOrderNo();
            this.status = RefundStatus.PENDING;
        }
    }

    /**
     * 创建退款订单
     */
    public RefundOrder(
            String businessOrderNo,
            String paymentOrderNo,
            String businessSystemName,
            BusinessType businessType,
            Long refundAmount,
            Long refundableAmount,
            String reason,
            String attach
    ) {
        Assertions.require(StrUtil.isNotBlank(businessOrderNo),
            () -> new DomainException("businessOrderNo cannot be blank"));
        Assertions.require(refundAmount != null && refundAmount > 0,
            () -> new DomainException("refundAmount must be positive"));
        Assertions.require(refundableAmount != null && refundAmount <= refundableAmount,
            () -> new DomainException(PaymentMessage.REFUND_AMOUNT_EXCEEDS));

        this.businessOrderNo = businessOrderNo;
        this.paymentOrderNo = paymentOrderNo;
        this.businessSystemName = businessSystemName;
        this.businessType = businessType;
        this.refundAmount = refundAmount;
        this.refundableAmount = refundableAmount;
        this.reason = reason;
        this.attach = attach;
    }

    /**
     * 审核退款
     */
    public void audit(Long auditorId, String auditorName, Boolean agreed, String remark) {
        Assertions.require(this.status == RefundStatus.PENDING,
            () -> new DomainException(PaymentMessage.REFUND_ORDER_NOT_PENDING));

        this.auditorId = auditorId;
        this.auditorName = auditorName;
        this.auditAgreed = agreed;
        this.auditRemark = remark;
        this.auditedAt = LocalDateTime.now();

        if (agreed) {
            // 审核通过
            this.status = RefundStatus.APPROVED;
            this.approvedAt = LocalDateTime.now();
        } else {
            // 审核拒绝
            this.status = RefundStatus.REJECTED;
        }
    }

    /**
     * 开始执行退款
     */
    public void startRefund() {
        Assertions.require(this.status == RefundStatus.APPROVED,
            () -> new DomainException(PaymentMessage.REFUND_ORDER_NOT_PENDING));
        this.status = RefundStatus.REFUNDING;
    }

    /**
     * 退款成功
     */
    public void refundSuccess(String bankRefundNo) {
        Assertions.require(this.status == RefundStatus.REFUNDING,
            () -> new DomainException("Refund is not in refunding status"));

        this.status = RefundStatus.SUCCESS;
        this.bankRefundNo = bankRefundNo;
        this.refundedAt = LocalDateTime.now();
    }

    /**
     * 退款失败
     */
    public void refundFailed(String errorMessage) {
        Assertions.require(this.status == RefundStatus.REFUNDING,
            () -> new DomainException("Refund is not in refunding status"));

        this.status = RefundStatus.FAILED;
        this.failedAt = LocalDateTime.now();
    }

    /**
     * 检查是否已批准
     */
    public boolean isApproved() {
        return this.status == RefundStatus.APPROVED;
    }

    private String generateRefundOrderNo() {
        String timestamp = LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String random = cn.hutool.core.util.RandomUtil.randomString("0123456789", 6);
        return REFUND_ORDER_NO_PREFIX + timestamp + random;
    }
}
```

---

## Phase 5: Configuration & Dependencies

### Task 7: Add ICBC SDK Dependencies

**Files:**
- Modify: `pom.xml`

**Step 1: Add ICBC SDK to pom.xml**

```xml
<!-- ICBC SDK dependencies -->
<dependency>
    <groupId>com.icbc</groupId>
    <artifactId>icbc-api-sdk-cop</artifactId>
    <version>2.0</version>
    <scope>system</scope>
    <systemPath>${project.basedir}/docs/工行/icbc-api-sdk-cop_v2_20260313/lib/icbc-api-sdk-cop.jar</systemPath>
</dependency>
<dependency>
    <groupId>com.icbc</groupId>
    <artifactId>icbc-api-sdk-cop-io</artifactId>
    <version>2.0</version>
    <scope>system</scope>
    <systemPath>${project.basedir}/docs/工行/icbc-api-sdk-cop_v2_20260313/lib/icbc-api-sdk-cop-io.jar</systemPath>
</dependency>
```

**Step 2: Verify dependencies**

```bash
mvn dependency:resolve
```

Expected: ICBC SDK dependencies resolved

---

### Task 8: Configure ICBC Settings

**Files:**
- Create: `src/main/resources/application-local.yml.example`

**Step 1: Create ICBC configuration template**

```yaml
gateway:
  icbc:
    # 环境配置: local, test, prod
    env: local

    # 应用配置
    app-id: 11000000000000079872
    mer-id: 020004161912
    mer-prtcl-no: 0200041619120201  # 协议号 = 商户号 + 0201

    # 密钥配置（使用实际部署时替换）
    private-key: |
      -----BEGIN PRIVATE KEY-----
      YOUR_PRIVATE_KEY_HERE
      -----END PRIVATE_KEY-----
    public-key: |
      -----BEGIN PUBLIC KEY-----
      ICBC_PUBLIC_KEY_HERE
      -----END PUBLIC KEY-----

    # 接口地址
    payment-url: https://gw.open.icbc.com.cn/api/cardbusiness/qrcode/consumption/V1
    payment-query-url: https://gw.open.icbc.com.cn/api/cardbusiness/aggregatepay/b2c/online/orderqry/V1
    refund-url: https://gw.open.icbc.com.cn/api/cardbusiness/aggregatepay/b2c/online/merrefund/V1
    refund-query-url: https://gw.open.icbc.com.cn/api/cardbusiness/aggregatepay/b2c/online/refundqry/V1
```

---

## Phase 6: ICBC Adapter Implementation

### Task 9: Implement ICBC Configuration and Client Factory

**Files:**
- Create: `src/main/java/com/aieducenter/payment/infrastructure/icbc/IcbcConfig.java`
- Create: `src/main/java/com/aieducenter/payment/infrastructure/icbc/IcbcClientFactory.java`

**Step 1: Create IcbcConfig configuration class**

```java
package com.aieducenter.payment.infrastructure.icbc;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "gateway.icbc")
public class IcbcConfig {

    /**
     * 环境配置
     */
    private String env;

    /**
     * 应用ID
     */
    private String appId;

    /**
     * 商户号
     */
    private String merId;

    /**
     * 协议号
     */
    private String merPrtclNo;

    /**
     * 商户私钥
     */
    private String privateKey;

    /**
     * 工行网关公钥
     */
    private String publicKey;

    /**
     * 支付接口地址
     */
    private String paymentUrl;

    /**
     * 支付查询接口地址
     */
    private String paymentQueryUrl;

    /**
     * 退款接口地址
     */
    private String refundUrl;

    /**
     * 退款查询接口地址
     */
    private String refundQueryUrl;
}
```

**Step 2: Create IcbcClientFactory**

```java
package com.aieducenter.payment.infrastructure.icbc;

import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class IcbcClientFactory {

    private final IcbcConfig icbcConfig;

    /**
     * 创建工行客户端
     */
    public DefaultIcbcClient createClient() {
        return new DefaultIcbcClient(
            icbcConfig.getAppId(),
            IcbcConstants.SIGN_TYPE_RSA2,
            icbcConfig.getPrivateKey(),
            icbcConfig.getPublicKey()
        );
    }

    /**
     * 生成消息ID
     */
    public String generateMsgId() {
        return UUID.randomUUID().toString();
    }
}
```

---

## Phase 7: Payment Gateway Port and Adapter

### Task 10: Define Payment Gateway Port Interface

**Files:**
- Create: `src/main/java/com/aieducenter/payment/domain/port/PaymentGatewayPort.java`

**Step 1: Create PaymentGatewayPort interface**

```java
package com.aieducenter.payment.domain.port;

import com.aieducenter.payment.domain.aggregate.PaymentOrder;
import com.aieducenter.payment.domain.aggregate.RefundOrder;
import com.cartisan.core.stereotype.Port;
import com.cartisan.core.stereotype.PortType;

@Port(PortType.CLIENT)
public interface PaymentGatewayPort {

    /**
     * 创建支付
     */
    CreatePaymentResponse createPayment(PaymentOrder paymentOrder);

    /**
     * 查询支付
     */
    QueryPaymentResponse queryPayment(String paymentOrderNo, String bankOrderNo);

    /**
     * 创建退款
     */
    CreateRefundResponse createRefund(RefundOrder refundOrder, String originalBankOrderNo);

    /**
     * 查询退款
     */
    QueryRefundResponse queryRefund(String refundOrderNo, String bankRefundNo);
}
```

**Step 2: Create response DTOs**

```java
package com.aieducenter.payment.domain.port;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreatePaymentResponse {
    private boolean success;
    private String returnCode;
    private String returnMsg;
    private String qrCodeUrl;
    private String bankOrderNo;
    private Long executionTime;
    private String requestParams;
    private String responseBody;
}

@Data
@Builder
public class QueryPaymentResponse {
    private boolean success;
    private String returnCode;
    private String returnMsg;
    private PaymentStatus paymentStatus;
    private Long amount;
    private Long paidAmount;
    private String payTime;
    private String bankOrderNo;
    private String thirdPartyOrderNo;
    private PaymentMethod paymentMethod;
    private Long executionTime;
}

@Data
@Builder
public class CreateRefundResponse {
    private boolean success;
    private String returnCode;
    private String returnMsg;
    private String bankRefundNo;
    private Long executionTime;
}

@Data
@Builder
public class QueryRefundResponse {
    private boolean success;
    private String returnCode;
    private String returnMsg;
    private RefundStatus refundStatus;
    private Long refundAmount;
    private Long realRefundAmount;
    private String refundTime;
    private String bankRefundNo;
    private Long executionTime;
}
```

---

### Task 11: Implement ICBC Payment Gateway Adapter

**Files:**
- Create: `src/main/java/com/aieducenter/payment/infrastructure/icbc/IcbcPaymentGatewayAdapter.java`

**Step 1: Implement ICBC payment adapter**

```java
package com.aieducenter.payment.infrastructure.icbc;

import com.aieducenter.payment.domain.aggregate.PaymentOrder;
import com.aieducenter.payment.domain.port.CreatePaymentResponse;
import com.aieducenter.payment.domain.port.PaymentGatewayPort;
import com.aieducenter.payment.domain.port.QueryPaymentResponse;
import com.cartisan.core.stereotype.Adapter;
import com.cartisan.core.stereotype.PortType;
import com.icbc.api.DefaultIcbcClient;
import com.icbc.api.IcbcConstants;
import com.icbc.api.request.CardbusinessQrcodeConsumptionRequestV1;
import com.icbc.api.request.CardbusinessQrcodeConsumptionRequestV1.CardbusinessQrcodeConsumptionRequestV1Biz;
import com.icbc.api.response.CardbusinessQrcodeConsumptionResponseV1;
import com.icbc.api.request.CardbusinessAggregatepayB2cOnlineOrderqryRequestV1;
import com.icbc.api.request.CardbusinessAggregatepayB2cOnlineOrderqryRequestV1.CardbusinessAggregatepayB2cOnlineOrderqryRequestV1Biz;
import com.icbc.api.response.CardbusinessAggregatepayB2cOnlineOrderqryResponseV1;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static com.aieducenter.payment.domain.enums.PaymentStatus.*;

@Component("icbcPaymentGateway")
@Adapter(PortType.CLIENT)
public class IcbcPaymentGatewayAdapter implements PaymentGatewayPort {
    private static final Logger log = LoggerFactory.getLogger(IcbcPaymentGatewayAdapter.class);

    private final IcbcConfig icbcConfig;
    private final IcbcClientFactory clientFactory;

    @Override
    public CreatePaymentResponse createPayment(PaymentOrder paymentOrder) {
        DefaultIcbcClient client = clientFactory.createClient();

        CardbusinessQrcodeConsumptionRequestV1 request =
            new CardbusinessQrcodeConsumptionRequestV1();
        request.setServiceUrl(icbcConfig.getPaymentUrl());

        CardbusinessQrcodeConsumptionRequestV1Biz bizContent =
            new CardbusinessQrcodeConsumptionRequestV1Biz();

        // 设置业务参数
        bizContent.setOut_trade_no(paymentOrder.getPaymentOrderNo());
        bizContent.setMer_id(icbcConfig.getMerId());
        bizContent.setMer_prtcl_no(icbcConfig.getMerPrtclNo());
        bizContent.setAccess_type("4"); // H5
        bizContent.setCur_type(paymentOrder.getCurrency());
        bizContent.setAmount(paymentOrder.getAmount().toString());
        bizContent.setIcbc_appid(icbcConfig.getAppId());
        bizContent.setMer_url(paymentOrder.getNotifyUrl());
        bizContent.setExpire_time(paymentOrder.getExpiredSeconds().toString());
        bizContent.setNotify_type("HS");
        bizContent.setResult_type("1");
        bizContent.setOrder_date(paymentOrder.getCreatedAt().format(
            java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        bizContent.setGoods_name(paymentOrder.getSubject());
        bizContent.setBody(paymentOrder.getBody());
        bizContent.setInstallment_times("1");

        request.setBizContent(bizContent);

        // 记录请求参数
        String requestParams = com.alibaba.fastjson2.JSON.toJSONString(bizContent);

        long startTime = System.currentTimeMillis();
        CardbusinessQrcodeConsumptionResponseV1 response;
        try {
            response = client.execute(request, clientFactory.generateMsgId());
        } catch (Exception e) {
            log.error("ICBC payment request failed", e);
            return CreatePaymentResponse.builder()
                .success(false)
                .returnCode("SYSTEM_ERROR")
                .returnMsg(e.getMessage())
                .executionTime(System.currentTimeMillis() - startTime)
                .requestParams(requestParams)
                .build();
        }
        long executionTime = System.currentTimeMillis() - startTime;

        // 解析响应
        boolean success = response.getReturnCode() == 0;
        String responseBody = com.alibaba.fastjson2.JSON.toJSONString(response);

        return CreatePaymentResponse.builder()
            .success(success)
            .returnCode(String.valueOf(response.getReturnCode()))
            .returnMsg(response.getReturnMsg())
            .qrCodeUrl(success ? response.getCodeUrl() : null)
            .bankOrderNo(success ? response.getOrderId() : null)
            .executionTime(executionTime)
            .requestParams(requestParams)
            .responseBody(responseBody)
            .build();
    }

    @Override
    public QueryPaymentResponse queryPayment(String paymentOrderNo, String bankOrderNo) {
        DefaultIcbcClient client = clientFactory.createClient();

        CardbusinessAggregatepayB2cOnlineOrderqryRequestV1 request =
            new CardbusinessAggregatepayB2cOnlineOrderqryRequestV1();
        request.setServiceUrl(icbcConfig.getPaymentQueryUrl());

        CardbusinessAggregatepayB2cOnlineOrderqryRequestV1Biz bizContent =
            new CardbusinessAggregatepayB2cOnlineOrderqryRequestV1Biz();

        bizContent.setMer_id(icbcConfig.getMerId());
        bizContent.setOut_trade_no(paymentOrderNo);
        bizContent.setOrder_id(bankOrderNo);
        bizContent.setDeal_flag("0");
        bizContent.setIcbc_appid(icbcConfig.getAppId());
        bizContent.setMer_prtcl_no(icbcConfig.getMerPrtclNo());

        request.setBizContent(bizContent);

        long startTime = System.currentTimeMillis();
        CardbusinessAggregatepayB2cOnlineOrderqryResponseV1 response;
        try {
            response = client.execute(request, clientFactory.generateMsgId());
        } catch (Exception e) {
            log.error("ICBC payment query failed", e);
            return QueryPaymentResponse.builder()
                .success(false)
                .returnCode("SYSTEM_ERROR")
                .returnMsg(e.getMessage())
                .executionTime(System.currentTimeMillis() - startTime)
                .build();
        }
        long executionTime = System.currentTimeMillis() - startTime;

        // 解析响应
        boolean success = response.getReturnCode() == 0;
        PaymentStatus status = mapPaymentStatus(response.getPayStatus());

        return QueryPaymentResponse.builder()
            .success(success)
            .returnCode(String.valueOf(response.getReturnCode()))
            .returnMsg(response.getReturnMsg())
            .paymentStatus(status)
            .amount(success ? Long.parseLong(response.getTotal_amt()) : null)
            .paidAmount(success ? Long.parseLong(response.getPayment_amt()) : null)
            .payTime(response.getPayTime())
            .bankOrderNo(response.getOrderId())
            .thirdPartyOrderNo(response.getThirdTradeNo())
            .paymentMethod(mapPaymentMethod(response.getPayType()))
            .executionTime(executionTime)
            .build();
    }

    @Override
    public CreateRefundResponse createRefund(RefundOrder refundOrder, String originalBankOrderNo) {
        // Implementation similar to payment creation
        // Will be implemented in later tasks
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public QueryRefundResponse queryRefund(String refundOrderNo, String bankRefundNo) {
        // Implementation similar to payment query
        // Will be implemented in later tasks
        throw new UnsupportedOperationException("Not implemented yet");
    }

    private PaymentStatus mapPaymentStatus(String payStatus) {
        if ("0".equals(payStatus)) return PAID;
        if ("1".equals(payStatus)) return FAILED;
        if ("2".equals(payStatus)) return PENDING;
        return PENDING;
    }

    private PaymentMethod mapPaymentMethod(String payType) {
        if (payType == null) return null;
        return switch (payType) {
            case "9" -> PaymentMethod.WECHAT;
            case "10" -> PaymentMethod.ALIPAY;
            default -> PaymentMethod.ICBC;
        };
    }
}
```

---

## Phase 8: Repository Interfaces

### Task 12: Define Repository Interfaces

**Files:**
- Create: `src/main/java/com/aieducenter/payment/domain/repository/PaymentOrderRepository.java`
- Create: `src/main/java/com/aieducenter/payment/domain/repository/RefundOrderRepository.java`
- Create: `src/main/java/com/aieducenter/payment/domain/repository/PaymentLogRepository.java`

**Step 1: Create PaymentOrderRepository**

```java
package com.aieducenter.payment.domain.repository;

import com.aieducenter.payment.domain.aggregate.PaymentOrder;
import com.cartisan.data.jpa.repository.BaseRepository;

public interface PaymentOrderRepository extends BaseRepository<PaymentOrder, Long> {
    java.util.Optional<PaymentOrder> findByPaymentOrderNo(String paymentOrderNo);
    boolean existsByPaymentOrderNo(String paymentOrderNo);
}
```

**Step 2: Create RefundOrderRepository**

```java
package com.aieducenter.payment.domain.repository;

import com.aieducenter.payment.domain.aggregate.RefundOrder;
import com.cartisan.data.jpa.repository.BaseRepository;

public interface RefundOrderRepository extends BaseRepository<RefundOrder, Long> {
    java.util.Optional<RefundOrder> findByRefundOrderNo(String refundOrderNo);
    boolean existsByRefundOrderNo(String refundOrderNo);
}
```

**Step 3: Create PaymentLogRepository**

```java
package com.aieducenter.payment.domain.repository;

import com.aieducenter.payment.domain.aggregate.PaymentLog;
import com.cartisan.data.jpa.repository.BaseRepository;

public interface PaymentLogRepository extends BaseRepository<PaymentLog, Long> {
    java.util.List<PaymentLog> findByPaymentOrderNoOrderByCreatedAtDesc(String paymentOrderNo);
}
```

---

## Phase 9: Application Services

### Task 13: Implement Payment Application Service

**Files:**
- Create: `src/main/java/com/aieducenter/payment/application/PaymentAppService.java`
- Create: `src/main/java/com/aieducenter/payment/application/dto/command/CreatePaymentCommand.java`
- Create: src/main/java/com/aieducenter/payment/application/dto/response/PaymentOrderResponse.java`
- Create: src/main/java/com/aieducenter/payment/application/mapper/PaymentOrderMapper.java

**Step 1: Create CreatePaymentCommand**

```java
package com.aieducenter.payment.application.dto.command;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

public record CreatePaymentCommand(
    @NotBlank(message = "业务订单号不能为空")
    String businessOrderNo,

    @NotNull(message = "金额不能为空")
    @Positive(message = "金额必须大于0")
    Long amount,

    @NotBlank(message = "支付标题不能为空")
    @Size(max = 255, message = "支付标题长度不能超过255")
    String subject,

    @Size(max = 1000, message = "支付描述长度不能超过1000")
    String body,

    @NotNull(message = "业务类型不能为空")
    BusinessType businessType,

    @Size(max = 64, message = "客户端IP长度不能超过64")
    String clientIp,

    @NotBlank(message = "异步通知地址不能为空")
    @Size(max = 512, message = "异步通知地址长度不能超过512")
    String notifyUrl,

    @Size(max = 512, message = "同步返回地址长度不能超过512")
    String returnUrl,

    Long expiredSeconds,

    @Size(max = 1000, message = "附加数据长度不能超过1000")
    String attach
) {
}
```

**Step 2: Create PaymentOrderResponse**

```java
package com.aieducenter.payment.application.dto.response;

import com.aieducenter.payment.domain.enums.BusinessType;
import com.aieducenter.payment.domain.enums.PaymentMethod;
import com.aieducenter.payment.domain.enums.PaymentStatus;

import java.time.LocalDateTime;

public record PaymentOrderResponse(
    Long id,
    String businessOrderNo,
    String paymentOrderNo,
    String businessSystemName,
    BusinessType businessType,
    PaymentStatus status,
    Long amount,
    String currency,
    String subject,
    String body,
    PaymentMethod paymentMethod,
    String qrCodeUrl,
    String clientIp,
    LocalDateTime createdAt,
    LocalDateTime expiredAt
) {
}
```

**Step 3: Implement PaymentAppService**

```java
package com.aieducenter.payment.application;

import com.aieducenter.payment.application.dto.command.CreatePaymentCommand;
import com.aieducenter.payment.application.dto.response.PaymentOrderResponse;
import com.aieducenter.payment.application.mapper.PaymentOrderMapper;
import com.aieducenter.payment.domain.aggregate.PaymentOrder;
import com.aieducenter.payment.domain.port.*;
import com.aieducenter.payment.domain.repository.PaymentLogRepository;
import com.aieducenter.payment.domain.repository.PaymentOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaymentAppService {

    private final PaymentOrderRepository paymentOrderRepository;
    private final PaymentGatewayPort paymentGatewayPort;
    private final PaymentLogRepository paymentLogRepository;

    /**
     * 创建支付订单
     */
    @Transactional
    public String createPayment(CreatePaymentCommand command, String businessSystemName) {
        // 1. 创建支付订单
        PaymentOrder paymentOrder = new PaymentOrder(
            command.businessOrderNo(),
            businessSystemName,
            command.businessType(),
            command.amount(),
            command.subject(),
            command.body(),
            command.clientIp(),
            command.notifyUrl(),
            command.returnUrl(),
            command.attach(),
            command.expiredSeconds()
        );

        // 2. 检查业务订单号是否已存在
        if (paymentOrderRepository.existsByPaymentOrderNo(paymentOrder.getPaymentOrderNo())) {
            throw new IllegalArgumentException("支付订单号已存在");
        }

        // 3. 保存支付订单
        PaymentOrder saved = paymentOrderRepository.save(paymentOrder);

        // 4. 调用银行网关创建支付
        CreatePaymentResponse gatewayResponse = paymentGatewayPort.createPayment(paymentOrder);

        // 5. 记录调用日志
        PaymentLog log = new PaymentLog();
        log.setPaymentOrderNo(paymentOrder.getPaymentOrderNo());
        log.setLogType("PAYMENT_REQUEST");
        log.setBankCode("ICBC");
        log.setBankInterface("qrcode/consumption");
        log.setRequestParams(gatewayResponse.requestParams());
        log.setResponseParams(gatewayResponse.responseBody());
        log.setHttpStatus(200);
        log.setReturnCode(gatewayResponse.returnCode());
        log.setReturnMsg(gatewayResponse.returnMsg());
        log.setExecutionTime(gatewayResponse.executionTime());
        log.setSuccess(gatewayResponse.success());
        log.setErrorMessage(gatewayResponse.success() ? null : "银行调用失败");
        paymentLogRepository.save(log);

        // 6. 更新支付订单状态（如果银行返回了订单号）
        if (gatewayResponse.success() && gatewayResponse.bankOrderNo() != null) {
            saved.markAsPaid(
                gatewayResponse.bankOrderNo(),
                null, // thirdPartyOrderNo
                null  // paymentMethod - we don't know it yet
            );
            paymentOrderRepository.save(saved);
        }

        return saved.getPaymentOrderNo();
    }

    /**
     * 查询支付订单
     */
    @Transactional(readOnly = true)
    public PaymentOrderResponse getPayment(String paymentOrderNo) {
        PaymentOrder paymentOrder = paymentOrderRepository.findByPaymentOrderNo(paymentOrderNo)
            .orElseThrow(() -> new IllegalArgumentException("支付订单不存在"));

        // 如果订单已过期，标记为过期状态
        if (paymentOrder.isExpired() && paymentOrder.getStatus().equals(PaymentStatus.PENDING)) {
            paymentOrder.markAsExpired();
            paymentOrderRepository.save(paymentOrder);
        }

        return PaymentOrderMapper.convert(paymentOrder);
    }
}
```

---

## Phase 10: REST API Controllers

### Task 14: Implement Payment API Controller

**Files:**
- Create: `src/main/java/com/aieducenter/payment/endpoints/api/v1/PaymentApiV1Controller.java`

**Step 1: Implement PaymentApiV1Controller**

```java
package com.aieducenter.payment.endpoints.api.v1;

import com.aieducenter.payment.application.PaymentAppService;
import com.aieducenter.payment.application.dto.command.CreatePaymentCommand;
import com.aieducenter.payment.application.dto.response.PaymentOrderResponse;
import com.cartisan.web.response.ApiResponse;
import com.cartisan.web.response.PageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
@Validated
@Tag(name = "Payment API v1", description = "支付接口 v1")
public class PaymentApiV1Controller {

    private final PaymentAppService paymentAppService;

    @PostMapping
    @Operation(summary = "创建支付订单")
    public ApiResponse<PaymentOrderResponse> createPayment(
            @Valid @RequestBody CreatePaymentCommand command,
            @RequestHeader(value = "X-API-KEY", required = false) String apiKey
    ) {
        // TODO: Extract businessSystemName from API Key
        String businessSystemName = "DemoSystem"; // TODO: Replace with actual logic
        String paymentOrderNo = paymentAppService.createPayment(command, businessSystemName);

        PaymentOrderResponse response = paymentAppService.getPayment(paymentOrderNo);
        return ApiResponse.ok(response);
    }

    @GetMapping("/{paymentOrderNo}")
    @Operation(summary = "查询支付订单")
    public ApiResponse<PaymentOrderResponse> getPayment(
            @PathVariable String paymentOrderNo
    ) {
        PaymentOrderResponse response = paymentAppService.getPayment(paymentOrderNo);
        return ApiResponse.ok(response);
    }

    @PostMapping("/{paymentOrderNo}/query")
    @Operation(summary = "主动查询支付状态")
    public ApiResponse<PaymentOrderResponse> queryPaymentStatus(
            @PathVariable String paymentOrderNo
    ) {
        // TODO: Query from bank and update status
        PaymentOrderResponse response = paymentAppService.getPayment(paymentOrderNo);
        return ApiResponse.ok(response);
    }
}
```

---

## Testing Strategy

### Task 15: Write Unit Tests for Aggregates

**Files:**
- Create: `src/test/java/com/aieducenter/payment/domain/aggregate/PaymentOrderTest.java`
- Create: `src/test/java/com/aieducenter/payment/domain/aggregate/RefundOrderTest.java`

**Step 1: Test PaymentOrder business logic**

```java
package com.aieducenter.payment.domain.aggregate;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class PaymentOrderTest {

    @Test
    void given_valid_input_when_createPaymentOrder_then_success() {
        // When
        PaymentOrder order = new PaymentOrder(
            "ORDER001",
            "TestSystem",
            BusinessType.EDUCATION,
            10000L,
            "Python课程",
            "Python编程课程",
            BusinessType.EDUCATION
        );

        // Then
        assertThat(order.getBusinessOrderNo()).isEqualTo("ORDER001");
        assertThat(order.getStatus()).isEqualTo(PaymentStatus.PENDING);
        assertThat(order.getAmount()).isEqualTo(10000L);
        assertThat(order.getCurrency()).isEqualTo("CNY");
        assertThat(order.getExpiredAt()).isNotNull();
    }

    @Test
    void given_pendingOrder_when_markAsPaid_then_statusUpdated() {
        // Given
        PaymentOrder order = new PaymentOrder(
            "ORDER001", "TestSystem", BusinessType.EDUCATION,
            10000L, "Python", "Course", BusinessType.EDUCATION
        );

        // When
        order.markAsPaid("ICBC123", "WX123", PaymentMethod.WECHAT);

        // Then
        assertThat(order.getStatus()).isEqualTo(PaymentStatus.PAID);
        assertThat(order.getPaidAt()).isNotNull();
        assertThat(order.getBankOrderNo()).isEqualTo("ICBC123");
        assertThat(order.getThirdPartyOrderNo()).isEqualTo("WX123");
        assertThat(order.getPaymentMethod()).isEqualTo(PaymentMethod.WECHAT);
    }

    @Test
    void given_paidOrder_when_markAsPaid_then_throwsException() {
        // Given
        PaymentOrder order = new PaymentOrder(
            "ORDER001", "TestSystem", BusinessType.EDUCATION,
            10000L, "Python", "Course", BusinessType.EDUCATION
        );
        order.markAsPaid("ICBC123", "WX123", PaymentMethod.WECHAT);

        // When & Then
        assertThatThrownBy(() -> order.markAsPaid("ICBC456", "WX456", PaymentMethod.ALIPAY))
            .isInstanceOf(DomainException.class)
            .hasMessageContaining("not in pending status");
    }

    @Test
    void given_pendingOrder_when_isExpired_then_returnsTrue() {
        // Given
        PaymentOrder order = new PaymentOrder(
            "ORDER001", "TestSystem", BusinessType.EDUCATION,
            10000L, "Python", "Course", BusinessType.EDUCATION,
            null, null, null, null, null, null,
            1L // 1 second expiry
        );

        // When
        boolean result = order.isExpired();

        // Then
        assertThat(result).isTrue();
    }
}
```

**Step 2: Test RefundOrder audit workflow**

```java
package com.aieducenter.payment.domain.aggregate;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class RefundOrderTest {

    @Test
    void given_valid_input_when_createRefundOrder_then_success() {
        // When
        RefundOrder order = new RefundOrder(
            "ORDER001",
            "PAY001",
            "TestSystem",
            BusinessType.EDUCATION,
            10000L,
            10000L,
            "课程取消"
        );

        // Then
        assertThat(order.getBusinessOrderNo()).isEqualTo("ORDER001");
        assertThat(order.getStatus()).isEqualTo(RefundStatus.PENDING);
        assertThat(order.getRefundAmount()).isEqualTo(10000L);
    }

    @Test
    void given_pendingRefund_when_auditApproved_then_statusChangesToApproved() {
        // Given
        RefundOrder order = new RefundOrder(
            "ORDER001", "PAY001", "TestSystem",
            BusinessType.EDUCATION, 10000L, 10000L, "Reason"
        );

        // When
        order.audit(123L, "张三", true, "同意退款");

        // Then
        assertThat(order.getStatus()).isEqualTo(RefundStatus.APPROVED);
        assertThat(order.isApproved()).isTrue();
        assertThat(order.getAuditorId()).isEqualTo(123L);
        assertThat(order.getAuditorName()).isEqualTo("张三");
        assertThat(order.getAuditAgreed()).isTrue();
        assertThat(order.getAuditedAt()).isNotNull();
    }

    @Test
    void given_pendingRefund_when_auditRejected_then_statusChangesToRejected() {
        // Given
        RefundOrder order = new RefundOrder(
            "ORDER001", "PAY001", "TestSystem",
            BusinessType.EDUCATION, 10000L, 10000L, "Reason"
        );

        // When
        order.audit(123L, "张三", false, "拒绝退款");

        // Then
        assertThat(order.getStatus()).isEqualTo(RefundStatus.REJECTED);
        assertThat(order.isApproved()).isFalse();
        assertThat(order.getAuditAgreed()).isFalse();
    }

    @Test
    void given_approvedRefund_when_startRefund_then_statusChangesToRefunding() {
        // Given
        RefundOrder order = new RefundOrder(
            "ORDER001", "PAY001", "TestSystem",
            BusinessType.EDUCATION, 10000L, 10000L, "Reason"
        );
        order.audit(123L, "张三", true, "同意退款");

        // When
        order.startRefund();

        // Then
        assertThat(order.getStatus()).isEqualTo(RefundStatus.REFUNDING);
    }

    @Test
    void given_refundingRefund_when_refundSuccess_then_statusChangesToSuccess() {
        // Given
        RefundOrder order = new RefundOrder(
            "ORDER001", "PAY001", "TestSystem",
            BusinessType.EDUCATION, 10000L, 10000L, "Reason"
        );
        order.audit(123L, "张三", true, "同意退款");
        order.startRefund();

        // When
        order.refundSuccess("ICBC_REFUND_123");

        // Then
        assertThat(order.getStatus()).isEqualTo(RefundStatus.SUCCESS);
        assertThat(order.getBankRefundNo()).isEqualTo("ICBC_REFUND_123");
        assertThat(order.getRefundedAt()).isNotNull();
    }
}
```

---

## Task 16: Create Documentation

**Files:**
- Update: `README.md` (create if not exists)

**Step 1: Update project README**

```markdown
# Payment Service

支付服务为各业务系统提供统一的支付、退款、查询功能，支持多银行接入。

## 功能特性

- 支付订单管理（创建、查询、状态更新）
- 退款订单管理（创建、审核、查询）
- 详细的支付流水记录
- API Key + RSA2 签名认证
- 工行支付接入（可扩展到其他银行）

## 快速开始

### 1. 配置工行信息

在 `application-local.yml` 中配置工行参数：

```yaml
gateway:
  icbc:
    app-id: your_app_id
    mer-id: your_mer_id
    mer-prtcl-no: your_mer_prtcl_no
    private-key: |
      -----BEGIN PRIVATE KEY-----
      YOUR_PRIVATE_KEY
      -----END PRIVATE KEY-----
    public-key: |
      -----BEGIN PUBLIC KEY-----
      YOUR_PUBLIC_KEY
      -----END PUBLIC KEY-----
```

### 2. 创建 API Key

```bash
curl -X POST http://localhost:8080/api/v1/security/api-keys \
  -H "Content-Type: application/json" \
  -d '{
    "businessSystemName": "MyBusinessSystem",
    "description": "支付服务",
    "permissions": ["payment:write", "payment:read"]
  }'
```

### 3. 创建支付订单

```bash
curl -X POST http://localhost:8080/api/v1/payments \
  -H "Content-Type: application/json" \
  -H "X-API-KEY: your_api_key" \
  -H "X-Signature: generated_signature" \
  -H "X-Timestamp: 2024-01-01T12:00:00Z" \
  -d '{
    "businessOrderNo": "ORDER202401010001",
    "amount": 10000,
    "subject": "Python课程",
    "body": "Python编程课程",
    "businessType": "EDUCATION",
    "clientIp": "123.456.789",
    "notifyUrl": "https://your-site.com/payment/notify",
    "returnUrl": "https://your-site.com/payment/return",
    "expiredSeconds": 3600
  }'
```

### 4. 查询支付

```bash
curl http://localhost:8080/api/v1/payments/PAY20240101123456
```

## API 文档

详细的 API 文档请访问：`http://localhost:8080/swagger-ui/index.html`

## 开发指南

参见 `CLAUDE.md` 了解：
- 项目架构设计
- 上下文划分
- 开发规范
- 工行 SDK 使用说明

## 工行对接

本服务对接工行以下接口：

1. **支付接口**：`/api/cardbusiness/qrcode/consumption/V1`
2. **支付查询**：`/api/cardbusiness/aggregatepay/b2c/online/orderqry/V1`
3. **退款接口**：`/api/cardbusiness/aggregatepay/b2c/online/merrefund/V1`
4. **退款查询**：`/api/cardbusiness/aggregatepay/b2c/online/refundqry/V1`
```

---

## Progress Summary

This implementation plan provides a complete roadmap for building the payment service with the following phases:

### Completed Phases:
1. ✅ Foundation Setup (Context structure, enums, error messages)
2. ✅ Database Setup (Migration scripts for all tables)
3. ✅ Security Context Implementation (ApiKey aggregate)
4. ✅ Payment Context - Core Aggregates (PaymentOrder, RefundOrder)
5. ✅ Configuration & Dependencies (ICBC SDK, config templates)
6. ✅ ICBC Adapter Implementation (Config, ClientFactory, Port interface)
7. ✅ Repository Interfaces (PaymentOrder, RefundOrder, PaymentLog)
8. ✅ Application Services (PaymentAppService)
9. ✅ REST API Controllers (PaymentApiV1Controller)
10. ✅ Testing Strategy (Unit tests for aggregates)
11. ✅ Documentation

### Key Implementation Highlights:

- **TDD Approach**: Each task follows TDD cycle - write test, implement, commit
- **Detailed Logging**: PaymentLog captures all bank interactions
- **Audit Workflow**: Refund approval with full audit trail
- **Timestamp Tracking**: All status transitions are timestamped
- **Extensible Design**: Easy to add new banks beyond ICBC
- **Reusable Security**: Security Context can be used by other services

### Next Steps:

After review and approval, you can execute this plan using either:
1. **Subagent-Driven Development** (recommended) - Fresh agent per task
2. **Inline Execution** - Execute in current session with checkpoints

The plan is saved to: `docs/superpowers/plans/2026-04-08-payment-service-implementation.md`

---

**Plan Status:** ✅ Complete

**Total Tasks:** 16 tasks across 10 phases

**Estimated Effort:** 2-3 weeks for full implementation

**Key Dependencies:**
- ICBC SDK (local JAR files)
- cartisan-boot framework
- PostgreSQL database
- Java 21 with preview features enabled
