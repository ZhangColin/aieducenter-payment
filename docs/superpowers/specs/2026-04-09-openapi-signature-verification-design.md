# OpenAPI 签名验证机制设计

> 日期：2026-04-09
> 状态：已确认，待实现

## 概述

为开放平台 API 实现基于 HMAC-SHA256 的请求签名验证机制。通过自定义注解 `@RequireSignature` 标记需要验证的 Controller 方法，由 HandlerInterceptor 拦截并执行多重校验，确保请求的完整性、防篡改和防重放。

## 设计决策

| 项 | 决策 | 理由 |
|---|------|------|
| 签名算法 | HMAC-SHA256（对称） | 实现简单，性能好，适合服务端分发密钥的场景 |
| Body 参与方式 | Body Digest（SHA-256 hash） | 避免嵌套 JSON 展平的复杂性，排序拼接只处理固定参数 |
| 触发方式 | `@RequireSignature` 注解 | 灵活精确，只影响指定接口 |
| 实现层 | HandlerInterceptor | 能直接读取注解，与 Spring MVC 深度集成 |
| 防重放 | Timestamp + Nonce（Redis） | 双重防重放，适合分布式架构 |
| 所在上下文 | openapi | 是 ApiKey 管理的延伸，其他上下文通过注解自然复用 |
| 编码格式 | 小写 hex 字符串 | 避免 Base64 的特殊字符问题 |

## 核心组件

### 新增文件清单

```
openapi 上下文内新增：
├── domain/
│   ├── service/
│   │   └── SignatureService.java               ← 签名计算/验证核心领域服务
│   ├── port/
│   │   ├── SignatureCalculator.java             ← 签名计算端口接口（领域层定义）
│   │   └── NonceRepository.java                ← Nonce 存储端口（防重放）
│   └── exception/
│       └── SignatureVerificationException.java  ← 签名验证异常
├── application/
│   └── ApiKeyQueryAppService.java               ← ApiKey 查询应用服务（含 apiSecret，供验证使用）
├── infrastructure/
│   ├── signature/
│   │   └── HmacSha256SignatureCalculator.java   ← HMAC-SHA256 签名计算实现（实现 SignatureCalculator 端口）
│   └── redis/
│       └── RedisNonceRepository.java            ← Redis Nonce 存储实现（TTL 通过构造注入）
└── web/
    ├── RequireSignature.java                    ← 自定义注解
    ├── SignatureVerificationInterceptor.java    ← 签名验证拦截器
    ├── CachingRequestBodyFilter.java            ← 请求体缓存过滤器（自定义实现）
    └── WebMvcConfig.java                        ← 注册拦截器和过滤器
```

### 组件职责

**RequireSignature** — 自定义注解，标记需要签名验证的 Controller 方法或类。

**SignatureService** — 领域服务，负责签名计算和验证的核心逻辑。依赖 `SignatureCalculator` 端口接口和 `NonceRepository` 端口接口，不直接依赖基础设施实现。

**SignatureCalculator** — 领域端口接口，定义签名计算方法。`HmacSha256SignatureCalculator` 是其基础设施实现。

**SignatureVerificationInterceptor** — HandlerInterceptor，检查注解、提取参数、调用应用层和领域服务、处理结果。

**CachingRequestBodyFilter** — Servlet Filter，自定义实现（不使用 Spring 的 ContentCachingRequestWrapper）。在 Filter 层将 body 字节读入 byte[] 并缓存，使 body 可被 Interceptor 和 Controller 分别读取。

**NonceRepository** — 领域端口接口，定义 Nonce 的存在性检查和存储。TTL 由实现类通过构造注入获取。

**RedisNonceRepository** — Redis 实现，TTL 通过 `@Value` 从配置注入，使用 SETNX + EXPIRE 原子操作。

**HmacSha256SignatureCalculator** — HMAC-SHA256 签名计算的具体实现，实现 `SignatureCalculator` 端口。

**ApiKeyQueryAppService** — 应用服务，提供 `findByApiKeyForVerification(String apiKey)` 方法，返回包含 apiSecret 的完整 ApiKey 聚合（与现有的返回 DTO 且屏蔽 secret 的查询方法不同）。

## 签名计算规则

### 客户端流程

```
Step 1: 计算 Body Digest
  bodyDigest = hex(SHA-256(requestBody.getBytes(UTF-8)))
  GET 请求无 body: bodyDigest = hex(SHA-256(""))

Step 2: 收集参与签名的参数
  - 系统参数: appId, timestamp, nonce
  - Body 参数: bodyDigest
  - Query 参数: URL 中所有 ?key=value 参数（值需 URL Decode）
    - 多值参数（?key=a&key=b）：按值字典序排列后拼接为 key=a&key=b
    - 同名 key 出现多次时，值按字典序排列

Step 3: 按 key 字典序排列，拼接为 key1=value1&key2=value2&...
  示例: appId=test001&bodyDigest=a1b2c3d4...&nonce=uuid-xxx&timestamp=1710000000

Step 4: 计算签名
  sign = hex(HMAC-SHA256(stringToSign, appSecret.getBytes(UTF-8)))
```

