package com.aieducenter.payment.openapi;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.moreThan;
import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.aieducenter.payment.domain.port.PaymentGatewayPort;
import com.aieducenter.payment.domain.port.response.CreatePaymentResponse;
import com.cartisan.openapi.provider.ApiKeyInfo;
import com.cartisan.web.response.ApiResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import java.util.Map;
import java.util.UUID;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

/**
 * 验签端到端集成测试（issue #2 脊柱）：证明 payment 经 app-registry 完成入站签名校验。
 *
 * <p>单一 seam——全上下文 Web 集成：{@code @SpringBootTest} 随机 Web 端口 + WireMock 假扮
 * app-registry 的 bootstrap 端点（{@code GET /api/app-registry/api-keys/{apiKey}}），
 * {@code @DynamicPropertySource} 注入 {@code APP_REGISTRY_BASE_URL}（application.yml 中
 * {@code apikey-service-url} 引用它）指向 WireMock。
 *
 * <p><strong>刻意注入 {@code APP_REGISTRY_BASE_URL} 而非覆盖完整 URL</strong>：让测试走真实的
 * {@code ${APP_REGISTRY_BASE_URL}/api/app-registry/api-keys/{apiKey}} 配置模式——占位符
 * {@code {apiKey}} 丢失类回归会以「WireMock 未被命中」的断言失败暴露。
 *
 * <p>走完 RemoteApiKeyProvider → URL 替换 → 响应解析 → SignatureVerificationFilter →
 * RequestContext 富化 → controller 全链路；不断言 filter 内部机制（由框架自身测试覆盖）。
 * {@code @MockitoBean PaymentGatewayPort} 避免 createPayment 真调工行网关，使签名放行后能返回 200。
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("验签端到端集成测试（app-registry bootstrap）")
class SignatureVerificationIntegrationTest {

    /** WireMock 预置的调用方凭证 + 签名工具使用的同一组凭证。appName 即断言的 businessSystemName。 */
    private static final String CALLER_API_KEY = "test-caller";
    private static final String CALLER_API_SECRET = "test-caller-secret-32bytes!";
    private static final String CALLER_APP_NAME = "测试调用方系统";

    /** app-registry bootstrap 端点路径前缀（与 {@code apikey-service-url} 配置一致）。 */
    private static final String BOOTSTRAP_PATH = "/api/app-registry/api-keys/" + CALLER_API_KEY;

    private static final WireMockServer WIRE_MOCK =
            new WireMockServer(WireMockConfiguration.options().dynamicPort());

    static {
        WIRE_MOCK.start();
        stubBootstrapEndpoint();
    }

    @AfterAll
    static void stopWireMock() {
        WIRE_MOCK.stop();
    }

    private static void stubBootstrapEndpoint() {
        String body;
        try {
            body = new ObjectMapper().writeValueAsString(
                    ApiResponse.ok(new ApiKeyInfo(CALLER_API_KEY, CALLER_APP_NAME, CALLER_API_SECRET)));
        } catch (Exception e) {
            throw new RuntimeException("无法序列化 bootstrap stub 响应体", e);
        }
        WIRE_MOCK.stubFor(get(urlEqualTo(BOOTSTRAP_PATH)).willReturn(okJson(body)));
    }

    /**
     * 注入 {@code APP_REGISTRY_BASE_URL}（application.yml 中 {@code apikey-service-url} 引用它）指向 WireMock，
     * 而非直接覆盖完整 URL —— 让测试走真实的 {@code ${APP_REGISTRY_BASE_URL}/.../{apiKey}} 配置模式。
     */
    @DynamicPropertySource
    static void appRegistryBaseUrl(DynamicPropertyRegistry registry) {
        registry.add("APP_REGISTRY_BASE_URL", WIRE_MOCK::baseUrl);
    }

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    /** 避免 createPayment 真调工行网关：桩为成功，使签名放行后订单落库并返回 200。 */
    @MockitoBean
    private PaymentGatewayPort paymentGatewayPort;

    private final TestSignatureHelper signer = new TestSignatureHelper(CALLER_API_KEY, CALLER_API_SECRET);

    @Test
    @DisplayName("正确签名 → 200，且调用方 appName 作为 businessSystemName 到达订单")
    void given_validSignature_when_createPayment_then_returns200AndBusinessSystemNameIsAppName() throws Exception {
        when(paymentGatewayPort.createPayment(any())).thenReturn(new CreatePaymentResponse(
                true, "0", "ok", "https://qr/example", "BANK-1", 10L, "{}", "{}"));

        String businessOrderNo = "SIG-TEST-" + UUID.randomUUID();
        String body = "{\"businessOrderNo\":\"" + businessOrderNo + "\","
                + "\"amount\":100,\"subject\":\"签名集成测试\","
                + "\"notifyUrl\":\"https://example.com/notify\"}";

        ResponseEntity<String> response = postPayment(body, signer.sign(body));

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        JsonNode root = objectMapper.readTree(response.getBody());
        assertThat(root.at("/data/businessSystemName").asText()).isEqualTo(CALLER_APP_NAME);

        // {apiKey} 占位符替换正确：bootstrap 端点被命中
        WIRE_MOCK.verify(moreThan(0), getRequestedFor(urlEqualTo(BOOTSTRAP_PATH)));
    }

    @Test
    @DisplayName("未签名请求 → 401")
    void given_noSignatureHeaders_when_createPayment_then_returns401() {
        String body = "{\"businessOrderNo\":\"SIG-UNSIGNED-" + UUID.randomUUID() + "\","
                + "\"amount\":100,\"subject\":\"未签名\",\"notifyUrl\":\"https://example.com/notify\"}";

        ResponseEntity<String> response = postPayment(body, Map.of());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }

    @Test
    @DisplayName("签名被篡改 → 401")
    void given_tamperedSignature_when_createPayment_then_returns401() {
        String body = "{\"businessOrderNo\":\"SIG-TAMPERED-" + UUID.randomUUID() + "\","
                + "\"amount\":100,\"subject\":\"篡改\",\"notifyUrl\":\"https://example.com/notify\"}";

        Map<String, String> headers = signer.sign(body);
        // 篡改 X-Sign：与计算出的签名不一致 → HMAC 校验失败
        headers.put(TestSignatureHelper.HEADER_SIGN, headers.get(TestSignatureHelper.HEADER_SIGN) + "deadbeef");

        ResponseEntity<String> response = postPayment(body, headers);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }

    private ResponseEntity<String> postPayment(String body, Map<String, String> extraHeaders) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAll(extraHeaders);
        return restTemplate.postForEntity("/api/v1/payments", new HttpEntity<>(body, headers), String.class);
    }
}
