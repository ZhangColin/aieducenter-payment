package com.aieducenter.payment.infrastructure.icbc;

import com.cartisan.core.stereotype.Adapter;
import com.cartisan.core.stereotype.PortType;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * 工行回调验签和响应签名
 *
 * <p>处理两件事：
 * <ul>
 *   <li>验签：用工行网关公钥验证回调报文的RSA签名（SHA1WithRSA）</li>
 *   <li>签名：用商户私钥对响应报文签名（SHA256WithRSA，即RSA2）</li>
 * </ul>
 */
@Component
@Adapter(PortType.CLIENT)
@RequiredArgsConstructor
public class IcbcCallbackVerifier {

    private static final Logger log = LoggerFactory.getLogger(IcbcCallbackVerifier.class);

    private final IcbcConfig icbcConfig;

    /**
     * 构建签名原文
     *
     * <p>格式：请求路径path + "?" + 参数按ASCII升序拼接（排除sign字段）</p>
     */
    public String buildSignaturePlaintext(String path, Map<String, String> params) {
        TreeMap<String, String> sortedParams = new TreeMap<>(params);
        sortedParams.remove("sign");

        String queryString = sortedParams.entrySet().stream()
            .map(e -> e.getKey() + "=" + e.getValue())
            .collect(Collectors.joining("&"));

        return path + "?" + queryString;
    }

    /**
     * 验证工行回调签名
     *
     * <p>使用工行网关公钥验证SHA1WithRSA签名</p>
     */
    public boolean verifySignature(String path, Map<String, String> params, String sign) {
        try {
            String plaintext = buildSignaturePlaintext(path, params);
            PublicKey publicKey = loadPublicKey(icbcConfig.getPublicKey());

            Signature verifier = Signature.getInstance("SHA1WithRSA");
            verifier.initVerify(publicKey);
            verifier.update(plaintext.getBytes(StandardCharsets.UTF_8));

            return verifier.verify(Base64.getDecoder().decode(sign));
        } catch (Exception e) {
            log.error("ICBC callback signature verification failed", e);
            return false;
        }
    }

    /**
     * 构建工行回调应答
     *
     * <p>返回完整的应答JSON，包含签名</p>
     * <p>格式要求：response_biz_content, sign_type, sign 顺序，无空格换行</p>
     */
    public String buildCallbackResponse(String msgId) {
        String responseBizContent = "{\"return_code\":0,\"return_msg\":\"success\",\"returnCode\":\"0\",\"msg_id\":\"" + msgId + "\"}";
        String signType = "RSA2";

        String signContent = "\"response_biz_content\":" + responseBizContent + ",\"sign_type\":\"" + signType + "\"";

        String signature = signWithRsa2(signContent);

        return "{\"response_biz_content\":" + responseBizContent + ",\"sign_type\":\"" + signType + "\",\"sign\":\"" + signature + "\"}";
    }

    /**
     * 生成唯一的消息ID
     */
    public String generateResponseMsgId() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    private String signWithRsa2(String content) {
        try {
            PrivateKey privateKey = loadPrivateKey(icbcConfig.getPrivateKey());

            Signature signer = Signature.getInstance("SHA256WithRSA");
            signer.initSign(privateKey);
            signer.update(content.getBytes(StandardCharsets.UTF_8));

            return Base64.getEncoder().encodeToString(signer.sign());
        } catch (Exception e) {
            log.error("Failed to sign response", e);
            throw new RuntimeException("签名失败", e);
        }
    }

    private PublicKey loadPublicKey(String publicKeyBase64) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(publicKeyBase64);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        return KeyFactory.getInstance("RSA").generatePublic(keySpec);
    }

    private PrivateKey loadPrivateKey(String privateKeyBase64) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(privateKeyBase64);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        return KeyFactory.getInstance("RSA").generatePrivate(keySpec);
    }
}
