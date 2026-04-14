package com.aieducenter.payment.infrastructure.icbc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Signature;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("工行回调验签和响应签名测试")
class IcbcCallbackVerifierTest {

    private IcbcCallbackVerifier verifier;
    private String testPublicKey;
    private String testPrivateKey;

    @BeforeEach
    void setUp() throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);
        KeyPair keyPair = generator.generateKeyPair();

        testPublicKey = Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
        testPrivateKey = Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded());

        IcbcConfig config = new IcbcConfig();
        config.setPublicKey(testPublicKey);
        config.setPrivateKey(testPrivateKey);

        verifier = new IcbcCallbackVerifier(config);
    }

    @Test
    @DisplayName("验签通过：正确的RSA签名")
    void verifySignature_withValidSign_returnsTrue() throws Exception {
        Map<String, String> params = new LinkedHashMap<>();
        params.put("api", "/api/cardbusiness/qrcode/consumption/V1");
        params.put("app_id", "10000000000004095781");
        params.put("biz_content", "{\"return_code\":\"0\",\"out_trade_no\":\"PAY001\"}");
        params.put("charset", "utf-8");
        params.put("format", "json");
        params.put("from", "icbc-api");
        params.put("sign_type", "RSA");
        params.put("timestamp", "2024-01-12 17:33:56");

        String path = "/api/v1/payment/callbacks/icbc";

        String plaintext = verifier.buildSignaturePlaintext(path, params);
        String sign = signWithSha1Rsa(plaintext, testPrivateKey);

        assertThat(verifier.verifySignature(path, params, sign)).isTrue();
    }

    @Test
    @DisplayName("验签失败：篡改参数")
    void verifySignature_withTamperedParams_returnsFalse() throws Exception {
        Map<String, String> params = new LinkedHashMap<>();
        params.put("api", "/api/cardbusiness/qrcode/consumption/V1");
        params.put("app_id", "10000000000004095781");
        params.put("biz_content", "{\"return_code\":\"0\"}");
        params.put("charset", "utf-8");
        params.put("format", "json");
        params.put("from", "icbc-api");
        params.put("sign_type", "RSA");
        params.put("timestamp", "2024-01-12 17:33:56");

        String path = "/api/v1/payment/callbacks/icbc";
        String plaintext = verifier.buildSignaturePlaintext(path, params);
        String sign = signWithSha1Rsa(plaintext, testPrivateKey);

        // Tamper with params
        params.put("biz_content", "{\"return_code\":\"1\"}");

        assertThat(verifier.verifySignature(path, params, sign)).isFalse();
    }

    @Test
    @DisplayName("构建签名原文：参数按ASCII升序拼接")
    void buildSignaturePlaintext_sortsParamsByAscii() {
        Map<String, String> params = new LinkedHashMap<>();
        params.put("timestamp", "2024-01-12 17:33:56");
        params.put("api", "/test");
        params.put("charset", "utf-8");

        String result = verifier.buildSignaturePlaintext("/callback", params);

        assertThat(result).isEqualTo("/callback?api=/test&charset=utf-8&timestamp=2024-01-12 17:33:56");
    }

    @Test
    @DisplayName("构建工行应答：包含returnCode字段且格式紧凑")
    void buildResponse_containsReturnCodeAndIsCompact() {
        String response = verifier.buildCallbackResponse("msg-123");

        assertThat(response).contains("\"return_code\":0");
        assertThat(response).contains("\"returnCode\":\"0\"");
        assertThat(response).contains("\"msg_id\":\"msg-123\"");
        assertThat(response).contains("\"sign_type\":\"RSA2\"");
        assertThat(response).contains("\"sign\":");
        assertThat(response).doesNotContain("\n");
        assertThat(response).doesNotContain("\r");
    }

    private String signWithSha1Rsa(String plaintext, String privateKeyBase64) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(privateKeyBase64);
        java.security.spec.PKCS8EncodedKeySpec keySpec = new java.security.spec.PKCS8EncodedKeySpec(keyBytes);
        java.security.PrivateKey privateKey = java.security.KeyFactory.getInstance("RSA").generatePrivate(keySpec);

        Signature signature = Signature.getInstance("SHA1WithRSA");
        signature.initSign(privateKey);
        signature.update(plaintext.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(signature.sign());
    }
}
