# OpenAPI 签名验证机制 实现计划

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** 为开放平台 API 实现 HMAC-SHA256 签名验证机制，通过 `@RequireSignature` 注解标记需要验证的 Controller 方法。

**Architecture:** DDD 六边形架构。领域层定义端口接口（SignatureCalculator、NonceRepository）和领域服务（SignatureService），基础设施层提供 HMAC-SHA256 和 Redis 实现。HandlerInterceptor + 自定义注解驱动验证，CachingRequestBodyFilter 解决 body 重复读取问题。

**Tech Stack:** Spring Boot 3.x, Spring Data JPA, Spring Data Redis, Hutool, JUnit 5 + AssertJ + Mockito

**Spec:** `docs/superpowers/specs/2026-04-09-openapi-signature-verification-design.md`

---

## File Structure

### 新建文件

| 文件 | 职责 |
|------|------|
| `openapi/domain/port/SignatureCalculator.java` | 签名计算端口接口 |
| `openapi/domain/port/NonceRepository.java` | Nonce 防重放端口接口 |
| `openapi/domain/service/SignatureService.java` | 签名验证核心领域服务 |
| `openapi/application/ApiKeyQueryAppService.java` | 签名验证专用的 ApiKey 查询 |
| `openapi/infrastructure/signature/HmacSha256SignatureCalculator.java` | HMAC-SHA256 实现 |
| `openapi/infrastructure/redis/RedisNonceRepository.java` | Redis Nonce 存储 |
| `openapi/web/RequireSignature.java` | 自定义注解 |
| `openapi/web/CachingRequestBodyFilter.java` | 请求体缓存 Filter + CachedBodyHttpServletRequest |
| `openapi/web/SignatureVerificationInterceptor.java` | 签名验证拦截器 |
| `openapi/web/OpenApiSignatureConfig.java` | 配置属性类 |

### 修改文件

| 文件 | 改动 |
|------|------|
| `openapi/domain/error/OpenApiMessage.java` | 新增 6 个签名验证错误码 |
| `config/WebConfig.java` | 新增 Interceptor 注册 + Filter 注册 |
| `payment/endpoints/api/v1/PaymentApiV1Controller.java` | 移除 X-API-KEY，加 @RequireSignature |
| `payment/endpoints/api/v1/RefundApiV1Controller.java` | 移除 X-API-KEY，加 @RequireSignature |
| `application-local.yml` | 新增 openapi.signature 配置 |

### 测试文件

| 文件 | 测试内容 |
|------|---------|
| `openapi/domain/service/SignatureServiceTest.java` | 签名计算和验证逻辑 |
| `openapi/infrastructure/signature/HmacSha256SignatureCalculatorTest.java` | HMAC-SHA256 计算 |
| `openapi/web/SignatureVerificationInterceptorTest.java` | 拦截器行为 |

---

## Task 1: 添加签名验证错误码

**Files:**
- Modify: `src/main/java/com/aieducenter/openapi/domain/error/OpenApiMessage.java`

- [ ] **Step 1: 在 OpenApiMessage 枚举中添加签名验证错误码**

在 `PERMISSION_DENIED` 之前添加签名验证相关错误码：

```java
// ========== 签名验证错误 (401) ==========
SIGNATURE_PARAMS_MISSING(401, "OAS_040", "缺少签名验证必要参数"),
SIGNATURE_TIMESTAMP_EXPIRED(401, "OAS_041", "请求时间戳已过期"),
SIGNATURE_NONCE_DUPLICATED(401, "OAS_042", "重复的请求"),
SIGNATURE_API_KEY_INVALID(401, "OAS_043", "API Key 无效或已禁用"),
SIGNATURE_BODY_DIGEST_MISMATCH(401, "OAS_044", "请求体摘要不匹配"),
SIGNATURE_MISMATCH(401, "OAS_045", "签名不匹配"),
```

- [ ] **Step 2: 编译验证**

Run: `mvn compile -q`
Expected: BUILD SUCCESS

- [ ] **Step 3: Commit**

```bash
git add src/main/java/com/aieducenter/openapi/domain/error/OpenApiMessage.java
git commit -m "feat(openapi): add signature verification error codes to OpenApiMessage"
```

---

## Task 2: 创建领域端口接口

**Files:**
- Create: `src/main/java/com/aieducenter/openapi/domain/port/SignatureCalculator.java`
- Create: `src/main/java/com/aieducenter/openapi/domain/port/NonceRepository.java`

- [ ] **Step 1: 创建 SignatureCalculator 端口**

```java
package com.aieducenter.openapi.domain.port;

/**
 * 签名计算端口
 */
public interface SignatureCalculator {
    /**
     * 计算签名
     *
     * @param stringToSign 待签名字符串（已排序拼接）
     * @param secret       签名密钥
     * @return 小写 hex 格式的签名结果
     */
    String calculate(String stringToSign, String secret);
}
```

- [ ] **Step 2: 创建 NonceRepository 端口**

```java
package com.aieducenter.openapi.domain.port;

/**
 * Nonce 存储端口（防重放）
 */
public interface NonceRepository {
    /**
     * 检查 Nonce 是否已存在，不存在则存储
     * TTL 由实现类通过构造注入从配置获取
     *
     * @return true = 已存在（重复请求），false = 不存在（已存储）
     */
    boolean existsAndStore(String nonce);
}
```

- [ ] **Step 3: 编译验证**

Run: `mvn compile -q`
Expected: BUILD SUCCESS

- [ ] **Step 4: Commit**

```bash
git add src/main/java/com/aieducenter/openapi/domain/port/
git commit -m "feat(openapi): add SignatureCalculator and NonceRepository port interfaces"
```

---

## Task 3: SignatureService 领域服务 - TDD

**Files:**
- Test: `src/test/java/com/aieducenter/openapi/domain/service/SignatureServiceTest.java`
- Create: `src/main/java/com/aieducenter/openapi/domain/service/SignatureService.java`

- [ ] **Step 1: 编写 SignatureServiceTest 测试类**