### HTTP Headers

| Header | 说明 | 示例 |
|--------|------|------|
| `X-App-Id` | API Key（现有 apiKey 字段） | `ak_a1b2c3d4e5f6...` |
| `X-Timestamp` | 当前时间戳（秒级） | `1710000000` |
| `X-Nonce` | UUID 随机串 | `550e8400-e29b-41d4...` |
| `X-Sign` | HMAC-SHA256 签名结果 | `f7c3bc1d808e04732adf...` |
| `X-Body-Digest` | Body SHA-256 摘要 | `2c26b46b68ffc68ff99b...` |

### 服务端验证顺序

1. **非空校验** — 检查 5 个 Header 是否齐全
2. **时间戳校验** — 与服务器时间差超过容忍窗口则拒绝
3. **Nonce 防重放** — Redis SETNX 检查，已存在则拒绝
4. **身份校验** — 根据 X-App-Id 查询 ApiKey，不存在或已禁用则拒绝
5. **Body Digest 校验** — 服务端计算 body SHA-256，与 X-Body-Digest 比对
6. **签名校验** — 服务端用同样规则重算签名，与 X-Sign 比对

## 接口设计

### 自定义注解

```java
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequireSignature {
}
```

- 标在方法上：该方法需要签名验证
- 标在类上（Controller）：该 Controller 所有方法都需要签名验证

### 领域端口：SignatureCalculator

```java
public interface SignatureCalculator {
    /**
     * 计算签名
     * @param stringToSign 待签名字符串（已排序拼接）
     * @param secret 签名密钥
     * @return 小写 hex 格式的签名结果
     */
    String calculate(String stringToSign, String secret);
}
```

### 领域服务：SignatureService

```java
public class SignatureService {
    // 依赖注入
    private final SignatureCalculator signatureCalculator;
    private final NonceRepository nonceRepository;

    /**
     * 验证请求签名
     * @param apiKey 已查询到的 ApiKey 聚合（由 ApiKeyQueryAppService 查询）
     * @param timestamp 请求时间戳
     * @param nonce 请求随机串
     * @param sign 客户端签名
     * @param clientBodyDigest 客户端发送的 body digest
     * @param serverBodyDigest 服务端计算的 body digest
     * @param queryParams URL query 参数（已处理多值情况）
     * @param timestampTolerance 时间戳容忍窗口（秒）
     * @throws SignatureVerificationException 验证失败
     */
    public void verify(ApiKey apiKey, String timestamp, String nonce,
                       String sign, String clientBodyDigest, String serverBodyDigest,
                       Map<String, String> queryParams, long timestampTolerance);

    /**
     * 计算签名
     */
    public String calculateSign(String appId, String bodyDigest, String nonce,
                                String timestamp, Map<String, String> queryParams,
                                String appSecret);
}
```

### 领域端口：NonceRepository

```java
public interface NonceRepository {
    /**
     * 检查 Nonce 是否已存在，不存在则存储
     * TTL 由实现类通过构造注入从配置获取
     * @return true = 已存在（重复请求），false = 不存在（已存储）
     */
    boolean existsAndStore(String nonce);
}
```

### Controller 使用方式

```java
@RequireSignature
@PostMapping("/api/v1/payments")
public Result<PaymentResponse> createPayment(
        @RequestBody CreatePaymentCommand command,
        HttpServletRequest request) {
    String businessSystemName = (String) request.getAttribute("verifiedBusinessSystemName");
    // 业务逻辑...
}
```

验证通过后，拦截器将以下信息放入 request attribute：
- `verifiedAppId` — 验证通过的 API Key
- `verifiedBusinessSystemName` — 业务系统名称

## 基础设施

### CachingRequestBodyFilter

- `@Order(Ordered.HIGHEST_PRECEDENCE)` 确保最先执行
- **自定义实现**（不使用 Spring 的 `ContentCachingRequestWrapper`，因为它在首次读取后才缓存，不适合拦截器场景）
- 在 Filter 层读取原始 body 字节到 `byte[]`，包装为自定义 `CachedBodyHttpServletRequest`
- 自定义 Wrapper 重写 `getInputStream()` 和 `getReader()`，每次返回基于缓存字节的新流
- 使 body 可被 Interceptor（计算 digest）和 Controller（业务处理）分别读取

### RedisNonceRepository

- Key 格式：`openapi:nonce:{nonce}`
- 使用 `SETNX + EXPIRE` 原子操作
- TTL 通过 `@Value("${openapi.signature.nonce-ttl}")` 构造注入，不从领域层传入

### WebMvcConfig

