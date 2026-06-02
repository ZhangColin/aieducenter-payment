# HSB 对账文件接收接口 Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** 实现建行惠市宝对账文件接收回调接口，验签后保存 zip 到本地文件系统。

**Architecture:** 在现有 HSB 回调体系上扩展。Controller 接收 Multipart POST，AppService 负责验签和文件保存。复用已有的 `HsbSignUtil` 验签工具。配置通过 `HsbConfig` 管理。

**Tech Stack:** Spring Boot, Spring MVC Multipart, JUnit 5 + Mockito

---

## File Structure

| File | Action | Responsibility |
|---|---|---|
| `src/main/java/.../hsb/endpoints/api/v1/HsbCallbackController.java` | Modify | 新增 `/reconciliation` 端点 |
| `src/main/java/.../hsb/application/HsbCallbackAppService.java` | Modify | 新增 `handleReconciliationCallback` 方法 |
| `src/main/java/.../hsb/infrastructure/HsbConfig.java` | Modify | 新增 `reconciliationStoragePath` 字段 |
| `src/main/resources/application-local.yml` | Modify | 添加 `reconciliation-storage-path` 配置 |
| `src/main/resources/application-test.yml` | Modify | 添加 `reconciliation-storage-path` 配置 |
| `src/main/resources/application-prod.yml` | Modify | 添加 hsb 配置和 `reconciliation-storage-path` |
| `src/test/java/.../hsb/application/HsbReconciliationCallbackTest.java` | Create | 对账回调 AppService 单元测试 |

---

### Task 1: HsbConfig 添加 reconciliationStoragePath 配置

**Files:**
- Modify: `src/main/java/com/aieducenter/payment/hsb/infrastructure/HsbConfig.java`
- Modify: `src/main/resources/application-local.yml`
- Modify: `src/main/resources/application-test.yml`
- Modify: `src/main/resources/application-prod.yml`

- [ ] **Step 1: 在 HsbConfig 中添加字段**

在 `HsbConfig.java` 类中添加：

```java
private String reconciliationStoragePath = "data/reconciliation";
```

完整的 `HsbConfig.java`：

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
    private String platformMerchantId;
    private String privateKey;
    private String platformPublicKey;
    private String initiatorSystemId = "00000";
    private String initiatorChannelCode = "0000000000000000000000000";
    private String reconciliationStoragePath = "data/reconciliation";
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

- [ ] **Step 2: 在 application-local.yml 中添加配置**

在 `hsb:` 节点下 `initiator-channel-code` 之后添加：

```yaml
  reconciliation-storage-path: ${HSB_RECONCILIATION_STORAGE_PATH:data/reconciliation}
```

- [ ] **Step 3: 在 application-test.yml 中添加同样的配置**

在 `hsb:` 节点下 `initiator-channel-code` 之后添加：

```yaml
  reconciliation-storage-path: ${HSB_RECONCILIATION_STORAGE_PATH:data/reconciliation}
```

- [ ] **Step 4: 在 application-prod.yml 中添加配置（如果 hsb 节点不存在则新增整个 hsb 配置块）**

```yaml
  reconciliation-storage-path: ${HSB_RECONCILIATION_STORAGE_PATH:/data/reconciliation}
```

- [ ] **Step 5: Commit**

```bash
git add src/main/java/com/aieducenter/payment/hsb/infrastructure/HsbConfig.java src/main/resources/application-local.yml src/main/resources/application-test.yml src/main/resources/application-prod.yml
git commit -m "feat(hsb): add reconciliation storage path config"
```

---

### Task 2: 编写对账回调 AppService 单元测试

**Files:**
- Create: `src/test/java/com/aieducenter/payment/hsb/application/HsbReconciliationCallbackTest.java`

- [ ] **Step 1: 编写测试类**

测试要点：
- 验签通过 + 文件保存成功 → 返回 `Svc_Rsp_St=00`
- 验签失败 → 返回 `Svc_Rsp_St=01`
- 文件为空 → 返回 `Svc_Rsp_St=01`

```java
package com.aieducenter.payment.hsb.application;

import com.aieducenter.payment.hsb.infrastructure.HsbConfig;
import com.aieducenter.payment.hsb.infrastructure.HsbSignUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mockStatic;

@ExtendWith(MockitoExtension.class)
class HsbReconciliationCallbackTest {

    private HsbCallbackAppService appService;
    private HsbConfig hsbConfig;

    @TempDir
    Path tempDir;

    @BeforeEach
    void setUp() {
        hsbConfig = new HsbConfig();
        hsbConfig.setReconciliationStoragePath(tempDir.toString());
        appService = new HsbCallbackAppService(
            null, null, null, hsbConfig, null
        );
    }

    @Test
    @DisplayName("验签通过且文件保存成功，返回成功")
    void handleReconciliationCallback_success() throws IOException {
        MultipartFile file = new MockMultipartFile(
            "file", "test.zip", "application/zip",
            "zip-content".getBytes()
        );

        try (MockedStatic<HsbSignUtil> signUtilMock = mockStatic(HsbSignUtil.class)) {
            signUtilMock.when(() -> HsbSignUtil.verifySign(anyString(), anyString(), anyString()))
                .thenReturn(true);

            String result = appService.handleReconciliationCallback(
                "summary-info", "sign-value", file
            );

            assertThat(result).contains("\"Svc_Rsp_St\":\"00\"");
        }

        // 验证文件已保存
        assertThat(Files.list(tempDir).findFirst()).isPresent();
    }

    @Test
    @DisplayName("验签失败，返回失败")
    void handleReconciliationCallback_signatureVerificationFailed() {
        MultipartFile file = new MockMultipartFile(
            "file", "test.zip", "application/zip",
            "zip-content".getBytes()
        );

        try (MockedStatic<HsbSignUtil> signUtilMock = mockStatic(HsbSignUtil.class)) {
            signUtilMock.when(() -> HsbSignUtil.verifySign(anyString(), anyString(), anyString()))
                .thenReturn(false);

            String result = appService.handleReconciliationCallback(
                "summary-info", "sign-value", file
            );

            assertThat(result).contains("\"Svc_Rsp_St\":\"01\"");
        }
    }

    @Test
    @DisplayName("文件为空，返回失败")
    void handleReconciliationCallback_emptyFile() {
        MultipartFile file = new MockMultipartFile(
            "file", "test.zip", "application/zip", new byte[0]
        );

        try (MockedStatic<HsbSignUtil> signUtilMock = mockStatic(HsbSignUtil.class)) {
            signUtilMock.when(() -> HsbSignUtil.verifySign(anyString(), anyString(), anyString()))
                .thenReturn(true);

            String result = appService.handleReconciliationCallback(
                "summary-info", "sign-value", file
            );

            assertThat(result).contains("\"Svc_Rsp_St\":\"01\"");
        }
    }
}
```