```java
package com.aieducenter.openapi.domain.service;

import com.aieducenter.openapi.domain.aggregate.ApiKey;
import com.aieducenter.openapi.domain.enums.ApiKeyStatus;
import com.aieducenter.openapi.domain.error.OpenApiMessage;
import com.aieducenter.openapi.domain.port.NonceRepository;
import com.aieducenter.openapi.domain.port.SignatureCalculator;
import com.cartisan.core.exception.ApplicationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("SignatureService 签名验证领域服务测试")
class SignatureServiceTest {

    @Mock
    private SignatureCalculator signatureCalculator;

    @Mock
    private NonceRepository nonceRepository;

    private SignatureService signatureService;

    private ApiKey testApiKey;

    @BeforeEach
    void setUp() throws Exception {
        signatureService = new SignatureService(signatureCalculator, nonceRepository);
        // 使用反射创建 ApiKey 实例（绕过 JPA 要求）
        testApiKey = createTestApiKey("TESTAPPKEY12345678901234567890", "testSecret", "TestSystem");
    }

    private ApiKey createTestApiKey(String apiKey, String apiSecret, String businessSystemName) throws Exception {
        // 使用 ApiKey 的三参构造函数创建实例，再用反射覆盖 apiKey 和 apiSecret
        ApiKey key = new ApiKey(businessSystemName, "test", null);

        var apiKeyField = ApiKey.class.getDeclaredField("apiKey");
        apiKeyField.setAccessible(true);
        apiKeyField.set(key, apiKey);

        var apiSecretField = ApiKey.class.getDeclaredField("apiSecret");
        apiSecretField.setAccessible(true);
        apiSecretField.set(key, apiSecret);

        return key;
    }

    @Test
    @DisplayName("给定有效参数，计算签名时应该正确排序拼接并调用 SignatureCalculator")
    void given_validParams_when_calculateSign_then_correctlySortsAndConcatenates() {
        // Given
        when(signatureCalculator.calculate(anyString(), eq("testSecret"))).thenReturn("calculated_sign");

        // When
        String result = signatureService.calculateSign(
            "TESTAPPKEY12345678901234567890",
            "bodyDigest123",
            "nonce-abc",
            "1710000000",
            Map.of("name", "zhangsan"),
            "testSecret"
        );

        // Then
        assertThat(result).isEqualTo("calculated_sign");
        verify(signatureCalculator).calculate(
            eq("appId=TESTAPPKEY12345678901234567890&bodyDigest=bodyDigest123&name=zhangsan&nonce=nonce-abc&timestamp=1710000000"),
            eq("testSecret")
        );
    }

    @Test
    @DisplayName("给定无 query 参数，计算签名时应该只包含系统参数和 bodyDigest")
    void given_noQueryParams_when_calculateSign_then_onlySystemParams() {
        // Given
        when(signatureCalculator.calculate(anyString(), eq("secret"))).thenReturn("sign");

        // When
        signatureService.calculateSign("appId", "digest", "nonce", "1234", Collections.emptyMap(), "secret");

        // Then
        verify(signatureCalculator).calculate(
            eq("appId=appId&bodyDigest=digest&nonce=nonce&timestamp=1234"),
            eq("secret")
        );
    }

    @Test
    @DisplayName("给定有效请求，验证签名时应该通过")
    void given_validRequest_when_verify_then_pass() {
        // Given
        long currentTimestamp = System.currentTimeMillis() / 1000;
        when(nonceRepository.existsAndStore("nonce-123")).thenReturn(false);
        when(signatureCalculator.calculate(anyString(), eq("testSecret"))).thenReturn("expected_sign");

        // When & Then - should not throw
        assertThatCode(() -> signatureService.verify(
            testApiKey,
            String.valueOf(currentTimestamp),
            "nonce-123",
            "expected_sign",
            "bodyDigest123",
            "bodyDigest123",
            Collections.emptyMap(),
            300
        )).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("给定过期时间戳，验证签名时应该抛出 TIMESTAMP_EXPIRED")
    void given_expiredTimestamp_when_verify_then_throwTimestampExpired() {
        // Given - 10 分钟前的时间戳
        long expiredTimestamp = System.currentTimeMillis() / 1000 - 600;

        // When & Then
        assertThatThrownBy(() -> signatureService.verify(
            testApiKey,
            String.valueOf(expiredTimestamp),
            "nonce-123",
            "sign",
            "digest",
            "digest",
            Collections.emptyMap(),
            300
        ))
        .isInstanceOf(ApplicationException.class)
        .hasMessageContaining(OpenApiMessage.SIGNATURE_TIMESTAMP_EXPIRED.message());
    }

    @Test
    @DisplayName("给定重复 Nonce，验证签名时应该抛出 NONCE_DUPLICATED")
    void given_duplicatedNonce_when_verify_then_throwNonceDuplicated() {
        // Given
        long currentTimestamp = System.currentTimeMillis() / 1000;
        when(nonceRepository.existsAndStore("nonce-dup")).thenReturn(true);

        // When & Then
        assertThatThrownBy(() -> signatureService.verify(
            testApiKey,
            String.valueOf(currentTimestamp),
            "nonce-dup",
            "sign",
            "digest",
            "digest",
            Collections.emptyMap(),
            300
        ))
        .isInstanceOf(ApplicationException.class)
        .hasMessageContaining(OpenApiMessage.SIGNATURE_NONCE_DUPLICATED.message());
    }

    @Test
    @DisplayName("给定不匹配的 bodyDigest，验证签名时应该抛出 BODY_DIGEST_MISMATCH")
    void given_mismatchedBodyDigest_when_verify_then_throwBodyDigestMismatch() {
        // Given
        long currentTimestamp = System.currentTimeMillis() / 1000;
        when(nonceRepository.existsAndStore("nonce-123")).thenReturn(false);

        // When & Then
        assertThatThrownBy(() -> signatureService.verify(
            testApiKey,
            String.valueOf(currentTimestamp),
            "nonce-123",
            "sign",
            "clientDigest",
            "serverDigest",
            Collections.emptyMap(),
            300
        ))
        .isInstanceOf(ApplicationException.class)
        .hasMessageContaining(OpenApiMessage.SIGNATURE_BODY_DIGEST_MISMATCH.message());
    }

    @Test
    @DisplayName("给定不匹配的签名，验证签名时应该抛出 SIGNATURE_MISMATCH")
    void given_mismatchedSignature_when_verify_then_throwSignatureMismatch() {
        // Given
        long currentTimestamp = System.currentTimeMillis() / 1000;
        when(nonceRepository.existsAndStore("nonce-123")).thenReturn(false);
        when(signatureCalculator.calculate(anyString(), anyString())).thenReturn("server_sign");

        // When & Then
        assertThatThrownBy(() -> signatureService.verify(
            testApiKey,
            String.valueOf(currentTimestamp),
            "nonce-123",
            "client_sign",
            "sameDigest",
            "sameDigest",
            Collections.emptyMap(),
            300
        ))
        .isInstanceOf(ApplicationException.class)
        .hasMessageContaining(OpenApiMessage.SIGNATURE_MISMATCH.message());
    }
}
```