- 拦截路径：`/api/v1/**`（仅为性能优化，避免拦截静态资源等无关路径）
- **注解是唯一的验证触发条件**：拦截器在 `preHandle` 中检查方法/类是否有 `@RequireSignature`，有才执行验证，无则直接放行
- 排除路径（双重保险）：
  - `/api/v1/openapi/**` — API Key 管理接口
  - `/api/v1/payment/callbacks/**` — 工行回调接口（工行不携带签名）

## 配置参数

```yaml
openapi:
  signature:
    timestamp-tolerance: 300     # 时间戳容忍窗口（秒），默认 5 分钟
    nonce-ttl: 300              # Nonce 过期时间（秒），与容忍窗口一致
    enabled: true               # 开关，方便开发环境关闭
```

## 错误响应

验证失败返回 HTTP 401，body 格式：

```json
{
  "code": "OAS_1XX",
  "message": "签名验证失败：时间戳过期",
  "data": null
}
```

### 错误码（在 OpenApiMessage 枚举中新增）

| 枚举值 | HTTP 状态 | 含义 |
|-------|----------|------|
| SIGNATURE_PARAMS_MISSING | 401 | 缺少必要参数（appId/timestamp/nonce/sign/bodyDigest） |
| SIGNATURE_TIMESTAMP_EXPIRED | 401 | 时间戳过期 |
| SIGNATURE_NONCE_DUPLICATED | 401 | 重复请求（Nonce 已存在） |
| SIGNATURE_API_KEY_INVALID | 401 | API Key 无效或已禁用 |
| SIGNATURE_BODY_DIGEST_MISMATCH | 401 | Body Digest 不匹配 |
| SIGNATURE_MISMATCH | 401 | 签名不匹配 |

## 与现有代码的集成

### ApiKey 聚合字段映射

无需修改现有 ApiKey 聚合，字段直接复用：

| 现有字段 | 方案用途 |
|---------|---------|
| `apiKey` | 对应 `appId`，通过 Header `X-App-Id` 传递 |
| `apiSecret` | 对应 `appSecret`，用于 HMAC-SHA256 计算 |
| `status` | 验证时检查 ACTIVE/DISABLED |
| `businessSystemName` | 验证通过后放入 request attribute |

### ApiKey 查找机制

拦截器不直接调用 `ApiKeyRepository`，而是通过应用层 `ApiKeyQueryAppService` 查询：

```java
// 应用层新增方法
public ApiKey findByApiKeyForVerification(String apiKey) {
    return apiKeyRepository.findByApiKey(apiKey)
        .filter(key -> key.getStatus() == ApiKeyStatus.ACTIVE)
        .orElseThrow(() -> new SignatureVerificationException(SIGNATURE_API_KEY_INVALID));
}
```

这与现有的 `ApiKeyManagementAppService` 区分开：现有服务返回 DTO 且屏蔽 apiSecret，新服务返回完整聚合供签名验证使用。

### Payment/Refund Controller 改造

**需要 `@RequireSignature` 的接口**（涉及写操作或敏感数据）：

| Controller | 方法 | 改造说明 |
|-----------|------|---------|
| PaymentApiV1Controller | `createPayment()` | 移除 `@RequestHeader("X-API-KEY")`，加 `@RequireSignature` |
| PaymentApiV1Controller | `getPayment()` | 加 `@RequireSignature`（查询也需要身份验证） |
| PaymentApiV1Controller | `queryPaymentStatus()` | 加 `@RequireSignature` |
| RefundApiV1Controller | `createRefund()` | 移除 `@RequestHeader("X-API-KEY")`，加 `@RequireSignature` |
| RefundApiV1Controller | `auditRefund()` | 加 `@RequireSignature` |
| RefundApiV1Controller | `getRefund()` | 加 `@RequireSignature` |

**不需要 `@RequireSignature` 的接口**：

| Controller | 方法 | 原因 |
|-----------|------|------|
| OpenApiApiV1Controller | 所有方法 | 内部管理接口 |
| PaymentCallbackApiV1Controller | 所有方法 | 工行回调，不携带签名 |

改造后获取身份信息方式：
```java
String businessSystemName = (String) request.getAttribute("verifiedBusinessSystemName");
```

### 跨上下文依赖说明

`@RequireSignature` 注解和拦截器位于 `openapi` 上下文的 `web` 包中，Payment/Refund Controller（位于 `payment` 上下文）需要导入该注解。这是**有意的跨上下文依赖**：openapi 上下文作为基础设施性的认证能力，被其他业务上下文依赖是合理的。

### openapi 管理 Controller

API Key CRUD 接口不加 `@RequireSignature`，保持现有访问方式。

## 安全注意事项

1. 所有 API 必须通过 HTTPS 传输
2. AppSecret 绝不在网络中传输
3. 日志中不记录完整的 AppSecret 和签名
4. 开发环境可通过配置关闭签名验证