- [ ] **Step 2: 运行测试确认失败**

Run: `mvn test -pl . -Dtest=HsbReconciliationCallbackTest -Dsurefire.failIfNoSpecifiedTests=false`
Expected: 编译失败或测试失败（`handleReconciliationCallback` 方法不存在）

---

### Task 3: 实现 AppService handleReconciliationCallback 方法

**Files:**
- Modify: `src/main/java/com/aieducenter/payment/hsb/application/HsbCallbackAppService.java`

- [ ] **Step 1: 在 HsbCallbackAppService 中添加方法**

在 `HsbCallbackAppService.java` 中添加以下方法（在 `yuanToFen` 方法之前）：

```java
public String handleReconciliationCallback(String fileSmryInf, String signInf, MultipartFile file) {
    log.info("Received HSB reconciliation callback: fileSmryInf={}", fileSmryInf);

    // 验签：签名原文为 "File_Smry_Inf={fileSmryInf}"
    String signStr = "File_Smry_Inf=" + fileSmryInf;
    boolean verified = HsbSignUtil.verifySign(hsbConfig.getPlatformPublicKey(), signStr, signInf);

    if (!verified) {
        log.warn("HSB reconciliation callback signature verification failed");
        return buildReconciliationResponse(false);
    }

    // 检查文件
    if (file == null || file.isEmpty()) {
        log.warn("HSB reconciliation callback: empty file");
        return buildReconciliationResponse(false);
    }

    // 保存文件
    try {
        String dateDir = java.time.LocalDate.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Path dirPath = Path.of(hsbConfig.getReconciliationStoragePath(), dateDir);
        Files.createDirectories(dirPath);

        String fileName = System.currentTimeMillis() + ".zip";
        Path filePath = dirPath.resolve(fileName);
        file.transferTo(filePath);

        log.info("HSB reconciliation file saved: {}, size: {}", filePath, file.getSize());
        return buildReconciliationResponse(true);
    } catch (IOException e) {
        log.error("HSB reconciliation callback: failed to save file", e);
        return buildReconciliationResponse(false);
    }
}

private String buildReconciliationResponse(boolean success) {
    return "{\"Svc_Rsp_St\":\"" + (success ? "00" : "01") + "\"}";
}
```

需要在文件顶部添加的 import：

```java
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
```

- [ ] **Step 2: 运行测试确认通过**

Run: `mvn test -pl . -Dtest=HsbReconciliationCallbackTest -Dsurefire.failIfNoSpecifiedTests=false`
Expected: 3 tests PASS

- [ ] **Step 3: Commit**

```bash
git add src/main/java/com/aieducenter/payment/hsb/application/HsbCallbackAppService.java src/test/java/com/aieducenter/payment/hsb/application/HsbReconciliationCallbackTest.java
git commit -m "feat(hsb): implement reconciliation callback with signature verification and file storage"
```

---

### Task 4: Controller 添加对账回调端点

**Files:**
- Modify: `src/main/java/com/aieducenter/payment/hsb/endpoints/api/v1/HsbCallbackController.java`

- [ ] **Step 1: 在 HsbCallbackController 中添加端点**

在 `handleRefundCallback` 方法之后添加：

```java
@PostMapping(value = "/reconciliation", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
@Operation(summary = "接收建行惠市宝对账文件推送")
public ResponseEntity<String> handleReconciliationCallback(
        @RequestParam("File_Smry_Inf") String fileSmryInf,
        @RequestParam("Sign_Inf") String signInf,
        @RequestPart MultipartFile file
) {
    log.info("Received HSB reconciliation file push: fileSmryInf={}, fileName={}, fileSize={}",
        fileSmryInf, file.getOriginalFilename(), file.getSize());
    String response = callbackAppService.handleReconciliationCallback(fileSmryInf, signInf, file);
    return ResponseEntity.ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(response);
}
```

需要在文件顶部添加的 import：

```java
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
```

- [ ] **Step 2: 编译确认无错误**

Run: `mvn compile -pl . -q`
Expected: BUILD SUCCESS

- [ ] **Step 3: Commit**

```bash
git add src/main/java/com/aieducenter/payment/hsb/endpoints/api/v1/HsbCallbackController.java
git commit -m "feat(hsb): add reconciliation callback endpoint"
```

---

### Task 5: 全量测试 & 验证

**Files:** 无新增

- [ ] **Step 1: 运行全量测试**

Run: `mvn test`
Expected: BUILD SUCCESS

- [ ] **Step 2: 检查编译打包**

Run: `mvn package -DskipTests`
Expected: BUILD SUCCESS