- [ ] **Step 2: 运行测试验证失败**

Run: `mvn test -Dtest=SignatureServiceTest -pl . -q`
Expected: FAIL (class not found)

- [ ] **Step 3: 实现 SignatureService**

```java
package com.aieducenter.openapi.domain.service;

import com.aieducenter.openapi.domain.aggregate.ApiKey;
import com.aieducenter.openapi.domain.error.OpenApiMessage;
import com.aieducenter.openapi.domain.port.NonceRepository;
import com.aieducenter.openapi.domain.port.SignatureCalculator;
import com.cartisan.core.exception.ApplicationException;
import com.cartisan.core.stereotype.DomainService;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.TreeMap;

/**
 * 签名验证领域服务
 */
@DomainService
@RequiredArgsConstructor
public class SignatureService {

    private final SignatureCalculator signatureCalculator;
    private final NonceRepository nonceRepository;

    /**
     * 验证请求签名
     */
    public void verify(ApiKey apiKey, String timestamp, String nonce,
                       String clientSign, String clientBodyDigest, String serverBodyDigest,
                       Map<String, String> queryParams, long timestampTolerance) {
        // 1. 时间戳校验
        long requestTime = Long.parseLong(timestamp);
        long currentTime = System.currentTimeMillis() / 1000;
        if (Math.abs(currentTime - requestTime) > timestampTolerance) {
            throw new ApplicationException(OpenApiMessage.SIGNATURE_TIMESTAMP_EXPIRED);
        }

        // 2. Nonce 防重放
        if (nonceRepository.existsAndStore(nonce)) {
            throw new ApplicationException(OpenApiMessage.SIGNATURE_NONCE_DUPLICATED);
        }

        // 3. Body Digest 校验
        if (!serverBodyDigest.equals(clientBodyDigest)) {
            throw new ApplicationException(OpenApiMessage.SIGNATURE_BODY_DIGEST_MISMATCH);
        }

        // 4. 签名校验
        String expectedSign = calculateSign(
            apiKey.getApiKey(), clientBodyDigest, nonce, timestamp, queryParams, apiKey.getApiSecret()
        );
        if (!expectedSign.equals(clientSign)) {
            throw new ApplicationException(OpenApiMessage.SIGNATURE_MISMATCH);
        }
    }

    /**
     * 计算签名
     */
    public String calculateSign(String appId, String bodyDigest, String nonce,
                                String timestamp, Map<String, String> queryParams,
                                String appSecret) {
        TreeMap<String, String> sortedParams = new TreeMap<>();
        sortedParams.put("appId", appId);
        sortedParams.put("bodyDigest", bodyDigest);
        sortedParams.put("nonce", nonce);
        sortedParams.put("timestamp", timestamp);
        if (queryParams != null) {
            sortedParams.putAll(queryParams);
        }

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : sortedParams.entrySet()) {
            if (!sb.isEmpty()) {
                sb.append("&");
            }
            sb.append(entry.getKey()).append("=").append(entry.getValue());
        }

        return signatureCalculator.calculate(sb.toString(), appSecret);
    }

    /**
     * 计算请求体的 SHA-256 摘要
     */
    public String calculateBodyDigest(byte[] body) {
        try {
            java.security.MessageDigest digest = java.security.MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(body != null ? body : new byte[0]);
            return java.util.HexFormat.of().formatHex(hash);
        } catch (java.security.NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not available", e);
        }
    }
}
```

- [ ] **Step 4: 运行测试验证通过**

Run: `mvn test -Dtest=SignatureServiceTest -pl . -q`
Expected: All tests PASS

- [ ] **Step 5: Commit**

```bash
git add src/main/java/com/aieducenter/openapi/domain/service/SignatureService.java \
        src/test/java/com/aieducenter/openapi/domain/service/SignatureServiceTest.java
git commit -m "feat(openapi): implement SignatureService domain service with HMAC-SHA256 verification"
```

---

## Task 4: HmacSha256SignatureCalculator - TDD

**Files:**
- Test: `src/test/java/com/aieducenter/openapi/infrastructure/signature/HmacSha256SignatureCalculatorTest.java`
- Create: `src/main/java/com/aieducenter/openapi/infrastructure/signature/HmacSha256SignatureCalculator.java`

- [ ] **Step 1: 编写测试**

```java
package com.aieducenter.openapi.infrastructure.signature;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("HmacSha256SignatureCalculator 测试")
class HmacSha256SignatureCalculatorTest {

    private HmacSha256SignatureCalculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new HmacSha256SignatureCalculator();
    }

    @Test
    @DisplayName("给定已知输入，计算结果应该为小写 hex 格式")
    void given_knownInput_when_calculate_then_returnsLowercaseHex() {
        String result = calculator.calculate("test string to sign", "secret key");

        // 结果应该是小写 hex 字符串
        assertThat(result).matches("[0-9a-f]+");
        assertThat(result).hasSize(64); // SHA-256 = 32 bytes = 64 hex chars
    }

    @Test
    @DisplayName("给定相同输入，计算结果应该一致")
    void given_sameInput_when_calculateTwice_then_sameResult() {
        String result1 = calculator.calculate("appId=test&nonce=abc", "mySecret");
        String result2 = calculator.calculate("appId=test&nonce=abc", "mySecret");

        assertThat(result1).isEqualTo(result2);
    }

    @Test
    @DisplayName("给定不同密钥，计算结果应该不同")
    void given_differentSecret_when_calculate_then_differentResult() {
        String result1 = calculator.calculate("same string", "secret1");
        String result2 = calculator.calculate("same string", "secret2");

        assertThat(result1).isNotEqualTo(result2);
    }

    @Test
    @DisplayName("给定不同待签名字符串，计算结果应该不同")
    void given_differentStringToSign_when_calculate_then_differentResult() {
        String result1 = calculator.calculate("string1", "sameSecret");
        String result2 = calculator.calculate("string2", "sameSecret");

        assertThat(result1).isNotEqualTo(result2);
    }
}
```

- [ ] **Step 2: 运行测试验证失败**

Run: `mvn test -Dtest=HmacSha256SignatureCalculatorTest -pl . -q`
Expected: FAIL

- [ ] **Step 3: 实现 HmacSha256SignatureCalculator**

```java
package com.aieducenter.openapi.infrastructure.signature;

import com.aieducenter.openapi.domain.port.SignatureCalculator;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.HexFormat;

/**
 * HMAC-SHA256 签名计算实现
 */
@Component
public class HmacSha256SignatureCalculator implements SignatureCalculator {

    private static final String ALGORITHM = "HmacSHA256";

    @Override
    public String calculate(String stringToSign, String secret) {
        try {
            Mac mac = Mac.getInstance(ALGORITHM);
            SecretKeySpec keySpec = new SecretKeySpec(
                secret.getBytes(StandardCharsets.UTF_8), ALGORITHM
            );
            mac.init(keySpec);
            byte[] hash = mac.doFinal(stringToSign.getBytes(StandardCharsets.UTF_8));
            return HexFormat.of().formatHex(hash);
        } catch (Exception e) {
            throw new RuntimeException("Failed to calculate HMAC-SHA256", e);
        }
    }
}
```

- [ ] **Step 4: 运行测试验证通过**

Run: `mvn test -Dtest=HmacSha256SignatureCalculatorTest -pl . -q`
Expected: All tests PASS

- [ ] **Step 5: Commit**

```bash
git add src/main/java/com/aieducenter/openapi/infrastructure/signature/HmacSha256SignatureCalculator.java \
        src/test/java/com/aieducenter/openapi/infrastructure/signature/HmacSha256SignatureCalculatorTest.java
git commit -m "feat(openapi): implement HmacSha256SignatureCalculator"
```

---

## Task 5: RedisNonceRepository - TDD

**Files:**
- Test: `src/test/java/com/aieducenter/openapi/infrastructure/redis/RedisNonceRepositoryTest.java`
- Create: `src/main/java/com/aieducenter/openapi/infrastructure/redis/RedisNonceRepository.java`

- [ ] **Step 1: 编写测试**

```java
package com.aieducenter.openapi.infrastructure.redis;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.time.Duration;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("RedisNonceRepository 测试")
class RedisNonceRepositoryTest {

    @Mock
    private StringRedisTemplate redisTemplate;

    @Mock
    private ValueOperations<String, String> valueOperations;

    private RedisNonceRepository nonceRepository;

    @BeforeEach
    void setUp() {
        when(redisTemplate.opsForValue()).thenReturn(valueOperations);
        nonceRepository = new RedisNonceRepository(redisTemplate, 300);
    }

    @Test
    @DisplayName("给定新 Nonce，existsAndStore 应该返回 false 并存储")
    void given_newNonce_when_existsAndStore_then_returnsFalse() {
        // Given
        when(valueOperations.setIfAbsent(anyString(), anyString(), any(Duration.class)))
            .thenReturn(true);

        // When
        boolean result = nonceRepository.existsAndStore("new-nonce-123");

        // Then
        assertThat(result).isFalse();
        verify(valueOperations).setIfAbsent(
            eq("openapi:nonce:new-nonce-123"),
            eq("1"),
            eq(Duration.ofSeconds(300))
        );
    }

    @Test
    @DisplayName("给定已存在的 Nonce，existsAndStore 应该返回 true")
    void given_existingNonce_when_existsAndStore_then_returnsTrue() {
        // Given
        when(valueOperations.setIfAbsent(anyString(), anyString(), any(Duration.class)))
            .thenReturn(false);

        // When
        boolean result = nonceRepository.existsAndStore("existing-nonce");

        // Then
        assertThat(result).isTrue();
    }
}
```

- [ ] **Step 2: 运行测试验证失败**

Run: `mvn test -Dtest=RedisNonceRepositoryTest -pl . -q`
Expected: FAIL

- [ ] **Step 3: 实现 RedisNonceRepository**

```java
package com.aieducenter.openapi.infrastructure.redis;

import com.aieducenter.openapi.domain.port.NonceRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

/**
 * Redis Nonce 存储实现
 */
@Component
public class RedisNonceRepository implements NonceRepository {

    private static final String KEY_PREFIX = "openapi:nonce:";

    private final StringRedisTemplate redisTemplate;
    private final long nonceTtlSeconds;

    public RedisNonceRepository(StringRedisTemplate redisTemplate,
                                @Value("${openapi.signature.nonce-ttl:300}") long nonceTtlSeconds) {
        this.redisTemplate = redisTemplate;
        this.nonceTtlSeconds = nonceTtlSeconds;
    }

    @Override
    public boolean existsAndStore(String nonce) {
        Boolean absent = redisTemplate.opsForValue()
            .setIfAbsent(KEY_PREFIX + nonce, "1", Duration.ofSeconds(nonceTtlSeconds));
        return !Boolean.TRUE.equals(absent);
    }
}
```

- [ ] **Step 4: 运行测试验证通过**

Run: `mvn test -Dtest=RedisNonceRepositoryTest -pl . -q`
Expected: All tests PASS

- [ ] **Step 5: Commit**

```bash
git add src/main/java/com/aieducenter/openapi/infrastructure/redis/RedisNonceRepository.java \
        src/test/java/com/aieducenter/openapi/infrastructure/redis/RedisNonceRepositoryTest.java
git commit -m "feat(openapi): implement RedisNonceRepository with SETNX for anti-replay"
```

---

## Task 6: ApiKeyQueryAppService 应用服务 - TDD

**Files:**
- Test: `src/test/java/com/aieducenter/openapi/application/ApiKeyQueryAppServiceTest.java`
- Create: `src/main/java/com/aieducenter/openapi/application/ApiKeyQueryAppService.java`

- [ ] **Step 1: 编写测试**

```java
package com.aieducenter.openapi.application;

import com.aieducenter.openapi.domain.aggregate.ApiKey;
import com.aieducenter.openapi.domain.enums.ApiKeyStatus;
import com.aieducenter.openapi.domain.error.OpenApiMessage;
import com.aieducenter.openapi.domain.repository.ApiKeyRepository;
import com.cartisan.core.exception.ApplicationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("ApiKeyQueryAppService 测试")
class ApiKeyQueryAppServiceTest {

    @Mock
    private ApiKeyRepository apiKeyRepository;

    @InjectMocks
    private ApiKeyQueryAppService apiKeyQueryAppService;

    @Test
    @DisplayName("给定有效的活跃 API Key，查询时应该返回 ApiKey 聚合")
    void given_activeApiKey_when_findByApiKeyForVerification_then_returnsApiKey() throws Exception {
        // Given
        ApiKey apiKey = createTestApiKey("APPKEY123", "secret123", "TestSystem", ApiKeyStatus.ACTIVE);
        when(apiKeyRepository.findByApiKey("APPKEY123")).thenReturn(Optional.of(apiKey));

        // When
        ApiKey result = apiKeyQueryAppService.findByApiKeyForVerification("APPKEY123");

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getApiKey()).isEqualTo("APPKEY123");
        assertThat(result.getApiSecret()).isEqualTo("secret123");
    }

    @Test
    @DisplayName("给定不存在的 API Key，查询时应该抛出 SIGNATURE_API_KEY_INVALID")
    void given_nonExistentApiKey_when_findByApiKeyForVerification_then_throwsException() {
        // Given
        when(apiKeyRepository.findByApiKey("NOTEXIST")).thenReturn(Optional.empty());

        // When & Then
        assertThatThrownBy(() -> apiKeyQueryAppService.findByApiKeyForVerification("NOTEXIST"))
            .isInstanceOf(ApplicationException.class)
            .hasMessageContaining(OpenApiMessage.SIGNATURE_API_KEY_INVALID.message());
    }

    @Test
    @DisplayName("给定已禁用的 API Key，查询时应该抛出 SIGNATURE_API_KEY_INVALID")
    void given_disabledApiKey_when_findByApiKeyForVerification_then_throwsException() throws Exception {
        // Given
        ApiKey disabledKey = createTestApiKey("DISABLEDKEY", "secret", "TestSystem", ApiKeyStatus.DISABLED);
        when(apiKeyRepository.findByApiKey("DISABLEDKEY")).thenReturn(Optional.of(disabledKey));

        // When & Then
        assertThatThrownBy(() -> apiKeyQueryAppService.findByApiKeyForVerification("DISABLEDKEY"))
            .isInstanceOf(ApplicationException.class)
            .hasMessageContaining(OpenApiMessage.SIGNATURE_API_KEY_INVALID.message());
    }

    private ApiKey createTestApiKey(String apiKey, String apiSecret, String businessSystemName, ApiKeyStatus status) throws Exception {
        ApiKey key = new ApiKey(businessSystemName, "test", null);

        var apiKeyField = ApiKey.class.getDeclaredField("apiKey");
        apiKeyField.setAccessible(true);
        apiKeyField.set(key, apiKey);

        var apiSecretField = ApiKey.class.getDeclaredField("apiSecret");
        apiSecretField.setAccessible(true);
        apiSecretField.set(key, apiSecret);

        if (status == ApiKeyStatus.DISABLED) {
            key.disable();
        }

        return key;
    }
}
```

- [ ] **Step 2: 运行测试验证失败**

Run: `mvn test -Dtest=ApiKeyQueryAppServiceTest -pl . -q`
Expected: FAIL

- [ ] **Step 3: 实现 ApiKeyQueryAppService**

```java
package com.aieducenter.openapi.application;

import com.aieducenter.openapi.domain.aggregate.ApiKey;
import com.aieducenter.openapi.domain.enums.ApiKeyStatus;
import com.aieducenter.openapi.domain.error.OpenApiMessage;
import com.aieducenter.openapi.domain.repository.ApiKeyRepository;
import com.cartisan.core.exception.ApplicationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 签名验证专用的 ApiKey 查询服务
 *
 * <p>返回完整 ApiKey 聚合（包含 apiSecret），与 ApiKeyManagementAppService 区分</p>
 */
@Service
@RequiredArgsConstructor
public class ApiKeyQueryAppService {

    private final ApiKeyRepository apiKeyRepository;

    /**
     * 根据 apiKey 查询并验证状态
     *
     * @param apiKey API Key 字符串
     * @return 活跃的 ApiKey 聚合
     * @throws ApplicationException API Key 不存在或已禁用
     */
    @Transactional(readOnly = true)
    public ApiKey findByApiKeyForVerification(String apiKey) {
        return apiKeyRepository.findByApiKey(apiKey)
            .filter(key -> key.getStatus() == ApiKeyStatus.ACTIVE)
            .orElseThrow(() -> new ApplicationException(OpenApiMessage.SIGNATURE_API_KEY_INVALID));
    }
}
```

- [ ] **Step 4: 运行测试验证通过**

Run: `mvn test -Dtest=ApiKeyQueryAppServiceTest -pl . -q`
Expected: All tests PASS

- [ ] **Step 5: Commit**

```bash
git add src/main/java/com/aieducenter/openapi/application/ApiKeyQueryAppService.java \
        src/test/java/com/aieducenter/openapi/application/ApiKeyQueryAppServiceTest.java
git commit -m "feat(openapi): add ApiKeyQueryAppService for signature verification"
```

---

## Task 7: Web 层 - 注解 + Filter + 拦截器 + 配置

**Files:**
- Create: `src/main/java/com/aieducenter/openapi/web/RequireSignature.java`
- Create: `src/main/java/com/aieducenter/openapi/web/CachingRequestBodyFilter.java`
- Create: `src/main/java/com/aieducenter/openapi/web/SignatureVerificationInterceptor.java`
- Create: `src/main/java/com/aieducenter/openapi/web/OpenApiSignatureConfig.java`
- Test: `src/test/java/com/aieducenter/openapi/web/SignatureVerificationInterceptorTest.java`
- Modify: `src/main/java/com/aieducenter/config/WebConfig.java`

- [ ] **Step 1: 创建 @RequireSignature 注解**

```java
package com.aieducenter.openapi.web;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标记需要签名验证的 Controller 方法或类
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequireSignature {
}
```

- [ ] **Step 2: 创建配置属性类 OpenApiSignatureConfig**

```java
package com.aieducenter.openapi.web;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 签名验证配置属性
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "openapi.signature")
public class OpenApiSignatureConfig {

    /**
     * 时间戳容忍窗口（秒）
     */
    private long timestampTolerance = 300;

    /**
     * Nonce 过期时间（秒）
     */
    private long nonceTtl = 300;

    /**
     * 是否启用签名验证
     */
    private boolean enabled = true;
}
```

- [ ] **Step 3: 创建 CachingRequestBodyFilter**

```java
package com.aieducenter.openapi.web;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * 缓存请求体的 Filter
 *
 * <p>将请求体读入 byte[] 并缓存，使 body 可被 Interceptor 和 Controller 分别读取。
 * 不使用 Spring 的 ContentCachingRequestWrapper，因为它在首次读取后才缓存。</p>
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CachingRequestBodyFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        if (request instanceof HttpServletRequest httpRequest) {
            String contentType = httpRequest.getContentType();
            if (contentType != null && contentType.contains("application/json")) {
                byte[] body = httpRequest.getInputStream().readAllBytes();
                chain.doFilter(new CachedBodyHttpServletRequest(httpRequest, body), response);
                return;
            }
        }
        chain.doFilter(request, response);
    }

    /**
     * 自定义 Request 包装器，缓存 body 字节
     */
    static class CachedBodyHttpServletRequest extends HttpServletRequestWrapper {
        private final byte[] cachedBody;

        CachedBodyHttpServletRequest(HttpServletRequest request, byte[] body) {
            super(request);
            this.cachedBody = body;
        }

        byte[] getCachedBody() {
            return cachedBody;
        }

        @Override
        public ServletInputStream getInputStream() {
            return new CachedBodyServletInputStream(cachedBody);
        }

        @Override
        public BufferedReader getReader() {
            return new BufferedReader(new InputStreamReader(getInputStream(), StandardCharsets.UTF_8));
        }
    }

    static class CachedBodyServletInputStream extends ServletInputStream {
        private final ByteArrayInputStream inputStream;

        CachedBodyServletInputStream(byte[] cachedBody) {
            this.inputStream = new ByteArrayInputStream(cachedBody);
        }

        @Override
        public boolean isFinished() {
            return inputStream.available() == 0;
        }

        @Override
        public boolean isReady() {
            return true;
        }

        @Override
        public void setReadListener(ReadListener listener) {
            throw new UnsupportedOperationException("Not implemented");
        }

        @Override
        public int read() {
            return inputStream.read();
        }
    }
}
```

- [ ] **Step 4: 创建 SignatureVerificationInterceptor**

```java
package com.aieducenter.openapi.web;

import com.aieducenter.openapi.application.ApiKeyQueryAppService;
import com.aieducenter.openapi.domain.aggregate.ApiKey;
import com.aieducenter.openapi.domain.error.OpenApiMessage;
import com.aieducenter.openapi.domain.service.SignatureService;
import com.cartisan.core.exception.ApplicationException;
import com.cartisan.web.response.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * 签名验证拦截器
 *
 * <p>检查方法/类上的 @RequireSignature 注解，有则执行签名验证</p>
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class SignatureVerificationInterceptor implements HandlerInterceptor {

    private final SignatureService signatureService;
    private final ApiKeyQueryAppService apiKeyQueryAppService;
    private final OpenApiSignatureConfig config;
    private final ObjectMapper objectMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 只处理 Controller 方法
        if (!(handler instanceof HandlerMethod handlerMethod)) {
            return true;
        }

        // 检查注解（方法级或类级）
        RequireSignature methodAnnotation = handlerMethod.getMethodAnnotation(RequireSignature.class);
        RequireSignature classAnnotation = handlerMethod.getBeanType().getAnnotation(RequireSignature.class);
        if (methodAnnotation == null && classAnnotation == null) {
            return true;
        }

        // 检查开关
        if (!config.isEnabled()) {
            return true;
        }

        try {
            // 1. 提取并校验必要参数
            String appId = getRequiredHeader(request, "X-App-Id");
            String timestamp = getRequiredHeader(request, "X-Timestamp");
            String nonce = getRequiredHeader(request, "X-Nonce");
            String sign = getRequiredHeader(request, "X-Sign");
            String clientBodyDigest = getRequiredHeader(request, "X-Body-Digest");

            // 2. 身份校验
            ApiKey apiKey = apiKeyQueryAppService.findByApiKeyForVerification(appId);

            // 3. 计算 server body digest
            byte[] body = getCachedBody(request);
            String serverBodyDigest = signatureService.calculateBodyDigest(body);

            // 4. 提取 query 参数
            Map<String, String> queryParams = extractQueryParams(request);

            // 5. 签名验证
            signatureService.verify(
                apiKey, timestamp, nonce, sign,
                clientBodyDigest, serverBodyDigest,
                queryParams, config.getTimestampTolerance()
            );

            // 6. 验证通过，放入 request attribute
            request.setAttribute("verifiedAppId", apiKey.getApiKey());
            request.setAttribute("verifiedBusinessSystemName", apiKey.getBusinessSystemName());

            return true;

        } catch (ApplicationException e) {
            writeErrorResponse(response, e.getCodeMessage().httpStatus(), e.getCodeMessage());
            return false;
        }
    }

    private String getRequiredHeader(HttpServletRequest request, String headerName) {
        String value = request.getHeader(headerName);
        if (value == null || value.isBlank()) {
            throw new ApplicationException(OpenApiMessage.SIGNATURE_PARAMS_MISSING);
        }
        return value;
    }

    private byte[] getCachedBody(HttpServletRequest request) {
        if (request instanceof CachingRequestBodyFilter.CachedBodyHttpServletRequest cachedRequest) {
            return cachedRequest.getCachedBody();
        }
        return new byte[0];
    }

    private Map<String, String> extractQueryParams(HttpServletRequest request) {
        Map<String, String> params = new HashMap<>();
        String queryString = request.getQueryString();
        if (queryString != null && !queryString.isBlank()) {
            String[] pairs = queryString.split("&");
            for (String pair : pairs) {
                int idx = pair.indexOf('=');
                if (idx > 0) {
                    String key = URLDecoder.decode(pair.substring(0, idx), StandardCharsets.UTF_8);
                    String value = URLDecoder.decode(pair.substring(idx + 1), StandardCharsets.UTF_8);
                    params.put(key, value);
                }
            }
        }
        return params;
    }

    private void writeErrorResponse(HttpServletResponse response, int httpStatus, com.cartisan.core.exception.CodeMessage codeMessage) throws Exception {
        response.setStatus(httpStatus);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(
            ApiResponse.error(codeMessage)
        ));
    }
}
```

- [ ] **Step 5: 编写 Interceptor 测试**

```java
package com.aieducenter.openapi.web;

import com.aieducenter.openapi.application.ApiKeyQueryAppService;
import com.aieducenter.openapi.domain.aggregate.ApiKey;
import com.aieducenter.openapi.domain.enums.ApiKeyStatus;
import com.aieducenter.openapi.domain.service.SignatureService;
import com.aieducenter.openapi.web.CachingRequestBodyFilter.CachedBodyHttpServletRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.method.HandlerMethod;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("SignatureVerificationInterceptor 测试")
class SignatureVerificationInterceptorTest {

    @Mock private SignatureService signatureService;
    @Mock private ApiKeyQueryAppService apiKeyQueryAppService;
    private OpenApiSignatureConfig config;
    private ObjectMapper objectMapper;

    private SignatureVerificationInterceptor interceptor;

    @BeforeEach
    void setUp() {
        config = new OpenApiSignatureConfig();
        config.setEnabled(true);
        config.setTimestampTolerance(300);
        objectMapper = new ObjectMapper();
        interceptor = new SignatureVerificationInterceptor(signatureService, apiKeyQueryAppService, config, objectMapper);
    }

    @Test
    @DisplayName("给定非 HandlerMethod，应该直接放行")
    void given_nonHandlerMethod_when_preHandle_then_pass() throws Exception {
        MockHttpServletResponse response = new MockHttpServletResponse();
        boolean result = interceptor.preHandle(null, response, new Object());
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("给定无 @RequireSignature 注解的方法，应该直接放行")
    void given_noAnnotation_when_preHandle_then_pass() throws Exception {
        HandlerMethod handlerMethod = createHandlerMethod("methodWithoutAnnotation");
        MockHttpServletResponse response = new MockHttpServletResponse();

        boolean result = interceptor.preHandle(
            new MockRequestWithHeaders().build(), response, handlerMethod
        );

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("给定缺少必要 Header 的请求，应该返回 401")
    void given_missingHeaders_when_preHandle_then_returns401() throws Exception {
        HandlerMethod handlerMethod = createHandlerMethod("methodWithAnnotation");
        MockHttpServletResponse response = new MockHttpServletResponse();
        MockRequestWithHeaders request = new MockRequestWithHeaders()
            .header("X-App-Id", "testApp")
            // 缺少其他 headers
            ;

        boolean result = interceptor.preHandle(request.build(), response, handlerMethod);
        assertThat(result).isFalse();
        assertThat(response.getStatus()).isEqualTo(401);
    }

    @Test
    @DisplayName("给定完整有效请求，应该放行并设置 request attributes")
    void given_validRequest_when_preHandle_then_passWithAttributes() throws Exception {
        HandlerMethod handlerMethod = createHandlerMethod("methodWithAnnotation");
        MockHttpServletResponse response = new MockHttpServletResponse();

        ApiKey apiKey = createTestApiKey("testApp", "secret", "TestSystem");
        when(apiKeyQueryAppService.findByApiKeyForVerification("testApp")).thenReturn(apiKey);
        when(signatureService.calculateBodyDigest(any(byte[].class))).thenReturn("serverDigest");
        doNothing().when(signatureService).verify(any(), any(), any(), any(), any(), any(), any(), anyLong());

        MockRequestWithHeaders requestBuilder = new MockRequestWithHeaders()
            .header("X-App-Id", "testApp")
            .header("X-Timestamp", String.valueOf(System.currentTimeMillis() / 1000))
            .header("X-Nonce", "nonce-123")
            .header("X-Sign", "valid-sign")
            .header("X-Body-Digest", "serverDigest");

        var request = requestBuilder.build();
        boolean result = interceptor.preHandle(request, response, handlerMethod);

        assertThat(result).isTrue();
        assertThat(request.getAttribute("verifiedAppId")).isEqualTo("testApp");
        assertThat(request.getAttribute("verifiedBusinessSystemName")).isEqualTo("TestSystem");
    }

    // --- Helper methods ---

    @RequireSignature
    public void methodWithAnnotation() {}

    public void methodWithoutAnnotation() {}

    private HandlerMethod createHandlerMethod(String methodName) throws NoSuchMethodException {
        Method method = this.getClass().getMethod(methodName);
        return new HandlerMethod(this, method);
    }

    private ApiKey createTestApiKey(String apiKeyStr, String apiSecret, String businessSystemName) throws Exception {
        ApiKey key = new ApiKey(businessSystemName, "test", null);
        setField(key, "apiKey", apiKeyStr);
        setField(key, "apiSecret", apiSecret);
        return key;
    }

    private void setField(Object target, String fieldName, Object value) throws Exception {
        var field = target.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(target, value);
    }

    /**
     * Helper to build mock requests with headers.
     * Uses CachedBodyHttpServletRequest for body caching.
     */
    static class MockRequestWithHeaders {
        private final jakarta.servlet.http.HttpServletRequest mockRequest =
            new org.springframework.mock.web.MockHttpServletRequest();

        MockRequestWithHeaders header(String name, String value) {
            ((org.springframework.mock.web.MockHttpServletRequest) mockRequest).addHeader(name, value);
            return this;
        }

        CachedBodyHttpServletRequest build() {
            return new CachedBodyHttpServletRequest(
                (org.springframework.mock.web.MockHttpServletRequest) mockRequest,
                new byte[0]
            );
        }
    }
}
```

- [ ] **Step 6: 运行测试验证通过**

Run: `mvn test -Dtest=SignatureVerificationInterceptorTest -pl . -q`
Expected: All tests PASS

- [ ] **Step 7: 修改 WebConfig 注册拦截器**

在现有 `WebConfig.java` 中添加 `WebMvcConfigurer` 实现：

```java
package com.aieducenter.config;

import com.aieducenter.openapi.web.SignatureVerificationInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final SignatureVerificationInterceptor signatureVerificationInterceptor;

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:3000");
        config.addAllowedOrigin("http://127.0.0.1:3000");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.setMaxAge(3600L);
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(signatureVerificationInterceptor)
            .addPathPatterns("/api/v1/**")
            .excludePathPatterns(
                "/api/v1/openapi/**",
                "/api/v1/payment/callbacks/**"
            );
    }
}
```

- [ ] **Step 8: 编译验证**

Run: `mvn compile -q`
Expected: BUILD SUCCESS

- [ ] **Step 9: Commit**

```bash
git add src/main/java/com/aieducenter/openapi/web/ \
        src/test/java/com/aieducenter/openapi/web/ \
        src/main/java/com/aieducenter/config/WebConfig.java
git commit -m "feat(openapi): add @RequireSignature annotation, CachingRequestBodyFilter, SignatureVerificationInterceptor"
```

---

## Task 8: 集成 - 改造 Controller + 配置

**Files:**
- Modify: `src/main/java/com/aieducenter/payment/endpoints/api/v1/PaymentApiV1Controller.java`
- Modify: `src/main/java/com/aieducenter/payment/endpoints/api/v1/RefundApiV1Controller.java`
- Modify: `src/main/resources/application-local.yml`

- [ ] **Step 1: 改造 PaymentApiV1Controller**

移除 `ApiKeyManagementAppService` 依赖和 `@RequestHeader("X-API-KEY")`，改用 `@RequireSignature`：

```java
package com.aieducenter.payment.endpoints.api.v1;

import com.aieducenter.openapi.web.RequireSignature;
import com.aieducenter.payment.application.PaymentAppService;
import com.aieducenter.payment.application.dto.command.CreatePaymentCommand;
import com.aieducenter.payment.application.dto.response.PaymentOrderResponse;
import com.cartisan.web.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
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
    @RequireSignature
    @Operation(summary = "创建支付订单")
    public ApiResponse<PaymentOrderResponse> createPayment(
            @Valid @RequestBody CreatePaymentCommand command,
            HttpServletRequest request
    ) {
        String businessSystemName = (String) request.getAttribute("verifiedBusinessSystemName");
        PaymentOrderResponse response = paymentAppService.createPayment(command, businessSystemName);
        return ApiResponse.ok(response);
    }

    @GetMapping("/{paymentOrderNo}")
    @RequireSignature
    @Operation(summary = "查询支付订单")
    public ApiResponse<PaymentOrderResponse> getPayment(
            @PathVariable String paymentOrderNo
    ) {
        PaymentOrderResponse response = paymentAppService.getPayment(paymentOrderNo);
        return ApiResponse.ok(response);
    }

    @PostMapping("/{paymentOrderNo}/query")
    @RequireSignature
    @Operation(summary = "主动查询支付状态")
    public ApiResponse<PaymentOrderResponse> queryPaymentStatus(
            @PathVariable String paymentOrderNo
    ) {
        PaymentOrderResponse response = paymentAppService.queryPaymentStatus(paymentOrderNo);
        return ApiResponse.ok(response);
    }
}
```

- [ ] **Step 2: 改造 RefundApiV1Controller**

```java
package com.aieducenter.payment.endpoints.api.v1;

import com.aieducenter.openapi.web.RequireSignature;
import com.aieducenter.payment.application.RefundAppService;
import com.aieducenter.payment.application.dto.command.AuditRefundCommand;
import com.aieducenter.payment.application.dto.command.CreateRefundCommand;
import com.aieducenter.payment.application.dto.response.RefundOrderResponse;
import com.cartisan.web.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/refunds")
@RequiredArgsConstructor
@Validated
@Tag(name = "Refund API v1", description = "退款接口 v1")
public class RefundApiV1Controller {

    private final RefundAppService refundAppService;

    @PostMapping
    @RequireSignature
    @Operation(summary = "创建退款订单")
    public ApiResponse<RefundOrderResponse> createRefund(
            @Valid @RequestBody CreateRefundCommand command,
            HttpServletRequest request
    ) {
        String businessSystemName = (String) request.getAttribute("verifiedBusinessSystemName");
        RefundOrderResponse response = refundAppService.createRefund(command, businessSystemName);
        return ApiResponse.ok(response);
    }

    @PostMapping("/{refundOrderNo}/audit")
    @RequireSignature
    @Operation(summary = "审核退款")
    public ApiResponse<RefundOrderResponse> auditRefund(
            @PathVariable String refundOrderNo,
            @Valid @RequestBody AuditRefundCommand command
    ) {
        RefundOrderResponse response = refundAppService.auditRefund(refundOrderNo, command);
        return ApiResponse.ok(response);
    }

    @GetMapping("/{refundOrderNo}")
    @RequireSignature
    @Operation(summary = "查询退款订单")
    public ApiResponse<RefundOrderResponse> getRefund(
            @PathVariable String refundOrderNo
    ) {
        RefundOrderResponse response = refundAppService.getRefund(refundOrderNo);
        return ApiResponse.ok(response);
    }
}
```

- [ ] **Step 3: 添加 YAML 配置**

在 `application-local.yml` 末尾添加：

```yaml
# 签名验证配置
openapi:
  signature:
    timestamp-tolerance: 300
    nonce-ttl: 300
    enabled: true
```

- [ ] **Step 4: 全量编译验证**

Run: `mvn compile -q`
Expected: BUILD SUCCESS

- [ ] **Step 5: 运行所有测试**

Run: `mvn test -pl . -q`
Expected: All tests PASS

- [ ] **Step 6: Commit**

```bash
git add src/main/java/com/aieducenter/payment/endpoints/api/v1/PaymentApiV1Controller.java \
        src/main/java/com/aieducenter/payment/endpoints/api/v1/RefundApiV1Controller.java \
        src/main/resources/application-local.yml
git commit -m "feat(payment): integrate @RequireSignature into Payment and Refund controllers"
```

---

## Task 9: 全量验证

- [ ] **Step 1: 运行全部测试**

Run: `mvn test -pl .`
Expected: All tests PASS (including existing PaymentOrderTest, RefundOrderTest, ArchitectureTest)

- [ ] **Step 2: 检查编译和代码风格**

Run: `mvn compile -pl .`
Expected: BUILD SUCCESS

- [ ] **Step 3: Final Commit（如有遗漏修复）**

如果有任何遗漏的修复，在此提交。
